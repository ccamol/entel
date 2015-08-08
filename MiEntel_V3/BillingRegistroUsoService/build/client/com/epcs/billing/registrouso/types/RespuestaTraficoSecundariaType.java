
package com.epcs.billing.registrouso.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RespuestaTraficoSecundariaType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RespuestaTraficoSecundariaType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="numeroParticiones" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="traficoParticion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RespuestaTraficoSecundariaType", propOrder = {
    "numeroParticiones",
    "traficoParticion"
})
public class RespuestaTraficoSecundariaType {

    @XmlElement(required = true)
    protected String numeroParticiones;
    @XmlElement(required = true)
    protected String traficoParticion;

    /**
     * Gets the value of the numeroParticiones property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroParticiones() {
        return numeroParticiones;
    }

    /**
     * Sets the value of the numeroParticiones property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroParticiones(String value) {
        this.numeroParticiones = value;
    }

    /**
     * Gets the value of the traficoParticion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTraficoParticion() {
        return traficoParticion;
    }

    /**
     * Sets the value of the traficoParticion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTraficoParticion(String value) {
        this.traficoParticion = value;
    }

}
