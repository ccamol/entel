package com.esa.ponline.portalbolsas.web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.esa.ponline.portalbolsas.bean.Movil;
import com.esa.ponline.portalbolsas.util.PCBUtils;
import com.esa.ponline.portalbolsas.ws.recarga.dao.delegate.RecargaDelegate;
import com.esa.ponline.portalbolsas.ws.recarga.dao.imp.RecargaController;

public class Recarga extends Init {

    private static final long serialVersionUID = 1L;
    
    private static final Logger LOGGER = Logger.getLogger(Recarga.class);
    
    RecargaDelegate recargaDelegate = new RecargaDelegate();
    
    private void processRequest(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.info("Se procesa peticion para flujo webPay");
        RecargaController recargaController = new RecargaController();
        
        recargaController.solicitarRecargaTarjetaCredito(req);
    
        LOGGER.info("1.- UrlFormAction: " + recargaController.getPagoWebPay().getUrlFormAction());
        LOGGER.info("2.- TBK_TIPO_TRANSACCION: " + recargaController.getPagoWebPay().getTipoTransaccion());
        LOGGER.info("3.- TBK_MONTO: " + recargaController.getRecargaWebPay().getTBKMontoRecarga());
        LOGGER.info("4.- TBK_ORDEN_COMPRA: " + recargaController.getRecargaWebPay().getOrdenCompra());
        LOGGER.info("5.- TBK_ID_SESION: " + recargaController.getTbkIdSesion());
        LOGGER.info("6.- TBK_URL_RESULTADO: " + recargaController.getPagoWebPay().getUrlResultado()+"&ordenCompra="+recargaController.getRecargaWebPay().getOrdenCompra());
        LOGGER.info("7.- TBK_URL_FRACASO: " + recargaController.getPagoWebPay().getUrlFracaso()+"&ordenCompra="+recargaController.getRecargaWebPay().getOrdenCompra());
        LOGGER.info("8.- TBK_NUM_TRX: " + recargaController.getPagoWebPay().getNumTrx());
        LOGGER.info("9.- TBK_CODIGO_TIENDA_M001: " + recargaController.getPagoWebPay().getCodigoTienda());
        LOGGER.info("10.- TBK_ORDEN_TIENDA_M001: " + recargaController.getRecargaWebPay().getOrdenCompra());
        LOGGER.info("11.- TBK_MONTO_TIENDA_M001: " + recargaController.getRecargaWebPay().getTBKMontoRecarga());

        
        req.setAttribute("recargaController", recargaController);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	if(idSessionLog(req, resp).equals("NOK")){
    		return;
    	} else {
        	idSessionLog(req, resp);
    	}
    	cleanAllMessages(req);
        String monto = req.getParameter("monto");
        processRequest(req, resp);
        HttpSession session = req.getSession(false);
        String msisdn = (String) session.getAttribute("msisdn");
        LOGGER.info("MSISDN a recargar del usuario: " + msisdn);
        LOGGER.info("monto a recargar: " + monto);
        
        String contexto = req.getContextPath();
        LOGGER.info("contexto: " + contexto);
        
        LOGGER.info("testing doGet from Recarga servlet");
        
        req.setAttribute("msisdn", msisdn);
        req.setAttribute("monto", PCBUtils.formatMoneyPuntos(Double.valueOf(monto)));

        req.getRequestDispatcher("/jsp/bolsas-mm-wp-monto-3.jsp").forward(req, resp);
    }
    
	private void cleanAllMessages(HttpServletRequest req) {
        setRecFailMsg("");
        LOGGER.info("recFailMsg: " + this.recFailMsg);
        req.setAttribute("recFailMsg", this.recFailMsg);
	}
}