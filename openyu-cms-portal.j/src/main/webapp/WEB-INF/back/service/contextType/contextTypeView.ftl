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
			<@s.url var="parentUrl" namespace="/back/service/contextType" action="index" />
			<@s.set name="parentTitle">
				<@s.text name="contextType.title" />
			</@s.set>
			<@s.url var="currentUrl" namespace="/back/service/contextType" action="view">
				<@s.param name="contextType.seq">
					<@s.property value="contextType.seq" />
				</@s.param>
			</@s.url>
			<@s.set name="currentTitle">
				<@s.text name="global.view" />
			</@s.set>			
			<#include "/WEB-INF/app/breadcrumb.ftl">
		</div>
		<#-- end #breadcrumb-->
		
		<div class="story">
			<table class="view" width="100%" border="0">
				<tbody>
					<tr>
						<td width="20%" class="viewlabel"><span class="required">*</span>
							<@s.text name="contextType.id" /> :</td>
						<td width="30%"><@s.property value="contextType.id" /></td>

						<td width="20%" class="viewlabel"><span class="required">*</span>
							<@s.text name="contextType.valid" /> :</td>
						<td width="30%"><@s.property
								value="%{getTrueFalseName(contextType.valid)}" /></td>
					</tr>
					<tr>
						<td class="viewlabel"><span class="required">*</span> <@s.text
								name="contextType.names" /> :</td>
						<td><@s.property value="%{contextTypeName}" /></td>

						<td class="viewlabel"><@s.text name="contextType.image" /> :</td>
						<td><@s.property
								value="%{getTrueFalseName(contextType.image)}" /></td>
					</tr>
					<tr>
						<td class="viewlabel"><span class="required">*</span> <@s.text
								name="contextType.imgWidth" /> :</td>
						<td><@s.property value="contextType.imgWidth" /></td>

						<td class="viewlabel"><span class="required">*</span> <@s.text
								name="contextType.imgHeight" /> :</td>
						<td><@s.property value="contextType.imgHeight" /></td>
					</tr>
					<tr>
						<td class="viewbutton" colspan="4">&nbsp;</td>
					</tr>
				</tbody>
			</table>
		</div>
		<#-- end .story-->

		<#-- audit -->
		<div id="audit">
			<@s.set name="version" value="contextType.version"/>
			<@s.set name="audit" value="contextType.audit"/>
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