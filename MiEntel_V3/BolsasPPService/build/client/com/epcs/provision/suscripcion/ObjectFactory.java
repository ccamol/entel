
package com.epcs.provision.suscripcion;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import com.epcs.provision.suscripcion.types.ComprarRequestType;
import com.epcs.provision.suscripcion.types.ComprarResponseType;
import com.epcs.provision.suscripcion.types.ComprarServiceFaultType;
import com.epcs.provision.suscripcion.types.ListarBolsasActivasBAMRequestType;
import com.epcs.provision.suscripcion.types.ListarBolsasActivasBAMResponseType;
import com.epcs.provision.suscripcion.types.ListarBolsasActivasBAMServiceFaultType;
import com.epcs.provision.suscripcion.types.ListarBolsasActivasRequestType;
import com.epcs.provision.suscripcion.types.ListarBolsasActivasResponseType;
import com.epcs.provision.suscripcion.types.ListarBolsasActivasServiceFaultType;
import com.epcs.provision.suscripcion.types.ListarBolsasDisponiblesBAMRequestType;
import com.epcs.provision.suscripcion.types.ListarBolsasDisponiblesBAMResponseType;
import com.epcs.provision.suscripcion.types.ListarBolsasDisponiblesBAMServiceFaultType;
import com.epcs.provision.suscripcion.types.ListarBolsasRequestType;
import com.epcs.provision.suscripcion.types.ListarBolsasResponseType;
import com.epcs.provision.suscripcion.types.ListarBolsasServiceFaultType;
import com.epcs.provision.suscripcion.types.RegalarRequestType;
import com.epcs.provision.suscripcion.types.RegalarResponseType;
import com.epcs.provision.suscripcion.types.RegalarServiceFaultType;
import com.epcs.provision.suscripcion.types.ValidacionCompraRequestType;
import com.epcs.provision.suscripcion.types.ValidacionCompraResponseType;
import com.epcs.provision.suscripcion.types.ValidacionCompraServiceFaultType;
import com.epcs.provision.suscripcion.types.ValidacionRegaloRequestType;
import com.epcs.provision.suscripcion.types.ValidacionRegaloResponseType;
import com.epcs.provision.suscripcion.types.ValidacionRegaloServiceFaultType;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.epcs.provision.suscripcion package. 
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

    private final static QName _ListarBolsasResponseDocument_QNAME = new QName("http://www.epcs.com/Provision/Suscripcion", "listarBolsasResponseDocument");
    private final static QName _ComprarRequestDocument_QNAME = new QName("http://www.epcs.com/Provision/Suscripcion", "comprarRequestDocument");
    private final static QName _ComprarServiceFaultDocument_QNAME = new QName("http://www.epcs.com/Provision/Suscripcion", "comprarServiceFaultDocument");
    private final static QName _ValidacionRegaloResponseDocument_QNAME = new QName("http://www.epcs.com/Provision/Suscripcion", "validacionRegaloResponseDocument");
    private final static QName _ListarBolsasActivasServiceFaultDocument_QNAME = new QName("http://www.epcs.com/Provision/Suscripcion", "listarBolsasActivasServiceFaultDocument");
    private final static QName _RegalarResponseDocument_QNAME = new QName("http://www.epcs.com/Provision/Suscripcion", "regalarResponseDocument");
    private final static QName _ListarBolsasActivasBAMResponseDocument_QNAME = new QName("http://www.epcs.com/Provision/Suscripcion", "listarBolsasActivasBAMResponseDocument");
    private final static QName _ValidacionCompraResponseDocument_QNAME = new QName("http://www.epcs.com/Provision/Suscripcion", "validacionCompraResponseDocument");
    private final static QName _RegalarRequestDocument_QNAME = new QName("http://www.epcs.com/Provision/Suscripcion", "regalarRequestDocument");
    private final static QName _ListarBolsasActivasBAMRequestDocument_QNAME = new QName("http://www.epcs.com/Provision/Suscripcion", "listarBolsasActivasBAMRequestDocument");
    private final static QName _ListarBolsasDisponiblesBAMServiceFaultDocument_QNAME = new QName("http://www.epcs.com/Provision/Suscripcion", "listarBolsasDisponiblesBAMServiceFaultDocument");
    private final static QName _ValidacionRegaloRequestDocument_QNAME = new QName("http://www.epcs.com/Provision/Suscripcion", "validacionRegaloRequestDocument");
    private final static QName _ListarBolsasActivasResponseDocument_QNAME = new QName("http://www.epcs.com/Provision/Suscripcion", "listarBolsasActivasResponseDocument");
    private final static QName _ListarBolsasServiceFaultDocument_QNAME = new QName("http://www.epcs.com/Provision/Suscripcion", "listarBolsasServiceFaultDocument");
    private final static QName _ListarBolsasRequestDocument_QNAME = new QName("http://www.epcs.com/Provision/Suscripcion", "listarBolsasRequestDocument");
    private final static QName _ValidacionCompraServiceFaultDocument_QNAME = new QName("http://www.epcs.com/Provision/Suscripcion", "validacionCompraServiceFaultDocument");
    private final static QName _ListarBolsasActivasBAMServiceFaultDocument_QNAME = new QName("http://www.epcs.com/Provision/Suscripcion", "listarBolsasActivasBAMServiceFaultDocument");
    private final static QName _ListarBolsasActivasRequestDocument_QNAME = new QName("http://www.epcs.com/Provision/Suscripcion", "listarBolsasActivasRequestDocument");
    private final static QName _ValidacionRegaloServiceFaultDocument_QNAME = new QName("http://www.epcs.com/Provision/Suscripcion", "validacionRegaloServiceFaultDocument");
    private final static QName _RegalarServiceFaultDocument_QNAME = new QName("http://www.epcs.com/Provision/Suscripcion", "regalarServiceFaultDocument");
    private final static QName _ListarBolsasDisponiblesBAMRequestDocument_QNAME = new QName("http://www.epcs.com/Provision/Suscripcion", "listarBolsasDisponiblesBAMRequestDocument");
    private final static QName _ComprarResponseDocument_QNAME = new QName("http://www.epcs.com/Provision/Suscripcion", "comprarResponseDocument");
    private final static QName _ValidacionCompraRequestDocument_QNAME = new QName("http://www.epcs.com/Provision/Suscripcion", "validacionCompraRequestDocument");
    private final static QName _ListarBolsasDisponiblesBAMResponseDocument_QNAME = new QName("http://www.epcs.com/Provision/Suscripcion", "listarBolsasDisponiblesBAMResponseDocument");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.epcs.provision.suscripcion
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarBolsasResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.epcs.com/Provision/Suscripcion", name = "listarBolsasResponseDocument")
    public JAXBElement<ListarBolsasResponseType> createListarBolsasResponseDocument(ListarBolsasResponseType value) {
        return new JAXBElement<ListarBolsasResponseType>(_ListarBolsasResponseDocument_QNAME, ListarBolsasResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ComprarRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.epcs.com/Provision/Suscripcion", name = "comprarRequestDocument")
    public JAXBElement<ComprarRequestType> createComprarRequestDocument(ComprarRequestType value) {
        return new JAXBElement<ComprarRequestType>(_ComprarRequestDocument_QNAME, ComprarRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ComprarServiceFaultType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.epcs.com/Provision/Suscripcion", name = "comprarServiceFaultDocument")
    public JAXBElement<ComprarServiceFaultType> createComprarServiceFaultDocument(ComprarServiceFaultType value) {
        return new JAXBElement<ComprarServiceFaultType>(_ComprarServiceFaultDocument_QNAME, ComprarServiceFaultType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidacionRegaloResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.epcs.com/Provision/Suscripcion", name = "validacionRegaloResponseDocument")
    public JAXBElement<ValidacionRegaloResponseType> createValidacionRegaloResponseDocument(ValidacionRegaloResponseType value) {
        return new JAXBElement<ValidacionRegaloResponseType>(_ValidacionRegaloResponseDocument_QNAME, ValidacionRegaloResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarBolsasActivasServiceFaultType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.epcs.com/Provision/Suscripcion", name = "listarBolsasActivasServiceFaultDocument")
    public JAXBElement<ListarBolsasActivasServiceFaultType> createListarBolsasActivasServiceFaultDocument(ListarBolsasActivasServiceFaultType value) {
        return new JAXBElement<ListarBolsasActivasServiceFaultType>(_ListarBolsasActivasServiceFaultDocument_QNAME, ListarBolsasActivasServiceFaultType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegalarResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.epcs.com/Provision/Suscripcion", name = "regalarResponseDocument")
    public JAXBElement<RegalarResponseType> createRegalarResponseDocument(RegalarResponseType value) {
        return new JAXBElement<RegalarResponseType>(_RegalarResponseDocument_QNAME, RegalarResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarBolsasActivasBAMResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.epcs.com/Provision/Suscripcion", name = "listarBolsasActivasBAMResponseDocument")
    public JAXBElement<ListarBolsasActivasBAMResponseType> createListarBolsasActivasBAMResponseDocument(ListarBolsasActivasBAMResponseType value) {
        return new JAXBElement<ListarBolsasActivasBAMResponseType>(_ListarBolsasActivasBAMResponseDocument_QNAME, ListarBolsasActivasBAMResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidacionCompraResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.epcs.com/Provision/Suscripcion", name = "validacionCompraResponseDocument")
    public JAXBElement<ValidacionCompraResponseType> createValidacionCompraResponseDocument(ValidacionCompraResponseType value) {
        return new JAXBElement<ValidacionCompraResponseType>(_ValidacionCompraResponseDocument_QNAME, ValidacionCompraResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegalarRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.epcs.com/Provision/Suscripcion", name = "regalarRequestDocument")
    public JAXBElement<RegalarRequestType> createRegalarRequestDocument(RegalarRequestType value) {
        return new JAXBElement<RegalarRequestType>(_RegalarRequestDocument_QNAME, RegalarRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarBolsasActivasBAMRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.epcs.com/Provision/Suscripcion", name = "listarBolsasActivasBAMRequestDocument")
    public JAXBElement<ListarBolsasActivasBAMRequestType> createListarBolsasActivasBAMRequestDocument(ListarBolsasActivasBAMRequestType value) {
        return new JAXBElement<ListarBolsasActivasBAMRequestType>(_ListarBolsasActivasBAMRequestDocument_QNAME, ListarBolsasActivasBAMRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarBolsasDisponiblesBAMServiceFaultType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.epcs.com/Provision/Suscripcion", name = "listarBolsasDisponiblesBAMServiceFaultDocument")
    public JAXBElement<ListarBolsasDisponiblesBAMServiceFaultType> createListarBolsasDisponiblesBAMServiceFaultDocument(ListarBolsasDisponiblesBAMServiceFaultType value) {
        return new JAXBElement<ListarBolsasDisponiblesBAMServiceFaultType>(_ListarBolsasDisponiblesBAMServiceFaultDocument_QNAME, ListarBolsasDisponiblesBAMServiceFaultType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidacionRegaloRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.epcs.com/Provision/Suscripcion", name = "validacionRegaloRequestDocument")
    public JAXBElement<ValidacionRegaloRequestType> createValidacionRegaloRequestDocument(ValidacionRegaloRequestType value) {
        return new JAXBElement<ValidacionRegaloRequestType>(_ValidacionRegaloRequestDocument_QNAME, ValidacionRegaloRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarBolsasActivasResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.epcs.com/Provision/Suscripcion", name = "listarBolsasActivasResponseDocument")
    public JAXBElement<ListarBolsasActivasResponseType> createListarBolsasActivasResponseDocument(ListarBolsasActivasResponseType value) {
        return new JAXBElement<ListarBolsasActivasResponseType>(_ListarBolsasActivasResponseDocument_QNAME, ListarBolsasActivasResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarBolsasServiceFaultType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.epcs.com/Provision/Suscripcion", name = "listarBolsasServiceFaultDocument")
    public JAXBElement<ListarBolsasServiceFaultType> createListarBolsasServiceFaultDocument(ListarBolsasServiceFaultType value) {
        return new JAXBElement<ListarBolsasServiceFaultType>(_ListarBolsasServiceFaultDocument_QNAME, ListarBolsasServiceFaultType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarBolsasRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.epcs.com/Provision/Suscripcion", name = "listarBolsasRequestDocument")
    public JAXBElement<ListarBolsasRequestType> createListarBolsasRequestDocument(ListarBolsasRequestType value) {
        return new JAXBElement<ListarBolsasRequestType>(_ListarBolsasRequestDocument_QNAME, ListarBolsasRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidacionCompraServiceFaultType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.epcs.com/Provision/Suscripcion", name = "validacionCompraServiceFaultDocument")
    public JAXBElement<ValidacionCompraServiceFaultType> createValidacionCompraServiceFaultDocument(ValidacionCompraServiceFaultType value) {
        return new JAXBElement<ValidacionCompraServiceFaultType>(_ValidacionCompraServiceFaultDocument_QNAME, ValidacionCompraServiceFaultType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarBolsasActivasBAMServiceFaultType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.epcs.com/Provision/Suscripcion", name = "listarBolsasActivasBAMServiceFaultDocument")
    public JAXBElement<ListarBolsasActivasBAMServiceFaultType> createListarBolsasActivasBAMServiceFaultDocument(ListarBolsasActivasBAMServiceFaultType value) {
        return new JAXBElement<ListarBolsasActivasBAMServiceFaultType>(_ListarBolsasActivasBAMServiceFaultDocument_QNAME, ListarBolsasActivasBAMServiceFaultType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarBolsasActivasRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.epcs.com/Provision/Suscripcion", name = "listarBolsasActivasRequestDocument")
    public JAXBElement<ListarBolsasActivasRequestType> createListarBolsasActivasRequestDocument(ListarBolsasActivasRequestType value) {
        return new JAXBElement<ListarBolsasActivasRequestType>(_ListarBolsasActivasRequestDocument_QNAME, ListarBolsasActivasRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidacionRegaloServiceFaultType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.epcs.com/Provision/Suscripcion", name = "validacionRegaloServiceFaultDocument")
    public JAXBElement<ValidacionRegaloServiceFaultType> createValidacionRegaloServiceFaultDocument(ValidacionRegaloServiceFaultType value) {
        return new JAXBElement<ValidacionRegaloServiceFaultType>(_ValidacionRegaloServiceFaultDocument_QNAME, ValidacionRegaloServiceFaultType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegalarServiceFaultType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.epcs.com/Provision/Suscripcion", name = "regalarServiceFaultDocument")
    public JAXBElement<RegalarServiceFaultType> createRegalarServiceFaultDocument(RegalarServiceFaultType value) {
        return new JAXBElement<RegalarServiceFaultType>(_RegalarServiceFaultDocument_QNAME, RegalarServiceFaultType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarBolsasDisponiblesBAMRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.epcs.com/Provision/Suscripcion", name = "listarBolsasDisponiblesBAMRequestDocument")
    public JAXBElement<ListarBolsasDisponiblesBAMRequestType> createListarBolsasDisponiblesBAMRequestDocument(ListarBolsasDisponiblesBAMRequestType value) {
        return new JAXBElement<ListarBolsasDisponiblesBAMRequestType>(_ListarBolsasDisponiblesBAMRequestDocument_QNAME, ListarBolsasDisponiblesBAMRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ComprarResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.epcs.com/Provision/Suscripcion", name = "comprarResponseDocument")
    public JAXBElement<ComprarResponseType> createComprarResponseDocument(ComprarResponseType value) {
        return new JAXBElement<ComprarResponseType>(_ComprarResponseDocument_QNAME, ComprarResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidacionCompraRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.epcs.com/Provision/Suscripcion", name = "validacionCompraRequestDocument")
    public JAXBElement<ValidacionCompraRequestType> createValidacionCompraRequestDocument(ValidacionCompraRequestType value) {
        return new JAXBElement<ValidacionCompraRequestType>(_ValidacionCompraRequestDocument_QNAME, ValidacionCompraRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarBolsasDisponiblesBAMResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.epcs.com/Provision/Suscripcion", name = "listarBolsasDisponiblesBAMResponseDocument")
    public JAXBElement<ListarBolsasDisponiblesBAMResponseType> createListarBolsasDisponiblesBAMResponseDocument(ListarBolsasDisponiblesBAMResponseType value) {
        return new JAXBElement<ListarBolsasDisponiblesBAMResponseType>(_ListarBolsasDisponiblesBAMResponseDocument_QNAME, ListarBolsasDisponiblesBAMResponseType.class, null, value);
    }

}
