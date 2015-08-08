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
		<c:set var="movil" value="${movil}" />
		<c:set var="codCompra" value="${codCompra}" />
		<c:set var="recController" value="${recController}" />
		<c:set var="montosWebpay" value="${montosWebpay}" />	
		<c:set var="sinTraficoMsg" value="${sinTraficoMsg}" />	
		<c:set var="recFailMsg" value="${recFailMsg}" />
		<c:set var="recSucMsg" value="${recSucMsg}" />
		<c:set var="saldoInsuficiente" value="${saldoInsuficiente}" />
	
	</head>
	<body class="">
		<div class="container">
			<!-- HEADER -->
			<header class="section-header clearfix">
				<a href="javascript: history.back()" id="menu-button" class="top-nav-btn">
					<span class="icon icon-arrow-left"></span>
				</a>
				<div class="logo logo-ql"></div>
				<h1 class="title bracket">Bolsas de Datos_</h1>
			</header>			
			
			<!-- HEADER
			<header class="section-header clearfix">
				<a href="javascript: history.back()" id="menu-button" class="top-nav-btn">
					<span class="icon icon-arrow-left"></span>
				</a>
				<h1 class="title bracket">Bolsas_</h1>
			</header>
			-->

			<div class="section">
				
				<div class="sc-section top">
					<ul>
						<c:forEach items="${bolsasCliente}" var="bolsa">
						<li>
						<c:choose>
    						<c:when test="${bolsa.saldoInsuficiente=='saldoInsuficiente'}">
    							<div class="list-item list-option list-icon no-arrow">
									<span class="icon icon-bolsas-datos"></span>
									<b>${bolsa.nombre}</b><br>
									<span class="txt-color"><b>${saldoInsuficiente}</b></span>							
									<span class="list-secondary-txt"><b>$${bolsa.precio}</b></span>    							
    							</div>						
    						</c:when>
    						<c:otherwise>						
								<a href="${pageContext.servletContext.contextPath}/inicio/compra?pcbcarta=${bolsa.codigo}" 
									class="list-item list-option list-icon" id="${bolsa.codigo}">
									<span class="icon icon-bolsas-datos"></span>
									<b>${bolsa.nombre}</b>
									<p class="txt-small">${bolsa.descripcionComercial}</p>
									<span class="list-secondary-txt"><b>$${bolsa.precio}</b></span>
								</a>																	
    						</c:otherwise>
						</c:choose>
						</li>
						</c:forEach>
					</ul>
				</div>
				
				
				<ul class="planes-list content"></ul>

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
			</div><!--/sc-section top-->				
		</div><!--/container-->
		<!-- JS Libraries -->
		<script src="${pageContext.servletContext.contextPath}/js/jquery-2.1.3.min.js"></script>
		<script src="${pageContext.servletContext.contextPath}/js/jquery.collapse.js"></script>
		<script src="${pageContext.servletContext.contextPath}/js/jquery.idTabs.min.js"></script>
		
		<!-- JS Config -->
		<script src="${pageContext.servletContext.contextPath}/js/collapse-cfg.js"></script>
		<script src="${pageContext.servletContext.contextPath}/js/swipe.js"></script>
		
	</body>
</html>