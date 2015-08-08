/* Propiedad de Entel S.A. Todos los derechos reservados */

package com.esa.ponline.appmobile.web.actions;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.esa.ponline.appmobile.constants.Constants;
import com.esa.ponline.appmobile.vo.login.LoginVO;
import com.esa.ponline.appmobile.vo.plan.PlanesVO;
import com.esa.ponline.appmobile.web.delegate.DelegatePlan;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author ccastro (MZZO) en nombre de Absalon Opazo (Plataformas Online, EntelSA) 
 * version 1.0.0 
 * date 15-09-2014
 */

public class PlanAction extends ActionSupport {

	private static final long serialVersionUID = 6403586997441780780L;
	
	private static final Logger LOGGER = Logger.getLogger(PlanAction.class);
	
	private Map<String, String> idFamiliaPlan = new HashMap<String, String>();
	
	private Map<String, String> xmlPlanes = new HashMap<String, String>();
	
	private DelegatePlan delegatePlan = new DelegatePlan();

	private LoginVO datosClientes;
	
	private PlanesVO planesVO;
	
	private String minutosIlimitados;

	@Override
	public String execute() throws Exception {
		LOGGER.info("Usuario realiza consulta MiPlan");
		datosClientes = (LoginVO) getSession().get("cliente");
		String aaa = String.valueOf(datosClientes.getAaa());
		setIdFamiliaPlan();
		setXmlPlanes();
		setMinutosIlimitados();
		setPlanesVO(delegatePlan.obtienePlanActual(datosClientes.getMsisdn(), datosClientes.getMercado(), aaa,datosClientes.getCuenta()));
		
		//Es un Full MM cambiamos el tipo de plan
		if (planesVO.getInfoPlanMMExcedido() != null){
        		if (planesVO.getInfoPlanMMExcedido().getTipoPlan().equals("13") || planesVO.getInfoPlanMMExcedido().getTipoPlan().equals("14")
        			|| planesVO.getInfoPlanMMExcedido().getTipoPlan().equals("31")) {		    
        		    planesVO.getObjPlan().setIdFamiliaPlan("2");
        		}
		}
		try {
			LOGGER.info("FIN consulta mi plan");
			return datosClientes.getResultNav();
			
			// forTest
//			return "app_ss_high";
//			return "app_ss_medium";
//			return "web_ss_medium";
			// fi forTest
		} catch (Exception e) {
			LOGGER.error("Ha ocurrido un problema en PlanAction " + e.getMessage());
			e.printStackTrace();
			return "error_general";
		}
	}
	
	private Map<String, Object> getSession() {
		Map<String, Object> attibutes = ActionContext.getContext().getSession();
		return attibutes;
	}

	public LoginVO getDatosClientes() {
		return datosClientes;
	}

	public void setDatosClientes(LoginVO datosClientes) {
		this.datosClientes = datosClientes;
	}

	public PlanesVO getPlanesVO() {
		return planesVO;
	}

	public void setPlanesVO(PlanesVO planesVO) {
		this.planesVO = planesVO;
	}

	public Map<String, String> getIdFamiliaPlan() {
		return idFamiliaPlan;
	}

	public void setIdFamiliaPlan() {
		this.idFamiliaPlan.put("PLAN_NORMAL", Constants.TIPO_PLAN_NORMAL.getStringValue());
		this.idFamiliaPlan.put("PLAN_RED", Constants.TIPO_PLAN_RED.getStringValue());
		this.idFamiliaPlan.put("PLAN_GLOBAL", Constants.TIPO_PLAN_GLOBAL.getStringValue());
		this.idFamiliaPlan.put("PLAN_JOVEN", Constants.TIPO_PLAN_JOVEN.getStringValue());
		this.idFamiliaPlan.put("PLAN_FULL", Constants.TIPO_PLAN_FULL.getStringValue());
		this.idFamiliaPlan.put("PLAN_UNICA", Constants.TIPO_PLAN_UNICA.getStringValue());
		this.idFamiliaPlan.put("PLAN_REDFIJA", Constants.TIPO_PLAN_REDFIJA.getStringValue());
		this.idFamiliaPlan.put("PLAN_FAMILIA", Constants.TIPO_PLAN_FAMILIA.getStringValue());
		this.idFamiliaPlan.put("PLAN_DATOS", Constants.TIPO_PLAN_DATOS.getStringValue());
	}

	public Map<String, String> getXmlPlanes() {
		return xmlPlanes;
	}

	public void setXmlPlanes() {
		this.xmlPlanes.put("XMLPLANES_PLANMULTIMEDIA", Constants.XMLPLANES_PLANMULTIMEDIA.getStringValue());
		this.xmlPlanes.put("XMLPLANES_TIPOPLAN13", Constants.XMLPLANES_TIPOPLAN13.getStringValue());
		this.xmlPlanes.put("XMLPLANES_TIPOPLAN14", Constants.XMLPLANES_TIPOPLAN14.getStringValue());
		this.xmlPlanes.put("XMLPLANES_TIPOPLAN31", Constants.XMLPLANES_TIPOPLAN31.getStringValue());
	}

	public String getMinutosIlimitados() {
		return this.minutosIlimitados;
	}

	public void setMinutosIlimitados() {
		this.minutosIlimitados = Constants.MINUTOS_ILIMITADOS.getStringValue();
	}

}
