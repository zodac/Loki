<%@ page import="main.*"%>
<jsp:include page="../templates/header.jsp" />
<jsp:include page="../templates/sysNav.jsp" />
<!-- content here -->
<script src="../../js/jquery.form.min.js"></script>
<script src="../../js/upload.js"></script>
<script>
	onload = checkAdmin();
</script>
<script>
	$(function() {
		$('#upload').submit(function(event) {
			event.preventDefault();
			document.getElementById("uploading").style.display="block";
		});
	});
</script>
<div class="col-md-9 text-center">
	<h3 class="col-md-offset-4 col-md-7 text-left">
		<em><%=Strings.IMPORT%></em>
	</h3>
	<br /> <br /> <br />

	<form name="upload" id="upload" enctype="multipart/form-data">
		<div class="form-group">
			<div class="col-md-offset-4 col-md-4">
				<input type="file" value="Import" id="importfile" name="importfile"
					accept="application/vnd.ms-excel, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
					required />
			</div>
		</div>
		<br /> <br />
		
		<div class="form-group">
			<div class="col-md-offset-3 col-md-4">
				<input type="submit" class="btn btn-primary"
					value="<%=Strings.UPLOAD%>" />
			</div>
		</div>
	</form>
	<br /> <br />
	<div class="col-md-4" style="display: none" id="uploading"><img src="../../images/uploading.gif"></div>
	<div style="text-align: left" id="importresult"></div>
	<br />
	<div style="text-align: left; display: none" id="invalidfailures"></div>
</div>
<jsp:include page="../templates/footer.jsp" />