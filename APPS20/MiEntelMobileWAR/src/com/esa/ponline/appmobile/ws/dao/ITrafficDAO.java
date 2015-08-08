/* Propiedad de Entel S.A. Todos los derechos reservados */

package com.esa.ponline.appmobile.ws.dao;

import java.util.List;

import com.esa.ponline.appmobile.exceptions.AppMobileException;
import com.esa.ponline.appmobile.vo.PlanMMAG_VO;
import com.esa.ponline.appmobile.vo.PlanMultimediaVO;
import com.esa.ponline.appmobile.vo.traffic.TrafficVO;

/**
 * @author ccastro (MZZO) en nombre de Absalon Opazo (EntelSA)
 * @version 1.0
 */

public interface ITrafficDAO {

	public List<TrafficVO> consultaTrafico(String msisdn, String aaa) throws AppMobileException;
	public List<PlanMultimediaVO> consultaTraficoExcedido(String msisdn) throws AppMobileException;
    public List<PlanMMAG_VO> consultarTraficoMMAGExcedido(String msisdn) throws AppMobileException;	
	public String consultaNombrePlan(String msisdn) throws AppMobileException;
}
