
package com.esa.clientes.perfilesclientes.sgacarterasvip.types;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.esa.clientes.perfilesclientes.sgacarterasvip.types package. 
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

    private final static QName _ConsultaEjecutivoFaultDocument_QNAME = new QName("http://www.esa.com/Clientes/PerfilesClientes/SGACarterasVIP/types", "ConsultaEjecutivoFaultDocument");
    private final static QName _ConsultaEjecutivoRequestDocument_QNAME = new QName("http://www.esa.com/Clientes/PerfilesClientes/SGACarterasVIP/types", "ConsultaEjecutivoRequestDocument");
    private final static QName _ConsultaEjecutivoResponseDocument_QNAME = new QName("http://www.esa.com/Clientes/PerfilesClientes/SGACarterasVIP/types", "ConsultaEjecutivoResponseDocument");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.esa.clientes.perfilesclientes.sgacarterasvip.types
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link EntradaConsultaEjecutivo }
     * 
     */
    public EntradaConsultaEjecutivo createEntradaConsultaEjecutivo() {
        return new EntradaConsultaEjecutivo();
    }

    /**
     * Create an instance of {@link SalidaConsultaEjecutivoType }
     * 
     */
    public SalidaConsultaEjecutivoType createSalidaConsultaEjecutivoType() {
        return new SalidaConsultaEjecutivoType();
    }

    /**
     * Create an instance of {@link FaultConsultaEjecutivoType }
     * 
     */
    public FaultConsultaEjecutivoType createFaultConsultaEjecutivoType() {
        return new FaultConsultaEjecutivoType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FaultConsultaEjecutivoType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.esa.com/Clientes/PerfilesClientes/SGACarterasVIP/types", name = "ConsultaEjecutivoFaultDocument")
    public JAXBElement<FaultConsultaEjecutivoType> createConsultaEjecutivoFaultDocument(FaultConsultaEjecutivoType value) {
        return new JAXBElement<FaultConsultaEjecutivoType>(_ConsultaEjecutivoFaultDocument_QNAME, FaultConsultaEjecutivoType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EntradaConsultaEjecutivo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.esa.com/Clientes/PerfilesClientes/SGACarterasVIP/types", name = "ConsultaEjecutivoRequestDocument")
    public JAXBElement<EntradaConsultaEjecutivo> createConsultaEjecutivoRequestDocument(EntradaConsultaEjecutivo value) {
        return new JAXBElement<EntradaConsultaEjecutivo>(_ConsultaEjecutivoRequestDocument_QNAME, EntradaConsultaEjecutivo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SalidaConsultaEjecutivoType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.esa.com/Clientes/PerfilesClientes/SGACarterasVIP/types", name = "ConsultaEjecutivoResponseDocument")
    public JAXBElement<SalidaConsultaEjecutivoType> createConsultaEjecutivoResponseDocument(SalidaConsultaEjecutivoType value) {
        return new JAXBElement<SalidaConsultaEjecutivoType>(_ConsultaEjecutivoResponseDocument_QNAME, SalidaConsultaEjecutivoType.class, null, value);
    }

}
