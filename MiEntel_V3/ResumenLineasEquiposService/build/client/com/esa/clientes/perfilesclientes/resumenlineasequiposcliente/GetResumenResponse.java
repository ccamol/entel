
package com.esa.clientes.perfilesclientes.resumenlineasequiposcliente;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getResumenResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getResumenResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="resumenDTO" type="{http://www.esa.com/Clientes/PerfilesClientes/resumenLineasEquiposCliente}resumenDTO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getResumenResponse", propOrder = {
    "resumenDTO"
})
public class GetResumenResponse {

    protected ResumenDTO resumenDTO;

    /**
     * Gets the value of the resumenDTO property.
     * 
     * @return
     *     possible object is
     *     {@link ResumenDTO }
     *     
     */
    public ResumenDTO getResumenDTO() {
        return resumenDTO;
    }

    /**
     * Sets the value of the resumenDTO property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResumenDTO }
     *     
     */
    public void setResumenDTO(ResumenDTO value) {
        this.resumenDTO = value;
    }

}
