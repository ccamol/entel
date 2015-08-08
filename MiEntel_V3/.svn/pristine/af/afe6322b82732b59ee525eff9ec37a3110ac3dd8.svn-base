<!--
    The flowlayout skeleton file renderes a HTML DIV element for the layout. Each placeholder is rendered as
    a contained HTML DIV element. Placeholder contents are rendered via the explicit use of the child tag in
    the context of each placeholder DIV element.
-->
<jsp:root version="2.0" 
    xmlns:jsp="http://java.sun.com/JSP/Page"
    xmlns:c="http://java.sun.com/jsp/jstl/core"
    xmlns:skeleton="http://www.bea.com/servers/portal/tags/netuix/skeleton"
>
    <jsp:directive.page session="false" />
    <jsp:directive.page isELIgnored="false" />
    <skeleton:context type="flowlayoutpc">
            <c:forEach items="${flowlayoutpc.placeholders}" var="cell" varStatus="status">
                <skeleton:child presentationContext="${cell}"/>
            </c:forEach>
    </skeleton:context>
</jsp:root>
