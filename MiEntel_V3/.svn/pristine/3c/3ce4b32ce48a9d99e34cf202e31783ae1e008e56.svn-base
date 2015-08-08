<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cm" uri="http://www.bea.com/servers/portal/tags/content" %>
<%@ taglib prefix="r" uri="http://www.bea.com/servers/portal/tags/netuix/render" %>

<cm:search id="noInscritaInstrucciones" query="idContenido = 'noInscritaInstrucciones'" useCache="true" />
<cm:search id="marcaFacturacion" query="idContenido = 'marcaFacturacion'" useCache="true" />
<cm:search id="terminosBoletaElectronica" query="idContenido = 'terminosBoletaElectronica'" useCache="true" />
<cm:search id="includeImgSuscribeTuBE" query="idContenido = 'includeImgSuscribeTuBE'" useCache="false" />
<cm:search id="includeImgNuevoInscritoBE" query="idContenido = 'includeImgNuevoInscritoBE'" useCache="false" />
<cm:search id="debesSaberNoInscrito" query="idContenido = 'debesSaberNoInscrito'" useCache="false" />
<cm:search id="debesSaberModificarEmail" query="idContenido = 'debesSaberModificarEmail'" useCache="false" />

<script type="text/javascript">
<!--
	function closeGlobo(){
		$('#globoError').fadeOut();
	}
//-->

	/* EVENTOS DE LOS PASOS */
	$(document).ready(function(e) {
		$('.beModificarEmail').click(function(){
			$('#beSusPaso1').hide();
			$('#beSusPaso3').hide();
			$('#beSusPaso2').show();
			return false;
		});
					
		$('.beEnlDes').click(function(){
			$('#beSusPaso1').hide();
			$('#beSusPaso3').hide();
			$('#beDesPaso2').show();
			return false;			
		});		
		
		$('.beDesincribir input[type="radio"]').click(function(){
			if($(this).is(':checked')){
				$('.btDesCorreoConf').addClass('btnAzul').removeClass('btnDesactivado');
			}
		});
					
		$('.btDesCorreoConf').click(function(){
			$('#beDesPaso2').hide();
			if(!$('.btDesCorreoConf').hasClass('btnDesactivado')){
				var url = '<%=request.getContextPath()%>/portlet/facturacion/cancelarFEJSON.faces';
				$.ajax({
					type: 'POST',
					url: url,
					dataType: 'json',		
					success: function(data) {
						if (data.estado == 'Ok') {
							mxTracker._trackPageview('Personas/Mi Entel/Mi Cuenta/Boleta Electronica/Desinscripcion Realizada');
							$('#beDesPaso3').show();
						} else {
							$('#beDesError').show();
						}
					}
				});
			}
			return false;
		});
		
		$('#btDesCorreoCancelar').click(function(){
			$('#beSusPaso1').show();
			$('#beSusPaso3').hide();
			$('#beDesPaso2').hide();
			return false;				
		});

		$('.email_10').blur(function() {
			return validaEmail();
		});		

		$('.email_10').keyup(function() {
			return validaEmail();
		});

		$('.email_10').val($("input[id*=emailBUIC]").val());

		$('.email_20').blur(function() {
			return validaEmail();
		});		

		$('.email_20').keyup(function() {
			return validaEmail();
		});

		$('.email_20').bind('paste', function(e) {
			 e.preventDefault();
		});

		$('.email_20').val($("input[id*=emailBUIC]").val());

		$('.email_01').blur(function() {
			return validaEmailModificar();
		});

		$('.email_01').keyup(function() {
			return validaEmailModificar();
		});

		$('.email_02').blur(function() {
			return validaEmailModificar();
		});		

		$('.email_02').keyup(function() {
			return validaEmailModificar();
		});

		$('.email_02').bind('paste', function(e) {
			 e.preventDefault();
		});

		if ($('.nuevoInscritoBE').length) {
			dataLayer = dataLayer||[];
				dataLayer.push({
				'mx_content': 'Personas/Mi Entel/Mi Cuenta/Boleta Electronica/Inscripcion Realizada',
				'event': 'pageview'
			});
		} else {
			dataLayer = dataLayer||[];
			dataLayer.push({
				'mx_content': 'Personas/Mi Entel/Mi Cuenta/Boleta Electronica',
				'event': 'pageview'
			});
		}

		//
	});	 		
	/* /EVENTOS DE LOS PASOS */
	
	function validaEmail() {
		if ($('.email_10').val() == $('.email_20').val()) {
			if ($('.email_10').valid() && $('.email_20').valid()) {
				$('.btnAceptarBe').addClass('btnAzul').removeClass('btnDesactivado');
			}
		} else {
			if ($('.email_10').val() == "") {
				$('.email_20').val('');
			}
			$('.btnAceptarBe').addClass('btnDesactivado').removeClass('btnAzul');
		}
	}

	function validaEmailModificar() {
		if ($('.email_01').val() == $('.email_02').val()) {
			if ($('.email_01').valid() && $('.email_02').valid()) {
				$('.btnModificarBe').addClass('btnAzul').removeClass('btnDesactivado');
			}
		} else {
			$('.btnModificarBe').addClass('btnDesactivado').removeClass('btnAzul');
		}
	}	
	
	function validaFormModificar() {
		if ($('#centro form:first').valid()) {
			$('#beSusPaso1').hide();
			return true;		
		} else {
			return false;
		}
	}

    function toggleContratoNoInscrito(accion){
    	switch(accion){
    		case 1:
        		if($('#centro form:first').valid()){
            		$('#espacio-correofe-contrato').text($('#formulario-correofe-noinscrito .email_10').val()+' ');            		
        			$('#titulo-fe, #div-instrucciones-noinscrito, #formulario-correofe-noinscrito').hide();
            		$('#contrato-fe-noinscrito').show();
            	}        		
        	break;
    		case 2:    			
        		$('#contrato-fe-noinscrito').hide();
        		$('#titulo-fe, #div-instrucciones-noinscrito, #formulario-correofe-noinscrito').show();
        	break;
    	}
    }
</script>

<f:view beforePhase="#{facturacionElectronicaController.init}">

	<h:panelGroup rendered="#{facturacionElectronicaController.AAA != '3'}">
		<cm:search id="infoRestriccionAAA0"
			query="idContenido = 'infoRestriccionAAA0'" useCache="false" />
		<div class="contenedor-mensajes">
		<ul>
			<li class="mensaje-alerta">
			<div align="center"><span><cm:getProperty
				node="${infoRestriccionAAA0[0]}" name="html" /></span></div>
			</li>
		</ul>
		</div>
	</h:panelGroup>
	
	<h:panelGroup 
				rendered="#{facturacionElectronicaController.infoTitularBean == null}">
					
				<cm:search id="autorizacionVoluntariaError"
			query="idContenido = 'autorizacionVoluntariaError'" useCache="false" />
		<div class="contenedor-mensajes">
			<ul>
			<li class="mensaje-alerta">
			<div align="center"><span><cm:getProperty
				node="${autorizacionVoluntariaError[0]}" name="html" /></span></div>
			</li>
			</ul>
		</div>	
	</h:panelGroup>		
				
	<h:panelGroup rendered="#{facturacionElectronicaController.AAA == '3'}">
		<div id="marca-adwords"><cm:getProperty node='${marcaFacturacion[0]}' name='html'/></div>
		
		<h:panelGroup
			rendered="#{(facturacionElectronicaController.facturacionElectronicaBean == null && facturacionElectronicaController.infoTitularBean != null) || (facturacionElectronicaController.facturacionElectronicaBean != null && !facturacionElectronicaController.estadoInscrito && facturacionElectronicaController.infoTitularBean != null)}">
			<!-- CONTENIDO CENTRAL -->
			<h1 id="titulo-fe">Boleta por email</h1>
			
			<!-- CONTENIDO 1 -->	
			<div id="div-instrucciones-noinscrito">				
				<cm:getProperty node="${noInscritaInstrucciones[0]}" name="html" />
			</div>
			<!-- FIN CONTENIDO 1 -->
			
			<h:form id="ingresar_email" styleClass="ingresar_email">
				<jsp:include page="/token.jsp" flush="true"/>
				<div id="formulario-correofe-noinscrito" class="formulario margenTopTriple">					
					<div class="clearfix beForm1">
						<div class="formulario_item mostrar_globo clearfix">
						<div class="formulario_label">Email:</div>
							<div class="formulario_input">                            	
								<h:inputText id="email_10"
							styleClass="inputBox required email_10" maxlength="40" style="width:140px"
									value="#{facturacionElectronicaController.correoFE}"
									onblur="validaEmail();" onkeyup="validaEmail();"></h:inputText>
						</div>
						</div>
		
						<div class="formulario_item mostrar_globo clearfix">
						<div class="formulario_label">Confirmar email:</div>
                            <div class="formulario_input">                                
                            	<input type="text" id="email_20"
							name="email_20" maxlength="40" class="inputBox required email_20"
									style="width: 140px" onblur="validaEmail();" onkeyup="validaEmail();" />
						</div>
                            <div style="margin-top:-3px;" class="formulario_input">
                            	 <a class="btnAceptarBe btnDesactivado" onclick="toggleContratoNoInscrito(1); return false;" href="#">
							<span>Aceptar</span>
						</a>
						</div>
						</div>
						<c:choose>
						    <c:when test="${facturacionElectronicaController.usuarioBean.email != null}">
								<h:inputHidden id="emailBUIC" value="#{facturacionElectronicaController.usuarioBean.email}"/>
							</c:when>
						</c:choose>
					</div>
					
					<cm:getProperty node="${debesSaberNoInscrito[0]}" name="html" />										
								
					<div class="beLineaDiv margenTopCuatro"></div>
					<cm:getProperty node="${terminosBoletaElectronica[0]}" name="html" />
			  </div>
				
				
				<!-- SECCION DE DESPLIEGUE DE CONTRATO -->
				<div id="contrato-fe-noinscrito" style="display:none">

				<h:panelGroup 
				rendered="#{facturacionElectronicaController.infoTitularBean != null}">
				
					<h:outputText value="#{facturacionElectronicaController.autorizacionVoluntariaContent}" escape="false"/>
		
					<br/>
					<div align="center">					
						<a onclick="toggleContratoNoInscrito(2); return false;" class="btnAzul btnAzulLargo"><span>Cancelar</span></a>
						<h:commandLink action="#{facturacionElectronicaController.inscribirServicioFacturacionElectronica}" styleClass="btnAzul btnAzulLargo" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mi Cuenta/Boleta Electronica/Autorizacion');"><span>Aceptar</span></h:commandLink>
					</div>
					</h:panelGroup>
					
				</div>
			</h:form>		
				
			<!-- /CONTENIDO CENTRAL -->

		</h:panelGroup>

		<h:panelGroup
			rendered="#{facturacionElectronicaController.facturacionElectronicaBean != null && facturacionElectronicaController.estadoInscrito}">
			<h1 id="titulo-fe">Boleta por email</h1>
			
			<h:panelGroup rendered="#{!facturacionElectronicaController.nuevoInscritoFE}">
				<cm:getProperty node="${includeImgSuscribeTuBE[0]}" name="html" />
			</h:panelGroup>
			
			<h:panelGroup rendered="#{facturacionElectronicaController.nuevoInscritoFE}">
				<cm:getProperty node="${includeImgNuevoInscritoBE[0]}" name="html" />
			</h:panelGroup>			
			
			<div id="beSusPaso1" style="display:block;">
				<h:panelGroup rendered="#{!facturacionElectronicaController.nuevoInscritoFE}">
					<h2 class="sin_icono beSubTitulo margenTopTriple">El env&iacute;o de tu boleta por email se encuentra activado</h2>
				
					<p>Actualmente recibes tus boletas en esta direcci&oacute;n de correo electr&oacute;nico:</p>					
				</h:panelGroup>
				
				<h:panelGroup rendered="#{facturacionElectronicaController.nuevoInscritoFE}"> 
					<h2 class="sin_icono beSubTitulo margenTopTriple nuevoInscritoBE">Has activado exitosamente el env&iacute;o de tu boleta por email</h2>

					<p>Recuerda que desde ahora dejar&aacute;n de llegar tus boletas en papel y comenzar&aacute;s a recibirlas 
					en esta direcci&oacute;n de correo electr&oacute;nico:</p>
				</h:panelGroup>

				<div class="clearfix beEstado">
				<div class="nombreEmail generalFE">
					<h:outputText value="#{facturacionElectronicaController.facturacionElectronicaBean.correoFactura}"></h:outputText>
				</div>
			
					<div class="" style="float:right;">
						<a href="#" class="btnAzul btnAzulLargo beModificarEmail" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mi Cuenta/Boleta Electronica/Modificar');">
							<span>Modificar email</span>
						</a>
					</div>
					</div>
				
				<p class="margenTop"><a href="#" class="beEnlDes" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mi Cuenta/Boleta Electronica/Desinscribir');">Desactivar</a> el env&iacute;o de tu boleta por email </p>				
				</div>
		       				
			<div id="beSusPaso2" style="display:none;">
				<h2 class="sin_icono beSubTitulo margenTopTriple">Modificar email donde recibes tu boleta</h2> 
				<div class="instruccion_paso clearfix">
					<p><strong>Ingresa la direcci&oacute;n de email donde quieras recibir tu boleta.</strong></p>                    
						</div>
		               		
				<h:form id="form_modificar_email" styleClass="form_modificar_email paso1">
					<jsp:include page="/token.jsp" flush="true"/>
					<div id="formulario-correofe-noinscrito" class="formulario">
						<div class="clearfix beForm1">
							<div class="formulario_item mostrar_globo clearfix">
								<div class="formulario_label">Email:</div>
								<div class="formulario_input">
									<h:inputText id="email_1" styleClass="inputBox required email_01" maxlength="40" style="width:140px" value="#{facturacionElectronicaController.correoFE}" />
				</div>
			</div>

							<div class="formulario_item mostrar_globo clearfix">
								<div class="formulario_label">Confirmar email:</div>
				<div class="formulario_input">
									<input type="text" id="email_2" name="email_2" maxlength="40" class="inputBox required email_02" style="width: 140px" />
				</div>
								<div class="formulario_input" style="margin-top:-3px;">									
									<h:commandLink action="#{facturacionElectronicaController.actualizarServicioFacturacionElectronica}" styleClass="btnModificarBe btnDesactivado" onclick="return validaFormModificar();">
										<span>Aceptar</span>
									</h:commandLink>
				</div>
				</div>				
				</div>
				</div>

				<cm:getProperty node="${debesSaberModificarEmail[0]}" name="html" />
				
				</h:form>
			</div>

			<div id="beDesPaso2" style="display:none;">         	
	            <h2 class="sin_icono beSubTitulo margenTopTriple">Desactivar el env&iacute;o de tu boleta por email</h2>            
	            <p class="paddingTop">Ayúdanos a mejorar nuestros servicios cont&aacute;ndonos el motivo de esta desactivaci&oacute;n.</p>

				<h:form>
					<jsp:include page="/token.jsp" flush="true"/>
					<div class="beDesincribir">
						<h:selectOneRadio layout="pageDirection" value="#{facturacionElectronicaController.motivoDesinscripcion}">
							<f:selectItem id="item1" itemLabel="Prefiero documento en papel." itemValue="3" escape="false" />
							<f:selectItem id="item2" itemLabel="No puedo abrir el archivo recibido." itemValue="7" escape="false" />
							<f:selectItem id="item3" itemLabel="No recib&iacute; documento en mi email." itemValue="4" escape="false" />
						</h:selectOneRadio>                        
					</div>

					<div class="clearfix beInforma margenTop">
						<p>Recuerda que al recibir tu boleta en papel contribuyes a la acumulaci&oacute;n de basura y a la tala de &aacute;rboles en Chile.</p>
			</div>

					<div class="fila clearfix margenTopMedio"> <p><strong>&iquest;Confirmar que deseas desactivar el env&iacute;o de tu boleta por email?</strong><br /> 
	                    Dejar&aacute;s de recibir tu boleta por email y te llegar&aacute; &uacute;nicamente un papel.</p> </div>

	        			<div class="clearfix">
	                        <div class="fila beCajaBtDes">
	                        	<a href="#" class="btnDesactivado btDesCorreoConf"><span>Confirmar</span></a>
	                            <a href="#" class="flotaDer" id="btDesCorreoCancelar" style="margin:8px 10px 0 0;">Cancelar</a>                            
			</div>
			</div>
				</h:form>
			</div>

			<div id="beDesPaso3" style="display:none;">
	         	<h2 class="sin_icono beSubTitulo margenTopTriple">Has cancelado el env&iacute;o de tu boleta por email</h2>
	            <p class="paddingTop">Desde ahora comenzar&aacute;s a recibir tu boleta en papel en la siguiente direcci&oacute;n:</p>

	            <div class="clearfix beEstado beCajaDirect margenTopDoble">
	            	<div class="beDirecText generalFE">
	                    <h:outputText value="#{facturacionElectronicaController.usuarioBean.direccionFactura.direccionCompleta}"/>
				</div>

	                <div class="flotaDer" style="width:150px;">
	                    <a href="<r:pageUrl pageLabel='${facturacionElectronicaController.pageLabelMisDatos}'/>">
	                       <span>Modificar datos personales</span>
	                    </a>
				</div>
				</div>

			</div>

			<div id="beDesError" style="display:none;">
	         	<h2 class="sin_icono beSubTitulo margenTopTriple">Activar env&iacute;o de boleta por email</h2>

				<div class="clearfix beInforma margenTop">
					<p>Lo sentimos, se ha producido un error y no hemos podido completar tu suscripci&oacute;n. Por favor intenta m&aacute;s tarde.</p>
                </div>
	            
			</div>				
		</h:panelGroup>

	</h:panelGroup>
</f:view>