<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="entel" uri="/WEB-INF/tld/entel-tags.tld" %>

<% 		
	String resaltador = "";
	if(request.getParameter("rowNumber") != null){
		resaltador = (Integer.parseInt(request.getParameter("rowNumber"))) % 2 == 0? "resaltador":"";
	}
%>

<entel:view name="num606" inverse="true"> 
<!-- Fila de información -->     
<div class="tabla_fila clearfix <%=resaltador%>">

	<div class="columna1">
       	<strong class="clearfix">Numeraci&oacute;n 606</strong>
        <label class="titulo_inferior"></label>
    </div>
         
    <div class="columna2">
       	<a class="ico_interrogacionNuevo autoTooltip" href="#TooltipNumeracion606"></a>
   	</div>

    <div class="columna3">
    	<!-- Mensaje grande -->
        <h:panelGroup layout="block" styleClass="mensaje_grande #{administracionServicios.administracionServiciosBean.servicioNum606.activo ? 'mensaje_grande_habilitado' :'mensaje_grande_deshabilitado'}">
        	<h:outputText value="#{administracionServicios.administracionServiciosBean.servicioNum606.activo ? 'Habilitado' :'Deshabilitado'}"/>
			<br />Solo el titular o administrador de la cuenta, puede cambiar el estado.
        	<br />
        	<h:form>
				<jsp:include page="/token.jsp" flush="true"/>
        		<h:commandLink action="mis_usuarios" styleClass="enlace">Ir a tabla de atributos</h:commandLink>
        	</h:form>
        </h:panelGroup>

    </div>
</div>
<!-- /Fila de información -->
</entel:view>


<entel:view name="num606">
<!-- Fila de información -->
<h:panelGroup rendered="#{administracionServicios.administracionServiciosBean.servicioNum606.visible}">

<div class="tabla_fila clearfix <%=resaltador%>">
    <h:form id="formNum606"> 
		<jsp:include page="/token.jsp" flush="true"/>
	<div class="estadoHabilitado clearfix <h:outputText value="#{administracionServicios.administracionServiciosBean.servicioNum606.activo? '' :'ocultar'}"/>">
    
    	<div class="columna1">
           	<strong class="clearfix">Numeraci&oacute;n 606</strong>
            <label class="titulo_inferior"></label>
        </div>
             
        <div class="columna2">
           	<a class="ico_interrogacionNuevo autoTooltip" href="#TooltipNumeracion606"></a>
       	</div>
         
        <div class="columna3">
        	<!-- Mensaje habilitado/deshabilitado -->
            <div class="mensaje habilitado">Habilitado</div>
                       
           	<!-- Btoón deshabilitado/habilitado -->
            <div class="btnDeshabilitar">
            	<a class="btnAzulDelgado btnAncho2 caso1" href="#" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Adm. de Servicios/Datos/Numeracion 606/Deshabilitar');"><span>Deshabilitar</span></a>
            </div>
                       
        </div>
     </div>
     
     
	<!-- Paso Confirmar Habilitar -->
	  <div class="confirmarHabilitar clearfix ocultar">	
			<div class="columna1">
				<strong class="clearfix">Numeraci&oacute;n 606</strong>
			</div>
			<div class="columna2">
				<a class="ico_interrogacionNuevo autoTooltip" href="#TTCobroRevertido"></a>
			</div>
			<div class="columna3">
				<!-- Mensaje habilitado/deshabilitado -->
				<div class="mensaje_ancho"><strong>Habilitar&aacute;s Numeraci&oacute;n 606</strong><br />
				</div>
				
				<!-- Btoon deshabilitado/habilitado -->
			<!-- Btoon deshabilitado/habilitado -->
				<div class="btnHabilitar">
				    <h:commandLink  styleClass="btnAzulDelgado btnAncho caso3" action="#{num606Controller.activarNum606}" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Adm. de Servicios/Datos/Numeracion 606/Habilitado');"><span>Habilitar</span></h:commandLink>
				</div>
				
				<!-- Boton Cancelar -->
				<div class="enlaceCancelar">
					<a href="#" class="enlaceCancelarH">Cancelar</a>
				</div>
			</div>
		</div>
	<!-- Fin Paso Confirmar Habilitar -->
                    
    <div class="estadoHabilitado1 clearfix ocultar">
      	<div class="columna1">
          	<strong class="clearfix">Numeraci&oacute;n 606</strong>
            <label class="titulo_inferior"></label>
		</div>
             
        <div class="columna2">
	       	<a class="ico_interrogacionNuevo autoTooltip" href="#TooltipNumeracion606"></a>
		</div>
             
		<div class="columna3">
             
           	<!-- Mensaje grande -->
            <div class="mensaje_grande mensaje_grande_reloj">
                 	En proceso de <strong>habilitaci&oacute;n</strong>
			</div>
     	</div>
	</div>
                 
    <div class="estadoDeshabilitado clearfix <h:outputText value="#{administracionServicios.administracionServiciosBean.servicioNum606.activo? 'ocultar' : ''}"/>">
       	<div class="columna1">
			<strong class="clearfix">Numeraci&oacute;n 606</strong>
            <label class="titulo_inferior"></label>
        </div>
           
        <div class="columna2">
           	<a class="ico_interrogacionNuevo autoTooltip" href="#TooltipNumeracion606"></a>
		</div>
           
        <div class="columna3">
           
        	<!-- Mensaje habilitado/deshabilitado -->
            <div class="mensaje deshabilitado">Deshabilitado</div>
                      
            <!-- Btoón deshabilitado/habilitado -->
             <div class="btnHabilitar">
             <a class="btnAzulDelgado btnAncho caso1H" href="#" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Adm. de Servicios/Datos/Numeracion 606/Habilitar');"><span>Habilitar</span></a>	             	
             </div>
                       
       	</div>
	</div>
                
    <div class="estadoDeshabilitado1 clearfix ocultar">
          
       	<div class="columna1">
           	<strong class="clearfix">Numeraci&oacute;n 606</strong>
            <label class="titulo_inferior"></label>
        </div>
             
        <div class="columna2">
          	<a class="ico_interrogacionNuevo autoTooltip" href="#TooltipNumeracion606"></a>
        </div>
             
        <div class="columna3">
              
			<!-- Mensaje habilitado/deshabilitado -->
           	<div class="mensaje_ancho">
            	<strong>Deshabilitar&aacute;s Numeraci&oacute;n 606</strong>
             	<br />
         	</div>
                    
         	<!-- Btoón deshabilitado/habilitado -->
           	<div class="btnDeshabilitar">
             	<h:commandLink styleClass="btnAzulDelgado btnAncho2 caso4" action="#{num606Controller.desactivarNum606}" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Adm. de Servicios/Datos/Numeracion 606/Deshabilitado');">
             			<span>Deshabilitar</span></h:commandLink>
           	</div>
                    
           	<!-- Botón Cancelar -->
           	<div class="enlaceCancelar">
           		<a href="#" class="enlaceCancelar">Cancelar</a>
       		</div>
   		</div>
	</div>
                 
    <div class="estadoDeshabilitado2 clearfix ocultar">
          
    	<div class="columna1">
        	<strong class="clearfix">Numeraci&oacute;n 606</strong>
            <label class="titulo_inferior"></label>
		</div>
              
        <div class="columna2">
        	<a class="ico_interrogacionNuevo autoTooltip" href="#TooltipNumeracion606"></a>
        </div>
              
        <div class="columna3">
              
        	<!-- Mensaje grande -->
            <div class="mensaje_grande mensaje_grande_reloj">
            	En proceso de <strong>deshabilitaci&oacute;n</strong>
            </div>
        </div>
    </div>
    
	</h:form>
</div>	

</h:panelGroup>

<h:panelGroup rendered="#{!administracionServicios.administracionServiciosBean.servicioNum606.visible}"> 
	<jsp:include page="../servicioNoDisponible.jsp" flush="true">
    	<jsp:param value="Numeraci&oacute;n 606" name="serviceName"/>
        <jsp:param value="" name="cargo"/>
        <jsp:param value="TooltipNumeracion606" name="tooltip"/>
    </jsp:include>
</h:panelGroup>	                

<!-- /Fila de información -->
</entel:view>   
