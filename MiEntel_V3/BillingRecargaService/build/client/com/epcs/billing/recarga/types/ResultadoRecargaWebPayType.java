
package com.epcs.billing.recarga.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ResultadoRecargaWebPayType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResultadoRecargaWebPayType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="respuesta" type="{http://www.epcs.com/billing/recarga/types}RespuestaType"/>
 *         &lt;element name="resultadoRecargaWebPay" type="{http://www.epcs.com/billing/recarga/types}ResultadoRecWebPayType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResultadoRecargaWebPayType", propOrder = {
    "respuesta",
    "resultadoRecargaWebPay"
})
public class ResultadoRecargaWebPayType {

    @XmlElement(required = true)
    protected RespuestaType respuesta;
    protected ResultadoRecWebPayType resultadoRecargaWebPay;

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
     * Gets the value of the resultadoRecargaWebPay property.
     * 
     * @return
     *     possible object is
     *     {@link ResultadoRecWebPayType }
     *     
     */
    public ResultadoRecWebPayType getResultadoRecargaWebPay() {
        return resultadoRecargaWebPay;
    }

    /**
     * Sets the value of the resultadoRecargaWebPay property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResultadoRecWebPayType }
     *     
     */
    public void setResultadoRecargaWebPay(ResultadoRecWebPayType value) {
        this.resultadoRecargaWebPay = value;
    }

}
