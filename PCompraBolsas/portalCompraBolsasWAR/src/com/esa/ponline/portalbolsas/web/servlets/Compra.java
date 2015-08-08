package com.esa.ponline.portalbolsas.web.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epcs.provision.suscripcion.bolsaspp.types.ComprarResponseType.Mensaje;
import com.esa.ponline.portalbolsas.bean.BolsaComprar;
import com.esa.ponline.portalbolsas.bean.DetalleBolsaCliente;
import com.esa.ponline.portalbolsas.bean.Movil;
import com.esa.ponline.portalbolsas.configuration.properties.LoadProperty;
import com.esa.ponline.portalbolsas.ws.scobb.bolsas.dao.delegate.DelegateBolsa;
import com.esa.ponline.portalbolsas.ws.scobb.bolsas.dao.imp.helper.BolsaHelper;

/**
 * @author ccastro
 *
 */

public class Compra extends Init {

    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = Logger.getLogger(Compra.class);
    
    private BolsaHelper bolsaHelper = new BolsaHelper();
    
    private DelegateBolsa delegateBolsa;
    
    private BolsaComprar bolsaDetalle;
    
    private String msgCompraNOK;
    
    private String msgCompraOK;
    
    private String sinSaldoMsg;
    
    private String saldo;
    
    private String publicidad;
    
    
    private void procesoCompra(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(false);
        Movil movilCliente = (Movil) session.getAttribute("movil");
		@SuppressWarnings("unchecked")
		ArrayList<DetalleBolsaCliente> detalleBolsasCliente = (ArrayList<DetalleBolsaCliente>) session.getAttribute("bolsasCliente");        
        
        String codigoCarta = req.getParameter("confirmar");
        String cobro = "";
        
        LOGGER.info("inicio proceso compra para: [" + movilCliente.getMsisdn() + "]");
        LOGGER.info("Bolsa a comprar (codigo): " + codigoCarta + "]");
        
        delegateBolsa = new DelegateBolsa();
        
        try {
            Mensaje mensaje = delegateBolsa.comprarBolsa(movilCliente.getMsisdn(), codigoCarta, cobro);
            
            if (mensaje.getCodigo().equals("200")) {
                DelegateBolsa.cacheBolsaDisponibleMM.invalidateAll();
				detalleBolsasCliente = delegateBolsa.listarBolsas(movilCliente.getMsisdn());
            	
                setMsgCompraOK(mensaje.getDescripcion());
                setPublicidad(mensaje.getPublicidad());
    	        req.setAttribute("msgCompraOK", this.msgCompraOK);
    	        req.setAttribute("publicidad", this.publicidad);
                req.setAttribute("bolsasCliente", detalleBolsasCliente);    	        
                
                LOGGER.info("COMPRA DE BOLSA EXITOSA");
                LOGGER.info("CODIGO RESPONSE: " + mensaje.getCodigo());
                LOGGER.info("DESCRIPCION RESPONSE: " + mensaje.getDescripcion());
                LOGGER.info("PUBLICIDAD RESPONSE: " + mensaje.getPublicidad());
                
                req.getRequestDispatcher("/jsp/bolsas-mm-bolsaCompra-ok.jsp").forward(req, resp);
                return;
            } else if (mensaje.getDescripcion() != null) {
                LOGGER.warn("Fracaso en compra de bolsa");
                
                setMsgCompraNOK(mensaje.getDescripcion());
    	        req.setAttribute("msgCompraNOK", this.msgCompraNOK);
    	        
                LOGGER.warn("FRACASO EN COMPRA DE BOLSA");
                LOGGER.warn("CODIGO RESPONSE: " + mensaje.getCodigo());
                LOGGER.warn("DESCRIPCION RESPONSE: " + mensaje.getDescripcion());
                LOGGER.warn("PUBLICIDAD RESPONSE: " + mensaje.getPublicidad());
                req.getRequestDispatcher("/jsp/bolsas-mm-bolsaCompra-ok.jsp").forward(req, resp);
                return;
            }
        } catch (Exception e) {
            LOGGER.error("ServiceException lanzada al realizar transaccion para comprar bolsa: " + e);
            e.printStackTrace();
        }
    }
   
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	if(idSessionLog(req, resp).equals("NOK")){
    		return;
    	} else {
        	idSessionLog(req, resp);
    	}
    	String respuesta = "";
    	
    	try {
			respuesta = getDetalleSeleccionBolsaMM(req);
			
			if (respuesta.equalsIgnoreCase("OK")){
				
				req.setAttribute("bolsaDetalle", bolsaDetalle);
				req.setAttribute("saldo", obtenerSaldoCliente(req, bolsaDetalle));				
				req.setAttribute("sinSaldoMsg", getSinSaldoMsg());
				req.setAttribute("valorBolsa", bolsaDetalle.getPrecioInt());

		        HttpSession session = req.getSession();
		        session.setAttribute("bolsaDetalle", bolsaDetalle);
			}
		} catch (Exception e) {
			LOGGER.error("Error lanzado debido a: " + e.getMessage());
			e.printStackTrace();
		}
    	
        req.getRequestDispatcher("/jsp/2.11.2b-bolsas-detalle.jsp").forward(req, resp);
    }
    
    private String obtenerSaldoCliente(HttpServletRequest req, BolsaComprar bolsaDetalle){
    	setSinSaldoMsg("");
        HttpSession session = req.getSession(false);
        //TODO a modo prueba se agrega saldo en duro
//        String saldo = "600";
        //TODO linea que aplica
        String saldo = (String) session.getAttribute("saldo");    	
    	if(Integer.parseInt(bolsaDetalle.getPrecioInt()) > Integer.parseInt(saldo)){
    		LOGGER.info("Cliente NO posee saldo a favor para comprar esta bolsa [CARTA]: " + bolsaDetalle.getCodigo());
    		LOGGER.info("bolsaValor: " + bolsaDetalle.getPrecioInt());
    		LOGGER.info("saldoCliente: " + Integer.parseInt(saldo));  
    		
        	setSinSaldoMsg(LoadProperty.getProperty("sinSaldo2"));
    	}

    	setSaldo(saldo);
        
    	return getSaldo();
    }

	private String getDetalleSeleccionBolsaMM(HttpServletRequest req) throws Exception {
		String respuesta = "";
        HttpSession session = req.getSession(false);
        String msisdn = (String) session.getAttribute("msisdn");
        String codigo = req.getParameter("pcbcarta");
		LOGGER.info("Se obtiene detalle de seleccion de bolsa para carta: [" + codigo + "]");
        
        try {
            setBolsaDetalle(new BolsaComprar());
            setBolsaDetalle(bolsaHelper.getBolsaDescripcionDetalleMM(codigo, msisdn));
            respuesta = "OK";
		} catch (Exception e) {
			LOGGER.error("Error lanzado debido a: " + e.getMessage());
			e.printStackTrace();
		}
        
        return respuesta;
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	if(idSessionLog(req, resp).equals("NOK")){
    		return;
    	} else {
        	idSessionLog(req, resp);
    	}
        procesoCompra(req, resp);
    }
    
	/**
	 * @return the bolsaDetalle
	 */
	public BolsaComprar getBolsaDetalle() {
		return bolsaDetalle;
	}

	/**
	 * @param bolsaDetalle the bolsaDetalle to set
	 */
	public void setBolsaDetalle(BolsaComprar bolsaDetalle) {
		this.bolsaDetalle = bolsaDetalle;
	}


	/**
	 * @return the msgCompraNOK
	 */
	public String getMsgCompraNOK() {
		return msgCompraNOK;
	}


	/**
	 * @param msgCompraNOK the msgCompraNOK to set
	 */
	public void setMsgCompraNOK(String msgCompraNOK) {
		this.msgCompraNOK = msgCompraNOK;
	}


	/**
	 * @return the msgCompraOK
	 */
	public String getMsgCompraOK() {
		return msgCompraOK;
	}


	/**
	 * @param msgCompraOK the msgCompraOK to set
	 */
	public void setMsgCompraOK(String msgCompraOK) {
		this.msgCompraOK = msgCompraOK;
	}


	/**
	 * @return the publicidad
	 */
	public String getPublicidad() {
		return publicidad;
	}


	/**
	 * @param publicidad the publicidad to set
	 */
	public void setPublicidad(String publicidad) {
		this.publicidad = publicidad;
	}


	/**
	 * @return the sinSaldoMsg
	 */
	public String getSinSaldoMsg() {
		return sinSaldoMsg;
	}


	/**
	 * @param sinSaldoMsg the sinSaldoMsg to set
	 */
	public void setSinSaldoMsg(String sinSaldoMsg) {
		this.sinSaldoMsg = sinSaldoMsg;
	}


	/**
	 * @return the saldo
	 */
	public String getSaldo() {
		return saldo;
	}


	/**
	 * @param saldo the saldo to set
	 */
	public void setSaldo(String saldo) {
		this.saldo = saldo;
	}
	
}
