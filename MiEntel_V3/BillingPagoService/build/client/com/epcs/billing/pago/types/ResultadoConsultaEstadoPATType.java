
package com.epcs.billing.pago.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ResultadoConsultaEstadoPATType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResultadoConsultaEstadoPATType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="estadoPAT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fechaSuscripcion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResultadoConsultaEstadoPATType", propOrder = {
    "estadoPAT",
    "fechaSuscripcion"
})
public class ResultadoConsultaEstadoPATType {

    @XmlElement(required = true)
    protected String estadoPAT;
    @XmlElement(required = true)
    protected String fechaSuscripcion;

    /**
     * Gets the value of the estadoPAT property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstadoPAT() {
        return estadoPAT;
    }

    /**
     * Sets the value of the estadoPAT property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstadoPAT(String value) {
        this.estadoPAT = value;
    }

    /**
     * Gets the value of the fechaSuscripcion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaSuscripcion() {
        return fechaSuscripcion;
    }

    /**
     * Sets the value of the fechaSuscripcion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaSuscripcion(String value) {
        this.fechaSuscripcion = value;
    }

}
