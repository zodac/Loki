<%@ page import="main.*"%>
<jsp:include page="../templates/header.jsp" />
<jsp:include page="../templates/nmeNav.jsp" />
<script src="../../js/userConf.js"></script>
<script>
onload = checkNME();
</script>
<!-- content here -->

<div class="col-md-9 text-center">
	<br /> <br /> <br />
	<div class="col-md-12" id="queryresult"></div>
	<script>topMOCGraph();</script>
</div>

<jsp:include page="../templates/footer.jsp" />