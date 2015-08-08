
package com.epcs.billing.balance.types;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ResultadoConsultarFacturacionFullType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResultadoConsultarFacturacionFullType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="respuesta" type="{http://www.epcs.com/billing/balance/types}RespuestaType"/>
 *         &lt;element name="documentosFacturasFull" type="{http://www.epcs.com/billing/balance/types}DocumentoFacturaFullType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="detalleFacturaMes" type="{http://www.epcs.com/billing/balance/types}DetalleFacturaMesType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResultadoConsultarFacturacionFullType", propOrder = {
    "respuesta",
    "documentosFacturasFull",
    "detalleFacturaMes"
})
public class ResultadoConsultarFacturacionFullType {

    @XmlElement(required = true)
    protected RespuestaType respuesta;
    protected List<DocumentoFacturaFullType> documentosFacturasFull;
    protected DetalleFacturaMesType detalleFacturaMes;

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
     * Gets the value of the documentosFacturasFull property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the documentosFacturasFull property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDocumentosFacturasFull().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DocumentoFacturaFullType }
     * 
     * 
     */
    public List<DocumentoFacturaFullType> getDocumentosFacturasFull() {
        if (documentosFacturasFull == null) {
            documentosFacturasFull = new ArrayList<DocumentoFacturaFullType>();
        }
        return this.documentosFacturasFull;
    }

    /**
     * Gets the value of the detalleFacturaMes property.
     * 
     * @return
     *     possible object is
     *     {@link DetalleFacturaMesType }
     *     
     */
    public DetalleFacturaMesType getDetalleFacturaMes() {
        return detalleFacturaMes;
    }

    /**
     * Sets the value of the detalleFacturaMes property.
     * 
     * @param value
     *     allowed object is
     *     {@link DetalleFacturaMesType }
     *     
     */
    public void setDetalleFacturaMes(DetalleFacturaMesType value) {
        this.detalleFacturaMes = value;
    }

}
