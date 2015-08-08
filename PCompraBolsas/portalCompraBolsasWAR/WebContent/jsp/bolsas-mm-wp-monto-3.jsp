<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="es">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="language" content="es" />
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<meta name="description" content="" />
		<meta name="keywords" content="" />
		<meta name="author" content="MZZO" />
		<link href="favicon.ico" rel="icon" />
		<title>Bolsas_ - Entel</title>
		<!-- CSS -->
		<link href="${pageContext.servletContext.contextPath}/css/reset.css" rel="stylesheet" />
		<link href="${pageContext.servletContext.contextPath}/css/main.css" rel="stylesheet" />
		<link href="${pageContext.servletContext.contextPath}/css/showcase.css" rel="stylesheet" />
		<!-- JS -->
		<!--<script src="js/modernizr.custom.js"></script>-->
		<c:set var="movil" value="${movil}"/>
		<c:set var="codCompra" value="${codCompra}"/>
		<c:set var="recargaController" value="${recargaController}"/>
		<c:set var="monto" value="${monto}"/>	
		<c:set var="msisdn" value="${msisdn}"/>				
		
	</head>
	<body class="">
		<div class="container">
			<!-- HEADER -->
			<header class="section-header clearfix">
				<a href="${pageContext.servletContext.contextPath}/inicio" id="menu-button" class="top-nav-btn">
					<span class="icon icon-arrow-left"></span>
				</a>
				<div class="logo logo-ql"></div>
				<h1 class="title bracket">Recarga WebPay_</h1>
			</header>		
			
			<!-- HEADER
			<header class="section-header clearfix">
				<a href="${pageContext.servletContext.contextPath}/inicio" id="menu-button" class="top-nav-btn">
					<span class="icon icon-arrow-left"></span>
				</a>
				<h1 class="title bracket">Bolsas_</h1>
			</header>
			-->
			
<!-- 
			<header class="section-header-sub clearfix">
				<h1 class="title bracket">WebPay_</h1>
			</header>
-->			
			
			<div class="sc-container">
				<h1 class="title title-md bracket">Confirma que los datos sean correctos</h1>
				<ul class="striped">
					<li class="term-list term-block">N&uacute;mero que quieres recargar: <b class="term-value">${msisdn}</b></li>
				</ul>
				
				<ul class="striped">
					<li class="term-list term-block">Monto a recargar: <b class="term-value">$${monto}</b></li>
				</ul>
				
				<ul class="striped">
					<li class="term-list term-block">Promoci&oacute;n:<b class="term-value"></b></li>
					<li class="term-list term-promocion"><b class="term-value-promocion">Este monto no tiene promoción asociada, te recomendamos recargar un monto más alto. *Promoción válida para telefonía móvil prepago y cuenta controlada</b></li>					
				</ul>
				
				<div class="separador no-margin-bottom"></div>

				<div class="form-group">			

					<form name="formulario_webpay" id="formulario_webpay" target="_self" method="POST" action="${recargaController.pagoWebPay.urlFormAction}">

						<input type="hidden" name="TBK_TIPO_TRANSACCION"   value="${recargaController.pagoWebPay.tipoTransaccion}"/>
						<input type="hidden" name="TBK_MONTO"              value="${recargaController.recargaWebPay.TBKMontoRecarga}"/>
						<input type="hidden" name="TBK_ORDEN_COMPRA"       value="${recargaController.recargaWebPay.ordenCompra}"/>
						<input type="hidden" name="TBK_ID_SESION"          value="${recargaController.tbkIdSesion}"/>               
						<input type="hidden" name="TBK_URL_RESULTADO"      value="${recargaController.pagoWebPay.urlResultado}&ordenCompra=${recargaController.recargaWebPay.ordenCompra}"/>
						<input type="hidden" name="TBK_URL_FRACASO"        value="${recargaController.pagoWebPay.urlFracaso}&ordenCompra=${recargaController.recargaWebPay.ordenCompra}"/>   
						<input type="hidden" name="TBK_NUM_TRX"            value="${recargaController.pagoWebPay.numTrx}"/>
						<input type="hidden" name="TBK_CODIGO_TIENDA_M001" value="${recargaController.pagoWebPay.codigoTienda}"/>
						<input type="hidden" name="TBK_ORDEN_TIENDA_M001"  value="${recargaController.recargaWebPay.ordenCompra}"/>
						<input type="hidden" name="TBK_MONTO_TIENDA_M001"  value="${recargaController.recargaWebPay.TBKMontoRecarga}"/>
	
						<!-- <a href="https://recaudacion.entelpcs.com/cgi-bin/kcc5/tbk_bp_pago.cgi" onclick="document.getElementById('formulario_webpay').submit()" class="btn btn-primary btn-symbols center-btn">Recargar</a> -->	

					</form>
		
				</div>
				
    			<a href="Javascript:submitWebpay();" class="btn btn-primary btn-symbols center-btn">Confirmar</a>				
				
					<script type="text/javascript">
						function submitWebpay(){ 
							var theForm = document.getElementById("formulario_webpay");
							theForm.submit();
						}
					</script>				
				

				<!-- <div class="separador"></div>  -->

				<!-- <a href="#" title="" alt="" class="txt-small txt-link text-center center-block">Condiciones Generales</a>  -->
		</div>
			
			<div class="sc-section">
				<div class="sc-container no-margin-bottom"></div>			
			
				<div class="sc-section">

					<div class="collapse">

						<h3 data-collapse-summary="" aria-expanded="false"><a href="" class="list-item list-collapse collapse-open">Condiciones Generales</a></h3>

						<div class="component-panel content" aria-hidden="true" style="display: none;">				

							<p>Para poder usar la bolsa, debes contar con un saldo m&iacute;nimo de $7 luego de haber comprado la bolsa.</p>

						</div>

					</div>
				</div>			
			
			

			<!-- FOOTER -->
			<footer class="page-footer">
				<p class="txt-small">Copyright Empresa Nacional de Telecomunicaciones S.A. Todos los derechos reservados</p>
			</footer>
		</div><!--/main-->
	
	</div><!--/container-->

		<!-- JS Libraries -->
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
		<script src="${pageContext.servletContext.contextPath}/js/snap.min.js"></script>
		<script src="${pageContext.servletContext.contextPath}/js/jquery.collapse.js"></script>
		<script src="${pageContext.servletContext.contextPath}/js/jquery.idTabs.min.js"></script>
		<script src="${pageContext.servletContext.contextPath}/js/jquery.magnific-popup.min.js"></script>

		<!-- JS Config -->
		<script src="${pageContext.servletContext.contextPath}/js/sidebar-cfg.js"></script>
		<script src="${pageContext.servletContext.contextPath}/js/collapse-cfg.js"></script>
		<script src="${pageContext.servletContext.contextPath}/js/modal-cfg.js"></script>

	</body>

</html>
