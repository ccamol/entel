<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib uri="http://www.bea.com/servers/portal/tags/content" prefix="cm"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="r"  uri="http://www.bea.com/servers/portal/tags/netuix/render"%>
<cm:search id="mensaje" query="idContenido = 'mensaje_oferta_webpay'" useCache="true"  />
<cm:search id="var_contenido_promo" query="idContenido = 'contenido_promo'" useCache="true"  />

<f:view beforePhase="#{recargaController.init}">
  			<div class="clearfix">			      
				 <span class="imagen_landing_oferta_recarga"></span>
			</div>
			      <div class="mensaje_punto_naranja">
			        <cm:getProperty node="${mensaje[0]}" name="html" />
			      </div>
			      	
                  <div class="header_tabla_historico clearfix">
                    <div class="top"><span></span></div>
                    <div class="main">
                        <table>
                            <tbody>
	                            <tr>
	                            <th width="33%" style="text-align:center;font-weight: bold;font-size:12px">Monto Recarga</th>
                                <th width="33%" style="text-align:center;font-weight: bold;font-size:12px">Minutos Adicionales</th>
                                <th width="33%" style="text-align:center;font-weight: bold;font-size:12px">Bolsa Internet <br/>Min. Adicional </th>                             
	                            </tr>
                        	</tbody>
                        </table>					
                    </div>
                    <div class="bottom"><span></span></div>
                </div>
              
                
                
                <div class="contenido_tabla">
				<table class="tablaFacturacion">
					<tbody>					
						<cm:getProperty node="${var_contenido_promo[0]}" name="html" />															
					</tbody>
				</table>						
			</div>		      
	      
	         <h:form>
	         	<jsp:include page="/token.jsp" flush="true"/>
      		     <h:commandLink  id="solicitud_recarga" styleClass="btnVerdeDelgado alargar" actionListener="#{recargaController.seleccionRecargaWebpay}" ><span>Recargar</span>
			       <f:param name="tipoWebpay" value="oferta_recarga_wp"/>     
			    </h:commandLink>
            </h:form>
			
</f:view>
