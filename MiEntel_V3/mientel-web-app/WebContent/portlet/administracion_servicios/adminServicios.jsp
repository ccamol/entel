<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="entel" uri="/WEB-INF/tld/entel-tags.tld" %>
<%@ taglib uri="http://www.bea.com/servers/portal/tags/netuix/preferences" prefix="preferences" %>
<%@ taglib uri="http://www.bea.com/servers/portal/tags/content" prefix="cm"%>
<cm:search id="tituloTabMKT" query="idContenido = 'tituloTabMKT'" useCache="true"  />

<preferences:getPreference name="flagMobileMarketing" var="flagMobileMarketing"/>

<script type="text/javascript">
	function obtenerParametroURL(name) {		 
		var regexS = "[\\?&]"+name+"=([^&#]*)"; 
		var regex = new RegExp(regexS); 
		var tmpURL = window.location.href; 
		var results = regex.exec(tmpURL); 
	 
		if (results == null) { 
			return "" ; 
		} else { 
			return results[1]; 
		} 
	}

	$(document).ready(function(){
		var param = obtenerParametroURL("contexto"); 

		if (param == 'mobileMKT') {
			$(".contenido4").trigger("click");
		}

		$('div.linea_tabs').find('div.tab.seleccionado').trigger('click');
	});
	
</script>

<f:view beforePhase="#{administracionServicios.init}" >
<h1>Administraci&oacute;n de servicios</h1>

<!-- MENSAJES -->
<jsp:include page="../common/messages_table.jsp"></jsp:include>

<%
	int rowNumber = 1;
%>

<h:panelGroup rendered="#{!(administracionServicios.administracionServiciosBean==null)}">
<div class="linea_tabs clearfix">

	<div class="tab contenido1" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Adm. de Servicios/Voz');">
		<span class="voz">
			Voz
		</span>
	</div>

	<div class="tab contenido2" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Adm. de Servicios/Mensajeria');">
		<span class="mensajeria">
			Mensajer&iacute;a
		</span>
	</div>

	<div class="tab contenido3" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Adm. de Servicios/Datos');">
		<span class="datos">
			Datos
		</span>
	</div>
	
	<c:if test="${flagMobileMarketing==1}">
		<div class="tab contenido4" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Adm. de Servicios/Mobile Marketing');">
			<span class="mobil_marketing">
				<cm:getProperty node="${tituloTabMKT[0]}" name="html" />  
			</span>
		</div>
	</c:if>
	
	

</div>

<div class="contenido_tabs">
	
	<div class="contenido_tab contenido1">
    
    	<br /><br />
    	
    	<p>Estos servicios te permitir&aacute;n estar siempre comunicado a trav&eacute;s de funciones asociadas a las llamadas telef&oacute;nicas. <br />Algunos son gratuitos, otros pueden tener cargos fijos o solamente se cobrar&aacute; su uso.</p>
	
		<!-- Tabla Interactiva de Voz -->
        <div id="tabla_informacion" class="tabla_informacion clearfix">
			
			<c:if test="${(profile.mercado eq miEntelBean.siglaSuscripcion) or (profile.mercado eq miEntelBean.siglaCuentaControlada)}">
			<!-- Buzon de voz Premium (SS y CC) -->
        	<jsp:include page="servicios/voz/buzonPremium.jsp" flush="true">
        		<jsp:param value="<%=rowNumber%>" name="rowNumber"/>
        	</jsp:include>
        	<% rowNumber++; %>
			</c:if>

			<c:if test="${profile.mercado eq miEntelBean.siglaPrepago}">
			<!-- Buzon de voz (PP) -->           	
        	<jsp:include page="servicios/voz/servicioBuzon.jsp" flush="true">
        		<jsp:param value="<%=rowNumber%>" name="rowNumber"/>
        	</jsp:include>
        	<% rowNumber++; %>
        	</c:if>

			<!-- Notificacion SMS -->           	
        	<jsp:include page="servicios/voz/smsNotificacion.jsp" flush="true">
        		<jsp:param value="<%=rowNumber%>" name="rowNumber"/>
        	</jsp:include>
        	<% rowNumber++; %>
			
			<c:if test="${profile.mercado eq miEntelBean.siglaSuscripcion}">
			<!-- Cobro revertido -->           	
        	<jsp:include page="servicios/voz/cobroRevertido.jsp" flush="true">
        		<jsp:param value="<%=rowNumber%>" name="rowNumber"/>
        	</jsp:include>
        	<% rowNumber++; %>
			</c:if>

			<c:if test="${(profile.mercado eq miEntelBean.siglaCuentaControlada) or (profile.mercado eq miEntelBean.siglaSuscripcion)}">
			<!-- Avisale -->           	
        	<jsp:include page="servicios/voz/avisale.jsp" flush="true">
        		<jsp:param value="<%=rowNumber%>" name="rowNumber"/>
        	</jsp:include>
        	<% rowNumber++; %>
        	</c:if>

			<c:if test="${profile.mercado eq miEntelBean.siglaSuscripcion}">
			<!-- Cobro revertido automatico 
				NO IMPLEMENTADO, A ESPERA DE DEFINICIONES DE ENTEL 
        	<jsp:include page="servicios/voz/cobroRevertidoAutomatico.jsp" flush="true">
        		<jsp:param value="" name="rowNumber"/>
        	</jsp:include>
        	-->
        	</c:if>

			<c:if test="${(profile.mercado eq miEntelBean.siglaSuscripcion)}">
			<!-- Recepcion revertido automatico -->
        	<jsp:include page="servicios/voz/recepcionCobroRevertido.jsp" flush="true">
        		<jsp:param value="<%=rowNumber%>" name="rowNumber"/>
        	</jsp:include>
        	<% rowNumber++; %>
        	</c:if>
        	
            <c:if test="${(profile.mercado eq miEntelBean.siglaSuscripcion)}">
			<!-- Acceso Larga Distancia Nacional -->           	
        	<jsp:include page="servicios/voz/accesoLDI.jsp" flush="true">
        		<jsp:param value="<%=rowNumber%>" name="rowNumber"/>
        	</jsp:include>
        	<% rowNumber++; %>
            </c:if>
            
            <c:if test="${(profile.mercado eq miEntelBean.siglaCuentaControlada) or (profile.mercado eq miEntelBean.siglaSuscripcion)}">
			<!-- Numeracion 300 -->           	
        	<jsp:include page="servicios/voz/numeracion300.jsp" flush="true">
        		<jsp:param value="<%=rowNumber%>" name="rowNumber"/>
        	</jsp:include>
        	<% rowNumber++; %>
            </c:if>                        
            
			<c:if test="${(profile.mercado eq miEntelBean.siglaCuentaControlada) or (profile.mercado eq miEntelBean.siglaSuscripcion)}">
			<!-- Numeracion 700 -->           	
        	<jsp:include page="servicios/voz/numeracion700.jsp" flush="true">
        		<jsp:param value="<%=rowNumber%>" name="rowNumber"/>
        	</jsp:include>
        	<% rowNumber++; %>
            </c:if>
                        
		</div>
        <!-- /Tabla Interactiva de Voz -->
           
	</div>
	
	<div class="contenido_tab contenido2">
		<br /><br/>
		<p>Servicios que te permitir&aacute;n estar siempre comunicado a trav&eacute;s de funciones asociadas a las llamadas telef&oacute;nicas. <br />Algunos son gratuitos, otros pueden tener cargos fijos o solamente se cobrar&aacute; su uso.</p>
		
		<!-- Tabla Interactiva de Mensajeria -->
        <div id="tabla_informacion" class="tabla_informacion clearfix">
       
       		<c:if test="${(profile.mercado eq miEntelBean.siglaSuscripcion)}">
			<!-- Mensajeria Multimedia SS-->           	
        	<jsp:include page="servicios/mensajeria/mensajeriaMultimediaSS.jsp" flush="true">
        		<jsp:param value="<%=rowNumber%>" name="rowNumber"/>
        	</jsp:include>
        	<% rowNumber++; %>
        	</c:if>
        	
        	<c:if test="${(profile.mercado eq miEntelBean.siglaCuentaControlada) or (profile.mercado eq miEntelBean.siglaPrepago)}">
        	<!-- Mensajeria Multimedia CC y PP -->           	
        	<jsp:include page="servicios/mensajeria/mensajeriaMultimedia.jsp" flush="true">
        		<jsp:param value="<%=rowNumber%>" name="rowNumber"/>
        	</jsp:include>
        	<% rowNumber++; %>
        	</c:if>

            <c:if test="${(profile.mercado eq miEntelBean.siglaCuentaControlada) or (profile.mercado eq miEntelBean.siglaSuscripcion)}">
			<!-- Aviso Vencimiento Factura -->           	
        	<jsp:include page="servicios/mensajeria/avisoVencimientoFactura.jsp" flush="true">
        		<jsp:param value="<%=rowNumber%>" name="rowNumber"/>
        	</jsp:include>
        	<% rowNumber++; %>
            </c:if>

        </div>
        <!-- /Tabla Interactiva de Mensajeria -->					
	</div>
	
	<div class="contenido_tab contenido3">
		<br /><br />
		<p>Servicios que te permitir&aacute;n estar siempre comunicado a trav&eacute;s de funciones asociadas a Internet. <br />Algunos son gratuitos, otros pueden tener cargos fijos o solo se cobra su utilizaci&oacute;n.</p>

		<!-- Tabla Interactiva de Datos -->
        <div id="tabla_informacion" class="tabla_informacion clearfix">

			<!-- Circuito Conmutado -->           	
        	<jsp:include page="servicios/datos/circuitoConmutado.jsp" flush="true">
        		<jsp:param value="<%=rowNumber%>" name="rowNumber"/>
        	</jsp:include>
        	<% rowNumber++; %>

            <c:if test="${(profile.mercado eq miEntelBean.siglaSuscripcion)}">
			<!-- Internet movil -->           	
        	<jsp:include page="servicios/datos/internetMovil.jsp" flush="true">
        		<jsp:param value="<%=rowNumber%>" name="rowNumber"/>
        	</jsp:include>
        	<% rowNumber++; %>
            </c:if>

			<c:if test="${(profile.mercado eq miEntelBean.siglaSuscripcion)}">
			<!-- Banda Ancha Movil -->           	
        	<jsp:include page="servicios/datos/bam.jsp" flush="true">
        		<jsp:param value="<%=rowNumber%>" name="rowNumber"/>
        	</jsp:include>
        	<% rowNumber++; %>
            </c:if>

            <c:if test="${(profile.mercado eq miEntelBean.siglaCuentaControlada) or (profile.mercado eq miEntelBean.siglaPrepago)}">
			<!-- Internet movil - BAM -->           	
        	<jsp:include page="servicios/datos/internetMovil_bam.jsp" flush="true">
        		<jsp:param value="<%=rowNumber%>" name="rowNumber"/>
        	</jsp:include>
        	<% rowNumber++; %>
            </c:if>
            
            <c:if test="${(profile.mercado eq miEntelBean.siglaCuentaControlada) or (profile.mercado eq miEntelBean.siglaSuscripcion)}">
			<!-- Numeracion 606 -->           	
        	<jsp:include page="servicios/voz/numeracion606.jsp" flush="true">
        		<jsp:param value="<%=rowNumber%>" name="rowNumber"/>
        	</jsp:include>
        	<% rowNumber++; %>
            </c:if>
            
            <c:if test="${(profile.mercado eq miEntelBean.siglaCuentaControlada) or (profile.mercado eq miEntelBean.siglaSuscripcion)}">
			<!-- Numeracion 609 -->           	
        	<jsp:include page="servicios/voz/numeracion609.jsp" flush="true">
        		<jsp:param value="<%=rowNumber%>" name="rowNumber"/>
        	</jsp:include>
        	<% rowNumber++; %>
            </c:if> 

        </div>
        <!-- /Tabla Interactiva de Mensajeria -->
	</div>
	
	<c:if test="${flagMobileMarketing==1}">
		<div class="contenido_tab contenido4">			
		
			<h:panelGroup rendered="#{(administracionServicios.consultaSAGEN==null)}">
				<jsp:include page="/portlet/administracion_servicios/servicios/mobileMarketing/inscripcion.jsp"/>
			</h:panelGroup>
			
			<h:panelGroup rendered="#{!(administracionServicios.consultaSAGEN==null)}">				
				<jsp:include page="/portlet/administracion_servicios/servicios/mobileMarketing/actualizacionInscripcion.jsp"/>				
			</h:panelGroup>		
		</div>
	</c:if>	
                
</div>

</h:panelGroup>

<!-- Tooltips -->           	
<jsp:include page="servicios/toolTips.jsp" flush="true"/>

</f:view>