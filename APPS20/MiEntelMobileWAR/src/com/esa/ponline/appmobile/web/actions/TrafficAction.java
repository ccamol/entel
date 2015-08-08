/* Propiedad de Entel S.A. Todos los derechos reservados */

package com.esa.ponline.appmobile.web.actions;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;

import com.esa.billing.orderingprod.t.validarplanmmautogestion.BILTValidarPlanMMAutogestionPortType;
import com.esa.billing.orderingprod.t.validarplanmmautogestion.ValidarPlanMMAutogestionFaultMessage;
import com.esa.billing.orderingprod.t.validarplanmmautogestion.request.ValidarPlanMMAutogestionReq;
import com.esa.billing.orderingprod.t.validarplanmmautogestion.response.ValidarPlanMMAutogestionRes;
import com.esa.ponline.appmobile.constants.Constants;
import com.esa.ponline.appmobile.exceptions.EntelServicesLocatorException;
import com.esa.ponline.appmobile.util.Config;
import com.esa.ponline.appmobile.util.TimeWatch;
import com.esa.ponline.appmobile.vo.PlanMMAG_VO;
import com.esa.ponline.appmobile.vo.PlanMultimediaVO;
import com.esa.ponline.appmobile.vo.login.LoginVO;
import com.esa.ponline.appmobile.vo.traffic.TrafficVO;
import com.esa.ponline.appmobile.web.delegate.DelegateTraffic;
import com.esa.ponline.appmobile.ws.EntelServices;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author ccastro (MZZO) en nombre de Absalon Opazo (EntelSA)
 * @version 1.0
 */

public class TrafficAction extends ActionSupport {
    
	private static final long serialVersionUID = -3221417135681885623L;
    
	private static final Logger LOGGER = Logger.getLogger(TrafficAction.class);
	
	private DelegateTraffic delegate = new DelegateTraffic();
	
	private LoginVO datosClientes;
	
	private TrafficVO trafico;
	
	private String restriccionZona;
	  
	public String execute() {
		try {
			
			// forza error
//			String cdf = null;
//			cdf.length();
			
		return trafico();
		} catch (Exception e) {
			LOGGER.error("Ha ocurrido un problema en TrafficAction " + e.getMessage());
			e.printStackTrace();
			return "error_general";
		}
	}
    
	public String trafico(){
		String pagina="";
		List<TrafficVO> traficos = new ArrayList<TrafficVO>();
		List<PlanMultimediaVO> multimedia = new ArrayList<PlanMultimediaVO>();
	    List<PlanMMAG_VO> multimediaMMAG = new ArrayList<PlanMMAG_VO>();
		
		trafico = new TrafficVO();
		
		datosClientes = (LoginVO) getSession().get("cliente");
			if(datosClientes==null){
				datosClientes = (LoginVO) getSession().get("cliente_registro");
			}
        
		if (!datosClientes.getMercado().equalsIgnoreCase("ss")){
			return "nosoportado";
		}
		
		try {
		    LOGGER.info ("Cantidad de marcas fix zona activas :" + Config.cacheRestriccionZona.size() );
		    //Config.cacheRestriccionZona.put(datosClientes.getMsisdn(), true);
	            if (Config.cacheRestriccionZona.get(datosClientes.getMsisdn())){
	        	//Config.cacheRestriccionZona.put(datosClientes.getMsisdn(), false);
	        	restriccionZona="restringido";
	            }
		    
			traficos = delegate.consultaTrafico(datosClientes.getMsisdn(),String.valueOf(datosClientes.getAaa()));
		} catch (Exception e) {
			LOGGER.error("problema en la llamada a consulta trafico para msisdn [" + datosClientes.getMsisdn() + "]"
					+ e.getMessage());
			e.printStackTrace();
		}
		
		try {
			multimedia = delegate.consultaTraficoExcedido(datosClientes.getMsisdn());
			
			//TODO multimediaAutoGestion
			
			if (esMMAutogestion(datosClientes.getMsisdn())){
			    multimediaMMAG = delegate.consultarTraficoMMAGExcedido(datosClientes.getMsisdn());
			}
		} catch (Exception e) {
			LOGGER.error("problema en la llamada a consulta trafico excedido para msisdn [" + datosClientes.getMsisdn()
					+ "]" + e.getMessage());
			e.printStackTrace();
		}

		if (traficos != null && traficos.iterator().hasNext()) {
			for (int i = 0; i < traficos.size(); i++) {
				if (traficos.get(i).getTipoTrafico().equals(Constants.SERVICIO_TRAFICO_VOZ.getStringValue())) {
					trafico.setMinutosUtilizados(traficos.get(i).getTrafico());
					trafico.setFechaVoz(traficos.get(i).getPeriodo());
				} else if (traficos.get(i).getTipoTrafico().equals(Constants.SERVICIO_TRAFICO_SMS.getStringValue())) {
					trafico.setSmsMMS(traficos.get(i).getTrafico());
					trafico.setFechaMensajeria(traficos.get(i).getPeriodo());
					trafico.setOtros(traficos.get(i).getOtros());
				} else if (traficos.get(i).getTipoTrafico()
						.equals(Constants.SERVICIO_TRAFICO_BANDA_ANCHA_MOVIL.getStringValue())) {
					trafico.setBam(traficos.get(i).getTrafico());
					trafico.setFechaDatos(traficos.get(i).getPeriodo());
				} else if (traficos.get(i).getTipoTrafico()
						.equals(Constants.SERVICIO_TRAFICO_BLACKBERRY.getStringValue())) {
					trafico.setBbry(traficos.get(i).getTrafico());
				} else if (traficos.get(i).getTipoTrafico().equals(Constants.SERVICIO_TRAFICO_ISHOP.getStringValue())) {
					trafico.setIshop(traficos.get(i).getTrafico());
				}
			}
		}

		//TODO validar si variables multimedia y multimediaMMAG se duplican
		if (multimedia != null && multimedia.iterator().hasNext()) {
			for (int i = 0; i < multimedia.size(); i++) {
				//multimedia.get(i).setpConsumido(multimedia.get(i).getpConsumido());
				trafico.setMultimediaTrafico(multimedia.get(i).getTotalTrafico());
				trafico.setPorcentaje(multimedia.get(i).getpConsumido());
				trafico.setMultimediaTraficoConsumido(multimedia.get(i).getConsumido());
				trafico.setPlan(multimedia.get(i).getDescPlanBSCS());
				trafico.setTraficoExcedido(multimedia.get(i).getTraficoExcedido() + Constants.MEGAS.getStringValue());
				trafico.setTieneExcedido(isExcedido(multimedia.get(i).getTraficoExcedido()));
				trafico.setFechaDatos(multimedia.get(i).getFechaReferencia());
				trafico.setHoraReferencia(multimedia.get(i).getHoraReferencia());
				trafico.setValorAdicional(multimedia.get(i).getValorMBExcedido());
				pagina="full_";
			}
		} else if (multimediaMMAG !=null && multimediaMMAG.iterator().hasNext()) {
		    //TODO MMAutogestion Trafico
	          for (int i = 0; i < multimediaMMAG.size(); i++) {
	                //multimedia.get(i).setpConsumido(multimedia.get(i).getpConsumido());
	                trafico.setMultimediaTrafico(multimediaMMAG.get(i).getCuotaTrafico());
	                trafico.setPorcentaje(multimediaMMAG.get(i).getPorcentajeConsumido());
	                trafico.setMultimediaTraficoConsumido(multimediaMMAG.get(i).getCosumoCap1());
	                trafico.setPlan(multimediaMMAG.get(i).getDescPlan());
	                
	                Double consumoCap2 = Double.valueOf(multimediaMMAG.get(i).getCosumoCap2());
	                Double consumoCap3 = Double.valueOf(multimediaMMAG.get(i).getCosumoCap3());
	                        
	                DecimalFormat df = new DecimalFormat("#.00"); 
	                trafico.setTraficoExcedido(String.valueOf(df.format(consumoCap2+consumoCap3)) + Constants.MEGAS.getStringValue());
	                trafico.setTieneExcedido(isExcedido(multimediaMMAG.get(i).getCosumoCap2()));
	                trafico.setFechaDatos(multimediaMMAG.get(i).getFechaConsulta());
	                trafico.setHoraReferencia(multimediaMMAG.get(i).getFechaHoraReferencia());
	                //TODO valor en duro, validar desde que servicio se saca, sino llevar a property
//	                trafico.setValorAdicional(null);
	                pagina="full_";
	            }
		    
		}else{
			// TODO No aplica para trafico que no seea excedido, no llena la vista			
			try {
				trafico.setPlan(delegate.consultaNombrePlan(datosClientes.getMsisdn()));	
			} catch (Exception e) {
				LOGGER.error("Problema en la llamada a consulta nombre plan " + e.getMessage());
				e.printStackTrace();
			}
			trafico.setMultimediaTrafico("");
			trafico.setPorcentaje("");
			trafico.setMultimediaTraficoConsumido("");			
			trafico.setHoraReferencia("");

			// TODO: Hay que llegar esto a una funcion
			Long bytesBam=NumberUtils.toLong(trafico.getBam(), 0);
			Long bytesBbry=NumberUtils.toLong(trafico.getBbry(), 0);
			Long bytesIshop=NumberUtils.toLong(trafico.getIshop(), 0);

			int cont=0;
			Double bytes=bytesBbry.doubleValue()+bytesIshop.doubleValue()+bytesBam.doubleValue();
			while (bytes>1024){
				bytes=bytes/1024;
				cont++;
			}
			String sufijo = "";
			switch (cont) {
			case 0:
				sufijo = " bytes";
				break;
			case 1:
				sufijo = " KB";
				break;
			case 2:
				sufijo = " MB";
				break;
			case 3:
				sufijo = " GB";
				break;
			}
			DecimalFormat df = new DecimalFormat("#.##");		        
			trafico.setSuma(df.format(bytes)+sufijo);
		}
        
		return pagina+datosClientes.getResultNav();
	}
    
	private boolean esMMAutogestion(String msisdn) {
        ValidarPlanMMAutogestionReq validarPlanMMReq = new ValidarPlanMMAutogestionReq();
        validarPlanMMReq.setMsisdn(Constants.PREFIJO_ENTEL.getStringValue()+msisdn);
        ValidarPlanMMAutogestionRes response = null;
        
        try {
            response = getPlanMMAutogestionConnection().validarPlanMMAutogestion(validarPlanMMReq);     
        } catch (ValidarPlanMMAutogestionFaultMessage e) {
            LOGGER.error("No es posible obtener respuesta para validar cliente MMAutogestion");
            e.printStackTrace();
        }
        
        if(String.valueOf(response.getResultado().getCodigoResp().intValue()).equals("0")){
            LOGGER.info("MSISDN consultado ->" + validarPlanMMReq.getMsisdn() + "<- pertenece a MMAutogestion");
            return true;
        }
        LOGGER.info("MSISDN consultado ->" + validarPlanMMReq.getMsisdn() + "<- NO pertenece a MMAutogestion");
        return false;
    }

    private String isExcedido(String traficoExcedido) {
		double datosExcedidos = Double.parseDouble(traficoExcedido.replaceAll(",", ""));
			if(datosExcedidos > 0){
				LOGGER.info("Usuario posee datos Excedidos");
				return "tiene";
			}else{
				LOGGER.info("Usuario no posee datos Excedidos");
				return "notiene";
			}
	}
	
    /**
     * Gets the account service connection.
     *
     * @return the account service connection
     */
    private BILTValidarPlanMMAutogestionPortType getPlanMMAutogestionConnection() {
        TimeWatch watch = TimeWatch.start();
        BILTValidarPlanMMAutogestionPortType port = null;

        try {
            port = EntelServices.getMMAutoGestionServicePort().getBILTValidarPlanMMAutogestionBindingQSPort();
        } catch (EntelServicesLocatorException e) {
            LOGGER.error("Error al inicializar el Port " + BILTValidarPlanMMAutogestionPortType.class, e);
            LOGGER.error("Tiempo: " + watch.time() + " | " + BILTValidarPlanMMAutogestionPortType.class);
            e.printStackTrace();
        } catch (Exception e) {
            LOGGER.error("Error al inicializar el Port " + BILTValidarPlanMMAutogestionPortType.class, e);
            LOGGER.error("Tiempo: " + watch.time() + " | " + BILTValidarPlanMMAutogestionPortType.class);
            e.printStackTrace();
        }
        return port;
    }

	public Map<String, Object> getSession() {
		HttpServletRequest req = ServletActionContext.getRequest();
	    	SessionMap<String, Object> attributes = new SessionMap<String, Object>(req);
		return attributes;
	}
	
	public TrafficVO getTrafico() {
		return trafico;
	}
    
	public void setTrafico(TrafficVO trafico) {
		this.trafico = trafico;
	}
    
	public LoginVO getDatosClientes() {
		return datosClientes;
	}
    
	public void setDatosClientes(LoginVO datosClientes) {
		this.datosClientes = datosClientes;
	}
	

	    public String getRestriccionZona() {
	        return restriccionZona;
	    }

	    public void setRestriccionZona(String restriccionZona) {
	        this.restriccionZona = restriccionZona;
	    }
}
