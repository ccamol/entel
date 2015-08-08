<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="entel" uri="/WEB-INF/tld/entel-tags.tld"%>
<%@ taglib prefix="r" uri="http://www.bea.com/servers/portal/tags/netuix/render"%>


<f:view beforePhase="#{misbamController.init}">
	<h:panelGroup rendered="#{misbamController.misBAM != '' && misbamController.misBAM != null && misbamController.misBAMSolicitud != 0}">	    
		<div id="bannerMISBAM" style="margin-bottom: 12px; min-height: 50px; margin-left: 14px;">
		    <a href="http://appswls.entel.cl/HomeCautivoBAMPP/plan.jsp?MSISDN=<c:out value='${profile.numeroPcsSeleccionado}' />" target="_blank">
		        <img id="bannerMISBAM" src="http://www.entel.cl/img/mis_bam_mientel.png" alt="MIS BAM" style="cursor: pointer; width: 95%" />    
		    </a>
		</div>		
	</h:panelGroup>
</f:view>


