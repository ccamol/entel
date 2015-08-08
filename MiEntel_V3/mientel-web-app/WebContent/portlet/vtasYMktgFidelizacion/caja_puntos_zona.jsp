<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://www.bea.com/servers/portal/tags/content" prefix="cm"%>
<%@ taglib uri="http://www.bea.com/servers/portal/tags/netuix/preferences" prefix="pref" %>
<%@ taglib prefix="r"  uri="http://www.bea.com/servers/portal/tags/netuix/render"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<pref:getPreference name="pageLabelHistorial" var="pageLabelHistorial"/>
<pref:getPreference name="pageLabelCanje" var="pageLabelCanje"/>
<pref:getPreference name="tipo_caja"  var="tipo_caja"/>
<pref:getPreference name="consultarPuntosPromo"  var="consultarPuntosPromo" defaultValue="0"/>
<pref:getPreference name="idContenido" var="id_contenido" defaultValue="" />
<cm:search id="nodo" query="idContenido = '${id_contenido}'" useCache="false"  />
<f:view>
<c:choose>
  <c:when test="${tipo_caja == 0}">	
	<script type="text/javascript">
	//<![CDATA[
	     $(document).ready(function() {
			 
        	 var url='<%=request.getContextPath()%>/portlet/vtasYMktgFidelizacion/cajaPuntosZonaJson.faces';
        	
        	 $.ajax({
	            type: 'POST',
	            url: url,	            
	            dataType: 'json',
	            data: {consultarPuntosPromo : '${consultarPuntosPromo}'},
	            success: function (resp){		            	
	    	        if(resp.estado == 'Ok'){
	    	        	if(resp.respuesta.estadoPromocion == 'OK'){
	    	        		$('#puntos_promocion').html(resp.respuesta.puntosPromocionFormated);
	    	        		$('#puntos_acumulados2').html(resp.respuesta.saldoPuntosFormated);
	    	        		$('#fecha_venc').html(resp.respuesta.fechaVencPromoFormated);
	    	        		$('#caja_puntos_zona').hide();
	    	        		$('#caja_puntos_promocion').show();	    	        		
	    	        	}else{
	    	        		$('#puntos_acumulados').html(resp.respuesta.saldoPuntosFormated);
	    	        		$('#caja_puntos_promocion').hide();
	    	        		$('#caja_puntos_zona').show();
		    	        }
	    	        }else{
	    	        	$('#caja_puntos_zona').hide();
		    	    }      
	            }
	        });
	     });
	//]]>  
	</script>		
	<div id="caja_puntos_zona" style="display:none">	
		<div class="cajalinks">	
			<table width="175" height="178" border="0" cellspacing="0" cellpadding="0" style="margin-left: -2px;">
			  <tr>
			    <td width="175" height="61" align="left" valign="top"><img src="<%=request.getContextPath()%>/portlet/vtasYMktgFidelizacion/img/calgua_01_01.jpg" width="175" height="61" hspace="0" vspace="0" border="0" style="display:block; vertical-align:bottom;"/></td>
			  </tr>
			  <tr>
			    <td height="57" align="left" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
			      <tr>
			        <td width="24" height="57" align="left" valign="top" border="0" cellspacing="0" cellpadding="0"><img src="<%=request.getContextPath()%>/portlet/vtasYMktgFidelizacion/img/calgua_01_14.jpg" width="24" height="57" /></td>
			        <td width="126" height="57" align="center" valign="middle" bgcolor="#FFFFFF" border="0" cellspacing="0" cellpadding="0"><font style="font-family:'Trebuchet MS', Arial, Helvetica, sans-serif; font-weight:normal; color:#Ff6702; font-size:29px;"><font style="font-family:'Trebuchet MS', Arial, Helvetica, sans-serif; font-weight:normal; color:#Ff6702; font-size:28px; line-height:18px;"><span id="puntos_acumulados"></span><br>
						<span style="font-size:11px;">PUNTOS ACUMULADOS</span></font></td>
			        <td width="25" height="57" align="left" valign="top" border="0" cellspacing="0" cellpadding="0"><img src="<%=request.getContextPath()%>/portlet/vtasYMktgFidelizacion/img/calgua_01_15.jpg" width="25" height="57" /></td>
			      </tr>
			    </table></td>
			  </tr>
			  <tr>
			    <td width="175" height="17" align="left" valign="top" border="0" cellspacing="0" cellpadding="0"><table width="100%" border="0" cellspacing="0" cellpadding="0">
			      <tr>
			        <td width="39" height="17" align="left" valign="top" border="0" cellspacing="0" cellpadding="0"><img src="<%=request.getContextPath()%>/portlet/vtasYMktgFidelizacion/img/calgua_01_06.jpg" width="39" height="17" hspace="0" vspace="0" border="0" style="display:block; vertical-align:bottom;"/></td>
			        <td width="95" height="17" align="left" valign="top" border="0" cellspacing="0" cellpadding="0"><a href="<r:pageUrl pageLabel='${pageLabelHistorial}'></r:pageUrl>"><img src="<%=request.getContextPath()%>/portlet/vtasYMktgFidelizacion/img/calgua_01_07.jpg" width="95" height="17" border="0" hspace="0" vspace="0"  style="display:block; vertical-align:bottom;"/></a></td>
			        <td width="41" height="17" align="left" valign="top" border="0" cellspacing="0" cellpadding="0"><img src="<%=request.getContextPath()%>/portlet/vtasYMktgFidelizacion/img/calgua_01_08.jpg" width="41" height="17" hspace="0" vspace="0" border="0" style="display:block; vertical-align:bottom;"/></td>
			      </tr>
			    </table></td>
			  </tr>
			  <tr>
			    <td align="left" valign="top" border="0" cellspacing="0" cellpadding="0"><img src="<%=request.getContextPath()%>/portlet/vtasYMktgFidelizacion/img/calgua_01_09.jpg" width="175" height="6" hspace="0" vspace="0" border="0" style="display:block; vertical-align:bottom;"/></td>
			  </tr>
			  <tr>
			    <td height="17" align="left" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
			      <tr>
			        <td width="39" height="17" align="left" valign="top" border="0" cellspacing="0" cellpadding="0"><img src="<%=request.getContextPath()%>/portlet/vtasYMktgFidelizacion/img/calgua_01_10.jpg" width="39" height="17" hspace="0" vspace="0" border="0" style="display:block; vertical-align:bottom;"/></td>
			        <td width="95" height="17" align="left" valign="top" border="0" cellspacing="0" cellpadding="0"><a href="<r:pageUrl pageLabel='${pageLabelCanje}'></r:pageUrl>"><img src="<%=request.getContextPath()%>/portlet/vtasYMktgFidelizacion/img/calgua_01_11.jpg" width="95" height="17" border="0" hspace="0" vspace="0" style="display:block; vertical-align:bottom;"/></a></td>
			        <td width="41" height="17" align="left" valign="top" border="0" cellspacing="0" cellpadding="0"><img src="<%=request.getContextPath()%>/portlet/vtasYMktgFidelizacion/img/calgua_01_12.jpg" width="41" height="17" hspace="0" vspace="0" border="0" style="display:block; vertical-align:bottom;"/></td>
			      </tr>
			    </table></td>
			  </tr>
			  <tr>
			    <td align="left" valign="top" border="0" cellspacing="0" cellpadding="0"><img src="<%=request.getContextPath()%>/portlet/vtasYMktgFidelizacion/img/calgua_01_13.jpg" width="175" height="20" hspace="0" vspace="0" border="0" style="display:block; vertical-align:bottom;"/></td>
			  </tr>
			</table>
			</div>
		               		</div>
	<div id="caja_puntos_promocion" style="display:none">	
		<div class="cajalinks">
			<table width="175" border="0" cellspacing="0" cellpadding="0" style="margin-left: -1px;">
			  <tr align="left" valign="top">
			    <td width="175" height="74" align="left" valign="top"><img src="<%=request.getContextPath()%>/portlet/vtasYMktgFidelizacion/img/calgua_02_01.jpg" width="175" height="74" hspace="0" vspace="0" border="0" style="display:block; vertical-align:bottom;"/></td>
			  </tr>
			  <tr align="left" valign="top" height="22" border="0" cellspacing="0" cellpadding="0">
			    <td width="175" height="22" align="left" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
			      <tr bgcolor="#FF671E">
			        <td width="23" height="22" align="left" valign="top" border="0" cellspacing="0" cellpadding="0"><img src="<%=request.getContextPath()%>/portlet/vtasYMktgFidelizacion/img/calgua_02_02.jpg" width="23" height="22" hspace="0" vspace="0" border="0" style="display:block; vertical-align:bottom;"/></td>
			        <td width="120" height="22" align="right" valign="middle" border="0" cellspacing="0" cellpadding="0"><font style="font-family:'Trebuchet MS', Arial, Helvetica, sans-serif; font-weight:normal; color:#FFF; font-size:15px;"><span id="puntos_promocion"></span> puntos</font></td>
			        <td width="22" height="22" align="left" valign="top" border="0" cellspacing="0" cellpadding="0"><img src="<%=request.getContextPath()%>/portlet/vtasYMktgFidelizacion/img/calgua_02_04.jpg" width="16" height="22" align="right" hspace="0" vspace="0" border="0" style="display:block; vertical-align:bottom;"/></td>
			      </tr>
			    </table></td>
			  </tr>
			  <tr align="left" valign="top" border="0" cellspacing="0" cellpadding="0">
			    <td width="175" height="30" align="left" valign="top" border="0" cellspacing="0" cellpadding="0"><img src="<%=request.getContextPath()%>/portlet/vtasYMktgFidelizacion/img/calgua_02_05.jpg" width="175" height="30" hspace="0" vspace="0" border="0" style="display:block; vertical-align:bottom;"/></td>
			  </tr>
			  <tr width="175" height="21" align="left" valign="top" border="0" cellspacing="0" cellpadding="0">
			    <td width="175" height="21" align="right" valign="top"><table width="100%" border="0" align="right" cellpadding="0" cellspacing="0">
			      <tr align="right" valign="top" bgcolor="#FF671E">
			        <td width="19" height="21" border="0" cellspacing="0" cellpadding="0"><img src="<%=request.getContextPath()%>/portlet/vtasYMktgFidelizacion/img/calgua_02_06.jpg" width="19" height="21" hspace="0" vspace="0" border="0" style="display:block; vertical-align:bottom;"/></td>
			        <td height="21" border="0" cellspacing="0" cellpadding="0"><font style="font-family:'Trebuchet MS', Arial, Helvetica, sans-serif; font-weight:normal; color:#FFF; font-size:10px;"> el d&iacute;a <span id="fecha_venc"></span></font></td>
			        <td width="24" height="21" border="0" cellspacing="0" cellpadding="0"><img src="<%=request.getContextPath()%>/portlet/vtasYMktgFidelizacion/img/calgua_02_08.jpg" width="16" height="21" hspace="0" vspace="0" border="0" align="right" style="display:block; vertical-align:bottom;"/></td>
			      </tr>
			    </table></td>
			  </tr>
			  <tr align="left" valign="top" border="0" cellspacing="0" cellpadding="0">
			    <td width="175" height="35" align="left" valign="top"><img src="<%=request.getContextPath()%>/portlet/vtasYMktgFidelizacion/img/calgua_02_09.jpg" width="175" height="35" hspace="0" vspace="0" border="0" style="display:block; vertical-align:bottom;"/></td>
			  </tr>
			  <tr align="left" valign="top" height="36" border="0" cellspacing="0" cellpadding="0">
			    <td height="36"><table width="100%" border="0" cellspacing="0" cellpadding="0">
			      <tr>
			        <td width="26" height="67" align="left" valign="top" border="0" cellspacing="0" cellpadding="0"><img src="<%=request.getContextPath()%>/portlet/vtasYMktgFidelizacion/img/calgua_02_10.jpg" width="26" height="67" hspace="0" vspace="0" border="0" style="display:block; vertical-align:bottom;"/></td>
			        <td width="126" height="67" align="center" valign="top" bgcolor="#FFFFFF" border="0" cellspacing="0" cellpadding="0"><font style="font-family:'Trebuchet MS', Arial, Helvetica, sans-serif; font-weight:normal; color:#Ff6702; font-size:28px;"><span id="puntos_acumulados2"></span><br>
			<span style="font-size:11px;">PUNTOS ACUMULADOS</span></font></td>
			        <td width="23" height="67" align="left" valign="top" border="0" cellspacing="0" cellpadding="0"><img src="<%=request.getContextPath()%>/portlet/vtasYMktgFidelizacion/img/calgua_02_12.jpg" width="23" height="67" hspace="0" vspace="0" border="0" style="display:block; vertical-align:bottom;"/></td>
			      </tr>
			    </table>
			    </td>
			  </tr>
			  <tr>
			    <td align="left" valign="top" border="0" cellspacing="0" cellpadding="0"><table width="100%" border="0" cellspacing="0" cellpadding="0">
			      <tr>
			        <td width="36" height="19" border="0" cellspacing="0" cellpadding="0"><img src="<%=request.getContextPath()%>/portlet/vtasYMktgFidelizacion/img/calgua_02_13.jpg" width="36" height="19" hspace="0" vspace="0" border="0" style="display:block; vertical-align:bottom;"/></td>
			        <td width="106" height="19" border="0" cellspacing="0" cellpadding="0"><a href="<r:pageUrl pageLabel='${pageLabelHistorial}'></r:pageUrl>"><img src="<%=request.getContextPath()%>/portlet/vtasYMktgFidelizacion/img/calgua_02_14.jpg" width="106" height="19" hspace="0" vspace="0" border="0" style="display:block; vertical-align:bottom;"/></a></td>
			        <td width="33" height="19" border="0" cellspacing="0" cellpadding="0"><img src="<%=request.getContextPath()%>/portlet/vtasYMktgFidelizacion/img/calgua_02_15.jpg" width="33" height="19" hspace="0" vspace="0" border="0" style="display:block; vertical-align:bottom;"/></td>
			      </tr>
			    </table></td>
			  </tr>
			  <tr>
			    <td height="9" align="left" valign="top"><img src="<%=request.getContextPath()%>/portlet/vtasYMktgFidelizacion/img/calgua_02_16.jpg" width="175" height="9" hspace="0" vspace="0" border="0" style="display:block; vertical-align:bottom;"/></td>
			  </tr>
			  <tr  width="175" height="19" align="left" valign="top">
			    <td width="175" height="19" align="left" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
			      <tr>
			        <td width="36" height="19" align="left" valign="top"><img src="<%=request.getContextPath()%>/portlet/vtasYMktgFidelizacion/img/calgua_02_17.jpg" width="36" height="19" hspace="0" vspace="0" border="0" style="display:block; vertical-align:bottom;"/></td>
			        <td width="106" height="19" align="left" valign="top"><a href="<r:pageUrl pageLabel='${pageLabelCanje}'></r:pageUrl>"><img src="<%=request.getContextPath()%>/portlet/vtasYMktgFidelizacion/img/calgua_02_18.jpg" width="106" height="19" hspace="0" vspace="0" border="0" style="display:block; vertical-align:bottom;"/></a></td>
			        <td width="33" height="19" align="left" valign="top"><img src="<%=request.getContextPath()%>/portlet/vtasYMktgFidelizacion/img/calgua_02_19.jpg" width="33" height="19" hspace="0" vspace="0" border="0" style="display:block; vertical-align:bottom;"/></td>
			      </tr>
			    </table></td>
			  </tr>
			  <tr>
			    <td align="left" valign="top"><img src="<%=request.getContextPath()%>/portlet/vtasYMktgFidelizacion/img/calgua_02_20.jpg" width="175" height="28" hspace="0" vspace="0" border="0" style="display:block; vertical-align:bottom;"/></td>
			  </tr>
			</table>
		</div>
	</div>	   
  </c:when>
  <c:otherwise>
     <div class="cajalinks">		    
			<div class="cabecera  cabecera_nueva_azul clearfix">
				<h1><cm:getProperty node="${nodo[0]}" name="titulo" /></h1>
			</div>
			<div class="cuerpo">			
				<cm:getProperty node="${nodo[0]}" name="html" />
			</div>
			<div class="pie"></div>
		</div>		
   </c:otherwise>
</c:choose>
</f:view>