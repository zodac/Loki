<%@ page import="main.*"%>
<jsp:include page="../templates/header.jsp" />
<jsp:include page="../templates/nmeNav.jsp" />
<!-- content here -->

<div class="col-md-9 text-center">
	<h4 class="col-md-12 text-center"><%=Strings.UNIQUE_EVENTID_AND_CAUSECODE_COMBINATION_AND_OCCURANCES_BY_MODEL%></h4>
	<br /> <br /> <br />
	<form name="nmequery" id="nmequery" 
		class="form-inline">
		<div class="form-group">
			<div class="col-md-4">
				<input type="text" class="form-control" id="model" name="model"
					placeholder="<%=Strings.PH_PHONE_MODEL%>"
					title="<%=Strings.TT_PHONE_MODEL%>">
			</div>
		</div>
		<input class="btn btn-primary" type="button" onclick="uniqueEventCauseAndOccurancesByModel()" value="<%=Strings.SUBMIT%>" />
	</form>
	<div class="col-md-5" id="queryresult"></div><br />
	<div class="col-md-7" id="causeContainer"></div>
	
</div>

<jsp:include page="../templates/footer.jsp" />