
package com.epcs.billing.registrouso.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DetalleLlamadosType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DetalleLlamadosType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="folio" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numeroIteraciones" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="parametro3" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="detalleLlamados" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DetalleLlamadosType", propOrder = {
    "folio",
    "numeroIteraciones",
    "parametro3",
    "detalleLlamados"
})
public class DetalleLlamadosType {

    @XmlElement(required = true)
    protected String folio;
    @XmlElement(required = true)
    protected String numeroIteraciones;
    @XmlElement(required = true)
    protected String parametro3;
    @XmlElement(required = true)
    protected String detalleLlamados;

    /**
     * Gets the value of the folio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFolio() {
        return folio;
    }

    /**
     * Sets the value of the folio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFolio(String value) {
        this.folio = value;
    }

    /**
     * Gets the value of the numeroIteraciones property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroIteraciones() {
        return numeroIteraciones;
    }

    /**
     * Sets the value of the numeroIteraciones property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroIteraciones(String value) {
        this.numeroIteraciones = value;
    }

    /**
     * Gets the value of the parametro3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParametro3() {
        return parametro3;
    }

    /**
     * Sets the value of the parametro3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParametro3(String value) {
        this.parametro3 = value;
    }

    /**
     * Gets the value of the detalleLlamados property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDetalleLlamados() {
        return detalleLlamados;
    }

    /**
     * Sets the value of the detalleLlamados property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDetalleLlamados(String value) {
        this.detalleLlamados = value;
    }

}
