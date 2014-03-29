function numberOfFailuresAndDuration() {
	var fromDate = document.forms["nmequery"]["from"].value;
	var toDate = document.forms["nmequery"]["to"].value;
	var results = makeJSONObject("./../../webservice/NMEQueries/FD/" + fromDate
			+ "/" + toDate);

	var div = document.createElement("div");
	div.setAttribute("style", "max-height: 400px; overflow: auto;");
	var table = document.createElement("table");
	table.setAttribute("class", "table table-striped table-bordered");

	var tbody = document.createElement("tbody");
	table.appendChild(createHead("IMSI", "Number of failures",
			"Total Duration (ms)"));

	for ( var i = 0; i < results.length; i++) {
		var row = document.createElement("tr");
		var cell = document.createElement("td");
		cell.appendChild(document.createTextNode(results[i][0]));
		row.appendChild(cell);

		cell = document.createElement("td");
		cell.appendChild(document.createTextNode(results[i][1]));
		row.appendChild(cell);

		cell = document.createElement("td");
		cell.appendChild(document.createTextNode(results[i][2]));
		row.appendChild(cell);

		tbody.appendChild(row);
	}
	table.appendChild(tbody);

	div.appendChild(table);
	document.getElementById("queryresult").appendChild(div);
}

function uniqueEventCauseAndOccurancesByModel() {
	var modelInput = document.forms["nmequery"]["model"].value;
	var model = makeJSONObject("./../../webservice/Phones/" + modelInput);
	var dataForChart = [];

	if (model != "") {
		var results = makeJSONObject("./../../webservice/NMEQueries/"
				+ model.tac);

		var div = document.createElement("div");
		div.setAttribute("style", "max-height: 400px; overflow: auto;");
		var table = document.createElement("table");
		table.setAttribute("class", "table table-striped table-bordered");

		var tbody = document.createElement("tbody");
		table.appendChild(createHead("Event ID", "Cause Code", "Occurrences"));
		
		

		$.each(results, function(key, value) {
			var row = document.createElement("tr");
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
				y : value.occurrences
			});
		});
		table.appendChild(tbody);

		div.appendChild(table);
		document.getElementById("queryresult").appendChild(div);

		// chart starts here
		$(document).ready(function() {
			chart = new Highcharts.Chart({
				chart : {
					renderTo : 'causeContainer',
					plotBackgroundColor : null,
					plotBorderWidth : null,
					plotShadow : false
				},
				title : {
					text : null
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
								return Highcharts.numberFormat(this.percentage,
										2)
										+ '%';
							}
						},
						showInLegend : false
					}
				},
				series : [ {
					type : 'pie',
					data : dataForChart
				} ]
			});
		});
	} else {
		alert("Invalid phone model!");
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
	var results = makeJSONObject("./../../webservice/NMEQueries/" + fromDate
			+ "/" + toDate);

	var div = document.createElement("div");
	div.setAttribute("style", "max-height: 400px; overflow: auto;");
	var table = document.createElement("table");
	table.setAttribute("class", "table table-striped table-bordered");

	var tbody = document.createElement("tbody");
	var thead = document.createElement("thead");
	var th = document.createElement("th");
	th.appendChild(document.createTextNode("Rank"));
	thead.appendChild(th);
	th = document.createElement("th");
	th.appendChild(document.createTextNode("Cell ID"));
	thead.appendChild(th);
	th = document.createElement("th");
	th.appendChild(document.createTextNode("Market"));
	thead.appendChild(th);
	th = document.createElement("th");
	th.appendChild(document.createTextNode("Operator"));
	thead.appendChild(th);
	th = document.createElement("th");
	th.appendChild(document.createTextNode("Failures"));
	thead.appendChild(th);
	table.appendChild(thead);
	
	var counter = 1;
	var dataForChart = []; 

	$.each(results, function(key, value) {
		var row = document.createElement("tr");
		var cell = document.createElement("td");
		cell.appendChild(document.createTextNode(counter));
		row.appendChild(cell);

		cell = document.createElement("td");
		cell.appendChild(document.createTextNode(value.cellId));
		row.appendChild(cell);

		cell = document.createElement("td");
		cell.appendChild(document.createTextNode(value.country));
		row.appendChild(cell);

		cell = document.createElement("td");
		cell.appendChild(document.createTextNode(value.operator));
		row.appendChild(cell);

		cell = document.createElement("td");
		cell.appendChild(document.createTextNode(value.numberOfFailures));
		row.appendChild(cell);

		tbody.appendChild(row);
		counter++;
		
		var yaxis = "cellId: " + value.cellId + ", "
				+ value.country + ", " + value.operator;
		dataForChart.push([ yaxis, value.numberOfFailures ]);
	});
	table.appendChild(tbody);

	div.appendChild(table);
	document.getElementById("queryresult").appendChild(div);
	$(document).ready(
			function() {
				chart = new Highcharts.Chart({
					chart : {
						renderTo : 'chartContainer', 
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
							return 'Number Of Failures:<b>'
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
					series : [ {
						showInLegend : false,
						data: dataForChart
					} ]
				});

			});
}

function topIMSIs() {
	var fromDate = document.forms["nmequery"]["from"].value;
	var toDate = document.forms["nmequery"]["to"].value;
	var results = makeJSONObject("./../../webservice/NMEQueries/IMSI/"
			+ fromDate + "/" + toDate);

	var div = document.createElement("div");
	div.setAttribute("style", "max-height: 400px; overflow: auto;");
	var table = document.createElement("table");
	table.setAttribute("class", "table table-striped table-bordered");

	var tbody = document.createElement("tbody");
	table.appendChild(createHead("Rank", "IMSI", "Number of failures"));

	for ( var i = 0; i < results.length; i++) {
		var row = document.createElement("tr");
		var cell = document.createElement("td");
		cell.appendChild(document.createTextNode(i + 1));
		row.appendChild(cell);

		cell = document.createElement("td");
		cell.appendChild(document.createTextNode(results[i][0]));
		row.appendChild(cell);

		cell = document.createElement("td");
		cell.appendChild(document.createTextNode(results[i][1]));
		row.appendChild(cell);

		tbody.appendChild(row);
	}
	table.appendChild(tbody);

	div.appendChild(table);
	document.getElementById("queryresult").appendChild(div);
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

	var mainNode = document.getElementById("queryresult");
	while (mainNode.lastChild) {
		mainNode.removeChild(mainNode.lastChild);
	}

	if (request.responseText != "") {
		return eval("(" + request.responseText + ")");
	} else {
		return "";
	}
}