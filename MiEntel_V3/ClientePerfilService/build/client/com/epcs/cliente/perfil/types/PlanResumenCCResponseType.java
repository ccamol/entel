
package com.epcs.cliente.perfil.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PlanResumenCCResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PlanResumenCCResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="resultadoPlanResumenCC" type="{http://www.epcs.com/cliente/perfil/types}ResultadoPlanResumenCCType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PlanResumenCCResponseType", propOrder = {
    "resultadoPlanResumenCC"
})
public class PlanResumenCCResponseType {

    @XmlElement(required = true)
    protected ResultadoPlanResumenCCType resultadoPlanResumenCC;

    /**
     * Gets the value of the resultadoPlanResumenCC property.
     * 
     * @return
     *     possible object is
     *     {@link ResultadoPlanResumenCCType }
     *     
     */
    public ResultadoPlanResumenCCType getResultadoPlanResumenCC() {
        return resultadoPlanResumenCC;
    }

    /**
     * Sets the value of the resultadoPlanResumenCC property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResultadoPlanResumenCCType }
     *     
     */
    public void setResultadoPlanResumenCC(ResultadoPlanResumenCCType value) {
        this.resultadoPlanResumenCC = value;
    }

}
