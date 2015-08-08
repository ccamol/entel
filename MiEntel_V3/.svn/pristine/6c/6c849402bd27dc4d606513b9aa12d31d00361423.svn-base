/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * @author zmanotas (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class DataSourceConnection {

    private DataSource dataSource;

    public DataSourceConnection() throws NamingException, Exception {
        Context ctx = new InitialContext();
        dataSource = (DataSource) ctx.lookup("MientelV3_DS");
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
    
    /**
     * Metodo que cierra los recursos asociados a una conexion de la Base de Datos
     * @param con
     * @param ps
     * @param rs
     * @throws SQLException
     */    
    public void cerrarConexion(Connection con, PreparedStatement ps, ResultSet rs) throws SQLException {
        if (rs != null) {
            rs.close();
            rs = null;
        }
        if (ps != null) {
            ps.close();
            ps = null;
        }
        if (con != null) {
            con.close();
            con = null;
        }
    }
}
