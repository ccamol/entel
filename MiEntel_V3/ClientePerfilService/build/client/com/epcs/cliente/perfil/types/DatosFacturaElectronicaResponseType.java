
package com.epcs.cliente.perfil.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DatosFacturaElectronicaResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DatosFacturaElectronicaResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="respuesta" type="{http://www.epcs.com/cliente/perfil/types}RespuestaType"/>
 *         &lt;element name="descGlosaPlan" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="correoActual" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DatosFacturaElectronicaResponseType", propOrder = {
    "respuesta",
    "descGlosaPlan",
    "correoActual"
})
public class DatosFacturaElectronicaResponseType {

    @XmlElement(required = true)
    protected RespuestaType respuesta;
    @XmlElement(required = true)
    protected String descGlosaPlan;
    @XmlElement(required = true)
    protected String correoActual;

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
     * Gets the value of the descGlosaPlan property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescGlosaPlan() {
        return descGlosaPlan;
    }

    /**
     * Sets the value of the descGlosaPlan property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescGlosaPlan(String value) {
        this.descGlosaPlan = value;
    }

    /**
     * Gets the value of the correoActual property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCorreoActual() {
        return correoActual;
    }

    /**
     * Sets the value of the correoActual property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCorreoActual(String value) {
        this.correoActual = value;
    }

}
