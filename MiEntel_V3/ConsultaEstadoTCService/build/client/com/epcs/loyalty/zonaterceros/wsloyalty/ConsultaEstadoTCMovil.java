
package com.epcs.loyalty.zonaterceros.wsloyalty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for consultaEstadoTCMovil complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="consultaEstadoTCMovil">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Movil" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultaEstadoTCMovil", propOrder = {
    "movil"
})
public class ConsultaEstadoTCMovil {

    @XmlElement(name = "Movil")
    protected int movil;

    /**
     * Gets the value of the movil property.
     * 
     */
    public int getMovil() {
        return movil;
    }

    /**
     * Sets the value of the movil property.
     * 
     */
    public void setMovil(int value) {
        this.movil = value;
    }

}
