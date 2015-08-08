
package com.epcs.cliente.perfil.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ResultadoConsultarBolsaActualType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResultadoConsultarBolsaActualType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="respuesta" type="{http://www.epcs.com/cliente/perfil/types}RespuestaType"/>
 *         &lt;element name="bolsasActuales" type="{http://www.epcs.com/cliente/perfil/types}BolsasType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResultadoConsultarBolsaActualType", propOrder = {
    "respuesta",
    "bolsasActuales"
})
public class ResultadoConsultarBolsaActualType {

    @XmlElement(required = true)
    protected RespuestaType respuesta;
    @XmlElement(required = true)
    protected BolsasType bolsasActuales;

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
     * Gets the value of the bolsasActuales property.
     * 
     * @return
     *     possible object is
     *     {@link BolsasType }
     *     
     */
    public BolsasType getBolsasActuales() {
        return bolsasActuales;
    }

    /**
     * Sets the value of the bolsasActuales property.
     * 
     * @param value
     *     allowed object is
     *     {@link BolsasType }
     *     
     */
    public void setBolsasActuales(BolsasType value) {
        this.bolsasActuales = value;
    }

}
