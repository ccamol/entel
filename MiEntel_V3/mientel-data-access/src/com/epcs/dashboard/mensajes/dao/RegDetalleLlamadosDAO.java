/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.dashboard.mensajes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.epcs.data.DataSourceConnection;

/**
 * Clase que permite la conexion al esquema de base de datos ENTEL_MIENTELV3
 * @author zmanotas (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs) 
 */
public class RegDetalleLlamadosDAO {

    /**
     * Logger para RegDetalleLlamadosDAO
     */
    private static final Logger LOGGER = Logger
            .getLogger(RegDetalleLlamadosDAO.class);
    private DataSourceConnection dsc;
    
    public RegDetalleLlamadosDAO() {
        dsc = DataSourceConnection.getInstance();
    }
    
    /**
     * Metodo que realiza una consulta al valor del campo FLAG_CONSULTADO 
     * en la tabla MIE_NEG_DETLLAMADOSMPT
     * @param numeroCuenta
     * @param msisdn
     * @param aaa
     * @param folio
     * @return
     * @throws SQLException
     */
    public Boolean consultar(String numeroCuenta, String msisdn, String aaa, String folio) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Boolean detalleConsultado = false;
        
        try {
            con = dsc.getConnection();
            ps = con.prepareStatement("SELECT FLAG_CONSULTADO FROM MIE_NEG_DETLLAMADOSMPT WHERE NMRO_CUENTA = ? AND NMRO_MSISDN = ? AND NMRO_AAA = ? AND NMRO_FOLIO = ? AND FLAG_CONSULTADO = 1");
            ps.setString(1, numeroCuenta);
            ps.setString(2, msisdn);
            ps.setString(3, aaa);
            ps.setString(4, folio);
            
            rs = ps.executeQuery();            
            detalleConsultado = rs.next();            
            
            String mensajeLog = "Consulta realizada sobre la tabla MIE_NEG_DETLLAMADOSMPT con los parametros:\n";
            mensajeLog += "numeroCuenta: " + numeroCuenta + "\n";
            mensajeLog += "msisdn: " + msisdn + "\n";
            mensajeLog += "aaa: " + aaa + "\n";
            mensajeLog += "folio: " + folio;
            LOGGER.info(mensajeLog);
        } catch (SQLException e) {
            LOGGER.error("Error al realizar consulta sobre la tabla MIE_NEG_DETLLAMADOSMPT ", e);
        } catch (Exception e) {
            LOGGER.error("Exception al realizar consulta sobre la tabla MIE_NEG_DETLLAMADOSMPT ", e);
        } finally {
            dsc.closeConnection(con, ps, rs);    
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
    public void insertar(String numeroCuenta, String msisdn, String aaa, String folio, String tipoDocumento, String periodo) {
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
            
            String mensajeLog = "Insercion realizada sobre la tabla MIE_NEG_DETLLAMADOSMPT con los parametros:\n";
            mensajeLog += "numeroCuenta: " + numeroCuenta + "\n";
            mensajeLog += "msisdn: " + msisdn + "\n";
            mensajeLog += "aaa: " + aaa + "\n";
            mensajeLog += "folio: " + folio + "\n";
            mensajeLog += "aaa: " + tipoDocumento + "\n";
            mensajeLog += "aaa: " + periodo + "\n";
            LOGGER.info(mensajeLog);
        } catch (SQLException e) {
            LOGGER.error("Error al realizar insercion sobre la tabla MIE_NEG_DETLLAMADOSMPT ", e);
        } catch (Exception e) {
            LOGGER.error("Exception al realizar insercion sobre la tabla MIE_NEG_DETLLAMADOSMPT ", e);
        } finally {
            dsc.closeConnection(con, ps, null);
        }
    }
}
