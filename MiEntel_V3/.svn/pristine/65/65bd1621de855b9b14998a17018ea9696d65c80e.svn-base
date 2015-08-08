
package com.epcs.provision.suscripcion.types;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for listarBolsasDisponiblesBAMResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="listarBolsasDisponiblesBAMResponseType">
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
 *                             &lt;element name="saldoPlan" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="fechaExpiracionSaldo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="grupo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                             &lt;element name="limiteCreditoTotal" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                             &lt;element name="limiteCreditoAcumulado" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                           &lt;/sequence>
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
 *                                       &lt;element name="precio" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
@XmlType(name = "listarBolsasDisponiblesBAMResponseType", propOrder = {
    "mensaje"
})
public class ListarBolsasDisponiblesBAMResponseType {

    @XmlElement(required = true)
    protected ListarBolsasDisponiblesBAMResponseType.Mensaje mensaje;

    /**
     * Gets the value of the mensaje property.
     * 
     * @return
     *     possible object is
     *     {@link ListarBolsasDisponiblesBAMResponseType.Mensaje }
     *     
     */
    public ListarBolsasDisponiblesBAMResponseType.Mensaje getMensaje() {
        return mensaje;
    }

    /**
     * Sets the value of the mensaje property.
     * 
     * @param value
     *     allowed object is
     *     {@link ListarBolsasDisponiblesBAMResponseType.Mensaje }
     *     
     */
    public void setMensaje(ListarBolsasDisponiblesBAMResponseType.Mensaje value) {
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
     *                   &lt;element name="saldoPlan" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="fechaExpiracionSaldo" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="grupo" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *                   &lt;element name="limiteCreditoTotal" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *                   &lt;element name="limiteCreditoAcumulado" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *                 &lt;/sequence>
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
     *                             &lt;element name="precio" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
        "listadoCartas"
    })
    public static class Mensaje {

        @XmlElement(required = true)
        protected ListarBolsasDisponiblesBAMResponseType.Mensaje.Movil movil;
        @XmlElement(required = true)
        protected ListarBolsasDisponiblesBAMResponseType.Mensaje.ListadoCartas listadoCartas;

        /**
         * Gets the value of the movil property.
         * 
         * @return
         *     possible object is
         *     {@link ListarBolsasDisponiblesBAMResponseType.Mensaje.Movil }
         *     
         */
        public ListarBolsasDisponiblesBAMResponseType.Mensaje.Movil getMovil() {
            return movil;
        }

        /**
         * Sets the value of the movil property.
         * 
         * @param value
         *     allowed object is
         *     {@link ListarBolsasDisponiblesBAMResponseType.Mensaje.Movil }
         *     
         */
        public void setMovil(ListarBolsasDisponiblesBAMResponseType.Mensaje.Movil value) {
            this.movil = value;
        }

        /**
         * Gets the value of the listadoCartas property.
         * 
         * @return
         *     possible object is
         *     {@link ListarBolsasDisponiblesBAMResponseType.Mensaje.ListadoCartas }
         *     
         */
        public ListarBolsasDisponiblesBAMResponseType.Mensaje.ListadoCartas getListadoCartas() {
            return listadoCartas;
        }

        /**
         * Sets the value of the listadoCartas property.
         * 
         * @param value
         *     allowed object is
         *     {@link ListarBolsasDisponiblesBAMResponseType.Mensaje.ListadoCartas }
         *     
         */
        public void setListadoCartas(ListarBolsasDisponiblesBAMResponseType.Mensaje.ListadoCartas value) {
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
         *                   &lt;element name="precio" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
        public static class ListadoCartas {

            protected List<ListarBolsasDisponiblesBAMResponseType.Mensaje.ListadoCartas.DetalleCartaServicio> detalleCartaServicio;

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
             * {@link ListarBolsasDisponiblesBAMResponseType.Mensaje.ListadoCartas.DetalleCartaServicio }
             * 
             * 
             */
            public List<ListarBolsasDisponiblesBAMResponseType.Mensaje.ListadoCartas.DetalleCartaServicio> getDetalleCartaServicio() {
                if (detalleCartaServicio == null) {
                    detalleCartaServicio = new ArrayList<ListarBolsasDisponiblesBAMResponseType.Mensaje.ListadoCartas.DetalleCartaServicio>();
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
             *         &lt;element name="precio" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
                "precio",
                "cuotaInicialVoucher"
            })
            public static class DetalleCartaServicio
                extends DetallePackBolsasType
            {

                protected int precio;
                protected int cuotaInicialVoucher;

                /**
                 * Gets the value of the precio property.
                 * 
                 */
                public int getPrecio() {
                    return precio;
                }

                /**
                 * Sets the value of the precio property.
                 * 
                 */
                public void setPrecio(int value) {
                    this.precio = value;
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
         *         &lt;element name="saldoPlan" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="fechaExpiracionSaldo" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="grupo" type="{http://www.w3.org/2001/XMLSchema}int"/>
         *         &lt;element name="limiteCreditoTotal" type="{http://www.w3.org/2001/XMLSchema}int"/>
         *         &lt;element name="limiteCreditoAcumulado" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
            "saldoPlan",
            "fechaExpiracionSaldo",
            "grupo",
            "limiteCreditoTotal",
            "limiteCreditoAcumulado"
        })
        public static class Movil
            extends MovilType
        {

            @XmlElement(required = true)
            protected String saldoPlan;
            @XmlElement(required = true)
            protected String fechaExpiracionSaldo;
            protected int grupo;
            protected int limiteCreditoTotal;
            protected int limiteCreditoAcumulado;

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
             * Gets the value of the grupo property.
             * 
             */
            public int getGrupo() {
                return grupo;
            }

            /**
             * Sets the value of the grupo property.
             * 
             */
            public void setGrupo(int value) {
                this.grupo = value;
            }

            /**
             * Gets the value of the limiteCreditoTotal property.
             * 
             */
            public int getLimiteCreditoTotal() {
                return limiteCreditoTotal;
            }

            /**
             * Sets the value of the limiteCreditoTotal property.
             * 
             */
            public void setLimiteCreditoTotal(int value) {
                this.limiteCreditoTotal = value;
            }

            /**
             * Gets the value of the limiteCreditoAcumulado property.
             * 
             */
            public int getLimiteCreditoAcumulado() {
                return limiteCreditoAcumulado;
            }

            /**
             * Sets the value of the limiteCreditoAcumulado property.
             * 
             */
            public void setLimiteCreditoAcumulado(int value) {
                this.limiteCreditoAcumulado = value;
            }

        }

    }

}
