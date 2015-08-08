
package com.epcs.cliente.orden;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import com.epcs.cliente.orden.types.AuthType;
import com.epcs.cliente.orden.types.ConsultarEstadoReposicionType;
import com.epcs.cliente.orden.types.ConsultarValidarReposicionType;
import com.epcs.cliente.orden.types.ReposicionServicioMQType;
import com.epcs.cliente.orden.types.ReposicionServicioOrqType;
import com.epcs.cliente.orden.types.ResultadoConsultarEstadoReposicionType;
import com.epcs.cliente.orden.types.ResultadoReposicionServicioMQType;
import com.epcs.cliente.orden.types.ResultadoReposicionServicioOrqType;
import com.epcs.cliente.orden.types.ResultadoValidarReposicionType;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.epcs.cliente.orden package. 
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

    private final static QName _ReposicionServicioOrqDocument_QNAME = new QName("http://www.epcs.com/cliente/orden", "ReposicionServicioOrqDocument");
    private final static QName _ReposicionServicioMQDocument_QNAME = new QName("http://www.epcs.com/cliente/orden", "ReposicionServicioMQDocument");
    private final static QName _AuthHeaderDocument_QNAME = new QName("http://www.epcs.com/cliente/orden", "AuthHeaderDocument");
    private final static QName _ResultadoConsultarEstadoReposicionDocument_QNAME = new QName("http://www.epcs.com/cliente/orden", "ResultadoConsultarEstadoReposicionDocument");
    private final static QName _ResultadoReposicionServicioOrqDocument_QNAME = new QName("http://www.epcs.com/cliente/orden", "ResultadoReposicionServicioOrqDocument");
    private final static QName _ConsultarEstadoReposicionDocument_QNAME = new QName("http://www.epcs.com/cliente/orden", "ConsultarEstadoReposicionDocument");
    private final static QName _ResultadoValidarReposicionDocument_QNAME = new QName("http://www.epcs.com/cliente/orden", "ResultadoValidarReposicionDocument");
    private final static QName _ResultadoReposicionServicioMQDocument_QNAME = new QName("http://www.epcs.com/cliente/orden", "ResultadoReposicionServicioMQDocument");
    private final static QName _ConsultarValidarReposicionDocument_QNAME = new QName("http://www.epcs.com/cliente/orden", "ConsultarValidarReposicionDocument");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.epcs.cliente.orden
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReposicionServicioOrqType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.epcs.com/cliente/orden", name = "ReposicionServicioOrqDocument")
    public JAXBElement<ReposicionServicioOrqType> createReposicionServicioOrqDocument(ReposicionServicioOrqType value) {
        return new JAXBElement<ReposicionServicioOrqType>(_ReposicionServicioOrqDocument_QNAME, ReposicionServicioOrqType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReposicionServicioMQType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.epcs.com/cliente/orden", name = "ReposicionServicioMQDocument")
    public JAXBElement<ReposicionServicioMQType> createReposicionServicioMQDocument(ReposicionServicioMQType value) {
        return new JAXBElement<ReposicionServicioMQType>(_ReposicionServicioMQDocument_QNAME, ReposicionServicioMQType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AuthType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.epcs.com/cliente/orden", name = "AuthHeaderDocument")
    public JAXBElement<AuthType> createAuthHeaderDocument(AuthType value) {
        return new JAXBElement<AuthType>(_AuthHeaderDocument_QNAME, AuthType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResultadoConsultarEstadoReposicionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.epcs.com/cliente/orden", name = "ResultadoConsultarEstadoReposicionDocument")
    public JAXBElement<ResultadoConsultarEstadoReposicionType> createResultadoConsultarEstadoReposicionDocument(ResultadoConsultarEstadoReposicionType value) {
        return new JAXBElement<ResultadoConsultarEstadoReposicionType>(_ResultadoConsultarEstadoReposicionDocument_QNAME, ResultadoConsultarEstadoReposicionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResultadoReposicionServicioOrqType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.epcs.com/cliente/orden", name = "ResultadoReposicionServicioOrqDocument")
    public JAXBElement<ResultadoReposicionServicioOrqType> createResultadoReposicionServicioOrqDocument(ResultadoReposicionServicioOrqType value) {
        return new JAXBElement<ResultadoReposicionServicioOrqType>(_ResultadoReposicionServicioOrqDocument_QNAME, ResultadoReposicionServicioOrqType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultarEstadoReposicionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.epcs.com/cliente/orden", name = "ConsultarEstadoReposicionDocument")
    public JAXBElement<ConsultarEstadoReposicionType> createConsultarEstadoReposicionDocument(ConsultarEstadoReposicionType value) {
        return new JAXBElement<ConsultarEstadoReposicionType>(_ConsultarEstadoReposicionDocument_QNAME, ConsultarEstadoReposicionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResultadoValidarReposicionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.epcs.com/cliente/orden", name = "ResultadoValidarReposicionDocument")
    public JAXBElement<ResultadoValidarReposicionType> createResultadoValidarReposicionDocument(ResultadoValidarReposicionType value) {
        return new JAXBElement<ResultadoValidarReposicionType>(_ResultadoValidarReposicionDocument_QNAME, ResultadoValidarReposicionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResultadoReposicionServicioMQType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.epcs.com/cliente/orden", name = "ResultadoReposicionServicioMQDocument")
    public JAXBElement<ResultadoReposicionServicioMQType> createResultadoReposicionServicioMQDocument(ResultadoReposicionServicioMQType value) {
        return new JAXBElement<ResultadoReposicionServicioMQType>(_ResultadoReposicionServicioMQDocument_QNAME, ResultadoReposicionServicioMQType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultarValidarReposicionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.epcs.com/cliente/orden", name = "ConsultarValidarReposicionDocument")
    public JAXBElement<ConsultarValidarReposicionType> createConsultarValidarReposicionDocument(ConsultarValidarReposicionType value) {
        return new JAXBElement<ConsultarValidarReposicionType>(_ConsultarValidarReposicionDocument_QNAME, ConsultarValidarReposicionType.class, null, value);
    }

}
