
package com.epcs.billing.pago.types;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.epcs.billing.pago.types package. 
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

    private final static QName _AuthType_QNAME = new QName("http://www.epcs.com/billing/pago/types", "authType");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.epcs.billing.pago.types
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ResultadoConsultarEstadoPromoPATPACType }
     * 
     */
    public ResultadoConsultarEstadoPromoPATPACType createResultadoConsultarEstadoPromoPATPACType() {
        return new ResultadoConsultarEstadoPromoPATPACType();
    }

    /**
     * Create an instance of {@link ResultadoConsultaPromoPATPACType }
     * 
     */
    public ResultadoConsultaPromoPATPACType createResultadoConsultaPromoPATPACType() {
        return new ResultadoConsultaPromoPATPACType();
    }

    /**
     * Create an instance of {@link ConsultarEstadoPATType }
     * 
     */
    public ConsultarEstadoPATType createConsultarEstadoPATType() {
        return new ConsultarEstadoPATType();
    }

    /**
     * Create an instance of {@link RespuestaType }
     * 
     */
    public RespuestaType createRespuestaType() {
        return new RespuestaType();
    }

    /**
     * Create an instance of {@link ResultadoConsultaEstadoPATType }
     * 
     */
    public ResultadoConsultaEstadoPATType createResultadoConsultaEstadoPATType() {
        return new ResultadoConsultaEstadoPATType();
    }

    /**
     * Create an instance of {@link InscribirPATType }
     * 
     */
    public InscribirPATType createInscribirPATType() {
        return new InscribirPATType();
    }

    /**
     * Create an instance of {@link ResultadoConsultarEstadoPATType }
     * 
     */
    public ResultadoConsultarEstadoPATType createResultadoConsultarEstadoPATType() {
        return new ResultadoConsultarEstadoPATType();
    }

    /**
     * Create an instance of {@link ConsultarEstadoPromoPATPACType }
     * 
     */
    public ConsultarEstadoPromoPATPACType createConsultarEstadoPromoPATPACType() {
        return new ConsultarEstadoPromoPATPACType();
    }

    /**
     * Create an instance of {@link AuthType }
     * 
     */
    public AuthType createAuthType() {
        return new AuthType();
    }

    /**
     * Create an instance of {@link ResultadoInscribirPATType }
     * 
     */
    public ResultadoInscribirPATType createResultadoInscribirPATType() {
        return new ResultadoInscribirPATType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AuthType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.epcs.com/billing/pago/types", name = "authType")
    public JAXBElement<AuthType> createAuthType(AuthType value) {
        return new JAXBElement<AuthType>(_AuthType_QNAME, AuthType.class, null, value);
    }

}
