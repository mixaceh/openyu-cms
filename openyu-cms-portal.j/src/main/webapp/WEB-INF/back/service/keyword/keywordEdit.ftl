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
			<@s.url var="parentUrl" namespace="/back/service/keyword" action="index" />
			<@s.set name="parentTitle">
				<@s.text name="keyword.title" />
			</@s.set>
			<@s.url var="currentUrl" namespace="/back/service/keyword" action="edit">
				<@s.param name="keyword.seq">
					<@s.property value="keyword.seq" />
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
				namespace="/back/service/keyword" action="editSave"
				validate="true">
				<table class="free" width="100%" border="0">

					<#-- hidden field-->
					<@s.hidden id="keyword.seq" name="keyword.seq" />
					<@s.hidden id="keyword.version" name="keyword.version" />
					<@s.hidden id="keyword.id" name="keyword.id" />
					<@s.hidden id="keyword.audit.createDate"
						name="keyword.audit.createDate" />
					<@s.hidden id="keyword.audit.createUser"
						name="keyword.audit.createUser" />
					<@s.hidden id="keyword.audit.modifiedDate"
						name="keyword.audit.modifiedDate" />
					<@s.hidden id="keyword.audit.modifiedUser"
						name="keyword.audit.modifiedUser" />

					<#-- hidden biz-->
					<@s.hidden id="siteSeq" name="siteSeq" />
					
					<#-- column -->
					<#include "keywordColumn.ftl"/>
				</table>
			</@s.form>
		</div>
		<#-- end .story-->

		<#-- audit -->
		<div id="audit">
			<@s.set name="version" value="keyword.version"/>
			<@s.set name="audit" value="keyword.audit"/>
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