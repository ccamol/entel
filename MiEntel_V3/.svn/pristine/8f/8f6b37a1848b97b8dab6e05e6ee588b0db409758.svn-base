/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.cliente.perfil.delegate;

import com.epcs.bean.ResumenProductosContratadosBean;
import com.epcs.cliente.perfil.dao.ProductoDAO;

/**
 * @author jlopez (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class ProductoDelegate {

//    private static final Logger LOGGER = Logger
//            .getLogger(ProductoDelegate.class);

    private ProductoDAO productoDAO;

    public ProductoDelegate() {
//        this.productoDAO = new ProductoDAO();
    }

    /**
     * @return the productoDAO
     */
    public ProductoDAO getProductoDAO() {
        return productoDAO;
    }

    /**
     * @param productoDAO
     *            the productoDAO to set
     */
    public void setProductoDAO(ProductoDAO productoDAO) {
        this.productoDAO = productoDAO;
    }

    public ResumenProductosContratadosBean getProductosContratados(
            String mercado, String numeroPcs) throws Exception {
        return this.productoDAO.getProductosContratados(mercado, numeroPcs);
    }

}
