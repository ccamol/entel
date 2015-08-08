/* Propiedad de Entel S.A. Todos los derechos reservados */

package com.esa.ponline.appmobile.vo.plan;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author ccastro(MZZO) en nombre de Absalon Opazo (POnline, EntelSA)
 * Sep 15, 2014
 * version 1.0.0
 */

public class PlanesVO implements Serializable{

	private static final long serialVersionUID = 4256774166813360791L;
	
	private Collection<PlanVO> listaPlanes;
	private PlanVO objPlan;
	private InfoPlanMMExcededVO infoPlanMMExcedido;
	
	
	public Collection<PlanVO> getListaPlanes() {
		return listaPlanes;
	}
	public void setListaPlanes(Collection<PlanVO> listaPlanes) {
		this.listaPlanes = listaPlanes;
	}
	public PlanVO getObjPlan() {
		return objPlan;
	}
	public void setObjPlan(PlanVO objPlan) {
		this.objPlan = objPlan;
	}
	public InfoPlanMMExcededVO getInfoPlanMMExcedido() {
		return infoPlanMMExcedido;
	}
	public void setInfoPlanMMExcedido(InfoPlanMMExcededVO infoPlanMMExcedido) {
		this.infoPlanMMExcedido = infoPlanMMExcedido;
	}
	public void setLineasDisponiblees(String lineasdisp) {
		this.infoPlanMMExcedido.setLineasDisponiblesAdicionales(lineasdisp) ;
	}

}
