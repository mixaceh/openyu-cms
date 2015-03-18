<%@ include file="/WEB-INF/app/content.jspf"%>
<html>
<head>
<title><s:text name="global.app" /></title>
</head>

<body>

	<div class="leftcontent" id="leftnav">
		<img alt="bg image"
			src="${contextPath}/back/theme/doctors_office/img/left_bg_top.gif" />

		<%@ include file="/WEB-INF/back/service/user/userLeftNav.jspf"%>
		<div class="left_news">
			<p>&nbsp;</p>
			<p>&nbsp;</p>
		</div>
	</div>
	<div id="centercontent">

		<div id="breadcrumb">
			<s:include value="/WEB-INF/app/breadcrumb.jsp">
				<s:param name="rootPosition">
					<s:url var="url" namespace="/back/service/group" action="index" />
					<s:a href="%{url}">
						<s:text name="group.title" />
					</s:a>
				</s:param>
				<s:param name="position">
					<s:url var="url" namespace="/back/service/group" action="list" />
					<s:a href="%{url}">
						<s:text name="global.list" />
					</s:a>
				</s:param>
			</s:include>
		</div>
		<!-- end #breadcrumb-->

		<div class="feature">
			<%-- search --%>
			<s:form id="searchForm" name="searchForm">
				<table class="search" width="100%" border="0">
					<tbody>
						<tr>
							<%-- name --%>
							<td width="5%" class="searchlabel"><s:text
									name="group.names" /> :</td>
							<td width="15%"><s:textfield id="searcher.name"
									name="searcher.name" size="20" maxlength="50" /></td>
							<%-- sort,order --%>
							<td width="15%"><s:select list="inquiry.sorts" listKey="id"
									listValue="%{getName(getLocale())}" id="inquiry.sort.id"
									name="inquiry.sort.id">
								</s:select> <s:select list="inquiry.orders" listKey="id"
									listValue="%{getName(getLocale())}" id="inquiry.order.id"
									name="inquiry.order.id">
								</s:select></td>
							<%-- search --%>
							<td width="63%"><s:url var="url"
									namespace="/back/service/group" action="find" /> <s:a href="#"
									onclick="paginationSubmit('searchForm',0,'%{url}')">
									<s:text name="global.find" />
								</s:a></td>
						</tr>
					</tbody>
				</table>
				<div id="pagination">
					<s:url var="url" namespace="/back/service/group" action="find" />
					<s:include value="/WEB-INF/app/pagination.jsp">
						<s:param name="action">
							<s:property value="%{url}" />
						</s:param>
					</s:include>
				</div>
				<!-- end #pagination-->

			</s:form>
		</div>
		<!-- end .feature-->

		<div id="opnav">
			<ul>
				<%-- add --%>
				<li><s:url var="url" namespace="/back/service/group"
						action="add" /> <s:a href="%{url}" target="_self">
						<s:text name="global.add" />
					</s:a></li>

				<%-- delete --%>
				<li><s:url var="url" namespace="/back/service/group"
						action="delete" /> <a href="#"
					onclick="pickSubmit('listForm','seqs','${url}','<s:text name="global.delete" />')">
						<s:text name="global.delete" />
				</a></li>

				<%-- save --%>
				<li><s:url var="url" namespace="/back/service/group"
						action="save" /> <s:a href="%{url}" target="_self">
						<s:text name="global.save" />
					</s:a></li>
			</ul>
		</div>
		<!-- end #opnav-->

		<div class="story">
			<s:form id="listForm" name="listForm">
				<table class="list" width="100%" border="0">
					<thead>
						<tr>
							<th width="3%" class="listcheckbox"><s:checkbox id="allSeqs"
									name="allSeqs" value="false"
									onclick="pickAll('seqs',this.checked)" /></th>
							<th width="6%"><s:text name="group.seq" /></th>
							<th width="10%"><s:text name="global.operation" /></th>
							<th width="23%"><s:text name="group.names" /></th>
							<th width="11%"><s:text name="group.dayUpload" /></th>
							<th width="11%"><s:text name="group.maxUpload" /></th>
							<th width="6%"><s:text name="group.sort" /></th>
							<th width="4%"><s:text name="group.default" /></th>
							<th width="8%"><s:text name="group.captcha" /></th>
							<th width="7%"><s:text name="group.check" /></th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="groups" var="group" status="st">
							<tr>

								<%-- pick --%>
								<td><s:checkbox name="seqs" fieldValue="%{seq}"
										value="false" /></td>
								<td><s:property value="seq" /></td>

								<%-- edit --%>
								<td><s:url var="url" namespace="/back/service/group"
										action="edit">
										<s:param name="group.seq">
											<s:property value="seq" />
										</s:param>
									</s:url> <s:a href="%{url}" target="_self">
										<s:text name="global.edit" />
									</s:a></td>

								<%-- view --%>
								<td><s:url var="url" namespace="/back/service/group"
										action="view">
										<s:param name="group.seq">
											<s:property value="seq" />
										</s:param>
									</s:url> <s:a href="%{url}" target="_self">
										<s:property value="%{getName(getLocale())}" />
									</s:a></td>
								</td>
								<td><s:property value="dayUpload" /></td>
								<td><s:property value="maxUpload" /></td>

								<%-- edit --%>
								<td><s:textfield name="%{'groups['+#st.index+'].sort'}"
										size="10" maxlength="10" />
								<td><s:radio list="{'true'}" listValue="''" id="default"
										name="default"></s:radio></td>
								<td><s:property
										value="%{getTrueFalseName(captcha,getLocale())}" /></td>
								<td><s:property
										value="%{getTrueFalseName(check,getLocale())}" /></td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
			</s:form>
		</div>
		<!-- end .story-->

		<br /> <br /> <br />
		<div class="footer" id="footer">
			<%@ include file="/WEB-INF/back/service/home/footer.jspf"%>
		</div>

		<%-- just for javascript to use --%>
		<s:set var="global_confirm">
			<s:text name="global.confirm" />
		</s:set>

		<s:set var="global_please_pick_data">
			<s:text name="global.please.pick.data" />
		</s:set>
	</div>

	<script language="JavaScript">

		function pickSubmit(formName, elementName, action, text) {

			if (!pickCheck(elementName) || !confirm('${global_confirm}' + text)) {
				return false;
			}
			//
			var form = document.forms[formName];
			if (typeof (action) != "undefined") {
				form.action = action;
			}
			//alert(form.name + " " + form.action);
			form.submit();
		}

		function pickCheck(elementName) {
			var elements = document.getElementsByName(elementName);
			for ( var i = 0; i < elements.length; i++) {
				if (elements[i].type != "checkbox") {
					continue;
				}
				if (elements[i].checked) {
					//alert("pick one");
					return true;
				}
			}
			alert('${global_please_pick_data}');
			return false;
		};
	</script>
</body>
</html>
