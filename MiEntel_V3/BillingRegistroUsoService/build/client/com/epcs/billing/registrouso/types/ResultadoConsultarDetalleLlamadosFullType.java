
package com.epcs.billing.registrouso.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ResultadoConsultarDetalleLlamadosFullType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResultadoConsultarDetalleLlamadosFullType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="respuesta" type="{http://www.epcs.com/billing/registrouso/types}RespuestaType"/>
 *         &lt;element name="detalleLlamadosFull" type="{http://www.epcs.com/billing/registrouso/types}DetalleLlamadosFullType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResultadoConsultarDetalleLlamadosFullType", propOrder = {
    "respuesta",
    "detalleLlamadosFull"
})
public class ResultadoConsultarDetalleLlamadosFullType {

    @XmlElement(required = true)
    protected RespuestaType respuesta;
    @XmlElement(required = true)
    protected DetalleLlamadosFullType detalleLlamadosFull;

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
     * Gets the value of the detalleLlamadosFull property.
     * 
     * @return
     *     possible object is
     *     {@link DetalleLlamadosFullType }
     *     
     */
    public DetalleLlamadosFullType getDetalleLlamadosFull() {
        return detalleLlamadosFull;
    }

    /**
     * Sets the value of the detalleLlamadosFull property.
     * 
     * @param value
     *     allowed object is
     *     {@link DetalleLlamadosFullType }
     *     
     */
    public void setDetalleLlamadosFull(DetalleLlamadosFullType value) {
        this.detalleLlamadosFull = value;
    }

}
