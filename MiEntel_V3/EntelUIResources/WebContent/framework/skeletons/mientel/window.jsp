<!--
    The window skeleton file renders a HTML DIV element for the window.  This DIV element contains a titlebar
    and window content.  The window content is contained within an additional HTML DIV element.  The window content
    and its containing DIV is rendered only if the window is not minimized.
-->
<jsp:root version="2.0" 
    xmlns:jsp="http://java.sun.com/JSP/Page"
    xmlns:c="http://java.sun.com/jsp/jstl/core"
    xmlns:skeleton="http://www.bea.com/servers/portal/tags/netuix/skeleton"
>
    <jsp:directive.page session="false" />
    <jsp:directive.page isELIgnored="false" />
    <skeleton:context type="windowpc">
        <skeleton:children/>
    </skeleton:context>
</jsp:root>
