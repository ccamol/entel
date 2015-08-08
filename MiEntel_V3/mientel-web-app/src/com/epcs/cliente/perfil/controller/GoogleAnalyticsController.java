package com.epcs.cliente.perfil.controller;

import javax.faces.event.PhaseEvent;
import org.apache.log4j.Logger;
import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.recursoti.configuracion.JsonHelper;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.jsf.utils.JSFPortletHelper;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;

public class GoogleAnalyticsController {
	
	private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = Logger
	            .getLogger(EquipoController.class);	
	
	private String  jsonMarcaMercado;
	
	
	public void init(PhaseEvent event) {
        try {
        	String keyMarca ="";
        	String flag  ="";
        	String mercado  ="";
            ProfileWrapper profileWrapper = ProfileWrapperHelper
                    .getProfile(JSFPortletHelper.getRequest());
            
                  mercado  = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "mercado");
                  flag  = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "flagBam");
                  
                  flag = flag==null?"login":flag; 
                  if (!flag.equals("login"))
                  flag = flag.equals("0")?"VOZ":"BAM";  
                  mercado = mercado==null?"":"."+mercado;
             
                  keyMarca = MiEntelProperties.getProperty("parametros.googleAnalytics."+flag+mercado);
                  
                  jsonMarcaMercado = JsonHelper.toJsonResponse(keyMarca);
           
        } catch (Exception e) {
            LOGGER.error("Error al buscar informacion del mercado", e);            
        }
    }



	public String getJsonMarcaMercado() {
		return jsonMarcaMercado;
	}



	public void setJsonMarcaMercado(String jsonMarcaMercado) {
		this.jsonMarcaMercado = jsonMarcaMercado;
	}

}
