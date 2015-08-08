/* Propiedad de Entel S.A. Todos los derechos reservados */

package com.esa.ponline.appmobile.ws.dao;

import java.util.List;

import com.epcs.cliente.perfil.types.PlanActualPPType;
import com.epcs.cliente.perfil.types.ResPlanResumenCCType;
import com.epcs.cliente.perfil.types.ResPlanResumenPPType;
import com.esa.billing.billingcons.t.conssaldobonoextra.types.ConsultarSaldoResponseType;
import com.esa.ponline.appmobile.exceptions.AppMobileException;

/**
 * @author ccastro (MZZO) en nombre de Absalon Opazo (EntelSA)
 * @version 1.0
 */

public interface IMenuDAO {

	public List<PlanActualPPType> consultarPlanActualPP(String msisdn) throws AppMobileException;
	public List<ResPlanResumenPPType> consultarPlanResumenPP(String msisdn) throws AppMobileException;
	public ResPlanResumenPPType consultaSaldoPP(String msisdn) throws AppMobileException;
	public ResPlanResumenCCType consultarPlanResumenCC(String msisdn) throws AppMobileException;
    public ConsultarSaldoResponseType consultarSaldoSecundario(String msisdn) throws AppMobileException;;
}
