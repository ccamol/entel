/* Propiedad de Entel S.A. Todos los derechos reservados */

package com.esa.ponline.appmobile.web.delegate;

import com.esa.ponline.appmobile.vo.login.LoginVO;
import com.esa.ponline.appmobile.ws.dao.factory.FactoryWSDAO;

/**
 * @author ccastro (MZZO) en nombre de Absalon Opazo (EntelSA)
 * @version 1.0
 */

public class DelegateRegister {

	public String enviarSMS(String msisdn) throws Exception {
		return FactoryWSDAO.getRegistroDAO().enviarSMS(msisdn);
	}
	
	public String registrarUsuario(LoginVO usuarioVO) throws Exception {
		return FactoryWSDAO.getRegistroDAO().registrarUsuario(usuarioVO);
	}
	
	public Boolean existeRegistro(String msisdn) throws Exception {
		return FactoryWSDAO.getRegistroDAO().existeRegistro(msisdn);
	}
	
	public Boolean validaDatosRegistro(String msisdn, String pin) throws Exception {
		return FactoryWSDAO.getRegistroDAO().validaDatosRegistro(msisdn, pin);
	}
}
