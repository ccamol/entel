/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.recursoti.configuracion.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import com.bea.p13n.usermgmt.profile.ProfileWrapper;
import com.epcs.bean.DocumentoFacturaFullBean;
import com.epcs.bean.FacturacionFullBean;
import com.epcs.billing.balance.delegate.FacturacionDelegate;
import com.epcs.recursoti.configuracion.DateHelper;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

/**
 * @author gcastro (I2B) en nombre de Absalon Opazo (Atencion al Cliente, 
 * EntelPcs)
 *
 */
public class ChartFacturacionFullImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = Logger
    .getLogger(ChartFacturacionFullImageServlet.class);
	
	//Bean
	private FacturacionFullBean facturacionFullBean;

    // Delegate
    private FacturacionDelegate facturacionDelegate;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  
		  response.setContentType("image/jpeg");
	      response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
	      response.addHeader("Cache-Control", "post-check=0, pre-check=0");
	      response.setHeader("Pragma", "no-cache");
	      response.setDateHeader("Expires", 0);

	      try{
		      ProfileWrapper profileWrapper = ProfileWrapperHelper
	          .getProfile(request);
		      
		      String numero = ProfileWrapperHelper
		      .getPropertyAsString(profileWrapper,"numeroPcsSeleccionado");
	          String rutTitular = ProfileWrapperHelper
	          .getPropertyAsString(profileWrapper,"rutTitular");

	          facturacionFullBean = new FacturacionFullBean();
	          facturacionDelegate = new FacturacionDelegate();
	          
	          facturacionFullBean = facturacionDelegate.getFacturacionFull(numero, rutTitular);
	          
	       } catch (DAOException e) {
	    	    facturacionFullBean = null;
	    	    LOGGER.error("DAOException al intentar obtener la facturacion full", e);
	        } catch (ServiceException e) {
	        	facturacionFullBean = null;
	            LOGGER.error("Error de servicio codigo: " + e.getCodigoRespuesta()
	                    + " - " + e.getDescripcionRespuesta()); 
	        } catch (Exception e) {
	        	facturacionFullBean = null;
	            LOGGER.error("Exception al intentar obtener la facturacion full", e);
	        }

	         String mes = "";
		     
		     String series1 = "series1"; // Telefonia Movil
		     String series2 = "series2"; // Servicio Adicional
		     String series3 = "series3"; // Cobros Descuentos 
		    
		     
		     DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		     
		     try{
		     
			     ArrayList<DocumentoFacturaFullBean> dfull = new ArrayList<DocumentoFacturaFullBean>(facturacionFullBean.getDocumentosFacturasFull());
		            for( DocumentoFacturaFullBean df : dfull ){           
		                Date emision = df.getFechaEmision();
		                if( emision != null ){
		                	mes = DateHelper.getMonthName(emision).toUpperCase();
		                }   

		                if( df.getMontoTelefoniaMovil() != null ){
			                // Telefonia Movil
			                dataset.addValue(
			                		df.getMontoTelefoniaMovil(),
			                		series1, 
			                		mes);
			             }
		                
		                if( df.getMontoServAdicional() != null ){
			                // Servicio Adicional
			                dataset.addValue(
			                		df.getMontoServAdicional(), 
			                		series2, 
			                		mes);
			            }
		                
		                if( df.getMontoCobrosDescuentos() != null ){
		                // Cobros Descuentos
		                dataset.addValue(
		                		df.getMontoCobrosDescuentos(), 
		                		series3, 
		                		mes);
		                }

		            }

		     }catch(Exception e){
		    	  LOGGER.error("Exception al intentar configurar y armar los valores de la grafica", e);
		      }

		      String titulo = new String("");
		      String unidadX = new String("");
		      String unidadY = new String("");
		      int width = 240;
		      int height = 185;
		      
		      JFreeChart chart = ChartFactory
		      .createStackedBarChart(titulo, unidadX, unidadY, dataset, PlotOrientation.VERTICAL, false, true, false);       
	 
		      ChartAsJPG.prepareChartForFacturacionFull(response, chart, width, height);

	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  doGet(request, response);
	}

}
