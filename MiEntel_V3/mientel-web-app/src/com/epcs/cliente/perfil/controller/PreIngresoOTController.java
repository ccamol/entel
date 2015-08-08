/**
 * 
 */
package com.epcs.cliente.perfil.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.event.PhaseEvent;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;

import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.bean.CodeDescBean;
import com.epcs.bean.ConsultaSintomaBean;
import com.epcs.bean.DetalleBodegaVigenteBean;
import com.epcs.bean.EquiposSoporteBean;
import com.epcs.bean.ObtenerBodegasVigentesBean;
import com.epcs.bean.ObtenerEquiposSoporteBean;
import com.epcs.bean.PreIngresoOTBean;
import com.epcs.bean.RutBean;
import com.epcs.bean.SintomaBean;
import com.epcs.bean.UsuarioBean;
import com.epcs.cliente.perfil.delegate.CuentaDelegate;
import com.epcs.cliente.perfil.delegate.PreIngresoOTDelegate;
import com.epcs.recursoti.configuracion.DateHelper;
import com.epcs.recursoti.configuracion.HTMLEntities;
import com.epcs.recursoti.configuracion.JsonHelper;
import com.epcs.recursoti.configuracion.MiEntelBusinessHelper;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.jsf.utils.JSFMessagesHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JSFPortletHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JsfUtil;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

/**
 * @author I2B, haltamiranda
 *
 */
public class PreIngresoOTController implements Serializable {
	
	 private static final long serialVersionUID = 1L;
	 private static final Logger LOGGER = Logger
     .getLogger(PreIngresoOTController.class);
	 
	 private PreIngresoOTDelegate preIngresoOTDelegate; 
	 private CuentaDelegate cuentaDelegate;
	 
	 private ConsultaSintomaBean consultaSintomaBean;
	 private ObtenerEquiposSoporteBean obtenerEquiposSoporteBean;
	 private SelectItem[] listRegiones;
	 private String rutRegiones = MiEntelProperties.getProperty("preingresoot.regiones.rut");//"96806980-2"; //TODO agregar a properties	 

	 public int mercadoPreIngresoOT;
	 public String flagEquipoSoporte;	 
	 private String responseAjax;	 
	 private String emailUsuario;
	 private String fonoContacto;
	 private String msisdn;	

	public void initPreIngresoOT(PhaseEvent event) {
		
			String rutTitular = "";
	    	
	    	try {	    			
	    		    final ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());
		            String mercado = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "mercado");
		            String subMercado = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"subMercado");		            
		            this.msisdn = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "numeroPcsSeleccionado");		            
		            String rutUsuario = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "rutUsuario");		            
		            //String numeroPcs = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "numeroPcsSeleccionado");		            
		            
		            this.flagEquipoSoporte = "";
		                        
		            if((MiEntelBusinessHelper.isMercadoSuscripcion(mercado) || MiEntelBusinessHelper.isMercadoCuentaControlada(mercado))
		            		&&(!subMercado.equals(MiEntelProperties.getProperty("miEntel.subMercadoEMP")))
		            		&& (!subMercado.equals(MiEntelProperties.getProperty("miEntel.subMercadoSGO")))){
		            	
		            	rutTitular = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "rutTitular");
			            rutTitular = rutTitular == null ? ProfileWrapperHelper.getPropertyAsString(profileWrapper,"rutUsuarioSeleccionado") : rutTitular;
		            	
		            	this.mercadoPreIngresoOT = 1;           	
			            
			            //OBTENER EQUIPOS SOPORTE
		            	this.obtenerEquiposSoporteBean = obtenerEquiposSoporte(rutTitular, msisdn);					
						
		            	if(this.obtenerEquiposSoporteBean != null){
		            		if(this.obtenerEquiposSoporteBean.getTotalEquipos()>0){ 
		            			this.flagEquipoSoporte = "true";
		            		}
		            	}
		            }else{
		            	this.mercadoPreIngresoOT = 2;
		            	this.obtenerEquiposSoporteBean = null;
		            }
		            
		            //FALLAS
					this.consultaSintomaBean = obtenerListadoFallas();
		            
		            listRegiones = this.consultarRegionesTiendas();
		            this.consultarEmailUsuario(rutUsuario,msisdn);
		            
	    		
	        }  catch (DAOException e) {
	            LOGGER.error("DAOException en el metodo initPreIngresoOT.", e);
	            JSFMessagesHelper.addServiceErrorMessage("noinfoequipo");
	        } catch (ServiceException e) {
	            LOGGER.info("ServiceException en el metodo initPreIngresoOT.");
	            JSFMessagesHelper.addErrorCodeMessage("general", e.getCodigoRespuesta());
	        } catch (Exception e) {
	            LOGGER.error("Exception en el metodo initPreIngresoOT.", e);
	            JSFMessagesHelper.addServiceErrorMessage("noinfoequipo");
	        }
    }
	
	private ObtenerEquiposSoporteBean obtenerEquiposSoporte(String rutTitular,String Msisdn){
		ObtenerEquiposSoporteBean obtenerEquiposSoporte = new ObtenerEquiposSoporteBean(); 
    	try {    		    				                		                
            RutBean rt = new RutBean(rutTitular);
            String rutSinDV = String.valueOf(rt.getNumero());
            String Dv = String.valueOf(rt.getDigito());
            String Email = "";
    		obtenerEquiposSoporte = this.preIngresoOTDelegate.obtenerEquiposSoporte(Dv,Email,Msisdn,rutSinDV);
        } catch (DAOException e) {
            LOGGER.error("DAOException al obtener equipo soporte", e);
            JSFMessagesHelper.addServiceErrorMessage("noDisponible");
        } catch (ServiceException e) {
            LOGGER.info("ServiceException al obtener equipo soporte");	           
            JSFMessagesHelper.addErrorCodeMessage(e.getCodigoRespuesta(), "preIngresoOT" );
        } catch (Exception e) {
            LOGGER.error("Exception al obtener equipo soporte", e);	            
            JSFMessagesHelper.addServiceErrorMessage("noDisponible");
        }			
		return obtenerEquiposSoporte;
    }
	 
    private ConsultaSintomaBean obtenerListadoFallas(){
		ConsultaSintomaBean consultaSintoma = new ConsultaSintomaBean(); 
    	try {	    		
    		consultaSintoma = this.preIngresoOTDelegate.consultarSintomas();	            
        } catch (DAOException e) {
            LOGGER.error("DAOException al obtener el listado de fallas", e);            
            JSFMessagesHelper.addServiceErrorMessage("noDisponible");
        } catch (ServiceException e) {
            LOGGER.info("ServiceException al obtener el listado de fallas");	           
            JSFMessagesHelper.addErrorCodeMessage(e.getCodigoRespuesta(), "preIngresoOT" );
        } catch (Exception e) {
            LOGGER.error("Exception al obtener el listado de fallas", e);	            
            JSFMessagesHelper.addServiceErrorMessage("noDisponible");
        }			
		return consultaSintoma;
    }
	 
	 /**
	     * Metodo que se encarga de obtener y retornar el valor de la preferencia del portlet en solicitud
	     * dependiendo del mercado como clave asociada al valor.
	     * @return
	     */
    public String getPageLabelPreIngresoOT(){
    	try{
    	  ProfileWrapper profileWrapper = ProfileWrapperHelper
            .getProfile(JSFPortletHelper.getRequest());
    		
    	  return JSFPortletHelper.getPreference(JSFPortletHelper.getPreferencesObject(), ProfileWrapperHelper.getPropertyAsString(
                  profileWrapper, "mercado"), null);
		 
        }catch(Exception e){
        	LOGGER.error("No se ha podido obtener el pageLabel"+ e.getMessage(), e);
        	return "";
        }
    }
	    
	/**
	 * Devueve listado de Regiones     
	 * @return
	 */
    private SelectItem[] consultarRegionesTiendas() {
		SelectItem[] arrayRegiones = new SelectItem[0];
		try {
			Hashtable<String, CodeDescBean> regiones = new Hashtable<String, CodeDescBean>();
			ObtenerBodegasVigentesBean bodegasVigentesBean = this.preIngresoOTDelegate
					.obtenerBodegasVigentesBean(rutRegiones);

			for (DetalleBodegaVigenteBean bodega : bodegasVigentesBean
					.getDetalleBodegaVigente()) {
				String codRegion = bodega.getRegion().split(":")[0];
				String nombreRegion = bodega.getRegion().split(":")[1];
				if (!regiones.containsKey(codRegion.trim())) {
					regiones.put(codRegion.trim(),  new CodeDescBean(codRegion.trim(),
							nombreRegion));
				}
			}
			arrayRegiones = JsfUtil.getSelectItemsCodeDesc(Collections
					.list(regiones.elements()), false);

		} catch (Exception e) {
			LOGGER.error("No se ha podido consultar el listado de regiones", e);

		}
		return arrayRegiones;
	}
	
	/**
	 * 
	 * @param event
	 */
	public void managerAjax(PhaseEvent event){ 
		String accion = JsfUtil.getRequestParameter("accion");
		Hashtable<String, CodeDescBean> ciudades = new Hashtable<String, CodeDescBean>();
		List<DetalleBodegaVigenteBean> tiendas = new ArrayList<DetalleBodegaVigenteBean>();
		EquiposSoporteBean equiposSoporteBean = new EquiposSoporteBean();
		
		try{
			if(accion.equals("loadCiudad")){
			   String idRegion = JsfUtil.getRequestParameter("idRegion");	
			   
			   ObtenerBodegasVigentesBean bodegasVigentesBean = this.preIngresoOTDelegate
				.obtenerBodegasVigentesBean(rutRegiones);
			   
				for (DetalleBodegaVigenteBean bodega : bodegasVigentesBean
						.getDetalleBodegaVigente()) {
					String codRegion = bodega.getRegion().split(":")[0];
					if(codRegion.trim().equals(idRegion)){
						String nomCiudad = bodega.getComuna().split(":")[1];
						if(!ciudades.containsKey(bodega.getComuna())){
						    ciudades.put(bodega.getComuna(), new CodeDescBean(bodega.getComuna(),
								nomCiudad));
						}
					}
				}
			   
				responseAjax = JsonHelper.toJsonResponse(Collections.list(ciudades.elements()));
			   
			}else if(accion.equals("loadTiendas")){
				String idRegion = JsfUtil.getRequestParameter("idRegion");
				String idCiudad = JsfUtil.getRequestParameter("idCiudad");
				
				ObtenerBodegasVigentesBean bodegasVigentesBean = this.preIngresoOTDelegate
					.obtenerBodegasVigentesBean(rutRegiones);
				
				for (DetalleBodegaVigenteBean bodega : bodegasVigentesBean
						.getDetalleBodegaVigente()) {
					String codRegion = bodega.getRegion().split(":")[0];
					String codCiudad = bodega.getComuna();
					if(codRegion.trim().equals(idRegion) && codCiudad.trim().equals(idCiudad) && bodega.getFlagAtiendePublico().equals("S")){
						tiendas.add(bodega);
					}
				}			
				responseAjax = JsonHelper.toJsonResponse(tiendas);
			}else if(accion.equals("loadImei")){
				
				final ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());	           
	            String Msisdn = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "numeroPcs");	            
				String idImei = JsfUtil.getRequestParameter("imei");
				
				LOGGER.info("loadImei Parametros: "+Msisdn+","+idImei);
				
				try{
					this.preIngresoOTDelegate.validaPreOT(Msisdn, idImei);
					
					try{
						equiposSoporteBean = this.preIngresoOTDelegate.consultaDescripcionMovil(idImei);											
						
					}catch (Exception e) {
			            LOGGER.warn("No se pudo obtener la informacion del equipo IMEI :"+idImei);		            
			            equiposSoporteBean = new EquiposSoporteBean();
			            equiposSoporteBean.setImei(idImei);
			            equiposSoporteBean.setMarca("--");
			            equiposSoporteBean.setFechaNegocio("--");
			            equiposSoporteBean.setMesAntiguedad("0");
					}
					
					responseAjax = JsonHelper.toJsonResponse(equiposSoporteBean);
					
				}catch (ServiceException e) {
		            LOGGER.info("ServiceException en el (preIngresoOTDelegate.validaPreOT) metodo managerAjax accion=loadImei");		            
					responseAjax = JsonHelper.toJsonServiceErrorMessage("preIngresoOT", e.getCodigoRespuesta());
				}
						
			}else if(accion.equals("loadPreIngresoOT")){
				
				final ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());				
				String movil = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "numeroPcsSeleccionado");
				String nombreUsuario = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "nombreUsuarioSeleccionado");
	    		String serie = JsfUtil.getRequestParameter("imei");
	    		String fonoContacto = formatearPhoneNumber(JsfUtil.getRequestParameter("fonoContacto"));
	    		String mailContacto = JsfUtil.getRequestParameter("mailContacto");
	    		String comentario = JsfUtil.getRequestParameter("comentario");
	    		String sintomas =  JsfUtil.getRequestParameter("sintomas");
	    		String marca =  JsfUtil.getRequestParameter("marca");
	    		String accesorios = JsfUtil.getRequestParameter("accesorios");
	    		String nombreSintomas = JsfUtil.getRequestParameter("nombreSintomas");
	    		PreIngresoOTBean preIngresoOTBean = new PreIngresoOTBean();
	    		ArrayList<SintomaBean> arraySintomaBean = new ArrayList<SintomaBean>(); 
	    		
	    		StringTokenizer token = new StringTokenizer(sintomas, ",");
	    		String codSintoma="";
	    		LOGGER.info("loadPreIngresoOT Inicio del token");
	    		while(token.hasMoreTokens()){
	    			codSintoma=token.nextToken().trim();
	    			if(!codSintoma.equals("")){	    			
		    			SintomaBean sintomaBean = new SintomaBean();	    			
		    			sintomaBean.setCodigo(codSintoma);
		    			LOGGER.info("loadPreIngresoOT Parametros: codigo:"+codSintoma+" ,bean:"+sintomaBean);
		    			arraySintomaBean.add(sintomaBean);
	    			}
    			}
	    		
	    		LOGGER.info("loadPreIngresoOT Parametros: "+nombreSintomas+","+accesorios+","+nombreUsuario+","+marca+","+movil+", "+serie+", "+fonoContacto+", "+mailContacto+", "+comentario);
	    		
	    		try{
	    		    preIngresoOTBean = this.preIngresoOTDelegate.preIngresoOT(movil, serie, fonoContacto, mailContacto, comentario, arraySintomaBean);
	    		    String nroOrden = preIngresoOTBean.getNmroOrden();
	    		    enviarMail(nroOrden, marca, serie, nombreSintomas, accesorios, comentario, mailContacto, nombreUsuario);    		    
					responseAjax = JsonHelper.toJsonResponse(preIngresoOTBean);
	    		} catch (ServiceException e) {
		            LOGGER.info("ServiceException en el (guardar preIngreso servicio tecnico) metodo managerAjax accion=loadPreIngresoOT");		            
					responseAjax = JsonHelper.toJsonServiceErrorMessage("preIngresoOT", e.getCodigoRespuesta());
				}
			}
		
		}catch (Exception e) {
			LOGGER.error("Error al ejecutar accion "+accion, e);
			responseAjax = JsonHelper.toJsonServiceErrorMessage("general","0001");
		}
	}
	
	/**
	 * Devuelve el mail del usuario en sesion
	 * @return
	 */
	private void consultarEmailUsuario(String rutUsuario,String numeroPcs){		
		try{	
     		UsuarioBean usuario = cuentaDelegate.obtenerUsuario(numeroPcs, rutUsuario);     		
     		this.emailUsuario = usuario.getEmail() != null ?  usuario.getEmail().trim() : "";
     		this.fonoContacto = usuario.getPrefijoTelefonoAdicional() + "-" + usuario.getTelefonoAdicional();     		
		}catch (Exception e) {
			LOGGER.error("Error en consultarEmailUsuario", e);
		}				
	}
	
	/**
	 * 
	 * @param nroOT
	 * @param marca
	 * @param imei
	 * @param fallas
	 * @param accesorios
	 * @param obs
	 * @param mailTo
	 * @param nombre
	 */
	private void enviarMail(String nroOT, String marca, String imei,
			String fallas, String accesorios, String obs, String mailTo,
			String nombre) {
		String idContenido = "templateMailPreingresoOT";
		String from = "";
		String mailContent = "";
		String mailSubject = "";
		InputStream in = null;
		
		try {

			mailSubject = JSFPortletHelper.getNodePropertyAsString("id", idContenido, "subject");
			from = JSFPortletHelper.getNodePropertyAsString("id", idContenido, "from");
			
			in = JSFPortletHelper.getNodePropertyBinaryValue("id", idContenido, "attachment");
			BufferedReader br=new BufferedReader(new InputStreamReader(in));
			String read = null;
			StringBuffer sb = new StringBuffer();
			while((read = br.readLine()) != null) {
				sb.append(read);
				}
			
			mailContent = sb.toString();

		} catch (Exception e) {
			LOGGER
					.error("No se pudo obtener contenido: idContenido : templateMailPreingresoOT");
		}finally{
			if(in != null){ 
				try{in.close();	}catch (Exception e2) {}
			}
		}

		if (mailContent != null && !mailContent.equals("")) {
			mailContent = mailContent.replace("#NOMBRE#", HTMLEntities.htmlentities(nombre));
			mailContent = mailContent.replace("#NUMEROOT#", nroOT);
			mailContent = mailContent.replace("#FECHAPREINGRESO#", DateHelper
					.format(new Date(), DateHelper.FORMAT_ddMMyyyy_HYPHEN));
			mailContent = mailContent.replace("#MARCAMODELO#", HTMLEntities.htmlentities(marca));
			mailContent = mailContent.replace("#IMEI#", imei);
			mailContent = mailContent.replace("#FALLAS#", HTMLEntities.htmlentities(fallas));
			mailContent = mailContent.replace("#OBSERVACIONES#", HTMLEntities.htmlentities(obs));
			mailContent = mailContent.replace("#ACCESORIOS#", HTMLEntities.htmlentities(accesorios));
			try {
				this.preIngresoOTDelegate.enviarMail(from, mailTo, mailContent,mailSubject);
			} catch (Exception e) {
				LOGGER.warn("No fue posible enviar el mail de confirmacion", e);
			}

		}
	}
	
	private String formatearPhoneNumber(String sPhoneNumber){
		  String PhoneNumber = "";	  
		  Pattern patternCel = Pattern.compile("\\d{1}-\\d{8}"); 
		  Pattern patternFijo = Pattern.compile("\\d{2}-\\d{7}");		       
	      Matcher matcher = patternCel.matcher(sPhoneNumber);
	      Matcher matcherFijo = patternFijo.matcher(sPhoneNumber);
		  
	      if (matcher.matches() || matcherFijo.matches() ) { 
	    	  PhoneNumber = sPhoneNumber; 
		  }
		  return PhoneNumber;				
	}	
	
	 
	 /**
	 * @return the mercadoPreIngresoOT
	 */
	public int getMercadoPreIngresoOT() {
		return mercadoPreIngresoOT;
	}

	/**
	 * @param mercadoPreIngresoOT the mercadoPreIngresoOT to set
	 */
	public void setMercadoPreIngresoOT(int mercadoPreIngresoOT) {
		this.mercadoPreIngresoOT = mercadoPreIngresoOT;
	}


	public void setResponseAjax(String responseAjax) {
		this.responseAjax = responseAjax;
	}


	public String getResponseAjax() {
		return responseAjax;
	}
	
	/**
	 * @return the obtenerEquiposSoporteBean
	 */
	public ObtenerEquiposSoporteBean getObtenerEquiposSoporteBean() {
		return obtenerEquiposSoporteBean;
	}

	/**
	 * @param obtenerEquiposSoporteBean the obtenerEquiposSoporteBean to set
	 */
	public void setObtenerEquiposSoporteBean(
			ObtenerEquiposSoporteBean obtenerEquiposSoporteBean) {
		this.obtenerEquiposSoporteBean = obtenerEquiposSoporteBean;
	}
	
	/**
	 * @return the preIngresoOTDelegate
	 */
	public PreIngresoOTDelegate getPreIngresoOTDelegate() {
		return preIngresoOTDelegate;
	}

	/**
	 * @param preIngresoOTDelegate the preIngresoOTDelegate to set
	 */
	public void setPreIngresoOTDelegate(PreIngresoOTDelegate preIngresoOTDelegate) {
		this.preIngresoOTDelegate = preIngresoOTDelegate;
	}
	
	/**
	 * @return the flagEquipoSoporte
	 */
	public String getFlagEquipoSoporte() {
		return flagEquipoSoporte;
	}

	/**
	 * @param flagEquipoSoporte the flagEquipoSoporte to set
	 */
	public void setFlagEquipoSoporte(String flagEquipoSoporte) {
		this.flagEquipoSoporte = flagEquipoSoporte;
	}
	
	/**
	 * @return the consultaSintomaBean
	 */
	public ConsultaSintomaBean getConsultaSintomaBean() {
		return consultaSintomaBean;
	}

	/**
	 * @param consultaSintomaBean the consultaSintomaBean to set
	 */
	public void setConsultaSintomaBean(ConsultaSintomaBean consultaSintomaBean) {
		this.consultaSintomaBean = consultaSintomaBean;
	}

	public void setListRegiones(SelectItem[] listRegiones) {
		this.listRegiones = listRegiones;
	}

	public SelectItem[] getListRegiones() {
		return listRegiones;
	}
	
	public CuentaDelegate getCuentaDelegate() {
			return cuentaDelegate;
		}

	public void setCuentaDelegate(CuentaDelegate cuentaDelegate) {
			this.cuentaDelegate = cuentaDelegate;
		}

	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}

	public String getEmailUsuario() {
		return emailUsuario;
	}	
	
	public String getFonoContacto() {
		return fonoContacto;
	}
	
	public void setFonoContacto(String fonoContacto) {
		this.fonoContacto = fonoContacto;
	}	
	
	public String getMsisdn() {
		return msisdn;
	}
	
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
}
