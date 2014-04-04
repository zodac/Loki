<%@ page import="main.*"%>
<jsp:include page="../templates/header.jsp" />
<script>
	onload = checkCS();
</script>

<div class="row">
	<div class="col-md-3 text-left panel-group" id="accordion">
		<ul class="nav nav-pills nav-stacked">
			<li><a href="csHome.jsp"><span
					class="glyphicon glyphicon-home"></span> <strong><u><%=Strings.HOME%></u></strong></a></li>
			<li><a data-toggle="collapse" data-parent="#accordion"
				href="#collapseOne"><span class="glyphicon glyphicon-stats"></span>
					<strong><u><%=Strings.CSQUERIES%></u></strong></a></li>

			<div id="collapseOne" class="panel-collapse collapse">
				<ul class="nav nav-pills nav-stacked text-left">
					<li><a href="csEventCause.jsp"><span
							class="glyphicon glyphicon-arrow-right"></span> <%=Strings.UNIQUE_EVENTID_AND_CAUSECODE_COMBINATIONS_BY_IMSI%></a></li>
					<li><a href="csNumOfFail.jsp"><span
							class="glyphicon glyphicon-arrow-right"></span> <%=Strings.NUM_FAILURES_BY_IMSI_BY_TIME_PERIOD%></a></li>
					<li><a href="csUniqueCodes.jsp"><span
							class="glyphicon glyphicon-arrow-right"></span> <%=Strings.UNIQUE_CAUSECODES_BY_IMSI%></a></li>
				</ul>
			</div>
		</ul>
		<a class="twitter-timeline" width="250" height="250" data-dnt="true"
			href="https://twitter.com/ericsson"
			data-widget-id="444114613219520513"><%=Strings.TWEETS%></a>
	</div>

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
</div>
<script>
	loadUserDetails();
</script>
<jsp:include page="../templates/footer.jsp" />