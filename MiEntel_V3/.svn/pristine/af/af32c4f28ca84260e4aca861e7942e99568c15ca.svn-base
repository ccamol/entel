<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="it" uri="http://i2b.cl/jsf/iterator/" %>
<%@ taglib uri="http://www.bea.com/servers/portal/tags/content" prefix="cm"%>
<cm:search id="tituloNoInscrito" query="idContenido = 'tituloNoInscritoMKT'" useCache="true"  />
<cm:search id="tituloPaso1" query="idContenido = 'tituloPaso1MKT'" useCache="true"  />
<cm:search id="msjConfirmaBUIC" query="idContenido = 'msjConfirmaBUICMKT'" useCache="true"  />

<script type="text/javascript">
		function fillSelects(selectid){ 

			// Vector para saber cuál es el siguiente combo a llenar
			var combos = new Array();
			combos['regionpromociones'] = "ciudadpromociones"; 
			combos['ciudadpromociones'] = "comunapromociones"; 
			// Tomo el nombre del combo al que se le a dado el clic por ejemplo: país 
			
			posicion = selectid.indexOf('regionpromociones') != -1 ? 'regionpromociones' : 'ciudadpromociones';
			 
			// Tomo el valor de la opción seleccionada 
			valor = $("select[id*="+posicion+"]").val(); 
		
			  
			// Evaluó que si es país y el valor es 0, vacié los combos de estado y ciudad
			if(posicion == 'regionpromociones' && valor == "0"){ 
				  $("select[id*=ciudadpromociones]").html(' <option value="0" selected="selected">Seleccione</option>');
			      $("select[id*=comunapromociones]").html(' <option value="0" selected="selected">Seleccione</option>');
			      $("select[id*=ciudadpromociones]")[0].setValue('0');
			      $("select[id*=comunapromociones]")[0].setValue('0');
			} else { 
				/* En caso contrario agregado el letreo de cargando a el combo siguiente 
				 Ejemplo: Si seleccione país voy a tener que el siguiente según mi vector combos es: estado por qué combos [país] = estado
				*/ 
				$("select[id*="+combos[posicion]+"]").html('<option value="0">Cargando...</option>');
				$("select[id*="+combos[posicion]+"]")[0].setValue('0');
				$("select[id*=comunapromociones]").html('<option value="0" selected="selected"></option>');
				$("select[id*=comunapromociones]")[0].setValue('0');
				/* Verificamos si el valor seleccionado es diferente de 0 y si el combo es diferente de ciudad, esto porque no tendría caso hacer la consulta a ciudad porque no existe un combo dependiente de este */
				if(valor != "0") { 
					// Llamamos a pagina de donde ejecuto las consultas para llenar los combos
					var combo = $("select[id*="+posicion+"]").attr("name"); // Nombre del combo 
					var id =  $("select[id*="+posicion+"]").val();
					  
					var reg = $("select[id*=regionpromociones]").val();
					  
					var url = '<%= request.getContextPath()%>/ServletAreas';
				      //var url = '<%= request.getContextPath()%>/fillSelectAjax.faces';
					$.ajax({
				    	type: 'POST',
				        url: url,
				        dataType: 'json',
				        data : 'combo='+combo+'&id='+id+'&reg='+reg+'&r='+Math.random(),
				        success: function (opt){
							var optionsselect = "";
							//var opt = eval("(" + data + ")");
							optionsselect = '<option value="0">Seleccione</option>';
							for(var i = 0; i< opt.length;i++){
			                	optionsselect += '<option value="'+opt[i].codigo+'">'+opt[i].descripcion+'</option>';
							}
							     
							$("select[id*="+combos[posicion]+"]").html(optionsselect); //Tomo el resultado de pagina e inserto los datos en el combo indicado
							$("select[id*="+combos[posicion]+"]")[0].setValue('0');
						}
					});
				}
			}
		}


		$(document).ready(function() {
			var regionMMKT = '<h:outputText value="#{administracionServicios.datosBuic.direccionContacto.region.descripcion}"/>';
			var ciudadMMKT = '<h:outputText value="#{administracionServicios.datosBuic.direccionContacto.ciudad.descripcion}"/>';
			var comunaMMKT = '<h:outputText value="#{administracionServicios.datosBuic.direccionContacto.comuna.descripcion}"/>';
			var calleMMKT = '<h:outputText value="#{administracionServicios.datosBuic.direccionContacto.calle}"/>';
			var nmroMMKT = '<h:outputText value="#{administracionServicios.datosBuic.direccionContacto.numero}"/>';
			var emailMMKT = '<h:outputText value="#{administracionServicios.datosBuic.email}"/>';

			if (regionMMKT=='' || ciudadMMKT=='' || comunaMMKT=='' || calleMMKT=='' || nmroMMKT=='' || emailMMKT == ''){
				$("#bt_serv_continuar1B").addClass('deshabilitado btnAzulGrandeDesactivado').removeClass('btnAzulGrande');
			}

		    $("select[id*=regionpromociones]")[0].setValue('<h:outputText value="#{cuentaController.usuario.direccionContacto.region.codigo}"/>');
			$("select[id*=ciudadpromociones]")[0].setValue('<h:outputText value="#{cuentaController.usuario.direccionContacto.ciudad.codigo}"/>');
		    $("select[id*=comunapromociones]")[0].setValue('<h:outputText value="#{cuentaController.usuario.direccionContacto.comuna.codigo}"/>');

		    checkedRadio('sexoBUIC','<h:outputText value="#{administracionServicios.datosBuic.sexo}"/>');		
		});
		
		function setAreas(){
			$("input[id*=hcpromociones]").val($("select[id*=ciudadpromociones]").val());
			$("input[id*=hcompromociones]").val($("select[id*=comunapromociones]").val());
		}
		
		function upper(txt) {
			var txtUpper = $(txt).val().toUpperCase();
			$(txt).val(txtUpper);	
		}

		function checkedRadio(nameRadio,valueRadio){
	        var i;
	    	var dep  = $("[name*='"+nameRadio+"']");

	        for (i=0;i<dep.length;i++){
	           if (dep[i].value == valueRadio ){
	    	   		dep[i].checked= true;
	    	   		return;
	    	   }
	           
	        }
	    }
	</script>

   <div class="caja verde clearfix">
      <div class="caja_imagen_alerta"></div>
      <div class="caja_texto">
         <p class="tipoVerde"><span class="caja_verde_titulo">Estado:
            <cm:getProperty node="${tituloNoInscrito[0]}" name="html" />         
         </p>
      </div>
   </div>
   <h2 style="padding-left: 0;">Instrucciones</h2>
   <!-- MOBIL MARKETING PASO 1 -->
   <div class="clearfix" id="serv_mm_paso_1">

      <span class="serv_pasos"><strong>Paso 1 de 3</strong></span>
      <cm:getProperty node="${tituloPaso1[0]}" name="html" /> 
      
      <div class="servicios margen_top_doble" id="serv_mm_datos_personales">
         <h2 class="datos_personales"><a href="#" class="modificar">Modificar</a>
            Datos Personales
         </h2>
         <span class="disclaimer">* Todos los campos son obligatorios.</span>
         
         <div class="form_datos_personales clearfix">
	         <h:form styleClass="validarFormMMKT form_serv_mm_datos_personales_MMKT" id="form_serv_mm_datos_personales_MMKT">
				<jsp:include page="/token.jsp" flush="true"/>
	            <div class="clearfix fsmmdp_direccion_detalle">
	               <div class="misDatos_fila clearfix" style="position: relative; z-index: 490;">
	                  <label>Direcci&oacute;n:</label>
	                  <div class="campo">
	                     <strong> 
	                     	<h:outputText value="#{administracionServicios.datosBuic.direccionContacto.calle}"/>&nbsp;
	                     	<h:outputText value="#{administracionServicios.datosBuic.direccionContacto.numero}"/>,&nbsp;
	                     	<h:outputText value="#{administracionServicios.datosBuic.direccionContacto.departamento}"/>,&nbsp;
	                     	<h:outputText value="#{administracionServicios.datosBuic.direccionContacto.comuna.descripcion}"/>,&nbsp;
	                     	<h:outputText value="#{administracionServicios.datosBuic.direccionContacto.ciudad.descripcion}"/>,&nbsp;
	                     	<h:outputText value="#{administracionServicios.datosBuic.direccionContacto.region.descripcion}"/>	                     	
	                     </strong>
	                  </div>
	               </div>
	            </div>
	            <div class="clearfix fsmmdp_direccion" style="display:none;">
	                <div class="misDatos_fila clearfix" style="position:relative; z-index:3;">
	                    <label>Región:</label>
	                    <div class="campo">
	                        <div class="yellowField oculto">
	                        	<h:selectOneMenu onchange="fillSelects(this.id)" id="regionpromociones" styleClass="selectBoxAmarillo region-promociones region_MMKT" style="width:250px;">
                                     <f:selectItems value="#{cuentaController.regionesList}"/>
                        		</h:selectOneMenu>                        		
	                        </div>
	                        <strong> <h:outputText value="#{administracionServicios.datosBuic.direccionContacto.region.descripcion}"/> </strong>
	                    </div>
	                </div>
	                
	                <div class="misDatos_fila clearfix" style="position:relative; z-index:2;">
	                    <label>Ciudad:</label>
	                    <div class="campo">                                            
	                        <div class="yellowField oculto">
	                            <select onchange="fillSelects(this.id)"	id="ciudadpromociones"	class="selectBoxAmarillo ciudad_MMKT" name="ciudad_MMKT" style="width:250px;">
									<it:iterator var="item" value="#{cuentaController.ciudadesList}">
									    <option value="<h:outputText value="#{item.codigo}"/>"><h:outputText value="#{item.codigo}"/></option>
									</it:iterator>									
                            	</select>
                            	<h:inputHidden id="hcpromociones" value="#{cuentaController.usuario.direccionContacto.ciudad.codigo}"/> 
	                        </div> 
	                        <strong><h:outputText value="#{administracionServicios.datosBuic.direccionContacto.ciudad.descripcion}"/></strong>                                     
	                    </div>
	                </div>
	                
	                
	                <div class="misDatos_fila clearfix" style="position:relative; z-index:1;">
	                    <label>Comuna:</label>
	                    <div class="campo">
	                        <div class="yellowField oculto">                                                
	                            <select onchange="setAreas()" id="comunapromociones"
								class="selectBoxAmarillo comuna_MMKT" name="comuna_MMKT" style="width:250px;">
									<it:iterator var="itemc" value="#{cuentaController.comunasList}">
									    <option value="<h:outputText value="#{itemc.codigo}"/>"><h:outputText value="#{itemc.codigo}"/></option>
									</it:iterator>
                             	</select>
                            <h:inputHidden id="hcompromociones" value="#{cuentaController.usuario.direccionContacto.comuna.codigo}"/>                                             
	                        </div>
	                        <strong><h:outputText value="#{administracionServicios.datosBuic.direccionContacto.comuna.descripcion}"/>  </strong>
	                    </div>
	                </div>
	                
	                <div class="misDatos_fila clearfix">
	                    <label>Calle:</label>
	                    <div class="campo">                                            
	                        <div class="yellowField oculto">
	                            <span class="left"></span>
	                            <h:inputText id="calleBUIC" styleClass="calle_MMKT" maxlength="30" style="width:240px"/>	                            
	                            <span class="right"></span>
	                        </div>
	                        <strong><h:outputText value="#{administracionServicios.datosBuic.direccionContacto.calle}"/></strong>
	                        
	                    </div> 
	                </div>
	                
	                <div class="misDatos_fila clearfix">
	                    <label>Nro:</label>
	                    <div class="campo" style="padding-right:10px;">
	                        <div class="yellowField oculto">
	                            <span class="left"></span>
	                            <h:inputText id="nmroBUIC" styleClass="numero_MMKT input_numerico" maxlength="30" style="width:50px" />
	                            <span class="right"></span>
	                        </div>
	                        <strong><h:outputText value="#{administracionServicios.datosBuic.direccionContacto.numero}"/></strong>
	                    </div>
	                    <div class="campo">
	                        <span class="text">Dpto / Casa / Otro:</span>
	                        <div class="yellowField oculto">
	                            <span class="left"></span>
	                            <h:inputText id="dptoBUIC" maxlength="30" style="width:50px" />
	                            <span class="right"></span>
	                        </div>
	                        <strong><h:outputText value="#{administracionServicios.datosBuic.direccionContacto.departamento}"/></strong>
	                    </div>
	                </div>
	                
	            </div>
	                        
	            <div class="misDatos_fila clearfix">
	               <label>E-mail:</label>
	               <div class="campo">
	                  <div class="yellowField oculto" style="display: none;">
	                     <span class="left"></span>
	                     <h:inputText id="emailBUIC" value="#{administracionServicios.datosBuic.email}" styleClass="email_MMKT"/>
	                     <span class="right"></span>
	                  </div>
	                  <strong><h:outputText value="#{administracionServicios.datosBuic.email}"/></strong>
	               </div>
	            </div>
	            <div class="misDatos_fila clearfix">
	               <label>Fecha Nacimiento:</label>
	               <div class="campo">
	                  <div class="oculto" style="display: none;">
	                     <span class="left"></span>
	                     <strong> </strong><h:outputText value="#{administracionServicios.datosBuic.fechaNacimiento}"><f:convertDateTime type="date" pattern="dd/MM/yyyy"/></h:outputText> </strong>>
	                     <span class="right"></span>
	                  </div>
	                  <strong><h:outputText value="#{administracionServicios.datosBuic.fechaNacimiento}"><f:convertDateTime type="date" pattern="dd/MM/yyyy"/></h:outputText></strong>	                  
	               </div>
	            </div>
	            <div class="misDatos_fila clearfix">
	               <label>Soy:</label>
	               <div class="campo">
	                  <div class="yellowField checklist oculto" style="display: none;">	                     
	                     <h:selectOneRadio id="sexoBUIC" styleClass="radio sinFondo" style="background-image: none;" value="#{administracionServicios.datosBuic.sexo}">
	                     	<f:selectItems value="#{cuentaController.sexoList}" />	                     	
	                   	 </h:selectOneRadio> 	 
	                  </div>
	                  <strong> <h:outputText value="#{administracionServicios.datosBuic.sexoDesc}"/> </strong>
	               </div>	               
	            </div>
	            <div class="misDatos_boton" style="display: block;" id="bt_serv_continuar_1">
	               <a href="#" class="btnAzulGrande btnAzulGrandeLargo" id="bt_serv_continuar1B"><span>Continuar</span></a>
	            </div>
	            <div class="misDatos_boton" style="display: none;" id="bt_serv_dp_actualizar">
	               <a href="#" class="btnAzulGrande btnAzulGrandeLargo"><span>Guardar Cambios</span></a>
	            </div>
	            <div class="caja amarilla clearfix margen_top_medio" id="bt_serv_dp_act_confirmar" style="padding-left:30px; display:none;">
	               <div class="caja_imagen_alerta_confirma"></div>
	               <div class="caja_texto">
	                  <p class="tipoVerde"> 
	                  	<cm:getProperty node="${msjConfirmaBUIC[0]}" name="html" /> 
	                  </p>
	                  <a href="#" class="btnAzulGrande btnAzulLargo">
	                  		<span>Confirmar</span>  
	                  </a>                                      
	               </div>
	            </div>
	         </h:form>
	  	 </div>
      </div>
   </div>
