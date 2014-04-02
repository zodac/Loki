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
	<br> <br><br>
<div class="container-fluid well span6">
<br><br>

	<div class="col-md-12">
    <div class="row">
   
        <div class="col-sm-6">
		    <img src="../../images/profile.png" class="img-circle">
        </div>
        <div class="col-sm-6 text-left">
 			<h3>User Name: <element id = "tblusername"></element></h3>
            <h6>Role: <element id = "tblrole"></element></h6>
            <h6>Firstname: <element id = "tblfirstname"></element></h6>
            <h6>Lastname: <element id = "tbllastname"></element></h6>
            <h6>Email: <element id = "tblemail"></element></h6>
            <h6>Phone: <element id = "tblphone"></element></h6>        </div>
    </div>
     <br><br>
</div>

</div>

</div>
<script>
loadUserDetails();
</script>
<jsp:include page="../templates/footer.jsp" />