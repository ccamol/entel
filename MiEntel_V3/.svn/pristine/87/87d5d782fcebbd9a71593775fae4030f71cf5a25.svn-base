
package com.epcs.provision.suscripcion.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for validacionRegaloRequestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="validacionRegaloRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="mensaje">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;extension base="{http://www.epcs.com/Provision/Suscripcion/types}requestType">
 *                 &lt;sequence>
 *                   &lt;element name="codigo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="msisdnDestino" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "validacionRegaloRequestType", propOrder = {
    "mensaje"
})
public class ValidacionRegaloRequestType {

    @XmlElement(required = true)
    protected ValidacionRegaloRequestType.Mensaje mensaje;

    /**
     * Gets the value of the mensaje property.
     * 
     * @return
     *     possible object is
     *     {@link ValidacionRegaloRequestType.Mensaje }
     *     
     */
    public ValidacionRegaloRequestType.Mensaje getMensaje() {
        return mensaje;
    }

    /**
     * Sets the value of the mensaje property.
     * 
     * @param value
     *     allowed object is
     *     {@link ValidacionRegaloRequestType.Mensaje }
     *     
     */
    public void setMensaje(ValidacionRegaloRequestType.Mensaje value) {
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
     *     &lt;extension base="{http://www.epcs.com/Provision/Suscripcion/types}requestType">
     *       &lt;sequence>
     *         &lt;element name="codigo" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="msisdnDestino" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "codigo",
        "msisdnDestino"
    })
    public static class Mensaje
        extends RequestType
    {

        @XmlElement(required = true)
        protected String codigo;
        @XmlElement(required = true)
        protected String msisdnDestino;

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
         * Gets the value of the msisdnDestino property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMsisdnDestino() {
            return msisdnDestino;
        }

        /**
         * Sets the value of the msisdnDestino property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMsisdnDestino(String value) {
            this.msisdnDestino = value;
        }

    }

}
