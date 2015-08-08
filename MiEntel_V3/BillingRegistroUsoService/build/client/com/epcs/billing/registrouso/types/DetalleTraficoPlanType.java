
package com.epcs.billing.registrouso.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DetalleTraficoPlanType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DetalleTraficoPlanType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idProducto" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fechaInicioTraf" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fechaFinTraf" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nombreTasacionPro1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="valorTasacionPro1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nombreTasacionPro2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="valorTasacionPro2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nombreTasacionPro3" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="valorTasacionPro3" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nombreTasacionPro4" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="valorTasacionPro4" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DetalleTraficoPlanType", propOrder = {
    "idProducto",
    "fechaInicioTraf",
    "fechaFinTraf",
    "nombreTasacionPro1",
    "valorTasacionPro1",
    "nombreTasacionPro2",
    "valorTasacionPro2",
    "nombreTasacionPro3",
    "valorTasacionPro3",
    "nombreTasacionPro4",
    "valorTasacionPro4"
})
public class DetalleTraficoPlanType {

    @XmlElement(required = true)
    protected String idProducto;
    @XmlElement(required = true)
    protected String fechaInicioTraf;
    @XmlElement(required = true)
    protected String fechaFinTraf;
    @XmlElement(required = true)
    protected String nombreTasacionPro1;
    @XmlElement(required = true)
    protected String valorTasacionPro1;
    @XmlElement(required = true)
    protected String nombreTasacionPro2;
    @XmlElement(required = true)
    protected String valorTasacionPro2;
    @XmlElement(required = true)
    protected String nombreTasacionPro3;
    @XmlElement(required = true)
    protected String valorTasacionPro3;
    @XmlElement(required = true)
    protected String nombreTasacionPro4;
    @XmlElement(required = true)
    protected String valorTasacionPro4;

    /**
     * Gets the value of the idProducto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdProducto() {
        return idProducto;
    }

    /**
     * Sets the value of the idProducto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdProducto(String value) {
        this.idProducto = value;
    }

    /**
     * Gets the value of the fechaInicioTraf property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaInicioTraf() {
        return fechaInicioTraf;
    }

    /**
     * Sets the value of the fechaInicioTraf property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaInicioTraf(String value) {
        this.fechaInicioTraf = value;
    }

    /**
     * Gets the value of the fechaFinTraf property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaFinTraf() {
        return fechaFinTraf;
    }

    /**
     * Sets the value of the fechaFinTraf property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaFinTraf(String value) {
        this.fechaFinTraf = value;
    }

    /**
     * Gets the value of the nombreTasacionPro1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreTasacionPro1() {
        return nombreTasacionPro1;
    }

    /**
     * Sets the value of the nombreTasacionPro1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreTasacionPro1(String value) {
        this.nombreTasacionPro1 = value;
    }

    /**
     * Gets the value of the valorTasacionPro1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValorTasacionPro1() {
        return valorTasacionPro1;
    }

    /**
     * Sets the value of the valorTasacionPro1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValorTasacionPro1(String value) {
        this.valorTasacionPro1 = value;
    }

    /**
     * Gets the value of the nombreTasacionPro2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreTasacionPro2() {
        return nombreTasacionPro2;
    }

    /**
     * Sets the value of the nombreTasacionPro2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreTasacionPro2(String value) {
        this.nombreTasacionPro2 = value;
    }

    /**
     * Gets the value of the valorTasacionPro2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValorTasacionPro2() {
        return valorTasacionPro2;
    }

    /**
     * Sets the value of the valorTasacionPro2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValorTasacionPro2(String value) {
        this.valorTasacionPro2 = value;
    }

    /**
     * Gets the value of the nombreTasacionPro3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreTasacionPro3() {
        return nombreTasacionPro3;
    }

    /**
     * Sets the value of the nombreTasacionPro3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreTasacionPro3(String value) {
        this.nombreTasacionPro3 = value;
    }

    /**
     * Gets the value of the valorTasacionPro3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValorTasacionPro3() {
        return valorTasacionPro3;
    }

    /**
     * Sets the value of the valorTasacionPro3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValorTasacionPro3(String value) {
        this.valorTasacionPro3 = value;
    }

    /**
     * Gets the value of the nombreTasacionPro4 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreTasacionPro4() {
        return nombreTasacionPro4;
    }

    /**
     * Sets the value of the nombreTasacionPro4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreTasacionPro4(String value) {
        this.nombreTasacionPro4 = value;
    }

    /**
     * Gets the value of the valorTasacionPro4 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValorTasacionPro4() {
        return valorTasacionPro4;
    }

    /**
     * Sets the value of the valorTasacionPro4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValorTasacionPro4(String value) {
        this.valorTasacionPro4 = value;
    }

}
