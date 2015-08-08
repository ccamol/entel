/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.erp.seguridad.controller;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.bean.MarcaEstadisticaBean;
import com.epcs.erp.seguridad.delegate.SeguridadDelegate;
import com.epcs.erp.seguridad.util.AccesoMovilNoBuic;
import com.epcs.erp.seguridad.util.AccesoMovilRutNoAsociado;
import com.epcs.erp.seguridad.util.AccesoNormal;
import com.epcs.inteligencianegocio.satisfaccioncliente.delegate.EstadisticasDelegate;
import com.epcs.inteligencianegocio.satisfaccioncliente.util.EstadisticasHelper;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.Utils;
import com.epcs.recursoti.configuracion.jsf.utils.JSFMessagesHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JSFPortletHelper;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;


/**
 * @author gcastro (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class LoginController implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = Logger
            .getLogger(LoginController.class);
    
    private static final String DEFAULT_USERNAME_CREDENTIAL = "miEntel.movil.tag";
	private static final String DEFAULT_RUT_CREDENTIAL = "miEntel.rut.tag";
	private static final String DEFAULT_PASSWORD_CREDENTIAL = "miEntel.clave.tag";

	private static final String COD_MOVIL_NOBUIC = MiEntelProperties
			.getProperty("miEntel.autenticacion.movilNoBuic");
	
	private static final String COD_MOVILRUT_NOASOCIADO = MiEntelProperties
			.getProperty("miEntel.autenticacion.movilRutNoAsociado");
	
	private static final String COD_MOVIL_BLOQ_INTENTOS_FALLIDOS = MiEntelProperties
    		.getProperty("miEntel.autenticacion.movilBloqIntentosFallidos");
	
	
		
	private String urlSolicitaClave;

	private SeguridadDelegate seguridadDelegate;
	
	private EstadisticasDelegate estadisticasDelegate;
 
    private MarcaEstadisticaBean marcaEstadisticaBean;

	/**
	 * @return the urlSolicitaClave
	 */
	public String getUrlSolicitaClave() {
		return urlSolicitaClave;
	}

	/**
	 * @param urlSolicitaClave the urlSolicitaClave to set
	 */
	public void setUrlSolicitaClave(String urlSolicitaClave) {
		this.urlSolicitaClave = urlSolicitaClave;
	}

	/**
	 * 
	 * @param event
	 */
	public void init(PhaseEvent event) {
		LOGGER.info("ESTO ES EL INICIO DEL LOGIN");
		System.out.println("ESTO ES EL INICIO DEL LOGIN");
		HttpServletRequest request = JSFPortletHelper
    	.getRequest(FacesContext.getCurrentInstance());
		
		HttpSession session = request.getSession();
		String acceso = (String)session.getAttribute("acceso");
		
		if(Utils.isNotEmptyString(acceso)) {
            session.invalidate();
		}
		
		this.urlSolicitaClave = MiEntelProperties.getProperty("mienEntel.login.solicitaClave");
		
	}
    
	/**
	 * 
	 */
    public String login(){
    	
    	HttpServletRequest request = JSFPortletHelper
    	.getRequest(FacesContext.getCurrentInstance());
    	
    	HttpServletResponse response = JSFPortletHelper
    	.getResponse();
    	
    	String msisdn = "";
    	String clave = "";
    	String rut = "";
    	
    	try{
    	

	        msisdn = request.getParameter(MiEntelProperties
	                .getProperty(DEFAULT_USERNAME_CREDENTIAL));
	        clave = request.getParameter(MiEntelProperties
	                .getProperty(DEFAULT_PASSWORD_CREDENTIAL));
	        rut = request.getParameter(MiEntelProperties
	                .getProperty(DEFAULT_RUT_CREDENTIAL));
	        
	        if (msisdn != null && clave != null) {
	        	
		        seguridadDelegate = new SeguridadDelegate();
		        seguridadDelegate.autenticarUsuario(msisdn, rut, clave);
		        
		        /* acceso Normal */
		        AccesoNormal accesoNormal = new AccesoNormal();
		        accesoNormal.acceder(request, response, msisdn, rut, clave);
		        
		        //agregar marca estadistica 
	            this.agregarMarcaEstadistica(request.getRemoteAddr(), EstadisticasHelper.TRANSACCION_OK , msisdn, rut, request.getParameter("flagCautivo"));
		        
	        }

    	} catch (DAOException e) {
    		
    		 LOGGER.error("DAOException al intentar autenticar usuario", e);
    		 JSFMessagesHelper.addErrorCodeMessage("autenticacion","general");
    		 
        } catch (ServiceException e) {
        	
        	 if( e.getCodigoRespuesta().equals(COD_MOVIL_NOBUIC) ){

        		/* acceso Movil No registrado en BUIC */
        		AccesoMovilNoBuic accesoMovilNoBuic = new AccesoMovilNoBuic();
                accesoMovilNoBuic.acceder(msisdn, rut, clave);
    			
    		}else if( e.getCodigoRespuesta().equals(COD_MOVILRUT_NOASOCIADO) ){

    			/* acceso Movil y Rut No Asociados */
    			AccesoMovilRutNoAsociado accesoMovilRutNoAsociado = new AccesoMovilRutNoAsociado();
    			accesoMovilRutNoAsociado.acceder(msisdn, rut, clave);
    		}else if( e.getCodigoRespuesta().equals(COD_MOVIL_BLOQ_INTENTOS_FALLIDOS) ){

    			/* acceso bloqueado por demasiados intentos fallidos */
    			LOGGER.info("Error de servicio: " + e.getCodigoRespuesta()
                        + " - " + e.getDescripcionRespuesta());
                JSFMessagesHelper.addErrorCodeMessage("autenticacion","accesobloqueado");
    		}else{
    			LOGGER.error("Error de servicio codigo: " + e.getCodigoRespuesta()
                        + " - " + e.getDescripcionRespuesta());
                JSFMessagesHelper.addErrorCodeMessage("autenticacion","noautentica");

    		}
        
        } catch (Exception e) {
            LOGGER.error("No fue posible obtener las propiedades del perfil del usuario",e);
            JSFMessagesHelper.addErrorCodeMessage("autenticacion","general");
        }

        return "login";  
    }
    
    /**
     * 
     * @param request
     * @param response
     */
    private void agregarMarcaEstadistica(String ip , String casoOperacion, String msisdnNO, String rutNO, String flagCautivo){
        try{
            
            marcaEstadisticaBean = new MarcaEstadisticaBean();
            estadisticasDelegate = new EstadisticasDelegate();
                        
            if(EstadisticasHelper.TRANSACCION_OK.equals(casoOperacion)){
         
	            ProfileWrapper profileWrapper = ProfileWrapperHelper
	            .getProfile(JSFPortletHelper.getRequest());
	            
	            String atributo = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "aaa");
	            String msisdn = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "numeroPcs");
	            String rut = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "rutUsuarioSeleccionado");
	            String mercado = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "mercado");
	            String flagBam = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "flagBam");
	         
	            marcaEstadisticaBean.setAtributoCliente(atributo);  
	            marcaEstadisticaBean.setGrupo(flagBam != null && flagBam.equals("0") ? MiEntelProperties.getProperty("parametros.estadistica.login.grupo") : MiEntelProperties.getProperty("parametros.estadistica.login.grupobam"));
	            marcaEstadisticaBean.setMsisdn(msisdn);
	            marcaEstadisticaBean.setRut(rut);
	            marcaEstadisticaBean.setSegmento(mercado);
	            marcaEstadisticaBean.setServicio(flagBam != null && flagBam.equals("0") ? MiEntelProperties.getProperty("parametros.estadistica.login.servicio") : MiEntelProperties.getProperty("parametros.estadistica.login.serviciobam"));
		                                                    
            }else{
                marcaEstadisticaBean.setMsisdn(msisdnNO);
                marcaEstadisticaBean.setRut(rutNO);
            }
            //Datos comunes
            marcaEstadisticaBean.setFlagExitoFracasoOperacion(casoOperacion);
            marcaEstadisticaBean.setCampoOpcional1("");
            marcaEstadisticaBean.setCampoOpcional2("");
            marcaEstadisticaBean.setIp(ip);
            marcaEstadisticaBean.setFuncionalidad(MiEntelProperties.getProperty("parametros.estadistica.funcionalidad"));
            marcaEstadisticaBean.setOrigen(MiEntelProperties.getProperty("parametros.estadistica.origenweb"));
            
            //Validar si el usuario viene desde el Portal Cautivo	                       
            if(flagCautivo != null){
            	if ("2".equals(flagCautivo)){
            		LOGGER.info("Ingreso desde alerta");
            		marcaEstadisticaBean.setGrupo(MiEntelProperties.getProperty("parametros.estadistica.login.grupoalerta"));            		
            		marcaEstadisticaBean.setServicio(MiEntelProperties.getProperty("parametros.estadistica.login.servicio.alerta"));
            		marcaEstadisticaBean.setOrigen(MiEntelProperties.getProperty("parametros.estadistica.origenalerta"));
            	}else{
            	LOGGER.info("Ingreso desde home");
            	marcaEstadisticaBean.setServicio(MiEntelProperties.getProperty("parametros.estadistica.login.servicio.home"));
            	marcaEstadisticaBean.setOrigen(MiEntelProperties.getProperty("parametros.estadistica.origenhome"));         
            }
            }
            
            estadisticasDelegate.agregarMarcaEstadistica(marcaEstadisticaBean);
            
            
        }catch (Exception e) {
            LOGGER.info("No fue posible agregar marca estadistica de login");
        }
    }

}
