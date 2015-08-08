
package com.epcs.billing.plan.types;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ResultadoConsultarPlanesDisponiblesSSCCType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResultadoConsultarPlanesDisponiblesSSCCType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="respuesta" type="{http://www.epcs.com/billing/plan/types}RespuestaType"/>
 *         &lt;element name="planesDisponiblesSS" type="{http://www.epcs.com/billing/plan/types}PlanesDisponiblesSSCCType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="planesDisponiblesCC" type="{http://www.epcs.com/billing/plan/types}PlanesDisponiblesSSCCType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResultadoConsultarPlanesDisponiblesSSCCType", propOrder = {
    "respuesta",
    "planesDisponiblesSS",
    "planesDisponiblesCC"
})
public class ResultadoConsultarPlanesDisponiblesSSCCType {

    @XmlElement(required = true)
    protected RespuestaType respuesta;
    protected List<PlanesDisponiblesSSCCType> planesDisponiblesSS;
    protected List<PlanesDisponiblesSSCCType> planesDisponiblesCC;

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
     * Gets the value of the planesDisponiblesSS property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the planesDisponiblesSS property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPlanesDisponiblesSS().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PlanesDisponiblesSSCCType }
     * 
     * 
     */
    public List<PlanesDisponiblesSSCCType> getPlanesDisponiblesSS() {
        if (planesDisponiblesSS == null) {
            planesDisponiblesSS = new ArrayList<PlanesDisponiblesSSCCType>();
        }
        return this.planesDisponiblesSS;
    }

    /**
     * Gets the value of the planesDisponiblesCC property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the planesDisponiblesCC property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPlanesDisponiblesCC().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PlanesDisponiblesSSCCType }
     * 
     * 
     */
    public List<PlanesDisponiblesSSCCType> getPlanesDisponiblesCC() {
        if (planesDisponiblesCC == null) {
            planesDisponiblesCC = new ArrayList<PlanesDisponiblesSSCCType>();
        }
        return this.planesDisponiblesCC;
    }

}
