<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="cm"      uri="http://www.bea.com/servers/portal/tags/content" %>
<cm:search id="infoDetalleTarifaDiariaFile" query="textContent = 'infoDetalleTarifaDiariaFile'" useCache="true"  />  
<f:view> 
<body>
      <div class="clearfix">
          <div class="nuevaFormaMovil" style="display:block;" align="justify">
             <h1 align="justify"><cm:getProperty node="${infoDetalleTarifaDiariaFile[0]}" name="title" /></h1>
   		        <div class="contenido_parrafos">                	
		            <cm:getProperty node="${infoDetalleTarifaDiariaFile[0]}" name="file" />
              </div>
          </div>
      </div>	
</body>
</f:view>