$('#upload').ajaxForm({
	url : './../../webservice/Upload', // or whatever
	type : 'post',
	contentType : "application/json", //Maybe not??
	dataType : 'multipart/form-data',
	success : function(response) {
		alert("Success");
		alert(response);
	},
	error : function(error) {
		var rawArray = JSON.stringify(error).toString().split("'")[1];
		var rawValues = rawArray.substring(1, rawArray.length-1).split(",");
		var table = document.createElement("table");
		var tbody = document.createElement("tbody");
		
		var cell = document.createElement("tr");
		cell.appendChild(document.createTextNode(rawValues[0] + " CallFailures added"));
		tbody.appendChild(cell);
		cell = document.createElement("tr");
		cell.appendChild(document.createTextNode(rawValues[1] + " EventCauses added"));
		tbody.appendChild(cell);
		cell = document.createElement("tr");
		cell.appendChild(document.createTextNode(rawValues[2] + " FailureClasses added"));
		tbody.appendChild(cell);
		cell = document.createElement("tr");
		cell.appendChild(document.createTextNode(rawValues[3] + " MCC_MNCs added"));
		tbody.appendChild(cell);
		cell = document.createElement("tr");
		cell.appendChild(document.createTextNode(rawValues[4] + " UETypes added"));
		tbody.appendChild(cell);
		cell = document.createElement("tr");
		cell.appendChild(document.createTextNode(rawValues[5] + " InvalidCallFailures removed due to inconsistencies"));
		tbody.appendChild(cell);
		table.appendChild(tbody);
		
		document.getElementById("importresult").appendChild(table);
	}
});