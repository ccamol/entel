/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @author esuarez (Tecnova) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class ResumenPlanCategoria implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String nombreCategoria;
    private Double porcentaje;
    private String montoAcutal;
    private String semestre;
    
    
	public String getNombreCategoria() {
		return nombreCategoria;
	}
	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}
	public Double getPorcentaje() {
		return porcentaje;
	}
	public void setPorcentaje(Double porcentaje) {
		this.porcentaje = porcentaje;
	}
	public String getMontoAcutal() {
		return montoAcutal;
	}
	public void setMontoAcutal(String montoAcutal) {
		this.montoAcutal = montoAcutal;
	}
	public String getSemestre() {
		return semestre;
	}
	public void setSemestre(String semestre) {
		this.semestre = semestre;
	} 
}
