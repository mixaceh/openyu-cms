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
			<@s.url var="parentUrl" namespace="/back/service/friendType" action="index" />
			<@s.set name="parentTitle">
				<@s.text name="friendType.title" />
			</@s.set>
			<@s.url var="currentUrl" namespace="/back/service/friendType" action="edit">
				<@s.param name="friendType.seq">
					<@s.property value="friendType.seq" />
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
				namespace="/back/service/friendType" action="editSave"
				validate="true">
				<table class="free" width="100%" border="0">

					<#-- hidden field-->
					<@s.hidden id="friendType.seq" name="friendType.seq" />
					<@s.hidden id="friendType.version" name="friendType.version" />
					<@s.hidden id="friendType.audit.createDate"
						name="friendType.audit.createDate" />
					<@s.hidden id="friendType.audit.createUser"
						name="friendType.audit.createUser" />
					<@s.hidden id="friendType.audit.modifiedDate"
						name="friendType.audit.modifiedDate" />
					<@s.hidden id="friendType.audit.modifiedUser"
						name="friendType.audit.modifiedUser" />

					<#-- hidden biz-->
					<@s.hidden id="siteSeq" name="siteSeq" />

					<#-- column -->
					<#include "friendTypeColumn.ftl">
				</table>
			</@s.form>
		</div>
		<#-- end .story-->

		<#-- audit -->
		<div id="audit">
			<@s.set name="version" value="friendType.version"/>
			<@s.set name="audit" value="friendType.audit"/>
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