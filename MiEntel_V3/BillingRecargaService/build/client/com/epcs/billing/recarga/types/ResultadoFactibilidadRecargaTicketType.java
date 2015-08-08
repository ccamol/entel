
package com.epcs.billing.recarga.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ResultadoFactibilidadRecargaTicketType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResultadoFactibilidadRecargaTicketType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="respuesta" type="{http://www.epcs.com/billing/recarga/types}RespuestaType"/>
 *         &lt;element name="resultadoFactibilidadRecargaTicket" type="{http://www.epcs.com/billing/recarga/types}ResultadoRecargaTicketType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResultadoFactibilidadRecargaTicketType", propOrder = {
    "respuesta",
    "resultadoFactibilidadRecargaTicket"
})
public class ResultadoFactibilidadRecargaTicketType {

    @XmlElement(required = true)
    protected RespuestaType respuesta;
    protected ResultadoRecargaTicketType resultadoFactibilidadRecargaTicket;

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
     * Gets the value of the resultadoFactibilidadRecargaTicket property.
     * 
     * @return
     *     possible object is
     *     {@link ResultadoRecargaTicketType }
     *     
     */
    public ResultadoRecargaTicketType getResultadoFactibilidadRecargaTicket() {
        return resultadoFactibilidadRecargaTicket;
    }

    /**
     * Sets the value of the resultadoFactibilidadRecargaTicket property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResultadoRecargaTicketType }
     *     
     */
    public void setResultadoFactibilidadRecargaTicket(ResultadoRecargaTicketType value) {
        this.resultadoFactibilidadRecargaTicket = value;
    }

}
