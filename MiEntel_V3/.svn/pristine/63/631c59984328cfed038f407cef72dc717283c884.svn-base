<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://www.bea.com/servers/portal/tags/content" prefix="cm"%>
<%@ taglib prefix="it" uri="http://i2b.cl/jsf/iterator/" %>
<%@ taglib prefix="pref" uri="http://www.bea.com/servers/portal/tags/netuix/preferences" %>
<cm:search id="ZonaVeranoTitulo" query="idContenido = 'idZonaVeranoTitulo'" useCache="false"  />
<cm:search id="TextoComoCanjear" query="idContenido = 'idTextoComoCanjear'" useCache="false"  />
<cm:search id="TextoConfirmarCanje" query="idContenido = 'TextoConfirmarCanje'" useCache="false"  />
<cm:search id="TextoExitoCanje" query="idContenido = 'TextoExitoCanje'" useCache="false"  />
<cm:search id="TextoExitoCanjeOB" query="idContenido = 'TextoExitoCanjeOB'" useCache="false"  />
<cm:search id="necesitasMasPuntosBolsas" query="idContenido = 'necesitasMasPuntosBolsas'" useCache="false"/>
<cm:search id="no_hay_bolsas_disponibles" query="idContenido = 'no_hay_bolsas_disponibles'" useCache="false"/>
<cm:search id="clavePromotora" query="idContenido = 'clavePromotora'" useCache="false"/>
<cm:search id="MsgclaveCorrecta" query="idContenido = 'MsgclaveCorrecta'" useCache="false"/>
<%@ taglib prefix="entel" uri="/WEB-INF/tld/entel-tags.tld" %>

<f:view beforePhase="#{vtasYMktgFidelizacionController.obtenerCatalogoZonaVerano}">

<entel:view name="zonaVerano">
<script type="text/javascript">

    var validandoClave='0';
    var varClaveGuardada='';
	var urlValidarClave='<%=request.getContextPath()%>/portlet/vtasYMktgFidelizacion/validarClaveZonaVeranoJson.faces';
	var urlCanjear='<%=request.getContextPath()%>/portlet/vtasYMktgFidelizacion/canjearPuntosZonaVeranoJson.faces';
	var urlActualizarPuntos='<%=request.getContextPath()%>/portlet/vtasYMktgFidelizacion/actualizarPuntosJson.faces';
	var textoConfirmarCanje ="<cm:getProperty node='${TextoConfirmarCanje[0]}' name='html'/>";
	var textoExitoCanje ="<cm:getProperty node='${TextoExitoCanje[0]}' name='html'/>";	
	var textoExitoCanjeOB ="<cm:getProperty node='${TextoExitoCanjeOB[0]}' name='html'/>";		
	var numeroPcs ='<h:outputText value="#{profile.numeroPcs}"/>';
</script>
<br></br>
 <h:panelGroup rendered="#{(vtasYMktgFidelizacionController.existenZonasVerano)}"> 
  <div class="zona-verano-caja">
        	<h2 class="bullet"><cm:getProperty node="${ZonaVeranoTitulo[0]}" name="html" /></h2>
        	<p>
        	<cm:getProperty node="${TextoComoCanjear[0]}" name="titulo" />
            <cm:getProperty node="${TextoComoCanjear[0]}" name="html" />
            </p> 
            <div class="linea_tabs2 clearfix">
              <it:iterator var="listaZonasHabilitadasTag" value="#{vtasYMktgFidelizacionController.tagZonaVerano}" rowIndexVar="index">
	                <f:verbatim rendered="#{!empty listaZonasHabilitadasTag}">
			                <div class="tab2 contenido<h:outputText value="#{index+1}"/>">
			                    <span class="">
			                    <h:outputText value="#{listaZonasHabilitadasTag[1]}"/>
			                    </span>
			                </div> 			             
		             </f:verbatim>
               </it:iterator>      
            </div>
            
            <div class="contenido_tabs">
               <it:iterator var="listaZonasHabilitadasItems" value="#{vtasYMktgFidelizacionController.tagZonaVerano}" rowIndexVar="indexZona">
	                <div class="contenido_tab contenido<h:outputText value="#{indexZona+1}"/> clearfix" style="display:<h:outputText value="#{indexZona == 0 ? 'block' : 'none'}"/> clearfix">
	                	
	                	     <h:panelGroup rendered="#{(listaZonasHabilitadasItems[3] == '')}">
	                	      <br></br>	
		                         <div class="mensaje-alerta-sistema-pequeno">
									<div class="clearfix sub-contenedor">
										<div class="contenedor-imagen">
											<div class="imagen"></div>
										</div>
										<div class="texto"><cm:getProperty node="${no_hay_bolsas_disponibles[0]}" name="html" /></div>
									</div>
								</div>
		                     </h:panelGroup>
		                     
		                     <h:panelGroup rendered="#{(listaZonasHabilitadasItems[3] == 'Ok')}">
			                     <div class="clearfix debes-saber-caja"> 
			                        <div id="debes_saber_inicial" class="debes-saber clearfix">
			                            <div class="contenido">
			                                <p style="padding:0px;"><h:outputText value="#{listaZonasHabilitadasItems[2]}" escape="false" /></p>
			                            </div>
		                           </div>
		                          </div>
		                     </h:panelGroup> 
	                   
	                    <div id="menu-desplegable-planes" class="margen">
	                    <!-- ELEMENTO -->	                     
	                    <it:iterator var="listaProductosZonas" value="#{vtasYMktgFidelizacionController.listaBolsasZonaVerano}" rowIndexVar="indexProductosZona">
	                      	                   
	                              <h:panelGroup rendered="#{(listaProductosZonas.codZona eq listaZonasHabilitadasItems[0])}">	                                 
				                       <div class="bolsa <h:outputText value="#{(indexProductosZona % 2) == 0 ? 'impar' : 'par'}"/> clearfix" style="width:550px;" >
				                        	<div class="cabecera cajaConfirmar clearfix" style="display:none;">
				                            	<div class="nombreLargo float-izq textoMensaje texto_confirmar_canje"></div>
				                                <!-- Contratar, si es en módo cuenta controlada pasa a ser "comprar -->                                
				                                <!-- fin contratar-->
				                                <div style="float:left;position: relative;top: 6px;"><a href="javascript:;" class="btnVerdeDelgado alargar confirmarCanjear"><span>Confirmar</span></a></div>
				                                <div class="cancelarConfirmar" style="position: relative;top: 6px;"><a href="javascript:;" class="cancelarCanjear">Cancelar</a></div>
				                            </div>
				                            
				                            <input type="hidden" class="info_producto" value="<h:outputText value="#{listaProductosZonas.codProducto}|#{listaProductosZonas.puntos}|#{listaProductosZonas.codZona}|#{listaZonasHabilitadasItems[1]}|#{listaProductosZonas.descProducto}"/>"/>
				                            <input type="hidden" class="clave_guardada" value=""/>
				                            
				                            <!-- cabecera-->
				                            <div class="cabeceraPaso1 cabecera clearfix">
				                                <div class="nombre float-izq textoMensaje" style="font-weight:bolder;"><h:outputText value="#{listaProductosZonas.descProducto}"/></div>
				                                <div class="precio"><h:outputText value="#{listaProductosZonas.puntos}"/> puntos</div>
				                                <!-- Contratar, si es en módo cuenta controlada pasa a ser "comprar -->                                
				                                <!-- fin contratar-->
				                                
				                               <h:panelGroup rendered="#{vtasYMktgFidelizacionController.detallePuntos.saldoPuntos >= listaProductosZonas.puntos}"> 
					                                <div class="regalar" style=""><a href="javascript:;" class="btnVerdeDelgado alargar canjear"><span>Canjear</span></a></div>
					                                <div class="cancelar" style="display:none;border-color:#CFCDCD;border-width:1px 3px 0 1px;border-style:solid;background-color:#FFF;"><a href="javascript:;" class="cancelarCanjear">Cancelar</a></div>
					                                <div class="puntos_insuficientes puntos_insuficientes_2" style="width:102px;display:none;"><cm:getProperty node="${necesitasMasPuntosBolsas[0]}" name="html"/></div>
				                               </h:panelGroup> 
				                               <h:panelGroup rendered="#{vtasYMktgFidelizacionController.detallePuntos.saldoPuntos < listaProductosZonas.puntos}"> 
					                                <div class="puntos_insuficientes" style="width:102px;"><cm:getProperty node="${necesitasMasPuntosBolsas[0]}" name="html"/></div>
				                               </h:panelGroup>
				                            </div>
				                            <!--/cabecera-->                            
				                            <!-- cuerpo-->
				                            <div class="cuerpo clearfix">
				                                <div class="box-numero" style="display:none;border-style:solid;border-color:#CFCDCD;border-width:1px 3px 3px 1px;background-color:#FFF;">
				                                    <!-- formulario (paso 1) -->
				                                    <form class="paso1" style="" action="#">
				                                    	<jsp:include page="/token.jsp" flush="true"/>
				                                        <fieldset>
				                                            <h5><cm:getProperty node='${clavePromotora[0]}' name='html'/></h5>
				                                            <div class="fila clearfix">
				                                                <div class="fila_label">
				                                                    <label>Clave:</label>
				                                                </div>
				                                                <div class="fila_input campo">
				                                                    <input type="password" style="width:125px" maxlength="6" class="txt-numero" id="clave" name="clave";  />
				                                                    <div id="globoError" class="globoError mensaje" style="top: -16px; left: 40px; position: absolute; display: none;"><table><tbody><tr><td class="body"><cm:getProperty node='${MsgclaveCorrecta[0]}' name='html'/></td></tr></tbody></table></div>
				                                                    
				                                                </div>
				                                                				                                                
				                                            </div>
				                                            <div class="fila clearfix">
				                                                <label>&nbsp;</label>
				                                                <div class="loading" style="display:none"></div>				                                                
				                                                <a href="javascript:;" class="btnVerdeDelgado alargar aceptarCanjear"><span>Aceptar</span></a>								
				                                            </div>
				                                        </fieldset>
				                                        
				                                    </form>                                    
				                                </div>				                               	
				                            </div>
				                            
				                                <!--Estado de espera antes del canje. -->
					                            <div class="fila_estado3 fila_impar fila_bolsas" style="display:none">									   			
										   		   <div class="loading loading_canje"></div>
										   		   <p style="font-weight:bold; text-align:center;">Un momento por favor...</p>
											    </div>
											     <!--Div para el manejo de los errores -->
												<div id="mensajes_error" class="mensajes-lista-error mensaje_error_zv" style="display:none; padding:14px 0;font-weight:bold;font-size:12px;background:#fff1a8;border-top:1px solid #d1c289;border-bottom: 1px solid #d1c289;">
												 </div>												
												<div class="cabecera cajaExitoCanje clearfix" style="display:none;width:550px;">				                            	
				                                </div>																																			
												<div class="cancelarConfirmar"><a href="javascript:;" class="volverACanjear" style="display:none;">Realizar nuevo canje</a></div>												
				                            
				                            				                    
				                            <!--/nota/descripcion-->
				                        </div>
			                       </h:panelGroup>		                             		                     
	                      </it:iterator>
	                      <!-- /ELEMENTO -->                        
	                    </div>
	                </div>  
              </it:iterator>                   
            </div>
      </div>
</h:panelGroup>
</entel:view>
</f:view>

