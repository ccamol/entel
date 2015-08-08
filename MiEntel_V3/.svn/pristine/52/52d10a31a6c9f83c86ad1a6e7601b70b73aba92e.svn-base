/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.recursoti.configuracion.servlet.chart;

import java.io.IOException;

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
import com.epcs.bean.ResumenFacturacionBean;
import com.epcs.billing.balance.delegate.FacturacionDelegate;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.configuracion.uup.ProfileWrapperHelper;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.excepcion.ServiceException;

/**
 * @author gcastro (I2B) en nombre de Absalon Opazo (Atencion al Cliente, 
 * EntelPcs)
 *
 */
public class ChartFacturacionImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = Logger
    .getLogger(ChartFacturacionImageServlet.class);
	
	//Bean
    private ResumenFacturacionBean resumenFacturacionBean;
    
    // Delegate
    private FacturacionDelegate facturacionDelegate;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  
		  response.setContentType("image/jpeg");
	      response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
	      response.setHeader("Pragma", "no-cache");
	      response.setDateHeader("Expires", -1);

	      try{
		      ProfileWrapper profileWrapper = ProfileWrapperHelper
	          .getProfile(request);
		      
			  String numeroPcs = ProfileWrapperHelper.getPropertyAsString(
			          profileWrapper, "numeroPcs");
			  String rut = ProfileWrapperHelper.getPropertyAsString(profileWrapper,
			          "rutTitular");
			  String numeroCuenta = ProfileWrapperHelper.getPropertyAsString(
			          profileWrapper, "numeroCuenta");

			  resumenFacturacionBean = new ResumenFacturacionBean();
			  facturacionDelegate = new FacturacionDelegate();
			  
			  //Se indica al metodo que NO REGISTRE en el log de la caja de Facturacion del Dashboard 
			  boolean logCajaFacturacion = false;
			  resumenFacturacionBean = facturacionDelegate.getResumenFacturacion(
		                numeroPcs, rut, numeroCuenta, logCajaFacturacion);

		       String numIntervalosGrafico = MiEntelProperties.getProperty("facturacion.resumen.grafico.numIntervalos");
		       resumenFacturacionBean.setNumIntervalosGrafico(numIntervalosGrafico);
		  
	       } catch (DAOException e) {  
	    	    LOGGER.error("DAOException al intentar obtener el resumen de facturacion", e);
	        } catch (ServiceException e) {
	            LOGGER.error("Error de servicio codigo: " + e.getCodigoRespuesta()
	                    + " - " + e.getDescripcionRespuesta()); 
	        } catch (Exception e) {
	            LOGGER.error("Exception al intentar obtener el resumen de facturacion", e);
	        }

		     String series1 = "";
		     DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		      try{
	            for (int i=0; i< Integer.parseInt(resumenFacturacionBean.getNumIntervalosGrafico()) ; i++){
	                dataset.addValue(
	                		Integer.parseInt(resumenFacturacionBean.getFacturacionMeses().get(i)), 
	                		series1, 
	                		resumenFacturacionBean.getMeses().get(i));
	            }
		      }catch(Exception e){
		    	  LOGGER.error("Exception al intentar configurar y armar los valores de la grafica", e);
		      }
		      
		      String titulo = new String("");
		      String unidadX = new String("");
		      String unidadY = new String("");
		      int width = 225;
		      int height = 145;
		      
		      JFreeChart chart = ChartFactory
		      .createStackedBarChart(titulo, unidadX, unidadY, dataset, PlotOrientation.VERTICAL, false, true, false);       
	 
		      ChartAsJPG.prepareChartForFacturacion(response, chart, width, height);

	}

}
