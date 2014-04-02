<%@ page import="main.*"%>
<jsp:include page="../templates/header.jsp" />
<jsp:include page="../templates/nmeNav.jsp" />
<script src="../../js/typeahead.bundle.js"></script>
<script src="../../js/handlebars.js"></script>
<script>
onload = checkNME();
</script>
<!-- content here -->
<script>
	$(function() {
		$('#nmequery').submit(function(event) {
			event.preventDefault();
			uniqueEventCauseAndOccurancesByModel();
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

		var phoneModels = makeJSONObject("./../../webservice/Phones/");

		$('#the-basics .form-control').typeahead({
			hint : true,
			highlight : true,
			minLength : 1
		}, {
			name : 'phoneModels',
			displayKey : 'value',
			source : substringMatcher(phoneModels)
		});
	});
</script>
<div class="col-md-9 text-center">
	<h4 class="col-md-12 text-center"><%=Strings.UNIQUE_EVENTID_AND_CAUSECODE_COMBINATION_AND_OCCURANCES_BY_MODEL%></h4>
	<br /> <br /> <br />
	<form name="nmequery" id="nmequery" 
		class="form-inline">
		<div class="form-group">
			<div id="the-basics" class="col-md-4">
				<input type="text" class="form-control" id="model" name="model" size=25
					placeholder="<%=Strings.PH_PHONE_MODEL%>"
					title="<%=Strings.TT_PHONE_MODEL%>">
			</div>
		</div>
		<input class="btn btn-primary" type="submit" value="<%=Strings.SUBMIT%>" />
	</form>
	<div class="col-md-5" id="queryresult"></div><br />
	<div class="col-md-7" id="causeContainer"></div>
	
</div>

<jsp:include page="../templates/footer.jsp" />