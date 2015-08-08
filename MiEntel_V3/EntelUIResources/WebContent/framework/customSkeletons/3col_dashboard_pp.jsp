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
			<c:set var="top"    value="${ph[1]}"/>
			<c:set var="left"    value="${ph[0]}"/>
			<c:set var="center1"   value="${ph[2]}"/>
			<c:set var="center2"   value="${ph[3]}"/>
			<c:set var="center3"   value="${ph[4]}"/>
			<c:set var="right" value="${ph[5]}"/>
			
			<skeleton:control name="div" presentationContext="${flowlayoutpc}" presentationId="bloque-izq">
		    	<skeleton:child presentationContext="${left}"/>
		    </skeleton:control>
		    <skeleton:control name="div" presentationContext="${flowlayoutpc}" presentationId="centro" presentationClass="db-centro">
		    	<skeleton:control name="div" presentationContext="${flowlayoutpc}" presentationId="centro-top" presentationClass="db-centro-top primer-ph">
		    		<skeleton:child presentationContext="${top}"/>
		    	</skeleton:control>
		    	<skeleton:control name="div" presentationContext="${flowlayoutpc}" presentationClass="db-bloque-tablas dash-pp left-side clearfix">
			    	<skeleton:child presentationContext="${center1}"/>
			    </skeleton:control>
			    <skeleton:control name="div" presentationContext="${flowlayoutpc}" presentationClass="db-bloque-tablas dash-pp right-side learfix">
			    	<skeleton:child presentationContext="${center2}"/>
			    </skeleton:control>
			    <skeleton:control name="div" presentationContext="${flowlayoutpc}" presentationClass="db-bloque-tablas dash-pp bottom-side clearfix">
			    	<skeleton:child presentationContext="${center3}"/>
			    </skeleton:control>
			    
		    </skeleton:control>
		    <skeleton:control name="div" presentationContext="${flowlayoutpc}" presentationId="bloque-der">
			    <skeleton:child presentationContext="${right}"/>	
		    </skeleton:control> 
		</skeleton:control>
	</skeleton:context>
</jsp:root>