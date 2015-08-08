/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.esa.ponline.portalbolsas.bean;

/**
 * @author ccastro (MZZO) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */

public class MultitiendaBean {

    private String nombreProperty;
    
    private String nombre;
    
    private String id;
    
    private int tipo;
    
    private int digitos;
    
    private String[] montos;
    
    private String[] cuotas;
    
    public MultitiendaBean(){
        
    }

    /**
     * 
     * @param nombreProperty
     * @param nombre
     * @param id
     * @param tipo
     * @param digitos
     * @param montos
     * @param cuotas
     */
    public MultitiendaBean(String nombreProperty, String nombre, String id, int tipo, int digitos,
            String[] montos, String[] cuotas) {
        super();
        this.nombre = nombre;
        this.id = id;
        this.tipo = tipo;
        this.digitos = digitos;
        this.montos = montos;
        this.cuotas = cuotas;
        this.nombreProperty = nombreProperty;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the tipo
     */
    public int getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the digitos
     */
    public int getDigitos() {
        return digitos;
    }

    /**
     * @param digitos the digitos to set
     */
    public void setDigitos(int digitos) {
        this.digitos = digitos;
    }

    /**
     * @return the montos
     */
    public String[] getMontos() {
        return montos;
    }

    /**
     * @param montos the montos to set
     */
    public void setMontos(String[] montos) {
        this.montos = montos;
    }

    /**
     * @return the cuotas
     */
    public String[] getCuotas() {
        return cuotas;
    }

    /**
     * @param cuotas the cuotas to set
     */
    public void setCuotas(String[] cuotas) {
        this.cuotas = cuotas;
    }

    /**
     * @return the nombreProperty
     */
    public String getNombreProperty() {
        return nombreProperty;
    }

    /**
     * @param nombreProperty the nombreProperty to set
     */
    public void setNombreProperty(String nombreProperty) {
        this.nombreProperty = nombreProperty;
    }
    
}
