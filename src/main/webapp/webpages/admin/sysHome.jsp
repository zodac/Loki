<%@ page import="main.*"%>
<%@ page import="java.util.*"%>
<jsp:include page="../templates/header.jsp" />
<jsp:include page="../templates/sysNav.jsp" />

<!-- content here -->

<div class="col-md-9 text-center">
	<h3 class="col-md-9 text-center"><em><%=Strings.PROFILE_PAGE%></em></h3>
	<p style="padding: 10px;" class="col-md-12"></p>
	<dl class="dl-horizontal col-md-12">
		<dt><%=Strings.PROFILE_USERNAME%></dt>
		<dd></dd>
		<dt><%=Strings.PROFILE_ROLE%></dt>
		<dd style="padding-bottom: 20px;"></dd>
		<dt><%=Strings.PROFILE_FIRSTNAME%></dt>
		<dd></dd>
		<dt><%=Strings.PROFILE_LASTNAME%></dt>
		<dd style="padding-bottom: 20px;"></dd>
		<dt><%=Strings.PROFILE_EMAIL%></dt>
		<dd></dd>
		<dt><%=Strings.PROFILE_PHONE%></dt>
		<dd></dd>
	</dl>
</div>

<jsp:include page="../templates/footer.jsp" />