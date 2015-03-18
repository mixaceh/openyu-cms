<%@ include file="/WEB-INF/app/content.jspf"%>

<html>
<head>
<title><s:text name="global.app" /></title>
</head>
<body>
	<div class="leftcontent" id="leftnav">
		<img alt="bg image"
			src="${pageContext.request.contextPath}/back/theme/doctors_office/img/left_bg_top.gif" />

		<%@ include file="/WEB-INF/back/service/module/moduleLeftNav.jspf"%>
		<div class="left_news">
			<p>&nbsp;</p>
			<p>&nbsp;</p>
		</div>
	</div>
	<div id="centercontent">

		<div id="breadcrumb">
			<s:include value="/WEB-INF/app/breadcrumb.jsp">
				<s:param name="rootPosition">
					<s:url var="url" namespace="/back/service/module" action="index" />
					<a href="${url}"><s:text name="global.module" /> <s:text
							name="global.management" /></a>
				</s:param>
				<s:param name="position">
					<s:text name="global.add" />
				</s:param>
			</s:include>
		</div>

		<div class="story">
			<s:form id="addForm" name="addForm" namespace="/back/service/module"
				action="addSave">
				<table class="free" width="100%" border="0">
					<s:hidden id="module.id" name="module.id" />
					<s:hidden id="module.default" name="module.default" />
					<tbody>
						<tr>
							<td width="20%" class="freelabel"><span class="required">*</span>
								<s:text name="module.names" /> :</td>
							<td width="30%"><s:textfield id="module.name"
									name="module.name" /></td>

							<td width="20%" class="freelabel"><span class="required">*</span>
								<s:text name="module.path" /> :</td>
							<td width="30%"><s:textfield id="module.path"
									name="module.path" size="20" maxlength="255" /></td>
						</tr>
						<tr>
							<td class="freelabel"><span class="required">*</span> <s:text
									name="module.catalogPrefix" /> :</td>
							<td><s:textfield id="module.catalogPrefix"
									name="module.catalogPrefix" size="20" maxlength="30" /></td>

							<td class="freelabel"><s:text name="module.contextPrefix" />
								:</td>
							<td><s:textfield id="module.contextPrefix"
									name="module.contextPrefix" size="20" maxlength="30" /></td>
						</tr>
						<tr>
							<td class="freelabel"><s:text name="module.catalogImg" /> :</td>
							<td><s:text name="module.imgWidth" /> <s:textfield
									id="module.catalogImgWidth" name="module.catalogImgWidth"
									size="4" maxlength="4" /> <s:text name="module.imgHeight" />
								<s:textfield id="module.catalogImgHeight"
									name="module.catalogImgHeight" size="4" maxlength="4" /></td>

							<td class="freelabel"><s:text name="module.contextImg" /> :</td>
							<td><s:text name="module.imgWidth" /> <s:textfield
									id="module.contextImgWidth" name="module.contextImgWidth"
									size="4" maxlength="4" /> <s:text name="module.imgHeight" />
								<s:textfield id="module.contextImgHeight"
									name="module.contextImgHeight" size="4" maxlength="4" /></td>
						</tr>
						<tr>
							<td class="freelabel"><s:text name="module.sort" /> :</td>
							<td><s:textfield id="module.sort" name="module.sort"
									size="10" maxlength="10" /></td>

							<td class="freelabel"><s:text name="module.context" /> :</td>
							<td><s:radio list="trueFalses" listKey="id.value"
									listValue="%{getName(getLocale())}" name="module.context"></s:radio></td>
						</tr>
						<tr>
							<td class="freebutton" colspan="4"><s:submit name="submit"
									key="global.submit" /> <s:reset name="reset"
									key="global.reset" /></td>
						</tr>
					</tbody>
				</table>

				<%-- catalogItems --%>
				<span class="squares"> <span>&#8250;&#8250;</span>
				</span>
				<s:text name="module.catalog" />
				<table class="list" width="100%" border="0">
					<thead>
						<tr>
							<th width="6%"><s:text name="moduleItem.id" /></th>
							<th width="10%"><s:text name="moduleItem.columnType" /></th>
							<th width="25%"><s:text name="moduleItem.names" /></th>
							<th width="12%"><s:text name="moduleItem.sort" /></th>
							<th width="12%"><s:text name="moduleItem.single" /></th>
							<th width="8%"><s:text name="moduleItem.display" /></th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="module.catalogItems" var="catalogItem"
							status="catalogItemsStatus">
							<tr>
								<td><s:property value="id" /></td>
								<td><s:property value="columnType" /></td>
								<td><s:textfield id="catalogItem.name"
										name="#catalogItem.name" /></td>
								<td><s:property value="sort" /></td>
								<td><s:property value="single" /></td>
								<td><s:property value="display" /></td>
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
	</div>
</body>
</html>