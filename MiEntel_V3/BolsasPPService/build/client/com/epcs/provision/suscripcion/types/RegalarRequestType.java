
package com.epcs.provision.suscripcion.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for regalarRequestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="regalarRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="mensaje">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;extension base="{http://www.epcs.com/Provision/Suscripcion/types}requestType">
 *                 &lt;sequence>
 *                   &lt;element name="msisdnDestino" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="codigo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="plataforma" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="usuario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "regalarRequestType", propOrder = {
    "mensaje"
})
public class RegalarRequestType {

    @XmlElement(required = true)
    protected RegalarRequestType.Mensaje mensaje;

    /**
     * Gets the value of the mensaje property.
     * 
     * @return
     *     possible object is
     *     {@link RegalarRequestType.Mensaje }
     *     
     */
    public RegalarRequestType.Mensaje getMensaje() {
        return mensaje;
    }

    /**
     * Sets the value of the mensaje property.
     * 
     * @param value
     *     allowed object is
     *     {@link RegalarRequestType.Mensaje }
     *     
     */
    public void setMensaje(RegalarRequestType.Mensaje value) {
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
     *         &lt;element name="msisdnDestino" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="codigo" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="plataforma" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="usuario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "msisdnDestino",
        "codigo",
        "plataforma",
        "usuario"
    })
    public static class Mensaje
        extends RequestType
    {

        @XmlElement(required = true)
        protected String msisdnDestino;
        @XmlElement(required = true)
        protected String codigo;
        protected String plataforma;
        protected String usuario;

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
         * Gets the value of the plataforma property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPlataforma() {
            return plataforma;
        }

        /**
         * Sets the value of the plataforma property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPlataforma(String value) {
            this.plataforma = value;
        }

        /**
         * Gets the value of the usuario property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getUsuario() {
            return usuario;
        }

        /**
         * Sets the value of the usuario property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setUsuario(String value) {
            this.usuario = value;
        }

    }

}
