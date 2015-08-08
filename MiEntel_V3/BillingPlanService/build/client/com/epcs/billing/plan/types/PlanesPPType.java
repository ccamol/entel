
package com.epcs.billing.plan.types;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PlanesPPType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PlanesPPType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idTarifa" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="estado" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="costoCambioPlan" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="flagVisible" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="flagFrecuente" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nombrePlan" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="slots" type="{http://www.epcs.com/billing/plan/types}SlotsType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="script" type="{http://www.epcs.com/billing/plan/types}ScriptType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PlanesPPType", propOrder = {
    "idTarifa",
    "estado",
    "costoCambioPlan",
    "flagVisible",
    "flagFrecuente",
    "nombrePlan",
    "slots",
    "script"
})
public class PlanesPPType {

    @XmlElement(required = true)
    protected String idTarifa;
    @XmlElement(required = true)
    protected String estado;
    @XmlElement(required = true)
    protected String costoCambioPlan;
    @XmlElement(required = true)
    protected String flagVisible;
    @XmlElement(required = true)
    protected String flagFrecuente;
    @XmlElement(required = true)
    protected String nombrePlan;
    protected List<SlotsType> slots;
    protected ScriptType script;

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
     * Gets the value of the estado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Sets the value of the estado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstado(String value) {
        this.estado = value;
    }

    /**
     * Gets the value of the costoCambioPlan property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCostoCambioPlan() {
        return costoCambioPlan;
    }

    /**
     * Sets the value of the costoCambioPlan property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCostoCambioPlan(String value) {
        this.costoCambioPlan = value;
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
     * Gets the value of the flagFrecuente property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFlagFrecuente() {
        return flagFrecuente;
    }

    /**
     * Sets the value of the flagFrecuente property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFlagFrecuente(String value) {
        this.flagFrecuente = value;
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
     * Gets the value of the slots property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the slots property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSlots().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SlotsType }
     * 
     * 
     */
    public List<SlotsType> getSlots() {
        if (slots == null) {
            slots = new ArrayList<SlotsType>();
        }
        return this.slots;
    }

    /**
     * Gets the value of the script property.
     * 
     * @return
     *     possible object is
     *     {@link ScriptType }
     *     
     */
    public ScriptType getScript() {
        return script;
    }

    /**
     * Sets the value of the script property.
     * 
     * @param value
     *     allowed object is
     *     {@link ScriptType }
     *     
     */
    public void setScript(ScriptType value) {
        this.script = value;
    }

}
