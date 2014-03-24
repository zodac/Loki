function numberOfFailuresAndDuration(){
	var fromDate = document.forms["nmequery"]["from"].value;
	var toDate = document.forms["nmequery"]["to"].value;
	var results = makeJSONObject("./../../webservice/NMEQueries/" + fromDate + "/" + toDate);

	var div = document.createElement("div");
	div.setAttribute("style", "max-height: 400px; overflow: auto;");
	var table = document.createElement("table");
	table.setAttribute("class", "table table-striped table-bordered");
	
	var tbody = document.createElement("tbody");
	table.appendChild(createHead("IMSI", "Number of failures", "Total Duration (ms)"));		
	
	for(var i = 0; i < results.length; i++){
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

function uniqueEventCauseAndOccurancesByModel(){
	var modelInput = document.forms["nmequery"]["model"].value;
	var model = makeJSONObject("./../../webservice/Phones/" + modelInput);	
	
	if(model != ""){
		var results = makeJSONObject("./../../webservice/NMEQueries/" + model.tac);

		var div = document.createElement("div");
		div.setAttribute("style", "max-height: 400px; overflow: auto;");
		var table = document.createElement("table");
		table.setAttribute("class", "table table-striped table-bordered");
		
		var tbody = document.createElement("tbody");
		table.appendChild(createHead("Event ID", "Cause Code", "Occurrences"));
		
		for(var i = 0; i < results.length; i++){
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
	} else{
		alert("Invalid phone model!");
		document.forms["nmequery"]["model"].focus();
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
	
	var mainNode = document.getElementById("queryresult");
	while (mainNode.lastChild) {
		mainNode.removeChild(mainNode.lastChild);
	}
	
	if(request.responseText != "") {
		return eval("(" + request.responseText + ")");
	} else{
		return  "";
	}
}