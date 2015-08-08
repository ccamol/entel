<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="it" uri="http://i2b.cl/jsf/iterator/" %>
<%@ taglib prefix="r"  uri="http://www.bea.com/servers/portal/tags/netuix/render"%>
<%@ taglib prefix="cm" uri="http://www.bea.com/servers/portal/tags/content" %>
<%@ taglib prefix="entel" uri="/WEB-INF/tld/entel-tags.tld" %>
<%@ taglib prefix="pref" uri="http://www.bea.com/servers/portal/tags/netuix/preferences" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<f:view beforePhase="#{recargaController.init}">

	<h1>Recargas</h1>
	
	<h:form id="formRecargaZonaPuntos" styleClass="formRecargaZonaPuntos">
	<jsp:include page="/token.jsp" flush="true"/>
    <div class="margen_azul_superior"></div>
    <div class="margen_azul_contenido clearfix">
    	<span class="marginador clearfix">Selecciona como quieres recargar:</span>
    	
		<script type="text/javascript">
			$(document).ready(function() {
				//$("#top-select select").selectBox();
				$('.select-recarga')[0].setValue('<h:outputText value="#{recargaController.tipoRecarga}"></h:outputText>');

				var esVozBAM = "<h:outputText value="#{profile.flagBam == miEntelBean.siglaUsuarioBAM ? 'Banda Ancha Movil' : 'Telefonia'}"/>";

				dataLayer = dataLayer||[];
  				dataLayer.push({
    				'mx_content': 'Personas/Mi Entel/Mis Productos/' + esVozBAM + '/Recargas/Puntos Zona',
    				'event': 'pageview'
  				});
			});
		</script>
    	
    	<h:selectOneMenu id="select-recarga" styleClass="selectBox select-recarga" enabledClass="select-recarga" style="width: 350px;" 
    		value="#{recargaController.tipoRecarga}" valueChangeListener="#{recargaController.seleccionTipoRecarga}" onchange="submit();">
    		<f:selectItems value="#{recargaController.tiposRecarga}" /> 
    	</h:selectOneMenu>
    </div>    
    <div class="margen_azul_inferior"></div>
</h:form>      

 <entel:view name="alertaAAARecargaZonaPuntos">
  <br></br>
	          <cm:search id="infoRestriccionAAA0" query="idContenido = 'infoRestriccionAAA'" useCache="false"/>
				<div class="contenedor-mensajes">
			        <ul>
				        <li class="mensaje-alerta">
					        <div align="center"> 
					        	<span><cm:getProperty node="${infoRestriccionAAA0[0]}" name="html"/></span>
					        </div> 
				        </li>
			        </ul>
			     </div>
   </entel:view>
   
    <entel:view name="cuadroRecargarZonaPuntos">
   <h2 style="padding-left: 0;">Puntos Zona</h2>
      <script type="text/javascript">
	      $(document).ready(function() {
		      $("#pie").css( {					
					left : "154px",
					right:"50px",
				    position:"relative"
				});
		      $(".cajalinks").hide();
	      });	
	      
      </script>
     <jsp:include page="/portlet/vtasYMktgFidelizacion/verPuntos.jsp"></jsp:include>
     </div><div class="caja_historial_bottom"> </div></div></div>  
     <jsp:include page="/portlet/vtasYMktgFidelizacion/canjePuntosBolsasRecargas.jsp"></jsp:include> 
   </entel:view>
 
</f:view>		
 