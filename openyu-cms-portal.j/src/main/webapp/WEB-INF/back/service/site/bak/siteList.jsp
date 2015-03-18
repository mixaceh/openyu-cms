<%@ include file="/WEB-INF/app/content.jspf"%>
<html>
<head>
<title><s:text name="global.app" /></title>
</head>

<body>

	<div class="leftcontent" id="leftnav">
		<img alt="bg image"
			src="${contextPath}/back/theme/doctors_office/img/left_bg_top.gif" />

		<%@ include file="/WEB-INF/back/service/config/configLeftNav.jspf"%>
		<div class="left_news">
			<p>&nbsp;</p>
			<p>&nbsp;</p>
		</div>
	</div>
	<div id="centercontent">

		<div id="breadcrumb">
			<s:include value="/WEB-INF/app/breadcrumb.jsp">
				<s:param name="rootPosition">
					<s:url var="url" namespace="/back/service/site" action="index" />
					<s:a href="%{url}">
						<s:text name="site.title" />
					</s:a>
				</s:param>
				<s:param name="position">
					<s:url var="url" namespace="/back/service/site" action="list" />
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
							<%-- id --%>
							<td width="5%" class="searchlabel"><s:text name="site.id" />
								:</td>
							<td width="15%"><s:textfield id="searcher.id"
									name="searcher.id" size="20" maxlength="255" /></td>
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
									namespace="/back/service/site" action="find" /> <s:a href="#"
									onclick="paginationSubmit('searchForm',0,'%{url}')">
									<s:text name="global.find" />
								</s:a></td>
						</tr>
						<tr>
							<%-- names --%>
							<td class="searchlabel"><s:text name="site.names" /> :</td>
							<td colspan="3"><s:textfield id="searcher.name"
									name="searcher.name" size="20" maxlength="50" /></td>
						</tr>
					</tbody>
				</table>
				<div id="pagination">
					<s:url var="url" namespace="/back/service/site" action="find" />
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
				<li><s:url var="url" namespace="/back/service/site"
						action="add" /> <s:a href="%{url}" target="_self">
						<s:text name="global.add" />
					</s:a></li>

				<%-- delete --%>
				<li><s:url var="url" namespace="/back/service/site"
						action="delete" /> <a href="#"
					onclick="pickSubmit('listForm','seqs','${url}','<s:text name="global.delete" />')">
						<s:text name="global.delete" />
				</a></li>

				<%-- save --%>
				<li><s:url var="url" namespace="/back/service/site"
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
							<th width="6%"><s:text name="site.seq" /></th>
							<th width="8%"><s:text name="global.operation" /></th>
							<th width="10%"><s:text name="site.id" /></th>
							<th width="26%"><s:text name="site.names" /></th>
							<th width="10%"><s:text name="site.resPath" /></th>
							<th width="8%"><s:text name="site.dynamicSuffix" /></th>
							<th width="8%"><s:text name="site.staticSuffix" /></th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="sites" var="site" status="st">
							<tr>

								<%-- pick --%>
								<td><s:checkbox name="seqs" fieldValue="%{seq}"
										value="false" /></td>
								<td><s:property value="seq" /></td>

								<%-- edit --%>
								<td><s:url var="url" namespace="/back/service/site"
										action="edit">
										<s:param name="site.seq">
											<s:property value="seq" />
										</s:param>
									</s:url> <s:a href="%{url}" target="_self">
										<s:text name="global.edit" />
									</s:a></td>

								<td><s:property value="id" /></td>
								<%-- view --%>
								<td><s:url var="url" namespace="/back/service/site"
										action="view">
										<s:param name="site.seq">
											<s:property value="seq" />
										</s:param>
									</s:url> <s:a href="%{url}" target="_self">
										<s:property value="%{getName(getLocale())}" />
									</s:a></td>
								</td>
								<td><s:property value="resPath" /></td>
								<td><s:property value="dynamicSuffix" /></td>
								<td><s:property value="staticSuffix" /></td>
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
		/*
		 * 選取提交
		 */
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

		/*
		 * 選取檢查
		 */
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
