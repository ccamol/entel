<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@taglib uri="http://www.bea.com/servers/portal/tags/content" prefix="cm"%>
<%@ taglib uri="http://www.bea.com/servers/portal/tags/netuix/preferences" prefix="pref" %>
<pref:getPreference name="idContenido" var="idContenido" defaultValue="" />
<c:set var="query">idContenido = '${idContenido}'</c:set>
<cm:search id="nodo" query="${query}" useCache="false"  />
<cm:getProperty node="${nodo[0]}" name="html" />