/* Propiedad de Entel S.A. Todos los derechos reservados */

package com.esa.ponline.appmobile.ws.dao;

import com.esa.ponline.appmobile.exceptions.AppMobileException;
import com.esa.ponline.appmobile.vo.login.LoginVO;

/**
 * @author ccastro (MZZO) en nombre de Absalon Opazo (EntelSA)
 * @version 1.0
 */

public interface IRegisterDAO {

	public String registrarUsuario(LoginVO usuario) throws AppMobileException;
	public String enviarSMS(String msisdn) throws AppMobileException;
	public boolean existeRegistro(String msisdn) throws AppMobileException;
	public boolean validaDatosRegistro(String msisdn, String pin) throws AppMobileException;
	
	
}
