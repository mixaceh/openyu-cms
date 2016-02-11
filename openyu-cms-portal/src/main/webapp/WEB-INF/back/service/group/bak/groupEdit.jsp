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
					<s:url var="url" namespace="/back/service/group" action="edit">
						<s:param name="group.seq">
							<s:property value="group.seq" />
						</s:param>
					</s:url>
					<s:a href="%{url}">
						<s:text name="global.edit" />
					</s:a>
				</s:param>
			</s:include>
		</div>
		<div class="story">
			<s:form id="editForm" name="editForm" namespace="/back/service/group"
				action="editSave" validate="true">
				<table class="free" width="100%" border="0">

					<%-- hidden field--%>
					<s:hidden id="group.seq" name="group.seq" />
					<s:hidden id="group.version" name="group.version" />
					<s:hidden id="group.id" name="group.id" />
					<s:hidden id="group.audit.createDate" name="group.audit.createDate" />
					<s:hidden id="group.audit.createUser" name="group.audit.createUser" />
					<s:hidden id="group.audit.modifiedDate"
						name="group.audit.modifiedDate" />
					<s:hidden id="group.audit.modifiedUser"
						name="group.audit.modifiedUser" />

					<%-- hidden biz--%>
					<s:hidden id="group.default" name="group.default" />

					<%-- column --%>
					<%@ include file="groupColumn.jspf"%>
				</table>
			</s:form>
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