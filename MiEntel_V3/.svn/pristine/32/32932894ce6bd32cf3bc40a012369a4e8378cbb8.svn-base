<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<f:view>

<h1>Cambiar Clave</h1>

<!-- MENSAJES -->
<jsp:include page="../common/messages_table.jsp"></jsp:include>

<!-- NUEVA CLAVE -->
<div class="mis-datos-info">
	<h:form id="formulario-nueva-clave" styleClass="nueva-clave">
		<jsp:include page="/token.jsp" flush="true"/>
		<div class="formulario-clave">

		<div class="box-clave-actual">

		<div class="mis-datos-fila clearfix"><label><strong>Ingresa
		tu clave actual:</strong></label>
		<div class="campo">
		<h:inputSecret id="claveActual" 
			required="true"
			value="#{seguridadController.usuarioBean.claveActual}"
			styleClass="inputBox clave_actual"
			style="width:170px"
			onkeypress="return soloNumeros(event);" 
			maxlength="4" 
			requiredMessage="Ingresa tu clave actual."
			converter="#{Integer}"
			converterMessage="Tu clave actual debe ser numerica." /></div>
		</div>
		<!-- /misDatos_fila --></div>
		<!-- /boxClaveActual -->


		<div class="boxNuevaClave">

		<div class="mis-datos-fila clearfix"><label><strong>Ingresa tu clave nueva:</strong></label>
		<div class="campo">
		<div class="campo-amarillo"><span class="left"></span>
		<h:inputSecret
			required="true" 
			id="nuevaClave" 
			value="#{seguridadController.usuarioBean.claveNueva}"
			style="width:160px;" 
			styleClass="nueva_clave" 
			onkeypress="return soloNumeros(event);"
			maxlength="4" 
			requiredMessage="Ingresa tu nueva clave."
			converter="#{Integer}"
			converterMessage="Tu nueva clave debe ser numerica."/>
			<span class="right"></span></div>
		</div>
		<div class="campo"><span>(Num&eacute;rica de 4 d&iacute;gitos)</span></div>
		</div>
		<!-- /misDatos_fila -->

		<div class="mis-datos-fila clearfix"><label><strong>Reingresa tu clave nueva:</strong></label>
		<div class="campo">
		<div class="campo-amarillo"><span class="left"></span>
		<h:inputSecret
			required="true" id="nuevaClave2"
			value="#{seguridadController.usuarioBean.claveNuevaReingreso}" style="width:160px;"
			onkeypress="return soloNumeros(event);" 
			maxlength="4" 
			styleClass="nueva_clave2"
			requiredMessage="Reingresa tu clave nueva."
			converter="#{Integer}"
			converterMessage="Tu clave nueva debe ser numerica."/>
			<span class="right"></span></div>
		</div>
		</div>
		<!-- /misDatos_fila -->
		<div class="guardar_clave"><h:commandLink value=""
			styleClass="btnAzulGrande btnAzulLargo"
			action="#{seguridadController.cambiarClave}">
			<span>Aceptar</span>
		</h:commandLink></div>
		
		
		
		<!-- /misDatos_guardar --></div>
		<!-- /boxNuevaClave --></div>

	</h:form></div>
	<!-- /misDatos_datos --> <!-- /NUEVA CLAVE -->

	<!-- /centro -->
	<!-- /contenido -->

</f:view>