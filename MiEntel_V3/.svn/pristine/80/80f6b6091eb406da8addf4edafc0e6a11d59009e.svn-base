package com.epcs.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Bean para representar informacion de planes comunik2
 * 
 * @author wbrochero (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class HistorialComunik2Bean implements Serializable,Comparable<HistorialComunik2Bean>{

	/**
	 * 
	 */
   private static final long serialVersionUID = 1L;
	
	
    private String numeroPcs;    
    private String tipoInvitacion;
    private Date   fecha;
    private String respuesta;    
    private String fechaFormateada ;
    
    
    public HistorialComunik2Bean(){
        
    }

	/**
	 * @return the numeroPcs
	 */
	public String getNumeroPcs() {
		return numeroPcs;
	}

	/**
	 * @param numeroPcs the numeroPcs to set
	 */
	public void setNumeroPcs(String numeroPcs) {
		this.numeroPcs = numeroPcs;
	}

	/**
	 * @return the tipoInvitacion
	 */
	public String getTipoInvitacion() {
		return tipoInvitacion;
	}

	/**
	 * @param tipoInvitacion the tipoInvitacion to set
	 */
	public void setTipoInvitacion(String tipoInvitacion) {
		this.tipoInvitacion = tipoInvitacion;
	}

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
	 * @return the respuesta
	 */
	public String getRespuesta() {
		return respuesta;
	}

	/**
	 * @param respuesta the respuesta to set
	 */
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	/**
	 * @return the fechaFormateada
	 */
	public String getFechaFormateada() {
		return fechaFormateada;
	}

	/**
	 * @param fechaFormateada the fechaFormateada to set
	 */
	public void setFechaFormateada(String fechaFormateada) {
		this.fechaFormateada = fechaFormateada;
	}
    
    
    /* (non-Javadoc)
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(HistorialComunik2Bean HistorialComunik2Bean) {
        return HistorialComunik2Bean.getFecha().compareTo(this.getFecha());
    }  
    
	
	
	
	

}
