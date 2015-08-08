
package com.epcs.billing.recarga.types;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FactibilidadRecargaTicketType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FactibilidadRecargaTicketType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="metodo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="plataforma" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tipoOperacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codUnicoTransaccion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codLocal" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="msisdnRecarga" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="montoRecarga" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numeroEntelTicket" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="montoTicket" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="montoRecarga" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codigoBono" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="validezRecarga" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="origenPeticion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="canalServicio" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="agenciaTicket" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="folioTicket" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FactibilidadRecargaTicketType", propOrder = {
    "content"
})
public class FactibilidadRecargaTicketType {

    @XmlElementRefs({
        @XmlElementRef(name = "folioTicket", namespace = "http://www.epcs.com/billing/recarga/types", type = JAXBElement.class),
        @XmlElementRef(name = "tipoOperacion", namespace = "http://www.epcs.com/billing/recarga/types", type = JAXBElement.class),
        @XmlElementRef(name = "agenciaTicket", namespace = "http://www.epcs.com/billing/recarga/types", type = JAXBElement.class),
        @XmlElementRef(name = "msisdnRecarga", namespace = "http://www.epcs.com/billing/recarga/types", type = JAXBElement.class),
        @XmlElementRef(name = "origenPeticion", namespace = "http://www.epcs.com/billing/recarga/types", type = JAXBElement.class),
        @XmlElementRef(name = "numeroEntelTicket", namespace = "http://www.epcs.com/billing/recarga/types", type = JAXBElement.class),
        @XmlElementRef(name = "metodo", namespace = "http://www.epcs.com/billing/recarga/types", type = JAXBElement.class),
        @XmlElementRef(name = "validezRecarga", namespace = "http://www.epcs.com/billing/recarga/types", type = JAXBElement.class),
        @XmlElementRef(name = "montoTicket", namespace = "http://www.epcs.com/billing/recarga/types", type = JAXBElement.class),
        @XmlElementRef(name = "codigoBono", namespace = "http://www.epcs.com/billing/recarga/types", type = JAXBElement.class),
        @XmlElementRef(name = "canalServicio", namespace = "http://www.epcs.com/billing/recarga/types", type = JAXBElement.class),
        @XmlElementRef(name = "codUnicoTransaccion", namespace = "http://www.epcs.com/billing/recarga/types", type = JAXBElement.class),
        @XmlElementRef(name = "plataforma", namespace = "http://www.epcs.com/billing/recarga/types", type = JAXBElement.class),
        @XmlElementRef(name = "codLocal", namespace = "http://www.epcs.com/billing/recarga/types", type = JAXBElement.class),
        @XmlElementRef(name = "montoRecarga", namespace = "http://www.epcs.com/billing/recarga/types", type = JAXBElement.class)
    })
    protected List<JAXBElement<String>> content;

    /**
     * Gets the rest of the content model. 
     * 
     * <p>
     * You are getting this "catch-all" property because of the following reason: 
     * The field name "MontoRecarga" is used by two different parts of a schema. See: 
     * line 42 of file:/D:/entel-dev-it2/projects/BillingRecargaService/wsdl/BillingRecargaService.xsd
     * line 39 of file:/D:/entel-dev-it2/projects/BillingRecargaService/wsdl/BillingRecargaService.xsd
     * <p>
     * To get rid of this property, apply a property customization to one 
     * of both of the following declarations to change their names: 
     * Gets the value of the content property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the content property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * 
     */
    public List<JAXBElement<String>> getContent() {
        if (content == null) {
            content = new ArrayList<JAXBElement<String>>();
        }
        return this.content;
    }

}
