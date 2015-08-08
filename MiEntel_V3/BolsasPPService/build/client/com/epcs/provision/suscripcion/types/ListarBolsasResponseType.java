
package com.epcs.provision.suscripcion.types;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for listarBolsasResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="listarBolsasResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="mensaje">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="movil">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;extension base="{http://www.epcs.com/Provision/Suscripcion/types}movilType">
 *                         &lt;/extension>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="listadoCartas">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="detalleCartaServicio" maxOccurs="unbounded" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;extension base="{http://www.epcs.com/Provision/Suscripcion/types}detallePackBolsasType">
 *                                     &lt;sequence>
 *                                       &lt;element name="descripcionTecnica" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                       &lt;element name="precio" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                       &lt;element name="tipoOferta" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                       &lt;element name="unidad" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                       &lt;element name="destino" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                       &lt;element name="vigencia" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                       &lt;element name="orden" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                       &lt;element name="inicioExposicion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                       &lt;element name="finExposicion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                     &lt;/sequence>
 *                                   &lt;/extension>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
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
@XmlType(name = "listarBolsasResponseType", propOrder = {
    "mensaje"
})
public class ListarBolsasResponseType {

    @XmlElement(required = true)
    protected ListarBolsasResponseType.Mensaje mensaje;

    /**
     * Gets the value of the mensaje property.
     * 
     * @return
     *     possible object is
     *     {@link ListarBolsasResponseType.Mensaje }
     *     
     */
    public ListarBolsasResponseType.Mensaje getMensaje() {
        return mensaje;
    }

    /**
     * Sets the value of the mensaje property.
     * 
     * @param value
     *     allowed object is
     *     {@link ListarBolsasResponseType.Mensaje }
     *     
     */
    public void setMensaje(ListarBolsasResponseType.Mensaje value) {
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
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="movil">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;extension base="{http://www.epcs.com/Provision/Suscripcion/types}movilType">
     *               &lt;/extension>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="listadoCartas">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="detalleCartaServicio" maxOccurs="unbounded" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;extension base="{http://www.epcs.com/Provision/Suscripcion/types}detallePackBolsasType">
     *                           &lt;sequence>
     *                             &lt;element name="descripcionTecnica" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                             &lt;element name="precio" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                             &lt;element name="tipoOferta" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                             &lt;element name="unidad" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                             &lt;element name="destino" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                             &lt;element name="vigencia" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                             &lt;element name="orden" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                             &lt;element name="inicioExposicion" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                             &lt;element name="finExposicion" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                           &lt;/sequence>
     *                         &lt;/extension>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *               &lt;/restriction>
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
    @XmlType(name = "", propOrder = {
        "movil",
        "listadoCartas"
    })
    public static class Mensaje {

        @XmlElement(required = true)
        protected ListarBolsasResponseType.Mensaje.Movil movil;
        @XmlElement(required = true)
        protected ListarBolsasResponseType.Mensaje.ListadoCartas listadoCartas;

        /**
         * Gets the value of the movil property.
         * 
         * @return
         *     possible object is
         *     {@link ListarBolsasResponseType.Mensaje.Movil }
         *     
         */
        public ListarBolsasResponseType.Mensaje.Movil getMovil() {
            return movil;
        }

        /**
         * Sets the value of the movil property.
         * 
         * @param value
         *     allowed object is
         *     {@link ListarBolsasResponseType.Mensaje.Movil }
         *     
         */
        public void setMovil(ListarBolsasResponseType.Mensaje.Movil value) {
            this.movil = value;
        }

        /**
         * Gets the value of the listadoCartas property.
         * 
         * @return
         *     possible object is
         *     {@link ListarBolsasResponseType.Mensaje.ListadoCartas }
         *     
         */
        public ListarBolsasResponseType.Mensaje.ListadoCartas getListadoCartas() {
            return listadoCartas;
        }

        /**
         * Sets the value of the listadoCartas property.
         * 
         * @param value
         *     allowed object is
         *     {@link ListarBolsasResponseType.Mensaje.ListadoCartas }
         *     
         */
        public void setListadoCartas(ListarBolsasResponseType.Mensaje.ListadoCartas value) {
            this.listadoCartas = value;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="detalleCartaServicio" maxOccurs="unbounded" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;extension base="{http://www.epcs.com/Provision/Suscripcion/types}detallePackBolsasType">
         *                 &lt;sequence>
         *                   &lt;element name="descripcionTecnica" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                   &lt;element name="precio" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                   &lt;element name="tipoOferta" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                   &lt;element name="unidad" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                   &lt;element name="destino" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                   &lt;element name="vigencia" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                   &lt;element name="orden" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                   &lt;element name="inicioExposicion" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                   &lt;element name="finExposicion" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        @XmlType(name = "", propOrder = {
            "detalleCartaServicio"
        })
        public static class ListadoCartas {

            protected List<ListarBolsasResponseType.Mensaje.ListadoCartas.DetalleCartaServicio> detalleCartaServicio;

            /**
             * Gets the value of the detalleCartaServicio property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the detalleCartaServicio property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getDetalleCartaServicio().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link ListarBolsasResponseType.Mensaje.ListadoCartas.DetalleCartaServicio }
             * 
             * 
             */
            public List<ListarBolsasResponseType.Mensaje.ListadoCartas.DetalleCartaServicio> getDetalleCartaServicio() {
                if (detalleCartaServicio == null) {
                    detalleCartaServicio = new ArrayList<ListarBolsasResponseType.Mensaje.ListadoCartas.DetalleCartaServicio>();
                }
                return this.detalleCartaServicio;
            }


            /**
             * <p>Java class for anonymous complex type.
             * 
             * <p>The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType>
             *   &lt;complexContent>
             *     &lt;extension base="{http://www.epcs.com/Provision/Suscripcion/types}detallePackBolsasType">
             *       &lt;sequence>
             *         &lt;element name="descripcionTecnica" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *         &lt;element name="precio" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *         &lt;element name="tipoOferta" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *         &lt;element name="unidad" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *         &lt;element name="destino" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *         &lt;element name="vigencia" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *         &lt;element name="orden" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *         &lt;element name="inicioExposicion" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *         &lt;element name="finExposicion" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
                "descripcionTecnica",
                "precio",
                "tipoOferta",
                "unidad",
                "destino",
                "vigencia",
                "orden",
                "inicioExposicion",
                "finExposicion"
            })
            public static class DetalleCartaServicio
                extends DetallePackBolsasType
            {

                @XmlElement(required = true)
                protected String descripcionTecnica;
                @XmlElement(required = true)
                protected String precio;
                @XmlElement(required = true)
                protected String tipoOferta;
                @XmlElement(required = true)
                protected String unidad;
                @XmlElement(required = true)
                protected String destino;
                @XmlElement(required = true)
                protected String vigencia;
                @XmlElement(required = true)
                protected String orden;
                @XmlElement(required = true)
                protected String inicioExposicion;
                @XmlElement(required = true)
                protected String finExposicion;

                /**
                 * Gets the value of the descripcionTecnica property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getDescripcionTecnica() {
                    return descripcionTecnica;
                }

                /**
                 * Sets the value of the descripcionTecnica property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setDescripcionTecnica(String value) {
                    this.descripcionTecnica = value;
                }

                /**
                 * Gets the value of the precio property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getPrecio() {
                    return precio;
                }

                /**
                 * Sets the value of the precio property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setPrecio(String value) {
                    this.precio = value;
                }

                /**
                 * Gets the value of the tipoOferta property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getTipoOferta() {
                    return tipoOferta;
                }

                /**
                 * Sets the value of the tipoOferta property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setTipoOferta(String value) {
                    this.tipoOferta = value;
                }

                /**
                 * Gets the value of the unidad property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getUnidad() {
                    return unidad;
                }

                /**
                 * Sets the value of the unidad property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setUnidad(String value) {
                    this.unidad = value;
                }

                /**
                 * Gets the value of the destino property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getDestino() {
                    return destino;
                }

                /**
                 * Sets the value of the destino property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setDestino(String value) {
                    this.destino = value;
                }

                /**
                 * Gets the value of the vigencia property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getVigencia() {
                    return vigencia;
                }

                /**
                 * Sets the value of the vigencia property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setVigencia(String value) {
                    this.vigencia = value;
                }

                /**
                 * Gets the value of the orden property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getOrden() {
                    return orden;
                }

                /**
                 * Sets the value of the orden property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setOrden(String value) {
                    this.orden = value;
                }

                /**
                 * Gets the value of the inicioExposicion property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getInicioExposicion() {
                    return inicioExposicion;
                }

                /**
                 * Sets the value of the inicioExposicion property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setInicioExposicion(String value) {
                    this.inicioExposicion = value;
                }

                /**
                 * Gets the value of the finExposicion property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getFinExposicion() {
                    return finExposicion;
                }

                /**
                 * Sets the value of the finExposicion property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setFinExposicion(String value) {
                    this.finExposicion = value;
                }

            }

        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;extension base="{http://www.epcs.com/Provision/Suscripcion/types}movilType">
         *     &lt;/extension>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class Movil
            extends MovilType
        {


        }

    }

}
