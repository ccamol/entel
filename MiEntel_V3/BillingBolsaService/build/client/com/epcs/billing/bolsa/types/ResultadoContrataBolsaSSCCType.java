
package com.epcs.billing.bolsa.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ResultadoContrataBolsaSSCCType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResultadoContrataBolsaSSCCType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="respuesta" type="{http://www.epcs.com/billing/bolsa/types}RespuestaType"/>
 *         &lt;element name="tipoActivacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cantidad " type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResultadoContrataBolsaSSCCType", propOrder = {
    "respuesta",
    "tipoActivacion",
    "cantidad0020"
})
public class ResultadoContrataBolsaSSCCType {

    @XmlElement(required = true)
    protected RespuestaType respuesta;
    @XmlElement(required = true)
    protected String tipoActivacion;
    @XmlElement(name = "cantidad ", required = true)
    protected String cantidad0020;

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
     * Gets the value of the tipoActivacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoActivacion() {
        return tipoActivacion;
    }

    /**
     * Sets the value of the tipoActivacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoActivacion(String value) {
        this.tipoActivacion = value;
    }

    /**
     * Gets the value of the cantidad0020 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCantidad_0020() {
        return cantidad0020;
    }

    /**
     * Sets the value of the cantidad0020 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCantidad_0020(String value) {
        this.cantidad0020 = value;
    }

}
