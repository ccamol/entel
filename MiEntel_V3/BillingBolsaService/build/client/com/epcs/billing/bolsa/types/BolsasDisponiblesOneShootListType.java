
package com.epcs.billing.bolsa.types;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BolsasDisponiblesOneShootListType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BolsasDisponiblesOneShootListType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bolsasDisponiblesOneShootType" type="{http://www.epcs.com/billing/bolsa/types}BolsasDisponiblesOneShootType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BolsasDisponiblesOneShootListType", propOrder = {
    "bolsasDisponiblesOneShootType"
})
public class BolsasDisponiblesOneShootListType {

    protected List<BolsasDisponiblesOneShootType> bolsasDisponiblesOneShootType;

    /**
     * Gets the value of the bolsasDisponiblesOneShootType property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the bolsasDisponiblesOneShootType property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBolsasDisponiblesOneShootType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BolsasDisponiblesOneShootType }
     * 
     * 
     */
    public List<BolsasDisponiblesOneShootType> getBolsasDisponiblesOneShootType() {
        if (bolsasDisponiblesOneShootType == null) {
            bolsasDisponiblesOneShootType = new ArrayList<BolsasDisponiblesOneShootType>();
        }
        return this.bolsasDisponiblesOneShootType;
    }

}
