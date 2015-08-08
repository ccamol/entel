
package com.epcs.cliente.perfil.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ResultadoPerfilamientoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResultadoPerfilamientoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="respuesta" type="{http://www.epcs.com/cliente/perfil/types}RespuestaType"/>
 *         &lt;element name="resumenPerfilamiento" type="{http://www.epcs.com/cliente/perfil/types}ResumenPerfilamientoType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResultadoPerfilamientoType", propOrder = {
    "respuesta",
    "resumenPerfilamiento"
})
public class ResultadoPerfilamientoType {

    @XmlElement(required = true)
    protected RespuestaType respuesta;
    protected ResumenPerfilamientoType resumenPerfilamiento;

    /**
     * Gets the value of the respuesta property.
     * 
     * @return
     *     possible object is
     *     {@link RespuestaType }
     *     
     */
    public RespuestaType getRespuesta() {
        return respuesta;
    }

    /**
     * Sets the value of the respuesta property.
     * 
     * @param value
     *     allowed object is
     *     {@link RespuestaType }
     *     
     */
    public void setRespuesta(RespuestaType value) {
        this.respuesta = value;
    }

    /**
     * Gets the value of the resumenPerfilamiento property.
     * 
     * @return
     *     possible object is
     *     {@link ResumenPerfilamientoType }
     *     
     */
    public ResumenPerfilamientoType getResumenPerfilamiento() {
        return resumenPerfilamiento;
    }

    /**
     * Sets the value of the resumenPerfilamiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResumenPerfilamientoType }
     *     
     */
    public void setResumenPerfilamiento(ResumenPerfilamientoType value) {
        this.resumenPerfilamiento = value;
    }

}
