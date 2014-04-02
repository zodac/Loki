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
	<h3 class="text-center"><em><%=Strings.ALL_USERS%></em></h3>
	<br />
	<!-- <div style="max-height: 400px; overflow: auto;" id="users">-->
		<table id="datatablehhtml" class="table table-striped table-bordered"><thead><tr>
			<td>Username </td>
			<td>First Name</td>
			<td>Surname</td>
			<td>User Role</td>
		</tr>
		</thead>
		<tbody>
			
		</tbody>
		</table>
		
<!-- 	</div> -->
	<script type="text/javascript">
			var results = makeJSONObject("./../../webservice/Users");
			var completearray = [];
			$.each(results, function(key, value){
				var array = [];
				array.push(value.userName);
				array.push(value.firstName);
				array.push(value.lastName);
				array.push(value.usertype);
				completearray.push(array);

			});



			$(document).ready(function(){
			    $('#datatablehhtml').dataTable({
			    	"aaData": completearray
			    });
			});
						

	</script>
	
	<!--  <script>showUsers();</script>-->
</div>
</div>
<jsp:include page="../templates/footer.jsp" />