function loginUser(){
	var username = document.forms["loginform"]["userName"].value;
	var password = document.forms["loginform"]["password"].value;
	var userJSON = makeJSONObject("./webservice/Users/" + username);
	
	if (userJSON != null) {
		if (userJSON.userPassword == password) {
			var type = userJSON.usertype;
			
			if(type == "System Administrator"){
				location.replace("webpages/admin/sysHome.jsp");
			} else if(type == "Network Management Engineer"){
				location.replace("webpages/networkManEng/nmeHome.jsp");
			} else if(type == "Support Engineer"){
				location.replace("webpages/supportEng/seHome.jsp");
			} else if(type == "Customer Service Rep"){
				location.replace("webpages/customerRep/csHome.jsp");
			} else{
				alert("Invalid usertype!");
			}
		}
	} else {
		alert("Invalid username and/or password!");
	}
}

function makeJSONObject(location) {
	var request = new XMLHttpRequest();
	request.open("GET", location, false);
	request.send(null);
	
	if(request.responseText != "") {
		result = eval("(" + request.responseText + ")");
	}
	return result;
}