
package com.epcs.billing.registrouso.types;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ResultadoConsultarTipoTraficoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResultadoConsultarTipoTraficoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="respuesta" type="{http://www.epcs.com/billing/registrouso/types}RespuestaType"/>
 *         &lt;element name="tipoTrafico" type="{http://www.epcs.com/billing/registrouso/types}TipoTraficoType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResultadoConsultarTipoTraficoType", propOrder = {
    "respuesta",
    "tipoTrafico"
})
public class ResultadoConsultarTipoTraficoType {

    @XmlElement(required = true)
    protected RespuestaType respuesta;
    protected List<TipoTraficoType> tipoTrafico;

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
     * Gets the value of the tipoTrafico property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tipoTrafico property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTipoTrafico().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TipoTraficoType }
     * 
     * 
     */
    public List<TipoTraficoType> getTipoTrafico() {
        if (tipoTrafico == null) {
            tipoTrafico = new ArrayList<TipoTraficoType>();
        }
        return this.tipoTrafico;
    }

}
