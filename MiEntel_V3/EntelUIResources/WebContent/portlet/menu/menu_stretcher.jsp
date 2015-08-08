<%--
  - Author(s): david.roig@i2b.cl
  - Date: 7 Febrero 2011
  - Description: JSP del portlet (PortletMenuStretcher) que renderiza un stretcher desde algun punto 
    del menu dado el numero de saltos en el portlet preference 
--%>
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ page import="com.bea.netuix.servlets.controls.page.BookPresentationContext" %>
<%@ taglib uri="http://www.bea.com/servers/portal/tags/netuix/preferences" prefix="pref" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.bea.com/servers/portal/tags/netuix/render" prefix="render" %>
<%@page import="com.epcs.portlet.menu.TreeMenuHelper"%>

<pref:getPreference name="profundidad" var="saltos" defaultValue="3" />
<%!
public boolean tieneBooks(java.util.List<Object> lista) {
	for (Object item : lista) {
		if (item instanceof BookPresentationContext) {
			return true;
		}
	}
	return false;
}
%>
<%
	int saltosInt = Integer.parseInt(saltos);
	BookPresentationContext books = 
		TreeMenuHelper.getBookTree((HttpServletRequest)pageContext.getRequest(), saltosInt);
%>

<%--
	indexBookFijo: esta variable se utiliza si se quiere dejar un book siempre abierto 
	de forma permanente, independiente de si esta activo o no.
--%>
<c:set var="indexBookFijo" value="0" />

<c:set var="hijos" value="<%= books.getEntitledPagePresentationContexts() %>" />

<div id="cabecera_mientel">Mi Entel</div>
<c:if test="${saltos < 2}">
<div id="menu_vertical" class="despliegues_categoria azul">
	<div class="stretch_no_amin abierto">
	    <div class="stretch_top"></div>
	    <div class="stretch_main"><h3><a id="link_dashboard" href="${pageContext.request.requestURI}">P&aacute;gina de inicio</a></h3></div>
		<div class="stretch_bottom"></div>
	</div>

	<div class="stretch abierto"> 
	    <div class="stretch_top"></div>
	    <div class="stretch_main"><h3><%= books.getTitle() %></h3></div>
		<div class="stretch_bottom"></div>
	</div>
	<div class="contenido_stretcher abierto">
    	<div class="cuerpo_categoria">
    		<ul class="segundo">
    		<c:forEach var="item" items="${hijos}">  		
	    		<c:choose>
			 		<c:when test="${item.displayed}">
			 			<c:set var="seleccionado" value="seleccionado" />
			 			<c:set var="bloque" value="block" />
			 		</c:when>
			 		<c:otherwise>
			 			<c:set var="seleccionado" value="" />
			 			<c:set var="bloque" value="none" />
			 		</c:otherwise>
		 		</c:choose>
				<c:if test="${item.class.simpleName == 'PagePresentationContext'}">
					<!-- (Esa es la actual)  ${item.visible} -->				
					<c:if test="${item.visible}">						
					 	<render:pageUrl pageLabel="${item.label}" var="url"/>	
						<li class="${seleccionado}"><a href="${url}">${item.title}</a></li>
					</c:if>		
				</c:if>
			</c:forEach>
    		</ul>
    	</div>
    </div>	
</div>
</c:if>
<c:if test="${saltos >= 2}">
<div id="menu_vertical" class="despliegues_categoria azul">

	<div class="stretch_no_amin abierto">
	    <div class="stretch_top"></div>
	    <div class="stretch_main"><h3><a id="link_dashboard" href="${pageContext.request.requestURI}">P&aacute;gina de inicio</a></h3></div>
		<div class="stretch_bottom"></div>
	</div>
	<c:forEach var="item" items="${hijos}" varStatus="loop">
	<c:set var="fijo" value="" /><c:if test="${loop.index==indexBookFijo}"><c:set var="fijo" value=" fijo menu_abierto" /></c:if>
	
	<!-- Si es un book, es parte del menu(Asi excluimos la home o landings) -->
	<c:if test="${item.class.simpleName == 'BookPresentationContext'}">
	 	<!-- Esta activo el menu  -->
	 	<c:choose>
	 		<c:when test="${item.displayed}">
	 			<c:set var="abierto" value="abierto" />
	 		</c:when>
	 		<c:otherwise>
	 			<c:set var="abierto" value="" />
	 		</c:otherwise>
 		</c:choose>
		<div class="stretch ${abierto}${fijo}"> 
		    <div class="stretch_top"></div>
		    <div class="stretch_main"><h3>${item.title}</h3></div>
			<div class="stretch_bottom"></div>
		</div>
		<div class="contenido_stretcher ${abierto}${fijo}">
	    	<div class="cuerpo_categoria">
	    		<ul class="segundo">
					<c:set var="itemChilds" value="${item.entitledPagePresentationContexts}" />
					<c:forEach var="item" items="${itemChilds}">
						<!-- Si es pagina, la agregamos al menu -->
						<!-- Si NO es pagina, es un book, obtenemos los hijos -->
						<!-- Esta activo el menu  -->
						<c:set var="seleccionado" value="" />
					 	<c:choose>
					 		<c:when test="${item.displayed}">
					 			<c:set var="seleccionado" value="seleccionado" />
					 			<c:set var="bloque" value="block" />
					 		</c:when>
					 		<c:when test="${loop.index==indexBookFijo}"><c:set var="bloque" value="block" /></c:when>
					 		<c:otherwise>
					 			<c:set var="bloque" value="none" />
					 		</c:otherwise>
				 		</c:choose>
					 	<c:choose>													
							<c:when test="${item.class.simpleName == 'PagePresentationContext'}">
								<!-- (Esa es la actual)  ${item.visible} -->				
								<c:if test="${item.visible}">
								 	<render:pageUrl pageLabel="${item.label}" var="url"/>	
									<li class="${seleccionado}"><a href="${url}">${item.title}</a></li>
								</c:if>		
							</c:when>
							<c:otherwise>
								<li class="submenu_cabecera ${seleccionado} as"><a href="#">${item.title}</a></li>
								<li class="submenu_contenido ULContainer" style="display: ${bloque};">							
									<c:set var="itemChilds" value="${item.entitledPagePresentationContexts}" />
									<ul class="tercero">
										<c:forEach var="item" items="${itemChilds}">		
											<c:if test="${item.visible}">
												<render:pageUrl pageLabel="${item.label}" var="url"/>	
												<!-- Esta activo el menu  -->
											 	<c:choose>
											 		<c:when test="${item.displayed}">
											 			<li class="seleccionado"><a href="${url}">${item.title}</a></li>
											 		</c:when>
											 		<c:otherwise>
											 			<li><a href="${url}">${item.title}</a></li>
											 		</c:otherwise>
										 		</c:choose>
												
											</c:if>				
										</c:forEach>				
									</ul>
								</li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</ul>
			</div>
		</div>
	</c:if>
	</c:forEach>
</div>
</c:if>
<div class="cierre_menu"></div>