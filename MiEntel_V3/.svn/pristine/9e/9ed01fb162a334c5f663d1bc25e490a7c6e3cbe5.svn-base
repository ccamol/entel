/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.billing.registrouso.dao.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.epcs.bean.ResumenTraficoBean;
import com.epcs.billing.registrouso.dao.TraficoDAO;
import com.epcs.billing.registrouso.types.ProductoType;
import com.epcs.recursoti.configuracion.DateHelper;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.Utils;

/**
 * Clase utilitaria para identificar el servicio/producto al que corresponden
 * los traficos obtenidos desde los servicios.<br>
 * Esta clase esta preparada trabajar con instancias de {@link ProductoType} en
 * vista que fue hecha para el cliente de WS BillingRegitroUsoService
 * 
 * @author jlopez (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class TraficoSSHelper {
	private static final Logger LOGGER = Logger.getLogger(TraficoSSHelper.class);
    /**
     * Lista con id de Productos Voz.<br>
     * Se llena de manera sincronizada, para evitar problemas de concurrencia
     */
    private static List<String> productoVozIdsList;

    /**
     * List con los tipos de Plan para Trafico Voz.<br>
     * Se llena de manera sincronizada, para evitar problemas de concurrencia
     */
    private static List<String> tiposPlanList;

    /**
     * Responde si <code>producto</code> corresponde a un Producto Voz.<br>
     * Este metodo toma todos los valores indicados en la propiedad
     * <code>producto.voz</code> y por cada uno compara su id de producto con el
     * valor de idProducto de <code>producto</code>
     * 
     * @param producto
     *            {@link ProductoType} producto a evaluar
     * @return <code>true</code> si <code>produto</code> es Producto Voz.
     *         <code>false</code> en caso contrario
     */
    public static boolean isProductoVoz(ProductoType producto) {
        List<String> productoVozIdsList = getProductoVozIds();
        return productoVozIdsList.contains(producto.getIdProducto());
    }

    /**
     * Metodo utilitario para recuperar todos los id de producto validos para un
     * Producto Voz.<br>
     * Este metodo recupera el id de producto de cada producto voz definido en
     * la propiedad <code>producto.voz</code>
     * 
     * @return {@link List} de String con los id de producto validos para
     *         productos Voz
     */
    private static List<String> getProductoVozIds() {

        if (productoVozIdsList == null) {
            
            productoVozIdsList = new ArrayList<String>();
            
            //Recupera producto.voz definidos en propiedades
            String[] availableProductosVoz = MiEntelProperties.getProperty(
                    "producto.voz").split(",");

            //por cada uno obtiene su id y lo agrega a la lista
            for (int i = 0; i < availableProductosVoz.length; i++) {
                String productoVozId = MiEntelProperties
                        .getProperty("producto.voz."
                                + availableProductosVoz[i] + ".id");
                if (Utils.isEmptyString(productoVozId)) {
                    continue;
                }
                else {
                    productoVozIdsList.add(productoVozId);
                }
            }
        }

        return productoVozIdsList;
    }

    /**
     * Asigna al bean <code>resumenTraficoBean</code> el trafico de Voz que
     * contenga el <code>producto</code>.<br>
     * Este metodo asume que <code>producto</code> corresponde a un Producto Voz
     * valido (ver {@link TraficoSSHelper#isProductoVoz(ProductoType)}).<br>
     * 
     * @param resumenTraficoBean
     *            Bean a quien se asignara el trafico
     * @param producto
     *            {@link ProductoType} con la informacion del trafico
     * @return {@link ResumenTraficoBean} corresponde a la misma instancia de
     *         <code>resumenTraficoBean</code> con el trafico de Voz asignado
     * @throws Exception
     *             Si el tipo de plan de <code>producto</code> no es reconocido
     *             y no puede obtener el trafico de voz
     */
    public static ResumenTraficoBean asignarTraficoVoz(
            final ResumenTraficoBean resumenTraficoBean, ProductoType producto)
            throws Exception {

        Double trafico = getTraficoVozByTipoPlan(producto);

        resumenTraficoBean.setTraficoVoz(trafico);

        Date fechaActualizacion = DateHelper.parseDate(producto
                .getFechaRegistro(), DateHelper.FORMAT_ddMMyyyy_HYPHEN);
        resumenTraficoBean
                .setTraficoVozFechaActualizacion(fechaActualizacion);

        return resumenTraficoBean;
    }

    /**
     * Obtiene el trafico de Voz de <code>producto</code><br>
     * 
     * 
     * @param producto
     *            {@link ProductoType} de donde se obtenra trafico de Voz
     * @return Double con el trafico de voz de <code>producto</code>
     * @throws Exception
     *             Si el tipo de plan de <code>producto</code> no es reconocido
     */
    private static Double getTraficoVozByTipoPlan(ProductoType producto)
            throws Exception {

        // Obtengo tipos de plan indicados en .properties
        List<String> tiposPlanList = getTiposPlanList();

        // busco el tipo de plan al que pertenece producto
        for (String tipoPlan : tiposPlanList) {

            /*
             * Obtengo id del tipo de plan, para compararlo con el id de tipo de
             * plan que contiene producto
             */
            String idTipoPlan = MiEntelProperties
                    .getProperty("producto.voz.tipoPlan." + tipoPlan.trim() + ".id");

            if (producto.getTipoPlan().equals(idTipoPlan.trim())) {
                return TraficoVozSSHelper.getTraficoVoz(producto, tipoPlan);
            }
        }

        // Si sale del ciclo, producto no pertenece a ningun tipo de plan
        // conocido por la aplicacion
        LOGGER.error( new Exception("Tipo de plan desconocido: "
                + producto.getTipoPlan()));
		return null;
    }

    /**
     * Entrega un {@link List} con los valores ingresados como tipos de plan en
     * la propiedad <code>producto.voz.tipoPlan</code>
     * 
     * @return {@link List} de String con tipos de plan
     */
    private static List<String> getTiposPlanList() {

        if (tiposPlanList == null) {
            
            tiposPlanList = new ArrayList<String>();
            
            // Obtengo tipos de plan indicados en .properties
            String[] availableTiposPlan = MiEntelProperties.getProperty(
                    "producto.voz.tipoPlan").trim().split(",");

            tiposPlanList = Arrays.asList(availableTiposPlan);
        }

        return tiposPlanList;

    }

    /**
     * Responde si <code>producto</code> corresponde a un Producto Mensajes.<br>
     * Este metodo compara contra la propiedad
     * <code>producto.mensajeria.id</code>
     * 
     * @param producto
     *            {@link ProductoType} producto a evaluar
     * @return <code>true</code> si <code>produto</code> es Producto Mensajes.
     *         <code>false</code> en caso contrario
     */
    public static boolean isProductoMensajes(ProductoType producto) {
        return producto.getIdProducto().equals(
                MiEntelProperties.getProperty("producto.mensajeria.id"));
    }

    /**
     * Asigna al bean <code>resumenTraficoBean</code> el trafico de Mensajes que
     * contenga el <code>producto</code>.<br>
     * Este metodo asume que <code>producto</code> corresponde a un Producto
     * Mensajes valido (ver
     * {@link TraficoSSHelper#isProductoMensajes(ProductoType)}).<br>
     * 
     * @param resumenTraficoBean
     *            Bean a quien se asignara el trafico
     * @param producto
     *            {@link ProductoType} con la informacion del trafico
     * @return {@link ResumenTraficoBean} corresponde a la misma instancia de
     *         <code>resumenTraficoBean</code> con el trafico de Mensajes
     *         asignado
     */
    public static ResumenTraficoBean asignarTraficoMensajes(
            final ResumenTraficoBean resumenTraficoBean, ProductoType producto) {

        long trafico = 0;
        trafico += Long.parseLong(producto.getKey1());
        trafico += Long.parseLong(producto.getKey3());

        resumenTraficoBean.setTraficoMensajes(trafico);

        Date fechaActualizacion = DateHelper.parseDate(producto
                .getFechaRegistro(), DateHelper.FORMAT_ddMMyyyy_HYPHEN);
        resumenTraficoBean
                .setTraficoMensajesFechaActualizacion(fechaActualizacion);

        return resumenTraficoBean;
    }

    /**
     * Responde si <code>producto</code> corresponde a un Producto Internet
     * Movil
     * Este metodo compara contra la propiedad
     * <code>producto.internetMovil.id</code>
     * 
     * @param producto
     *            {@link ProductoType} producto a evaluar
     * @return <code>true</code> si <code>produto</code> es Producto Internet
     *         Movil. <code>false</code> en caso contrario
     */
    public static boolean isProductoInternetMovil(ProductoType producto) {
        return producto.getIdProducto().equals(
                MiEntelProperties.getProperty("producto.internetMovil.id"));
    }

    /**
     * Asigna al bean <code>resumenTraficoBean</code> el trafico de Internet
     * Movil que contenga el <code>producto</code>.<br>
     * Este metodo asume que <code>producto</code> corresponde a un Producto
     * Internet Movil valido (ver
     * {@link TraficoSSHelper#isProductoInternetMovil(ProductoType)}).<br>
     * 
     * @param resumenTraficoBean
     *            Bean a quien se asignara el trafico
     * @param producto
     *            {@link ProductoType} con la informacion del trafico
     * @return {@link ResumenTraficoBean} corresponde a la misma instancia de
     *         <code>resumenTraficoBean</code> con el trafico de Internet Movil
     *         asignado
     */
    public static ResumenTraficoBean asignarTraficoInternetMovil(
            final ResumenTraficoBean resumenTraficoBean, ProductoType producto) {

        long traficoInternerMovil = Long.parseLong(producto.getKey1());
        
        resumenTraficoBean.setTraficoInternetMovil(traficoInternerMovil);

        Date fechaActualizacion = DateHelper.parseDate(producto
                .getFechaRegistro(), DateHelper.FORMAT_ddMMyyyy_HYPHEN);
        resumenTraficoBean
                .setTraficoInternetMovilFechaActualizacion(fechaActualizacion);

        return resumenTraficoBean;
    }

    /**
     * Responde si <code>producto</code> corresponde a un Producto banda ancha
     * Este metodo compara contra la propiedad
     * <code>producto.bam.id</code>
     * 
     * @param producto
     *            {@link ProductoType} producto a evaluar
     * @return <code>true</code> si <code>produto</code> es Producto Voz.
     *         <code>false</code> en caso contrario
     */
    public static boolean isProductoBandaAnchaMovil(ProductoType producto) {
        return producto.getIdProducto().equals(
                MiEntelProperties.getProperty("producto.bam.id"));
    }

    /**
     * Asigna al bean <code>resumenTraficoBean</code> el trafico de Banda Ancha
     * Movil que contenga el <code>producto</code>.<br>
     * Este metodo asume que <code>producto</code> corresponde a un Producto
     * Banda Ancha Movil valido (ver
     * {@link TraficoSSHelper#isProductoBandaAnchaMovil(ProductoType)}).<br>
     * 
     * @param resumenTraficoBean
     *            Bean a quien se asignara el trafico
     * @param producto
     *            {@link ProductoType} con la informacion del trafico
     * @return {@link ResumenTraficoBean} corresponde a la misma instancia de
     *         <code>resumenTraficoBean</code> con el trafico de Banda Ancha
     *         Movil asignado
     */
    public static ResumenTraficoBean asignarTraficoBandaAnchaMovil(
            final ResumenTraficoBean resumenTraficoBean, ProductoType producto) {

        long trafico = Long.parseLong(producto.getKey1());
        
        resumenTraficoBean.setTraficoBandaAnchaMovil(trafico);

        Date fechaActualizacion = DateHelper.parseDate(producto
                .getFechaRegistro(), DateHelper.FORMAT_ddMMyyyy_HYPHEN);
        resumenTraficoBean
                .setTraficoBandaAnchaMovilFechaActualizacion(fechaActualizacion);

        return resumenTraficoBean;
    }

    /**
     * Responde si <code>producto</code> corresponde a un Producto Blackberry
     * Este metodo compara contra la propiedad
     * <code>producto.blackberry.id</code>
     * 
     * @param producto
     *            {@link ProductoType} producto a evaluar
     * @return <code>true</code> si <code>produto</code> es Producto Voz.
     *         <code>false</code> en caso contrario
     */
    public static boolean isProductoBlackberry(ProductoType producto) {
        return producto.getIdProducto().equals(
                MiEntelProperties.getProperty("producto.blackberry.id"));
    }

    /**
     * Asigna al bean <code>resumenTraficoBean</code> el trafico de Blackberry
     * que contenga el <code>producto</code>.<br>
     * Este metodo asume que <code>producto</code> corresponde a un Producto
     * Blackberry valido (ver
     * {@link TraficoSSHelper#isProductoBlackberry(ProductoType)}).<br>
     * 
     * @param resumenTraficoBean
     *            Bean a quien se asignara el trafico
     * @param producto
     *            {@link ProductoType} con la informacion del trafico
     * @return {@link ResumenTraficoBean} corresponde a la misma instancia de
     *         <code>resumenTraficoBean</code> con el trafico de Blackberry
     *         asignado
     */
    public static ResumenTraficoBean asignarTraficoBlackberry(
            final ResumenTraficoBean resumenTraficoBean, ProductoType producto) {

        long trafico = Long.parseLong(producto.getKey1());
        
        resumenTraficoBean.setTraficoBlackberry(trafico);

        Date fechaActualizacion = DateHelper.parseDate(producto
                .getFechaRegistro(), DateHelper.FORMAT_ddMMyyyy_HYPHEN);
        resumenTraficoBean
                .setTraficoBlackberryFechaActualizacion(fechaActualizacion);

        return resumenTraficoBean;
    }

}
