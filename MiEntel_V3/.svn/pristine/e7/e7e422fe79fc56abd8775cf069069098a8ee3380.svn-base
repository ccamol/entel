/*--------------------------------
 *         OBJETO WAPPUSH
 *-------------------------------*/
var wappush = {
    version: '1.3.0',

    // Una peticion a la vez.
    enProceso: false,

    tempForm: '',
    llamarFlash: function(el) {
	
            var num = $('.inputBox').val();

            if(validar(num) && (num.length == 8)){
                $('#msisdn').val(num);
                this.tempForm = this.fn.findParentNode('formularioWappush', el);
                this.writeSWFObject();
                return false;
            }else{
            	$('#respuestaSMS').html(
            		"<div class='mensaje-alerta-sistema-pequeno'>" +
            			"<div class='clearfix sub-contenedor'>" +
            				"<div class='contenedor-imagen'>" + 
            					"<div class='imagen'></div>" +
            				"</div>" +
	            			"<div class='texto'>" +
	            				"<span>Te equivocaste al ingresar el n&uacute;mero, reint&eacute;ntalo.</span>" +
	            			"</div>" +
	            		"</div>" +
            		"</div>");
                $('#respuestaSMS').show();                
                return false;
            }

    },

    /*---------------------*/
    /* INYECCION DEL PROXY */
    writeSWFObject: function() {
    	
        if(document.getElementById('proxy')) {
            var proxy = document.getElementById('proxy');
            proxy.parentNode.removeChild(proxy);
        }

        var div = document.createElement('div');
        div.id = 'proxyContent';
        document.body.appendChild(div);

        var flashvars = {
            serviceUrl: this.tempForm.action,
            msisdn: this.tempForm.msisdn.value,
            idCampana: this.tempForm.idCampana.value
        };
        var params = {
            allowScriptAccess: "always"
        };
        var attributes = {
            id: "proxy",
            allowScriptAccess: "always"
        };
        
        swfobject.embedSWF("http://wappush.entelpcs.cl/proxy.swf", "proxyContent", "1", "1", "8.0.0","http://www.entelpcs.cl/services/wap_push/expressInstall.swf", flashvars, params, attributes);
    },

    getFlashMovie: function(movieName) {
        var isIE = navigator.appName.indexOf("Microsoft") != -1;
        return (isIE) ? window[movieName] : document[movieName];
    },

    /*---------*/
    /* HELPERS */
    fn: {
        findParentNode: function(parentName, childObj) {
            return document.getElementById(parentName);
        },

        getElementsByClassName: function(className, el) {
            if(!el) el = document.body;
            var results = [];
            this.walkTheDOM(el, function(node) {
                var a, c = node.className, i;
                if(c) {
                    a = c.split(' ');
                    for(i=0; i<a.length; i++) {
                        if(a[i] === className) {
                            results.push(node);
                            break;
                        }
                    }
                }
            });
            return results;
        },

        walkTheDOM: function(node, func) {
            func(node);
            node = node.firstChild;
            while(node) {
                this.walkTheDOM(node, func);
                node = node.nextSibling;
            }
        }
    }
};

/*--------------------------------
 *      Respuestas del Proxy
 *-------------------------------*/
function handleSuccesMessage(message) {
        $('#respuestaSMS').show();
        var textoOk = $("#mensajeExitoSMS").val();
        $('#respuestaSMS').html(
        	"<div class='mensaje-exito'>" +
        		"<div class='clearfix sub-contenedor'>" +
        			"<div class='contenedor-imagen'>" +
		            	"<div class='imagen'></div>" +
		            "</div>" +
        			"<div class='texto2'><span>" + textoOk +"</span></div>" +
        		"</div>" +
        	"</div>");
    //wappush.mostrarExito(message);
}

function handleErrorMessage(message) {
        $('#respuestaSMS').show();
        var textoError = $("#mensajeErrorSMS").val();
        $('#respuestaSMS').html(
        	"<div class='mensaje-error-pequeno'>" +
        		"<div class='clearfix sub-contenedor'>" +
	        		"<div class='contenedor-imagen'>" + 
						"<div class='imagen'></div>" +
					"</div>" +
        			"<div class='texto'><span>" + textoError +"</span></div>" +
        		"</div>" +
        	"</div>");
    //wappush.mostrarError(message);
}

function sendFlashVariable() {
	alert("sendFlashVariable");
    var text = wappush.tempForm.msisdn.value;
       wappush.getFlashMovie("proxy").doHTTPRequest(text);
}

function validar(texto){
    if(/^[0-9]+$/.test(texto)){
        return true;
    }

    return false;
}

