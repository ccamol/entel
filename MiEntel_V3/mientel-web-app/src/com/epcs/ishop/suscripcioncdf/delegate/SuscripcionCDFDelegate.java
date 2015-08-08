/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.ishop.suscripcioncdf.delegate;

import com.epcs.bean.ResultadoConsultarCatalogoCDFBean;
import com.epcs.bean.ResultadoDesuscribirCDFBean;
import com.epcs.bean.ResultadoObtenerCompatibilidadBean;
import com.epcs.bean.ResultadoObtenerSuscripcionCDFOrqBean;
import com.epcs.bean.ResultadoObtenerSuscripcionesActivasBean;
import com.epcs.bean.ResultadoSuscribirCDFBean;
import com.epcs.bean.ResultadoValidarRoamingBean;
import com.epcs.ishop.suscripcioncdf.dao.SuscripcionCDFDAO;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

/**
 * @author jivasquez (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class SuscripcionCDFDelegate {
    
    private SuscripcionCDFDAO suscripcionCDFDAO;
    
    public SuscripcionCDFDelegate(){
        suscripcionCDFDAO = new SuscripcionCDFDAO();
    }

    /**
     * @return the suscripcionCDFDAO
     */
    public SuscripcionCDFDAO getSuscripcionCDFDAO() {
        return suscripcionCDFDAO;
    }

    /**
     * @param suscripcionCDFDAO the suscripcionCDFDAO to set
     */
    public void setSuscripcionCDFDAO(SuscripcionCDFDAO suscripcionCDFDAO) {
        this.suscripcionCDFDAO = suscripcionCDFDAO;
    }
    
    /**
     * Obtiene el catalogo de suscripciones CDF
     * @return {@link ResultadoConsultarCatalogoCDFBean} con el catalogo de suscripciones CDF
     * @throws DAOException
     * @throws ServiceException
     */
    public ResultadoConsultarCatalogoCDFBean consultarCatalogo() 
        throws DAOException, ServiceException {
        return suscripcionCDFDAO.consultarCatalogo();
    }
    
    /**
     * Obtiene las suscripciones CDF activadas por el usuario
     * @param msisdn
     * @return {@link ResultadoObtenerSuscripcionesActivasBean} con las suscripciones CDF 
     * que el usuario tiene activas.
     * @throws DAOException
     * @throws ServiceException
     */
    public ResultadoObtenerSuscripcionesActivasBean obtenerSuscripcionesActivas(String msisdn)
        throws DAOException, ServiceException {
        return suscripcionCDFDAO.obtenerSuscripcionesActivas(msisdn);
    }
    
    /**
     * Consulta el estado de roaming del usuario.
     * @param msisdn
     * @return {@link ResultadoValidarRoamingBean} con el estado de roaming del usuario
     * @throws DAOException
     * @throws ServiceException
     */
    public ResultadoValidarRoamingBean validarRoaming(String msisdn) 
        throws DAOException, ServiceException {
        return suscripcionCDFDAO.validarRoaming(msisdn);
    }
    
    /**
     * Obtiene la compatibilidad del movil del usuario para video y streaming
     * @param msisdn numero del movil
     * @return {@link ResultadoObtenerCompatibilidadBean} con la info de compatibilidad
     * @throws DAOException
     * @throws ServiceException
     */
    public ResultadoObtenerCompatibilidadBean obtenerCompatibilidad (String msisdn) 
        throws DAOException, ServiceException {
        return suscripcionCDFDAO.obtenerCompatibilidad(msisdn);
    }
    
    /**
     * Activa la suscripcion <CODE>idSuscripcion</CODE> para el usuario con numero <CODE>msisdn</CODE>
     * @param idSuscripcion
     * @param msisdn numero del movil
     * @return {@link ResultadoSuscribirCDFBean} con el resultado de la operacion
     * @throws DAOException
     * @throws ServiceException
     */
    public ResultadoSuscribirCDFBean suscribirCDF (String idSuscripcion, String msisdn)
        throws DAOException, ServiceException {
        return suscripcionCDFDAO.suscribirCDF(idSuscripcion, msisdn);
    }
    
    /**
     * Inactiva la suscripcion <CODE>idSuscripcion</CODE> para el usuario con numero <CODE>msisdn</CODE>
     * @param idSuscripcion
     * @param msisdn numero del movil
     * @return {@link ResultadoDesuscribirCDFBean} con el resultado de la operacion
     * @throws DAOException
     * @throws ServiceException
     */
    public ResultadoDesuscribirCDFBean deSuscribirCDF (String idSuscripcion, String msisdn)
        throws DAOException, ServiceException {
        return suscripcionCDFDAO.deSuscribirCDF(idSuscripcion, msisdn);
    }
    
    /**
     * Realiza una orquestacion de suscripciones CDF para el usuario con numero <CODE>msisdn</CODE>.
     * Esta orquestacion verifica, para cada suscripcion del catalogo, si el usuario la tiene activa
     * o no; tambien verifica que el movil del usuario sea compatible con el tipo de suscripcion. Al
     * final, se devuelve una lista de suscripciones con la info de compatibilidad y activacion. 
     * @param msisdn
     * @return {@link ResultadoObtenerSuscripcionCDFOrqBean} resultado de la orquestacion
     * @throws DAOException
     * @throws ServiceException
     */
    public ResultadoObtenerSuscripcionCDFOrqBean orquestarSuscripcionesCDF(String msisdn)
        throws DAOException, ServiceException{
        return suscripcionCDFDAO.orquestarSuscripcionesCDF(msisdn);
    }
    
}
