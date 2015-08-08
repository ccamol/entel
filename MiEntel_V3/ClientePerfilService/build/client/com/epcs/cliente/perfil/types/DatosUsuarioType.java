
package com.epcs.cliente.perfil.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DatosUsuarioType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DatosUsuarioType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="primerNombre" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="segundoNombre" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="primerApellido" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="segundoApellido" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="sexo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="direccionCalle" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="direccionNumero" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="direccionResto" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="comuna" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ciudad" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="region" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codPostal" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="emailDominio" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fechaNacimiento" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="estadoCivil" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="telefonoFijoArea" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="telefonoFijoNumero" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nivelEstudios" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="actividad" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DatosUsuarioType", propOrder = {
    "primerNombre",
    "segundoNombre",
    "primerApellido",
    "segundoApellido",
    "sexo",
    "direccionCalle",
    "direccionNumero",
    "direccionResto",
    "comuna",
    "ciudad",
    "region",
    "codPostal",
    "email",
    "emailDominio",
    "fechaNacimiento",
    "estadoCivil",
    "telefonoFijoArea",
    "telefonoFijoNumero",
    "nivelEstudios",
    "actividad"
})
public class DatosUsuarioType {

    @XmlElement(required = true)
    protected String primerNombre;
    @XmlElement(required = true)
    protected String segundoNombre;
    @XmlElement(required = true)
    protected String primerApellido;
    @XmlElement(required = true)
    protected String segundoApellido;
    @XmlElement(required = true)
    protected String sexo;
    @XmlElement(required = true)
    protected String direccionCalle;
    @XmlElement(required = true)
    protected String direccionNumero;
    @XmlElement(required = true)
    protected String direccionResto;
    @XmlElement(required = true)
    protected String comuna;
    @XmlElement(required = true)
    protected String ciudad;
    @XmlElement(required = true)
    protected String region;
    @XmlElement(required = true)
    protected String codPostal;
    @XmlElement(required = true)
    protected String email;
    @XmlElement(required = true)
    protected String emailDominio;
    @XmlElement(required = true)
    protected String fechaNacimiento;
    @XmlElement(required = true)
    protected String estadoCivil;
    @XmlElement(required = true)
    protected String telefonoFijoArea;
    @XmlElement(required = true)
    protected String telefonoFijoNumero;
    @XmlElement(required = true)
    protected String nivelEstudios;
    @XmlElement(required = true)
    protected String actividad;

    /**
     * Gets the value of the primerNombre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrimerNombre() {
        return primerNombre;
    }

    /**
     * Sets the value of the primerNombre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrimerNombre(String value) {
        this.primerNombre = value;
    }

    /**
     * Gets the value of the segundoNombre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSegundoNombre() {
        return segundoNombre;
    }

    /**
     * Sets the value of the segundoNombre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSegundoNombre(String value) {
        this.segundoNombre = value;
    }

    /**
     * Gets the value of the primerApellido property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrimerApellido() {
        return primerApellido;
    }

    /**
     * Sets the value of the primerApellido property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrimerApellido(String value) {
        this.primerApellido = value;
    }

    /**
     * Gets the value of the segundoApellido property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSegundoApellido() {
        return segundoApellido;
    }

    /**
     * Sets the value of the segundoApellido property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSegundoApellido(String value) {
        this.segundoApellido = value;
    }

    /**
     * Gets the value of the sexo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * Sets the value of the sexo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSexo(String value) {
        this.sexo = value;
    }

    /**
     * Gets the value of the direccionCalle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDireccionCalle() {
        return direccionCalle;
    }

    /**
     * Sets the value of the direccionCalle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDireccionCalle(String value) {
        this.direccionCalle = value;
    }

    /**
     * Gets the value of the direccionNumero property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDireccionNumero() {
        return direccionNumero;
    }

    /**
     * Sets the value of the direccionNumero property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDireccionNumero(String value) {
        this.direccionNumero = value;
    }

    /**
     * Gets the value of the direccionResto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDireccionResto() {
        return direccionResto;
    }

    /**
     * Sets the value of the direccionResto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDireccionResto(String value) {
        this.direccionResto = value;
    }

    /**
     * Gets the value of the comuna property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComuna() {
        return comuna;
    }

    /**
     * Sets the value of the comuna property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComuna(String value) {
        this.comuna = value;
    }

    /**
     * Gets the value of the ciudad property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * Sets the value of the ciudad property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCiudad(String value) {
        this.ciudad = value;
    }

    /**
     * Gets the value of the region property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegion() {
        return region;
    }

    /**
     * Sets the value of the region property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegion(String value) {
        this.region = value;
    }

    /**
     * Gets the value of the codPostal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodPostal() {
        return codPostal;
    }

    /**
     * Sets the value of the codPostal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodPostal(String value) {
        this.codPostal = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the emailDominio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailDominio() {
        return emailDominio;
    }

    /**
     * Sets the value of the emailDominio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailDominio(String value) {
        this.emailDominio = value;
    }

    /**
     * Gets the value of the fechaNacimiento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Sets the value of the fechaNacimiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaNacimiento(String value) {
        this.fechaNacimiento = value;
    }

    /**
     * Gets the value of the estadoCivil property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstadoCivil() {
        return estadoCivil;
    }

    /**
     * Sets the value of the estadoCivil property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstadoCivil(String value) {
        this.estadoCivil = value;
    }

    /**
     * Gets the value of the telefonoFijoArea property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelefonoFijoArea() {
        return telefonoFijoArea;
    }

    /**
     * Sets the value of the telefonoFijoArea property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelefonoFijoArea(String value) {
        this.telefonoFijoArea = value;
    }

    /**
     * Gets the value of the telefonoFijoNumero property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelefonoFijoNumero() {
        return telefonoFijoNumero;
    }

    /**
     * Sets the value of the telefonoFijoNumero property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelefonoFijoNumero(String value) {
        this.telefonoFijoNumero = value;
    }

    /**
     * Gets the value of the nivelEstudios property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNivelEstudios() {
        return nivelEstudios;
    }

    /**
     * Sets the value of the nivelEstudios property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNivelEstudios(String value) {
        this.nivelEstudios = value;
    }

    /**
     * Gets the value of the actividad property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActividad() {
        return actividad;
    }

    /**
     * Sets the value of the actividad property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActividad(String value) {
        this.actividad = value;
    }

}
