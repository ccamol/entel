<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<f:view>
<h1>Pago autom&aacute;tico</h1>
			
			<p>Entel facilita el pago de tu cuenta a trav&eacute;s de estas dos modalidades de pago autom&aacute;tico.</p><br />

	<div id="pago-automatico-paso2" class="clearfix" style="display:block;">

				<h2 class="sin_icono">Paso 2 de 3</h2>
					<p><strong>Los datos de mi tarjeta</strong></p><br />
		         <h:form>
		         	<jsp:include page="/token.jsp" flush="true"/>		
				<div class="formulario">
					<div class="formulario_item clearfix">
						<div class="formulario_label formulario_label_pat formulario_label_pat_2">Fecha:</div>
						<div class="formulario_input formulario_input_pat"> <h:outputText value="#{pagoAutomaticoController.fechaActual}">
						<f:convertDateTime pattern="dd/MM/yyyy"/>
						</h:outputText></div>
					</div>

					<div class="formulario_item clearfix">
						<div class="formulario_label formulario_label_pat formulario_label_pat_2">Nombre del titular:</div>
						<div class="formulario_input formulario_input_pat">
						<h:outputText value="#{pagoAutomaticoController.pagoAutomaticoBean.titular}"/>
						<h:inputHidden value="#{pagoAutomaticoController.pagoAutomaticoBean.titular}"/>
						</div>
					</div>
					<div class="formulario_item clearfix">
						<div class="formulario_label formulario_label_pat formulario_label_pat_2">Rut del titular:</div>
						<div class="formulario_input formulario_input_pat">
						<h:outputText value="#{pagoAutomaticoController.pagoAutomaticoBean.rut}" converter="rutConverter"/>
						<h:inputHidden value="#{pagoAutomaticoController.pagoAutomaticoBean.rut}"/>
						</div>

					</div>									
					<div class="formulario_item clearfix">
						<div class="formulario_label formulario_label_pat formulario_label_pat_2">E-mail:</div>
						<div class="formulario_input formulario_input_pat"><h:outputText value="#{pagoAutomaticoController.pagoAutomaticoBean.email}"/>
						<h:inputHidden value="#{pagoAutomaticoController.pagoAutomaticoBean.email}"/>
						</div>
					</div>
					<div class="formulario_item clearfix">
						<div class="formulario_label formulario_label_pat formulario_label_pat_2">Tel&eacute;fono:</div>

						<div class="formulario_input formulario_input_pat">
						<h:outputText value="#{pagoAutomaticoController.pagoAutomaticoBean.telefonoArea}"/>&nbsp;<h:outputText value="#{pagoAutomaticoController.pagoAutomaticoBean.telefono}"/>
						<h:inputHidden value="#{pagoAutomaticoController.pagoAutomaticoBean.telefonoArea}"/>
						<h:inputHidden value="#{pagoAutomaticoController.pagoAutomaticoBean.telefono}"/>
						</div>
					</div>
					<div class="formulario_item clearfix">
						<div class="formulario_label formulario_label_pat formulario_label_pat_2">N&uacute;mero de tarjeta de cr&eacute;dito:</div>
						<div class="formulario_input formulario_input_pat">
						<h:outputText value="#{pagoAutomaticoController.numeroTarjetaOculto}"/>
						<h:inputHidden value="#{pagoAutomaticoController.pagoAutomaticoBean.tipoTarjeta}"/>
						<h:inputHidden value="#{pagoAutomaticoController.pagoAutomaticoBean.numeroTarjeta}"/>
						</div>
					</div>														
					<div class="formulario_item clearfix">

						<div class="formulario_label formulario_label_pat">&nbsp;</div>
						<div class="formulario_input">
						 <h:commandLink value="" action="#{pagoAutomaticoController.inscribirPAT}" 	
		              	    	styleClass="btnAzulGrande btnAzulGrandeLargo"><span>Confirmar</span></h:commandLink>
						</div>
					</div>							
				</div>
				</h:form>
			</div>
</f:view>

