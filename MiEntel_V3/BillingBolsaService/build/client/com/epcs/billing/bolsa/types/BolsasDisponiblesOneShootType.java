
package com.epcs.billing.bolsa.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BolsasDisponiblesOneShootType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BolsasDisponiblesOneShootType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codBolsa" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nombreBolsa" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cantidad" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cartaServicio" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="costo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="descBolsa" type="{http://www.epcs.com/billing/bolsa/types}ItemDescBolsaType"/>
 *         &lt;element name="tipoBolsa" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BolsasDisponiblesOneShootType", propOrder = {
    "codBolsa",
    "nombreBolsa",
    "cantidad",
    "cartaServicio",
    "costo",
    "descBolsa",
    "tipoBolsa"
})
public class BolsasDisponiblesOneShootType {

    @XmlElement(required = true)
    protected String codBolsa;
    @XmlElement(required = true)
    protected String nombreBolsa;
    @XmlElement(required = true)
    protected String cantidad;
    @XmlElement(required = true)
    protected String cartaServicio;
    @XmlElement(required = true)
    protected String costo;
    @XmlElement(required = true)
    protected ItemDescBolsaType descBolsa;
    @XmlElement(required = true)
    protected String tipoBolsa;

    /**
     * Gets the value of the codBolsa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodBolsa() {
        return codBolsa;
    }

    /**
     * Sets the value of the codBolsa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodBolsa(String value) {
        this.codBolsa = value;
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
     * Gets the value of the cartaServicio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCartaServicio() {
        return cartaServicio;
    }

    /**
     * Sets the value of the cartaServicio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCartaServicio(String value) {
        this.cartaServicio = value;
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

}
