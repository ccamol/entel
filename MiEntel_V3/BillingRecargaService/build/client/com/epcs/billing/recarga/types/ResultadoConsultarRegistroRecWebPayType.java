
package com.epcs.billing.recarga.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ResultadoConsultarRegistroRecWebPayType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResultadoConsultarRegistroRecWebPayType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="respuesta" type="{http://www.epcs.com/billing/recarga/types}RespuestaType"/>
 *         &lt;element name="resultadoConsultarRegRecWebPay" type="{http://www.epcs.com/billing/recarga/types}ResConsultarRegistroRecWebPayType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResultadoConsultarRegistroRecWebPayType", propOrder = {
    "respuesta",
    "resultadoConsultarRegRecWebPay"
})
public class ResultadoConsultarRegistroRecWebPayType {

    @XmlElement(required = true)
    protected RespuestaType respuesta;
    protected ResConsultarRegistroRecWebPayType resultadoConsultarRegRecWebPay;

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
     * Gets the value of the resultadoConsultarRegRecWebPay property.
     * 
     * @return
     *     possible object is
     *     {@link ResConsultarRegistroRecWebPayType }
     *     
     */
    public ResConsultarRegistroRecWebPayType getResultadoConsultarRegRecWebPay() {
        return resultadoConsultarRegRecWebPay;
    }

    /**
     * Sets the value of the resultadoConsultarRegRecWebPay property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResConsultarRegistroRecWebPayType }
     *     
     */
    public void setResultadoConsultarRegRecWebPay(ResConsultarRegistroRecWebPayType value) {
        this.resultadoConsultarRegRecWebPay = value;
    }

}
