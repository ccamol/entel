/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.cliente.perfil.dao;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.epcs.cliente.perfil.bean.Oferta;

import com.epcs.data.DataSourceConnectionEntelVisa;;

/**
 * @author wbrochero (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class OfertaVisaEntelDAO implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /**
     * Logger para OfertaVisaEntelDAO
     */
    private static final Logger LOGGER = Logger
            .getLogger(OfertaVisaEntelDAO.class);
    private DataSourceConnectionEntelVisa dsc;
    
    public OfertaVisaEntelDAO() {
        dsc = DataSourceConnectionEntelVisa.getInstance();
    }
    
    /**
     * Marca la oferta entel visa, para no volver a mostrarla al cliente
     * @param codigo
     * @param rechazado     
     * @throws SQLException
     */
    public void marcarOfertaVisa(long codigo, boolean rechazado) {
        Connection con = null;
        PreparedStatement ps = null;
        
        int rechazo = (rechazado == true) ? 1 : 0;
        
        try {
            con = dsc.getConnection();
            
            ps = con.prepareStatement("UPDATE PCD_NEG_VISA SET FECH_RECHAZO = SYSDATE, FLAG_RECHAZADO = ? WHERE CODI_VISA = ? ");
            ps.setInt(1, rechazo);
            ps.setLong(2, codigo);
            
            ps.executeUpdate();
            
            String mensajeLog = "UPDATE  realizada sobre la tabla PCD_NEG_VISA con los parametros:\n";
            mensajeLog += "codigo: " + codigo + "\n";
            mensajeLog += "rechazo: " + rechazo + "\n";
            
            LOGGER.info(mensajeLog);            
        } catch (SQLException e) {
            LOGGER.error("Error al realizar insercion sobre la tabla PCD_NEG_VISA ", e);
        } catch (Exception e) {
            LOGGER.error("Exception al realizar insercion sobre la tabla PCD_NEG_VISA", e);
        } finally {
            dsc.closeConnection(con, ps, null);
        }
    }
    
   
    /**
     * Marca la oferta entel visa con la fecha en la que fue visitada
     * @param codigo        
     * @throws SQLException
     */
    public void actualizarVisitaVisaEntel(long codigo) throws Exception {

    	Connection con = null;
        PreparedStatement ps = null;

        try {
            StringBuffer sb = new StringBuffer(500);

            sb.append("UPDATE  PCD_NEG_VISA  ");
            sb.append("SET FECH_VISITA = SYSDATE ");
            sb.append("WHERE CODI_VISA = ? ");

            con = dsc.getConnection();           
            ps = con.prepareStatement(sb.toString());
            ps.setLong(1, codigo);            
            ps.executeUpdate();
            String mensajeLog = "UPDATE  realizada sobre la tabla PCD_NEG_VISA con los parametros:\n";
            mensajeLog += "codigo: " + codigo + "\n";
            LOGGER.info(mensajeLog);    
            
        } catch (SQLException e) {
            LOGGER.error("Error al realizar insercion sobre la tabla PCD_NEG_VISA ", e);
        } catch (Exception e) {
            LOGGER.error("Exception al realizar insercion sobre la tabla PCD_NEG_VISA ", e);
        } finally {
            dsc.closeConnection(con, ps, null);
        }    
    }

    /**
     * Consultar la oferta visa para cargar los datos de esta en un objeto con atributos de la oferta.
     * @param movil    
     * @param rut 
     * @param estado     
     * @throws SQLException
     */
    public Oferta getVisaEntelByMovilRutEstado(String movil, String rut, int estado) throws Exception {

    	Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        Oferta visaEntel = null;

        try {

            StringBuffer sb = new StringBuffer(1500);

            sb.append("SELECT ");
            sb.append("CASE WHEN  FLAG_RECHAZADO IS NULL THEN 0 ELSE FLAG_RECHAZADO END AS FLAG_RECHAZADO, ");
            sb.append("CODI_VISA, NMRO_MOVIL, NRUT_CLIENTE, INDC_TIPOOFERTA  ");
            sb.append("FROM  PCD_NEG_VISA ");
            sb.append("WHERE NMRO_MOVIL = ? AND NRUT_CLIENTE = ? AND INDC_TIPOOFERTA = ?");
          
            con = dsc.getConnection();           
            ps = con.prepareStatement(sb.toString());
           
            ps.setString(1, movil);
            ps.setString(2, rut);
            ps.setInt(3, estado);


            rs = ps.executeQuery();
            while (rs.next()) {
                visaEntel = new Oferta();
                visaEntel.setOfertaId(Long.toString(rs.getLong("CODI_VISA")));
                visaEntel.setMovil(rs.getString("NMRO_MOVIL"));
                visaEntel.setRut(rs.getString("NRUT_CLIENTE"));
                visaEntel.setRechazada((rs.getInt("FLAG_RECHAZADO") == 1) ? true : false);
                visaEntel.setEstado(rs.getInt("INDC_TIPOOFERTA")+"");
            }
            
            String mensajeLog = "SELECT  realizado sobre la tabla PCD_NEG_VISA con los parametros:\n";
            mensajeLog += "movil: " + movil + "\n";
            mensajeLog += "rut: " + rut + "\n";
            LOGGER.info(mensajeLog);     
            
        } catch (SQLException e) {
            LOGGER.error("Error al realizar insercion sobre la tabla PCD_NEG_VISA ", e);
        } catch (Exception e) {
            LOGGER.error("Exception al realizar insercion sobre la tabla PCD_NEG_VISA ", e);
        } finally {
            dsc.closeConnection(con, ps, null);
        }    
        return visaEntel;

    }

    /**
     * Insertar una oferta visa.
     * @param movil    
     * @param rut 
     * @param estado     
     * @throws SQLException
     */
    public void insertarVisitaVisaEntel(String movil, String rut, int estado) throws Exception {
    	
    	Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;        

        try {
            StringBuffer sb = new StringBuffer(500);

            sb.append("INSERT INTO  PCD_NEG_VISA  ");
            sb.append("(CODI_VISA, NRUT_CLIENTE, NMRO_MOVIL, INDC_TIPOOFERTA, FECH_VISITA,  FLAG_RECHAZADO) ");
            sb.append("VALUES (SEQ_MIE_REPORTEVISA.NEXTVAL, ?, ?, ?, SYSDATE, 0) ");

            con = dsc.getConnection();           
            ps = con.prepareStatement(sb.toString());
            
            ps.setString(1, rut);
            ps.setString(2, movil);
            ps.setInt(3, estado);
            rs = ps.executeQuery();

            String mensajeLog = "INSERTA  realizado sobre la tabla PCD_NEG_VISA con los parametros:\n";
            
            mensajeLog += "rut: " + rut + "\n";
            mensajeLog += "movil: " + movil + "\n";           
            mensajeLog += "estado: " + estado + "\n";
            LOGGER.info(mensajeLog);     
            
        } catch (SQLException e) {
            LOGGER.error("Error al realizar insercion sobre la tabla PCD_NEG_VISA ", e);
        } catch (Exception e) {
            LOGGER.error("Exception al realizar insercion sobre la tabla PCD_NEG_VISA ", e);
        } finally {
            dsc.closeConnection(con, ps, null);
        }    
    }
    
public int consultarAlternancia(String rut, String movil, String ofertas) throws Exception {
        
		Connection con = null;
	    PreparedStatement ps = null;        
        CallableStatement cs = null;
        int resultado = -1;

        try {
            StringBuffer sb = new StringBuffer(200);
            sb.append("{ CALL PKG_MIE_ALTERNANCIA.PRO_MIE_ALTERNAROFERTAS(?,?,?,?) }");

            con = dsc.getConnection();           
            ps = con.prepareStatement(sb.toString());

            cs = con.prepareCall(sb.toString());

            cs.setString(1, rut);
            cs.setString(2, movil);
            cs.setString(3, ofertas);

            cs.registerOutParameter(4, java.sql.Types.INTEGER);

            cs.execute();

            resultado = cs.getInt(4);
            String mensajeLog = "Resultado en consultarAlternancia():::: :\n";            
            mensajeLog += "resultado: " + resultado + "\n";           
            LOGGER.info(mensajeLog); 
            
        } catch (SQLException e) {
            LOGGER.error("Error al realizar insercion sobre la tabla PCD_NEG_VISA ", e);
        } catch (Exception e) {
            LOGGER.error("Exception al realizar insercion sobre la tabla PCD_NEG_VISA ", e);
        } finally {
            dsc.closeConnection(con, ps, null);
        }  
        
        return resultado;
    } 
    
    

}
