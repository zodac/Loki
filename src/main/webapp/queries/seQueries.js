function allIMSIsByTimePeriod(){
	var fromDate = document.forms["sequery"]["from"].value;
	var toDate = document.forms["sequery"]["to"].value;
	
	if (new Date(fromDate) > new Date(toDate)) {
		clearResult();
		alertify.error("Invalid date range!");
	    document.forms["sequery"]["from"].focus();
	} else if(new Date(fromDate) > new Date()){
		clearResult();
		alertify.error("Invalid date range!");
	    document.forms["sequery"]["from"].focus();
	} else{
		var results = makeJSONObject("./../../webservice/SEQueries/" + fromDate + "/" + toDate);
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
		thead.appendChild(tr);
		table.appendChild(thead);
		table.appendChild(tbody);
		document.getElementById("queryresult").appendChild(table);		
		
		var completearray = [];
		$.each(results, function(i, value){
			var array = [];
			array.push(value);
			completearray.push(array);
			
		});
		
		$(document).ready(function(){
		    $('#datatablehtml').dataTable({
		    	"aaData": completearray
		    });
		});
	}
}

function numberOfFailuresByModelAndTimePeriod(){
	var modelInput = document.forms["sequery"]["model"].value;
	var model = makeJSONObject("./../../webservice/Phones/" + modelInput);	
	
	if(model != ""){
		var fromDate = document.forms["sequery"]["from"].value;
		var toDate = document.forms["sequery"]["to"].value;
		
		if(new Date(fromDate) > new Date(toDate)) {
			clearResult();
			alertify.error("Invalid date range!");
			document.forms["sequery"]["from"].focus();
		} else if(new Date(fromDate) > new Date()){
			clearResult();
			alertify.error("Invalid date range!");
		    document.forms["sequery"]["from"].focus();
		} else{
			var results = makeJSONObject("./../../webservice/SEQueries/" + modelInput + "/" + fromDate + "/" + toDate);
			alertify.success(results.length + " results loaded!");

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
		}
	} else{
		alertify.log("Invalid phone model!");
		document.forms["sequery"]["model"].focus();
	}
}

function allIMSIsByFailureClass(){
	var inputFC = document.forms["sequery"]["failureclass"].value;
	
	var fc = inputFC.split(" -")[0];
	
	if(!/^-{0,1}\d*\.{0,1}\d+$/.test(fc)){
		clearResult();
		alertify.error("Invalid FailureClass format!");
    	document.forms["sequery"]["failureclass"].focus();
    } else if(fc.length > 19){
    	clearResult();
    	alertify.error("FailureClass too long!");
    	document.forms["sequery"]["failureclass"].focus();
    } else if(makeJSONObject("./../../webservice/FailureClass/" + fc) == 0){
    	clearResult();
    	alertify.log("Invalid FailureClass value!");
		document.forms["sequery"]["failureclass"].focus();
    } else{
    	var results = makeJSONObject("./../../webservice/SEQueries/" + fc);
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
		thead.appendChild(tr);
		table.appendChild(thead);
		table.appendChild(tbody);
		document.getElementById("queryresult").appendChild(table);
		var completearray = [];
		$.each(results, function(i, value){
			var array = [];
			array.push(value);
			completearray.push(array);
			
		});
		
		$(document).ready(function(){
		    $('#datatablehtml').dataTable({
		    	"aaData": completearray
		    });
		});
  
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
	
	clearResult();
	
	if(request.responseText != "") {
		return eval("(" + request.responseText + ")");
	} else{
		return  "";
	}
}

function clearResult() {
	var mainNode = document.getElementById("queryresult");
	while (mainNode.lastChild) {
		mainNode.removeChild(mainNode.lastChild);
	}
}