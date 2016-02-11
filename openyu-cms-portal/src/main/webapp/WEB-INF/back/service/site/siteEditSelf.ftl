<#include "/WEB-INF/app/content.ftl"/>

<html>
<head>
<title><@s.text name="global.app" /></title>
</head>
<body>
	<div class="leftcontent" id="leftnav">
		<img alt="bg image"
			src="${base}/back/theme/doctors_office/img/left_bg_top.gif" />

		<#include "/WEB-INF/back/service/config/configLeftNav.ftl">
		<div class="left_news">
			<p>&nbsp;</p>
			<p>&nbsp;</p>
		</div>
	</div>
	<div id="centercontent">
		<div id="breadcrumb">
			<@s.url var="parentUrl" namespace="/back/service/site" action="editSelf" />
			<@s.set name="parentTitle">
				<@s.text name="site.self.title" />
			</@s.set>
			<@s.url var="currentUrl" namespace="/back/service/site" action="editSelf">
			</@s.url>
			<@s.set name="currentTitle">
				<@s.text name="global.edit" />
			</@s.set>				
			<#include "/WEB-INF/app/breadcrumb.ftl">
		</div>
		<#-- end #breadcrumb-->		
		
		<div class="story">
			<@s.form id="editSelfForm" name="editSelfForm" namespace="/back/service/site"
				action="editSelfSave" validate="true">
				<table class="free" width="100%" border="0">
					<#-- hidden field-->
					<@s.hidden id="site.seq" name="site.seq" />
					<@s.hidden id="site.version" name="site.version" />
					<@s.hidden id="site.audit.createDate" name="site.audit.createDate" />
					<@s.hidden id="site.audit.createUser" name="site.audit.createUser" />
					<@s.hidden id="site.audit.modifiedDate"
						name="site.audit.modifiedDate" />
					<@s.hidden id="site.audit.modifiedUser"
						name="site.audit.modifiedUser" />

					<#-- hidden biz-->

					<#-- column -->
					<#include "siteColumn.ftl">
				</table>
			</@s.form>
		</div>
		<#-- end .story-->

		<#-- audit -->
		<div id="audit">
			<@s.set name="version" value="site.version"/>
			<@s.set name="audit" value="site.audit"/>
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