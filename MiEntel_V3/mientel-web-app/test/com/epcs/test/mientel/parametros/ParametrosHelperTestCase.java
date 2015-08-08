/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.test.mientel.parametros;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.epcs.bean.CodeDescBean;
import com.epcs.recursoti.configuracion.ParametrosHelper;

/**
 * @author jlopez (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class ParametrosHelperTestCase {

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {

    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test method for
     * {@link com.epcs.recursoti.configuracion.ParametrosHelper#getEstadosCivilList()}.
     */
    @Test
    public void testGetEstadosCivilList() {

        try {
            List<CodeDescBean> list = ParametrosHelper.getEstadosCivilList();
            assertTrue(!list.isEmpty());
        } catch (Exception e) {
            fail("Exception thrown");
        }

    }

    /**
     * Test method for
     * {@link com.epcs.recursoti.configuracion.ParametrosHelper#getNivelEstudiosList()}.
     */
    @Test
    public void testGetNivelEstudiosList() {

        try {
            List<CodeDescBean> list = ParametrosHelper.getNivelEstudiosList();
            assertTrue(!list.isEmpty());
        } catch (Exception e) {
            fail("Exception thrown");
        }

    }

    /**
     * Test method for
     * {@link com.epcs.recursoti.configuracion.ParametrosHelper#getActividadLaboralList()}.
     */
    @Test
    public void testGetActividadLaboralList() {
        try {
            List<CodeDescBean> list = ParametrosHelper
                    .getActividadLaboralList();
            assertTrue(!list.isEmpty());
        } catch (Exception e) {
            fail("Exception thrown");
        }
    }

    /**
     * Test method for
     * {@link com.epcs.recursoti.configuracion.ParametrosHelper#getAreaLaboralList()}.
     */
    @Test
    public void testGetAreaLaboralList() {
        try {
            List<CodeDescBean> list = ParametrosHelper.getAreaLaboralList();
            assertTrue(!list.isEmpty());
        } catch (Exception e) {
            fail("Exception thrown");
        }
    }

    /**
     * Test method for
     * {@link com.epcs.recursoti.configuracion.ParametrosHelper#getRelacionesTitularList()}.
     */
    @Test
    public void testGetRelacionesTitularList() {
        try {
            List<CodeDescBean> list = ParametrosHelper
                    .getRelacionesTitularList();
            assertTrue(!list.isEmpty());
        } catch (Exception e) {
            fail("Exception thrown");
        }
    }

    /**
     * Test method for {@link com.epcs.recursoti.configuracion.ParametrosHelper#getSexoList()}.
     */
    @Test
    public void testGetSexoList() {
        try {
            List<CodeDescBean> list = ParametrosHelper.getSexoList();
            assertTrue(!list.isEmpty());
        } catch (Exception e) {
            fail("Exception thrown");
        }
    }

    /**
     * Test method for {@link com.epcs.recursoti.configuracion.ParametrosHelper#getHijosList()}.
     */
    @Test
    public void testGetHijosList() {
        try {
            List<CodeDescBean> list = ParametrosHelper.getHijosList();
            assertTrue(!list.isEmpty());
        } catch (Exception e) {
            fail("Exception thrown");
        }
    }

    /**
     * Test method for
     * {@link com.epcs.recursoti.configuracion.ParametrosHelper#getCodeDescBeanAAA(java.lang.String)}
     * .
     */
    @Test
    public void testGetCodeDescBeanAAA() {
        try {
            CodeDescBean bean = ParametrosHelper.getAAABean("0", ParametrosHelper.getAAABeanList());
            assertFalse(bean.getCodigo().equals("")
                    && bean.getDescripcion().equals(""));
        } catch (Exception e) {
            fail("Exception thrown");
        }
    }

}
