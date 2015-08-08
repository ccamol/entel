
package com.epcs.billing.bolsa.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ComprarBolsasOneShootSSCCType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ComprarBolsasOneShootSSCCType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="tipoMercado" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="msisdn" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="msisdnRegalo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codBolsa" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="valorBolsa" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ComprarBolsasOneShootSSCCType", propOrder = {
    "tipoMercado",
    "msisdn",
    "msisdnRegalo",
    "codBolsa",
    "valorBolsa"
})
public class ComprarBolsasOneShootSSCCType {

    @XmlElement(required = true)
    protected String tipoMercado;
    @XmlElement(required = true)
    protected String msisdn;
    @XmlElement(required = true)
    protected String msisdnRegalo;
    @XmlElement(required = true)
    protected String codBolsa;
    @XmlElement(required = true)
    protected String valorBolsa;

    /**
     * Gets the value of the tipoMercado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoMercado() {
        return tipoMercado;
    }

    /**
     * Sets the value of the tipoMercado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoMercado(String value) {
        this.tipoMercado = value;
    }

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
     * Gets the value of the msisdnRegalo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMsisdnRegalo() {
        return msisdnRegalo;
    }

    /**
     * Sets the value of the msisdnRegalo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMsisdnRegalo(String value) {
        this.msisdnRegalo = value;
    }

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
     * Gets the value of the valorBolsa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValorBolsa() {
        return valorBolsa;
    }

    /**
     * Sets the value of the valorBolsa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValorBolsa(String value) {
        this.valorBolsa = value;
    }

}
