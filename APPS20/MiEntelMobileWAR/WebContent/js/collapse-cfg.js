//=========================================
// COLLAPSE CFG - jQuery Collapse https://github.com/danielstocks/jQuery-Collapse
//=========================================

$(".collapse").collapse({
	accordion: true,
 	//query: 'div h4',
 	clickQuery: ".collapse-open",
	open: function() {
		this.slideDown(150);
	},
	close: function() {
		this.slideUp(150);
	}
});	