<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.bea.com/servers/portal/tags/netuix/preferences" prefix="preferences" %>
<%@ taglib uri="http://www.bea.com/servers/portal/tags/content" prefix="cm"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="entel" uri="/WEB-INF/tld/entel-tags.tld" %>

<preferences:getPreference name="account" var="node"/>
<preferences:getPreference name="marca"   var="nodomarca"/>
<preferences:getPreference name="marcaEventoBotonEcuenta" var="marcaEcuenta"/>

<cm:search id="params" query="idContenido = '${marcaEcuenta}'" useCache="false"/>

<entel:view name="${marcaEcuenta}" inverse="true">
	<script type="text/javascript">

				var url = window.location.href;									
				url = url.replace('/'+window.location.host+'/',"");
				url = url.replace(window.location.protocol,"");	
												
				 var _gaq = _gaq || []; 
					 _gaq.push(['_setAccount', '${node}']); 
					 _gaq.push(['_setDomainName', 'none']); 
					 _gaq.push(['_setAllowLinker', true]); 
					 _gaq.push(['_trackPageview', '/'+'${nodomarca}'+url]);
					 <cm:getProperty node="${params[0]}" name="html"/>;
				(function() { 
					 var ga = document.createElement('script');
					 ga.type = 'text/javascript'; 
					 ga.async = true; 
					 ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js'; 
					 var s = document.getElementsByTagName('script')[0]; 
					 s.parentNode.insertBefore(ga, s);
				})(); 		
				
	</script>
</entel:view>
<entel:view name="${marcaEcuenta}">
	<script type="text/javascript">

				var url = window.location.href;									
				url = url.replace('/'+window.location.host+'/',"");
				url = url.replace(window.location.protocol,"");	
												
				 var _gaq = _gaq || []; 
					 _gaq.push(['_setAccount', '${node}']); 
					 _gaq.push(['_setDomainName', 'none']); 
					 _gaq.push(['_setAllowLinker', true]); 
					 _gaq.push(['_trackPageview', '/'+'${nodomarca}'+url]);
				(function() { 
					 var ga = document.createElement('script');
					 ga.type = 'text/javascript'; 
					 ga.async = true; 
					 ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js'; 
					 var s = document.getElementsByTagName('script')[0]; 
					 s.parentNode.insertBefore(ga, s);
				})(); 		
				
	</script>
</entel:view>
