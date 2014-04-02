<%@ page import="main.*"%>
<%@ page import="java.util.*"%>
<jsp:include page="../templates/header.jsp" />
<jsp:include page="../templates/sysNav.jsp" />
<script src="../../js/userConf.js"></script>
<script>
	onload = checkAdmin();
</script>

<!-- content here -->

<div class="col-md-9 text-center">
	<h3 class="col-md-9 text-center">
		<em><%=Strings.PROFILE_PAGE%></em>
	</h3>
	<p style="padding: 10px;" class="col-md-12"></p>
	<dl class="dl-horizontal col-md-12">
		<dt><%=Strings.PROFILE_USERNAME%></dt>
		<dd id="tblusername"> 
		</dd>
		<dt><%=Strings.PROFILE_ROLE%></dt>
		<dd id="tblrole">
		</dd>
		<dd style="padding-bottom: 20px;"></dd>
		<dt><%=Strings.PROFILE_FIRSTNAME%></dt>
		<dd id="tblfirstname"> 
		</dd>
		<dt><%=Strings.PROFILE_LASTNAME%></dt>
		<dd id="tbllastname"> 
		</dd>
		<dd style="padding-bottom: 20px;"></dd>
		<dt><%=Strings.PROFILE_EMAIL%></dt>
		<dd id="tblemail"> 
		</dd>
		<dt><%=Strings.PROFILE_PHONE%></dt>
		<dd id="tblphone"> 
		</dd>
	</dl>
</div>
<script>
loadUserDetails();
</script>
<jsp:include page="../templates/footer.jsp" />