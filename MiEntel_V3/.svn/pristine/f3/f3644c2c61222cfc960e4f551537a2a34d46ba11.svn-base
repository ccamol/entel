<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="it" uri="http://i2b.cl/jsf/iterator/" %>
<%@ taglib prefix="r"  uri="http://www.bea.com/servers/portal/tags/netuix/render"%>
<%@ taglib prefix="cm" uri="http://www.bea.com/servers/portal/tags/content" %>
<%@ taglib prefix="entel" uri="/WEB-INF/tld/entel-tags.tld" %>

<div class="numeroFavoritoPP">
	<h:panelGroup rendered="#{planController.planActualPP != null}">	
		
		<it:iterator var="item" value="#{planController.planActualPP.numerosFrecuentes}" rowIndexVar="row" >	
	
               <h:panelGroup rendered="#{item.idSlot eq slotBean.slotNumeroFavorito}">
               
               <h2 class="movil">N&uacute;mero Favorito</h2> 
	                	
			   <h:panelGroup rendered="#{item.numeroFrecuente != ''}">
				<!-- posee numero favorito -->	
	
					<div class="numero_favorito">
						<div class="paso_1 clearfix">
							<div class="numero"><h:outputText value="#{item.numeroFrecuente}"/></div>
		                    <div class="nombre"><h:outputText value="#{item.nombreSlot}"/></div>
							<div class="botonera">
								<a href="#" class="eliminar"><u>Eliminar n&uacute;mero</u></a>
								<a href="#" class="btnAzulDelgado cambiar"><span>Cambiar n&uacute;mero</span></a>
		
							</div>
							<div class="msg"></div>
							<div class="alerta_borrar">
								<span>Vas a eliminar tu N&uacute;mero Favorito </span>
								<a href="javascript:void(0)" class="btnAzul administrar" onClick="eliminarNumeroFavorito('2','<h:outputText value="#{item.idSlot}"/>');"><span>Confirmar</span></a>
								<a href="#" class="cancelar"><u>Cancelar</u></a>
								
								<a id="loadingNumeroFavorito"></a>
							</div>
		
						</div>
						<div class="paso_modificar clearfix">
							<div class="opciones">
							<!--  
								<div class="clearfix">
									<div class="campo_radio">
										<input type="radio" name="tipo2" id="movil" checked="checked"/>
										<label for="movil">M&oacute;vil EPCS</label>
									</div>
								</div>
							-->
		                        
								<div class="clearfix">
									<div class="campo mostrar_globo">
		                            	<div class="contenedor-text flotar_izq">
		                            		<span class="ejemplo">Ejemplo: Nro de m&oacute;vil 99999999 </span><br></br>
											<input type="text" maxlength="8" name="numero_favorito" class="nf_input" onkeypress="return soloNumeros(event);" />
		                                </div>
										<div id="globoError" class="mensaje"><table><tbody><tr><td class="body">El n&uacute;mero es inv&aacute;lido.</td></tr></tbody></table></div>
									</div>
								</div>
							</div>
							<div class="botonera">
								<a href="#" class="cancelar"><u>Cancelar</u></a>
								<a href="javascript:void(0)" class="btnAzul administrar" onClick="administrarNumeroFavorito('3','<h:outputText value="#{item.idSlot}"/>');"><span>Aceptar</span></a>
								
								<a id="loadingNumeroFavorito"></a>
							</div>
						</div>
						<!--/paso_modificar-->
	
						
						<div class="paso_nuevo clearfix">
							<strong>El n&uacute;mero ha sido eliminado</strong>
							<a href="#" class="btnAzul flotar_der nuevoNumero"><span>Agregar nuevo n&uacute;mero</span></a>
						</div>
						
						</div>
						
				<!-- / posee numero favorito -->
				
				
				</h:panelGroup>
				
	
				<h:panelGroup rendered="#{item.numeroFrecuente == ''}">
					<!-- no posee numero favorito -->	
		
						<div class="numero_favorito">
						
							<div class="paso_1 clearfix" style="display: none">
								<div class="numero"></div>
			                    <div class="nombre"><h:outputText value="#{item.nombreSlot}"></h:outputText></div>
	
								<div class="msg"></div>
	
							</div>
							<div class="paso_modificar clearfix" style="display: none">
								<div class="opciones">
								<!--
									<div class="clearfix">
										<div class="campo_radio">
											<input type="radio" name="tipo2" id="movil" checked="checked"/>
											<label for="movil">M&oacute;vil EPCS</label>
										</div>
									</div>
								-->				
			                        
									<div class="clearfix">
										<div class="campo mostrar_globo">
			                            	<div class="contenedor-text flotar_izq">
			                            		<span class="ejemplo">Ejemplo: Nro de m&oacute;vil 99999999 </span><br></br>
			                            		<input type="text" maxlength="8" name="numero_favorito" class="nf_input" onkeypress="return soloNumeros(event);" />
			                                </div>
											<div id="globoError" class="mensaje"><table><tbody><tr><td class="body">El n&uacute;mero es inv&aacute;lido.</td></tr></tbody></table></div>
										</div>
									</div>
								</div>
								<div class="botonera">
									<a href="#" class="cancelar"><u>Cancelar</u></a>
									<a href="javascript:void(0)" class="btnAzul administrar" onClick="administrarNumeroFavorito('1','<h:outputText value="#{item.idSlot}"/>');"><span>Aceptar</span></a>
									
									<a id="loadingNumeroFavorito"></a>
								</div>
							</div>
							<!--/paso_modificar-->
		
							
							<div class="paso_nuevo clearfix" style="display: block">
								<a href="#" class="btnAzul flotar_der nuevoNumero"><span>Agregar nuevo n&uacute;mero</span></a>
							</div>
							
							</div>
							
					<!-- /no posee numero favorito -->
				  </h:panelGroup>
				  
				  <cm:getProperty node="${infoDebesSaber[0]}" name="html" />
				</h:panelGroup>
			
		</it:iterator>
		
	</h:panelGroup>
	
</div>