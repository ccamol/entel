/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.esa.clientes.perfilesclientes.ejecutivo.controller;

import java.util.StringTokenizer;

import javax.faces.event.PhaseEvent;

import org.apache.log4j.Logger;

import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.bean.EjecutivoVIPBean;
import com.epcs.bean.GrupoClienteBean;
import com.epcs.cliente.perfil.delegate.EquipoDelegate;
import com.epcs.recursoti.configuracion.jsf.utils.JSFPortletHelper;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;
import com.esa.clientes.perfilesclientes.ejecutivo.delegate.EjecutivoVIPDelegate;

/**
 * @author zmanotas (I2B) 
 */
public class EjecutivoVIPController {

	private static final Logger LOGGER = Logger.getLogger(EjecutivoVIPController.class);	
	private EjecutivoVIPBean datosEjecutivo;
	private EjecutivoVIPDelegate ejecutivoVIPDelegate;
	private EquipoDelegate equipoDelegate;
	
	public void init(PhaseEvent event) {
		String msisdn = "";
		ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());
		
		try {
			msisdn = ProfileWrapperHelper.getPropertyAsString(profileWrapper,
					"numeroPcsSeleccionado");
			String rut = ProfileWrapperHelper.getPropertyAsString(
					profileWrapper, "rutUsuarioSeleccionado").toUpperCase();
			String nroCuenta = ProfileWrapperHelper.getPropertyAsString(
					profileWrapper, "numeroCuenta");
            
			// Grupos permitidos para despliegue de caja ejecutivo VIP
			String gruposPermitidos = JSFPortletHelper.getPreference(
					JSFPortletHelper.getPreferencesObject(), "gruposPermitidos", "");
			
			// Obtener grupo del cliente
			GrupoClienteBean grupoCliente = equipoDelegate.obtenerGrupoCliente(rut, nroCuenta);
            String codigoGrupo = grupoCliente.getCodigoGrupo();
            
            StringTokenizer tokenizer = new StringTokenizer(gruposPermitidos, ",");
            
            while (tokenizer.hasMoreTokens()) {
            	String grupoPermitido = tokenizer.nextToken();
            	if (grupoPermitido.equals(codigoGrupo)) {
            		datosEjecutivo = ejecutivoVIPDelegate.obtenerDatosEjecutivo(msisdn);
            		break;
            	}
            }
		} catch (DAOException e) {          
            LOGGER.error("DAOException al consultar datos de ejecutivo asignado. msisdn: " + msisdn, e);
        } catch (ServiceException e) {
        	LOGGER.error("ServiceException al consultar datos de ejecutivo asignado. msisdn: " + msisdn, e);
        } catch (Exception e) {
        	LOGGER.error("Exception al consultar datos de ejecutivo asignado. msisdn: " + msisdn, e);
        }
	}

	public EjecutivoVIPBean getDatosEjecutivo() {
		return datosEjecutivo;
	}

	public void setDatosEjecutivo(EjecutivoVIPBean datosEjecutivo) {
		this.datosEjecutivo = datosEjecutivo;
	}

	public EjecutivoVIPDelegate getEjecutivoVIPDelegate() {
		return ejecutivoVIPDelegate;
	}

	public void setEjecutivoVIPDelegate(
			EjecutivoVIPDelegate ejecutivoVIPDelegate) {
		this.ejecutivoVIPDelegate = ejecutivoVIPDelegate;
	}

	public EquipoDelegate getEquipoDelegate() {
		return equipoDelegate;
	}

	public void setEquipoDelegate(EquipoDelegate equipoDelegate) {
		this.equipoDelegate = equipoDelegate;
	}

}