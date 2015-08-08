
package com.epcs.inteligencianegocios.satisfaccioncliente.types;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EncuestasPerfiladasType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EncuestasPerfiladasType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cantidadUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="urls" type="{http://www.epcs.com/inteligencianegocios/satisfaccioncliente/types}UrlType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EncuestasPerfiladasType", propOrder = {
    "cantidadUrl",
    "urls"
})
public class EncuestasPerfiladasType {

    @XmlElement(required = true)
    protected String cantidadUrl;
    protected List<UrlType> urls;

    /**
     * Gets the value of the cantidadUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCantidadUrl() {
        return cantidadUrl;
    }

    /**
     * Sets the value of the cantidadUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCantidadUrl(String value) {
        this.cantidadUrl = value;
    }

    /**
     * Gets the value of the urls property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the urls property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUrls().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link UrlType }
     * 
     * 
     */
    public List<UrlType> getUrls() {
        if (urls == null) {
            urls = new ArrayList<UrlType>();
        }
        return this.urls;
    }

}
