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
				tooltip : + 'Failures: ' + value.count + '<br />IMSI: ' + value.imsi 
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
	$(document).ready(
			function() {
				var categories = [];
				var options = {
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
							return '% Of All Failures:<b>' + categories[this.x]
									+ '</b><br/>' + 'Number Of Failures:<b>'
									+ this.y + '</b>';
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
					 credits: {
					      enabled: false
					  },
					series : [ {
						showInLegend : false,
					} ]
				};
				$.getJSON("./../../webservice/NMEQueries", function(data) {
					var val1 = [];
					$.each(data, function(key, value) {
						categories.push(value["ofAllFailures"]);
						var yaxis = "cellId: " + value.cellId + ", "
								+ value.country + ", " + value.operator;
						val1.push([ yaxis, value.numberOfFailures ]);
					});
					options.series[0].data = val1;
					new Highcharts.Chart(options);
				});

			});
}

function topMOC() {
	var fromDate = document.forms["nmequery"]["from"].value;
	var toDate = document.forms["nmequery"]["to"].value;

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
		var dataForChart = [];
		var completearray = [];

		$.each(results, function(key, value) {
			var array = [];
			array.push(counter);
			array.push(value.cellId);
			array.push(value.country);
			array.push(value.operator);
			array.push(value.numberOfFailures);
			completearray.push(array);
			counter++;

			var yaxis = "cellId: " + value.cellId + ", " + value.country + ", "
					+ value.operator;
			dataForChart.push([ yaxis, value.numberOfFailures ]);
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
					text : 'Top 10 Market/Operator/Cell Nodes over a duration'
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

function topIMSIs() {
	var fromDate = document.forms["nmequery"]["from"].value;
	var toDate = document.forms["nmequery"]["to"].value;

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
		var dataForChart = [];
		var categories = [];
		var completearray = [];
		$.each(results, function(key, value) {
			var array = [];
			array.push(counter);
			array.push(value.imsi);
			array.push(value.numofFailures);
			completearray.push(array);
			
			counter++;
			dataForChart.push({
				y : value.numofFailures
			});
			categories.push([ value.imsi ]);
		});

		$(document).ready(function() {
			$('#datatablehtml').dataTable({
		    	"aaData": completearray
		    });
			chart = new Highcharts.Chart({
				chart : {
					renderTo : 'chartContainer',
					type : 'bar'
				},
				xAxis : {
					categories : categories,
					title : {
						text : null
					}
				},
				yAxis : {
					min : 0,
					title : {
						text : 'Number Of Failures',
						align : 'middle'
					},
					labels : {
						overflow : 'justify'
					}
				},
				title : {
					text : 'Top 10 IMSIs for a given time period'
				},
				 credits: {
				      enabled: false
				  },
				tooltip : {
					formatter : function() {
						return 'Number Of Failures:<b>' + this.y + '</b>';
					}
				},
				series : [ {
					showInLegend : false,
					data : dataForChart
				} ]
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