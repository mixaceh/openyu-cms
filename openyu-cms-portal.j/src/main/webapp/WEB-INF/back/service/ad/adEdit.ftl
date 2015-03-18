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
			<@s.url var="parentUrl" namespace="/back/service/ad" action="index" />
			<@s.set name="parentTitle">
				<@s.text name="ad.title" />
			</@s.set>
			<@s.url var="currentUrl" namespace="/back/service/ad" action="edit">
				<@s.param name="ad.seq">
					<@s.property value="ad.seq" />
				</@s.param>			
			</@s.url>
			<@s.set name="currentTitle">
				<@s.text name="global.edit" />
			</@s.set>				
			<#include "/WEB-INF/app/breadcrumb.ftl">
		</div>
		<#-- end #breadcrumb-->
		
		<div class="story">
			<@s.form id="editForm" name="editForm" namespace="/back/service/ad"
				action="editSave" validate="true">
				<table class="free" width="100%" border="0">

					<#-- hidden field-->
					<@s.hidden id="ad.seq" name="ad.seq" />
					<@s.hidden id="ad.version" name="ad.version" />
					<@s.hidden id="ad.id" name="ad.id" />
					<@s.hidden id="ad.audit.createDate" name="ad.audit.createDate" />
					<@s.hidden id="ad.audit.createUser" name="ad.audit.createUser" />
					<@s.hidden id="ad.audit.modifiedDate"
						name="ad.audit.modifiedDate" />
					<@s.hidden id="ad.audit.modifiedUser"
						name="ad.audit.modifiedUser" />

					<#-- hidden biz -->
					<@s.hidden id="siteSeq" name="siteSeq" />

					<#-- column -->
					<#include "adColumn.ftl"/>	
				</table>
			</@s.form>
		</div>
		<#-- end .story-->

		<#-- audit -->
		<div id="audit">
			<@s.set name="version" value="ad.version"/>
			<@s.set name="audit" value="ad.audit"/>
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