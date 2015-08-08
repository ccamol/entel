/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.cliente.perfil.dao;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.apache.log4j.Logger;

import com.epcs.data.DataSourceConnectionBlindaje;

/**
 * @author jvasquez (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class HistorialPrepagoPlusDAO implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /**
     * Logger para HistorialPrepagoPlusDAO
     */
    private static final Logger LOGGER = Logger.getLogger(HistorialPrepagoPlusDAO.class);
    private DataSourceConnectionBlindaje dsc;
    
    public HistorialPrepagoPlusDAO() {
        dsc = DataSourceConnectionBlindaje.getInstance();
    }
    
    /**
     * Metodo que valida si se debe desplegar lightbox PP Plus para el movil indicado
     * @param msisdn
     */
    public boolean validarDespliegueLightbox(String msisdn) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean desplegar = true;
        
        try {
            con = dsc.getConnection();
            ps = con.prepareStatement("SELECT * FROM PCD_NEG_DISABLELIGHTBOXPPAV WHERE NMRO_MOVIL = ? AND FLAG_DISABLED = 1");
            ps.setString(1, msisdn);
            
            rs = ps.executeQuery();
            if(rs.next()){
            	desplegar = false;
            }
            
            String mensajeLog = "Select realizado sobre la tabla PCD_NEG_DISABLELIGHTBOXPPAV.\n";
            if(desplegar)
            	mensajeLog += "Despliegue de lightbox PP Plus HABILITADO para movil: " + msisdn + ".\n";
            else
            	mensajeLog += "Despliegue de lightbox PP Plus DESHABILITADO para movil: " + msisdn + ".\n";
            	
            LOGGER.info(mensajeLog);            
        } catch (SQLException e) {
            LOGGER.error("Error al realizar Select sobre la tabla PCD_NEG_DISABLELIGHTBOXPPAV. Msisdn: " + msisdn + "\n" + e.getMessage(), e);
        } catch (Exception e) {
            LOGGER.error("Exception al realizar Select sobre la tabla PCD_NEG_DISABLELIGHTBOXPPAV. Msisdn: " + msisdn + "\n" + e.getMessage(), e);
        } finally {
            dsc.closeConnection(con, ps, null);
        }
        
        return desplegar;
    }
    
    /**
     * Metodo que inserta un registro en la tabla PCD_NEG_DISABLELIGHTBOXPPAV
     * @param msisdn
     */
    public void registrarRechazoDespliegueLightbox(String msisdn) {
        Connection con = null;
        PreparedStatement ps = null;
        
        try {
            con = dsc.getConnection();
            ps = con.prepareStatement("INSERT INTO PCD_NEG_DISABLELIGHTBOXPPAV VALUES (?,1,sysdate)");
            ps.setString(1, msisdn);
            
            ps.executeUpdate();
            
            String mensajeLog = "Insercion realizada sobre la tabla PCD_NEG_DISABLELIGHTBOXPPAV con los parametros:\n";
            mensajeLog += "msisdn: " + msisdn + "\n";
            LOGGER.info(mensajeLog);            
        } catch (SQLException e) {
            LOGGER.error("Error al realizar insercion sobre la tabla PCD_NEG_DISABLELIGHTBOXPPAV. Msisdn: " + msisdn + "\n" + e.getMessage(), e);
        } catch (Exception e) {
            LOGGER.error("Exception al realizar insercion sobre la tabla PCD_NEG_DISABLELIGHTBOXPPAV. Msisdn: " + msisdn + "\n" + e.getMessage(), e);
        } finally {
            dsc.closeConnection(con, ps, null);
        }
    }
    
    /**
     * Metodo que ejecuta procedimiento almacenado, encargado de truncar la tabla PCD_NEG_DISABLELIGHTBOXPPAV
     */
    public String [] borrarHistorialDespliegueLightbox() {
        Connection con = null;
        CallableStatement cStmt = null;
        String [] datos = new String [3];
        
        try {
            con = dsc.getConnection();
            con.setAutoCommit(false);
            
            cStmt = con.prepareCall("{call ownblin.PRO_BLIN_TRUNCATABLA(?, ?, ?, ?)}");
            
            //Parametro de entrada del procedimiento almacenado
            cStmt.setString("ntabla", "PCD_NEG_DISABLELIGHTBOXPPAV");
            
            //Definimos los tipos de los parametros de salida del procedimiento almacenado
            cStmt.registerOutParameter("i_retorno", Types.NUMERIC);
            cStmt.registerOutParameter("sqlcod", Types.NUMERIC);
            cStmt.registerOutParameter("sqlerr", Types.VARCHAR);
            
            //Ejecuta el procedimiento almacenado.
            cStmt.execute();
            
            int codigoRespuesta = cStmt.getInt("i_retorno");
            if(codigoRespuesta != 0){
            	datos[0] = Integer.toString(codigoRespuesta);
            	datos[1] = cStmt.getString("sqlcod");
            	datos[2] = cStmt.getString("sqlerr");
            	LOGGER.error("Error al ejecutar procedimiento ownblin.PRO_BLIN_TRUNCATABLA. \n"+datos[1]+": "+datos[2]);
            }else{
            	datos[0] = Integer.toString(codigoRespuesta);
            	datos[1] = cStmt.getString("sqlcod");
            	datos[2] = cStmt.getString("sqlerr");
            	LOGGER.info("Truncado de la tabla PCD_NEG_DISABLELIGHTBOXPPAV realizado con exito.\n");
            }
            
        } catch (SQLException sqle) {
        	try{
        		if(con != null)
        			con.rollback();
        	}catch(Exception rollex){
        		LOGGER.error("Error haciendo rollback. "+rollex.getMessage(), rollex);
        	}
            LOGGER.error("Error al realizar truncado de la tabla PCD_NEG_DISABLELIGHTBOXPPAV. "+sqle.getMessage(), sqle);
        } catch (Exception e) {
        	try{
        		if(con != null)
        			con.rollback();
        	}catch(Exception rollex){
        		LOGGER.error("Error haciendo rollback. "+rollex.getMessage(), rollex);
        	}
            LOGGER.error("Exception al realizar truncado de la tabla PCD_NEG_DISABLELIGHTBOXPPAV. "+e.getMessage(), e);
        } finally {
            dsc.closeConnection(con, null, null);
            try{
            	if(cStmt != null)
            		cStmt.close();
        	}catch(Exception rollex){
        		LOGGER.error("Error cerrando CallableStatement. "+rollex.getMessage(), rollex);
        	}
        }
        return datos;
    }
    

}
