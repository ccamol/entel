
package com.esa.clientes.perfilesclientes.sgacarterasvip.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for salidaConsultaEjecutivoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="salidaConsultaEjecutivoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nombre_ejecutivo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="apellido_paterno_ejecutivo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="apellido_materno_ejecutivo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="mail_contacto_ejecutivo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fono_ejecutivo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codigo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="mensaje" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "salidaConsultaEjecutivoType", propOrder = {
    "nombreEjecutivo",
    "apellidoPaternoEjecutivo",
    "apellidoMaternoEjecutivo",
    "mailContactoEjecutivo",
    "fonoEjecutivo",
    "codigo",
    "mensaje"
})
public class SalidaConsultaEjecutivoType {

    @XmlElement(name = "nombre_ejecutivo", required = true)
    protected String nombreEjecutivo;
    @XmlElement(name = "apellido_paterno_ejecutivo", required = true)
    protected String apellidoPaternoEjecutivo;
    @XmlElement(name = "apellido_materno_ejecutivo", required = true)
    protected String apellidoMaternoEjecutivo;
    @XmlElement(name = "mail_contacto_ejecutivo", required = true)
    protected String mailContactoEjecutivo;
    @XmlElement(name = "fono_ejecutivo", required = true)
    protected String fonoEjecutivo;
    @XmlElement(required = true)
    protected String codigo;
    @XmlElement(required = true)
    protected String mensaje;

    /**
     * Gets the value of the nombreEjecutivo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreEjecutivo() {
        return nombreEjecutivo;
    }

    /**
     * Sets the value of the nombreEjecutivo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreEjecutivo(String value) {
        this.nombreEjecutivo = value;
    }

    /**
     * Gets the value of the apellidoPaternoEjecutivo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApellidoPaternoEjecutivo() {
        return apellidoPaternoEjecutivo;
    }

    /**
     * Sets the value of the apellidoPaternoEjecutivo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApellidoPaternoEjecutivo(String value) {
        this.apellidoPaternoEjecutivo = value;
    }

    /**
     * Gets the value of the apellidoMaternoEjecutivo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApellidoMaternoEjecutivo() {
        return apellidoMaternoEjecutivo;
    }

    /**
     * Sets the value of the apellidoMaternoEjecutivo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApellidoMaternoEjecutivo(String value) {
        this.apellidoMaternoEjecutivo = value;
    }

    /**
     * Gets the value of the mailContactoEjecutivo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMailContactoEjecutivo() {
        return mailContactoEjecutivo;
    }

    /**
     * Sets the value of the mailContactoEjecutivo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMailContactoEjecutivo(String value) {
        this.mailContactoEjecutivo = value;
    }

    /**
     * Gets the value of the fonoEjecutivo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFonoEjecutivo() {
        return fonoEjecutivo;
    }

    /**
     * Sets the value of the fonoEjecutivo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFonoEjecutivo(String value) {
        this.fonoEjecutivo = value;
    }

    /**
     * Gets the value of the codigo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Sets the value of the codigo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigo(String value) {
        this.codigo = value;
    }

    /**
     * Gets the value of the mensaje property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * Sets the value of the mensaje property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMensaje(String value) {
        this.mensaje = value;
    }

}
