/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.cliente.perfil.delegate;

import java.io.Serializable;
import java.util.List;

import com.epcs.bean.CuentaClienteBean;
import com.epcs.bean.DireccionBean;
import com.epcs.bean.InformacionTitularBean;
import com.epcs.bean.MsisdnAsociadoBean;
import com.epcs.bean.PerfilUsuarioBean;
import com.epcs.bean.PlanContratadoBean;
import com.epcs.bean.UsuarioBean;
import com.epcs.cliente.perfil.dao.CuentaDAO;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

/**
 * @author jlopez (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class CuentaDelegate implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private CuentaDAO cuentaDAO;

    public CuentaDelegate() {
        super();
        cuentaDAO = new CuentaDAO();
    }

    /**
     * @return the cuentaDAO
     */
    public CuentaDAO getCuentaDAO() {
        return cuentaDAO;
    }

    /**
     * @param cuentaDAO
     *            the cuentaDAO to set
     */
    public void setCuentaDAO(CuentaDAO cuentaDAO) {
        this.cuentaDAO = cuentaDAO;
    }

	/**
     * Obtiene Informacion del Un Usuario
     * 
     * @param numeroPcs
	 * @param rut
     * @return
     * @throws ServiceException
     * @throws DAOException
     */
    public UsuarioBean obtenerUsuario(String numeroPcs, String rut)
            throws DAOException, ServiceException {
        return this.cuentaDAO.obtenerUsuario(numeroPcs, rut);
    }

    /**
     * Obtiene los usuarios asociados a la cuenta del usuario en sesion.
     * 
     * @return java.util.List de {@link UsuarioBean} con los datos de los
     *         usuarios
     * @throws ServiceException
     * @throws DAOException
     */
    public List<UsuarioBean> getUsuarios(String nroPcs) throws DAOException,
            ServiceException {
        return this.cuentaDAO.getUsuarios(nroPcs);
    }

    public void actualizarDatos(UsuarioBean usuarioBean) throws DAOException,
            ServiceException {
        this.cuentaDAO.actualizarDatos(usuarioBean);
    }

    /**
     * 
     * @param nroPcs
     * @param rut
     * @param actAaa
     * @param nuevoAAA
     * @throws DAOException
     * @throws ServiceException
     */
    public void actualizarAAA(String nroPcs, String rut, String sesionAaa,
            String nuevoAAA) throws DAOException, ServiceException {
        this.cuentaDAO.actualizarAAA(nroPcs, rut, sesionAaa, nuevoAAA);
    }

	public List<MsisdnAsociadoBean> getListaMsisdnAsociados(String msisdnUsuario, String subMercado, String flagBam) throws DAOException {
		return this.cuentaDAO.getListaMsisdnAsociados(msisdnUsuario, subMercado, flagBam);
	}

    /**
     * @param numeroPcs
     * @return
     * @throws DAOException
     * @throws ServiceException 
     */
    public PerfilUsuarioBean obtenerPerfilUsuario(String numeroPcs)
            throws DAOException, ServiceException {
        return this.cuentaDAO.obtenerPerfilUsuario(numeroPcs);
    }
    
    /**
     * Obtiene la direccion postal para factura de un usuario.
     * @param rut
     * @param numeroCuenta
     * @param usuario
     * @param password
     * 
     * @throws ServiceException 
     * @throws DAOException 
     * 
     */
    public DireccionBean obtenerDireccionFactura(String rut, String numeroCuenta)
            throws DAOException, ServiceException {
        return this.cuentaDAO.obtenerDireccionFactura(rut, numeroCuenta);
    }
    
    /**
     * 
     * @param ip
     * @param direccionBean
     * @throws DAOException
     * @throws ServiceException
     */
    public void actualizarDireccionPostal(String nroCuenta, String ip, DireccionBean direccionBean) throws DAOException, ServiceException {
        this.cuentaDAO.actualizarDireccionPostal(nroCuenta, ip, direccionBean);
    }
    
    /**
     * Metodo que recupea la informacion sobre plan contratado por un usuario
     * @param numeroPcs
     * @return
     * @throws DAOException
     * @throws ServiceException
     */
    public PlanContratadoBean consultarPlanContratadoPP(String numeroPcs)
		      throws DAOException, ServiceException {
    	return this.cuentaDAO.consultarPlanContratadoPP(numeroPcs);
    }
    
    /**
     * Actualiza solo el correo del usuario
     * @param numeroPcs
     * @param rut
     * @param usuario
     * @param dominio
     * @return
     * @throws DAOException
     * @throws ServiceException
     */
    public boolean actualizarCorreoBuzon(
  		  String numeroPcs, String rut, String usuario, String dominio)
		      throws DAOException, ServiceException {
    	return this.cuentaDAO.actualizarCorreoBuzon(numeroPcs, rut, usuario, dominio);
    }
    
    /**
     * Obtiene el plan actual de seguridad
     * @param msisdn
     * @return
     * @throws DAOException
     * @throws ServiceException
     */
    public InformacionTitularBean obtenerInformacionTitular(String msisdn) 
    	throws DAOException, ServiceException {
    	return this.cuentaDAO.obtenerInformacionTitular(msisdn);
    }
    
    
    /**
     * Obtiene datos de cuenta de un cliente
     * @param rut
     * @return
     * @throws DAOException
     * @throws ServiceException
     */
    public List<CuentaClienteBean> obtenerCuentaCliente(String rut) 
    	throws DAOException, ServiceException {
    	return this.cuentaDAO.obtenerCuentaCliente(rut);
    }
    
    /**
     * Metodo que permite desplegar una lista con los moviles asociados a una
     * a una cuenta, en principio este metodo tiene logica pensada para desplegar 
     * moviles en el formulario de reclamos.
     * 
     * @param msisdnUsuario
     *            numero del administrador de la cuenta
     * @return una lista con los datos de los moviles asociados
     * @throws DAOException
     *             en caso de algun problema al obtener la informacion del
     *             servicio de perfilamiento.
     */
    public List<MsisdnAsociadoBean> getListaMsisdnAsociadosReclamos(String msisdnUsuario, String subMercado, String flagBam) throws DAOException {
		return this.cuentaDAO.getListaMsisdnAsociadosReclamos(msisdnUsuario, subMercado, flagBam);
	}
    
}
