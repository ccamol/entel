
package com.epcs.billing.balance.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DocumentoFacturaFullType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DocumentoFacturaFullType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="numeroCuenta" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tipo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="folio" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fechaPeriodo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fechaEmision" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fechaVencimiento" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="estado" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="montoTotal" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="montoServAdicional" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="montoCobrosDescuentos" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="montoTelefoniaMovil" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DocumentoFacturaFullType", propOrder = {
    "numeroCuenta",
    "tipo",
    "folio",
    "fechaPeriodo",
    "fechaEmision",
    "fechaVencimiento",
    "estado",
    "montoTotal",
    "montoServAdicional",
    "montoCobrosDescuentos",
    "montoTelefoniaMovil"
})
public class DocumentoFacturaFullType {

    @XmlElement(required = true)
    protected String numeroCuenta;
    @XmlElement(required = true)
    protected String tipo;
    @XmlElement(required = true)
    protected String folio;
    @XmlElement(required = true)
    protected String fechaPeriodo;
    @XmlElement(required = true)
    protected String fechaEmision;
    @XmlElement(required = true)
    protected String fechaVencimiento;
    @XmlElement(required = true)
    protected String estado;
    @XmlElement(required = true)
    protected String montoTotal;
    @XmlElement(required = true)
    protected String montoServAdicional;
    @XmlElement(required = true)
    protected String montoCobrosDescuentos;
    @XmlElement(required = true)
    protected String montoTelefoniaMovil;

    /**
     * Gets the value of the numeroCuenta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    /**
     * Sets the value of the numeroCuenta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroCuenta(String value) {
        this.numeroCuenta = value;
    }

    /**
     * Gets the value of the tipo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Sets the value of the tipo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipo(String value) {
        this.tipo = value;
    }

    /**
     * Gets the value of the folio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFolio() {
        return folio;
    }

    /**
     * Sets the value of the folio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFolio(String value) {
        this.folio = value;
    }

    /**
     * Gets the value of the fechaPeriodo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaPeriodo() {
        return fechaPeriodo;
    }

    /**
     * Sets the value of the fechaPeriodo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaPeriodo(String value) {
        this.fechaPeriodo = value;
    }

    /**
     * Gets the value of the fechaEmision property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaEmision() {
        return fechaEmision;
    }

    /**
     * Sets the value of the fechaEmision property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaEmision(String value) {
        this.fechaEmision = value;
    }

    /**
     * Gets the value of the fechaVencimiento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    /**
     * Sets the value of the fechaVencimiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaVencimiento(String value) {
        this.fechaVencimiento = value;
    }

    /**
     * Gets the value of the estado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Sets the value of the estado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstado(String value) {
        this.estado = value;
    }

    /**
     * Gets the value of the montoTotal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMontoTotal() {
        return montoTotal;
    }

    /**
     * Sets the value of the montoTotal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMontoTotal(String value) {
        this.montoTotal = value;
    }

    /**
     * Gets the value of the montoServAdicional property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMontoServAdicional() {
        return montoServAdicional;
    }

    /**
     * Sets the value of the montoServAdicional property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMontoServAdicional(String value) {
        this.montoServAdicional = value;
    }

    /**
     * Gets the value of the montoCobrosDescuentos property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMontoCobrosDescuentos() {
        return montoCobrosDescuentos;
    }

    /**
     * Sets the value of the montoCobrosDescuentos property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMontoCobrosDescuentos(String value) {
        this.montoCobrosDescuentos = value;
    }

    /**
     * Gets the value of the montoTelefoniaMovil property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMontoTelefoniaMovil() {
        return montoTelefoniaMovil;
    }

    /**
     * Sets the value of the montoTelefoniaMovil property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMontoTelefoniaMovil(String value) {
        this.montoTelefoniaMovil = value;
    }

}
