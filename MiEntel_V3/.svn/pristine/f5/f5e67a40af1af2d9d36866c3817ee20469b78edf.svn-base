
package com.epcs.billing.bolsa.types;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GrupoBolsasItemType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GrupoBolsasItemType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GrupoBolsaItem" type="{http://www.epcs.com/billing/bolsa/types}GrupoBolsasType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GrupoBolsasItemType", propOrder = {
    "grupoBolsaItem"
})
public class GrupoBolsasItemType {

    @XmlElement(name = "GrupoBolsaItem")
    protected List<GrupoBolsasType> grupoBolsaItem;

    /**
     * Gets the value of the grupoBolsaItem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the grupoBolsaItem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGrupoBolsaItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GrupoBolsasType }
     * 
     * 
     */
    public List<GrupoBolsasType> getGrupoBolsaItem() {
        if (grupoBolsaItem == null) {
            grupoBolsaItem = new ArrayList<GrupoBolsasType>();
        }
        return this.grupoBolsaItem;
    }

}
