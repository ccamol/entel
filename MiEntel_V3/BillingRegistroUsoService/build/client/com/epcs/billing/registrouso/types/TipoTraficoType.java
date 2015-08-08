
package com.epcs.billing.registrouso.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TipoTraficoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TipoTraficoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idPatron" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="patron" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="patronSecundario" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="descPatron" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="flagMostrar" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TipoTraficoType", propOrder = {
    "idPatron",
    "patron",
    "patronSecundario",
    "descPatron",
    "flagMostrar"
})
public class TipoTraficoType {

    @XmlElement(required = true)
    protected String idPatron;
    @XmlElement(required = true)
    protected String patron;
    @XmlElement(required = true)
    protected String patronSecundario;
    @XmlElement(required = true)
    protected String descPatron;
    @XmlElement(required = true)
    protected String flagMostrar;

    /**
     * Gets the value of the idPatron property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdPatron() {
        return idPatron;
    }

    /**
     * Sets the value of the idPatron property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdPatron(String value) {
        this.idPatron = value;
    }

    /**
     * Gets the value of the patron property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPatron() {
        return patron;
    }

    /**
     * Sets the value of the patron property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPatron(String value) {
        this.patron = value;
    }

    /**
     * Gets the value of the patronSecundario property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPatronSecundario() {
        return patronSecundario;
    }

    /**
     * Sets the value of the patronSecundario property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPatronSecundario(String value) {
        this.patronSecundario = value;
    }

    /**
     * Gets the value of the descPatron property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescPatron() {
        return descPatron;
    }

    /**
     * Sets the value of the descPatron property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescPatron(String value) {
        this.descPatron = value;
    }

    /**
     * Gets the value of the flagMostrar property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFlagMostrar() {
        return flagMostrar;
    }

    /**
     * Sets the value of the flagMostrar property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFlagMostrar(String value) {
        this.flagMostrar = value;
    }

}
