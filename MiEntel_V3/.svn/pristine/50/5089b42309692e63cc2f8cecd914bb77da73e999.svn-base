/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.dashboard.mensajes.delegate;

import java.util.Date;

import com.epcs.administracion.suscripciones.dao.SuscripcionesBolsaDAO;
import com.epcs.bean.ConsultarBloqueoDesbloqueoBean;
import com.epcs.bean.ConsultarDetalleLlamadosMPTBean;
import com.epcs.bean.EquiposContratadosYArriendoMPTBean;
import com.epcs.bean.EstadoCuentaMPTBean;
import com.epcs.bean.PlanBean;
import com.epcs.bean.ResumenFacturacionBean;
import com.epcs.bean.RutBean;
import com.epcs.bean.ServicioTecnicoMPTBean;
import com.epcs.billing.balance.dao.FacturacionDAO;
import com.epcs.billing.prodfactura.dao.FacturacionElectronicaDAO;
import com.epcs.cliente.perfil.dao.EquipoDAO;
import com.epcs.cliente.perfil.dao.PlanDAO;
import com.epcs.dashboard.mensajes.dao.ConsultarDetalleLlamadosDAO;
import com.epcs.recursoti.configuracion.DateHelper;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

/**
 * @author zmanotas (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 */
public class MensajesParaTiDelegate {

    private EquipoDAO equipoDAO;
    private ConsultarDetalleLlamadosDAO consultarDetalleLlamadosDAO;
    private FacturacionDAO facturacionDAO;
    private FacturacionElectronicaDAO facturacionElectronicaDAO;
    private SuscripcionesBolsaDAO suscripcionesBolsaDAO;
    private PlanDAO planDAO;

    /**
     * @return the equipoDAO
     */
    public EquipoDAO getEquipoDAO() {
        return equipoDAO;
    }

    /**
     * @param equipoDAO the equipoDAO to set
     */
    public void setEquipoDAO(EquipoDAO equipoDAO) {
        this.equipoDAO = equipoDAO;
    }

    /**
     * @return the consultarDetalleLlamadosDAO
     */
    public ConsultarDetalleLlamadosDAO getConsultarDetalleLlamadosDAO() {
        return consultarDetalleLlamadosDAO;
    }

    /**
     * @param consultarDetalleLlamadosDAO the consultarDetalleLlamadosDAO to set
     */
    public void setConsultarDetalleLlamadosDAO(
            ConsultarDetalleLlamadosDAO consultarDetalleLlamadosDAO) {
        this.consultarDetalleLlamadosDAO = consultarDetalleLlamadosDAO;
    }

    /**
     * @return the facturacionDAO
     */
    public FacturacionDAO getFacturacionDAO() {
        return facturacionDAO;
    }

    /**
     * @param facturacionDAO the facturacionDAO to set
     */
    public void setFacturacionDAO(FacturacionDAO facturacionDAO) {
        this.facturacionDAO = facturacionDAO;
    }

    /**
     * @return the facturacionElectronicaDAO
     */
    public FacturacionElectronicaDAO getFacturacionElectronicaDAO() {
        return facturacionElectronicaDAO;
    }

    /**
     * @param facturacionElectronicaDAO the facturacionElectronicaDAO to set
     */
    public void setFacturacionElectronicaDAO(
            FacturacionElectronicaDAO facturacionElectronicaDAO) {
        this.facturacionElectronicaDAO = facturacionElectronicaDAO;
    }

    /**
     * @return the suscripcionesBolsaDAO
     */
    public SuscripcionesBolsaDAO getSuscripcionesBolsaDAO() {
        return suscripcionesBolsaDAO;
    }

    /**
     * @param suscripcionesBolsaDAO the suscripcionesBolsaDAO to set
     */
    public void setSuscripcionesBolsaDAO(SuscripcionesBolsaDAO suscripcionesBolsaDAO) {
        this.suscripcionesBolsaDAO = suscripcionesBolsaDAO;
    }

    public PlanDAO getPlanDAO() {
		return planDAO;
	}

	public void setPlanDAO(PlanDAO planDAO) {
		this.planDAO = planDAO;
	}

    public boolean validarBloqueo(String numeroPcs, String mercado , String DVImei) throws DAOException,
            ServiceException {
        return equipoDAO.tieneBloqueoExistente(numeroPcs,mercado,DVImei);
    }
    

    public boolean consultarBolsasPlanes(String mercado, String msisdn)
            throws DAOException, ServiceException {
        return suscripcionesBolsaDAO.consultarBolsasPlanes(mercado, msisdn);
    }

    public EquiposContratadosYArriendoMPTBean consultarEquiposContratadosYArriendo(
            String msisdn, RutBean rut, String nroCuenta) throws DAOException, ServiceException {
        return equipoDAO.consultarEquiposContratadosYArriendo(msisdn,
                rut, nroCuenta);
    }

    public EstadoCuentaMPTBean consultarEstadoCuenta(String msisdn,
            String rutTitular) throws DAOException, ServiceException {
        return facturacionDAO.consultarEstadoCuenta(msisdn, rutTitular);
    }

    public String consultarFacturaElectronica(String rut, String nroCuenta, String mercado)
            throws DAOException, ServiceException {
        return facturacionElectronicaDAO.consultarFacturaElectronica(rut, nroCuenta, mercado);
    }

    public boolean consultarInternetMovilContratado(String msisdn)
            throws DAOException, ServiceException {
        return suscripcionesBolsaDAO.consultarInternetMovilContratado(msisdn);
    }
    
    public ConsultarDetalleLlamadosMPTBean consultarDetalleLlamados(
            String msisdn, String aaa, String rut, String numeroCuenta)
            throws DAOException, ServiceException, Exception {

    	//Se indica al metodo que NO REGISTRE en el log de la caja de Facturacion del Dashboard 
    	boolean logCajaFacturacion = false;
        ResumenFacturacionBean resumenFact = facturacionDAO
                .getResumenFacturacion(msisdn, rut, numeroCuenta, logCajaFacturacion);
        String folio = resumenFact.getFolio();
        Date fechaPeriodo = resumenFact.getFechaPeriodo();

        Boolean consultado = consultarDetalleLlamadosDAO.isConsultado(
                numeroCuenta, msisdn, aaa, folio);

        ConsultarDetalleLlamadosMPTBean bean = new ConsultarDetalleLlamadosMPTBean();

        if (consultado == Boolean.FALSE) {
            // Periodo en letras
            int anno = Integer
                    .parseInt(DateHelper.format(fechaPeriodo, "yyyy"));
            int mes = Integer.parseInt(DateHelper.format(fechaPeriodo, "MM"));
            int ultimoDiaMes = DateHelper.ultimoDiaMes(anno, mes);
            String periodoLetras = DateHelper.format(fechaPeriodo, " '01 al' "
                    + ultimoDiaMes + " 'de' MMMM 'de' yyyy");
            bean.setPeriodo(periodoLetras);
        }
        
        bean.setConsultado(consultado);
        return bean;
    }
    
    public ServicioTecnicoMPTBean consultarServicioTecnico(String msisdn)
            throws DAOException, ServiceException {
        return equipoDAO.consultarServicioTecnico(msisdn);
    }
    
    public boolean consultarBolsasPlanesBAM(String msisdn)
    throws DAOException, ServiceException {
        return suscripcionesBolsaDAO.consultarBolsasPlanesBAM(msisdn);
    }
    
    public ConsultarBloqueoDesbloqueoBean getBloqueoDesbloqueo(String msisdn, String mercado, String imeiDV) throws DAOException, ServiceException {
    	return equipoDAO.getConsultarBloqueoDesbloqueo(msisdn, mercado, imeiDV);
    }
    
    public PlanBean getPlanActual(String msisdn, String mercado, String AAA) throws DAOException, ServiceException {
    	return planDAO.obtenerPlanActualSSCC(msisdn, mercado, AAA);
    }
}
