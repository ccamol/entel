
package com.epcs.billing.pago;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import com.epcs.billing.pago.types.AuthType;
import com.epcs.billing.pago.types.ConsultarEstadoPATType;
import com.epcs.billing.pago.types.ConsultarEstadoPromoPATPACType;
import com.epcs.billing.pago.types.InscribirPATType;
import com.epcs.billing.pago.types.ResultadoConsultarEstadoPATType;
import com.epcs.billing.pago.types.ResultadoConsultarEstadoPromoPATPACType;
import com.epcs.billing.pago.types.ResultadoInscribirPATType;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.epcs.billing.pago package. 
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

    private final static QName _ResultadoConsultarEstadoPATDocument_QNAME = new QName("http://www.epcs.com/billing/pago", "ResultadoConsultarEstadoPATDocument");
    private final static QName _AuthHeaderDocument_QNAME = new QName("http://www.epcs.com/billing/pago", "AuthHeaderDocument");
    private final static QName _InscribirPATDocument_QNAME = new QName("http://www.epcs.com/billing/pago", "InscribirPATDocument");
    private final static QName _ConsultarEstadoPromoPATPACDocument_QNAME = new QName("http://www.epcs.com/billing/pago", "ConsultarEstadoPromoPATPACDocument");
    private final static QName _ResultadoInscribirPATDocument_QNAME = new QName("http://www.epcs.com/billing/pago", "ResultadoInscribirPATDocument");
    private final static QName _ResultadoConsultarEstadoPromoPATPACDocument_QNAME = new QName("http://www.epcs.com/billing/pago", "ResultadoConsultarEstadoPromoPATPACDocument");
    private final static QName _ConsultarEstadoPATDocument_QNAME = new QName("http://www.epcs.com/billing/pago", "ConsultarEstadoPATDocument");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.epcs.billing.pago
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResultadoConsultarEstadoPATType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.epcs.com/billing/pago", name = "ResultadoConsultarEstadoPATDocument")
    public JAXBElement<ResultadoConsultarEstadoPATType> createResultadoConsultarEstadoPATDocument(ResultadoConsultarEstadoPATType value) {
        return new JAXBElement<ResultadoConsultarEstadoPATType>(_ResultadoConsultarEstadoPATDocument_QNAME, ResultadoConsultarEstadoPATType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AuthType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.epcs.com/billing/pago", name = "AuthHeaderDocument")
    public JAXBElement<AuthType> createAuthHeaderDocument(AuthType value) {
        return new JAXBElement<AuthType>(_AuthHeaderDocument_QNAME, AuthType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InscribirPATType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.epcs.com/billing/pago", name = "InscribirPATDocument")
    public JAXBElement<InscribirPATType> createInscribirPATDocument(InscribirPATType value) {
        return new JAXBElement<InscribirPATType>(_InscribirPATDocument_QNAME, InscribirPATType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultarEstadoPromoPATPACType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.epcs.com/billing/pago", name = "ConsultarEstadoPromoPATPACDocument")
    public JAXBElement<ConsultarEstadoPromoPATPACType> createConsultarEstadoPromoPATPACDocument(ConsultarEstadoPromoPATPACType value) {
        return new JAXBElement<ConsultarEstadoPromoPATPACType>(_ConsultarEstadoPromoPATPACDocument_QNAME, ConsultarEstadoPromoPATPACType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResultadoInscribirPATType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.epcs.com/billing/pago", name = "ResultadoInscribirPATDocument")
    public JAXBElement<ResultadoInscribirPATType> createResultadoInscribirPATDocument(ResultadoInscribirPATType value) {
        return new JAXBElement<ResultadoInscribirPATType>(_ResultadoInscribirPATDocument_QNAME, ResultadoInscribirPATType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResultadoConsultarEstadoPromoPATPACType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.epcs.com/billing/pago", name = "ResultadoConsultarEstadoPromoPATPACDocument")
    public JAXBElement<ResultadoConsultarEstadoPromoPATPACType> createResultadoConsultarEstadoPromoPATPACDocument(ResultadoConsultarEstadoPromoPATPACType value) {
        return new JAXBElement<ResultadoConsultarEstadoPromoPATPACType>(_ResultadoConsultarEstadoPromoPATPACDocument_QNAME, ResultadoConsultarEstadoPromoPATPACType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultarEstadoPATType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.epcs.com/billing/pago", name = "ConsultarEstadoPATDocument")
    public JAXBElement<ConsultarEstadoPATType> createConsultarEstadoPATDocument(ConsultarEstadoPATType value) {
        return new JAXBElement<ConsultarEstadoPATType>(_ConsultarEstadoPATDocument_QNAME, ConsultarEstadoPATType.class, null, value);
    }

}
