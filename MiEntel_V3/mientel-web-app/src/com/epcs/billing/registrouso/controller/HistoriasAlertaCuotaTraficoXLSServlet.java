package com.epcs.billing.registrouso.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.log4j.Logger;

import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.bean.AlertaCuotaTraficoBean;
import com.epcs.billing.registrouso.delegate.TraficoDelegate;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;

/**
 * @author wbrochero (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
/**
 * Servlet implementation class HistoriasAlertaCuotaTraficoXLSServlet
 */
public class HistoriasAlertaCuotaTraficoXLSServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private TraficoDelegate traficoDelegate;
	
	private static final Logger LOGGER = Logger.getLogger(HistoriasAlertaCuotaTraficoXLSServlet.class);
	
	private List<AlertaCuotaTraficoBean> alertaCuotaTrafico;
    

	/**
	 * @return the alertaCuotaTrafico
	 */
	public List<AlertaCuotaTraficoBean> getAlertaCuotaTrafico() {
		return alertaCuotaTrafico;
	}

	/**
	 * @param alertaCuotaTrafico the alertaCuotaTrafico to set
	 */
	public void setAlertaCuotaTrafico(
			List<AlertaCuotaTraficoBean> alertaCuotaTrafico) {
		this.alertaCuotaTrafico = alertaCuotaTrafico;
	}

	/**
     * @see HttpServlet#HttpServlet()
     */
    public HistoriasAlertaCuotaTraficoXLSServlet() {
    	traficoDelegate = new TraficoDelegate();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.exportXLS(request, response);
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.exportXLS(request, response);
		// TODO Auto-generated method stub
	}

	private void exportXLS(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        OutputStream out 	= null;
        int fila 			= 0;

        
        try {
             
            //HttpServletResponse response = JSFPortletHelper.getResponse();
        	//ProfileWrapper profile = ProfileWrapperHelper.getProfile(JSFPortletHelper.getRequest());
            
            ProfileWrapper profileWrapper = ProfileWrapperHelper.getProfile(request);
            String numeroPcs = ProfileWrapperHelper.getPropertyAsString(profileWrapper,"numeroPcsSeleccionado");
            

            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment; filename=historicoAlertaCuotaTrafico.xls");

        	
            WritableWorkbook w = Workbook.createWorkbook(response.getOutputStream());
            WritableSheet s = w.createSheet("Alertas Cuota de Trafico", 0);

            s.addCell(new Label(0, fila, "Fecha y Hora"));
            s.addCell(new Label(1, fila, "Texto del SMS"));

            alertaCuotaTrafico = this.traficoDelegate.getHistoricoAlertaCuotaTrafico(numeroPcs);
            Iterator iter = alertaCuotaTrafico.iterator();
            if (iter.hasNext()){
                fila = detalleXLS(iter, s, fila);
            }else{
                w.write();
                w.close();
                //return null;
            }
            w.write();
            w.close();
        } catch (Exception e) {
        	LOGGER.error("No ha sido posible descargar el archivo excel ", e);
        } finally {
            if (out != null){
                try {
                    out.close();
                }
                catch(IOException io) {
                }
            }
        }
        //return null;
    }
    
    private int detalleXLS(Iterator iter, WritableSheet s, int fila) {
        while (iter.hasNext()) {
            fila++;
            AlertaCuotaTraficoBean elemento = (AlertaCuotaTraficoBean)iter.next();

            try {
            	
                s.addCell(new Label(0, fila, elemento.getFechaEnvioSMSFormat().replaceAll("&nbsp;", "")));
                s.addCell(new Label(1, fila, elemento.getTxtSMS().replaceAll("&nbsp;", "")));
                
            } catch (java.util.NoSuchElementException e) {
                LOGGER.info("NO EXITEN MAS ELEMENTOS"); 
            } catch (Exception ee) {
                LOGGER.error(ee);
            }
        }
        return fila;
    }

}
