/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.data;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

/**
 * @author zmanotas (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class DataSourceConnection implements Serializable {    
    
	private static final long serialVersionUID = 1L;
	
	/**
     * Logger para DataSourceConnection
     */
    private static final Logger LOGGER = Logger
            .getLogger(DataSourceConnection.class);
    private static DataSourceConnection instance;
    private DataSource dataSource;

    private DataSourceConnection() { 
        try {
            Context ctx = new InitialContext();
            dataSource = (DataSource) ctx.lookup("MientelV3_DS");
            LOGGER.info("DataSource inicializado");
        } catch (NamingException e) {
            LOGGER.error("Error al buscar nombre JNDI ", e);
        } catch (Exception e) {
            LOGGER.error("Exception al inicializar el DataSource ", e);
        }
    }
    
    public static DataSourceConnection getInstance() {
        if (instance == null) {
            instance = new DataSourceConnection();
        }
        return instance;
    }
    
    public Connection getConnection() {
        Connection con = null;        
        try {
            con = dataSource.getConnection();
            LOGGER.info("Conexion obtenida desde el DataSource");
        } catch (SQLException e) {
            LOGGER.error("Error al obtener conexion a la base de datos ", e);
        }
        return con;
    }
    
    /**
     * Metodo que cierra los recursos asociados a una conexion de la Base de Datos
     * @param con
     * @param ps
     * @param rs
     * @throws SQLException
     */    
    public void closeConnection(Connection con, PreparedStatement ps, ResultSet rs) {
        try {
            if (con != null) {
                con.close();
                con = null;
            }
            if (rs != null) {
                rs.close();
                rs = null;
            }
            if (ps != null) {
                ps.close();
                ps = null;
            }
            LOGGER.info("Conexion cerrada");
        } catch (SQLException e) {
            LOGGER.error("Error al cerrar conexion a la base de datos ", e);
        } catch (Exception e) {
            LOGGER.error("Exception al cerrar conexion a la base de datos ", e);
        }
    }
}
