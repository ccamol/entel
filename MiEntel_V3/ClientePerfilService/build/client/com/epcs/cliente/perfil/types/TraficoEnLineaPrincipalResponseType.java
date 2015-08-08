
package com.epcs.cliente.perfil.types;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TraficoEnLineaPrincipalResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TraficoEnLineaPrincipalResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="respuesta" type="{http://www.epcs.com/cliente/perfil/types}RespuestaType"/>
 *         &lt;element name="respuestaTraficoPrincipal" type="{http://www.epcs.com/cliente/perfil/types}RespuestaTraficoPrincipalType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TraficoEnLineaPrincipalResponseType", propOrder = {
    "respuesta",
    "respuestaTraficoPrincipal"
})
public class TraficoEnLineaPrincipalResponseType {

    @XmlElement(required = true)
    protected RespuestaType respuesta;
    protected List<RespuestaTraficoPrincipalType> respuestaTraficoPrincipal;

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

}
