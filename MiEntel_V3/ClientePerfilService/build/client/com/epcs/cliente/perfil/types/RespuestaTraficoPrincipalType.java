
package com.epcs.cliente.perfil.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RespuestaTraficoPrincipalType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RespuestaTraficoPrincipalType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ticked" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numeroParticiones" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="trafico" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RespuestaTraficoPrincipalType", propOrder = {
    "ticked",
    "numeroParticiones",
    "trafico"
})
public class RespuestaTraficoPrincipalType {

    @XmlElement(required = true)
    protected String ticked;
    @XmlElement(required = true)
    protected String numeroParticiones;
    @XmlElement(required = true)
    protected String trafico;

    /**
     * Gets the value of the ticked property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTicked() {
        return ticked;
    }

    /**
     * Sets the value of the ticked property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTicked(String value) {
        this.ticked = value;
    }

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
     * Gets the value of the trafico property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrafico() {
        return trafico;
    }

    /**
     * Sets the value of the trafico property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrafico(String value) {
        this.trafico = value;
    }

}
