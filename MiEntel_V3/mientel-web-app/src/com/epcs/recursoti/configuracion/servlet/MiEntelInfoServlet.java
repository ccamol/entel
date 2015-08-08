/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.recursoti.configuracion.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epcs.recursoti.configuracion.JsonHelper;

/**
 * Servlet implementation class MiEntelInfoServlet<br>
 * Este Servlet entrega la informacion de la version de Mi Entel en que se encuentra.
 * 
 * 
 * @author jlopez (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 */
public class MiEntelInfoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    private static final String CONTENT_TYPE = "application/json";

    /**
     * Logger para MiEntelInfoServlet
     */
    private static final Logger LOGGER = Logger
            .getLogger(MiEntelInfoServlet.class);

    
    private Map<String, String> infoAtts;


	/**
	 * @see Servlet#init(ServletConfig)
	 */
    public void init(ServletConfig config) throws ServletException {

        infoAtts = new HashMap<String, String>();

        InputStream input = config.getServletContext().getResourceAsStream(
                "/META-INF/MANIFEST.MF");

        try {

            Manifest manifest = new Manifest(input);

            LOGGER.info("Loading Manifest Main attributes");
            Attributes mainAtts = manifest.getMainAttributes();
            loadAttributes(mainAtts);

            Map<String, Attributes> mfEntries = manifest.getEntries();
            Set<String> mfEntriesKeys = mfEntries.keySet();
            LOGGER.info("Loading Manifest attributes:");
            for (Iterator<String> it = mfEntriesKeys.iterator(); it.hasNext();) {
                loadAttributes(mfEntries.get(it.next()));
            }

        } catch (Exception e) {
            LOGGER.error("no se pudo leer manifest", e);
        } finally {
            try {
                input.close();
            } catch (Exception e) {
                LOGGER.warn("IOException", e);
            }
        }

    }
	
	private void loadAttributes(Attributes attributes) {
	    
        Set<Object> mainKeys = attributes.keySet();
        for (Iterator<Object> it = mainKeys.iterator(); it.hasNext();) {
            Object key = it.next();
            infoAtts.put(key.toString(), attributes.getValue(key.toString()));
        }
	    
	}

	/**
	 * Delivers Json with manifest values
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOGGER.info("Info requested");
        LOGGER.info("    Host: " + request.getRemoteHost());
        LOGGER.info("    Addr: " + request.getRemoteAddr());
	    String jsonAtts = JsonHelper.toJson(infoAtts);
	    enviarRespuesta(response, jsonAtts);
	}

	/**
	 * Calls doGet
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

    private void enviarRespuesta(HttpServletResponse response, String salida) {
        PrintWriter out = null;
        try {
            response.setContentType(CONTENT_TYPE);
            out = response.getWriter();
            out.println(salida);
        } catch (IOException ex) {
            LOGGER.error(ex);
        } finally {
            out.close();
        }

    }
}
