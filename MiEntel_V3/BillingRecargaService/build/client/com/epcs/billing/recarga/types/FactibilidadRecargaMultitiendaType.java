
package com.epcs.billing.recarga.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FactibilidadRecargaMultitiendaType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FactibilidadRecargaMultitiendaType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="metodo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="plataforma" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tipoOperacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codEmpresa" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codLocal" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codUnicoTransaccion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="msisdnRecarga" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="montoRecarga" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codTransMultitienda" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="rut" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numeroCuotas" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="claveTarjeta" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="diaPago" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fechaSolicitud" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codigoUnicoAutorizacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="idSistema" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FactibilidadRecargaMultitiendaType", propOrder = {
    "metodo",
    "plataforma",
    "tipoOperacion",
    "codEmpresa",
    "codLocal",
    "codUnicoTransaccion",
    "msisdnRecarga",
    "montoRecarga",
    "codTransMultitienda",
    "rut",
    "numeroCuotas",
    "claveTarjeta",
    "diaPago",
    "fechaSolicitud",
    "codigoUnicoAutorizacion",
    "idSistema"
})
public class FactibilidadRecargaMultitiendaType {

    @XmlElement(required = true)
    protected String metodo;
    @XmlElement(required = true)
    protected String plataforma;
    @XmlElement(required = true)
    protected String tipoOperacion;
    @XmlElement(required = true)
    protected String codEmpresa;
    @XmlElement(required = true)
    protected String codLocal;
    @XmlElement(required = true)
    protected String codUnicoTransaccion;
    @XmlElement(required = true)
    protected String msisdnRecarga;
    @XmlElement(required = true)
    protected String montoRecarga;
    @XmlElement(required = true)
    protected String codTransMultitienda;
    @XmlElement(required = true)
    protected String rut;
    @XmlElement(required = true)
    protected String numeroCuotas;
    @XmlElement(required = true)
    protected String claveTarjeta;
    @XmlElement(required = true)
    protected String diaPago;
    @XmlElement(required = true)
    protected String fechaSolicitud;
    @XmlElement(required = true)
    protected String codigoUnicoAutorizacion;
    @XmlElement(required = true)
    protected String idSistema;

    /**
     * Gets the value of the metodo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMetodo() {
        return metodo;
    }

    /**
     * Sets the value of the metodo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMetodo(String value) {
        this.metodo = value;
    }

    /**
     * Gets the value of the plataforma property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlataforma() {
        return plataforma;
    }

    /**
     * Sets the value of the plataforma property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlataforma(String value) {
        this.plataforma = value;
    }

    /**
     * Gets the value of the tipoOperacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoOperacion() {
        return tipoOperacion;
    }

    /**
     * Sets the value of the tipoOperacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoOperacion(String value) {
        this.tipoOperacion = value;
    }

    /**
     * Gets the value of the codEmpresa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodEmpresa() {
        return codEmpresa;
    }

    /**
     * Sets the value of the codEmpresa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodEmpresa(String value) {
        this.codEmpresa = value;
    }

    /**
     * Gets the value of the codLocal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodLocal() {
        return codLocal;
    }

    /**
     * Sets the value of the codLocal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodLocal(String value) {
        this.codLocal = value;
    }

    /**
     * Gets the value of the codUnicoTransaccion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodUnicoTransaccion() {
        return codUnicoTransaccion;
    }

    /**
     * Sets the value of the codUnicoTransaccion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodUnicoTransaccion(String value) {
        this.codUnicoTransaccion = value;
    }

    /**
     * Gets the value of the msisdnRecarga property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMsisdnRecarga() {
        return msisdnRecarga;
    }

    /**
     * Sets the value of the msisdnRecarga property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMsisdnRecarga(String value) {
        this.msisdnRecarga = value;
    }

    /**
     * Gets the value of the montoRecarga property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMontoRecarga() {
        return montoRecarga;
    }

    /**
     * Sets the value of the montoRecarga property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMontoRecarga(String value) {
        this.montoRecarga = value;
    }

    /**
     * Gets the value of the codTransMultitienda property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodTransMultitienda() {
        return codTransMultitienda;
    }

    /**
     * Sets the value of the codTransMultitienda property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodTransMultitienda(String value) {
        this.codTransMultitienda = value;
    }

    /**
     * Gets the value of the rut property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRut() {
        return rut;
    }

    /**
     * Sets the value of the rut property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRut(String value) {
        this.rut = value;
    }

    /**
     * Gets the value of the numeroCuotas property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroCuotas() {
        return numeroCuotas;
    }

    /**
     * Sets the value of the numeroCuotas property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroCuotas(String value) {
        this.numeroCuotas = value;
    }

    /**
     * Gets the value of the claveTarjeta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClaveTarjeta() {
        return claveTarjeta;
    }

    /**
     * Sets the value of the claveTarjeta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClaveTarjeta(String value) {
        this.claveTarjeta = value;
    }

    /**
     * Gets the value of the diaPago property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDiaPago() {
        return diaPago;
    }

    /**
     * Sets the value of the diaPago property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDiaPago(String value) {
        this.diaPago = value;
    }

    /**
     * Gets the value of the fechaSolicitud property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaSolicitud() {
        return fechaSolicitud;
    }

    /**
     * Sets the value of the fechaSolicitud property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaSolicitud(String value) {
        this.fechaSolicitud = value;
    }

    /**
     * Gets the value of the codigoUnicoAutorizacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoUnicoAutorizacion() {
        return codigoUnicoAutorizacion;
    }

    /**
     * Sets the value of the codigoUnicoAutorizacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoUnicoAutorizacion(String value) {
        this.codigoUnicoAutorizacion = value;
    }

    /**
     * Gets the value of the idSistema property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdSistema() {
        return idSistema;
    }

    /**
     * Sets the value of the idSistema property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdSistema(String value) {
        this.idSistema = value;
    }

}
