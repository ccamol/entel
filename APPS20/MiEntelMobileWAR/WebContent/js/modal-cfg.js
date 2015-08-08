//=========================================
// jAlert2 CFG - 
//=========================================

function popupAlert(texto){
	if (typeof appMobile === "undefined" || !appMobile){
		$.fn.jAlert({
			'message': texto,
			'btn': [
					{'label':'OK', 'cssClass': 'undefined', 'closeOnClick': true }
				],
			'closeBtn': false,
			'size': 'small',
			'clickAnywhere': true
		});	
	}else{
		if (navigator.userAgent.match(/(iPod|iPhone|iPad)/)) {
	 		window.location = "http://error?msg="+encodeURIComponent(texto);
		}else{
			window.Android.errorPopup(texto);
		}
	}
}

