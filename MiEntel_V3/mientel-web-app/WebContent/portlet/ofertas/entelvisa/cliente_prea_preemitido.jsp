<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<f:view >    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>
</title>
</head>
<body>

<div id="container_entel_visa">
  <div id="header_entel_visa">
  
	    <div class="sl_entel_visa">
	      <p class="arriba_entel_visa">Bienvenido al mundo Entel Visa...</p>
	    </div>
	    
	    <!--sl-->
	    <div class="zona_entel_visa"><img src="../../framework/skins/mientel/img/ofertaVisa/zona.png" width="83" height="59" alt="Zona" /></div>
	    <!--zona-->
     
  </div>
  <!--header-->
  
 <div class="subtitulo_entel_visa">
 
	 <p>Hola <h:outputText value="#{ofertaBlindajeController.nombreUsuario }" /> ,<span style="color: #666; font-size:14px;font-weight:bold;" > Banco de Chile y Entel se unen hoy para darte una gran noticia: </span><br />
	 Tienes tu Tarjeta <span style="color:#FF6600; font-size:22px"> Entel Visa</span> pre aprobada!!!
	 </p>

 </div>
 

 <div class="vinetas_entel_visa" style="width:320px;">
 
 		<ul> 	 
 		    <li><img src="../../framework/skins/mientel/img/ofertaVisa/check.gif" width="16" height="16" alt="" /><span style="color:#FF6600;">Si deseas solicitarla</span> solo debes ingresar <a href="javascript:abrirFormEV();" class="abrirFormEV" >aqu&iacute;</a> y el banco se</li>                   
            <li class="no-imagen_entel_visa"> contactar&aacute; contigo para hacerte entrega de tu Tarjeta.</li>
            <li><img src="../../framework/skins/mientel/img/ofertaVisa/check.gif" width="16" height="16" alt="" />Acumulaci&oacute;n de puntos Zona por uso de tu Entel Visa.</li>
            <li><img src="../../framework/skins/mientel/img/ofertaVisa/check.gif" width="16" height="16" alt="" />30% descuento en equipos Entel la primera vez que se</li>
            <li class="no-imagen_entel_visa"> paguen con la tarjeta Entel Visa.</li>
            <li><img src="../../framework/skins/mientel/img/ofertaVisa/check.gif" width="16" height="16" alt="" />Pago de equipos Entel de 3 a 12 cuotas sin inter&eacute;s</li>
            <li class="no-imagen_entel_visa">pagando con Entel Visa.</li>
            <li><img src="../../framework/skins/mientel/img/ofertaVisa/check.gif" width="16" height="16" alt="" />Entradas al cine por $1.000 en Cinemundo y Cinemark.</li> 
            <li><img src="../../framework/skins/mientel/img/ofertaVisa/check.gif" width="16" height="16" alt="" />Hasta 30% de dcto. en fiestas y conciertos auspiciados</li>
            <li class="no-imagen_entel_visa">por Entel. </li>
            <li><img src="../../framework/skins/mientel/img/ofertaVisa/check.gif" width="16" height="16" alt="" />10% adicional en recargas realizadas por la Web con</li>
            <li class="no-imagen_entel_visa">cargo a Entel Visa.</li>
        </ul>
 </div>
 <!--vineta-->
 

 
 <div class="tarjeta_entel_visa">
     <img src="../../framework/skins/mientel/img/ofertaVisa/tarjeta.png" width="300" height="174" alt="" /></div>
 <!--tarjeta-->
 

  
  
  
 <div class="logos_entel_visa"><img src="../../framework/skins/mientel/img/ofertaVisa/logos.gif" width="189" height="22" alt="" /></div>
 
 <div class="disc_entel_visa">
 
  <div class="check_entel_visa">
    <p>
      <input id="no_mostrar" type="checkbox" value="0" name="no_mostrar" />
      No volver a mostrar este mensaje</p>
  </div><!--check-->
 
 <div id="ver-terminos" class="ver-terminos_entel_visa"><a>Ver condiciones</a><img src="../../framework/skins/mientel/img/ofertaVisa/ver-terminos.gif" width="14" height="14" alt="" /></div><!--terminos-->
 </div> 
<!--disc-->  
    <div id="terminos" class="terminos_entel_visa terminos_tres_entel_visa" style="display:none;">
    <h1>T&eacute;rminos y condiciones</h1>
    <div id="ver-terminos-into" class="ver-terminos_entel_visa into_entel_visa"><a>Ocultar condiciones</a><img src="../../framework/skins/mientel/img/ofertaVisa/cerrar-terminos.gif" width="14" height="14" alt="" /></div>
	<ul>
		<li><img src="../../framework/skins/mientel/img/ofertaVisa/check-terminos.gif" width="16" height="16" alt="" />Todas las compras con Entel Visa, suman puntos Zona, los que pueden ser utilizados en canje de equipos,</li>
        <li class="no-image_entel_visa">bolsas de minutos, recargas, Tarjetas SIM, reparaciones en Servicio T&eacute;cnico, entre otros. </li>              
        <li><img src="../../framework/skins/mientel/img/ofertaVisa/check-terminos.gif" width="16" height="16" alt="" />Adem&aacute;s, todo lo que hables con tu m&oacute;vil ENTEL suma Puntos Zona. </li>
        <li><img src="../../framework/skins/mientel/img/ofertaVisa/check-terminos.gif" width="16" height="16" alt="" />Por cada $100 de compras acumulas 1 punto Zona, que equivale a $1.</li>
        <li><img src="../../framework/skins/mientel/img/ofertaVisa/check-terminos.gif" width="16" height="16" alt="" />No acumulan puntos Zona las compras de cargo inmediato (avances en efectivo, compras en casino, juegos de azar),</li>
        <li class="no-image_entel_visa"> compra de combustibles, pagos autom&aacute;ticos de cuenta con cargo a esta tarjeta (PAT).</li>       
        <li><img src="../../framework/skins/mientel/img/ofertaVisa/check-terminos.gif" width="16" height="16" alt="" />El descuento de un 30% en equipo es v&aacute;lido solo la primera vez que se pague la cuota inicial de un equipo entel</li>
        <li class="no-image_entel_visa"> en arriendo con tu Entel Visa. Este beneficio tiene un tope de $100.000. S&Oacute;LO APLICA PARA NEGOCIOS REALIZADOS POR TITULARES ENTEL (equipo para cualquiera de sus l&iacute;neas). Descuento v&aacute;lido en Sucursales, Tiendas Express y Dealers.</li>   
        <li><img src="../../framework/skins/mientel/img/ofertaVisa/check-terminos.gif" width="16" height="16" alt="" />El beneficio de entradas al cine por $1.000 puede ser usado pagando con Entel Visa, v&aacute;lido 48 hrs. despu&eacute;s de</li>
   	    <li class="no-image_entel_visa">de su activaci&oacute;n. </li>      
        <li><img src="../../framework/skins/mientel/img/ofertaVisa/check-terminos.gif" width="16" height="16" alt="" />El descuento de 30% en entradas de fiestas y conciertos auspiciados por Entel es v&aacute;lido 48. hrs. despu&eacute;s de la</li>
        <li class="no-image_entel_visa"> activaci&oacute;n de tu Entel Visa.</li>   
        <li><img src="../../framework/skins/mientel/img/ofertaVisa/check-terminos.gif" width="16" height="16" alt="" />Descuento en recargas es v&aacute;lido para transacciones realizadas en <a href="http://www.entel.cl" target="_blank">www.entel.cl</a> y <a  href="http://www.bancochile.cl" target="_blank">www.bancochile.cl</a></li>
   	    <li class="no-image_entel_visa"> pagando con Entel Visa. </li>   
    </ul>

</div><!--terminos-->
</div>
<div id="formpreemitidovisa" style="display:none;margin:45px 70px 2px 2px">
<h1>Solicitud Tarjeta Entel Visa</h1>
<iframe scrolling="auto" src="http://entelvisa.bancochile-promociones.cl/cliente/" marginheight="0" marginwidth="0" frameborder="0" height="650px" width="100%" name="cci" id="cci">
</iframe>
</div>
<!--container-->
</body>
</html>
<script type="text/javascript">
//<![CDATA[
     $(document).ready(function() {

		//fix png
    	//$(document).pngFix();
			//términos
			$("#ver-terminos").click(function () {
			$("#terminos").fadeIn();
			$("#ver-terminos").hide();
			$("#ver-terminos-into").show();	
		});
			
		$("#ver-terminos-into").click(function () {
			$("#terminos").fadeOut();
			$("#ver-terminos").show();	
			$("#ver-terminos-into").hide();
		});		
		
        $("#no_mostrar").click(function () {
        	
        	var url='<%=request.getContextPath()%>/portlet/ofertas/entelvisa/ofertaVisaJson.faces';
    		$.ajax( {
    			type : 'POST',
    			url : url,	
    			dataType:'json',		
    			success : function(data)
    			{    
	    			if(data.estado == 'Ok'){
	    				location.href=window.location.pathname;
		        	}		  			
    		    }
    	        }); 
            

    	  });               
        
     });

     function abrirFormEV(){    	 
    	 $('#container_entel_visa').hide();
         $('#formpreemitidovisa').show();
     };

   //]]>  
</script>
</f:view >  