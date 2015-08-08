
package com.epcs.billing.recarga.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ResultadoFactWebPayType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResultadoFactWebPayType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="plataforma" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codIntegrador" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codDistribuidor" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codComercio" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codigoUnicoTransaccion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="msisdnRecarga" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codigoRespuesta" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResultadoFactWebPayType", propOrder = {
    "plataforma",
    "codIntegrador",
    "codDistribuidor",
    "codComercio",
    "codigoUnicoTransaccion",
    "msisdnRecarga",
    "codigoRespuesta"
})
public class ResultadoFactWebPayType {

    @XmlElement(required = true)
    protected String plataforma;
    @XmlElement(required = true)
    protected String codIntegrador;
    @XmlElement(required = true)
    protected String codDistribuidor;
    @XmlElement(required = true)
    protected String codComercio;
    @XmlElement(required = true)
    protected String codigoUnicoTransaccion;
    @XmlElement(required = true)
    protected String msisdnRecarga;
    @XmlElement(required = true)
    protected String codigoRespuesta;

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
     * Gets the value of the codIntegrador property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodIntegrador() {
        return codIntegrador;
    }

    /**
     * Sets the value of the codIntegrador property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodIntegrador(String value) {
        this.codIntegrador = value;
    }

    /**
     * Gets the value of the codDistribuidor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodDistribuidor() {
        return codDistribuidor;
    }

    /**
     * Sets the value of the codDistribuidor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodDistribuidor(String value) {
        this.codDistribuidor = value;
    }

    /**
     * Gets the value of the codComercio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodComercio() {
        return codComercio;
    }

    /**
     * Sets the value of the codComercio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodComercio(String value) {
        this.codComercio = value;
    }

    /**
     * Gets the value of the codigoUnicoTransaccion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoUnicoTransaccion() {
        return codigoUnicoTransaccion;
    }

    /**
     * Sets the value of the codigoUnicoTransaccion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoUnicoTransaccion(String value) {
        this.codigoUnicoTransaccion = value;
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
     * Gets the value of the codigoRespuesta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoRespuesta() {
        return codigoRespuesta;
    }

    /**
     * Sets the value of the codigoRespuesta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoRespuesta(String value) {
        this.codigoRespuesta = value;
    }

}
