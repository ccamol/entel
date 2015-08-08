<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.bea.com/servers/portal/tags/content" prefix="cm"%>
<cm:search id="mensajeConfirmacionExitoCM" query="idContenido = 'idContentMensajeConfirmacionExitoCM'" useCache="true"  />

<script type="text/javascript">

 function asignarCodReclamo(){
	
	var codReclamo = '<%=request.getParameter("imp_cod_reclamo") != null ? request.getParameter("imp_cod_reclamo") : ""%>';
	var divMensaje = document.getElementById("mensajeConfirmacionExito").innerHTML;  

	document.getElementById("numeroReclamoOk").innerHTML= (codReclamo);
		
 }    
  window.onload=function(){
		asignarCodReclamo();
  };

	function replaceAll( text, busca, reemplaza ){
		  while (text.toString().indexOf(busca) != -1)
		      text = text.toString().replace(busca,reemplaza);
		  return text;
	}
</script>

<style type="text/css">
body,div,dl,dt,dd,ul,ol,li,h1,h2,h3,h4,h5,h6,pre,form,fieldset,input,textarea,p,blockquote,th,td,span{
	margin:0;
	padding:0;
	outline:none;
}
body{
	font: 62.5% Arial, Helvetica, sans-serif;
}

/*---------------------
BLOQUES DE CONTENIDO
---------------------*/
DIV#bloque-izq
{
	width: 179px;
	float: left;
}
/*--------------------------
BLOQUES CONTENIDO
--------------------------*/
DIV#bloque-izq
{
	width: 179px;
	float: left;
	margin-left: 2px;
}
DIV#bloque-der
{
	float: left;
}
DIV#centro
{
	width: 548px;
	float: left;
	display: inline;
	margin: 0 20px;
}
#centro DIV.debes-saber
{
	padding: 5px 0 0 78px;
	margin: 0 0 12px;
}
.fix-floated-err
{
	width: 100%;
	float: left;
}
DIV#centro P
{
	font-size: 12px;
	margin-bottom: 9px;
}

/***********************************************************
*************** MENSAJE DE ALERTA DE SISTEMA  **************
***********************************************************/

#centro h1,
#centroCompleto h1 {
	font-family:Arial, Helvetica, sans-serif;
	font-size:1.8em;
	font-weight:bolder;
	color:#064687;
	padding:0 0 20px 15px;
	background:transparent url(../../framework/skins/mientel/img/sitio/sitioBulletH2.gif) no-repeat 0 4px;
}

/*-----------------------------------------------------------
					Parrafos
-----------------------------------------------------------*/
#centro .contenido_parrafos {
	display:block;
	margin:0 0 20px 0;
}
.imprimir{
	border: #000000 dotted 2px;
	background: #FFFFC9;
    float:right;
	font-size:12px;
	font-weight:bold;
	padding:3px;
	position: relative;
	top:-10px;
}
#centro h1 {
    background: url("../../framework/skins/mientel/img/bullet_h2.gif") no-repeat scroll 0 4px transparent;
    color: #064687;
    font-family: Arial,Helvetica,sans-serif;
    font-size: 1.8em;
    font-weight: bolder;
    padding: 0 0 20px 15px;
}
#centro p.nota {
    color: #999999;
    font-family: Arial,Helvetica,sans-serif;
    font-size: 1.1em;
}
#centro .bloqueReclamo {
    display: block;
    padding-bottom: 80px;
}
#centro .bloqueReclamo .reclamoFila {
    display: block;
    padding-top: 15px;
}
#centro .bloqueReclamo .reclamoFila.filaCorta {
    padding-top: 5px;
}
#centro .bloqueReclamo .reclamoFila.filaEstandar {
    padding-top: 10px;
}
#centro .bloqueReclamo .reclamoFila label {
    color: #444444;
    float: left;
    font-size: 1.2em;
    padding-top: 5px;
    width: 159px;
}
#centro .bloqueReclamo .reclamoFila .bloqueCampos {
    float: left;
    width: 389px;
}
#centro .bloqueReclamo .reclamoFila .bloqueCampos {
    float: left;
    width: 389px;
}
#centro .bloqueReclamo .reclamoFila .bloqueCampos .textoCampo {
    color: #444444;
    font-size: 1.2em;
    padding-top: 5px;
}
#centro .bloqueReclamo .reclamoFila .bloqueCampos .filaAlto {
    height: 26px;
}
#centro .bloqueReclamo .reclamoFila .subFila {
    position: relative;
}
.bloqueTecnico {
    display: none;
}
.textAreaReclamo {
    background: url("../../img/reclamo/textAreaReclamo.jpg") no-repeat scroll 0 0 transparent;
    display: block;
    height: 142px;
    width: 307px;
}
.textAreaReclamo textarea {
    background: none repeat scroll 0 0 transparent;
    border: medium none;
    height: 124px;
    outline: medium none;
    overflow: hidden;
    padding: 9px 7px;
    resize: none;
    width: 293px;
}
.textoNotificacion {
    color: #444444;
    float: left;
    font-size: 1.2em;
    padding-left: 11px;
}
.flotarIzq {
    float: left;
}
.flotarDer {
    float: right;
}
.bloqueTecnicoNotificacionCarta, .bloqueTipoReclamoComercial, .bloqueCobrosBoleta, .bloqueRecargasSaldos, .bloqueRecargasNotificacionCarta, . {
    display: none;
}
.bloqueNumeroDocumento {
    display: none;
}
.bloqueResumenReclamoCobros, .bloqueResumenReclamoTecnico, .bloqueResumenReclamoRecarga {
    display: none;
}
.altoFijo {
    padding-top: 5px;
}
.altoTriple {
    padding-top: 30px;
}
.distanciaTriple {
    margin-top: 150px;
}
.botonEnlace {
    color: #000000;
    cursor: pointer;
    display: inline-block;
    font-family: Arial,Helvetica,sans-serif;
    font-size: 12px;
    padding: 10px 0 0 10px;
    text-decoration: underline;
}
.botonEnlace:hover {
    text-decoration: none;
}
.mensajeAlertaGrande {
    background-color: #FFF1A8;
    border-bottom: 1px solid #CFC489;
    border-top: 1px solid #CFC489;
    display: block;
    padding: 10px 0 12px;
    text-align: center;
}
.mensajeAlertaGrande .textoArriba {
    background: url("../../img/icons/icoAlerta.jpg") no-repeat scroll 0 0 transparent;
    color: #000000;
    display: inline-block;
    font-family: Arial,Helvetica,sans-serif;
    font-size: 11px;
    line-height: 27px;
    padding-left: 47px;
}
.mensajeAlertaMediano {
    background-color: #FFF1A8;
    border-bottom: 1px solid #CFC489;
    border-top: 1px solid #CFC489;
    display: block;
    padding: 10px 0 12px;
}
.mensajeAlertaMediano .texto {
    color: #000000;
    float: left;
    font-family: Arial,Helvetica,sans-serif;
    font-size: 11px;
    padding: 10px 15px 0 117px;
}
.mensajeConfirmacionExito {
    background-color: #DDF0D9;
    border-bottom: 1px solid #BED7BC;
    border-top: 1px solid #BED7BC;
    display: block;
    padding: 20px;
}
.mensajeConfirmacionExito p {
    color: #000000;
    font-family: Arial,Helvetica,sans-serif;
    font-size: 11px;
}
.tipoReclamoMensaje1, .tipoReclamoMensaje2 {
    display: none;
}

.tipoReclamoTecnicoMensaje1, .tipoReclamoTecnicoMensaje2 {
    display: none;
}

.btnAzulMediano span {
    padding: 10px 5px 10px 14px;
}
.btnAzulReclamo span {
    padding: 10px 19px 10px 31px;
}
.btnAzulDelgadoExtraLargo span {
    padding: 8px 20px 6px 28px;
}
.blurDireccion {
    color: #999999;
    display: inline-block;
    font-family: Arial,Helvetica,sans-serif;
    font-size: 12px;
    left: 6px;  
    top: 11px;
    z-index: 52;
    position: relative;
    top: -30px;
}



</style>


<!-- *************************    COMPROBANTE RECLAMO -->

<div id="centro">
<br /><br />	
<h1>Reclamo</h1>
<div class="imprimir">Para imprimir debes utilizar el<br/>boton derecho del Mouse</div>
<br /><br />
<div class="bloqueReclamo clearfix";>
  <div class="bloqueResumen">
            <!-- FILA -->			
			<div class="reclamoFila clearfix" style="display:inline-block;">
				<label><strong>Concesionaria:</strong></label>
				<div class="bloqueCampos clearfix">
					<div class="textoCampo">Entel PCS Telecomunicaciones S. A.</div>
				</div>
			</div>
			<!-- /FILA -->
		   <!-- FILA -->			
			<div class="reclamoFila filaEstandar clearfix">
				<label><strong>Nombre:</strong></label>
				<div class="bloqueCampos clearfix">
					<div class="textoCampo resumen_rut"><%=request.getParameter("imp_nombre") != null ? request.getParameter("imp_nombre") : ""%></div>
				</div>
			</div>
			<!-- /FILA -->
			<!-- FILA -->
			<div class="reclamoFila filaEstandar clearfix">
				<label><strong>RUT:</strong></label>
				<div class="bloqueCampos clearfix">
					<div class="textoCampo resumen_rut"><%=request.getParameter("imp_rut") != null ? request.getParameter("imp_rut") : ""%></div>
				</div>
			</div>
			<!-- /FILA -->
			<!-- FILA -->
			<div class="reclamoFila filaEstandar clearfix">
				<label><strong>N&uacute;mero reclamado:</strong></label>
				<div class="bloqueCampos clearfix">
					<div class="textoCampo resumen_numero_reclamo"><%=request.getParameter("imp_numero_reclamado") != null ? request.getParameter("imp_numero_reclamado") : ""%></div>
				</div>
			</div>
			<!-- /FILA -->						
			<!-- FILA -->
			<div class="reclamoFila filaEstandar clearfix">
				<label><strong>Reclamo:</strong></label>
				<div class="bloqueCampos clearfix">
					<div class="textoCampo resumen_reclamo"><%=request.getParameter("imp_reclamo") != null ? request.getParameter("imp_reclamo") : ""%></div>
				</div>
			</div>
			<!-- /FILA -->
			<!-- FILA -->
			<div class="reclamoFila filaEstandar clearfix">
				<label><strong>Tipo de reclamo:</strong></label>
				<div class="bloqueCampos clearfix">
					<div class="textoCampo resumen_tipo_reclamo"><%=request.getParameter("imp_tipo_reclamo") != null ? request.getParameter("imp_tipo_reclamo") : ""%></div>
				</div>
			</div>
			<c:set var="reclamo"><%=request.getParameter("id_imp_reclamo") != null ? request.getParameter("id_imp_reclamo") : ""%></c:set>
			<c:if test="${reclamo =='1'}">
			    <c:set var="tipo"><%=request.getParameter("id_imp_tipo_reclamo") != null ? request.getParameter("id_imp_tipo_reclamo") : ""%></c:set>
			   <c:if test="${tipo =='1'}">			
					<!-- FILA -->				
						<div class="reclamoFila filaEstandar clearfix solo_comercial">
							<label><strong>N&uacute;mero de Documento:</strong></label>
							<div class="bloqueCampos clearfix">
								<div class="textoCampo textoCobroNumero resumen_numero_docuemnto"><%=request.getParameter("imp_nro_doc") != null ? request.getParameter("imp_nro_doc") : ""%></div>
							</div>
						</div>			
					<!-- /FILA -->
					<!-- FILA -->
					<div class="reclamoFila filaEstandar clearfix solo_comercial" >
						<label><strong>Monto Impugnado:</strong></label>
						<div class="bloqueCampos clearfix">
							<div class="textoCampo textoCobroMonto resumen_monto"><%=request.getParameter("imp_monto_doc") != null ? request.getParameter("imp_monto_doc") : ""%></div>
						</div>
					</div>
				<!-- /FILA -->
				</c:if>
			</c:if>				
			<c:if test="${reclamo =='2'}">
			<!-- FILA -->
					<div class="reclamoFila filaEstandar clearfix solo_comercial" >
						<label><strong>Localización del problema:</strong></label>
						<div class="bloqueCampos clearfix">
							<div class="textoCampo textoCobroMonto resumen_monto"><%=request.getParameter("imp_localizacion") != null ? request.getParameter("imp_localizacion") : ""%></div>
						</div>
					</div>
				<!-- /FILA -->
			
			</c:if>
			<!-- FILA -->
			<div class="reclamoFila filaEstandar clearfix">
				<label><strong>Antecedentes:</strong></label>
				<div class="bloqueCampos clearfix">
					<div class="textoCampo textoCobroAntecedentes resumen_antecedentes" ><p align="justify"><%=request.getParameter("imp_antecedentes") != null ? request.getParameter("imp_antecedentes") : ""%></p></div>
				</div>
			</div>
			<!-- /FILA -->
			<!-- FILA -->
			<div class="reclamoFila filaEstandar clearfix" >
				<label><strong>Medio de respuesta:</strong></label>
				<div class="bloqueCampos clearfix">
					<div class="textoCampo textoCobroNotificacion resumen_medio_respuesta"><%=request.getParameter("imp_medio_respuesta") != null ? request.getParameter("imp_medio_respuesta") : ""%></div>
				</div>
			</div>
	
		<div class="bloqueConfirmacionExito" style="display:block;display:inline-block;margin-top:20px;">
			<!-- MENSAJE CONFIRMACION EXITO -->
			<div class="mensajeConfirmacionExito" id="mensajeConfirmacionExito" >
				<cm:getProperty node="${mensajeConfirmacionExitoCM[0]}" name="html"/>				
			</div>
      
       </div>
       
       <p style="font-weight: bold; color:#C0C0C0;padding:0px!important;border-top: 1px solid #999999; margin-left: 150px;width:250px; padding-top: 20px;text-align: center;margin-top:50px;" >Firma del cliente o representante legal</p>
			<!-- /FILA -->			
			<!-- /FILA -->				
  </div> 			
			<!-- /FILA -->
</div> 

</div>


