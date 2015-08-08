
package com.epcs.billing.bolsa.types;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BolsasRegaladasSSListType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BolsasRegaladasSSListType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bolsasRegaladasSSType" type="{http://www.epcs.com/billing/bolsa/types}ItemBolsasRegaladasSSType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BolsasRegaladasSSListType", propOrder = {
    "bolsasRegaladasSSType"
})
public class BolsasRegaladasSSListType {

    protected List<ItemBolsasRegaladasSSType> bolsasRegaladasSSType;

    /**
     * Gets the value of the bolsasRegaladasSSType property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the bolsasRegaladasSSType property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBolsasRegaladasSSType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ItemBolsasRegaladasSSType }
     * 
     * 
     */
    public List<ItemBolsasRegaladasSSType> getBolsasRegaladasSSType() {
        if (bolsasRegaladasSSType == null) {
            bolsasRegaladasSSType = new ArrayList<ItemBolsasRegaladasSSType>();
        }
        return this.bolsasRegaladasSSType;
    }

}
