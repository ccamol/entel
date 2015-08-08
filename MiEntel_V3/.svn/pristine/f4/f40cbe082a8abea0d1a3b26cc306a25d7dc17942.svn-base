<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="it" uri="http://i2b.cl/jsf/iterator/" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Contenido</title>

<script type="text/javascript">
function printDiv()
{
  var divToPrint=document.getElementById('informe');
  var newWin = window.open('','Print-Window','width=800,height=600');
  newWin.document.open();
  newWin.document.write('<html><body onload="window.print()">'+divToPrint.innerHTML+'</body></html>');
  newWin.document.close();
  setTimeout(function(){newWin.close();},10);
}
 </script>

</head>

<f:view beforePhase="#{equipoController.obtenerInformeTecnico}">

<body bgcolor="#FFFFFF">				
		
		<div style="position:absolute;left:8px;top:40px;width:98%;height:80%;background-color:#ffffff;overflow:auto;" id="informe">
		
		<h:panelGroup rendered="#{equipoController.informeTecnicoOT != null}">
		
        <table width='100%' border='0' cellspacing='0' cellpadding='0' class='normalinf'>
            <tr><td class='EncabezadoInformeTecnico'>SUBGERENCIA SOPORTE T&Eacute;CNICO A CLIENTES</td></tr>
            <tr><td class='EncabezadoInformeTecnico'>Entel Telecomunicaciones S.A.</td></tr>
            <tr><td class='EncabezadoInformeTecnico'>RUT: 96.806.980-2</td></tr>
            <tr><td class='EncabezadoInformeTecnico'>Giro: Telecomunicaciones</td></tr>
        </table>

        <br>
        <br>
        <table width='100%' border='0' cellspacing='0' cellpadding='0' class='tit'>
            <tr><td class="InformeTecnico"><u> INFORME T&Eacute;CNICO</u></td></tr>
            <tr><td class="InformeTecnico" align='right'>Santiago, <h:outputText value="#{equipoController.fechaActual}" /></td></tr>
        </table>

        <br>
        <table width='100%' border='0' cellspacing='0' cellpadding='0' class='normalinf'>
            <tr>
                <td width='20%' class="normalInf">Ref.</td>
                <td width='10%' class="normalInf">:</td>
                <td width='70%' class="normalInf"><h:outputText value="#{equipoController.informeTecnicoOT.resolucionLaboratorio}" /></td>
            </tr>
            <tr>
                <td width='20%' class="normalInf">OT</td>
                <td width='10%' class="normalInf">:</td>
                <td width='70%' class="normalInf"><h:outputText value="#{equipoController.informeTecnicoOT.nroOrden}" /></td>
            </tr>
            <tr>
                <td width='20%' class="normalInf">Serie F&iacute;sica</td>
                <td width='10%' class="normalInf">:</td>
                <td width='70%' class="normalInf"><h:outputText value="#{equipoController.informeTecnicoOT.nroserie}" /></td>
            </tr>
        </table>

        <br>
        <br>
        <table width='100%' border='0' cellspacing='0' cellpadding='0' class='normalinf'>
            <tr>
                <td align='left' class="normalInf"><dd>Estimado Cliente, despu&eacute;s de haber realizado un an&aacute;lisis a su equipo
                <h:outputText value="#{equipoController.informeTecnicoOT.marca}" /> modelo <h:outputText value="#{equipoController.informeTecnicoOT.modelo}" /> se diagnostico que este presentaba(n) el(los) siguiente(s) defecto(s) : </dd></td>
            </tr>
        </table>

        <br>
        <table width='100%' border='0' cellspacing='0' cellpadding='0' class='normalinf'>                        
                        <it:iterator value="#{equipoController.informeTecnicoOT.defectos}" rowIndexVar="index" var="object">
                            <tr>
                               <td><dd><li><h:outputText value="#{object.descDefecto}" /></li><dd></dd></dd><br></td>
                            </tr>
                        </it:iterator>                        
			
        </table>

        <br>
        <table width='100%' border='0' cellspacing='0' cellpadding='0' class='normalinf'>
            <tr>
                <td class="normalInf"><dd>Para reparar el(los) defecto(s) encontrado(s) se efectu&oacute; : </dd></td>
            </tr>
        </table>

        <br>
        <table width='100%' border='0' cellspacing='0' cellpadding='0' class='normalinf'>
                <it:iterator value="#{equipoController.informeTecnicoOT.reparaciones}" rowIndexVar="index" var="object">
                    <tr>
                       <td><dd><dd><li><h:outputText value="#{object.descReparacion}" /></li></dd></dd><br></td>
                    </tr>
                </it:iterator>
        </table>

        <br>
        <table width='100%' border='0' cellspacing='0' cellpadding='0' >
            <tr><td class='normalinf' class="normalInf"><dd>Esta reparaci&oacute;n <h:outputText value="#{equipoController.informeTecnicoOT.garantia}" /> se encuentra cubierta por la garant&iacute;a. </dd></td></tr>
        </table>

        <br>
        <br>
        <table width='100%' border='0' cellspacing='0' cellpadding='0' >
            <tr>
                <td class='normalinf' class="normalInf"><dd>Cabe hacer menci&oacute;n que la garant&iacute;a  Entel cubre los desperfectos que
                puedan tener tanto el tel&eacute;fono como accesorios durante la vigencia de la garant&iacute;a, exceptuando
                el daño ocasionado. </dd></td>
            </tr>
        </table>

        <br>
        <br>
        <table width='100%' border='0' cellspacing='0' cellpadding='0' >
            <tr>
                <td class="normalInf"><dd>El presente informe confirma lo expuesto seg&uacute;n O. De Trabajo N° <h:outputText value="#{equipoController.informeTecnicoOT.nroOrden}" /> </dd></td>
            </tr>
        </table>
        <br>
        <br>
        <table width='100%' border='0' cellspacing='0' cellpadding='0' >
            <tr><td class="normalInf">Sin  otro Particular. Atte,</td></tr>
        </table>

        <br>
        <br>
        <table width='100%' border='0' cellspacing='0' cellpadding='0' >
            <tr><td class="normalInf"><h:outputText value="#{equipoController.informeTecnicoOT.piePagina1}" /></td></tr>
            <tr><td class="normalInf"><h:outputText value="#{equipoController.informeTecnicoOT.piePagina2}" /></td></tr>
        </table>
		
		</h:panelGroup>
		
		<h:panelGroup rendered="#{equipoController.informeTecnicoOT == null}">
			<div class="centro" id="centro">
			<div id="msgP">
					<div class="caja verde clearfix">
						<div class="caja_texto" id="contactoBoxP">									
						<p><strong>Informe T&eacute;cnico</strong><br>No existe un informe t&eacute;cnico asociado a su numero de orden.</p>
						</div>
					</div>
			</div>			
			</div>
		
		</h:panelGroup>
    
    </div>
    
    <div align="center" style="position:absolute;left:8px;top:520px;">
		<div class="TB_botones">
				<a href="javascript:printDiv();" class="btnAzulGrande btnAzulGrandeLargo TB_boton"><span><font color="white">Imprimir</font></span></a>
			</div>
		</div>
    
    </body>
    
   </f:view>
   
   
   </html>