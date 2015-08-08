
package com.epcs.cliente.orden.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ReposicionServicioOrqType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ReposicionServicioOrqType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="msisdn" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="idSistema" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="passwordSistema" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tipoReposicion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReposicionServicioOrqType", propOrder = {
    "msisdn",
    "idSistema",
    "passwordSistema",
    "tipoReposicion"
})
public class ReposicionServicioOrqType {

    @XmlElement(required = true)
    protected String msisdn;
    @XmlElement(required = true)
    protected String idSistema;
    @XmlElement(required = true)
    protected String passwordSistema;
    @XmlElement(required = true)
    protected String tipoReposicion;

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

    /**
     * Gets the value of the passwordSistema property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPasswordSistema() {
        return passwordSistema;
    }

    /**
     * Sets the value of the passwordSistema property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPasswordSistema(String value) {
        this.passwordSistema = value;
    }

    /**
     * Gets the value of the tipoReposicion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoReposicion() {
        return tipoReposicion;
    }

    /**
     * Sets the value of the tipoReposicion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoReposicion(String value) {
        this.tipoReposicion = value;
    }

}
