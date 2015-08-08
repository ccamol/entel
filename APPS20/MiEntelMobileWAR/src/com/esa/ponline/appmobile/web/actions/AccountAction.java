/* Propiedad de Entel S.A. Todos los derechos reservados */

package com.esa.ponline.appmobile.web.actions;

import java.util.Map;

import org.apache.log4j.Logger;

import com.epcs.billing.balance.types.DetalleFacturaMesType;
import com.esa.ponline.appmobile.exceptions.ServiceException;
import com.esa.ponline.appmobile.exceptions.WSDAOException;
import com.esa.ponline.appmobile.vo.account.AccountVO;
import com.esa.ponline.appmobile.vo.login.LoginVO;
import com.esa.ponline.appmobile.web.delegate.DelegateAccount;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


/**
 * @author ccastro (MZZO) en nombre de Absalon Opazo (Plataformas Online,
 *         EntelSA) version 1.0.0 Sep 11, 2014
 */

public class AccountAction extends ActionSupport {

	private static final long serialVersionUID = 3104712059542803353L;
	
	private static final Logger LOGGER = Logger.getLogger(AccountAction.class);
	
	private DelegateAccount delegateAccount = new DelegateAccount();
	
	private LoginVO datosClientes;

	private AccountVO datosEstadoCuenta;

	
	@Override
	public String execute(){
		datosClientes = (LoginVO) getSession().get("cliente");
		datosEstadoCuenta = new AccountVO();
		try {
			DetalleFacturaMesType detalle = delegateAccount.obtenerEstadoCuenta(datosClientes);
			
			if(detalle.getEstadoMes().equalsIgnoreCase("No Pagado")){
				datosEstadoCuenta.setEstadoMes(detalle.getEstadoMes());
				datosEstadoCuenta.setFechaEmisionMes(detalle.getFechaEmisionMes());
				datosEstadoCuenta.setFechaPeriodoMes(detalle.getFechaPeriodoMes());
				datosEstadoCuenta.setFechaVencimientoMes(detalle.getFechaVencimientoMes());
				datosEstadoCuenta.setSaldoAnteriorMes(detalle.getSaldoAnteriorMes());
				datosEstadoCuenta.setTotalActualMes(detalle.getTotalActualMes());
				datosEstadoCuenta.setTotalPagoMes(detalle.getTotalPagoMes());
				datosEstadoCuenta.setUrlImagenFactura(detalle.getUrlImagenFactura());
				setDatosEstadoCuenta(datosEstadoCuenta);
				return datosClientes.getResultNav() + "_nopago";
			}else if(detalle.getEstadoMes().equalsIgnoreCase("Pagado")){
				datosEstadoCuenta.setEstadoMes(detalle.getEstadoMes());
				datosEstadoCuenta.setFechaPeriodoMes(detalle.getFechaPeriodoMes());
				setDatosEstadoCuenta(datosEstadoCuenta);
				return datosClientes.getResultNav() + "_pagado";
			}else if(detalle.getEstadoMes().equalsIgnoreCase("nok")){
				return "error_general";
			}
			//Nunca debio llegar aca
			LOGGER.error("Flujo no controlado en AccountAction Exception ");			
			return "error_general";
		} catch (WSDAOException e) {
			LOGGER.error("Ha ocurrido un problema en AccountAction WSDAOException " + e.getMessage());
			e.printStackTrace();
			return "error_general";
		} catch (ServiceException e) {
			LOGGER.error("Ha ocurrido un problema en AccountAction ServiceException " + e.getMessage());
			e.printStackTrace();
			return "error_general";
		} catch (Exception e) {
			LOGGER.error("Ha ocurrido un problema en AccountAction Exception " + e.getMessage());
			e.printStackTrace();
			return "error_general";
		}
	}
	
	private Map<String, Object> getSession() {
		Map<String, Object> attibutes = ActionContext.getContext().getSession();
		return attibutes;
	}

	public AccountVO getDatosEstadoCuenta() {
		return datosEstadoCuenta;
	}

	public void setDatosEstadoCuenta(AccountVO datosEstadoCuenta) {
		this.datosEstadoCuenta = datosEstadoCuenta;
	}
	
	public LoginVO getDatosClientes() {
		return datosClientes;
	}

	public void setDatosClientes(LoginVO datosClientes) {
		this.datosClientes = datosClientes;
	}
}
