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
					<s:url var="url" namespace="/back/service/site" action="view">
						<s:param name="site.seq">
							<s:property value="site.seq" />
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
				<%-- hidden field--%>

				<tbody>
					<tr>
						<td width="20%" class="viewlabel"><span class="required">*</span>
							<s:text name="site.names" /> :</td>
						<td width="30%"><s:property value="%{siteName}" /></td>

						<td width="20%" class="viewlabel"><span class="required">*</span>
							<s:text name="site.shortNames" /> :</td>
						<td width="30%"><s:property value="siteShortName" /></td>
					</tr>
					<tr>
						<td class="viewlabel"><span class="required">*</span> <s:text
								name="site.id" /> :</td>
						<td><s:property value="site.id" /></td>

						<td class="viewlabel"><span class="required">*</span> <s:text
								name="site.resPath" /> :</td>
						<td><s:property value="site.resPath" /></td>
					</tr>
					<tr>
						<td class="viewlabel"><s:text name="site.alias" /> :</td>
						<td><s:property value="site.alias" /> (<s:text
								name="global.multiple.use.comma.tip" />)</td>

						<td class="viewlabel"><s:text name="site.redirect" /> :</td>
						<td><s:property value="site.redirect" /> (<s:text
								name="global.multiple.use.comma.tip" />)</td>
					</tr>
					<tr>
						<td class="viewlabel"><span class="required">*</span> <s:text
								name="site.relativePath" /> :</td>
						<td><s:property
								value="%{getTrueFalseName(site.relativePath,getLocale())}" /> (<s:text
								name="site.relativePath.tip" />)</td>

						<td class="viewlabel"><span class="required">*</span> <s:text
								name="site.protocol" /> :</td>
						<td><s:property value="site.protocol" /></td>
					</tr>
					<tr>
						<td class="viewlabel"><span class="required">*</span> <s:text
								name="site.dynamicSuffix" /> :</td>
						<td><s:property value="site.dynamicSuffix" /></td>

						<td class="viewlabel"><span class="required">*</span> <s:text
								name="site.staticSuffix" /> :</td>
						<td><s:property value="site.staticSuffix" /></td>
					</tr>
					<tr>
						<td class="viewlabel"><s:text name="site.staticPath" /> :</td>
						<td><s:property value="site.staticPath" /></td>

						<td class="viewlabel"><span class="required">*</span> <s:text
								name="site.staticIndex" /> :</td>
						<td><s:property
								value="%{getTrueFalseName(site.staticIndex,getLocale())}" /></td>
					</tr>
					<tr>
						<td class="viewlabel"><s:text name="site.indexRoot" /> :</td>
						<td colspan="3"><s:property
								value="%{getTrueFalseName(site.indexRoot,getLocale())}" /></td>
					</tr>
					<tr>
						<td class="viewlabel"><s:text name="site.ftp" /> :</td>
						<td><s:if test="%{site.ftp!=null}">
								<%-- <s:property value="site.ftp.getName(getLocale())" />--%>
								<s:property value="site.ftp.seq" />
							</s:if></td>
						<td class="viewlabel"><span class="required">*</span> <s:text
								name="site.recover" /> :</td>
						<td><s:property
								value="%{getTrueFalseName(site.recover,getLocale())}" /></td>
					</tr>
					<tr>
						<td class="viewlabel"><span class="required">*</span> <s:text
								name="site.verifyType" /> :</td>
						<td><s:property
								value="%{getVerifyTypeName(site.verifyType,getLocale())}" /></td>

						<td class="viewlabel"><span class="required">*</span> <s:text
								name="site.modifyType" /> :</td>
						<td><s:property
								value="%{getModifyTypeName(site.modifyType,getLocale())}" /></td>
					</tr>
					<tr>
						<td class="freebutton" colspan="4">&nbsp;</td>
					</tr>
				</tbody>
			</table>
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