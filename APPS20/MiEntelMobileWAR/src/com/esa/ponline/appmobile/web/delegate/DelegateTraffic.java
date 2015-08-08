/* Propiedad de Entel S.A. Todos los derechos reservados */

package com.esa.ponline.appmobile.web.delegate;

import java.util.List;

import com.esa.ponline.appmobile.vo.PlanMMAG_VO;
import com.esa.ponline.appmobile.vo.PlanMultimediaVO;
import com.esa.ponline.appmobile.vo.traffic.TrafficVO;
import com.esa.ponline.appmobile.ws.dao.factory.FactoryWSDAO;

/**
 * @author ccastro (MZZO) en nombre de Absalon Opazo (EntelSA)
 * @version 1.0
 */

public class DelegateTraffic {
	
	public List<TrafficVO> consultaTrafico(String msisdn, String aaa) throws Exception {
		return FactoryWSDAO.getTraficoDAO().consultaTrafico(msisdn, aaa);
	}

	public List<PlanMultimediaVO> consultaTraficoExcedido(String msisdn) throws Exception {
		return FactoryWSDAO.getTraficoDAO().consultaTraficoExcedido(msisdn);
	}
	
	public String consultaNombrePlan(String msisdn) throws Exception {
		return FactoryWSDAO.getTraficoDAO().consultaNombrePlan(msisdn);
	}

    public List<PlanMMAG_VO> consultarTraficoMMAGExcedido(String msisdn) throws Exception {
        return FactoryWSDAO.getTraficoDAO().consultarTraficoMMAGExcedido(msisdn);
    }
}
