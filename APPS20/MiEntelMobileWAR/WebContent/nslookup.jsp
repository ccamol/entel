<%@ page import="com.esa.ponline.appmobile.util.JavaLookup"%>
<%
	String url = request.getParameter("url");
    if ( url == null) {
        out.println("Ingrese una Url a verificar");
    } else {
    	out.println(url);
    	out.println("<br/>");
        out.println(JavaLookup.lookup(url));        
    }
%>