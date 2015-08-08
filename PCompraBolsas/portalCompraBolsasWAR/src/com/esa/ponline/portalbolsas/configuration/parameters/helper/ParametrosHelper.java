/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.esa.ponline.portalbolsas.configuration.parameters.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.MissingResourceException;

import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;

import com.esa.ponline.portalbolsas.bean.CodeDescBean;
import com.esa.ponline.portalbolsas.bean.MontoRecargaBean;
import com.esa.ponline.portalbolsas.bean.MultitiendaBean;
import com.esa.ponline.portalbolsas.configuration.properties.LoadProperty;
import com.esa.ponline.portalbolsas.util.PCBUtils;

/**
 * Helper para la obtencion de listas de parametros, tales como
 * listas de valores para formularios de Usuarios, y cuentas
 * 
 * @author ccastro (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class ParametrosHelper {

    private static final Logger LOGGER = Logger.getLogger(ParametrosHelper.class);
    
    private static String montosRecargaTarjetaCredito;

    private static final String ESTADO_CIVIL_PROPERTY = "estadoCivil";

    private static final String NIVEL_ESTUDIOS_PROPERTY = "nivelEstudios";

    private static final String ACTIVIDAD_LABORAL_PROPERTY = "actividad";

    private static final String AREA_LABORAL_PROPERTY = "areaLaboral";

    private static final String RELACION_TITULAR_PROPERTY = "relacionTitular";
    
    private static final String SEXO_PROPERTY = "sexo";
    
    private static final String AREA_TELEFONICA_PROPERTY = "areaTelefonica";
    
    private static final String HIJO_PROPERTY = "hijo";
    
    private static final String TIPO_TARJETA = "tipotarjeta";

    private static final String MULTITIENDAS_PROPERTY = "multitienda";
    
    private static final String TIPO_BOLSA = "tipobolsa";
    
    private static final String TIPO_BOLSA_PP = "tipobolsaPP";
    
    @SuppressWarnings("unused")
    private static final String TIPO_PLAN = "tipoplan";

    @SuppressWarnings("unused")
    private static final String TIPO_PLAN_BAM = "tipoplanbam";
        
    @SuppressWarnings("unused")
    private static final String PREFIJO_REDFIJA = "numeroPrefijo.redfija.prefijos";
    
    private static final String PERIODO_HISTORIAL = "zonaEntel.historialCanje.listado.verHistorial";
    
    private static final String TIPO_HISTORIAL = "zonaEntel.historialCanje.listado.tipo";    
    
    private static final String PREFIJO_TELEFONO = "prefijoTelefono.telefonoAdicional.prefijos";
    
    private static final String TIPO_BOLSAS_MAS_VENDIDAS = "parametros.bolsasSCOBPP.tipobolsaMasVendidas";
    
    private static final String PRODUCTOS_RECARGA = "productosRecarga";
    
    /**
     * Entrega la lista de Estados Civiles
     * 
     * @return List<CodeDescBean> lista de estados civiles
     */
    public static List<CodeDescBean> getEstadosCivilList() {
        List<CodeDescBean> estadosCivilList = getParametrosList(ESTADO_CIVIL_PROPERTY);
        return estadosCivilList;
    }

    /**
     * Entrega lista de niveles de estudios
     * 
     * @return List<CodeDescBean> lista de niveles de estudios
     */
    public static List<CodeDescBean> getNivelEstudiosList() {
        List<CodeDescBean> nivelEstudiosList = getParametrosList(NIVEL_ESTUDIOS_PROPERTY);
        return nivelEstudiosList;
    }

    /**
     * Entrega lista de actividades laborales
     * 
     * @return List<CodeDescBean> lista de actividades laborales
     */
    public static List<CodeDescBean> getActividadLaboralList() {
        List<CodeDescBean> actividaLabralList = getParametrosList(ACTIVIDAD_LABORAL_PROPERTY);
        return actividaLabralList;
    }

    /**
     * Entrega lista de areas laborales
     * 
     * @return List<CodeDescBean> lista de areas laborales
     */
    public static List<CodeDescBean> getAreaLaboralList() {
        List<CodeDescBean> areaLaboralList = getParametrosList(AREA_LABORAL_PROPERTY);
        return areaLaboralList;
    }

    /**
     * Entrega lista de relaciones con el titular
     * 
     * @return List<CodeDescBean> lista de relaciones con el titular
     */
    public static List<CodeDescBean> getRelacionesTitularList() {
        List<CodeDescBean> relacionTitularList = getParametrosList(RELACION_TITULAR_PROPERTY);
        return relacionTitularList;
    }
    
    
    /**
     * Entrega lista de sexos
     * 
     * @return List<CodeDescBean> lista de sexos
     */
    public static List<CodeDescBean> getSexoList() {
        List<CodeDescBean> sexoList = getParametrosList(SEXO_PROPERTY);
        return sexoList;
     }
    
    public static List<CodeDescBean> getAreaTelefonicaList(){
    	List<CodeDescBean> areaList = getParametrosList(AREA_TELEFONICA_PROPERTY);
    	return areaList;
    }
    
    /**
     * Entrega lista de tiene hijos
     * 
     * @return List<CodeDescBean> lista de tene hijos
     */
    public static List<CodeDescBean> getHijosList() {
        List<CodeDescBean> hijoList = getParametrosList(HIJO_PROPERTY);
        return hijoList;
    }
    
    public static List<CodeDescBean> getTiposRecargaList() {
        List<CodeDescBean> tipoRecargaList = getParametrosList("tipoRecarga");
        return tipoRecargaList;
    }

    /**
     * Entrega lista de dias de anticipacion para aviso de factura electronica
     * @return
     */
    public static List<CodeDescBean> getDiasAnticipacionFacturaElecList() {
        List<CodeDescBean> tipoRecargaList = getParametrosList("adminServicios.avisoFactElectronica.diasAnticipacion");
        return tipoRecargaList;
    }
    
    /**
     * Entrega la lista de periodos para filtrar el historial de canje
     * @author jivasquez (I2B) en nombre Absalon Opazo (Atencion al Cliente, EntelPcs)
     * @return List<CodeDescBean> lista de periodos
     */
    public static List<CodeDescBean> obtenerPeridosHistorialList() {
        List<CodeDescBean> peridosHistorialList = getParametrosList(PERIODO_HISTORIAL);
        return peridosHistorialList;
    }
    
    /**
     * Entrega la lista de tipos para filtrar el historial de canje
     * @return List<CodeDescBean> lista de tipos
     */
    public static List<CodeDescBean> obtenerTiposHistorialList() {
        List<CodeDescBean> tiposHistorialList = getParametrosList(TIPO_HISTORIAL);
        return tiposHistorialList;
    }
    

    /**
     * Entrega un array con los parametros registrados para el tipo de parametro
     * <code>type</code>, que debe estar indicado en el properties de miEntel de 
     * la forma:
     * <p>
     * <pre>parametros.<type></pre>
     * </p>
     * 
     * @param type
     *            String con el nombre del tipo de parametro que deseamos
     *            accesar (Ver constantes de esta clase, para saber los tipos de
     *            parametros posibles)
     * @return String[] Array de Strings con los parametros reconocidos para el
     *         tipo de parametro. <code>null</code> si el tipo de parametro no
     *         existe, o no hay valores para el.
     */
    private static String[] getParametrosAvailableValues(String type) {

        String availableValues = LoadProperty.getProperty("parametros." + type);
        
        if (availableValues == null || availableValues.trim().equals("")) {
            return null;
        }

        return availableValues.split(",");
    }
    
    
    
    public static List<MultitiendaBean> getMultitiendas() {
        return getMultitiendaParametrosList(MULTITIENDAS_PROPERTY);
    } 
    
    /**
     * 
     * @param nombreMultitienda
     * @return
     */
    
    private static MultitiendaBean getMultitiendaBeanByParametro(
            String nombreProperty) {

        MultitiendaBean multitienda = null;

        try {

            String keyPrefix = "parametros.multitienda." + nombreProperty + ".";
            String nombre = LoadProperty.getProperty(keyPrefix + "nombre");
            String id = LoadProperty.getProperty(keyPrefix + "id");
            int tipo = Integer.parseInt(LoadProperty.getProperty(keyPrefix
                    + "tipo"));
            int digitos = Integer.parseInt(LoadProperty
                    .getProperty(keyPrefix + "digitos"));
            String[] montos = getParametrosAvailableValues(keyPrefix.replace(
                    "parametros.", "")
                    + "montos");
            String[] cuotas = getParametrosAvailableValues(keyPrefix.replace(
                    "parametros.", "")
                    + "cuotas");

            multitienda = new MultitiendaBean(nombreProperty, nombre, id, tipo,
                    digitos, montos, cuotas);

        } catch (MissingResourceException e) {
            LOGGER.warn("Parametro '" + nombreProperty
                    + "' no encontrado para el tipo '" + nombreProperty + "'");
        }

        return multitienda;

    }

    /**
     * Retorna un objeto {@link CodeDescBean} con el id y descripcion del
     * parametro <code>parametro</code> para el tipo de parametro indicado en
     * <code>type</code>.<br>
     * Si el codigo o la descripcion no son encontradas, este metodo retorna
     * <code>null</code>
     * 
     * @param type
     *            String tipo de parametro
     * @param parametro
     *            String el nombre del parametro
     * @return {@link CodeDescBean} con el id y descripcion del parametro.
     *         <code>null</code> si alguno de sus valores no son encontrados.
     */
        private static CodeDescBean getCodeDescBeanByParametro(String type,
            String parametro) {

        CodeDescBean codeDescBean = null;
        try {
            String keyPrefix = "parametros." + type + "." + parametro;
            String codigo = LoadProperty.getProperty(keyPrefix + ".id");
            String descripcion = LoadProperty.getProperty(keyPrefix
                    + ".desc");
            codeDescBean = new CodeDescBean(codigo, descripcion);
        } catch (MissingResourceException e) {
            LOGGER.warn("Parametro '" + parametro
                    + "' no encontrado para el tipo '" + type + "'");
        }
        return codeDescBean;

    }
        
    /**
     * Retorna un objeto {@link CodeDescBean} con el id y descripcion del
     * AAA  para el tipo de parametro indicado en <code>code</code>
     * <br>
     * Si el codigo o la descripcion no son encontradas, este metodo retorna
     * <code>CodeDescBean</code> vacio
     * @param aaaList {@link List} con posibles valores de aaa
     * @param type
     *            String tipo de parametro
     * @param parametro
     *            String el nombre del parametro
     * 
     * @return {@link CodeDescBean} con el id y descripcion del parametro.
     *         <code>null</code> si alguno de sus valores no son encontrados.
     */
    public static CodeDescBean getAAABean(String code, List<CodeDescBean> aaaList) {

    	if(PCBUtils.isEmptyString(code)) {
    		LOGGER.warn("'code' no puede ser null");
    		return CodeDescBean.emptyBean();
    	}
    	
    	if(aaaList == null || aaaList.isEmpty()) {
    		LOGGER.warn("aaaList es null o vacia");
    		return CodeDescBean.emptyBean();
    	}
    	
    	for(CodeDescBean aaa: aaaList) {
    		if(aaa.getCodigo().equals(code)) {
    			return aaa;
    		}
    	}
        
    	LOGGER.warn("AAA para code '" + code + "' no encontrado an lista");
    	return CodeDescBean.emptyBean();

    }     

	/**
	 * Entrega una lista de {@link CodeDescBean} con los posibles valores de AAA
	 * de un usuario.<br>
	 * Para efectos de performance y concurrencia, este metodo retorna una nueva instancia
	 * cada vez que es invocado. Si es necesaria una busqueda de un determinado valor de AAA
	 * emplear el metodo {@link #getAAABean(String, List)}
	 * 
	 * @return {@link List}
	 */
	public static List<CodeDescBean> getAAABeanList() {

		List<CodeDescBean> aaaList = new ArrayList<CodeDescBean>();

		String[] availableAAAValue = getAvailableAAAValues();

		for (int i = 0; i < availableAAAValue.length; i++) {

			try {
				String codigo = LoadProperty.getProperty("aaa."
						+ availableAAAValue[i] + ".code");
				String descripcion = LoadProperty.getProperty("aaa."
						+ availableAAAValue[i] + ".desc");
				aaaList.add(new CodeDescBean(codigo, descripcion));

			} catch (MissingResourceException e) {
				LOGGER.warn("AAA '" + availableAAAValue[i] + "' no encontrado");
			}

		}

		return aaaList;
	}
    
    private static String[] getAvailableAAAValues() {

        String availableValues = LoadProperty.getProperty("aaa");
        
        if (availableValues == null || availableValues.trim().equals("")) {
            return null;
        }

        return availableValues.split(",");
    }

    /**
     * @return the montoRecargaTarjetaCredito
     */
    public static String getMontosRecargaTarjetaCreditoDescripcion() {
        if (montosRecargaTarjetaCredito == null) {
            montosRecargaTarjetaCredito = ParametrosHelper
                    .getSelectItemsMontosRecargaTarjetaCreditoDescipcion();
        }
        return montosRecargaTarjetaCredito;
    }
    
    /**
     * @return the montoRecargaTarjetaCredito
     */
    public static String getMontosRecargaTarjetaCreditoDescripcionBAK() {
        if (montosRecargaTarjetaCredito == null) {
            montosRecargaTarjetaCredito = ParametrosHelper
                    .getSelectItemsMontosRecargaTarjetaCreditoDescipcion();
        }
        return montosRecargaTarjetaCredito;
    }    
    
    /**
     * Entrega una lista de {@link MontoRecargaBean} con los montos para
     * recargas de tarjetas de creditos que esten indicados en las propiedades
     * de la aplicacion, bajo el key parametros.montoRecargaTarjetaCredito
     * 
     * @return {@link List} de {@link MontoRecargaBean} con los montos para
     *         recargas de tarjetas de credito
     */
    public static List<MontoRecargaBean> getMontosRecargaTarjetaCredito() {

        List<MontoRecargaBean> parametrosList = new ArrayList<MontoRecargaBean>();

        String[] availableMontos = getParametrosAvailableValues("montoRecargaTarjetaCredito");

        // Si no hay parametros se entrega lista vacia
        if (availableMontos == null || availableMontos.length == 0) {
            return parametrosList;
        }

        for (int i = 0; i < availableMontos.length; i++) {
            String montoId = availableMontos[i];
            // si el monto esta mal indicado se ignora, para evitar
            // NullPointerException
            MontoRecargaBean monto = getMontoRecargaTarjetaCredito(montoId);
            if (monto != null) {
                parametrosList.add(monto);
            }
        }

        return parametrosList;
    }
    
    /**
     * Entrega una lista de {@link CodDescBean} con los motivos para
     * el formulario contacto
     * 
     * @return {@link List} de {@link CodDescBean} con los motivos para
     *         el formulario contacto
     */
    public static List<CodeDescBean> getMotivosFormularioContacto() {

        List<CodeDescBean> parametrosList = new ArrayList<CodeDescBean>();

        String[] availableMotivos = getParametrosAvailableValues("formularioContacto.motivo");

        // Si no hay parametros se entrega lista vacia
        if (availableMotivos == null || availableMotivos.length == 0) {
            return parametrosList;
        }

        for (int i = 0; i < availableMotivos.length; i++) {
        	CodeDescBean codDesc = new CodeDescBean(availableMotivos[i], availableMotivos[i]);
            parametrosList.add(codDesc);
        }

        return parametrosList;
    }
    
    /**
     * Entrega una lista de {@link CodDescBean} con los indicativos telefonicos para
     * el formulario contacto
     * 
     * @return {@link List} de {@link CodDescBean} con los indicativos telefonicos para
     *         el formulario contacto
     */
    public static List<CodeDescBean> getIndicativoTelefono() {

        List<CodeDescBean> parametrosList = new ArrayList<CodeDescBean>();

        String[] availableIndicativos = getParametrosAvailableValues("formularioContacto.telAdicional.indicativo");

        // Si no hay parametros se entrega lista vacia
        if (availableIndicativos == null || availableIndicativos.length == 0) {
            return parametrosList;
        }

        for (int i = 0; i < availableIndicativos.length; i++) {
        	CodeDescBean codDesc = new CodeDescBean(availableIndicativos[i], availableIndicativos[i]);
            parametrosList.add(codDesc);
        }

        return parametrosList;
    }
    
    /**
     * Entrega una lista de {@link CodDescBean} con los tipos para
     * el formulario contacto
     * 
     * @return {@link List} de {@link CodDescBean} con los tipos para
     *         el formulario contacto
     */
    public static List<CodeDescBean> getTiposFormularioContacto() {

        List<CodeDescBean> parametrosList = new ArrayList<CodeDescBean>();

        String[] availableTipos = getParametrosAvailableValues("formularioContacto.tipo");

        // Si no hay parametros se entrega lista vacia
        if (availableTipos == null || availableTipos.length == 0) {
            return parametrosList;
        }

        for (int i = 0; i < availableTipos.length; i++) {
        	CodeDescBean codDesc = new CodeDescBean(availableTipos[i], availableTipos[i]);
            parametrosList.add(codDesc);
        }

        return parametrosList;
    }
    
    /** 
     * Crea un array de SelectItem
     * @param entities List 
     * @param selectOne boolean que indica si se agreaga una opcion vacia al inicio.
     * @return Array de tipo SelectItem
     * @throws No dispara ninguna excepcion.
     */
    public static String getSelectItemsMontosRecargaTarjetaCreditoDescipcion() {
        String montoDescripcion = null;
//        List<MontoRecargaBean> parametrosList = new ArrayList<MontoRecargaBean>();
        
        List<MontoRecargaBean> availableMontos = ParametrosHelper.getMontosRecargaTarjetaCredito();
        
        for (MontoRecargaBean bean : availableMontos) {
            montoDescripcion = "$ " + PCBUtils.formatMoneyPuntos(bean.getDoubleMonto()) + " - " + bean.getVigencia() + " dias";
            
//            MontoRecargaBean montoRecargaBean = getMontoRecargaTarjetaCredito(montoDescripcion);
//            if (montoRecargaBean != null) {
//                parametrosList.add(montoRecargaBean);
//            }
        }
        return montoDescripcion;
    }
    

    /**
     * Obtiene de los parametros el monto y vigencia de un monto y lo entrega
     * como un {@link CodeDescBean}, cuyo codigo es el monto y descripcion es el
     * monto formateao seguido de la vigencia
     * 
     * @param montoId
     *            String id del parametro monto
     * @return {@link CodeDescBean} con monto como codigo/descripcion
     */
    public static MontoRecargaBean getMontoRecargaTarjetaCredito(String montoId) {

        String doubleMonto = LoadProperty.getProperty("parametros.montoRecargaTarjetaCredito." + montoId + ".monto");
        String vigencia = LoadProperty.getProperty("parametros.montoRecargaTarjetaCredito." + montoId + ".vigencia");
        String descripcion = "$ " + PCBUtils.formatMoneyPuntos(Double.parseDouble(doubleMonto)) + " - " + vigencia + " dias";
        
        if(Integer.parseInt(doubleMonto)<500){
            descripcion = "Seleccione Monto";
        }
        
//        String descripcion = getSelectItemsMontosRecargaTarjetaCreditoDescipcion();

        if (PCBUtils.isEmptyString(doubleMonto) || PCBUtils.isEmptyString(vigencia) || PCBUtils.isEmptyString(descripcion)) {
            return null;
        }

        return new MontoRecargaBean(Double.valueOf(doubleMonto), vigencia, descripcion);
    }


    /**
     * @return
     */
    public static List<CodeDescBean> getMontos(String multitienda){
        return getParamMoneyList(MULTITIENDAS_PROPERTY+"."+multitienda+".montos");
    }
    
    /**
     * 
     * @return
     */
    public static List<CodeDescBean> getCuotas(String multitienda){
        return getParamList(MULTITIENDAS_PROPERTY+"."+multitienda+".cuotas");
    }
    
    /**
     * 
     * @return
     */
    public static String getNombreMultitienda(String multitienda){
        return LoadProperty.getProperty("parametros."+MULTITIENDAS_PROPERTY+"."+multitienda+".nombre");
    }
            
    /**
     * 
     * @param type
     * @return
     */
    private static List<CodeDescBean> getParamList(String type) {

        List<CodeDescBean> parametrosList = new ArrayList<CodeDescBean>();

        String[] parametros = getParametrosAvailableValues(type);
        
        //Si no hay parametros se entrega lista vacia
        if(parametros == null || parametros.length == 0) {
             return parametrosList;
        }

        for(int i = 0; i < parametros.length; i++) {            
            String parametro = parametros[i];            
            parametrosList.add(new CodeDescBean(parametro, parametro));
        }
        
        return parametrosList;

    }
    
    /**
     * 
     * @param type
     * @return
     */
    private static List<CodeDescBean> getParamMoneyList(String type) {

        List<CodeDescBean> parametrosList = new ArrayList<CodeDescBean>();

        String[] parametros = getParametrosAvailableValues(type);
        
        //Si no hay parametros se entrega lista vacia
        if(parametros == null || parametros.length == 0) {
             return parametrosList;
        }

        for(int i = 0; i < parametros.length; i++) {            
            String parametro = parametros[i];            
            parametrosList.add(new CodeDescBean(parametro, "$" + PCBUtils.formatMoney(Double.parseDouble(parametro))));
        }
        
        return parametrosList;

    }
    
    /**
     * Retorna un objeto {@link CodeDescBean} con el id y descripcion del
     * parametro <code>type</code> para el tipo de parametro indicado en
     * <code>codigo</code>.<br>
     * Si el codigo o la descripcion no son encontradas, este metodo retorna
     * <code>null</code>
     * 
     * @param type
     *            String tipo de parametro
     * @param parametro
     *            String el nombre del parametro
     * @return {@link CodeDescBean} con el id y descripcion del parametro.
     *         <code>null</code> si alguno de sus valores no son encontrados.
     */
    public static CodeDescBean getCodeDescBeanByTypeId(String type,
            String codigo) {
        
        String parametro = null;
        try {
            
            if(codigo == null || codigo.equals("")){
                return new CodeDescBean("","");
            }
            
            String[] parametros = getParametrosAvailableValues(type);
             // Si no hay parametros se entrega lista vacia
            if(parametros == null || parametros.length == 0) {
                 return new CodeDescBean("","");
            }

            for(int i = 0; i < parametros.length; i++) {
                
                parametro = parametros[i];
                CodeDescBean codeDescBean = getCodeDescBeanByParametro(type, parametro);
                if(codeDescBean != null && codeDescBean.getCodigo().equals(codigo)) {
                    return (codeDescBean);
                }
            }
            
            return new CodeDescBean("", "");
        } catch (MissingResourceException e) {
            LOGGER.warn("Parametro '" + parametro
                    + "' no encontrado para el tipo '" + type + "'");
            return new CodeDescBean("","");
        }

    }
    
    private static List<MultitiendaBean> getMultitiendaParametrosList(String type) {

        List<MultitiendaBean> parametrosList = new ArrayList<MultitiendaBean>();

        String[] parametros = getParametrosAvailableValues(type);
        
        //Si no hay parametros se entrega lista vacia
        if(parametros == null || parametros.length == 0) {
             return parametrosList;
        }

        for(int i = 0; i < parametros.length; i++) {
            
            String parametro = parametros[i];
            MultitiendaBean multitienda = getMultitiendaBeanByParametro(parametro);
            if(multitienda != null) {
                parametrosList.add(multitienda);
            }
        }
        
        return parametrosList;

    }

    /**
     * Entrega la lista de parametros registrados para el tipo de parametro
     * indicado en <code>type</code>.<br>
     * Si el tipo de parametro no tiene valores, este metodo entega una lista
     * vacia.
     * 
     * @param type
     *            String tipo de parametro
     * @return {@link List} con instancias {@link CodeDescBean} con los
     *         parametros encontrados para el tipo <code>type</code>.<br>
     *         Si no hay valores, este metodo entrega una lista vacia.
     */
    private static List<CodeDescBean> getParametrosList(String type) {

        List<CodeDescBean> parametrosList = new ArrayList<CodeDescBean>();

        String[] parametros = getParametrosAvailableValues(type);
        
        //Si no hay parametros se entrega lista vacia
        if(parametros == null || parametros.length == 0) {
             return parametrosList;
        }

        for(int i = 0; i < parametros.length; i++) {
            
            String parametro = parametros[i];
            CodeDescBean codeDescBean = getCodeDescBeanByParametro(type, parametro);
            if(codeDescBean != null) {
                parametrosList.add(codeDescBean);
            }
        }
        
        return parametrosList;

    }
    
    /**
     * Entrega lista de tiene hijos
     * 
     * @return List<CodeDescBean> lista de tene hijos
     */
    public static List<CodeDescBean> getListTiposTarjeta() {
        List<CodeDescBean> tipoTarjetaList = getParametrosList(TIPO_TARJETA);
        return tipoTarjetaList;
    }
    
    
    /**
     * Listado de tipos de bolsas.
     * @return
     */
    public static List<CodeDescBean> getListTiposBolsa() {
        List<CodeDescBean> tipoBolsaList = getParametrosList(TIPO_BOLSA);
        return tipoBolsaList;
    }    
      
    
    /**
     * Listado de prefijos telefono
     * @return
     */
    public static List<SelectItem> getPrefijoTelefonoParametrosList() {
    	List<SelectItem> listSelect = new ArrayList<SelectItem>();
    	String [] parametros = getParametrosAvailableValues(PREFIJO_TELEFONO);
        SelectItem item;
        for (int i = 0; i < parametros.length; i++) {
        	item = new SelectItem(parametros[i], parametros[i]);
			listSelect.add(item);
		}
        return listSelect;
    }
    

    /**
     * Regresa un valor booleano el cual determina si un dato esta una lista del properties
     * indicado en <code>type</code>.<br> y el <code>dato</code>.<br> a buscar
     * Si el dato esta en la lista regresa true de los contrario false.  
     * 
     * @param type
     *            String tipo de parametro
     * @param dato
     *            String dato a buscar en la lista.           
     * @return {@link Boolean} se  regresa true en caso de que el <code>dato</code>.<br>
     *          este dentro del listado del parametros encontrados para el tipo <code>type</code>.<br>
     *          Si esta dentro de esta lista regresa false.
     */
    public static boolean getExisteParametro(String type , String dato)
    {
    	String[] listado;
    	boolean sw =false;
    	int i = 0;
    	try
    	{ 
    		listado = getParametrosAvailableValues(type);    	    
    		while(i < listado.length && !sw)
    		{    			
    			if(listado[i].equals(dato))
    			{    				
    				sw=true;  
    			}
    			i++;    			
    		}
        }catch(Exception e){
        	LOGGER.error("No fue determinar si el valor esta en la lista del properties "+ e.getMessage());        	
        }        
        return sw;
    }   
    
    /**
     * Regresa un valor dependindo del tipo de parametro, por los generar son respuestas de servicios
     * para esto se indica el<code>type</code>.<br> y el <code>dato</code>.<br> a buscar 
     * @param type
     *            String tipo de parametro
     * @param dato
     *            String dato a buscar.           
     * @return {String respuesta} se  regresa el valor buscado en el properties , 
     *          si el valor no esta regresa el mismo valor del dato
     **/
    public static String getRespuestaServicio(String type , String dato)
    {
    	String respuesta="";
       try
    	  { 
    	   respuesta = LoadProperty.getProperty("parametros." + type+"."+dato); 
    	   if(respuesta==null)
    	   {  
    		 respuesta = dato;
    		 
    	   }else if(respuesta.equals(""))
		   {
    		  respuesta = dato;
		   }    	   
    		
        }catch(Exception e){
        	LOGGER.error("No fue posible determinar si el valor esta en la lista del properties "+ e.getMessage());        	
        }        
        return respuesta;
    }   
    
    public static List<CodeDescBean> getMarcasEquipo() {

        List<CodeDescBean> parametrosList = new ArrayList<CodeDescBean>();

        String[] marcas = getParametrosAvailableValues("formularioContactoPortabilidad.marcaEquipo");

        // Si no hay parametros se entrega lista vacia
        if (marcas == null || marcas.length == 0) {
            return parametrosList;
        }

        for (int i = 0; i < marcas.length; i++) {
        	CodeDescBean codDesc = new CodeDescBean(marcas[i], marcas[i]);
            parametrosList.add(codDesc);
        }

        return parametrosList;
    }
    
    /**
     * Listado de tipos de bolsas.
     * @return
     */
    public static List<CodeDescBean> getListTiposBolsaPP() {
        List<CodeDescBean> tipoBolsaList = getParametrosList(TIPO_BOLSA_PP);
        return tipoBolsaList;
    }      
    
    /**
     * @param descripcion 
     *                   String descripcion en formato [SALDO] [UNIDAD] Texto descripcion
     * @return (String decs) , retora descripcion del saldo sin corchetes [], Ejem SALDO UNIDAD Texto descripcion
     */
    public static String getParseDescSaldo(String descripcion) {    	
        try {
        	descripcion = descripcion.replace("[", "");        	
        	descripcion = descripcion.replace("]", "");     	   
         } catch (Exception e) {
             LOGGER.warn("Error parceando la descripcion del saldo :"+descripcion+ e.getMessage());
         }
         return descripcion;
     }  
    
    /**
     * Regresa el codigo para el tipo de bolsas mas vendidas.
     * @return
     */
    public static String getTipoDeBolsasMasVendidas() {
    	 String tipo="";
    	 try {
    		 tipo =  LoadProperty.getProperty(TIPO_BOLSAS_MAS_VENDIDAS);     	   
          } catch (Exception e) {
              LOGGER.warn("Error buscando el valor para el properties :"+TIPO_BOLSAS_MAS_VENDIDAS);
          }  
        return tipo;
    } 
    
    /**
     * Retorna el listado de productos disponbiles para recarga
     * @return
     */    
    public static List<SelectItem> getProductosRecargaList() {
    	List<SelectItem> listSelect = new ArrayList<SelectItem>();
    	String[] parametros = getParametrosAvailableValues(PRODUCTOS_RECARGA);
    	
        //Si no hay parametros se entrega lista vacia
        if(parametros == null || parametros.length == 0) {
             return listSelect;
        }

        for(int i = 0; i < parametros.length; i++) {            
            String parametro = parametros[i];
            CodeDescBean codeDescBean = getCodeDescBeanByParametro(PRODUCTOS_RECARGA, parametro);
            if (codeDescBean != null) {
            	listSelect.add(new SelectItem(codeDescBean.getCodigo(), codeDescBean.getDescripcion()));
            }
        }
        
        return listSelect;
    }   
    
    
    /**
     * Entrega una lista de {@link CodeDescBean} con los tipos de reclamos
     * indicados en las propiedades de la aplicacion, bajo el key parametros.montoRecargaTarjetaCredito     
     * @return {@link List} de {@link CodeDescBean} con los tipos de reclamos
     */
    public static List<CodeDescBean> getTiposReclamoList(String tipo) {

        List<CodeDescBean> parametrosList = new ArrayList<CodeDescBean>();

        String[] tipoReclamos = getParametrosAvailableValues(tipo);

        // Si no hay parametros se entrega lista vacia
        if (tipoReclamos == null || tipoReclamos.length == 0) {
            return parametrosList;
        }

        for (int i = 0; i < tipoReclamos.length; i++) {
            String reclamoCod = tipoReclamos[i];
            // si el tipo de reclamo esta mal indicado se ignora, para evitar
            // NullPointerException
            CodeDescBean reclamo = getTipoReclamoFull(reclamoCod,tipo);
            if (reclamo != null) {
                parametrosList.add(reclamo);
            }
        }

        return parametrosList;
    }
    
 
    
    /**
    * Obtiene de los parametros el codigo tipo reclamo y descripcion del tipo reclamo
    * como un {@link CodeDescBean},
    * 
    * @param codTipoReclamo
    *            String codgo del parametro codTipoReclamo
    * @return {@link CodeDescBean} con monto como codigo/descripcion
    */
   public static CodeDescBean getTipoReclamoFull(String codTipoReclamo,String tipo) {

       String codigo = LoadProperty
               .getProperty("parametros."+tipo+"."+codTipoReclamo
                       + ".codigo");
       String descripcion = LoadProperty
               .getProperty("parametros."+tipo+"."+codTipoReclamo
                       + ".descripcion");

       if (PCBUtils.isEmptyString(codigo) || PCBUtils.isEmptyString(descripcion)) {
           return null;
       }

       return new CodeDescBean(codigo, descripcion);
   }

    
    
    
   /**
    * Entrega una lista de {@link CodDescBean} con los estados de los servicios de
    * suscripcion
    * 
    * @return {@link List} de {@link CodDescBean} con los estados de los servicios de
    * suscripcion
    */
   public static List<CodeDescBean> getEstados() {

       List<CodeDescBean> parametrosList = new ArrayList<CodeDescBean>();

       String[] availableEstados = getParametrosAvailableValues("adminServicios.estado");

       // Si no hay parametros se entrega lista vacia
       if (availableEstados == null || availableEstados.length == 0) {
           return parametrosList;
       }

       for (int i = 0; i < availableEstados.length; i++) {
       	CodeDescBean codDesc = new CodeDescBean(availableEstados[i], availableEstados[i]);
           parametrosList.add(codDesc);
       }

       return parametrosList;
   }
   

}
