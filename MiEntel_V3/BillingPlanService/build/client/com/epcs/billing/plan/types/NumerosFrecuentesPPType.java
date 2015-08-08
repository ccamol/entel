
package com.epcs.billing.plan.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for NumerosFrecuentesPPType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NumerosFrecuentesPPType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idSlot" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nombreSlot" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="usoSlot" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tipo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="costoAgregar" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="costoEditar" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="costoBorrar" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numeroFrecuente" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NumerosFrecuentesPPType", propOrder = {
    "idSlot",
    "nombreSlot",
    "usoSlot",
    "tipo",
    "costoAgregar",
    "costoEditar",
    "costoBorrar",
    "url",
    "numeroFrecuente"
})
public class NumerosFrecuentesPPType {

    @XmlElement(required = true)
    protected String idSlot;
    @XmlElement(required = true)
    protected String nombreSlot;
    @XmlElement(required = true)
    protected String usoSlot;
    @XmlElement(required = true)
    protected String tipo;
    @XmlElement(required = true)
    protected String costoAgregar;
    @XmlElement(required = true)
    protected String costoEditar;
    @XmlElement(required = true)
    protected String costoBorrar;
    @XmlElement(required = true)
    protected String url;
    @XmlElement(required = true)
    protected String numeroFrecuente;

    /**
     * Gets the value of the idSlot property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdSlot() {
        return idSlot;
    }

    /**
     * Sets the value of the idSlot property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdSlot(String value) {
        this.idSlot = value;
    }

    /**
     * Gets the value of the nombreSlot property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreSlot() {
        return nombreSlot;
    }

    /**
     * Sets the value of the nombreSlot property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreSlot(String value) {
        this.nombreSlot = value;
    }

    /**
     * Gets the value of the usoSlot property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsoSlot() {
        return usoSlot;
    }

    /**
     * Sets the value of the usoSlot property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsoSlot(String value) {
        this.usoSlot = value;
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
     * Gets the value of the costoAgregar property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCostoAgregar() {
        return costoAgregar;
    }

    /**
     * Sets the value of the costoAgregar property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCostoAgregar(String value) {
        this.costoAgregar = value;
    }

    /**
     * Gets the value of the costoEditar property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCostoEditar() {
        return costoEditar;
    }

    /**
     * Sets the value of the costoEditar property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCostoEditar(String value) {
        this.costoEditar = value;
    }

    /**
     * Gets the value of the costoBorrar property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCostoBorrar() {
        return costoBorrar;
    }

    /**
     * Sets the value of the costoBorrar property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCostoBorrar(String value) {
        this.costoBorrar = value;
    }

    /**
     * Gets the value of the url property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the value of the url property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrl(String value) {
        this.url = value;
    }

    /**
     * Gets the value of the numeroFrecuente property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroFrecuente() {
        return numeroFrecuente;
    }

    /**
     * Sets the value of the numeroFrecuente property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroFrecuente(String value) {
        this.numeroFrecuente = value;
    }

}
