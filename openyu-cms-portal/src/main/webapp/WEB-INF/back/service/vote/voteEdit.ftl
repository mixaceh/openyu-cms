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
			<@s.url var="parentUrl" namespace="/back/service/vote" action="index" />
			<@s.set name="parentTitle">
				<@s.text name="vote.title" />
			</@s.set>
			<@s.url var="currentUrl" namespace="/back/service/vote" action="edit">
				<@s.param name="vote.seq">
					<@s.property value="vote.seq" />
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
				namespace="/back/service/vote" action="editSave" validate="true">
				<table class="free" width="100%" border="0">

					<#-- hidden field-->
					<@s.hidden id="vote.seq" name="vote.seq" />
					<@s.hidden id="vote.version" name="vote.version" />
					<@s.hidden id="vote.id" name="vote.id" />
					<@s.hidden id="vote.audit.createDate"
						name="vote.audit.createDate" />
					<@s.hidden id="vote.audit.createUser"
						name="vote.audit.createUser" />
					<@s.hidden id="vote.audit.modifiedDate"
						name="vote.audit.modifiedDate" />
					<@s.hidden id="vote.audit.modifiedUser"
						name="vote.audit.modifiedUser" />

					<#-- hidden biz-->
					<@s.hidden id="siteSeq" name="siteSeq" />
					
					<#-- column -->
					<#include "voteColumn.ftl">
				</table>
			</@s.form>
		</div>
		<#-- end .story-->

		<#-- audit -->
		<div id="audit">
			<@s.set name="version" value="vote.version"/>
			<@s.set name="audit" value="vote.audit"/>
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