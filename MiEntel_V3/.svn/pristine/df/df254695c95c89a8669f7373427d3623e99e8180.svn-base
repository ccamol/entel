/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.cliente.perfil.util;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;


import com.bea.content.Node;
import com.epcs.administracion.suscripciones.dao.SuscripcionesBolsaDAO;
import com.epcs.administracion.suscripciones.delegate.BolsasBAMCCPPDelegate;
import com.epcs.administracion.suscripciones.delegate.SuscripcionesDelegate;
import com.epcs.bean.BolsaActivaBAMCCPP;
import com.epcs.bean.BolsaBean;
import com.epcs.bean.BolsaPPBean;
import com.epcs.bean.BolsasActualesDisponiblesBean;
import com.epcs.bean.GrupoPlanBean;
import com.epcs.bean.PlanBAMBean;
import com.epcs.bean.PlanBean;
import com.epcs.bean.ResumenBolsasActivasBAMCCPP;
import com.epcs.bean.ResumenPlan;
import com.epcs.bean.TasacionBean;
import com.epcs.bean.TipoPlanBean;
import com.epcs.billing.prodfactura.dao.FacturacionElectronicaDAO;
import com.epcs.billing.prodfactura.delegate.FacturacionElectronicaDelegate;
import com.epcs.billing.registrouso.dao.TraficoBamDAO;
import com.epcs.billing.registrouso.delegate.TraficoBamCCDelegate;
import com.epcs.cliente.orden.dao.BolsaDAO;
import com.epcs.cliente.orden.delegate.BolsaDelegate;
import com.epcs.cliente.perfil.bean.Oferta;
import com.epcs.cliente.perfil.dao.PlanDAO;
import com.epcs.cliente.perfil.delegate.OfertaBlindajeDelegate;
import com.epcs.cliente.perfil.delegate.PlanDelegate;
import com.epcs.cliente.perfil.types.DetallePlanActualType;
import com.epcs.erp.seguridad.delegate.SeguridadDelegate;
import com.epcs.nba.NBA;
import com.epcs.nba.NBAUtils;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.ParametrosHelper;
import com.epcs.recursoti.configuracion.Utils;
import com.epcs.recursoti.configuracion.jsf.utils.JSFMessagesHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JSFPortletHelper;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

/**
 * @author gcastro (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class PlanHelper {
	
	
	private static final Logger LOGGER = Logger.getLogger(PlanHelper.class);
	
	private static final String ID_CONTENIDO_GRUPO_OFERTAS_NBA = MiEntelProperties
	.getProperty("parametros.ofertas.grupoOfertasNBA");
	
	/*
	 * Planes Suscripcion
	 */
	
	
	/**
     * Referencia al id Plan Tarifa Plana existente en aplicacion.properties
     */
    private static final String PLAN_TARIFA_PLANA_SS = MiEntelProperties
            .getProperty("parametros.tipoplan.tarifaplana.id");
    
	/**
     * Referencia al id Plan Red existente en aplicacion.properties
     */
    private static final String PLAN_RED_SS = MiEntelProperties
            .getProperty("parametros.tipoplan.red.id");
    
	/**
     * Referencia al id Plan Tarifa Unica Frecuente existente en aplicacion.properties
     */
    private static final String PLAN_TARIFA_UNICA_FREC = MiEntelProperties
            .getProperty("parametros.tipoplan.tarifaunicafrec.id");
    
    
    /**
     * Referencia al id Plan Joven existente en aplicacion.properties
     */
    private static final String PLAN_JOVEN = MiEntelProperties
            .getProperty("parametros.tipoplan.joven.id");
    
    
    /**
     * Referencia al id Plan Full existente en aplicacion.properties
     */
    private static final String PLAN_FULL = MiEntelProperties
            .getProperty("parametros.tipoplan.full.id");
    
    /**
     * Referencia al id Plan Full existente en aplicacion.properties
     */
    private static final String PLAN_TARIFA_UNICA = MiEntelProperties
            .getProperty("parametros.tipoplan.tarifaunica.id");
    
    
    /**
     * Referencia al id Plan Red Fija existente en aplicacion.properties
     */
    private static final String PLAN_RED_FIJA = MiEntelProperties
            .getProperty("parametros.tipoplan.redfija.id");
    
    
    /**
     * Referencia al id Plan Familia existente en aplicacion.properties
     */
    private static final String PLAN_FAMILIA = MiEntelProperties
            .getProperty("parametros.tipoplan.familia.id");
    
    
    /**
     * Referencia al id Plan Red Empresa existente en aplicacion.properties
     */
    private static final String PLAN_RED_EMPRESA = MiEntelProperties
            .getProperty("parametros.tipoplan.redempresa.id");
    
    /**
     * Referencia al id Plan Multimedia Iphone existente en aplicacion.properties
     */
    private static final String PLAN_MULTIMEDIA_IPHONE = MiEntelProperties
            .getProperty("parametros.tipoplan.muliphone.id");
    
    /**
     * Referencia al id Plan Multimedia Red existente en aplicacion.properties
     */
    private static final String PLAN_MULTIMEDIA_RED = MiEntelProperties
            .getProperty("parametros.tipoplan.mulred.id");
    
    /**
     * Referencia al id Plan Grupal Empresas existente en aplicacion.properties
     */    
    private static final String PLAN_GRUPAL_EMPRESAS_SS = MiEntelProperties
			.getProperty("parametros.tipoplan.grpdexemp.id");    
    
    /*
	 * Planes Cuenta Controlada
	 */
    
	/**
     * Referencia al id Plan Tarifa Plana existente en aplicacion.properties
     */
    private static final String PLAN_TARIFA_PLANA_CC = MiEntelProperties
            .getProperty("parametros.tipoplan.ccplana.id");
    
	/**
     * Referencia al id Plan Red existente en aplicacion.properties
     */
    private static final String PLAN_RED_CC = MiEntelProperties
            .getProperty("parametros.tipoplan.ccred.id");
    
	/**
     * Referencia al id Plan Multimedia en aplicacion.properties
     */
    private static final String PLAN_MULTIMEDIA_CC = MiEntelProperties
            .getProperty("parametros.tipoplan.ccmul.id");
    
    /**
     * Referencia al idSlot para numero frecuente en aplicacion.properties
     */
    private static final String SLOT_NUMERO_FRECUENTE = MiEntelProperties
    .getProperty("parametros.frecuente.idslot");
    
    /**
     * Referencia al idSlot para numero favorito en aplicacion.properties
     */
    private static final String SLOT_NUMERO_FAVORITO = MiEntelProperties
    .getProperty("parametros.favorito.idslot");
    
    
	/**
	 * Referencia a la data cuando plan posee minutos ilimitado existente en aplicacion.properties
	 */
	private static final String PLAN_TOTALMINUTOS_LIMITADO = MiEntelProperties
    .getProperty("plan.totalMinutos.ilimitado");
	
	/**
	 * Referencia al limite a superar de los segundos de un plan para ser ilimitado existente en aplicacion.properties
	 */
	private static final String PLAN_LIMITESEGUNDOS_LIMITADO = MiEntelProperties
    .getProperty("plan.limiteSegundos.ilimitado");
	
	/**
	 * Referencia al numero de minutos que posee la tasacion MINFRE existente en aplicacion.properties
	 */
	private static final String PLAN_MINUTOS_FRECUENTE = MiEntelProperties
    .getProperty("plan.valorTasacion.minFrecuente");
	
	
	/**
	 * Referencia al id del plan Comunik2 existente en aplicacion.properties
	 */
	private static final String PLAN_COMUNIK2_ID = MiEntelProperties
    .getProperty("plan.comunikd2.id");
	
	
    
    /**
     * Referencia al nombre de la tasacion Minutos Frecuentes en aplicacion.properties
     */
    private static final String PLAN_NOMBRETASACION_MINFRE = MiEntelProperties
            .getProperty("plan.nombreTasacion.minFrecuente");
    
    /**
     * Referencia al atributo de la tasacion DBOL aplicacion.properties
     */
    private static final String TASACION_DBOL = MiEntelProperties
    .getProperty("plan.nombreTasacion.dbol");
    
    
    /**
	 * Referencia al atributo de la tasacion MBOL aplicacion.properties
	 */
	private static final String TASACION_MBOL = MiEntelProperties
    .getProperty("plan.nombreTasacion.mbol");
	
	
	 /**
	 * Referencia al atributo de la tasacion VBOL aplicacion.properties
	 */
	private static final String TASACION_VBOL = MiEntelProperties
    .getProperty("plan.nombreTasacion.vbol");
	
	
	
	private static final String FLAG_HIDDEN = MiEntelProperties
    .getProperty("plan.flagHidden.attr");
    
    /**
     * 
     * @return id plan tarifa plana ss
     */
	public static int getPlanTarifaPlanaSs() {
		return Integer.parseInt(PLAN_TARIFA_PLANA_SS);
	}
	
	
	 /**
     * Referencia al id Plan Multimedia Red Excedido existente en aplicacion.properties
     */
    private static final String PLAN_MULTIMEDIA_RED_EXCEDIDO = MiEntelProperties
            .getProperty("parametros.tipoplan.mulredexc.id");
    
    
    /**
     * Referencia al id Plan Multimedia Red Excedido Todo destino existente en aplicacion.properties
     */
    private static final String PLAN_MULTIMEDIA_RED_EXCEDIDO_TODO_DESTINO = MiEntelProperties
            .getProperty("parametros.tipoplan.mulexctd.id");
    
    /*
     *Nuevo Estructura Tarifaria Planes Empresa
     */
    
    private static final String PLAN_EMPRESA = MiEntelProperties
    .getProperty("parametros.tipoplan.planempresaSGO.id");
    
    
    /**
     * Referencia al id Plan Multimedia Red Excedido Todo destino  Empresa existente en aplicacion.properties
     */
    private static final String PLAN_MULTIMEDIA_RED_EXCEDIDO_TODO_DESTINO_EMPRESAS = MiEntelProperties
            .getProperty("parametros.tipoplan.mulexctdemp.id");  

    
    private static final String URL_CARGAR_OFERTA_DISPONIBLE = MiEntelProperties
			.getProperty("parametros.blindaje.urlCargarOferta");
    
    private OfertaBlindajeDelegate ofertaBlindajeDelegate;
	private FacturacionElectronicaDelegate facturacionElectronicaDelegate;
	private BolsasBAMCCPPDelegate bolsasBAMCCPPDelegate;
	private ResumenBolsasActivasBAMCCPP bolsasActivas;
	private PlanBAMBean planActualBAMSSCC;
	private TraficoBamCCDelegate miTraficoBAMCCDelegate;
	
	public OfertaBlindajeDelegate getOfertaBlindajeDelegate() {
		return ofertaBlindajeDelegate;
	}

	public void setOfertaBlindajeDelegate(
			OfertaBlindajeDelegate ofertaBlindajeDelegate) {
		this.ofertaBlindajeDelegate = ofertaBlindajeDelegate;
	}
	
	public FacturacionElectronicaDelegate getFacturacionElectronicaDelegate() {
		return facturacionElectronicaDelegate;
	}

	public void setFacturacionElectronicaDelegate(
				 FacturacionElectronicaDelegate facturacionElectronicaDelegate) {
		this.facturacionElectronicaDelegate = facturacionElectronicaDelegate;
	}
	
	public void setBolsasBAMCCPPDelegate(BolsasBAMCCPPDelegate bolsasBAMCCPPDelegate) {
		this.bolsasBAMCCPPDelegate = bolsasBAMCCPPDelegate;
	}
	public BolsasBAMCCPPDelegate getBolsasBAMCCPPDelegate() {
		return bolsasBAMCCPPDelegate;
	}
    

	/**
	 * 
	 * @return id plan red ss
	 */
	public static int getPlanRedSs() {
		return Integer.parseInt(PLAN_RED_SS);
	}


	/**
	 * 
	 * @return id tarifa unica frecuente
	 */
	public static int getPlanTarifaUnicaFrec() {
		return Integer.parseInt(PLAN_TARIFA_UNICA_FREC);
	}


	/**
	 * 
	 * @return id plan joven
	 */
	public static int getPlanJoven() {
		return Integer.parseInt(PLAN_JOVEN);
	}


	/**
	 * 
	 * @return id plan full
	 */
	public static int getPlanFull() {
		return Integer.parseInt(PLAN_FULL);
	}


	/**
	 * 
	 * @return id plan tarifa unica
	 */
	public static int getPlanTarifaUnica() {
		return Integer.parseInt(PLAN_TARIFA_UNICA);
	}


	/**
	 * 
	 * @return id plan red fija 
	 */
	public static int getPlanRedFija() {
		return Integer.parseInt(PLAN_RED_FIJA);
	}


	/**
	 * 
	 * @return id plan familia
	 */
	public static int getPlanFamilia() {
		return Integer.parseInt(PLAN_FAMILIA);
	}


	/**
	 * 
	 * @return id plan red empresa
	 */
	public static int getPlanRedEmpresa() {
		return Integer.parseInt(PLAN_RED_EMPRESA);
	}


	/**
	 * 
	 * @return id plan multimedia Iphone
	 */
	public static int getPlanMultimediaIphone() {
		return Integer.parseInt(PLAN_MULTIMEDIA_IPHONE);
	}
	
	

	/**
	 * 
	 * @return id plan multimedia red
	 */
	public static int getPlanMultimediaRed() {
		return Integer.parseInt(PLAN_MULTIMEDIA_RED);
	}
	
	/**
	 * 
	 * @return id Plan Grupal Empresas
	 */
	public static int getPlanGrupalEmpresas() {
		return Integer.parseInt(PLAN_GRUPAL_EMPRESAS_SS);
	}


	/**
	 * 
	 * @return id plan tarifa plana cc
	 */
	public static int getPlanTarifaPlanaCc() {
		return Integer.parseInt(PLAN_TARIFA_PLANA_CC);
	}


	/**
	 * 
	 * @return id plan red cc
	 */
	public static int getPlanRedCc() {
		return Integer.parseInt(PLAN_RED_CC);
	}


	/**
	 * 
	 * @return id plan multimedia cc
	 */
	public static int getPlanMultimediaCc() {
		return Integer.parseInt(PLAN_MULTIMEDIA_CC);
	}

	/**
	 * 
	 * @return id slot numero frecuente
	 */
	public static int getSlotNumeroFrecuente() {
		return Integer.parseInt(SLOT_NUMERO_FRECUENTE);
	}


	/**
	 * 
	 * @return id slot numero favorito
	 */
	public static int getSlotNumeroFavorito() {
		return Integer.parseInt(SLOT_NUMERO_FAVORITO);
	}

	/**
	 * 
	 * @return id plan comunik2
	 */
	public static int getPlanComunik2Id() {
		return Integer.parseInt(PLAN_COMUNIK2_ID);
	}
	
	

	/**
	 * 
	 * @return flagHidden
	 */
	public static String getFlagHidden() {
		return FLAG_HIDDEN;
	}
	
	
	/**
	 * @return the planMultimediaRedExcedido
	 */
	public static int getPlanMultimediaRedExcedido() {
		return Integer.parseInt(PLAN_MULTIMEDIA_RED_EXCEDIDO);
	}

	/**
	 * @return the planMultimediaRedExcedidoTodoDestino
	 */
	public static int getPlanMultimediaRedExcedidoTodoDestino() {
		return Integer.parseInt(PLAN_MULTIMEDIA_RED_EXCEDIDO_TODO_DESTINO);
	}	
	

	/**
	 * @return the planMultimediaRedExcedidoTodoDestinoEmpresas
	 */
	public static int getPlanMultimediaRedExcedidoTodoDestinoEmpresas() {
		return Integer.parseInt(PLAN_MULTIMEDIA_RED_EXCEDIDO_TODO_DESTINO_EMPRESAS);
	}
	/*
	 * Nuevo Plan Empresa - Estructura Tarifaria
	 */
	public static int getPLAN_EMPRESA() {
		return Integer.parseInt(PLAN_EMPRESA);
	}	

	/**
	 * Agrupa los planes por tipo con sus respectivas tasaciones.
	 * 
	 * @param listPlanes
	 * @return
	 */
	 public static List<GrupoPlanBean> buildGruposPlan(List<PlanBean> listPlanes, String codigoPlanActual){
        List<GrupoPlanBean> listGrupoPlan = new ArrayList<GrupoPlanBean>();
        
        // Tipos de Plan.
        List<TipoPlanBean> tiposPlanes = ParametrosHelper.getListTiposPlan();
        String nombreTasacion = "";
        GrupoPlanBean grupoPlan;
        List<PlanBean> listTemp;
        for (TipoPlanBean tipoPlanBean : tiposPlanes) {
        	grupoPlan = new GrupoPlanBean();
        	grupoPlan.setTipoPlan(Integer.parseInt(tipoPlanBean.getId()));
        	grupoPlan.setMercadoPlan(tipoPlanBean.getMercado());
        	grupoPlan.setNombrePlan(tipoPlanBean.getNombre());
        	grupoPlan.setNombrePlanSinAcentos(Utils.removerAcentos(tipoPlanBean.getNombre()));        	
        	grupoPlan.setDescPlan(tipoPlanBean.getDescripcion());
            listTemp = new ArrayList<PlanBean>();
            Iterator<PlanBean> itetator = listPlanes.iterator();
            while (itetator.hasNext()) {
                PlanBean plan = itetator.next();
                // Filtro para que no me agregue a la lista de planes disponibles, mi plan actual.
                if(!plan.getCodbscs2().contains(codigoPlanActual)){
	                if ( grupoPlan.getTipoPlan() == Integer.parseInt(plan.getTipoPlan()) && (tipoPlanBean.getMercado().contains(plan.getTipoMercado())) ) {
	                	listTemp.add(plan);	
	                }
                }
               
            }
            // Se ordena la lista de los planes disponibles por coddbsc2
            Collections.sort(listTemp); 
            
            grupoPlan.setPlanesDisponibles(listTemp);
            // Si el grupo tiene planes, se agregan para ser visualizadas.
            if (!listTemp.isEmpty()) {
            	listGrupoPlan.add(grupoPlan);
            }
        }
        
        // Se agregan los nombres a las tasaciones para cada grupo de plan deacuerdo a los especificados en el properties
        Iterator<GrupoPlanBean> iter = listGrupoPlan.iterator();
        while (iter.hasNext()) {
        	GrupoPlanBean grupos = iter.next();
        	
        	 Iterator<PlanBean> iterPlan= grupos.getPlanesDisponibles().iterator();
        	
        	 while (iterPlan.hasNext()) {
        		 PlanBean planes = iterPlan.next();
        		 
        		     Iterator<TasacionBean> iterTasacion = planes.getListaTasaciones().iterator();
        		     
        		     while (iterTasacion.hasNext()) {
        		    	 TasacionBean tasaciones = iterTasacion.next();
        		    	 
        		    	 try{
        		    	    nombreTasacion = MiEntelProperties.getProperty("parametros.plan.nombreTasacion."+planes.getTipoPlan()+"."+(tasaciones.getNombre()).replace(" ", ""));
        		    	 }catch (Exception e) {
        		    		 nombreTasacion =  	tasaciones.getNombre();
        		    	 } 
        		    	 
        		    	 grupos.add(nombreTasacion);
        		     }
        		     
        		 break;    
        	 }
        }

        return listGrupoPlan;
    }
    
    
   /**
	 * Metodo que recibe la descripcion del plan actual del usuario para construir de forma 
	 * correcta la visualizacion de la glosa.
	 * @param descripcionPlan
	 * @return
	 */
	public static  ArrayList<String> construirGlosaPlan(String descripcionPlan){
		
		ArrayList<String> list = new ArrayList<String>();

       Pattern pattern = Pattern.compile("\\.\\s[A-Z]");
       Matcher matcher = pattern.matcher(descripcionPlan);

       boolean found = false;
       int ini = 0;
       int fin = 0;
       while (matcher.find()) {
           fin = matcher.start();
           list.add(descripcionPlan.substring(ini, fin ));
           ini = matcher.end() - 1;
           found = true;
       }
       
       if(found){
            list.add(descripcionPlan.substring(ini, descripcionPlan.length() - 1 ));
       }
       
       if(!found){
       	list.add("-");
       }

       return list;
	}
	
	
	/**
	 * Metodo que retorna el nombre del plan luego de haber extraido el codigo del mismo
	 * ya que el servicio devuelve concatenado el codigo y nombre del plan
	 * 
	 * @param nombrePlan
	 * @return
	 */
	public static String extraerNombrePlan(String nombrePlan){
		String nombrePlanStr = "";
		 Matcher matcher = Pattern.compile("([a-zA-Z].+)").matcher(nombrePlan);
        if (matcher.find()) {
       	 nombrePlanStr = matcher.group(1);
        }
		
		return nombrePlanStr;
	}
	
	/**
	 * Metodo que retorna el codigo del plan extraido del nombre del mismo
	 * ya que el servicio devuelve concatenado el codigo y nombre del plan
	 * 
	 * @param nombrePlan
	 * @return
	 */
	public static String extraerCodigoNombrePlan(String nombrePlan){
		String nombrePlanStr = "";
		 Matcher matcher = Pattern.compile("([0-9]+)").matcher(nombrePlan);
        if (matcher.find()) {
       	 nombrePlanStr = matcher.group(1);
        }
		
		return nombrePlanStr;
	}

	/**
	 * Metodo que construye el detalle de las tasaciones para SS y CC.
	 * 
	 * @param planActual
	 * @param tipoPlan
	 * @param tipoTasacion
	 * @return
	 * @throws Exception
	 */
   public static List<TasacionBean> construirDetalleTasacionesPlanSSCC(List<DetallePlanActualType> listDetalle, String tipoPlan, String tipoTasacion) throws Exception{
		
		List<TasacionBean> listaTasaciones = new ArrayList<TasacionBean>();
		TasacionBean tasacionBean;
		
		
		/* Si tipo de plan es 3 - Tarifa Unica Frecuente */
		if(tipoPlan.equals(PLAN_TARIFA_UNICA_FREC)){
			
			DetallePlanActualType e = new DetallePlanActualType();
			e.setNombreTasacion(PLAN_NOMBRETASACION_MINFRE);
			e.setValorTasacion(PLAN_MINUTOS_FRECUENTE);
			listDetalle.add(e);
		}

		for(DetallePlanActualType detalle : listDetalle){
			tasacionBean = new TasacionBean();
			tasacionBean.setNombre(definirNombreTasacion(detalle.getNombreTasacion(), tipoPlan));
			
			if(tipoPlan.equals(PLAN_FAMILIA) && 
			        (( detalle.getNombreTasacion().equals(TASACION_DBOL) && validarDatoDouble(detalle.getValorTasacion()) < Integer.parseInt(PLAN_LIMITESEGUNDOS_LIMITADO) ) ||
			        detalle.getNombreTasacion().equals(TASACION_MBOL) || 
			        detalle.getNombreTasacion().equals(TASACION_VBOL) )){
				tasacionBean.setValor(detalle.getValorTasacion());
			}else if (tipoPlan.equals(PLAN_GRUPAL_EMPRESAS_SS)) {
				String valor = convertirValorTasacion(detalle.getValorTasacion());				
				tasacionBean.setValor(valor);
				tasacionBean.setValorNumerico(Long.parseLong(valor));
			} else {
				tasacionBean.setValor(convertirValorTasacion(detalle.getValorTasacion()));				
			}
			tasacionBean.setTipo(tipoTasacion);
			tasacionBean.setUnidad("segundo");
			
			listaTasaciones.add(tasacionBean);
		}
		return listaTasaciones;
	}
   
   
   /**
    * Metodo que construye el detalle de las tasaciones para los planes SS y CC.
    * 
    * @param list
    * @param tipoPlan
    * @param tipoTasacion
    * @return
    * @throws Exception
    */
   public static List<TasacionBean> construirDetalleTasacionesPlanTraficoSSCC(List<com.epcs.billing.registrouso.types.DetallePlanActualType> listDetalle, String tipoPlan, String tipoTasacion) throws Exception{
		
	   List<TasacionBean> listaTasaciones = new ArrayList<TasacionBean>();
		TasacionBean tasacionBean;
		
		
		/* Si tipo de plan es 3 - Tarifa Unica Frecuente */
		if(tipoPlan.equals(PLAN_TARIFA_UNICA_FREC)){
			
			com.epcs.billing.registrouso.types.DetallePlanActualType e = new com.epcs.billing.registrouso.types.DetallePlanActualType();
			e.setNombreTasacion(PLAN_NOMBRETASACION_MINFRE);
			e.setValorTasacion(PLAN_MINUTOS_FRECUENTE);
			listDetalle.add(e);
		}

		for(com.epcs.billing.registrouso.types.DetallePlanActualType detalle : listDetalle){
			tasacionBean = new TasacionBean();
			tasacionBean.setNombre(definirNombreTasacion(detalle.getNombreTasacion(), tipoPlan));
			
			if(tipoPlan.equals(PLAN_FAMILIA) && 
			        (( detalle.getNombreTasacion().equals(TASACION_DBOL) && validarDatoDouble(detalle.getValorTasacion()) < Integer.parseInt(PLAN_LIMITESEGUNDOS_LIMITADO) ) ||
			        detalle.getNombreTasacion().equals(TASACION_MBOL) || 
			        detalle.getNombreTasacion().equals(TASACION_VBOL) )){
				tasacionBean.setValor(detalle.getValorTasacion());
			}else if (tipoPlan.equals(PLAN_GRUPAL_EMPRESAS_SS)) {
				String valor = convertirValorTasacion(detalle.getValorTasacion());
				tasacionBean.setValor(valor);
				tasacionBean.setValorNumerico(Long.parseLong(valor));
			} else {
				tasacionBean.setValor(convertirValorTasacion(detalle.getValorTasacion()));
			}
			tasacionBean.setTipo(tipoTasacion);
			tasacionBean.setUnidad("segundo");
			
			listaTasaciones.add(tasacionBean);
		}
		return listaTasaciones;
	}
   
   
	 
	 /**
	  * Metodo que valida los segundos de la tasacion y convierte el valor de minutos a segundos
	  * @param valorTasacion
	  * @return
	  */
	 public static String convertirValorTasacion(String valorTasacion){
		 String valorRespuesta = "";
		 try{
			 if( ( validarDatoDouble(valorTasacion) * 60) >= Integer.parseInt(PLAN_LIMITESEGUNDOS_LIMITADO)){
				 valorRespuesta = PLAN_TOTALMINUTOS_LIMITADO;
			 }else{
				 valorRespuesta = Long.toString( (Math.round(validarDatoDouble(valorTasacion)*60)) );
			 }
		 }catch (Exception e) {
			 return "-";
		}
		 
		 return valorRespuesta;
	 }

	
	
	/**
	 * Metodo que valida que convierte de segundos a minutos.
	 * 
	 * @param value
	 * @return
	 */
	public static String convertirSegundosaMinutos(String value){
	  	 String valueRetorno = null;
		
	  	 try{ 
	  		if(!value.equals(FLAG_HIDDEN)){
	  			valueRetorno = Integer.toString((Integer.parseInt(value)/60));
	  		}else{
	  			valueRetorno = value;
	  		}
	  	 }catch (Exception e) {
	  		 return "-"; 	 
		 }
	  	 return valueRetorno;
	   }
	
	
   
   /**
    * Validacion y conversion de dato que proviene del servicio a double.
    * 
    * @param value
    * @return
    */
   public static Double validarDatoDouble(String value){
  	 try{
  		 return Double.parseDouble(value);
  	 }catch (Exception e) {
  		 return new Double(0);
		}
   }
   
   
   /**
    * Metodo utilitario que obtiene el nombre de la tasacion del properties
    * 
    * @param nombreTasacion
    * @return
    */
	public static String definirNombreTasacion(String nombreTasacion,
			String tipoPlan) {
		String nomTasacion = "";

		try {
			nomTasacion = MiEntelProperties
					.getProperty("parametros.plan.nombreTasacion." + tipoPlan
							+ "." + (nombreTasacion).replace(" ", ""));
		} catch (Exception e) {
			nomTasacion = nombreTasacion;
		}

		return nomTasacion;
	}
   
   
   /**
    * Metodo utilitario que obtiene de un properties la breve descripcion del plan
    * deacuerdo el tipo de plan y mercado.
    * 
    * @param planActual
    * @return
    */
   public static String obtenerBreveDescripcionPlan(PlanBean planActual){
	   
	   String breveDescripcion = "";
	    // Tipos de Plan.
       List<TipoPlanBean> tiposPlanes = ParametrosHelper.getListTiposPlan();
       for (TipoPlanBean tipoPlanBean : tiposPlanes) {
           if ( tipoPlanBean.getId().equals((planActual.getTipoPlan())) && (tipoPlanBean.getMercado().equals(planActual.getTipoMercado())) ) {
        	   breveDescripcion = tipoPlanBean.getDescripcion();
           }
           
       }
	   return breveDescripcion;
   }
   
   /**
    * 
    * @param data
    * @return
    */
   public static String calculateValorTraficado (String data) {
       StringBuffer traficoString = new StringBuffer();
       Float dataParse = Float.parseFloat(data);
       try{

	       if (dataParse < 1) { // La unidad son KB
	           dataParse = dataParse * 1024;
	
	           traficoString.append(dataParse.intValue());
	           traficoString.append(" KB");
	       }

	       if (dataParse < 1024 && dataParse >= 1) { // La unidad son MB
	           traficoString.append(dataParse.intValue());
	           traficoString.append(" MB");
	       }

	       if (dataParse >= 1024) { // La unidad son GB
	           dataParse = dataParse / 1024;
	
	           DecimalFormat unDecimal = new DecimalFormat("#.#");
	           traficoString.append(unDecimal.format(dataParse));
	           traficoString.append(" GB");
	       }
	       
       }catch (Exception e) {
			LOGGER.error("Exception al intentar la cantidad traficada en un mes calendario utilizada " +
					"en la unidad adecuada",e);
	   } 	 
       
       return traficoString.toString();
       
   }
   
   /**
    * Metodo utilitario que verifica si <code>codbscs2</code> identifica a un plan tarifa unica con SMS
    * 
    * @param codbscs2
    * @return valor tipo <code>boolean</code> con el resultado de la validacion
    */
   public static boolean isPlanTarifaUnicaConSMS(String codbscs2){
	   if(ParametrosHelper.getExisteParametro("planes.tarifaunica.sms", codbscs2))
		   return true;
	   return false;
   }
   
   /**
    * Metodo utilitario que verifica si <code>codbscs2</code> identifica a un plan tarifa unica con SMS
    * 
    * @param codbscs2
    * @return valor tipo <code>boolean</code> con el resultado de la validacion
    */
   public static boolean isPlanBberryMMTodoDestino(String codbscs2){
	   if(ParametrosHelper.getExisteParametro("planes.bberry.mm.tododestino", codbscs2))
		   return true;
	   return false;
   }
   
   /**
    * Metodo utilitario que verifica si <code>codbscs2</code> identifica a un plan tarifa unica con SMS
    * 
    * @param codbscs2
    * @return valor tipo <code>boolean</code> con el resultado de la validacion
    */
   public static boolean isPlanMMCuentaControlada(String codbscs2){
	   if(ParametrosHelper.getExisteParametro("planes.mm.cc", codbscs2))
		   return true;
	   return false;
   }
   
   
   /**
    * Metodo utilitario que verifica si <code>codbscs2</code> identifica a un plan tarifa unica con SMS
    * 
    * @param codbscs2
    * @return valor tipo <code>boolean</code> con el resultado de la validacion
    */
   public static boolean isPlanBBMMCuentaControlada(String codbscs2){
	   if(ParametrosHelper.getExisteParametro("planes.bberry.mm.cc", codbscs2))
		   return true;
	   return false;
   }
   
   /**
    * Metodo utilitario que verifica si <code>codbscs2</code> identifica a un plan tarifa unica con SMS
    * 
    * @param codbscs2
    * @return valor tipo <code>boolean</code> con el resultado de la validacion
    */
   public static boolean isPlanBBNoMM(String codbscs2){
	   if(ParametrosHelper.getExisteParametro("planes.bberry.no.mm", codbscs2))
		   return true;
	   return false;
   } 
   
   
   
   /**
    * Metodo utilitario que verifica si <code>codbscs2</code> identifica a un plan tarifa unica con SMS
    * 
    * @param codbscs2
    * @return valor tipo <code>boolean</code> con el resultado de la validacion
    */
   public static boolean isPlanMMediaRed(String codbscs2){
	   if(ParametrosHelper.getExisteParametro("planes.mm.cc.red", codbscs2))
		   return true;
	   return false;
   }
   
   /**
    * Metodo utilitario que verifica si <code>codbscs2</code> identifica a un plan tarifa unica con SMS
    * 
    * @param codbscs2
    * @return valor tipo <code>boolean</code> con el resultado de la validacion
    */
   public static boolean isPlanMMultimediaCControladaTodoDestido(String codbscs2){
	   if(ParametrosHelper.getExisteParametro("planes.mm.cc.td", codbscs2))
		   return true;
	   return false;
   }   
   
   
   /**
    * Metodo utilitario que verifica si <code>codbscs2</code> identifica a un plan multimedia red excedido
    * 
    * @param codbscs2
    * @return valor tipo <code>boolean</code> con el resultado de la validacion
    */
   public static boolean isPlanMultimediaRedExcedido(String codbscs2){
	   if(ParametrosHelper.getExisteParametro("planes.mm.ss.red.exc", codbscs2))
		   return true;
	   return false;
   }   
   
   
   /**
    * Metodo utilitario que verifica si <code>codbscs2</code> identifica a un plan multimedia red excedido todo destino
    * 
    * @param codbscs2
    * @return valor tipo <code>boolean</code> con el resultado de la validacion
    */
   public static boolean isPlanMultimediaRedExcedidoTodoDestino(String codbscs2){
	   if(ParametrosHelper.getExisteParametro("planes.mm.ss.red.exc.td", codbscs2))
		   return true;
	   return false;
   }   
   
   
   /**
    * Metodo utilitario que verifica si <code>codbscs2</code> identifica a un plan multimedia red excedido todo destino Empresas
    * 
    * @param codbscs2
    * @return valor tipo <code>boolean</code> con el resultado de la validacion
    */
   public static boolean isPlanMultimediaRedExcedidoTodoDestinoEmpresas(String codbscs2){
	   if(ParametrosHelper.getExisteParametro("planes.mm.ss.red.exc.td.emp", codbscs2))
		   return true;
	   return false;
   }   
   
   
   /**
    * Metodo utilitario que ordena una lista de ofertas.
    * 
    * @param oferta
    * @return valor tipo <code>List<Oferta></code> con el resultado del ordenamiento
    */
   public static List<Oferta> sortOfertasMain(List<Oferta> oferta){
		List<Oferta> finalListOferta = new ArrayList<Oferta>();
		
		finalListOferta = ordenarGrupo(finalListOferta,oferta,"G4");
		//finalListOferta = ordenarGrupo(finalListOferta,oferta,"G1");
		finalListOferta = ordenarGrupo(finalListOferta,oferta,"G10");
		finalListOferta = ordenarGrupo(finalListOferta,oferta,"G6");
		//finalListOferta = ordenarGrupo(finalListOferta,oferta,"G5");
		//finalListOferta = ordenarGrupo(finalListOferta,oferta,"G3");
		finalListOferta = ordenarGrupo(finalListOferta,oferta,"G7");	
		//finalListOferta = ordenarGrupo(finalListOferta,oferta,"G2");
		
		return finalListOferta;
		
		
		}
   
   /**
    * Metodo utilitario que ordena una lista de ofertas NBA.
    * 
    * @param oferta
    * @return valor tipo <code>List<Oferta></code> con el resultado del ordenamiento
    */
   public static List<NBA> sortOfertasNBAMain(List<NBA> oferta){
		List<NBA> finalListOferta = new ArrayList<NBA>();
		//ordenar las ofertas de acuerdo al contenido obtenido
    	try {
    		Node grupoOfertas = JSFPortletHelper.getContentNode("idContenido", ID_CONTENIDO_GRUPO_OFERTAS_NBA);
			String ofertas = grupoOfertas.getProperty("html").getValue().getStringValue();
			List<String> listaOfertas = Arrays.asList(ofertas.split(","));
			for (int i = 0; i < listaOfertas.size(); i++) {
				//LOGGER.info("grupo campaña "+listaOfertas.get(i));
				finalListOferta = ordenarGrupoNBA(finalListOferta,oferta,listaOfertas.get(i));
			}
		} catch (Exception e) {
			LOGGER.error(e);
		}
		return finalListOferta;
		
		
		}
   
   /**
    * Metodo utilitario que ordena una lista de ofertas.
    * 
    * @param oferta
    * @return valor tipo <code>List<Oferta></code> con el resultado del ordenamiento
    */
   public static List<Oferta> ordenarGrupo(List<Oferta> sortedOferta, List<Oferta> oferta, String group){
		Oferta auxOferta = new Oferta();
		List<Oferta> finalListOferta = new ArrayList<Oferta>();
		int i = 0;
		
		finalListOferta = sortedOferta;
		
		for(i = 0; i < oferta.size(); i++){
			auxOferta = new Oferta();
			auxOferta = oferta.get(i);
			if((group).equals(auxOferta.getGrupoOferta())){
				finalListOferta.add(auxOferta);
			}
		}
		
		return finalListOferta;
		
		
	}
   
   /**
    * Metodo utilitario que ordena una lista de ofertas segun el listado de tipos recibidos.
    * @param sortedOferta lista ordena
    * @param ofertas
    * @param tipos
    * @return finalListOferta <code>List<Oferta></code> con el resultado del ordenamiento
    */
    public static List<Oferta> seleccionarOfertasPorPrioridadTipo(List<Oferta> sortedOferta, List<Oferta> ofertas, String tipos){
		List<Oferta> finalListOferta = new ArrayList<Oferta>();
		finalListOferta = sortedOferta;
		
		StringTokenizer tiposPlanesBolsas = new StringTokenizer(tipos, ",");
		boolean sw = true;
		while (sw && tiposPlanesBolsas.hasMoreTokens()) {
			String t = tiposPlanesBolsas.nextToken();
		
			for(Oferta o : ofertas){
				if((t).equals(o.getTipoBolsaPlan())){
					finalListOferta.add(o);
					sw = false;
				}
			}
		}
		
		return finalListOferta;
		
	}
   
   
   /**
    * Metodo utilitario que filtra una lista de ofertas por grupo especifico.
    * 
    * @param oferta
    * @return valor tipo <code>List<Oferta></code> con el resultado del ordenamiento
    */
   public static List<NBA> ordenarGrupoNBA(List<NBA> sortedOferta, List<NBA> oferta, String group){
	   NBA auxOferta = new NBA();
		List<NBA> finalListOferta = new ArrayList<NBA>();
		int i = 0;
		
		finalListOferta = sortedOferta;
		
		for(i = 0; i < oferta.size(); i++){
			auxOferta = new NBA();
			auxOferta = oferta.get(i);
			if((group).equals(auxOferta.getGrupo())){
				finalListOferta.add(auxOferta);
			}
		}
		
		return finalListOferta;
	
	
	}
   
   	
   /**
	 * Metodo que realiza las validaciones comerciales para ver si una oferta es mostrada o no
	 * @param oferta
	 * @throws DAOException
	 * @throws ServiceException
	 */
	public boolean validacionesComerciales(Oferta oferta,String mercado, String flagBam, String aaa, String rutTitular, String nroCuenta) throws DAOException, ServiceException {
		String grupo=oferta.getGrupoOferta();
		BolsaDelegate delegateBolsas=new BolsaDelegate();
		delegateBolsas.setBolsaDAO(new BolsaDAO());
		String movilAsociado=oferta.getMovil();
		String codigoOferta=oferta.getOfertaId();
		
		//GRUPO 3
		if(grupo.equalsIgnoreCase("G3")){
			return true;
		}
		//GRUPO 5
		if(grupo.equalsIgnoreCase("G5")){
			return true;
		}
		if(grupo.equalsIgnoreCase("G1") ||  grupo.equalsIgnoreCase("G4")){
			//PARTE AGREGADA EL 25-08-2013
			if(flagBam.equals("1")){
				//es BAM
				boolean logCajaFacturacion = false;
				miTraficoBAMCCDelegate = new TraficoBamCCDelegate();
				miTraficoBAMCCDelegate.setTraficoBamDAO(new TraficoBamDAO());
				planActualBAMSSCC = miTraficoBAMCCDelegate.obtenerPlanActualBAMSSCC(movilAsociado, mercado, aaa, logCajaFacturacion);
		        if(!planActualBAMSSCC.getCodbscs2().equalsIgnoreCase(oferta.getCodBscsOferta())){
		        	if(grupo.equalsIgnoreCase("G1")){
		        		return true;
					}
		        	if(grupo.equalsIgnoreCase("G4")){
						return true;
					}
		        }
		        
			}else{
				LOGGER.info("Validaciones comerciales G4 VOZ");
				PlanDelegate planDelegate=new PlanDelegate();
				planDelegate.setPlanDAO(new PlanDAO());
				PlanBean planActual = planDelegate.getPlanActualSSCC(movilAsociado, mercado, aaa);
				if(!planActual.getCodbscs2().equalsIgnoreCase(oferta.getCodBscsOferta())){
					if(grupo.equalsIgnoreCase("G1")){
						return true;
					}
					if(grupo.equalsIgnoreCase("G4")){
						if (numeroTieneMismoPlan(movilAsociado, codigoOferta, "G4", mercado, aaa)) {
							return true;
						}
					}
				}
			}
			
		}
		if(grupo.equalsIgnoreCase("G6")){
			//nivel 5 - validaciones otros grupos
			if(flagBam.equals("1")){
				//es BAM
				bolsasActivas = bolsasBAMCCPPDelegate.listarBolsasActivas(movilAsociado);
				List<BolsaActivaBAMCCPP> bolsasActivasBam = bolsasActivas.getBolsas();
				if(bolsasActivasBam.size()==0){
					return true;
				}else{
					//hacer validaciones 
					int contOfertas=0;
					for(int i=0;i<bolsasActivasBam.size();i++){
						if(((BolsaActivaBAMCCPP)bolsasActivasBam.get(i)).getCodigo().equalsIgnoreCase(oferta.getCodBscsOferta())){
							contOfertas++;
						}
					}
					if(contOfertas==0){
						return true;
					}
				}
			}else{
				LOGGER.info("Validaciones comerciales G6 VOZ");
				//CC SS
				//listaBolsaPPBean=delegateBolsas.obtenerBolsasScob(movilAsociado);
				
				SuscripcionesDelegate suscripcionesDelegate = new SuscripcionesDelegate();
				suscripcionesDelegate.setSuscripcionesBolsaDAO(new SuscripcionesBolsaDAO());
				BolsasActualesDisponiblesBean listaBolsaPPBean2 = suscripcionesDelegate.consultarBolsasActualesDisponibles(movilAsociado);				

				
				//if(listaBolsaPPBean.size()==0){
				if(listaBolsaPPBean2.getBolsasActuales() == null || listaBolsaPPBean2.getBolsasActuales().size()==0 ){
					if (numeroTieneMismoPlan(movilAsociado, codigoOferta, "G6", mercado, aaa) && numeroTieneSaldoSuficiente(movilAsociado, oferta.getCodBscsOferta(), mercado, aaa)) {
						return true;
					}
				}else{
					//hacer validaciones 
					int contOfertas=0;
					/*
					for(int i=0;i<listaBolsaPPBean.size();i++){
						if(((BolsaPPBean)listaBolsaPPBean.get(i)).getCodBolsa().equalsIgnoreCase(oferta.getCodBscsOferta())){
							contOfertas++;
						}
					}
					*/
					for(int i=0;i<listaBolsaPPBean2.getBolsasActuales().size();i++){
						if(((BolsaBean)listaBolsaPPBean2.getBolsasActuales().get(i)).getSnCodigo().equalsIgnoreCase(oferta.getCodBscsOferta())){
							contOfertas++;
						}
					}
					
					if(contOfertas==0){
						if (numeroTieneMismoPlan(movilAsociado, codigoOferta, "G6", mercado, aaa) && numeroTieneSaldoSuficiente(movilAsociado, oferta.getCodBscsOferta(), mercado, aaa)) {
							return true;
						}
					}
				}
			}
		}
		if(grupo.equalsIgnoreCase("G7")){
			//nivel 5 - validaciones grupo 7
			facturacionElectronicaDelegate =new FacturacionElectronicaDelegate();
			facturacionElectronicaDelegate.setFacturacionElectronicaDAO(new FacturacionElectronicaDAO());
			
	        	if(!facturacionElectronicaDelegate.getFacturacionElectronicaEstado(rutTitular, nroCuenta)){
	        		return true;
	        	}
		}
		if(grupo.equalsIgnoreCase("G10")){
			//nivel 5 - validaciones grupo 10
			return true;
		}
		
		return false;
	}
	
	/**
	 * metodo sobrecargado de validacionesComerciales utilizado SOLO para campañas G4 y G6 VOZ para el banner de bolsas y planes
	 * @param oferta
	 * @param movil
	 * @param mercado
	 * @param aaa
	 * @return true si pasa validaciones, false en caso contrario
	 * @throws DAOException
	 * @throws ServiceException
	 */
	public boolean validacionesComerciales(Oferta oferta, String movil, String mercado,String aaa)throws DAOException, ServiceException {
		String grupo=oferta.getGrupoOferta();
		String codigoOferta=oferta.getOfertaId();
		if(grupo.equalsIgnoreCase("G4")){
			PlanDelegate planDelegate=new PlanDelegate();
			planDelegate.setPlanDAO(new PlanDAO());
			PlanBean planActual = planDelegate.getPlanActualSSCC(movil, mercado, aaa);
			if(!planActual.getCodbscs2().equalsIgnoreCase(oferta.getCodBscsOferta())){
				if (numeroTieneMismoPlan(movil, codigoOferta, "G4", mercado, aaa)) {
					return true;
				}
			}
		}
		if(grupo.equalsIgnoreCase("G6")){
			SuscripcionesDelegate suscripcionesDelegate = new SuscripcionesDelegate();
			suscripcionesDelegate.setSuscripcionesBolsaDAO(new SuscripcionesBolsaDAO());
			BolsasActualesDisponiblesBean listaBolsaPPBean2 = suscripcionesDelegate.consultarBolsasActualesDisponibles(movil);				
			if(listaBolsaPPBean2.getBolsasActuales() == null || listaBolsaPPBean2.getBolsasActuales().size()==0 ){
				if (this.numeroTieneMismoPlan(movil, codigoOferta, "G6", mercado, aaa) && this.numeroTieneSaldoSuficiente(movil, oferta.getCodBscsOferta(), mercado, aaa)) {
					return true;
				}
			}else{
				int contOfertas=0;
				for(int i=0;i<listaBolsaPPBean2.getBolsasActuales().size();i++){
					if(((BolsaBean)listaBolsaPPBean2.getBolsasActuales().get(i)).getSnCodigo().equalsIgnoreCase(oferta.getCodBscsOferta())){
						contOfertas++;
					}
				}
				if(contOfertas==0){
					if (this.numeroTieneMismoPlan(movil, codigoOferta, "G6", mercado, aaa) && this.numeroTieneSaldoSuficiente(movil, oferta.getCodBscsOferta(),mercado,aaa)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	
	public boolean numeroTieneMismoPlan(String movil, String codigoOferta, String tipoOferta, String mercado, String aaa) {
		
		LOGGER.info("Validando cambio de plan... movil: " + movil + ", mercado: " + mercado + ", aaa: " + aaa + ", codigoOferta: " + codigoOferta + ", tipoOferta: " + tipoOferta);
		
		boolean mismoPlan = false;
		
		try {			

			// Obtiene plan original, cargado en la baseIT
			ofertaBlindajeDelegate =new OfertaBlindajeDelegate();
			String planOriginalNumero = ofertaBlindajeDelegate.obtenerPlanOriginalNumero(codigoOferta, tipoOferta);						
			LOGGER.info("Plan original numero: " + planOriginalNumero);
			
			// Obtiene plan actual
			PlanDelegate planDelegate=new PlanDelegate();
			planDelegate.setPlanDAO(new PlanDAO());
			PlanBean planActual = planDelegate.getPlanActualSSCC(movil, mercado, aaa);			
			String planActualNumero = planActual.getCodbscs2();
			LOGGER.info("Codigo plan actual: " + planActualNumero);
			
			// verifica cambio
			if (planOriginalNumero.equals(planActualNumero)) {				
				LOGGER.info("Plan del numero NO ha sido cambiado");
				mismoPlan = true;
			} else {
				LOGGER.info("Plan del numero fue cambiado");
			}
						
		} catch (Exception e) {
			LOGGER.error("Excepcion al validar cambio de plan...",e);
		}
				
		return mismoPlan;
	}
	
	
public boolean numeroTieneSaldoSuficiente(String movil, String codigoOferta, String mercado, String aaa) {
		
		LOGGER.info("Validando saldo disponible para Bolsa... movil: " + movil + ", mercado: " + mercado + ", aaa: " + aaa + ", codigoOferta: " + codigoOferta);
		
		boolean tieneSaldo = false;
		
		if (mercado.equalsIgnoreCase("CC")) {
			try {			
	
				// Obtiene saldo actual
				ResumenPlan resumenPlan = new ResumenPlan();			
				PlanDelegate planDelegate=new PlanDelegate();
				planDelegate.setPlanDAO(new PlanDAO());
				resumenPlan = planDelegate.getPlanResumenCC(movil, aaa);
				Double saldoActual = resumenPlan.getSaldo();
				LOGGER.info("SaldoActual movil " + movil + ": " + saldoActual.toString());
				
				// Obtiene valor de la bolsa
				ofertaBlindajeDelegate =new OfertaBlindajeDelegate();
				String datosBolsa[]=ofertaBlindajeDelegate.ConsultarOfertaBolsaNBA(codigoOferta);
				
				if(datosBolsa[1]!=null){
					LOGGER.info("Valor de la bolsa ofertada " + codigoOferta + ": " + datosBolsa[1]);
					Double valorBolsa = Double.parseDouble(datosBolsa[1]);
		            if(saldoActual <= 7 || saldoActual > valorBolsa){
		            	LOGGER.info("Movil tiene saldo suficiente para la bolsa");
		            	tieneSaldo = true;
		            } else {
		            	LOGGER.info("Movil NO tiene saldo suficiente para la bolsa");
		            }
				} else {
					LOGGER.error("Valor de bolsa nulo, es posible que no exista en la BD");
				}
			
				
			} catch (Exception e) {
				LOGGER.error("Excepcion al validar saldo del numero...",e);
			}
		} else {
			LOGGER.info("Numero no es CC, se omite validacion");
			tieneSaldo = true;
		}
				
		return tieneSaldo;
	}	
   

}
