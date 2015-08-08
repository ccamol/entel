
package com.epcs.cliente.perfil.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ProductosActualesType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProductosActualesType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="planActual" type="{http://www.epcs.com/cliente/perfil/types}PlanActualType"/>
 *         &lt;element name="bolsasActuales" type="{http://www.epcs.com/cliente/perfil/types}BolsasActualesType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProductosActualesType", propOrder = {
    "planActual",
    "bolsasActuales"
})
public class ProductosActualesType {

    @XmlElement(required = true)
    protected PlanActualType planActual;
    @XmlElement(required = true)
    protected BolsasActualesType bolsasActuales;

    /**
     * Gets the value of the planActual property.
     * 
     * @return
     *     possible object is
     *     {@link PlanActualType }
     *     
     */
    public PlanActualType getPlanActual() {
        return planActual;
    }

    /**
     * Sets the value of the planActual property.
     * 
     * @param value
     *     allowed object is
     *     {@link PlanActualType }
     *     
     */
    public void setPlanActual(PlanActualType value) {
        this.planActual = value;
    }

    /**
     * Gets the value of the bolsasActuales property.
     * 
     * @return
     *     possible object is
     *     {@link BolsasActualesType }
     *     
     */
    public BolsasActualesType getBolsasActuales() {
        return bolsasActuales;
    }

    /**
     * Sets the value of the bolsasActuales property.
     * 
     * @param value
     *     allowed object is
     *     {@link BolsasActualesType }
     *     
     */
    public void setBolsasActuales(BolsasActualesType value) {
        this.bolsasActuales = value;
    }

}
