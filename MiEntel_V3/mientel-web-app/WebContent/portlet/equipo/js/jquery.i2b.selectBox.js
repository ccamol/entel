/*
*SelectBox V2.0
*/
$.fn.selectBox = function(options){
    //Opciones Generales
    defaults = {        	
            img_input				: '',
        	img_input_error 		: '',
        	img_select_arrow		: '',
        	img_select_arrow_error  : '',
        	height					: '20px',
        	font_size				: '12px',
        	whidth_arrow			: 21,					
            color_input				: '#b1afb1'
            
    }
    var optiones = $.extend({}, defaults, options);
	var zIndex = 120;
    return this.each(function(){
        var w = $(this).css('width');
        var h = $(this).css('height');
        w = (/[0-9]*px/i.test(w)) ? parseInt(w) : 200;
        h = (/[0-9]*px/i.test(h)) ? parseInt(h) : 26;       
        var opt = {
            'border': 'none',
            'background': 'transparent',
            'height': optiones.height,
            'width': w,
            'textAlign': 'left',
			'fontSize': optiones.font_size,
            'paddingLeft': '2px'
        }
        
        if ($(this).attr('opt')) {
            $.extend(opt, eval('(' + $(this).attr('opt') + ')'));
        }
        
        var select = $(this).hide();
        var options = select[0].options;
        var selectSpan = $(this).find('span');

        var input_s = $('<input readonly="readonly" type="text" />').css({
            'border': opt.border,
            'background': opt.background,
            'color': optiones.color_input,
			'fontSize': opt.fontSize,
            'paddingLeft': opt.paddingLeft
        });
        var container = select.parent();
        
        var css_divs = {
            'float': 'left',
            'backgroundImage': 'url('+ optiones.img_input +')',
            'backgroundRepeat': 'no-repeat',
            'width': 0,
            'height': opt.height
        };
		var css_divs_error = {
            'backgroundImage': 'url('+optiones.img_input_error+')'
		};
        var css_arrow = {
            'backgroundImage': 'url('+optiones.img_select_arrow+')',
            'width': optiones.whidth_arrow,
            'height': opt.height
        };
		var css_arrow_error = {
			'backgroundImage': 'url('+optiones.img_select_arrow_error+')'
		};
        var css_option = {
            'height': 20,
            'width' : opt.width - optiones.whidth_arrow,
            'color': '#b3b1b3',
            'background': '#f5f3f3',
			'border-right' : '1px solid rgb(179, 177, 179)',
            'cursor': 'pointer',
            'fontSize': '11px',
            'verticalAlign': 'middle',
            'fontFamily': 'Arial',
            'paddingLeft': 5,
            'paddingRight': 10
        };

        var css_option_over = {};
        $.extend(css_option_over, css_option)
        css_option_over.color = '#fff';
        css_option_over.background = '#999';
        var drop_down = function(){
            options = select[0].options;

            if (options.length > 0) {
                divl.css('backgroundImage', 'url('+optiones.img_input+')');
                divc.css('backgroundImage', 'url('+optiones.img_input+')');
                diva.css('backgroundImage', 'url('+optiones.img_input+')');
                divr.css('backgroundImage', 'url('+optiones.img_input+')');
				arrow.css('backgroundImage', css_arrow.backgroundImage);
                options_div.empty().show();
                
                if (options.length > 6) 
                    options_div.css({
                        'overflowY': 'scroll',
                        'overflowX': 'hidden',
                        'height': css_option.height * 6
                    })
                var table = $('<table></table>').appendTo(options_div).css('borderCollapse', 'collapse');
                
                $.each(options, function(i, val){
                    var tr = $('<tr></tr>').appendTo(table);
                    var td = $('<td></td>').attr({
                        'align': 'left',
                        'value': val.value
                    }).html(val.text).appendTo(tr).css(css_option).bind('mouseover', function(){
                        $(this).css(css_option_over);
                    }).bind('mouseout', function(){
                        $(this).css(css_option);
                    }).bind('mousedown', function(){
                        select.attr('value', $(this).attr('value'));
                        input_s.val($(this).html());
                        if (select.change) {
                            select.change();
                        }
                    });
                });
               


                
                $(document).bind('mousedown', function(e){
                    if (!$(e.target).is('.options_div')) {
                        /*divl.css('backgroundImage', css_divs.backgroundImage);
                        divc.css('backgroundImage', css_divs.backgroundImage);
                        diva.css('backgroundImage', css_divs.backgroundImage);
                        divr.css('backgroundImage', css_divs.backgroundImage);
						arrow.css('backgroundImage', css_arrow.backgroundImage);*/
                        options_div.hide();
                        input_s.focus();
                        $(document).unbind('mousedown');
                    }
                });
            }
        }
        
        var div = $('<div></div>').appendTo(container).css({
            'zIndex': 50,
            'float': 'left',
            'position': 'relative',
            'height': css_divs.height,/*'width':opt.width,*/
            'cursor': 'pointer',
			'position': 'relative',
			'zIndex': zIndex--
        }).bind('click', drop_down);
        var divl = $('<div></div>').appendTo(div).css(css_divs).css({
            'backgroundPosition': '0px 0px'
        });
        var divc = $('<div></div>').appendTo(div).css(css_divs).css({
            'backgroundPosition': '-' + css_divs.width + 'px 0px',
            'width': opt.width - css_arrow.width - (css_divs.width * 2)
        });
        var diva = $('<div></div>').appendTo(div).css(css_divs).css({
            'backgroundPosition': '-' + css_divs.width + 'px 0px',
            'width': css_arrow.width
        });
        var divr = $('<div></div>').appendTo(div).css(css_divs).css({
            'backgroundPosition': 'right 0px'
        });
        var arrow = $('<div></div>').appendTo(diva).css(css_arrow);
        
        // Solo para centrar verticalmente el input ******
        var table = $('<table></table>').appendTo(divc).css({
            'height': '100%',
            'width': opt.width - css_arrow.width - (css_divs.width * 2)
        });
        var tr = $('<tr></tr>').appendTo(table);
        var td = $('<td style="vertical-align: middle;"></td>').appendTo(tr);
        // *****************
        
        input_s.appendTo(td).css({
            'cursor': 'pointer',
            'fontWeight': 'bold',
            'textAlign': opt.textAlign//,'width': opt.width - css_arrow.width - (css_divs.width * 2)
        }).bind('blur', function(){
            divl.css('backgroundImage', css_divs.backgroundImage);
            divc.css('backgroundImage', css_divs.backgroundImage);
            diva.css('backgroundImage', css_divs.backgroundImage);
            divr.css('backgroundImage', css_divs.backgroundImage);
            options_div.hide();
        });
        
        // Options **********
        var css_options_div = {
            'position': 'absolute',
            'background': '#F5F3F3',
            'width': opt.width,
            'top': 28,
            'left': 0,
            'border': '1px solid #b3b1b3',
            'overflowX': 'hidden',
            'zIndex': 51
        }
        
        var options_div = $('<div></div>').addClass('options_div').appendTo(div).css(css_options_div).hide();
        // ******************
        
        if(options.length > 0) {
            input_s.val(options[0].text);
            select.val(options[0].value);
        }

        var div_disabled = $('<div></div>').hide().appendTo(div).css({
            position: 'absolute',
            top: 0,
            left: 0,
            width: ($.browser.msie && $.browser.version == '6.0') ? opt.width + 4 : opt.width,
            height: opt.height,
            background: '#ccc',
            opacity: 0.7
        });
        
        this.enable = function(){
            div_disabled.hide();
            div.bind('click', drop_down);
        };
        this.disable = function(){
            div_disabled.show().css('cursor', 'default');
            div.unbind('click', drop_down);
        };
        this.setValue = function(v){
            select.attr('value', v);
            input_s.val(select[0].options[select[0].selectedIndex].text);
        };
		
		this.enableError = function() {
			divl.css('backgroundImage', css_divs_error.backgroundImage);
			divc.css('backgroundImage', css_divs_error.backgroundImage);
			diva.css('backgroundImage', css_divs_error.backgroundImage);
			divr.css('backgroundImage', css_divs_error.backgroundImage);
			arrow.css('backgroundImage', css_arrow_error.backgroundImage);
		};
		
		this.disableError = function() {
			divl.css('backgroundImage', css_divs.backgroundImage);
			divc.css('backgroundImage', css_divs.backgroundImage);
			diva.css('backgroundImage', css_divs.backgroundImage);
			divr.css('backgroundImage', css_divs.backgroundImage);
			arrow.css('backgroundImage', css_arrow.backgroundImage);
		};
        
        if (select.attr('disabled')) {
            this.disable();
        }
    });
};


