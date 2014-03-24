<%@ page import="main.*"%>
<%@ page import="java.text.DecimalFormat"%>
<jsp:include page="../templates/header.jsp" />
<jsp:include page="../templates/sysNav.jsp" />
<!-- content here -->

<div class="col-md-9 text-center">
	<h3 class="col-md-offset-4 col-md-7 text-left"><em><%=Strings.IMPORT%></em></h3>
	<br /> <br /> <br />

	<form name="upload" id="upload"
		enctype="multipart/form-data">
		<div class="form-group">
			<div class="col-md-offset-4 col-md-4">
				<input type="file" value="Import" name="ImportFile"
					accept="application/vnd.ms-excel, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
					required />
			</div>
		</div>
		<br /> <br />
		<div class="form-group">
			<div class="col-md-offset-3 col-md-4">
				<input type="button" class="btn btn-primary" onclick="import()" value="<%=Strings.UPLOAD%>" />
			</div>
		</div>
	</form>
	<br />
	<br />
	<div style="text-align:left" id="import"></div>

<jsp:include page="../templates/footer.jsp" />