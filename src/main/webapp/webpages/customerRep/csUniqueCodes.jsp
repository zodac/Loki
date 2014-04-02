<%@ page import="main.*"%>
<jsp:include page="../templates/header.jsp" />
<jsp:include page="../templates/csrepNav.jsp" />
<script src="../../js/userConf.js"></script>
<script>
onload = checkCS();
</script>
<script>
	$(function() {
		$('#csrquery').submit(function(event) {
			event.preventDefault();
			uniqueCauseCodeByIMSI();
		});
	});
</script>
<!-- content here -->
<div class="col-md-9 text-center">
	<h4 class="col-md-11 text-center"><%=Strings.UNIQUE_CAUSECODES_BY_IMSI%></h4>
	<br /> <br /> <br />
	<form name="csrquery" id="csrquery"
		class="form-inline">
		<div class="form-group">
			<div class="col-md-4">
				<input type="text" class="form-control" id="imsi" name="imsi"
					placeholder="<%=Strings.PH_IMSI%>" required
					title="<%=Strings.TT_IMSI%>" />
			</div>
		</div>
		<input class="btn btn-primary" type="submit" value="<%=Strings.SUBMIT%>" />
	</form>
	<div class="col-md-12" id="queryresult"></div>
</div>

<jsp:include page="../templates/footer.jsp" />