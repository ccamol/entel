
package com.epcs.cliente.perfil.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DatosUsuarioResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DatosUsuarioResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="respuesta" type="{http://www.epcs.com/cliente/perfil/types}RespuestaType"/>
 *         &lt;element name="datosUsuario" type="{http://www.epcs.com/cliente/perfil/types}DatosUsuarioType"/>
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
@XmlType(name = "DatosUsuarioResponseType", propOrder = {
    "respuesta",
    "datosUsuario",
    "datosTitular"
})
public class DatosUsuarioResponseType {

    @XmlElement(required = true)
    protected RespuestaType respuesta;
    @XmlElement(required = true)
    protected DatosUsuarioType datosUsuario;
    @XmlElement(required = true)
    protected DatosTitularType datosTitular;

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
     *     {@link DatosUsuarioType }
     *     
     */
    public DatosUsuarioType getDatosUsuario() {
        return datosUsuario;
    }

    /**
     * Sets the value of the datosUsuario property.
     * 
     * @param value
     *     allowed object is
     *     {@link DatosUsuarioType }
     *     
     */
    public void setDatosUsuario(DatosUsuarioType value) {
        this.datosUsuario = value;
    }

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
