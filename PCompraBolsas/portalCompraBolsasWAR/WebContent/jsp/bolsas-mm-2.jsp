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
   		<link href="${pageContext.servletContext.contextPath}/css/jAlert-v2.css" rel="stylesheet" />		
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
		<c:set var="bolsasActivas" value="${bolsasActivas}" />
		<c:set var="urlWebpay" value="${urlWebpay}" />				
	
	</head>
	<body class="">
		<div class="container">
			
			<!-- HEADER 
			<header class="section-header clearfix">
				<a href="" id="menu-button" class="top-nav-btn">
					<span class="logo"></span>
				</a>
			</header>
			-->

			<!-- HEADER -->
			<header class="section-header clearfix">
				<div class="logo logo-ql"></div>
				<h1 class="title bracket">Mis Bolsas_</h1>
			</header>			
					
<!-- 
			<div class="alerts-block">

				<div class="callout bg-warning ${empty sinTraficoMsg ? 'hidden' : ''}">
					<span class="icon icon-warning"></span>
					<span class="bracket-msg">${sinTraficoMsg}</span>
				</div>

				<div class="callout bg-warning ${empty recFailMsg ? 'hidden' : ''}">
					<span class="icon icon-error"></span>
					<span class="bracket-msg">${recFailMsg}</span>
				</div>

				<div class="callout bg-info ${empty recSucMsg ? 'hidden' : ''}">
					<span class="icon icon-success"></span>
					<span class="bracket-msg">${recSucMsg}</span>
				</div>

			</div>
-->			
			
			<div class="sc-container">
				<!-- <h1 class="title title-md bracket"></h1>  -->
				<div class="section-title">
                   	<h1 class="title  title-md bracket" align="left">Saldo</h1>
                </div>				

				<ul class="striped">
					<li class="term-list term-block">Saldo <b class="term-value">
						$<c:out value="${empty movil ? '0' : movil.saldo}"/></b>
						<!-- 
						<ul>
							<li class="term-list term-block"><strong>Aumenta el monto de tu recarga</strong> <b class="term-value">
								<select class="form-control" id="amount" form="amountWP">
									<c:forEach items="${montosWebpay}" var="montosWebpay">
										<option value="${montosWebpay.doubleMonto}">${montosWebpay.descripcion}</option>
									</c:forEach>
								</select></b>
							</li>							
						</ul>				
						-->		
					</li>
				</ul>
				<ul class="striped">
					<li class="term-list term-block">Fecha vencimiento <b class="term-value">
						<c:out value="${empty movil ? '0' : movil.fechaDesactivacion}"/></b>
					</li>
				</ul>

				<div class="form-group"></div>
				
				<div align="center">
					<form action="${pageContext.servletContext.contextPath}/inicio/recarga" method="GET" id="amountWP">
						<a href="${urlWebpay}" class="btn btn-primary btn-symbols center-btn" target="_blank" id="recargar">Recargar</a>
					</form>
				</div>
				
				
						<c:choose>
    						<c:when test="${empty bolsasActivas}">
								<div class="section-title">
									<h1 class="title title-md bracket" align="left">No tienes Bolsa Activas</h1>
								</div>							
    						</c:when>
    						<c:otherwise>						
								<div class="section-title">
                   					<h1 class="title title-md bracket" align="left">Bolsa Activas</h1>
               					</div>
						
								<div>
									<table class="table striped">
										<thead>
											<tr>
												<th>Nombre</th>
												<th>Saldo</th>
												<th>Expiraci&oacute;n</th>
											</tr>
										</thead>
										<tbody>
											<!-- <#list bolsasActivas as bundle>  -->
											<c:forEach items="${bolsasActivasCliente}" var="bolsaActivas">
											<tr>
												<td>${bolsaActivas.nombreBolsa}</td>
												<td>${bolsaActivas.saldoUnidad}</td>
												<td>${bolsaActivas.fechaExp}</td>
											</tr>
											</c:forEach>
											<!-- </#list>  -->
										</tbody>		
									</table>
								</div>																		
    						</c:otherwise>
						</c:choose>							
			</div>

            <section class="section">
            	<a href="${pageContext.servletContext.contextPath}/inicio/bolsas" class="btn btn-primary btn-lg btn-symbols btn-block">Ver bolsas disponibles</a>
            </section>   

			<div class="section">
				<!-- FOOTER -->
				<footer class="page-footer">
					<p class="txt-small">Copyright Empresa Nacional de Telecomunicaciones S.A. Todos los derechos reservados</p>
				</footer>
			</div><!--/sc-section top-->				
		</div><!--/container-->
		
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

		
<script type="text/javascript">
function submitAmountWebpay(){
	var monto = $("#amount").find(":selected").val();
			
	if (monto > 0) {
		var form = document.getElementById("amountWP");
		var params = [
			{
				name: "monto",
				value: monto
			}
		];
    	$.each(params, function(i,param){
        	$('<input />').attr('type', 'hidden')
           		.attr('name', param.name)
            	.attr('value', param.value)
            	.appendTo("#amountWP");
    	});				
			form.submit();
	} else {
		alert("Seleccione monto");
		return;	
	}
}
</script>	



	</body>
</html>