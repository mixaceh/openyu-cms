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
			<@s.url var="parentUrl" namespace="/back/service/backup" action="index" />
			<@s.set name="parentTitle">
				<@s.text name="backup.title" />
			</@s.set>
			<@s.url var="currentUrl" namespace="/back/service/backup" action="view">
				<@s.param name="backup.seq">
					<@s.property value="backup.seq" />
				</@s.param>
			</@s.url>
			<@s.set name="currentTitle">
				<@s.text name="global.view" />
			</@s.set>				
			<#include "/WEB-INF/app/breadcrumb.ftl">
		</div>
		<#-- end #breadcrumb-->
		
		<div class="story">
			<table class="list" width="100%" border="0">
				<thead>
					<tr>
						<th width="20%"><@s.text name="backup.fieldName" /></th>
						<th width="15%"><@s.text name="backup.fieldType" /></th>
						<th width="15%"><@s.text name="backup.fieldProperty" /></th>
						<th width="15%"><@s.text name="backup.fieldDefault" /></th>
						<th width="35%"><@s.text name="backup.fieldExtra" /></th>
					</tr>
				</thead>
				<tbody>
					<@s.iterator value="fields" var="field" status="st">
						<tr>
							<td><@s.property value="name" /></td>
							<td><@s.property value="fieldType" /></td>
							<td><@s.property value="fieldProperty" /></td>
							<td><@s.property value="fieldDefault" /></td>
							<td><@s.property value="extra" /></td>
						</tr>
					</@s.iterator>
				</tbody>
			</table>
		</div>
		<#-- end .story-->

		<br /> <br /> <br />
		<div class="footer" id="footer">
			<#include "/WEB-INF/back/service/home/footer.ftl">
		</div>
	</div>
</body>
</html>