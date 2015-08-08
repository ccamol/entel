/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.cliente.orden.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.faces.event.PhaseEvent;

import org.apache.log4j.Logger;
import com.bea.content.Node;
import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.administracion.suscripciones.delegate.SuscripcionesDelegate;
import com.epcs.bean.BolsasActualesDisponiblesBean;
import com.epcs.bean.MiEntelBean;
import com.epcs.bean.RutBean;
import com.epcs.bean.TarifaDiaria;
import com.epcs.cliente.orden.delegate.TarifaDiariaDelegate;
import com.epcs.recursoti.configuracion.DateHelper;
import com.epcs.recursoti.configuracion.JsonHelper;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.jsf.utils.JSFMessagesHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JSFPortletHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JsfUtil;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;
import com.epcs.recursoti.configuracion.JsonHelper;


/**
 * Esta clase se encarga de controlar el flujo de tarifa diaria * 
 * 
 * @author wbrochero (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs) 
 */
public class TarifaDiariaController implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = Logger
            .getLogger(TarifaDiariaController.class);  
    
    String ofertaTarifaVisitada="";
    
    public static final String ID_BOLSAS_ILIMITADAS_CC = MiEntelProperties.getProperty("parametros.tarifaDiaria.bolsaIlimitadasCC");
    public static final String ID_BOLSAS_ILIMITADAS_SS = MiEntelProperties.getProperty("parametros.tarifaDiaria.bolsaIlimitadasSS");
    public static final String ID_BOLSAS_LIMITADAS_SS = MiEntelProperties.getProperty("parametros.tarifaDiaria.bolsaLimitadasSS");
    public static final String ID_BOLSAS_LIMITADAS_CC = MiEntelProperties.getProperty("parametros.tarifaDiaria.bolsaLimitadasCC");
    public static final String ID_TEXTO_BOLSA_LIMITADA_CC = MiEntelProperties.getProperty("parametros.tarifaDiaria.idTextoBolsaLimitadaCC");
    public static final String ID_TEXTO_BOLSA_ILIMITADA_CC = MiEntelProperties.getProperty("parametros.tarifaDiaria.idTextoBolsaIlimitadaCC");
    public static final String ID_TEXTO_BOLSA_NO_LIMITADA_ILIMITADA_CC = MiEntelProperties.getProperty("parametros.tarifaDiaria.idTextoBolsaNLICC");
    public static final String ID_TEXTO_BOLSA_LIMITADA_SS = MiEntelProperties.getProperty("parametros.tarifaDiaria.idTextoBolsaLimitadaSS");
    public static final String ID_TEXTO_BOLSA_ILIMITADA_SS = MiEntelProperties.getProperty("parametros.tarifaDiaria.idTextoBolsaIlimitadaSS");
    public static final String ID_TEXTO_BOLSA_NO_LIMITADA_ILIMITADA_SS = MiEntelProperties.getProperty("parametros.tarifaDiaria.idTextoBolsaNLISS");
    public static final String ID_TEXTO_ACEPTADA_RECHAZADA = MiEntelProperties.getProperty("parametros.tarifaDiaria.idTextoAR");
    public static final String ID_CODIGO_BOLSAS_IM = MiEntelProperties.getProperty("parametros.tarifaDiaria.codBolsaIM");
    public static final String CODIGO_RESPUESTA_ACEPTADA = MiEntelProperties.getProperty("parametros.tarifaDiaria.aceptada");
    public static final String CODIGO_RESPUESTA_RECHAZADA= MiEntelProperties.getProperty("parametros.tarifaDiaria.rechazada");
    public static final String CANAL_ACEPTACION= MiEntelProperties.getProperty("parametros.tarifaDiaria.canalAceptacion");    
    public static final String CANAL_SIN_IM= MiEntelProperties.getProperty("parametros.tarifaDiaria.sinIM");
    public static final String TEXTO_ACEPTADA= MiEntelProperties.getProperty("parametros.tarifaDiaria.TextoRechazada");
    public static final String TEXTO_RECHAZADA= MiEntelProperties.getProperty("parametros.tarifaDiaria.TextoAceptada");
 
    
   
    private SuscripcionesDelegate suscripcionesDelegate;    
    private BolsasActualesDisponiblesBean bolsasActuales;   
    private TarifaDiariaDelegate tarifaDiariaDelegate;
    private boolean mostrarAceptarRechazar;
    TarifaDiaria tarifaDiaria;
    String contenido="";
    String contenidoAceptadaRechazada="";
    String respuestaJson = "";
    String productoNavegacionMovil="";
    
     /**
	 * @return the suscripcionesDelegate
	 */
	public SuscripcionesDelegate getSuscripcionesDelegate() {
		return suscripcionesDelegate;
	}
	
	/**
	 * @param suscripcionesDelegate the suscripcionesDelegate to set
	 */
	public void setSuscripcionesDelegate(SuscripcionesDelegate suscripcionesDelegate) {
		this.suscripcionesDelegate = suscripcionesDelegate;
	}
	/**     
     * @param phase
     */
    public void init(final PhaseEvent phase) {
    	 String msisdn="";
    	 boolean sw = true;
    	 Map<String, String> resp = new HashMap<String, String>();
        try {

        	 final ProfileWrapper profileWrapper = ProfileWrapperHelper
             .getProfile(JSFPortletHelper.getRequest());
        	 
        	 msisdn = ProfileWrapperHelper.getPropertyAsString(
                     profileWrapper, "numeroPcsSeleccionado");
        	 String mercado = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "mercado");
        	 
        	 bolsasActuales = suscripcionesDelegate.consultarBolsasActualesDisponibles(msisdn);
        	 int i=0;
        	 if(mercado.equals("CC")){
        		 
		        	while(sw && i < bolsasActuales.getBolsasActuales().size()){
		        		 if(isPlanEnLista(bolsasActuales.getBolsasActuales().get(i).getSnCodigo().toString().trim(),ID_BOLSAS_ILIMITADAS_CC)){
		        			 sw = false;
		        			 LOGGER.info("CC : BOLSA IM ILIMITADA :" + bolsasActuales.getBolsasActuales().get(i).getNombreBolsa().toString() );
		        			 replaceTextCM(ID_TEXTO_BOLSA_ILIMITADA_CC);
		        			 productoNavegacionMovil= bolsasActuales.getBolsasActuales().get(i).getNombreBolsa().toString().trim();
		        		 }
		        		 i++;
		        	 }
		        	
		        	i=0;
		        	 if(sw){
		        		 while(sw && i < bolsasActuales.getBolsasActuales().size()){
			        		 if(isPlanEnLista(bolsasActuales.getBolsasActuales().get(i).getSnCodigo().toString().trim(),ID_BOLSAS_LIMITADAS_CC)){
			        			 sw = false;        
			        			 LOGGER.info("CC : BOLSA IM LIMITADA :" + bolsasActuales.getBolsasActuales().get(i).getNombreBolsa().toString() );
			        			 replaceTextCM(ID_TEXTO_BOLSA_LIMITADA_CC);
			        			 productoNavegacionMovil= bolsasActuales.getBolsasActuales().get(i).getNombreBolsa().toString().trim();			        			 
			        		 }
			        		 i++;
		        		 }	 
		        	 }		        	 
		        	 if(sw){
		        		 replaceTextCM(ID_TEXTO_BOLSA_NO_LIMITADA_ILIMITADA_CC); 
		        		 productoNavegacionMovil=CANAL_SIN_IM;
		        	 } 
		        	   
		        	   tarifaDiaria = tarifaDiariaDelegate.consultarEstadoTarifaDiaria(msisdn);	
		        	   replaceTextCMAceptadaRechazada(ID_TEXTO_ACEPTADA_RECHAZADA);			        	   
		        	 
        	  }else if(mercado.equals("SS")){        		 
        		  i=0;
        		  while(sw && i < bolsasActuales.getBolsasActuales().size()){        			    
		        		 if(isPlanEnLista(bolsasActuales.getBolsasActuales().get(i).getSnCodigo().toString().trim(),ID_BOLSAS_ILIMITADAS_SS)){
		        			 sw = false;		        			 
		        			 LOGGER.info("SS : BOLSA IM ILIMITADA :" + bolsasActuales.getBolsasActuales().get(i).getNombreBolsa().toString() );
		        			 replaceTextCM(ID_TEXTO_BOLSA_ILIMITADA_SS);
		        		 }
		        		 i++;	 
		            }
	        		 i=0;
	        		 if(sw){
		        		 while(sw && i < bolsasActuales.getBolsasActuales().size()){
				        		 if(isPlanEnLista(bolsasActuales.getBolsasActuales().get(i).getSnCodigo().toString().trim(),ID_BOLSAS_LIMITADAS_SS)){
				        			 sw = false;			        			 
				        			 LOGGER.info("SS : BOLSA IM LIMITADA :" + bolsasActuales.getBolsasActuales().get(i).getNombreBolsa().toString() );
				        			 replaceTextCM(ID_TEXTO_BOLSA_LIMITADA_SS);
				        		 }
				        	   i++;
				           }
			        	 
	        		  }	 
		        	 if(sw){
		        		replaceTextCM(ID_TEXTO_BOLSA_NO_LIMITADA_ILIMITADA_SS);     		
		        	 } 
        	  }	

        } catch (DAOException e) {
            LOGGER.error("DAOException al procesar la informacion tarifa diaria .", e);
            JSFMessagesHelper.addServiceErrorMessage("noDisponible");
        } catch (ServiceException e) {
            LOGGER.info("ServiceException al procesar la informacion tarifa diaria "+msisdn);            
            JSFMessagesHelper.addServiceErrorMessage("noDisponible");
        } catch (Exception e) {
            LOGGER.error("ServiceException al procesar la informacion tarifa diaria", e);
            JSFMessagesHelper.addServiceErrorMessage("inesperado");
        }    
     }
    
    
   /*  metodo para ayudar a determinar si la bolsa es ilimitada/limitada o ninguna de las dos ateriores.
    * @param planActual, idContenido
    * @return (boolean sw)
    */
   
   private boolean isPlanEnLista (String planActual,String idContenido){
   	boolean sw = false;    	
   	String listaPlanes="";
   	String[] arrayPlanesExcluidos;
   	try{    		
   		       //String idContenido = MiEntelProperties.getProperty(id);
		   	    Node mensaje = JSFPortletHelper.getContentNode("idContenido", idContenido);
		        listaPlanes = mensaje.getProperty("html").getValue().getStringValue();
		        
		        if(listaPlanes!=null){
		        	arrayPlanesExcluidos = listaPlanes.split(",");
		        	int i =0;
		        	while(i < arrayPlanesExcluidos.length && sw == false){
		        	     if(arrayPlanesExcluidos[i].equals(planActual)){
		        	    	 sw=true;
		        	     }
		        		i++;
		        	}		        	
		        }
   	
       } catch (Exception e) {
           LOGGER.error("Exception al buscar informacion de planes excluidos de tarifa diaria en el CM.", e);           
       }
		return sw;     	
   }
   
   
   /*  metodo para reemplazar nodos del contenido informacion tarifa diaria por valores del usuario.
    * @param idContenido    * 
    */
   
   private void replaceTextCM (String idContenido){ 
	  
	   final ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());	   
   	try{    		
   		        Node texto = JSFPortletHelper.getContentNode("idContenido", idContenido);
   		       
   		       if(texto!=null){   		        	
   		        	contenido = texto.getProperty("html").getValue().getStringValue();   		        	
   		        	contenido = contenido.replaceAll("<nombreUsuario>" ,ProfileWrapperHelper.getPropertyAsString(profileWrapper, "nombreUsuarioSeleccionado"));
   		        	contenido = contenido.replaceAll("<movilUsuario>" ,ProfileWrapperHelper.getPropertyAsString(profileWrapper, "numeroPcsSeleccionado"));
   		        	contenido = contenido.replaceAll("<rutUsuario>" ,ProfileWrapperHelper.getPropertyAsString(profileWrapper, "rutUsuarioSeleccionado"));
   		        	contenido = contenido.replaceAll("<nombreTitular>" ,ProfileWrapperHelper.getPropertyAsString(profileWrapper, "nombreUsuario"));
   		        	contenido = contenido.replaceAll("<rutTitular>" ,ProfileWrapperHelper.getPropertyAsString(profileWrapper, "rutTitular"));   			        
   		        }
   		    
   	
       } catch (Exception e) {
           LOGGER.error("Exception al buscar informacion de planes excluidos de tarifa diaria en el CM.", e);           
       }       
   }
   
   /*  metodo para reemplazar nodos del contenido informacion tarifa diaria rechaza / aceptada y fecha.
    * @param idContenido    * 
    */
   
   private void replaceTextCMAceptadaRechazada(String idContenido){
	   String textoAccion="";
	   mostrarAceptarRechazar =false;	  
   	try{    		
   		      if(tarifaDiaria!=null){
   		    	   if(tarifaDiaria.getEstadoRespuesta().equals(CODIGO_RESPUESTA_ACEPTADA))
    		        	textoAccion = TEXTO_ACEPTADA;
   		    	   else if(tarifaDiaria.getEstadoRespuesta().equals(CODIGO_RESPUESTA_RECHAZADA))
   		    		    textoAccion = TEXTO_RECHAZADA;
   		      }else{
   		    	mostrarAceptarRechazar=true;   		       
   		      } 
   		        Node texto = JSFPortletHelper.getContentNode("idContenido", idContenido);
   		       
   		       if(texto!=null && !mostrarAceptarRechazar){   		        	
   		    	      contenidoAceptadaRechazada = texto.getProperty("html").getValue().getStringValue();   		        	
   		    	      contenidoAceptadaRechazada = contenidoAceptadaRechazada.replaceAll("<accion>" ,textoAccion);
   		    	      contenidoAceptadaRechazada = contenidoAceptadaRechazada.replaceAll("<fecha>" , getFechaFormato(tarifaDiaria.getFechaRechazoAceptacion()));
   		           			        
   		        }
   		    
   	
       } catch (Exception e) {
           LOGGER.error("Exception al buscar informacion de planes excluidos de tarifa diaria en el CM.", e);           
       }       
   }
   
   
   public void aceptarRechazarTarifaDiaria(PhaseEvent event) {
   	   String numeroPcs = "";
   	   tarifaDiaria  = new TarifaDiaria();
   	   String accion="";
   	   String pnm="";
       boolean respuesta = true; 
       Map<String, String> resp = new HashMap<String, String>();
       try{           
       	
       	   LOGGER.info("Registrando Aceptar/Rechazar Tarifa Diaria .....");  
       	   
           ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());
           
           numeroPcs = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "numeroPcsSeleccionado");
           String rutSeleccionado = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "rutUsuarioSeleccionado");
           String rutTitular = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "rutTitular");
           
            accion = JsfUtil.getRequestParameter("accion").equals("1")?CODIGO_RESPUESTA_ACEPTADA:CODIGO_RESPUESTA_RECHAZADA;
            pnm = JsfUtil.getRequestParameter("pnm");
            
            //FECHA =============
               String fechaRegistro="";
               Date fechaActual = new Date();
                   SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			     //SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); Formato Temporal,
			   
               fechaRegistro = formatoFecha.format(fechaActual);
            //FIN FECHA =============
                 
				tarifaDiaria.setCanalAceptacion(CANAL_ACEPTACION); 
				tarifaDiaria.setNumeroMovil(numeroPcs);     	     
				RutBean rt = new RutBean(rutSeleccionado);
				tarifaDiaria.setRutUsuario(rt.getNumero()+"-"+rt.getDigito());    	     
				rt = new RutBean(rutTitular);
				tarifaDiaria.setRutTitular(rt.getNumero()+"-"+rt.getDigito());				
				tarifaDiaria.setEstadoRespuesta(accion);     	    
				tarifaDiaria.setFechaRechazoAceptacion(fechaRegistro);
				tarifaDiaria.setPlataforma("");
				tarifaDiaria.setUsuario("");
				tarifaDiaria.setCuenta("");
				tarifaDiaria.setGrupoUsuario("");
				tarifaDiaria.setProducto(pnm);
				     	    
     	     if(tarifaDiariaDelegate.registrarAceptarRechazarTarifaDiaria(tarifaDiaria))
     	                respuestaJson = JsonHelper.toJsonResponse("Ok");
     	            else
     	            	respuestaJson = JsonHelper.toJsonErrorMessage("Error");
     	         
     	    
       } catch (DAOException e) {          
           LOGGER.error("DAOException Error Registrando Aceptar/Rechazar Tarifa Diaria", e);
            respuestaJson = JsonHelper.toJsonErrorMessage("Error");
       } catch (ServiceException e) {
       	  LOGGER.info("ServiceException Error Registrando Aceptar/Rechazar Tarifa Diaria: "+numeroPcs);
       	    respuestaJson = JsonHelper.toJsonErrorMessage("Error");
       } catch (Exception e) {
       	 LOGGER.error("Exception Error Registrando Aceptar/Rechazar Tarifa Diaria", e);
           	respuestaJson = JsonHelper.toJsonErrorMessage("Error");       	    
       }
   }
   
   public String getFechaFormato(String fecha) {	   
	   String fechaFormato="";	  
	   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");	   
	   try{
		  if(fecha!=null){
			  if(!fecha.equals("")){				  
				    Date fechaDate = sdf.parse(fecha);				    
				    SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
				    fechaFormato = formatoFecha.format(fechaDate);				   
			  }
		  }
		   
	   }  catch (Exception e) {
      	  LOGGER.error("Exception Error Formarteando la fecha", e);
      	 
      }
		return fechaFormato;
	}

/**
 * @return the mostrarAceptarRechazar
 */
public boolean isMostrarAceptarRechazar() {
	return mostrarAceptarRechazar;
}

/**
 * @param mostrarAceptarRechazar the mostrarAceptarRechazar to set
 */
public void setMostrarAceptarRechazar(boolean mostrarAceptarRechazar) {
	this.mostrarAceptarRechazar = mostrarAceptarRechazar;
}

/**
 * @return the respuestaJson
 */
public String getRespuestaJson() {
	return respuestaJson;
}

/**
 * @param respuestaJson the respuestaJson to set
 */
public void setRespuestaJson(String respuestaJson) {
	this.respuestaJson = respuestaJson;
}

/**
 * @return the contenido
 */
public String getContenido() {
	return contenido;
}

/**
 * @param contenido the contenido to set
 */
public void setContenido(String contenido) {
	this.contenido = contenido;
}

/**
 * @return the tarifaDiariaDelegate
 */
public TarifaDiariaDelegate getTarifaDiariaDelegate() {
	return tarifaDiariaDelegate;
}

/**
 * @param tarifaDiariaDelegate the tarifaDiariaDelegate to set
 */
public void setTarifaDiariaDelegate(TarifaDiariaDelegate tarifaDiariaDelegate) {
	this.tarifaDiariaDelegate = tarifaDiariaDelegate;
}

/**
 * @return the productoNavegacionMovil
 */
public String getProductoNavegacionMovil() {
	return productoNavegacionMovil;
}

/**
 * @param productoNavegacionMovil the productoNavegacionMovil to set
 */
public void setProductoNavegacionMovil(String productoNavegacionMovil) {
	this.productoNavegacionMovil = productoNavegacionMovil;
}

/**
 * @return the tarifaDiaria
 */
public TarifaDiaria getTarifaDiaria() {
	return tarifaDiaria;
}

/**
 * @param tarifaDiaria the tarifaDiaria to set
 */
public void setTarifaDiaria(TarifaDiaria tarifaDiaria) {
	this.tarifaDiaria = tarifaDiaria;
}

/**
 * @return the contenidoAceptadaRechazada
 */
public String getContenidoAceptadaRechazada() {
	return contenidoAceptadaRechazada;
}

/**
 * @param contenidoAceptadaRechazada the contenidoAceptadaRechazada to set
 */
public void setContenidoAceptadaRechazada(String contenidoAceptadaRechazada) {
	this.contenidoAceptadaRechazada = contenidoAceptadaRechazada;
}  
   
   
    

public void marcarVisita(final PhaseEvent phase) {
   try{
		 final ProfileWrapper profileWrapper = ProfileWrapperHelper
	       .getProfile(JSFPortletHelper.getRequest());
	  	 
	  	 String msisdn = ProfileWrapperHelper.getPropertyAsString(
	               profileWrapper, "numeroPcsSeleccionado");
	  	 String mercado = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "mercado"); 
  	 
      //FECHA =============
         String fechaRegistro="";
         Date fechaActual = new Date();
             SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		     //SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); Formato Temporal,
		   
         fechaRegistro = formatoFecha.format(fechaActual);
      //FIN FECHA =============
               
         ofertaTarifaVisitada = (String) JSFPortletHelper.getSession().getAttribute("ofertaTarifaVisitada"); 
		 
		 if (ofertaTarifaVisitada == null || ofertaTarifaVisitada=="") {
               this.tarifaDiariaDelegate.marcarVisita(msisdn, mercado,fechaRegistro);
		 }      
		 
		 JSFPortletHelper.getSession().setAttribute("ofertaTarifaVisitada", "true");
		
   }catch (Exception e) {
   	 LOGGER.error("Exception Error Registrando Visita Tarifa Diaria", e);      	    
   }
}
}