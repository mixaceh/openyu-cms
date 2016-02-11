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
					<s:url var="url" namespace="/back/service/site" action="edit">
						<s:param name="site.seq">
							<s:property value="site.seq" />
						</s:param>
					</s:url>
					<s:a href="%{url}">
						<s:text name="global.edit" />
					</s:a>
				</s:param>
			</s:include>
		</div>
		<div class="story">
			<s:form id="editForm" name="editForm" namespace="/back/service/site"
				action="editSave" validate="true">
				<table class="free" width="100%" border="0">
					<%-- hidden field--%>
					<s:hidden id="site.seq" name="site.seq" />
					<s:hidden id="site.version" name="site.version" />
					<s:hidden id="site.audit.createDate" name="site.audit.createDate" />
					<s:hidden id="site.audit.createUser" name="site.audit.createUser" />
					<s:hidden id="site.audit.modifiedDate"
						name="site.audit.modifiedDate" />
					<s:hidden id="site.audit.modifiedUser"
						name="site.audit.modifiedUser" />

					<%-- hidden biz--%>
					<s:hidden id="site.template" name="site.template" />

					<%-- column --%>
					<%@ include file="siteColumn.jspf"%>
				</table>
			</s:form>
		</div>
		<!-- end .story-->

		<%-- audit --%>
		<%@ include file="siteAudit.jspf"%>
		<!-- end .audit-->

		<br /> <br /> <br />
		<div class="footer" id="footer">
			<%@ include file="/WEB-INF/back/service/home/footer.jspf"%>
		</div>
	</div>
</body>
</html>