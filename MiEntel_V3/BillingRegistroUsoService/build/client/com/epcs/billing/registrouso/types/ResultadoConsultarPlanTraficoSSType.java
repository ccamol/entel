
package com.epcs.billing.registrouso.types;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ResultadoConsultarPlanTraficoSSType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResultadoConsultarPlanTraficoSSType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="respuesta" type="{http://www.epcs.com/billing/registrouso/types}RespuestaType"/>
 *         &lt;element name="numeroPlanesConsultados" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="planTrafico" type="{http://www.epcs.com/billing/registrouso/types}PlanType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResultadoConsultarPlanTraficoSSType", propOrder = {
    "respuesta",
    "numeroPlanesConsultados",
    "planTrafico"
})
public class ResultadoConsultarPlanTraficoSSType {

    @XmlElement(required = true)
    protected RespuestaType respuesta;
    protected String numeroPlanesConsultados;
    protected List<PlanType> planTrafico;

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
     * Gets the value of the numeroPlanesConsultados property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroPlanesConsultados() {
        return numeroPlanesConsultados;
    }

    /**
     * Sets the value of the numeroPlanesConsultados property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroPlanesConsultados(String value) {
        this.numeroPlanesConsultados = value;
    }

    /**
     * Gets the value of the planTrafico property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the planTrafico property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPlanTrafico().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PlanType }
     * 
     * 
     */
    public List<PlanType> getPlanTrafico() {
        if (planTrafico == null) {
            planTrafico = new ArrayList<PlanType>();
        }
        return this.planTrafico;
    }

}
