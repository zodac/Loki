<%@ page import="main.*"%>
<jsp:include page="../templates/header.jsp" />
<jsp:include page="../templates/sysNav.jsp" />

<script src="../../js/userConf.js"></script>
<script>
	onload = checkAdmin();
</script>
<script>
	$(function() {
		$('#register').submit(function(event) {
			event.preventDefault();
			registerUser();
		});
	});
</script>
<!-- content here -->
<div class="col-md-9 text-center">
	<h3 class="col-md-offset-5 col-md-8 text-left">
		<em><%=Strings.CREATE_USER%></em>
	</h3>
	<br /> <br /> <br />
	<form name="register" id="register"
		class="form-horizontal">
		<div class="col-md-6 text-center">
			<div class="form-group">
				<div class="col-md-offset-3 col-md-8">
					<select class="form-control" name="role" id="role">
						<option value="0"><%=Strings.SELECT_ROLE%></option>
						<option value="Network Management Engineer"><%=Strings.NME%></option>
						<option value="Support Engineer"><%=Strings.SE%></option>
						<option value="Customer Service Rep"><%=Strings.CSR%></option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-offset-3 col-md-8">
					<input type="text" class="form-control" id="userName"
						name="userName" placeholder="<%=Strings.PH_USERNAME%>" required />
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-offset-3 col-md-8">
					<input type="password" class="form-control" id="password"
						name="password" placeholder="<%=Strings.PH_PASSWORD%>" required />
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-offset-3 col-md-8">
					<input type="password" class="form-control" id="confirm"
						name="confirm" placeholder="<%=Strings.PH_CONFIRM%>" required />
				</div>
			</div>
		</div>
		<div class="col-md-6 text-center">
			<div class="form-group">

				<div class="col-md-offset-1 col-md-8">
					<input type="text" class="form-control" id="fname" name="fname"
						placeholder="<%=Strings.PH_FNAME%>" required />
				</div>
			</div>
			<div class="form-group">

				<div class="col-md-offset-1 col-md-8">
					<input type="text" class="form-control" id="lname" name="lname"
						placeholder="<%=Strings.PH_LNAME%>" required />
				</div>
			</div>
			<div class="form-group">

				<div class="col-md-offset-1 col-md-8">
					<input type="text" class="form-control" id="email" name="email"
						placeholder="<%=Strings.PH_EMAIL%>" required
						title="<%=Strings.TT_EMAIL%>" data-toggle="tooltip"
						data-placement="bottom" />
				</div>
			</div>
			<div class="form-group">

				<div class="col-md-offset-1 col-md-8">
					<input type="text" class="form-control" id="phone" name="phone"
						placeholder="<%=Strings.PH_PHONE%>" required
						title="<%=Strings.TT_PHONE%>" data-toggle="tooltip"
						data-placement="bottom" />
				</div>
			</div>
		</div>
		<br />
		<div class="form-group">
			<div class="col-md-offset-4 col-md-4">
				<input type="submit" class="btn btn-primary" value="<%=Strings.CREATE_USER%>" />
			</div>
		</div>
	</form>
</div>

<jsp:include page="../templates/footer.jsp" />