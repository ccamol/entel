
package com.epcs.billing.bolsa.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ContrataBolsaSSCCType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ContrataBolsaSSCCType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="msisdn" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codBolsa" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="valorBolsa" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="opcionActivacionBolsa" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ContrataBolsaSSCCType", propOrder = {
    "msisdn",
    "codBolsa",
    "valorBolsa",
    "opcionActivacionBolsa"
})
public class ContrataBolsaSSCCType {

    @XmlElement(required = true)
    protected String msisdn;
    @XmlElement(required = true)
    protected String codBolsa;
    @XmlElement(required = true)
    protected String valorBolsa;
    @XmlElement(required = true)
    protected String opcionActivacionBolsa;

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

    /**
     * Gets the value of the opcionActivacionBolsa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOpcionActivacionBolsa() {
        return opcionActivacionBolsa;
    }

    /**
     * Sets the value of the opcionActivacionBolsa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOpcionActivacionBolsa(String value) {
        this.opcionActivacionBolsa = value;
    }

}
