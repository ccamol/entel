
package com.epcs.cliente.perfil.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OrqMisDatosResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OrqMisDatosResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="respuesta" type="{http://www.epcs.com/cliente/perfil/types}RespuestaType"/>
 *         &lt;element name="datosUsuario" type="{http://www.epcs.com/cliente/perfil/types}OrqDatosUsuarioType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrqMisDatosResponseType", propOrder = {
    "respuesta",
    "datosUsuario"
})
public class OrqMisDatosResponseType {

    @XmlElement(required = true)
    protected RespuestaType respuesta;
    protected OrqDatosUsuarioType datosUsuario;

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
     * Gets the value of the datosUsuario property.
     * 
     * @return
     *     possible object is
     *     {@link OrqDatosUsuarioType }
     *     
     */
    public OrqDatosUsuarioType getDatosUsuario() {
        return datosUsuario;
    }

    /**
     * Sets the value of the datosUsuario property.
     * 
     * @param value
     *     allowed object is
     *     {@link OrqDatosUsuarioType }
     *     
     */
    public void setDatosUsuario(OrqDatosUsuarioType value) {
        this.datosUsuario = value;
    }

}
