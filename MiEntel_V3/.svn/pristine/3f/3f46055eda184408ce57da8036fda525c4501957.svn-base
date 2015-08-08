/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.cliente.orden.delegate;

import java.util.Iterator;
import java.util.List;

import com.epcs.administracion.suscripciones.dao.SuscripcionesBolsaDAO;
import com.epcs.bean.BolsaBean;
import com.epcs.bean.BolsaPPBean;
import com.epcs.bean.BolsasActualesDisponiblesBean;
import com.epcs.bean.ResultadoContratarBolsaBean;
import com.epcs.bean.ResumenPlan;
import com.epcs.cliente.orden.dao.BolsaDAO;
import com.epcs.cliente.perfil.dao.PlanDAO;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.Utils;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

/**
 * @author jmanzur (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class InternetMovilDelegate {
    
    private SuscripcionesBolsaDAO suscripcionesBolsaDAO;
    private PlanDAO planPAO = new PlanDAO();
    private BolsaDAO bolsaDAO;
    
    private static final String BOLSAS_BAM = MiEntelProperties
    .getProperty("parametros.bolsas.im.id");

    private static final String BOLSAS_BAM_PP = MiEntelProperties
    .getProperty("parametros.bolsas.impp.id");
        
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
    /**
     * @return the bolsaDAO
     */
    public BolsaDAO getBolsaDAO() {
        return bolsaDAO;
    }
    /**
     * @param bolsaDAO the bolsaDAO to set
     */
    public void setBolsaDAO(BolsaDAO bolsaDAO) {
        this.bolsaDAO = bolsaDAO;
    }
    
    /**
     * Consultar las bolsas actuales y disponibles de un usuario ss
     * 
     * @param msisdn
     * @return BolsasActualesDisponiblesBean
     * @throws DAOException
     * @throws ServiceException
     */
    public BolsasActualesDisponiblesBean consultarBolsasActDisponiblesBAM(String msisdn) throws DAOException, ServiceException{
        BolsasActualesDisponiblesBean bolsasActyDisp = suscripcionesBolsaDAO.consultarBolsasActualesDisponibles(msisdn);
        Iterator<BolsaBean> itBolsa = bolsasActyDisp.getBolsasActuales().iterator();
        while(itBolsa.hasNext()){
            BolsaBean bolsa = itBolsa.next();
            if(!BOLSAS_BAM.contains(bolsa.getTipoBolsa())){
                itBolsa.remove();
            }
        }
        itBolsa = bolsasActyDisp.getBolsasDisponibles().iterator();
        while(itBolsa.hasNext()){
            BolsaBean bolsa = itBolsa.next();
            if(!BOLSAS_BAM.contains(bolsa.getTipoBolsa())){
                itBolsa.remove();
            }
        }
        return bolsasActyDisp;
    }
    
    /**
     * Contratar una Bolsa para un usuario SS o CC
     * 
     * @param msisdn
     * @param codBolsa
     * @param valorBolsa
     * @param opcionActivacion
     * @throws DAOException
     * @throws ServiceException
     */
    public ResultadoContratarBolsaBean contratarBolsaSSCC(String msisdn,String codBolsa,Double valorBolsa, String opcionActivacion) throws DAOException, ServiceException{
         return this.bolsaDAO.contratarBolsaSSCC(msisdn, codBolsa, valorBolsa, opcionActivacion);   
    }
    
    /**
     * Listado de bolsas Disponibles para regalo
     * 
     * @param msisdn
     * @return
     * @throws DAOException
     * @throws ServiceException
     */
    public List<BolsaBean> consultarBolsasDisponiblesRegaloBAM(String msisdn, String mercado) throws DAOException, ServiceException{
        List<BolsaBean> listBolsas = this.suscripcionesBolsaDAO.consultarBolsasDisponiblesRegalo(msisdn, mercado);
        Iterator<BolsaBean> itBolsa = listBolsas.iterator();
        while(itBolsa.hasNext()){
            BolsaBean bolsa = itBolsa.next();
            if(Utils.isEmptyString(bolsa.getTipoBolsa()) || !BOLSAS_BAM_PP.contains(bolsa.getTipoBolsa())){
                itBolsa.remove();
            }
        }
        return listBolsas;
    }
    
    /**
     * Obtiene bolsas compradas y regaladas
     * 
     * @param msisdn
     * @return List<BolsaBean>
     * @throws DAOException
     * @throws ServiceException
     */
    public List<BolsaPPBean> consultarPlanesActualesBAMPP(String msisdn) throws DAOException, ServiceException{
        List<BolsaPPBean> listBolsas = this.bolsaDAO.obtenerBolsasOneShot(msisdn);
        Iterator<BolsaPPBean> itBolsa = listBolsas.iterator();
        while(itBolsa.hasNext()){
            BolsaPPBean bolsa = itBolsa.next();
            if(Utils.isEmptyString(bolsa.getTipoBolsa()) || !BOLSAS_BAM_PP.contains(bolsa.getTipoBolsa())){
                itBolsa.remove();
            }
        }
        return listBolsas;
    }
    
    /**
     * Comprar una bolsa para un usuario pp
     * 
     * @param msisdn
     * @param cartaServicio
     * @param valorBolsa
     * @throws DAOException
     * @throws ServiceException
     */
    public void comprarBolsasOneShotPP(String msisdn,String cartaServicio, Double valorBolsa) throws DAOException, ServiceException{
        this.bolsaDAO.comprarBolsasOneShotPP(msisdn, cartaServicio, valorBolsa);
    }
    
    /**
     * Obtiene Resumen del Plan de un Uusario PP
     * 
     * @param msisdn
     * @return ResumenPlan
     * @throws DAOException
     * @throws ServiceException
     */
    public ResumenPlan obtenerResumenPlanPP(String msisdn) throws DAOException, ServiceException{
        return planPAO.getPlanResumenPP(msisdn);
    }

}
