<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@page import="com.bea.netuix.servlets.controls.page.BookBackingContext"%>
<%@page import="com.bea.netuix.servlets.controls.page.BookPresentationContext"%>

<f:view>

<%
	BookPresentationContext book = BookPresentationContext.getBookPresentationContext(request);
	//pageContext.getRequest()
%>

		<div class="pie clearfix" id="pie-Entel">
			<!-- LINKS CORPORATIVOS-->
			<div class="pie-izquierda">
				<div class="clearfix">
					<div class="alinear"><a target="_parent" href="http://www.entel.cl/corporativo/">Información Corporativa</a></div>
					<div class="alinear"><a target="_parent" href="http://www.entel.cl/inversionistas/">Inversionistas</a></div>
					<div class="alinear"><a target="_parent" href="#">Trabaja con Nosotros</a></div>
					<div class="alinear"><a target="_parent" href="#">Extranet</a></div>
					<div class="alinear"><a target="_parent" href="#">Derechos del Cliente</a></div>
					<div class="alinear"><a target="_parent" href="#">Cómo ser Cliente</a></div>
		            <div class="alinear"><a target="_parent" href="http://foros.entel.cl/">Foro Entel</a></div>
				</div>
				<p>Copyright Empresa Nacional de Telecomunicaciones S.A. Todos los derechos reservados.</p>
			</div>

		    <!-- /LINKS CORPORATIVOS-->
			<!-- COMPARTIR -->
			<div class="pie-derecha">
				<div class="clearfix">			
					<div class="alinear url" id="boton-Compartir-Pagina">
						<div class="tooltip">
							<div class="tooltip-top"><span></span></div>
							<div class="tooltip-main">Comparte esta URL</div>
							<div class="tooltip-bottom"><span></span></div>
							<div class="tooltip-flecha"></div>
						</div>
					</div>
					<!-- TWITER -->
					<div class="alinear twitter" id="footer-Twitter">					
						<div class="tooltip">
							<div class="tooltip-top"><span></span></div>
							<div class="tooltip-main">Siguenos en Twitter</div>
							<div class="tooltip-bottom"><span></span></div>
							<div class="tooltip-flecha"></div>
						</div>					
					</div>
		            <!-- /TWITER -->
					<!-- FACEBOOK -->
					<div class="alinear facebook" id="footer-Facebook">					
						<div class="tooltip">
							<div class="tooltip-top"><span></span></div>
							<div class="tooltip-main">Facebook</div>
							<div class="tooltip-bottom"><span></span></div>
							<div class="tooltip-flecha"></div>
						</div>						
					</div>
		            <!-- /FACEBOOK -->
					<!-- YOUTUBE -->
					<div class="alinear youtube" id="footer-YouTube">					
						<div class="tooltip">
							<div class="tooltip-top"><span></span></div>
							<div class="tooltip-main">Nuestros Comerciales</div>
							<div class="tooltip-bottom"><span></span></div>
							<div class="tooltip-flecha"></div>
						</div>				
					</div>
		            <!-- /YOUTUBE -->
					<!-- RSS -->
					<div class="rss alinear" id="footer-RSS">				
						<div class="tooltip">
							<div class="tooltip-top"><span></span></div>
							<div class="tooltip-main">RSS Noticias</div>
							<div class="tooltip-bottom"><span></span></div>
							<div class="tooltip-flecha"></div>
						</div>
					</div>
		            <!-- /RSS -->
					<!-- COMPARTIR -->
				</div>
			</div>
			<!-- /COMPARTIR -->
		</div>
</f:view>