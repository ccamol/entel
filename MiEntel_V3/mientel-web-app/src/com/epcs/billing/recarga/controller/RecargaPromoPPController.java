/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.billing.recarga.controller;

import java.util.Date;

import javax.faces.event.PhaseEvent;
import javax.servlet.http.HttpServletRequest;

import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.bean.ResumenPlan;
import com.epcs.billing.recarga.delegate.RecargaDelegate;
import com.epcs.cliente.perfil.delegate.OfertaBlindajeDelegate;
import com.epcs.cliente.perfil.delegate.PlanDelegate;
import com.epcs.recursoti.configuracion.DateHelper;
import com.epcs.recursoti.configuracion.JsonHelper;
import com.epcs.recursoti.configuracion.MiEntelBusinessHelper;
import com.epcs.recursoti.configuracion.jsf.utils.JSFPortletHelper;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;

/**
 * Controller para el portlet de promocion recarga PP
 * 
 * @author zmanotas
 * 
 */
public class RecargaPromoPPController {

	private PlanDelegate planDelegate;
	private RecargaDelegate recargaDelegate;
	private OfertaBlindajeDelegate ofertaBlindajeDelegate;
	private String respuestaJson;

	public void init(PhaseEvent event) {
		try {
			HttpServletRequest request = JSFPortletHelper.getRequest();

			ProfileWrapper profileWrapper = ProfileWrapperHelper
					.getProfile(request);

			String msisdn = ProfileWrapperHelper.getPropertyAsString(
					profileWrapper, "numeroPcsSeleccionado");

			/**
			 * Antes de validar los criterios para el despliegue de la promocion, se valida que 
			 * el movil no tenga ninguna oferta vigente de Blindaje y que no se haya marcado
			 * previamente la opcion de "Cerrar y no volver a mostrar".
			 */
			if (ofertaBlindajeDelegate.ConsultarOfertaBlindaje(msisdn).size() == 0) {
				Date fechaInicioPromo = DateHelper.parseDate(request
						.getParameter("fechaInicioPromo"),
						DateHelper.FORMAT_ddMMyyyy_HHmmss_SLASH);
				Date fechaFinPromo = DateHelper.parseDate(request
						.getParameter("fechaFinPromo"),
						DateHelper.FORMAT_ddMMyyyy_HHmmss_SLASH);				
				
				String strInicioPromo = DateHelper.format(fechaInicioPromo,
						DateHelper.FORMAT_ddMMyyyy_HHmmss_SLASH);
				String strFinPromo = DateHelper.format(fechaFinPromo,
						DateHelper.FORMAT_ddMMyyyy_HHmmss_SLASH);
				if (recargaDelegate.validarDesplieguePromo(msisdn, strInicioPromo, strFinPromo)) {

					String mercado = ProfileWrapperHelper.getPropertyAsString(
							profileWrapper, "mercado");
					String flagBam = ProfileWrapperHelper.getPropertyAsString(
							profileWrapper, "flagBam");
					ResumenPlan resumenPlan = planDelegate.getPlanResumenPP(msisdn);

					double saldoMinimo = Double.parseDouble(request
							.getParameter("saldoMinimo"));
					double saldoPlan = resumenPlan.getSaldo();

					/**
					 * Para validar el despliegue de la promocion, se tiene en cuenta:
					 * 
					 * 1- Que sea un usuario PP
					 * 2- El saldo del plan debe ser menor o igual al saldo minimo 
					 * 3- La fecha actual se encuentre entre las fechas de inicio / fin de la promocion 
					 */
					Date fechaActual = new Date();
					if (MiEntelBusinessHelper.isMercadoPrepago(mercado)
							&& MiEntelBusinessHelper.isNotUserBam(flagBam)
							&& (saldoPlan <= saldoMinimo)
							&& (fechaActual.after(fechaInicioPromo) && fechaActual.before(fechaFinPromo))) {
						respuestaJson = JsonHelper.toJsonResponse("");
					} else {
						respuestaJson = JsonHelper.toJsonErrorMessage("");
					}
				}
			}
		} catch (Exception e) {
			respuestaJson = JsonHelper.toJsonErrorMessage("");
		}
	}
	
	public void noMostrarPromo(PhaseEvent event) {
		try {
			HttpServletRequest request = JSFPortletHelper.getRequest();

			ProfileWrapper profileWrapper = ProfileWrapperHelper
					.getProfile(request);

			String msisdn = ProfileWrapperHelper.getPropertyAsString(
					profileWrapper, "numeroPcsSeleccionado");

			recargaDelegate.cancelarDesplieguePromo(msisdn);			
			respuestaJson = JsonHelper.toJsonResponse("");
		} catch (Exception e) {
			respuestaJson = JsonHelper.toJsonErrorMessage("");
		}
	}

	public String getRespuestaJson() {
		return respuestaJson;
	}

	public PlanDelegate getPlanDelegate() {
		return planDelegate;
	}

	public void setPlanDelegate(PlanDelegate planDelegate) {
		this.planDelegate = planDelegate;
	}

	public RecargaDelegate getRecargaDelegate() {
		return recargaDelegate;
	}

	public void setRecargaDelegate(RecargaDelegate recargaDelegate) {
		this.recargaDelegate = recargaDelegate;
	}

	public OfertaBlindajeDelegate getOfertaBlindajeDelegate() {
		return ofertaBlindajeDelegate;
	}

	public void setOfertaBlindajeDelegate(
			OfertaBlindajeDelegate ofertaBlindajeDelegate) {
		this.ofertaBlindajeDelegate = ofertaBlindajeDelegate;
	}	
}