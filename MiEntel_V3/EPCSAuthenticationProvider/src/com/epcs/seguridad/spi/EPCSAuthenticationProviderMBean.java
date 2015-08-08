/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.seguridad.spi;

/**
 * Bean del proveedor de autenticacion. Permite definir la version, el control de fallos y la descripcion del mismo.
 * @author mmartinez (I2B) en nombre de Absalon Opazo (Atencion al Cliente, EntelPcs)
 *
 */
public class EPCSAuthenticationProviderMBean {

    /**
     * La version del provider.
     */
    private String version;
    
    /**
     * La descripcion del custom provider.
     */
    private String description;
    
    /**
     * Flag del control de errores en la autenticacion.
     */
    private String controlFlag;

    /**
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the controlFlag
     */
    public String getControlFlag() {
        return controlFlag;
    }

    /**
     * @param controlFlag the controlFlag to set
     */
    public void setControlFlag(String controlFlag) {
        this.controlFlag = controlFlag;
    }	
}
