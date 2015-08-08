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

	<title>Detalle bolsa - Entel</title>

    <!-- CSS -->
	<link href="${pageContext.servletContext.contextPath}/css/reset.css" rel="stylesheet" />
	<link href="${pageContext.servletContext.contextPath}/css/main.css" rel="stylesheet" />
   	<link href="${pageContext.servletContext.contextPath}/css/jAlert-v2.css" rel="stylesheet" />
   	
   	<script type="text/javascript"></script>  
    <style>
        /* Para dejar el No con color naranjo :( */
	    .switch label {
	        background-color: #ff6601;
	    }
    </style>
	<!-- JS -->
	<script src="${pageContext.servletContext.contextPath}/js/modernizr.custom.js"></script>
	<c:set var="bolsaDetalle" value="${bolsaDetalle}" />
	<c:set var="sinSaldoMsg" value="${sinSaldoMsg}" />
	
</head>
		<body class="">
		<div class="container">
		
			<!-- HEADER -->
			<header class="section-header clearfix">
				<a href="javascript: history.back()" id="menu-button" class="top-nav-btn">
					<span class="icon icon-arrow-left"></span>
				</a>
				<div class="logo logo-ql"></div>
				<h1 class="title bracket">Bolsas_</h1>
			</header>		
			
		<!-- HEADER
			<header class="section-header clearfix">
				<a href="javascript: history.back()" id="menu-button" class="top-nav-btn">
					<span class="icon icon-arrow-left"></span>
				</a>
				
				<h1 class="title bracket">Bolsas_</h1>
			</header>
			-->	

			<!-- MAIN -->
			<div id="main">			

<!-- 
			<header class="section-header-sub clearfix">
				<h1 class="title bracket">Confirmar selecci&oacute;n_</h1>
			</header>
-->			


				<div class="alerts-block">
					<div class="callout bg-warning ${empty sinSaldoMsg ? 'hidden' : ''}">
						<span class="icon icon-warning"></span>
						<span class="bracket-msg">${sinSaldoMsg}</span>
					</div>				
				</div>

					
					<section class="section">

						<h3 class="title  title-md bracket">${bolsaDetalle.nombre}</sma++ll></h3>

						<!-- TERM LIST -->
						<ul class="striped">

							<li class="term-list term-block">Valor <b class="term-value">${bolsaDetalle.precio}</b></li>

							<li class="term-list term-block">Descripcion <b class="term-value">${bolsaDetalle.descripcionComercial}</b></li>
							
						</ul>						

					</section>
						

<c:choose>
    <c:when test="${sinSaldoMsg != ''}">
   					<section class="section">
						<a href="${pageContext.servletContext.contextPath}/inicio" class="btn btn-default btn-lg btn-symbols btn-block">Volver</a>
					</section>
    </c:when>    
    <c:otherwise>
	                <div class="section" style="text-align:center;margin-bottom: 10px;">
                        <div id="divConfirmaBolsa">
                            <span class="switch">
                                Comprar esta bolsa                                    
                                <input id="confirmaCompra" type="checkbox" name="confirmaCompra"/>
                                <label for="switch-remember"></label>
                            </span>     
                        </div>
                    </div> 
						
					<section class="section">
					
						<form action="${pageContext.servletContext.contextPath}/inicio/compra?confirmar=${bolsaDetalle.codigo}" class="btn btn-block" method="POST" id="bolsasForm">
							<a href="Javascript: submitConfirmar();" class="btn btn-secondary btn-lg btn-symbols btn-block" id="btn_confirmar">Confirmar bolsa</a>
						</form>
						
						<!-- <a href="${pageContext.servletContext.contextPath}/inicio" class="btn btn-default btn-lg btn-symbols btn-block">Volver</a> -->
						<a href="javascript: history.back()" class="btn btn-default btn-lg btn-symbols btn-block">Volver</a>
					</section>
    </c:otherwise>
</c:choose>					
						
						

					
					
					<!-- FOOTER -->
					<footer class="page-footer">

						<p class="txt-small">Copyright Empresa Nacional de Telecomunicaciones S.A. Todos los derechos reservados</p>

					</footer>


			</div><!--/main-->


			
		</div><!--/container-->
		

		<script type="text/javascript">
			function submitConfirmar(){
				var form = document.getElementById("bolsasForm");
				form.submit();
			}
		</script>	

		<!-- JS Libraries -->
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
		<script src="${pageContext.servletContext.contextPath}/js/jquery.collapse.js"></script>
		<script src="${pageContext.servletContext.contextPath}/js/jquery.idTabs.min.js"></script>
		<script src="${pageContext.servletContext.contextPath}/js/jAlert-v2.js"></script>
		<script src="${pageContext.servletContext.contextPath}/js/jquery.magnific-popup.min.js"></script>

		<!-- JS Config -->
		<script src="${pageContext.servletContext.contextPath}/js/collapse-cfg.js"></script>
		<script src="${pageContext.servletContext.contextPath}/js/modal-cfg.js"></script>
		<script src="${pageContext.servletContext.contextPath}/js/bundle.detail.js"></script>
	</body>
</html>
