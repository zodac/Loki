/*function showUsers(){
	var users = makeJSONObject("./../../webservice/Users");
	
	var table = document.createElement("table");
	table.setAttribute("class", "table table-striped table-bordered");
	
	var tbody = document.createElement("tbody");
	var thead = document.createElement("thead");
	var th = document.createElement("th");
	th.appendChild(document.createTextNode("Username"));
	thead.appendChild(th);
	th = document.createElement("th");
	th.appendChild(document.createTextNode("First Name"));
	thead.appendChild(th);
	th = document.createElement("th");
	th.appendChild(document.createTextNode("Surname"));
	thead.appendChild(th);
	th = document.createElement("th");
	th.appendChild(document.createTextNode("User Role"));
	thead.appendChild(th);
	
	table.appendChild(thead);
	
	for(var i = 0; i < users.length; i++){
		var row = document.createElement("tr");
		var cell = document.createElement("td");
		cell.appendChild(document.createTextNode(users[i].userName));
		row.appendChild(cell);
		
		cell = document.createElement("td");
		cell.appendChild(document.createTextNode(users[i].firstName));
		row.appendChild(cell);
		
		cell = document.createElement("td");
		cell.appendChild(document.createTextNode(users[i].lastName));
		row.appendChild(cell);
		
		cell = document.createElement("td");
		cell.appendChild(document.createTextNode(users[i].usertype));
		row.appendChild(cell);
		
		tbody.appendChild(row);
	}
	table.appendChild(tbody);
	document.getElementById("users").appendChild(table);
	
	$(document).ready(function(){
		$("#users").flexigrid();
	});
}

function makeJSONObject(location) {
	var request = new XMLHttpRequest();
	request.open("GET", location, false);
	request.send(null);
	
	if(request.responseText != "") {
		return eval("(" + request.responseText + ")");
	}

	
	console.log(result);
	return result;
}*/


//	return request.responseText;
//}

