/* Propiedad de Entel Pcs. Todos los derechos reservados */
package com.esa.ponline.portalbolsas.configuration.locator.handler;

/**
 * @author ccastro (MZZO)
 *
 */
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.apache.log4j.Logger;

public class AutenticacionUsuarioHandler implements SOAPHandler<SOAPMessageContext> {

    private static final Logger LOGGER = Logger.getLogger(AutenticacionUsuarioHandler.class);

    private String username;

    private String password;

    private String targetNameSpace;

    private String service;

    public AutenticacionUsuarioHandler(String usr, String pass, String targetNameSpace, String service) {
        this.username = usr;
        this.password = pass;
        this.targetNameSpace = targetNameSpace;
        this.service = service;

    }

    public Set<QName> getHeaders() {
        return null;
    }

    public boolean handleMessage(SOAPMessageContext context) {

        Boolean isRequest = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

        if (isRequest) {

            try {

                SOAPMessage soapMsg = context.getMessage();

                SOAPEnvelope soapEnv = soapMsg.getSOAPPart().getEnvelope();
                SOAPHeader soapHeader = soapEnv.getHeader();

                if (soapHeader == null) {
                    soapHeader = soapEnv.addHeader();
                }

                QName usrnm = new QName(targetNameSpace, service);
                SOAPHeaderElement soapHeaderElement = soapHeader.addHeaderElement(usrnm);

                // soapHeaderElement.setActor(SOAPConstants.URI_SOAP_ACTOR_NEXT);

                SOAPElement user = soapHeaderElement.addChildElement("username");
                user.setValue(this.username);
                SOAPElement pass = soapHeaderElement.addChildElement("password");
                pass.setValue(this.password);

                soapMsg.saveChanges();
                // soapMsg.writeTo(System.out);
            } catch (SOAPException e) {
                LOGGER.error(e.getMessage());
            } /*
               * catch (IOException e) { System.err.println(e); }
               */
        }
        return true;
    }

    public boolean handleFault(SOAPMessageContext context) {
        return false;
    }

    public void close(MessageContext context) {

    }

}
