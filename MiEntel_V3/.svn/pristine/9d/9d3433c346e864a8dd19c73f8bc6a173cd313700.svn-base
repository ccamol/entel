<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<f:view>
<h1>Pago autom&aacute;tico</h1>
			
			<p>Entel facilita el pago de tu cuenta a trav&eacute;s de estas dos modalidades de pago autom&aacute;tico.</p><br />

<h:panelGroup binding="#{pagoAutomaticoController.panelResultado}">				
			<div id="pago-automatico-paso3" class="clearfix" style="display:block;">
				<h2 class="sin_icono">Paso 3 de 3</h2>

					<p><strong>La solicitud de inscripci&oacute;n ha sido recibida.</strong><br />Nos contactaremos a la brevedad para confirmarte la inscripci&oacute;n</p><br />
				
				<div class="clearfix">	
					<div class="pago_automatico_correcto"></div>
					<div class="formulario formulario_pat tamano-form-pat">
						<div class="formulario_item clearfix">
							<div class="formulario_label formulario_label_pat formulario_label_pat_2">Fecha:</div>
							<div class="formulario_input formulario_input_pat"><h:outputText value="#{pagoAutomaticoController.fechaActual}">
						<f:convertDateTime pattern="dd/MM/yyyy"/>
						</h:outputText></div>

						</div>
						<div class="formulario_item clearfix">
							<div class="formulario_label formulario_label_pat formulario_label_pat_2">Nombre del titular:</div>
							<div class="formulario_input formulario_input_pat"><h:outputText value="#{pagoAutomaticoController.pagoAutomaticoBean.titular}"/></div>
						</div>
						<div class="formulario_item clearfix">
							<div class="formulario_label formulario_label_pat formulario_label_pat_2">Rut del titular:</div>

							<div class="formulario_input formulario_input_pat"><h:outputText value="#{pagoAutomaticoController.pagoAutomaticoBean.rut}" converter="rutConverter"/></div>
						</div>									
						<div class="formulario_item clearfix">
							<div class="formulario_label formulario_label_pat formulario_label_pat_2">E-mail:</div>
							<div class="formulario_input formulario_input_pat"><h:outputText value="#{pagoAutomaticoController.pagoAutomaticoBean.email}"/></div>
						</div>
						<div class="formulario_item clearfix">
							<div class="formulario_label formulario_label_pat formulario_label_pat_2">Tel&eacute;fono:</div>

							<div class="formulario_input formulario_input_pat"><h:outputText value="#{pagoAutomaticoController.pagoAutomaticoBean.telefono}"/></div>
						</div>
						<div class="formulario_item clearfix">
							<div class="formulario_label formulario_label_pat formulario_label_pat_2">N&uacute;mero de tarjeta de cr&eacute;dito:</div>
							<div class="formulario_input formulario_input_pat"><h:outputText value="#{pagoAutomaticoController.numeroTarjetaOculto}"/></div>
						</div>														
						<div class="formulario_item clearfix">

							<div class="formulario_label formulario_label_pat formulario_label_botones">
								<a href="javascript:;" onclick="window.print();" class="btnAzulGrande btnAzulGrandeLargo"><span>Imprimir</span></a>
								<a href="javascript:;" class="btnAzulGrande btnAzulGrandeLargo"><span>Enviar por e-mail</span></a>
							</div>
							<div class="formulario_input"></div>
						</div>							
					</div>
				</div>

			</div>		
</h:panelGroup>		
  <div class="contenedor-mensajes-verde clearfix">
	   <div></div>
	   	<h:messages id="messageList" 
			styleClass="mensajes-lista"
			errorClass="mensaje-error" 
			infoClass="mensaje-informacion" showSummary="true" />
	</div>
</f:view>

