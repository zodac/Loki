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


<script src="../../js/jquery.min.js"></script>
<script src="../../js/jquery.dataTables.js"></script>
<script src="../../js/bootstrap.min.js"></script>
<script type="text/javascript" charset="utf-8" src="../../js/dataTables.bootstrap.js" ></script>
<script type="text/javascript" charset="utf-8" src="../../js/dataTables.editor.bootstrap.js" ></script>
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
</script>

</head>
<body>
	<div class="container" id="main">
		<div class="row">
			<div class="col-md-2 text-center">
			<a href="../../login.jsp"><img src="../../images/logo.jpg" height="100" width="100" id="logo"></a>
			</div>
			<div class="col-md-10" id="banner"
				style="background-image: url(../../images/banner.png); height: 120px; width: 950px;">
				<form method="POST" name="logout" action="/JPASprint1/LoginServlet"
					id="postionbt">
					<button class="btn btn-danger right" type="submit">
						<span class="glyphicon glyphicon-log-out"></span><%=Strings.LOGOUT%></button>
				</form>
			</div>
			<div class="col-md-12" id="loggedinastext">

				<p class="text-right center" id="margintext">
					<span class="glyphicon glyphicon-user"></span>
					<%=Strings.LOGGED_IN_AS%>
					<!-- <strong></%=userName + " (" + userType + ")"%></strong> -->
				</p>
				<!-- get from session -->
			</div>

		</div>