/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.bean;


/**
 * @author jivasquez (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class ResultadoConsultarPuntosBean {
    
    private PuntosBean puntos;
    private String categoriaCliente;
    
    public ResultadoConsultarPuntosBean (){
        
    }

    /**
     * @return the puntos
     */
    public PuntosBean getPuntos() {
        return puntos;
    }

    /**
     * @param puntos the puntos to set
     */
    public void setPuntos(PuntosBean puntos) {
        this.puntos = puntos;
    }
    
    /**
     * @return the categoriaCliente
     */
    public String getCategoriaCliente() {
        return categoriaCliente;
    }

    /**
     * @param puntos the categoriaCliente to set
     */
    public void setCategoriaCliente(String categoriaCliente) {
        this.categoriaCliente = categoriaCliente;
    }

}
