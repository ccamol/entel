<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="it" uri="http://i2b.cl/jsf/iterator/"%>
<%@ taglib uri="http://www.bea.com/servers/portal/tags/netuix/preferences" prefix="preferences" %>
<%@ taglib uri="http://www.bea.com/servers/portal/tags/content" prefix="cm"%>
<cm:search id="tituloInscrito" query="idContenido = 'tituloInscritoMKT'" useCache="true"  />
<cm:search id="msjDesinscribe" query="idContenido = 'msjDesinscribeMKT'" useCache="true"  />
<preferences:getPreference name="flagTooltip" var="flagTooltip"/>
<script type="text/javascript">
   var urlEliminarSuscMMKT = '<%=request.getContextPath()%>/portlet/administracion_servicios/servicios/mobileMarketing/eliminaSuscMMKT.faces';
   var urlActualizarSuscMMKT = '<%=request.getContextPath()%>/portlet/administracion_servicios/servicios/mobileMarketing/actualizaSuscMMKT.faces';
</script>
<f:view beforePhase="#{administracionServicios.initCatalogoActualSAGEN}">
   <h:panelGroup>
      <br/> <br/>
      <div class="clearfix" id="serv_mm_estado_inscrito" style="display:block";>
         <div class="caja verde clearfix" id="sev_mm_caja_inscrito" style="display:block;">
            <div class="caja_imagen_inscrito"></div>
            <div class="caja_texto" style="width:290px;">
               <p class="tipoVerde">
                  <span class="caja_verde_titulo">
                  <cm:getProperty node="${tituloInscrito[0]}" name="html" />
               </p>
            </div>
            <div class="caja_boton flotar_der margen_top_medio" style="padding-right:10px;">
               <a class="btnAzulDelgado btnAncho2" href="#" id="serv_mm_desincribir"><span>Desinscribir</span></a>
            </div>
         </div>
         <div class="caja amarilla clearfix" id="sev_mm_caja_desincribir" style="display:none;">
            <div class="caja_imagen_inscrito"></div>
            <div class="caja_texto" style="width:290px;">
               <p class="tipoVerde">
                  <span class="caja_verde_titulo">
                  <cm:getProperty node="${msjDesinscribe[0]}" name="html" />
               </p>
            </div>
            <div class="caja_boton flotar_der margen_top_medio" style="padding-right:10px;">
               <a class="btnAzulDelgado btnAncho2 flotar_izq" href="#" id="serv_mm_desincribir_final"><span>Desinscribir</span></a>                            
               <a href="#" class="serv_mm_enlace margen_top_min" id="serv_mm_desc_cancelar">Cancelar</a>
            </div>
         </div>
         <h2 style="padding-left: 0;">Categorias Suscritas</h2>
         <!-- TABLA INSCRITO MOBILE MARKETING -->
         <div id="tabla_informacion" class="tabla_informacion MMTablaInfo clearfix">
            <it:iterator var="categoria" value="#{administracionServicios.catalogoSAGEN.categoria}" rowIndexVar="indexCat">
               <!-- Fila de informacion -->
               <div class='
               <h:outputText value="#{indexCat % 2 == 0 ? 'tabla_fila resaltador clearfix' : 'tabla_fila clearfix'}"/>               '> 
               
               <!-- Cuando se encuentra habilitada -->
               <h:panelGroup rendered="#{(categoria.estado==1)}">
                  <!-- Estado habilitado (estado 1) -->
                  <div class="estadoHabilitado clearfix">
                     <div class="columna1 MMColumna1">
                        <strong class="clearfix">
                           <h:outputText value="#{categoria.nombreCategoria}"/>
                        </strong>
                     </div>
                     
                     <div class="columna2">
                        <c:if test="${flagTooltip==1}">
                        	<a class="ico_interrogacionNuevo autoTooltip" href="#MMTecnologia" tooltip=""></a>
                        </c:if>
                     </div>
                     
                     <div class="columna3 MMColumna3">
                        <!-- Mensaje habilitado/deshabilitado -->
                        <div class="mensaje habilitado">Habilitado</div>
                        <!-- Btoon deshabilitado/habilitado -->
                        <div class="btnDeshabilitar">
                           <a class="btnAzulDelgado btnAncho2 caso1" href="#" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Adm. de Servicios/Mobile Marketing/Actualizacion Inscripcion/Deshabilitar');"><span>Deshabilitar</span></a>
                        </div>
                        <!-- Cabecera del boton configurar/cerrar -->
                        <div class="MMEnlaceConfigurar">
                           <a href="#" class="MMEnlaceConfigurar">Campañas</a>
                        </div>
                        <div class="MMCerrarConfigurar">
                           <a href="#" class="MMEnlaceCerrar">Cerrar</a>
                        </div>
                        <!-- /Cabecera del boton configurar/cerrar -->                           
                     </div>
                     <!-- Contenido de Configurar/cerrar -->
                     <div class="configurar_contenido MMConf_cont clearfix">
                        <!-- Contenido de avisar a todos -->
                        <div class="configurar_contenido_inferior clearfix" style="display:block;">
                           <!-- Tabla de numeros -->
                           <div class="MMopciones tabla_interna">
                              <div class="top_tabla_ancha"></div>
                              <div class="main_tabla_ancha">
                                 Campañas activas:
                              </div>
                              <div class="bottom_tabla_ancha"></div>
                              <it:iterator var="campana" value="#{categoria.campanas}">
                                 <h:panelGroup>
                                 <div class="tabla_interna_fila MMTabla_if clearfix">
                                    <div class="tabla_interna_columna1">
                                       <strong>
                                          <h:outputText value="#{campana.nombre}"/>
                                       </strong>
                                    </div>
                                    <div class="tabla_interna_columna2">
                                       <h:outputText value="#{campana.descripcion}"/>
                                    </div>
                                 </div>
                                 </h:panelGroup>
                              </it:iterator>
                           </div>
                           <!-- /Tabla de numeros -->
                        </div>
                        <!-- /Contenido de avisar a todos -->
                     </div>
                     <!-- /Contenido de Configurar/cerrar -->                  
                  </div>
                  <!-- /Estado habilitado (estado 1) -->
                  <div class="estadoDeshabilitado1 clearfix ocultar">
                     <div class="columna1 MMColumna1">
                        <strong class="clearfix">
                           <h:outputText value="#{categoria.nombreCategoria}"/>
                        </strong>
                     </div>                    
                     <div class="columna2">
                        <c:if test="${flagTooltip==1}">
                        	<a class="ico_interrogacionNuevo autoTooltip" href="#MMTecnologia" tooltip=""></a>
                        </c:if>
                     </div>                     
                     <div class="columna3 MMColumna3">
                        <!-- Mensaje habilitado/deshabilitado -->
                        <div class="mensaje_ancho">
                           <strong>
                              Deshabilitarás<br />
                              <h:outputText value="#{categoria.nombreCategoria}"/>
                           </strong>
                        </div>
                        <!-- Btoon deshabilitado/habilitado -->
                        <div class="btnDeshabilitar">
                           <a class="btnAzulDelgado btnAncho2 MMCaso4B" href="#" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Adm. de Servicios/Mobile Marketing/Actualizacion Inscripcion/Deshabilitar');"><span>Deshabilitar</span></a>
                        </div>
                        <!-- Boton Cancelar -->
                        <div class="enlaceCancelar">
                           <a href="#" class="enlaceCancelar">Cancelar</a>
                        </div>
                     </div>
                  </div>
                  <div class="estadoDeshabilitado clearfix ocultar">
                     <div class="columna1 MMColumna1">
                        <strong class="clearfix">
                           <h:outputText value="#{categoria.nombreCategoria}"/>
                        </strong>
                     </div>
                     <div class="columna2">
	                     <c:if test="${flagTooltip==1}">
	                     		<a class="ico_interrogacionNuevo autoTooltip" href="#MMTecnologia" tooltip=""></a>
	                     </c:if>
                     </div>                     
                     <div class="columna3 MMColumna3">
                        <!-- Mensaje habilitado/deshabilitado -->
                        <div class="mensaje deshabilitado">Deshabilitado</div>
                        <!-- Btoon deshabilitado/habilitado -->
                        <div class="btnHabilitar">
                           <a class="btnAzulDelgado btnAncho MMCaso3B" href="#" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Adm. de Servicios/Mobile Marketing/Inscripción/Habilitar');"><span>Habilitar</span></a>
                        </div>
                        <!-- Cabecera del boton configurar/cerrar -->
                        <div class="MMEnlaceConfigurar">
                           <a href="#" class="MMEnlaceConfigurar">Campañas</a>
                        </div>
                        <div class="MMCerrarConfigurar">
                           <a href="#" class="MMEnlaceCerrar">Cerrar</a>
                        </div>
                        <!-- /Cabecera del boton configurar/cerrar -->
                        <!-- Contenido de Configurar/cerrar -->
                        <div class="configurar_contenido MMConf_cont clearfix">
                           <!-- Contenido de avisar a todos -->
                           <div class="configurar_contenido_inferior clearfix" style="display:block;">
                              <!-- Tabla de numeros -->
                              <div class="MMopciones tabla_interna">
                                 <div class="top_tabla_ancha"></div>
                                 <div class="main_tabla_ancha">
                                    Campañas activas:
                                 </div>
                                 <div class="bottom_tabla_ancha"></div>
                                 <it:iterator var="campana" value="#{categoria.campanas}">
                                    <div class="tabla_interna_fila MMTabla_if clearfix">
                                       <div class="tabla_interna_columna1">
                                          <strong>
                                             <h:outputText value="#{campana.nombre}"/>
                                          </strong>
                                       </div>
                                       <div class="tabla_interna_columna2">
                                          <h:outputText value="#{campana.descripcion}"/>
                                       </div>
                                    </div>
                                 </it:iterator>
                              </div>
                              <!-- /Tabla de numeros -->
                           </div>
                           <!-- /Contenido de avisar a todos -->
                        </div>
                        <!-- /Contenido de Configurar/cerrar -->
                     </div>
                  </div>
               </h:panelGroup>
               
               <!-- Cuando se encuentra deshabilitada -->
               <h:panelGroup rendered="#{(categoria.estado==0)}">
                  <div class="estadoDeshabilitado clearfix">
                     <div class="columna1 MMColumna1">
                        <strong class="clearfix">
                           <h:outputText value="#{categoria.nombreCategoria}"/>
                        </strong>
                     </div>                     
                     <div class="columna2">
                        <c:if test="${flagTooltip==1}">
                        	<a class="ico_interrogacionNuevo autoTooltip" href="#MMTecnologia" tooltip=""></a>
                        </c:if>
                     </div>
                                          
                     <div class="columna3 MMColumna3">
                        <!-- Mensaje habilitado/deshabilitado -->
                        <div class="mensaje deshabilitado">Deshabilitado</div>
                        <!-- Btoon deshabilitado/habilitado -->
                        <div class="btnHabilitar">
                           <a class="btnAzulDelgado btnAncho MMCaso3B" href="#" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Adm. de Servicios/Mobile Marketing/Inscripción/Habilitar');"><span>Habilitar</span></a>
                        </div>
                        <!-- Cabecera del boton configurar/cerrar -->
                        <div class="MMEnlaceConfigurar" style="display:none";>
                           <a href="#" class="MMEnlaceConfigurar">Campañas</a>
                        </div>
                        <div class="MMCerrarConfigurar">
                           <a href="#" class="MMEnlaceCerrar">Cerrar</a>
                        </div>
                        <!-- /Cabecera del boton configurar/cerrar -->
                        <!-- Contenido de Configurar/cerrar -->
                        <div class="configurar_contenido MMConf_cont clearfix">
                           <!-- Contenido de avisar a todos -->
                           <div class="configurar_contenido_inferior clearfix" style="display:block;">
                              <!-- Tabla de numeros -->
                              <div class="MMopciones tabla_interna">
                                 <div class="top_tabla_ancha"></div>
                                 <div class="main_tabla_ancha">
                                    Campañas activas:
                                 </div>
                                 <div class="bottom_tabla_ancha"></div>
                                 <it:iterator var="campana" value="#{categoria.campanas}">
                                    <div class="tabla_interna_fila MMTabla_if clearfix">
                                       <div class="tabla_interna_columna1">
                                          <strong>
                                             <h:outputText value="#{campana.nombre}"/>
                                          </strong>
                                       </div>
                                       <div class="tabla_interna_columna2">
                                          <h:outputText value="#{campana.descripcion}"/>
                                       </div>
                                    </div>
                                 </it:iterator>
                              </div>
                              <!-- /Tabla de numeros -->
                           </div>
                           <!-- /Contenido de avisar a todos -->
                        </div>
                        <!-- /Contenido de Configurar/cerrar -->
                     </div>
                  </div>
                  <!-- Estado habilitado (estado 1) -->
                  <div class="estadoHabilitado clearfix ocultar">
                     <div class="columna1 MMColumna1">
                        <strong class="clearfix">
                           <h:outputText value="#{categoria.nombreCategoria}"/>
                        </strong>
                     </div>
                     
                     <div class="columna2">
	                     <c:if test="${flagTooltip==1}">
	               			<a class="ico_interrogacionNuevo autoTooltip" href="#MMTecnologia" tooltip=""></a>
	                     </c:if>
                     </div>
                     <div class="columna3 MMColumna3">
                        <!-- Mensaje habilitado/deshabilitado -->
                        <div class="mensaje habilitado">Habilitado</div>
                        <!-- Btoon deshabilitado/habilitado -->
                        <div class="btnDeshabilitar">
                           <a class="btnAzulDelgado btnAncho2 caso1" href="#" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Adm. de Servicios/Mobile Marketing/Actualizacion Inscripcion/Deshabilitar');"><span>Deshabilitar</span></a>
                        </div>
                        <!-- Cabecera del boton configurar/cerrar -->
                        <div class="MMEnlaceConfigurar">
                           <a href="#" class="MMEnlaceConfigurar">Campañas</a>
                        </div>
                        <div class="MMCerrarConfigurar">
                           <a href="#" class="MMEnlaceCerrar">Cerrar</a>
                        </div>
                        <!-- /Cabecera del boton configurar/cerrar -->                           
                     </div>
                     <!-- Contenido de Configurar/cerrar -->
                     <div class="configurar_contenido MMConf_cont clearfix">
                        <!-- Contenido de avisar a todos -->
                        <div class="configurar_contenido_inferior clearfix" style="display:block;">
                           <!-- Tabla de numeros -->
                           <div class="MMopciones tabla_interna">
                              <div class="top_tabla_ancha"></div>
                              <div class="main_tabla_ancha">
                                 Campañas activas:
                              </div>
                              <div class="bottom_tabla_ancha"></div>
                              <it:iterator var="campana" value="#{categoria.campanas}">
                                 <div class="tabla_interna_fila MMTabla_if clearfix">
                                    <div class="tabla_interna_columna1">
                                       <strong>
                                          <h:outputText value="#{campana.nombre}"/>
                                       </strong>
                                    </div>
                                    <div class="tabla_interna_columna2">
                                       <h:outputText value="#{campana.descripcion}"/>
                                    </div>
                                 </div>
                              </it:iterator>
                           </div>
                           <!-- /Tabla de numeros -->
                        </div>
                        <!-- /Contenido de avisar a todos -->
                     </div>
                     <!-- /Contenido de Configurar/cerrar -->                  
                  </div>
                  <!-- /Estado habilitado (estado 1) -->
                  <div class="estadoDeshabilitado1 clearfix ocultar">
                     <div class="columna1 MMColumna1">
                        <strong class="clearfix">
                           <h:outputText value="#{categoria.nombreCategoria}"/>
                        </strong>
                     </div>
                     
                     <div class="columna2">
	                     <c:if test="${flagTooltip==1}">
	                     		<a class="ico_interrogacionNuevo autoTooltip" href="#MMTecnologia" tooltip=""></a>
	                     </c:if>
                     </div>
                     <div class="columna3 MMColumna3">
                        <!-- Mensaje habilitado/deshabilitado -->
                        <div class="mensaje_ancho">
                           <strong>
                              Deshabilitarás<br />
                              <h:outputText value="#{categoria.nombreCategoria}"/>
                           </strong>
                        </div>
                        <!-- Btoon deshabilitado/habilitado -->
                        <div class="btnDeshabilitar">
                           <a class="btnAzulDelgado btnAncho2 MMCaso4B" href="#" onclick="mxTracker._trackPageview('Personas/Mi Entel/Mis Productos/Telefonia/Adm. de Servicios/Mobile Marketing/Actualizacion Inscripcion/Deshabilitar');"><span>Deshabilitar</span></a>
                        </div>
                        <!-- Boton Cancelar -->
                        <div class="enlaceCancelar">
                           <a href="#" class="enlaceCancelar">Cancelar</a>
                        </div>
                     </div>
                  </div>
               </h:panelGroup>
               <input class="idCategoria" type="hidden" value='<h:outputText value="#{categoria.idCategoria}"/>'/> 
               <input class="nombreCategoria" type="hidden" value='<h:outputText value="#{categoria.nombreCategoria}"/>'/>
               <input class="flagHabilitado" type="hidden" value='<h:outputText value="#{categoria.estado}"/>'/>            
         </div>
         <!-- /Fila de informacion -->
         </it:iterator>		        
      </div>
      <!-- /TABLA INSCRITO MOBILE MARKETING -->
      </div>
      <!-- /MOBILE MARKETING INSCRITO -->
   </h:panelGroup>
</f:view>
