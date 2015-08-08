<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.bea.com/servers/portal/tags/netuix/preferences" prefix="preferences" %>
<%@ taglib uri="http://www.bea.com/servers/portal/tags/content" prefix="cm"%>
<cm:search id="tituloPaso3" query="idContenido = 'tituloPaso3MKT'" useCache="true"  />
<cm:search id="msjContrato" query="idContenido = 'msjContratoMKT'" useCache="true"  />

<!-- MOBIL MARKETING PASO 3 -->                    
<div class="clearfix" id="serv_mm_paso_3" style="display:none;">    	
    
    <h:panelGroup rendered="#{!(administracionServicios.catalogoSAGEN==null)}">	
    
    <span class="serv_pasos"><strong>Paso 3 de 3</strong></span>    
    <cm:getProperty node="${tituloPaso3[0]}" name="html" /> 
    
    
    
    <div class="serv_checklist clearfix">                            	
        <input name="horario" value="Cualquiera" type="radio" class="radio" id="horCualquiera" checked="checked">
        <label for="horCualquiera">Cualquier horario</label>
        
        <input name="horario" value="Especifico" type="radio" class="radio" id="horEspecifico">
        <label for="horEspecifico">Especificar horario</label>
    </div>
    
    <div id="form_serv_hor" class="clearfix">
        <form name="form_serv_mm_horario" id="form_serv_mm_horario" action="#" method="post">
			<jsp:include page="/token.jsp" flush="true"/>
            <div class="caja amarilla clearfix margen_top_doble">
                
                <p class="tipoVerde float_none">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus fringilla enim ac felis fringilla ornare. Fusce euismod blandit tortor sed ullamcorper.</p>
                
                <div class="clearfix margen_top">
                    <div class="campo flotar_izq" style="width:15px;">
                        <input type="checkbox" name="seh_condiciones" id="seh_condiciones2" value="Aceptado" class="checkBox" />
                    </div>
                    <label class="serv_info" for="seh_condiciones2"><strong>Acepto las condiciones</strong></label>
                </div>
                
            </div>
        </form>
    </div>
    
    <div id="form_serv_especif_hor" class="clearfix margen_top_doble">
        <form name="form_serv_mm_hora_especifica" id="form_serv_mm_hora_especifica" action="#" method="post">
            <jsp:include page="/token.jsp" flush="true"/>
            <div class="clearfix margen_top_doble">
                <label class="label_sel_centrar">Desde:</label>
                <div class="flotar_izq campo">                                    
                    
                    <select id="seh_desde" name="seh_desde" class="selectBox" style="width: 100px;">
                        <option value="0"></option>
                        <option value="00:00">00:00</option>
                        <option value="01:00">01:00</option>
                        <option value="02:00">02:00</option>
                        <option value="03:00">03:00</option>
                        <option value="04:00">04:00</option>
                        <option value="05:00">05:00</option>
                        <option value="06:00">06:00</option>
                        <option value="07:00">07:00</option>
                        <option value="08:00">08:00</option>
                        <option value="09:00">09:00</option>
                        <option value="10:00">10:00</option>
                        <option value="11:00">11:00</option>
                        <option value="12:00">12:00</option>
                        <option value="13:00">13:00</option>
                        <option value="14:00">14:00</option>
                        <option value="15:00">15:00</option>
                        <option value="16:00">16:00</option>
                        <option value="17:00">17:00</option>
                        <option value="18:00">18:00</option>
                        <option value="19:00">19:00</option>
                        <option value="20:00">20:00</option>
                        <option value="21:00">21:00</option>
                        <option value="22:00">22:00</option>
                        <option value="23:00">23:00</option>
                    </select>
                    
                </div>
                
                <label class="label_sel_centrar" style="margin-left:30px;">Hasta:</label>
                <div class="flotar_izq campo">
                    <select id="seh_hasta" name="seh_hasta" class="selectBox" style="width:100px;">
                        <option value="0"></option>                                    	
                        <option value="01:00">01:00</option>
                        <option value="02:00">02:00</option>
                        <option value="03:00">03:00</option>
                        <option value="04:00">04:00</option>
                        <option value="05:00">05:00</option>
                        <option value="06:00">06:00</option>
                        <option value="07:00">07:00</option>
                        <option value="08:00">08:00</option>
                        <option value="09:00">09:00</option>
                        <option value="10:00">10:00</option>
                        <option value="11:00">11:00</option>
                        <option value="12:00">12:00</option>
                        <option value="13:00">13:00</option>
                        <option value="14:00">14:00</option>
                        <option value="15:00">15:00</option>
                        <option value="16:00">16:00</option>
                        <option value="17:00">17:00</option>
                        <option value="18:00">18:00</option>
                        <option value="19:00">19:00</option>
                        <option value="20:00">20:00</option>
                        <option value="21:00">21:00</option>
                        <option value="22:00">22:00</option>
                        <option value="23:00">23:00</option>
                    </select>
                </div>
                
            </div>
            
            <div class="clearfix margen_top_doble campo">
                <input type="checkbox" name="seh_dia" value="1" class="checkBox checkBoxDay" id="seh_dia_lunes" /><label for="seh_dia_lunes">Lunes</label>
                <input type="checkbox" name="seh_dia" value="2" class="checkBox checkBoxDay" id="seh_dia_martes" /><label for="seh_dia_martes">Martes</label>
                <input type="checkbox" name="seh_dia" value="3" class="checkBox checkBoxDay" id="seh_dia_miercoles" /><label for="seh_dia_miercoles">Mi&eacute;rcoles</label>
                <input type="checkbox" name="seh_dia" value="4" class="checkBox checkBoxDay" id="seh_dia_jueves" /><label for="seh_dia_jueves">Jueves</label>
                <input type="checkbox" name="seh_dia" value="5" class="checkBox checkBoxDay" id="seh_dia_viernes" /><label for="seh_dia_viernes">Viernes</label>
                <input type="checkbox" name="seh_dia" value="6" class="checkBox checkBoxDay" id="seh_dia_sabado" /><label for="seh_dia_sabado">S&aacute;bado</label>
                <input type="checkbox" name="seh_dia" value="7" class="checkBox checkBoxDay" id="seh_dia_domingo" /><label for="seh_dia_domingo">Domingo</label>
            </div>
            
            <div class="caja amarilla clearfix margen_top_doble">
        
                <p class="tipoVerde float_none">
                	<cm:getProperty node="${msjContrato[0]}" name="html" /> 
                </p>
                
                <div class="clearfix margen_top">
                    <div class="campo flotar_izq" style="width:15px;">
                        <input type="checkbox" name="seh_condiciones" id="seh_condiciones" value="Aceptado" class="checkBox" />
                    </div>
                    <label class="serv_info" for="seh_condiciones"><strong>Acepto las condiciones</strong></label>
                </div>
                
            </div>            
        </form>                        
        
    </div>
    
    <div class="clearfix" style="width:190px; margin:20px auto 0;">
        <div class="flotar_izq caja_enlace_volver">
            <a href="#" class="enlace_volver" id="bt_serv_volver_2"><span>Volver</span></a>
        </div>
        <div class="flotar_izq">
            <a href="#" class="btnAzulGrande btnAzulGrandeLargo" id="bt_serv_finalizar"><span>Finalizar</span></a>
        </div>
    </div>
    </h:panelGroup>    
</div>
<!-- /MOBIL MARKETING PASO 3 -->  