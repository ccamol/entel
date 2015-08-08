/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.dashboard.mensajes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.epcs.data.DataSourceConnection;

/**
 * Clase que permite la conexion al esquema de base de datos ENTEL_MIENTELV3
 * @author zmanotas (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs) 
 */
public class RegDetalleLlamadosDAO {
    
    private DataSourceConnection dsc;
    
    public RegDetalleLlamadosDAO() throws NamingException, Exception {
        dsc = new DataSourceConnection();
    }
    
    /**
     * Metodo que realiza una consulta del valor de campo FLAG_CONSULTADO 
     * en la tabla MIE_NEG_DETLLAMADOSMPT, si el valor de este campo es 0, significa
     * que el Detalle de Llamados no ha sido consultado, lo cual indica que no se debe
     * mostrar un Mensaje Para Ti de este servicio.
     * @param numeroCuenta
     * @param msisdn
     * @param aaa
     * @param folio
     * @return
     * @throws SQLException
     */
    public Boolean consultar(String numeroCuenta, String msisdn, String aaa, String folio) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Boolean detalleConsultado = null;
        
        try {
            con = dsc.getConnection();
            ps = con.prepareStatement("SELECT FLAG_CONSULTADO FROM MIE_NEG_DETLLAMADOSMPT WHERE NMRO_CUENTA = ? AND NMRO_MSISDN = ? AND NMRO_AAA = ? AND NMRO_FOLIO = ? AND FLAG_CONSULTADO = 1");
            ps.setString(1, numeroCuenta);
            ps.setString(2, msisdn);
            ps.setString(3, aaa);
            ps.setString(4, folio);
            
            rs = ps.executeQuery();            
            detalleConsultado = rs.next();
        } finally {
            dsc.cerrarConexion(con, ps, rs);    
        }
        return detalleConsultado;
    }
    
    /**
     * Metodo que inserta un registro en la tabla MIE_NEG_DETLLAMADOSMPT
     * @param numeroCuenta
     * @param msisdn
     * @param aaa
     * @param folio
     * @param tipoDocumento
     * @param periodo
     * @throws SQLException
     */
    public void insertar(String numeroCuenta, String msisdn, String aaa, String folio, String tipoDocumento, String periodo) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        
        try {
            con = dsc.getConnection();
            ps = con.prepareStatement("INSERT INTO MIE_NEG_DETLLAMADOSMPT VALUES (SEQ_MIE_DETLLAMADOSMPT.NEXTVAL,?,?,?,?,?,?,1)");
            ps.setString(1, numeroCuenta);
            ps.setString(2, msisdn);
            ps.setString(3, aaa);
            ps.setString(4, folio);
            ps.setString(5, tipoDocumento);
            ps.setString(6, periodo);
            
            ps.executeUpdate();
        } finally {
            dsc.cerrarConexion(con, ps, null);
        }
    }
}
