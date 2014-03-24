function registerUser(){
	var email = document.forms["register"]["email"].value;
	var phone = document.forms["register"]["phone"].value;
	var usertype = document.forms["register"]["role"].value;
	var username = document.forms["register"]["userName"].value;
	var password = document.forms["register"]["password"].value;
	var confirm = document.forms["register"]["confirm"].value;
	var fname = document.forms["register"]["fname"].value;
	var lname = document.forms["register"]["lname"].value;
	
	if(password != confirm){
		alert("Passwords must match!");
		document.forms["register"]["password"].focus();
	} else if(!/^-{0,1}\d*\.{0,1}\d+$/.test(phone) || phone.length < 7 || phone.length > 10){
		alert("Invalid phone number!");
		document.forms["register"]["phone"].focus();
	} else if(usertype == 0){
		alert("Please select a user role!");
		document.forms["register"]["role"].focus();
	} else{
		var request = new XMLHttpRequest();
		var location = "./../../webservice/Users/"+username+"/"+password+"/"+usertype+"/"+email+"/"+phone+"/"+fname+"/"+lname;
		request.open("GET", location, false);
		request.send(null);
		
		if(request.status == 200){
			location.replace("./webpages/admin/sysListUsers.jsp");
		}
	}
}