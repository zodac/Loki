<%@ page import="main.*"%>
<script src="../../js/showUsers.js"></script>
<script src="../../js/register.js"></script>
<div class="row">
	<div class="col-md-3 text-left" id="border">
		<ul class="nav nav-pills nav-stacked">
			<li><a href="sysHome.jsp"><span
					class="glyphicon glyphicon-home"></span> <%=Strings.HOME%></a></li>
			<li><a href="sysImport.jsp"><span
					class="glyphicon glyphicon-save"></span> <%=Strings.IMPORT%></a></li>
			<li><a href="sysAddUser.jsp"><span
					class="glyphicon glyphicon-user"></span> <%=Strings.ADD_USER%></a></li>
			<li><a href="sysListUsers.jsp"><span
					class="glyphicon glyphicon-user"></span> <%=Strings.SHOW_USERS%></a></li>
		</ul>
	</div>