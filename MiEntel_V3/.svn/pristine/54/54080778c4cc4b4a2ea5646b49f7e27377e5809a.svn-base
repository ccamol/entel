/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.bean;

import java.util.LinkedList;
import java.util.List;

/**
 * @author zmanotas (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class ServicioTecnicoMPTBean {
    
    private Integer numeroOrdenes;
    private List<OrdenTrabajoMPTBean> ordenes;
    
    /**
     * @return the numeroOrdenes
     */
    public Integer getNumeroOrdenes() {
        return numeroOrdenes;
    }

    /**
     * @return the ordenes
     */
    public List<OrdenTrabajoMPTBean> getOrdenes() {
        return ordenes;
    }

    /**
     * @param ordenes the ordenes to set
     */
    public void setOrdenes(List<OrdenTrabajoMPTBean> ordenes) {
        this.ordenes = ordenes;
    }
    
    public OrdenTrabajoMPTBean getOrden(int idx) {
        return ordenes.get(idx);
    }

    public void addOrdenBean(OrdenTrabajoMPTBean orden) {
        if (ordenes == null) {
            ordenes = new LinkedList<OrdenTrabajoMPTBean>();
            ordenes.add(orden);            
        } else {
            ordenes.add(orden);
        }
        numeroOrdenes = ordenes.size();
    }
}
