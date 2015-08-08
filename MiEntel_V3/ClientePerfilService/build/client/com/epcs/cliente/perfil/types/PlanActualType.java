
package com.epcs.cliente.perfil.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PlanActualType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PlanActualType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cdgPlan" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dscPlan" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PlanActualType", propOrder = {
    "cdgPlan",
    "dscPlan"
})
public class PlanActualType {

    @XmlElement(required = true)
    protected String cdgPlan;
    @XmlElement(required = true)
    protected String dscPlan;

    /**
     * Gets the value of the cdgPlan property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCdgPlan() {
        return cdgPlan;
    }

    /**
     * Sets the value of the cdgPlan property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCdgPlan(String value) {
        this.cdgPlan = value;
    }

    /**
     * Gets the value of the dscPlan property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDscPlan() {
        return dscPlan;
    }

    /**
     * Sets the value of the dscPlan property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDscPlan(String value) {
        this.dscPlan = value;
    }

}
