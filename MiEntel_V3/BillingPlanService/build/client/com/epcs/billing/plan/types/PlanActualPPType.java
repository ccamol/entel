
package com.epcs.billing.plan.types;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PlanActualPPType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PlanActualPPType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idTarifa" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tipoPlan" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="flagVisible" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nombrePlan" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="descripcionPlan" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numerosFavoritos" type="{http://www.epcs.com/billing/plan/types}NumerosFavoritosPPType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="numerosFrecuentes" type="{http://www.epcs.com/billing/plan/types}NumerosFrecuentesPPType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PlanActualPPType", propOrder = {
    "idTarifa",
    "tipoPlan",
    "flagVisible",
    "nombrePlan",
    "descripcionPlan",
    "numerosFavoritos",
    "numerosFrecuentes"
})
public class PlanActualPPType {

    @XmlElement(required = true)
    protected String idTarifa;
    @XmlElement(required = true)
    protected String tipoPlan;
    @XmlElement(required = true)
    protected String flagVisible;
    @XmlElement(required = true)
    protected String nombrePlan;
    @XmlElement(required = true)
    protected String descripcionPlan;
    protected List<NumerosFavoritosPPType> numerosFavoritos;
    protected List<NumerosFrecuentesPPType> numerosFrecuentes;

    /**
     * Gets the value of the idTarifa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdTarifa() {
        return idTarifa;
    }

    /**
     * Sets the value of the idTarifa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdTarifa(String value) {
        this.idTarifa = value;
    }

    /**
     * Gets the value of the tipoPlan property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoPlan() {
        return tipoPlan;
    }

    /**
     * Sets the value of the tipoPlan property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoPlan(String value) {
        this.tipoPlan = value;
    }

    /**
     * Gets the value of the flagVisible property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFlagVisible() {
        return flagVisible;
    }

    /**
     * Sets the value of the flagVisible property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFlagVisible(String value) {
        this.flagVisible = value;
    }

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
     * Gets the value of the descripcionPlan property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcionPlan() {
        return descripcionPlan;
    }

    /**
     * Sets the value of the descripcionPlan property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcionPlan(String value) {
        this.descripcionPlan = value;
    }

    /**
     * Gets the value of the numerosFavoritos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the numerosFavoritos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNumerosFavoritos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NumerosFavoritosPPType }
     * 
     * 
     */
    public List<NumerosFavoritosPPType> getNumerosFavoritos() {
        if (numerosFavoritos == null) {
            numerosFavoritos = new ArrayList<NumerosFavoritosPPType>();
        }
        return this.numerosFavoritos;
    }

    /**
     * Gets the value of the numerosFrecuentes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the numerosFrecuentes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNumerosFrecuentes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NumerosFrecuentesPPType }
     * 
     * 
     */
    public List<NumerosFrecuentesPPType> getNumerosFrecuentes() {
        if (numerosFrecuentes == null) {
            numerosFrecuentes = new ArrayList<NumerosFrecuentesPPType>();
        }
        return this.numerosFrecuentes;
    }

}
