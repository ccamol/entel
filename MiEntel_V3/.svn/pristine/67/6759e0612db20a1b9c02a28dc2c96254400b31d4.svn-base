
package com.epcs.provision.suscripcion.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for comprarResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="comprarResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="mensaje">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;extension base="{http://www.epcs.com/Provision/Suscripcion/types}responseCompraType">
 *                 &lt;sequence>
 *                   &lt;element name="publicidad" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/extension>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "comprarResponseType", propOrder = {
    "mensaje"
})
public class ComprarResponseType {

    @XmlElement(required = true)
    protected ComprarResponseType.Mensaje mensaje;

    /**
     * Gets the value of the mensaje property.
     * 
     * @return
     *     possible object is
     *     {@link ComprarResponseType.Mensaje }
     *     
     */
    public ComprarResponseType.Mensaje getMensaje() {
        return mensaje;
    }

    /**
     * Sets the value of the mensaje property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComprarResponseType.Mensaje }
     *     
     */
    public void setMensaje(ComprarResponseType.Mensaje value) {
        this.mensaje = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;extension base="{http://www.epcs.com/Provision/Suscripcion/types}responseCompraType">
     *       &lt;sequence>
     *         &lt;element name="publicidad" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/sequence>
     *     &lt;/extension>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "publicidad"
    })
    public static class Mensaje
        extends ResponseCompraType
    {

        @XmlElement(required = true)
        protected String publicidad;

        /**
         * Gets the value of the publicidad property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPublicidad() {
            return publicidad;
        }

        /**
         * Sets the value of the publicidad property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPublicidad(String value) {
            this.publicidad = value;
        }

    }

}
