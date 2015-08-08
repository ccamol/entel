
package com.epcs.cliente.perfil.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RegionesType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RegionesType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idRegion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nombreRegion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RegionesType", propOrder = {
    "idRegion",
    "nombreRegion"
})
public class RegionesType {

    @XmlElement(required = true)
    protected String idRegion;
    @XmlElement(required = true)
    protected String nombreRegion;

    /**
     * Gets the value of the idRegion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdRegion() {
        return idRegion;
    }

    /**
     * Sets the value of the idRegion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdRegion(String value) {
        this.idRegion = value;
    }

    /**
     * Gets the value of the nombreRegion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreRegion() {
        return nombreRegion;
    }

    /**
     * Sets the value of the nombreRegion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreRegion(String value) {
        this.nombreRegion = value;
    }

}
