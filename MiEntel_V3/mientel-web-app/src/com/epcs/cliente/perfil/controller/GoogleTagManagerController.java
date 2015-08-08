/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.cliente.perfil.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.epcs.bean.ProfileWrapperBean;
import com.epcs.cliente.orden.dao.TarifaDiariaDAO;
import com.epcs.nba.NBAController;
import com.epcs.recursoti.configuracion.MiEntelBusinessHelper;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.Utils;
import com.epcs.recursoti.configuracion.jsf.utils.JSFPortletHelper;

/**
 * @author zmanotas (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class GoogleTagManagerController {
	private static final Logger LOGGER = Logger.getLogger(GoogleTagManagerController.class);
	private static final long serialVersionUID = 1L;

	private ProfileWrapperBean getProfileBean() {
		ProfileWrapperBean profileBean = (ProfileWrapperBean) JSFPortletHelper
				.getFacesBeanInstance("profile");
		return profileBean;
	}
	
	public String getMxContent() {
		try {
			String nombreLogico = JSFPortletHelper.getPreference(JSFPortletHelper.getPreferencesObject(), "nombreLogico", "");
			return getMxContentVar(nombreLogico);
		} catch (Exception e) {
			return "";
		}
	}

	public String getMxSeg1() {
		try {
			String mercado = getProfileBean().getMercado() != null ? getProfileBean().getMercado() : "";
			String flagBam = getProfileBean().getFlagBam() != null ? getProfileBean().getFlagBam() : "";
			String segmento = MiEntelBusinessHelper.isUserBam(flagBam) ? "BAM" : "VOZ";
			
			if (!mercado.trim().isEmpty() && !segmento.trim().isEmpty()) {
				return getMxSegVar(1, mercado + "_" + segmento);
			}
			return "";			
		} catch (Exception e) {
			return "";
		}
	}

	public String getMxSeg2() {
		try {
			String aaa = getProfileBean().getAaa() != null ? getProfileBean().getAaa() : "";
			
			if (!aaa.trim().isEmpty()) {
				return getMxSegVar(2, "AAA" + aaa);
			}
			return "";
		} catch (Exception e) {
			return "";
		}
	}

	public String getMxSeg3() {
		try {
			HttpServletRequest request = (HttpServletRequest) JSFPortletHelper.getRequest();
			if (request.getParameter("numeroPcs") != null) {
				String numeroPcs = request.getParameter("numeroPcs");
    			String llaveEncriptacion = MiEntelProperties.getProperty("miEntel.userProfile.llaveEncriptacion");
				return getMxSegVar(3, Utils.encriptar(numeroPcs, llaveEncriptacion));
			} else {
				return getMxSegVar(3, getProfileBean().getNumeroPcsEncriptado());
			}
		} catch (Exception e) {
			return "";
		}
	}

	public String getMxSeg5() {
		try {
			return getMxSegVar(5, getProfileBean().getCategoriaCliente());
		} catch (Exception e) {
			return "";
		}
	}

	public String getMxSeg7() {
		try {
			NBAController nbaController = (NBAController) JSFPortletHelper
					.getFacesBeanInstance("nbaController");
			String mxSeg7 = "";			
			
			if (nbaController.isTieneOfertasPorMovil(getProfileBean().getNumeroPcsSeleccionado())) {
				mxSeg7 = getMxSegVar(7, "CVM");
			}
	
			return mxSeg7;
		} catch (Exception e) {
			LOGGER.error(e);
			return "";
		}
	}

	public String getMxSeg8() {
		HttpServletRequest request = (HttpServletRequest) JSFPortletHelper.getRequest();
		String contexto = request.getParameter("contexto") != null ? request.getParameter("contexto") : "";
		String flagCautivo = request.getParameter("flagCautivo") != null ? request.getParameter("flagCautivo"): "";
		String value = flagCautivo.equals("1") ? "Cautivo_" + contexto : !contexto.trim().equals("") ? "Contexto_" + contexto : "";

		return getMxSegVar(8, value);
	}

	public String getMxSeg9() {
		try {
			MisUsuariosController misUsuariosController = (MisUsuariosController) JSFPortletHelper
					.getFacesBeanInstance("misUsuariosController");
			String mxSeg9 = "";
	
			if (misUsuariosController.isCuentaMultilinea()) {
				mxSeg9 = getMxSegVar(9, "ML");
			}
			
			return mxSeg9;
		} catch (Exception e) {
			return "";
		}
	}
	
	private String getMxContentVar(String value) {
		String mxContent = "";

		if (!value.trim().equals("")) {
			mxContent = "var mx_content = '" + value + "';";
		}

		return mxContent;
	}

	private String getMxSegVar(int id, String value) {
		String mxVar = "";

		if (!value.trim().equals("")) {
			mxVar = "var mxSeg" + id + " = '" + value + "';";
		}

		return mxVar;
	}
}
