
package com.epcs.cliente.perfil.types;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TraficoEnLineaSecundariaResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TraficoEnLineaSecundariaResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="respuesta" type="{http://www.epcs.com/cliente/perfil/types}RespuestaType"/>
 *         &lt;element name="respuestaTraficoSecundario" type="{http://www.epcs.com/cliente/perfil/types}RespuestaTraficoSecundariaType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TraficoEnLineaSecundariaResponseType", propOrder = {
    "respuesta",
    "respuestaTraficoSecundario"
})
public class TraficoEnLineaSecundariaResponseType {

    @XmlElement(required = true)
    protected RespuestaType respuesta;
    protected List<RespuestaTraficoSecundariaType> respuestaTraficoSecundario;

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
     * Gets the value of the respuestaTraficoSecundario property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the respuestaTraficoSecundario property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRespuestaTraficoSecundario().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RespuestaTraficoSecundariaType }
     * 
     * 
     */
    public List<RespuestaTraficoSecundariaType> getRespuestaTraficoSecundario() {
        if (respuestaTraficoSecundario == null) {
            respuestaTraficoSecundario = new ArrayList<RespuestaTraficoSecundariaType>();
        }
        return this.respuestaTraficoSecundario;
    }

}
