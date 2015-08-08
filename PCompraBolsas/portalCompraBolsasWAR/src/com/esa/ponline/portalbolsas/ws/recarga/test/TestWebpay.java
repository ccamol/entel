/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.esa.ponline.portalbolsas.ws.recarga.test;

import java.io.IOException;
import java.io.Serializable;


//import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.esa.ponline.portalbolsas.bean.RecargaWebPayBean;
import com.esa.ponline.portalbolsas.exceptions.dao.DAOException;
import com.esa.ponline.portalbolsas.exceptions.ws.ServiceException;
import com.esa.ponline.portalbolsas.util.PCBUtils;
import com.esa.ponline.portalbolsas.ws.recarga.dao.delegate.RecargaDelegate;

//import com.epcs.bean.RecargaWebPayBean;
//import com.epcs.billing.recarga.delegate.RecargaDelegate;
//import com.epcs.recursoti.configuracion.Utils;
//import com.epcs.recursoti.configuracion.jsf.utils.JSFMessagesHelper;
//import com.epcs.recursoti.configuracion.jsf.utils.JSFPortletHelper;
//import com.epcs.recursoti.excepcion.DAOException;
//import com.epcs.recursoti.excepcion.ServiceException;

/**
 * @author ccastro (MZZO) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class TestWebpay implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Logger para TestWebpay
     */
    private static final Logger LOGGER = Logger.getLogger(TestWebpay.class);

    private RecargaDelegate recargaDelegate;

    private String ordenCompra;

    public TestWebpay(String ordenCompra) {
        super();
        this.ordenCompra = ordenCompra;
        this.recargaDelegate = new RecargaDelegate();
    }

    /**
     * @return the ordenCompra
     */
    public String getOrdenCompra() {
        return ordenCompra;
    }

    /**
     * @param ordenCompra
     *            the ordenCompra to set
     */
    public void setOrdenCompra(String ordenCompra) {
        this.ordenCompra = ordenCompra;
    }

    /**
     * @return the recargaDelegate
     */
    public RecargaDelegate getRecargaDelegate() {
        return recargaDelegate;
    }

    /**
     * @param recargaDelegate
     *            the recargaDelegate to set
     */
    public void setRecargaDelegate(RecargaDelegate recargaDelegate) {
        this.recargaDelegate = recargaDelegate;
    }

    public String testPago() {
    	HttpServletRequest req = null;

        RecargaWebPayBean recarga = null;

        if (PCBUtils.isEmptyString(ordenCompra)) {
            LOGGER.error("parametro 'ordenCompra' no presente. "
                    + "Finaliza ejecucion de CierreWebPay");
//            JSFMessagesHelper.addServiceErrorMessage("faltaInformacion");
            return null;
        }

        /*
         * Obtencion recarga asociada
         */
        try {
            recarga = recargaDelegate.consultarRecargaWebPayBean(ordenCompra);
        } catch (DAOException e) {
            LOGGER.error("DAOException caught: " + e.getMessage());
//            JSFMessagesHelper.addServiceErrorMessage("consultarRecarga",
//                    new String[] { ordenCompra });
        } catch (ServiceException e) {
            LOGGER.error("ServiceException caught: " + e.getCodigoRespuesta()
                    + " - " + e.getDescripcionRespuesta());
//            JSFMessagesHelper.addErrorCodeMessage("recarga", e.getCodigoRespuesta());
        } catch (Exception e) {
            LOGGER.error("Exception caught: " + e.getMessage());
//            JSFMessagesHelper.addServiceErrorMessage("consultarRecarga",
//                    new String[] { ordenCompra });
        }

        if (recarga != null) {

            /*
             * Armado URL de pago
             */
            String url = "http://localhost:7001/personas/webpay/pagoWebPay";
            url += "?" + recarga.getRespuestaTransbank();

            prepareResponse(url, req, false);

        }

        return null;
    }

    @SuppressWarnings("null")
	private void prepareResponse(String url, HttpServletRequest request,
            boolean error) {

        if (error) {
//            FacesContext.getCurrentInstance().renderResponse();
        }
        else {

            HttpServletResponse response = null; // = request.getContextPath();
//            JSFPortletHelper.getResponse();
            try {
                response.sendRedirect(url);
                // Se indica que al ciclo de JSF que la peticion debe finalizar
//                FacesContext.getCurrentInstance().responseComplete();

            } catch (IOException e) {
                LOGGER.error("Unexpected error on redirect", e);
//                FacesContext.getCurrentInstance().renderResponse();
            }

        }
    }

}
