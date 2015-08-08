/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.formulariocontacto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.epcs.data.DataSourceConnection;

/**
 * @author zmanotas (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class RegBugYComentariosDAO {
    
    private DataSourceConnection dsc;
    
    public RegBugYComentariosDAO() throws NamingException, Exception {
        dsc = new DataSourceConnection();
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
    public void insertar(String msisdn, String mercado, String nombres, String apellidos, String telAdicional, String eMail, String mensaje) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        
        try {
            con = dsc.getConnection();
            ps = con.prepareStatement("INSERT INTO MIE_NEG_BUGYCOMENTARIOS VALUES (SEQ_MIE_BUGYCOMENTARIOS.NEXTVAL,?,?,?,?,?,?,?)");
            ps.setString(1, msisdn);
            ps.setString(2, mercado);
            ps.setString(3, nombres);
            ps.setString(4, apellidos);
            ps.setString(5, telAdicional);
            ps.setString(6, eMail);
            ps.setString(7, mensaje);
            
            ps.executeUpdate();
        } finally {
            dsc.cerrarConexion(con, ps, null);
        }
    }
    

}
