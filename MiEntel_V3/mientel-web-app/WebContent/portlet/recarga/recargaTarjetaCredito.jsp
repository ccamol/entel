<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib prefix="cm" uri="http://www.bea.com/servers/portal/tags/content" %>
<%@ taglib prefix="pref" uri="http://www.bea.com/servers/portal/tags/netuix/preferences" %>

<cm:search id="mensajeEligeTuPromo" query="idContenido = 'mensajeEligeTuPromo'" useCache="true" />
<cm:search id="basesEligeTuPromo" query="idContenido = 'basesEligeTuPromo'" useCache="true" />
<cm:search id="flagMostrarEligeTuPromo" query="idContenido = 'flagMostrarEligeTuPromo'" useCache="true" />
<cm:getProperty node="${flagMostrarEligeTuPromo[0]}" name="valorFlag" resultId="mostrarEligeTuPromo" isMultiple="false"  />

<!-- <script type="text/javascript" src="https://mi.entel.cl/personas/mientelresources/js/ampliacion.numeracion.js"></script> -->

<f:view beforePhase="#{recargaController.init}">

	<h1>Recargas</h1>

	<!-- MENSAJES -->
	<jsp:include page="../common/messages_table.jsp"></jsp:include>
	          
	<h:form id="formRecargaTarjetacredito" styleClass="formCredito">
	<jsp:include page="/token.jsp" flush="true"/>
    <div class="margen_azul_superior"></div>
    <div class="margen_azul_contenido clearfix">
    	<span class="marginador clearfix">Selecciona como quieres recargar:</span>    	
    	
		<script type="text/javascript">
			var esVozBAM = "<h:outputText value="#{profile.flagBam == miEntelBean.siglaUsuarioBAM ? 'Banda Ancha Movil' : 'Telefonia'}"/>";
			var datosEligeTuPromo = $.parseJSON('<h:outputText value="#{recargaController.JSONPromocionesRecargaTarjetaCredito}" />');			
			var mostrarEligeTuPromo = '${mostrarEligeTuPromo}';
			
			$(document).ready(function() {
				$('.select-recarga')[0].setValue('<h:outputText value="#{recargaController.tipoRecarga}"></h:outputText>');
				$('.prefijo').change(function() {
					var fecha = new Number('<h:outputText value="#{cuentaController.fechaActualFormat}"></h:outputText>');
				    $('.ampliacionNumerica').attr('maxlength',ampliacionNumerica(parseInt($('.prefijo').val(), 10),fecha));
				    $('.ampliacionNumerica').val('');
				    validarCampos();
				});

				$('.producto').change(function() {
					var producto = $(this).val();
					var prefijo = $('select[id*=prefijo]');
					var promocion = $('select[id*=promoRecarga]');					
					var mercado = '<h:outputText value="#{recargaController.mercado}" />';
					var inputTelefono = $('input[id*=numero_telefono]');
					if (producto == 'TM' || producto == 'BAM' || producto == 'BAMH') {
						if (!$.browser.msie || ($.browser.msie && $.browser.version > '6.0')) {
							if (prefijo.find("option[value='09']").length == 0) {
								prefijo.append("<option value='09'>09</option>");
							}
						}
						prefijo[0].setValue('09');
						prefijo.get(0).disable();
						if (mercado == 'PP' || mercado == 'CC') {
							inputTelefono.val('<h:outputText value="#{recargaController.numeroPcs}"></h:outputText>');
						}
						if (mostrarEligeTuPromo == 'true') { // Elige tu Promo
							if (producto == 'BAM' || producto == 'BAMH') {
								promocion[0].setValue("0");							
								$('#eligeTuPromo').hide();
								$('#basesPromo').hide();
							} else if (producto == 'TM') {
								$('#eligeTuPromo').show();
								$('#basesPromo').show();
							}
						}						
					} else if (producto == 'TH') {
						prefijo[0].setValue('02');
						if (!$.browser.msie || ($.browser.msie && $.browser.version > '6.0')) {
							prefijo.find("option[value='09']").remove();
						}
						inputTelefono.val('');
						prefijo.get(0).enable();
						if (mostrarEligeTuPromo == 'true') { // Elige tu Promo
							$('#eligeTuPromo').hide();
						}
					}
					validarCampos();
				});
				
				$('.montoRecarga').change(function() {
					if (mostrarEligeTuPromo == 'true') { // Elige tu Promo
						var montoRecargaDatosPromo = datosEligeTuPromo[$(this).val().toString()];
						var divSelectPromo = $('#selectPromo');
						var divMensajeNoPromo = $('#mensajeNoPromo');
	
						if (montoRecargaDatosPromo !== undefined) {
							var promocion = $('select[id*=promoRecarga]');
							promocion[0].setValue("0");
							
							if($('.montoRecarga') < 3500){
							promocion.html('<option selected="selected" value="0">Elige tu regalo</option>');				
							}else{
								promocion.html('<option selected="selected" value="1"></option>');
							}
			
							$.each(montoRecargaDatosPromo, function(i, p) {
								promocion.append("<option value='" + p.id + "' name='" + p.mensaje + "'>" + p.descripcion + "</option>");							
							});

							//promocion[0].setValue("0");
							divMensajeNoPromo.hide();
							//SC 2608 set hide
							//divSelectPromo.show();
							divSelectPromo.hide();

								
							
						} else {
							divSelectPromo.hide();
							//SC 2608 set hide
							//divMensajeNoPromo.show();
							divMensajeNoPromo.hide();						
						}
	
						setPromoRecarga();
					}
					validarCampos();
				});

				$('.producto').trigger("change");

				dataLayer = dataLayer||[];
  				dataLayer.push({
    				'mx_content': 'Personas/Mi Entel/Mis Productos/' + esVozBAM + '/Recargas/Web Pay/Datos',
    				'event': 'pageview'
  				});
			});

			function setPromoRecarga() {
				var promocion = $('select[id*=promoRecarga]');
				var promocionSelected = promocion.find('option:selected');
				$("input[id*=hiddenIdPromoRecarga]").val(promocion.val());				
				$("input[id*=hiddenDescPromoRecarga]").val(promocionSelected.text());

				var mensaje = '';
				if ($('.montoRecarga').val() < 3500) {
					mensaje = $('#mensajeNoPromo').find('b').html();					
				} else {
					mensaje = promocionSelected.attr('name');
				}
									
				$("input[id*=hiddenMensajePromoRecarga]").val(mensaje);
			}

			function validarCampos() {
				var monto = $('.montoRecarga').val();
				var promocion = $('.promoRecarga').val();
				var producto = $('.producto').val();
				var prefijo = $('.prefijo').val();
				var fecha = new Number('<h:outputText value="#{cuentaController.fechaActualFormat}"></h:outputText>');
				var maxlength = ampliacionNumerica(parseInt(prefijo, 10), fecha);
				var movilRecarga = $('.numero_prepago_credito').val();
				var desactivar = false;

				$('.ampliacionNumerica').attr('maxlength', maxlength);				

				if (producto == 'TM') {

					if (monto == 0) {
						$('#eligeTuPromo').hide();
					} else {
						$('#eligeTuPromo').show();
					}
										
					if (monto > 0 && movilRecarga != '' && movilRecarga.length == maxlength) {
						if ((monto >= 3500 && promocion != '0') || monto < 3500) {
							desactivar = false;
						} else {
							desactivar = true;
						}	
					} else {
						desactivar = true;
					}	
				} else {					
					if (movilRecarga == '' || movilRecarga.length < maxlength) {
						desactivar = true;
					}
				}

				if (desactivar) {
					$('.recargaCredito').attr('disabled', 'disabled');
					$('.recargaCredito').removeClass('botonContinuarNaranja');
					$('.recargaCredito').addClass('botonContinuarNaranjaDesactivado');
				} else {
					$('.recargaCredito').removeAttr('disabled');
					$('.recargaCredito').removeClass('botonContinuarNaranjaDesactivado');
					$('.recargaCredito').addClass('botonContinuarNaranja');
				}
			}
		</script>
    	
    	<h:selectOneMenu id="select-recarga" styleClass="selectBox select-recarga" enabledClass="select-recarga" style="width: 350px;" 
    		value="#{recargaController.tipoRecarga}" valueChangeListener="#{recargaController.seleccionTipoRecarga}" onchange="submit()">
    		<f:selectItems value="#{recargaController.tiposRecarga}" /> 
    	</h:selectOneMenu>
    </div>
    <div class="margen_azul_inferior"></div>
	
	
	<h2 style="padding-left: 0;">Tarjeta de Cr&eacute;dito</h2>

	<p><strong>Paso 1 de 3</strong></p>
	
	<div id="tabla_formulario" class="tabla_formulario">

	     <span class="marginador clearfix">Selecciona el monto a cargar.</span>

	     <div class="tabla_formulario_fila clearfix" style="position:relative;z-index:40;">
	     	<div class="tabla_formulario_label recortador">Selecciona producto a recargar:</div>
			<div class="tabla_formulario_dato formulario_input campo">
		        <div class="campo">
					<h:selectOneMenu id="producto" style="width:140px;" styleClass="selectBox producto monto_recarga_safari" value="#{recargaController.productoRecargar}">
	        			<f:selectItems value="#{recargaController.productosRecarga}"/>
	        		</h:selectOneMenu>
	             </div>
	        </div>
	     </div>

	     <div class="tabla_formulario_fila clearfix" style="position:relative;z-index:30;">
	        <div class="tabla_formulario_label recortador">N&uacute;mero a recargar:</div>
	        <div class="tabla_formulario_dato formulario_input campo">
	            <div class="campo" >
		            <h:selectOneMenu id="prefijo" value="#{recargaController.indicativoTelefono}"
						styleClass="selectBox prefijo codigo_tel_adicional required" style="width:50px;">
						   <f:selectItems value="#{recargaController.prefijosTelefono}"/>
				    </h:selectOneMenu>
	            </div>
	            
	            <div class="campo" style="position: relative; float: left; margin: 0 0 0 5px;" >
		            <h:inputText styleClass="inputBox input_numerico required ampliacionNumerica numero_prepago_credito" 
		            	value="#{recargaController.numeroPcs}" id="numero_telefono" style="width:85px;" maxlength="8"
		            	onkeyup="validarCampos();" onblur="validarCampos();" />
	            </div>
	        </div>
	        <div class="tabla_formulario_dato texto_ejemplo">
                Ej. 92227755    
            </div>
	        <div class="mensaje_alerta0">
	        	<table class="tabla_alerta2">
	                <tbody>
	
	                    <tr>
	                        <td class="tabla_alerta2">Ingrese un n&uacute;mero.</td>
	                    </tr>
	                </tbody>
	            </table>
	        </div>
	        <div class="mensaje_alerta1">
	        	<table class="tabla_alerta2">
	
	                <tbody>
	                    <tr>
	                        <td class="tabla_alerta2">Ingrese un n&uacute;mero v&aacute;lido.</td>
	                    </tr>
	                </tbody>
	            </table>
	        </div>
	
	    </div>
	                                    
	    <div class="tabla_formulario_fila clearfix" style="z-index:20">
	        <div class="tabla_formulario_label recortador">Monto y vigencia:</div>
	        <div class="tabla_formulario_dato formulario_input" style="width:195px">
	        
	        	<h:selectOneMenu id="montoRecarga" style="width:145px;" 
					styleClass="selectBox required montoRecarga monto_recarga_safari" value="#{recargaController.montoRecarga}">
					<f:selectItems value="#{recargaController.montosRecargaTarjetaCredito}"/>
				</h:selectOneMenu>

	        </div>
	    </div>
	    
	    <!-- ELIGE TU PROMOCION -->
	    <!-- SC 2608 eq false / retornar a true despues de contingencia -->
	    <c:if test="${mostrarEligeTuPromo eq true}">	    
		    <div id="eligeTuPromo">
		    
		    	<!-- SC REACTIV 26/08 comentado mensaje desde el property -->
	    		<!--<cm:getProperty node="${mensajeEligeTuPromo[0]}" name="html" />-->
	    		
			    <div class="tabla_formulario_fila clearfix" style="z-index:10">
			    
			   		<!-- SC REACTIV 26/08 comentado div Elige Tu promocion: -->
			        <!-- <div class="tabla_formulario_label recortador">Elige tu Promoci&oacute;n:</div>-->
			        
			        <div id="mensajeNoPromo" class="tabla_formulario_dato formulario_input" style="width: 270px; display: block">
			       
			        <!-- SC REACTIV 26/08 -->
			        	<!--<b>Este monto no tiene promoci&oacute;n asociada, te recomendamos recargar un monto m&aacute;s alto.</b>-->
			        	<b></b>
			        <!-- FIN SC -->
			        
			        </div>
		        
			        <div id="selectPromo" class="tabla_formulario_dato formulario_input" style="width: 195px; display: none">			        	
			        	<select id="promoRecarga" style="width:180px;" 
			        			class="selectBox required promoRecarga monto_recarga_safari"
			        			onchange="setPromoRecarga(); validarCampos()">

								<!-- <option selected="selected" value="1">40 Min + 50MB</option>-->
							    <option selected="selected" value="0">Elige tu regalo</option>
						</select>
						<h:inputHidden id="hiddenIdPromoRecarga" value="#{recargaController.idPromoRecarga}"/>								
						<h:inputHidden id="hiddenDescPromoRecarga" value="#{recargaController.descPromoRecarga}"/>
						<h:inputHidden id="hiddenMensajePromoRecarga" value="#{recargaController.mensajePromoRecarga}"/>		
			        </div>
			    </div>	    
		    </div>
	    </c:if>
	    <!-- /ELIGE TU PROMOCION -->
	</div>
	
	<div class="botonera_paso1">

		<h:commandButton styleClass="botonContinuarNaranjaDesactivado recargaCredito"
			 action="#{recargaController.solicitarRecargaTarjetaCredito}"></h:commandButton>

	</div>
	
	<div class="contenedor_debes_saber">
		Una vez ingresado el monto que quieres cargar, se abrir&aacute; la p&aacute;gina de pagos de transbank (webpay), 
		una vez que la transacci&oacute;n se complete volver&aacute;s al sitio de Entel.
		<br/>
		Para pagar con Cuenta Rut, en la p&aacute;gina de Transbank selecciona la opci&oacute;n Redcompra y en emisor elige Banco Estado         	
	</div>
	
	<c:if test="${mostrarEligeTuPromo eq true}">
		<cm:getProperty node="${basesEligeTuPromo[0]}" name="html" />
	</c:if>
	
	</h:form>

</f:view>
