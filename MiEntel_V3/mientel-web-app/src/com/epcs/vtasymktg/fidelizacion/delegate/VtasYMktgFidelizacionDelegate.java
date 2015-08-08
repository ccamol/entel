/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.vtasymktg.fidelizacion.delegate;

import java.util.List;

import com.epcs.bean.ExpiracionProximaPuntosBean;
import com.epcs.bean.ItemProductoCanjeBean;
import com.epcs.bean.MatrizCatalogoCanjeBean;
import com.epcs.bean.PaginaHistorialCanjeBean;
import com.epcs.bean.PuntosBean;
import com.epcs.bean.ResultadoCanjeDePuntosBean;
import com.epcs.bean.ResultadoConsultarPuntosBean;
import com.epcs.bean.ResultadoObtenerCatalogoBean;
import com.epcs.bean.ResultadoObtenerHistorialBean;
import com.epcs.bean.ResultadoRegalarPuntosBean;
import com.epcs.cliente.perfil.dao.CuentaDAO;
//import com.epcs.bean.ResultadoRegalarPuntosBean;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;
import com.epcs.vtasymktg.fidelizacion.dao.VtasYMktgFidelizacionDAO;

/**
 * @author jivasquez (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class VtasYMktgFidelizacionDelegate {
    
    private VtasYMktgFidelizacionDAO vtasYMktgFidelizacionDelegateDAO;
    private CuentaDAO cuentaDAO;
    
    public VtasYMktgFidelizacionDelegate(){
        vtasYMktgFidelizacionDelegateDAO = new VtasYMktgFidelizacionDAO();
        cuentaDAO = new CuentaDAO();
    }
    
    /**
     * Obtiene Informacion de los puntos de un usuario
     * @param rutSinDV
     * @return {@link ResultadoConsultarPuntosBean} con el detalle de los puntos
     * @throws ServiceException
     * @throws DAOException
     */
    public ResultadoConsultarPuntosBean consultarInfoPuntosCliente (
            String rutSinDV) throws DAOException, ServiceException {
    	ResultadoConsultarPuntosBean resultado = vtasYMktgFidelizacionDelegateDAO.consultarPuntos(rutSinDV);
        return resultado;
    }
    
    /**
     * Obtiene Informacion de los puntos de un usuario
     * @param infoPuntos {@link ResultadoConsultarPuntosBean} con la informacion de puntos del usuario
     * @param msisdn numero movil del usuario. Se usara para consultar la categoria de usuario
     * @return {@link ResultadoConsultarPuntosBean} <code>infoPuntos</code> actualizado con la categoria
     * @throws ServiceException
     * @throws DAOException
     */
    public ResultadoConsultarPuntosBean consultarCategoriaCliente (
    		ResultadoConsultarPuntosBean infoPuntos, String msisdn) throws DAOException, ServiceException {
    	infoPuntos.setCategoriaCliente(cuentaDAO.consultarCategoriaCliente(msisdn));
        return infoPuntos;
    }
    
    /**
     * 
     * @param rut
     * @param periodo
     * @param tipo   
     * @param porPagina  
     * @throws DAOException
     * @throws ServiceException
     */
    public List<PaginaHistorialCanjeBean> getHistorialCartolaPuntos(int rut, int periodo, int tipo, int porPagina, String descAbonoPromocional) throws ServiceException, DAOException{
    	return vtasYMktgFidelizacionDelegateDAO.getHistorialCartolaPuntos(rut, periodo, tipo, porPagina, descAbonoPromocional);
    } 
    
    /**
     * Realiza la operacion de canje de puntos por bolsas o recargas
     * @param msisdn
     * @param rut
     * @param monto
     * @return {@link ResultadoCanjeDePuntosBean}
     * @throws DAOException
     * @throws ServiceException
     */
    public ResultadoCanjeDePuntosBean canjearPuntos(String msisdn, String rut, String monto)
            throws DAOException, ServiceException {
        return vtasYMktgFidelizacionDelegateDAO.canjearPuntos(msisdn, rut, monto);
    }
    
    public ResultadoRegalarPuntosBean regalarPuntos(String msisdnSponsor, String msisdnRecibe, String rutSinDV, String monto, String mercado)
    throws DAOException, ServiceException {
    	return vtasYMktgFidelizacionDelegateDAO.regalarPuntos(msisdnSponsor, msisdnRecibe, rutSinDV, monto, mercado);
    }
    
    /**
     * Valida la asociacion de entre el movil y el rut
     * @param msisdn
     * @param rutSinDV
     * @return {@link String}
     * @throws DAOException
     * @throws ServiceException
     */
    public String validarAsociacionRutMovil(String msisdn, String rutSinDV)
            throws DAOException, ServiceException {
        return vtasYMktgFidelizacionDelegateDAO.validarAsociacionRutMovil(msisdn, rutSinDV);
    }

    /**
     * 
     * @param canal
     * @param mercado  
     * @throws DAOException
     * @throws ServiceException
     */
    public MatrizCatalogoCanjeBean catalogoCanje(int canal, int mercado) throws ServiceException, DAOException{
    	return vtasYMktgFidelizacionDelegateDAO.getMatrizCatalogo(canal, mercado);
    }   
    
    
    /**
     * 
     * @param 
     * @param   
     * @throws DAOException
     * @throws ServiceException
     */
   
    public List<ExpiracionProximaPuntosBean> expiracionPuntos(int rut) throws ServiceException, DAOException{
    	return vtasYMktgFidelizacionDelegateDAO.getExpiracionProximaPuntos(rut);
    }
   

    /**
     * @return the vtasYMktgFidelizacionDelegateDAO
     */
    public VtasYMktgFidelizacionDAO getVtasYMktgFidelizacionDAO() {
        return vtasYMktgFidelizacionDelegateDAO;
    }

    /**
     * @param vtasYMktgFidelizacionDelegateDAO the vtasYMktgFidelizacionDelegateDAO to set
     */
    public void setVtasYMktgFidelizacionDAO(VtasYMktgFidelizacionDAO vtasYMktgFidelizacionDAO) {
        this.vtasYMktgFidelizacionDelegateDAO = vtasYMktgFidelizacionDAO;
    }
    
    /**      
     * @param clave 
     * @param rutCliente 
     * @param movil
     * @param codProducto
     * @param codStand
     * @param claveStand
     * @param puntosCanje
     * @throws DAOException
     * @throws ServiceException
     */
    public String canjearPuntosZonaVerano(String rutCliente, String movil, String codProducto,String codStand,String claveStand, String puntosCanje ) throws ServiceException, DAOException{
    	return vtasYMktgFidelizacionDelegateDAO.canjearPuntosZonaVerano(rutCliente, movil,codProducto,codStand,claveStand,puntosCanje);
    } 
    
    /**
     * @param claveStand    
     * @throws DAOException
     * @throws ServiceException
     */
    public boolean validarClaveZonaVerano(String claveStand,String codigoStand) throws ServiceException, DAOException{
    	return vtasYMktgFidelizacionDelegateDAO.validarClaveZonaVerano(claveStand,codigoStand);
    } 
    
    /**
     * 
     * @param canal
     * @param mercado  
     * @param codigosZona  
     * @throws DAOException
     * @throws ServiceException
     */
    public List<ItemProductoCanjeBean> catalogoCanjeZonaVerano(int canal, int mercado, String codigosZona) throws ServiceException, DAOException{
    	return vtasYMktgFidelizacionDelegateDAO.getMatrizCatalogoZonaVerano(canal, mercado, codigosZona);
    }
    
    /**
     * 
     * @param codTrx
     * @param canal
     * @param rut
     * @return
     * @throws DAOException
     * @throws ServiceException
     */
    public PuntosBean consultarPuntosPromocion(Integer codTrx, Integer canal, String rut) throws DAOException, ServiceException {
    	return vtasYMktgFidelizacionDelegateDAO.consultarPuntosPromocion(codTrx, canal, rut);
    }

}
