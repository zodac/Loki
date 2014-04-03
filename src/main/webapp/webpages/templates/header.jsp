<%@ page import="main.*"%>

<!DOCTYPE>
<html>
<head>
<title><%=Strings.TITLE%></title>
<link rel="shortcut icon" type="image/x-icon"
	href="../../images/favicon.ico">
<link href="../../css/bootstrap.min.css" rel="stylesheet">
<link href="../../css/jquery.dataTables.css" rel="stylesheet" type="text/css" />
<link href="../../css/dataTables.bootstrap.css" rel="stylesheet" type="text/css" />
<link href="../../css/styles.css" rel="stylesheet">
<link href="../../css/alertify.core.css" rel="stylesheet">
<link href="../../css/alertify.default.css" rel="stylesheet">
<script src="../../js/jquery.min.js"></script>
<script src="../../js/jquery.dataTables.js"></script>
<script src="../../js/bootstrap.min.js"></script>
<script type="text/javascript" charset="utf-8" src="../../js/dataTables.bootstrap.js" ></script>
<script type="text/javascript" charset="utf-8" src="../../js/dataTables.editor.bootstrap.js" ></script>
<script src="../../js/login.js"></script>
<script src="../../js/alertify.min.js"></script>
<script src="../../js/userConf.js"></script>
<script>
	$(function() {
		$("#to, #from, #email, #phone").tooltip({
			position : "right center",
			offset : [ -2, 10 ],
			effect : "explode",
			opacity : 0.7
		});
	});
	!function(d, s, id) {
		var js, fjs = d.getElementsByTagName(s)[0], p = /^http:/
				.test(d.location) ? 'http' : 'https';
		if (!d.getElementById(id)) {
			js = d.createElement(s);
			js.id = id;
			js.src = p + "://platform.twitter.com/widgets.js";
			fjs.parentNode.insertBefore(js, fjs);
		}
	}(document, "script", "twitter-wjs");

	function loadDetails(){

		if(localStorage.getItem("User") != null){
			var userInfo = localStorage.getItem("User");
			var details = userInfo.split(",",6);
			document.getElementById("username").innerHTML= details[2] + " " + details[3];
			document.getElementById("usertype").innerHTML= " (" + details[1] + ")";
			
		}else{
			location.replace("/Loki/");
		}
	}
</script>

</head>
<body>
	<div class="container" id="main">
		<div class="row">
			<div class="col-md-2 text-center">
			<a href="#" onclick="redirectToHome()"><img src="../../images/logo.jpg" height="100" width="100" id="logo"></a>
			</div>
			<div class="col-md-10" id="banner"
				style="background-image: url(../../images/banner.png); height: 120px; width: 950px;">
				<form name="logout" action="/Loki" id="postionbt">
					<button class="btn btn-danger right" type="submit">
						<span class="glyphicon glyphicon-log-out"></span><%=Strings.LOGOUT%></button>
				</form>
			</div>
			<div class="col-md-12" id="loggedinastext">

				<p class="text-right center" id="margintext">
					<span class="glyphicon glyphicon-user"></span>
					<%=Strings.LOGGED_IN_AS%>
					<strong>
						<element id="username"></element>
					</strong><strong><element id="usertype"></element></strong>
				<script>loadDetails();</script>
				</p>
				<!-- get from session -->
			</div>

		</div>