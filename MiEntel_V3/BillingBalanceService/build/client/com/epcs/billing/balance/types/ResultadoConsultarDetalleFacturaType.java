
package com.epcs.billing.balance.types;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ResultadoConsultarDetalleFacturaType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResultadoConsultarDetalleFacturaType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="respuesta" type="{http://www.epcs.com/billing/balance/types}RespuestaType"/>
 *         &lt;element name="cobros" type="{http://www.epcs.com/billing/balance/types}CobroType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="urlImagenDocumento" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResultadoConsultarDetalleFacturaType", propOrder = {
    "respuesta",
    "cobros",
    "urlImagenDocumento"
})
public class ResultadoConsultarDetalleFacturaType {

    @XmlElement(required = true)
    protected RespuestaType respuesta;
    protected List<CobroType> cobros;
    @XmlElement(required = true)
    protected String urlImagenDocumento;

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
     * Gets the value of the cobros property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cobros property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCobros().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CobroType }
     * 
     * 
     */
    public List<CobroType> getCobros() {
        if (cobros == null) {
            cobros = new ArrayList<CobroType>();
        }
        return this.cobros;
    }

    /**
     * Gets the value of the urlImagenDocumento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrlImagenDocumento() {
        return urlImagenDocumento;
    }

    /**
     * Sets the value of the urlImagenDocumento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrlImagenDocumento(String value) {
        this.urlImagenDocumento = value;
    }

}
