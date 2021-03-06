<#include "/WEB-INF/app/content.ftl"/>

<html>
<head>
<title><@s.text name="global.app" /></title>
</head>
<body>
	<div class="leftcontent" id="leftnav">
		<img alt="bg image"
			src="${base}/back/theme/doctors_office/img/left_bg_top.gif" />

		<#include "/WEB-INF/back/service/resource/resourceLeftNav.ftl">
		<div class="left_news">
			<p>&nbsp;</p>
			<p>&nbsp;</p>
		</div>
	</div>
	<div id="centercontent">
		<#-- breadcrumb -->
		<div id="breadcrumb">
			<@s.url var="parentUrl" namespace="/back/service/resource" action="index" />
			<@s.set var="parentTitle">
				<@s.text name="resource.title" />
			</@s.set>
			<#include "/WEB-INF/app/breadcrumb.ftl">
			<#-- parentPath -->
			<@s.url var="url" namespace="/back/service/resource"
				action="list">
				<@s.param name="selectPath">
					<@s.property value="resourcePath" escape="false" />
				</@s.param>
			</@s.url>
			<@s.a href="%{url}">
				<@s.property value="resourcePath" />
			</@s.a>
			<#-- breadcrumbs -->
			<@s.iterator value="breadcrumbs" var="breadcrumb" status="st">
				<@s.url var="url" namespace="/back/service/resource"
					action="list">
					<@s.param name="selectPath">
						<@s.property value="path" escape="false" />
					</@s.param>
				</@s.url>
				<@s.a href="%{url}">
					<@s.property value="name" />
				</@s.a>					
			</@s.iterator>
		</div>
		<#-- end #breadcrumb-->

		<div class="story">
			<@s.form id="renameForm" name="renameForm" namespace="/back/service/resource"
				action="renameSave" validate="true">
				<table class="free" width="100%" border="0">

					<#-- hidden biz-->
					<@s.hidden id="selectPath" name="selectPath" />	
					<@s.hidden id="origName" name="origName" />	

					<#-- column -->
					<tbody>
						<tr>
							<td width="20%" class="freelabel">
								<@s.text name="resource.origName" /> :</td>
							<td width="80%"><@s.property value="origName" escape="false"/></td>
						</tr>
						<tr>
							<td class="freelabel"><span class="required">*</span>
								<@s.text name="resource.newName" /> :</td>
							<td><@s.textfield id="newName" name="newName"
									size="30" maxlength="50" /></td>
						</tr>	
						<tr>
							<td class="freebutton" colspan="4"><@s.token /> <@s.submit
									value="%{getText('global.submit')}" /> <@s.reset
									value="%{getText('global.reset')}" /></td>
						</tr>	
					</tbody>					
				</table>
			</@s.form>
		</div>
		<#-- end .story-->

		<br /> <br /> <br />
		<div class="footer" id="footer">
			<#include "/WEB-INF/back/service/home/footer.ftl">
		</div>
		<br/>
	</div>
</body>
</html>