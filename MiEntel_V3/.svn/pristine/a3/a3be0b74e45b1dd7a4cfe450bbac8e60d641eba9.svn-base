/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.formulariosatisfaccion.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.epcs.data.DataSourceConnection;

/**
 * @author zmanotas (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class RegBugYComentariosDAO {

    /**
     * Logger para RegBugYComentariosDAO
     */
    private static final Logger LOGGER = Logger
            .getLogger(RegBugYComentariosDAO.class);
    private DataSourceConnection dsc;
    
    public RegBugYComentariosDAO() {
        dsc = DataSourceConnection.getInstance();
    }
    
    /**
     * Metodo que inserta un registro en la tabla MIE_NEG_BUGYCOMENTARIOS
     * @param msisdn
     * @param mercado
     * @param nombres
     * @param apellidos
     * @param telAdicional
     * @param eMail
     * @param mensaje
     * @throws SQLException
     */
    public void insertar(String msisdn, String rut, String mercado, String nombres, String apellidos, String telAdicional, String eMail, String mensaje) {
        Connection con = null;
        PreparedStatement ps = null;
        
        try {
            con = dsc.getConnection();
            ps = con.prepareStatement("INSERT INTO MIE_NEG_BUGYCOMENTARIOS (CORR_BUGYCOMENTARIOS, NMRO_MSISDN, NRUT_USUARIO, INDC_MERCADO, NOMB_NOMBRES, NOMB_APELLIDOS, NMRO_TELADICIONAL, VLOR_EMAIL, VLOR_MENSAJE) VALUES (SEQ_MIE_BUGYCOMENTARIOS.NEXTVAL,?,?,?,?,?,?,?,?)");
            ps.setString(1, msisdn);
            ps.setString(2, rut);
            ps.setString(3, mercado);
            ps.setString(4, nombres);
            ps.setString(5, apellidos);
            ps.setString(6, telAdicional);
            ps.setString(7, eMail);
            ps.setString(8, mensaje);
            
            ps.executeUpdate();
            
            String mensajeLog = "Insercion realizada sobre la tabla MIE_NEG_BUGYCOMENTARIOS con los parametros:\n";
            mensajeLog += "msisdn: " + msisdn + "\n";
            mensajeLog += "rut: " + rut + "\n";
            mensajeLog += "mercado: " + mercado + "\n";
            mensajeLog += "nombres: " + nombres + "\n";
            mensajeLog += "apellidos: " + apellidos + "\n";
            mensajeLog += "telAdicional: " + telAdicional + "\n";
            mensajeLog += "eMail: " + eMail + "\n";
            mensajeLog += "mensaje: " + mensaje;
            LOGGER.info(mensajeLog);            
        } catch (SQLException e) {
            LOGGER.error("Error al realizar insercion sobre la tabla MIE_NEG_BUGYCOMENTARIOS ", e);
        } catch (Exception e) {
            LOGGER.error("Exception al realizar insercion sobre la tabla MIE_NEG_BUGYCOMENTARIOS ", e);
        } finally {
            dsc.closeConnection(con, ps, null);
        }
    }
    

}
