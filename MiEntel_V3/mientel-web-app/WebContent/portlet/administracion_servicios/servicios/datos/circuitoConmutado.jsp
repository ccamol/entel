<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="entel" uri="/WEB-INF/tld/entel-tags.tld" %>
<%@ taglib prefix="cm" uri="http://www.bea.com/servers/portal/tags/content" %>

<% 
	String resaltador = "";
	if(request.getParameter("rowNumber") != null){
		resaltador = (Integer.parseInt(request.getParameter("rowNumber"))) % 2 == 0? "resaltador":"";
	}
%>

<cm:search id="mensajeDeshabilitar" query="idContenido = 'mensajeDeshabilitar'" useCache="true"  />

<entel:view name="bam" inverse="true"> 
<!-- Fila de información -->     
<div class="tabla_fila clearfix <%=resaltador%>">

	<div class="columna1">
       	<strong class="clearfix">Circuito Conmutado</strong>
    </div>
         
    <div class="columna2">
       	<a class="ico_interrogacionNuevo autoTooltip" href="#TTCircuitoConmutado"></a>
   	</div>

    <div class="columna3">
    	<!-- Mensaje grande -->
        <h:panelGroup layout="block" styleClass="mensaje_grande #{administracionServicios.administracionServiciosBean.servicioWap.activo ? 'mensaje_grande_habilitado' :'mensaje_grande_deshabilitado'}">
        	<h:outputText value="#{administracionServicios.administracionServiciosBean.servicioWap.activo ? 'Habilitado' :'Deshabilitado'}"/>
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

<entel:view name="bam">
<!-- Fila de información -->
<h:panelGroup rendered="#{administracionServicios.administracionServiciosBean.servicioWap.visible}">

<div class="tabla_fila clearfix <%=resaltador%>">
    <h:form>
	<jsp:include page="/token.jsp" flush="true"/>
	<div class="estadoHabilitado clearfix <h:outputText value="#{administracionServicios.administracionServiciosBean.servicioWap.activo ? '' :'ocultar'}"/>">
    
    	<div class="columna1">
        	<strong class="clearfix">Circuito Conmutado</strong>
        </div>
             
        <div class="columna2">
           	<a class="ico_interrogacionNuevo autoTooltip" href="#TTCircuitoConmutado"></a>
       	</div>
         
        <div class="columna3">
        	<!-- Mensaje habilitado/deshabilitado -->
            <div class="mensaje habilitado">Habilitado</div>
                       
			<cm:getProperty node="${mensajeDeshabilitar[0]}" name="html" />
           	<!-- <label class="titulo_inferior">Para desactivar este servicio deber&aacute;s llamar desde tu m&oacute;vil al 103</label>  -->
                       
        </div>
     </div>
                    
    <div class="estadoHabilitado1 clearfix ocultar">
      	<div class="columna1">
        	<strong class="clearfix">Circuito Conmutado</strong>
		</div>
             
        <div class="columna2">
	       	<a class="ico_interrogacionNuevo autoTooltip" href="#TTCircuitoConmutado"></a>
		</div>
             
		<div class="columna3">
             
           	<!-- Mensaje grande -->
            <div class="mensaje_grande mensaje_grande_reloj">
                 	En proceso de <strong>habilitaci&oacute;n</strong>
			</div>
     	</div>
	</div>
                 
    <div class="estadoDeshabilitado clearfix <h:outputText value="#{administracionServicios.administracionServiciosBean.servicioWap.activo ? 'ocultar' :''}"/>">
       	<div class="columna1">
        	<strong class="clearfix">Circuito Conmutado</strong>
        </div>
           
        <div class="columna2">
           	<a class="ico_interrogacionNuevo autoTooltip" href="#TTCircuitoConmutado"></a>
		</div>
           
        <div class="columna3">
           
        	<!-- Mensaje habilitado/deshabilitado -->
            <div class="mensaje deshabilitado">Deshabilitado</div>
                      
            <!-- Btoón deshabilitado/habilitado -->
             <div class="btnHabilitar">
                <a class="btnAzulDelgado btnAncho caso1H" href="#" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Adm. de Servicios/Datos/Circuito Conmutado/Habilitar');"><span>Habilitar</span></a>
             </div>
                       
       	</div>
	</div>
    
	<!-- Paso Confirmar Habilitar -->
	  <div class="confirmarHabilitar clearfix ocultar">	
			<div class="columna1">
				<strong class="clearfix">Circuito Conmutado</strong>
			</div>
			<div class="columna2">
				<a class="ico_interrogacionNuevo autoTooltip" href="#TTCobroRevertido"></a>
			</div>
			<div class="columna3">
				<!-- Mensaje habilitado/deshabilitado -->
				<div class="mensaje_ancho"><strong>Habilitar&aacute;s Circuito Conmutado</strong>
				</div>
				
				<!-- Btoon deshabilitado/habilitado -->
			<!-- Btoon deshabilitado/habilitado -->
				<div class="btnHabilitar">
				    <h:commandLink  styleClass="btnAzulDelgado btnAncho caso3" action="#{wapController.activarWap}" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Adm. de Servicios/Datos/Circuito Conmutado/Habilitado');"><span>Habilitar</span></h:commandLink>
				</div>
				
				<!-- Boton Cancelar -->
				<div class="enlaceCancelar">
					<a href="#" class="enlaceCancelarH">Cancelar</a>
				</div>
			</div>
		</div>
		<!-- Fin Paso Confirmar Habilitar -->
	 
                
    <div class="estadoDeshabilitado1 clearfix ocultar">
          
       	<div class="columna1">
        	<strong class="clearfix">Circuito Conmutado</strong>
        </div>
             
        <div class="columna2">
          	<a class="ico_interrogacionNuevo autoTooltip" href="#TTCircuitoConmutado"></a>
        </div>
             
        <div class="columna3">
              
			<!-- Mensaje habilitado/deshabilitado -->
           	<div class="mensaje_ancho">
            	<strong>Deshabilitar&aacute;s Banda Ancha M&oacute;vil</strong>
             	<br />
         	</div>
                    
         	<!-- Btoón deshabilitado/habilitado -->
           	<div class="btnDeshabilitar">
             	<h:commandLink styleClass="btnAzulDelgado btnAncho2 caso4" action="#{wapController.desactivarWap}" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Adm. de Servicios/Datos/Circuito Conmutado/Deshabilitado');">
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
        	<strong class="clearfix">Circuito Conmutado</strong>
		</div>
              
        <div class="columna2">
        	<a class="ico_interrogacionNuevo autoTooltip" href="#TTCircuitoConmutado"></a>
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

<h:panelGroup rendered="#{!administracionServicios.administracionServiciosBean.servicioWap.visible}"> 
	<jsp:include page="../servicioNoDisponible.jsp" flush="true">
  	<jsp:param value="Circuito Conmutado" name="serviceName"/>
  	<jsp:param value="" name="cargo"/>
  	<jsp:param value="#TTCircuitoConmutado" name="tooltip"/>
  	</jsp:include>
</h:panelGroup>	                

<!-- /Fila de información -->
</entel:view>