//=========================================
// MODAL CFG - Magnific Popup http://dimsemenov.com/plugins/magnific-popup/
//=========================================

$(document).ready(function() {
	$('.modal-open').magnificPopup({
	  type:'inline',
	  showCloseBtn: false,
	  midClick: true,
	  mainClass: 'animate-modal' //add class on display
	});

	$('.modal-close').click(function(e) {
		$.magnificPopup.close();
		e.preventDefault();
	});
});