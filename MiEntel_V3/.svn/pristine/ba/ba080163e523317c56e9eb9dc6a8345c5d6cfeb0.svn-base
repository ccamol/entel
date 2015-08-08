/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author jmanzur (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class DetalleMiTraficoSSBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private Date fecha;
    private Date fechaFin;
    private TraficoVoz traficoVoz;
    private List<TraficoDatosYMensajes> traficoDatos = new LinkedList<TraficoDatosYMensajes>();
    private List<TraficoDatosYMensajes> traficoMensajes = new LinkedList<TraficoDatosYMensajes>();
    private PlanBean planBean;
    
    /*
     Nuevo Estructura 
    */
    private String cargarCargoFijo = "0";
    
    
    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }
    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    /**
     * @return the traficoVoz
     */
    public TraficoVoz getTraficoVoz() {
        return traficoVoz;
    }
    /**
     * @param traficoVoz the traficoVoz to set
     */
    public void setTraficoVoz(TraficoVoz traficoVoz) {
        this.traficoVoz = traficoVoz;
    }
    /**
     * @return the traficoDatos
     */
    public List<TraficoDatosYMensajes> getTraficoDatos() {
        return traficoDatos;
    }
    /**
     * @param traficoDatos the traficoDatos to set
     */
    public void setTraficoDatos(List<TraficoDatosYMensajes> traficoDatos) {
        this.traficoDatos = traficoDatos;
    }
    /**
     * @return the traficoMensajes
     */
    public List<TraficoDatosYMensajes> getTraficoMensajes() {
        return traficoMensajes;
    }
    /**
     * @param traficoMensajes the traficoMensajes to set
     */
    public void setTraficoMensajes(List<TraficoDatosYMensajes> traficoMensajes) {
        this.traficoMensajes = traficoMensajes;
    }
    /**
     * @return the fechaFin
     */
    public Date getFechaFin() {
        return fechaFin;
    }
    /**
     * @param fechaFin the fechaFin to set
     */
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    /**
     * 
     * @return
     */
    public PlanBean getPlanBean() {
		return planBean;
	}
    
    /**
     * 
     * @param planBean
     */
    
	public void setPlanBean(PlanBean planBean) {
		this.planBean = planBean;
	}
	public String getCargarCargoFijo() {
		return cargarCargoFijo;
	}
	public void setCargarCargoFijo(String cargarCargoFijo) {
		this.cargarCargoFijo = cargarCargoFijo;
	}

}
