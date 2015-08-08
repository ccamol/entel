<jsp:root
	version="2.0"
	xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:skeleton="http://www.bea.com/servers/portal/tags/netuix/skeleton">	
	<jsp:directive.page session="false" />
	<jsp:directive.page isELIgnored="false" />
	<skeleton:context type="flowlayoutpc">
		<skeleton:control name="div" presentationContext="${flowlayoutpc}" presentationId="contenido" presentationClass="clearfix">
			<c:set var="ph" value="${flowlayoutpc.placeholders}"/>
			<c:set var="left"    value="${ph[0]}"/>		
			<c:set var="right" value="${ph[1]}"/>
			
			<skeleton:control name="div" presentationContext="${flowlayoutpc}" presentationId="bloque-izq">
		    	<skeleton:child presentationContext="${left}"/>
		    </skeleton:control>		    
		    <skeleton:control name="div" presentationContext="${flowlayoutpc}" presentationId="bloque-der">
			    <skeleton:child presentationContext="${right}"/>	
		    </skeleton:control> 
		</skeleton:control>
	</skeleton:context>
</jsp:root>