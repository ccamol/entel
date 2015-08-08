
package com.esa.clientes.perfilesclientes.resumenlineasequiposcliente;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.esa.clientes.perfilesclientes.resumenlineasequiposcliente package. 
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

    private final static QName _GetResumen_QNAME = new QName("http://www.esa.com/Clientes/PerfilesClientes/resumenLineasEquiposCliente", "getResumen");
    private final static QName _GetResumenResponse_QNAME = new QName("http://www.esa.com/Clientes/PerfilesClientes/resumenLineasEquiposCliente", "getResumenResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.esa.clientes.perfilesclientes.resumenlineasequiposcliente
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ClienteDTO }
     * 
     */
    public ClienteDTO createClienteDTO() {
        return new ClienteDTO();
    }

    /**
     * Create an instance of {@link ResumenDTO }
     * 
     */
    public ResumenDTO createResumenDTO() {
        return new ResumenDTO();
    }

    /**
     * Create an instance of {@link GetResumenResponse }
     * 
     */
    public GetResumenResponse createGetResumenResponse() {
        return new GetResumenResponse();
    }

    /**
     * Create an instance of {@link GetResumen }
     * 
     */
    public GetResumen createGetResumen() {
        return new GetResumen();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetResumen }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.esa.com/Clientes/PerfilesClientes/resumenLineasEquiposCliente", name = "getResumen")
    public JAXBElement<GetResumen> createGetResumen(GetResumen value) {
        return new JAXBElement<GetResumen>(_GetResumen_QNAME, GetResumen.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetResumenResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.esa.com/Clientes/PerfilesClientes/resumenLineasEquiposCliente", name = "getResumenResponse")
    public JAXBElement<GetResumenResponse> createGetResumenResponse(GetResumenResponse value) {
        return new JAXBElement<GetResumenResponse>(_GetResumenResponse_QNAME, GetResumenResponse.class, null, value);
    }

}
