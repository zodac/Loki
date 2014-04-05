<%@ page import="main.*"%>
<script src="../../js/showUsers.js"></script>
<script src="../../js/register.js"></script>
<div class="row">
	<div class="col-md-3 text-left" id="border">
		<ul class="nav nav-pills nav-stacked">
			<li><a href="sysHome.jsp"><span
					class="glyphicon glyphicon-home"></span> <strong><u><%=Strings.HOME%></u></strong></a></li>
			<li><a href="sysImport.jsp"><span
					class="glyphicon glyphicon-save"></span> <strong><u><%=Strings.IMPORT%></u></strong></a></li>
			<li><a href="sysAddUser.jsp"><span
					class="glyphicon glyphicon-user"></span> <strong><u><%=Strings.ADD_USER%></u></strong></a></li>
			<li><a href="sysListUsers.jsp"><span
					class="glyphicon glyphicon-user"></span> <strong><u><%=Strings.SHOW_USERS%></u></strong></a></li>
			<li><a href="sysLog.jsp"><span
					class="glyphicon glyphicon-list"></span> <strong><u><%=Strings.LOGS%></u></strong></a></li>
		</ul>
	</div>