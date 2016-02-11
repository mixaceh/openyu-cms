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
			<@s.url var="parentUrl" namespace="/back/service/sensitivity" action="index" />
			<@s.set name="parentTitle">
				<@s.text name="sensitivity.title" />
			</@s.set>
			<@s.url var="currentUrl" namespace="/back/service/sensitivity" action="edit">
				<@s.param name="sensitivity.seq">
					<@s.property value="sensitivity.seq" />
				</@s.param>
				<@s.param name="dictionary.key">
					<@s.property value="dictionary.key" />
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
				namespace="/back/service/sensitivity" action="editSave"
				validate="true">
				<table class="free" width="100%" border="0">

					<#-- hidden field-->
					<@s.hidden id="sensitivity.seq" name="sensitivity.seq" />
					<@s.hidden id="sensitivity.version" name="sensitivity.version" />
					<@s.hidden id="sensitivity.id" name="sensitivity.id" />
					<@s.hidden id="sensitivity.audit.createDate"
						name="sensitivity.audit.createDate" />
					<@s.hidden id="sensitivity.audit.createUser"
						name="sensitivity.audit.createUser" />
					<@s.hidden id="sensitivity.audit.modifiedDate"
						name="sensitivity.audit.modifiedDate" />
					<@s.hidden id="sensitivity.audit.modifiedUser"
						name="sensitivity.audit.modifiedUser" />

					<#-- hidden biz-->

					<#-- column -->
					<#include "sensitivityColumn.ftl"/>
				</table>
			</@s.form>
		</div>
		<#-- end .story-->

		<#-- audit -->
		<div id="audit">
			<@s.set name="version" value="sensitivity.version"/>
			<@s.set name="audit" value="sensitivity.audit"/>
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