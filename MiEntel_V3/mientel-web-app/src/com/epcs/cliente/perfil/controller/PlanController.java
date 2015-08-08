/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.cliente.perfil.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.PhaseEvent;
import javax.faces.model.SelectItem;
import javax.portlet.PortletRequest;

import org.apache.log4j.Logger;

import com.bea.content.Node;
import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.bean.GrupoClienteBean;
import com.epcs.bean.GrupoPlanBean;
import com.epcs.bean.HistorialComunik2Bean;
import com.epcs.bean.NumeroFrecuenteBean;
import com.epcs.bean.PlanBean;
import com.epcs.bean.PlanDisponibleBean;
import com.epcs.bean.PlanPPBean;
import com.epcs.bean.RecargaHistoricoBean;
import com.epcs.bean.ResumenPlan;
import com.epcs.bean.ResumenPlanCategoria;
import com.epcs.bean.RutBean;
import com.epcs.bean.SolicitudComunik2Bean;
import com.epcs.bean.TipoPlanBean;
import com.epcs.bean.TransaccionGTMBean;
import com.epcs.bean.VelocidadesPlanBean;
import com.epcs.bean.ZonaPerfilBean;
import com.epcs.billing.recarga.delegate.RecargaDelegate;
import com.epcs.cliente.perfil.dao.CuentaDAO;
import com.epcs.cliente.perfil.delegate.EquipoDelegate;
import com.epcs.cliente.perfil.delegate.PlanDelegate;
import com.epcs.cliente.perfil.util.PlanHelper;
import com.epcs.recursoti.configuracion.DateHelper;
import com.epcs.recursoti.configuracion.JsonHelper;
import com.epcs.recursoti.configuracion.MiEntelBusinessHelper;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.ParametrosHelper;
import com.epcs.recursoti.configuracion.Utils;
import com.epcs.recursoti.configuracion.jsf.utils.JSFMessagesHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JSFPortletHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JsfUtil;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;
import com.epcs.vtasymktg.fidelizacion.delegate.BeneficiosDelegate;
import com.epcs.vtasymktg.fidelizacion.delegate.VtasYMktgFidelizacionDelegate;
import com.epcs.bean.ResultadoConsultarPuntosBean;
import com.epcs.bean.PuntosBean;

/**
 * @author jmanzur (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class PlanController implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private static final Logger LOGGER = Logger.getLogger(PlanController.class);

    private PlanDelegate planDelegate;
    
    //BORRAR
    
    private EquipoDelegate equipoDelegate;
    
    
    public EquipoDelegate getEquipoDelegate() {
		return equipoDelegate;
	}

	public void setEquipoDelegate(EquipoDelegate equipoDelegate) {
		this.equipoDelegate = equipoDelegate;
	}
	// FIN BORRAR
    

	private ResumenPlan resumenPlan;

    private ResumenPlanCategoria resumenPlanCategoria;
    
    private String resumenPlanJson;
    
    private String resumenPlanCategoriaJson;
    
    private PlanPPBean planActualPP;
    
    private PlanBean planActual;
    
    private String planCCNuevo;
    private PlanDisponibleBean planDisponible;
    
    private List<GrupoPlanBean> grupoPlanesDisponibles;
    
    private boolean existenPlanesDisponibles = false;
    
    private String respuestaJson;
    
    private int cantidadNumeroFrecuentes;
    
    private static final int AGREGAR_NUMEROFRECUENTE = 1;
    
    private static final int MODIFICAR_NUMEROFRECUENTE = 3;
    
    private static final int ELIMINAR_NUMEROFRECUENTE = 2;
    
    private String descripcionPlan;
    
    private List<SelectItem> prefijosRedFija;
          
    private String multimedia="";
    
    private String planOrigen="";
 
	private String planesFullRed="";
    private String planesTarifaUnicaSinSMS="";
    private String planesTarifaUnicaConSMS="";
    private String planesCuentaControladaTarifaRed="";
    private String planesCuentaControladaTarifaPlana="";
    private String planesMultimediaRed="";
    private String planesMultimediaCuentaControlada="";
    private String planesMultimediaTodoDestino="";
    private String planesBlackberryMultimediaRed="";
    private String planesBlackberryMultimediaTodoDestino="";
    private String planesBlackberryMultimediaCuentaControlada="";
    private String planesMultimediaRedExcedidoTodoDestino="";
    private String planesMultimediaRedExcedido="";
    
	 /*NBuevoRodrigo*/
	 private String cargarCargoFijo = "0";
    /*Fin nuevo Rodrigo*/
	
    //SC 31-07-2014
    private String categoriaCliente = "";
    
    private static final String CONTENT_PLANES_FULL_RED = MiEntelProperties.getProperty("parametros.cambioplan.planesFullRed.idContenido");
    private static final String CONTENT_PLANES_TARIFA_UNICA_SIN_SMS = MiEntelProperties.getProperty("parametros.cambioplan.planesTarifaUnicaSinSMS.idContenido");
    private static final String CONTENT_PLANES_TARIFA_UNICA_CON_SMS = MiEntelProperties.getProperty("parametros.cambioplan.planesTarifaUnicaConSMS.idContenido");
    private static final String CONTENT_PLANES_CC_TARIFA_RED = MiEntelProperties.getProperty("parametros.cambioplan.planesCuentaControladaTarifaRed.idContenido");
    private static final String CONTENT_PLANES_CC_TARIFA_PLANA = MiEntelProperties.getProperty("parametros.cambioplan.planesCuentaControladaTarifaPlana.idContenido");
    private static final String CONTENT_PLANES_MM_CC = MiEntelProperties.getProperty("parametros.cambioplan.planesMultimediaCuentaControlada.idContenido");
    private static final String CONTENT_PLANES_MM_RED = MiEntelProperties.getProperty("parametros.cambioplan.planesMultimediaRed.idContenido");
    private static final String CONTENT_PLANES_MM_TODO_DESTINO = MiEntelProperties.getProperty("parametros.cambioplan.planesMultimediaTodoDestino.idContenido");
    private static final String CONTENT_PLANES_BBERRY_MM_CC = MiEntelProperties.getProperty("parametros.cambioplan.planesBlackberryMultimediaCuentaControlada.idContenido");
    private static final String CONTENT_PLANES_BBERRY_MM_RED = MiEntelProperties.getProperty("parametros.cambioplan.planesBlackberryMultimediaRed.idContenido");
    private static final String CONTENT_PLANES_BBERRY_TODO_DESTINO = MiEntelProperties.getProperty("parametros.cambioplan.planesBlackberryMultimediaTodoDestino.idContenido");
    private static final String CONTENT_PLANES_MM_RED_EXCEDIDO = MiEntelProperties.getProperty("parametros.cambioplan.planesMultimediaRedExcedido.idContenido");
    private static final String CONTENT_PLANES_MM_EXCEDIDO_TODO_DESTINO = MiEntelProperties.getProperty("parametros.cambioplan.planesMultimediaRedExcedidoTodoDestino.idContenido");
   //Diferenciacion usuarios MP1
    private static final String CATEGORIA_CLIENTE_PP = MiEntelProperties.getProperty("parametros.categoriausuario.diferenciacioncanalespp.altovalor");

    private boolean validoTarifasPlus=false;
    
    public boolean isValidoTarifasPlus(){
    	return validoTarifasPlus;
    }
    
         
   //
    private static final int MANANA = 1;
    private static final int PROX_MES = 2;

	private List<HistorialComunik2Bean> listcomunik2Bean;
    
    private boolean isComunik2 = false;
    
    private SolicitudComunik2Bean solicitudComunik2Bean;
    
    private String numeroComunik2 ="";
    
    private String mensajeVelocidadTransmisionCM;
    
    private String mensajePlanesMMExcedidosCM;
    
    private boolean cargaNrosFrecuenteAjax = false;
	
    // Validacion prestaluka
    private BeneficiosDelegate beneficiosDelegate;
    private ZonaPerfilBean zonaPerfilBean;
    private static final String CODSBAD_PRESTALUKA = MiEntelProperties.getProperty("zonaEntel.prestaLukaService.codsbad");
    private String mensajeErrorPrestaLuka;
    private boolean validoPrestaLuka = true;
    
    private String fechaActualFormat;
    
    
    public boolean isValidoPrestaLuka() {
		return validoPrestaLuka;
	}

	public String getMensajeErrorPrestaLuka() {
		return mensajeErrorPrestaLuka;
	}

	/**
     * @return the planDelegate
     */
    public PlanDelegate getPlanDelegate() {
        return planDelegate;
    }

    /**
     * Metodo para la injection del delegate
     * @param planDelegate
     */
    public void setPlanDelegate(PlanDelegate planDelegate) {
        this.planDelegate = planDelegate;
    }    
    
    public BeneficiosDelegate getBeneficiosDelegate() {
		return beneficiosDelegate;
	}

	public void setBeneficiosDelegate(BeneficiosDelegate beneficiosDelegate) {
		this.beneficiosDelegate = beneficiosDelegate;
	}

	/**
     * 
     * @param event
     */
    public void init(PhaseEvent event){
    
        String numeroPcsSeleccionado = "";
        
        try{
            ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());
            String mercado = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"mercado");
            numeroPcsSeleccionado = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"numeroPcsSeleccionado");
            String rutSeleccionado = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"rutUsuarioSeleccionado");
            
            LOGGER.info("mercado: "+mercado);
            
            if(MiEntelBusinessHelper.isMercadoCuentaControlada(mercado)){
               String atributoAA = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"aaa");
              this.resumenPlan = this.planDelegate.getPlanResumenCC(numeroPcsSeleccionado,
                    atributoAA);
            }else if(MiEntelBusinessHelper.isMercadoPrepago(mercado)){
              this.resumenPlan = this.planDelegate.getPlanResumenPP(numeroPcsSeleccionado);

              if(this.resumenPlan.getNombrePlan().isEmpty()){
            	 this.resumenPlan.setNombrePlan(MiEntelProperties.getProperty("tarifa.nombreTarifa.noDisponible"));
              }

              
              this.resumenPlan.setSaldoReservadoFormated(Utils.formatMoneyPuntos((this.resumenPlan.getSaldoReservado())));
              if(this.resumenPlan.getFechaExpiracionSaldoReservado() != null){
            	  this.resumenPlan.setFechaExpiracionSaldoReservadoFormated(
            			  		   DateHelper.format(this.resumenPlan.getFechaExpiracionSaldoReservado(), DateHelper.FORMAT_ddMMyyyy_SLASH));
              }                     
            }
            
            if(MiEntelBusinessHelper.isMercadoPrepago(mercado)){
             	RutBean rutBean = new RutBean(rutSeleccionado);
                zonaPerfilBean = beneficiosDelegate.getZonaPerfil(numeroPcsSeleccionado, rutBean.getNumero()+"0"+rutBean.getDigito());                
                 
                String[] codsBad = CODSBAD_PRESTALUKA.split(",");
         		for(String cod : codsBad){
         			if(zonaPerfilBean.getStatusRespuesta().equals(cod)){
         				validoPrestaLuka = false;
         				break;
         			}
         		}
            }
            
            this.resumenPlan.setPrestaLuka(!validoPrestaLuka);
            //this.resumenPlan.setFechaPrestaLuka(null);
            
            //formatear
            this.resumenPlan.setSaldoFormated(Utils.formatMoneyPuntos((this.resumenPlan.getSaldo())));
            this.resumenPlan.setFechaExpiracionFormated(DateHelper.format(this.resumenPlan.getFechaExpiracion(), DateHelper.FORMAT_ddMMyyyy_SLASH));
            
            
            setResumenPlanJson(JsonHelper.toJsonResponse(this.resumenPlan));
            
       } catch (DAOException e) {
    	   LOGGER.error("DAOException al obtener datos del plan", e);
           resumenPlanJson = JsonHelper.toJsonServiceErrorMessage("noDisponible");
           
       } catch (ServiceException e) {
           LOGGER.info("ServiceException msisdn: "+numeroPcsSeleccionado+" - codigo: " + e.getCodigoRespuesta()
                   + " - " + e.getDescripcionRespuesta());
           resumenPlanJson = JsonHelper.toJsonServiceErrorMessage("gestionDePerfiles", e.getCodigoRespuesta(), new String[]{numeroPcsSeleccionado});
          
       } catch (Exception e) {
           LOGGER.error("Exception inesperado al obtener datos del plan", e);
           resumenPlanJson = JsonHelper.toJsonServiceErrorMessage("inesperado");
       }
    }
    
    /**
     * 
     * @param event
     */
    public void initCategoria(PhaseEvent event){
    	
        String numeroPcsSeleccionado = "";
        String rut = "";
        String rutSinDV = "";
        
        try{
        	ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());
           
        	numeroPcsSeleccionado = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"numeroPcsSeleccionado");
            
            //SC 31-07-2014 Luis
            //Obtenemos datos necesarios para la consulta de la categoría
            rut = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "rutUsuario");
            
            //Eliminamos el Digito verificador
            rutSinDV = Long.toString(RutBean.parseRut(rut).getNumero());

            //SC FIN 31-07-2014 Luis
            
            
            this.resumenPlanCategoria=new ResumenPlanCategoria();
            
            //SC 31-07-2014 Luis I2B | Optimización de código depreciado para setear las variables semestrales

            Calendar now = Calendar.getInstance();
            int year = now.get(Calendar.YEAR);
            int month = now.get(Calendar.MONTH);
            String semestre= "";
            String fechaInicial= "";
            String fechaFinal= ""; 
 
            if (month<6){
            	semestre="Enero - Junio";
            	fechaInicial=year+"0101000000";
            	fechaFinal=year+"0630235959";
            	
            }else{
            	semestre="Julio - Diciembre";
            	fechaInicial=year+"0701000000";
            	fechaFinal=year+"1231235959";
            }

            //Consulta ws puntos para obtener la categoría del cliente
            consultarCategoriaCliente(rutSinDV, numeroPcsSeleccionado);
            
            //FIN SC 31-07-2014 Luis I2B || Obtengo la Categoria del Cliente

            RecargaDelegate recargaDelegate= new RecargaDelegate();
            RecargaHistoricoBean recargaHistoricoBean = recargaDelegate.getHistoricoRecargasRango(numeroPcsSeleccionado,fechaInicial,fechaFinal); 
            Double porcentaje=recargaHistoricoBean.getMontoTotal()/500;            
            if (porcentaje>100) porcentaje=100.0;
            	
            this.resumenPlanCategoria.setNombreCategoria(getCategoriaCliente());
            this.resumenPlanCategoria.setMontoAcutal(Utils.formatMoneyPuntos(recargaHistoricoBean.getMontoTotal()));
            this.resumenPlanCategoria.setPorcentaje(porcentaje);            
            this.resumenPlanCategoria.setSemestre(semestre);
            
            setResumenPlanCategoriaJson(JsonHelper.toJsonResponse(this.resumenPlanCategoria));
            
       } catch (DAOException e) {
    	   LOGGER.error("DAOException al obtener datos del plan", e);
           resumenPlanCategoriaJson = JsonHelper.toJsonServiceErrorMessage("noDisponible");
           
       } catch (ServiceException e) {
           LOGGER.info("ServiceException msisdn: "+numeroPcsSeleccionado+" - codigo: " + e.getCodigoRespuesta()
                   + " - " + e.getDescripcionRespuesta());
           resumenPlanCategoriaJson = JsonHelper.toJsonServiceErrorMessage("gestionDePerfiles", e.getCodigoRespuesta(), new String[]{numeroPcsSeleccionado});
          
       } catch (Exception e) {
           LOGGER.error("Exception inesperado al obtener datos del plan", e);
           resumenPlanCategoriaJson = JsonHelper.toJsonServiceErrorMessage("inesperado");
       }
    }

    
    /**
     * Obtener Datos del Plan de Un Usuario CC
     * 
     * @return ResumenPlan
     * @throws ServiceException
     * @throws DAOException
     */
    public ResumenPlan getPlanResumen() throws DAOException, ServiceException {
        return this.resumenPlan;
    }
    
     /**
     * @return the resumenPlanJson
     */
    public String getResumenPlanJson() {
        return resumenPlanJson;
    }

    /**
     * @param resumenPlanJson the resumenPlanJson to set
     */
    public void setResumenPlanJson(String resumenPlanJson) {
        this.resumenPlanJson = resumenPlanJson;
    }
    
    /**
     * @return the resumenPlanJson
     */
    public String getResumenPlanCategoriaJson() {
        return resumenPlanCategoriaJson;
    }

    /**
     * @param resumenPlanJson the resumenPlanJson to set
     */
    public void setResumenPlanCategoriaJson(String resumenPlanJson) {
        this.resumenPlanCategoriaJson = resumenPlanJson;
    }
    
    /**
     * Metodo que se encarga de obtener y retornar el valor de la preferencia del portlet en solicitud
     * dependiendo del mercado como clave asociada al valor.
     * @return
     */
    public String getPageLabelRecargaEnLinea(){
    	try{
    	  ProfileWrapper profileWrapper = ProfileWrapperHelper
            .getProfile(JSFPortletHelper.getRequest());
    		
    	  return JSFPortletHelper.getPreference(JSFPortletHelper.getPreferencesObject(), ProfileWrapperHelper.getPropertyAsString(
                 profileWrapper, "mercado"), null);
		 
        }catch(Exception e){
        	LOGGER.error("No se ha podido obtener el pageLabel "+ e.getMessage(),e);
        	return "";
        }
    }
    
    
    /**
     * Metodo que obtiene la informacion correspondiente al Plan actual del usuario
     * @param event
     */
    public void obtenerPlanActual(PhaseEvent event){
    	
    	String numeroPcsSeleccionado = "";
    	String mercado ="";
    	String atributoAA="";
        
    	TipoPlanBean tipo = new TipoPlanBean();
    	
        try {        	
        	 ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());
        	
        	 mercado = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"mercado");
             numeroPcsSeleccionado = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"numeroPcsSeleccionado");
             atributoAA = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"aaa");
             /*Nuevo ROdrigo Diaz */
             String nroCuenta = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"numeroCuenta");
             /*FIN Nuevo*/
             if(MiEntelBusinessHelper.isMercadoSuscripcion(mercado) || MiEntelBusinessHelper.isMercadoCuentaControlada(mercado)){
            	 // PLAN ACTUAL SS Y CC
            	 planActual = planDelegate.getPlanActualSSCC(numeroPcsSeleccionado, mercado, atributoAA);
            	 System.out.println(planActual.getTotalMinutos());
            	 descripcionPlan = PlanHelper.obtenerBreveDescripcionPlan(planActual);
            	 
            	 // Cambio de descripcion para familias de planes Cuenta Controlada Tarifa Plana y Cuenta Controlada Tarifa Red
            	 if ( planActual.getTipoPlan().equals(MiEntelProperties.getProperty("parametros.tipoplan.ccplana.id")) 
            			 && planActual.getTipoMercado().equals(MiEntelProperties.getProperty("parametros.tipoplan.ccplana.mercado")) ) {
            		 planCCNuevo = "0";
            		 descripcionPlan = MiEntelProperties.getProperty("parametros.tipoplan.ccplana.descPlanActual");
            		 //Nuevo Rod
            		 if(ParametrosHelper.getExisteParametro("plan.validacion",planActual.getCodbscs2())){
            			 planCCNuevo = planActual.getCodbscs2();
            		 }
            		 System.out.println("planCCNuevo "+planCCNuevo);
            		 
                 }else if( planActual.getTipoPlan().equals(MiEntelProperties.getProperty("parametros.tipoplan.ccred.id")) 
                    	 && planActual.getTipoMercado().equals(MiEntelProperties.getProperty("parametros.tipoplan.ccred.mercado")) ){
                	 descripcionPlan = MiEntelProperties.getProperty("parametros.tipoplan.ccred.descPlanActual");
                 }

            	 if(MiEntelBusinessHelper.isMercadoCuentaControlada(mercado)){
            		 resumenPlan = this.planDelegate.getPlanResumenCC(numeroPcsSeleccionado,atributoAA);
            		 resumenPlan.setMercadoCuentaControlada(true);
            	 }

            	 
             }else if(MiEntelBusinessHelper.isMercadoPrepago(mercado)){
	              // RESUMEN PLAN PP
	              resumenPlan = planDelegate.getPlanResumenPP(numeroPcsSeleccionado);   
	              planActualPP = planDelegate.getPlanActualPP(numeroPcsSeleccionado);
             }
             
             /*Nuevo Rodrigo Diaz */
         	String rutTitular = null;
             String pageLabel = getPageLabelActual();
             
             System.out.println("PageLabel "+pageLabel);
             if(mercado.equals("PP")){
             	 rutTitular = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"rutUsuario");
             }else{
             	rutTitular = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"rutTitular");
             }
             
             rutTitular = rutTitular == null ? ProfileWrapperHelper.getPropertyAsString(profileWrapper,"rutUsuarioSeleccionado") : rutTitular;
             
             RutBean rt = new RutBean(rutTitular);
             String rutSinDV = rutTitular != null ? rutTitular.substring(0, rutTitular.length()-1) : "";
             String DV = String.valueOf(rt.getDigito());
         	String codigoGrupo = null;
         	String rutFormated = rutSinDV.concat(DV.toUpperCase());
         	 try {
         		 //EquipoDelegate delegateEquipo = new EquipoDelegate();
         		 System.out.println("rutFormated"+rutFormated+ " nroCuenta"+nroCuenta);
         	//GrupoClienteBean grupoCliente = delegateEquipo
 			//.obtenerGrupoCliente(rutFormated, nroCuenta);
         		GrupoClienteBean grupoCliente = this.equipoDelegate
     			.obtenerGrupoCliente(rutFormated, nroCuenta);
         	System.out.println("Grupo"+grupoCliente.getCodigoGrupo());	
         	//MODIFICARRRRRRRRRRRRRRRRRR
         	//codigoGrupo = "13";
         	codigoGrupo = grupoCliente.getCodigoGrupo();
         	System.out.println(codigoGrupo);	
         	if (ParametrosHelper.getExisteParametro("equipo.grupoCliente.empresa",codigoGrupo)
         			&& ParametrosHelper.getExisteParametro("grupoCliente.excentoTrafico.empresa",codigoGrupo) && pageLabel != null
         			&& pageLabel.contains("trafico")) {
         		System.out.println("Existe");	
         		//resumenTrafico.setMostrarSeccionExcedidoDash("1");
         		cargarCargoFijo = "0";
 			}else if(ParametrosHelper.getExisteParametro("equipo.grupoCliente.empresa",codigoGrupo) && pageLabel != null
         			&& pageLabel.contains("trafico")){
 				System.out.println("No Existe");
 				//resumenTrafico.setMostrarSeccionExcedidoDash("0");
 				cargarCargoFijo = "1";
 			}else{
 				cargarCargoFijo = "0";
 			}
         	
         	 } catch (Exception e) {
 					LOGGER.error("Error al verificar el grupo del cliente.", e);
 			 }
         	//tipoplan
 	/*Fin Rodrigo Diaz */
             
       } catch (DAOException e) {
    	   LOGGER.error("Exception inesperado al obtener datos del plan",  e);
    	   JSFMessagesHelper.addServiceErrorMessage("planActual");
           
       } catch (ServiceException e) {
           LOGGER.info("ServiceException msisdn: "+numeroPcsSeleccionado+" - codigo: " + e.getCodigoRespuesta()
                   + " - " + e.getDescripcionRespuesta());
           JSFMessagesHelper.addErrorCodeMessage("gestionDePerfiles", 
        		   e.getCodigoRespuesta(), new String[]{numeroPcsSeleccionado});
           
       } catch (Exception e) {
           LOGGER.error("Exception inesperado al obtener datos del plan", e);
           JSFMessagesHelper.addServiceErrorMessage("planActual");
       }
    }
    
    
    /**
     * Metodo que obtiene la informacion correspondiente al Plan actual del usuario
     * @param event
     */
    public void obtenerNumerosFrecuentes(PhaseEvent event){
    	
    	String numeroPcsSeleccionado = "";
        
        try {
        	ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());
        	
            numeroPcsSeleccionado = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"numeroPcsSeleccionado");
            
            String llamadoAjax = JsfUtil.getRequestParameter("llamadoAjax");

            planActualPP = planDelegate.getPlanActualPP(numeroPcsSeleccionado);

            prefijosRedFija = obtenerPrefijosRedFija();
            
            obtenerCantidadNumeroFrecuentes();
            
            
            isComunik2 = ParametrosHelper.getExisteParametro("comunik2.planescomunik2",String.valueOf(planActualPP.getIdTarifa()));
           
            setRespuestaJson(JsonHelper.toJsonResponse(""));
            
            if(Utils.isNotEmptyString(llamadoAjax))
            	cargaNrosFrecuenteAjax = true;
            
       } catch (DAOException e) {
    	   LOGGER.error("DAOException al obtener Numeros Frecuentes ", e);
    	   JSFMessagesHelper.addServiceErrorMessage("numeroFrecuente");
    	   respuestaJson = JsonHelper.toJsonServiceErrorMessage("numeroFrecuente");
           
       } catch (ServiceException e) {
           LOGGER.info("ServiceException msisdn: "+numeroPcsSeleccionado+" - codigo: " + e.getCodigoRespuesta()
                   + " - " + e.getDescripcionRespuesta());
           JSFMessagesHelper.addErrorCodeMessage("gestionDePerfiles", 
        		   e.getCodigoRespuesta(), new String[]{numeroPcsSeleccionado});
           respuestaJson = JsonHelper.toJsonServiceErrorMessage("gestionDePerfiles", e.getCodigoRespuesta());
           
       } catch (Exception e) {
           LOGGER.error("Exception inesperado al obtener Numeros Frecuentes",  e);
           JSFMessagesHelper.addServiceErrorMessage("numeroFrecuente");
           respuestaJson = JsonHelper.toJsonServiceErrorMessage("numeroFrecuente");
       }
    }

    /**
     * Metodo que obtiene la informacion correspondiente los planes disponibles 
     * a los cuales puede cambiarse SS - CC.
     * @param event
     */
    public void obtenerPlanesDisponibles(PhaseEvent event){
    	
    	String numeroPcsSeleccionado = "";
    	String mercado ="";
    	String atributoAA="";
    	String rutSeleccionado="";
    	
        try {
        	 ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());
        	 mercado = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"mercado");
             numeroPcsSeleccionado = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"numeroPcsSeleccionado");
             atributoAA = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"aaa");
             
             String flagBam = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"flagBam");
             rutSeleccionado = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"rutUsuarioSeleccionado");
             
             if(MiEntelBusinessHelper.isMercadoSuscripcion(mercado) || MiEntelBusinessHelper.isMercadoCuentaControlada(mercado)){
            	
            	 planActual = planDelegate.getPlanActualSSCC(numeroPcsSeleccionado, mercado, atributoAA); 
	             planDisponible = planDelegate.getPlanesDisponibles(numeroPcsSeleccionado);
	             existenPlanesDisponibles = (planDisponible != null && !planDisponible.getPlanesDisponibles().isEmpty());
	             
	             
	             this.obtenerFiltroCambioPlanOrigen(planActual.getTipoPlan());
	            
	          // Agrupar Planes por tipo y Filtro para que no me agregue a la lista de planes, mi plan actual.
	             grupoPlanesDisponibles = PlanHelper.buildGruposPlan(this.planDisponible
	            		 .getPlanesDisponibles(), planActual.getCodbscs2());
	             
	            
             }else if(MiEntelBusinessHelper.isMercadoPrepago(mercado)){
            	 
	              resumenPlan = planDelegate.getPlanResumenPP(numeroPcsSeleccionado);
	              planDisponible = planDelegate.getPlanesDisponiblesPP(numeroPcsSeleccionado);
	            
	              existenPlanesDisponibles = (planDisponible != null && !planDisponible.getPlanesDisponiblesPP().isEmpty());
	              
	              validoTarifasPlus = getTarifasDisponiblesPPAltoValor(planDisponible);
             } 
             if(existenPlanesDisponibles && grupoPlanesDisponibles != null && !grupoPlanesDisponibles.isEmpty()){
            	 Iterator<GrupoPlanBean> iterGrupoPlanes = grupoPlanesDisponibles.iterator();
            	 
            	 Node texto = null;
            	 
                 while (iterGrupoPlanes.hasNext()) {
					GrupoPlanBean grupoPlanes = iterGrupoPlanes.next();
					
					if (grupoPlanes.getTipoPlan() == PlanHelper.getPlanRedSs()){
						texto = JSFPortletHelper.getContentNode("idContenido", CONTENT_PLANES_FULL_RED);
		     		   	planesFullRed = texto.getProperty("html").getValue().getStringValue();
		     		   	planesFullRed = replaceFechaNuevoPlanCM(planesFullRed, MANANA);
		     		   	
		     		   	texto = JSFPortletHelper.getContentNode("idContenido", CONTENT_PLANES_BBERRY_MM_RED);
		    		    planesBlackberryMultimediaRed = texto.getProperty("html").getValue().getStringValue();
		    		    planesBlackberryMultimediaRed = replaceFechaNuevoPlanCM(planesBlackberryMultimediaRed, MANANA);
		     		   	
					} else if(grupoPlanes.getTipoPlan() == PlanHelper.getPlanTarifaUnica()){
						Iterator<PlanBean> iterPlanesTU = grupoPlanes.getPlanesDisponibles().iterator();
						boolean tuSMS = false;	//indica si hay planes tarifa unica con SMS
						boolean bbMMTD = false;	//indica si hay planes blackberry multimedia todo destino
						while (iterPlanesTU.hasNext()){
							PlanBean planTU = iterPlanesTU.next();
							if(PlanHelper.isPlanTarifaUnicaConSMS(planTU.getCodigoNombrePlan())){
								tuSMS = true;
								planTU.setTarifaUnicaConSMS(tuSMS);
							} else if(PlanHelper.isPlanBberryMMTodoDestino(planTU.getCodigoNombrePlan())){
								bbMMTD = true;
								planTU.setBberryMMTodoDestino(bbMMTD);
							}
						}
						
						texto = JSFPortletHelper.getContentNode("idContenido", CONTENT_PLANES_TARIFA_UNICA_SIN_SMS);
		     		    planesTarifaUnicaSinSMS = texto.getProperty("html").getValue().getStringValue();
		     		    planesTarifaUnicaSinSMS = replaceFechaNuevoPlanCM(planesTarifaUnicaSinSMS, MANANA);
						
		     		    if(tuSMS){
							texto = JSFPortletHelper.getContentNode("idContenido", CONTENT_PLANES_TARIFA_UNICA_CON_SMS);
			     		    planesTarifaUnicaConSMS = texto.getProperty("html").getValue().getStringValue();
			     		    planesTarifaUnicaConSMS = replaceFechaNuevoPlanCM(planesTarifaUnicaConSMS, MANANA);
						}
		     		    
		     		    if(bbMMTD){
		     		    	/* Estos planes tiene tabla de velocidades para descargar y subir archivos */
			     		    texto = JSFPortletHelper.getContentNode("idContenido", CONTENT_PLANES_BBERRY_TODO_DESTINO);
			    		    planesBlackberryMultimediaTodoDestino = texto.getProperty("html").getValue().getStringValue();
			    		    planesBlackberryMultimediaTodoDestino = replaceFechaNuevoPlanCM(planesBlackberryMultimediaTodoDestino, MANANA);
			    		    
			    		    iterPlanesTU = grupoPlanes.getPlanesDisponibles().iterator();
			    		    List<VelocidadesPlanBean> tablaVelocidades = new ArrayList<VelocidadesPlanBean>();
			    		    while(iterPlanesTU.hasNext()){
			    		    	PlanBean plan = iterPlanesTU.next();
			    		    	if(plan.isBberryMMTodoDestino()){
			    		    		VelocidadesPlanBean vel = new VelocidadesPlanBean();			    		    		
			    		    		try{			    		    			
			    		    			
				    		    		String [] velocidades = MiEntelProperties
				    		    							.getProperty("parametros.plan.BlackberryMultimedia."+plan.getCodigoNombrePlan()).split("[|]");
				    		    		vel.setNombrePlan(plan.getNombrePlan());
				    		    		vel.setVelMaxDescarga(velocidades[0]);
				    		    		vel.setVelMaxSubida(velocidades[1]);
				    		    		vel.setVelPromNacDescarga(velocidades[2]);
				    		    		vel.setVelPromNacSubida(velocidades[3]);
				    		    		vel.setVelPromInterDescarga(velocidades[4]);
				    		    		vel.setVelPromInterSubida(velocidades[5]);
				    		    		vel.setCodbscs2(plan.getCodbscs2());
				    		    		tablaVelocidades.add(vel);			    		    		
			    		    			
			    		    		}catch (Exception e) {
			    		    	    	   LOGGER.error("No se encontro el informacion para el parametro : parametros.plan.BlackberryMultimedia."+plan.getCodigoNombrePlan(),e);
			    		    		}
			    		    	}
			    		    }
			    		    grupoPlanes.setTablaVelocidades(tablaVelocidades);
		     		    }
		     		    
					} else if(grupoPlanes.getTipoPlan() == PlanHelper.getPlanRedCc()){
						texto = JSFPortletHelper.getContentNode("idContenido", CONTENT_PLANES_CC_TARIFA_RED); 
		     		    planesCuentaControladaTarifaRed = texto.getProperty("html").getValue().getStringValue();
		     		    planesCuentaControladaTarifaRed = replaceFechaNuevoPlanCM(planesCuentaControladaTarifaRed, PROX_MES);
		     		    
					} else if(grupoPlanes.getTipoPlan() == PlanHelper.getPlanTarifaPlanaCc()){
						texto = JSFPortletHelper.getContentNode("idContenido", CONTENT_PLANES_CC_TARIFA_PLANA); 
		    		    planesCuentaControladaTarifaPlana = texto.getProperty("html").getValue().getStringValue();
		    		    planesCuentaControladaTarifaPlana = replaceFechaNuevoPlanCM(planesCuentaControladaTarifaPlana, PROX_MES);
		    		    
					} else if(grupoPlanes.getTipoPlan() == PlanHelper.getPlanMultimediaCc()){						
		    		    
		    		    Iterator<PlanBean> iterPlanesMMCC = grupoPlanes.getPlanesDisponibles().iterator();
						boolean mmCC = false;	//indica si hay planes multimedia cuenta controlada
						boolean bbMMCC = false;	//indica si hay planes Blackberry multimedia cuenta controlada
						while (iterPlanesMMCC.hasNext()){
							PlanBean planMMCC = iterPlanesMMCC.next();
							if(PlanHelper.isPlanMMCuentaControlada(planMMCC.getCodigoNombrePlan())){
								mmCC = true;
								planMMCC.setMMediaCControlada(mmCC);
							}else if(PlanHelper.isPlanBBMMCuentaControlada(planMMCC.getCodigoNombrePlan())){
								bbMMCC = true;
								planMMCC.setBberryMMediaCControlada(bbMMCC);
							}
						}
						
						if(mmCC){
						   /* Estos planes tiene tabla de velocidades para descargar y subir archivos */
							texto = JSFPortletHelper.getContentNode("idContenido", CONTENT_PLANES_MM_CC);
			    		    planesMultimediaCuentaControlada = texto.getProperty("html").getValue().getStringValue();
			    		    planesMultimediaCuentaControlada = replaceFechaNuevoPlanCM(planesMultimediaCuentaControlada, PROX_MES);
			    		    
			    		    iterPlanesMMCC = grupoPlanes.getPlanesDisponibles().iterator();
			    		    List<VelocidadesPlanBean> tablaVelocidades = new ArrayList<VelocidadesPlanBean>();
			    		    while(iterPlanesMMCC.hasNext()){
			    		    	PlanBean plan = iterPlanesMMCC.next();
			    		    	if(plan.isMMediaCControlada()){
			    		    		VelocidadesPlanBean vel = new VelocidadesPlanBean();
					    		       try{	
					    		    		String [] velocidades = MiEntelProperties
					    		    							.getProperty("parametros.plan.MultimediaCtaCda."+plan.getCodigoNombrePlan()).split("[|]");
					    		    		vel.setNombrePlan(plan.getNombrePlan());
					    		    		vel.setVelMaxDescarga(velocidades[0]);
					    		    		vel.setVelMaxSubida(velocidades[1]);
					    		    		vel.setVelPromNacDescarga(velocidades[2]);
					    		    		vel.setVelPromNacSubida(velocidades[3]);
					    		    		vel.setVelPromInterDescarga(velocidades[4]);
					    		    		vel.setVelPromInterSubida(velocidades[5]);
					    		    		vel.setCodbscs2(plan.getCodbscs2());
					    		    		tablaVelocidades.add(vel);
					    		    	}catch (Exception e) {
				    		    	    	   LOGGER.error("No se encontro el informacion para el parametro : parametros.plan.MultimediaCtaCda."+plan.getCodigoNombrePlan(),e);
				    		    		}
			    		    	}
			    		    }			    		    
			    		    grupoPlanes.setTablaVelocidades(tablaVelocidades);							
						}
		    		    
						if(bbMMCC){
							
							 /* Estos planes tiene tabla de velocidades para descargar y subir archivos */
			    		    texto = JSFPortletHelper.getContentNode("idContenido", CONTENT_PLANES_BBERRY_MM_CC);
			    		    planesBlackberryMultimediaCuentaControlada = texto.getProperty("html").getValue().getStringValue();
			    		    planesBlackberryMultimediaCuentaControlada = replaceFechaNuevoPlanCM(planesBlackberryMultimediaCuentaControlada, PROX_MES);
							
			    		    iterPlanesMMCC = grupoPlanes.getPlanesDisponibles().iterator();
			    		    List<VelocidadesPlanBean> tablaVelocidades = new ArrayList<VelocidadesPlanBean>();
			    		    while(iterPlanesMMCC.hasNext()){
			    		    	PlanBean plan = iterPlanesMMCC.next();
			    		    	if(plan.isBberryMMTodoDestino()){
			    		    		VelocidadesPlanBean vel = new VelocidadesPlanBean();
				    		    	try{	
				    		    		String [] velocidades = MiEntelProperties
				    		    							.getProperty("parametros.plan.BlackberryMultimediaCtaCda."+plan.getCodigoNombrePlan()).split("[|]");
				    		    		vel.setNombrePlan(plan.getNombrePlan());
				    		    		vel.setVelMaxDescarga(velocidades[0]);
				    		    		vel.setVelMaxSubida(velocidades[1]);
				    		    		vel.setVelPromNacDescarga(velocidades[2]);
				    		    		vel.setVelPromNacSubida(velocidades[3]);
				    		    		vel.setVelPromInterDescarga(velocidades[4]);
				    		    		vel.setVelPromInterSubida(velocidades[5]);
				    		    		vel.setCodbscs2(plan.getCodbscs2());
				    		    		tablaVelocidades.add(vel);
				    		    	}catch (Exception e) {
			    		    	    	   LOGGER.error("No se encontro el informacion para el parametro : parametros.plan.BlackberryMultimediaCtaCda."+plan.getCodigoNombrePlan(),e);
			    		    		}
			    		    	}
			    		    }	
			    		    grupoPlanes.setTablaVelocidades(tablaVelocidades);
						}
		    		   
		    		    
					} else if(grupoPlanes.getTipoPlan() == PlanHelper.getPlanMultimediaIphone()){
						
						Iterator<PlanBean> iterPlanesMMCC = grupoPlanes.getPlanesDisponibles().iterator();
						boolean mmRED = false;	//indica si hay planes multimedia red
						boolean mmTD  = false;	//indica si hay planes multimedia cuenta controlada todo destino
						while (iterPlanesMMCC.hasNext()){
							PlanBean planMMCC = iterPlanesMMCC.next();
							if(PlanHelper.isPlanMMediaRed(planMMCC.getCodigoNombrePlan())){
								mmRED = true;
								planMMCC.setMMediaRed(mmRED);
							}else if(PlanHelper.isPlanMMultimediaCControladaTodoDestido(planMMCC.getCodigoNombrePlan())){
								mmTD = true;
								planMMCC.setMMediaCuentaControladaTodoDestino(mmTD);
							}
						}
						
						if(mmRED){
							
							/* Estos planes tiene tabla de velocidades para descargar y subir archivos */
							texto = JSFPortletHelper.getContentNode("idContenido", CONTENT_PLANES_MM_RED);
			     		    planesMultimediaRed = texto.getProperty("html").getValue().getStringValue();
			     		    planesMultimediaRed = replaceFechaNuevoPlanCM(planesMultimediaRed, PROX_MES);
			     		    
			     		   iterPlanesMMCC = grupoPlanes.getPlanesDisponibles().iterator();
			    		    List<VelocidadesPlanBean> tablaVelocidades = new ArrayList<VelocidadesPlanBean>();
			    		    while(iterPlanesMMCC.hasNext()){
			    		    	PlanBean plan = iterPlanesMMCC.next();
			    		    	if(plan.isMMediaRed()){
			    		    		VelocidadesPlanBean vel = new VelocidadesPlanBean();
					    		      try{	
					    		    		String [] velocidades = MiEntelProperties
					    		    							.getProperty("parametros.plan.MultimediaRed."+plan.getCodigoNombrePlan()).split("[|]");
					    		    		vel.setNombrePlan(plan.getNombrePlan());
					    		    		vel.setVelMaxDescarga(velocidades[0]);
					    		    		vel.setVelMaxSubida(velocidades[1]);
					    		    		vel.setVelPromNacDescarga(velocidades[2]);
					    		    		vel.setVelPromNacSubida(velocidades[3]);
					    		    		vel.setVelPromInterDescarga(velocidades[4]);
					    		    		vel.setVelPromInterSubida(velocidades[5]);
					    		    		vel.setCodbscs2(plan.getCodbscs2());
					    		    		tablaVelocidades.add(vel);
					    		    	}catch (Exception e) {
				    		    	    	   LOGGER.error("No se encontro el informacion para el parametro : parametros.plan.MultimediaRed."+plan.getCodigoNombrePlan(),e);
				    		    		}
			    		    	}
			    		    }
			    		    grupoPlanes.setTablaVelocidades(tablaVelocidades);							
						}
						
						if(mmTD){
							/* Estos planes tiene tabla de velocidades para descargar y subir archivos */
							texto = JSFPortletHelper.getContentNode("idContenido", CONTENT_PLANES_MM_TODO_DESTINO);
			    		    planesMultimediaTodoDestino = texto.getProperty("html").getValue().getStringValue();
			    		    planesMultimediaTodoDestino = replaceFechaNuevoPlanCM(planesMultimediaTodoDestino, PROX_MES);
			    		    
			    		    iterPlanesMMCC = grupoPlanes.getPlanesDisponibles().iterator();
			    		    List<VelocidadesPlanBean> tablaVelocidades = new ArrayList<VelocidadesPlanBean>();
			    		    while(iterPlanesMMCC.hasNext()){
			    		    	PlanBean plan = iterPlanesMMCC.next();
			    		    	if(plan.isMMediaCuentaControladaTodoDestino()){
			    		    		VelocidadesPlanBean vel = new VelocidadesPlanBean();
				    		    	try{	
				    		    		String [] velocidades = MiEntelProperties
				    		    							.getProperty("parametros.plan.Multimedia."+plan.getCodigoNombrePlan()).split("[|]");
				    		    		vel.setNombrePlan(plan.getNombrePlan());
				    		    		vel.setVelMaxDescarga(velocidades[0]);
				    		    		vel.setVelMaxSubida(velocidades[1]);
				    		    		vel.setVelPromNacDescarga(velocidades[2]);
				    		    		vel.setVelPromNacSubida(velocidades[3]);
				    		    		vel.setVelPromInterDescarga(velocidades[4]);
				    		    		vel.setVelPromInterSubida(velocidades[5]);
				    		    		vel.setCodbscs2(plan.getCodbscs2());
				    		    		tablaVelocidades.add(vel);
				    		    	}catch (Exception e) {
			    		    	    	   LOGGER.error("No se encontro el informacion para el parametro : parametros.plan.Multimedia."+plan.getCodigoNombrePlan(),e);
			    		    		}
			    		    		
			    		    	}
			    		    }
			    		    grupoPlanes.setTablaVelocidades(tablaVelocidades);
						}		     		    
		    		    
					} else if(grupoPlanes.getTipoPlan() == PlanHelper.getPlanMultimediaRedExcedido()){
						
						texto = JSFPortletHelper.getContentNode("idContenido", CONTENT_PLANES_MM_RED_EXCEDIDO);
						planesMultimediaRedExcedido = texto.getProperty("html").getValue().getStringValue();
						planesMultimediaRedExcedido = replaceFechaNuevoPlanCM(planesMultimediaRedExcedido, PROX_MES);						
						
					} else if(grupoPlanes.getTipoPlan() == PlanHelper.getPlanMultimediaRedExcedidoTodoDestino()){
						
						texto = JSFPortletHelper.getContentNode("idContenido", CONTENT_PLANES_MM_EXCEDIDO_TODO_DESTINO);
						planesMultimediaRedExcedidoTodoDestino = texto.getProperty("html").getValue().getStringValue();
						planesMultimediaRedExcedidoTodoDestino = replaceFechaNuevoPlanCM(planesMultimediaRedExcedidoTodoDestino, PROX_MES);	
						
					}
				}
		   }
     	 
           // Solo para mercados PP (voz) - > posiblemente mas adelante para CC
             
           if(MiEntelBusinessHelper.isMercadoPrepago(mercado)){
        	   RutBean rutBean = new RutBean(rutSeleccionado);
               zonaPerfilBean = beneficiosDelegate.getZonaPerfil(numeroPcsSeleccionado, rutBean.getNumero()+"0"+rutBean.getDigito());                
                 
               String[] codsBad = CODSBAD_PRESTALUKA.split(",");
         	   for(String cod : codsBad){
         		   if(zonaPerfilBean.getStatusRespuesta().equals(cod)){
         			   validoPrestaLuka = false;
         			   break;
         		   }
         	   }
                 
               if(!isValidoPrestaLuka()){
            	   if(MiEntelBusinessHelper.isMercadoCuentaControlada(mercado)){
            		   mensajeErrorPrestaLuka = MiEntelProperties.getServiceMessages().getErrorMessage("planes.prestalukaNoValido");  
                   }
                   else{
                	   mensajeErrorPrestaLuka = MiEntelProperties.getServiceMessages().getErrorMessage("tarifas.prestalukaNoValido");  
                   }
               }
           }
             
       } catch (DAOException e) {
    	   LOGGER.error("DAOException al obtener datos de los planes disponibles ", e);
    	   JSFMessagesHelper.addServiceErrorMessage("planesDisponibles");
           
       } catch (ServiceException e) {
           LOGGER.info("ServiceException msisdn: "+numeroPcsSeleccionado+" - codigo: " + e.getCodigoRespuesta()
                   + " - " + e.getDescripcionRespuesta());
           JSFMessagesHelper.addErrorCodeMessage("gestionDePerfiles", 
        		   e.getCodigoRespuesta(), new String[]{numeroPcsSeleccionado});
           
       } catch (Exception e) {
           LOGGER.error("Exception inesperado al obtener datos de los planes disponibles ", e);
           JSFMessagesHelper.addServiceErrorMessage("planesDisponibles");
       }
    }
    
    
    
    /*
     * Preferences planes ppplus
     */    
    private String[] getPreferencesTarifasPPaltovalor(){		               	
    	FacesContext context = FacesContext.getCurrentInstance();		 
    	PortletRequest req = (PortletRequest) context.getExternalContext().getRequest();		 
    	String idTarifasPlus = req.getPreferences().getValue("Idtarifasppaltovalor",null);
  
		
		
		return idTarifasPlus!= null ? idTarifasPlus.split(",") : null;   
    }
    /*
     * Funcion para obtener planes disponiblesplus
     */

    private boolean getTarifasDisponiblesPPAltoValor(PlanDisponibleBean planDisponible){		
    	
    	ArrayList<PlanPPBean> listadoTarifasPlus = new ArrayList<PlanPPBean>();
    	String categoriaUserpp ="";
    	
    	try{
		   
    		ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());		   
    		 categoriaUserpp = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "categoriaCliente");
		   
		   if(categoriaUserpp!=null && categoriaUserpp.equals(CATEGORIA_CLIENTE_PP)){
			   
			   String[] idTarifasAltovalorPP = getPreferencesTarifasPPaltovalor();
			   
   				   if( planDisponible != null && !planDisponible.getPlanesDisponiblesPP().isEmpty() && idTarifasAltovalorPP != null ){ 			        				        						        				        	
					  
					   for( String idT : idTarifasAltovalorPP ){
						   Iterator<PlanPPBean> it = planDisponible.getPlanesDisponiblesPP().iterator();
						   while( it.hasNext() ){
							   PlanPPBean planPPBean = it.next();
							   if( planPPBean.getIdTarifa() == Integer.parseInt(idT) ){
								   listadoTarifasPlus.add(planPPBean);
								   it.remove();
							   }
						   }
					   }
					 if(listadoTarifasPlus!=null && !listadoTarifasPlus.isEmpty()){
					   validoTarifasPlus=true;
					   planDisponible.setPlanesDisponiblesPPAltoValor(listadoTarifasPlus);
					   }
				   }
		   }
		
    	}catch (Exception e) {
			LOGGER.error("Error inesperado al cargar las tarifas especiales prepagoPlus", e);
			}
			
    	return validoTarifasPlus;
		
    }
   
    /**
     * 
     * @return
     */
	public PlanPPBean getPlanActualPP() throws DAOException, ServiceException {
		return this.planActualPP;
	}

	/**
	 * 
	 * @param planActualBean
	 */
	public void setPlanActualPP(PlanPPBean planActualPP) {
		this.planActualPP = planActualPP;
	}
	
	
	/**
	 * 
	 * @return
	 */
	public PlanBean getPlanActualSSCC() {
		return planActual;
	}

	/**
	 * 
	 * @param planActualSSCC
	 */
	public void setPlanActualSSCC(PlanBean planActualSSCC) {
		this.planActual = planActualSSCC;
	}

	/**
	 * 
	 * @return
	 */
	public PlanDisponibleBean getPlanDisponible() {
		return this.planDisponible;
	}

	/**
	 * 
	 * @param planDisponibleBean
	 */
	public void setPlanDisponible(PlanDisponibleBean planDisponible) {
		this.planDisponible = planDisponible;
	}
	

	/**
	 * 
	 * @return
	 */
	public List<GrupoPlanBean> getGrupoPlanesDisponibles() {
		return grupoPlanesDisponibles;
	}

	/**
	 * 
	 * @param grupoPlanesDisponibles
	 */
	public void setGrupoPlanesDisponibles(List<GrupoPlanBean> grupoPlanesDisponibles) {
		this.grupoPlanesDisponibles = grupoPlanesDisponibles;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isExistenPlanesDisponibles() {
		return existenPlanesDisponibles;
	}

	/**
	 * 
	 * @param existenPlanesDisponibles
	 */
	public void setExistenPlanesDisponibles(boolean existenPlanesDisponibles) {
		this.existenPlanesDisponibles = existenPlanesDisponibles;
	}

	/**
	 * 
	 * @return
	 */
	public String getDescripcionPlan() {
		return descripcionPlan;
	}

	/**
	 * 
	 * @param descripcionPlan
	 */
	public void setDescripcionPlan(String descripcionPlan) {
		this.descripcionPlan = descripcionPlan;
	}

	/**
     * Metodo que efectua el cambio de Plan para los mercados SS y CC
     *
     * @param phase
     */
    public void cambiarPlanSSCC(PhaseEvent phase) {
        
    	String codigoPlan = "";
    	String nombreNuevoPlan = "";
    	String valorNuevoPlan = "";
    	String numeroPcsSeleccionado = "";
        try {
			codigoPlan = JsfUtil.getRequestParameter("codigoNuevoPlan");
			nombreNuevoPlan = JsfUtil.getRequestParameter("nombreNuevoPlan");
			valorNuevoPlan = JsfUtil.getRequestParameter("valorNuevoPlan");
            ProfileWrapper profileWrapper = ProfileWrapperHelper
                    .getProfile(JSFPortletHelper.getRequest());

            numeroPcsSeleccionado = ProfileWrapperHelper.getPropertyAsString(profileWrapper,
                    "numeroPcsSeleccionado");
            
            LOGGER.info("Movil seleccionado cambio plan: " + numeroPcsSeleccionado);
            LOGGER.info("Movil Logueado: " + ProfileWrapperHelper.getPropertyAsString(profileWrapper, "numeroPcs"));
            
            // Validacion Bloqueo Temporal
            this.planDelegate.validacionBloqueoTemporal(numeroPcsSeleccionado);
            
            // Validacion Comercial
            this.planDelegate.validacionComercial(numeroPcsSeleccionado, codigoPlan);
            
            this.planDelegate.cambiarPlanSSCC(numeroPcsSeleccionado, codigoPlan);
            
            //Carga de datos para el marcado GTM
            TransaccionGTMBean transGTM = new TransaccionGTMBean();
            transGTM.setIdTransaccion(numeroPcsSeleccionado.substring(numeroPcsSeleccionado.length() - 4));
            transGTM.setSkuID(codigoPlan);
            transGTM.setNombre(nombreNuevoPlan);
            transGTM.setNuevoValor(valorNuevoPlan);
            transGTM.setCostoOperacional(0);
            transGTM.setNumeroPlanes(1);
            transGTM.setNumeroOperaciones(1);
            transGTM.setValorTransaccion(Double.parseDouble(valorNuevoPlan) + transGTM.getCostoOperacional());
            
            respuestaJson = JsonHelper.toJsonGTMResponse(transGTM);
            
            LOGGER.info("Cambio plan realizado para movil " + numeroPcsSeleccionado);
            
        } catch (DAOException e) {
        	LOGGER.error("DAOException al realizar el cambio de plan.", e);
            respuestaJson = JsonHelper
            .toJsonServiceErrorMessage("noDisponible");

        } catch (ServiceException e) {        	
            LOGGER.info("ServiceException msisdn: "+numeroPcsSeleccionado+" - codigo: " + e.getCodigoRespuesta()
                    + " - " + e.getDescripcionRespuesta());            
            
            respuestaJson = JsonHelper.toJsonServiceErrorMessage(
                    "gestionDePerfiles", e.getCodigoRespuesta(),
                    new String[] { numeroPcsSeleccionado });

        } catch (Exception e) {
            LOGGER.error("Exception inesperada al realizar el cambio de plan.", e);
            respuestaJson = JsonHelper
           		.toJsonServiceErrorMessage("inesperado");
        }
    }

	
	/**
     * Metodo para cambiar Plan o Tarifa para el caso del mercado PP 
     *
     * @param phase
     */
    public void cambiarPlanPP(PhaseEvent phase) {
            
    	String numeroPcsSeleccionado = null;
    	
        try {
        	
        	String codigoNuevoPlan = JsfUtil.getRequestParameter("codigoNuevoPlan");
        	String nombreNuevoPlan = JsfUtil.getRequestParameter("nombreNuevoPlan");
        	String valorNuevoPlan = JsfUtil.getRequestParameter("valorNuevoPlan");
            
            ProfileWrapper profileWrapper = ProfileWrapperHelper
                    .getProfile(JSFPortletHelper.getRequest());

            numeroPcsSeleccionado = ProfileWrapperHelper.getPropertyAsString(profileWrapper,
                    "numeroPcsSeleccionado");
            
            //Carga de datos para el marcado GTM
            TransaccionGTMBean transGTM = new TransaccionGTMBean();
            transGTM.setIdTransaccion(numeroPcsSeleccionado.substring(numeroPcsSeleccionado.length() - 4));
            transGTM.setSkuID(codigoNuevoPlan);
            transGTM.setNombre(nombreNuevoPlan);
            transGTM.setNuevoValor(valorNuevoPlan);
            transGTM.setCostoOperacional(0);
            transGTM.setNumeroPlanes(1);
            transGTM.setNumeroOperaciones(1);
            transGTM.setValorTransaccion(Double.parseDouble(valorNuevoPlan) + transGTM.getCostoOperacional());
            
            planDelegate.cambiarPlanPP(numeroPcsSeleccionado, codigoNuevoPlan);
            
            respuestaJson = JsonHelper.toJsonGTMResponse(transGTM);
            
        } catch (DAOException e) {
        	LOGGER.error("DAOException al realizar el cambio de tarifa.", e);
            respuestaJson = JsonHelper
            	.toJsonServiceErrorMessage("noDisponible");

        } catch (ServiceException e) {
            LOGGER.info("ServiceException msisdn: "+numeroPcsSeleccionado+" - codigo: " + e.getCodigoRespuesta()
                    + " - " + e.getDescripcionRespuesta());            
            
            respuestaJson = JsonHelper.toJsonServiceErrorMessage(
                    "gestionDePerfiles", e.getCodigoRespuesta(),
                    new String[] { numeroPcsSeleccionado });

        } catch (Exception e) {
            LOGGER.error("Exception inesperada al realizar el cambio de tarifa.", e);
            respuestaJson = JsonHelper
            	.toJsonServiceErrorMessage("inesperado");
        }
    }
    
    
    /**
     * Cambiar Plan Comunik2 PP 
     *
     * @param phase
     */
    public void cambiarPlanComunik2PP(PhaseEvent phase) {
        
    	String numeroPcsSeleccionado = "";
    	String numeroRecibeSolicitud="";
        try {
        	numeroRecibeSolicitud = JsfUtil.getRequestParameter("numeroRecibeSolicitud");
            	
            ProfileWrapper profileWrapper = ProfileWrapperHelper
                    .getProfile(JSFPortletHelper.getRequest());

            numeroPcsSeleccionado = ProfileWrapperHelper.getPropertyAsString(profileWrapper,
                    "numeroPcsSeleccionado");

            this.planDelegate.cambiarPlanComunik2PP(numeroPcsSeleccionado, numeroRecibeSolicitud);
            
            respuestaJson = JsonHelper
            .toJsonResponse("Ok");
            
        } catch (DAOException e) {
        	LOGGER.error("DAOException al realizar el cambio de tarifa.", e);
            respuestaJson = JsonHelper
            	.toJsonServiceErrorMessage("noDisponible");

        } catch (ServiceException e) {
            LOGGER.info("ServiceException msisdn: "+numeroPcsSeleccionado+" - codigo: " + e.getCodigoRespuesta()
                    + " - " + e.getDescripcionRespuesta());                        
            respuestaJson = JsonHelper.toJsonServiceErrorMessage(
                    "gestionDePerfiles", e.getCodigoRespuesta(),
                    new String[] { numeroPcsSeleccionado });

        } catch (Exception e) {
            LOGGER.error("Exception inesperada al realizar el cambio de tarifa.", e);
            respuestaJson = JsonHelper
            	.toJsonServiceErrorMessage("inesperado");
        }
    }
    
    
    /**
     * Agregar, Modificar y Eliminar Numero Frecuente
     *
     * @param phase
     */
    public void administrarNumeroFrecuentePP(PhaseEvent phase) {
    	int codAccion = 0;
    	String numeroPcsSeleccionado = "";
        try {
        	
        	codAccion = Integer.parseInt(JsfUtil.getRequestParameter("codAccion"));
        	String idSlot = JsfUtil.getRequestParameter("idSlot");
        	String numeroRecibeSolicitud = JsfUtil.getRequestParameter("numeroRecibeSolicitud");
            
            ProfileWrapper profileWrapper = ProfileWrapperHelper
                    .getProfile(JSFPortletHelper.getRequest());

            numeroPcsSeleccionado = ProfileWrapperHelper.getPropertyAsString(profileWrapper,
                    "numeroPcsSeleccionado");
            
            if(codAccion==AGREGAR_NUMEROFRECUENTE || codAccion == MODIFICAR_NUMEROFRECUENTE){
            	
            	this.planDelegate
            	.agregarmodificarNumeroFrecuentePP(numeroPcsSeleccionado, idSlot, numeroRecibeSolicitud, codAccion);

            }else if(codAccion==ELIMINAR_NUMEROFRECUENTE){
            	
            	this.planDelegate
            	.eliminarNumeroFrecuentePP(numeroPcsSeleccionado, idSlot);
            }
            
            respuestaJson = JsonHelper
            .toJsonResponse("Ok");

        } catch (DAOException e) {
        	LOGGER.error("DAOException al realizar una accion en Numero Frecuente.", e);
            respuestaJson = JsonHelper
            		.toJsonServiceErrorMessage("noDisponible");

        } catch (ServiceException e) {
            LOGGER.info("ServiceException msisdn: "+numeroPcsSeleccionado+" - codigo: " + e.getCodigoRespuesta()
                    + " - " + e.getDescripcionRespuesta());            
            
            respuestaJson = JsonHelper.toJsonServiceErrorMessage(
                    "gestionDePerfiles", e.getCodigoRespuesta(),
                    new String[] { numeroPcsSeleccionado });

        } catch (Exception e) {
            LOGGER.error("Exception inesperada al realizar una accion en Numero Frecuente.", e);
            respuestaJson = JsonHelper
            		.toJsonServiceErrorMessage("inesperado");
        }
    }

    /**
     * 
     * @return
     */
	public String getRespuestaJson() {
		return respuestaJson;
	}

	/**
	 * 
	 * @param respuestaJson
	 */
	public void setRespuestaJson(String respuestaJson) {
		this.respuestaJson = respuestaJson;
	}
	
	
	
	/**
	 * Metodo que devuelve la lista de los prefijos para red fija
	 * @return
	 */
	public List<SelectItem> obtenerPrefijosRedFija(){
		if(prefijosRedFija == null){
			prefijosRedFija = ParametrosHelper.getPrefijoRedFijaParametrosList();
		}
		return prefijosRedFija;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<SelectItem> getPrefijosRedFija() {
		return this.prefijosRedFija;
	}

	/**
	 * 
	 * @param prefijosRedFija
	 */
	public void setPrefijosRedFija(List<SelectItem> prefijosRedFija) {
		this.prefijosRedFija = prefijosRedFija;
	}


	
	/**
	 * Metodo que obtiene la cantidad de numeros frecuentes que posee el plan actual
	 * al que pertenece el usuario
	 */
	public int obtenerCantidadNumeroFrecuentes(){
		ArrayList<NumeroFrecuenteBean> numerosFrecuentes = this.planActualPP.getNumerosFrecuentes();
		this.cantidadNumeroFrecuentes = numerosFrecuentes.size();
		return this.cantidadNumeroFrecuentes;
	}

	/**
	 * 
	 * @return
	 */
	public int getCantidadNumeroFrecuentes() {
		return cantidadNumeroFrecuentes;
	}

	/**
	 * 
	 * @param cantidadNumeroFrecuentes
	 */
	public void setCantidadNumeroFrecuentes(int cantidadNumeroFrecuentes) {
		this.cantidadNumeroFrecuentes = cantidadNumeroFrecuentes;
	}



    /**
     * Metodo que se encarga de obtener y retornar el valor de la preferencia del portlet en solicitud
     * dependiendo del nombre.
     * @return
     */
    public String getInfoTitulo(){
    	try{
    	  return JSFPortletHelper.getPreference(JSFPortletHelper.getPreferencesObject(),"infoTitulo", null);
        }catch(Exception e){
        	LOGGER.error("No fue posible obtener la preferencia infoTitulo "+ e.getMessage(), e);
        	return "";
        }
    }
    
    
    /**
     * Metodo que carga el listado de los planes Multimedia y el plan actual
     * @param planActual
     */
    public void obtenerFiltroCambioPlanOrigen(String planActual){
    	    
       try {    	   
    	   this.setPlanOrigen(planActual);
    	   this.setMultimedia( MiEntelProperties.getProperty("parametros.cambioplan.multimedia")  );
    	  
       } catch (Exception e) {
    	   
    	   LOGGER.error("Exception Error al leer properties Cambio plan, Planes Origen y Destino", e);
       }
    }
    
    
    /**
     * Metodo que controla el listado del historial comunik2
     */
	public void obtenerHistorialComunik2(PhaseEvent event) {

		String numeroPcsSeleccionado = "";

		try {

			ProfileWrapper profileWrapper = ProfileWrapperHelper
					.getProfile(JSFPortletHelper.getRequest());
			numeroPcsSeleccionado = ProfileWrapperHelper.getPropertyAsString(
					profileWrapper, "numeroPcsSeleccionado");
			planActualPP = planDelegate.getPlanActualPP(numeroPcsSeleccionado);

			isComunik2 = ParametrosHelper.getExisteParametro(
					"comunik2.planescomunik2", String.valueOf(planActualPP
							.getIdTarifa()));
			if (isComunik2) {
				listcomunik2Bean = planDelegate
						.obtenerHistorialComunik2(numeroPcsSeleccionado);

				if (listcomunik2Bean.size() > 0) {

					Collections
							.<HistorialComunik2Bean> sort(this.listcomunik2Bean);

					this.listcomunik2Bean = this.listcomunik2Bean != null
							&& this.listcomunik2Bean.size() > 10 ? this.listcomunik2Bean
							.subList(0, 10)
							: this.listcomunik2Bean;

				} else {

					JSFMessagesHelper
							.addServiceErrorMessage("sinhistorialcomunik2");

				}

			}

		} catch (DAOException e) {
			LOGGER.error("DAOException caught", e);
			JSFMessagesHelper.addServiceErrorMessage("noDisponible");
		} catch (ServiceException e) {
			LOGGER.info("ServiceException msisdn: "+numeroPcsSeleccionado+" - codigo: " + e.getCodigoRespuesta()
					+ " - " + e.getDescripcionRespuesta());
			JSFMessagesHelper.addErrorCodeMessage("gestionDePerfiles", e
					.getCodigoRespuesta(),
					new String[] { numeroPcsSeleccionado });
		} catch (Exception e) {
			LOGGER.error("Exception caught: "+e.getMessage(), e);
			JSFMessagesHelper.addServiceErrorMessage("inesperado");
		}
	}
         
    
      /**
       * Metodo retorna un objeto con la solicitud pendiente del plan comunik2
       */
	public void consultarSolicitudComunik2(PhaseEvent event) {
		String numeroPcsSeleccionado = "";
		try {

			ProfileWrapper profileWrapper = ProfileWrapperHelper
					.getProfile(JSFPortletHelper.getRequest());
			numeroPcsSeleccionado = ProfileWrapperHelper.getPropertyAsString(
					profileWrapper, "numeroPcsSeleccionado");

			solicitudComunik2Bean = planDelegate
					.obtenerSolicitudComunik2(numeroPcsSeleccionado);

		} catch (DAOException e) {
			LOGGER.error("DAOException al obtener datos la solicitud comunik2", e);
			JSFMessagesHelper.addServiceErrorMessage("noDisponible");
		} catch (ServiceException e) {
			LOGGER.info("ServiceException msisdn: "+numeroPcsSeleccionado+" - codigo: " + e.getCodigoRespuesta()
					+ " - " + e.getDescripcionRespuesta());
			JSFMessagesHelper.addErrorCodeMessage("gestionDePerfiles", e
					.getCodigoRespuesta(),
					new String[] { numeroPcsSeleccionado });

		} catch (Exception e) {
			LOGGER.error("Exception inesperado al obtener datos la solicitud comunik2", e);
			JSFMessagesHelper.addServiceErrorMessage("inesperado");
		}
	}
        
      
        
        
	public void administrarSolicitudComunik2(ActionEvent event) {

		String numeroPcsSeleccionado = "";
		String accion = event.getComponent().getId().equals("aceptar_solictud")?"true":"false";

		try {
			ProfileWrapper profileWrapper = ProfileWrapperHelper
					.getProfile(JSFPortletHelper.getRequest());

			numeroPcsSeleccionado = ProfileWrapperHelper.getPropertyAsString(
					profileWrapper, "numeroPcsSeleccionado");
			
				planDelegate.administrarSolicitudComunik2(
						numeroPcsSeleccionado,accion);
				
				solicitudComunik2Bean = null;	 
			

		} catch (DAOException e) {
			LOGGER.error("DAOException al aceptar/rechazar la solicitud comunik2 ", e);
			JSFMessagesHelper.addServiceErrorMessage("noDisponible");
		} catch (ServiceException e) {
			LOGGER.info("ServiceException msisdn: "+numeroPcsSeleccionado+" - codigo: " + e.getCodigoRespuesta()
					+ " - " + e.getDescripcionRespuesta());
			JSFMessagesHelper.addErrorCodeMessage("gestionDePerfiles", e
					.getCodigoRespuesta(),
					new String[] { numeroPcsSeleccionado });
		} catch (Exception e) {
			LOGGER.error("Exception inesperado al aceptar/rechazar la solicitud comunik2 ", e);
			JSFMessagesHelper.addServiceErrorMessage("inesperado");
		}
	}
	      
      
	/**
	 * Cambiar Plan Comunik2 PP
	 * 
	 * @param phase
	 */
	public String cambioPlanComunik2PP() {

		String numeroPcsSeleccionado = "";	
		String resultado="";	
		
		try {			
            
			ProfileWrapper profileWrapper = ProfileWrapperHelper
					.getProfile(JSFPortletHelper.getRequest());

			numeroPcsSeleccionado = ProfileWrapperHelper.getPropertyAsString(
					profileWrapper, "numeroPcsSeleccionado");

			this.planDelegate.cambiarPlanComunik2PP(numeroPcsSeleccionado,
					numeroComunik2);
			
			numeroComunik2="";

		} catch (DAOException e) {
			LOGGER.error("DAOException al cambiar plan comunik2", e);
			JSFMessagesHelper.addServiceErrorMessage("noDisponible");
		} catch (ServiceException e) {
			LOGGER.info("ServiceException msisdn: "+numeroPcsSeleccionado+" - codigo: " + e.getCodigoRespuesta()
					+ " - " + e.getDescripcionRespuesta());
			JSFMessagesHelper.addErrorCodeMessage("gestionDePerfiles", e
					.getCodigoRespuesta(),new String[]{numeroPcsSeleccionado});
		} catch (Exception e) {
			LOGGER.error("Exception inesperado al cambiar plan comunik2", e);
			JSFMessagesHelper.addServiceErrorMessage("inesperado");
		}
		return resultado;
	}
		
    /**
	 * @return the esComunik2
	 */
	public boolean isComunik2() {
		return isComunik2;
	}

	/**
	 * @return the listcomunik2Bean
	 */
	public List<HistorialComunik2Bean> getListcomunik2Bean() {
		return listcomunik2Bean;
	}

	/**
	 * @return
	 */
    public String getPlanOrigen() {
		return planOrigen;
	}

    /**
	 * 
	 * @param planOrigen
	 */
	public void setPlanOrigen(String planOrigen) {
		this.planOrigen = planOrigen;
	}

	/**
	 * @return
	 */
	public String getMultimedia() {
		return multimedia;
	}

	/**
	 * 
	 * @param multimedia
	 */
	public void setMultimedia(String multimedia) {
		this.multimedia= multimedia;
	}

	/**
	 * @return the solicitudComunik2Bean
	 */
	public SolicitudComunik2Bean getSolicitudComunik2Bean() {
		return solicitudComunik2Bean;
	}

	/**
	 * @return the numeroComunik2
	 */
	public String getNumeroComunik2() {
		return numeroComunik2;
	}

	/**
	 * @param numeroComunik2 the numeroComunik2 to set
	 */
	public void setNumeroComunik2(String numeroComunik2) {
		this.numeroComunik2 = numeroComunik2;
	}
    
	/**
	 * 
	 */
	public String getMensajeVelocidadTransmisionCM(){
		mensajeVelocidadTransmisionCM = "";
		try{
			
		 String idContenido = MiEntelProperties.getProperty("plan.infoPlanActual.idContenido");
	   	 Node mensaje = JSFPortletHelper.getContentNode("idContenido", idContenido);
	   	 mensajeVelocidadTransmisionCM = mensaje.getProperty("html").getValue().getStringValue();
	   	 
		}catch (Exception e) {
			LOGGER.error("Exception al intentar obtener el mensaje de velocidad transmision del CM",e);
		} 	 
		
		return mensajeVelocidadTransmisionCM.replace("{trafico}", 
				PlanHelper.calculateValorTraficado(getPlanActualSSCC().getLimiteIMovil()));
	   	 
	}

	/**
	 * metodo utilitario que remplaza en el contenido la fecha en la que empezara a regir el nuevo plan	
	 * @param idContenido
	 */
	
	 private String replaceFechaNuevoPlanCM(String texto, int opcion){
		 try{
			 switch (opcion) {
			case 1:
				texto = texto.replaceAll("<fechaInicio>",DateHelper.manana(DateHelper.FORMAT_ddMMyyyy_SLASH));
				break;
			
			case 2:
				texto = texto.replaceAll("<fechaInicio>",DateHelper.primerDiaProximoMes(DateHelper.FORMAT_ddMMyyyy_SLASH));
				break;

			default:
				break;
			}
			 
		 }catch (Exception e) {
			return texto;
		 }
		 return texto;
	 }

	/**
	 * 
	 * @param mensajeVelocidadTransmisionCM
	 */
	public void setMensajeVelocidadTransmisionCM(String mensajeVelocidadTransmisionCM) {
		this.mensajeVelocidadTransmisionCM = mensajeVelocidadTransmisionCM;
	}

	/**
	 * @return the cargaNrosFrecuenteAjax
	 */
	public boolean isCargaNrosFrecuenteAjax() {
		return cargaNrosFrecuenteAjax;
	}

	/**
	 * @param cargaNrosFrecuenteAjax the cargaNrosFrecuenteAjax to set
	 */
	public void setCargaNrosFrecuenteAjax(boolean cargaNrosFrecuenteAjax) {
		this.cargaNrosFrecuenteAjax = cargaNrosFrecuenteAjax;
	}

	public String getFechaActualFormat() { 
		Calendar c = Calendar.getInstance(); 
		fechaActualFormat = (c.getTimeInMillis() / 1000L)+"";	 
		return fechaActualFormat; 
	} 
	
    public String getPlanCCNuevo() {
		return planCCNuevo;
	}

	public void setPlanCCNuevo(String planCCNuevo) {
		this.planCCNuevo = planCCNuevo;
	}

	public PlanBean getPlanActual() {
		return planActual;
	}

	public void setPlanActual(PlanBean planActual) {
		this.planActual = planActual;
	}
	
	public String getPlanesFullRed() {
		return planesFullRed;
	}

	public void setPlanesFullRed(String planesFullRed) {
		this.planesFullRed = planesFullRed;
	}

	public String getPlanesTarifaUnicaSinSMS() {
		return planesTarifaUnicaSinSMS;
	}

	public void setPlanesTarifaUnicaSinSMS(String planesTarifaUnicaSinSMS) {
		this.planesTarifaUnicaSinSMS = planesTarifaUnicaSinSMS;
	}

	public String getPlanesTarifaUnicaConSMS() {
		return planesTarifaUnicaConSMS;
	}

	public void setPlanesTarifaUnicaConSMS(String planesTarifaUnicaConSMS) {
		this.planesTarifaUnicaConSMS = planesTarifaUnicaConSMS;
	}

	public String getPlanesCuentaControladaTarifaRed() {
		return planesCuentaControladaTarifaRed;
	}

	public void setPlanesCuentaControladaTarifaRed(
			String planesCuentaControladaTarifaRed) {
		this.planesCuentaControladaTarifaRed = planesCuentaControladaTarifaRed;
	}

	public String getPlanesCuentaControladaTarifaPlana() {
		return planesCuentaControladaTarifaPlana;
	}

	public void setPlanesCuentaControladaTarifaPlana(
			String planesCuentaControladaTarifaPlana) {
		this.planesCuentaControladaTarifaPlana = planesCuentaControladaTarifaPlana;
	}

	public String getPlanesMultimediaRed() {
		return planesMultimediaRed;
	}

	public void setPlanesMultimediaRed(String planesMultimediaRed) {
		this.planesMultimediaRed = planesMultimediaRed;
	}

	public String getPlanesBlackberryMultimediaRed() {
		return planesBlackberryMultimediaRed;
	}

	public void setPlanesBlackberryMultimediaRed(String planesBlackberryMultimediaRed) {
		this.planesBlackberryMultimediaRed = planesBlackberryMultimediaRed;
	}

	public String getPlanesMultimediaTodoDestino() {
		return planesMultimediaTodoDestino;
	}

	public void setPlanesMultimediaTodoDestino(String planesMultimediaTodoDestino) {
		this.planesMultimediaTodoDestino = planesMultimediaTodoDestino;
	}

	public String getPlanesBlackberryMultimediaTodoDestino() {
		return planesBlackberryMultimediaTodoDestino;
	}

	public void setPlanesBlackberryMultimediaTodoDestino(
			String planesBlackberryMultimediaTodoDestino) {
		this.planesBlackberryMultimediaTodoDestino = planesBlackberryMultimediaTodoDestino;
	}

	public String getPlanesMultimediaCuentaControlada() {
		return planesMultimediaCuentaControlada;
	}

	public void setPlanesMultimediaCuentaControlada(
			String planesMultimediaCuentaControlada) {
		this.planesMultimediaCuentaControlada = planesMultimediaCuentaControlada;
	}

	public String getPlanesBlackberryMultimediaCuentaControlada() {
		return planesBlackberryMultimediaCuentaControlada;
	}

	public void setPlanesBlackberryMultimediaCuentaControlada(
			String planesBackberryMultimediaCuentaControlada) {
		this.planesBlackberryMultimediaCuentaControlada = planesBackberryMultimediaCuentaControlada;
	}
	
	/**
	 * @return the planesMultimediaRedExcedidoTodoDestino
	 */
	public String getPlanesMultimediaRedExcedidoTodoDestino() {
		return planesMultimediaRedExcedidoTodoDestino;
	}

	/**
	 * @param planesMultimediaRedExcedidoTodoDestino the planesMultimediaRedExcedidoTodoDestino to set
	 */
	public void setPlanesMultimediaRedExcedidoTodoDestino(
			String planesMultimediaRedExcedidoTodoDestino) {
		this.planesMultimediaRedExcedidoTodoDestino = planesMultimediaRedExcedidoTodoDestino;
	}

	/**
	 * @return the planesMultimediaRedExcedido
	 */
	public String getPlanesMultimediaRedExcedido() {
		return planesMultimediaRedExcedido;
	}

	/**
	 * @param planesMultimediaRedExcedido the planesMultimediaRedExcedido to set
	 */
	public void setPlanesMultimediaRedExcedido(String planesMultimediaRedExcedido) {
		this.planesMultimediaRedExcedido = planesMultimediaRedExcedido;
	}
	
	//SC 31-07-2014 Luis I2B
	public String getCategoriaCliente() {
		return categoriaCliente;
	}

	public void setCategoriaCliente(String categoriaCliente) {
		this.categoriaCliente = categoriaCliente;
	}
	//FIN SC 31-07-2014 Luis I2B


	/**
	 * @return the mensajePlanesMMExcedidosCM
	 */
	public String getMensajePlanesMMExcedidosCM() {
		  mensajePlanesMMExcedidosCM = "";
		try{
			
		 String idContenido = MiEntelProperties.getProperty("plan.infoPlanActualMMExcedido.idContenido");
		 idContenido = idContenido + getPlanActualSSCC().getCodbscs2();
		 
	   	 Node mensaje = JSFPortletHelper.getContentNode("idContenido", idContenido);
	   	mensajePlanesMMExcedidosCM = mensaje.getProperty("html").getValue().getStringValue();
	   	 
		}catch (Exception e) {
			LOGGER.error("Exception al buscar el mensaje informativo de los planes multimedia red excedidos",e);
		} 	 
		
		return mensajePlanesMMExcedidosCM;
	   	 
	}
	
	public Double getSaldoActualCC(){
		String numeroPcsSeleccionado = "";
    	String atributoAA="";
    	Double saldoActualCC = 0.0;
    	
        try {        	
        	 ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());
        	
             numeroPcsSeleccionado = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"numeroPcsSeleccionado");
             atributoAA = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"aaa");
             
             resumenPlan = this.planDelegate.getPlanResumenCC(numeroPcsSeleccionado,atributoAA);
             resumenPlan.setMercadoCuentaControlada(true);
             
             saldoActualCC = resumenPlan.getSaldo();
       }
       catch (DAOException e) {
    	   LOGGER.error("Exception inesperado al obtener datos del plan",  e);
    	   JSFMessagesHelper.addServiceErrorMessage("planActual");
           
       } catch (ServiceException e) {
           LOGGER.info("ServiceException msisdn: "+numeroPcsSeleccionado+" - codigo: " + e.getCodigoRespuesta()
                   + " - " + e.getDescripcionRespuesta());
           JSFMessagesHelper.addErrorCodeMessage("gestionDePerfiles", 
        		   e.getCodigoRespuesta(), new String[]{numeroPcsSeleccionado});
           
       } catch (Exception e) {
           LOGGER.error("Exception inesperado al obtener datos del plan", e);
           JSFMessagesHelper.addServiceErrorMessage("planActual");
       }
       
       return saldoActualCC;
	}

	public String getCategoriaZona(String numeroPcsSeleccionado) {

		LOGGER.info("Consultando categoria de cliente: " + numeroPcsSeleccionado);
		
        String categoriaCliente="";              
        ResultadoConsultarPuntosBean infoPuntos = new ResultadoConsultarPuntosBean();
        VtasYMktgFidelizacionDelegate VyFDelegate = new VtasYMktgFidelizacionDelegate();
        
        try{        	        	
        	
            infoPuntos = VyFDelegate.consultarCategoriaCliente(infoPuntos, numeroPcsSeleccionado);
            categoriaCliente = "Cliente " + infoPuntos.getCategoriaCliente().replace("Zona", "").trim();
                        
            LOGGER.info("Categoria de cliente " + numeroPcsSeleccionado + ": " + categoriaCliente);    
            
        } catch (DAOException e) {
            LOGGER.error("DAOException al consultar categoria Zona para el rut: " + numeroPcsSeleccionado, e);            
        } catch (ServiceException e) {
        	LOGGER.info("ServiceException en la consulta de la categoria Zona para el rut: " + numeroPcsSeleccionado + " - " + e.getCodigoRespuesta() + " - " + e.getDescripcionRespuesta());             
        } catch (Exception e) {
            LOGGER.error("Exception al consultar categoria para el rut: " + numeroPcsSeleccionado, e);            
        }
        
		return categoriaCliente;
	   	 
	}
	
	//SC 31-07-2014 Luis I2B || Unificación de Categoría DashBoard/Zona Mis Puntos
	/**
     * Action method para consultar informacion de puntos para el rut del usuario en sesion
     * @param <code>rutSinDV</code> rut sin digito verificador
     */
    private void consultarCategoriaCliente(String rutSinDV, String numeroPcsSeleccionado){
        
    	ResultadoConsultarPuntosBean infoPuntos = new ResultadoConsultarPuntosBean();
	
        try{        	 

        if(0 == categoriaCliente.trim().length()){
        		
            infoPuntos = planDelegate.consultarCategoriaCliente(infoPuntos, numeroPcsSeleccionado);

            StringBuffer categoriaClienteBuffer = new StringBuffer();
            
            categoriaClienteBuffer.append("Cliente ");
            categoriaClienteBuffer.append(infoPuntos.getCategoriaCliente().replace("Zona", "").trim());
            
            setCategoriaCliente(categoriaClienteBuffer.toString());

            LOGGER.info("Categoria de cliente " + numeroPcsSeleccionado + ": " + getCategoriaCliente());
            
        }
        
        } catch (DAOException e) {
            LOGGER.error("DAOException al consultar puntos para el rut: " + rutSinDV, e);            
        } catch (ServiceException e) {
        	LOGGER.error("ServiceException en la consulta de puntos: " + numeroPcsSeleccionado + " - "
						+ e.getCodigoRespuesta() + " - " + e.getDescripcionRespuesta());             
        } catch (Exception e) {
            LOGGER.error("Exception al conusltar puntos para el rut: " + rutSinDV, e);         
        }

    }
    //FIN SC 31-07-2014 Luis I2B
	
		/*NUEVO RODRIGO DIAZ*/
	public String getPageLabelActual() {
		return JSFPortletHelper.getCurrentPageLabel();
	}   
	
	public String getCargarCargoFijo() {
		return cargarCargoFijo;
	}

	public void setCargarCargoFijo(String cargarCargoFijo) {
		this.cargarCargoFijo = cargarCargoFijo;
	}
	/*FIN NUEVO RODRIGO DIAZ*/

}