function allIMSIsByTimePeriod(){
	var fromDate = document.forms["sequery"]["from"].value;
	var toDate = document.forms["sequery"]["to"].value;
	var results = makeJSONObject("./../../webservice/SEQueries/" + fromDate + "/" + toDate);

	var div = document.createElement("div");
	div.setAttribute("style", "max-height: 400px; overflow: auto;");
	var table = document.createElement("table");
	table.setAttribute("class", "table table-striped table-bordered");
	
	var tbody = document.createElement("tbody");
	table.appendChild(createHead("IMSI"));		
	
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

function numberOfFailuresByModelAndTimePeriod(){
	var modelInput = document.forms["sequery"]["model"].value;
	var model = makeJSONObject("./../../webservice/Phones/" + modelInput);	
	
	if(model != ""){
		var fromDate = document.forms["sequery"]["from"].value;
		var toDate = document.forms["sequery"]["to"].value;
		var results = makeJSONObject("./../../webservice/SEQueries/" + modelInput + "/" + fromDate + "/" + toDate);

		var div = document.createElement("div");
		div.setAttribute("style", "max-height: 400px; overflow: auto;");
		var table = document.createElement("table");
		table.setAttribute("class", "table table-striped table-bordered");
		
		var tbody = document.createElement("tbody");
		table.appendChild(createHead("Number of failures"));
		
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
	} else{
		alert("Invalid phone model!");
		document.forms["sequery"]["model"].focus();
	}
}

function allIMSIsByFailureClass(){
	var fc = document.forms["sequery"]["failureclass"].value;
	
	if(!/^-{0,1}\d*\.{0,1}\d+$/.test(fc)){
    	alert("Invalid FailureClass format!");
    	document.forms["sequery"]["failureclass"].focus();
    } else if(makeJSONObject("./../../webservice/FailureClass/" + fc) == 0){
    	alert("Invalid FailureClass value!");
		document.forms["sequery"]["failureclass"].focus();
    } else{
    	var results = makeJSONObject("./../../webservice/SEQueries/" + fc);

    	var div = document.createElement("div");
    	div.setAttribute("style", "max-height: 400px; overflow: auto;");
    	var table = document.createElement("table");
    	table.setAttribute("class", "table table-striped table-bordered");
    	
    	var tbody = document.createElement("tbody");
    	table.appendChild(createHead("IMSI"));		
    	
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

function createHead(t1) {
	var thead = document.createElement("thead");
	var th = document.createElement("th");
	th.appendChild(document.createTextNode(t1));
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