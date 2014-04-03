<%@ page import="main.*"%>
<jsp:include page="../templates/header.jsp" />
<jsp:include page="../templates/sysNav.jsp" />

<script src="../../js/userConf.js"></script>
<script>
	onload = checkAdmin();
</script>
<!-- content here -->


<div class="col-md-9 text-center">
<div class="col-md-12 text-center">
	<h3 class="text-center"><em>Logs</em></h3>
	<br />
		<table id="datatablehhtml" class="table table-striped table-bordered"><thead><tr>
			<td>Log Details </td>
		</tr>
		</thead>
		<tbody>
			
		</tbody>
		</table>
		
<!-- 	</div> -->
	<script type="text/javascript">
			var results = makeJSONObject("./../../webservice/Log");
			var completearray = [];
			$.each(results, function(key, value){
				var array = [];
				array.push(value);
				completearray.push(array);

			});



			$(document).ready(function(){
			    $('#datatablehhtml').dataTable({
			    	"aaData": completearray
			    });
			});
						

	</script>
	
</div>
</div>
<jsp:include page="../templates/footer.jsp" />