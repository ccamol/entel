package com.epcs.dashboard.mensajes.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.bean.ResumenFacturacionBean;
import com.epcs.billing.balance.delegate.FacturacionDelegate;
import com.epcs.recursoti.configuracion.DateHelper;
import com.epcs.recursoti.configuracion.PDFCreatorHelper;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;
import com.lowagie.text.Font;

/**
 * Servlet implementation class MensajesParaTiPDFServlet
 */
public class DetalleLlamadosPDFServlet extends HttpServlet {
	
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(DetalleLlamadosPDFServlet.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetalleLlamadosPDFServlet() { 
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    this.processRequest(request, response);
	}
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String origen = request.getParameter("origen");
            
            if (origen.equals("FACT")) {
                generarPDFFacturacion(request, response);
            } else if (origen.equals("MPT")) {
                generarPDFMPT(request, response);
            }
        } catch (DAOException de) {
            LOGGER.error("Error al obtener apertura de documento detalle llamados", de);
        } catch (ServiceException se) {
            LOGGER.info("Error al obtener apertura de documento");
        } catch (SQLException e) {
            LOGGER.error("Error al registrar en la base de datos", e);
        } catch (Exception e) {
            LOGGER.error("Error al obtener detalle llamado mes", e);
        }	    
	}
	
	private void generarPDFFacturacion(HttpServletRequest request, HttpServletResponse response) throws DAOException, ServiceException, Exception {
	    ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(request);
        
        String numeroCuenta = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "numeroCuenta");
        String numeroPcs = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "numeroPcs");
        String aaa = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "aaa");
        String folio = request.getParameter("folio");
        String tipoDocumento = request.getParameter("tipo");
        FacturacionDelegate facturacionDelegate = new FacturacionDelegate();
        String detalleLlamadosFull = facturacionDelegate.consultarDetalleLlamadosFull(numeroCuenta, numeroPcs, aaa, folio, tipoDocumento);            
        
        PDFCreatorHelper.createAndDownloadPDF(detalleLlamadosFull,
                "detalleLlamadosMes.pdf", response.getOutputStream(),
                new Font(Font.COURIER, 6, Font.NORMAL), response);
	}
	
	private void generarPDFMPT(HttpServletRequest request, HttpServletResponse response) throws DAOException, ServiceException, SQLException, Exception {
        ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(request);
        String numeroCuenta = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "numeroCuenta");
        String numeroPcs = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "numeroPcs");
        String aaa = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "aaa");
        String rut = ProfileWrapperHelper.getPropertyAsString(profileWrapper, "rutTitular");
                    
        FacturacionDelegate facturacionDelegate = new FacturacionDelegate();
        //Se indica al metodo que NO REGISTRE en el log de la caja de Facturacion del Dashboard 
		boolean logCajaFacturacion = false;
        ResumenFacturacionBean resumenFact = facturacionDelegate.getResumenFacturacion(numeroPcs, rut, numeroCuenta, logCajaFacturacion);
        String tipoDocumento = resumenFact.getTipoDocumento();
        String folio = resumenFact.getFolio();
        String periodo = DateHelper.format(resumenFact.getFechaPeriodo(), DateHelper.FORMAT_MMyyyy_SLASH);
        
        String detalleLlamadosFull = facturacionDelegate.consultarDetalleLlamadosFullMPT(numeroCuenta, numeroPcs, numeroPcs, aaa, folio, tipoDocumento, periodo);
        
        PDFCreatorHelper.createAndDownloadPDF(detalleLlamadosFull,
                "detalleLlamadosMes.pdf", response.getOutputStream(),
                new Font(Font.COURIER, 6, Font.NORMAL), response);
	}
}
