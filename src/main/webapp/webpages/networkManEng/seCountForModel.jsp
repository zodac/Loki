<%@ page import="main.*"%>
<jsp:include page="../templates/header.jsp" />
<jsp:include page="../templates/nmeNav.jsp" />
<script src="../../js/userConf.js"></script>
<script>
onload = checkNME();
</script>
<!-- content here -->
<script>
	$(function() {
		$('#sequery').submit(function(event) {
			event.preventDefault();
			numberOfFailuresByModelAndTimePeriod();
		});
	});
</script>
<div class="col-md-9 text-center">
	<h4 class="col-md-12 text-center"><%=Strings.NUM_FAILURES_BY_MODEL_BY_TIME_PERIOD%></h4>
	<br /> <br /> <br />
	<form name="sequery" id="sequery"
		class="form-inline">
		<div class="form-group">
			<div class="col-md-1">
				<input type="text" class="form-control" id="model" name="model"
					placeholder="Phone Model" required />
			</div>
		</div>
		<div class="form-group">
			<div class="col-md-1">
				<input type="datetime-local" class="form-control" id="from"
					name="from" value="2013-01-01T00:00:00" step="1"
					data-toggle="tooltip" data-placement="bottom" required
					title="<%=Strings.TT_FROM%>">
			</div>
		</div>
		<span class="glyphicon glyphicon-arrow-right"></span>
		<div class="form-group">
			<div class="col-md-1">
				<input type="datetime-local" class="form-control" id="to" name="to"
					value="2013-12-31T23:59:59" step="1" data-toggle="tooltip"
					data-placement="bottom" required title="<%=Strings.TT_TO%>">
			</div>
		</div>
		<span style="display: inline"><input class="btn btn-primary" type="submit" value="<%=Strings.SUBMIT%>" /></span>
	</form>
	<div class="col-md-offset-2 col-md-7" id="queryresult"></div>
</div>

<jsp:include page="../templates/footer.jsp" />