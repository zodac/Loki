<%@ page import="main.*"%>
<jsp:include page="../templates/header.jsp" />
<jsp:include page="../templates/nmeNav.jsp" />
<!-- content here -->

<div class="col-md-9 text-center">
	<h4 class="col-md-offset-2 col-md-7 text-center"><%=Strings.TOP_10_MARKET_OPERATOR_CELL_NODES_BY_TIME_PERIOD%></h4>
	<br /> <br /> <br />
	<form name="nmequery" id="nmequery"
		class="form-inline">
		<div class="form-group">
			<div class="col-md-1">
				<input type="datetime-local" class="form-control" id="from"
					name="from" title="<%=Strings.TT_FROM%>" data-toggle="tooltip" data-placement="bottom" value="2013-01-01T00:00:00" step="1">
			</div>
		</div>
		<span class="glyphicon glyphicon-arrow-right"></span>
		<div class="form-group">
			<div class="col-md-1">
				<input type="datetime-local" class="form-control" id="to"
					name="to" title="<%=Strings.TT_TO%>" data-toggle="tooltip" data-placement="bottom" value="2013-12-31T23:59:00" step="1">
			</div>
		</div>
				<span style="display:inline"><input class="btn btn-primary" type="button" onclick="topMOC()" value="<%=Strings.SUBMIT%>" /></span>

	</form>
	<div class="col-md-offset-2 col-md-7" id="queryresult"></div><br />
	<div class="col-md-12" id="chartContainer"></div>
</div>

<jsp:include page="../templates/footer.jsp" />