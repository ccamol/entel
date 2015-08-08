/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.formulariosatisfaccion.dao;


/**
 * @author zmanotas (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class FormularioSatisfaccionDAO {

    private RegBugYComentariosDAO regBugYComentariosDAO;    
    
    public FormularioSatisfaccionDAO() {
        regBugYComentariosDAO = new RegBugYComentariosDAO();
    }

    /**
     * @return the regBugYComentariosDAO
     */
    public RegBugYComentariosDAO getRegBugYComentariosDAO() {
        return regBugYComentariosDAO;
    }

    /**
     * @param regBugYComentariosDAO the regBugYComentariosDAO to set
     */
    public void setRegBugYComentariosDAO(RegBugYComentariosDAO regBugYComentariosDAO) {
        this.regBugYComentariosDAO = regBugYComentariosDAO;
    }
    
    public void insertar(String msisdn, String rut, String mercado, String nombres, String apellidos, String telAdicional, String eMail, String mensaje) throws Exception {
        regBugYComentariosDAO.insertar(msisdn, rut, mercado, nombres, apellidos, telAdicional, eMail, mensaje);        
    }
}
