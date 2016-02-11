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
			<@s.url var="parentUrl" namespace="/back/service/topic" action="index" />
			<@s.set name="parentTitle">
				<@s.text name="topic.title" />
			</@s.set>
			<@s.url var="currentUrl" namespace="/back/service/topic" action="edit">
				<@s.param name="topic.seq">
					<@s.property value="topic.seq" />
				</@s.param>			
			</@s.url>
			<@s.set name="currentTitle">
				<@s.text name="global.edit" />
			</@s.set>				
			<#include "/WEB-INF/app/breadcrumb.ftl">
		</div>
		<#-- end #breadcrumb-->
		
		<div class="story">
			<@s.form id="editForm" name="editForm" namespace="/back/service/topic"
				action="editSave" validate="true">
				<table class="free" width="100%" border="0">

					<#-- hidden field-->
					<@s.hidden id="topic.seq" name="topic.seq" />
					<@s.hidden id="topic.version" name="topic.version" />
					<@s.hidden id="topic.id" name="topic.id" />
					<@s.hidden id="topic.audit.createDate" name="topic.audit.createDate" />
					<@s.hidden id="topic.audit.createUser" name="topic.audit.createUser" />
					<@s.hidden id="topic.audit.modifiedDate"
						name="topic.audit.modifiedDate" />
					<@s.hidden id="topic.audit.modifiedUser"
						name="topic.audit.modifiedUser" />

					<#-- hidden biz -->
					<@s.hidden id="siteSeq" name="siteSeq" />

					<#-- column -->
					<#include "topicColumn.ftl"/>	
				</table>
			</@s.form>
		</div>
		<#-- end .story-->

		<#-- audit -->
		<div id="audit">
			<@s.set name="version" value="topic.version"/>
			<@s.set name="audit" value="topic.audit"/>
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