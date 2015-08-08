//=========================================
// SIDEBAR CFG - Snap.js https://github.com/jakiestfu/Snap.js
//=========================================

// Initialize
var snapper = new Snap({
    element: document.getElementById('main'),
    disable: 'right'
});

// Toggle menu button
var addEvent = function addEvent(element, eventName, func) {
	if (element.addEventListener) {
    	return element.addEventListener(eventName, func, false);
    } else if (element.attachEvent) {
        return element.attachEvent("on" + eventName, func);
    }
};
	/* Main button */
	addEvent(document.getElementById('menu-button'), 'click', function(){
	    if( snapper.state().state=="left" ){
	        snapper.close();
	    } else {
	        snapper.open('left');
	    }
	});


/* Prevent Safari opening links when viewing as a Mobile App */
(function (a, b, c) {
    if(c in b && b[c]) {
        var d, e = a.location,
            f = /^(a|html)$/i;
        a.addEventListener("click", function (a) {
            d = a.target;
            while(!f.test(d.nodeName)) d = d.parentNode;
            "href" in d && (d.href.indexOf("http") || ~d.href.indexOf(e.host)) && (a.preventDefault(), e.href = d.href)
        }, !1)
    }
})(document, window.navigator, "standalone");