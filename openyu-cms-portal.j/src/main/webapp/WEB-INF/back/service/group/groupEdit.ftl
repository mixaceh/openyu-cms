<#include "/WEB-INF/app/content.ftl"/>

<html>
<head>
<title><@s.text name="global.app" /></title>
</head>
<body>
	<div class="leftcontent" id="leftnav">
		<img alt="bg image"
			src="${base}/back/theme/doctors_office/img/left_bg_top.gif" />

		<#include "/WEB-INF/back/service/user/userLeftNav.ftl"/>
		<div class="left_news">
			<p>&nbsp;</p>
			<p>&nbsp;</p>
		</div>
	</div>
	<div id="centercontent">
		<#-- breadcrumb -->
		<div id="breadcrumb">
			<@s.url var="parentUrl" namespace="/back/service/group" action="index" />
			<@s.set name="parentTitle">
				<@s.text name="group.title" />
			</@s.set>
			<@s.url var="currentUrl" namespace="/back/service/group" action="edit">
				<@s.param name="group.seq">
					<@s.property value="group.seq" />
				</@s.param>
			</@s.url>
			<@s.set name="currentTitle">
				<@s.text name="global.edit" />
			</@s.set>				
			<#include "/WEB-INF/app/breadcrumb.ftl">
		</div>
		<#-- end #breadcrumb-->

		<div class="story">
			<@s.form id="editForm" name="editForm" namespace="/back/service/group"
				action="editSave" validate="true">
				<table class="free" width="100%" border="0">

					<#-- hidden field-->
					<@s.hidden id="group.seq" name="group.seq" />
					<@s.hidden id="group.version" name="group.version" />
					<@s.hidden id="group.id" name="group.id" />
					<@s.hidden id="group.audit.createDate" name="group.audit.createDate" />
					<@s.hidden id="group.audit.createUser" name="group.audit.createUser" />
					<@s.hidden id="group.audit.modifiedDate"
						name="group.audit.modifiedDate" />
					<@s.hidden id="group.audit.modifiedUser"
						name="group.audit.modifiedUser" />

					<#-- hidden biz-->
					<@s.hidden id="group.default" name="group.default" />

					<#-- column -->
					<#include "groupColumn.ftl">
				</table>
			</@s.form>
		</div>
		<#-- end .story-->

		<#-- audit -->
		<div id="audit">
			<@s.set name="version" value="group.version"/>
			<@s.set name="audit" value="group.audit"/>
			<#include "/WEB-INF/app/auditView.ftl">
		</div>
		<#-- end #audit-->

		<br /> <br /> <br />
		<div class="footer" id="footer">
			<#include "/WEB-INF/back/service/home/footer.ftl">
		</div>
		<br/>
	</div>
</body>
</html>