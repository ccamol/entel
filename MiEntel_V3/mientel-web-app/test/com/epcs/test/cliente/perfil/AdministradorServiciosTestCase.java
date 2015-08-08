/* Propiedad de Entel Pcs. Todos los derechos reservados */
package com.epcs.test.cliente.perfil;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.epcs.bean.ResultadoServicioBean;
import com.epcs.cliente.perfil.dao.AdministracionServiciosDAO;
import com.epcs.recursoti.configuracion.MiEntelProperties;

/**
 * @author jlopez (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class AdministradorServiciosTestCase {


    private static final String propsFile = "C:/properties/aplicacion";

    private AdministracionServiciosDAO dao;
    
    private String numeroPcs;
    
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        
        MiEntelProperties.load(propsFile);
        dao = new AdministracionServiciosDAO();
        numeroPcs = "81575685";
    }

    public void setNumeroPcs(String numero) {
        this.numeroPcs = numero;
    }
    
    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test method for {@link com.epcs.cliente.perfil.dao.AdministracionServiciosDAO#activarNumeroInterarea(java.lang.String)}.
     */
    @Test
    public void testActivarNumeroInterarea() {

        try {
            ResultadoServicioBean resultado = dao
                    .activarNumeroInterarea(numeroPcs);
            assertNotNull(resultado);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception caught " + e.getMessage());
        }

    }

    /**
     * Test method for {@link com.epcs.cliente.perfil.dao.AdministracionServiciosDAO#desactivarNumeroInterarea(java.lang.String)}.
     */
    @Test
    public void testDesactivarNumeroInterarea() {

        try {
            ResultadoServicioBean resultado = dao
                    .desactivarNumeroInterarea(numeroPcs);
            assertNotNull(resultado);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception caught " + e.getMessage());
        }

    
    }

    /**
     * Test method for {@link com.epcs.cliente.perfil.dao.AdministracionServiciosDAO#activarRoaming(java.lang.String)}.
     */
    @Test
    public void testActivarRoaming() {

        try {
            ResultadoServicioBean resultado = dao
                    .activarRoaming(numeroPcs);
            assertNotNull(resultado);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception caught " + e.getMessage());
        }

    }

    /**
     * Test method for {@link com.epcs.cliente.perfil.dao.AdministracionServiciosDAO#desactivarRoaming(java.lang.String)}.
     */
    @Test
    public void testDesactivarRoaming() {

        try {
            ResultadoServicioBean resultado = dao
                    .desactivarRoaming(numeroPcs);
            assertNotNull(resultado);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception caught " + e.getMessage());
        }
    
    }

    /**
     * Test method for {@link com.epcs.cliente.perfil.dao.AdministracionServiciosDAO#activarLDI(java.lang.String)}.
     */
    @Test
    public void testActivarLDI() {

        try {
            ResultadoServicioBean resultado = dao
                    .activarLDI(numeroPcs);
            assertNotNull(resultado);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception caught " + e.getMessage());

        }
    
    }

    /**
     * Test method for {@link com.epcs.cliente.perfil.dao.AdministracionServiciosDAO#desactivarLDI(java.lang.String)}.
     */
    @Test
    public void testDesactivarLDI() {

        try {
            ResultadoServicioBean resultado = dao
                    .desactivarLDI(numeroPcs);
            assertNotNull(resultado);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception caught " + e.getMessage());

        }
    
    }

    /**
     * Test method for {@link com.epcs.cliente.perfil.dao.AdministracionServiciosDAO#activarNumero300(java.lang.String)}.
     */
    @Test
    public void testActivarNumero300() {

        try {
            ResultadoServicioBean resultado = dao
                    .activarNumero300(numeroPcs);
            assertNotNull(resultado);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception caught " + e.getMessage());

        }
    
    }

    /**
     * Test method for {@link com.epcs.cliente.perfil.dao.AdministracionServiciosDAO#desactivarNumero300(java.lang.String)}.
     */
    @Test
    public void testDesactivarNumero300() {

        try {
            ResultadoServicioBean resultado = dao
                    .desactivarNumero300(numeroPcs);
            assertNotNull(resultado);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception caught " + e.getMessage());

        }
        
    }

    /**
     * Test method for {@link com.epcs.cliente.perfil.dao.AdministracionServiciosDAO#activarNumero700(java.lang.String)}.
     */
    @Test
    public void testActivarNumero700() {

        try {
            ResultadoServicioBean resultado = dao
                    .activarNumero700(numeroPcs);
            assertNotNull(resultado);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception caught " + e.getMessage());

        }
    
    }

    /**
     * Test method for {@link com.epcs.cliente.perfil.dao.AdministracionServiciosDAO#desactivarNumero700(java.lang.String)}.
     */
    @Test
    public void testDesactivarNumero700() {
        
        try {
            ResultadoServicioBean resultado = dao
                    .desactivarNumero700(numeroPcs);
            assertNotNull(resultado);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception caught " + e.getMessage());
        }
        
    }

    /**
     * Test method for {@link com.epcs.cliente.perfil.dao.AdministracionServiciosDAO#activarNumero606(java.lang.String)}.
     */
    @Test
    public void testActivarNumero606() {

        try {
            ResultadoServicioBean resultado = dao
                    .activarNumero606(numeroPcs);
            assertNotNull(resultado);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception caught " + e.getMessage());
        }
    
    }

    /**
     * Test method for {@link com.epcs.cliente.perfil.dao.AdministracionServiciosDAO#desactivarNumero606(java.lang.String)}.
     */
    @Test
    public void testDesactivarNumero606() {

        try {
            ResultadoServicioBean resultado = dao
                    .desactivarNumero606(numeroPcs);
            assertNotNull(resultado);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception caught " + e.getMessage());
        }
    
    }

    /**
     * Test method for {@link com.epcs.cliente.perfil.dao.AdministracionServiciosDAO#activarNumero609(java.lang.String)}.
     */
    @Test
    public void testActivarNumero609() {
       
        try {
            ResultadoServicioBean resultado = dao
                    .activarNumero609(numeroPcs);
            assertNotNull(resultado);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception caught " + e.getMessage());
        }
        
    }

    /**
     * Test method for {@link com.epcs.cliente.perfil.dao.AdministracionServiciosDAO#desactivarNumero609(java.lang.String)}.
     */
    @Test
    public void testDesactivarNumero609() {

        try {
            ResultadoServicioBean resultado = dao
                    .desactivarNumero609(numeroPcs);
            assertNotNull(resultado);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception caught " + e.getMessage());
        }

    
    }

    /**
     * Test method for {@link com.epcs.cliente.perfil.dao.AdministracionServiciosDAO#activarWap(java.lang.String)}.
     */
    @Test
    public void testActivarWap() {

        try {
            ResultadoServicioBean resultado = dao
                    .activarWap(numeroPcs);
            assertNotNull(resultado);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception caught " + e.getMessage());
        }

    
    }

    /**
     * Test method for {@link com.epcs.cliente.perfil.dao.AdministracionServiciosDAO#desactivarWap(java.lang.String)}.
     */
    @Test
    public void testDesactivarWap() {

        try {
            ResultadoServicioBean resultado = dao
                    .desactivarWap(numeroPcs);
            assertNotNull(resultado);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception caught " + e.getMessage());
        }
    
    }

}
