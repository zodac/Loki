function checkAdmin() {
	if (localStorage.getItem("User") != null) {
		var userInfo = localStorage.getItem("User");
		var details = userInfo.split(",", 6);
		var type = details[1];

		if (type == "Network Management Engineer") {
			location.replace("../../webpages/networkManEng/nmeHome.jsp");
		} else if (type == "Support Engineer") {
			location.replace("../../webpages/supportEng/seHome.jsp");
		} else if (type == "Customer Service Rep") {
			location.replace("../../webpages/customerRep/csHome.jsp");
		}
	}
}

function checkCS() {
	if (localStorage.getItem("User") != null) {
		var userInfo = localStorage.getItem("User");
		var details = userInfo.split(",", 6);
		var type = details[1];

		if (type == "Network Management Engineer") {
			location.replace("../../webpages/networkManEng/nmeHome.jsp");
		} else if (type == "Support Engineer") {
			location.replace("../../webpages/supportEng/seHome.jsp");
		}
	}
}

function checkNME() {
	if (localStorage.getItem("User") != null) {
		var userInfo = localStorage.getItem("User");
		var details = userInfo.split(",", 6);
		var type = details[1];

		if (type == "Customer Service Rep") {
			location.replace("../../webpages/customerRep/csHome.jsp");
		} else if (type == "Support Engineer") {
			location.replace("../../webpages/supportEng/seHome.jsp");
		}
	}
}

function checkSupp() {
	if (localStorage.getItem("User") != null) {
		var userInfo = localStorage.getItem("User");
		var details = userInfo.split(",", 6);
		var type = details[1];

		if (type == "Customer Service Rep") {
			location.replace("../../webpages/customerRep/csHome.jsp");
		} else if (type == "Network Management Engineer") {
			location.replace("../../webpages/networkManEng/nmeHome.jsp");
		}
	}
}

function loadUserDetails() {
	if (localStorage.getItem("User") != null) {
		var userInfo = localStorage.getItem("User");
		var details = userInfo.split(",", 6);
		document.getElementById("tblusername").innerHTML = details[0];
		document.getElementById("tblrole").innerHTML = details[1];
		document.getElementById("tblfirstname").innerHTML = details[2];
		document.getElementById("tbllastname").innerHTML = details[3];
		document.getElementById("tblemail").innerHTML = details[4];
		document.getElementById("tblphone").innerHTML = details[5];
	} else {
		location.replace("/Loki/");
	}
}


function redirectToHome(){
	if (localStorage.getItem("User") != null) {
		var type = localStorage.getItem("User").split(",", 6)[1];
		
		if (type == "Network Management Engineer") {
			location.replace("../../webpages/networkManEng/nmeHome.jsp");
		} else if (type == "Support Engineer") {
			location.replace("../../webpages/supportEng/seHome.jsp");
		} else if (type == "Customer Service Rep") {
			location.replace("../../webpages/customerRep/csHome.jsp");
		} else{
			location.replace("../../webpages/admin/sysHome.jsp");
		}
	} else {
		location.replace("/Loki/");
	}
}