//=========================================
// CHART CFG - chart.js http://www.chartjs.org/docs/
//=========================================

var dayCount = [
		{
			value: 19,
			color:"#00529f"
		},
		{
			value : 30-19,
			color : "#cfdced"
		}				
	];

	var chartOptions = {
		//Boolean - Whether we should show a stroke on each segment
		segmentShowStroke : false,				
		
		//The percentage of the chart that we cut out of the middle.
		percentageInnerCutout : 74,	
		
		//String - Animation easing effect
		animationEasing : "easeOutQuart"	
	};


var dayChart1 = new Chart(document.getElementById("dayChart1").getContext("2d")).Doughnut(dayCount, chartOptions);