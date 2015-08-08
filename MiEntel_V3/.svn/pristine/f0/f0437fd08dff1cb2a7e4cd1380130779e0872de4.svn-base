
package com.epcs.billing.recarga.types;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for InsertarRegistroRecWebPayType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="InsertarRegistroRecWebPayType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ordCompra" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IDP" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="parametro4" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tipoPago" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fechaTransaccion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="detalleMonto" type="{http://www.epcs.com/billing/recarga/types}DetalleMontoType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InsertarRegistroRecWebPayType", propOrder = {
    "ordCompra",
    "idp",
    "parametro4",
    "tipoPago",
    "fechaTransaccion",
    "detalleMonto"
})
public class InsertarRegistroRecWebPayType {

    @XmlElement(required = true)
    protected String ordCompra;
    @XmlElement(name = "IDP", required = true)
    protected String idp;
    @XmlElement(required = true)
    protected String parametro4;
    @XmlElement(required = true)
    protected String tipoPago;
    @XmlElement(required = true)
    protected String fechaTransaccion;
    protected List<DetalleMontoType> detalleMonto;

    /**
     * Gets the value of the ordCompra property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrdCompra() {
        return ordCompra;
    }

    /**
     * Sets the value of the ordCompra property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrdCompra(String value) {
        this.ordCompra = value;
    }

    /**
     * Gets the value of the idp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIDP() {
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
    public void setIDP(String value) {
        this.idp = value;
    }

    /**
     * Gets the value of the parametro4 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParametro4() {
        return parametro4;
    }

    /**
     * Sets the value of the parametro4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParametro4(String value) {
        this.parametro4 = value;
    }

    /**
     * Gets the value of the tipoPago property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoPago() {
        return tipoPago;
    }

    /**
     * Sets the value of the tipoPago property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoPago(String value) {
        this.tipoPago = value;
    }

    /**
     * Gets the value of the fechaTransaccion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaTransaccion() {
        return fechaTransaccion;
    }

    /**
     * Sets the value of the fechaTransaccion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaTransaccion(String value) {
        this.fechaTransaccion = value;
    }

    /**
     * Gets the value of the detalleMonto property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the detalleMonto property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDetalleMonto().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DetalleMontoType }
     * 
     * 
     */
    public List<DetalleMontoType> getDetalleMonto() {
        if (detalleMonto == null) {
            detalleMonto = new ArrayList<DetalleMontoType>();
        }
        return this.detalleMonto;
    }

}
