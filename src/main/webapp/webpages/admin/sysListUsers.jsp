<%@ page import="main.*"%>
<jsp:include page="../templates/header.jsp" />
<jsp:include page="../templates/sysNav.jsp" />

<!-- content here -->

<div class="col-md-9 text-center">
<div class="col-md-offset-2 col-md-8 text-center">
	<h3 class="text-center"><em><%=Strings.ALL_USERS%></em></h3>
	<br />
	<div style="max-height: 400px; overflow: auto;" id="users"></div>
	<script>showUsers();</script>
</div>
</div>
<jsp:include page="../templates/footer.jsp" />