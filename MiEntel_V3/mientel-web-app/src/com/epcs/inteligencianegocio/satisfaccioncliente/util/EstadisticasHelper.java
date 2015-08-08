/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.inteligencianegocio.satisfaccioncliente.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.bean.MarcaEstadisticaBean;
import com.epcs.inteligencianegocio.satisfaccioncliente.delegate.EstadisticasDelegate;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.jsf.utils.JSFPortletHelper;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;

/**
 * @author jmanzur (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class EstadisticasHelper {
    
    private static final Logger LOGGER = Logger
    .getLogger(EstadisticasHelper.class);
    
    public static final String TRANSACCION_OK = MiEntelProperties.getProperty("parametros.estadistica.ok");
    public static final String TRANSACCION_NO_OK = MiEntelProperties.getProperty("parametros.estadistica.nook");
    
    //Grupos
    public static final String GRUPO_BOLSAS = MiEntelProperties.getProperty("parametros.estadistica.bolsa.grupo");
    
    //Servicios
    //Bolsas PP
    public static final String BOLSAS_PANTALLA_COMPRAR = MiEntelProperties.getProperty("parametros.estadistica.bolsa.pp.servicio.BOLSAS_PANTALLA_COMPRAR");
    public static final String BOLSAS_CONFIRMAR_COMPRAR = MiEntelProperties.getProperty("parametros.estadistica.bolsa.pp.servicio.BOLSAS_CONFIRMAR_COMPRAR");    
    //Bolsas SSCC
    public static final String BOLSAS_PANTALLA_CONTRATAR_SS = MiEntelProperties.getProperty("parametros.estadistica.bolsa.ss.servicio.BOLSAS_PANTALLA_CONTRATAR");    
    public static final String BOLSAS_PANTALLA_COMPRAR_CC = MiEntelProperties.getProperty("parametros.estadistica.bolsa.cc.servicio.BOLSAS_PANTALLA_COMPRAR");
    public static final String BOLSAS_CONFIRMAR_CONTRATAR_SSCC = MiEntelProperties.getProperty("parametros.estadistica.bolsa.sscc.servicio.BOLSAS_CONFIRMAR_CONTRATAR");    
    
    /**
     * 
     * @param request
     * @param response
     */
    public static void agregarMarcaEstadistica(String casoOperacion, String grupo, String servicio){
        try{
            EstadisticasDelegate estadisticasDelegate = new EstadisticasDelegate();
            MarcaEstadisticaBean marcaEstadisticaBean = new MarcaEstadisticaBean();
                     
            HttpServletRequest request =  JSFPortletHelper.getRequest();        
            ProfileWrapper profileWrapper = ProfileWrapperHelper
            .getProfile(JSFPortletHelper.getRequest());
            
            String ip = request.getRemoteAddr();
            
            String atributo = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "aaa");
            String msisdn = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "numeroPcs");
            String rut = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "rutUsuarioSeleccionado");
            String mercado = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "mercado");
                     
            marcaEstadisticaBean.setAtributoCliente(atributo);  
            marcaEstadisticaBean.setGrupo(grupo);
            marcaEstadisticaBean.setMsisdn(msisdn);
            marcaEstadisticaBean.setRut(rut);
            marcaEstadisticaBean.setSegmento(mercado);
            marcaEstadisticaBean.setServicio(servicio);

            marcaEstadisticaBean.setFlagExitoFracasoOperacion(casoOperacion);
            marcaEstadisticaBean.setCampoOpcional1("");
            marcaEstadisticaBean.setCampoOpcional2("");
            marcaEstadisticaBean.setIp(ip);
            marcaEstadisticaBean.setFuncionalidad(MiEntelProperties.getProperty("parametros.estadistica.funcionalidad"));
            marcaEstadisticaBean.setOrigen(MiEntelProperties.getProperty("parametros.estadistica.origenweb"));
            
            estadisticasDelegate.agregarMarcaEstadistica(marcaEstadisticaBean);
            
            
        }catch (Exception e) {
            LOGGER.info("No fue posible agregar marca estadistica grupo :"+grupo+" -Servicio :"+servicio+" : "+e.getMessage());
        }
    }
    
}
