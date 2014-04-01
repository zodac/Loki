$('#upload').ajaxForm({
url : './../../webservice/Upload',
type : 'post',
contentType : "application/json",
dataType : 'multipart/form-data',
success : function(response) {
},
error : function(error) {
	var rawArray = JSON.stringify(error).toString().split(
			"'")[1];
	var rawValues = rawArray.substring(1,
			rawArray.length - 1).split(",");
	var div = document.createElement("div");
	var table = document.createElement("table");
	var tbody = document.createElement("tbody");

	var row = document.createElement("tr");
	var cell = document.createElement("td");
	var b = document.createElement("b");
	cell.setAttribute("style", "min-width:50px;");
	b.innerHTML = rawValues[0];
	cell.appendChild(b);
	row.appendChild(cell);
	cell = document.createElement("td");
	cell.appendChild(document.createTextNode("Call Failures added"));
	row.appendChild(cell);
	tbody.appendChild(row);
	
	row = document.createElement("tr");
	cell = document.createElement("td");
	b = document.createElement("b");
	b.innerHTML = rawValues[1];
	cell.appendChild(b);
	row.appendChild(cell);
	cell = document.createElement("td");
	cell.appendChild(document.createTextNode("Event/Cause combinations added"));
	row.appendChild(cell);
	tbody.appendChild(row);
	
	row = document.createElement("tr");
	cell = document.createElement("td");
	b = document.createElement("b");
	b.innerHTML = rawValues[2];
	cell.appendChild(b);
	row.appendChild(cell);
	cell = document.createElement("td");
	cell.appendChild(document.createTextNode("Failure Classes added"));
	row.appendChild(cell);
	tbody.appendChild(row);
	
	row = document.createElement("tr");
	cell = document.createElement("td");
	b = document.createElement("b");
	b.innerHTML = rawValues[3];
	cell.appendChild(b);
	row.appendChild(cell);
	cell = document.createElement("td");
	cell.appendChild(document.createTextNode("MCC_MNCs added"));
	row.appendChild(cell);
	tbody.appendChild(row);
	
	row = document.createElement("tr");
	cell = document.createElement("td");
	b = document.createElement("b");
	b.innerHTML = rawValues[4];
	cell.appendChild(b);
	row.appendChild(cell);
	cell = document.createElement("td");
	cell.appendChild(document.createTextNode("UE Types added"));
	row.appendChild(cell);
	tbody.appendChild(row);
	
	row = document.createElement("tr");
	cell = document.createElement("td");
	b = document.createElement("b");
	b.innerHTML = rawValues[5];
	cell.appendChild(b);
	row.appendChild(cell);
	cell = document.createElement("td");
	cell.appendChild(document.createTextNode("Call Failures removed due to inconsistencies"));
	row.appendChild(cell);
	tbody.appendChild(row);
	
	table.appendChild(tbody);
	div.appendChild(table);
	div.appendChild(document.createElement("br"));
	
	var button = document.createElement("button");
	button.setAttribute("class", "btn btn-success");
	button.setAttribute("id", "importTableButton");
	button.onclick = function(){
		//$("#invalidfailures").hide();
		//	var buttonText = $("#invalidfailures");
		console.log("vsdavs");
			$("#invalidfailures").toggle(function() {
				if ($(this).css('display') == 'none') {
					console.log("if");
			//		buttonText.html('Show Table');
				} else {
					console.log("else");
			//		buttonText.html('Hide Table');
				}
			});
	};
	button.appendChild(document.createTextNode("Show Table"));
	
	div.appendChild(button);
	document.getElementById("importresult").appendChild(div);

	var results = makeJSONObject("./../../webservice/InvalidCallFailure");

	console.log("Got results");
	div = document.createElement("div");
	div.setAttribute("style","max-height: 400px; overflow: auto;");
	table = document.createElement("table");
	table.setAttribute("class","table table-striped table-bordered");
	console.log("Making header");
	tbody = document.createElement("tbody");
	thead = document.createElement("thead");
	th = document.createElement("th");
	th.appendChild(document.createTextNode("ID"));
	thead.appendChild(th);
	th = document.createElement("th");
	th.setAttribute("style", "min-width:175px;");
	th.appendChild(document.createTextNode("Date"));
	thead.appendChild(th);
	th = document.createElement("th");
	th.setAttribute("style", "min-width:100px;");
	th.appendChild(document.createTextNode("Event ID"));
	thead.appendChild(th);
	th = document.createElement("th");
	th.setAttribute("style", "min-width:100px;");
	th.appendChild(document.createTextNode("Failure Class"));
	thead.appendChild(th);
	th = document.createElement("th");
	th.appendChild(document.createTextNode("UE Type"));
	thead.appendChild(th);
	th = document.createElement("th");
	th.appendChild(document.createTextNode("Market"));
	thead.appendChild(th);
	th = document.createElement("th");
	th.appendChild(document.createTextNode("Operator"));
	thead.appendChild(th);
	th = document.createElement("th");
	th.setAttribute("style", "min-width:50px;");
	th.appendChild(document.createTextNode("Cell ID"));
	thead.appendChild(th);
	th = document.createElement("th");
	th.appendChild(document.createTextNode("Duration"));
	thead.appendChild(th);
	th = document.createElement("th");
	th.setAttribute("style", "min-width:100px;");
	th.appendChild(document.createTextNode("Cause Code"));
	thead.appendChild(th);
	th = document.createElement("th");
	th.setAttribute("style", "min-width:100px;");
	th.appendChild(document.createTextNode("NE Version"));
	thead.appendChild(th);
	th = document.createElement("th");
	th.appendChild(document.createTextNode("IMSI"));
	thead.appendChild(th);
	th = document.createElement("th");
	th.appendChild(document.createTextNode("HIER3_ID"));
	thead.appendChild(th);
	th = document.createElement("th");
	th.appendChild(document.createTextNode("HIER32_ID"));
	thead.appendChild(th);
	th = document.createElement("th");
	th.appendChild(document.createTextNode("HIER321_ID"));
	thead.appendChild(th);

	table.appendChild(thead);
	console.log("Added header");
	for (var i = 0; i < results.length; i++) {
		row = document.createElement("tr");
		cell = document.createElement("td");
		cell.appendChild(document
				.createTextNode(results[i].id));
		row.appendChild(cell);
		cell = document.createElement("td");
		var now = new Date(results[i].date);
		cell.appendChild(document.createTextNode(now.customFormat("#DD#/#MM#/#YYYY# #hh#:#mm#")));
		row.appendChild(cell);
		cell = document.createElement("td");
		cell.appendChild(document
				.createTextNode(results[i].eventId));
		row.appendChild(cell);
		cell = document.createElement("td");
		cell.appendChild(document
				.createTextNode(results[i].failureClass));
		row.appendChild(cell);
		cell = document.createElement("td");
		cell.appendChild(document
				.createTextNode(results[i].uetype));
		row.appendChild(cell);
		cell = document.createElement("td");
		cell.appendChild(document
				.createTextNode(results[i].market));
		row.appendChild(cell);
		cell = document.createElement("td");
		cell.appendChild(document
				.createTextNode(results[i].operator));
		row.appendChild(cell);
		cell = document.createElement("td");
		cell.appendChild(document
				.createTextNode(results[i].cellId));
		row.appendChild(cell);
		cell = document.createElement("td");
		cell.appendChild(document
				.createTextNode(results[i].duration));
		row.appendChild(cell);
		cell = document.createElement("td");
		cell.appendChild(document
				.createTextNode(results[i].causeCode));
		row.appendChild(cell);
		cell = document.createElement("td");
			cell.appendChild(document
					.createTextNode(results[i].neversion));
			row.appendChild(cell);
			cell = document.createElement("td");
			cell.appendChild(document
					.createTextNode(results[i].imsi));
			row.appendChild(cell);
			cell = document.createElement("td");
			cell.appendChild(document
					.createTextNode(results[i].hier3Id));
			row.appendChild(cell);
			cell = document.createElement("td");
			cell.appendChild(document
					.createTextNode(results[i].hier32Id));
			row.appendChild(cell);
			cell = document.createElement("td");
			cell.appendChild(document
					.createTextNode(results[i].hier321Id));
			row.appendChild(cell);
	
			tbody.appendChild(row);
		}
	console.log("Adding body");
		table.appendChild(tbody);
	
		console.log("Adding table");
		div.appendChild(table);
		console.log("Adding div");
		document.getElementById("invalidfailures").appendChild(div);
		}
});

function makeJSONObject(location) {
	var request = new XMLHttpRequest();
	request.open("GET", location, false);
	request.send(null);

	if (request.responseText != "") {
		return eval("(" + request.responseText + ")");
	}
	return request.responseText;
}

Date.prototype.customFormat = function(formatString){
    var YYYY,YY,MMMM,MMM,MM,M,DDDD,DDD,DD,D,hhh,hh,h,mm,m,ss,s,ampm,AMPM,dMod,th;
    var dateObject = this;
    YY = ((YYYY=dateObject.getFullYear())+"").slice(-2);
    MM = (M=dateObject.getMonth()+1)<10?('0'+M):M;
    MMM = (MMMM=["January","February","March","April","May","June","July","August","September","October","November","December"][M-1]).substring(0,3);
    DD = (D=dateObject.getDate())<10?('0'+D):D;
    DDD = (DDDD=["Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"][dateObject.getDay()]).substring(0,3);
    th=(D>=10&&D<=20)?'th':((dMod=D%10)==1)?'st':(dMod==2)?'nd':(dMod==3)?'rd':'th';
    formatString = formatString.replace("#YYYY#",YYYY).replace("#YY#",YY).replace("#MMMM#",MMMM).replace("#MMM#",MMM).replace("#MM#",MM).replace("#M#",M).replace("#DDDD#",DDDD).replace("#DDD#",DDD).replace("#DD#",DD).replace("#D#",D).replace("#th#",th);

    h=(hhh=dateObject.getHours());
    if (h==0) h=24;
    if (h>12) h-=12;
    hh = h<10?('0'+h):h;
    AMPM=(ampm=hhh<12?'am':'pm').toUpperCase();
    mm=(m=dateObject.getMinutes())<10?('0'+m):m;
    ss=(s=dateObject.getSeconds())<10?('0'+s):s;
    return formatString.replace("#hhh#",hhh).replace("#hh#",hh).replace("#h#",h).replace("#mm#",mm).replace("#m#",m).replace("#ss#",ss).replace("#s#",s).replace("#ampm#",ampm).replace("#AMPM#",AMPM);
};