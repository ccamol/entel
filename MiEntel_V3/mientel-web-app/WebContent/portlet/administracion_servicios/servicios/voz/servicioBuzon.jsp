<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="entel" uri="/WEB-INF/tld/entel-tags.tld" %>

<% 		
	String resaltador = "";
	if(request.getParameter("rowNumber") != null){
		resaltador = (Integer.parseInt(request.getParameter("rowNumber"))) % 2 == 0? "resaltador":"";
	}
%>
<entel:view name="buzonVoz" inverse="true"> 

<!-- Fila de información -->     
<div class="tabla_fila clearfix <%=resaltador%>">

	<div class="columna1">
		<strong class="clearfix">Buz&oacute;n de Voz</strong>
		<label class="titulo_inferior">Cargo por uso</label>
	</div>
	<div class="columna2">
		<a class="ico_interrogacionNuevo autoTooltip" href="#TTBuzondeVoz"></a>
	</div>

    <div class="columna3">
    	<!-- Mensaje grande -->
        <h:panelGroup layout="block" styleClass="mensaje_grande #{administracionServicios.administracionServiciosBean.servicioBuzon.activo ? 'mensaje_grande_habilitado' :'mensaje_grande_deshabilitado'}">
        	<h:outputText value="#{administracionServicios.administracionServiciosBean.servicioBuzon.activo ? 'Habilitado' :'Deshabilitado'}"/>
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

<entel:view name="buzonVoz">
<!-- Fila de información -->
<h:panelGroup rendered="#{administracionServicios.administracionServiciosBean.servicioBuzon.visible}">
<div class="tabla_fila clearfix <%=resaltador%>">
    <h:form>
		<jsp:include page="/token.jsp" flush="true"/>
	<div class="estadoHabilitado clearfix <h:outputText value="#{administracionServicios.administracionServiciosBean.servicioBuzon.activo? '' :'ocultar'}"/>">
    
    	<div class="columna1">
           	<strong class="clearfix">Buz&oacute;n de Voz</strong>
            <label class="titulo_inferior">Cargo por uso</label>
        </div>
             
        <div class="columna2">
           	<a class="ico_interrogacionNuevo autoTooltip" href="#TTBuzondeVoz"></a>
       	</div>
         
        <div class="columna3">
        	<!-- Mensaje habilitado/deshabilitado -->
            <div class="mensaje habilitado">Habilitado</div>
                       
           	<!-- Btoón deshabilitado/habilitado -->
            <div class="btnDeshabilitar">
            	<a class="btnAzulDelgado btnAncho2 caso1" href="#" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Adm. de Servicios/Voz/Buzon de Voz/Deshabilitar');"><span>Deshabilitar</span></a>
            </div>

        </div>
     </div>

    <div class="estadoHabilitado1 clearfix ocultar">
      	<div class="columna1">
          	<strong class="clearfix">Buz&oacute;n de Voz</strong>
            <label class="titulo_inferior">Cargo por uso</label>
		</div>

        <div class="columna2">
	       	<a class="ico_interrogacionNuevo autoTooltip" href="#TTBuzondeVoz"></a>
		</div>

		<div class="columna3">

           	<!-- Mensaje grande -->
            <div class="mensaje_grande mensaje_grande_reloj">
                 	En proceso de <strong>habilitaci&oacute;n</strong>
			</div>
     	</div>
	</div>

    <div class="estadoDeshabilitado clearfix <h:outputText value="#{administracionServicios.administracionServiciosBean.servicioBuzon.activo? 'ocultar' : ''}"/>">
       	<div class="columna1">
			<strong class="clearfix">Buz&oacute;n de Voz</strong>
            <label class="titulo_inferior">Cargo por uso</label>
        </div>
           
        <div class="columna2">
           	<a class="ico_interrogacionNuevo autoTooltip" href="#TTBuzondeVoz"></a>
		</div>
           
        <div class="columna3">
           
        	<!-- Mensaje habilitado/deshabilitado -->
            <div class="mensaje deshabilitado">Deshabilitado</div>
                      
            <!-- Btoón deshabilitado/habilitado -->
             <div class="btnHabilitar">
             	<h:commandLink styleClass="btnAzulDelgado btnAncho caso3" action="#{buzonVozController.activarBuzonBasico}" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Adm. de Servicios/Voz/Buzon de Voz/Habilitar');">
             			<span>Habilitar</span></h:commandLink>
             </div>
                       
       	</div>
	</div>
                
    <div class="estadoDeshabilitado1 clearfix ocultar">
          
       	<div class="columna1">
           	<strong class="clearfix">Buz&oacute;n de Voz</strong>
            <label class="titulo_inferior">Cargo por uso</label>
        </div>
             
        <div class="columna2">
          	<a class="ico_interrogacionNuevo autoTooltip" href="#TTBuzondeVoz"></a>
        </div>
             
        <div class="columna3">
              
			<!-- Mensaje habilitado/deshabilitado -->
           	<div class="mensaje_ancho">
            	<strong>Deshabilitar&aacute;s Buz&oacute;n de Voz</strong>
             	<br />
             	Los n&uacute;meros configurados ser&aacute;n eliminados.
         	</div>
                    
         	<!-- Btoón deshabilitado/habilitado -->
           	<div class="btnDeshabilitar">
             	<h:commandLink styleClass="btnAzulDelgado btnAncho2 caso4" action="#{buzonVozController.desactivarBuzon}" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Adm. de Servicios/Voz/Buzon de Voz/Deshabilitado');">
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
        	<strong class="clearfix">Buz&oacute;n de Voz</strong>
            <label class="titulo_inferior">Cargo por uso</label>
		</div>
              
        <div class="columna2">
        	<a class="ico_interrogacionNuevo autoTooltip" href="#TTBuzondeVoz"></a>
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

<h:panelGroup rendered="#{!administracionServicios.administracionServiciosBean.servicioBuzon.visible}"> 
	<jsp:include page="../servicioNoDisponible.jsp" flush="true">
   	<jsp:param value="Buz&oacute;n de Voz" name="serviceName"/>
   	<jsp:param value="Cargo por uso" name="cargo"/>
   	<jsp:param value="TTBuzondeVoz" name="tooltip"/>
</jsp:include>
</h:panelGroup>	                

<!-- /Fila de información -->
</entel:view>