<%@ page import="main.*"%>
<jsp:include page="../templates/header.jsp" />
<jsp:include page="../templates/seNav.jsp" />
<!-- content here -->

<div class="col-md-9 text-center">
	<h4 class="col-md-12 text-center"><%=Strings.ALL_IMSIS_BY_TIME_PERIOD%></h4>
	<br /> <br /> <br />
	<form name="sequery" id="sequery"
		class="form-inline">
		<div class="form-group">
			<div class="col-md-1">
				<input type="datetime-local" class="form-control" id="from"
					name="from" data-toggle="tooltip" data-placement="bottom" required
					title="<%=Strings.TT_FROM%>" value="2013-01-01T00:00:00" step="1" />
			</div>
		</div>
		<span class="glyphicon glyphicon-arrow-right"></span>
		<div class="form-group">
			<div class="col-md-1">
				<input type="datetime-local" class="form-control" id="to" name="to"
					data-toggle="tooltip" data-placement="bottom" required
					title="<%=Strings.TT_TO%>" value="2013-12-31T23:59:00" step="1" />
			</div>
		</div>
		<span style="display:inline"><input class="btn btn-primary" type="button" onclick="allIMSIsByTimePeriod()" value="<%=Strings.SUBMIT%>" /></span>
	</form>

	<div class="col-md-offset-2 col-md-7" id="queryresult"></div>
</div>

<jsp:include page="../templates/footer.jsp" />

