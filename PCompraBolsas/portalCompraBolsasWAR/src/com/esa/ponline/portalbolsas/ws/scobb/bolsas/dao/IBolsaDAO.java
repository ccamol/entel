package com.esa.ponline.portalbolsas.ws.scobb.bolsas.dao;


import java.util.ArrayList;

import com.epcs.provision.suscripcion.bolsaspp.types.ComprarResponseType;
import com.esa.ponline.portalbolsas.bean.BolsaComprada;
import com.esa.ponline.portalbolsas.bean.BolsaDisponibleMM;
import com.esa.ponline.portalbolsas.bean.DetalleBolsaCliente;
import com.esa.ponline.portalbolsas.bean.Movil;
import com.esa.ponline.portalbolsas.exceptions.PortalBolsasException;
import com.esa.ponline.portalbolsas.exceptions.ws.ServiceException;
import com.esa.ponline.portalbolsas.exceptions.ws.WSDAOException;

/**
 * @author ccastro
 *
 */

public interface IBolsaDAO {
    
    ComprarResponseType.Mensaje comprarBolsa(String msisdn, String codigo, String cobro) throws PortalBolsasException, ServiceException, WSDAOException;
    
    ArrayList<DetalleBolsaCliente> listarBolsas(String msisdn) throws PortalBolsasException, ServiceException, WSDAOException;
    
    ArrayList<BolsaComprada> listarBolsasActivas(String msisdn) throws PortalBolsasException, ServiceException, WSDAOException;

    Movil desplegarInfoCliente(String msisdn) throws PortalBolsasException, ServiceException, WSDAOException;
    
    BolsaDisponibleMM getBolsaSeleccionDetalleMM(String msisdn) throws WSDAOException, ServiceException;
    
}
