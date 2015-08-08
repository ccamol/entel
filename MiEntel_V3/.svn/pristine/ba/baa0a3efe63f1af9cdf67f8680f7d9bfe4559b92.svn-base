/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.formulariosatisfaccion.delegate;

import java.sql.SQLException;

import com.epcs.formulariosatisfaccion.dao.FormularioSatisfaccionDAO;

/**
 * @author zmanotas (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class FormularioSatisfaccionDelegate {

    private FormularioSatisfaccionDAO formularioSatisfaccionDAO;

    /**
     * @return the formularioSatisfaccionDAO
     */
    public FormularioSatisfaccionDAO getFormularioSatisfaccionDAO() {
        return formularioSatisfaccionDAO;
    }

    /**
     * @param formularioSatisfaccionDAO
     *            the formularioSatisfaccionDAO to set
     */
    public void setFormularioSatisfaccionDAO(
            FormularioSatisfaccionDAO formularioSatisfaccionDAO) {
        this.formularioSatisfaccionDAO = formularioSatisfaccionDAO;
    }

    /**
     * Inserta un nuevo registro correspondiente al formulario de satisfaccion
     * @param msisdn
     * @param mercado
     * @param nombres
     * @param apellidos
     * @param telAdicional
     * @param eMail
     * @param mensaje
     * @throws SQLException
     */
    public void insertar(String msisdn, String rut, String mercado, String nombres, String apellidos, String telAdicional, String eMail, String mensaje) throws Exception {
        this.formularioSatisfaccionDAO.insertar(msisdn, rut, mercado, nombres, apellidos, telAdicional, eMail, mensaje);
    }
}
