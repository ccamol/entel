/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.dashboard.mensajes.dao;

import java.sql.SQLException;

/**
 * @author zmanotas (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class ConsultarDetalleLlamadosDAO {
    
    private RegDetalleLlamadosDAO regDetalleLlamadosDAO;
    
    public ConsultarDetalleLlamadosDAO() {
        regDetalleLlamadosDAO = new RegDetalleLlamadosDAO();
    }

    /**
     * @return the regDetalleLlamadosDAO
     */
    public RegDetalleLlamadosDAO getRegDetalleLlamadosDAO() {
        return regDetalleLlamadosDAO;
    }

    /**
     * @param regDetalleLlamadosDAO the regDetalleLlamadosDAO to set
     */
    public void setRegDetalleLlamadosDAO(RegDetalleLlamadosDAO regDetalleLlamadosDAO) {
        this.regDetalleLlamadosDAO = regDetalleLlamadosDAO;
    }

    /**
     * Verifica si ya se realizo una consulta de Detalle de Llamados
     * @param numeroCuenta
     * @param msisdn
     * @param aaa
     * @param folio
     * @return
     * @throws SQLException
     */
    public Boolean isConsultado(String numeroCuenta, String msisdn, String aaa, String folio) throws Exception {
        return this.regDetalleLlamadosDAO.consultar(numeroCuenta, msisdn, aaa, folio);
    }
    
    /**
     * Inserta un registro en la base de datos indicando que ya se realizo una consulta de Detalle de Llamados
     * @param numeroCuenta
     * @param msisdn
     * @param aaa
     * @param folio
     * @param tipoDocumento
     * @param periodo
     * @throws SQLException
     */
    public void insertar(String numeroCuenta, String msisdn, String aaa, String folio, String tipoDocumento, String periodo) throws Exception {
        this.regDetalleLlamadosDAO.insertar(numeroCuenta, msisdn, aaa, folio, tipoDocumento, periodo);
    }
}
