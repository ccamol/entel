
package com.epcs.billing.recarga.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ResultadoRecargaMultitiendaType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResultadoRecargaMultitiendaType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="plataforma" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tipoOperacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codEmpresa" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codLocal" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codUnicoTransaccion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="msisdnRecarga" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codRespuestaTransA" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codRespuestaTransB" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codTransMultitienda" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="montoRecarga" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="montoBono" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nuevoSaldo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="validezRecarga" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codigoUnicoAutorizacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResultadoRecargaMultitiendaType", propOrder = {
    "plataforma",
    "tipoOperacion",
    "codEmpresa",
    "codLocal",
    "codUnicoTransaccion",
    "msisdnRecarga",
    "codRespuestaTransA",
    "codRespuestaTransB",
    "codTransMultitienda",
    "montoRecarga",
    "montoBono",
    "nuevoSaldo",
    "validezRecarga",
    "codigoUnicoAutorizacion"
})
public class ResultadoRecargaMultitiendaType {

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
    protected String codRespuestaTransA;
    @XmlElement(required = true)
    protected String codRespuestaTransB;
    @XmlElement(required = true)
    protected String codTransMultitienda;
    @XmlElement(required = true)
    protected String montoRecarga;
    @XmlElement(required = true)
    protected String montoBono;
    @XmlElement(required = true)
    protected String nuevoSaldo;
    @XmlElement(required = true)
    protected String validezRecarga;
    @XmlElement(required = true)
    protected String codigoUnicoAutorizacion;

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
     * Gets the value of the codRespuestaTransA property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodRespuestaTransA() {
        return codRespuestaTransA;
    }

    /**
     * Sets the value of the codRespuestaTransA property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodRespuestaTransA(String value) {
        this.codRespuestaTransA = value;
    }

    /**
     * Gets the value of the codRespuestaTransB property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodRespuestaTransB() {
        return codRespuestaTransB;
    }

    /**
     * Sets the value of the codRespuestaTransB property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodRespuestaTransB(String value) {
        this.codRespuestaTransB = value;
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
     * Gets the value of the montoBono property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMontoBono() {
        return montoBono;
    }

    /**
     * Sets the value of the montoBono property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMontoBono(String value) {
        this.montoBono = value;
    }

    /**
     * Gets the value of the nuevoSaldo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNuevoSaldo() {
        return nuevoSaldo;
    }

    /**
     * Sets the value of the nuevoSaldo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNuevoSaldo(String value) {
        this.nuevoSaldo = value;
    }

    /**
     * Gets the value of the validezRecarga property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValidezRecarga() {
        return validezRecarga;
    }

    /**
     * Sets the value of the validezRecarga property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValidezRecarga(String value) {
        this.validezRecarga = value;
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

}
