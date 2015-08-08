
package com.epcs.cliente.perfil.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ResPlanResumenPPType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResPlanResumenPPType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nombrePlan" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="saldo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fechaExpiracion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResPlanResumenPPType", propOrder = {
    "nombrePlan",
    "saldo",
    "fechaExpiracion"
})
public class ResPlanResumenPPType {

    @XmlElement(required = true)
    protected String nombrePlan;
    @XmlElement(required = true)
    protected String saldo;
    @XmlElement(required = true)
    protected String fechaExpiracion;

    /**
     * Gets the value of the nombrePlan property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombrePlan() {
        return nombrePlan;
    }

    /**
     * Sets the value of the nombrePlan property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombrePlan(String value) {
        this.nombrePlan = value;
    }

    /**
     * Gets the value of the saldo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSaldo() {
        return saldo;
    }

    /**
     * Sets the value of the saldo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSaldo(String value) {
        this.saldo = value;
    }

    /**
     * Gets the value of the fechaExpiracion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaExpiracion() {
        return fechaExpiracion;
    }

    /**
     * Sets the value of the fechaExpiracion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaExpiracion(String value) {
        this.fechaExpiracion = value;
    }

}
