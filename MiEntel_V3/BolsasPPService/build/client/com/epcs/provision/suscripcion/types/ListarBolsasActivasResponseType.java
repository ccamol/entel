
package com.epcs.provision.suscripcion.types;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for listarBolsasActivasResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="listarBolsasActivasResponseType">
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
 *                             &lt;element name="iccid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="imsi" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="fechaActivacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="fechaDesactivo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="fechaDesactivacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="saldo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="estado" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="proveedor" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="pin" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="puk" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="pin2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="puk2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="plan" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="fechaUltimaLlamada" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="fechaConversion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="fechaEliminacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="categoria" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="recargas" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="cambiosPlan" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="fechaCambioPlan" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="frecuentes" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="creacionFrecuente" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="modificacionFrecuente" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="rutBSCS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="planBSCS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="estadoBSCS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="fechaActivacionBSCS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="grupoBSCS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                           &lt;/sequence>
 *                         &lt;/extension>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="listadoCartasActivas">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="detalleBono" maxOccurs="unbounded" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;extension base="{http://www.epcs.com/Provision/Suscripcion/types}detalleBonoType">
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
@XmlType(name = "listarBolsasActivasResponseType", propOrder = {
    "mensaje"
})
public class ListarBolsasActivasResponseType {

    @XmlElement(required = true)
    protected ListarBolsasActivasResponseType.Mensaje mensaje;

    /**
     * Gets the value of the mensaje property.
     * 
     * @return
     *     possible object is
     *     {@link ListarBolsasActivasResponseType.Mensaje }
     *     
     */
    public ListarBolsasActivasResponseType.Mensaje getMensaje() {
        return mensaje;
    }

    /**
     * Sets the value of the mensaje property.
     * 
     * @param value
     *     allowed object is
     *     {@link ListarBolsasActivasResponseType.Mensaje }
     *     
     */
    public void setMensaje(ListarBolsasActivasResponseType.Mensaje value) {
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
     *                   &lt;element name="iccid" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="imsi" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="fechaActivacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="fechaDesactivo" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="fechaDesactivacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="saldo" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="estado" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="proveedor" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="pin" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="puk" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="pin2" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="puk2" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="plan" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="fechaUltimaLlamada" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="fechaConversion" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="fechaEliminacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="categoria" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="recargas" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="cambiosPlan" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="fechaCambioPlan" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="frecuentes" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="creacionFrecuente" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="modificacionFrecuente" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="rutBSCS" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="planBSCS" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="estadoBSCS" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="fechaActivacionBSCS" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="grupoBSCS" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                 &lt;/sequence>
     *               &lt;/extension>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="listadoCartasActivas">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="detalleBono" maxOccurs="unbounded" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;extension base="{http://www.epcs.com/Provision/Suscripcion/types}detalleBonoType">
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
        "listadoCartasActivas"
    })
    public static class Mensaje {

        @XmlElement(required = true)
        protected ListarBolsasActivasResponseType.Mensaje.Movil movil;
        @XmlElement(required = true)
        protected ListarBolsasActivasResponseType.Mensaje.ListadoCartasActivas listadoCartasActivas;

        /**
         * Gets the value of the movil property.
         * 
         * @return
         *     possible object is
         *     {@link ListarBolsasActivasResponseType.Mensaje.Movil }
         *     
         */
        public ListarBolsasActivasResponseType.Mensaje.Movil getMovil() {
            return movil;
        }

        /**
         * Sets the value of the movil property.
         * 
         * @param value
         *     allowed object is
         *     {@link ListarBolsasActivasResponseType.Mensaje.Movil }
         *     
         */
        public void setMovil(ListarBolsasActivasResponseType.Mensaje.Movil value) {
            this.movil = value;
        }

        /**
         * Gets the value of the listadoCartasActivas property.
         * 
         * @return
         *     possible object is
         *     {@link ListarBolsasActivasResponseType.Mensaje.ListadoCartasActivas }
         *     
         */
        public ListarBolsasActivasResponseType.Mensaje.ListadoCartasActivas getListadoCartasActivas() {
            return listadoCartasActivas;
        }

        /**
         * Sets the value of the listadoCartasActivas property.
         * 
         * @param value
         *     allowed object is
         *     {@link ListarBolsasActivasResponseType.Mensaje.ListadoCartasActivas }
         *     
         */
        public void setListadoCartasActivas(ListarBolsasActivasResponseType.Mensaje.ListadoCartasActivas value) {
            this.listadoCartasActivas = value;
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
         *         &lt;element name="detalleBono" maxOccurs="unbounded" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;extension base="{http://www.epcs.com/Provision/Suscripcion/types}detalleBonoType">
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
            "detalleBono"
        })
        public static class ListadoCartasActivas {

            protected List<ListarBolsasActivasResponseType.Mensaje.ListadoCartasActivas.DetalleBono> detalleBono;

            /**
             * Gets the value of the detalleBono property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the detalleBono property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getDetalleBono().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link ListarBolsasActivasResponseType.Mensaje.ListadoCartasActivas.DetalleBono }
             * 
             * 
             */
            public List<ListarBolsasActivasResponseType.Mensaje.ListadoCartasActivas.DetalleBono> getDetalleBono() {
                if (detalleBono == null) {
                    detalleBono = new ArrayList<ListarBolsasActivasResponseType.Mensaje.ListadoCartasActivas.DetalleBono>();
                }
                return this.detalleBono;
            }


            /**
             * <p>Java class for anonymous complex type.
             * 
             * <p>The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType>
             *   &lt;complexContent>
             *     &lt;extension base="{http://www.epcs.com/Provision/Suscripcion/types}detalleBonoType">
             *     &lt;/extension>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class DetalleBono
                extends DetalleBonoType
            {


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
         *         &lt;element name="iccid" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="imsi" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="fechaActivacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="fechaDesactivo" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="fechaDesactivacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="saldo" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="estado" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="proveedor" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="pin" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="puk" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="pin2" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="puk2" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="plan" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="fechaUltimaLlamada" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="fechaConversion" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="fechaEliminacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="categoria" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="recargas" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="cambiosPlan" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="fechaCambioPlan" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="frecuentes" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="creacionFrecuente" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="modificacionFrecuente" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="rutBSCS" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="planBSCS" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="estadoBSCS" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="fechaActivacionBSCS" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="grupoBSCS" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
            "iccid",
            "imsi",
            "fechaActivacion",
            "fechaDesactivo",
            "fechaDesactivacion",
            "saldo",
            "estado",
            "proveedor",
            "pin",
            "puk",
            "pin2",
            "puk2",
            "plan",
            "fechaUltimaLlamada",
            "fechaConversion",
            "fechaEliminacion",
            "categoria",
            "recargas",
            "cambiosPlan",
            "fechaCambioPlan",
            "frecuentes",
            "creacionFrecuente",
            "modificacionFrecuente",
            "rutBSCS",
            "planBSCS",
            "estadoBSCS",
            "fechaActivacionBSCS",
            "grupoBSCS"
        })
        public static class Movil
            extends MovilType
        {

            @XmlElement(required = true)
            protected String iccid;
            @XmlElement(required = true)
            protected String imsi;
            @XmlElement(required = true)
            protected String fechaActivacion;
            @XmlElement(required = true)
            protected String fechaDesactivo;
            @XmlElement(required = true)
            protected String fechaDesactivacion;
            @XmlElement(required = true)
            protected String saldo;
            @XmlElement(required = true)
            protected String estado;
            @XmlElement(required = true)
            protected String proveedor;
            @XmlElement(required = true)
            protected String pin;
            @XmlElement(required = true)
            protected String puk;
            @XmlElement(required = true)
            protected String pin2;
            @XmlElement(required = true)
            protected String puk2;
            @XmlElement(required = true)
            protected String plan;
            @XmlElement(required = true)
            protected String fechaUltimaLlamada;
            @XmlElement(required = true)
            protected String fechaConversion;
            @XmlElement(required = true)
            protected String fechaEliminacion;
            @XmlElement(required = true)
            protected String categoria;
            @XmlElement(required = true)
            protected String recargas;
            @XmlElement(required = true)
            protected String cambiosPlan;
            @XmlElement(required = true)
            protected String fechaCambioPlan;
            @XmlElement(required = true)
            protected String frecuentes;
            @XmlElement(required = true)
            protected String creacionFrecuente;
            @XmlElement(required = true)
            protected String modificacionFrecuente;
            @XmlElement(required = true)
            protected String rutBSCS;
            @XmlElement(required = true)
            protected String planBSCS;
            @XmlElement(required = true)
            protected String estadoBSCS;
            @XmlElement(required = true)
            protected String fechaActivacionBSCS;
            @XmlElement(required = true)
            protected String grupoBSCS;

            /**
             * Gets the value of the iccid property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getIccid() {
                return iccid;
            }

            /**
             * Sets the value of the iccid property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setIccid(String value) {
                this.iccid = value;
            }

            /**
             * Gets the value of the imsi property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getImsi() {
                return imsi;
            }

            /**
             * Sets the value of the imsi property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setImsi(String value) {
                this.imsi = value;
            }

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
             * Gets the value of the fechaDesactivo property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getFechaDesactivo() {
                return fechaDesactivo;
            }

            /**
             * Sets the value of the fechaDesactivo property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setFechaDesactivo(String value) {
                this.fechaDesactivo = value;
            }

            /**
             * Gets the value of the fechaDesactivacion property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getFechaDesactivacion() {
                return fechaDesactivacion;
            }

            /**
             * Sets the value of the fechaDesactivacion property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setFechaDesactivacion(String value) {
                this.fechaDesactivacion = value;
            }

            /**
             * Gets the value of the saldo property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getSaldo() {
                return saldo;
            }

            /**
             * Sets the value of the saldo property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setSaldo(String value) {
                this.saldo = value;
            }

            /**
             * Gets the value of the estado property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getEstado() {
                return estado;
            }

            /**
             * Sets the value of the estado property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setEstado(String value) {
                this.estado = value;
            }

            /**
             * Gets the value of the proveedor property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getProveedor() {
                return proveedor;
            }

            /**
             * Sets the value of the proveedor property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setProveedor(String value) {
                this.proveedor = value;
            }

            /**
             * Gets the value of the pin property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPin() {
                return pin;
            }

            /**
             * Sets the value of the pin property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPin(String value) {
                this.pin = value;
            }

            /**
             * Gets the value of the puk property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPuk() {
                return puk;
            }

            /**
             * Sets the value of the puk property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPuk(String value) {
                this.puk = value;
            }

            /**
             * Gets the value of the pin2 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPin2() {
                return pin2;
            }

            /**
             * Sets the value of the pin2 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPin2(String value) {
                this.pin2 = value;
            }

            /**
             * Gets the value of the puk2 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPuk2() {
                return puk2;
            }

            /**
             * Sets the value of the puk2 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPuk2(String value) {
                this.puk2 = value;
            }

            /**
             * Gets the value of the plan property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPlan() {
                return plan;
            }

            /**
             * Sets the value of the plan property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPlan(String value) {
                this.plan = value;
            }

            /**
             * Gets the value of the fechaUltimaLlamada property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getFechaUltimaLlamada() {
                return fechaUltimaLlamada;
            }

            /**
             * Sets the value of the fechaUltimaLlamada property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setFechaUltimaLlamada(String value) {
                this.fechaUltimaLlamada = value;
            }

            /**
             * Gets the value of the fechaConversion property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getFechaConversion() {
                return fechaConversion;
            }

            /**
             * Sets the value of the fechaConversion property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setFechaConversion(String value) {
                this.fechaConversion = value;
            }

            /**
             * Gets the value of the fechaEliminacion property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getFechaEliminacion() {
                return fechaEliminacion;
            }

            /**
             * Sets the value of the fechaEliminacion property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setFechaEliminacion(String value) {
                this.fechaEliminacion = value;
            }

            /**
             * Gets the value of the categoria property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCategoria() {
                return categoria;
            }

            /**
             * Sets the value of the categoria property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCategoria(String value) {
                this.categoria = value;
            }

            /**
             * Gets the value of the recargas property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getRecargas() {
                return recargas;
            }

            /**
             * Sets the value of the recargas property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setRecargas(String value) {
                this.recargas = value;
            }

            /**
             * Gets the value of the cambiosPlan property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCambiosPlan() {
                return cambiosPlan;
            }

            /**
             * Sets the value of the cambiosPlan property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCambiosPlan(String value) {
                this.cambiosPlan = value;
            }

            /**
             * Gets the value of the fechaCambioPlan property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getFechaCambioPlan() {
                return fechaCambioPlan;
            }

            /**
             * Sets the value of the fechaCambioPlan property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setFechaCambioPlan(String value) {
                this.fechaCambioPlan = value;
            }

            /**
             * Gets the value of the frecuentes property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getFrecuentes() {
                return frecuentes;
            }

            /**
             * Sets the value of the frecuentes property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setFrecuentes(String value) {
                this.frecuentes = value;
            }

            /**
             * Gets the value of the creacionFrecuente property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCreacionFrecuente() {
                return creacionFrecuente;
            }

            /**
             * Sets the value of the creacionFrecuente property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCreacionFrecuente(String value) {
                this.creacionFrecuente = value;
            }

            /**
             * Gets the value of the modificacionFrecuente property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getModificacionFrecuente() {
                return modificacionFrecuente;
            }

            /**
             * Sets the value of the modificacionFrecuente property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setModificacionFrecuente(String value) {
                this.modificacionFrecuente = value;
            }

            /**
             * Gets the value of the rutBSCS property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getRutBSCS() {
                return rutBSCS;
            }

            /**
             * Sets the value of the rutBSCS property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setRutBSCS(String value) {
                this.rutBSCS = value;
            }

            /**
             * Gets the value of the planBSCS property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPlanBSCS() {
                return planBSCS;
            }

            /**
             * Sets the value of the planBSCS property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPlanBSCS(String value) {
                this.planBSCS = value;
            }

            /**
             * Gets the value of the estadoBSCS property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getEstadoBSCS() {
                return estadoBSCS;
            }

            /**
             * Sets the value of the estadoBSCS property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setEstadoBSCS(String value) {
                this.estadoBSCS = value;
            }

            /**
             * Gets the value of the fechaActivacionBSCS property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getFechaActivacionBSCS() {
                return fechaActivacionBSCS;
            }

            /**
             * Sets the value of the fechaActivacionBSCS property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setFechaActivacionBSCS(String value) {
                this.fechaActivacionBSCS = value;
            }

            /**
             * Gets the value of the grupoBSCS property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getGrupoBSCS() {
                return grupoBSCS;
            }

            /**
             * Sets the value of the grupoBSCS property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setGrupoBSCS(String value) {
                this.grupoBSCS = value;
            }

        }

    }

}
