package com.esa.ponline.portalbolsas.ws.recarga.dao.imp;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.esa.ponline.portalbolsas.configuration.properties.LoadProperty;
import com.esa.ponline.portalbolsas.exceptions.dao.DAOException;
import com.esa.ponline.portalbolsas.exceptions.ws.ServiceException;
import com.esa.ponline.portalbolsas.ws.recarga.dao.delegate.RecargaDelegate;
import com.esa.ponline.portalbolsas.ws.recarga.dao.util.helper.WebPayHelper;
import com.esa.ponline.portalbolsas.bean.FactibilidadRecargaWebPayBean;
import com.esa.ponline.portalbolsas.bean.PagoWebPayBean;
import com.esa.ponline.portalbolsas.bean.PromoRecargaWebPayBean;
import com.esa.ponline.portalbolsas.bean.RecargaWebPayBean;

/**
 * 
 * @author ccastro (MZZO) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class RecargaController implements Serializable {  
	//TODO Chequear codigo RecargaController
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Logger para RecargaController
     */
    private static final Logger LOGGER = Logger.getLogger(RecargaController.class);
    
    /**
     * Delegado de negocio
     */
    private RecargaDelegate recargaDelegate = new RecargaDelegate();
    
    /**
     * numero a quien se realiza la recarga
     */
    private String numeroPcs;

    private PagoWebPayBean pagoWebPay = new PagoWebPayBean();

    private RecargaWebPayBean recargaWebPay = new RecargaWebPayBean();
    
    private String tbkIdSesion;
    
    private String indicativoTelefono;
    
//    private String idPromoRecarga;
//    
//    private String descPromoRecarga;
//    
//    private String mensajePromoRecarga;
    
    private PromoRecargaWebPayBean promoRecarga;
    
    private boolean eligeTuPromo;
// 
    /**
     * Default constructor
     */
    public RecargaController() {
        super();
    }

    /**
     * Action method para solicitar recarga tarjeta de credito.<br>
     * Este metodo realiza el ingreso y la factibilidad
     * 
     * @return String postback
     */
    public String solicitarRecargaTarjetaCredito(HttpServletRequest req) {
		LOGGER.info("## Inicio solicitud recarga TC-Validando promocion ##");
		String monto = req.getParameter("monto");
		
        HttpSession session = req.getSession(false);
        String msisdn = (String) session.getAttribute("msisdn");
        
		RecargaWebPayBean recarga = new RecargaWebPayBean();
		recarga.setNumeroPcs(msisdn);
		
    	String respuesta = "confirmar";

        Double montoRecarga = null;


        // Validacion de monto recarga
        try {
//            montoRecarga = Double.valueOf(getMontoRecarga());      
            montoRecarga = Double.valueOf(monto); 
        } catch (Exception e) {
        	LOGGER.error("Error debido a: " + e.getMessage());
        }
        
//         Ingreso recarga
        String codPromo = "";
        String codPromoF = "";
        try {        	
        	if (promoRecarga != null) {
        		if (montoRecarga >= 3500) {
        			codPromo = "-" + promoRecarga.getCodigo();
        			codPromoF = promoRecarga.getCodigo();
        			LOGGER.info("Recarga mayor o igual a 3500, PromoRecargaID: "+promoRecarga.getId()+", ETPMovil: " + numeroPcs+", codPromo: "+codPromo+" , codPromoF: "+codPromoF);
        		} else {
        			LOGGER.info("Recarga menor a 3500, PromoRecargaID: "+promoRecarga.getId()+", ETPMovil: " + numeroPcs+", codPromo: "+codPromo+" , codPromoF: "+codPromoF);
        		}
        	} else {
        		LOGGER.info("ETPMovil: " + numeroPcs+" ,codPromo: "+codPromo+" , codPromoF: "+codPromoF);
        	}
        	
        	LOGGER.info("## Invocando IngresarRecargaWebPay ##");
//            recarga = recargaDelegate.ingresarRecargaWebPay(WordNumberHelper.getPrefijoAmpliacion()+Integer.parseInt(getIndicativoTelefono(),10)+getNumeroPcs(), montoRecarga, codPromo);
        	recarga = recargaDelegate.ingresarRecargaWebPay(recarga.getNumeroPcs(), montoRecarga, codPromo);
            
        } catch (DAOException e) {
            LOGGER.error("DAOException caught: " + e.getMessage(), e);
        } catch (ServiceException e) {
            LOGGER.info("ServiceException caught: "+ numeroPcs + " - " + e.getCodigoRespuesta() + " - " + e.getDescripcionRespuesta());
        } catch (Exception e) {
            LOGGER.error("Exception caught: " + e.getMessage(), e);
        }

        // Factibilidad
        try {
        	LOGGER.info("## Invocando servicio de factibilidad ##");
            FactibilidadRecargaWebPayBean factibilidad = recargaDelegate.factibilidadRecargaWebPay(recarga, eligeTuPromo, codPromoF);
//            FactibilidadRecargaWebPayBean factibilidad = recargaDelegate.factibilidadRecargaWebPay(recarga, false, "");

            if (factibilidad == null) {
            	LOGGER.info("FactibilidadRecargaWebPayBean es nulo!");
               LOGGER.error( new Exception("no se obtuvo factibilidad"));
            }

        } catch (DAOException e) {
            LOGGER.error("DAOException caught: " + e.getMessage(), e);
        } catch (ServiceException e) {
        	LOGGER.info("ServiceException caught: "+ numeroPcs + " - " 
        				+ e.getCodigoRespuesta() + " - " + e.getDescripcionRespuesta());
        } catch (Exception e) {
            LOGGER.error("Exception caught: " + e.getMessage(), e);
        }
        this.setRecargaWebPay(recarga);

        return respuesta;
    }
    


    /**
     * Action method para la confirmacion a recarga tarjeta de credito
     * @return
     */
    public String confirmarRecargaTarjetaCredito(HttpServletRequest req) {
        String monto = req.getParameter("monto");

        String postback = null;

        try {
            recargaWebPay.setOrdenCompra(WebPayHelper.generarIdOrdenDeCompra());
            recargaWebPay.setMontoRecarga(Double.valueOf(monto));
            String ordenCompra = recargaWebPay.getOrdenCompra();
            
            LOGGER.info("## Inicio ConfirmarRecargaTC - Invocando consultarRecargaWebPayBean ##");
//            this.recargaWebPay = recargaDelegate.consultarRecargaWebPayBean(ordenCompra);
            // orden de compra en duro para realizar consulta de transaccion rechazada
//            this.recargaWebPay = recargaDelegate.consultarRecargaWebPayBean("1433434517829");
            this.recargaWebPay = recargaDelegate.consultarRecargaWebPayBean(ordenCompra);
            postback = "recargaWebpay";
        } catch (DAOException e) {
            LOGGER.error("DAOException caught: " + e.getMessage(), e);
        } catch (ServiceException e) {
        	LOGGER.info("ServiceException caught: "+ numeroPcs + " - " 
        				+ e.getCodigoRespuesta() + " - " + e.getDescripcionRespuesta());
        } catch (Exception e) {
            LOGGER.error("Exception caught: " + e.getMessage(), e);
        }

        // PagoWebPayBean: bean con las constantes para el pago mediante webpay
        // El objeto PagoWebPayBean debe recibir la URL inicial del servidor
        // ie: http://<serverName>:<port>
//        FacesContext context = FacesContext.getCurrentInstance();
        
        String contextPath = req.getContextPath();
        LOGGER.info("conexto: " + contextPath);
        
        pagoWebPay = new PagoWebPayBean(contextPath);
        
        //TODO pagoWebPay.setUrlRecaudacion(LoadProperty.getProperty("recarga.webpay.pago.action")); se establece en
        //duro para mapear recaudacion produccion, ya que en desa se lee http://testjsp2.entelpcs.com/cgi-bin/kcc5/tbk_bp_pago.cgi
		pagoWebPay.setUrlRecaudacion("https://recaudacion.entelpcs.com/cgi-bin/kcc5/tbk_bp_pago.cgi");
		
		String urlActionRecaudacion = pagoWebPay.getUrlRecaudacion();
		
		
		pagoWebPay.setUrlFormAction(urlActionRecaudacion);
		
        return postback;
    }
//
    /**
     * Entrega el valor del parametro TBK_ID_SESION que sera enviado a Tranbank.<br>
     * Lo genera concatenando un prefijo definido en la propiedad
     * <code>recarga.webpay.pago.TBK_ID_SESION.prefix</code> el caracter ":" y
     * el IDP de la recarga Webpay
     * 
     * @return String
     */
    public String getTbkIdSesion() {
        this.tbkIdSesion = LoadProperty.getProperty("recarga.webpay.pago.TBK_ID_SESION.prefix");
        return tbkIdSesion + ":" + recargaWebPay.getIdp();
    }
  
    public String getIndicativoTelefono() {
		return indicativoTelefono;
	}

	public void setIndicativoTelefono(String indicativoTelefono) {
		this.indicativoTelefono = indicativoTelefono;
	}

    /**
     * @return the recargaWebPay
     */
    public RecargaWebPayBean getRecargaWebPay() {
        return recargaWebPay;
    }

    /**
     * @param recargaWebPay the recargaWebPay to set
     */
    public void setRecargaWebPay(RecargaWebPayBean recargaWebPay) {
        this.recargaWebPay = recargaWebPay;
    }
    
    /**
     * @return the pagoWebPay
     */
    public PagoWebPayBean getPagoWebPay() {
        return pagoWebPay;
    }

    /**
     * @param pagoWebPay the pagoWebPay to set
     */
    public void setPagoWebPay(PagoWebPayBean pagoWebPay) {
        this.pagoWebPay = pagoWebPay;
    }
    
    /**
     * @return the recargaDelegate
     */
    public RecargaDelegate getRecargaDelegate() {
        return recargaDelegate;
    }

    /**
     * @param recargaDelegate the recargaDelegate to set
     */
    public void setRecargaDelegate(RecargaDelegate recargaDelegate) {
        this.recargaDelegate = recargaDelegate;
    }
    
    /**
     * @return the numeroPcs
     */
    public String getNumeroPcs() {
        return numeroPcs;
    }

    /**
     * @param numeroPcs
     *            the numeroPcs to set
     */
    public void setNumeroPcs(String numeroPcs) {
        this.numeroPcs = numeroPcs;
    }
}
