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
		<c:set var="msgCompraNOK" value="${msgCompraNOK}"/>
		<c:set var="msgCompraOK" value="${msgCompraOK}"/>
		<c:set var="bolsaDetalle" value="${bolsaDetalle}"/>	
		<c:set var="publicidad" value="${publicidad}"/>			
		
	</head>
	<body class="">
		<div class="container">
			
			<!-- HEADER
			<header class="section-header clearfix">
				<a href="Javascript: history.back()" id="menu-button" class="top-nav-btn">
					<span class="icon icon-arrow-left"></span>
				</a>

				<h1 class="title bracket">Bolsas_</h1>
			</header>
			-->
			
			
			<!-- HEADER -->
			<header class="section-header clearfix">
				<c:choose>
    				<c:when test="${empty msgCompraOK}">
						<a href="javascript: history.back()" id="menu-button" class="top-nav-btn">    																
    				</c:when>
    				<c:otherwise>
						<a href="${pageContext.servletContext.contextPath}/inicio" id="menu-button" class="top-nav-btn">    				
    				</c:otherwise>
				</c:choose>
					<span class="icon icon-arrow-left"></span>
				</a>
				<div class="logo logo-ql"></div>
				<h1 class="title bracket">Bolsas_</h1>
			</header>			

		<!-- 
			<header class="section-header-sub clearfix">
				<h1 class="title bracket">Detalle Bolsa_</h1>
			</header>
		-->	

			<div class="sc-container">									
				<c:choose>
    				<c:when test="${empty msgCompraOK}">	
       					<h1 class="title title-md bracket">No ha sido posible efectuar tu solicitud</h1>

						<div class="separador no-margin-bottom"></div>	
       					
       					<ul class="list-bullet list-bullet-dot">
							<li>${msgCompraNOK}</li>
						</ul>																
    				</c:when>
    				<c:otherwise>
     					<h1 class="title title-md bracket">Tu solicitud fue enviada con &eacute;xito</h1>

						<div class="separador no-margin-bottom"></div>	
       					
       					<ul class="list-bullet list-bullet-dot">
							<li>Has contratado con &eacute;xito la <b>${bolsaDetalle.nombre}</b>. Quedar&aacute; activa dentro de las pr&oacute;ximas 4 hrs.</li>
							<li>${publicidad}</li>
							<li>Para desactivar una bolsa o renunciar a un plan, deber&aacute;s llamar desde tu m&oacute;vil al 103</li>
						</ul>
    				</c:otherwise>
				</c:choose>	
				<!-- <div class="separador"></div>  -->

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
