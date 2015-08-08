
package com.epcs.cliente.perfil.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ResultadoConsultarPlanResumenPPType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResultadoConsultarPlanResumenPPType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="respuesta" type="{http://www.epcs.com/cliente/perfil/types}RespuestaType"/>
 *         &lt;element name="resPlanResumenPP" type="{http://www.epcs.com/cliente/perfil/types}ResPlanResumenPPType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResultadoConsultarPlanResumenPPType", propOrder = {
    "respuesta",
    "resPlanResumenPP"
})
public class ResultadoConsultarPlanResumenPPType {

    @XmlElement(required = true)
    protected RespuestaType respuesta;
    protected ResPlanResumenPPType resPlanResumenPP;

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
     * Gets the value of the resPlanResumenPP property.
     * 
     * @return
     *     possible object is
     *     {@link ResPlanResumenPPType }
     *     
     */
    public ResPlanResumenPPType getResPlanResumenPP() {
        return resPlanResumenPP;
    }

    /**
     * Sets the value of the resPlanResumenPP property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResPlanResumenPPType }
     *     
     */
    public void setResPlanResumenPP(ResPlanResumenPPType value) {
        this.resPlanResumenPP = value;
    }

}
