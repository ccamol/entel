
package com.epcs.billing.registrouso.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ResultadoConsultarDetalleLlamadosType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResultadoConsultarDetalleLlamadosType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="respuesta" type="{http://www.epcs.com/billing/registrouso/types}RespuestaType"/>
 *         &lt;element name="detalleLlamados" type="{http://www.epcs.com/billing/registrouso/types}DetalleLlamadosType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResultadoConsultarDetalleLlamadosType", propOrder = {
    "respuesta",
    "detalleLlamados"
})
public class ResultadoConsultarDetalleLlamadosType {

    @XmlElement(required = true)
    protected RespuestaType respuesta;
    @XmlElement(required = true)
    protected DetalleLlamadosType detalleLlamados;

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
     * Gets the value of the detalleLlamados property.
     * 
     * @return
     *     possible object is
     *     {@link DetalleLlamadosType }
     *     
     */
    public DetalleLlamadosType getDetalleLlamados() {
        return detalleLlamados;
    }

    /**
     * Sets the value of the detalleLlamados property.
     * 
     * @param value
     *     allowed object is
     *     {@link DetalleLlamadosType }
     *     
     */
    public void setDetalleLlamados(DetalleLlamadosType value) {
        this.detalleLlamados = value;
    }

}
