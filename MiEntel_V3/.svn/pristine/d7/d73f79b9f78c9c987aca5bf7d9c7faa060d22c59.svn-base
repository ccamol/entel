<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://www.bea.com/servers/portal/tags/netuix/render" prefix="render"%>
<%@ taglib uri="http://www.bea.com/servers/portal/tags/content" prefix="cm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>

<!-- Contenidos -->
<cm:search id="headerAuthMsg" query="idContenido = 'mensajeVersionBeta'" useCache="false"/>
<cm:search id="textoLinkReclamo" query="idContenido = 'idTextoLinkReclamo'" useCache="true"  />

<f:view>

	<c:set var="isAdmin" value="${miEntelBean.AAATitular}" />
	<c:set var="aaa" value="${profile.aaa}" />

    <render:defineObjects/>
    <c:if test="${headerController.userAutenticathed}">	
    <script type="text/javascript">    
	$(document).ready(function(){
		var link = "<render:pageUrl pageLabel='${headerController.pageLabelFormSatisfaccion}'/>";		
		$("a#formSat").attr("href", link.replace("&amp;", "&"));
		var linkCotacto = "<render:pageUrl pageLabel='${headerController.pageLabelContacto}'/>";
		$("a.enlace-contacto-header").attr("href", linkCotacto.replace("&amp;", "&")+"&_nfls=false");
		

		var linkReclamos = "<render:pageUrl pageLabel='${headerController.pageLabelReclamos}'/>";
		$("a.enlace-reclamo-header").attr("href", linkReclamos.replace("&amp;", "&"));
		
	});
	</script>
	</c:if>        
    
    <div class="clearfix versionPrueba"  id="cabecera">
    <!-- LINKS & TABS SUPERIORES -->
        <c:if test="${!headerController.userAutenticathed}">
        	<div class="cabeceraAlternaSinMensaje"></div>
        </c:if>
		<!-- cabecera alterna -->
		<c:if test="${headerController.userAutenticathed}">
	        <%-- <div class="cabeceraAlterna">
	        	<h:form>
		        	<p><cm:getProperty node="${headerAuthMsg[0]}" name="html"/> <h:outputText value="#{headerController.oldVersionText}" /> <h:commandLink actionListener="#{headerController.redirectToMiEntelV2}" value="#{headerController.oldVersionLink}"/></p>
	        	</h:form>    	
	        </div>
	        --%>
	        <div class="cabeceraAlternaSinMensaje"></div>
        </c:if>
        <!-- /cabecera alterna -->
       
      	<div class="cabecera-logo clearfix">
			<c:if test="${headerController.userAutenticathed}">
				<div class="enlace-sesion" style="padding-right:10px">
				    <div style="float:left;">
				 		<div ><a class="enlace-reclamo-header" href="" ><cm:getProperty node="${textoLinkReclamo[0]}" name="html"/></a></div>
				 	</div>
				 	<div style="float:left;">&nbsp;<img src="../framework/skins/mientel/img/cabecera/Cabecera.gif" alt="">&nbsp;</div>
				 	<div style="float:left;">
				 		<div ><a class="enlace-contacto-header" href="" >Contacto</a></div>
				 	</div>				 	
					<div style="float:left;">&nbsp;<img src="../framework/skins/mientel/img/cabecera/Cabecera.gif" alt="">&nbsp;</div>					
				 	<div style="float:left;">
						<h:form>
							<jsp:include page="/token.jsp" flush="true"/>
							<h:commandLink immediate="true" styleClass="cerrar-sesion" actionListener="#{headerController.logout2}">Cerrar sesión</h:commandLink>
						</h:form>
					</div>
				</div>
			</c:if>
    	</div>
    
    <div class="cabecera-top clearfix"></div>
		
    <div class="cabecera-bottom">
        <!-- LOGO -->
        <div class="cabecera-logo clearfix">
            <div class="logo">
                <a title="entel" id="logo-Entel" href="http://www.entel.cl"></a>
            </div>
            <!-- SESION -->
           
            <c:if test="${headerController.userAutenticathed}">
            <div class="enlace-sesion">
            	<c:choose>
					<c:when test="${isAdmin==aaa}">
						<c:if test="${!empty headerController.nombreTitular}">
		                    <span><h:outputText value="#{headerController.nombreTitular}" /></span>
		                </c:if>
					</c:when>
					<c:otherwise>
						<c:if test="${!empty headerController.nombreUsuario}">
		                    <span>Hola <h:outputText value="#{headerController.nombreUsuario}" /></span>
		                </c:if>
		                <c:if test="${!empty headerController.nombreTitular}">
		                    <span>Titular: <h:outputText value="#{headerController.nombreTitular}" /></span>
		                </c:if>
					</c:otherwise>
				</c:choose>                
            </div>
            </c:if>
        </div>       
    </div>
</div>
  <div id="cabecera-menu" class="fake">
      <!-- MENU PERSONAS FAKE -->
      <div class="menu-principal personas">
          <div class="menu-principal-top"></div>
          <div class="menu-principal-main">
              &nbsp;
          </div>
          <div class="menu-principal-bottom"></div>
      </div>
  </div>
</f:view>