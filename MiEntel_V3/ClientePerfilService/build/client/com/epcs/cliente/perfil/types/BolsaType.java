
package com.epcs.cliente.perfil.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BolsaType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BolsaType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cdgSp" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="csdSn" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dscBolsa" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="observacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="costo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cantidad" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tipoBolsa" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tipoVigencia" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="vigencia" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BolsaType", propOrder = {
    "cdgSp",
    "csdSn",
    "dscBolsa",
    "observacion",
    "costo",
    "cantidad",
    "tipoBolsa",
    "tipoVigencia",
    "vigencia"
})
public class BolsaType {

    @XmlElement(required = true)
    protected String cdgSp;
    @XmlElement(required = true)
    protected String csdSn;
    @XmlElement(required = true)
    protected String dscBolsa;
    @XmlElement(required = true)
    protected String observacion;
    @XmlElement(required = true)
    protected String costo;
    @XmlElement(required = true)
    protected String cantidad;
    @XmlElement(required = true)
    protected String tipoBolsa;
    @XmlElement(required = true)
    protected String tipoVigencia;
    @XmlElement(required = true)
    protected String vigencia;

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
     * Gets the value of the observacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObservacion() {
        return observacion;
    }

    /**
     * Sets the value of the observacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObservacion(String value) {
        this.observacion = value;
    }

    /**
     * Gets the value of the costo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCosto() {
        return costo;
    }

    /**
     * Sets the value of the costo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCosto(String value) {
        this.costo = value;
    }

    /**
     * Gets the value of the cantidad property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCantidad() {
        return cantidad;
    }

    /**
     * Sets the value of the cantidad property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCantidad(String value) {
        this.cantidad = value;
    }

    /**
     * Gets the value of the tipoBolsa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoBolsa() {
        return tipoBolsa;
    }

    /**
     * Sets the value of the tipoBolsa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoBolsa(String value) {
        this.tipoBolsa = value;
    }

    /**
     * Gets the value of the tipoVigencia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoVigencia() {
        return tipoVigencia;
    }

    /**
     * Sets the value of the tipoVigencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoVigencia(String value) {
        this.tipoVigencia = value;
    }

    /**
     * Gets the value of the vigencia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVigencia() {
        return vigencia;
    }

    /**
     * Sets the value of the vigencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVigencia(String value) {
        this.vigencia = value;
    }

}
