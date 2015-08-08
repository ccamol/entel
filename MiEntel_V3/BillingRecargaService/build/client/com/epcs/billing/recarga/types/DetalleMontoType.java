
package com.epcs.billing.recarga.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DetalleMontoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DetalleMontoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ordenCompra" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="parametroPersonalizable" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="monto" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DetalleMontoType", propOrder = {
    "ordenCompra",
    "parametroPersonalizable",
    "monto"
})
public class DetalleMontoType {

    @XmlElement(required = true)
    protected String ordenCompra;
    @XmlElement(required = true)
    protected String parametroPersonalizable;
    @XmlElement(required = true)
    protected String monto;

    /**
     * Gets the value of the ordenCompra property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrdenCompra() {
        return ordenCompra;
    }

    /**
     * Sets the value of the ordenCompra property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrdenCompra(String value) {
        this.ordenCompra = value;
    }

    /**
     * Gets the value of the parametroPersonalizable property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParametroPersonalizable() {
        return parametroPersonalizable;
    }

    /**
     * Sets the value of the parametroPersonalizable property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParametroPersonalizable(String value) {
        this.parametroPersonalizable = value;
    }

    /**
     * Gets the value of the monto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMonto() {
        return monto;
    }

    /**
     * Sets the value of the monto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMonto(String value) {
        this.monto = value;
    }

}
