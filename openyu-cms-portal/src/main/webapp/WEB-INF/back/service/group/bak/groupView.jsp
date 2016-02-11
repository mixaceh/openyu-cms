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
					<s:url var="url" namespace="/back/service/group" action="view">
						<s:param name="group.seq">
							<s:property value="group.seq" />
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
							<s:text name="group.names" /> :</td>
						<td width="30%"><s:property value="%{groupName}" /></td>

						<td width="20%" class="viewlabel"><span class="required">*</span>
							<s:text name="group.sort" /> :</td>
						<td width="30%"><s:property value="group.sort" /></td>
					</tr>
					<tr>
						<td class="viewlabel"><span class="required">*</span> <s:text
								name="group.dayUpload" /> :</td>
						<td><s:property value="group.dayUpload" /> <s:text
								name="global.unit.kb.tip" /> (<s:text
								name="global.zero.no.restriction.tip" />)</td>

						<td class="viewlabel"><span class="required">*</span> <s:text
								name="group.maxUpload" /> :</td>
						<td><s:property value="group.maxUpload" /> <s:text
								name="global.unit.kb.tip" />(<s:text
								name="global.zero.no.restriction.tip" />)</td>
					</tr>
					<tr>
						<td class="viewlabel"><s:text name="group.uploadSuffix" /> :</td>
						<td colspan="3"><s:property value="group.uploadSuffix" />(<s:text
								name="global.empty.no.restriction.tip" />, <s:text
								name="global.multiple.use.comma.tip" />)</td>
					</tr>
					<tr>
						<td class="viewlabel"><s:text name="group.captcha" /> :</td>
						<td><s:property
								value="%{getTrueFalseName(group.captcha,getLocale())}" /></td>

						<td class="viewlabel"><s:text name="group.check" /> :</td>
						<td><s:property
								value="%{getTrueFalseName(group.check,getLocale())}" /></td>
					</tr>
					<tr>
						<td class="viewlabel"><span class="required">*</span> <s:text
								name="group.browse" /> :</td>
						<td colspan="3">&nbsp;</td>
					</tr>
					<tr>
						<td class="viewlabel"><span class="required">*</span> <s:text
								name="group.contribute" /> :</td>
						<td colspan="3">&nbsp;</td>
					</tr>
					<tr>
						<td class="viewbutton" colspan="4">&nbsp;</td>
					</tr>
				</tbody>
			</table>
		</div>
		<!-- end .story-->

		<%-- audit --%>
		<%@ include file="groupAudit.jspf"%>
		<!-- end .audit-->

		<br /> <br /> <br />
		<div class="footer" id="footer">
			<%@ include file="/WEB-INF/back/service/home/footer.jspf"%>
		</div>
	</div>
</body>
</html>