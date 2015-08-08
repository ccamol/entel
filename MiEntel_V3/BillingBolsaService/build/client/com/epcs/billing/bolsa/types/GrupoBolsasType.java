
package com.epcs.billing.bolsa.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GrupoBolsasType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GrupoBolsasType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nombreGrupoBolsa" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="bolsasCompradasPP" type="{http://www.epcs.com/billing/bolsa/types}BolsaCompradaPPType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GrupoBolsasType", propOrder = {
    "nombreGrupoBolsa",
    "bolsasCompradasPP"
})
public class GrupoBolsasType {

    @XmlElement(required = true)
    protected String nombreGrupoBolsa;
    protected BolsaCompradaPPType bolsasCompradasPP;

    /**
     * Gets the value of the nombreGrupoBolsa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreGrupoBolsa() {
        return nombreGrupoBolsa;
    }

    /**
     * Sets the value of the nombreGrupoBolsa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreGrupoBolsa(String value) {
        this.nombreGrupoBolsa = value;
    }

    /**
     * Gets the value of the bolsasCompradasPP property.
     * 
     * @return
     *     possible object is
     *     {@link BolsaCompradaPPType }
     *     
     */
    public BolsaCompradaPPType getBolsasCompradasPP() {
        return bolsasCompradasPP;
    }

    /**
     * Sets the value of the bolsasCompradasPP property.
     * 
     * @param value
     *     allowed object is
     *     {@link BolsaCompradaPPType }
     *     
     */
    public void setBolsasCompradasPP(BolsaCompradaPPType value) {
        this.bolsasCompradasPP = value;
    }

}
