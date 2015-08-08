
package com.epcs.cliente.perfil.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OrqDatosUsuarioType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OrqDatosUsuarioType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="rut" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="msisdn" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="primerNombre" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="segundoNombre" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="apellidoPaterno" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="apellidoMaterno" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="sexo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="idRelacionTitular" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="descRelacionTitular" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="emailFacturacionElectronica" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="telefonoAdicional" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="aaa" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="region" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ciudad" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="comuna" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="calle" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numero" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="departamento" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="estadoCivil" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="hijos" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="nivelEstudio" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="actividadLaboral" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="areaLaboral" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fechaNacimento" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="areaTelefonoRes" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="telefonoRes" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrqDatosUsuarioType", propOrder = {
    "rut",
    "msisdn",
    "primerNombre",
    "segundoNombre",
    "apellidoPaterno",
    "apellidoMaterno",
    "sexo",
    "idRelacionTitular",
    "descRelacionTitular",
    "email",
    "emailFacturacionElectronica",
    "telefonoAdicional",
    "aaa",
    "region",
    "ciudad",
    "comuna",
    "calle",
    "numero",
    "departamento",
    "estadoCivil",
    "hijos",
    "nivelEstudio",
    "actividadLaboral",
    "areaLaboral",
    "fechaNacimento",
    "areaTelefonoRes",
    "telefonoRes"
})
public class OrqDatosUsuarioType {

    @XmlElement(required = true)
    protected String rut;
    @XmlElement(required = true)
    protected String msisdn;
    @XmlElement(required = true)
    protected String primerNombre;
    @XmlElement(required = true)
    protected String segundoNombre;
    @XmlElement(required = true)
    protected String apellidoPaterno;
    @XmlElement(required = true)
    protected String apellidoMaterno;
    @XmlElement(required = true)
    protected String sexo;
    @XmlElement(required = true)
    protected String idRelacionTitular;
    @XmlElement(required = true)
    protected String descRelacionTitular;
    @XmlElement(required = true)
    protected String email;
    @XmlElement(required = true)
    protected String emailFacturacionElectronica;
    @XmlElement(required = true)
    protected String telefonoAdicional;
    @XmlElement(required = true)
    protected String aaa;
    @XmlElement(required = true)
    protected String region;
    @XmlElement(required = true)
    protected String ciudad;
    @XmlElement(required = true)
    protected String comuna;
    @XmlElement(required = true)
    protected String calle;
    @XmlElement(required = true)
    protected String numero;
    @XmlElement(required = true)
    protected String departamento;
    @XmlElement(required = true)
    protected String estadoCivil;
    protected int hijos;
    @XmlElement(required = true)
    protected String nivelEstudio;
    @XmlElement(required = true)
    protected String actividadLaboral;
    @XmlElement(required = true)
    protected String areaLaboral;
    @XmlElement(required = true)
    protected String fechaNacimento;
    @XmlElement(required = true)
    protected String areaTelefonoRes;
    @XmlElement(required = true)
    protected String telefonoRes;

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
     * Gets the value of the apellidoPaterno property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    /**
     * Sets the value of the apellidoPaterno property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApellidoPaterno(String value) {
        this.apellidoPaterno = value;
    }

    /**
     * Gets the value of the apellidoMaterno property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    /**
     * Sets the value of the apellidoMaterno property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApellidoMaterno(String value) {
        this.apellidoMaterno = value;
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
     * Gets the value of the idRelacionTitular property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdRelacionTitular() {
        return idRelacionTitular;
    }

    /**
     * Sets the value of the idRelacionTitular property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdRelacionTitular(String value) {
        this.idRelacionTitular = value;
    }

    /**
     * Gets the value of the descRelacionTitular property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescRelacionTitular() {
        return descRelacionTitular;
    }

    /**
     * Sets the value of the descRelacionTitular property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescRelacionTitular(String value) {
        this.descRelacionTitular = value;
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
     * Gets the value of the emailFacturacionElectronica property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailFacturacionElectronica() {
        return emailFacturacionElectronica;
    }

    /**
     * Sets the value of the emailFacturacionElectronica property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailFacturacionElectronica(String value) {
        this.emailFacturacionElectronica = value;
    }

    /**
     * Gets the value of the telefonoAdicional property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelefonoAdicional() {
        return telefonoAdicional;
    }

    /**
     * Sets the value of the telefonoAdicional property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelefonoAdicional(String value) {
        this.telefonoAdicional = value;
    }

    /**
     * Gets the value of the aaa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAaa() {
        return aaa;
    }

    /**
     * Sets the value of the aaa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAaa(String value) {
        this.aaa = value;
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
     * Gets the value of the calle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCalle() {
        return calle;
    }

    /**
     * Sets the value of the calle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCalle(String value) {
        this.calle = value;
    }

    /**
     * Gets the value of the numero property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Sets the value of the numero property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumero(String value) {
        this.numero = value;
    }

    /**
     * Gets the value of the departamento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepartamento() {
        return departamento;
    }

    /**
     * Sets the value of the departamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepartamento(String value) {
        this.departamento = value;
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
     * Gets the value of the hijos property.
     * 
     */
    public int getHijos() {
        return hijos;
    }

    /**
     * Sets the value of the hijos property.
     * 
     */
    public void setHijos(int value) {
        this.hijos = value;
    }

    /**
     * Gets the value of the nivelEstudio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNivelEstudio() {
        return nivelEstudio;
    }

    /**
     * Sets the value of the nivelEstudio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNivelEstudio(String value) {
        this.nivelEstudio = value;
    }

    /**
     * Gets the value of the actividadLaboral property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActividadLaboral() {
        return actividadLaboral;
    }

    /**
     * Sets the value of the actividadLaboral property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActividadLaboral(String value) {
        this.actividadLaboral = value;
    }

    /**
     * Gets the value of the areaLaboral property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAreaLaboral() {
        return areaLaboral;
    }

    /**
     * Sets the value of the areaLaboral property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAreaLaboral(String value) {
        this.areaLaboral = value;
    }

    /**
     * Gets the value of the fechaNacimento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaNacimento() {
        return fechaNacimento;
    }

    /**
     * Sets the value of the fechaNacimento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaNacimento(String value) {
        this.fechaNacimento = value;
    }

    /**
     * Gets the value of the areaTelefonoRes property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAreaTelefonoRes() {
        return areaTelefonoRes;
    }

    /**
     * Sets the value of the areaTelefonoRes property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAreaTelefonoRes(String value) {
        this.areaTelefonoRes = value;
    }

    /**
     * Gets the value of the telefonoRes property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelefonoRes() {
        return telefonoRes;
    }

    /**
     * Sets the value of the telefonoRes property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelefonoRes(String value) {
        this.telefonoRes = value;
    }

}
