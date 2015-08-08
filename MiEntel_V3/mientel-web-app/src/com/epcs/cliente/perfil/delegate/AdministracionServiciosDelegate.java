package com.epcs.cliente.perfil.delegate;

import java.util.List;

import com.epcs.bean.AdminServicioBuzonBean;
import com.epcs.bean.AdministracionServiciosBean;
import com.epcs.bean.CatalogoSAGENBean;
import com.epcs.bean.CatalogoServiciosBean;
import com.epcs.bean.FamiliaSuscripcionBean;
import com.epcs.bean.PaginaServiciosSagenBean;
import com.epcs.bean.ResultadoConsultarSAGENBean;
import com.epcs.bean.ResultadoCrearSuscripcionSAGENBean;
import com.epcs.bean.ResultadoEliminarSuscripcionSAGENBean;
import com.epcs.bean.ResultadoFamiliaSegmentate;
import com.epcs.bean.ResultadoServicioBean;
import com.epcs.bean.ResultadoServiciosSSCCBean;
import com.epcs.bean.RutBean;
import com.epcs.bean.ServicioSagenBean;
import com.epcs.bean.SuscripcionSAGENBean;
import com.epcs.cliente.perfil.dao.AdministracionServiciosDAO;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;
import com.epcs.bean.ServicioCobroRevertidoBean;

public class AdministracionServiciosDelegate {
    
    private AdministracionServiciosDAO administracionServiciosDAO;

    public AdministracionServiciosDelegate() {
        super();
        this.administracionServiciosDAO = new AdministracionServiciosDAO();
    }
    
    /**
     * @return the administracionServiciosDAO
     */
    public AdministracionServiciosDAO getAdministracionServiciosDAO() {
        return administracionServiciosDAO;
    }


    /**
     * @param administracionServiciosDAO the administracionServiciosDAO to set
     */
    public void setAdministracionServiciosDAO(
            AdministracionServiciosDAO administracionServiciosDAO) {
        this.administracionServiciosDAO = administracionServiciosDAO;
    }
    
    /**
     * Consulta los servicios para un usuario SS o CC
     * 
     * @param msisdn
     * @param mercado 
     * @return bean AdministracionServiciosBean
     * @throws DAOException
     * @throws ServiceException
     */
    public AdministracionServiciosBean consultarEstadoServiciosDisponibles(
            String msisdn, String mercado) throws DAOException, ServiceException {
        return administracionServiciosDAO.consultarEstadoServiciosDisponibles(msisdn, mercado);
    }


    /**
     * @param numeroPcs
     * @return
     * @throws DAOException
     * @throws ServiceException
     * @see com.epcs.cliente.perfil.dao.AdministracionServiciosDAO#activarLDI(java.lang.String)
     */
    public ResultadoServiciosSSCCBean activarLDI(String numeroPcs)
            throws DAOException, ServiceException {
        return administracionServiciosDAO.activarLDI(numeroPcs);
    }


    /**
     * @param numeroPcs
     * @return
     * @throws DAOException
     * @throws ServiceException
     * @see com.epcs.cliente.perfil.dao.AdministracionServiciosDAO#activarNumero300(java.lang.String)
     */
    public ResultadoServiciosSSCCBean activarNumero300(String numeroPcs)
            throws DAOException, ServiceException {
        return administracionServiciosDAO.activarNumero300(numeroPcs);
    }


    /**
     * @param numeroPcs
     * @return
     * @throws DAOException
     * @throws ServiceException
     * @see com.epcs.cliente.perfil.dao.AdministracionServiciosDAO#activarNumero606(java.lang.String)
     */
    public ResultadoServiciosSSCCBean activarNumero606(String numeroPcs)
            throws DAOException, ServiceException {
        return administracionServiciosDAO.activarNumero606(numeroPcs);
    }


    /**
     * @param numeroPcs
     * @return
     * @throws DAOException
     * @throws ServiceException
     * @see com.epcs.cliente.perfil.dao.AdministracionServiciosDAO#activarNumero609(java.lang.String)
     */
    public ResultadoServiciosSSCCBean activarNumero609(String numeroPcs)
            throws DAOException, ServiceException {
        return administracionServiciosDAO.activarNumero609(numeroPcs);
    }


    /**
     * @param numeroPcs
     * @return
     * @throws DAOException
     * @throws ServiceException
     * @see com.epcs.cliente.perfil.dao.AdministracionServiciosDAO#activarNumero700(java.lang.String)
     */
    public ResultadoServiciosSSCCBean activarNumero700(String numeroPcs)
            throws DAOException, ServiceException {
        return administracionServiciosDAO.activarNumero700(numeroPcs);
    }
    
    /**
     * @param numeroPcs
     * @return
     * @throws DAOException
     * @throws ServiceException
     * @see com.epcs.cliente.perfil.dao.AdministracionServiciosDAO#activarInternetMovil(java.lang.String)
     */
    public ResultadoServiciosSSCCBean activarInternetMovil(String numeroPcs)
            throws DAOException, ServiceException {
        return administracionServiciosDAO.activarInternetMovil(numeroPcs);
    }
    
    /**
     * @param numeroPcs
     * @return
     * @throws DAOException
     * @throws ServiceException
     * @see com.epcs.cliente.perfil.dao.AdministracionServiciosDAO#activarBandaAnchaMovil(java.lang.String)
     */
    public ResultadoServiciosSSCCBean activarBandaAnchaMovil(String numeroPcs)
            throws DAOException, ServiceException {
        return administracionServiciosDAO.activarBandaAnchaMovil(numeroPcs);
    }
    
    /**
     * @param numeroPcs
     * @return
     * @throws DAOException
     * @throws ServiceException
     * @see com.epcs.cliente.perfil.dao.AdministracionServiciosDAO#activarInternetMovilBAM(java.lang.String)
     */
    public ResultadoServiciosSSCCBean activarInternetMovilBAM(String numeroPcs)
            throws DAOException, ServiceException {
        return administracionServiciosDAO.activarInternetMovilBAM(numeroPcs);
    }
    
    /**
     * @param numeroPcs
     * @return
     * @throws DAOException
     * @throws ServiceException
     * @see com.epcs.cliente.perfil.dao.AdministracionServiciosDAO#activarMMSBAM(java.lang.String)
     */
    public ResultadoServiciosSSCCBean activarMMSBAM(String numeroPcs)
            throws DAOException, ServiceException {
        return administracionServiciosDAO.activarMMSBAM(numeroPcs);
    }

    /**
     * @param numeroPcs
     * @return
     * @throws DAOException
     * @throws ServiceException
     * @see com.epcs.cliente.perfil.dao.AdministracionServiciosDAO#activarNumeroInterarea(java.lang.String)
     */
    public ResultadoServiciosSSCCBean activarNumeroInterarea(String numeroPcs)
            throws DAOException, ServiceException {
        return administracionServiciosDAO.activarNumeroInterarea(numeroPcs);
    }


    /**
     * @param numeroPcs
     * @return
     * @throws DAOException
     * @throws ServiceException
     * @see com.epcs.cliente.perfil.dao.AdministracionServiciosDAO#activarRoaming(java.lang.String)
     */
    public ResultadoServiciosSSCCBean activarRoaming(String numeroPcs)
            throws DAOException, ServiceException {
        return administracionServiciosDAO.activarRoaming(numeroPcs);
    }


    /**
     * @param numeroPcs
     * @return
     * @throws DAOException
     * @throws ServiceException
     * @see com.epcs.cliente.perfil.dao.AdministracionServiciosDAO#activarWap(java.lang.String)
     */
    public ResultadoServiciosSSCCBean activarWap(String numeroPcs)
            throws DAOException, ServiceException {
        return administracionServiciosDAO.activarWap(numeroPcs);
    }


    /**
     * @param numeroPcs
     * @return
     * @throws DAOException
     * @throws ServiceException
     * @see com.epcs.cliente.perfil.dao.AdministracionServiciosDAO#desactivarLDI(java.lang.String)
     */
    public ResultadoServiciosSSCCBean desactivarLDI(String numeroPcs)
            throws DAOException, ServiceException {
        return administracionServiciosDAO.desactivarLDI(numeroPcs);
    }


    /**
     * @param numeroPcs
     * @return
     * @throws DAOException
     * @throws ServiceException
     * @see com.epcs.cliente.perfil.dao.AdministracionServiciosDAO#desactivarNumero300(java.lang.String)
     */
    public ResultadoServiciosSSCCBean desactivarNumero300(String numeroPcs)
            throws DAOException, ServiceException {
        return administracionServiciosDAO.desactivarNumero300(numeroPcs);
    }


    /**
     * @param numeroPcs
     * @return
     * @throws DAOException
     * @throws ServiceException
     * @see com.epcs.cliente.perfil.dao.AdministracionServiciosDAO#desactivarNumero606(java.lang.String)
     */
    public ResultadoServiciosSSCCBean desactivarNumero606(String numeroPcs)
            throws DAOException, ServiceException {
        return administracionServiciosDAO.desactivarNumero606(numeroPcs);
    }


    /**
     * @param numeroPcs
     * @return
     * @throws DAOException
     * @throws ServiceException
     * @see com.epcs.cliente.perfil.dao.AdministracionServiciosDAO#desactivarNumero609(java.lang.String)
     */
    public ResultadoServiciosSSCCBean desactivarNumero609(String numeroPcs)
            throws DAOException, ServiceException {
        return administracionServiciosDAO.desactivarNumero609(numeroPcs);
    }


    /**
     * @param numeroPcs
     * @return
     * @throws DAOException
     * @throws ServiceException
     * @see com.epcs.cliente.perfil.dao.AdministracionServiciosDAO#desactivarNumero700(java.lang.String)
     */
    public ResultadoServiciosSSCCBean desactivarNumero700(String numeroPcs)
            throws DAOException, ServiceException {
        return administracionServiciosDAO.desactivarNumero700(numeroPcs);
    }

    
    /**
     * @param numeroPcs
     * @return
     * @throws DAOException
     * @throws ServiceException
     * @see com.epcs.cliente.perfil.dao.AdministracionServiciosDAO#desactivarInternetMovil(java.lang.String)
     */
    public ResultadoServiciosSSCCBean desactivarInternetMovil(String numeroPcs)
            throws DAOException, ServiceException {
        return administracionServiciosDAO.desactivarInternetMovil(numeroPcs);
    }
    
    
    /**
     * @param numeroPcs
     * @return
     * @throws DAOException
     * @throws ServiceException
     * @see com.epcs.cliente.perfil.dao.AdministracionServiciosDAO#desactivarBandaAnchaMovil(java.lang.String)
     */
    public ResultadoServiciosSSCCBean desactivarBandaAnchaMovil(String numeroPcs)
            throws DAOException, ServiceException {
        return administracionServiciosDAO.desactivarBandaAnchaMovil(numeroPcs);
    }
    
    /**
     * @param numeroPcs
     * @return
     * @throws DAOException
     * @throws ServiceException
     * @see com.epcs.cliente.perfil.dao.AdministracionServiciosDAO#desactivarInternetMovilBAM(java.lang.String)
     */
    public ResultadoServiciosSSCCBean desactivarInternetMovilBAM(String numeroPcs)
            throws DAOException, ServiceException {
        return administracionServiciosDAO.desactivarInternetMovilBAM(numeroPcs);
    }
    
    /**
     * @param numeroPcs
     * @return
     * @throws DAOException
     * @throws ServiceException
     * @see com.epcs.cliente.perfil.dao.AdministracionServiciosDAO#desactivarMMSBAM(java.lang.String)
     */
    public ResultadoServiciosSSCCBean desactivarMMSBAM(String numeroPcs)
            throws DAOException, ServiceException {
        return administracionServiciosDAO.desactivarMMSBAM(numeroPcs);
    }

    /**
     * @param numeroPcs
     * @return
     * @throws DAOException
     * @throws ServiceException
     * @see com.epcs.cliente.perfil.dao.AdministracionServiciosDAO#desactivarNumeroInterarea(java.lang.String)
     */
    public ResultadoServiciosSSCCBean desactivarNumeroInterarea(String numeroPcs)
            throws DAOException, ServiceException {
        return administracionServiciosDAO.desactivarNumeroInterarea(numeroPcs);
    }


    /**
     * @param numeroPcs
     * @return
     * @throws DAOException
     * @throws ServiceException
     * @see com.epcs.cliente.perfil.dao.AdministracionServiciosDAO#desactivarRoaming(java.lang.String)
     */
    public ResultadoServiciosSSCCBean desactivarRoaming(String numeroPcs)
            throws DAOException, ServiceException {
        return administracionServiciosDAO.desactivarRoaming(numeroPcs);
    }


    /**
     * @param numeroPcs
     * @return
     * @throws DAOException
     * @throws ServiceException
     * @see com.epcs.cliente.perfil.dao.AdministracionServiciosDAO#desactivarWap(java.lang.String)
     */
    public ResultadoServiciosSSCCBean desactivarWap(String numeroPcs)
            throws DAOException, ServiceException {
        return administracionServiciosDAO.desactivarWap(numeroPcs);
    }


    /**
     * @param numeroPcs
     * @return
     * @throws DAOException
     * @throws ServiceException
     * @see com.epcs.cliente.perfil.dao.AdministracionServiciosDAO#activarNotificacionSMS(java.lang.String)
     */
    public ResultadoServicioBean activarNotificacionSMS(String numeroPcs)
            throws DAOException, ServiceException {
        return administracionServiciosDAO.activarNotificacionSMS(numeroPcs);
    }


    /**
     * @param numeroPcs
     * @return
     * @throws DAOException
     * @throws ServiceException
     * @see com.epcs.cliente.perfil.dao.AdministracionServiciosDAO#desactivarNotificacionSMS(java.lang.String)
     */
    public ResultadoServicioBean desactivarNotificacionSMS(String numeroPcs)
            throws DAOException, ServiceException {
        return administracionServiciosDAO.desactivarNotificacionSMS(numeroPcs);
    }


    /**
     * @param adminServicioBuzonBean
     * @return
     * @throws DAOException
     * @throws ServiceException
     * @see com.epcs.cliente.perfil.dao.AdministracionServiciosDAO#activarBuzonVoz(com.epcs.bean.AdminServicioBuzonBean)
     */
    public ResultadoServicioBean activarBuzonVoz(
            AdminServicioBuzonBean adminServicioBuzonBean) throws DAOException,
            ServiceException {
        return administracionServiciosDAO
                .activarBuzonVoz(adminServicioBuzonBean);
    }


    /**
     * @param adminServicioBuzonBean
     * @return
     * @throws DAOException
     * @throws ServiceException
     * @see com.epcs.cliente.perfil.dao.AdministracionServiciosDAO#desactivarBuzonVoz(com.epcs.bean.AdminServicioBuzonBean)
     */
    public ResultadoServicioBean desactivarBuzonVoz(
            AdminServicioBuzonBean adminServicioBuzonBean) throws DAOException,
            ServiceException {
        return administracionServiciosDAO
                .desactivarBuzonVoz(adminServicioBuzonBean);
    }

    /**
     * 
     * @param numeroPcs
     * @param rutUsuario
     * @return
     * @throws DAOException
     * @throws ServiceException
     * @see AdministracionServiciosDAO#obtenerEmailBuzonPremium(String, RutBean)
     */
    public String obtenerEmailBuzonPremium(String numeroPcs, RutBean rutUsuario)
            throws DAOException, ServiceException {
        return administracionServiciosDAO.obtenerEmailBuzonPremium(numeroPcs,
                rutUsuario);
    }

    /**
     * @param numeroPcs
     * @param tipoLista
     * @param listaNumeros
     * @return
     * @throws DAOException
     * @throws ServiceException
     * @see com.epcs.cliente.perfil.dao.AdministracionServiciosDAO#activarAvisale(java.lang.String, java.lang.String, java.util.List)
     */
    public ResultadoServicioBean activarAvisale(String numeroPcs,
            String tipoLista, List<String> listaNumeros) throws DAOException,
            ServiceException {
        return administracionServiciosDAO.activarAvisale(numeroPcs, tipoLista,
                listaNumeros);
    }


    /**
     * @param numeroPcs
     * @return
     * @throws DAOException
     * @throws ServiceException
     * @see com.epcs.cliente.perfil.dao.AdministracionServiciosDAO#desactivarAvisale(java.lang.String)
     */
    public ResultadoServicioBean desactivarAvisale(String numeroPcs)
            throws DAOException, ServiceException {
        return administracionServiciosDAO.desactivarAvisale(numeroPcs);
    }


    /**
     * @param numeroPcs
     * @param tipoLista
     * @param listaNumeros
     * @return
     * @throws DAOException
     * @throws ServiceException
     * @see com.epcs.cliente.perfil.dao.AdministracionServiciosDAO#actualizarAvisale(java.lang.String, java.lang.String, java.util.List)
     */
    public ResultadoServicioBean actualizarAvisale(String numeroPcs,
            String tipoLista, List<String> listaNumeros) throws DAOException,
            ServiceException {
        return administracionServiciosDAO.actualizarAvisale(numeroPcs,
                tipoLista, listaNumeros);
    }


    /**
     * @param numeroPcs
     * @return
     * @throws DAOException
     * @throws ServiceException
     * @see com.epcs.cliente.perfil.dao.AdministracionServiciosDAO#activarBuzonPP(java.lang.String)
     */
    public ResultadoServicioBean activarBuzonPP(String numeroPcs)
            throws DAOException, ServiceException {
        return administracionServiciosDAO.activarBuzonPP(numeroPcs);
    }


    /**
     * @param numeroPcs
     * @return
     * @throws DAOException
     * @throws ServiceException
     * @see com.epcs.cliente.perfil.dao.AdministracionServiciosDAO#desactivarBuzonPP(java.lang.String)
     */
    public ResultadoServicioBean desactivarBuzonPP(String numeroPcs)
            throws DAOException, ServiceException {
        return administracionServiciosDAO.desactivarBuzonPP(numeroPcs);
    }


    /**
     * @param numeroPcs
     * @return
     * @throws DAOException
     * @throws ServiceException
     * @see com.epcs.cliente.perfil.dao.AdministracionServiciosDAO#activarNotificacionSMSPP(java.lang.String)
     */
    public ResultadoServicioBean activarNotificacionSMSPP(String numeroPcs)
            throws DAOException, ServiceException {
        return administracionServiciosDAO.activarNotificacionSMSPP(numeroPcs);
    }


    /**
     * @param numeroPcs
     * @return
     * @throws DAOException
     * @throws ServiceException
     * @see com.epcs.cliente.perfil.dao.AdministracionServiciosDAO#desactivarNotificacionSMSPP(java.lang.String)
     */
    public ResultadoServicioBean desactivarNotificacionSMSPP(String numeroPcs)
            throws DAOException, ServiceException {
        return administracionServiciosDAO.desactivarNotificacionSMSPP(numeroPcs);
    }


    /**
     * @param numeroPcs
     * @return
     * @throws DAOException
     * @throws ServiceException
     * @see com.epcs.cliente.perfil.dao.AdministracionServiciosDAO#activarMMSGPRS(java.lang.String)
     */
    public ResultadoServicioBean activarMMSGPRS(String numeroPcs)
            throws DAOException, ServiceException {
        return administracionServiciosDAO.activarMMSGPRS(numeroPcs);
    }


    /**
     * @param numeroPcs
     * @return
     * @throws DAOException
     * @throws ServiceException
     * @see com.epcs.cliente.perfil.dao.AdministracionServiciosDAO#desactivarMMSGPRS(java.lang.String)
     */
    public ResultadoServicioBean desactivarMMSGPRS(String numeroPcs)
            throws DAOException, ServiceException {
        return administracionServiciosDAO.desactivarMMSGPRS(numeroPcs);
    }


    /**
     * @param numeroPcs
     * @return
     * @throws DAOException
     * @throws ServiceException
     * @see com.epcs.cliente.perfil.dao.AdministracionServiciosDAO#activarWapPP(java.lang.String)
     */
    public ResultadoServicioBean activarWapPP(String numeroPcs)
            throws DAOException, ServiceException {
        return administracionServiciosDAO.activarWapPP(numeroPcs);
    }


    /**
     * @param numeroPcs
     * @return
     * @throws DAOException
     * @throws ServiceException
     * @see com.epcs.cliente.perfil.dao.AdministracionServiciosDAO#desactivarWapPP(java.lang.String)
     */
    public ResultadoServicioBean desactivarWapPP(String numeroPcs)
            throws DAOException, ServiceException {
        return administracionServiciosDAO.desactivarWapPP(numeroPcs);
    }

    /**
     * @param numeroPcs
     * @return
     * @throws DAOException
     * @throws ServiceException
     * @see com.epcs.cliente.perfil.dao.AdministracionServiciosDAO#desactivarWapPP(java.lang.String)
     */
    public ResultadoServicioBean activarFacturaElectronica(String numeroPcs, String diasAviso)
            throws DAOException, ServiceException {
        return administracionServiciosDAO.activarFacturaElectronica(numeroPcs, diasAviso);
    }
    
    /**
     * @param numeroPcs
     * @return
     * @throws DAOException
     * @throws ServiceException
     * @see com.epcs.cliente.perfil.dao.AdministracionServiciosDAO#desactivarWapPP(java.lang.String)
     */
    public ResultadoServicioBean modificarFacturaElectronica(String numeroPcs, String diasAviso)
            throws DAOException, ServiceException {
        return administracionServiciosDAO.modificarFacturaElectronica(numeroPcs, diasAviso);
    }

    /**
     * @param numeroPcs
     * @return
     * @throws DAOException
     * @throws ServiceException
     * @see com.epcs.cliente.perfil.dao.AdministracionServiciosDAO#desactivarWapPP(java.lang.String)
     */
    public ResultadoServicioBean desactivarFacturaElectronica(String numeroPcs)
            throws DAOException, ServiceException {
        return administracionServiciosDAO.desactivarFacturaElectronica(numeroPcs);
    }
    
    
    
    /**
     * @param numeroPcs
     * @return
     * @throws DAOException
     * @throws ServiceException
     * @see com.epcs.cliente.perfil.dao.AdministracionServiciosDAO#desactivarWapPP(java.lang.String)
     */
    public ResultadoServicioBean agregarNumeroCobroRevertido(String numeroPcs, String numeroAgregar)
            throws DAOException, ServiceException {
        return administracionServiciosDAO.agregarNumeroCobroRevertido(numeroPcs, numeroAgregar);
    }
    
    /**
     * @param numeroPcs
     * @return
     * @throws DAOException
     * @throws ServiceException
     * @see com.epcs.cliente.perfil.dao.AdministracionServiciosDAO#desactivarWapPP(java.lang.String)
     */
    public ResultadoServicioBean eliminarNumeroCobroRevertido(String numeroPcs, String numeroEliminar)
            throws DAOException, ServiceException {
        return administracionServiciosDAO.eliminarNumeroCobroRevertido(numeroPcs, numeroEliminar);
    }

    /**
     * @param numeroPcs
     * @return
     * @throws DAOException
     * @throws ServiceException
     * @see com.epcs.cliente.perfil.dao.AdministracionServiciosDAO#desactivarWapPP(java.lang.String)
     */
    public ResultadoServicioBean validarNumeroCobroRevertido(String numeroPcs, String numeroValidar)
            throws DAOException, ServiceException {
        return administracionServiciosDAO.validarNumeroCobroRevertido(numeroPcs, numeroValidar);
    }
    
    
    /**
     * Consulta la lista de numeros asociadas
     * 
     * @param msisdn
     * @return bean ServicioCobroRevertidoBean
     * @throws DAOException
     * @throws ServiceException
     */
    public ServicioCobroRevertidoBean consultarListaComunik2(String msisdn)throws DAOException, ServiceException{
        return administracionServiciosDAO.consultarListaComunik2(msisdn);
    }
    
    /**
     * @param numeroPcs
     * @return
     * @throws DAOException
     * @throws ServiceException
     * @see com.epcs.cliente.perfil.dao.servicios.ServiciosPPDAO#desactivarWap(java.lang.String)
     */
    public ResultadoServicioBean activarRecepcionCobroRevertido(String numeroPcs)
            throws DAOException, ServiceException {
        return administracionServiciosDAO.activarRecepcionCobroRevertido(numeroPcs);
    }
    
    /**
     * @param numeroPcs
     * @return
     * @throws DAOException
     * @throws ServiceException
     * @see com.epcs.cliente.perfil.dao.servicios.ServiciosPPDAO#desactivarWap(java.lang.String)
     */
    public ResultadoServicioBean desactivarRecepcionCobroRevertido(String numeroPcs)
            throws DAOException, ServiceException {
        return administracionServiciosDAO.desactivarRecepcionCobroRevertido(numeroPcs);
    }
         
    /**
     * @param numeroPcs
     * @return
     * @throws DAOException
     * @throws ServiceException
     * @see com.epcs.cliente.provision.suscripcion#consultarSuscripcionSAGEN(java.lang.String)
     */
    public ResultadoConsultarSAGENBean consultarSuscripcionSAGEN(String msisdn)
            throws DAOException, ServiceException {
        return administracionServiciosDAO.consultarSuscripcionSAGEN(msisdn);
    }
    
    /**
     * @return
     * @throws DAOException
     * @throws ServiceException
     * @see com.epcs.cliente.provision.suscripcion#CatalogoSAGENBean()
     */
   public CatalogoSAGENBean obtenerCatalogoSAGEN()
            throws DAOException, ServiceException {
        return administracionServiciosDAO.obtenerCatalogoSAGEN();
    }
 
    /**
     * @return
     * @throws DAOException
     * @throws ServiceException
     * @see com.epcs.cliente.provision.suscripcion#CatalogoSAGENBean()
     */
    public CatalogoSAGENBean obtenerCatalogoActualSAGEN(String msisdn) 
	throws DAOException, ServiceException{
    	return administracionServiciosDAO.obtenerCatalogoActualSAGEN(msisdn);
    }
    /**
     * @return
     * @throws DAOException
     * @throws ServiceException
     * @see com.epcs.cliente.provision.suscripcion#ResultadoCrearSuscripcionSAGEN(SuscripcionSAGENBean)
     */
    public ResultadoCrearSuscripcionSAGENBean crearSuscripcionSAGEN(SuscripcionSAGENBean suscripcion)
            throws DAOException, ServiceException {
        return administracionServiciosDAO.crearSuscripcionSAGEN(suscripcion);
    }   
    
    /**
     * @return
     * @throws DAOException
     * @throws ServiceException
     * @see com.epcs.cliente.provision.suscripcion#ResultadoCrearSuscripcionSAGEN(SuscripcionSAGENBean)
     */
    public ResultadoEliminarSuscripcionSAGENBean eliminarSuscripcionSAGEN(String msisdn)
            throws DAOException, ServiceException {
        return administracionServiciosDAO.eliminarSuscripcionSAGEN(msisdn);
    }  
    

    
    //hcastillo
    /**
     * @return
     * @throws DAOException
     * @throws ServiceException
     * @see com.epcs.cliente.provision.suscripcion#CatalogoSAGENBean()
     */
    public CatalogoServiciosBean obtenerCatalogoActualServicios(String msisdn, List<FamiliaSuscripcionBean> suscripcion)
            throws DAOException, ServiceException {
        return administracionServiciosDAO.obtenerCatalogoActualServicios(msisdn,suscripcion);
    }
    
    //hcastillo
    /**
     * @return
     * @throws DAOException
     * @throws ServiceException
     * @see com.epcs.cliente.provision.suscripcion#CatalogoSAGENBean()
     */
    public ResultadoCrearSuscripcionSAGENBean ResultadoCrearSuscriptionServices(SuscripcionSAGENBean suscripcion)
            throws DAOException, ServiceException {
        return administracionServiciosDAO.ResultadoCrearSuscriptionServices(suscripcion);
    }
    
    
    /**
     * @return
     * @throws DAOException
     * @throws ServiceException
     * @see com.epcs.cliente.provision.suscripcion#ResultadoCrearSuscripcionSAGEN(SuscripcionSAGENBean)
     */
    public ResultadoEliminarSuscripcionSAGENBean eliminarSuscripcionServiciosSagen(SuscripcionSAGENBean suscripcion)
            throws DAOException, ServiceException {
        return administracionServiciosDAO.eliminarSuscripcionServiciosSagen(suscripcion);
    }  
    
    
    /**
     * @return
     * @throws DAOException
     * @throws ServiceException
     * @see com.epcs.cliente.provision.suscripcion#ResultadoCrearSuscripcionSAGEN(SuscripcionSAGENBean)
     */
    public String getEstadoFamiliaService(String movil, String familia)
            throws DAOException, ServiceException {
         return administracionServiciosDAO.getEstadoFamiliaService(movil,familia);
    }  
    
    /**
     * @return
     * @throws DAOException
     * @throws ServiceException
     * @see com.epcs.cliente.provision.suscripcion#ResultadoCrearSuscripcionSAGEN(SuscripcionSAGENBean)
     */
    public ResultadoFamiliaSegmentate bloquearFamiliaSegmentate(SuscripcionSAGENBean suscripcion)
            throws DAOException, ServiceException {
         return administracionServiciosDAO.bloquearFamiliaSegmentate(suscripcion);
    }  
    
    /**
     * @return
     * @throws DAOException
     * @throws ServiceException
     * @see com.epcs.cliente.provision.suscripcion#ResultadoCrearSuscripcionSAGEN(SuscripcionSAGENBean)
     */
    public ResultadoFamiliaSegmentate desbloquearFamiliaSegmentate(String movil,String familia)
            throws DAOException, ServiceException {
         return administracionServiciosDAO.desbloquearFamiliaSegmentate(movil,familia);
    }  
    
    
    
}
