
package com.epcs.billing.balance.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DetalleFacturaMesType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DetalleFacturaMesType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="totalActualMes" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="saldoAnteriorMes" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="totalPagoMes" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fechaPeriodoMes" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fechaEmisionMes" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fechaVencimientoMes" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="estadoMes" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="urlImagenFactura" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DetalleFacturaMesType", propOrder = {
    "totalActualMes",
    "saldoAnteriorMes",
    "totalPagoMes",
    "fechaPeriodoMes",
    "fechaEmisionMes",
    "fechaVencimientoMes",
    "estadoMes",
    "urlImagenFactura"
})
public class DetalleFacturaMesType {

    @XmlElement(required = true)
    protected String totalActualMes;
    @XmlElement(required = true)
    protected String saldoAnteriorMes;
    @XmlElement(required = true)
    protected String totalPagoMes;
    @XmlElement(required = true)
    protected String fechaPeriodoMes;
    @XmlElement(required = true)
    protected String fechaEmisionMes;
    @XmlElement(required = true)
    protected String fechaVencimientoMes;
    @XmlElement(required = true)
    protected String estadoMes;
    @XmlElement(required = true)
    protected String urlImagenFactura;

    /**
     * Gets the value of the totalActualMes property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalActualMes() {
        return totalActualMes;
    }

    /**
     * Sets the value of the totalActualMes property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalActualMes(String value) {
        this.totalActualMes = value;
    }

    /**
     * Gets the value of the saldoAnteriorMes property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSaldoAnteriorMes() {
        return saldoAnteriorMes;
    }

    /**
     * Sets the value of the saldoAnteriorMes property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSaldoAnteriorMes(String value) {
        this.saldoAnteriorMes = value;
    }

    /**
     * Gets the value of the totalPagoMes property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalPagoMes() {
        return totalPagoMes;
    }

    /**
     * Sets the value of the totalPagoMes property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalPagoMes(String value) {
        this.totalPagoMes = value;
    }

    /**
     * Gets the value of the fechaPeriodoMes property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaPeriodoMes() {
        return fechaPeriodoMes;
    }

    /**
     * Sets the value of the fechaPeriodoMes property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaPeriodoMes(String value) {
        this.fechaPeriodoMes = value;
    }

    /**
     * Gets the value of the fechaEmisionMes property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaEmisionMes() {
        return fechaEmisionMes;
    }

    /**
     * Sets the value of the fechaEmisionMes property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaEmisionMes(String value) {
        this.fechaEmisionMes = value;
    }

    /**
     * Gets the value of the fechaVencimientoMes property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaVencimientoMes() {
        return fechaVencimientoMes;
    }

    /**
     * Sets the value of the fechaVencimientoMes property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaVencimientoMes(String value) {
        this.fechaVencimientoMes = value;
    }

    /**
     * Gets the value of the estadoMes property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstadoMes() {
        return estadoMes;
    }

    /**
     * Sets the value of the estadoMes property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstadoMes(String value) {
        this.estadoMes = value;
    }

    /**
     * Gets the value of the urlImagenFactura property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrlImagenFactura() {
        return urlImagenFactura;
    }

    /**
     * Sets the value of the urlImagenFactura property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrlImagenFactura(String value) {
        this.urlImagenFactura = value;
    }

}
