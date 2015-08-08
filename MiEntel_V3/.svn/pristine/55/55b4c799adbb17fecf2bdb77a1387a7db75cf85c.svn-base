<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="entel" uri="/WEB-INF/tld/entel-tags.tld"%>
<%@ taglib prefix="r" uri="http://www.bea.com/servers/portal/tags/netuix/render"%>
<%@ taglib prefix="pref" uri="http://www.bea.com/servers/portal/tags/netuix/preferences"%>
<%@ taglib prefix="cm" uri="http://www.bea.com/servers/portal/tags/content"%>

<cm:search id="imgBannerBolsasTrafico" query="idContenido = 'imgBannerBolsasTrafico'" useCache="true"  />
<pref:getPreference name="marcaPageBolsas" var="marcaPageBolsas"/>

<entel:view name="mostrarBanner">
	<a id="linkBannerBolsasTrafico" href="${marcaPageBolsas}">
		<!-- <img src="http://www.entel.cl/img/bolsas-trafico.jpg" width="172" height="307" /> -->
		<cm:getProperty node='${imgBannerBolsasTrafico[0]}' name='html' />
	</a> 		
</entel:view>