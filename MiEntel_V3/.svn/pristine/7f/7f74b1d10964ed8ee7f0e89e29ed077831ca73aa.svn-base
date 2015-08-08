/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.test.mientel.parametros;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.epcs.bean.CiudadBean;
import com.epcs.bean.ComunaBean;
import com.epcs.bean.RegionBean;
import com.epcs.recursoti.configuracion.MiEntelProperties;
import com.epcs.recursoti.parametros.dao.ParametrosDAO;

/**
 * @author jlopez (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class ParametrosDAOTestCase {

    private ParametrosDAO dao;
    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        
        MiEntelProperties.load("miEntel.properties");
        
    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        dao = new ParametrosDAO();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        dao = null;
    }

    /**
     * Test method for {@link com.epcs.recursoti.parametros.dao.ParametrosDAO#getCiudadesList(com.epcs.bean.RegionBean)}.
     */
    @Test
    public void testGetCiudadesList() {
        try {
            List<CiudadBean> list = dao.getCiudadesList(new RegionBean("01", ""));
            assertTrue(!list.isEmpty());
        } catch (Exception e) {
            fail("Exception caught" + e.getMessage());
        }
    }

    /**
     * Test method for {@link com.epcs.recursoti.parametros.dao.ParametrosDAO#getComunasList(com.epcs.bean.CiudadBean)}.
     */
    @Test
    public void testGetComunasList() {
        try {
            List<ComunaBean> list = dao.getComunasList(new RegionBean("01", "region I"), new CiudadBean("01", ""));
            assertTrue(!list.isEmpty());
        } catch (Exception e) {
            fail("Exception caught" + e.getMessage());
        }
    }

}
