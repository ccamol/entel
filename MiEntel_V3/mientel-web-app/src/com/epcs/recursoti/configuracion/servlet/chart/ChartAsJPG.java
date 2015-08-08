/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.recursoti.configuracion.servlet.chart;

import java.awt.Color;
import java.awt.Font;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;

/**
 * @author gcastro (I2B) en nombre de Absalon Opazo (Atencion al Cliente, 
 * EntelPcs)
 *
 */
public class ChartAsJPG {
	
	private static final Logger LOGGER = Logger
    .getLogger(ChartAsJPG.class);
	

	/**
	 * Metodo utilitario para la preparacion y generacion del chart a imagen de la funcionalidad de facturacion.
	 * 
	 * @param response
	 * @param chart
	 * @param width
	 * @param height
	 */
	public static void prepareChartForFacturacion(HttpServletResponse response, JFreeChart chart, int width, int height){

		try{
		  CategoryPlot plot = chart.getCategoryPlot();
	      BarRenderer renderer = (BarRenderer) plot.getRenderer();
	      renderer.setDrawBarOutline(false);
	      renderer.setSeriesPaint(0, new Color(102, 164, 237));   
	      renderer.setMaximumBarWidth(0.06);
	      chart.setBackgroundPaint(Color.white);

	      CategoryPlot categoryPlot = (CategoryPlot) chart.getPlot();      
	      categoryPlot.setBackgroundPaint(Color.WHITE);
	      categoryPlot.setRangeGridlinesVisible(true);
	      categoryPlot.setRangeGridlinePaint(new Color(102, 164, 237));
	      
	      final NumberAxis rangeAxis = (NumberAxis) categoryPlot.getRangeAxis();
	      rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
	      rangeAxis.setTickLabelFont(new Font("Arial", Font.BOLD, 8));
	      
	      renderer.setDrawBarOutline(true);
	      CategoryAxis domainAxis = categoryPlot.getDomainAxis();
	      domainAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 4.0));
	      domainAxis.setTickLabelFont(new Font("Arial", Font.BOLD, 9));

	      ChartUtilities.writeChartAsJPEG(response.getOutputStream(), chart, width, height);
	      
		}catch (Exception e) {
			LOGGER.error("Exception al intentar convertir el chart a imagen", e);
		}
		
	}
	
	
	/**
	 * Metodo utilitario para la preparacion y generacion del chart a imagen de la funcionalidad de facturacion full.
	 * 
	 * @param response
	 * @param chart
	 * @param width
	 * @param height
	 */
	public static void prepareChartForFacturacionFull(HttpServletResponse response, JFreeChart chart, int width, int height){

		try{
			  CategoryPlot plot = chart.getCategoryPlot();
		      BarRenderer renderer = (BarRenderer) plot.getRenderer();
		      renderer.setDrawBarOutline(false);

		      renderer.setSeriesPaint(0, new Color(0x285EFF)); // Telefonia Movil 
		      renderer.setSeriesPaint(1, new Color(0x99D2FF)); // Servicio Adicional
		      renderer.setSeriesPaint(2, new Color(0xCEFFFF)); // Cobros Descuentos

		      renderer.setMaximumBarWidth(0.06);
		      chart.setBackgroundPaint(Color.white);

		      CategoryPlot categoryPlot = (CategoryPlot) chart.getPlot();      
		      categoryPlot.setBackgroundPaint(Color.WHITE);
		      categoryPlot.setRangeGridlinesVisible(true);
		      
		      categoryPlot.setRangeGridlinePaint(Color.BLUE);
		      
		      final NumberAxis rangeAxis = (NumberAxis) categoryPlot.getRangeAxis();
		      rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		      rangeAxis.setTickLabelFont(new Font("Arial", Font.BOLD, 8));
		      
		      renderer.setDrawBarOutline(true);
		      CategoryAxis domainAxis = categoryPlot.getDomainAxis();
		      domainAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 4.0));
		      domainAxis.setTickLabelFont(new Font("Arial", Font.BOLD, 9));

	      ChartUtilities.writeChartAsJPEG(response.getOutputStream(), chart, width, height);
	      
		}catch (Exception e) {
			LOGGER.error("Exception al intentar convertir el chart a imagen", e);
		}
		
	}

}
