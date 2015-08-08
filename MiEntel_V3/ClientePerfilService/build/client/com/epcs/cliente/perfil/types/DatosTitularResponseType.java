
package com.epcs.cliente.perfil.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DatosTitularResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DatosTitularResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="datosTitular" type="{http://www.epcs.com/cliente/perfil/types}DatosTitularType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DatosTitularResponseType", propOrder = {
    "datosTitular"
})
public class DatosTitularResponseType {

    @XmlElement(required = true)
    protected DatosTitularType datosTitular;

    /**
     * Gets the value of the datosTitular property.
     * 
     * @return
     *     possible object is
     *     {@link DatosTitularType }
     *     
     */
    public DatosTitularType getDatosTitular() {
        return datosTitular;
    }

    /**
     * Sets the value of the datosTitular property.
     * 
     * @param value
     *     allowed object is
     *     {@link DatosTitularType }
     *     
     */
    public void setDatosTitular(DatosTitularType value) {
        this.datosTitular = value;
    }

}
