
package com.epcs.billing.balance.types;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ResumenFacturasType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResumenFacturasType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="facturas" type="{http://www.epcs.com/billing/balance/types}FacturaType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResumenFacturasType", propOrder = {
    "facturas"
})
public class ResumenFacturasType {

    @XmlElement(nillable = true)
    protected List<FacturaType> facturas;

    /**
     * Gets the value of the facturas property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the facturas property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFacturas().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FacturaType }
     * 
     * 
     */
    public List<FacturaType> getFacturas() {
        if (facturas == null) {
            facturas = new ArrayList<FacturaType>();
        }
        return this.facturas;
    }

}
