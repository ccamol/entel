/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.esa.ponline.portalbolsas.bean;

import java.io.Serializable;

import com.esa.ponline.portalbolsas.util.PCBUtils;

//import com.epcs.recursoti.configuracion.Utils;

/**
 * Bean Codigo-descripcion, pensado para listas de seleccion de categorias,
 * tipos, lugares geograficos, etc.<br>
 * Este bean esta pensado como alternativa a los <code>Map<String,String></code>, cuando es
 * mas comun usar el par codigo-descripcion en forma unitaria que en listas
 * 
 * @author ccastro (MZZO) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class CodeDescBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 5841169249922446153L;

    private String codigo;

    private String descripcion;

    /**
     * @param codigo
     *            String codigo del bean
     * @param descripcion
     *            String nombre o descripcion del bean
     */
    public CodeDescBean(String codigo, String descripcion) {
        super();
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo
     *            the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion
     *            the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Indica si bean es una instancia vacia. metodo utilitario para saber si el
     * bean fue creado por {@link #emptyBean()}
     * 
     * @param bean
     * @return true si el bean es empty, es decir codigo y descripcion vacios o null
     */
    public static boolean isEmptyBean(CodeDescBean bean) {
        return PCBUtils.isEmptyString(bean.getCodigo())
                && PCBUtils.isEmptyString(bean.getDescripcion());
    }

    /**
     * Entrega una instancia de {@link CodeDescBean} para propositos de
     * null-safe en otro metodos y clases
     * 
     * @return {@link CodeDescBean}
     */
    public static CodeDescBean emptyBean() {
        return new CodeDescBean("", "");
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
        result = prime * result
                + ((descripcion == null) ? 0 : descripcion.hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        CodeDescBean other = (CodeDescBean) obj;
        if (codigo == null) {
            if (other.codigo != null) {
                return false;
            }
        }
        else if (!codigo.equals(other.codigo)) {
            return false;
        }
        if (descripcion == null) {
            if (other.descripcion != null) {
                return false;
            }
        }
        else if (!descripcion.equals(other.descripcion)) {
            return false;
        }
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
       StringBuffer buffer = new StringBuffer(100);
       buffer.append("CodeDescBean [");
       buffer.append("codigo=" + codigo);
       buffer.append(", ");
       buffer.append("descripcion="+ descripcion);
       buffer.append("]");
       return buffer.toString();
    }
    
    

}
