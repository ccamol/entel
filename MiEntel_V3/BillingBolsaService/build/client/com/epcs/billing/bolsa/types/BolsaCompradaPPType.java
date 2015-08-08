
package com.epcs.billing.bolsa.types;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BolsaCompradaPPType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BolsaCompradaPPType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bolsaCompradaPP" type="{http://www.epcs.com/billing/bolsa/types}BolsaPPType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BolsaCompradaPPType", propOrder = {
    "bolsaCompradaPP"
})
public class BolsaCompradaPPType {

    protected List<BolsaPPType> bolsaCompradaPP;

    /**
     * Gets the value of the bolsaCompradaPP property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the bolsaCompradaPP property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBolsaCompradaPP().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BolsaPPType }
     * 
     * 
     */
    public List<BolsaPPType> getBolsaCompradaPP() {
        if (bolsaCompradaPP == null) {
            bolsaCompradaPP = new ArrayList<BolsaPPType>();
        }
        return this.bolsaCompradaPP;
    }

}
