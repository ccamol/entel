
package com.epcs.billing.bolsa.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ResultadoConsultaBolsasDisponiblesOneShootType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResultadoConsultaBolsasDisponiblesOneShootType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="respuesta" type="{http://www.epcs.com/billing/bolsa/types}RespuestaType"/>
 *         &lt;element name="bolsasDisponiblesOneShoot" type="{http://www.epcs.com/billing/bolsa/types}BolsasDisponiblesOneShootListType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResultadoConsultaBolsasDisponiblesOneShootType", propOrder = {
    "respuesta",
    "bolsasDisponiblesOneShoot"
})
public class ResultadoConsultaBolsasDisponiblesOneShootType {

    @XmlElement(required = true)
    protected RespuestaType respuesta;
    protected BolsasDisponiblesOneShootListType bolsasDisponiblesOneShoot;

    /**
     * Gets the value of the respuesta property.
     * 
     * @return
     *     possible object is
     *     {@link RespuestaType }
     *     
     */
    public RespuestaType getRespuesta() {
        return respuesta;
    }

    /**
     * Sets the value of the respuesta property.
     * 
     * @param value
     *     allowed object is
     *     {@link RespuestaType }
     *     
     */
    public void setRespuesta(RespuestaType value) {
        this.respuesta = value;
    }

    /**
     * Gets the value of the bolsasDisponiblesOneShoot property.
     * 
     * @return
     *     possible object is
     *     {@link BolsasDisponiblesOneShootListType }
     *     
     */
    public BolsasDisponiblesOneShootListType getBolsasDisponiblesOneShoot() {
        return bolsasDisponiblesOneShoot;
    }

    /**
     * Sets the value of the bolsasDisponiblesOneShoot property.
     * 
     * @param value
     *     allowed object is
     *     {@link BolsasDisponiblesOneShootListType }
     *     
     */
    public void setBolsasDisponiblesOneShoot(BolsasDisponiblesOneShootListType value) {
        this.bolsasDisponiblesOneShoot = value;
    }

}
