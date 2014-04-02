<%@ page import="main.*"%>
<jsp:include page="../templates/header.jsp" />
<jsp:include page="../templates/seNav.jsp" />
<script src="../../js/userConf.js"></script>
<script>
onload = checkSupp();
</script>
<!-- content here -->
<script>
	$(function() {
		$('#sequery').submit(function(event) {
			event.preventDefault();
			allIMSIsByFailureClass();
		});
	});
</script>
<div class="col-md-9 text-center">
	<h4 class="col-md-12 text-center"><%=Strings.IMSIS_BY_FAILURE_CLASS%></h4>
	<br /> <br /> <br />
	<form name="sequery" id="sequery"
		class="form-inline">
		<div class="form-group">
			<div class="col-md-1">
				<input type="text" class="form-control" id="failureclass" name="failureclass"
					placeholder="Failure Class" required />
			</div>
		</div>
		<input class="btn btn-primary" type="submit" value="<%=Strings.SUBMIT%>" />
	</form>
	<div class="col-md-offset-2 col-md-7" id="queryresult"></div>
</div>

<jsp:include page="../templates/footer.jsp" />

