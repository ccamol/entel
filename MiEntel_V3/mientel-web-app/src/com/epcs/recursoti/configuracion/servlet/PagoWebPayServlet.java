package com.epcs.recursoti.configuracion.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epcs.bean.RecargaWebPayBean;
import com.epcs.billing.recarga.delegate.RecargaDelegate;
import com.epcs.recursoti.configuracion.DateHelper;
import com.epcs.recursoti.configuracion.Utils;
import com.epcs.recursoti.configuracion.jsf.utils.JSFPortletHelper;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

/**
 * Servlet que maneja las llamadas de cierre de WebPay
 * 
 * Servlet implementation class CierreWebPayServlet
 */
public class PagoWebPayServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final String TBK_ORDEN_COMPRA = "TBK_ORDEN_COMPRA";

    private static final String CONTENT_TYPE = "text/html;charset=UTF-8";

    private static final String EXIT_OK = "ACEPTADO";

    private static final String EXIT_ERROR = "RECHAZADO";

    private RecargaDelegate delegate;
    /**
     * Logger para CierreWebPayServlet
     */
    private static final Logger LOGGER = Logger
            .getLogger(PagoWebPayServlet.class);

    /**
     * @see Servlet#init(ServletConfig)
     */
    public void init(ServletConfig config) throws ServletException {
        LOGGER.info("servlet init");
        this.delegate = new RecargaDelegate();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

    	LOGGER.info("## DoGet PagoWebPayServlet ##");
        //Algunos logs para mantener informacion de los request que recibe este Servlet
        LOGGER.info("Request recibido");
        LOGGER.info("    Host: " + request.getRemoteHost());
        LOGGER.info("    Addr: " + request.getRemoteAddr());

        //Validacion orden de compra
        String ordenCompra = request.getParameter(TBK_ORDEN_COMPRA);
        LOGGER.info(TBK_ORDEN_COMPRA + " : " + ordenCompra);
        if (Utils.isEmptyString(ordenCompra)) {
            LOGGER.error("Orden de compra invalida: " + ordenCompra);
            exitError(response);
            return;
        }
        
        //Recuperar recarga 
        LOGGER.info("##Recuperando Recarga - Invocando ConsultaRegistroRecWebPay ##");
        RecargaWebPayBean recarga = this.recuperarRecarga(ordenCompra);
        
        LOGGER.info("recarga.getIdp(): "+recarga.getIdp());
        if(recarga == null) {
        	LOGGER.info("Recarga es null!");
            exitError(response);
            return;
        }   
        
    	boolean eligeTuPromo = false;
        try {        	
			eligeTuPromo = JSFPortletHelper.getContentNode("idContenido",
					"flagMostrarEligeTuPromo").getProperty("valorFlag")
					.getValue().getBooleanValue();
        } catch (Exception e) {LOGGER.error("Error capturando contenido: ",e);}
        
        //Efectua recarga
        LOGGER.info("## Invocando RecargaWebPay ##");
        recarga = this.efectuarRecarga(recarga, eligeTuPromo);

        if (recarga == null) {
        	LOGGER.info("Recarga es null!");
            exitError(response);
            return;
        }
        else {
        LOGGER.info("recarga se hizo efectiva para la orden: "+ordenCompra);
       }

        //Actualiza String de respuesta transbank con parametros requeridos para la
        //nueva actualizacion
        
        String newRespuestaTransbank = this.appendParametrosRespuestaTransbank(recarga);
        recarga.setRespuestaTransbank(newRespuestaTransbank);
        
        LOGGER.info("## Invocando actualizar recarga ##");
        if(this.actualizarRecarga(recarga)) {
            LOGGER.info("actualizada con resultado de recarga efectuada en orden: "+ordenCompra);
        }
        else {
            exitError(response);
            return;
        }
        
        //Salida exitosa
        exitOK(response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
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
