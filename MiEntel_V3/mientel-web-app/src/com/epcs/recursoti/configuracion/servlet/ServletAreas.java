/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.recursoti.configuracion.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epcs.bean.CiudadBean;
import com.epcs.bean.RegionBean;
import com.epcs.recursoti.configuracion.JsonHelper;
import com.epcs.recursoti.excepcion.DAOException;
import com.epcs.recursoti.parametros.dao.ParametrosDAO;
import com.epcs.recursoti.parametros.delegate.ParametrosDelegate;

/**
 * 
 * Servlet empleado para llenar los combos de ciudad y comuna 
 * del formulario mis datos por medio de ajax.
 * @author jmanzur (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class ServletAreas extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = Logger.getLogger(ServletAreas.class);

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();
        ParametrosDelegate parametrosDelegate = new ParametrosDelegate();
        parametrosDelegate.setParametrosDAO(new ParametrosDAO());
        
        String combo = request.getParameter("combo") != null ? request
                .getParameter("combo") : "";
        String option = request.getParameter("id") != null ? request
                .getParameter("id") : "";
        String region = request.getParameter("reg") != null ? request
                        .getParameter("reg") : "";        

        LOGGER.info("Combo :" + combo);
        LOGGER.info("Combo :" + option);
        
        LOGGER.info("Es regionpromociones  :"
                + combo.contains("regionpromociones"));
        
        response.setHeader("Cache-Control", "no-cache");         
        
        try {
            if (combo.contains("regionpromociones")) {

                out.print(JsonHelper.toJson(parametrosDelegate
                        .getCiudadesList(new RegionBean(option, ""))));

            }
            else {
                out.print(JsonHelper.toJson(parametrosDelegate
                        .getComunasList(new RegionBean(region, ""), new CiudadBean(option, ""))));
            }

        } catch (DAOException e) {
            // TODO Auto-generated catch block
            LOGGER.error("Error al cargar combo :"+combo, e);
        }
        return;

    }

    @Override
    protected void doPost(HttpServletRequest arg0, HttpServletResponse arg1)
            throws ServletException, IOException {
        doGet(arg0, arg1);
    }

}
