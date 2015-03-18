<#include "/WEB-INF/app/content.ftl"/>

<html>
<head>
<title><@s.text name="global.app" /></title>
</head>
<body>
	<div class="leftcontent" id="leftnav">
		<img alt="bg image"
			src="${base}/back/theme/doctors_office/img/left_bg_top.gif" />

		<#include "/WEB-INF/back/service/assistant/assistantLeftNav.ftl"/>	
		<div class="left_news">
			<p>&nbsp;</p>
			<p>&nbsp;</p>
		</div>
	</div>
	<div id="centercontent">
		<#-- breadcrumb -->
		<div id="breadcrumb">
			<@s.url var="parentUrl" namespace="/back/service/adSpace" action="index" />
			<@s.set name="parentTitle">
				<@s.text name="adSpace.title" />
			</@s.set>
			<@s.url var="currentUrl" namespace="/back/service/adSpace" action="edit">
				<@s.param name="adSpace.seq">
					<@s.property value="adSpace.seq" />
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
				namespace="/back/service/adSpace" action="editSave" validate="true">
				<table class="free" width="100%" border="0">

					<#-- hidden field-->
					<@s.hidden id="adSpace.seq" name="adSpace.seq" />
					<@s.hidden id="adSpace.version" name="adSpace.version" />
					<@s.hidden id="adSpace.audit.createDate"
						name="adSpace.audit.createDate" />
					<@s.hidden id="adSpace.audit.createUser"
						name="adSpace.audit.createUser" />
					<@s.hidden id="adSpace.audit.modifiedDate"
						name="adSpace.audit.modifiedDate" />
					<@s.hidden id="adSpace.audit.modifiedUser"
						name="adSpace.audit.modifiedUser" />

					<#-- hidden biz-->
					<@s.hidden id="siteSeq" name="siteSeq" />

					<#-- column -->
					<#include "adSpaceColumn.ftl">
				</table>
			</@s.form>
		</div>
		<#-- end .story-->

		<#-- audit -->
		<div id="audit">
			<@s.set name="version" value="adSpace.version"/>
			<@s.set name="audit" value="adSpace.audit"/>
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