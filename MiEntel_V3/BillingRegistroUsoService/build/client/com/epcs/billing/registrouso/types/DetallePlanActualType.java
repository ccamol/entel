
package com.epcs.billing.registrouso.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DetallePlanActualType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DetallePlanActualType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nombreTasacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="valorTasacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DetallePlanActualType", propOrder = {
    "nombreTasacion",
    "valorTasacion"
})
public class DetallePlanActualType {

    @XmlElement(required = true)
    protected String nombreTasacion;
    @XmlElement(required = true)
    protected String valorTasacion;

    /**
     * Gets the value of the nombreTasacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreTasacion() {
        return nombreTasacion;
    }

    /**
     * Sets the value of the nombreTasacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreTasacion(String value) {
        this.nombreTasacion = value;
    }

    /**
     * Gets the value of the valorTasacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValorTasacion() {
        return valorTasacion;
    }

    /**
     * Sets the value of the valorTasacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValorTasacion(String value) {
        this.valorTasacion = value;
    }

}
