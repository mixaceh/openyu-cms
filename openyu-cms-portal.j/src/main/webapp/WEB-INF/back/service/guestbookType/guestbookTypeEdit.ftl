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
			<@s.url var="parentUrl" namespace="/back/service/guestbookType" action="index" />
			<@s.set name="parentTitle">
				<@s.text name="guestbookType.title" />
			</@s.set>
			<@s.url var="currentUrl" namespace="/back/service/guestbookType" action="edit">
				<@s.param name="guestbookType.seq">
					<@s.property value="guestbookType.seq" />
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
				namespace="/back/service/guestbookType" action="editSave" validate="true">
				<table class="free" width="100%" border="0">

					<#-- hidden field-->
					<@s.hidden id="guestbookType.seq" name="guestbookType.seq" />
					<@s.hidden id="guestbookType.version" name="guestbookType.version" />
					<@s.hidden id="guestbookType.audit.createDate"
						name="guestbookType.audit.createDate" />
					<@s.hidden id="guestbookType.audit.createUser"
						name="guestbookType.audit.createUser" />
					<@s.hidden id="guestbookType.audit.modifiedDate"
						name="guestbookType.audit.modifiedDate" />
					<@s.hidden id="guestbookType.audit.modifiedUser"
						name="guestbookType.audit.modifiedUser" />

					<#-- hidden biz-->
					<@s.hidden id="siteSeq" name="siteSeq" />

					<#-- column -->
					<#include "guestbookTypeColumn.ftl">
				</table>
			</@s.form>
		</div>
		<#-- end .story-->

		<#-- audit -->
		<div id="audit">
			<@s.set name="version" value="guestbookType.version"/>
			<@s.set name="audit" value="guestbookType.audit"/>
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