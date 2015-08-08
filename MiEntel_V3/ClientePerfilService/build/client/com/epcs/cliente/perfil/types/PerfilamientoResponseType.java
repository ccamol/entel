
package com.epcs.cliente.perfil.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PerfilamientoResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PerfilamientoResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="resultadoPerfilamiento" type="{http://www.epcs.com/cliente/perfil/types}ResultadoPerfilamientoType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PerfilamientoResponseType", propOrder = {
    "resultadoPerfilamiento"
})
public class PerfilamientoResponseType {

    @XmlElement(required = true)
    protected ResultadoPerfilamientoType resultadoPerfilamiento;

    /**
     * Gets the value of the resultadoPerfilamiento property.
     * 
     * @return
     *     possible object is
     *     {@link ResultadoPerfilamientoType }
     *     
     */
    public ResultadoPerfilamientoType getResultadoPerfilamiento() {
        return resultadoPerfilamiento;
    }

    /**
     * Sets the value of the resultadoPerfilamiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResultadoPerfilamientoType }
     *     
     */
    public void setResultadoPerfilamiento(ResultadoPerfilamientoType value) {
        this.resultadoPerfilamiento = value;
    }

}
