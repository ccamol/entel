
package com.epcs.loyalty.zonaterceros.wsloyalty;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.epcs.loyalty.zonaterceros.wsloyalty package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ConsultaDatosNoCliente_QNAME = new QName("http://www.epcs.com/Loyalty/zonaTerceros/WSLoyalty", "consultaDatosNoCliente");
    private final static QName _ConsultaEstadoTCRutMovil_QNAME = new QName("http://www.epcs.com/Loyalty/zonaTerceros/WSLoyalty", "consultaEstadoTCRutMovil");
    private final static QName _ConsultaEstadoTCRutMovilResponse_QNAME = new QName("http://www.epcs.com/Loyalty/zonaTerceros/WSLoyalty", "consultaEstadoTCRutMovilResponse");
    private final static QName _ConsultaEstadoTCRutResponse_QNAME = new QName("http://www.epcs.com/Loyalty/zonaTerceros/WSLoyalty", "consultaEstadoTCRutResponse");
    private final static QName _ConsultaDatosNoClienteResponse_QNAME = new QName("http://www.epcs.com/Loyalty/zonaTerceros/WSLoyalty", "consultaDatosNoClienteResponse");
    private final static QName _ConsultaEstadoTCMovil_QNAME = new QName("http://www.epcs.com/Loyalty/zonaTerceros/WSLoyalty", "consultaEstadoTCMovil");
    private final static QName _ConsultaEstadoTCMovilResponse_QNAME = new QName("http://www.epcs.com/Loyalty/zonaTerceros/WSLoyalty", "consultaEstadoTCMovilResponse");
    private final static QName _ConsultaEstadoTCRut_QNAME = new QName("http://www.epcs.com/Loyalty/zonaTerceros/WSLoyalty", "consultaEstadoTCRut");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.epcs.loyalty.zonaterceros.wsloyalty
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ConsultaEstadoTCRutMovilResponse }
     * 
     */
    public ConsultaEstadoTCRutMovilResponse createConsultaEstadoTCRutMovilResponse() {
        return new ConsultaEstadoTCRutMovilResponse();
    }

    /**
     * Create an instance of {@link ConsultaDatosNoClienteResponse }
     * 
     */
    public ConsultaDatosNoClienteResponse createConsultaDatosNoClienteResponse() {
        return new ConsultaDatosNoClienteResponse();
    }

    /**
     * Create an instance of {@link ConsultaEstadoTCRutMovil }
     * 
     */
    public ConsultaEstadoTCRutMovil createConsultaEstadoTCRutMovil() {
        return new ConsultaEstadoTCRutMovil();
    }

    /**
     * Create an instance of {@link ConsultaEstadoTCMovil }
     * 
     */
    public ConsultaEstadoTCMovil createConsultaEstadoTCMovil() {
        return new ConsultaEstadoTCMovil();
    }

    /**
     * Create an instance of {@link Estadotarjetadto }
     * 
     */
    public Estadotarjetadto createEstadotarjetadto() {
        return new Estadotarjetadto();
    }

    /**
     * Create an instance of {@link ConsultaDatosNoCliente }
     * 
     */
    public ConsultaDatosNoCliente createConsultaDatosNoCliente() {
        return new ConsultaDatosNoCliente();
    }

    /**
     * Create an instance of {@link ConsultaEstadoTCRutResponse }
     * 
     */
    public ConsultaEstadoTCRutResponse createConsultaEstadoTCRutResponse() {
        return new ConsultaEstadoTCRutResponse();
    }

    /**
     * Create an instance of {@link ConsultaEstadoTCRut }
     * 
     */
    public ConsultaEstadoTCRut createConsultaEstadoTCRut() {
        return new ConsultaEstadoTCRut();
    }

    /**
     * Create an instance of {@link Datosnocliente }
     * 
     */
    public Datosnocliente createDatosnocliente() {
        return new Datosnocliente();
    }

    /**
     * Create an instance of {@link ConsultaEstadoTCMovilResponse }
     * 
     */
    public ConsultaEstadoTCMovilResponse createConsultaEstadoTCMovilResponse() {
        return new ConsultaEstadoTCMovilResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaDatosNoCliente }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.epcs.com/Loyalty/zonaTerceros/WSLoyalty", name = "consultaDatosNoCliente")
    public JAXBElement<ConsultaDatosNoCliente> createConsultaDatosNoCliente(ConsultaDatosNoCliente value) {
        return new JAXBElement<ConsultaDatosNoCliente>(_ConsultaDatosNoCliente_QNAME, ConsultaDatosNoCliente.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaEstadoTCRutMovil }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.epcs.com/Loyalty/zonaTerceros/WSLoyalty", name = "consultaEstadoTCRutMovil")
    public JAXBElement<ConsultaEstadoTCRutMovil> createConsultaEstadoTCRutMovil(ConsultaEstadoTCRutMovil value) {
        return new JAXBElement<ConsultaEstadoTCRutMovil>(_ConsultaEstadoTCRutMovil_QNAME, ConsultaEstadoTCRutMovil.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaEstadoTCRutMovilResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.epcs.com/Loyalty/zonaTerceros/WSLoyalty", name = "consultaEstadoTCRutMovilResponse")
    public JAXBElement<ConsultaEstadoTCRutMovilResponse> createConsultaEstadoTCRutMovilResponse(ConsultaEstadoTCRutMovilResponse value) {
        return new JAXBElement<ConsultaEstadoTCRutMovilResponse>(_ConsultaEstadoTCRutMovilResponse_QNAME, ConsultaEstadoTCRutMovilResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaEstadoTCRutResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.epcs.com/Loyalty/zonaTerceros/WSLoyalty", name = "consultaEstadoTCRutResponse")
    public JAXBElement<ConsultaEstadoTCRutResponse> createConsultaEstadoTCRutResponse(ConsultaEstadoTCRutResponse value) {
        return new JAXBElement<ConsultaEstadoTCRutResponse>(_ConsultaEstadoTCRutResponse_QNAME, ConsultaEstadoTCRutResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaDatosNoClienteResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.epcs.com/Loyalty/zonaTerceros/WSLoyalty", name = "consultaDatosNoClienteResponse")
    public JAXBElement<ConsultaDatosNoClienteResponse> createConsultaDatosNoClienteResponse(ConsultaDatosNoClienteResponse value) {
        return new JAXBElement<ConsultaDatosNoClienteResponse>(_ConsultaDatosNoClienteResponse_QNAME, ConsultaDatosNoClienteResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaEstadoTCMovil }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.epcs.com/Loyalty/zonaTerceros/WSLoyalty", name = "consultaEstadoTCMovil")
    public JAXBElement<ConsultaEstadoTCMovil> createConsultaEstadoTCMovil(ConsultaEstadoTCMovil value) {
        return new JAXBElement<ConsultaEstadoTCMovil>(_ConsultaEstadoTCMovil_QNAME, ConsultaEstadoTCMovil.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaEstadoTCMovilResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.epcs.com/Loyalty/zonaTerceros/WSLoyalty", name = "consultaEstadoTCMovilResponse")
    public JAXBElement<ConsultaEstadoTCMovilResponse> createConsultaEstadoTCMovilResponse(ConsultaEstadoTCMovilResponse value) {
        return new JAXBElement<ConsultaEstadoTCMovilResponse>(_ConsultaEstadoTCMovilResponse_QNAME, ConsultaEstadoTCMovilResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaEstadoTCRut }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.epcs.com/Loyalty/zonaTerceros/WSLoyalty", name = "consultaEstadoTCRut")
    public JAXBElement<ConsultaEstadoTCRut> createConsultaEstadoTCRut(ConsultaEstadoTCRut value) {
        return new JAXBElement<ConsultaEstadoTCRut>(_ConsultaEstadoTCRut_QNAME, ConsultaEstadoTCRut.class, null, value);
    }

}
