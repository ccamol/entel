package com.epcs.cliente.perfil.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.epcs.bean.OfertaBolsaBlindaje;
import com.epcs.data.DataSourceConnectionBlindaje;


public class BolsaBlindajeDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
    /**
     * Logger para OfertaBlindajeDAO
     */
    private static final Logger LOGGER = Logger
            .getLogger(OfertaBlindajeEntelDAO.class);
    
    java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("ddMMyyyy");
    public static final int TIPO_OFERTA_BLINDAJE = 0;
    
    private DataSourceConnectionBlindaje dsc;
    
    public BolsaBlindajeDAO() {
        dsc = DataSourceConnectionBlindaje.getInstance();
    }   



public OfertaBolsaBlindaje ObtenerBolsa(String movil) {
    	
	Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    OfertaBolsaBlindaje bolsa = null;
    try {  
    	        	
	    	StringBuffer sb = new StringBuffer(200);
		        
	        sb.append("SELECT CONTR_BOLSA.CODI_BOLSA, CONTR_BOLSA.CODI_OFERTA, BOLSA.NOMB_BOLSA, BOLSA.DESC_CONDCOMP1, ");
	        sb.append("BOLSA.DESC_CONDCOMP2, BOLSA.VLOR_TIPOBOLSA, ");
	        sb.append("BOLSA.DESC_CONDCOMP3, BOLSA.DESC_CONDCOMP4, BOLSA.DESC_CONDCOMP5, BOLSA.DESC_CONDCOMP6, ");
	        sb.append("BOLSA.DESC_ARG1, BOLSA.DESC_ARG2, BOLSA.DESC_ARG3, BOLSA.DESC_PROMO, BOLSA.VLOR_BOLSA ");
	        sb.append("FROM PCD_MAE_BOLSAS BOLSA, PCD_REL_CONTRATABOLSAS CONTR_BOLSA WHERE BOLSA.CODI_BOLSA = CONTR_BOLSA.CODI_BOLSA ");
	        sb.append("AND CONTR_BOLSA.CODI_OFERTA IN (SELECT CODI_OFERTA FROM PCD_NEG_OFERTA WHERE NMRO_MOVIL_C = ?) AND ROWNUM <= 1 ");
     
	        
            con = dsc.getConnection();           
            ps = con.prepareStatement(sb.toString());
            
            ps.setString(1, movil);
            rs = ps.executeQuery();

            while (rs.next()) {
            	bolsa = new OfertaBolsaBlindaje();
            	
                bolsa.setCodigo(rs.getInt("CODI_OFERTA"));
                bolsa.setCodigoBolsa(rs.getString("CODI_BOLSA"));
                bolsa.setNombreBolsa(rs.getString("NOMB_BOLSA"));
                bolsa.setTipoBolsa(rs.getString("VLOR_TIPOBOLSA"));
                bolsa.setDescripcionArg1(rs.getString("DESC_CONDCOMP1"));
                bolsa.setDescripcionArg2(rs.getString("DESC_CONDCOMP2"));
                bolsa.setDescripcionArg3(rs.getString("DESC_CONDCOMP3"));
                bolsa.setDescripcionArg4(rs.getString("DESC_CONDCOMP4"));
                bolsa.setDescripcionArg5(rs.getString("DESC_CONDCOMP5"));
                bolsa.setDescripcionArg6(rs.getString("DESC_CONDCOMP6"));
                bolsa.setDescripcionBolsa1(rs.getString("DESC_ARG1"));
                bolsa.setDescripcionBolsa2(rs.getString("DESC_ARG2"));
                bolsa.setDescripcionBolsa3(rs.getString("DESC_ARG3"));
                bolsa.setDescripcionBolsa4(rs.getString("DESC_PROMO"));
                bolsa.setValorBolsa(rs.getInt("VLOR_BOLSA"));

            }
        
        String mensajeLog = "SELECT  realizado sobre la tabla PCD_MAE_BOLSA con los parametros:\n";
        mensajeLog += "movil: " + movil + "\n";            
        
        LOGGER.info(mensajeLog);            
    } catch (SQLException e) {
        LOGGER.error("Error al realizar SELECT sobre la tabla PCD_MAE_BOLSAS ", e);
    } catch (Exception e) {
        LOGGER.error("Exception al realizar SELECT sobre la tabla PCD_MAE_BOLSAS", e);
    } finally {
        dsc.closeConnection(con, ps, null);
    }
    
    return bolsa;
  }


public boolean movilTieneOferta(String movil) {
	
	Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    boolean tieneOferta = false;
    
    try {  
    	        	
	    	StringBuffer sb = new StringBuffer(200);
		        
	        sb.append("SELECT COUNT(*) ");
	        sb.append("FROM PCD_NEG_OFERTA WHERE NMRO_MOVIL_C = ? AND SLEC_DISPONIBLE = 1 AND SLEC_SOLICITO = 0 ");
    
	        
            con = dsc.getConnection();           
            ps = con.prepareStatement(sb.toString());
            
            ps.setString(1, movil);
            rs = ps.executeQuery();

            if (rs.next()) {
            	
            	tieneOferta = (rs.getInt(1) > 0);
            }
        
        String mensajeLog = "SELECT  realizado sobre la tabla PCD_MAE_BOLSA con los parametros:\n";
        mensajeLog += "movil: " + movil + "\n";            
        
        LOGGER.info(mensajeLog);            
    } catch (SQLException e) {
        LOGGER.error("Error al realizar SELECT sobre la tabla PCD_MAE_BOLSAS ", e);
    } catch (Exception e) {
        LOGGER.error("Exception al realizar SELECT sobre la tabla PCD_MAE_BOLSAS", e);
    } finally {
        dsc.closeConnection(con, ps, null);
    }
    
    return tieneOferta;
  }


public void marcaOfertaBlindajeContratada(long codOferta) {
	
	Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    
    try {  
    	        	
	    	StringBuffer sb = new StringBuffer(200);
            
            sb.append("UPDATE PCD_NEG_OFERTA ");
            sb.append("SET SLEC_SOLICITO = 1 , FECH_SOLICITO = SYSDATE ");
            sb.append("WHERE CODI_OFERTA = ? ");
            
        
            con = dsc.getConnection();           
            ps = con.prepareStatement(sb.toString());
            
            ps.setLong(1, codOferta);
            rs = ps.executeQuery();

            String mensajeLog = "UPDATE  realizado sobre la tabla PCD_MAE_BOLSA con los parametros:\n";
            mensajeLog += "movil: " + codOferta + "\n";            
            
            LOGGER.info(mensajeLog);     
                   
    } catch (SQLException e) {
        LOGGER.error("Error al realizar SELECT sobre la tabla PCD_MAE_BOLSAS ", e);
    } catch (Exception e) {
        LOGGER.error("Exception al realizar SELECT sobre la tabla PCD_MAE_BOLSAS", e);
    } finally {
        dsc.closeConnection(con, ps, null);
    }
    
  }

public void rechazaOfertaBlindaje(long codOferta) {
	
	Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    
    try {      	        	
	    	StringBuffer sb = new StringBuffer(200);           
            
            sb.append("UPDATE PCD_NEG_OFERTA ");
            sb.append("SET SLEC_RECHAZO = 1 , FECH_RECHAZO = SYSDATE ");
            sb.append("WHERE CODI_OFERTA = ? AND SLEC_SOLICITO = 0 ");
            
            con = dsc.getConnection();           
            ps = con.prepareStatement(sb.toString());
            
            ps.setLong(1, codOferta);
            rs = ps.executeQuery();

            String mensajeLog = "UPDATE  realizado sobre la tabla PCD_NEG_OFERTA con los parametros:\n";
            mensajeLog += "movil: " + codOferta + "\n";            
            
            LOGGER.info(mensajeLog);     
                   
    } catch (SQLException e) {
        LOGGER.error("Error al realizar UPDATE sobre la tabla PCD_MAE_BOLSAS ", e);
    } catch (Exception e) {
        LOGGER.error("Exception al realizar UPDATE sobre la tabla PCD_MAE_BOLSAS", e);
    } finally {
        dsc.closeConnection(con, ps, null);
    }
    
  }


	public void marcarOtroCanalBlindaje(long codOferta) {
		
		Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    
	    
	    try {      	        	
		    	StringBuffer sb = new StringBuffer(200);           
	            
		        sb.append("UPDATE PCD_NEG_OFERTA ");
		        sb.append("SET SLEC_SOLICITO = 2 , FECH_SOLICITO = SYSDATE ");
		        sb.append("WHERE CODI_OFERTA = ? ");
	            
	            con = dsc.getConnection();           
	            ps = con.prepareStatement(sb.toString());
	            
	            ps.setLong(1, codOferta);
	            rs = ps.executeQuery();
	
	            String mensajeLog = "UPDATE  realizado sobre la tabla PCD_NEG_OFERTA con los parametros:\n";
	            mensajeLog += "movil: " + codOferta + "\n";            
	            
	            LOGGER.info(mensajeLog);     
	                   
	    } catch (SQLException e) {
	        LOGGER.error("Error al realizar UPDATE sobre la tabla PCD_NEG_OFERTA ", e);
	    } catch (Exception e) {
	        LOGGER.error("Exception al realizar UPDATE sobre la tabla PCD_NEG_OFERTA", e);
	    } finally {
	        dsc.closeConnection(con, ps, null);
	    }
	    
	}

}