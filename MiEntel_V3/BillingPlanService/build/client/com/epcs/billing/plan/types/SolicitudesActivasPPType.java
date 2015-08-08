
package com.epcs.billing.plan.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SolicitudesActivasPPType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SolicitudesActivasPPType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="msisdn" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fechaSolicitud" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="descripcionPlan" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="msisdnEnviaSolicitud" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="msisdnRecibeSolicitud" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SolicitudesActivasPPType", propOrder = {
    "msisdn",
    "fechaSolicitud",
    "descripcionPlan",
    "msisdnEnviaSolicitud",
    "msisdnRecibeSolicitud"
})
public class SolicitudesActivasPPType {

    @XmlElement(required = true)
    protected String msisdn;
    @XmlElement(required = true)
    protected String fechaSolicitud;
    @XmlElement(required = true)
    protected String descripcionPlan;
    @XmlElement(required = true)
    protected String msisdnEnviaSolicitud;
    @XmlElement(required = true)
    protected String msisdnRecibeSolicitud;

    /**
     * Gets the value of the msisdn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMsisdn() {
        return msisdn;
    }

    /**
     * Sets the value of the msisdn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMsisdn(String value) {
        this.msisdn = value;
    }

    /**
     * Gets the value of the fechaSolicitud property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaSolicitud() {
        return fechaSolicitud;
    }

    /**
     * Sets the value of the fechaSolicitud property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaSolicitud(String value) {
        this.fechaSolicitud = value;
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
     * Gets the value of the msisdnEnviaSolicitud property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMsisdnEnviaSolicitud() {
        return msisdnEnviaSolicitud;
    }

    /**
     * Sets the value of the msisdnEnviaSolicitud property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMsisdnEnviaSolicitud(String value) {
        this.msisdnEnviaSolicitud = value;
    }

    /**
     * Gets the value of the msisdnRecibeSolicitud property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMsisdnRecibeSolicitud() {
        return msisdnRecibeSolicitud;
    }

    /**
     * Sets the value of the msisdnRecibeSolicitud property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMsisdnRecibeSolicitud(String value) {
        this.msisdnRecibeSolicitud = value;
    }

}
