
package com.epcs.billing.balance.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DocumentoFacturaOut complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DocumentoFacturaOut">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="folioDocumento" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="estadoDocumento" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DocumentoFacturaOut", propOrder = {
    "folioDocumento",
    "estadoDocumento"
})
public class DocumentoFacturaOut {

    @XmlElement(required = true)
    protected String folioDocumento;
    @XmlElement(required = true)
    protected String estadoDocumento;

    /**
     * Gets the value of the folioDocumento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFolioDocumento() {
        return folioDocumento;
    }

    /**
     * Sets the value of the folioDocumento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFolioDocumento(String value) {
        this.folioDocumento = value;
    }

    /**
     * Gets the value of the estadoDocumento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstadoDocumento() {
        return estadoDocumento;
    }

    /**
     * Sets the value of the estadoDocumento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstadoDocumento(String value) {
        this.estadoDocumento = value;
    }

}
