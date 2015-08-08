
package com.epcs.inteligencianegocios.satisfaccioncliente.types;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ResultadoConsultarEncuestasPerfiladasType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResultadoConsultarEncuestasPerfiladasType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="respuesta" type="{http://www.epcs.com/inteligencianegocios/satisfaccioncliente/types}RespuestaType"/>
 *         &lt;element name="cantidadUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="encuestasPerfiladasUrls" type="{http://www.epcs.com/inteligencianegocios/satisfaccioncliente/types}EncuestaPerfiladaUrlType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResultadoConsultarEncuestasPerfiladasType", propOrder = {
    "respuesta",
    "cantidadUrl",
    "encuestasPerfiladasUrls"
})
public class ResultadoConsultarEncuestasPerfiladasType {

    @XmlElement(required = true)
    protected RespuestaType respuesta;
    @XmlElement(required = true)
    protected String cantidadUrl;
    protected List<EncuestaPerfiladaUrlType> encuestasPerfiladasUrls;

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
     * Gets the value of the cantidadUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCantidadUrl() {
        return cantidadUrl;
    }

    /**
     * Sets the value of the cantidadUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCantidadUrl(String value) {
        this.cantidadUrl = value;
    }

    /**
     * Gets the value of the encuestasPerfiladasUrls property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the encuestasPerfiladasUrls property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEncuestasPerfiladasUrls().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EncuestaPerfiladaUrlType }
     * 
     * 
     */
    public List<EncuestaPerfiladaUrlType> getEncuestasPerfiladasUrls() {
        if (encuestasPerfiladasUrls == null) {
            encuestasPerfiladasUrls = new ArrayList<EncuestaPerfiladaUrlType>();
        }
        return this.encuestasPerfiladasUrls;
    }

}
