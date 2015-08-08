/* Propiedad de Entel S.A. Todos los derechos reservados */

package com.esa.ponline.appmobile.web.delegate;

import com.epcs.billing.balance.types.DetalleFacturaMesType;
import com.esa.ponline.appmobile.exceptions.ServiceException;
import com.esa.ponline.appmobile.exceptions.WSDAOException;
import com.esa.ponline.appmobile.vo.login.LoginVO;
import com.esa.ponline.appmobile.ws.dao.factory.FactoryWSDAO;

/**
 * @author ccastro (MZZO) en nombre de Absalon Opazo (Plataformas Online,
 *         EntelSA) version 1.0.0 date 12-09-2014
 */

public class DelegateAccount {
	
	public DetalleFacturaMesType obtenerEstadoCuenta(LoginVO in) throws WSDAOException, ServiceException{
		return FactoryWSDAO.getCuentaDAO().obtenerEstadoCuenta(in);
	}

}
