
package com.epcs.billing.registrouso.types;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ResultadoConsultarTraficoEnLineaPrincipalType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResultadoConsultarTraficoEnLineaPrincipalType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="respuesta" type="{http://www.epcs.com/billing/registrouso/types}RespuestaType"/>
 *         &lt;element name="respuestaTraficoPrincipal" type="{http://www.epcs.com/billing/registrouso/types}RespuestaTraficoPrincipalType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="respuestaTraficoEnLinea" type="{http://www.epcs.com/billing/registrouso/types}RespuestaTraficoEnLineaType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResultadoConsultarTraficoEnLineaPrincipalType", propOrder = {
    "respuesta",
    "respuestaTraficoPrincipal",
    "respuestaTraficoEnLinea"
})
public class ResultadoConsultarTraficoEnLineaPrincipalType {

    @XmlElement(required = true)
    protected RespuestaType respuesta;
    protected List<RespuestaTraficoPrincipalType> respuestaTraficoPrincipal;
    protected List<RespuestaTraficoEnLineaType> respuestaTraficoEnLinea;

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
     * Gets the value of the respuestaTraficoPrincipal property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the respuestaTraficoPrincipal property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRespuestaTraficoPrincipal().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RespuestaTraficoPrincipalType }
     * 
     * 
     */
    public List<RespuestaTraficoPrincipalType> getRespuestaTraficoPrincipal() {
        if (respuestaTraficoPrincipal == null) {
            respuestaTraficoPrincipal = new ArrayList<RespuestaTraficoPrincipalType>();
        }
        return this.respuestaTraficoPrincipal;
    }

    /**
     * Gets the value of the respuestaTraficoEnLinea property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the respuestaTraficoEnLinea property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRespuestaTraficoEnLinea().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RespuestaTraficoEnLineaType }
     * 
     * 
     */
    public List<RespuestaTraficoEnLineaType> getRespuestaTraficoEnLinea() {
        if (respuestaTraficoEnLinea == null) {
            respuestaTraficoEnLinea = new ArrayList<RespuestaTraficoEnLineaType>();
        }
        return this.respuestaTraficoEnLinea;
    }

}
