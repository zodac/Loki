<%@ page import="main.*"%>
<script src="../../queries/seQueries.js"></script>
<script src="../../queries/csrQueries.js"></script>
<div class="row">
	<div class="col-md-3 text-left panel-group" id="accordion">
		<ul class="nav nav-pills nav-stacked">
			<li><a href="seHome.jsp"><span
					class="glyphicon glyphicon-home"></span> <%=Strings.HOME%></a></li>
			<li><a data-toggle="collapse" data-parent="#accordion"
				href="#collapseOne"><span class="glyphicon glyphicon-stats"></span> <%=Strings.SEQUERIES%></a></li>

			<div id="collapseOne" class="panel-collapse collapse in">
				<ul class="nav nav-pills nav-stacked text-left">
					<li><a href="seListIMSI.jsp"><span
							class="glyphicon glyphicon-arrow-right"></span> <%=Strings.ALL_IMSIS_BY_TIME_PERIOD%></a></li>
					<li><a href="seCountForModel.jsp"><span
							class="glyphicon glyphicon-arrow-right"></span> <%=Strings.NUM_FAILURES_BY_MODEL_BY_TIME_PERIOD%></a></li>
					<li><a href="seIMSIByFC.jsp"><span
							class="glyphicon glyphicon-arrow-right"></span> <%=Strings.IMSIS_BY_FAILURE_CLASS%></a></li>
				</ul>
			</div>
			<li><a data-toggle="collapse" data-parent="#accordion"
				href="#collapseTwo"><span class="glyphicon glyphicon-stats"></span> <%=Strings.CSQUERIES%></a></li>
			<div id="collapseTwo" class="panel-collapse collapse">
				<ul class="nav nav-pills nav-stacked text-left">
					<li><a href="csEventCause.jsp"><span
							class="glyphicon glyphicon-arrow-right"></span> <%=Strings.UNIQUE_EVENTID_AND_CAUSECODE_COMBINATIONS_BY_IMSI%></a></li>
					<li><a href="csNumOfFail.jsp"><span
							class="glyphicon glyphicon-arrow-right"></span> <%=Strings.NUM_FAILURES_BY_IMSI_BY_TIME_PERIOD%></a></li>
					<li><a href="csUniqueCodes.jsp"><span
							class="glyphicon glyphicon-arrow-right"></span> <%=Strings.UNIQUE_CAUSECODES_BY_IMSI%></a></li>
				</ul>
			</div>
		</ul>
		<a class="twitter-timeline" width="250" height="250" data-dnt="true"
			href="https://twitter.com/ericsson"
			data-widget-id="444114613219520513"><%=Strings.TWEETS%></a>
	</div>