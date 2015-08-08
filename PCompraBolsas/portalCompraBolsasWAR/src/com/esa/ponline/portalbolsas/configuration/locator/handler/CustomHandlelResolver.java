/* Propiedad de EntelPcs. Todos los derechos reservados */
package com.esa.ponline.portalbolsas.configuration.locator.handler;

import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.PortInfo;

/**
 * @author ccastro (MZZO) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */
public class CustomHandlelResolver implements HandlerResolver {

    private String username;
    private String password;
    private String targetNamespace;
    private String serviceName;

    public CustomHandlelResolver(String username, String password,
            String targetNamespace, String serviceName) {
        super();
        this.username = username;
        this.password = password;
        this.targetNamespace = targetNamespace;
        this.serviceName = serviceName;
    }

    @SuppressWarnings("rawtypes")
    public List<Handler> getHandlerChain(PortInfo portInfo) {
        List<Handler> handlerList = new ArrayList<Handler>();
        handlerList.add(new AutenticacionUsuarioHandler(username, password,
                targetNamespace, serviceName));
        return handlerList;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     *            the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the pass
     */
    public String getPass() {
        return password;
    }

    /**
     * @param pass
     *            the pass to set
     */
    public void setPass(String password) {
        this.password = password;
    }

    /**
     * @return the targetNamespace
     */
    public String getTargetNamespace() {
        return targetNamespace;
    }

    /**
     * @param targetNamespace
     *            the targetNamespace to set
     */
    public void setTargetNamespace(String targetNamespace) {
        this.targetNamespace = targetNamespace;
    }

    /**
     * @return the serviceName
     */
    public String getServiceName() {
        return serviceName;
    }

    /**
     * @param serviceName
     *            the serviceName to set
     */
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

}
