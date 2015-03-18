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
			<@s.url var="parentUrl" namespace="/back/service/friend" action="index" />
			<@s.set name="parentTitle">
				<@s.text name="friend.title" />
			</@s.set>
			<@s.url var="currentUrl" namespace="/back/service/friend" action="edit">
				<@s.param name="friend.seq">
					<@s.property value="friend.seq" />
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
				namespace="/back/service/friend" action="editSave" validate="true">
				<table class="free" width="100%" border="0">

					<#-- hidden field-->
					<@s.hidden id="friend.seq" name="friend.seq" />
					<@s.hidden id="friend.version" name="friend.version" />
					<@s.hidden id="friend.id" name="friend.id" />
					<@s.hidden id="friend.audit.createDate"
						name="friend.audit.createDate" />
					<@s.hidden id="friend.audit.createUser"
						name="friend.audit.createUser" />
					<@s.hidden id="friend.audit.modifiedDate"
						name="friend.audit.modifiedDate" />
					<@s.hidden id="friend.audit.modifiedUser"
						name="friend.audit.modifiedUser" />

					<#-- hidden biz-->
					<@s.hidden id="siteSeq" name="siteSeq" />

					<#-- column -->
					<#include "friendColumn.ftl">
				</table>
			</@s.form>
		</div>
		<#-- end .story-->

		<#-- audit -->
		<div id="audit">
			<@s.set name="version" value="friend.version"/>
			<@s.set name="audit" value="friend.audit"/>
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