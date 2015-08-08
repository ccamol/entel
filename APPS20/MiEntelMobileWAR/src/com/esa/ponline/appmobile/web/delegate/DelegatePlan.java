/* Propiedad de Entel S.A. Todos los derechos reservados */

package com.esa.ponline.appmobile.web.delegate;

import com.esa.ponline.appmobile.exceptions.AppMobileException;
import com.esa.ponline.appmobile.exceptions.ServiceException;
import com.esa.ponline.appmobile.exceptions.WSDAOException;
import com.esa.ponline.appmobile.vo.plan.PlanesVO;
import com.esa.ponline.appmobile.ws.dao.factory.FactoryWSDAO;

/**
 * @author ccastro (MZZO) en nombre de Absalon Opazo (Plataformas Online, EntelSA)
 * version 1.0.0
 * 15-09-2014
 */

public class DelegatePlan {

	public PlanesVO obtienePlanActual(String msisdn, String mercado, String aaa, String cuenta) throws AppMobileException, ServiceException, WSDAOException{
		return FactoryWSDAO.getPlanDAO().obtienePlanActual(msisdn, mercado, aaa, cuenta);
	}
}
