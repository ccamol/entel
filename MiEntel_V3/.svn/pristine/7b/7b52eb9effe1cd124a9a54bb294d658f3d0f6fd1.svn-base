<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="it" uri="http://i2b.cl/jsf/iterator/" %>
<%@ taglib prefix="r"  uri="http://www.bea.com/servers/portal/tags/netuix/render"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<f:view beforePhase="#{planController.obtenerPlanActual}">

<!--CONTENEDOR PRINCIPAL-->
		<!-- centro -->
				
			<h1><h:outputText value="#{planController.infoTitulo}"></h:outputText></h1>
				
			<div class="tarifaPP">
				
				<!-- MENSAJES -->
				<jsp:include page="../common/messages_table.jsp"></jsp:include>

		        <h:panelGroup rendered="#{planController.planActualPP != null}">
		        
					<!-- ESTRUCTURA TARIFARIA -->
					<div class="estructuraTarifaria">
						<h2 class="superchip">
		
							<strong><h:outputText value="#{planController.planActualPP.nombrePlan}"></h:outputText> : </strong>
							<span><h:outputText value="#{planController.planActualPP.breveDescripcion}"></h:outputText></span>
						</h2>
						
						<div class="info info_saldo clearfix">
		                    <div class="ico_saldo div_saldo">
		                        Saldo: <span id="saldoPlanActual" class="resaltado">&nbsp;$&nbsp; 
		                        <h:outputText value="#{planController.planResumen.saldo}">
		                        	<f:convertNumber currencyCode="CLP" locale="es" />
		                        </h:outputText></span>
		                    </div>
		
		                    <div class="ico_cal">
		                        Expira: <h:outputText value="#{planController.planResumen.fechaExpiracion}"><f:convertDateTime pattern="dd/MM/yyyy" locale="es" /></h:outputText>
		                    </div>
		                </div>
		                
		                <div class="info info_saldo clearfix">
		                    <div class="ico_saldo div_saldo">
		                        Saldo Reservado: <span id="saldoReservadoPlanActual" class="resaltado">&nbsp;$&nbsp; 
		                        <h:outputText value="#{planController.planResumen.saldoReservado}">
		                        	<f:convertNumber currencyCode="CLP" locale="es" />
		                        </h:outputText></span>
		                    </div>
		
		                    <h:panelGroup layout="block" styleClass="ico_cal" rendered="#{planController.planResumen.saldoReservado > 0}">
		                        Expira: <h:outputText value="#{planController.planResumen.fechaExpiracionSaldoReservado}"><f:convertDateTime pattern="dd/MM/yyyy" locale="es" /></h:outputText>
		                    </h:panelGroup>
		                </div>
						
						<!-- tabla bolsas-->
		
						<div class="cont_dual">
							<div class="dual_top2"></div>
							<div class="dual_dos2 clearfix">
								<div>Tarifa</div>
								<div class="dual_ultimo derecha">Descripci&oacute;n</div>
							</div>
						</div>
		
						<div class="dual_tres2 clearfix">
		
							<div><h:outputText value="#{planController.planActualPP.nombrePlan}"></h:outputText></div>
							<div id="descripcionPlan" class="dual_ultimo derecha">
							
							<!--  informacion glosa plan -->
							
							<it:iterator var="item" value="#{planController.planActualPP.glosaFormated}" rowIndexVar="index">	
								 
						       <h:panelGroup rendered="#{index > 0}">
								- <h:outputText value="#{item}"></h:outputText>.<br>
						       </h:panelGroup>
						       
						    </it:iterator>
			
							<!--  /informacion glosa plan -->
							</div>
						</div>
		
						<!--/tabla bolsas-->			
					</div>		
					<!-- /ESTRUCTURA TARIFARIA -->
				
					<div class="separacion_planes"></div>

				</h:panelGroup>
			</div>
		<!-- /centro -->
		
	<script type="text/javascript">
		$(document).ready(function(){ 
			$('.tarifaPP').find('h1').remove();
		});
	</script>
</f:view>
