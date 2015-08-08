
package com.epcs.provision.suscripcion.types;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for listarBolsasActivasBAMResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="listarBolsasActivasBAMResponseType">
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
 *                           &lt;sequence>
 *                             &lt;element name="tariffIdOPSC" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                             &lt;element name="saldoPlan" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="fechaExpiracionSaldo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="cuotaUtilizadaPlan" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                           &lt;/sequence>
 *                         &lt;/extension>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="cartasActivas">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="detalleCartaServicio" maxOccurs="unbounded" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;extension base="{http://www.epcs.com/Provision/Suscripcion/types}detallePackBolsasType">
 *                                     &lt;sequence>
 *                                       &lt;element name="fechaActivacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                       &lt;element name="fechaExpiracion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                       &lt;element name="cuotaUtilizadaVoucher" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                                       &lt;element name="cuotaInicialVoucher" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
@XmlType(name = "listarBolsasActivasBAMResponseType", propOrder = {
    "mensaje"
})
public class ListarBolsasActivasBAMResponseType {

    @XmlElement(required = true)
    protected ListarBolsasActivasBAMResponseType.Mensaje mensaje;

    /**
     * Gets the value of the mensaje property.
     * 
     * @return
     *     possible object is
     *     {@link ListarBolsasActivasBAMResponseType.Mensaje }
     *     
     */
    public ListarBolsasActivasBAMResponseType.Mensaje getMensaje() {
        return mensaje;
    }

    /**
     * Sets the value of the mensaje property.
     * 
     * @param value
     *     allowed object is
     *     {@link ListarBolsasActivasBAMResponseType.Mensaje }
     *     
     */
    public void setMensaje(ListarBolsasActivasBAMResponseType.Mensaje value) {
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
     *                 &lt;sequence>
     *                   &lt;element name="tariffIdOPSC" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *                   &lt;element name="saldoPlan" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="fechaExpiracionSaldo" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="cuotaUtilizadaPlan" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *                 &lt;/sequence>
     *               &lt;/extension>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="cartasActivas">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="detalleCartaServicio" maxOccurs="unbounded" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;extension base="{http://www.epcs.com/Provision/Suscripcion/types}detallePackBolsasType">
     *                           &lt;sequence>
     *                             &lt;element name="fechaActivacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                             &lt;element name="fechaExpiracion" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                             &lt;element name="cuotaUtilizadaVoucher" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *                             &lt;element name="cuotaInicialVoucher" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
        "cartasActivas"
    })
    public static class Mensaje {

        @XmlElement(required = true)
        protected ListarBolsasActivasBAMResponseType.Mensaje.Movil movil;
        @XmlElement(required = true)
        protected ListarBolsasActivasBAMResponseType.Mensaje.CartasActivas cartasActivas;

        /**
         * Gets the value of the movil property.
         * 
         * @return
         *     possible object is
         *     {@link ListarBolsasActivasBAMResponseType.Mensaje.Movil }
         *     
         */
        public ListarBolsasActivasBAMResponseType.Mensaje.Movil getMovil() {
            return movil;
        }

        /**
         * Sets the value of the movil property.
         * 
         * @param value
         *     allowed object is
         *     {@link ListarBolsasActivasBAMResponseType.Mensaje.Movil }
         *     
         */
        public void setMovil(ListarBolsasActivasBAMResponseType.Mensaje.Movil value) {
            this.movil = value;
        }

        /**
         * Gets the value of the cartasActivas property.
         * 
         * @return
         *     possible object is
         *     {@link ListarBolsasActivasBAMResponseType.Mensaje.CartasActivas }
         *     
         */
        public ListarBolsasActivasBAMResponseType.Mensaje.CartasActivas getCartasActivas() {
            return cartasActivas;
        }

        /**
         * Sets the value of the cartasActivas property.
         * 
         * @param value
         *     allowed object is
         *     {@link ListarBolsasActivasBAMResponseType.Mensaje.CartasActivas }
         *     
         */
        public void setCartasActivas(ListarBolsasActivasBAMResponseType.Mensaje.CartasActivas value) {
            this.cartasActivas = value;
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
         *                   &lt;element name="fechaActivacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                   &lt;element name="fechaExpiracion" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                   &lt;element name="cuotaUtilizadaVoucher" type="{http://www.w3.org/2001/XMLSchema}int"/>
         *                   &lt;element name="cuotaInicialVoucher" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
        public static class CartasActivas {

            protected List<ListarBolsasActivasBAMResponseType.Mensaje.CartasActivas.DetalleCartaServicio> detalleCartaServicio;

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
             * {@link ListarBolsasActivasBAMResponseType.Mensaje.CartasActivas.DetalleCartaServicio }
             * 
             * 
             */
            public List<ListarBolsasActivasBAMResponseType.Mensaje.CartasActivas.DetalleCartaServicio> getDetalleCartaServicio() {
                if (detalleCartaServicio == null) {
                    detalleCartaServicio = new ArrayList<ListarBolsasActivasBAMResponseType.Mensaje.CartasActivas.DetalleCartaServicio>();
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
             *         &lt;element name="fechaActivacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *         &lt;element name="fechaExpiracion" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *         &lt;element name="cuotaUtilizadaVoucher" type="{http://www.w3.org/2001/XMLSchema}int"/>
             *         &lt;element name="cuotaInicialVoucher" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
                "fechaActivacion",
                "fechaExpiracion",
                "cuotaUtilizadaVoucher",
                "cuotaInicialVoucher"
            })
            public static class DetalleCartaServicio
                extends DetallePackBolsasType
            {

                @XmlElement(required = true)
                protected String fechaActivacion;
                @XmlElement(required = true)
                protected String fechaExpiracion;
                protected int cuotaUtilizadaVoucher;
                protected int cuotaInicialVoucher;

                /**
                 * Gets the value of the fechaActivacion property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getFechaActivacion() {
                    return fechaActivacion;
                }

                /**
                 * Sets the value of the fechaActivacion property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setFechaActivacion(String value) {
                    this.fechaActivacion = value;
                }

                /**
                 * Gets the value of the fechaExpiracion property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getFechaExpiracion() {
                    return fechaExpiracion;
                }

                /**
                 * Sets the value of the fechaExpiracion property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setFechaExpiracion(String value) {
                    this.fechaExpiracion = value;
                }

                /**
                 * Gets the value of the cuotaUtilizadaVoucher property.
                 * 
                 */
                public int getCuotaUtilizadaVoucher() {
                    return cuotaUtilizadaVoucher;
                }

                /**
                 * Sets the value of the cuotaUtilizadaVoucher property.
                 * 
                 */
                public void setCuotaUtilizadaVoucher(int value) {
                    this.cuotaUtilizadaVoucher = value;
                }

                /**
                 * Gets the value of the cuotaInicialVoucher property.
                 * 
                 */
                public int getCuotaInicialVoucher() {
                    return cuotaInicialVoucher;
                }

                /**
                 * Sets the value of the cuotaInicialVoucher property.
                 * 
                 */
                public void setCuotaInicialVoucher(int value) {
                    this.cuotaInicialVoucher = value;
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
         *       &lt;sequence>
         *         &lt;element name="tariffIdOPSC" type="{http://www.w3.org/2001/XMLSchema}int"/>
         *         &lt;element name="saldoPlan" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="fechaExpiracionSaldo" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="cuotaUtilizadaPlan" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
            "tariffIdOPSC",
            "saldoPlan",
            "fechaExpiracionSaldo",
            "cuotaUtilizadaPlan"
        })
        public static class Movil
            extends MovilType
        {

            protected int tariffIdOPSC;
            @XmlElement(required = true)
            protected String saldoPlan;
            @XmlElement(required = true)
            protected String fechaExpiracionSaldo;
            protected int cuotaUtilizadaPlan;

            /**
             * Gets the value of the tariffIdOPSC property.
             * 
             */
            public int getTariffIdOPSC() {
                return tariffIdOPSC;
            }

            /**
             * Sets the value of the tariffIdOPSC property.
             * 
             */
            public void setTariffIdOPSC(int value) {
                this.tariffIdOPSC = value;
            }

            /**
             * Gets the value of the saldoPlan property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getSaldoPlan() {
                return saldoPlan;
            }

            /**
             * Sets the value of the saldoPlan property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setSaldoPlan(String value) {
                this.saldoPlan = value;
            }

            /**
             * Gets the value of the fechaExpiracionSaldo property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getFechaExpiracionSaldo() {
                return fechaExpiracionSaldo;
            }

            /**
             * Sets the value of the fechaExpiracionSaldo property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setFechaExpiracionSaldo(String value) {
                this.fechaExpiracionSaldo = value;
            }

            /**
             * Gets the value of the cuotaUtilizadaPlan property.
             * 
             */
            public int getCuotaUtilizadaPlan() {
                return cuotaUtilizadaPlan;
            }

            /**
             * Sets the value of the cuotaUtilizadaPlan property.
             * 
             */
            public void setCuotaUtilizadaPlan(int value) {
                this.cuotaUtilizadaPlan = value;
            }

        }

    }

}
