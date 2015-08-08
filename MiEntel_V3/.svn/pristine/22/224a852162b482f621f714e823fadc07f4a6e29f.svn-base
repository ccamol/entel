<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="it" uri="http://i2b.cl/jsf/iterator/" %>
<%@ taglib prefix="r"  uri="http://www.bea.com/servers/portal/tags/netuix/render"%>
<%@ taglib prefix="cm" uri="http://www.bea.com/servers/portal/tags/content" %>
<%@ taglib prefix="entel" uri="/WEB-INF/tld/entel-tags.tld" %>

<cm:search id="infoDebesSaber" query="idContenido = 'debesSaberNumeroFrecuente'" useCache="true"  />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- <script type="text/javascript" src="https://mi.entel.cl/personas/mientelresources/js/ampliacion.numeracion.js"></script> -->

<f:view beforePhase="#{planController.obtenerNumerosFrecuentes}">

<script type="text/javascript">

	$(document).ready(function() {
		initNumeroFrecuente();
		comunik2();
		comunik2Validacion();	

		$('input.nf_input').inputBox();
		$('input.nf_inputAmarillo').inputBoxAmarillo();
		$('select.nf_select').selectBox();
		$('select.nf_selectAmarillo').selectBoxAmarillo();	

		$('.prefijo').change(function(){
			   var fecha = new Number('<h:outputText value="#{planController.fechaActualFormat}"></h:outputText>');			   
			   $('.ampliacionNumerica').attr('maxlength',ampliacionNumerica(parseInt($('.prefijo').val(), 10),fecha));
			   $('.ampliacionNumerica').val('');
		 });
	     $('.prefijo').trigger("change");	
	   
	});

	// accion : 1(AGREGAR) 2(MODIFICAR)
	function administrarNumeroFrecuente(accion,codigoSlot){

         var url='<%=request.getContextPath()%>/portlet/plan/numeroFrecuentePPJson.faces';
         var nroRecibeSolicitud;
         var infoNroRecibeSolicitud;

         if($('div.numero_frecuente select').next().is(':visible')){
          nroRecibeSolicitud = $('div.numero_frecuente div.campo input').val()+$('div.numero_frecuente input.nf_input').val();
          infoNroRecibeSolicitud = $('div.numero_frecuente div.campo input').val()+" "+$('div.numero_frecuente input.nf_input').val();
	     }else{
	       nroRecibeSolicitud = "9"+$('div.numero_frecuente input.nf_input').val();
	       infoNroRecibeSolicitud = $('div.numero_frecuente input.nf_input').val();
	     } 

	     if($('div.numero_frecuente').find('input.nf_input').val().length == parseInt($('div.numero_frecuente').find('input.nf_input').attr('maxlength'))) {

	      $('a#loadingNumeroFrecuente').html("<img width='180px' src='../framework/skins/mientel/img/thickbox/TB_cargando.gif'/>&nbsp;&nbsp;<b>Enviando...</b>");
          $('div.numero_frecuente').find('a.cancelar').hide();
          $('div.numero_frecuente').find('a.administrar').hide();

	      var mensaje = (accion == 1) ? 'El N&uacute;mero Frecuente ha sido guardado.' : 
	              (accion == 3) ? 'El N&uacute;mero Frecuente ha sido modificado.' : 
	                   '';
	      $.ajax({
                  type: 'POST',
                  url: url,
                  async: false,
                  dataType: 'json',
                  data: {codAccion:accion,idSlot:codigoSlot,numeroRecibeSolicitud:nroRecibeSolicitud},
                  success: function (resp){
                    if(resp.estado == 'Ok'){
                      $('a#loadingNumeroFrecuente').html("");
                      $('div.numero_frecuente').addClass('activo');
                      $('div.numero_frecuente div.paso_1').show().find('div.msg').html(mensaje).show();
                      $('div.numero_frecuente div.paso_1').find('div.numero').text( infoNroRecibeSolicitud );
                 	  $('div.numero_frecuente div.paso_1').find('div.alerta_borrar').hide();
                      $('div.numero_frecuente div.paso_modificar').hide();
                      $('div.numero_frecuente div.paso_nuevo').hide();

                     actualizarSaldo(2);
                    }else{
                      $('a#loadingNumeroFrecuente').html("");
                      $('div.numero_frecuente').addClass('activo');
                      $('div.numero_frecuente div.paso_1').show().find('div.msg').html(resp.detalle).show();
                      $('div.numero_frecuente div.paso_1').find('div.numero').text( infoNroRecibeSolicitud );
                 	  $('div.numero_frecuente div.paso_1').find('div.alerta_borrar').hide();
                      $('div.numero_frecuente div.paso_1').find('div.botonera').hide();
                      $('div.numero_frecuente div.paso_modificar').hide(); 
              		}
                    $('div.numero_frecuente').find('a.cancelar').show();
                    $('div.numero_frecuente').find('a.administrar').show();
                 }
	         });
	     }else{
	       $('div.numero_frecuente div#globoError').fadeIn(200);
	       $('div.numero_frecuente input.nf_input').unbind().keypress(function() {
	       $('div.numero_frecuente div#globoError').fadeOut(200);        
	    }); 

	   }
	 }

	// accion : 3(ELIMINAR)
	function eliminarNumeroFrecuente(accion,codigoSlot){

		 var url='<%=request.getContextPath()%>/portlet/plan/numeroFrecuentePPJson.faces';

			   $('a#loadingNumeroFrecuente').html("<img width='180px' src='../framework/skins/mientel/img/thickbox/TB_cargando.gif'/>&nbsp;&nbsp;<b>Enviando...</b>");
			   $('div.numero_frecuente').find('a.cancelar').hide();
		       $('div.numero_frecuente').find('a.administrar').hide();

			       $.ajax({
		                type: 'POST',
		                url: url,
		                async: false,
		                dataType: 'json',
		                data: {codAccion:accion,idSlot:codigoSlot},
		                success: function (resp){
			                 if(resp.estado == 'Ok'){
			                	 $('a#loadingNumeroFrecuente').html('');
			                	 $('div.numero_frecuente').addClass('activo');
			                	 $('div.numero_frecuente').find('div.paso_1').hide();
			                	 $('div.numero_frecuente').find('div.paso_modificar').hide();
			                	 $('div.numero_frecuente').find('div.paso_nuevo').show();
	
			                	 actualizarSaldo(2);
			                 }else{

			                	 $('a#loadingNumeroFrecuente').html('');
			                	 $('div.numero_frecuente').addClass('activo');
			                	 $('div.numero_frecuente div.paso_1').show().find('div.msg').html(resp.detalle).show();
			                	 $('div.numero_frecuente div.paso_1').find('div.alerta_borrar').hide();
					         }

			                 $('div.numero_frecuente').find('a.cancelar').show();
			                 $('div.numero_frecuente').find('a.administrar').show();
	                }
		       });
		   }

		
</script>

	<h:panelGroup rendered="#{!planController.cargaNrosFrecuenteAjax}">
	
		<entel:view name="numeroFrecuente">
			<jsp:include page="numeroFrecuentePPInclude.jsp"></jsp:include>
		</entel:view>
		
	</h:panelGroup>
	
	<h:panelGroup rendered="#{planController.cargaNrosFrecuenteAjax}">
		<jsp:include page="numeroFrecuentePPInclude.jsp"></jsp:include>
	</h:panelGroup>
	
	<!-- MENSAJES -->
	<jsp:include page="../common/messages_table.jsp"></jsp:include>
	
</f:view>
