package com.esa.ponline.portalbolsas.web.servlets;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;

import com.esa.ponline.portalbolsas.bean.DetalleBolsaCliente;
import com.esa.ponline.portalbolsas.bean.MontoRecargaBean;
import com.esa.ponline.portalbolsas.bean.Movil;
import com.esa.ponline.portalbolsas.bean.RecargaWebPayBean;
import com.esa.ponline.portalbolsas.configuration.DateHelper;
import com.esa.ponline.portalbolsas.configuration.properties.LoadProperty;
import com.esa.ponline.portalbolsas.exceptions.dao.DAOException;
import com.esa.ponline.portalbolsas.exceptions.ws.ServiceException;
import com.esa.ponline.portalbolsas.exceptions.ws.WSDAOException;
import com.esa.ponline.portalbolsas.util.PCBUtils;
import com.esa.ponline.portalbolsas.ws.recarga.dao.delegate.RecargaDelegate;
import com.esa.ponline.portalbolsas.ws.recarga.test.TestWebpay;
import com.esa.ponline.portalbolsas.ws.scobb.bolsas.dao.delegate.DelegateBolsa;


/**
 * Servlet que maneja las llamadas de cierre de WebPay
 * 
 * Servlet implementation class CierreWebPayServlet
 */
@SuppressWarnings("unused")
public class CierreWP extends Init {
	
    private static final long serialVersionUID = 1L; 

    private static final String TBK_ORDEN_COMPRA = "TBK_ORDEN_COMPRA";

    private static final String CONTENT_TYPE = "text/html;charset=UTF-8";

    private static final String EXIT_OK = "ACEPTADO";

    private static final String EXIT_ERROR = "RECHAZADO";
    
    private static final String TBK_APPROVED_REG_EXP = LoadProperty.getProperty("txAbrobada").toLowerCase();
    
    private RecargaDelegate delegate = new RecargaDelegate();
    
    private DelegateBolsa delegateBolsa = new DelegateBolsa();
    
    
    /**
     * Logger para CierreWebPayServlet
     */
    private static final Logger LOGGER = Logger.getLogger(CierreWP.class);

    /**
     * @see Servlet#init(ServletConfig)
     */
//    @Override
//    public void init(ServletConfig config) throws ServletException {
//        LOGGER.info("servlet init");
//        this.delegate = new RecargaDelegate();
//    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
	protected void doGet(HttpServletRequest req,
            HttpServletResponse resp) throws ServletException, IOException {
    	if(idSessionLog(req, resp).equals("NOK")){
    		return;
    	} else {
        	idSessionLog(req, resp);
    	}
    	//TODO linea que aplica para prod, se simula oreden de compra valida en duro para obtener la respuesta de tx exitosa (0) o fracasada -1 y otras
        String ordenCompra = req.getParameter("ordenCompra");
//    	String ordenCompra = "1433953141570";
    	
        //TODO parametro EXITO - FRACASO ??
        String resultado = req.getParameter("RESULTADO");
        LOGGER.info("TRANSBANK RESPONDE CON EL PARAM [: " + resultado + "]");
        
        HttpSession session = req.getSession(false);
        Movil movilCliente = (Movil) session.getAttribute("movil");
		@SuppressWarnings("unchecked")
		ArrayList<DetalleBolsaCliente> detalleBolsasCliente = (ArrayList<DetalleBolsaCliente>) session.getAttribute("bolsasCliente");
		
		@SuppressWarnings("unchecked")
		List<MontoRecargaBean> montosWebpay = (List<MontoRecargaBean>) session.getAttribute("montosWebpay");
        
        LOGGER.info("movilCliente.msisdn: " + movilCliente.getMsisdn());
        LOGGER.info("movilCliente.mercado: " + movilCliente.getMercado());   
    	
    	
    	LOGGER.info("## DoGet PagoWebPayServlet ##");
        LOGGER.info("Request recibido");
        LOGGER.info("    Host: " + req.getRemoteHost());
        LOGGER.info("    Addr: " + req.getRemoteAddr());

        LOGGER.info(TBK_ORDEN_COMPRA + " : " + ordenCompra);
        if (PCBUtils.isEmptyString(ordenCompra)) {
            LOGGER.error("Orden de compra invalida: " + ordenCompra);
            setRecFailMsg(LoadProperty.getProperty("recFailMsg"));
            LOGGER.info("recFailMsg: " + super.recFailMsg);
            setSinSaldoSuficienteMsg(LoadProperty.getProperty("sinSaldo"));
            req.setAttribute("movil", movilCliente);
            req.setAttribute("bolsasCliente", detalleBolsasCliente);
            req.setAttribute("saldoInsuficiente", getSinSaldoSuficienteMsg());
            req.setAttribute("recFailMsg", super.recFailMsg);
            req.setAttribute("montosWebpay", montosWebpay);
            req.getRequestDispatcher("/jsp/bolsas-mm-2.jsp").forward(req, resp);
            return;
        }
        
        if(resultado.equalsIgnoreCase("FRACASO")){
        	LOGGER.warn("transbank a rechazado la recarga");
            setRecFailMsg(LoadProperty.getProperty("recFailMsg"));
            LOGGER.info("recFailMsg: " + super.recFailMsg);
            setSinSaldoSuficienteMsg(LoadProperty.getProperty("sinSaldo"));
            req.setAttribute("movil", movilCliente);
            req.setAttribute("bolsasCliente", detalleBolsasCliente);
            req.setAttribute("saldoInsuficiente", getSinSaldoSuficienteMsg());
            req.setAttribute("recFailMsg", super.recFailMsg);
            req.setAttribute("montosWebpay", montosWebpay);
            req.getRequestDispatcher("/jsp/bolsas-mm-2.jsp").forward(req, resp);
            return;
        }
        
        //Recuperar recarga 
        LOGGER.info("##Recuperando Recarga - Invocando ConsultaRegistroRecWebPay ##");
        RecargaWebPayBean recarga = this.recuperarRecarga(ordenCompra);
               
        //TODO validando flujo recargaWebPay OK (es el que aplica correcatemente para PCB)
        if(recarga != null) {
            if(recarga.getRespuestaTransbank() != null){
            	String tbkRespuesta = recarga.getRespuestaTransbank();
            	String tbkCodRespuesta = recarga.getRespuestaTransbank();
            	
            	// TODO simulacion respuestra Transbank
//            	String emulaTbk = "TBK_RESPUESTA=0&"+
//            			"TBK_MONTO=50000&"+
//            			"TBK_ORDEN_COMPRA=1433953141570&"+
//            			"TBK_FINAL_NUMERO_TARJETA=6203&"+
//            			"TBK_FECHA_CONTABLE=0610&"+
//            			"TBK_FECHA_TRANSACCION=0610&"+
//            			"TBK_HORA_TRANSACCION=131950&"+
//            			"TBK_ID_SESION=MIENTELV3:aa8c16b67aad7435083168b84321cae7&"+
//            			"TBK_ID_TRANSACCION=395369970&"+
//            			"TBK_COD_AUT_M001=003423&"+
//            			"TBK_TIPO_PAGO_M001=VD&"+
//            			"TBK_NUMERO_CUOTAS_M001=0&"+
//            			"TBK_COD_RESP_M001=0&"+
//            			"TBK_MONTO_TIENDA_M001=50000&"+
//            			"TBK_ORDEN_TIENDA_M001=1433953141570&"+
//            			"TBK_VCI_M001=TSY&"+
//            			"TBK_VCI=TSY&"+
//            			"TBK_MAC=72dfad42c8d0b76c61a907318fbf82c1a5512c34b795d08cf7b957c15c195bf1fb2a81d02af57d03a4cde9b8d082f9572079355d573c16e6766d1d9d9e705fe78e280cd15234101876ee6e5923be833a97d1b6acfdc6094c715fa78a804c5753ab6573031fc07c2f4fe8d6075bf9286cc9a93bc55a16abff36d9901b2d698f3580134deeab8c14ba29585b8200779698808ba8bec54fcc77a21d43b1e220db22ef3c1f64ac46464388721233e5c7b262859919dcd775e2c3f0718197523b3bbc3218bd189277d249a46212416f3db8038373d286f926c5af21e3f8b11bfc3142e6ace2cf99ff2ac8d6c77d87145860ed678d68efc8610c1349e74db4e71e0aa525cebfbec7";
//                
            	//TODO Linea que aplica
        		if (!(tbkRespuesta.toLowerCase().indexOf(TBK_APPROVED_REG_EXP.toLowerCase().trim())>=0)){
//            	if (!(emulaTbk.toLowerCase().indexOf(TBK_APPROVED_REG_EXP.toLowerCase().trim())>=0)){            	
                	LOGGER.warn("transbank a rechazado la recarga");
                    setRecFailMsg(LoadProperty.getProperty("recFailMsg"));
                    LOGGER.info("recFailMsg: " + super.recFailMsg);
                    setSinSaldoSuficienteMsg(LoadProperty.getProperty("sinSaldo"));
                    req.setAttribute("movil", movilCliente);
                    req.setAttribute("bolsasCliente", detalleBolsasCliente);
                    req.setAttribute("saldoInsuficiente", getSinSaldoSuficienteMsg());
                    req.setAttribute("recFailMsg", super.recFailMsg);
                    req.setAttribute("montosWebpay", montosWebpay);
                    req.getRequestDispatcher("/jsp/bolsas-mm-2.jsp").forward(req, resp);
                    return;
        		}        	
                //Efectua recarga
//                LOGGER.info("## Invocando RecargaWebPay ##");
//                recarga = this.efectuarRecarga(recarga, false);
//        		
//                if(recarga.getCodigoRespuestaTBK().equals("0000")){
//        			LOGGER.info("Codigo Respuesta Transbank: " + TBK_APPROVED_REG_EXP);
//        			LOGGER.info("La tx se realiza exitosamente para la repsuesta de Transbank(lowercase): " + tbkRespuesta.toLowerCase());
//        			LOGGER.info("Codigo respuesta Transbank: [0000] | Aprobada");
//                } else {
//                   	LOGGER.warn("transbank a rechazado la recarga");
//                    setRecFailMsg(LoadProperty.getProperty("recFailMsg"));
//                    LOGGER.info("recFailMsg: " + super.recFailMsg);
//                    setSinSaldoSuficienteMsg(LoadProperty.getProperty("sinSaldo"));
//                    req.setAttribute("movil", movilCliente);
//                    req.setAttribute("bolsasCliente", detalleBolsasCliente);
//                    req.setAttribute("saldoInsuficiente", getSinSaldoSuficienteMsg());
//                    req.setAttribute("recFailMsg", super.recFailMsg);
//                    req.setAttribute("montosWebpay", montosWebpay);
//                    req.getRequestDispatcher("/jsp/bolsas-mm-2.jsp").forward(req, resp);
//                    return;                	
//                }
                
                if(this.actualizarRecarga(recarga)) {
                	//TODO if para simular caso mensaje exitoso
//                if(!this.actualizarRecarga(recarga)) {
//                    HttpSession session = req.getSession(false);
//                    Movil movilCliente = (Movil) session.getAttribute("movil");
                    
                	try {
        				movilCliente = delegateBolsa.desplegarInfoCliente(movilCliente.getMsisdn());
        				LOGGER.info("Se actualiza saldo del cliente: [" + movilCliente.getMsisdn() + "], nuevo saldo: " + movilCliente.getSaldo());
                        DelegateBolsa.cacheBolsaDisponibleMM.invalidateAll();
						detalleBolsasCliente = delegateBolsa.listarBolsas(movilCliente.getMsisdn());
        			} catch (Exception e) {
        				LOGGER.error("Exception lanzada debido a: " +e.getMessage());
        				e.printStackTrace();
        			}
                	
                    LOGGER.info("recarga.getIdp(): "+recarga.getIdp());
                	LOGGER.info("recarga se hizo efectiva para la orden: " + ordenCompra);                	
                    LOGGER.info("actualizada con resultado de recarga efectuada en orden: " + ordenCompra);
                    
//                    setSinSaldoSuficienteMsg("Saldo insuficiente");
                    setSinSaldoSuficienteMsg(LoadProperty.getProperty("sinSaldo"));
                    setRecSucMsg(LoadProperty.getProperty("recSucMsg"));
                    LOGGER.info("recSucMsg: " + super.recSucMsg);
                    
                    req.setAttribute("movil", movilCliente);
                    req.setAttribute("bolsasCliente", detalleBolsasCliente);
                    req.setAttribute("saldoInsuficiente", getSinSaldoSuficienteMsg());             
                    req.setAttribute("recSucMsg", super.recSucMsg);
                    req.setAttribute("montosWebpay", montosWebpay);
                    req.getRequestDispatcher("/jsp/bolsas-mm-2.jsp").forward(req, resp);
                    return;
                }
                else {
                	LOGGER.warn("transbank a rechazado la recarga");
                    setRecFailMsg(LoadProperty.getProperty("recFailMsg"));
                    LOGGER.info("recFailMsg: " + super.recFailMsg);
                    setSinSaldoSuficienteMsg(LoadProperty.getProperty("sinSaldo"));
                    req.setAttribute("movil", movilCliente);
                    req.setAttribute("bolsasCliente", detalleBolsasCliente);
                    req.setAttribute("saldoInsuficiente", getSinSaldoSuficienteMsg());
                    req.setAttribute("recFailMsg", super.recFailMsg);
                    req.setAttribute("montosWebpay", montosWebpay);
                    req.getRequestDispatcher("/jsp/bolsas-mm-2.jsp").forward(req, resp);
                    return;
                }
            } else {
                if (!resultado.equalsIgnoreCase("EXITO")){
                	LOGGER.warn("transbank a rechazado la recarga");
                    setRecFailMsg(LoadProperty.getProperty("recFailMsg"));
                    LOGGER.info("recFailMsg: " + super.recFailMsg);
                    setSinSaldoSuficienteMsg(LoadProperty.getProperty("sinSaldo"));
                    req.setAttribute("movil", movilCliente);
                    req.setAttribute("bolsasCliente", detalleBolsasCliente);
                    req.setAttribute("saldoInsuficiente", getSinSaldoSuficienteMsg());
                    req.setAttribute("recFailMsg", super.recFailMsg);
                    req.setAttribute("montosWebpay", montosWebpay);
                    req.getRequestDispatcher("/jsp/bolsas-mm-2.jsp").forward(req, resp);
                    return;
                } else {
                	LOGGER.warn("transbank a rechazado la recarga");
                    setRecFailMsg(LoadProperty.getProperty("recFailMsg"));
                    LOGGER.info("recFailMsg: " + super.recFailMsg);
                    setSinSaldoSuficienteMsg(LoadProperty.getProperty("sinSaldo"));
                    req.setAttribute("movil", movilCliente);
                    req.setAttribute("bolsasCliente", detalleBolsasCliente);
                    req.setAttribute("saldoInsuficiente", getSinSaldoSuficienteMsg());
                    req.setAttribute("recFailMsg", super.recFailMsg);
                    req.setAttribute("montosWebpay", montosWebpay);
                    req.getRequestDispatcher("/jsp/bolsas-mm-2.jsp").forward(req, resp);
                    return;                	
                }
            }
        } else {
        	//TODO if para simular caso mensaje exitoso
//          if (recarga != null) {
        	LOGGER.info("Recarga es null!");
            exitError(resp);
//          setIsHidden("noHidden");
            setRecFailMsg(LoadProperty.getProperty("recFailMsg"));
            LOGGER.info("recFailMsg: " + super.recFailMsg);
            setSinSaldoSuficienteMsg(LoadProperty.getProperty("sinSaldo"));
            req.setAttribute("movil", movilCliente);
            req.setAttribute("bolsasCliente", detalleBolsasCliente);
            req.setAttribute("saldoInsuficiente", getSinSaldoSuficienteMsg());
            req.setAttribute("recFailMsg", super.recFailMsg);
            req.setAttribute("montosWebpay", montosWebpay);
            req.getRequestDispatcher("/jsp/bolsas-mm-2.jsp").forward(req, resp);
            return;
        }
    }


	/**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doPost(HttpServletRequest req,
            HttpServletResponse resp) throws ServletException, IOException {
//    	idSessionLog(req, resp);
        this.doGet(req, resp);
    }

    
    //---------------------------------- LOGIC METHODS
    
    /**
     * metodo privado utilitario para obtener resultado de Transbank a la recarga
     * 
     * @param ordenCompra String orden de compra de la recarga
     * 
     * @return {@link RecargaWebPayBean} si se encuentra una recarga, null en caso de Exception
     */
    private RecargaWebPayBean recuperarRecarga(String ordenCompra) {
        
    	//TODO trx exitosa para consultar 1433953141570
//    	ordenCompra = "1433953141570";
    	
        RecargaWebPayBean recarga = null;
        try {
            recarga = delegate.consultarRecargaWebPayBean(ordenCompra);
        } catch (DAOException e) {
            LOGGER.error("Recarga no pudo ser obtenida", e);
        } catch (ServiceException e) {
            LOGGER.error("Orden de compra invalida");
        } catch (Exception e) {
            LOGGER.error("Exception no esperada al consultar recarga", e);
        }
        
        return recarga;
    }
    
    /**
     * metodo privado utilitario que realiza la actualizacion de la recarga
     * @param recarga Recarga a actualizar
     */
    private boolean actualizarRecarga(RecargaWebPayBean recarga) {

        boolean resultado = false;
        try {
            delegate.actualizarRecargaWebPay(recarga.getOrdenCompra(), recarga
                    .getRespuestaTransbank());
            resultado = true;
        } catch (DAOException e) {
            LOGGER.error("Recarga no pudo ser obtenida", e);
        } catch (ServiceException e) {
            LOGGER.error("Orden de compra invalida");
        } catch (Exception e) {
            LOGGER.error("Exception no esperada al consultar recarga", e);
        }

        return resultado;
    }
    
    /**
     * Metodo privado utilitario para hacer efectiva la recarga al numero pcs indicado
     * @param recarga {@link RecargaWebPayBean} recarga a hacer efectiva
     * @return {@link RecargaWebPayBean} nueva instancia que representa la recarga efectiva. null si la recarga no pudo efectuarse
     */
    private RecargaWebPayBean efectuarRecarga(RecargaWebPayBean recarga, boolean eligeTuPromo) {
        
        RecargaWebPayBean recargaEfectuada = null;
        
        try {        	
            recargaEfectuada = delegate.efectuarRecargaWebPay(recarga, eligeTuPromo);
        } catch (DAOException e) {
            LOGGER.error("Recarga no pudo ser obtenida", e);
        } catch (ServiceException e) {
            LOGGER.error("Orden de compra invalida");
        } catch (Exception e) {
            LOGGER.error("Exception no esperada al consultar recarga", e);
        }
        
        return recargaEfectuada;
        
    }
    
    /**
     * Entrega el String de respuesta de transbank de <code>recarga</code>
     * actualizado con los valores del resultado de efectuar la recarga.<br>
     * Estos parametros son:<br>
     * <ul>
     * <li>Codigo Autorizacion entregado por Entel al momento de hacer efectiva
     * la recarga
     * <li>Fecha de transaccion de al recarga entregada por Entel al momento de
     * hacerla efectiva
     * </ul>
     * 
     * @param recarga
     *            {@link RecargaWebPayBean} que se acaba de hacer efectiva, y
     *            para la cual se requiere el String de transbank actualizado
     * @return String transbank actualizado con informacion de efectuar la
     *         recarga
     */
    private String appendParametrosRespuestaTransbank(RecargaWebPayBean recarga) {

        String respuestaTransbank = recarga.getRespuestaTransbank() + "&"
                + recarga.getCodigoUnicoAutorizacion() + "&"
                + DateHelper.format(recarga.getFechaSolicitud(), DateHelper.FORMAT_yyyyMMdd);

        return respuestaTransbank;
    }
    
    
    //---------------------------------- EXIT METHODS
    
    private void exitOK(HttpServletResponse response) {
        LOGGER.info("Finalizando: " + EXIT_OK);
        this.enviarRespuesta(response, EXIT_OK);
    }

    private void exitError(HttpServletResponse response) {
        LOGGER.info("Finalizando: " + EXIT_ERROR);
        this.enviarRespuesta(response, EXIT_ERROR);
    }

    private void enviarRespuesta(HttpServletResponse response, String salida) {
        PrintWriter out = null;
        try {
            response.setContentType(CONTENT_TYPE);
            out = response.getWriter();
            out.println(salida);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            out.close();
        }

    }  
}