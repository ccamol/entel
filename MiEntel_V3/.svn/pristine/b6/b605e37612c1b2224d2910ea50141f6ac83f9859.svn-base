<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="pref" uri="http://www.bea.com/servers/portal/tags/netuix/preferences"%>

<pref:getPreference name="account" var="account"/>
<pref:getPreference name="categoria" var="categoria"/>
<pref:getPreference name="accion" var="accion"/>
<pref:getPreference name="label" var="label"/>
<pref:getPreference name="linkID" var="linkID"/>

<script type="text/javascript">
	var url = window.location.href;									
	url = url.replace('/' + window.location.host + '/', "");
	url = url.replace(window.location.protocol, "");	
									
	var _gaq = _gaq || [];
		_gaq.push(['_setAccount', '${account}']); 
		_gaq.push(['_setDomainName', 'entel.cl']);
		_gaq.push(['_setAllowLinker', true]);
		_gaq.push(['_trackPageview']);
		_gaq.push(['_trackEvent', '${categoria}', '${accion}', '${label}']);		 		  
	(function() { 
		 var ga = document.createElement('script');
		 ga.type = 'text/javascript'; 
		 ga.async = true; 
		 ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js'; 
		 var s = document.getElementsByTagName('script')[0]; 
		 s.parentNode.insertBefore(ga, s);
	})();

	$(document).ready(function() {
		$('#${linkID}').click(function() {
			_gaq.push(['_trackEvent', '${categoria}', '${accion}', '${label}']);
		});		
	});
</script>