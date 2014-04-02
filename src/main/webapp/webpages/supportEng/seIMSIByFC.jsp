<%@ page import="main.*"%>
<jsp:include page="../templates/header.jsp" />
<jsp:include page="../templates/seNav.jsp" />
<script src="../../js/typeahead.bundle.js"></script>
<script src="../../js/handlebars.js"></script>
<script>
onload = checkSupp();
</script>
<!-- content here -->
<script>
$(function() {
	$('#sequery').submit(function(event) {
		event.preventDefault();
		allIMSIsByFailureClass();
	});
});
</script>
<script>
function makeJSONObject(location) {
	var request = new XMLHttpRequest();
	request.open("GET", location, false);
	request.send(null);

	clearResult();

	if (request.responseText != "") {
		return eval("(" + request.responseText + ")");
	}
	return request.responseText;
}
</script>
<script>
$(document).ready(
	function() {
		var substringMatcher = function(strs) {
			return function findMatches(q, cb) {
				var matches;
				matches = [];

				var substrRegex = new RegExp(q, 'i');

				$.each(strs, function(i, str) {
					if (substrRegex.test(str)) {
						matches.push({
							value : str
						});
					}
				});

				cb(matches);
			};
		};

		var failureClassIds = makeJSONObject("./../../webservice/FailureClass/");

		$('#the-basics .form-control').typeahead({
			hint : true,
			highlight : true,
			minLength : 1
		}, {
			name : 'failureClassIds',
			displayKey : 'value',
			source : substringMatcher(failureClassIds)
		});
	});
</script>
<div class="col-md-9 text-center">
	<h4 class="col-md-12 text-center"><%=Strings.IMSIS_BY_FAILURE_CLASS%></h4>
	<br /> <br /> <br />
	<form name="sequery" id="sequery" class="form-inline">
		<div class="form-group">
			<div id="the-basics" class="col-md-1">
				<input type="text" class="form-control" id="failureclass"
					name="failureclass" placeholder="Failure Class" size=25 required />
			</div>
		</div>
		<input class="btn btn-primary" type="submit"
			value="<%=Strings.SUBMIT%>" />
	</form>
	<div class="col-md-offset-2 col-md-7" id="queryresult"></div>
</div>

<jsp:include page="../templates/footer.jsp" />

