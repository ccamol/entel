
package com.epcs.cliente.perfil.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ResultadoConsultarPlanType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResultadoConsultarPlanType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="respuesta" type="{http://www.epcs.com/cliente/perfil/types}RespuestaType"/>
 *         &lt;element name="informacionPlan" type="{http://www.epcs.com/cliente/perfil/types}InformacionPlanType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResultadoConsultarPlanType", propOrder = {
    "respuesta",
    "informacionPlan"
})
public class ResultadoConsultarPlanType {

    @XmlElement(required = true)
    protected RespuestaType respuesta;
    protected InformacionPlanType informacionPlan;

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
     * Gets the value of the informacionPlan property.
     * 
     * @return
     *     possible object is
     *     {@link InformacionPlanType }
     *     
     */
    public InformacionPlanType getInformacionPlan() {
        return informacionPlan;
    }

    /**
     * Sets the value of the informacionPlan property.
     * 
     * @param value
     *     allowed object is
     *     {@link InformacionPlanType }
     *     
     */
    public void setInformacionPlan(InformacionPlanType value) {
        this.informacionPlan = value;
    }

}
