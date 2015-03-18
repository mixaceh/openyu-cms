<%@ include file="/WEB-INF/app/content.jspf"%>

<html>
<head>
<title><s:text name="global.app" /></title>
</head>
<body>
	<div class="leftcontent" id="leftnav">
		<img alt="bg image"
			src="${contextPath}/back/theme/doctors_office/img/left_bg_top.gif" />

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
					<s:a href="%{url}">
						<s:text name="module.title" />
					</s:a>
				</s:param>
				<s:param name="position">
					<s:url var="url" namespace="/back/service/module" action="view">
						<s:param name="module.seq">
							<s:property value="module.seq" />
						</s:param>
					</s:url>
					<s:a href="%{url}">
						<s:text name="global.view" />
					</s:a>
				</s:param>
			</s:include>
		</div>
		<div class="story">
			<table class="view" width="100%" border="0">
				<tbody>
					<tr>
						<td width="20%" class="viewlabel"><span class="required">*</span>
							<s:text name="module.names" /> :</td>
						<td width="30%"><s:property value="%{moduleName}" /></td>

						<td width="20%" class="viewlabel"><span class="required">*</span>
							<s:text name="module.valid" /> :</td>
						<td width="30%"><s:property
								value="%{getTrueFalseName(module.valid,getLocale())}" /></td>
					</tr>
					<tr>
						<td class="viewlabel"><s:text name="module.path" /> :</td>
						<td colspan="3"><s:property value="module.path" /></td>
					</tr>
					<tr>
						<td class="viewlabel"><span class="required">*</span> <s:text
								name="module.catalogPrefix" /> :</td>
						<td><s:property value="module.catalogPrefix" /></td>

						<td class="viewlabel"><s:text name="module.contextPrefix" />
							:</td>
						<td><s:property value="module.contextPrefix" /></td>
					</tr>
					<tr>
						<td class="viewlabel"><s:text name="module.catalogImg" /> :</td>
						<td><s:text name="module.imgWidth" /> :<s:property
								value="module.catalogImgWidth" /> <s:text
								name="module.imgHeight" /> :<s:property
								value="module.catalogImgHeight" /></td>

						<td class="viewlabel"><s:text name="module.contextImg" /> :</td>
						<td><s:text name="module.imgWidth" /> :<s:property
								value="module.contextImgWidth" /> <s:text
								name="module.imgHeight" /> :<s:property
								value="module.contextImgHeight" /></td>
					</tr>
					<tr>
						<td class="viewlabel"><s:text name="module.sort" /> :</td>
						<td><s:property value="module.sort" /></td>

						<td class="viewlabel"><s:text name="module.context" /> :</td>
						<td><s:property
								value="%{getTrueFalseName(module.context,getLocale())}" /></td>
					</tr>
					<tr>
						<td class="viewbutton" colspan="4">&nbsp;</td>
					</tr>
				</tbody>
			</table>
		</div>
		<!-- end .story-->

		<%-- audit --%>
		<%@ include file="moduleAudit.jspf"%>
		<!-- end .audit-->

		<br /> <br /> <br />
		<div class="footer" id="footer">
			<%@ include file="/WEB-INF/back/service/home/footer.jspf"%>
		</div>
	</div>
</body>
</html>