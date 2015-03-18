<#include "/WEB-INF/app/content.ftl"/>

<html>
<head>
<title><@s.text name="global.app" /></title>
</head>
<body>
	<div class="leftcontent" id="leftnav">
		<img alt="bg image"
			src="${base}/back/theme/doctors_office/img/left_bg_top.gif" />

		<#include "/WEB-INF/back/service/config/configLeftNav.ftl"/>
		<div class="left_news">
			<p>&nbsp;</p>
			<p>&nbsp;</p>
		</div>
	</div>
	<div id="centercontent">
		<#-- breadcrumb -->
		<div id="breadcrumb">
			<@s.url var="parentUrl" namespace="/back/service/ftp" action="index" />
			<@s.set name="parentTitle">
				<@s.text name="ftp.title" />
			</@s.set>
			<@s.url var="currentUrl" namespace="/back/service/ftp" action="edit">
				<@s.param name="ftp.seq">
					<@s.property value="ftp.seq" />
				</@s.param>
			</@s.url>
			<@s.set name="currentTitle">
				<@s.text name="global.edit" />
			</@s.set>				
			<#include "/WEB-INF/app/breadcrumb.ftl">
		</div>
		<#-- end #breadcrumb-->
		
		<div class="story">
			<@s.form id="editForm" name="editForm" namespace="/back/service/ftp"
				action="editSave" validate="true">
				<table class="free" width="100%" border="0">
					<#-- hidden field-->
					<@s.hidden id="ftp.seq" name="ftp.seq" />
					<@s.hidden id="ftp.version" name="ftp.version" />
					<@s.hidden id="ftp.id" name="ftp.id" />
					<@s.hidden id="ftp.audit.createDate" name="ftp.audit.createDate" />
					<@s.hidden id="ftp.audit.createUser" name="ftp.audit.createUser" />
					<@s.hidden id="ftp.audit.modifiedDate" name="ftp.audit.modifiedDate" />
					<@s.hidden id="ftp.audit.modifiedUser" name="ftp.audit.modifiedUser" />

					<#-- column -->
					<#include "ftpColumn.ftl">
				</table>
			</@s.form>
		</div>
		<#-- end .story-->

		<#-- audit -->
		<div id="audit">
			<@s.set name="version" value="ftp.version"/>
			<@s.set name="audit" value="ftp.audit"/>
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