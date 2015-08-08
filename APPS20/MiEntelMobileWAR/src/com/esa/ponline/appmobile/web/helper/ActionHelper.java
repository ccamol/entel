/* Propiedad de Entel S.A. Todos los derechos reservados */

package com.esa.ponline.appmobile.web.helper;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.apache.struts2.dispatcher.SessionMap;

import com.epcs.cliente.perfil.ClientePerfilServicePortType;
import com.epcs.cliente.perfil.types.ConsultarPerfilamientoType;
import com.epcs.cliente.perfil.types.ConsultarUsuarioBuicType;
import com.epcs.cliente.perfil.types.RespuestaType;
import com.epcs.cliente.perfil.types.ResultadoConsultarPerfilamientoType;
import com.epcs.cliente.perfil.types.ResultadoConsultarUsuarioBuicType;
import com.esa.ponline.appmobile.constants.Constants;
import com.esa.ponline.appmobile.exceptions.EntelServicesLocatorException;
import com.esa.ponline.appmobile.util.DefineTemplate;
import com.esa.ponline.appmobile.util.Rut;
import com.esa.ponline.appmobile.util.TimeWatch;
import com.esa.ponline.appmobile.vo.login.LoginVO;
import com.esa.ponline.appmobile.web.actions.SigninAction;
import com.esa.ponline.appmobile.ws.EntelServices;
import org.apache.log4j.MDC;

/**
 * @author ccastro (MZZO) en nombre de Absalon Opazo (EntelSA)
 * @version 1.0
 */

public class ActionHelper {
	
	private static final Logger LOGGER = Logger.getLogger(SigninAction.class);
	
	private DefineTemplate dt = new DefineTemplate();
	
	private LoginVO cliente;
	
	public SessionMap<String, Object> creaSession(LoginVO user, HttpServletRequest req, boolean isWeb) {
		SessionMap<String, Object> session = new SessionMap<String, Object>(req);
		String userAgent = req.getHeader("User-agent");
		
		cliente = new LoginVO();
//		String[] datosCliente = userData.split(",");
		cliente.setMsisdn(user.getMsisdn());
		cliente.setRut(user.getRut());
		cliente.setIsWeb(isWeb);
		
        ResultadoConsultarPerfilamientoType response = obtienePerfil(cliente.getMsisdn());
        
		if(response.getRespuesta().getCodigo().equals("0000")){
			cliente.setNombre(response.getResumenPerfilamiento().getNombreUsuario());
			cliente.setNombreTitular(response.getResumenPerfilamiento().getNombreTitular());
			cliente.setMercado(response.getResumenPerfilamiento().getMercado());
			cliente.setAaa(Integer.parseInt(response.getResumenPerfilamiento().getAaa()));
			cliente.setEsVoz(response.getResumenPerfilamiento().getFlagBam().equalsIgnoreCase("0") ? true : false);			
			cliente.setResultNav(dt.defineTemplateNav(cliente, userAgent));
			cliente.setCuenta(response.getResumenPerfilamiento().getNumeroCuenta());
			
		}else{
			LOGGER.error("cliente [" + cliente.getMsisdn() + "] no posee mercado");
			cliente.setMercado("");
			cliente.setNombre("");
			cliente.setNombreTitular("");
		}
		String idSession = new String(Hex.encodeHex(DigestUtils.md5(req.getSession().getId())));
		LOGGER.info("Se genera session para usuario ["+cliente.getMsisdn()+"] con id ["+idSession+"]");
		MDC.put("idSession", idSession);
		session.put(Constants.CLIENTE.getStringValue(), cliente);
		
		return session;
	}
	
	public ResultadoConsultarPerfilamientoType obtienePerfil(String msisdn) {
	    	TimeWatch watch = TimeWatch.start();
		ConsultarPerfilamientoType request = new ConsultarPerfilamientoType();
        request.setMsisdn(msisdn);

		ResultadoConsultarPerfilamientoType response;
		try {
			response = EntelServices.getProfileInstanceService().getClientePerfilServicePort()
					.consultarPerfilamiento(request);
			
	        LOGGER.info("Servicio ClientePerfilService responde: ");
	        LOGGER.info("Response Code: " + response.getRespuesta().getCodigo());
	        LOGGER.info("Response Descripcion: " + response.getRespuesta().getDescripcion());
	        LOGGER.info("Tiempo:" + watch.time());
		} catch (Exception e) {
			LOGGER.error("Exception caught on Service invocation: " + "consultarPerfilamiento", e);
			LOGGER.error("Tiempo:" + watch.time());
            response=new ResultadoConsultarPerfilamientoType();            
            RespuestaType value = new RespuestaType();
            value.setCodigo("0001");
			response.setRespuesta(value);
        }	
        
        return response;
	}
	
	public ResultadoConsultarUsuarioBuicType obtenerValorTipoBolsa(String msisdn){
	    //TODO: y el try/catch
	    	TimeWatch watch = TimeWatch.start();
		ClientePerfilServicePortType port = null;
		try {
			port = EntelServices.getProfileInstanceService().getClientePerfilServicePort();
			
		} catch (EntelServicesLocatorException error) {
			LOGGER.error("Problema en servicio " + error.getMessage());
			error.printStackTrace();
		}
		ConsultarUsuarioBuicType usuario = new ConsultarUsuarioBuicType();
		usuario.setMsisdn(msisdn);
		
		ResultadoConsultarUsuarioBuicType response = port.consultarUsuarioBuic(usuario);
		LOGGER.info("Tiempo:" + watch.time());
		
		return response;
	}
}