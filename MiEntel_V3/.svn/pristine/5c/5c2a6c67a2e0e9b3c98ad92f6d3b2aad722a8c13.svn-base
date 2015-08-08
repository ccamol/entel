/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.cliente.perfil.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.epcs.bean.GrupoPlanBAMBean;
import com.epcs.bean.PlanBAMBean;
import com.epcs.bean.TipoPlanBean;
import com.epcs.cliente.perfil.types.DetallePlanActualType;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.ParametrosHelper;
import com.epcs.recursoti.configuracion.Utils;

/**
 * @author gcastro (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class PlanBAMHelper {
	
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
     * Referencia al id Plan Multimedia Red existente en aplicacion.properties
     */
    private static final String PLAN_MULTIMEDIA_RED = MiEntelProperties
            .getProperty("parametros.tipoplan.mulred.id");
    
    
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
	 * Referencia al id del plan Comunik2 existente en aplicacion.properties
	 */
	private static final String PLAN_COMUNIK2_ID = MiEntelProperties
    .getProperty("plan.comunikd2.id");
	
    

    /**
     * 
     * @return id plan tarifa plana ss
     */
	public static int getPlanTarifaPlanaSs() {
		return Integer.parseInt(PLAN_TARIFA_PLANA_SS);
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
	 * @return id plan multimedia red
	 */
	public static int getPlanMultimediaRed() {
		return Integer.parseInt(PLAN_MULTIMEDIA_RED);
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
	 * Agrupa los planes por tipo con sus respectivas tasaciones.
	 * 
	 * @param listPlanes
	 * @return
	 */
	 public static List<GrupoPlanBAMBean> buildGruposPlan(final List<PlanBAMBean> listPlanes, final String codigoPlanActual){
        List<GrupoPlanBAMBean> listGrupoPlan = new ArrayList<GrupoPlanBAMBean>();
        // Tipos de Plan.
        List<TipoPlanBean> tiposPlanes = ParametrosHelper.getListTiposPlanBam();
        GrupoPlanBAMBean grupoPlan;
        List<PlanBAMBean> listTemp;
        for (TipoPlanBean tipoPlanBean : tiposPlanes) {
        	grupoPlan = new GrupoPlanBAMBean();
        	grupoPlan.setTipoPlan(Integer.parseInt(tipoPlanBean.getId()));
        	grupoPlan.setMercadoPlan(tipoPlanBean.getMercado());
        	grupoPlan.setNombrePlan(tipoPlanBean.getNombre());
        	grupoPlan.setNombrePlanSinAcentos(Utils.removerAcentos(tipoPlanBean.getNombre()));    
        	grupoPlan.setDescPlan(tipoPlanBean.getDescripcion());
            listTemp = new ArrayList<PlanBAMBean>();
            Iterator<PlanBAMBean> itetator = listPlanes.iterator();
            while (itetator.hasNext()) {
                PlanBAMBean plan = itetator.next();
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
        
      return listGrupoPlan;
    }
    
    
   /**
	 * Metodo que recibe la descripcion del plan actual del usuario para construir de forma 
	 * correcta la visualizacion de la glosa.
	 * @param descripcionPlan
	 * @return
	 */
	public static  List<String> construirGlosaPlan(final String descripcionPlan){
		
		List<String> list = new ArrayList<String>();

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
    * Metodo utilitario que obtiene de un properties la breve descripcion del plan
    * deacuerdo el tipo de plan y mercado.
    * 
    * @param planActual
    * @return
    */
   public static String obtenerBreveDescripcionPlan(PlanBAMBean planActual){
	   
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
    * Metodo utilitario que obtiene el valor de una tasacion de un plan bam
    * @param List<DetallePlanActualType>
    * @param nombreTasacion
    * @return
    */
   public static String obtenerTasacionPlanBAM(List<DetallePlanActualType> detallePlanActual, String nombreTasacion){
       
       String valorTasacion = "";
       
       for (DetallePlanActualType detalle : detallePlanActual) {
           if (nombreTasacion.equalsIgnoreCase(detalle.getNombreTasacion())) {
               valorTasacion = detalle.getValorTasacion();
               if(MiEntelProperties.getProperty("parametros.planesbam.ilimitado")
            		   .equalsIgnoreCase(valorTasacion)){
            	   valorTasacion = MiEntelProperties.getProperty("parametros.planesbam.ilimitadoLabel");
               }
           }
           
       }
       return valorTasacion;
   }
}
