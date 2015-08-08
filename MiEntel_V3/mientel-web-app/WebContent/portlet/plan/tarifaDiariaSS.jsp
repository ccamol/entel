<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="cm"      uri="http://www.bea.com/servers/portal/tags/content" %>  
<f:view beforePhase="#{tarifaDiariaController.init}"> 
<body>
      <div class="clearfix">
          <div class="nuevaFormaMovil" style="display:block;" align="justify">
             <h:outputText value="#{tarifaDiariaController.contenido}" escape="false" />
          </div>
      </div>	
</body>
</f:view>