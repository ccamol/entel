
package com.epcs.billing.registrouso.types;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DetalleTraficoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DetalleTraficoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="itemDetalleTraficoType" type="{http://www.epcs.com/billing/registrouso/types}DetalleTraficoPlanType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DetalleTraficoType", propOrder = {
    "itemDetalleTraficoType"
})
public class DetalleTraficoType {

    protected List<DetalleTraficoPlanType> itemDetalleTraficoType;

    /**
     * Gets the value of the itemDetalleTraficoType property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the itemDetalleTraficoType property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getItemDetalleTraficoType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DetalleTraficoPlanType }
     * 
     * 
     */
    public List<DetalleTraficoPlanType> getItemDetalleTraficoType() {
        if (itemDetalleTraficoType == null) {
            itemDetalleTraficoType = new ArrayList<DetalleTraficoPlanType>();
        }
        return this.itemDetalleTraficoType;
    }

}
