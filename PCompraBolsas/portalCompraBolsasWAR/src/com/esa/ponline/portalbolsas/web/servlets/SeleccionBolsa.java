/**
 * 
 */
package com.esa.ponline.portalbolsas.web.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.esa.ponline.portalbolsas.bean.DetalleBolsaCliente;
import com.esa.ponline.portalbolsas.bean.Movil;
import com.esa.ponline.portalbolsas.configuration.properties.LoadProperty;

/**
 * @author ccastro
 *
 */
public class SeleccionBolsa extends Init {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(SeleccionBolsa.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		LOGGER.info("consultando detalle de bolsas");
        HttpSession session = req.getSession(false);
        @SuppressWarnings("unchecked")
		ArrayList<DetalleBolsaCliente> 
        	detalleBolsasCliente = 
        		(ArrayList<DetalleBolsaCliente>) session.getAttribute("bolsasCliente");  
        
        setSinSaldoSuficienteMsg(LoadProperty.getProperty("sinSaldo"));

        req.setAttribute("saldoInsuficiente", getSinSaldoSuficienteMsg());        
        req.setAttribute("bolsasCliente", detalleBolsasCliente); 

		req.getRequestDispatcher("/jsp/seleccionBolsas.jsp").forward(req, resp);

	}
}
