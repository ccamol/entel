/* Propiedad de Entel S.A. Todos los derechos reservados */

package com.esa.ponline.appmobile.web.delegate;

import java.util.List;

import com.epcs.cliente.perfil.types.PlanActualPPType;
import com.epcs.cliente.perfil.types.ResPlanResumenCCType;
import com.epcs.cliente.perfil.types.ResPlanResumenPPType;
import com.esa.billing.billingcons.t.conssaldobonoextra.types.ConsultarSaldoResponseType;
import com.esa.ponline.appmobile.ws.dao.factory.FactoryWSDAO;

/**
 * @author ccastro (MZZO) en nombre de Absalon Opazo (EntelSA)
 * @version 1.0
 */

public class DelegateMenu {

	public List<PlanActualPPType> consultarPlanActualPP(String msisdn) throws Exception {
		return FactoryWSDAO.getMenuDAO().consultarPlanActualPP(msisdn);
	}
	
	public List<ResPlanResumenPPType> consultarPlanResumenPP(String msisdn) throws Exception {
		return FactoryWSDAO.getMenuDAO().consultarPlanResumenPP(msisdn);
	}
	
	public ResPlanResumenCCType consultarPlanResumenCC(String msisdn) throws Exception {
		return FactoryWSDAO.getMenuDAO().consultarPlanResumenCC(msisdn);
	}

    public ConsultarSaldoResponseType consultarSaldoSecundario(String msisdn) throws Exception {
        return FactoryWSDAO.getMenuDAO().consultarSaldoSecundario(msisdn);
    }
}
