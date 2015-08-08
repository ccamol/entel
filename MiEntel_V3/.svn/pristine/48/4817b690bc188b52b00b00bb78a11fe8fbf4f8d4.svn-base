/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.seguridad.spi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

import org.apache.log4j.Logger;

import weblogic.security.principal.WLSAbstractPrincipal;
import weblogic.security.principal.WLSGroupImpl;
import weblogic.security.principal.WLSUserImpl;

import com.epcs.recursoti.exception.DAOException;
import com.epcs.recursoti.exception.ServiceException;
import com.epcs.seguridad.bean.LoginBean;
import com.epcs.seguridad.bean.LoginRespuestaBean;
import com.epcs.seguridad.bean.RutBean;

/**
 * Implementacion de LoginModule que valida los usuarios que desean accesar al
 * proyecto MiEntel v3.0, a traves de credenciales tales como numero movil, rut
 * y clave.
 * 
 * @author mmartinez (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 */
final public class EPCSLoginModuleImpl implements LoginModule {

    /**
     * 
     */
    private static final String DEFAULT_SEPARATOR_CHAR = "\\|";

    /**
     * Constante que define el nombre por defecto del grupo en el que se
     * inscriben las aplicaciones que se autentican.
     */
    private static final String DEFAULT_USER_GROUPNAME = "Administrators";

    /**
     * Subject (JAAS).
     */
    private Subject subject;

    /**
     * CallBackHandler que maneja las credenciales de autenticacion (IDP).
     */
    private CallbackHandler callbackHandler;

    /**
     * DAO que se comunica con el servicio de autenticacion de aplicaciones.
     */
    private LoginDAO loginService;

    /**
     * Estado de la autenticacion
     */
    private Boolean loginSucceeded;

    /**
     * Define si dentro del Subject existe al menos un principal validado.
     */
    private boolean principalsInSubject;

    /**
     * Lista en la que se inscribe la aplicacion autenticada.
     */
    private final List<WLSAbstractPrincipal> principalsForSubject = new ArrayList<WLSAbstractPrincipal>();

    /**
     * Log del autenticador.
     */
    private static final Logger logger = Logger.getLogger(EPCSAuthenticatorImpl.class);

    /**
     * Inicializacion de artefactos de autenticacion.
     */
	public void initialize(final Subject subject,
			final CallbackHandler callbackHandler,
			final Map<String, ?> sharedState, final Map<String, ?> options) {
		logger.info("EPCSLoginModuleImpl initilize...");
		this.subject = subject;
		this.callbackHandler = callbackHandler;
		try {
			loginService = (LoginDAO) options
					.get(EPCSAuthenticatorImpl.LOGIN_SERVICE);
		} catch (Exception e) {
			logger.error("loginService not initialized", e);
		}
	}

    /**
     * Metodo que permite autenticar un usuario dado.
     * 
     * @return true si las credenciales de autenticacion son validas.
     * @throws LoginException
     *             si falla la autenticacion.
     */
	public boolean login() throws LoginException {
		
		Callback[] callbacks = getCallbacks();
		String movil_rut = getMovilRut(callbacks);
		
		
		String[] splitted = movil_rut.split(DEFAULT_SEPARATOR_CHAR);
//		
//		int index = movil_rut.indexOf(DEFAULT_SEPARATOR_CHAR);
		String movil = splitted[0];
		String rut = splitted[1];
		String token = splitted[2];
		
//		if (index != -1) {
//			movil = movil_rut.substring(0, index);
//			rut = movil_rut.substring(index + 1);
//		}
		String clave = getClave(callbacks);
		LoginBean bean = new LoginBean();

        if(logger.isDebugEnabled()) {
    		logger.debug("Datos usuario :");
    		logger.debug("rut   :" + rut);
    		logger.debug("movil :" + movil);
    		logger.debug("token :" + token);
    		logger.debug("clave :" + clave);
        }
		
		bean.setNumeroPcs(movil);
		try {
			bean.setRut(RutBean.parseRut(rut));
		} catch (Exception e) {
			logger.error("Exception caught on parsing Rut", e);
			throwFailedLoginException("No se pudo parsear el rut del usuario. ");
		}
		bean.setClave(clave);
		
		logger.info("login => movil [" + movil + "], rut ["
				+ RutBean.parseRut(rut) + "], clave [" + clave + "]");
		
		try {
			LoginRespuestaBean respuesta = loginService.login(bean);
			
	        if(logger.isDebugEnabled()) {
	        	if(respuesta != null) {
	        		logger.debug("codigoRespuesta " + respuesta.getCodigoRespuesta());
	        		logger.debug("mensajeRespuesta " + respuesta.getMensajeRespuesta());
	        	}
	        }
        
		} catch (DAOException e) {
			throwLoginException(e);
		} catch (ServiceException e) {
			throwFailedLoginException("Fallo en la autenticacion del usuario. Credenciales invalidas: movil ["
					+ movil + "], rut [" + rut + "], clave [" + clave + "].");
		} catch (Exception e) {
			throwLoginException(e);
		}
		
		try {
			loginSucceeded = true;
//			principalsForSubject.add(new WLSUserImpl(movil));
			principalsForSubject.add(new WLSUserImpl(movil + "|" + token));
			addGroupsForSubject();
		} catch (Exception e) {
			logger.error("login failed!", e);
			loginSucceeded = false;
		}
		
		return loginSucceeded;
	}

    /**
     * Los principal se agregan en el metodo commit ya que no hay que olvidar
     * que nuestro LoginModule convive eventualmente en una cadena de modulos de
     * autenticacion y es posible que alguno de ellos no valide con lo que si
     * completamos el subject antes de llamar a todos los login() del dominio
     * vamos a darle identidades a nuestro sujeto que no queremos.
     * 
     */
    public boolean commit() throws LoginException {

        boolean result = false;
        if (loginSucceeded) {
            logger.info("loggin succeeded...");
            subject.getPrincipals().addAll(principalsForSubject);
            principalsInSubject = true;
            result = true;
        }
        return result;
    }

    /**
     * Este metodo permite realizar un "rollback" en caso de que la autencion
     * falle.
     */
    public boolean abort() throws LoginException {
        logger.info("login abborting...");
        if (principalsInSubject) {            
            subject.getPrincipals().removeAll(principalsForSubject);
            principalsInSubject = false;
        }
        return true;
    }

    /**
     * Este metodo no deberia ser llamado!.
     */
    public boolean logout() throws LoginException {
        logger.info("loggout...");
        return true;
    }

    /**
     * metodo que permite registrar un error un arrojar una excepcion de tipo
     * LoginException.
     * 
     * @param msg
     *            mensaje de la excepcion correspondiente.
     * @throws LoginException
     *             el wrapper del error arrojado.
     */
    private void throwLoginException(final String msg) throws LoginException {
        logger.error(msg);
        throw new LoginException(msg);
    }

    /**
     * metodo que permite registrar un error un arrojar la excepcion
     * correspondiente.
     * 
     * @param msg
     *            mensaje de la excepcion correspondiente.
     * @throws LoginException
     *             el wrapper del error arrojado.
     */
    private void throwLoginException(final Throwable exception)
            throws LoginException {
        logger.error(exception.getMessage(), exception);
        throw new LoginException(exception.getMessage());
    }

    /**
     * metodo que permite registrar un error un arrojar una excepcion de tipo
     * FailedLoginException.
     * 
     * @param msg
     *            mensaje de la excepcion correspondiente.
     * @throws LoginException
     *             el wrapper del error arrojado.
     */
    private void throwFailedLoginException(final String msg)
            throws FailedLoginException {
        logger.error(msg);
        throw new FailedLoginException(msg);
    }

    /**
     * Metodo que se encarga de recuperar el callback de autenticacion para
     * llamar al handler.
     * 
     * @return el callback donde se encuentra las credenciales de autenticacion
     * @throws LoginException
     *             en caso de que no se hayan definido elementos necesarios para
     *             obtener las credenciales (CallbackHandler, LoginDAO).
     */
    private Callback[] getCallbacks() throws LoginException {
        if (callbackHandler == null) {
            throwLoginException("No se especifico ningun CallbackHandler");
        }
        if (loginService == null) {
            throwLoginException("No se ha definido un DAO para autenticacion de usuarios");
        }
        Callback[] callbacks = new Callback[2];
        callbacks[0] = new NameCallback("movil-rut: ");
        callbacks[1] = new PasswordCallback("clave: ", false);

        try {
            callbackHandler.handle(callbacks);
        } catch (IOException ioe) {
            throwLoginException(ioe);
        } catch (UnsupportedCallbackException uce) {
            throwLoginException(uce);
        }
        return callbacks;
    }

    /**
     * Metodo que se encarga de obtener los valores del movil y el rut del
     * usuario encapsulados en el Callback de autenticacion.
     * 
     * @param callbacks
     *            callback de autenticacion (JAAS).
     * @return el valor del movil y el rut que se pretende validar
     * @throws LoginException
     *             en caso de que no se haya definido un valor para el movil-rut
     *             del usuario.
     */
    private String getMovilRut(final Callback[] callbacks) throws LoginException {
        String movil_rut = ((NameCallback) callbacks[0]).getName();
        if (movil_rut == null) {
            throwLoginException("No se ha definido un valor para el movil-rut del usuario.");
        }
        return movil_rut;
    }

    /**
     * Metodo que se encarga de obtener la clave(password) del usuario
     * encapsulados en el Callback de autenticacion.
     * 
     * @param callbacks
     *            callback de autenticacion (JAAS).
     * @return la clave del usuario que se pretende validar
     * @throws LoginException
     *             en caso de que no se haya definido un valor para la clave del
     *             usuario.
     */
    private String getClave(final Callback[] callbacks) throws LoginException {
        PasswordCallback passwordCallback = (PasswordCallback) callbacks[1];
        char[] password = passwordCallback.getPassword();
        passwordCallback.clearPassword();
        if (password == null || password.length==0) {
            throwLoginException("No se ha definido ninguna clave para validar");
        }
        return new String(password);
    }

    /**
     * Agrega en la lista de entidades de autenticacion al grupo por defecto.
     */
    private void addGroupsForSubject() {
        logger.info("Adding group ["+DEFAULT_USER_GROUPNAME+"]");
        principalsForSubject.add(new WLSGroupImpl(DEFAULT_USER_GROUPNAME));
    }

}