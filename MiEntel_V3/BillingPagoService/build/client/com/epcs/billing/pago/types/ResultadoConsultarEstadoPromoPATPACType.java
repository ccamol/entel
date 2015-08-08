
package com.epcs.billing.pago.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ResultadoConsultarEstadoPromoPATPACType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResultadoConsultarEstadoPromoPATPACType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="respuesta" type="{http://www.epcs.com/billing/pago/types}RespuestaType"/>
 *         &lt;element name="resultadoConsultaEstadoPromoPATPAC" type="{http://www.epcs.com/billing/pago/types}ResultadoConsultaPromoPATPACType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResultadoConsultarEstadoPromoPATPACType", propOrder = {
    "respuesta",
    "resultadoConsultaEstadoPromoPATPAC"
})
public class ResultadoConsultarEstadoPromoPATPACType {

    @XmlElement(required = true)
    protected RespuestaType respuesta;
    protected ResultadoConsultaPromoPATPACType resultadoConsultaEstadoPromoPATPAC;

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
     * Gets the value of the resultadoConsultaEstadoPromoPATPAC property.
     * 
     * @return
     *     possible object is
     *     {@link ResultadoConsultaPromoPATPACType }
     *     
     */
    public ResultadoConsultaPromoPATPACType getResultadoConsultaEstadoPromoPATPAC() {
        return resultadoConsultaEstadoPromoPATPAC;
    }

    /**
     * Sets the value of the resultadoConsultaEstadoPromoPATPAC property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResultadoConsultaPromoPATPACType }
     *     
     */
    public void setResultadoConsultaEstadoPromoPATPAC(ResultadoConsultaPromoPATPACType value) {
        this.resultadoConsultaEstadoPromoPATPAC = value;
    }

}
