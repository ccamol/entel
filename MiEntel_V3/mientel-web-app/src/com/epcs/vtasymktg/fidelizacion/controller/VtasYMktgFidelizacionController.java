/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.vtasymktg.fidelizacion.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

//import javax.faces.event.ActionEvent;
import javax.faces.event.PhaseEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.portlet.PortletPreferences;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.log4j.Logger;

import com.bea.content.Node;
import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.bean.CanjeDePuntosBean;
import com.epcs.bean.CodeDescBean;
import com.epcs.bean.DetalleBean;
import com.epcs.bean.HistorialDetalleBean;
import com.epcs.bean.ItemDetalleCanjeBean;
import com.epcs.bean.ItemMatrizCanjeBean;
import com.epcs.bean.ItemProductoCanjeBean;
import com.epcs.bean.MatrizCatalogoCanjeBean;
import com.epcs.bean.PerfilUsuarioBean;
import com.epcs.bean.PuntosBean;
import com.epcs.bean.ResultadoCanjeDePuntosBean;
import com.epcs.bean.ResultadoConsultarPuntosBean;
import com.epcs.bean.ResultadoObtenerHistorialBean;
import com.epcs.bean.ResultadoRegalarPuntosBean;
import com.epcs.bean.RutBean;
import com.epcs.bean.TransaccionGTMBean;
import com.epcs.bean.ZonaPerfilBean;
import com.epcs.cliente.perfil.delegate.CuentaDelegate;
import com.epcs.recursoti.configuracion.DateHelper;
import com.epcs.recursoti.configuracion.JsonHelper;
import com.epcs.recursoti.configuracion.MiEntelBusinessHelper;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.ParametrosHelper;
import com.epcs.recursoti.configuracion.Utils;
import com.epcs.recursoti.configuracion.error.ServiceMessages;
import com.epcs.recursoti.configuracion.jsf.utils.JSFMessagesHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JSFPortletHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JsfUtil;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;
import com.epcs.vtasymktg.fidelizacion.delegate.BeneficiosDelegate;
import com.epcs.vtasymktg.fidelizacion.delegate.VtasYMktgFidelizacionDelegate;

/**
 * @author jivasquez (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class VtasYMktgFidelizacionController implements Serializable{    

	/**
     * Logger para VtasYMktgFidelizacionController
     */
    private static final Logger LOGGER = Logger
            .getLogger(VtasYMktgFidelizacionController.class);
    
    private static final long serialVersionUID = 1L;
    
    private VtasYMktgFidelizacionDelegate vtasYMktgFidelizacionDelegate;
    private BeneficiosDelegate beneficiosDelegate;
    private CuentaDelegate cuentaDelegate;
    
    private PuntosBean detallePuntos;
    private DetalleBean detalleHistorial;
    private CanjeDePuntosBean respuestaCanjearPuntos;
    private Set<ItemProductoCanjeBean> recargas;
    private Set<ItemProductoCanjeBean> bolsasVoz;
    private Set<ItemProductoCanjeBean> bolsasSMS;
    private Set<ItemProductoCanjeBean> bolsasBAM;
    private Set<ItemProductoCanjeBean> bolsasMixtas;
    private Set<ItemProductoCanjeBean> bolsasInternetMovil;
    private Set<ItemProductoCanjeBean> bolsasZonaVerano;
    private Set<ItemProductoCanjeBean> bolsasCanjeBAM;
    
    // Lista contenedora de los tipos de bolsas
    private List<Set<ItemProductoCanjeBean>> bolsas; 
    private List<ItemProductoCanjeBean> listaBolsasZonaVerano;  
    private List<HistorialDetalleBean> tablaHistorial;   
    private List<String[]> tagZonaVerano;
    private SelectItem[] periodosHistorialList;
    
    private static final Integer PERIODO_HISTORIAL_DEFAULT = 1;
    private Integer periodoHistorialSelector;
    
    private boolean existenRecargas = false;
    private boolean existenBolsasVoz = false;
    private boolean existenBolsasSMS = false;
    private boolean existenBolsasBAM = false;
    private boolean existenBolsasMixtas = false;
    private boolean existenBolsaZonaVerano = false;
    private boolean existenZonasVerano = false;
    private boolean existenRegistrosHist = false;
    private boolean showBolsasBAM = true;
    private boolean mostrarCajaPuntos = false;
    private boolean existenBolsasCanjeBAM = false;
    
    private String msisdnRecibe;
    private String categoriaClientePuntos;
    private String respuestaCanjearPuntosJson;
    private Double montoCanje;      
    
    private MatrizCatalogoCanjeBean matrizCatalogoBean; 
    private ZonaPerfilBean zonaPerfilBean;
    private boolean validoPrestaLuka;
    private boolean tiempoMinimoRegistro;
    private String mensajeIntroduccionBolsas;
    private String mensajeIntroduccionRecargas;
    
    private static final String COD_MOVIL_DEST_NO_REGISTRADO = "0002";
    
    private static final String CODOK_PRESTALUKA1 = "00";
	private static final String CODOK_PRESTALUKA2 = "80";
	private static final String COD_DEUDA_PRESTALUKA_SPONSOR = "34";
	private static final String COD_DEUDA_PRESTALUKA_DESTINO = "33";
	private static final String COD_ERROR_TIEMPO_MIN_REGISTRO = "21";
	private static final String SERVICE_NAME_ZONAENTEL = "zonaEntel";
	
	private static final int CANAL_WEB = Integer.parseInt(MiEntelProperties.getProperty("zonaEntel.catalogo.canal.web"));
	private static final int CANAL_WEBBAM = Integer.parseInt(MiEntelProperties.getProperty("zonaEntel.catalogo.canal.webbam"));
	private static final int MERCADO_VOZPP = Integer.parseInt(MiEntelProperties.getProperty("zonaEntel.catalogo.mercado.vozpp"));
	private static final int MERCADO_VOZCC = Integer.parseInt(MiEntelProperties.getProperty("zonaEntel.catalogo.mercado.vozcc"));
	private static final int MERCADO_VOZSS = Integer.parseInt(MiEntelProperties.getProperty("zonaEntel.catalogo.mercado.vozss"));
	private static final int MERCADO_BAMPP = Integer.parseInt(MiEntelProperties.getProperty("zonaEntel.catalogo.mercado.bampp"));
	private static final int MERCADO_BAMCC = Integer.parseInt(MiEntelProperties.getProperty("zonaEntel.catalogo.mercado.bamcc"));
	private static final int MERCADO_BAMSS = Integer.parseInt(MiEntelProperties.getProperty("zonaEntel.catalogo.mercado.bamss"));
	private static final int MERCADO_BAMCCN = Integer.parseInt(MiEntelProperties.getProperty("zonaEntel.catalogo.mercado.bamccn"));
	private static final String SUB_BAMCCN = "CCPP";
	
	public static final String BOLSA_OTROS = MiEntelProperties.getProperty("zonaEntel.catalogo.operacion.bolsaOtros");
	public static final String BOLSA_SIMISMO = MiEntelProperties.getProperty("zonaEntel.catalogo.operacion.bolsaSiMismo");
	public static final String RECARGA_OTROS = MiEntelProperties.getProperty("zonaEntel.catalogo.operacion.recargaOtros");
	public static final String RECARGA_SIMISMO = MiEntelProperties.getProperty("zonaEntel.catalogo.operacion.recargaSiMismo");
	
	/**
	 * @return the validoPrestaLuka
	 */
	
	public boolean isValidoPrestaLuka() {
		return (!zonaPerfilBean.getStatusRespuesta().equals(COD_DEUDA_PRESTALUKA_SPONSOR) 
				&& !zonaPerfilBean.getStatusRespuesta().equals(COD_DEUDA_PRESTALUKA_DESTINO));
	}	

	/**
	 * @param validoPrestaLuka the validoPrestaLuka to set
	 */
	public void setValidoPrestaLuka(boolean validoPrestaLuka) {
		this.validoPrestaLuka = validoPrestaLuka;
	}

	/**
	 * @return the mensajeIntroduccionBolsas
	 */
	public String getMensajeIntroduccionBolsas() {
		return mensajeIntroduccionBolsas;
	}

	/**
	 * @param mensajeIntroduccionBolsas the mensajeIntroduccionBolsas to set
	 */
	public void setMensajeIntroduccionBolsas(String mensajeIntroduccionBolsas) {
		this.mensajeIntroduccionBolsas = mensajeIntroduccionBolsas;
	}

	/**
	 * @return the mensajeIntroduccionRecargas
	 */
	public String getMensajeIntroduccionRecargas() {
		return mensajeIntroduccionRecargas;
	}

	/**
	 * @param mensajeIntroduccionRecargas the mensajeIntroduccionRecargas to set
	 */
	public void setMensajeIntroduccionRecargas(String mensajeIntroduccionRecargas) {
		this.mensajeIntroduccionRecargas = mensajeIntroduccionRecargas;
	}	

	/**
	 * @return the zonaPerfilBean
	 */
	public ZonaPerfilBean getZonaPerfilBean() {
		return zonaPerfilBean;
	}

	/**
	 * @param zonaPerfilBean the zonaPerfilBean to set
	 */
	public void setZonaPerfilBean(ZonaPerfilBean zonaPerfilBean) {
		this.zonaPerfilBean = zonaPerfilBean;
	}

	/**
	 * @return the recargas
	 */
	public Set<ItemProductoCanjeBean> getRecargas() {
		return recargas;
	}

	/**
	 * @param recargas the recargas to set
	 */
	public void setRecargas(Set<ItemProductoCanjeBean> recargas) {
		this.recargas = recargas;
	}

	/**
	 * @return the bolsasVoz
	 */
	public Set<ItemProductoCanjeBean> getBolsasVoz() {
		return bolsasVoz;
	}

	/**
	 * @param bolsasVoz the bolsasVoz to set
	 */
	public void setBolsasVoz(Set<ItemProductoCanjeBean> bolsasVoz) {
		this.bolsasVoz = bolsasVoz;
	}

	/**
	 * @return the bolsasSMS
	 */
	public Set<ItemProductoCanjeBean> getBolsasSMS() {
		return bolsasSMS;
	}

	/**
	 * @param bolsasSMS the bolsasSMS to set
	 */
	public void setBolsasSMS(Set<ItemProductoCanjeBean> bolsasSMS) {
		this.bolsasSMS = bolsasSMS;
	}

	/**
	 * @return the bolsasBAM
	 */
	public Set<ItemProductoCanjeBean> getBolsasBAM() {
		return bolsasBAM;
	}

	/**
	 * @param bolsasBAM the bolsasBAM to set
	 */
	public void setBolsasBAM(Set<ItemProductoCanjeBean> bolsasBAM) {
		this.bolsasBAM = bolsasBAM;
	}

	/**
	 * @return the bolsasMixtas
	 */
	public Set<ItemProductoCanjeBean> getBolsasMixtas() {
		return bolsasMixtas;
	}

	/**
	 * @param bolsasMixtas the bolsasMixtas to set
	 */
	public void setBolsasMixtas(Set<ItemProductoCanjeBean> bolsasMixtas) {
		this.bolsasMixtas = bolsasMixtas;
	}
	

	/**
	 * @return the bolsasInternetMovil
	 */
	public Set<ItemProductoCanjeBean> getBolsasInternetMovil() {
		return bolsasInternetMovil;
	}

	/**
	 * @param bolsasInternetMovil the bolsasInternetMovil to set
	 */
	public void setBolsasInternetMovil(
			Set<ItemProductoCanjeBean> bolsasInternetMovil) {
		this.bolsasInternetMovil = bolsasInternetMovil;
	}

	/**
	 * @return the bolsas
	 */
	public List<Set<ItemProductoCanjeBean>> getBolsas() {
		return bolsas;
	}

	/**
	 * @param bolsas the bolsas to set
	 */
	public void setBolsas(List<Set<ItemProductoCanjeBean>> bolsas) {
		this.bolsas = bolsas;
	}

	/**
     * @return the detallePuntos
     */
    public PuntosBean getDetallePuntos() {
        return detallePuntos;
    }

    /**
     * @param detallePuntos the detallePuntos to set
     */
    public void setDetallePuntos(PuntosBean detallePuntos) {
        this.detallePuntos = detallePuntos;
    }

    /**
     * @return the existenRecargas
     */
    public boolean isExistenRecargas() {
        return existenRecargas;
    }

    /**
     * @param existenRecargas the existenRecargas to set
     */
    public void setExistenRecargas(boolean existenRecargas) {
        this.existenRecargas = existenRecargas;
    }

    /**
     * @return the existenBolsasVoz
     */
    public boolean isExistenBolsasVoz() {
        return existenBolsasVoz;
    }

    /**
     * @param existenBolsasVoz the existenBolsasVoz to set
     */
    public void setExistenBolsasVoz(boolean existenBolsasVoz) {
        this.existenBolsasVoz = existenBolsasVoz;
    }

    /**
     * @return the existenBolsasSMS
     */
    public boolean isExistenBolsasSMS() {
        return existenBolsasSMS;
    }

    /**
     * @param existenBolsasSMS the existenBolsasSMS to set
     */
    public void setExistenBolsasSMS(boolean existenBolsasSMS) {
        this.existenBolsasSMS = existenBolsasSMS;
    }

    /**
     * @return the existenBolsasBAM
     */
    public boolean isExistenBolsasBAM() {
        return existenBolsasBAM;
    }

    /**
     * @param existenBolsasBAM the existenBolsasBAM to set
     */
    public void setExistenBolsasBAM(boolean existenBolsasBAM) {
        this.existenBolsasBAM = existenBolsasBAM;
    }

    /**
     * @return the existenBolsasMixtas
     */
    public boolean isExistenBolsasMixtas() {
        return existenBolsasMixtas;
    }

    /**
     * @param existenBolsasMixtas the existenBolsasMixtas to set
     */
    public void setExistenBolsasMixtas(boolean existenBolsasMixtas) {
        this.existenBolsasMixtas = existenBolsasMixtas;
    }

    /**
     * @return the detalleHistorial
     */
    public DetalleBean getDetalleHistorial() {
        return detalleHistorial;
    }

    /**
     * @param detalleHistorial the detalleHistorial to set
     */
    public void setDetalleHistorial(DetalleBean detalleHistorial) {
        this.detalleHistorial = detalleHistorial;
    }

    /**
     * @return the respuestaCanje
     */
    public CanjeDePuntosBean getRespuestaCanjearPuntos() {
        return respuestaCanjearPuntos;
    }

    /**
     * @param respuestaCanje the respuestaCanje to set
     */
    public void setRespuestaCanjearPuntos(CanjeDePuntosBean respuestaCanjearPuntos) {
        this.respuestaCanjearPuntos = respuestaCanjearPuntos;
    }

    /**
     * @return the tablaHistorial
     */
    public List<HistorialDetalleBean> getTablaHistorial() {
        return tablaHistorial;
    }

    /**
     * @param tablaHistorial the tablaHistorial to set
     */
    public void setTablaHistorial(List<HistorialDetalleBean> tablaHistorial) {
        this.tablaHistorial = tablaHistorial;
    }
    
    /**
     * @return the periodosHistorialList
     */
    public SelectItem[] getPeriodosHistorialList() {
        return periodosHistorialList;
    }

    /**
     * @param periodosHistorialList the periodosHistorialList to set
     */
    public void setPeriodosHistorialList(SelectItem[] periodosHistorialList) {
        this.periodosHistorialList = periodosHistorialList;
    }

    /**
     * @return the existenRegistrosHist
     */
    public boolean isExistenRegistrosHist() {
        return existenRegistrosHist;
    }

    /**
     * @param existenRegistrosHist the existenRegistrosHist to set
     */
    public void setExistenRegistrosHist(boolean existenRegistrosHist) {
        this.existenRegistrosHist = existenRegistrosHist;
    }

    /**
	 * @return the showBolsasBAM
	 */
	public boolean isShowBolsasBAM() {
		return showBolsasBAM;
	}

	/**
	 * @param showBolsasBAM the showBolsasBAM to set
	 */
	public void setShowBolsasBAM(boolean showBolsasBAM) {
		this.showBolsasBAM = showBolsasBAM;
	}
	

	/**
	 * @return the tiempoMinimoRegistro
	 */
	public boolean isTiempoMinimoRegistro() {
		if(null != zonaPerfilBean){
			return (!zonaPerfilBean.getStatusRespuesta().equals(COD_ERROR_TIEMPO_MIN_REGISTRO));
		}
		return false;
	}

	/**
	 * @param tiempoMinimoRegistro the tiempoMinimoRegistro to set
	 */
	public void setTiempoMinimoRegistro(boolean tiempoMinimoRegistro) {
		this.tiempoMinimoRegistro = tiempoMinimoRegistro;
	}

	/**
     * @return the periodoHidden
     */
    public Integer getPeriodoHistorialSelector() {
        return periodoHistorialSelector;
    }

    /**
     * @param periodoHidden the periodoHidden to set
     */
    public void setPeriodoHistorialSelector(Integer periodoHistorialSelector) {
        this.periodoHistorialSelector = periodoHistorialSelector;
    }

    /**
     * @return the msisdnRecibe
     */
    public String getMsisdnRecibe() {
        return msisdnRecibe;
    }

    /**
     * @param msisdnRecibe the msisdnRecibe to set
     */
    public void setMsisdnRecibe(String msisdnRecibe) {
        this.msisdnRecibe = msisdnRecibe;
    }
    
    /**
     * @return the categoriaClientePuntos
     */
    public String getCategoriaClientePuntos() {
        return categoriaClientePuntos;
    }

    /**
     * @param msisdnRecibe the categoriaClientePuntos to set
     */
    public void setCategoriaClientePuntos(String categoriaClientePuntos) {
        this.categoriaClientePuntos = categoriaClientePuntos;
    }

    /**
     * @return the respuestaCanjearPuntosJson
     */
    public String getRespuestaCanjearPuntosJson() {
        return respuestaCanjearPuntosJson;
    }

    /**
     * @param respuestaCanjearPuntosJson the respuestaCanjearPuntosJson to set
     */
    public void setRespuestaCanjearPuntosJson(String respuestaCanjearPuntosJson) {
        this.respuestaCanjearPuntosJson = respuestaCanjearPuntosJson;
    }

    /**
     * @return the montoCanje
     */
    public Double getMontoCanje() {
        return montoCanje;
    }

    /**
     * @param montoCanje the montoCanje to set
     */
    public void setMontoCanje(Double montoCanje) {
        this.montoCanje = montoCanje;
    }

    /**
     * Invoca el action method que obtiene el detalle de puntos disponibles para canje.
     * @param phase
     */
    public void initCargarInfoPuntosCanje(PhaseEvent phase) {
        String msisdn = "";
        try {
            LOGGER.info("phase " + phase.getPhaseId());
            //Obtenemos datos necesarios para consulta
            ProfileWrapper profile = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());
            
            String rut = ProfileWrapperHelper.getPropertyAsString(profile, "rutUsuario");
            msisdn = ProfileWrapperHelper.getPropertyAsString(profile, "numeroPcs");
            //Invocamos el metodo de accion que consulta los puntos
            String rutSinDV = Long.toString(RutBean.parseRut(rut).getNumero());
            consultarInfoPuntosCliente(rutSinDV, msisdn);

        } catch (Exception e) {
            LOGGER.error("Error inesperado al cargar informacion de los puntos de canje. msisdn :"+msisdn, e);
            JSFMessagesHelper.addServiceErrorMessage("consultaPuntos");
        }

    }
    
    /**
     * Invoca el action method que inicializa las listas de bolsas y paquetes 
     * de recarga disponibles para canjear por puntos. En este metodo tambien
     * invocamos el metodo que consulta los puntos. Esto se hace para poder
     * validar si se muestra o no los botones para canjear o regalar puntos.
     * @param phase
     * @see obtenerCatalogoZonaCanje(String mercado)
     */
    public void initCargarBolsasYRecargasCanje(PhaseEvent phase) {
        try {
            LOGGER.info("phase " + phase.getPhaseId());                        
            
            //Datos necesarios para consulta desde el perfiel del usuario
            ProfileWrapper profile = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());           
            
            String rut = ProfileWrapperHelper.getPropertyAsString(profile, "rutUsuario");            
            String msisdn = ProfileWrapperHelper.getPropertyAsString(profile, "numeroPcs");
            String mercado = "";
            String subMercado = "";
            int flagBam;
            
            
            // Se asegura que el mercado y el flagBam es el del usuario logueado
            if(msisdn.equals(ProfileWrapperHelper.getPropertyAsString(profile, "numeroPcsSeleccionado"))){
            	mercado = ProfileWrapperHelper.getPropertyAsString(profile, "mercado");
            	subMercado = ProfileWrapperHelper.getPropertyAsString(profile, "subMercado");
            	flagBam = ProfileWrapperHelper.getPropertyAsInt(profile, "flagBam");
            }
            else{
            	PerfilUsuarioBean pub = cuentaDelegate.obtenerPerfilUsuario(msisdn);
            	mercado = pub.getMercado();
            	subMercado = pub.getUsuarioSeleccionado().getSubMercado();
            	flagBam = pub.getFlagBam();
            }
            
            
            //Invocamos el metodo de accion que inicializa las bolsas y recargas
            obtenerCatalogoZonaCanje(mercado, subMercado, flagBam);                        
            
            //Invocamos el metodo de accion que consulta los puntos.
            String rutSinDV = Long.toString(RutBean.parseRut(rut).getNumero());
            LOGGER.info("Invocando metodo <consultarPuntos> desde metodo <initCargarBolsasYRecargasCanje>");
            consultarInfoPuntosCliente(rutSinDV, msisdn);                                   
            
            
            /*
             * Consultamos perfil zona del usuario logueado. Con esto podemos validar lo siguiente: 
             * Si cumple con el tiempo minimo de registro en BD Zona Entel para canjear puntos. 
             * Si tiene deuda con servicio presta-luka en caso que mercado sea PP.
             */
            
            boolean validoPrestaLuka = true;
            boolean tiempoMinimoRegistro = true;
        	RutBean rutBean = new RutBean(rut);
            zonaPerfilBean = beneficiosDelegate.getZonaPerfil(msisdn, rutBean.getNumero()+"0"+rutBean.getDigito());
            
            if(zonaPerfilBean.getStatusRespuesta().equals(COD_ERROR_TIEMPO_MIN_REGISTRO)){
            	mensajeIntroduccionBolsas = JSFPortletHelper.getNodePropertyAsString("idContenido", "msjTiempoMinimoRegistro", "html");
    			mensajeIntroduccionRecargas = mensajeIntroduccionBolsas;
    			tiempoMinimoRegistro = false;
            } else if(MiEntelBusinessHelper.isMercadoPrepago(mercado)){
            	// Se consulta la validacion presta luka si el mercado del usuario es pp
                if(zonaPerfilBean.getStatusRespuesta().equals(COD_DEUDA_PRESTALUKA_SPONSOR)
                		|| zonaPerfilBean.getStatusRespuesta().equals(COD_DEUDA_PRESTALUKA_DESTINO)){
            		mensajeIntroduccionBolsas = JSFPortletHelper.getNodePropertyAsString("idContenido", "pendientePrestaLukaPropio", "html");
        			mensajeIntroduccionRecargas = mensajeIntroduccionBolsas; 
        			validoPrestaLuka = false;
        		}
            }            
            
            if(tiempoMinimoRegistro && validoPrestaLuka){
            	String idContenidoBolsas = JSFPortletHelper.getPreference(JSFPortletHelper.getPreferencesObject(),
		 			 	   				   							      "idPuntosPorBolsas", null);
            	String idContenidoRecargas = JSFPortletHelper.getPreference(JSFPortletHelper.getPreferencesObject(),
							      		 								  "idPuntosPorRecargas", null);
            	mensajeIntroduccionBolsas = JSFPortletHelper.getNodePropertyAsString("idContenido", idContenidoBolsas, "html");
				mensajeIntroduccionRecargas = JSFPortletHelper.getNodePropertyAsString("idContenido", idContenidoRecargas, "html");   
            }
            	         	
            
                        
            
        } catch (Exception e) {
            LOGGER.error("Error inesperado al cargar las bolsas y paquetes de recarga.", e);
            JSFMessagesHelper.addServiceErrorMessage("consultaBolsasRecargas");
        }

    }
    
    /**
     * Invoca los action method que inicializan la tabla de historial y el listado 
     * de periodos para parametrizar la cantidad de datos a ver en dicha tabla.
     * Este metodo llena la tabla teniendo en cuenta el periodo por defecto
     * @param phase
     * @see obtenerPeriodosHistorialList() , obtenerHistorial(String rutSinDV, Integer periodo)
     */
    public void initCargarHistorialCanje(PhaseEvent phase) {
        try {
            LOGGER.info("phase " + phase.getPhaseId());
            
            if(periodoHistorialSelector == null){
              //Obtenemos datos necesarios para consulta
                ProfileWrapper profile = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());
                String rut = ProfileWrapperHelper.getPropertyAsString(profile, "rutUsuario");
                //Invocamos metodos de accion que inicializan las bolsas y recargas
                String rutSinDV = Long.toString(RutBean.parseRut(rut).getNumero());
                //periodo por defecto: Historial ultimo mes
                obtenerHistorialCanje(rutSinDV, PERIODO_HISTORIAL_DEFAULT);
                periodoHistorialSelector = PERIODO_HISTORIAL_DEFAULT;
            }
            
            //Inicializamos el listado de periodos para visializar el hostorial
            if(periodosHistorialList == null){
              obtenerPeriodosHistorialList();
            }

            //FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            LOGGER.error("Error inesperado al cargar historial de canje.", e);
            JSFMessagesHelper.addServiceErrorMessage("historial");
        }

    }
    
    
    
    private String puntosToJson;
    
    public void consultarPuntos(PhaseEvent phase){
    	
    	String rut = "";
    	String numeroPcs = "";
    	try{
    	
    		ProfileWrapper profile = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());    		
        	numeroPcs = ProfileWrapperHelper.getPropertyAsString(profile, "numeroPcs");
    		rut = ProfileWrapperHelper.getPropertyAsString(profile, "rutUsuario");
            
             //Invocamos el metodo de accion que consulta los puntos.
            String rutSinDV = Long.toString(RutBean.parseRut(rut).getNumero());
            
            ResultadoConsultarPuntosBean infoPuntos = vtasYMktgFidelizacionDelegate.consultarInfoPuntosCliente(rutSinDV);
            detallePuntos = infoPuntos.getPuntos();
            
            puntosToJson = JsonHelper.toJsonResponse(detallePuntos);
            
    	} catch (DAOException e) {
            LOGGER.error("DAOException al consultar puntos para el rut: " + rut, e);
            JSFMessagesHelper.addServiceErrorMessage("consultaPuntos");
        } catch (ServiceException e) {
        	LOGGER.info("ServiceException en la consulta de puntos: " + numeroPcs + " - "
						+ e.getCodigoRespuesta() + " - " + e.getDescripcionRespuesta());                 
            JSFMessagesHelper.addServiceErrorMessage(e.getCodigoRespuesta());
        } catch (Exception e) {
            LOGGER.error("Exception al conusltar puntos para el rut: " + rut, e);
            JSFMessagesHelper.addServiceErrorMessage("consultaPuntos");
        }
    }
    
    public void initCajaPuntos(PhaseEvent phase){
    	
    	LOGGER.info("INIT CAJA PUNTOS ZONA");
    	String aaa = "";
    	String mercado = "";
    	String rut = "";
    	String numeroPcs = "";
    	
    	try{
    		ProfileWrapper profile = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());    		
        	aaa = ProfileWrapperHelper.getPropertyAsString(profile, "aaa");
        	mercado = ProfileWrapperHelper.getPropertyAsString(profile, "mercado");
        	
        	if(MiEntelBusinessHelper.isMercadoPrepago(mercado)){
        		mostrarCajaPuntos = true;
        	} else if(MiEntelBusinessHelper.isAAATitular(aaa)){
        		mostrarCajaPuntos = true;
        	} else{
        		mostrarCajaPuntos = false;
        		puntosToJson = JsonHelper.toJsonErrorMessage("");
        	}
        	
        	
        	if(mostrarCajaPuntos){
        		LOGGER.info("SE MUESTRA CAJA PUNTOS ZONA");
        		numeroPcs = ProfileWrapperHelper.getPropertyAsString(profile, "numeroPcs");
        		rut = ProfileWrapperHelper.getPropertyAsString(profile, "rutUsuario");
        		
        		//Invocamos el metodo de accion que consulta los puntos.
                String rutSinDV = Long.toString(RutBean.parseRut(rut).getNumero());
                detallePuntos = new PuntosBean(); 
        	
                ResultadoConsultarPuntosBean infoPuntos = vtasYMktgFidelizacionDelegate.consultarInfoPuntosCliente(rutSinDV);                
                detallePuntos = infoPuntos.getPuntos();               
                
                String consultarPuntosPromo = JsfUtil.getRequestParameter("consultarPuntosPromo");
                
                if (consultarPuntosPromo.equals("1")) {
                    PuntosBean puntosPromo = null;
                    try {
    					puntosPromo = vtasYMktgFidelizacionDelegate.consultarPuntosPromocion(1, 10, rutSinDV);
    					detallePuntos.setEstadoPromocion(puntosPromo.getEstadoPromocion());
    				} catch (DAOException e) {
    		            LOGGER.error("DAOException al cargar puntos promocionales para el rut (Sin digito verificador): " + rutSinDV, e);
    		            detallePuntos.setEstadoPromocion("NO");
    		        } catch (ServiceException e) {
    		        	LOGGER.info("ServiceException al cargar puntos promocionales para el rut (Sin digito verificador): " + rutSinDV + " - "
    							+ e.getCodigoRespuesta() + " - " + e.getDescripcionRespuesta());
    		        	detallePuntos.setEstadoPromocion("NO");
    		        } catch (Exception e) {
    		        	LOGGER.error("Exception al cargar puntos promocionales para el rut (Sin digito verificador): " + rutSinDV, e);
    		        	detallePuntos.setEstadoPromocion("NO");
    		        }
    		        
    				Date fechaActual = new Date();
    				if (detallePuntos.getEstadoPromocion().equals("OK") && detallePuntos.getSaldoPuntos() > 0
    						&& (fechaActual.before(puntosPromo.getFechaVencPromocion()))) {
    					detallePuntos.setPuntosPromocion(puntosPromo.getPuntosPromocion());
    					detallePuntos.setPuntosPromocionFormated(puntosPromo.getPuntosPromocionFormated());
    					detallePuntos.setFechaVencPromocion(puntosPromo.getFechaVencPromocion());
    					detallePuntos.setFechaVencPromoFormated(puntosPromo.getFechaVencPromoFormated());    					
    				}
                }
                
                String saldoPuntos = Integer.toString(detallePuntos.getSaldoPuntos());
				detallePuntos.setSaldoPuntosFormated(Utils.formatMoneyPuntos(Double.parseDouble(saldoPuntos)));
                
                puntosToJson = JsonHelper.toJsonResponse(detallePuntos);
        	}
        	
    	} catch (DAOException e) {
            LOGGER.error("DAOException al iniciar caja puntos zona para el movil: " + numeroPcs, e);
            JSFMessagesHelper.addServiceErrorMessage("consultaPuntos");
        } catch (ServiceException e) {
        	LOGGER.info("ServiceException al iniciar caja puntos zona para movil: " + numeroPcs + " - "
						+ e.getCodigoRespuesta() + " - " + e.getDescripcionRespuesta());                 
            JSFMessagesHelper.addServiceErrorMessage(e.getCodigoRespuesta());
        } catch (Exception e) {
            LOGGER.error("Exception al iniciar caja puntos zona", e);
            puntosToJson = JsonHelper.toJsonErrorMessage("");
        }
    	
    }
    
    /**
     * Action method para consultar informacion de puntos para el rut del usuario en sesion
     * @param <code>rutSinDV</code> rut sin digito verificador
     */
    private void consultarInfoPuntosCliente(String rutSinDV, String msisdn){
        ResultadoConsultarPuntosBean infoPuntos = new ResultadoConsultarPuntosBean();
        
        try{        	        	
            infoPuntos = vtasYMktgFidelizacionDelegate.consultarInfoPuntosCliente(rutSinDV);
            infoPuntos = vtasYMktgFidelizacionDelegate.consultarCategoriaCliente(infoPuntos, msisdn);
            detallePuntos = infoPuntos.getPuntos();
            categoriaClientePuntos = infoPuntos.getCategoriaCliente();
            
        } catch (DAOException e) {
            LOGGER.error("DAOException al consultar puntos para el rut: " + rutSinDV, e);
            JSFMessagesHelper.addServiceErrorMessage("consultaPuntos");
        } catch (ServiceException e) {
        	LOGGER.info("ServiceException en la consulta de puntos: " + msisdn + " - "
						+ e.getCodigoRespuesta() + " - " + e.getDescripcionRespuesta()); 
            JSFMessagesHelper.addServiceErrorMessage(e.getCodigoRespuesta());
        } catch (Exception e) {
            LOGGER.error("Exception al conusltar puntos para el rut: " + rutSinDV, e);
            JSFMessagesHelper.addServiceErrorMessage("consultaPuntos");
        }
        
    }
    
    /**
     * Action method para obtener historial de canje de un usuario, dependiendo del periodo seleccionado
     * @param rutSinDV
     * @param periodo
     */
    private void obtenerHistorialCanje(String rutSinDV, Integer periodo){
        
    	ResultadoObtenerHistorialBean resultado = new ResultadoObtenerHistorialBean();
        String numeroPcs = "";
        
        try{
        	ProfileWrapper profile = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());    		
        	numeroPcs = ProfileWrapperHelper.getPropertyAsString(profile, "numeroPcs");
        	
            //resultado = vtasYMktgFidelizacionDelegate.obtenerHistorial(rutSinDV, periodo);
            detalleHistorial = resultado.getDetalle();
            tablaHistorial = resultado.getHistorial();
            
            existenRegistrosHist = (tablaHistorial != null && !tablaHistorial.isEmpty());
            
        } catch (DAOException e) {
            LOGGER.error("DAOException al consultar historial para los siguientes rutSinDV y periodo: " + rutSinDV + " " + periodo, e);
            JSFMessagesHelper.addServiceErrorMessage("historial");
        } catch (ServiceException e) {
        	LOGGER.info("ServiceException al consultar historial: " + numeroPcs + " - "
						+ e.getCodigoRespuesta() + " - " + e.getDescripcionRespuesta());
            JSFMessagesHelper.addServiceErrorMessage(e.getCodigoRespuesta());
        } catch (Exception e) {
            LOGGER.error("Exception al consultar historial para los siguientes rutSinDV y periodo: " + rutSinDV + " " + periodo, e);
            JSFMessagesHelper.addServiceErrorMessage("historial");
        }

    }
    
    /**
     * Action method para obtener historial de canje del usuario en sesion. 
     * Actualiza el historial dependiendo del nuevo periodo seleccionado por el usuario.
     * @param periodo
     * @see obtenerHistorial(String rutSinDV, Integer periodo)
     */
    public void actualizarTablaHistorialSelector(ValueChangeEvent event){
        try{            
            ProfileWrapper profile = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());            
            String rut = ProfileWrapperHelper.getPropertyAsString(profile, "rutUsuario");
            String rutSinDV = Long.toString(RutBean.parseRut(rut).getNumero());
            //Obtenemos el nuevo valor seleccionado en el selector
            String data = event.getNewValue().toString();
            periodoHistorialSelector = Integer.parseInt(data);
            obtenerHistorialCanje(rutSinDV, periodoHistorialSelector);

        } catch (Exception e) {
            LOGGER.error("Exception inesperada al actualizar el historial por selector");
            JSFMessagesHelper.addServiceErrorMessage("actualizarHistorial");
        }
        
    }    
    
    /**
     * Action method para la operacion de canjear bolsas o recargas para otros
     * @param event evento disparado al confirmar el canje de una bolsa o recarga
     */
    public void regalarPuntos(PhaseEvent event){
        ResultadoRegalarPuntosBean resultado = null;
        String tipoCanje = "";
        String msisdnSponsor = "";                
        
        try{
        	// ServiceMessages - mensajes de errorMessages.properties
            ServiceMessages errorMessages = MiEntelProperties.getServiceMessages();
            
        	String isRecarga = JsfUtil.getRequestParameter("isRecarga");               
            
        	//Verificamos que la operacion de canje corresponda a una recarga para saber que mensaje de error mostrar
            if(isRecarga != null && isRecarga.equals("true")){               
                tipoCanje = "regalarRecarga";
            }
            else{
            	tipoCanje = "regalarBolsa";
            }
            
            //Obtenemos datos necesarios para consulta
            ProfileWrapper profile = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());
            String rut = ProfileWrapperHelper.getPropertyAsString(profile, "rutUsuario");
            msisdnSponsor = ProfileWrapperHelper.getPropertyAsString(profile, "numeroPcs");            
            
            
            String mercado = "";
            String subMercado = "";
            int flagBam; 
           
            // Se asegura que el mercado es el del usuario logueado
            if(msisdnSponsor.equals(ProfileWrapperHelper.getPropertyAsString(profile, "numeroPcsSeleccionado"))){
            	mercado = ProfileWrapperHelper.getPropertyAsString(profile, "mercado");
            	subMercado = ProfileWrapperHelper.getPropertyAsString(profile, "subMercado");
            	flagBam = ProfileWrapperHelper.getPropertyAsInt(profile, "flagBam");
            }
            else{
            	PerfilUsuarioBean pub = cuentaDelegate.obtenerPerfilUsuario(msisdnSponsor);
            	mercado = pub.getMercado();
            	subMercado = pub.getUsuarioSeleccionado().getSubMercado();
            	flagBam = pub.getFlagBam();            	
            }           
                       
            String msisdnRecibe = JsfUtil.getRequestParameter("msisdnRecibe");
            String monto = JsfUtil.getRequestParameter("monto");
            String codProducto = JsfUtil.getRequestParameter("codProducto");
            String bolsaNombre = JsfUtil.getRequestParameter("bolsaNombre");
            PerfilUsuarioBean perfilDestino = null;
            
            try{
            	perfilDestino = cuentaDelegate.obtenerPerfilUsuario(msisdnRecibe);
            }catch (ServiceException e) {
            	LOGGER.info("ServiceException al obtener perfil del movil destino: " + msisdnRecibe + " - "
						+ e.getCodigoRespuesta() + " - " + e.getDescripcionRespuesta());
            	if(Utils.isEmptyString(e.getCodigoRespuesta()))
            		respuestaCanjearPuntosJson = JsonHelper.toJsonServiceErrorMessage("vtasymktg", "0001");
            	if(COD_MOVIL_DEST_NO_REGISTRADO.equals(e.getCodigoRespuesta()))
            		respuestaCanjearPuntosJson = JsonHelper.toJsonServiceErrorMessage("vtasymktg", "movilDestinoNoRegistrado");
            	else
            		respuestaCanjearPuntosJson = JsonHelper.toJsonServiceErrorMessage("vtasymktg", e.getCodigoRespuesta());
            	return;
			}
            
            String mercadoRecibe = perfilDestino.getMercado();
            String rutSponsorConDVCERO = RutBean.parseRut(rut).getNumero() + "0" + RutBean.parseRut(rut).getDigito();
            
            
            boolean validoCanje = true;            
            
            // Validacion contra la matriz
            
            obtenerCatalogoZonaCanje(mercado, subMercado, flagBam);            
            
            if(puedeCanjearRegalar(matrizCatalogoBean.getMatrizCatalogo(), msisdnRecibe, isRecarga.equals("true"), monto)){
            	
            	// Se realiza validacion presta luka si el usuario destino es pp             	
                if(MiEntelBusinessHelper.isMercadoPrepago(mercadoRecibe)){
                	ZonaPerfilBean zonaPerfilBean = beneficiosDelegate.getZonaPerfilRegalo(msisdnSponsor, msisdnRecibe, rutSponsorConDVCERO);
                	
            		if(!zonaPerfilBean.getStatusRespuesta().equals(CODOK_PRESTALUKA1) 
            		   && !zonaPerfilBean.getStatusRespuesta().equals(CODOK_PRESTALUKA2)){
                        respuestaCanjearPuntosJson = JsonHelper.toJsonErrorMessage(errorMessages
                        							 .getErrorMessage(SERVICE_NAME_ZONAENTEL, 
                        									 		  zonaPerfilBean.getStatusRespuesta()));
                        validoCanje = false;            			
            			     			
            		}
                	            	            
                }
            }
            else{ 
            	// La validacion contra la matriz no deberia fallar nunca si es una recarga a menos que el destino sea ss
            	validoCanje = false;            	
            	if(MiEntelBusinessHelper.isMercadoSuscripcion(mercadoRecibe)){
            		respuestaCanjearPuntosJson = JsonHelper.toJsonErrorMessage(errorMessages.getErrorMessage("vtasymktg","noRecibeCanje")); 
            	}    
            	else{
            		if(isRecarga != null && isRecarga.equals("true")){               
            			respuestaCanjearPuntosJson = JsonHelper.toJsonErrorMessage(errorMessages.getErrorMessage("vtasymktg","noRecibeEstaRecarga"));
                    }
                    else{
                    	respuestaCanjearPuntosJson = JsonHelper.toJsonErrorMessage(errorMessages.getErrorMessage("vtasymktg","noRecibeEstaBolsa"));
                    }
            		
            	}
            }                                                              
            
            if(validoCanje){
            	String rutSinDV = Long.toString(RutBean.parseRut(rut).getNumero());
                
                //Invocamos el metodo de accion que realiza el canje de puntos
                resultado = vtasYMktgFidelizacionDelegate.regalarPuntos(msisdnSponsor, msisdnRecibe, rutSinDV, monto, mercado);                                
                
                //Armamos respuesta de la recarga en formato JSON                       
                if( resultado == null ){                	
                    respuestaCanjearPuntosJson = JsonHelper.toJsonServiceErrorMessage(tipoCanje);
                }
                else{
                	//Carga de datos para el marcado GTM
                    TransaccionGTMBean transGTM = new TransaccionGTMBean();
                    transGTM.setIdTransaccion(msisdnSponsor.substring(msisdnSponsor.length() - 4));
                    transGTM.setSkuID(codProducto);
                    transGTM.setNombre(bolsaNombre);
                    transGTM.setNuevoValor(monto);
                    transGTM.setCostoOperacional(0);
                    transGTM.setNumeroPlanes(1);
                    transGTM.setNumeroOperaciones(1);
                    transGTM.setValorTransaccion(Double.parseDouble(monto) + transGTM.getCostoOperacional());
                	
                	if(isRecarga != null && isRecarga.equals("true")){               
                		respuestaCanjearPuntosJson = JsonHelper.toJsonResponse(transGTM, tipoCanje, "vtasymktg", 
                															  new String[]{monto, msisdnRecibe});
                    }
                    else{
                    	respuestaCanjearPuntosJson = JsonHelper.toJsonResponse(transGTM, tipoCanje, "vtasymktg", 
                    														  new String[]{JsfUtil.getRequestParameter("bolsaNombre"),
                    																	   JsfUtil.getRequestParameter("puntosCambio"),
                    																	   msisdnRecibe});
                    }                	
                }
                
                //Actualizamos la info de los puntos.            
                LOGGER.info("Actualizando informacion de puntos despues de la recarga");
                consultarInfoPuntosCliente(rutSinDV, msisdnSponsor);
            }                       
            
        } catch (DAOException e) {
            LOGGER.error("DAOException al canjear puntos: ", e);
            respuestaCanjearPuntosJson = JsonHelper.toJsonServiceErrorMessage(tipoCanje);
        } catch (ServiceException e) {
        	LOGGER.info("ServiceException al canjear puntos: " + msisdnSponsor + " - "
						+ e.getCodigoRespuesta() + " - " + e.getDescripcionRespuesta());            
            respuestaCanjearPuntosJson = JsonHelper.toJsonServiceErrorMessage("vtasymktg", e.getCodigoRespuesta());
        } catch(Exception e){
            LOGGER.error("Exception al canjear puntos", e);
            respuestaCanjearPuntosJson = JsonHelper.toJsonServiceErrorMessage(tipoCanje);
        }
        
    }
    
    /**
     * Action method para la operacion de canjear puntos para si mismo
     * @param event evento disparado al confirmar el canje de una bolsa o recarga
     */
    public void canjearBolsasRecargas(PhaseEvent event){
        
    	ResultadoCanjeDePuntosBean resultado = null;
        String tipoCanje = "";
        String msisdn = "";
        
        try{
        	// ServiceMessages - mensajes de errorMessages.properties
            ServiceMessages errorMessages = MiEntelProperties.getServiceMessages();
            
        	String isRecarga = JsfUtil.getRequestParameter("isRecarga");
        	String monto = JsfUtil.getRequestParameter("monto");
        	String codProducto = JsfUtil.getRequestParameter("codProducto");
        	String puntosCambio = JsfUtil.getRequestParameter("puntosCambio");
        	String bolsaNombre = JsfUtil.getRequestParameter("nombreBolsa");        	
        	
        	//Verificamos que la operacion de canje corresponda a una recarga y obtenemos el numero a recargar
            if(isRecarga != null && isRecarga.equals("true")){
                msisdn = JsfUtil.getRequestParameter("msisdn");
                tipoCanje = "canjearRecarga";
            }
            else{
            	tipoCanje = "canjearBolsa";
            }
            
            //Obtenemos datos necesarios para consulta
            ProfileWrapper profile = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());
            
            String rut = ProfileWrapperHelper.getPropertyAsString(profile, "rutUsuario");                        
            msisdn = ProfileWrapperHelper.getPropertyAsString(profile, "numeroPcs");     
            
            String montoCanje = JsfUtil.getRequestParameter("monto");               
            
            String mercado = "";
            String subMercado = "";
            int flagBam;                     
                        
            // Se asegura que el mercado es el del usuario logueado
            if(msisdn.equals(ProfileWrapperHelper.getPropertyAsString(profile, "numeroPcsSeleccionado"))){
            	mercado = ProfileWrapperHelper.getPropertyAsString(profile, "mercado");
            	subMercado = ProfileWrapperHelper.getPropertyAsString(profile, "subMercado");
            	flagBam = ProfileWrapperHelper.getPropertyAsInt(profile, "flagBam");
            }
            else{
            	PerfilUsuarioBean pub = cuentaDelegate.obtenerPerfilUsuario(msisdn);
            	mercado = pub.getMercado();
            	subMercado = pub.getUsuarioSeleccionado().getSubMercado();
            	flagBam = pub.getFlagBam();             	
            }
            
            
            String rutSinDV = Long.toString(RutBean.parseRut(rut).getNumero());
            String asociacionRutMovil = "";                        
            
            // Validacion contra matriz de catalogo  
            
            boolean validoCanje = true;
            obtenerCatalogoZonaCanje(mercado, subMercado, flagBam);           
            
            // Todos los PP deberian pasar esta validacion
            if(!puedeCanjearRegalar(matrizCatalogoBean.getMatrizCatalogo(), msisdn, isRecarga.equals("true"), montoCanje)){
            	
                // Validacion presta luka para pp se hace desde la vista
            	
            	// La validacion contra la matriz no deberia fallar nunca si es una recarga a menos que el destino sea ss
            	validoCanje = false;            	
            	if(MiEntelBusinessHelper.isMercadoSuscripcion(mercado)){
            		respuestaCanjearPuntosJson = JsonHelper.toJsonErrorMessage(errorMessages.getErrorMessage("vtasymktg","noRecibeCanje")); 
            	}    
            	else{
            		if(isRecarga != null && isRecarga.equals("true")){               
            			respuestaCanjearPuntosJson = JsonHelper.toJsonErrorMessage(errorMessages.getErrorMessage("vtasymktg","noRecibeEstaRecarga"));
                    }
                    else{
                    	respuestaCanjearPuntosJson = JsonHelper.toJsonErrorMessage(errorMessages.getErrorMessage("vtasymktg","noRecibeEstaBolsa"));
                    }
            	}            	
            }    
            
            // Switch para manejar mensaje en caso de erroe en validacion RutMovil
            boolean errorValidacionRutMovil = false; 
                      
            if(validoCanje){
            	if (MiEntelBusinessHelper.isMercadoPrepago(mercado)) {
                	//Validamos asociacion rut movil
                	asociacionRutMovil = vtasYMktgFidelizacionDelegate.validarAsociacionRutMovil(msisdn, rutSinDV);
                	
                	//Preguntamos si la asociacion rut movil es valida
                	if(asociacionRutMovil.equalsIgnoreCase("true")){
        	            //Invocamos el metodo de accion que realiza el canje de puntos
        	            resultado = vtasYMktgFidelizacionDelegate.canjearPuntos(msisdn, rutSinDV, montoCanje);                		
                    }else{
                    	respuestaCanjearPuntosJson = JsonHelper.toJsonServiceErrorMessage("validacionRutMovil");
                    	errorValidacionRutMovil = true;
                    }
                	
                } else {
                	//Invocamos el metodo de accion que realiza el canje de puntos
    	            resultado = vtasYMktgFidelizacionDelegate.canjearPuntos(msisdn, rutSinDV, montoCanje);    	            
                }
            	            	     
            	if(!errorValidacionRutMovil){
            		if( resultado == null){                	
                        respuestaCanjearPuntosJson = JsonHelper.toJsonServiceErrorMessage(tipoCanje);
                    }
                    else{
                    	//Carga de datos para el marcado GTM
                        TransaccionGTMBean transGTM = new TransaccionGTMBean();
                        transGTM.setIdTransaccion(msisdn.substring(msisdn.length() - 4));
                        transGTM.setSkuID(codProducto);
                        transGTM.setNombre(bolsaNombre);
                        transGTM.setNuevoValor(monto);
                        transGTM.setCostoOperacional(0);
                        transGTM.setNumeroPlanes(1);
                        transGTM.setNumeroOperaciones(1);
                        transGTM.setValorTransaccion(Double.parseDouble(puntosCambio) + transGTM.getCostoOperacional());
                    	
                    	if(isRecarga != null && isRecarga.equals("true")){               
                    		respuestaCanjearPuntosJson = JsonHelper.toJsonResponse(transGTM, tipoCanje, "vtasymktg",
                    															   new String[]{montoCanje});
                        }
                        else{
                        	respuestaCanjearPuntosJson = JsonHelper.toJsonResponse(transGTM, tipoCanje, "vtasymktg", 
                        														  new String[]{JsfUtil.getRequestParameter("bolsaNombre"),
                        																	   JsfUtil.getRequestParameter("puntosCambio")});
                        }                	                
                    }
            	}
                                                       
                //Actualizamos la info de los puntos.
                LOGGER.info("Actualizando informacion de puntos despues de la recarga");
                consultarInfoPuntosCliente(rutSinDV, msisdn); 
            }            
            
        } catch (DAOException e) {
            LOGGER.error("DAOException al canjear puntos: ", e);
            respuestaCanjearPuntosJson = JsonHelper.toJsonServiceErrorMessage(tipoCanje);
        } catch (ServiceException e) {
        	LOGGER.info("ServiceException al canjear puntos: " + msisdn + " - "
						+ e.getCodigoRespuesta() + " - " + e.getDescripcionRespuesta()); 
            respuestaCanjearPuntosJson = JsonHelper.toJsonServiceErrorMessage("vtasymktg", e.getCodigoRespuesta());
        } catch(Exception e){
            LOGGER.error("Exception al canjear puntos", e);
            respuestaCanjearPuntosJson = JsonHelper.toJsonServiceErrorMessage(tipoCanje);
        }
        
    }
    
    /**
     * Action method para obtener catalogo de bolsas y recargas para canjear por puntos
     * @param mercado
     */
    private void obtenerCatalogoZonaCanje(String mercado, String subMercado, int flagBam){
        
    	int canalRequest = (flagBam == 0) ? CANAL_WEB : CANAL_WEBBAM;
        int mercadoRequest = (MiEntelBusinessHelper.isMercadoPrepago(mercado) && flagBam == 0) ? MERCADO_VOZPP 
        					 : (MiEntelBusinessHelper.isMercadoCuentaControlada(mercado) && flagBam == 0) ? MERCADO_VOZCC 
        					 : (MiEntelBusinessHelper.isMercadoSuscripcion(mercado) && flagBam == 0) ? MERCADO_VOZSS 
        					 : (MiEntelBusinessHelper.isMercadoPrepago(mercado) && flagBam == 1) ? MERCADO_BAMPP        					 
        					 : (MiEntelBusinessHelper.isMercadoSuscripcion(mercado) && flagBam == 1) ? MERCADO_BAMSS
        					 : (MiEntelBusinessHelper.isMercadoCuentaControlada(mercado) && flagBam == 1) ? (subMercado.equals(SUB_BAMCCN)) ? MERCADO_BAMCCN : MERCADO_BAMCC
        					 : null;
                
        LOGGER.info("Obtener catalogo de canje para canal: "+canalRequest+" y mercado: "+mercadoRequest);
        
        try {
        	
        	matrizCatalogoBean = vtasYMktgFidelizacionDelegate.catalogoCanje(canalRequest, mercadoRequest);
        	recargas = matrizCatalogoBean.getRecargas();
            bolsasVoz = matrizCatalogoBean.getBolsasVoz();
            bolsasSMS = matrizCatalogoBean.getBolsasSMS();
            bolsasBAM = matrizCatalogoBean.getBolsasBAM();
            bolsasMixtas = matrizCatalogoBean.getBolsasMixtas(); 
            bolsasInternetMovil = matrizCatalogoBean.getBolsasInternetMovil();
            bolsasCanjeBAM = matrizCatalogoBean.getBolsasCanjeBAM();            
            bolsas = new ArrayList<Set<ItemProductoCanjeBean>>(){{add(bolsasVoz);add(bolsasSMS);
            													  add(bolsasMixtas);add(bolsasBAM);add(bolsasInternetMovil);add(bolsasCanjeBAM);}};  
            
            //valores usados para determinar en la vista si hay datos para mostrar
            existenRecargas = (recargas != null && !recargas.isEmpty());
            existenBolsasVoz = (bolsasVoz != null && !bolsasVoz.isEmpty());
            existenBolsasSMS = (bolsasSMS != null && !bolsasSMS.isEmpty());
            existenBolsasBAM = (bolsasBAM != null && !bolsasBAM.isEmpty());
            existenBolsasMixtas = (bolsasMixtas != null && !bolsasMixtas.isEmpty());
            existenBolsasCanjeBAM = (bolsasCanjeBAM != null && !bolsasCanjeBAM.isEmpty());
        	
        } catch (DAOException e) { 
            LOGGER.error("DAOException al obtener catalogo de bolsas y recargas para el mercado: " + mercado, e);
            JSFMessagesHelper.addServiceErrorMessage("consultaBolsasRecargas");
        } catch (ServiceException e) {
        	LOGGER.info("ServiceException en la obtencion de catalogo: "
    					+ e.getCodigoRespuesta() + " - " + e.getDescripcionRespuesta());            
            JSFMessagesHelper.addServiceErrorMessage(e.getCodigoRespuesta());
        } catch (Exception e) {
            LOGGER.error("Exception al obtener catalogo de bolsas y recargas para el mercado: " + mercado, e);
            JSFMessagesHelper.addServiceErrorMessage("consultaBolsasRecargas");
        }                

    }
    
    /**
     * Metodo que valida contra la matriz del catalogo si el numero de destino puede recibi la recarga o la bolsa 
     * @param matrizCatalogo Matriz en contra la cual se realiza la validaciÃ³n
     * @param msisdnDestino Numero al que se va hacer la recarga o canje
     * @param isRecarga Indice si se esta trabajando con recargas o con bolsas
     * @param codProducto Codigo del producto que se va a canjear o a recargar
     */
    private boolean puedeCanjearRegalar(Map<String,ItemMatrizCanjeBean> matrizCatalogo, String msisdnDestino, boolean isRecarga, String monto) {
    	boolean puede = false;
    	String mercado = "";
    	String llaveTipoOperacion = "";
    	
    	try{
	    	ProfileWrapper profile = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());
	        String numeroPropioPerfil = ProfileWrapperHelper.getPropertyAsString(profile, "numeroPcs"); 	        
	    	boolean simismo = msisdnDestino.equals(numeroPropioPerfil);	    		    		               	       	                   	    		    		    		    	
	    	
	    	if(simismo){
	    		
	    		String mercadoPropio = "";  
	    		String subMercadoPropio = "";
	 	        int flagBamPropio;	
	    		
	    		// Se asegura que el mercado y el flagBam es el del usuario logueado 
	            if(numeroPropioPerfil.equals(ProfileWrapperHelper.getPropertyAsString(profile, "numeroPcsSeleccionado"))){
	            	mercadoPropio = ProfileWrapperHelper.getPropertyAsString(profile, "mercado");
	            	flagBamPropio = ProfileWrapperHelper.getPropertyAsInt(profile, "flagBam");
	            }
	            else{
	            	PerfilUsuarioBean perfilPropio = cuentaDelegate.obtenerPerfilUsuario(numeroPropioPerfil);	            	
	            	mercadoPropio = perfilPropio.getMercado();
	            	subMercadoPropio = perfilPropio.getUsuarioSeleccionado().getSubMercado();
	            	flagBamPropio = perfilPropio.getFlagBam();
	            }	            
	            
	    		mercado = cadenaMercadoMatriz(flagBamPropio, mercadoPropio, subMercadoPropio); 
	    	}
	    	else{
    			PerfilUsuarioBean perfilDestino = cuentaDelegate.obtenerPerfilUsuario(msisdnDestino);
	    		mercado = cadenaMercadoMatriz(perfilDestino.getFlagBam(), perfilDestino.getMercado(), 
	    									  perfilDestino.getUsuarioSeleccionado().getSubMercado());
	    	}    	    	
	    		    	
	    	llaveTipoOperacion = (isRecarga && !simismo) ? RECARGA_OTROS : (isRecarga && simismo) ? RECARGA_SIMISMO : 
	    								(!isRecarga && !simismo) ? BOLSA_OTROS : BOLSA_SIMISMO; 	    		    	
	    	
	    	if(matrizCatalogo.get(llaveTipoOperacion) != null){	    	    		
    			for(ItemDetalleCanjeBean detalle : matrizCatalogo.get(llaveTipoOperacion).getListDetalles()){
	    			if(detalle.getHashMercados().get(mercado) != null){
	    				puede = (detalle.getHashMercados().get(mercado).getHashProductos().get(monto) != null) ? true : false;	    				
	    			}		    		
	    			else{	    				
	    				LOGGER.info("No se encontro el mercado "+mercado+" para la operacion "+llaveTipoOperacion+" de la matriz.");
	    			}
		    	}	    			    	
	    	}
	    	else{
	    		LOGGER.info("No se encontro la operacion '"+llaveTipoOperacion+"' en la matriz.");
	    	}	    	
	    		    	
    	}
    	catch(Exception e){
    		LOGGER.error("Error validando contra matriz de catalogo. ", e);
    	}
    	
    	if(puede){
    		LOGGER.info("Si puede canjear bolsa/recarga");    		
    	}
    	else{
    		LOGGER.info("No puede canjear bolsa/recarga");
    	}
    	
    	LOGGER.info("Operacion: "+llaveTipoOperacion);
    	LOGGER.info("Mercado destino: "+mercado);
    	
    	return puede;
    }    
    
    /**
     * Metodo privado utilitario que construye la cadena del mercado requerida para 
     * validar contra la matriz del catalogo. 
     * @param flagBam 
     * @param mercado
     */
    private String cadenaMercadoMatriz(int flagBam, String mercado, String subMercado){
        String cadenaMercado = "";
    	cadenaMercado += (flagBam==0) ? "VOZ" : "BAM";    	    			    	
    	cadenaMercado += (MiEntelBusinessHelper.isMercadoPrepago(mercado)) ? MiEntelBusinessHelper.getSiglaPrepago()     			       
    			       : (MiEntelBusinessHelper.isMercadoSuscripcion(mercado)) ? MiEntelBusinessHelper.getSiglaSuscripcion()
    			       : (MiEntelBusinessHelper.isMercadoCuentaControlada(mercado)) ? (subMercado.equals(SUB_BAMCCN)) ? "CCN" 
    			    		   												        : MiEntelBusinessHelper.getSiglaCuentaControlada()
    			       : null;
        return cadenaMercado;
    }
    
    /**
     * Metodo utilitario que devuelve un listado de periodos para ver el historial de canje <br>
     * haciendo uso de la clase {@link JsfUtil}
     */
    private void obtenerPeriodosHistorialList() {
        try{
            List<CodeDescBean> periodosList = ParametrosHelper.obtenerPeridosHistorialList();
            boolean opcionVacia = periodosList.isEmpty() ? true : false;
            periodosHistorialList = JsfUtil.getSelectItemsCodeDesc(periodosList, opcionVacia);
        } catch(Exception e){
            LOGGER.error("Exception caught in <obtenerPeriodosHistorialList>: ", e);
        }
    }
    
    /**
     * Metodo utilitario que obtiene la fecha limite hasta la cual un usuario puede hacer uso de una recarga.
     * Si ocurre algun error calculando la fecha, se devuelve la fecha en que se intento hacer la recarga.
     * @return {@link Date} fecha obtenida de sumar los dias de validez de una recarga a la fecha en que se realizo
     */
    private Date calcularrFechaValidezRecarga(){
        Date newDate = new Date();
        
        try{
            newDate = DateHelper.addDays(
            					respuestaCanjearPuntos.getFechaCanje(), 
            					respuestaCanjearPuntos.getDiasDeValidez());
        }catch (Exception e) {
            LOGGER.error("Exception caught in <calcularrFechaValidezRecarga>: ",e);
            JSFMessagesHelper.addServiceErrorMessage("serviceDisabled");
        }
        return newDate;
    }
    
    /**
     * Metodo privado utilitario para setear los atributos formateados
     */
    private void setFormattedValues() {
        
        respuestaCanjearPuntos.setFechaCanjeFormated(DateHelper
                .format(respuestaCanjearPuntos.getFechaCanje(),
                        "dd/MM/yyyy hh.mm 'hrs'"));

        respuestaCanjearPuntos.setDiasDeValidezFormated(DateHelper
                .format(calcularrFechaValidezRecarga(),
                        "dd/MM/yyyy"));

    }

	public String getPuntosToJson() {
		return puntosToJson;
	}

	public void setPuntosToJson(String puntosToJson) {
		this.puntosToJson = puntosToJson;
	}

    /**
     * @return the vtasYMktgFidelizacionDelegate
     */
    public VtasYMktgFidelizacionDelegate getVtasYMktgFidelizacionDelegate() {
        return vtasYMktgFidelizacionDelegate;
    }

    /**
     * @param vtasYMktgFidelizacionDelegate the vtasYMktgFidelizacionDelegate to set
     */
    public void setVtasYMktgFidelizacionDelegate(
            VtasYMktgFidelizacionDelegate vtasYMktgFidelizacionDelegate) {
        this.vtasYMktgFidelizacionDelegate = vtasYMktgFidelizacionDelegate;
    }

    /**
     * @return the beneficiosDelegate
     */
    public BeneficiosDelegate getBeneficiosDelegate() {
        return beneficiosDelegate;
    }

    /**
     * @param beneficiosDelegate the beneficiosDelegate to set
     */
    public void setBeneficiosDelegate(BeneficiosDelegate beneficiosDelegate) {
        this.beneficiosDelegate = beneficiosDelegate;
    }

    /**
     * @return the cuentaDelegate
     */
    public CuentaDelegate getCuentaDelegate() {
        return cuentaDelegate;
    }

    /**
     * @param cuentaDelegate the cuentaDelegate to set
     */
    public void setCuentaDelegate(CuentaDelegate cuentaDelegate) {
        this.cuentaDelegate = cuentaDelegate;
    }

	/**
	 * @return the bolsasZonaVerano
	 */
	public Set<ItemProductoCanjeBean> getBolsasZonaVerano() {
		return bolsasZonaVerano;
	}

	/**
	 * @param bolsasZonaVerano the bolsasZonaVerano to set
	 */
	public void setBolsasZonaVerano(Set<ItemProductoCanjeBean> bolsasZonaVerano) {
		this.bolsasZonaVerano = bolsasZonaVerano;
	}
	/**
	 * @return the listaBolsasZonaVerano
	 */
	public List<ItemProductoCanjeBean> getListaBolsasZonaVerano() {
		return listaBolsasZonaVerano;
	}

	/**
	 * @param listaBolsasZonaVerano the listaBolsasZonaVerano to set
	 */
	public void setListaBolsasZonaVerano(
			List<ItemProductoCanjeBean>
			listaBolsasZonaVerano) {
		this.listaBolsasZonaVerano = listaBolsasZonaVerano;
	}	
	

	/**
	 * @return the tagZonaVerano
	 */
	public List<String[]> getTagZonaVerano() {
		return tagZonaVerano;
	}

	/**
	 * @param tagZonaVerano the tagZonaVerano to set
	 */
	public void setTagZonaVerano(List<String[]> tagZonaVerano) {
		this.tagZonaVerano = tagZonaVerano;
	}

	/**
     * Metodo de obtener los codigos habilitados para visualizar en zona verano.   
     * @return boolean 
     */
    public void filtroZonaVerano(PortletPreferences preferences, List<ItemProductoCanjeBean> listaZonaVerano){
    	String listaVector[];
    	String codYDes[];
    	tagZonaVerano = new ArrayList<String[]>(); 
    	String codigoZona="";  
    	List<ItemProductoCanjeBean> listaZona;
    	listaZona = listaZonaVerano;    	
    	boolean sw = false;
    	try{
	       codigoZona = JSFPortletHelper.getPreference(preferences,  "listaCodigosZona", "");			   
		   codigoZona = codigoZona==null?"":codigoZona;  
    	      	   
	     if(!codigoZona.equals("")){	
		      listaVector = codigoZona.split(","); 
	    	   for (int i=0; i< listaVector.length;i++ ) {
	    		       codYDes = new String[4];
		    		   codYDes[0]=listaVector[i];
		    		   codYDes[1]=getTitulosZona(preferences,listaVector[i]);
		    		   sw = false;	
		    		   codYDes[3]="";
		    		   for (ItemProductoCanjeBean itemProductoCanjeBean : listaZona) {		    			   
		    			   if(itemProductoCanjeBean.getCodZona().equals(listaVector[i])){
		    				   sw=true;
		    				   codYDes[2]=textoFechaDebeSaber(listaVector[i],itemProductoCanjeBean.getFechaIniVigencia(),itemProductoCanjeBean.getFechaFinVigencia(),itemProductoCanjeBean.getAnoVigencia());
		    			   }		    			   
		    		   }		    		   
		    		   if(sw){
		    			   codYDes[3]="Ok"; 
		    		   }
		    		   tagZonaVerano.add(codYDes);
			    }
    	   if(listaVector.length>0){
    		   existenZonasVerano = true; 
    	   }  
	     }
		 
        }catch(Exception e){
        	LOGGER.error("No se ha podido obtener los valores de los codigos de zonaVerano desde el preferences :"+ e.getStackTrace() , e);        	
        }
       
    }
    
    /**
     * 
     * @param  zona     *  
     * @throws DAOException
     * @throws ServiceException
     */
    public String textoFechaDebeSaber(String zona,String fechaInicio,String fechaFin,int ano) throws ServiceException, DAOException{    	    
    	   String textoDebeSaber="";    	  
    	   try{    		 
			   Node texto = JSFPortletHelper.getContentNode("idContenido", "TextoDebesSaber_"+zona);   		       
   		       if(texto!=null){   		        	
   		    	    textoDebeSaber = texto.getProperty("html").getValue().getStringValue();	
   		    	    textoDebeSaber = textoDebeSaber.replaceAll("<fechaInicio>" ,fechaInicio);
   		    	    textoDebeSaber = textoDebeSaber.replaceAll("<fechaFin>" ,fechaFin);
   		    	    textoDebeSaber = textoDebeSaber.replaceAll("<ano>" ,ano+"");
   		        }	    	   
		   }catch(Exception e){
	       	LOGGER.error("No se ha podido el texto debe saber para la zona : "+zona +" Error : "+ e.getMessage(), e);        	
	      }		  	
        return textoDebeSaber;
    }  
    
    
    /**
     * Metodo para buscar los tutulos de las pestanas para zona verano  
     * @return boolean 
     */
    public String getTitulosZona(PortletPreferences preferences,String codigoZona){ 
    	String titulo="";
    	try{    	   
			  try{
				  titulo = JSFPortletHelper.getPreference(preferences,  codigoZona, "");	    	   
			   }catch(Exception e){
		       	LOGGER.error("No se ha podido encontrar el valor de preferences correspondiete al titulo del zona con codigo : "+codigoZona +" Error : "+ e.getMessage(), e);        	
		      }		 
        }catch(Exception e){
        	LOGGER.error("No se ha podido encontrar el valor de preferences correspondiete al titulo del zona con codigo : "+codigoZona +" Error : "+ e.getMessage(), e);        	
        }
       return titulo;
    }

	/**
	 * @return the existenBolsaZonaVerano
	 */
	public boolean isExistenBolsaZonaVerano() {
		return existenBolsaZonaVerano;
	}

	/**
	 * @param existenBolsaZonaVerano the existenBolsaZonaVerano to set
	 */
	public void setExistenBolsaZonaVerano(boolean existenBolsaZonaVerano) {
		this.existenBolsaZonaVerano = existenBolsaZonaVerano;
	}

	/**
	 * @return the existenZonasVerano
	 */
	public boolean isExistenZonasVerano() {
		return existenZonasVerano;
	}

	/**
	 * @param existenZonasVerano the existenZonasVerano to set
	 */
	public void setExistenZonasVerano(boolean existenZonasVerano) {
		this.existenZonasVerano = existenZonasVerano;
	}	
	
    public void validarClaveZonaVerano(PhaseEvent phase){
    	
    	LOGGER.info("INIT VALIDAR CLAVE ZONA VERANO");
    	String claveStand  = JsfUtil.getRequestParameter("clave")==null?"":JsfUtil.getRequestParameter("clave");   
    	String codigoStand = JsfUtil.getRequestParameter("codigoStand")==null?"":JsfUtil.getRequestParameter("codigoStand"); 
    	try{    		
        	
    		if(vtasYMktgFidelizacionDelegate.validarClaveZonaVerano(claveStand,codigoStand)){
    			respuestaCanjearPuntosJson =JsonHelper.toJsonResponse("");    		  
    		}
    		
    	} catch (DAOException e) {
            LOGGER.error("DAOException error al validar la clave zona verano", e);
            respuestaCanjearPuntosJson = JsonHelper.toJsonServiceErrorMessage("noDisponible");
        } catch (ServiceException e) {
        	LOGGER.info("ServiceException error al validar la clave zona verano "
						+ e.getCodigoRespuesta() + " - " + e.getDescripcionRespuesta());  
        	respuestaCanjearPuntosJson = JsonHelper.toJsonServiceErrorMessage("zonaVerano", e.getCodigoRespuesta());        	
        } catch (Exception e) {
            LOGGER.error("Exception al validar la clave zona verano", e);
            respuestaCanjearPuntosJson = JsonHelper.toJsonServiceErrorMessage("noDisponible");
        }
    	
    }
    
   public void canjearPuntosZonaVerano(PhaseEvent phase){
    	
    	LOGGER.info("INIT CANJEAR PUNTOS ZONA VERANO");
    	String claveStand = JsfUtil.getRequestParameter("clave")==null?"":JsfUtil.getRequestParameter("clave");  
    	String codProducto = JsfUtil.getRequestParameter("codProducto")==null?"":JsfUtil.getRequestParameter("codProducto");  
    	String codigoStand = JsfUtil.getRequestParameter("codigoStand")==null?"":JsfUtil.getRequestParameter("codigoStand"); 
    	String puntos = JsfUtil.getRequestParameter("puntos")==null?"0":JsfUtil.getRequestParameter("puntos"); 
    	ResultadoConsultarPuntosBean infoPuntos;
    	int intPuntos =0;
    	
    	try{ 
    		
    		try{
    			intPuntos = Integer.parseInt(puntos);
        	}catch (NumberFormatException nfe) {
    			LOGGER.info("El valor en puntos enviado para el canje no es un entero");    			
    		}
    		
    		 //Obtenemos datos necesarios para consulta
            ProfileWrapper profile = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());
            String rut = ProfileWrapperHelper.getPropertyAsString(profile, "rutUsuario");
            String msisdn = ProfileWrapperHelper.getPropertyAsString(profile, "numeroPcs");
            String rutSinDV = Long.toString(RutBean.parseRut(rut).getNumero());         
            
	          //Llamado previo para validar que no pueda canjear si no tiene la cantidad de puntos necesario
	          infoPuntos = vtasYMktgFidelizacionDelegate.consultarInfoPuntosCliente(rutSinDV);
	          
	          LOGGER.info("CANJE DE ZONA VERANO - COD_PROD::"+codProducto);
	          LOGGER.info("CANJE DE ZONA VERANO - PUNTOS_PROD::"+puntos);
	          LOGGER.info("CANJE DE ZONA VERANO - COD_ZONA::"+codigoStand);
	          LOGGER.info("CANJE DE ZONA VERANO - SALDO ACTUAL::"+infoPuntos.getPuntos().getSaldoPuntos());	
	          
	          if(intPuntos <= infoPuntos.getPuntos().getSaldoPuntos()){
	        	  String respuesta="";
	        	      respuesta = vtasYMktgFidelizacionDelegate.canjearPuntosZonaVerano(rutSinDV,msisdn,codProducto,codigoStand,claveStand,puntos);
	       			  infoPuntos = vtasYMktgFidelizacionDelegate.consultarInfoPuntosCliente(rutSinDV);
	       			  Map<String, String> resp = new HashMap<String, String>();
	       		  	  resp.put("codRespuesta",respuesta);
	       		      resp.put("puntos",infoPuntos.getPuntos().getSaldoPuntos().toString());
	       			  respuestaCanjearPuntosJson =JsonHelper.toJsonResponse(resp);
	       		
	           }else{
	        	   respuestaCanjearPuntosJson = JsonHelper.toJsonServiceErrorMessage("zonaVerano", "limitePuntos");  
	           }
    		
    		
    	} catch (DAOException e) {
            LOGGER.error("DAOException al cajear puntos zona verano", e);
            respuestaCanjearPuntosJson = JsonHelper.toJsonServiceErrorMessage("noDisponible");
        } catch (ServiceException e) {
        	LOGGER.info("ServiceException al cajear puntos zona verano "
						+ e.getCodigoRespuesta() + " - " + e.getDescripcionRespuesta());  
        	respuestaCanjearPuntosJson = JsonHelper.toJsonServiceErrorMessage("zonaVerano", e.getCodigoRespuesta());        	
        } catch (Exception e) {
            LOGGER.error("Exception al cajear puntos zona verano", e);
            respuestaCanjearPuntosJson = JsonHelper.toJsonServiceErrorMessage("noDisponible");
        }
    	
    } 
   
   /**
    * Action method para obtener catalogo de bolsas y recargas para canjear por puntos
    * @param mercado
    */
    public void obtenerCatalogoZonaVerano(PhaseEvent phase){     
	   String mercado = "";
	   String codigosZona ="";
	   ResultadoConsultarPuntosBean infoPuntos = new ResultadoConsultarPuntosBean();
	   try {
          //Datos necesarios para consulta desde el perfiel del usuario
	       ProfileWrapper profile = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());  
	       PortletPreferences preferences = JSFPortletHelper.getPreferencesObject();
	       
	       String rut = ProfileWrapperHelper.getPropertyAsString(profile, "rutUsuario");            
	       String msisdn = ProfileWrapperHelper.getPropertyAsString(profile, "numeroPcs");	       
	       String subMercado = "";
	       int flagBam;
	   
		  // Se asegura que el mercado y el flagBam es el del usuario logueado
	       if(msisdn.equals(ProfileWrapperHelper.getPropertyAsString(profile, "numeroPcsSeleccionado"))){
	       	mercado = ProfileWrapperHelper.getPropertyAsString(profile, "mercado");
	       	subMercado = ProfileWrapperHelper.getPropertyAsString(profile, "subMercado");
	       	flagBam = ProfileWrapperHelper.getPropertyAsInt(profile, "flagBam");
	       }
	       else{
	       	PerfilUsuarioBean pub = cuentaDelegate.obtenerPerfilUsuario(msisdn);
	       	mercado = pub.getMercado();
	       	subMercado = pub.getUsuarioSeleccionado().getSubMercado();
	       	flagBam = pub.getFlagBam();
	       }
       
	   	     int canalRequest = (flagBam == 0) ? CANAL_WEB : CANAL_WEBBAM;
	         int mercadoRequest = (MiEntelBusinessHelper.isMercadoPrepago(mercado) && flagBam == 0) ? MERCADO_VOZPP 
	       					 : (MiEntelBusinessHelper.isMercadoCuentaControlada(mercado) && flagBam == 0) ? MERCADO_VOZCC 
	       					 : (MiEntelBusinessHelper.isMercadoSuscripcion(mercado) && flagBam == 0) ? MERCADO_VOZSS 
	       					 : (MiEntelBusinessHelper.isMercadoPrepago(mercado) && flagBam == 1) ? MERCADO_BAMPP        					 
	       					 : (MiEntelBusinessHelper.isMercadoSuscripcion(mercado) && flagBam == 1) ? MERCADO_BAMSS
	       					 : (MiEntelBusinessHelper.isMercadoCuentaControlada(mercado) && flagBam == 1) ? (subMercado.equals(SUB_BAMCCN)) ? MERCADO_BAMCCN : MERCADO_BAMCC
	       					 : null;
               
            LOGGER.info("Obtener catalogo de canje zona verano para canal: "+canalRequest+" y mercado: "+mercadoRequest);
            
            codigosZona = JSFPortletHelper.getPreference(preferences,  "listaCodigosZona", "");
       	
            listaBolsasZonaVerano = vtasYMktgFidelizacionDelegate.catalogoCanjeZonaVerano(canalRequest, mercadoRequest,codigosZona);
            
            //Invocamos el metodo de accion que consulta los puntos.
            String rutSinDV = Long.toString(RutBean.parseRut(rut).getNumero());
            infoPuntos = vtasYMktgFidelizacionDelegate.consultarInfoPuntosCliente(rutSinDV);
            detallePuntos = infoPuntos.getPuntos();
           
	        //valores usados para determinar en la vista si hay datos para mostrar           
	        existenBolsaZonaVerano = (bolsasZonaVerano != null && !bolsasZonaVerano.isEmpty()); 
           
           this.filtroZonaVerano(preferences,listaBolsasZonaVerano);//Metodo para cargar zona verano habilitadas
       	
       } catch (DAOException e) { 
           LOGGER.error("DAOException al obtener catalogo de bolsas Zona Verano para el mercado: " + mercado, e);
           JSFMessagesHelper.addServiceErrorMessage("consultaBolsasZonaVerano");
       } catch (ServiceException e) {
       	LOGGER.info("ServiceException en la obtencion de catalogo: "
   					+ e.getCodigoRespuesta() + " - " + e.getDescripcionRespuesta());            
           JSFMessagesHelper.addServiceErrorMessage(e.getCodigoRespuesta());
       } catch (Exception e) {
           LOGGER.error("Exception al obtener catalogo de bolsas Zona Verano para el mercado: " + mercado, e);
           JSFMessagesHelper.addServiceErrorMessage("consultaBolsasZonaVerano");
       }                

   }

	/**
	 * @return the bolsasCanjeBAM
	 */
	public Set<ItemProductoCanjeBean> getBolsasCanjeBAM() {
		return bolsasCanjeBAM;
	}
	
	/**
	 * @param bolsasCanjeBAM the bolsasCanjeBAM to set
	 */
	public void setBolsasCanjeBAM(Set<ItemProductoCanjeBean> bolsasCanjeBAM) {
		this.bolsasCanjeBAM = bolsasCanjeBAM;
	}
	
	/**
	 * @return the existenBolsasCanjeBAM
	 */
	public boolean isExistenBolsasCanjeBAM() {
		return existenBolsasCanjeBAM;
	}
	
	/**
	 * @param existenBolsasCanjeBAM the existenBolsasCanjeBAM to set
	 */
	public void setExistenBolsasCanjeBAM(boolean existenBolsasCanjeBAM) {
		this.existenBolsasCanjeBAM = existenBolsasCanjeBAM;
	}
	   
	public String getPageLabelActual() {
		return JSFPortletHelper.getCurrentPageLabel();
	}   
	
    
}
