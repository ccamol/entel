/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.billing.recarga.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.epcs.data.DataSourceConnection;

/**
 * Clase que realiza operaciones sobre la tabla MIE_NEG_RECARGAPROMO
 * 
 * @author zmanotas
 * 
 */
public class RecargaPromoPPDAO {

	/**
	 * Logger para RecargaPromoPPDAO
	 */
	private static final Logger LOGGER = Logger
			.getLogger(RecargaPromoPPDAO.class);
	private DataSourceConnection dsc;

	public RecargaPromoPPDAO() {
		dsc = DataSourceConnection.getInstance();
	}
	
    /**
     * Metodo que inserta datos del usuario que cancela la promocion de recarga PP
     * en la tabla MIE_NEG_RECARGAPROMO    
     * @param msisdn
     * @return
     * @throws SQLException
     */
    public void cancelarDesplieguePromo(String msisdn) {
        Connection con = null;
        PreparedStatement ps = null;
        
        try {
            con = dsc.getConnection();
            ps = con.prepareStatement("INSERT INTO MIE_NEG_RECARGAPROMO (CODI_PROMO, NMRO_MOVIL, ESTD_CANCELADO) VALUES (SEQ_MIE_RECARGAPROMO.NEXTVAL, ?, 1)");
            ps.setString(1, msisdn);        
            
            ps.executeUpdate();
            
            String mensajeLog = "Insercion realizada sobre la tabla MIE_NEG_RECARGAPROMO con los parametros:\n";            
            mensajeLog += "msisdn: " + msisdn + "\n";
            LOGGER.info(mensajeLog);
        } catch (SQLException e) {
            LOGGER.error("Error al realizar insercion sobre la tabla MIE_NEG_RECARGAPROMO ", e);
        } catch (Exception e) {
            LOGGER.error("Exception al realizar insercion sobre la tabla MIE_NEG_RECARGAPROMO ", e);
        } finally {
            dsc.closeConnection(con, ps, null);
        }
    }
    
    /**
     * Metodo que consulta si hay datos para un movil en la tabla MIE_NEG_RECARGAPROMO
     * @param msisdn
     * @return
     */
    public boolean validarDesplieguePromo(String msisdn, String fechaInicioPromo, String fechaFinPromo) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean desplegarPromo = true;
        
        try {
            con = dsc.getConnection();
            ps = con.prepareStatement("SELECT NMRO_MOVIL FROM MIE_NEG_RECARGAPROMO WHERE NMRO_MOVIL = ? " +
            		"AND ESTD_CANCELADO = 1 AND TO_DATE(FECH_CANCELADO, 'DD/MM/YYYY HH24:MI:SS') " +
            		"BETWEEN TO_DATE(?, 'DD/MM/YYYY HH24:MI:SS') AND TO_DATE(?, 'DD/MM/YYYY HH24:MI:SS')");
            ps.setString(1, msisdn);        
            ps.setString(2, fechaInicioPromo);
            ps.setString(3, fechaFinPromo);
            
            rs = ps.executeQuery();
            if (rs.next()) {
            	desplegarPromo = false;
            }
            
        } catch (SQLException e) {
            LOGGER.error("Error al realizar consulta sobre la tabla MIE_NEG_RECARGAPROMO ", e);
        } catch (Exception e) {
            LOGGER.error("Exception al realizar consulta sobre la tabla MIE_NEG_RECARGAPROMO ", e);
        } finally {
            dsc.closeConnection(con, ps, rs);
        }
        
        return desplegarPromo;
    }    
}
