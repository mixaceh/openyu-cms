<#include "/WEB-INF/app/content.ftl"/>

<html>
<head>
<title><@s.text name="global.app" /></title>
</head>
<body>
	<div class="leftcontent" id="leftnav">
		<img alt="bg image"
			src="${base}/back/theme/doctors_office/img/left_bg_top.gif" />

		<#include "/WEB-INF/back/service/config/configLeftNav.ftl"/>
		<div class="left_news">
			<p>&nbsp;</p>
			<p>&nbsp;</p>
		</div>
	</div>
	<div id="centercontent">
		<#-- breadcrumb -->
		<div id="breadcrumb">
			<@s.url var="parentUrl" namespace="/back/service/contextType" action="index" />
			<@s.set name="parentTitle">
				<@s.text name="contextType.title" />
			</@s.set>
			<@s.url var="currentUrl" namespace="/back/service/contextType" action="edit">
				<@s.param name="contextType.seq">
					<@s.property value="contextType.seq" />
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
				namespace="/back/service/contextType" action="editSave"
				validate="true">
				<table class="free" width="100%" border="0">

					<#-- hidden field-->
					<@s.hidden id="contextType.seq" name="contextType.seq" />
					<@s.hidden id="contextType.version" name="contextType.version" />
					<@s.hidden id="contextType.audit.createDate"
						name="contextType.audit.createDate" />
					<@s.hidden id="contextType.audit.createUser"
						name="contextType.audit.createUser" />
					<@s.hidden id="contextType.audit.modifiedDate"
						name="contextType.audit.modifiedDate" />
					<@s.hidden id="contextType.audit.modifiedUser"
						name="contextType.audit.modifiedUser" />

					<#-- hidden biz-->

					<#-- column -->
					<#include "contextTypeColumn.ftl"/>
				</table>
			</@s.form>
		</div>
		<#-- end .story-->

		<#-- audit -->
		<div id="audit">
			<@s.set name="version" value="contextType.version"/>
			<@s.set name="audit" value="contextType.audit"/>
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