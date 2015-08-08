<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="it" uri="http://i2b.cl/jsf/iterator/"%>
<%@ taglib prefix="entel" uri="/WEB-INF/tld/entel-tags.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="r"  uri="http://www.bea.com/servers/portal/tags/netuix/render"%>
<%@ taglib uri="http://www.bea.com/servers/portal/tags/content" prefix="cm"%>
<%@ taglib uri="http://www.bea.com/servers/portal/tags/netuix/preferences"	prefix="preferences"%>


<cm:search id="preIngresoInit" query="idContenido = 'preIngresoInit'" useCache="true"  />
<cm:search id="preIngreso_paso1prepago" query="idContenido = 'preIngreso_paso1prepago'" useCache="true"  />
<cm:search id="preIngreso_paso1ccss" query="idContenido = 'preIngreso_paso1ccss'" useCache="true"  />
<cm:search id="preIngreso_paso4" query="idContenido = 'preIngreso_paso4'" useCache="true"  />
<cm:search id="preIngreso_paso3" query="idContenido = 'preIngreso_paso3'" useCache="true"  />
<cm:search id="preIngreso_paso1CasoB" query="idContenido = 'preIngreso_paso1CasoB'" useCache="true"  />
<cm:search id="preIngreso_mensajeGrande" query="idContenido = 'preIngreso_mensajeGrande'" useCache="true" />
<preferences:getPreference name="pageEquipos" var="pageEquipos"/>
<cm:search id="regiones" query="tipo = 'region'" useCache="true"/>

<link type="text/css" rel="stylesheet" media="all" href="${pageContext.request.contextPath}/portlet/equipo/css/style.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/portlet/equipo/js/jquery.i2b.selectBox.js"></script>           
<f:view beforePhase="#{preIngresoOTController.initPreIngresoOT}">
    <script type="text/javascript">
 	var path = '<%=request.getContextPath()%>/portlet/';
	    $(document).ready(function() {

	    	ordenaRegiones();
		    
            $('#ingresoSucursalRegion').selectBox({
                img_input: path+'equipo/img/bg_input.png',
                img_input_error: path+'equipo/img/bg_input_error.png',
                img_select_arrow: path+'equipo/img/img_select_arrow.png',
                img_select_arrow_error: path+'equipo/img/img_select_arrow_error.png',
                height: '28px',
                font_size: '11px'
            });

            $('#ingresoSucursalCiudad').selectBox({
                img_input: path+'equipo/img/bg_input.png',
                img_input_error: path+'equipo/img/bg_input_error.png',
                img_select_arrow: path+'equipo/img/img_select_arrow.png',
                img_select_arrow_error: path+'equipo/img/img_select_arrow_error.png',
                height: '28px',
                font_size: '11px'
            });

            $('#ingresoSucursalRegion').live('change', function () {
                var reg = $(this).val();
                if(reg != '0'){
                    $('.msj-error:firts').remove();
                } else {
                	$(".btnAzulEActivo").removeClass("btnAzulEActivo").addClass("btnAzulE");
                }
            });
            
            $('#ingresoSucursalCiudad').live('change', function () {                
            	var comu = $(this).val();
                if(comu != '0'){
                	$("#btn-Buscar").attr("href", "#");
                    $('.msj-error:firts').remove();
                } else {
                	$(".btnAzulEActivo").removeClass("btnAzulEActivo").addClass("btnAzulE");
                }               
            });
 
		    
	    	//Ocultar el enlace ver mas.
		    if($("#totalEquipos").val()<11){$(".verTodosEquipos").hide();}else{$(".verTodosEquipos").show();}

		    $("#fancy_outer").hide();

		    //Cargar la fecha actual.
	    	$(".resumenIngreso-fecha").html($.datepicker.formatDate('dd-mm-y', new Date()));

	    	$('.btnVolver').click(function(e) {
	    		ambiente = $("#preIngresoServicioTecnicoMercado").val(); //1=SS/CC; 2=PREPAGO	    				    	
	    		var pasoAmbiente;
	    	    var pasoactual = $(this).attr('id').toString();	    	    
	    		pasoactual = pasoactual.substring(5);
	    		var pasoanterior = pasoactual-1;
	    		$('.st_paso'+pasoactual).hide();
	    		$(".globoError").hide();
	    		if (pasoactual==2){
		    		//PASO2-PASO1
	    			$("input[name=opcion-falla]:checked").attr('checked', false);
	    			$('.btnPreingreso_2').removeClass('btnAzul').addClass('btnDesactivado');	    			
	    			//PASO2-PASO1	    			
	    			$("input[name=equipo-ingreso]").removeAttr('checked');
					$('.btnPreingreso_1').removeClass('btnAzul').addClass('btnDesactivado');
					$('.imeiEquipoIngreso').val("");
					$('.cajaInrgesoImei').show();
					
	    			if(ambiente==1){
	    				 pasoAmbiente = "paso_ccss";
	    			}else if (ambiente==2){
	    				pasoAmbiente = "paso_prepago";
	    			}	    			
	    			$('.st_paso'+pasoanterior+'.'+pasoAmbiente).show();	    			
	    		}else{
					if(pasoactual==1){
						$('.imeiEquipoIngreso').val("");						
	    				$("input[name=equipo-ingreso]").removeAttr('checked');
	    				$('.btnPreingreso_1').removeClass('btnAzul').addClass('btnDesactivado');
	    				$('.cajaInrgesoImei').show();
					}
	    			$('.st_paso'+pasoanterior).show();
	    		}	    		
	    	});
		    
	    	$('#imprimirComprobantePreIngreso').click(function() {
	    		$("#imprimir_SucursalesIngreso").val($("#resultadoSucursalesIngreso").html());	    		
	    		if(($("#ingresoSucursalCiudad").find("option:selected").val() != "0") && ($("#ingresoSucursalCiudad").find("option:selected").val())!=""){
	    			$("#imprimir_SucursalRegion").val($('.ingresoSucursalRegion').find('option:selected').text());    			    		
	    			$("#imprimir_SucursalCiudad").val($("#ingresoSucursalCiudad").find("option:selected").text());	    			    			    	
	    		}
	    		$('#myform').submit();
	    	});

	    	$(".verTodosEquipos").click(function() {
				if($('.sonMasDeDiez').hasClass('oculto')){
					$(this).html('Ver menos equipos (-)');
		    		$(".sonMasDeDiez").removeClass('oculto');		    		
		    	}else{
		    		$(this).html('Ver todos los equipos (+)');
		    		$(".sonMasDeDiez").addClass('oculto');		    		
		    	}    			  		    		
	    	});

					        	
	        $('#myform').submit(function() {
	        	Xpos=(screen.width/2)- 400;
	        	Ypos=(screen.height/2)- 300;	        	
	            window.open('', 'formpopup', 'width=650,height=700,menubar=yes,resizeable=yes,scrollbars=yes,toolbar=no,left = '+Xpos+',top = '+Ypos);
	            this.target = 'formpopup';
	        });

	        $('#btn-Buscar').live('click', function () {
				if($("#btn-Buscar").attr("class") == 'btnAzulEActivo') {
					$('.Result_tabla > table > tbody').empty();
	                $('.Result_tabla > table > tbody').append('<tr class="enca"><th style="width:121px;">Tienda</th><th style="width:119px;">Direcci&oacute;n</th><th style="width:111px">Lunes a Viernes</th><th style="width:74px">S&aacute;bado</th><th style="width:128px">Domingos y feriados</th></tr>');
	                var reg = $('#ingresoSucursalRegion').val();
	                var regId = $('#ingresoSucursalRegion').attr('id');
	                var com = $('#ingresoSucursalCiudad').val();
	                var comId = $('#ingresoSucursalCiudad').attr('id');
		
	                if (reg > 0) {
	                    if (com != '0') {
	                        $('.Result_tabla').show();
	                        //GeneraTabla(reg, com);
	                    } else {
	                    	$('#ingresoSucursalCiudad').get(0).enableError();
	                        $('.Result_tabla').hide();
	                        error_buscar(comId);   
	                    }
	                } else {
	                    $('#ingresoSucursalRegion').get(0).enableError();
	                    //$('.Result_tabla').hide();
	                    error_buscar(regId);
	                }
				}
            });
	        
	    });

        function ordenaRegiones(){
			var combo = $("#ingresoSucursalRegion");
			if (combo.length > 0) {
				var selectedVal = combo.val();
				var $options = $('option', combo);
				var arrVals = [];
				$options.each(function(){
					arrVals.push({
						val: $(this).val(),
						text: $(this).text()
					});
				});
				arrVals.sort(function(a, b){
					return a.val - b.val;
				});
				for (var i = 0, l = arrVals.length; i < l; i++) {
					$($options[i]).val(arrVals[i].val).text(arrVals[i].text);
				}
				combo.val(selectedVal);
			}
        }
		
	    function error_buscar(el){
		    
            var array_msj = {
                region : 'Debes seleccionar Region',
                comuna : 'Debes seleccionar una ciudad o comuna'
            }
            
            if(el == 'ingresoSucursalRegion'){
                $('#'+el+'').closest('.input').append('<div class="msj-error"><p>'+array_msj.region+'</p></div>');
            }else{
                $('#'+el+'').closest('.input').append('<div class="msj-error"><p>'+array_msj.comuna+'</p></div>');
            }
            
        }
	    

        $(document).ready(function(){
       		$('.emailIngreso').val('<h:outputText value="#{preIngresoOTController.emailUsuario}"/>');            
        });

        function cargarIMEI(valor){        	
        	//$('.cajaInrgesoImei .debe').hide();
        	$('.cajaInrgesoImei .cargando').show();
        	if(valor != "") {
				// Llamamos a pagina de donde ejecuto las consultas para llenar la informacion del equipo
				var url = '<%=request.getContextPath()%>/portlet/equipo/preIngresoOTJson.faces';

				$.ajax({
			    	type: 'POST',
			        url: url,
			        dataType: 'json',
			        data : 'accion=loadImei&imei='+valor,
			        success: function (resp){			            
						$('.cajaInrgesoImei .cargando').hide();
			        	//$('.cajaInrgesoImei .debe').show();			            
						if(resp.estado=="Ok"){
							$('.equipoSeleccionado .fila .marca').html(resp.respuesta.marca);
							$('.equipoSeleccionado .fila .imei').html(resp.respuesta.imei);
							$('.equipoSeleccionado .fila .fecha').html(resp.respuesta.fechaNegocio);							
							$('.btnPreingreso_1').removeClass('btnDesactivado').addClass('btnAzul');														
						}else{
							$('.cajaImei .globoError .body').html(resp.detalle);
							$('.cajaImei .globoError').show();
						}
						$('.imeiEquipoIngreso').attr("readonly",false);
					}
				});
			}
        }  

        function registrarPreIngresoOT(){                
			var url = "<%=request.getContextPath()%>/portlet/equipo/preIngresoOTJson.faces";
			movil = $("#msisdn").val();
    		imei = $(".equipoSeleccionado .fila .imei").html();
    		telContacto = $("#fonoContacto").val();
    		mailContacto = $("input[name=email-ingreso]").val();
    		comentario = $("textarea[name=detalles-falla]").val();
    		js_sintomas = codigoFallas();
    		nombreSintomas = $(".resumenIngreso-fallas").html();
			marca = $(".equipoSeleccionado .fila .marca").html();
			js_accesorios = $(".accesoriosIngreso").html();
			$(".cargandoConfirmacionPreOT").show();				
			$(".btnPreingreso_3").removeClass('btnAzul').addClass('btnDesactivado');
			$.ajax({
		    	type: 'POST',
		        url: url,
		        dataType: 'json',
		        data : 'accion=loadPreIngresoOT&nombreSintomas='+nombreSintomas+'&accesorios='+js_accesorios+'&marca='+marca+'&movil='+movil+'&imei='+imei+'&fonoContacto='+telContacto+'&mailContacto='+mailContacto+'&comentario='+comentario+'&sintomas=' + js_sintomas,
		        success: function (resp){
					$(".cargandoConfirmacionPreOT").hide();
					if(resp.estado=="Ok"){
						$('#resumenIngreso-ot').html(resp.respuesta.nmroOrden);
						$("#imprimir_nroOrden").val($('#resumenIngreso-ot').html());
						$("#imprimir_fecha").val($('.resumenIngreso-fecha').html());
						$('.st_paso3').hide();
						$('.st_paso4').show();
					}else{
						$(".btnPreingreso_3").removeClass('btnDesactivado').addClass('btnAzul');
					    $('.franja-botones .globoError .body').html(resp.detalle);
						$('.franja-botones .globoError').show();							
					}
				}
			});
    }
    
		function loadSucursal(){

			if($("#btn-Buscar").attr("href") ==  "#") {
				// Tomo el valor de la opción seleccionada 
				region = $("select[id*=ingresoSucursalRegion]").val(); 
				comuna = $("select[id*=ingresoSucursalCiudad]").val();
				if(region != '0'){
					 $('.msj-error').remove();
				 }
				
				$("#resultadoSucursalesIngreso").html(''); 
				//vacio los combos de ciudad
				$(".cargandoSucursales").show();
				if(comuna != ""){ 
					var url = '<%=request.getContextPath()%>/portlet/equipo/tiendasCercanas.jsp';
	
					$.ajax( {
						type : 'GET',
						url : url,
						data : 'accion=loadSucursal&idRegion=' + region + '&idCiudad='+comuna,
						success : function(resp) {
							$(".cargandoSucursales").hide();
							$("#resultadoSucursalesIngreso").html(resp);
						}
					});
				}
			}
			
			
		}

		function ocultarMensaje(){
			$(".btnAzulE").removeClass("btnAzulE").addClass("btnAzulEActivo");
			 var comu = $("select[id*=ingresoSucursalCiudad]").val();
			 if(comu != '0'){
				 $('.msj-error').remove();
			 }
		 	$("#resultadoSucursalesIngreso").html('');
		}

		function cargarComunas(){ 
			$("select[id*=ingresoSucursalCiudad]").html('<option value="0" selected="selected">Seleccione</option>');
		    $("select[id*=ingresoSucursalCiudad]")[0].setValue('0');
		    $("#resultadoSucursalesIngreso").html('');
			region = $("select[id*=ingresoSucursalRegion]").val();
			$("select[id*=ingresoSucursalRegion]").addClass("optionRegion");		
			//vacio los combos de ciudades
			if(region == '0'){ 
				  $("select[id*=ingresoSucursalCiudad]").html('<option value="0" selected="selected">Seleccione</option>');
			      $("select[id*=ingresoSucursalCiudad]")[0].setValue('0');
			      $("#resultadoSucursalesIngreso").html('');
			} else {				
				/* En caso contrario agregado el letreo de cargando a el combo siguiente 
				 Ejemplo: Si seleccione país voy a tener que el siguiente según mi vector combos es: estado por qué combos [país] = estado
				*/ 
		
				//if(region != '0'){
					 $('.msj-error').remove();
				// }
				 
				//if(region != '0'){ 
				var url = '<%=request.getContextPath()%>/portlet/equipo/comunas.jsp';
	
				$.ajax( {
					type : 'GET',
					url : url,
					data : 'accion=loadSucursal&idRegion=' + region,
					success : function(resp) {
						var inicio=resp.indexOf("<data>")+6;
						var fin=resp.indexOf("</data>");
						var xml=resp.substr(inicio,fin-inicio);							
						var values=xml.split('?');
						var optionsselect = "";
						optionsselect = '<option value="0">Seleccione</option>';
						if(values.length >1){
							//$("select[id*=ingresoSucursalCiudad]").html('<option value="">Seleccione</option>');
							//$("select[id*=ingresoSucursalCiudad]")[0].setValue('');
							for(var i=0; i<values.length-1;i++){
								//$("select[id*=ingresoSucursalCiudad]").html('<option value="">Seleccione</option>');
								$("select[id*=ingresoSucursalCiudad]")[0].setValue('0');
							    $('#ingresoSucursalCiudad').append('<option value="'+values[i]+'">'+values[i]+'</option>');
							}	
						}else{
							//$('#ingresoSucursalCiudad').append('<option value="" selected="selected">Seleccione</option>');
							$("select[id*=ingresoSucursalCiudad]").html(optionsselect); //Tomo el resultado de pagina e inserto los datos en el combo indicado
							$("select[id*=ingresoSucursalCiudad]")[0].setValue('0');
						}					
					}
				});
			//}
		}
	}
</script>	
	<input id="preIngresoServicioTecnicoMercado" type="hidden" value="<h:outputText value="#{preIngresoOTController.mercadoPreIngresoOT}"/>"/>		
	<input id="flagObtenerEquipoSoporte" type="hidden" value="<h:outputText value="#{preIngresoOTController.flagEquipoSoporte}"/>"/>
	<input id="fonoContacto" type="hidden" value="<h:outputText value="#{preIngresoOTController.fonoContacto}"/>"/>
	<input id="msisdn" type="hidden" value="<h:outputText value="#{preIngresoOTController.msisdn}"/>"/>
	<input id="totalEquipos" type="hidden" value="<h:outputText value="#{preIngresoOTController.obtenerEquiposSoporteBean.totalEquipos}"/>"/>
	<div class="clearfix">
		<div class="contenido_tab contenido2"><br />
		<br />
		<!-- Servicio tecnico 0 -->
		<div class="servicioTecnico st_paso0" style="display: block;">
			<h1>Preingreso Servicio T&eacute;cnico</h1>
						
			<cm:getProperty node="${preIngresoInit[0]}" name="html" />
		
			<div class="clearfix franja-botones">				
			    <a href="<r:pageUrl pageLabel='${pageEquipos}'></r:pageUrl>" class="btnVolver">< Volver</a> 
				<div class="botonesDerecha">
				  <a class="btnAzul btnPreingreso_0 btnDerecha" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Equipo/Servicio tecnico/Preingreso/Equipo');"><span>Preingresar equipo</span></a>
				</div>
			</div>
		</div>
		<!-- /Servicio tecnico 0 --> 
	
		<!-- Servicio tecnico 1 PREPAGO-->
		<div class="servicioTecnico st_paso1 paso_prepago">
			<h1>Preingreso Servicio T&eacute;cnico</h1>
			
			<div class="pasos clearfix">
				<div class="paso1_preIngresoactivo"></div>
				<div class="paso2_preIngresoinactivo"></div>
				<div class="paso3_preIngresoinactivo"></div>
				<div class="paso4_preIngresoinactivo"></div>
			</div>
		
			<!-- parrafos -->
				<cm:getProperty node="${preIngreso_paso1prepago[0]}" name="html" />
			<!-- /parrafos -->
			 
			<br />
	
			<div class="cajaInrgesoImei">
			<div class="cajaImei">	
				<div>			
					<div class="texto">
						<p>Intenta ingresando su Nr. IMEI</p>
						<a class="ico_interrogacionNuevo imeiEquipoIngresoToolTip autoTooltip" href="#ayudaImeiIngreso"></a>
					</div>
					<div class="input">
						<input type="text" maxlength="15" class="inputBox imeiEquipoIngreso inputNumerico" name="imei-equipo" style="width: 200px;" />
					</div>
					<div class="debe">Debe contener 15 caracteres num&eacute;ricos</div>
					
					<div id="globoError" class="globoError mensaje" style="top: 5px; left: 380px;">
					<table>
						<tbody>
							<tr>
								<td class="body"></td>
							</tr>
						</tbody>
					</table>
					</div>
				</div>								
				<div class="cargando" style="display: none;"></div>				
			</div>
			
				<div class="infoIngreso cajaImeiInfo clearfix">Si tienes
				dificultades para ingresar el Nr. IMEI deber&aacute;s dirigirte
				directamente a una de nuestras tiendas.</div>
			</div>
	
			<div class="clearfix franja-botones">
				<a href="#" class="btnVolver" id="paso-1">< Volver</a>
				<div class="botonesDerecha"><a href="#"
					class="btnCancelar btnCancelar_1">Cancelar</a> <a
					class="btnDesactivado btnPreingreso_1 btnDerecha" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Equipo/Servicio tecnico/Preingreso/Fallas');"><span>Continuar</span></a>
				</div>
			</div>
		</div>
		<!-- /Servicio tecnico 1 PREPAGO--> 
	
		<!-- Servicio tecnico 1 SS/CC-->
		<div class="servicioTecnico st_paso1 paso_ccss">
		<h1>Preingreso Servicio T&eacute;cnico</h1>
		
		<div class="pasos clearfix">
		<div class="paso1_preIngresoactivo"></div>
		<div class="paso2_preIngresoinactivo"></div>
		<div class="paso3_preIngresoinactivo"></div>
		<div class="paso4_preIngresoinactivo"></div>
		</div>
	
		<div class="paso1CasoA">
		
			<!-- parrafos -->
				<cm:getProperty node="${preIngreso_paso1ccss[0]}" name="html" />
			<!-- /parrafos -->
		
			<div class="header_tabla equiposSeleccionar clearfix">
				<div class="top"><span></span></div>
				<div class="main">
				<table>
					<tbody>
						<tr>
							<th width="260px">Marca / Modelo</th>
							<th width="100px"><span style="float: left;margin-left:20px;">Nr. IMEI</span>
							<a class="ico_interrogacionNuevo imeiEquipoIngresoToolTip autoTooltip" href="#ayudaImeiIngreso"></a></th>
							<th width="120px">Fecha de negocio</th>
							<th>&nbsp;</th>
						</tr>
					</tbody>
				</table>
				</div>
				<div class="bottom"><span></span></div>
			</div>
			<div class="equiposSeleccionar equipos">
				<!-- equipo -->
				<it:iterator value="#{preIngresoOTController.obtenerEquiposSoporteBean.listaEquiposSoporte}" rowIndexVar="index" var="EquiposSoporte">		
					<h:panelGroup rendered="#{(index+1) == 11}">				
					 	<div class="sonMasDeDiez oculto">
				 	</h:panelGroup>
					
						<div class="fila <h:outputText value="#{index % 2 == 0 ? 'impar': 'par'}"/> clearfix">						    
							<div class="marca"><h:outputText value="#{EquiposSoporte.marca}" /></div>					
							<div class="imei"><h:outputText value="#{EquiposSoporte.imei}" /></div>
							<div class="fecha"><h:outputText value="#{EquiposSoporte.fechaNegocio}" /></div>
							<div class="radio"><input type="radio" name="equipo-ingreso" class="equipo-ingreso" id="equipo_<h:outputText value="#{index+1}"/>" rel="<h:outputText value="#{EquiposSoporte.mesAntiguedad}"/>" /><!-- REL = ANTIGUEDAD EN MESES --></div>
						</div>
					
					<h:panelGroup rendered="#{((index+1) == preIngresoOTController.obtenerEquiposSoporteBean.totalEquipos) && ((index+1) >= 11)}">
					 	</div>
					</h:panelGroup>
				</it:iterator>
				<div class="clearfix"><a href="#" class="verTodosEquipos">Ver todos los equipos (+)</a></div>
		</div>
		</div>
	
		<div class="paso1CasoB">
		
		<!-- parrafos -->
			<cm:getProperty node="${preIngreso_paso1CasoB[0]}" name="html" />		 
		<!-- /parrafos -->
		
		</div>
	
		<br />
	
		<div class="cajaInrgesoImei">
			<p><strong>&iquest;No encuentras tu equipo?</strong></p>
			<div class="cajaImei">
				<div>
					<div class="texto">
					<p>Intenta ingresando su Nr. IMEI</p>
					<a class="ico_interrogacionNuevo imeiEquipoIngresoToolTip autoTooltip"
						href="#ayudaImeiIngreso"></a></div>
					<div class="input">
					    <input type="text" maxlength="15" class="inputBox imeiEquipoIngreso inputNumerico" name="imei-equipo" style="width: 200px;" />
					</div>
					<div class="debe">Debe contener 15 caracteres num&eacute;ricos</div>
					
					<div id="globoError" class="globoError mensaje" style="top: 5px; left: 380px;">
					<table>
						<tbody>
							<tr>
								<td class="body"></td>
							</tr>
						</tbody>
					</table>
					</div>
				</div>
				<div class="cargando" style="display: none;"></div>
			</div>	
			<div class="infoIngreso cajaImeiInfo clearfix">Si tienes
			dificultades para ingresar el Nr. IMEI deber&aacute;s dirigirte
			directamente a una de nuestras tiendas.</div>
		</div>
	
		<div class="clearfix franja-botones">
		     <a href="#" class="btnVolver" id="paso-1">< Volver</a>
		<div class="botonesDerecha">
		     <a href="#" class="btnCancelar btnCancelar_1">Cancelar</a> 
		     <a class="btnDesactivado btnPreingreso_1 btnDerecha" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Equipo/Servicio tecnico/Preingreso/Fallas');"><span>Continuar</span></a>
		</div>
		</div>
		</div>
		<!-- /Servicio tecnico 1 --> 
	
		<!-- Servicio tecnico 2 FALLAS-->
		<div class="servicioTecnico st_paso2">
		<h1>Preingreso Servicio T&eacute;cnico</h1>
		
		<div class="pasos clearfix">
		<div class="paso1_preIngresoinactivo"></div>
		<div class="paso2_preIngresoactivo"></div>
		<div class="paso3_preIngresoinactivo"></div>
		<div class="paso4_preIngresoinactivo"></div>
		</div>
	
		<br />
		<div class="header_tabla equiposSeleccionar clearfix">
		<div class="top"><span></span></div>
		<div class="main">
		<table>
			<tbody>
				<tr>
					<th width="260px">Marca / Modelo</th>
					<th width="100px"><span style="float: left;margin-left:20px;">Nr. IMEI</span><a
						class="ico_interrogacionNuevo imeiEquipoIngresoToolTip autoTooltip"
						href="#ayudaImeiIngreso"></a></th>
					<th width="120px">Fecha de negocio</th>
					<th>&nbsp;</th>
				</tr>
			</tbody>
		</table>
		</div>
		<div class="bottom"><span></span></div>
		</div>
		<div class="equipos equipoSeleccionado">
			<!-- equipo -->
			<div class="fila impar clearfix unica">
				<div class="marca">-</div>
				<div class="imei">-</div>
				<div class="fecha">-</div>
				<div class="radio">&nbsp;</div>
			</div>
			<!-- /equipo -->
		</div>
	
		<!-- parrafos -->
		<div class="contenido_parrafos">
		<p><strong>Selecciona al menos una falla:</strong></p>
		</div>
		<!-- /parrafos -->
		<div class="opcionesFallas clearfix">
		    <!-- fallas -->
		    
		    <it:iterator value="#{preIngresoOTController.consultaSintomaBean.listaSintoma}" rowIndexVar="index" var="ListaSintoma">
				<div class="opcion">
					<p>
						<input value="<h:outputText value="#{ListaSintoma.codigo},#{ListaSintoma.sintoma},#{ListaSintoma.accesorio}" />" type="checkbox" name="opcion-falla" class="opcion-falla" id="falla-<h:outputText value="#{index+1}"/>" />
						<label><h:outputText value="#{ListaSintoma.sintoma}" /></label>
					</p>
				</div>
			</it:iterator>
			<div class="opcion"><p><input type="checkbox" name="opcion-falla" class="opcion-falla" id="falla-otras" value="6,Otros sintomas no clasificados," /><label><span>Otras</span><a class="ico_interrogacionNuevo autoTooltip" href="#descripcionFalla"></a></label></p></div>	
			<!-- /fallas -->
		</div>
	
		<div class="detallesComentarios">
			<p>Detalles / Comentarios</p>
			<textarea name="detalles-falla" class="detalllesComentariosTexto" onKeyPress="return ( this.value.length < 200 );"></textarea>
			<div class="caracteresDisponibles"><span>200</span> caracteres disponibles</div>
			<div id="globoError" class="globoError mensaje" style="top: 35px; left: 380px;">
				<table>
					<tbody>
						<tr>
							<td class="body">Debe ingresar alg&uacute;n detalle/comentario</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	
		<div class="cajaEmail">
			<div class="texto" style="padding-left: 80px;">
				<p><strong>Ingresa un mail de contacto: *</strong></p>
			</div>
			<div class="input">
				<input name="email-ingreso" type="text" value="<h:outputText value="#{preIngresoOTController.emailUsuario}"/>" class="inputBox emailIngreso" style="width: 200px;" />
			</div>
			<div id="globoError" class="globoError mensaje" style="top: 5px; left: 380px;">
			<table>
				<tbody>
					<tr>
						<td class="body">Ingrese correctamente el email</td>
					</tr>
				</tbody>
			</table>
			</div>
		</div>
	
		<div class="clearfix franja-botones">
			<a href="#" class="btnVolver" id="paso-2">< Volver</a>
		<div class="botonesDerecha">
		    <a href="#" class="btnCancelar btnCancelar_1">Cancelar</a> 
		    <a class="btnDesactivado btnPreingreso_2 btnDerecha" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Equipo/Servicio tecnico/Preingreso/Confirmar');"><span>Continuar</span></a>
		<div id="globoError" class="globoError mensaje" style="top: -10px; left: 493px;">
		
		<table>
			<tbody>
				<tr>
					<td class="body">XXX</td>
				</tr>
			</tbody>
		</table>
		</div>
		</div>
		</div>
		</div>
		<!-- /Servicio tecnico 2 --> 
	
		<!-- Servicio tecnico 3-->
		<div class="servicioTecnico st_paso3">
		<h1>Preingreso Servicio T&eacute;cnico</h1>
		
		<div class="pasos clearfix">
		<div class="paso1_preIngresoinactivo"></div>
		<div class="paso2_preIngresoinactivo"></div>
		<div class="paso3_preIngresoactivo"></div>
		<div class="paso4_preIngresoinactivo"></div>
		</div>
	
		<br />
	
		<div class="tituloComprobante clearfix">
		<h4>Confirmaci&oacute;n</h4>
		</div>
	
		<!-- parrafos -->
			<cm:getProperty node="${preIngreso_paso3[0]}" name="html" />
		<!-- /parrafos -->
	
		<div class="header_tabla equiposSeleccionar clearfix">
		<div class="top"><span></span></div>
		<div class="main">
		<table>
			<tbody>
				<tr>
					<th width="260px">Fecha de preingreso:</th>
					<th width="120px" class="resumenIngreso-ot"><div class="resumenIngreso-fecha">-</div></th>
					<th>&nbsp;</th>
				</tr>
			</tbody>
		</table>
		</div>
		<div class="bottom"><span></span></div>
		</div>
		<div class="equipos resumen">
		<!--  <div class="fila par clearfix">
		<div class="marca">Fecha de preingreso:</div>
		<div class="imei resumenIngreso-fecha">-</div>
		<div class="radio">&nbsp;</div>
		</div>-->
		<div class="fila impar clearfix">
		<div class="marca">Marca/Modelo</div>
		<div class="marca resumenIngreso-marca">-</div>
		<div class="radio">&nbsp;</div>
		</div>
		<div class="fila par clearfix">
		<div class="marca">Nr. IMEI:</div>
		<div class="imei resumenIngreso-imei">-</div>
		<div class="radio">&nbsp;</div>
		</div>
		<div class="fila impar clearfix">
		<div class="marca">Fallas:</div>
		<div class="marca resumenIngreso-fallas">-</div>
		<div class="radio">&nbsp;</div>
		</div>
		<div class="fila par unica clearfix">
		<div class="marca">Observaciones/Comentarios</div>
		<div class="marca resumenIngreso-comentarios">-</div>
		<div class="radio">&nbsp;</div>
		</div>
		</div>
	
		<div class="cajaEmail">
			<div class="infoIngreso clearfix">
			<p><strong>Para una mejor atenci&oacute;n no olvides llevar los siguientes accesorios:</strong></p>
			</div>
			<div class="accesoriosIngreso">Accesorio 1; Accesorio 2</div>
		</div>
	
		<div class="clearfix franja-botones">
		    <a href="#" class="btnVolver" id="paso-3">< Volver</a>
			<div class="botonesDerecha">
	    		<a href="#" class="btnCancelar btnCancelar_1">Cancelar</a> 
			    <a class="btnAzul btnActivado btnPreingreso_3 btnDerecha" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Equipo/Servicio tecnico/Preingreso/Comprobante');"><span>Confirmar</span></a>
				<div id="globoError" class="globoError mensaje" style="top: -10px; left: 493px;">			
					<table>
						<tbody>
							<tr>
								<td class="body">XXX</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
				
		<div class="cargandoConfirmacionPreOT"></div>
	
		</div>
		<!-- /Servicio tecnico 3 --> 
	
		<!-- Servicio tecnico 4 -->
		<div class="servicioTecnico st_paso4">
		<h1>Preingreso Servicio T&eacute;cnico</h1>
		
		<div class="pasos clearfix">
		<div class="paso1_preIngresoinactivo"></div>
		<div class="paso2_preIngresoinactivo"></div>
		<div class="paso3_preIngresoinactivo"></div>
		<div class="paso4_preIngresoactivo"></div>
		</div>
	
		<br />
	
		<div class="tituloComprobante clearfix">
		<h4>Comprobante</h4>
		<a href="#" class="imprimirComprobanteIngreso" id="imprimirComprobantePreIngreso">Imprimir</a>
		</div>
	
		<!-- parrafos -->
			<cm:getProperty node="${preIngreso_paso4[0]}" name="html" />
		<!-- /parrafos -->
	
		<div class="header_tabla equiposSeleccionar clearfix">
		<div class="top"><span></span></div>
		<div class="main">
		<table>
			<tbody>
				<tr>
					<th width="260px">No Orden de trabajo (OT):</th>
					<th width="120px" class="resumenIngreso-ot"><div id="resumenIngreso-ot"></div></th>
					<th>&nbsp;</th>
				</tr>
			</tbody>
		</table>
		</div>
		<div class="bottom"><span></span></div>
		</div>
		<div class="equipos resumen">
		<div class="fila impar clearfix">
		<div class="marca">Fecha de preingreso:</div>
		<div class="imei resumenIngreso-fecha">-</div>
		<div class="radio">&nbsp;</div>
		</div>
		<div class="fila par clearfix">
		<div class="marca">Marca/Modelo</div>
		<div class="marca resumenIngreso-marca">-</div>
		<div class="radio">&nbsp;</div>
		</div>
		<div class="fila impar clearfix">
		<div class="marca">Nr. IMEI:</div>
		<div class="imei resumenIngreso-imei">-</div>
		<div class="radio">&nbsp;</div>
		</div>
		<div class="fila par clearfix">
		<div class="marca">Fallas:</div>
		<div class="marca resumenIngreso-fallas">-</div>
		<div class="radio">&nbsp;</div>
		</div>
		<div class="fila impar unica clearfix">
		<div class="marca">Observaciones/Comentarios</div>
		<div class="marca resumenIngreso-comentarios">-</div>
		<div class="radio">&nbsp;</div>
		</div>
		</div>
	
		<div class="cajaEmail">
		<div class="infoIngreso clearfix">
		<p><strong>Para una mejor atenci&oacute;n no olvides llevar los siguientes accesorios:</strong></p>
		</div>
		<div class="accesoriosIngreso">Accesorio 1; Accesorio 2</div>
		</div>
	
		<p>Un correo electr&oacute;nico ha sido enviado a la
		direcci&oacute;n ingresada para recordarle las instrucciones.</p>
	
		<!-- caja sucursales -->
		<div class="caja-enc-tienda">
        <h2 class="encuentre-ti"><span>(&nbsp;</span>Encuentra tu tienda m&aacute;s cercana</h2>
			      <div class="box-select">
			            <div class="box-region clearfix">
			                <div class="ti_region">
			                    <p>Regi&oacute;n</p>
			                </div>
			
							<% 
							int cont = 0;
							int cant = 0;
							int cantCom = 0;
							cant = regiones.length-1;
							System.out.println("cant comunas : "+cantCom);
							%>
							<div class="sel_region clearfix input">
							
							<select id="ingresoSucursalRegion" style="width:198px; display:none;" onchange="cargarComunas();">
							<option value="0">Seleccione</option>							
							<c:forEach var="i" begin="0" end="<%=cant%>">
								<option value=<cm:getProperty node="${regiones[i]}" name="idRegion" />><cm:getProperty node="${regiones[i]}" name="nombreRegion" /></option>
							</c:forEach>						
							</select>	
							</div>
						</div>
				        <div class="box-comuna clearfix">
			                <div class="ti_comuna clearfix">
			                    <p>Ciudad/Comuna</p>
			                </div>
			                <div id="resultadoComunas" class="sel_comuna clearfix input">					
								<select id="ingresoSucursalCiudad" style="width:198px" onchange="ocultarMensaje();">
									<option value="0">Seleccione</option>
								</select>
						</div>
						</div>		
						<div class="box-btn-buscar clearfix" >
			                <a id="btn-Buscar" onclick="loadSucursal();" class="btnAzulE">
			                    <span>[ Buscar_</span>
			                </a>
			            </div>		
					</div>
				<div class="cargandoSucursales"></div>
		
		       <div  class="Result_tabla" style="display:none;" id="resultadoSucursalesIngreso">
		</div>	
		</div>
		
		<!-- /caja sucursales --></div>
		<!-- /Servicio tecnico 4 -->
	
	</div>
	</div>

	<!-- CONTENIDO TOOLTIPS -->
	<div id="yaIngresado" style="display: none">
	<p>Este equipo ya se encuentra ingresado en nuestro servicio
	t&eacute;cnico.</p>
	</div>
	<div id="noReparable" style="display: none">
	<p>Este equipo se ha diagnosticado previamente con da&ntilde;o no
	reparable.</p>
	</div>
	<div id="ayudaImeiIngreso" style="display: none">
	<div class="ayudaImeiIngresotexto">
	<p>Obt&eacute;n el n&uacute;mero de IMEI marcando *#06# desde tu
	celular o del interior del equipo como muestra la imagen.</p>
	</div>
	<div class="ayudaImeiIngresoimagenImei"></div>
	</div>
	<div id="descripcionFalla" style="display: none">
	<p>Si marcas esta opci&oacute;n deber&aacute;s especificar
	obligatoriamente el problema en Detalles/Comentarios.</p>
	</div>
	<!-- /CONTENIDO TOOLTIPS -->


	<!-- lightboxes ingreso -->
	<div id="TB_overlayIngreso" class="TB_overlayBGIngreso"
		style="opacity: 0.75; display: none;"></div>

	<div id="cancelarProcesoIngreso" class="lightBoxIngreso"
		style="width: 378px;">
	<h3><strong>Cancelar - Confirmaci&oacute;n</strong></h3>
	<div class="mensaje clearfix">
	<p>Se cancelar&aacute; el pre ingreso de servicio t&eacute;cnico y
	ser&aacute;s dirigido a la p&aacute;gina principal de Mi Entel.</p>
	</div>
	<div class="botones">
	   <a href="#" class="btnContinuarPreIngreso enlaceSencillo">Continuar preingreso</a> 
	   <a class="btnAzul btnCancelarProceso" rel="<r:pageUrl pageLabel='${pageEquipos}'></r:pageUrl>"><span>Cancelar</span></a>
	</div>
	</div>
	
	<div class="cargando"></div>
	<div id="antiguedadMayor14" class="lightBoxIngreso">
	<h3><strong>Importante</strong></h3>
	<br />
	<div class="mensajeGrande">
	
	    
	    <!-- parrafos -->
			<cm:getProperty node="${preIngreso_mensajeGrande[0]}" name="html" />
		<!-- /parrafos -->
		
		<!-- <p>El equipo que has seleccionado tiene una antig&uuml;edad mayor a
		<span class="numeroMeses">XX</span> meses por lo que te recomendamos
		ingresar a la secci&oacute;n Renovar/Comprar donde podr&aacute;s <strong><span
			class="accionAntiguedad">xxxxx</span> desde costo $0.</strong></p>
			
		<p>Si accedes a <span class="accionAntiguedad">xxxxx</span> de
		equipo podr&aacute;s preingresar este equipo a servicio t&eacute;nico
		en cualquier otra oportunidad.</p> -->
		
		<br />
		<p>&iquest;Qu&eacute; deseas hacer?</p>
		<div class="opciones clearfix">
			<p>
			    <input type="radio" name="accion-antiguedad" class="accion-antiguedad" id="accion_1" />
				<label>Continuar con el preingreso de Servicio T&eacute;cnico</label>
			</p>
			<p>
			    <input type="radio" name="accion-antiguedad" class="accion-antiguedad" id="accion_2" />
			    <label>Ir a renovaci&oacute;n de equipo</label>
			</p>
		</div>
	</div>
	<div class="botones margenIzquierdo"><a href="#"
		class="btnCancelarSeleccionEquipo enlaceSencillo">Cancelar</a>
		<a class="btnDesactivado btnContinuarAccionAntiguedad" href="#" rel="<r:pageUrl pageLabel='${pageEquipos}'></r:pageUrl>">
				<span>Continuar</span>
		</a>		
	</div>
	</div>
	<!-- /lightboxes ingreso -->
	
	<form id="myform" action="<%=request.getContextPath()%>/portlet/equipo/comprobantePreIngresoImprimir.jsp" method="post">
		<jsp:include page="/token.jsp" flush="true"/>
		<input name="imprimir_nroOrden" id="imprimir_nroOrden" type="hidden" value=""/>
		<input name="imprimir_fecha" id="imprimir_fecha" type="hidden" value=""/>
		<input name="imprimir_marca" id="imprimir_marca" type="hidden" value=""/>
		<input name="imprimir_imei" id="imprimir_imei" type="hidden" value=""/>
		<input name="imprimir_fallas" id="imprimir_fallas" type="hidden" value=""/>
		<input name="imprimir_comentarios" id="imprimir_comentarios" type="hidden" value=""/>
		<input name="imprimir_accingreso" id="imprimir_accingreso" type="hidden" value=""/>		
		<input name="imprimir_SucursalRegion" id="imprimir_SucursalRegion" type="hidden" value=""/>
		<input name="imprimir_SucursalCiudad" id="imprimir_SucursalCiudad" type="hidden" value=""/>		
		<input name="imprimir_SucursalesIngreso" id="imprimir_SucursalesIngreso" type="hidden" value=""/>
	<form>

</f:view>