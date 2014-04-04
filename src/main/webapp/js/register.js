function registerUser(){
	var email = document.forms["register"]["email"].value;
	var phone = document.forms["register"]["phone"].value;
	var usertype = document.forms["register"]["role"].value;
	var username = document.forms["register"]["userName"].value;
	var password = document.forms["register"]["password"].value;
	var confirm = document.forms["register"]["confirm"].value;
	var fname = document.forms["register"]["fname"].value;
	var lname = document.forms["register"]["lname"].value;
	
	if(usertype == 0){
		alertify.error("Please select a user role!");
		document.forms["register"]["role"].focus();
	} else if(makeJSONObject("./../../webservice/Users/" + username) != ""){
		alertify.error("Username taken!");
		document.forms["register"]["userName"].focus();
	} else if(password.length < 4){
		alertify.error("Password must be at least 4 characters long!");
		document.forms["register"]["password"].focus();
	} else if(password != confirm){
		alertify.error("Passwords must match!");
		document.forms["register"]["password"].focus();
	} else if(!/^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/.test(email)){
		alertify.error("Invalid email format!");
		document.forms["register"]["email"].focus();
		return false;
	} else if(!/^-{0,1}\d*\.{0,1}\d+$/.test(phone) || phone.length < 7 || phone.length > 15){
		alertify.error("Invalid phone number!");
		document.forms["register"]["phone"].focus();
	} else {
		var request = new XMLHttpRequest();
		var location = "./../../webservice/Users/"+username+"/"+password+"/"+usertype+"/"+email+"/"+phone+"/"+fname+"/"+lname;
		request.open("GET", location, false);
		request.send(null);
		window.location.href = "sysListUsers.jsp";
	}
}

function makeJSONObject(location) {
	var request = new XMLHttpRequest();
	request.open("GET", location, false);
	request.send(null);
	
	if(request.responseText != "") {
		return eval("(" + request.responseText + ")");
	} else{
		return "";
	}
}