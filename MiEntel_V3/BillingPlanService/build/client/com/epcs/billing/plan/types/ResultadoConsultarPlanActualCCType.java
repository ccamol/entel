
package com.epcs.billing.plan.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ResultadoConsultarPlanActualCCType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResultadoConsultarPlanActualCCType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="respuesta" type="{http://www.epcs.com/billing/plan/types}RespuestaType"/>
 *         &lt;element name="planActual" type="{http://www.epcs.com/billing/plan/types}PlanActualCCType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResultadoConsultarPlanActualCCType", propOrder = {
    "respuesta",
    "planActual"
})
public class ResultadoConsultarPlanActualCCType {

    @XmlElement(required = true)
    protected RespuestaType respuesta;
    protected PlanActualCCType planActual;

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
     * Gets the value of the planActual property.
     * 
     * @return
     *     possible object is
     *     {@link PlanActualCCType }
     *     
     */
    public PlanActualCCType getPlanActual() {
        return planActual;
    }

    /**
     * Sets the value of the planActual property.
     * 
     * @param value
     *     allowed object is
     *     {@link PlanActualCCType }
     *     
     */
    public void setPlanActual(PlanActualCCType value) {
        this.planActual = value;
    }

}
