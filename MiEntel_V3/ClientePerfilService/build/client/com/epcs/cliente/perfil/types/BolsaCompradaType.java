
package com.epcs.cliente.perfil.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BolsaCompradaType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BolsaCompradaType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cdgSp" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="csdSn" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dscBolsa" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "BolsaCompradaType", propOrder = {
    "cdgSp",
    "csdSn",
    "dscBolsa",
    "saldo",
    "fechaExpiracion"
})
public class BolsaCompradaType {

    @XmlElement(required = true)
    protected String cdgSp;
    @XmlElement(required = true)
    protected String csdSn;
    @XmlElement(required = true)
    protected String dscBolsa;
    @XmlElement(required = true)
    protected String saldo;
    @XmlElement(required = true)
    protected String fechaExpiracion;

    /**
     * Gets the value of the cdgSp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCdgSp() {
        return cdgSp;
    }

    /**
     * Sets the value of the cdgSp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCdgSp(String value) {
        this.cdgSp = value;
    }

    /**
     * Gets the value of the csdSn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCsdSn() {
        return csdSn;
    }

    /**
     * Sets the value of the csdSn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCsdSn(String value) {
        this.csdSn = value;
    }

    /**
     * Gets the value of the dscBolsa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDscBolsa() {
        return dscBolsa;
    }

    /**
     * Sets the value of the dscBolsa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDscBolsa(String value) {
        this.dscBolsa = value;
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
