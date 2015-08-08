/* Propiedad de Entel S.A. Todos los derechos reservados */

package com.esa.ponline.appmobile.ws.dao;

import java.util.ArrayList;

import com.epcs.cliente.orden.types.ResultadoContrataBolsaSSCCType;
import com.epcs.provision.suscripcion.bolsasppmovil.types.ComprarResponseType.Mensaje;
import com.esa.ponline.appmobile.exceptions.AppMobileException;
import com.esa.ponline.appmobile.exceptions.ServiceException;
import com.esa.ponline.appmobile.exceptions.WSDAOException;
import com.esa.ponline.appmobile.vo.Bundle.AvailableBundleBAMCCPP;
import com.esa.ponline.appmobile.vo.Bundle.AvailableBundleBAMSSCC;
import com.esa.ponline.appmobile.vo.Bundle.PurchasedPPBundle;

/**
 * @author ccastro (MZZO) en nombre de Absalon Opazo (Plataformas Online, EntelSA)
 * version 1.0.0
 * Aug 12, 2014
 */

public interface IBundleDAO {

    public AvailableBundleBAMCCPP getAllAvailablePPBundles(String msisdn) throws AppMobileException, ServiceException, WSDAOException;
    public AvailableBundleBAMSSCC getAllAvailableSSCCBundles(String msisdn) throws AppMobileException, ServiceException, WSDAOException;
    
    
//    public List<PurchasedPPBundle> consultarBolsasCompradasPPBAM(String msisdn) throws AppMobileException, ServiceException, WSDAOException;
    public ArrayList<PurchasedPPBundle> obtenerSaldoBolsasPP(String msisdn) throws AppMobileException, ServiceException, WSDAOException;
    public AvailableBundleBAMSSCC getActualBundleAvailableSSCC(String msisdn) throws AppMobileException, ServiceException, WSDAOException;
    public Mensaje comprarBolsa(String msisdn, String codBolsa, String tipoCobro) throws WSDAOException, ServiceException;
	public ResultadoContrataBolsaSSCCType contratarBolsaSSCC(String msisdn, String codBolsa, double valorBolsa, String opcionActivacion) throws WSDAOException, ServiceException;
    public AvailableBundleBAMSSCC getBundleAvailableSSCC(String msisdn) throws AppMobileException, ServiceException, WSDAOException;
	
}
