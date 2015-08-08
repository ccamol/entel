<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="it" uri="http://i2b.cl/jsf/iterator/"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.bea.com/servers/portal/tags/netuix/preferences" prefix="preferences" %>
<%@ taglib uri="http://www.bea.com/servers/portal/tags/content" prefix="cm"%>
<cm:search id="tituloPaso2" query="idContenido = 'tituloPaso2MKT'" useCache="true"  />

<preferences:getPreference name="flagTooltip" var="flagTooltip"/>

<!-- MOBIL MARKETING PASO 2 -->
<div class="clearfix" id="serv_mm_paso_2" style="display:none;">
   <span class="serv_pasos"><strong>Paso 2 de 3</strong></span>
   <cm:getProperty node="${tituloPaso2[0]}" name="html" /> 
   
   <h:panelGroup rendered="#{!(administracionServicios.catalogoSAGEN==null)}">	
	   <!-- TABLA NO INSCRITO MOBILE MARKETING PASO 2 -->
	   <div id="tabla_informacion" class="tabla_informacion MMTablaInfo clearfix">      
	   	<it:iterator var="categoria" value="#{administracionServicios.catalogoSAGEN.categoria}" rowIndexVar="indexCat">
		      <!-- Fila de informacion -->
		      <div class='<h:outputText value="#{indexCat % 2 == 0 ? 'tabla_fila resaltador clearfix' : 'tabla_fila clearfix'}"/>'> 
                <!-- Estado habilitado (estado 1) -->
                   <div class="estadoHabilitado clearfix ocultar">
                       <div class="columna1 MMColumna1">
                           <strong class="clearfix"><h:outputText value="#{categoria.nombreCategoria}"/></strong>                                        
                       </div>
                       
                      <div class="columna2">
                      		<c:if test="${flagTooltip==1}">
                          		<a class="ico_interrogacionNuevo autoTooltip" href="#MM<h:outputText value="#{categoria.nombreCategoria}"/>" tooltip=""></a>
                           </c:if>
                      </div>
                       <div class="columna3 MMColumna3">
                           <!-- Mensaje habilitado/deshabilitado -->
                           <div class="mensaje habilitado">Habilitado</div>
                           
                           <!-- Btoon deshabilitado/habilitado -->
                           <div class="btnDeshabilitar">
                               <a class="btnAzulDelgado btnAncho2 caso1" href="#"><span>Deshabilitar</span></a>
                           </div>
                       </div>                     
                   </div>
                   <!-- /Estado habilitado (estado 1) -->
                   
                   <div class="estadoHabilitado1 clearfix ocultar">
                       <div class="columna1 MMColumna1">
                           <strong class="clearfix"><h:outputText value="#{categoria.nombreCategoria}"/></strong>                                        
                       </div>
                       
                       <div class="columna2">
                       		<c:if test="${flagTooltip==1}">
                           		<a class="ico_interrogacionNuevo autoTooltip" href="#MM<h:outputText value="#{categoria.nombreCategoria}"/>" tooltip=""></a>
                           	</c:if>
                       </div>
                       <div class="columna3 MMColumna3">
                           <!-- Mensaje grande -->
                           <div class="mensaje_grande mensaje_grande_reloj">
                               En proceso de <strong>habilitación</strong>
                           </div>
                       </div>
                   </div>
                   
                   <div class="estadoDeshabilitado clearfix">
                       <div class="columna1 MMColumna1">
                           <strong class="clearfix"><h:outputText value="#{categoria.nombreCategoria}"/></strong>                                        
                       </div>
                       <div class="columna2">
                           <c:if test="${flagTooltip==1}">
                           		<a class="ico_interrogacionNuevo autoTooltip" href="#MM<h:outputText value="#{categoria.nombreCategoria}"/>" tooltip=""></a>
                           </c:if>
                       </div>
                       <div class="columna3 MMColumna3">
                           <!-- Mensaje habilitado/deshabilitado -->
                           <div class="mensaje deshabilitado">Deshabilitado</div>
                           
                           <!-- Btoon deshabilitado/habilitado -->
                           <div class="btnHabilitar">
                               <a class="btnAzulDelgado btnAncho MMCaso3" href="#" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Adm. de Servicios/Mobile Marketing/Seleccion Interes/Habilitar');"><span>Habilitar</span></a>
                           </div>                                          
                       </div>                       
                   </div>
                   
                   <div class="estadoDeshabilitado1 clearfix ocultar">

                       <div class="columna1 MMColumna1">
                           <strong class="clearfix"><h:outputText value="#{categoria.nombreCategoria}"/></strong>                                        
                       </div>                    
                       <div class="columna2">
                       		 <c:if test="${flagTooltip==1}">
                        		<a class="ico_interrogacionNuevo autoTooltip" href="#MM<h:outputText value="#{categoria.nombreCategoria}"/>" tooltip=""></a>
                        	</c:if>
                       </div>
                       <div class="columna3 MMColumna3">
                           <!-- Mensaje habilitado/deshabilitado -->
                           <div class="mensaje_ancho"><strong>Deshabilitarás<br /><h:outputText value="#{categoria.nombreCategoria}"/></strong></div>
                           
                           <!-- Btoon deshabilitado/habilitado -->
                           <div class="btnDeshabilitar">
                               <a class="btnAzulDelgado btnAncho2 MMCaso4" href="#"><span>Deshabilitar</span></a>
                           </div>
                           
                           <!-- Boton Cancelar -->
                           <div class="enlaceCancelar">
                               <a href="#" class="enlaceCancelar">Cancelar</a>
                           </div>
                       </div>
                   </div>  
                     
                   <input class="idCategoria" type="hidden" value='<h:outputText value="#{categoria.idCategoria}"/>'/> 
                   <input class="nombreCategoria" type="hidden" value='<h:outputText value="#{categoria.nombreCategoria}"/>'/>
                   <input class="flagHabilitado" type="hidden" value="0"/>
                   
               </div>
               <!-- /Fila de informacion -->                              
		 	</it:iterator>
	   </div>

	   <!-- TABLA NO INSCRITO MOBILE MARKETING PASO 2 -->
	   <div class="clearfix" style="width:190px; margin:20px auto 0;">
	      <div class="flotar_izq caja_enlace_volver">
	         <a href="#" class="enlace_volver" id="bt_serv_volver_1"><span>Volver</span></a>
	      </div>
	      <div class="flotar_izq">
	         <a href="#" class="btnAzulGrande btnAzulGrandeLargo" id="bt_serv_continuar_2"><span>Continuar</span></a>
	      </div>
	   </div>
	   
	</h:panelGroup>
</div>
<!-- /MOBIL MARKETING PASO 2 -->

