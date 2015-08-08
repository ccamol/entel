/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.esa.ponline.portalbolsas.ws.recarga.dao.factory;

import com.epcs.billing.recarga.types.FactibilidadRecargaTicketType;
import com.epcs.billing.recarga.types.FactibilidadWebPayType;
import com.epcs.billing.recarga.types.RecargaWebPayType;
import com.esa.ponline.portalbolsas.configuration.properties.LoadProperty;

/**
 * Factoria para Types de BillingRecarga que hacen gran uso de constantes.<br>
 * Los metodos de esta factoria siempre entregan nuevas instancias, con las
 * constantes cargadas desde las propiedades de LoadProperty
 * 
 * @author ccastro (MZZO) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class RecargaTypeFactory {

    /**
     * Entrega instancias de {@link FactibilidadWebPayType} con los valores de
     * constantes ya asignados
     * 
     * @return {@link FactibilidadWebPayType} ready to use
     */
    public static FactibilidadWebPayType buildFactibilidadWebPayType(boolean eligeTuPromo) {

        FactibilidadWebPayType request = new FactibilidadWebPayType();

        String codComercio = LoadProperty.getProperty("recarga.webpay.factibilidad.codComercio");
        request.setCodComercio(codComercio);

        request.setCodComuna(LoadProperty.getProperty("recarga.webpay.factibilidad.codComuna"));

        String codDistribuidor = LoadProperty.getProperty("recarga.webpay.factibilidad.codDistribuidor");
        request.setCodDistribuidor(codDistribuidor);

        String codIntegrador = LoadProperty.getProperty("recarga.webpay.factibilidad.codIntegrador");
        request.setCodIntegrador(codIntegrador);

        String codLocal = LoadProperty.getProperty("recarga.webpay.factibilidad.codLocal");
        request.setCodLocal(codLocal);

        String codRegion = LoadProperty.getProperty("recarga.webpay.factibilidad.codRegion");
        request.setCodRegion(codRegion);

        String codTerminal = LoadProperty.getProperty("recarga.webpay.factibilidad.codTerminal");
        request.setCodTerminal(codTerminal);

        String correlativoTrans = LoadProperty.getProperty("recarga.webpay.factibilidad.correlativoTrans");
        request.setCorrelativoTrans(correlativoTrans);

        String metodo = LoadProperty.getProperty("recarga.webpay.factibilidad.metodo");
        request.setMetodo(metodo);

        String plataforma = LoadProperty.getProperty("recarga.webpay.factibilidad.plataforma");
        request.setPlataforma(plataforma);

        return request;
    }

    /**
     * Entrega instancias de {@link RecargaWebPayType} con los valores de
     * constantes ya asignados
     * 
     * @return {@link RecargaWebPayType} ready to use
     */
    public static RecargaWebPayType buildRecargaWebPayType(boolean eligeTuPromo) {

        RecargaWebPayType request = new RecargaWebPayType();

        String codComercio = LoadProperty.getProperty("recarga.webpay.efectuar.codComercio");
        request.setCodComercio(codComercio);

        request.setCodComuna(LoadProperty.getProperty("recarga.webpay.efectuar.codComuna"));

        String codDistribuidor = LoadProperty.getProperty("recarga.webpay.efectuar.codDistribuidor");
        request.setCodDistribuidor(codDistribuidor);

        String codIntegrador = LoadProperty.getProperty("recarga.webpay.efectuar.codIntegrador");
        request.setCodIntegrador(codIntegrador);

        String codLocal = LoadProperty.getProperty("recarga.webpay.efectuar.codLocal");
        request.setCodLocal(codLocal);

        String codRegion = LoadProperty.getProperty("recarga.webpay.efectuar.codRegion");
        request.setCodRegion(codRegion);

        String codTerminal = LoadProperty.getProperty("recarga.webpay.efectuar.codTerminal");
        request.setCodTerminal(codTerminal);

        String correlativoTrans = LoadProperty.getProperty("recarga.webpay.efectuar.correlativoTrans");
        request.setCorrelativoTrans(correlativoTrans);

        String metodo = LoadProperty.getProperty("recarga.webpay.efectuar.metodo");
        request.setMetodo(metodo);

        String plataforma = LoadProperty.getProperty("recarga.webpay.efectuar.plataforma");
        request.setPlataforma(plataforma);

        String formaPago = LoadProperty.getProperty("recarga.webpay.efectuar.formaPago");
        request.setFormaPago(formaPago);

        String envioSMS = LoadProperty.getProperty("recarga.webpay.efectuar.envioSMS");
        request.setEnvioSMS(envioSMS);

        return request;
    }

    /**
     * Entrega instancias de {@link FactibilidadRecargaTicketType} para ser
     * usadas con el servicio de factibilidad de recarga mediante Entelticket
     * con los valores de constantes ya asignados
     * 
     * @return {@link FactibilidadRecargaTicketType} listo para usar en
     *         factibilidad
     */
    public static FactibilidadRecargaTicketType buildFactibilidadEntelTicketType() {

        FactibilidadRecargaTicketType request = new FactibilidadRecargaTicketType();

        request.setMetodo(LoadProperty.getProperty("recarga.entelticket.factibilidad.metodo"));

        request.setPlataforma(LoadProperty.getProperty("recarga.entelticket.factibilidad.plataforma"));

        request.setTipoOperacion(LoadProperty.getProperty("recarga.entelticket.factibilidad.tipoOperacion"));

        request.setMontoTicket(LoadProperty.getProperty("recarga.entelticket.factibilidad.montoTicket"));

        request.setCodigoBono(LoadProperty.getProperty("recarga.entelticket.factibilidad.codigoBono"));

        request.setMontoBono(LoadProperty.getProperty("recarga.entelticket.factibilidad.montoBono"));

        request.setValidezRecarga(LoadProperty.getProperty("recarga.entelticket.factibilidad.validezRecarga"));

        request.setOrigenPeticion(LoadProperty.getProperty("recarga.entelticket.factibilidad.origenPeticion"));

        request.setCanalServicio(LoadProperty.getProperty("recarga.entelticket.factibilidad.canalServicio"));

        request.setAgenciaTicket(LoadProperty.getProperty("recarga.entelticket.factibilidad.agenciaTicket"));

        request.setFolioTicket(LoadProperty.getProperty("recarga.entelticket.factibilidad.folioTicket"));

        return request;

    }

    /**
     * Entrega instancias de {@link FactibilidadRecargaTicketType} para ser
     * usadas con el servicio de efectuar de recarga mediante Entelticket con
     * los valores de constantes ya asignados
     * 
     * @return {@link FactibilidadRecargaTicketType} listo para usar en efectuar
     *         recarga
     */
    public static FactibilidadRecargaTicketType buildRealizarEntelTicketType() {

        FactibilidadRecargaTicketType request = new FactibilidadRecargaTicketType();

        request.setMetodo(LoadProperty.getProperty("recarga.entelticket.efectuar.metodo"));

        request.setPlataforma(LoadProperty.getProperty("recarga.entelticket.efectuar.plataforma"));

        request.setTipoOperacion(LoadProperty.getProperty("recarga.entelticket.efectuar.tipoOperacion"));

        request.setCodigoBono(LoadProperty.getProperty("recarga.entelticket.efectuar.codigoBono"));

        request.setMontoBono(LoadProperty.getProperty("recarga.entelticket.efectuar.montoBono"));

        request.setOrigenPeticion(LoadProperty.getProperty("recarga.entelticket.efectuar.origenPeticion"));

        request.setCanalServicio(LoadProperty.getProperty("recarga.entelticket.efectuar.canalServicio"));

        return request;
    }

}
