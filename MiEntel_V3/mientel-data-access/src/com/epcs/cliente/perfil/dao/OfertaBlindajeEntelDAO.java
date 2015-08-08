package com.epcs.cliente.perfil.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.epcs.cliente.perfil.bean.Oferta;
import com.epcs.cliente.perfil.bean.CriteriosBusquedaOferta;
import com.epcs.data.DataSourceConnectionBlindaje;

public class OfertaBlindajeEntelDAO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    /**
     * Logger para OfertaBlindajeDAO
     */
    private static final Logger LOGGER = Logger
            .getLogger(OfertaBlindajeEntelDAO.class);
    
    java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("ddMMyyyy");
    public static final int TIPO_OFERTA_BLINDAJE = 0;
    
    private DataSourceConnectionBlindaje dsc;
    
    public OfertaBlindajeEntelDAO() {
        dsc = DataSourceConnectionBlindaje.getInstance();
    }       
	
	/**
     *  Consultar la oferta Blindaje para cargar los datos de esta en un objeto con atributos de la oferta.
     * @param codigo
     * @param rechazado     
     * @throws SQLException
     */
    public List<Oferta> ConsultarOfertaBlindaje(String movil) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        Oferta blindaje = null; 
        List<Oferta> listaBlindaje = new ArrayList<Oferta>();
        
        try {  
        	
                StringBuffer sb = new StringBuffer(1000);
                sb.append("SELECT CODI_OFERTA, CODI_GRUPO_G");
                sb.append(", CASE WHEN CODI_BSCSOF IS NULL THEN '0'" +
                		  "		  WHEN CODI_BSCSOF = 'N/A' THEN '0'" +
                		  "		  ELSE CODI_BSCSOF END AS CODI_BSCSOF");
                sb.append(", CASE WHEN SLEC_VISITA IS NULL THEN 0 ELSE SLEC_VISITA END AS SLEC_VISITA");
                sb.append(", CASE WHEN SLEC_RECHAZO IS NULL THEN 0 ELSE SLEC_RECHAZO END AS SLEC_RECHAZO ");
                sb.append(", CASE WHEN SLEC_SOLICITO IS NULL THEN 0 ELSE SLEC_SOLICITO END AS SLEC_SOLICITO ");
                sb.append(", TO_CHAR(TO_DATE(FECH_SOLICITO),'DDMMYYYY') AS FECH_SOLICITO ");
                sb.append("FROM PCD_NEG_OFERTA ");
                sb.append("WHERE NMRO_MOVIL_C = ? ");
                
                sb.append("AND SLEC_DISPONIBLE = 1 ");
                sb.append("AND (SLEC_SOLICITO IS NULL OR SLEC_SOLICITO = 0 OR SLEC_SOLICITO = 3 OR SLEC_SOLICITO = 4) ");
                sb.append("AND (SLEC_RECHAZO IS NULL OR SLEC_RECHAZO = 0) ");
         

                con = dsc.getConnection();           
                ps = con.prepareStatement(sb.toString());
                
                ps.setString(1, movil);
                rs = ps.executeQuery();

                while (rs.next()) {
                	blindaje = new Oferta();
                	blindaje.setOfertaId(String.valueOf(rs.getInt("CODI_OFERTA")));
                	blindaje.setGrupoOferta(rs.getString("CODI_GRUPO_G"));
					blindaje.setCodBscsOferta(rs.getString("CODI_BSCSOF"));
                	blindaje.setVisitada((rs.getInt("SLEC_VISITA") == 1) ? true : false);
                	blindaje.setRechazada((rs.getInt("SLEC_RECHAZO") == 1) ? true : false);
                	blindaje.setTipo(TIPO_OFERTA_BLINDAJE);
                	blindaje.setCodigoSolicito(rs.getInt("SLEC_SOLICITO"));

                    String fechaCadena = rs.getString("FECH_SOLICITO");
                    if(fechaCadena != null){
                        Date fechaSolicito = new Date();
                        try{
                            fechaSolicito = dateFormat.parse(fechaCadena);
                            blindaje.setFechaSolicito(fechaSolicito);
                        }catch(Exception ex){
                            LOGGER.warn("Problema obteniendo la fecha, no se tomara en cuenta", ex);
                            blindaje.setFechaSolicito(null);
                        }

                    }else{
                    	blindaje.setFechaSolicito(null);
                    }
                    
                    listaBlindaje.add(blindaje);

                }
            
            String mensajeLog = "SELECT  realizado sobre la tabla PCD_NEG_OFERTA con los parametros:\n";
            mensajeLog += "movil: " + movil + "\n";            
            
            LOGGER.info(mensajeLog);            
        } catch (SQLException e) {
            LOGGER.error("Error al realizar insercion sobre la tabla PCD_NEG_OFERTA ", e);
        } catch (Exception e) {
            LOGGER.error("Exception al realizar insercion sobre la tabla PCD_NEG_OFERTA", e);
        } finally {
            dsc.closeConnection(con, ps, null);
        }
        
        return listaBlindaje;
    }
    
    
	/**
     *  Consultar la oferta Blindaje para cargar los datos de esta en un objeto con atributos de la oferta.
     *  La consulta tambien entrega los productos rechazados para que el solicitante pueda verlos de nuevo.
     * @param codigo
     * @param rechazado     
     * @throws SQLException
     */
    public List<Oferta> ConsultarOfertaBlindajeBanner(String movil) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        Oferta blindaje = null; 
        List<Oferta> listaBlindaje = new ArrayList<Oferta>();
        
        try {  
        	
                StringBuffer sb = new StringBuffer(1000);
                sb.append("SELECT CODI_OFERTA, CODI_GRUPO_G");
                sb.append(", CASE WHEN CODI_BSCSOF IS NULL THEN '0'" +
                		  "		  WHEN CODI_BSCSOF = 'N/A' THEN '0'" +
                		  "		  ELSE CODI_BSCSOF END AS CODI_BSCSOF");
                sb.append(", CASE WHEN SLEC_VISITA IS NULL THEN 0 ELSE SLEC_VISITA END AS SLEC_VISITA");
                sb.append(", CASE WHEN SLEC_RECHAZO IS NULL THEN 0 ELSE SLEC_RECHAZO END AS SLEC_RECHAZO ");
                sb.append(", CASE WHEN SLEC_SOLICITO IS NULL THEN 0 ELSE SLEC_SOLICITO END AS SLEC_SOLICITO ");
                sb.append(", TO_CHAR(TO_DATE(FECH_SOLICITO),'DDMMYYYY') AS FECH_SOLICITO ");
                sb.append("FROM PCD_NEG_OFERTA ");
                sb.append("WHERE NMRO_MOVIL_C = ? ");
                
                sb.append("AND SLEC_DISPONIBLE = 1 AND SLEC_RECHAZO = 0");
                sb.append("AND (SLEC_SOLICITO IS NULL OR SLEC_SOLICITO = 0 OR SLEC_SOLICITO = 3 OR SLEC_SOLICITO = 4) ");
               
         

                con = dsc.getConnection();           
                ps = con.prepareStatement(sb.toString());
                
                ps.setString(1, movil);
                rs = ps.executeQuery();

                while (rs.next()) {
                	blindaje = new Oferta();
                	blindaje.setOfertaId(String.valueOf(rs.getInt("CODI_OFERTA")));
                	blindaje.setGrupoOferta(rs.getString("CODI_GRUPO_G"));
					blindaje.setCodBscsOferta(rs.getString("CODI_BSCSOF"));
                	blindaje.setVisitada((rs.getInt("SLEC_VISITA") == 1) ? true : false);
                	blindaje.setRechazada((rs.getInt("SLEC_RECHAZO") == 1) ? true : false);
                	blindaje.setTipo(TIPO_OFERTA_BLINDAJE);
                	blindaje.setCodigoSolicito(rs.getInt("SLEC_SOLICITO"));

                    String fechaCadena = rs.getString("FECH_SOLICITO");
                    if(fechaCadena != null){
                        Date fechaSolicito = new Date();
                        try{
                            fechaSolicito = dateFormat.parse(fechaCadena);
                            blindaje.setFechaSolicito(fechaSolicito);
                        }catch(Exception ex){
                            LOGGER.warn("Problema obteniendo la fecha, no se tomara en cuenta", ex);
                            blindaje.setFechaSolicito(null);
                        }

                    }else{
                    	blindaje.setFechaSolicito(null);
                    }
                    
                    listaBlindaje.add(blindaje);

                }
            
            String mensajeLog = "SELECT  realizado sobre la tabla PCD_NEG_OFERTA con los parametros:\n";
            mensajeLog += "movil: " + movil + "\n";            
            
            LOGGER.info(mensajeLog);            
        } catch (SQLException e) {
            LOGGER.error("Error al realizar insercion sobre la tabla PCD_NEG_OFERTA ", e);
        } catch (Exception e) {
            LOGGER.error("Exception al realizar insercion sobre la tabla PCD_NEG_OFERTA", e);
        } finally {
            dsc.closeConnection(con, ps, null);
        }
        
        return listaBlindaje;
    }
    
    /**
     *  Update la oferta Blindaje marcar las visitas
     * @param codigo
     * @param rechazado     
     * @throws SQLException
     */
    public void actualizarVisitaOferta(long ofertaId, boolean status) throws Exception {

    	Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        int estado = (status == true) ? 1 : 0;

        try {
            StringBuffer sb = new StringBuffer(500);

            sb.append("UPDATE PCD_NEG_OFERTA ");
            sb.append("SET SLEC_VISITA = ? ");
            sb.append("WHERE CODI_OFERTA = ? ");

            con = dsc.getConnection();           
            ps = con.prepareStatement(sb.toString());
            ps.setInt(1, estado);
            ps.setLong(2, ofertaId);
            rs = ps.executeQuery();
            
            String mensajeLog = "Update  realizado sobre la tabla PCD_NEG_OFERTA con los parametros:\n";
            mensajeLog += "estado: " + estado + "\n";  
            mensajeLog += "ofertaId: " + ofertaId + "\n";  
            
            LOGGER.info(mensajeLog);            
        } catch (SQLException e) {
            LOGGER.error("Error al realizar udapte sobre la tabla PCD_NEG_OFERTA ", e);
        } catch (Exception e) {
            LOGGER.error("Exception al realizar udapte sobre la tabla PCD_NEG_OFERTA", e);
        } finally {
            dsc.closeConnection(con, ps, null);
        }
    }
    
    /**
     *  Consultar la oferta Blindaje para cargar los datos de esta en un objeto con atributos de la oferta.
     * @param codigo
     * @param rechazado     
     * @throws SQLException
     */
    public List<Oferta> ConsultarOfertaNBA(String movil) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        Oferta blindaje = null; 
        List<Oferta> listaBlindaje = new ArrayList<Oferta>();
        
        try {  
        	
                StringBuffer sb = new StringBuffer(1000);
                sb.append("select oferta.codi_bscsof, oferta.codi_grupo_g, oferta.codi_oferta, oferta.nmro_movil_c, oferta.codi_plantilla,");
                sb.append(" planes.codi_plan,planes.VLOR_CARGO_FIJO,");
                sb.append(" CASE WHEN  planes.DESC_TIPO_PLAN  LIKE '%MM%' THEN '0' ELSE '1'  END AS DESCRIPCION");
                sb.append(" from pcd_neg_oferta oferta LEFT JOIN PCD_MAE_PLAN planes ON (CASE WHEN  oferta.CODI_BSCSOF  LIKE '%N/A%' THEN 0 ELSE TO_NUMBER(oferta.CODI_BSCSOF)  END ) = planes.CODI_PLAN");
				sb.append(" where nmro_movil_c = ? and slec_disponible=1 and SLEC_RECHAZO = 0 and SLEC_SOLICITO = 0 ORDER BY planes.VLOR_CARGO_FIJO asc");

                con = dsc.getConnection();           
                ps = con.prepareStatement(sb.toString());
                
                ps.setString(1, movil);
                rs = ps.executeQuery();

                while (rs.next()) {
                	blindaje = new Oferta();
                	blindaje.setOfertaId(String.valueOf(rs.getInt("codi_oferta")));
                	blindaje.setGrupoOferta(rs.getString("codi_grupo_g"));
                	blindaje.setCodBscsOferta(rs.getString("codi_bscsof"));
                	blindaje.setMovil(rs.getString("nmro_movil_c"));
                	blindaje.setCodPlantilla(rs.getString("codi_plantilla"));
                    
                    listaBlindaje.add(blindaje);

                }
            
            String mensajeLog = "SELECT  realizado sobre la tabla PCD_NEG_OFERTA con los parametros:\n";
            mensajeLog += "movil: " + movil + "\n";            
            
            LOGGER.info(mensajeLog);            
        } catch (SQLException e) {
            LOGGER.error("Error al realizar select sobre la tabla PCD_NEG_OFERTA ", e);
        } catch (Exception e) {
            LOGGER.error("Exception al realizar select sobre la tabla PCD_NEG_OFERTA", e);
        } finally {
            dsc.closeConnection(con, ps, null);
        }
        
        return listaBlindaje;
    }
    
    /**
     *  Consultar la oferta Blindaje para cargar los datos de esta en un objeto con atributos de la oferta por grupo.
     * @param codigo
     * @param rechazado     
     * @throws SQLException
     */
    public List<Oferta> ConsultarOfertaNBAByGroup(String movil, String grupo) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        Oferta blindaje = null; 
        List<Oferta> listaBlindaje = new ArrayList<Oferta>();
        
        try {  
        	
                StringBuffer sb = new StringBuffer(1000);
                sb.append("select codi_bscsof, codi_grupo_g, codi_oferta, nmro_movil_c from pcd_neg_oferta where nmro_movil_c = ? and slec_disponible=1 and codi_grupo_g = ?");

                con = dsc.getConnection();           
                ps = con.prepareStatement(sb.toString());
                
                ps.setString(1, movil);
                ps.setString(1, grupo);
                rs = ps.executeQuery();

                while (rs.next()) {
                	blindaje = new Oferta();
                	blindaje.setOfertaId(String.valueOf(rs.getInt("codi_oferta")));
                	blindaje.setGrupoOferta(rs.getString("codi_grupo_g"));
                	blindaje.setCodBscsOferta(rs.getString("codi_bscsof"));
                	blindaje.setMovil(rs.getString("nmro_movil_c"));
                    
                    listaBlindaje.add(blindaje);

                }
            
            String mensajeLog = "SELECT  realizado sobre la tabla PCD_NEG_OFERTA con los parametros:\n";
            mensajeLog += "movil: " + movil + "\n";            
            
            LOGGER.info(mensajeLog);            
        } catch (SQLException e) {
            LOGGER.error("Error al realizar select sobre la tabla PCD_NEG_OFERTA ", e);
        } catch (Exception e) {
            LOGGER.error("Exception al realizar select sobre la tabla PCD_NEG_OFERTA", e);
        } finally {
            dsc.closeConnection(con, ps, null);
        }
        
        return listaBlindaje;
    }
    
    /**
     *  Consultar descripcion plan y valor.
     * @param codigo Plan  
     * @throws SQLException
     */
    public String [] ConsultarOfertaPlanNBA(String codigoPlan) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String [] datos=new String [2];
        
        try {  
        	
                StringBuffer sb = new StringBuffer(1000);
                sb.append("select desc_plan, vlor_cargo_fijo, DESC_TIPO_PLAN from pcd_mae_plan where codi_plan = ?");

                con = dsc.getConnection();           
                ps = con.prepareStatement(sb.toString());
                
                ps.setString(1, codigoPlan);
                rs = ps.executeQuery();

                while (rs.next()) {
                	datos[0]=rs.getString("desc_plan");
                	datos[1]=rs.getString("vlor_cargo_fijo");
                	datos[2]=rs.getString("DESC_TIPO_PLAN");
                }
            
            String mensajeLog = "SELECT  realizado sobre la tabla pcd_mae_plan con los parametros:\n";
            mensajeLog += "codigoPlan: " + codigoPlan + "\n";            
            
            LOGGER.info(mensajeLog);            
        } catch (SQLException e) {
            LOGGER.error("Error al realizar select sobre la tabla pcd_mae_plan ", e);
        } catch (Exception e) {
            LOGGER.error("Exception al realizar select sobre la tabla pcd_mae_plan", e);
        } finally {
            dsc.closeConnection(con, ps, null);
        }
        
        return datos;
    }
    
    /**
     *  Consultar nombre bolsa y valor.
     * @param codigo bolsa  
     * @throws SQLException
     */
    public String [] ConsultarOfertaBolsaNBA(String codigoBolsa) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String [] datos=new String [3];
        
        try {  
        	
                StringBuffer sb = new StringBuffer(1000);
                sb.append("select nomb_bolsa, vlor_bolsa,vlor_tipobolsa from pcd_mae_bolsas where codi_bolsa = ?");

                con = dsc.getConnection();           
                ps = con.prepareStatement(sb.toString());
                
                ps.setString(1, codigoBolsa);
                rs = ps.executeQuery();

                while (rs.next()) {
                	datos[0]=rs.getString("nomb_bolsa");
                	datos[1]=rs.getString("vlor_bolsa");
                	datos[2]=rs.getString("vlor_tipobolsa");
                }
            
            String mensajeLog = "SELECT  realizado sobre la tabla pcd_mae_bolsas con los parametros:\n";
            mensajeLog += "codigoBolsa: " + codigoBolsa + "\n";            
            
            LOGGER.info(mensajeLog);            
        } catch (SQLException e) {
            LOGGER.error("Error al realizar select sobre la tabla pcd_mae_bolsas ", e);
        } catch (Exception e) {
            LOGGER.error("Exception al realizar select sobre la tabla pcd_mae_bolsas", e);
        } finally {
            dsc.closeConnection(con, ps, null);
        }
        
        return datos;
    }
    public int obtenerMaximoMinimizarBanner(String movil){
    	int minimizar=0;
    	Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {  
        	
                StringBuffer sb = new StringBuffer(1000);
                sb.append("select CASE WHEN max(FLAG_MINIMIZAR) IS NULL THEN 0 ELSE max(FLAG_MINIMIZAR) END as max from pcd_neg_oferta where nmro_movil_c = ? and slec_disponible=1");

                con = dsc.getConnection();           
                ps = con.prepareStatement(sb.toString());
                
                ps.setString(1, movil);
                rs = ps.executeQuery();

                if (rs.next()) {
                	minimizar=rs.getInt("max");
                }
            
            String mensajeLog = "SELECT  realizado sobre la tabla pcd_neg_oferta";            
            LOGGER.info(mensajeLog);            
        } catch (SQLException e) {
            LOGGER.error("Error al realizar select sobre la tabla pcd_neg_oferta ", e);
        } catch (Exception e) {
            LOGGER.error("Exception al realizar select sobre la tabla pcd_neg_oferta", e);
        } finally {
            dsc.closeConnection(con, ps, null);
        }
    	return minimizar;
    }
    public int minimizarBanner(String movil){
    	int minimizar =0;
    	Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {  
        	
                StringBuffer sb = new StringBuffer(1000);
                sb.append("select count(*) as cant from pcd_neg_oferta where nmro_movil_c = ? and slec_disponible=1 and  FLAG_MINIMIZAR=0");

                con = dsc.getConnection();           
                ps = con.prepareStatement(sb.toString());
                
                ps.setString(1, movil);
                rs = ps.executeQuery();

                if (rs.next()) {
                	minimizar=rs.getInt("cant");
                }
            
            String mensajeLog = "SELECT  realizado sobre la tabla pcd_neg_oferta";            
            LOGGER.info(mensajeLog);            
        } catch (SQLException e) {
            LOGGER.error("Error al realizar select sobre la tabla pcd_neg_oferta ", e);
        } catch (Exception e) {
            LOGGER.error("Exception al realizar select sobre la tabla pcd_neg_oferta", e);
        } finally {
            dsc.closeConnection(con, ps, null);
        }
    	return minimizar;
    }
    public void actualizarMinimizarBanner(String movil,int rechazo){
    	Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {  
            StringBuffer sb = new StringBuffer(1000);
            
            sb.append("UPDATE PCD_NEG_OFERTA SET FLAG_MINIMIZAR = ? WHERE nmro_movil_c = ?");

            con = dsc.getConnection();           
            ps = con.prepareStatement(sb.toString());
            
            ps.setInt(1, rechazo);
            ps.setString(2, movil);
            
            rs = ps.executeQuery();
            
            String mensajeLog = "UPDATE  realizado sobre la tabla pcd_neg_oferta";            
            LOGGER.info(mensajeLog);            
        } catch (SQLException e) {
            LOGGER.error("Error al realizar select sobre la tabla pcd_neg_oferta ", e);
        } catch (Exception e) {
            LOGGER.error("Exception al realizar select sobre la tabla pcd_neg_oferta", e);
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
                sb.append("SET SLEC_DISPONIBLE = 0 , FECH_RECHAZO = SYSDATE ");
                sb.append("WHERE CODI_OFERTA = ? AND SLEC_DISPONIBLE = 1 ");
                
                con = dsc.getConnection();           
                ps = con.prepareStatement(sb.toString());
                
                ps.setLong(1, codOferta);
                rs = ps.executeQuery();

                String mensajeLog = "UPDATE  realizado sobre la tabla PCD_NEG_OFERTA con los parametros:\n";
                mensajeLog += "oferta: " + codOferta + "\n";            
                
                LOGGER.info(mensajeLog);     
                       
        } catch (SQLException e) {
            LOGGER.error("Error al realizar UPDATE sobre la tabla PCD_NEG_OFERTA ", e);
        } catch (Exception e) {
            LOGGER.error("Exception al realizar UPDATE sobre la tabla PCD_NEG_OFERTA", e);
        } finally {
            dsc.closeConnection(con, ps, null);
        }
        
      }
    
	/**
	 * Consulta si un movil tiene ofertas disponibles
	 * @param movil
	 * @return
	 */
    public boolean tieneOfertasPorMovil(String movil) {
		boolean tieneOfertas = false;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT count(*) AS cant_ofertas ");
			sb.append("FROM pcd_neg_oferta ");
			sb.append("WHERE slec_disponible = 1 ");
			sb.append("AND nmro_movil_c = ?");

			con = dsc.getConnection();			
			ps = con.prepareStatement(sb.toString());
			ps.setString(1, movil);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				tieneOfertas = rs.getInt("cant_ofertas") > 0;
			}

			String mensajeLog = "SELECT realizado sobre la tabla pcd_neg_oferta";
			LOGGER.info(mensajeLog);
		} catch (SQLException e) {
			LOGGER.error("Error al realizar SELECT sobre la tabla pcd_neg_oferta ", e);
		} catch (Exception e) {
			LOGGER.error("Exception al realizar SELECT sobre la tabla pcd_neg_oferta", e);
		} finally {
			dsc.closeConnection(con, ps, null);
		}
		
		return tieneOfertas;
	}    
	
	public String obtenerPlanOriginalNumero(String codiOferta, String grupoOferta) {
		
		Connection con = null;
	    PreparedStatement ps = null;	  
	    ResultSet rs = null;
	    
	    String planOriginalNumero = "";
	    
	    LOGGER.info("Select plan original movil asociado a oferta " + grupoOferta + " - " + codiOferta);        
	    
	    try {  
	    
	    	LOGGER.info("Select descripcion plan original movil");
	    	
	    	StringBuffer sb = new StringBuffer(200);
		        
	    	if (grupoOferta.equals("G6")) {
	    		
	    		sb.append("select PCD_MAE_PLANORIGEN.CODI_BSCS ")
	    		.append("from PCD_REL_CONTRATABOLSAS, PCD_MAE_PLANORIGEN ")
	    		.append("where PCD_MAE_PLANORIGEN.CODI_PLAN = PCD_REL_CONTRATABOLSAS.CODI_PLAN and PCD_REL_CONTRATABOLSAS.CODI_OFERTA = ?");
	    		
	    	} else if (grupoOferta.equals("G4")) {
	    	
	    		sb.append("select PCD_MAE_PLANORIGEN.CODI_BSCS ")
	    		.append("from PCD_NEG_CAMBIO_PLAN, PCD_MAE_PLANORIGEN ")
	    		.append("where PCD_MAE_PLANORIGEN.CODI_PLAN = PCD_NEG_CAMBIO_PLAN.CODI_PLAN and PCD_NEG_CAMBIO_PLAN.CODI_OFERTA = ?");
	    		
	    	}
    		
            con = dsc.getConnection();           
            ps = con.prepareStatement(sb.toString());
            
            ps.setString(1, codiOferta);
            rs = ps.executeQuery();

            if (rs.next()) {	            	
            	planOriginalNumero = String.valueOf(rs.getInt("CODI_BSCS"));
            	LOGGER.info("Codigo Plan original movil: " + planOriginalNumero);               
            } else { 
            	LOGGER.info("Plan original de oferta " + codiOferta + " no pudo ser encontrado en PCD_MAE_PLANORIGEN");            
            }
            	        
	        
	    } catch (SQLException e) {
	        LOGGER.error("Error al ejecutar select del plan original del movil usando oferta " + grupoOferta + " - " + codiOferta, e);
	    } catch (Exception e) {
	    	LOGGER.error("Exception al ejecutar select del plan original del movil usando oferta " + grupoOferta + " - " + codiOferta, e);	       
	    } finally {
	        dsc.closeConnection(con, ps, null);
	    }
	    
	    return planOriginalNumero;
	  }
	
    /**
     * Consultar la oferta Blindaje segun los criterios de priorizacion definidos para la campana promocional del mes
     * @param criterios     
     * @throws SQLException
     */
    public List<Oferta> ConsultarOfertaByCriterios(CriteriosBusquedaOferta criterios) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        Oferta ofertaBlindaje = null;
        List<Oferta> listaBlindaje = new ArrayList<Oferta>();
        
        try {  
        	
                StringBuffer sb = new StringBuffer(1000);
                
                if(!criterios.isMultiOferta()){
                	sb.append("select CODI_OFERTA, NMRO_MOVIL_C, CODI_GRUPO_G, CODI_BSCSOF ");
                    sb.append("from PCD_NEG_OFERTA ");
                    sb.append("where NMRO_MOVIL_C = ? and ");
                    sb.append("		 SLEC_DISPONIBLE = 1 and ");
                    sb.append("		 CODI_GRUPO_G = ? and ");
                    if(criterios.isValidarPlantillaOferta())
                    	sb.append("  CODI_PLANTILLA in (").append(criterios.getTipoPlanBolsa()).append(") and ");
                    sb.append("		 SLEC_RECHAZO = 0 and ");
                    sb.append("		 SLEC_SOLICITO = 0 ");
 
                }else if(criterios.getGrupoCampana().equalsIgnoreCase("G4")){
                	sb.append("select oferta.CODI_OFERTA, oferta.NMRO_MOVIL_C, oferta.CODI_GRUPO_G, oferta.CODI_BSCSOF, planes.DESC_TIPO_PLAN, planes.VLOR_CARGO_FIJO ");
                    sb.append("from PCD_NEG_OFERTA oferta left join PCD_MAE_PLAN planes ON (CASE WHEN  oferta.CODI_BSCSOF  LIKE '%N/A%' THEN 0 ELSE TO_NUMBER(oferta.CODI_BSCSOF)  END ) = planes.CODI_PLAN ");
                    sb.append("where oferta.NMRO_MOVIL_C = ? and ");
                    sb.append("		 oferta.SLEC_DISPONIBLE = 1 and ");
                    sb.append("		 oferta.CODI_GRUPO_G = 'G4' and ");
                    
                    if(criterios.isValidarTipoPlanBolsa())
                    	sb.append("  planes.DESC_TIPO_PLAN in (").append(criterios.getTipoPlanBolsa()).append(") and ");
                    if(criterios.isValidarPlantillaOferta())
                    	sb.append("  oferta.CODI_PLANTILLA in (").append(criterios.getTipoPlanBolsa()).append(") and ");
                    
                    sb.append("		 oferta.SLEC_RECHAZO = 0 and ");
                    sb.append("		 oferta.SLEC_SOLICITO = 0 ");
                    
                    if(criterios.isValidarCargoFijo())
                    	sb.append("order by planes.VLOR_CARGO_FIJO ").append(criterios.getOrdenCargoFijo());
    				
                }else if(criterios.getGrupoCampana().equalsIgnoreCase("G6")){
                	sb.append("select oferta.CODI_OFERTA, oferta.NMRO_MOVIL_C, oferta.CODI_GRUPO_G, oferta.CODI_BSCSOF, bolsas.VLOR_TIPOBOLSA, bolsas.VLOR_BOLSA ");
                    sb.append("from PCD_NEG_OFERTA oferta left join PCD_MAE_BOLSAS bolsas ");
                    sb.append("		ON (CASE WHEN  oferta.CODI_BSCSOF  LIKE '%N/A%' THEN 0 ELSE TO_NUMBER(oferta.CODI_BSCSOF)  END ) = bolsas.CODI_BOLSA ");
                    sb.append("where oferta.NMRO_MOVIL_C = ? and ");
                    sb.append("		 oferta.SLEC_DISPONIBLE = 1 and ");
                    sb.append("		 oferta.CODI_GRUPO_G = 'G6' and ");
                    
                    if(criterios.isValidarTipoPlanBolsa())
                    	sb.append("	 bolsas.VLOR_TIPOBOLSA in (").append(criterios.getTipoPlanBolsa()).append(") and ");
                    if(criterios.isValidarPlantillaOferta())
                    	sb.append("  oferta.CODI_PLANTILLA in (").append(criterios.getTipoPlanBolsa()).append(") and ");
                    
                    sb.append("		 oferta.SLEC_RECHAZO = 0 and ");
                    sb.append("		 oferta.SLEC_SOLICITO = 0 ");
                    
                    if(criterios.isValidarCargoFijo())
                    	sb.append("order by bolsas.VLOR_BOLSA ").append(criterios.getOrdenCargoFijo());
    				
                }

                con = dsc.getConnection();
                LOGGER.info(sb.toString());
                ps = con.prepareStatement(sb.toString());
                
                if(!criterios.isMultiOferta()){
                	ps.setString(1, criterios.getMovil());
                	ps.setString(2, criterios.getGrupoCampana());
                	
                }else if(criterios.getGrupoCampana().equalsIgnoreCase("G4")
                		|| criterios.getGrupoCampana().equalsIgnoreCase("G6")){
                	ps.setString(1, criterios.getMovil());
                	
                }
                
                rs = ps.executeQuery();

                while (rs.next()) {
                	ofertaBlindaje = new Oferta();
                	ofertaBlindaje.setOfertaId(String.valueOf(rs.getInt("CODI_OFERTA")));
                	ofertaBlindaje.setMovil(rs.getString("NMRO_MOVIL_C"));
                	ofertaBlindaje.setGrupoOferta(rs.getString("CODI_GRUPO_G"));
                	ofertaBlindaje.setCodBscsOferta(rs.getString("CODI_BSCSOF"));
                	if(criterios.getGrupoCampana().equalsIgnoreCase("G4")){
	                	ofertaBlindaje.setCargoFijo(rs.getInt("VLOR_CARGO_FIJO"));
	                	ofertaBlindaje.setTipoBolsaPlan(rs.getString("DESC_TIPO_PLAN"));
                	}else if(criterios.getGrupoCampana().equalsIgnoreCase("G6")){
                		ofertaBlindaje.setCargoFijo(rs.getInt("VLOR_BOLSA"));
                    	ofertaBlindaje.setTipoBolsaPlan(rs.getString("VLOR_TIPOBOLSA"));
                	}
                	
                	listaBlindaje.add(ofertaBlindaje);
                }
                
            String mensajeLog = "SELECT  realizado sobre las tablas: PCD_NEG_OFERTA ";
            if(criterios.getGrupoCampana().equalsIgnoreCase("G4"))
            	mensajeLog += ", PCD_MAE_PLAN";
            if(criterios.getGrupoCampana().equalsIgnoreCase("G6"))
            	mensajeLog += ", PCD_MAE_BOLSAS";
            mensajeLog += " con los parametros:\n";
            mensajeLog += "movil: " + criterios.getMovil() + "\n";
            mensajeLog += "grupo campana: " + criterios.getGrupoCampana() + "\n";
            mensajeLog += "tipo plan-bolsa: " + criterios.getTipoPlanBolsa() + "\n";
            mensajeLog += "plantilla oferta: " + criterios.getPlantillaOferta() + "\n";
            mensajeLog += "cargo fijo actual cliente: " + criterios.getCfActualCliente() + "\n";
            
            LOGGER.info(mensajeLog);            
        } catch (SQLException e) {
            LOGGER.error("Error al realizar select sobre la tabla PCD_NEG_OFERTA ", e);
        } catch (Exception e) {
            LOGGER.error("Exception al realizar select sobre la tabla PCD_NEG_OFERTA", e);
        } finally {
            dsc.closeConnection(con, ps, null);
        }
        
        return listaBlindaje;
    }

/**
     *  Consultar la oferta 4GLTE para cargar los datos de esta en un objeto con atributos de la oferta.
     * @param codigo
     * @param rechazado     
     * @throws SQLException
     */
    public List<Oferta> consultarOferta4GLTE(String movil) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        Oferta blindaje = null; 
        List<Oferta> listaBlindaje = new ArrayList<Oferta>();
        
        try {  
        	
                StringBuffer sb = new StringBuffer(1000);
                sb.append("select oferta.codi_bscsof, oferta.codi_grupo_g, oferta.codi_oferta, oferta.nmro_movil_c,");
                sb.append(" planes.codi_plan,planes.VLOR_CARGO_FIJO,");
                sb.append(" CASE WHEN  planes.DESC_TIPO_PLAN  LIKE '%LIBRE MM%' THEN '0' ELSE '1'  END AS DESCRIPCION");
                sb.append(" from pcd_neg_oferta oferta LEFT JOIN PCD_MAE_PLAN planes ON (CASE WHEN  oferta.CODI_BSCSOF  LIKE '%N/A%' THEN 0 ELSE TO_NUMBER(oferta.CODI_BSCSOF)  END ) = planes.CODI_PLAN");
				sb.append(" where nmro_movil_c = ? and slec_disponible=1 and SLEC_RECHAZO = 0 and SLEC_SOLICITO = 0 ORDER BY planes.VLOR_CARGO_FIJO desc");

                con = dsc.getConnection();           
                ps = con.prepareStatement(sb.toString());
                
                ps.setString(1, movil);
                rs = ps.executeQuery();

                while (rs.next()) {
                	blindaje = new Oferta();
                	blindaje.setOfertaId(String.valueOf(rs.getInt("codi_oferta")));
                	blindaje.setGrupoOferta(rs.getString("codi_grupo_g"));
                	blindaje.setCodBscsOferta(rs.getString("codi_bscsof"));
                	blindaje.setMovil(rs.getString("nmro_movil_c"));
                	//Este valor es utilizado para saber si en el plan es LIBRE MM
                	blindaje.setEstado(rs.getString("DESCRIPCION"));
                    
                    listaBlindaje.add(blindaje);

                }
            
            String mensajeLog = "SELECT  realizado sobre la tabla PCD_NEG_OFERTA con los parametros:\n";
            mensajeLog += "movil: " + movil + "\n";            
            
            LOGGER.info(mensajeLog);            
        } catch (SQLException e) {
            LOGGER.error("Error al realizar select sobre la tabla PCD_NEG_OFERTA ", e);
        } catch (Exception e) {
            LOGGER.error("Exception al realizar select sobre la tabla PCD_NEG_OFERTA", e);
        } finally {
            dsc.closeConnection(con, ps, null);
        }
        
        return listaBlindaje;
    }



}
