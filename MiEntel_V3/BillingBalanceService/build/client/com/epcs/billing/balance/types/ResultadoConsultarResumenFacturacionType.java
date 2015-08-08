
package com.epcs.billing.balance.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ResultadoConsultarResumenFacturacionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResultadoConsultarResumenFacturacionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="respuesta" type="{http://www.epcs.com/billing/balance/types}RespuestaType"/>
 *         &lt;element name="resumenFacturas" type="{http://www.epcs.com/billing/balance/types}ResumenFacturasType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResultadoConsultarResumenFacturacionType", propOrder = {
    "respuesta",
    "resumenFacturas"
})
public class ResultadoConsultarResumenFacturacionType {

    @XmlElement(required = true)
    protected RespuestaType respuesta;
    @XmlElement(required = true)
    protected ResumenFacturasType resumenFacturas;

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
     * Gets the value of the resumenFacturas property.
     * 
     * @return
     *     possible object is
     *     {@link ResumenFacturasType }
     *     
     */
    public ResumenFacturasType getResumenFacturas() {
        return resumenFacturas;
    }

    /**
     * Sets the value of the resumenFacturas property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResumenFacturasType }
     *     
     */
    public void setResumenFacturas(ResumenFacturasType value) {
        this.resumenFacturas = value;
    }

}
