<%@ page import="main.*"%>
<jsp:include page="../templates/header.jsp" />
<jsp:include page="../templates/nmeNav.jsp" />
<!-- content here -->

<div class="col-md-9 text-center">
	<h4 class="col-md-offset-2 col-md-7 text-center"><%=Strings.TOP_10_MARKET_OPERATOR_CELL_NODES_GRAPHICAL%></h4>
	<br /> <br /> <br />
	<div class="col-md-offset-2 col-md-7" id="queryresult"></div>
	<script>topMOCGraph();</script>
</div>

<jsp:include page="../templates/footer.jsp" />