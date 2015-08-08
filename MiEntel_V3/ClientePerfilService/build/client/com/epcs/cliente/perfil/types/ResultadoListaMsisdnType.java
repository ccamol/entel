
package com.epcs.cliente.perfil.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ResultadoListaMsisdnType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResultadoListaMsisdnType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="respuesta" type="{http://www.epcs.com/cliente/perfil/types}RespuestaType"/>
 *         &lt;element name="listaMsisdn" type="{http://www.epcs.com/cliente/perfil/types}ListaMsisdnType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResultadoListaMsisdnType", propOrder = {
    "respuesta",
    "listaMsisdn"
})
public class ResultadoListaMsisdnType {

    @XmlElement(required = true)
    protected RespuestaType respuesta;
    @XmlElement(required = true)
    protected ListaMsisdnType listaMsisdn;

    /**
     * Gets the value of the respuesta property.
     * 
     * @return
     *     possible object is
     *     {@link RespuestaType }
     *     
     */
    public RespuestaType getRespuesta() {
        return respuesta;
    }

    /**
     * Sets the value of the respuesta property.
     * 
     * @param value
     *     allowed object is
     *     {@link RespuestaType }
     *     
     */
    public void setRespuesta(RespuestaType value) {
        this.respuesta = value;
    }

    /**
     * Gets the value of the listaMsisdn property.
     * 
     * @return
     *     possible object is
     *     {@link ListaMsisdnType }
     *     
     */
    public ListaMsisdnType getListaMsisdn() {
        return listaMsisdn;
    }

    /**
     * Sets the value of the listaMsisdn property.
     * 
     * @param value
     *     allowed object is
     *     {@link ListaMsisdnType }
     *     
     */
    public void setListaMsisdn(ListaMsisdnType value) {
        this.listaMsisdn = value;
    }

}
