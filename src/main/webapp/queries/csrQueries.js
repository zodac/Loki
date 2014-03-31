function eventCauseByIMSI(){
	var imsi = document.forms["csrquery"]["imsi"].value;

    if(!/^-{0,1}\d*\.{0,1}\d+$/.test(imsi)){
    	clearResult();
    	alert("Invalid IMSI format!");
    	document.forms["csrquery"]["imsi"].focus();
    } else if(makeJSONObject("./../../webservice/IMSI/" + imsi) == 0){
    	clearResult();
    	alert("Invalid IMSI value!");
		document.forms["csrquery"]["imsi"].focus();
    } else{
		var results = makeJSONObject("./../../webservice/CSRQueries/EC/" + imsi);

		var div = document.createElement("div");
		div.setAttribute("style", "max-height: 400px; overflow: auto;");
		var table = document.createElement("table");
		table.setAttribute("class", "table table-striped table-bordered");
		
		var tbody = document.createElement("tbody");
		var thead = document.createElement("thead");
		var th = document.createElement("th");
		th.appendChild(document.createTextNode("Event ID"));
		thead.appendChild(th);
		th = document.createElement("th");
		th.appendChild(document.createTextNode("Cause Code"));
		thead.appendChild(th);
		table.appendChild(thead);
		
		for(var i = 0; i < results.length; i++){
			var row = document.createElement("tr");
			var cell = document.createElement("td");
			cell.appendChild(document.createTextNode(results[i].id.a_Event_ID));
			row.appendChild(cell);
			cell = document.createElement("td");
			cell.appendChild(document.createTextNode(results[i].id.b_Cause_Code));
			row.appendChild(cell);
			
			tbody.appendChild(row);
		}
		table.appendChild(tbody);
		
		div.appendChild(table);
		document.getElementById("queryresult").appendChild(div);
	}
}

function numberOfFailuresByIMSIByTimePeriod(){
	var imsi = document.forms["csrquery"]["imsi"].value;

	if(!/^-{0,1}\d*\.{0,1}\d+$/.test(imsi)){
    	clearResult();
    	alert("Invalid IMSI format!");
    	document.forms["csrquery"]["imsi"].focus();
    } else if(makeJSONObject("./../../webservice/IMSI/" + imsi) == 0){
    	clearResult();
    	alert("Invalid IMSI value!");
		document.forms["csrquery"]["imsi"].focus();
    } else{
		var fromDate = document.forms["csrquery"]["from"].value;
		var toDate = document.forms["csrquery"]["to"].value;
		
		if(new Date(fromDate) > new Date(toDate)) {
			clearResult();
			alert("Invalid date range!");
			document.forms["csrquery"]["from"].focus();
		} else{
			var results = makeJSONObject("./../../webservice/CSRQueries/" + imsi + "/" + fromDate + "/" + toDate);
			var div = document.createElement("div");
			div.setAttribute("style", "max-height: 400px; overflow: auto;");
			var table = document.createElement("table");
			table.setAttribute("class", "table table-striped table-bordered");
			
			var tbody = document.createElement("tbody");
			var thead = document.createElement("thead");
			var th = document.createElement("th");
			th.appendChild(document.createTextNode("Number of Failures"));
			thead.appendChild(th);
			table.appendChild(thead);
			
			for(var i = 0; i < results.length; i++){
				var row = document.createElement("tr");
				var cell = document.createElement("td");
				cell.appendChild(document.createTextNode(results[i]));
				row.appendChild(cell);
				
				tbody.appendChild(row);
			}
			table.appendChild(tbody);
			
			div.appendChild(table);
			document.getElementById("queryresult").appendChild(div);
		}
	}
}

function uniqueCauseCodeByIMSI(){
	var imsi = document.forms["csrquery"]["imsi"].value;
	if(!/^-{0,1}\d*\.{0,1}\d+$/.test(imsi)){
    	clearResult();
    	alert("Invalid IMSI format!");
    	document.forms["csrquery"]["imsi"].focus();
    } else if(makeJSONObject("./../../webservice/IMSI/" + imsi) == 0){
    	clearResult();
    	alert("Invalid IMSI value!");
		document.forms["csrquery"]["imsi"].focus();
    } else{
		var results = makeJSONObject("./../../webservice/CSRQueries/CC/" + imsi);

		var div = document.createElement("div");
		div.setAttribute("style", "max-height: 400px; overflow: auto;");
		var table = document.createElement("table");
		table.setAttribute("class", "table table-striped table-bordered");
		
		var tbody = document.createElement("tbody");
		var thead = document.createElement("thead");
		var th = document.createElement("th");
		th.appendChild(document.createTextNode("Cause Code"));
		thead.appendChild(th);
		table.appendChild(thead);
		
		for(var i = 0; i < results.length; i++){
			var row = document.createElement("tr");
			var cell = document.createElement("td");
			cell.appendChild(document.createTextNode(results[i]));
			row.appendChild(cell);
			
			tbody.appendChild(row);
		}
		table.appendChild(tbody);
		
		div.appendChild(table);
		document.getElementById("queryresult").appendChild(div);
	}
}

function makeJSONObject(location) {
	var request = new XMLHttpRequest();
	request.open("GET", location, false);
	request.send(null);
	
	clearResult();
	
	if(request.responseText != "") {
		return eval("(" + request.responseText + ")");
	} else{
		return "";
	}
}

function clearResult() {
	var mainNode = document.getElementById("queryresult");
	while (mainNode.lastChild) {
		mainNode.removeChild(mainNode.lastChild);
	}
}