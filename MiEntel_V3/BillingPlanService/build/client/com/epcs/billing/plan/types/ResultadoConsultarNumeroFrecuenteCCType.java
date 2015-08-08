
package com.epcs.billing.plan.types;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ResultadoConsultarNumeroFrecuenteCCType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResultadoConsultarNumeroFrecuenteCCType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="respuesta" type="{http://www.epcs.com/billing/plan/types}RespuestaType"/>
 *         &lt;element name="fechaActivacionPrincipal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numerosFrecuentesCC" type="{http://www.epcs.com/billing/plan/types}ListaNumerosFrecuentesCCType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResultadoConsultarNumeroFrecuenteCCType", propOrder = {
    "respuesta",
    "fechaActivacionPrincipal",
    "numerosFrecuentesCC"
})
public class ResultadoConsultarNumeroFrecuenteCCType {

    @XmlElement(required = true)
    protected RespuestaType respuesta;
    protected String fechaActivacionPrincipal;
    protected List<ListaNumerosFrecuentesCCType> numerosFrecuentesCC;

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
     * Gets the value of the fechaActivacionPrincipal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaActivacionPrincipal() {
        return fechaActivacionPrincipal;
    }

    /**
     * Sets the value of the fechaActivacionPrincipal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaActivacionPrincipal(String value) {
        this.fechaActivacionPrincipal = value;
    }

    /**
     * Gets the value of the numerosFrecuentesCC property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the numerosFrecuentesCC property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNumerosFrecuentesCC().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ListaNumerosFrecuentesCCType }
     * 
     * 
     */
    public List<ListaNumerosFrecuentesCCType> getNumerosFrecuentesCC() {
        if (numerosFrecuentesCC == null) {
            numerosFrecuentesCC = new ArrayList<ListaNumerosFrecuentesCCType>();
        }
        return this.numerosFrecuentesCC;
    }

}
