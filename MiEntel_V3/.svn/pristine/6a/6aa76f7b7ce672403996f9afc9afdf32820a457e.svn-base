<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="entel" uri="/WEB-INF/tld/entel-tags.tld"%>
<%@ taglib prefix="r" uri="http://www.bea.com/servers/portal/tags/netuix/render"%>
<%@ taglib prefix="pref" uri="http://www.bea.com/servers/portal/tags/netuix/preferences"%>
<%@ taglib prefix="cm" uri="http://www.bea.com/servers/portal/tags/content" %>

<pref:getPreference name="pageFacturaElectronica" var="pageFacturaElectronica"/>
<cm:search id="htmlBannerFacturaElectronica" query="idContenido = 'htmlBannerFacturaElectronica'" useCache="true" />

<f:view beforePhase="#{facturacionElectronicaController.init}">
	<entel:view name="mostrarBanner">
		<h:panelGroup rendered="#{!facturacionElectronicaController.estadoInscrito}">
			<a id="linkBannerFacturaElectronica" href="<r:pageUrl pageLabel='${pageFacturaElectronica}'></r:pageUrl>"
				onclick="mxTracker._trackPromo('Banner','FE');">
				<cm:getProperty node="${htmlBannerFacturaElectronica[0]}" name="html" />
			</a>
		</h:panelGroup> 		
	</entel:view>
</f:view>
