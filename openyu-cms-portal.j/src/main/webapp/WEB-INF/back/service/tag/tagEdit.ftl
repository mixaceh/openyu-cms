<#include "/WEB-INF/app/content.ftl"/>

<html>
<head>
<title><@s.text name="global.app" /></title>
</head>
<body>
	<div class="leftcontent" id="leftnav">
		<img alt="bg image"
			src="${base}/back/theme/doctors_office/img/left_bg_top.gif" />

		<#include "/WEB-INF/back/service/maintain/maintainLeftNav.ftl"/>
		<div class="left_news">
			<p>&nbsp;</p>
			<p>&nbsp;</p>
		</div>
	</div>
	<div id="centercontent">
		<#-- breadcrumb -->
		<div id="breadcrumb">
			<@s.url var="parentUrl" namespace="/back/service/tag" action="index" />
			<@s.set name="parentTitle">
				<@s.text name="tag.title" />
			</@s.set>
			<@s.url var="currentUrl" namespace="/back/service/tag" action="edit">
				<@s.param name="tag.seq">
					<@s.property value="tag.seq" />
				</@s.param>
			</@s.url>
			<@s.set name="currentTitle">
				<@s.text name="global.edit" />
			</@s.set>				
			<#include "/WEB-INF/app/breadcrumb.ftl">
		</div>
		<#-- end #breadcrumb-->

		<div class="story">
			<@s.form id="editForm" name="editForm"
				namespace="/back/service/tag" action="editSave"
				validate="true">
				<table class="free" width="100%" border="0">

					<#-- hidden field-->
					<@s.hidden id="tag.seq" name="tag.seq" />
					<@s.hidden id="tag.version" name="tag.version" />
					<@s.hidden id="tag.audit.createDate"
						name="tag.audit.createDate" />
					<@s.hidden id="tag.audit.createUser"
						name="tag.audit.createUser" />
					<@s.hidden id="tag.audit.modifiedDate"
						name="tag.audit.modifiedDate" />
					<@s.hidden id="tag.audit.modifiedUser"
						name="tag.audit.modifiedUser" />

					<#-- hidden biz-->
					<@s.hidden id="siteSeq" name="siteSeq" />

					<#-- column -->
					<#include "tagColumn.ftl">
				</table>
			</@s.form>
		</div>
		<#-- end .story-->

		<#-- audit -->
		<div id="audit">
			<@s.set name="version" value="tag.version"/>
			<@s.set name="audit" value="tag.audit"/>
			<#include "/WEB-INF/app/auditView.ftl">
		</div>
		<#-- end #audit-->

		<br /> <br /> <br />
		<div class="footer" id="footer">
			<#include "/WEB-INF/back/service/home/footer.ftl">
		</div>
	</div>
</body>
</html>