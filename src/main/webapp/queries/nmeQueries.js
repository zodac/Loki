function numberOfFailuresAndDuration() {
	var fromDate = document.forms["nmequery"]["from"].value;
	var toDate = document.forms["nmequery"]["to"].value;

	if (new Date(fromDate) > new Date(toDate)) {
		clearResult();
		clearChart("scatterplot");
		alertify.error("Invalid date range!");
		document.forms["nmequery"]["from"].focus();
	} else if(new Date(fromDate) > new Date()){
		clearResult();
		clearChart("scatterplot");
		alertify.error("Invalid date range!");
	    document.forms["nmequery"]["from"].focus();
	} else {
		var results = makeJSONObject("./../../webservice/NMEQueries/FD/"
				+ fromDate + "/" + toDate);
		alertify.success(results.length + " results loaded!");
		
		var table = document.createElement("table");
		table.setAttribute("class", "table table-striped table-bordered");
		table.setAttribute("id", "datatablehtml");

		var tbody = document.createElement("tbody");
		var thead = document.createElement("thead");
		var tr = document.createElement("tr");
		var th = document.createElement("th");
		th.appendChild(document.createTextNode("IMSI"));
		tr.appendChild(th);
		th = document.createElement("th");
		th.appendChild(document.createTextNode("Number of failures"));
		tr.appendChild(th);
		th = document.createElement("th");
		th.appendChild(document.createTextNode("Total Duration (ms)"));
		tr.appendChild(th);
		thead.appendChild(tr);
		table.appendChild(thead);
		table.appendChild(tbody);
		document.getElementById("queryresult").appendChild(table);


		var dataForChart = [];
		var completearray = [];

		$.each(results, function(key, value) {
			var array = [];
			array.push(value.imsi);
			array.push(value.count);
			array.push(value.numofFailures);
			completearray.push(array);
			
			// for the tool-tips when count and duration are the same, entries
			// are on top of each other
			$.each(dataForChart, function() {
				if (this.x == value.count && this.y == value.numofFailures) {
					this.tooltip = this.tooltip + '<br />IMSI: ' + value.imsi;
				}
			});
			dataForChart.push({
				x : value.count,
				y : value.numofFailures,
				tooltip : 'Failures: ' + value.count + '<br />IMSI: ' + value.imsi 
			});

		});

		$(document).ready(function() {
			$('#datatablehtml').dataTable({
		    	"aaData": completearray
		    });
			chart = new Highcharts.Chart({
				chart : {
					renderTo : 'scatterplot',
					type : 'scatter'
				},
				xAxis : {
					title : {
						enabled : true,
						text : 'Number of Call Failures'
					},
					startOnTick : true,
					endOnTick : true,
					showLastLabel : true
				},
				yAxis : {
					min : 0,
					title : {
						text : 'Total Duration',
						align : 'middle'
					},
					labels : {
						overflow : 'justify',
						format : '{value} ms'
					}
				},
				title : {
					text : 'Number of failures of each IMSI with their combined duration'
				},
				plotOptions : {
					scatter : {
						marker : {
							radius : 5,
							states : {
								hover : {
									enabled : true,
									lineColor : 'rgb(100,100,100)'
								}
							}
						},
						states : {
							hover : {
								marker : {
									enabled : false
								}
							}
						}
					}, 
					series: {
	                    turboThreshold: 25000
	                }
				
				},
				tooltip : {
					formatter : function() {
						return this.point.tooltip;
					}
				},
				 credits: {
				      enabled: false
				 },
				series : [ {
					showInLegend : false,
					data : dataForChart
				} ]
			});
		});
	}
}

function uniqueEventCauseAndOccurancesByModel() {
	var modelInput = document.forms["nmequery"]["model"].value;
	var model = makeJSONObject("./../../webservice/Phones/" + modelInput);
	var dataForChart = [];

	if (model != "") {
		var results = makeJSONObject("./../../webservice/NMEQueries/" + model.tac);
		alertify.success(results.length + " results loaded!");

		var div = document.createElement("div");
		div.setAttribute("style", "max-height: 400px; overflow: auto;");
		var table = document.createElement("table");
		table.setAttribute("id", "tabledata");
		table.setAttribute("class", "table table-striped table-bordered");

		var tbody = document.createElement("tbody");
		var thead = document.createElement("thead");
		var tr = document.createElement("tr");
		var th = document.createElement("th");
		th.appendChild(document.createTextNode("Event ID"));
		tr.appendChild(th);
		th = document.createElement("th");
		th.appendChild(document.createTextNode("Cause Code"));
		tr.appendChild(th);
		th = document.createElement("th");
		th.appendChild(document.createTextNode("Occurrences"));
		tr.appendChild(th);
		thead.appendChild(tr);
		table.appendChild(thead);
		
		var chart = $('#container').highcharts();;

		 function toggleElement(index){
		 		$.each(chart.series[0].data, function(key, value){
		 			if(value.idForClick === index){
		 				this.select(); 
		 			}
		 		});    
		 }

		$.each(results, function(key, value) {
			var row = document.createElement("tr");
			var idText = '' + value.event_ID + value.cause_Code;
			row.setAttribute('id', idText);
			row.setAttribute('class', 'more');
			
			var cell = document.createElement("td");
			cell.appendChild(document.createTextNode(value.event_ID));
			row.appendChild(cell);

			cell = document.createElement("td");
			cell.appendChild(document.createTextNode(value.cause_Code));
			row.appendChild(cell);

			cell = document.createElement("td");
			cell.appendChild(document.createTextNode(value.occurrences));
			row.appendChild(cell);

			tbody.appendChild(row);

			var cause = "Cause Code: " + value.cause_Code;
			var evt = "Event ID: " + value.event_ID;
			dataForChart.push({
				causeName : cause,
				evtName : evt,
				y : value.occurrences,
				idForClick : idText
			});
		});
		table.appendChild(tbody);

		div.appendChild(table);
		document.getElementById("queryresult").appendChild(div);

		// chart starts here
		$(document).ready(
				function() {
					chart = new Highcharts.Chart({
						chart : {
							renderTo : 'causeContainer',
							plotBackgroundColor : null,
							plotBorderWidth : null,
							plotShadow : false
						},
						title : {
							text : 'Percentage of event id and cause code occurances of ' + modelInput
						},
						tooltip : {
							formatter : function() {
								return this.point.evtName + '<br />'
										+ this.point.causeName + '<br />'
										+ 'Occurrences: ' + this.y;
							}
						},
						plotOptions : {
							pie : {
								allowPointSelect : true,
								cursor : 'pointer',
								dataLabels : {
									enabled : true,
									formatter : function() {
										return Highcharts.numberFormat(
												this.percentage, 2)
												+ '%';
									}
								},
								showInLegend : false
							}
						},
						 credits: {
						      enabled: false
						 },
						series : [ {
							type : 'pie',
							data : dataForChart
						} ]
					});
					$(".more").click(function(){
			            toggleElement($(this).attr('id'));
			        });
					$("#tabledata tbody tr").click(function() {
					    var selected = $(this).hasClass("highlight");
					    $("#tabledata tbody tr").removeClass("highlight");
					    if(!selected){
					            $(this).addClass("highlight");
					    }
					});
				});
	} else {
		clearResult();
		clearChart("causeContainer");
		alertify.log("Invalid phone model!");
		document.forms["nmequery"]["model"].focus();
	}
}

function topMOCGraph() {
	var newArrayForDrillingInto  =[];
	var results = makeJSONObject("./../../webservice/NMEQueries/FailClass");
		$.each(results, function(key, value){
			newArrayForDrillingInto.push([value.cellId, value.operator, value.failureClass, value.occurences]);	
		});

	
	var valuesInDrillDown = [];
	var dataForChart = [];
	var dataForDrillDown = [];
	var categories = [];
	Highcharts.setOptions({
	    lang: {
	        drillUpText: '<--- Back to Top 10 Nodes'
	    }
	});
	
	$.getJSON("./../../webservice/NMEQueries", function(data) {
		$.each(data, function(key, value){
			var cell = value.cellId;
			var op = value.operator;
			valuesInDrillDown = [];
			categories.push(value["ofAllFailures"]);
			
			for (var i = 0; i < newArrayForDrillingInto.length; i++) {
				if (newArrayForDrillingInto[i][0] === cell && newArrayForDrillingInto[i][1] === op){
					var name = "Failure Class " + newArrayForDrillingInto[i][2] + " : " + returnFailureDesc(newArrayForDrillingInto[i][2]);
					console.log(name);
					valuesInDrillDown.push([name, newArrayForDrillingInto[i][3]]);
				}
			}
			dataForChart.push({
				name : value.cellId + ", " + value.country + ", " + value.operator,
				y : value.numberOfFailures,
				drilldown : key + '',
				mydata : 'inchart'
			});
			dataForDrillDown.push({
				id : key + '',
				data : valuesInDrillDown,
				mydata : ' '
			});
		});
		$(document).ready(function() {
			chart = new Highcharts.Chart({
				chart : {
					renderTo : 'queryresult',
					type : 'column',
					plotBackgroundColor : null,
					plotBorderWidth : null,
					plotShadow : false
				},
				title : {
					text : null
				},
				tooltip : {
					formatter : function() {
						if(this.point.mydata === 'inchart'){
							return '% Of All Failures:<b>' + categories[this.x]
								+ '</b><br/>' + 'Number Of Failures:<b>'
								+ this.y + '</b>';
						}
						else{
							return 'Number Of Failures:<b>' + this.y + '</b>';
						}
					}
				},
				xAxis : {
					categories : []
				},
				yAxis : {
					min : 0,
					title : {
						text : 'Number Of Failures'
					}
				},
				credits : {
					enabled : false
				},
				legend: {
	                    enabled: false
	            },
				series : [{
					showInLegend : false,
					data : dataForChart
				}],
				drilldown : {
					showInLegend : false,
					series : dataForDrillDown
				}
			});
		});	
	});
}

function topMOC() {
	var fromDate = document.forms["nmequery"]["from"].value;
	var toDate = document.forms["nmequery"]["to"].value;
	var newArrayForDrillingInto  =[];
	var valuesInDrillDown = [];
	var dataForChart = [];
	var dataForDrillDown = [];
	//var categories = [];
	
	Highcharts.setOptions({
	    lang: {
	        drillUpText: '<--- Back to Top 10 Nodes'
	    }
	});
	$.getJSON("./../../webservice/NMEQueries/FailClass/"+ fromDate + "/" + toDate, function(data){
		$.each(data, function(key, value){
			newArrayForDrillingInto.push([value.cellId, value.operator, value.failureClass, value.occurences]);	
		});
	});

	if (new Date(fromDate) > new Date(toDate)) {
		clearResult();
		clearChart("chartContainer");
		alertify.error("Invalid date range!");
		document.forms["nmequery"]["from"].focus();
	} else if(new Date(fromDate) > new Date()){
		clearResult();
		clearChart("chartContainer");
		alertify.error("Invalid date range!");
	    document.forms["nmequery"]["from"].focus();
	} else {
		var results = makeJSONObject("./../../webservice/NMEQueries/"
				+ fromDate + "/" + toDate);
		alertify.success(results.length + " results loaded!");

		var table = document.createElement("table");
		table.setAttribute("class", "table table-striped table-bordered");
		table.setAttribute("id", "datatablehtml");
		
		var tbody = document.createElement("tbody");
		var thead = document.createElement("thead");
		var tr = document.createElement("tr");
		var th = document.createElement("th");
		th.appendChild(document.createTextNode("Rank"));
		tr.appendChild(th);
		th = document.createElement("th");
		th.appendChild(document.createTextNode("Cell ID"));
		tr.appendChild(th);
		th = document.createElement("th");
		th.appendChild(document.createTextNode("Market"));
		tr.appendChild(th);
		th = document.createElement("th");
		th.appendChild(document.createTextNode("Operator"));
		tr.appendChild(th);
		th = document.createElement("th");
		th.appendChild(document.createTextNode("Failures"));
		tr.appendChild(th);
		
		thead.appendChild(tr);
		table.appendChild(thead);
		table.appendChild(tbody);
		document.getElementById("queryresult").appendChild(table);

		var counter = 1;
		var completearray = [];
		//var categories = [];

		$.each(results, function(key, value) {
			var array = [];
			array.push(counter);
			array.push(value.cellId);
			array.push(value.country);
			array.push(value.operator);
			array.push(value.numberOfFailures);
			completearray.push(array);
			counter++;
		});
		$.getJSON("./../../webservice/NMEQueries/"
				+ fromDate + "/" + toDate, function(data){
		$.each(data, function(key, value){
			var cell = value.cellId;
			
			var op = value.operator;
			valuesInDrillDown = [];
			
			for (var i = 0; i < newArrayForDrillingInto.length; i++) {
				if (newArrayForDrillingInto[i][0] === cell && newArrayForDrillingInto[i][1] === op){
					var name = "Failure Class " + newArrayForDrillingInto[i][2] + " : " + returnFailureDesc(newArrayForDrillingInto[i][2]);
					console.log(name);
					valuesInDrillDown.push([name, newArrayForDrillingInto[i][3]]);
				}
			}
			dataForChart.push({
				name : value.cellId + ", " + value.country + ", " + value.operator,
				y : value.numberOfFailures,
				drilldown : key + '',
				mydata : 'inchart'
			});
			dataForDrillDown.push({
				id : key + '',
				data : valuesInDrillDown,
				mydata : ' '
			});
		});
		$(document).ready(function() {
			$('#datatablehtml').dataTable({
		    	"aaData": completearray
		    });
			chart = new Highcharts.Chart({
				chart : {
					renderTo : 'chartContainer',
					type : 'column',
					plotBackgroundColor : null,
					plotBorderWidth : null,
					plotShadow : false
				},
				title : {
					text : 'Top 10 Market/Operator/Cell Nodes for a given time period'
				},
				tooltip : {
					formatter : function() {
							return 'Number Of Failures:<b>' + this.y + '</b>';
					}
				},
				xAxis : {
					categories : []
				},
				yAxis : {
					min : 0,
					title : {
						text : 'Number Of Failures'
					}
				},
				credits : {
					enabled : false
				},
				legend: {
	                    enabled: false
	            },
				series : [{
					showInLegend : false,
					data : dataForChart
				}],
				drilldown : {
					showInLegend : false,
					series : dataForDrillDown
				}
			});

		});
		});
	}
}

function topIMSIs() {
	var dataForChart = [];
	var dataForDrillDown = [];
	var valuesInDrillDown = [];
	var newArrayForDrillingInto  =[];
	var fromDate = document.forms["nmequery"]["from"].value;
	var toDate = document.forms["nmequery"]["to"].value;
	
	Highcharts.setOptions({
	    lang: {
	        drillUpText: '<--- Back to Top 10 IMSI\'s'
	    }
	});
	
	var r = makeJSONObject("./../../webservice/NMEQueries/IMSIFailClass/"+ fromDate + "/" + toDate);
	
	//$.getJSON("./../../webservice/NMEQueries/IMSIFailClass/"+ fromDate + "/" + toDate, function(results){
	$.each(r, function(key, value){
		newArrayForDrillingInto.push([value.count, value.failureClass, value.imsi]);	
	});
	//});
	


	if (new Date(fromDate) > new Date(toDate)) {
		clearResult();
		clearChart("chartContainer");
		alertify.error("Invalid date range!");
		document.forms["nmequery"]["from"].focus();
	} else if(new Date(fromDate) > new Date()){
		clearResult();
		clearChart("chartContainer");
		alertify.error("Invalid date range!");
	    document.forms["nmequery"]["from"].focus();
	} else {
		var results = makeJSONObject("./../../webservice/NMEQueries/IMSI/"
				+ fromDate + "/" + toDate);
		alertify.success(results.length + " results loaded!");

		var table = document.createElement("table");
		table.setAttribute("class", "table table-striped table-bordered");
		table.setAttribute("id", "datatablehtml");

		var tbody = document.createElement("tbody");
		var thead = document.createElement("thead");
		var tr = document.createElement("tr");
		var th = document.createElement("th");
		th.appendChild(document.createTextNode("Rank"));
		tr.appendChild(th);
		th = document.createElement("th");
		th.appendChild(document.createTextNode("IMSI"));
		tr.appendChild(th);
		th = document.createElement("th");
		th.appendChild(document.createTextNode("Number of failures"));
		tr.appendChild(th);
		thead.appendChild(tr);
		table.appendChild(thead);
		table.appendChild(tbody);
		document.getElementById("queryresult").appendChild(table);

		var counter = 1;
		var completearray = [];
		
		$.each(results, function(key, value) {
			var array = [];
			array.push(counter);
			array.push(value.imsi);
			array.push(value.numofFailures);
			completearray.push(array);
			
			counter++;
		});
		$.getJSON("./../../webservice/NMEQueries/IMSI/"+ fromDate + "/" + toDate, function(data){
			$.each(data, function(key, value) {
				valuesInDrillDown = [];
				var imsi = value.imsi;

						for (var i = 0; i < newArrayForDrillingInto.length; i++) {
							if (newArrayForDrillingInto[i][2] === imsi) {
								var name = "Failure Class " + newArrayForDrillingInto[i][1] + ": " + returnFailureDesc(newArrayForDrillingInto[i][1]);
								valuesInDrillDown.push([name, newArrayForDrillingInto[i][0]]);
							}
						}
				
				dataForChart.push({
					name : " " + value.imsi,
					y : value.numofFailures,
					drilldown : key + '',
					mydata : 'chartdata'
				});
				dataForDrillDown.push({
					id : key + '',
					data : valuesInDrillDown
				});
			});

		$(document).ready(function() {
			$('#datatablehtml').dataTable({
		    	"aaData": completearray
		    });
			chart = new Highcharts.Chart({
				chart : {
					renderTo : 'chartContainer',
					type : 'pie',
					plotBackgroundColor : null,
					plotBorderWidth : null,
					plotShadow : false
				},
				title : {
					text : 'Top 10 IMSIs in a given time period'
				},
				tooltip : {
					formatter : function() {
							return 'Number Of Failures:<b>' + this.y + '</b>';
					}
				},
				xAxis : {
					type: 'category',
		            showEmpty: false,
					labels: {
	                    overflow: 'justify'
	               },
	               title:{
	               	 margin: 25,
	               	text : 'IMSI'
	               }
				},
				yAxis : {
					min : 0,
					title : {
						text : 'Number Of Failures'
					}
				},
				plotOptions: {
		            series: {
		                borderWidth: 0,
		                dataLabels: {
		                    enabled: true,
		                }
		            }
				},
				credits : {
					enabled : false
				},
				legend: {
	                    enabled: true
	            },
				series : [{
					showInLegend : false,
					data : dataForChart,
					type: 'bar'
				}],
				drilldown : {
					showInLegend : true,
					series : dataForDrillDown
				}
			});
		});
	});
	}
}

function createHead(t1, t2, t3) {
	var thead = document.createElement("thead");
	var th = document.createElement("th");
	th.appendChild(document.createTextNode(t1));
	thead.appendChild(th);
	th = document.createElement("th");
	th.appendChild(document.createTextNode(t2));
	thead.appendChild(th);
	th = document.createElement("th");
	th.appendChild(document.createTextNode(t3));
	thead.appendChild(th);
	return thead;
}

function makeJSONObject(location) {
	var request = new XMLHttpRequest();
	request.open("GET", location, false);
	request.send(null);

	clearResult();

	if (request.responseText != "") {
		return eval("(" + request.responseText + ")");
	} else {
		return "";
	}
}

function clearResult() {
	var mainNode = document.getElementById("queryresult");
	while (mainNode.lastChild) {
		mainNode.removeChild(mainNode.lastChild);
	}
}

function clearChart(chartName) {
	var mainNode = document.getElementById(chartName);
	while (mainNode.lastChild) {
		mainNode.removeChild(mainNode.lastChild);
	}
}

function returnFailureDesc(numClass){
	if(numClass === 0){
		return "EMERGENCY";
	}
	else if(numClass === 1){
		return "HIGH PRIORITY ACCESS";
	}
	else if(numClass === 2){
		return "MT ACCESS";
	}
	else if(numClass === 3){
		return "MO SIGNALLING";
	}
	else{
		return "MO DATA";
	}
}