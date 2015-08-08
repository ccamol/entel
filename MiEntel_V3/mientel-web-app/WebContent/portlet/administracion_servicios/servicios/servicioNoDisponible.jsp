<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
 <!-- Fila de información -->
<div class="tabla_fila clearfix">
	<div class="columna1">
    	<strong class="clearfix"><%= request.getParameter("serviceName") %></strong>
        <label class="titulo_inferior"><%= request.getParameter("cargo") %></label>
    </div>
    <div class="columna2">
    	<a class="ico_interrogacionNuevo autoTooltip" href="#<%= request.getParameter("tooltip") %>"></a>
    </div>
    <div class="columna3">
    	<!-- Mensaje grande -->
        <div class="mensaje_grande mensaje_grande_alerta">
        	La informaci&oacute;n del servicio no se encuentra actualmente disponible.
    	</div>
    </div>
</div>
<!-- /Fila de información -->
