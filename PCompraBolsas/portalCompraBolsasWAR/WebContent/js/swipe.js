jQuery(document).ready(function($) {

	$(".planes-cont").each(function(index) {
		$(this).click(function() {
			if(!$(this).hasClass("off")){
			if($(this).parent().hasClass("duh")){
				//alert("listo");
				$(this).animate({left: "0"}, "slow");
				$(this).parent().removeClass("duh");
			}else{
				$(this).animate({left: "-68%"}, "slow");
			$(this).parent().addClass('duh');
			$(this).parent().find(".planes-cont-b").fadeIn("slow");
			}
			

			if ($(".planes-cont").parent().hasClass("duh")) {
				$(".planes-list-item.duh .planes-cont").not(this).animate({
					left: "0"
				}, "slow");
				$(".planes-cont").not(this).parent().removeClass("duh");
			}
			}else{
				//alert("bloqueado");
			}
		});
	});

	//$(".planes-cont-b").each(function(index) {
	//	$(this).click(function() {
	//		$(this).siblings('.planes-cont').animate({
	//			left: "0"
	//		}, "slow");
	//		$(this).parent().addClass('duh');
	//	});
	//});

	$(".planes-cont.off").each(function(index) {
		$(this).click(function() {
			$(this).stop();
			$(this).children().stop();
		})
	});

});