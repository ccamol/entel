package com.epcs.bean;

import java.util.ArrayList;
import java.util.List;

public class PresupuestoBean {

	private ArrayList<DiagnosticoBean> diagnosticos;
	
	private ArrayList<ReparacionBean> reparaciones;
    
    private double valorTotal;
    
    public PresupuestoBean(){

    }

	/**
	 * @return diagnosticos
	 */
	public List<DiagnosticoBean> getDiagnosticos() {
		return diagnosticos;
	}

	/**
	 * @param diagnosticos
	 */
	public void setDiagnosticos(ArrayList<DiagnosticoBean> diagnosticos) {
		this.diagnosticos = diagnosticos;
	}

	/**
	 * @return reparaciones
	 */
	public ArrayList<ReparacionBean> getReparaciones() {
		return reparaciones;
	}

	/**
	 * @param reparaciones
	 */
	public void setReparaciones(ArrayList<ReparacionBean> reparaciones) {
		this.reparaciones = reparaciones;
	}

	/**
	 * @return valorTotal
	 */
	public double getValorTotal() {
		return valorTotal;
	}

	/**
	 * @param valorTotal
	 */
	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	/**
	 * Agregar diagnostico
	 * @param diag
	 */
	public void addDiagnostico(DiagnosticoBean diag){
		if( diagnosticos == null ){
			diagnosticos = new ArrayList<DiagnosticoBean>();
		}
		this.diagnosticos.add(diag);
	}
	
	/**
	 * Agregar reparacion
	 * @param rep
	 */
	public void addReparacion(ReparacionBean rep){
		if( reparaciones == null ){
			reparaciones = new ArrayList<ReparacionBean>();
		}
		this.reparaciones.add(rep);
	}
	
}
