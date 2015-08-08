
package com.epcs.cliente.perfil.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ResPlanContratadoPPType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResPlanContratadoPPType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="msisdn" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="imsi" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="iccid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="pin1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="puk1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="pin2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="puk2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="saldo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fechaActivacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fechaExpiracion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fechaDesactivacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cdgPlan" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dscPlan" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="estMovil" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fechaUltimoCambio" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numeroRecarga" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResPlanContratadoPPType", propOrder = {
    "msisdn",
    "imsi",
    "iccid",
    "pin1",
    "puk1",
    "pin2",
    "puk2",
    "saldo",
    "fechaActivacion",
    "fechaExpiracion",
    "fechaDesactivacion",
    "cdgPlan",
    "dscPlan",
    "estMovil",
    "fechaUltimoCambio",
    "numeroRecarga"
})
public class ResPlanContratadoPPType {

    @XmlElement(required = true)
    protected String msisdn;
    @XmlElement(required = true)
    protected String imsi;
    @XmlElement(required = true)
    protected String iccid;
    @XmlElement(required = true)
    protected String pin1;
    @XmlElement(required = true)
    protected String puk1;
    @XmlElement(required = true)
    protected String pin2;
    @XmlElement(required = true)
    protected String puk2;
    @XmlElement(required = true)
    protected String saldo;
    @XmlElement(required = true)
    protected String fechaActivacion;
    @XmlElement(required = true)
    protected String fechaExpiracion;
    @XmlElement(required = true)
    protected String fechaDesactivacion;
    @XmlElement(required = true)
    protected String cdgPlan;
    @XmlElement(required = true)
    protected String dscPlan;
    @XmlElement(required = true)
    protected String estMovil;
    @XmlElement(required = true)
    protected String fechaUltimoCambio;
    @XmlElement(required = true)
    protected String numeroRecarga;

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
     * Gets the value of the imsi property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImsi() {
        return imsi;
    }

    /**
     * Sets the value of the imsi property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImsi(String value) {
        this.imsi = value;
    }

    /**
     * Gets the value of the iccid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIccid() {
        return iccid;
    }

    /**
     * Sets the value of the iccid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIccid(String value) {
        this.iccid = value;
    }

    /**
     * Gets the value of the pin1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPin1() {
        return pin1;
    }

    /**
     * Sets the value of the pin1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPin1(String value) {
        this.pin1 = value;
    }

    /**
     * Gets the value of the puk1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPuk1() {
        return puk1;
    }

    /**
     * Sets the value of the puk1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPuk1(String value) {
        this.puk1 = value;
    }

    /**
     * Gets the value of the pin2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPin2() {
        return pin2;
    }

    /**
     * Sets the value of the pin2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPin2(String value) {
        this.pin2 = value;
    }

    /**
     * Gets the value of the puk2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPuk2() {
        return puk2;
    }

    /**
     * Sets the value of the puk2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPuk2(String value) {
        this.puk2 = value;
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
     * Gets the value of the fechaActivacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaActivacion() {
        return fechaActivacion;
    }

    /**
     * Sets the value of the fechaActivacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaActivacion(String value) {
        this.fechaActivacion = value;
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

    /**
     * Gets the value of the fechaDesactivacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaDesactivacion() {
        return fechaDesactivacion;
    }

    /**
     * Sets the value of the fechaDesactivacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaDesactivacion(String value) {
        this.fechaDesactivacion = value;
    }

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

    /**
     * Gets the value of the estMovil property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstMovil() {
        return estMovil;
    }

    /**
     * Sets the value of the estMovil property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstMovil(String value) {
        this.estMovil = value;
    }

    /**
     * Gets the value of the fechaUltimoCambio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaUltimoCambio() {
        return fechaUltimoCambio;
    }

    /**
     * Sets the value of the fechaUltimoCambio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaUltimoCambio(String value) {
        this.fechaUltimoCambio = value;
    }

    /**
     * Gets the value of the numeroRecarga property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroRecarga() {
        return numeroRecarga;
    }

    /**
     * Sets the value of the numeroRecarga property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroRecarga(String value) {
        this.numeroRecarga = value;
    }

}
