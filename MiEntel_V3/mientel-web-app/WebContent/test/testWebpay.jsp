<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Testing Webpay </title>
</head>

<body>
<f:view>

<h1> Test Llamada Webpay </h1>

	<form name="testWebpayForm" id="testWebpayForm" method="POST"
	    action="http://testjsp2.entelpcs.com/cgi-bin/kcc5/tbk_bp_pago.cgi">
	   
	    <input type="hidden" name="TBK_TIPO_TRANSACCION" id="TBK_TIPO_TRANSACCION"    value="TR_MALL"/>
	    Monto: <br>
	    	<select name="TBK_MONTO" id="TBK_MONTO">
				<option label="$3.500" value="3500" >$3.500</option>
				<option label="$4.000" value="4000" >$4.000</option>
				<option label="$4.500" value="4500" >$4.500</option>
				<option label="$5.000" value="5000" >$5.000</option>
	    	</select>
	    	<br>
	    	
	    Orden de compra:<br> 
	    	<input type="text" name="TBK_ORDEN_COMPRA" id="TBK_ORDEN_COMPRA" value=""/><br>               
	    IDP: <br>
	    	<input type="text" name="TBK_ID_SESION" id="TBK_ID_SESION" value=""/><br>               
	    
	    <input type="hidden" name="TBK_URL_RESULTADO" id="TBK_URL_RESULTADO"
	    	value="http://localhost:7001/personas/portlet/recarga/cierreRecargaWebpay.faces?RESULTADO=EXITO" /><br>
	    <input type="hidden" name="TBK_URL_FRACASO" id="TBK_URL_FRACASO"   
	    	value="http://localhost:7001/personas/portlet/recarga/cierreRecargaWebpay.faces?RESULTADO=EXITO" /><br>
	    <input type="hidden" name="TBK_NUM_TRX" id="TBK_NUM_TRX" value="1" />
	    <input type="hidden" name="TBK_CODIGO_TIENDA_M001" id="TBK_CODIGO_TIENDA_M001" value="597026010047" /><br>

		Repetir Monto:<br>
	    <input type="text" name="TBK_ORDEN_TIENDA_M001" id="TBK_ORDEN_TIENDA_M001" value="" /><br>
	    
	    Repetir orden de Compra:<br>
	    <input type="text" name="TBK_MONTO_TIENDA_M001" id="TBK_MONTO_TIENDA_M001" value="" /><br>
	
		<input type="submit" name="testWebPayButton" value="Enviar" />
		
	</form>

<h1> Test Pago Webpay </h1>


	<h:form target="_blank">
		
	   	Orden de compra (generado por MiEntel):<br>
	   	<h:inputText id="ordenCompra" value="#{testWebpay.ordenCompra}" /><br>  
		
		<h:commandButton id="pagoWebpayButton" action="#{testWebpay.testPago}" label="Pago Webpay" value="Pago Webpay" />
	
	</h:form>


<h1> Test Cierre Webpay </h1>

	<form name="testCierreWebpayForm" id="testCierreWebpayForm" method="POST" target="_blank"
		    action="http://localhost:7001/personas/portlet/recarga/cierreRecargaWebpay.faces" >
	
		Seleccione que caso simular, según parámetro Resultado: <br>
	   	<select name="RESULTADO" id="RESULTADO">
			<option label="EXITO" value="EXITO" >EXITO</option>
			<option label="FRACASO" value="FRACASO" >FRACASO</option>
			<option label="DESCONOCIDO" value="DESCONOCIDO" >DESCONOCIDO</option>
	   	</select>
	   	<br>
	   	Orden de compra (generado por MiEntel):<br>
	   	<input type="text" name="ordenCompra" id="ordenCompra" value=""/><br>    
	   	
	   	TBK_ID_TRANSACCION (lo genera Transbank):<br>
		<input type="text" name="TBK_ID_TRANSACCION" id="TBK_ID_TRANSACCION" value="" size="25" /><br>
		
	   	TBK_ID_SESION (Corresponde al IDP generado para la recarga):<br>
		<input type="text" name="TBK_ID_SESION" id="TBK_ID_SESION" value="" size="40" /><br>
		   	   
		<input type="submit" name="submit" id="submitcierre" value="Probar Cierre" />	   	  
	</form>

</f:view>
</body>
</html>