/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.cliente.orden.dao;

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
public class TarifaDiariaAccesDAO {

    /**
     * Logger para TarifaDiariaDAO
     */
    private static final Logger LOGGER = Logger
            .getLogger(TarifaDiariaAccesDAO.class);
    private DataSourceConnection dsc;
    
    public TarifaDiariaAccesDAO() {
        dsc = DataSourceConnection.getInstance();
    }
    
   
    
    /**
     * Metodo que inserta datos del usuario que ingresa a tarifa diaria.
     * en la tabla MIE_NEG_TARIFADIARIAMPT    
     * @param msisdn
     * @param mercado
     * @param fecha Actual
     * @return
     * @throws SQLException
     */
    public void marcarVisita(String msisdn, String mercado,String fechaActual) {

        Connection con = null;
        PreparedStatement ps = null;
        
        try {
            con = dsc.getConnection();
            ps = con.prepareStatement("INSERT INTO MIE_NEG_TARIFADIARIAMPT VALUES (SEQ_MIE_TARIFADIARIAMPT.NEXTVAL,?,TO_TIMESTAMP(?, 'DD/MM/RR HH24:MI:SSXFF'),?)");
            ps.setString(1, msisdn);
            ps.setString(2, fechaActual);
            ps.setString(3, mercado);        
            
            ps.executeUpdate();
            
            String mensajeLog = "Insercion realizada sobre la tabla MIE_NEG_TARIFADIARIAMPT con los parametros:\n";            
            mensajeLog += "msisdn: " + msisdn + "\n";           
            mensajeLog += "fechaActual: " + fechaActual + "\n";
            mensajeLog += "mercado: " + mercado + "\n";            
            LOGGER.info(mensajeLog);
        } catch (SQLException e) {
            LOGGER.error("Error al realizar insercion sobre la tabla MIE_NEG_TARIFADIARIAMPT ", e);
        } catch (Exception e) {
            LOGGER.error("Exception al realizar insercion sobre la tabla MIE_NEG_TARIFADIARIAMPT ", e);
        } finally {
            dsc.closeConnection(con, ps, null);
        }
    }
}
