<!--
    The singlelevelmenu skeleton file renders an HTML TABLE element for the menu. This TABLE contains two
    TD cells in a single TR row corresponding to the menu items and menu buttons. Menu item rendering is
    accomplished by delegating to a common helper file.
-->
<jsp:root version="2.0"
    xmlns:jsp="http://java.sun.com/JSP/Page"
    xmlns:c="http://java.sun.com/jsp/jstl/core"
    xmlns:skeleton="http://www.bea.com/servers/portal/tags/netuix/skeleton"
    xmlns:render="http://www.bea.com/servers/portal/tags/netuix/render"
>
    <jsp:directive.page session="false" />
    <jsp:directive.page isELIgnored="false" />
    <render:defineObjects />    
    <skeleton:context type="menupc">
		<div id="cabecera_menu">
			<!-- MENU PERSONAS -->
			<div class="menu_principal personas">
				<div class="menu_principal_top"></div>
				<div class="menu_principal_main">
     
				</div>
				<div class="menu_principal_bottom"></div>
			</div>
		</div>
    </skeleton:context>
</jsp:root>
