
package com.epcs.inteligencianegocios.satisfaccioncliente.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ResultadoConsultarEncuestasPerfiladasOrqType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResultadoConsultarEncuestasPerfiladasOrqType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="respuesta" type="{http://www.epcs.com/inteligencianegocios/satisfaccioncliente/types}RespuestaType"/>
 *         &lt;element name="cantidadUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="consultarEncuestasPerfiladasPS" type="{http://www.epcs.com/inteligencianegocios/satisfaccioncliente/types}EncuestasPerfiladasType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResultadoConsultarEncuestasPerfiladasOrqType", propOrder = {
    "respuesta",
    "cantidadUrl",
    "consultarEncuestasPerfiladasPS"
})
public class ResultadoConsultarEncuestasPerfiladasOrqType {

    @XmlElement(required = true)
    protected RespuestaType respuesta;
    protected String cantidadUrl;
    protected EncuestasPerfiladasType consultarEncuestasPerfiladasPS;

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
     * Gets the value of the cantidadUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCantidadUrl() {
        return cantidadUrl;
    }

    /**
     * Sets the value of the cantidadUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCantidadUrl(String value) {
        this.cantidadUrl = value;
    }

    /**
     * Gets the value of the consultarEncuestasPerfiladasPS property.
     * 
     * @return
     *     possible object is
     *     {@link EncuestasPerfiladasType }
     *     
     */
    public EncuestasPerfiladasType getConsultarEncuestasPerfiladasPS() {
        return consultarEncuestasPerfiladasPS;
    }

    /**
     * Sets the value of the consultarEncuestasPerfiladasPS property.
     * 
     * @param value
     *     allowed object is
     *     {@link EncuestasPerfiladasType }
     *     
     */
    public void setConsultarEncuestasPerfiladasPS(EncuestasPerfiladasType value) {
        this.consultarEncuestasPerfiladasPS = value;
    }

}
