<%@ page import="main.*"%>
<jsp:include page="../templates/header.jsp" />
<jsp:include page="../templates/csrepNav.jsp" />
<script src="../../js/typeahead.bundle.js"></script>
<script src="../../js/handlebars.js"></script>
<script>
onload = checkCS();
</script>
<!-- content here -->
<script>
	$(function() {
		$('#csrquery').submit(function(event) {
			event.preventDefault();
			eventCauseByIMSI();
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

		var imsis = makeJSONObject("./../../webservice/IMSI/");

		$('#the-basics .form-control').typeahead({
			hint : true,
			highlight : true,
			minLength : 1
		}, {
			name : 'imsis',
			displayKey : 'value',
			source : substringMatcher(imsis)
		});
	});
</script>
<div class="col-md-9 text-center">
	<h4 class="col-md-12 text-center"><%=Strings.UNIQUE_EVENTID_AND_CAUSECODE_COMBINATIONS_BY_IMSI%></h4>
	<br /> <br /> <br />
	<form name="csrquery" id="csrquery" class="form-inline">
		<div class="form-group">
			<div id="the-basics" class="col-md-4">
				<input type="text" class="form-control" id="imsi" name="imsi"
					placeholder="<%=Strings.PH_IMSI%>" title="<%=Strings.TT_IMSI%>"
					required />
			</div>
		</div>
		<input class="btn btn-primary" type="submit" id="imsiSubmit" value="<%=Strings.SUBMIT%>" />
	</form>

	<div class="col-md-12" id="queryresult"></div>
</div>

<jsp:include page="../templates/footer.jsp" />
