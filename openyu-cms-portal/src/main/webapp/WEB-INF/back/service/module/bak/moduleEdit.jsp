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
					<s:url var="url" namespace="/back/service/module" action="edit">
						<s:param name="module.seq">
							<s:property value="module.seq" />
						</s:param>
					</s:url>
					<s:a href="%{url}">
						<s:text name="global.edit" />
					</s:a>
				</s:param>
			</s:include>
		</div>
		<div class="story">
			<s:form id="editForm" name="editForm"
				namespace="/back/service/module" action="editSave" validate="true">
				<table class="free" width="100%" border="0">

					<%-- hidden field--%>
					<s:hidden id="module.seq" name="module.seq" />
					<s:hidden id="module.version" name="module.version" />
					<s:hidden id="module.id" name="module.id" />
					<s:hidden id="module.audit.createDate"
						name="module.audit.createDate" />
					<s:hidden id="module.audit.createUser"
						name="module.audit.createUser" />
					<s:hidden id="module.audit.modifiedDate"
						name="module.audit.modifiedDate" />
					<s:hidden id="module.audit.modifiedUser"
						name="module.audit.modifiedUser" />

					<%-- hidden biz--%>
					<s:hidden id="module.default" name="module.default" />

					<%-- column --%>
					<%@ include file="moduleColumn.jspf"%>
				</table>
			</s:form>
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