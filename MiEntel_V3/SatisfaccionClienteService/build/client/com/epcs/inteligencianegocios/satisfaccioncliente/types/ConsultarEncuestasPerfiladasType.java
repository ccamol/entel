
package com.epcs.inteligencianegocios.satisfaccioncliente.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ConsultarEncuestasPerfiladasType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ConsultarEncuestasPerfiladasType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="msisdn" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="rut" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="edad" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codSexo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codBscs2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ipCliente" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codVisualizacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codMercado" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="idp" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codZona" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConsultarEncuestasPerfiladasType", propOrder = {
    "msisdn",
    "rut",
    "edad",
    "codSexo",
    "codBscs2",
    "ipCliente",
    "codVisualizacion",
    "codMercado",
    "idp",
    "codZona"
})
public class ConsultarEncuestasPerfiladasType {

    @XmlElement(required = true)
    protected String msisdn;
    @XmlElement(required = true)
    protected String rut;
    @XmlElement(required = true)
    protected String edad;
    @XmlElement(required = true)
    protected String codSexo;
    @XmlElement(required = true)
    protected String codBscs2;
    @XmlElement(required = true)
    protected String ipCliente;
    @XmlElement(required = true)
    protected String codVisualizacion;
    @XmlElement(required = true)
    protected String codMercado;
    @XmlElement(required = true)
    protected String idp;
    @XmlElement(required = true)
    protected String codZona;

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
     * Gets the value of the edad property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEdad() {
        return edad;
    }

    /**
     * Sets the value of the edad property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEdad(String value) {
        this.edad = value;
    }

    /**
     * Gets the value of the codSexo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodSexo() {
        return codSexo;
    }

    /**
     * Sets the value of the codSexo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodSexo(String value) {
        this.codSexo = value;
    }

    /**
     * Gets the value of the codBscs2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodBscs2() {
        return codBscs2;
    }

    /**
     * Sets the value of the codBscs2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodBscs2(String value) {
        this.codBscs2 = value;
    }

    /**
     * Gets the value of the ipCliente property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIpCliente() {
        return ipCliente;
    }

    /**
     * Sets the value of the ipCliente property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIpCliente(String value) {
        this.ipCliente = value;
    }

    /**
     * Gets the value of the codVisualizacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodVisualizacion() {
        return codVisualizacion;
    }

    /**
     * Sets the value of the codVisualizacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodVisualizacion(String value) {
        this.codVisualizacion = value;
    }

    /**
     * Gets the value of the codMercado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodMercado() {
        return codMercado;
    }

    /**
     * Sets the value of the codMercado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodMercado(String value) {
        this.codMercado = value;
    }

    /**
     * Gets the value of the idp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdp() {
        return idp;
    }

    /**
     * Sets the value of the idp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdp(String value) {
        this.idp = value;
    }

    /**
     * Gets the value of the codZona property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodZona() {
        return codZona;
    }

    /**
     * Sets the value of the codZona property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodZona(String value) {
        this.codZona = value;
    }

}
