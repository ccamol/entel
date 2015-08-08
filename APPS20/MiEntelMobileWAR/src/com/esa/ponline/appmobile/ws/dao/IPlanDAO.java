/* Propiedad de Entel S.A. Todos los derechos reservados */

package com.esa.ponline.appmobile.ws.dao;

import com.esa.ponline.appmobile.exceptions.AppMobileException;
import com.esa.ponline.appmobile.exceptions.ServiceException;
import com.esa.ponline.appmobile.exceptions.WSDAOException;
import com.esa.ponline.appmobile.vo.plan.PlanesVO;

/**
 * @author ccastro (MZZO) en nombre de Absalon Opazo (Plataformas Online, EntelSA)
 * version 1.0.0
 * 15-09-2014
 */

public interface IPlanDAO {

	public PlanesVO obtienePlanActual(String msisdn, String mercado, String aaa,String cuenta) throws AppMobileException, ServiceException, WSDAOException;
	
}
