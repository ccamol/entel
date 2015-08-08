<%@ taglib uri="http://www.bea.com/servers/portal/tags/content" prefix="cm"%>
<cm:search id="pinPuk" query="idContenido = 'pinPuk'" useCache="true"  />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Entel - Equipos</title>

</head>
<body>

<!-- centro -->

<div id="lightbox-centro">
    <br />
    <h1>Pin y Puk</h1>
        
    <cm:getProperty node="${pinPuk[0]}" name="html" />

</div>
<!-- /centro -->	

</body>
</html>