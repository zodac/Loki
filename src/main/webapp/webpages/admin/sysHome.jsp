<%@ page import="main.*"%>
<%@ page import="java.util.*"%>
<jsp:include page="../templates/header.jsp" />
<jsp:include page="../templates/sysNav.jsp" />
<script src="../../js/userConf.js"></script>
<script>
	onload = checkAdmin();
</script>

<!-- content here -->

<div class="col-md-9 text-center">
	<br> <br> <br>
	<div class="container-fluid well span6">
		<br> <br>
		<div class="col-md-12">
			<div class="row">
				<div class="col-sm-6">
					<img src="../../images/profile.png" class="img-circle">
				</div>
				<div class="col-sm-6 text-left">
					<table>
						<tr>
							<td style="min-width: 150px"><h3>User Name:</h3></td>
							<td><h3 id="tblusername"></h3></td>
						</tr>
						<tr>
							<td><h6>First Name:</h6></td>
							<td><h6 id="tblfirstname"></h6></td>
						</tr>
						<tr>
							<td><h6>Last Name:</h6></td>
							<td><h6 id="tbllastname"></h6></td>
						</tr>
						<tr>
							<td><h6>User Role:</h6></td>
							<td><h6 id="tblrole"></h6></td>
						</tr>
						<tr>
							<td><h6>Email:</h6></td>
							<td><h6 id="tblemail"></h6></td>
						</tr>
						<tr>
							<td><h6>Phone:</h6></td>
							<td><h6 id="tblphone"></h6></td>
						</tr>
					</table>
				</div>
			</div>
			<br> <br>
		</div>
	</div>
</div>
<script>
	loadUserDetails();
</script>
<jsp:include page="../templates/footer.jsp" />