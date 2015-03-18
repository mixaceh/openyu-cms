<#include "/WEB-INF/app/content.ftl"/>

<html>
<head>
<title><@s.text name="global.app" /></title>
</head>
<body>
	<div class="leftcontent" id="leftnav">
		<img alt="bg image"
			src="${base}/back/theme/doctors_office/img/left_bg_top.gif" />

		<#include "/WEB-INF/back/service/module/moduleLeftNav.ftl">
		<div class="left_news">
			<p>&nbsp;</p>
			<p>&nbsp;</p>
		</div>
	</div>
	<div id="centercontent">
		<#-- breadcrumb -->
		<div id="breadcrumb">
			<@s.url var="parentUrl" namespace="/back/service/module" action="index" />
			<@s.set name="parentTitle">
				<@s.text name="module.title" />
			</@s.set>
			<@s.url var="currentUrl" namespace="/back/service/module" action="edit">
				<@s.param name="module.seq">
					<@s.property value="module.seq" />
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
				namespace="/back/service/module" action="editSave" validate="true">
				<table class="free" width="100%" border="0">

					<#-- hidden field-->
					<@s.hidden id="module.seq" name="module.seq" />
					<@s.hidden id="module.version" name="module.version" />
					<@s.hidden id="module.id" name="module.id" />
					<@s.hidden id="module.audit.createDate"
						name="module.audit.createDate" />
					<@s.hidden id="module.audit.createUser"
						name="module.audit.createUser" />
					<@s.hidden id="module.audit.modifiedDate"
						name="module.audit.modifiedDate" />
					<@s.hidden id="module.audit.modifiedUser"
						name="module.audit.modifiedUser" />

					<#-- hidden biz-->
					<@s.hidden id="module.default" name="module.default" />

					<#-- column -->
					<#include "moduleColumn.ftl">
				</table>
			</@s.form>
		</div>
		<#-- end .story-->

		<#-- audit -->
		<div id="audit">
			<@s.set name="version" value="module.version"/>
			<@s.set name="audit" value="module.audit"/>
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