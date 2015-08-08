
package com.esa.clientes.perfilesclientes.resumenlineasequiposcliente;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for resumenDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="resumenDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cantEquipoMultim" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="cantEquipoNormal" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="cantLineaMulti" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="cantLineaNormal" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="cantLineaSimOnly" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="cantMaxEquipoAcoc" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="cantMaxEquipoFinan" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="cantMaxEquipoMulti" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="codRespuesta" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="descRespuesta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="totalEquiposAcoc" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="totalLineas" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "resumenDTO", propOrder = {
    "cantEquipoMultim",
    "cantEquipoNormal",
    "cantLineaMulti",
    "cantLineaNormal",
    "cantLineaSimOnly",
    "cantMaxEquipoAcoc",
    "cantMaxEquipoFinan",
    "cantMaxEquipoMulti",
    "codRespuesta",
    "descRespuesta",
    "totalEquiposAcoc",
    "totalLineas"
})
public class ResumenDTO {

    protected int cantEquipoMultim;
    protected int cantEquipoNormal;
    protected int cantLineaMulti;
    protected int cantLineaNormal;
    protected int cantLineaSimOnly;
    protected int cantMaxEquipoAcoc;
    protected int cantMaxEquipoFinan;
    protected int cantMaxEquipoMulti;
    protected int codRespuesta;
    protected String descRespuesta;
    protected int totalEquiposAcoc;
    protected int totalLineas;

    /**
     * Gets the value of the cantEquipoMultim property.
     * 
     */
    public int getCantEquipoMultim() {
        return cantEquipoMultim;
    }

    /**
     * Sets the value of the cantEquipoMultim property.
     * 
     */
    public void setCantEquipoMultim(int value) {
        this.cantEquipoMultim = value;
    }

    /**
     * Gets the value of the cantEquipoNormal property.
     * 
     */
    public int getCantEquipoNormal() {
        return cantEquipoNormal;
    }

    /**
     * Sets the value of the cantEquipoNormal property.
     * 
     */
    public void setCantEquipoNormal(int value) {
        this.cantEquipoNormal = value;
    }

    /**
     * Gets the value of the cantLineaMulti property.
     * 
     */
    public int getCantLineaMulti() {
        return cantLineaMulti;
    }

    /**
     * Sets the value of the cantLineaMulti property.
     * 
     */
    public void setCantLineaMulti(int value) {
        this.cantLineaMulti = value;
    }

    /**
     * Gets the value of the cantLineaNormal property.
     * 
     */
    public int getCantLineaNormal() {
        return cantLineaNormal;
    }

    /**
     * Sets the value of the cantLineaNormal property.
     * 
     */
    public void setCantLineaNormal(int value) {
        this.cantLineaNormal = value;
    }

    /**
     * Gets the value of the cantLineaSimOnly property.
     * 
     */
    public int getCantLineaSimOnly() {
        return cantLineaSimOnly;
    }

    /**
     * Sets the value of the cantLineaSimOnly property.
     * 
     */
    public void setCantLineaSimOnly(int value) {
        this.cantLineaSimOnly = value;
    }

    /**
     * Gets the value of the cantMaxEquipoAcoc property.
     * 
     */
    public int getCantMaxEquipoAcoc() {
        return cantMaxEquipoAcoc;
    }

    /**
     * Sets the value of the cantMaxEquipoAcoc property.
     * 
     */
    public void setCantMaxEquipoAcoc(int value) {
        this.cantMaxEquipoAcoc = value;
    }

    /**
     * Gets the value of the cantMaxEquipoFinan property.
     * 
     */
    public int getCantMaxEquipoFinan() {
        return cantMaxEquipoFinan;
    }

    /**
     * Sets the value of the cantMaxEquipoFinan property.
     * 
     */
    public void setCantMaxEquipoFinan(int value) {
        this.cantMaxEquipoFinan = value;
    }

    /**
     * Gets the value of the cantMaxEquipoMulti property.
     * 
     */
    public int getCantMaxEquipoMulti() {
        return cantMaxEquipoMulti;
    }

    /**
     * Sets the value of the cantMaxEquipoMulti property.
     * 
     */
    public void setCantMaxEquipoMulti(int value) {
        this.cantMaxEquipoMulti = value;
    }

    /**
     * Gets the value of the codRespuesta property.
     * 
     */
    public int getCodRespuesta() {
        return codRespuesta;
    }

    /**
     * Sets the value of the codRespuesta property.
     * 
     */
    public void setCodRespuesta(int value) {
        this.codRespuesta = value;
    }

    /**
     * Gets the value of the descRespuesta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescRespuesta() {
        return descRespuesta;
    }

    /**
     * Sets the value of the descRespuesta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescRespuesta(String value) {
        this.descRespuesta = value;
    }

    /**
     * Gets the value of the totalEquiposAcoc property.
     * 
     */
    public int getTotalEquiposAcoc() {
        return totalEquiposAcoc;
    }

    /**
     * Sets the value of the totalEquiposAcoc property.
     * 
     */
    public void setTotalEquiposAcoc(int value) {
        this.totalEquiposAcoc = value;
    }

    /**
     * Gets the value of the totalLineas property.
     * 
     */
    public int getTotalLineas() {
        return totalLineas;
    }

    /**
     * Sets the value of the totalLineas property.
     * 
     */
    public void setTotalLineas(int value) {
        this.totalLineas = value;
    }

}
