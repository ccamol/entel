<%@ taglib prefix="cm" uri="http://www.bea.com/servers/portal/tags/content"%>

<!-- Contenidos -->
<cm:search id="introCondicionesMcAfee" query="idContenido = 'introCondicionesMcAfee'" useCache="false"/>
<cm:search id="condiciones12McAfee" query="idContenido = 'condiciones12McAfee'" useCache="false"/>
<cm:search id="condiciones34McAfee" query="idContenido = 'condiciones34McAfee'" useCache="false"/>
<cm:search id="condiciones56McAfee" query="idContenido = 'condiciones56McAfee'" useCache="false"/>
<cm:search id="condiciones78McAfee" query="idContenido = 'condiciones78McAfee'" useCache="false"/>
<cm:search id="condiciones9McAfee" query="idContenido = 'condiciones9McAfee'" useCache="false"/>
<cm:search id="condicionesFinalMcAfee" query="idContenido = 'condicionesFinalMcAfee'" useCache="false"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Entel - McAfee</title>

</head>
<body>

<!-- centro -->

<div id="lightbox-centro" class="mcafee">
    <br />
    
	<h1 class="cabecera"><cm:getProperty node="${introCondicionesMcAfee[0]}" name="titulo" /></h1>
	
	<cm:getProperty node="${introCondicionesMcAfee[0]}" name="html" />
	
	<div style="padding: 0 0 0 20px;">
		<cm:getProperty node="${condiciones12McAfee[0]}" name="html" />
		<cm:getProperty node="${condiciones34McAfee[0]}" name="html" />
		<cm:getProperty node="${condiciones56McAfee[0]}" name="html" />
		<cm:getProperty node="${condiciones78McAfee[0]}" name="html" />
		<cm:getProperty node="${condiciones9McAfee[0]}" name="html" />
	</div>
	
	<cm:getProperty node="${condicionesFinalMcAfee[0]}" name="html" />

</div>
<!-- /centro -->	

</body>
</html>