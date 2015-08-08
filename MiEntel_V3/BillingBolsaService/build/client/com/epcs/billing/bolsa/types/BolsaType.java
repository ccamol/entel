
package com.epcs.billing.bolsa.types;

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
 *         &lt;element name="spCodigo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="snCodigo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nombreBolsa" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="descBolsa" type="{http://www.epcs.com/billing/bolsa/types}ItemDescBolsaType"/>
 *         &lt;element name="observacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="costo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cantidad" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="flagPromocion" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "spCodigo",
    "snCodigo",
    "nombreBolsa",
    "descBolsa",
    "observacion",
    "costo",
    "cantidad",
    "flagPromocion",
    "tipoBolsa",
    "tipoVigencia",
    "vigencia"
})
public class BolsaType {

    @XmlElement(required = true)
    protected String spCodigo;
    @XmlElement(required = true)
    protected String snCodigo;
    @XmlElement(required = true)
    protected String nombreBolsa;
    @XmlElement(required = true)
    protected ItemDescBolsaType descBolsa;
    @XmlElement(required = true)
    protected String observacion;
    @XmlElement(required = true)
    protected String costo;
    @XmlElement(required = true)
    protected String cantidad;
    @XmlElement(required = true)
    protected String flagPromocion;
    @XmlElement(required = true)
    protected String tipoBolsa;
    @XmlElement(required = true)
    protected String tipoVigencia;
    @XmlElement(required = true)
    protected String vigencia;

    /**
     * Gets the value of the spCodigo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpCodigo() {
        return spCodigo;
    }

    /**
     * Sets the value of the spCodigo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpCodigo(String value) {
        this.spCodigo = value;
    }

    /**
     * Gets the value of the snCodigo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSnCodigo() {
        return snCodigo;
    }

    /**
     * Sets the value of the snCodigo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSnCodigo(String value) {
        this.snCodigo = value;
    }

    /**
     * Gets the value of the nombreBolsa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreBolsa() {
        return nombreBolsa;
    }

    /**
     * Sets the value of the nombreBolsa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreBolsa(String value) {
        this.nombreBolsa = value;
    }

    /**
     * Gets the value of the descBolsa property.
     * 
     * @return
     *     possible object is
     *     {@link ItemDescBolsaType }
     *     
     */
    public ItemDescBolsaType getDescBolsa() {
        return descBolsa;
    }

    /**
     * Sets the value of the descBolsa property.
     * 
     * @param value
     *     allowed object is
     *     {@link ItemDescBolsaType }
     *     
     */
    public void setDescBolsa(ItemDescBolsaType value) {
        this.descBolsa = value;
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
     * Gets the value of the flagPromocion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFlagPromocion() {
        return flagPromocion;
    }

    /**
     * Sets the value of the flagPromocion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFlagPromocion(String value) {
        this.flagPromocion = value;
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
