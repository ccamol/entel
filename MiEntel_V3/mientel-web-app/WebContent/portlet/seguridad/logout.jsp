<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setDateHeader("Expires", 0); //prevents caching at the proxy server
response.setHeader("Cache-Control", "private"); // HTTP 1.1 
response.setHeader("Cache-Control", "no-store"); // HTTP 1.1 
response.setHeader("Cache-Control", "max-stale=0"); // HTTP 1.1

response.setStatus(302);
response.setHeader( "Location", "http://www.entel.cl" );
response.setHeader( "Connection", "close" );
 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="refresh" content="1;url=http://www.entel.cl"/>
</head>
<body>
</body>
</html>