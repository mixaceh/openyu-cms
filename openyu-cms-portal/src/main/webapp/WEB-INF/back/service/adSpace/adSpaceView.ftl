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
			<@s.url var="parentUrl" namespace="/back/service/adSpace" action="index" />
			<@s.set name="parentTitle">
				<@s.text name="adSpace.title" />
			</@s.set>
			<@s.url var="currentUrl" namespace="/back/service/adSpace" action="view">
				<@s.param name="adSpace.seq">
					<@s.property value="adSpace.seq" />
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
							<@s.text name="adSpace.id" /> :</td>
						<td width="30%"><@s.property value="adSpace.id" /></td>

						<td width="20%" class="viewlabel"><span class="required">*</span>
							<@s.text name="adSpace.valid" /> :</td>
						<td width="30%"><@s.property
								value="%{getTrueFalseName(adSpace.valid)}" /></td>
					</tr>
					<tr>
						<td class="viewlabel"><span class="required">*</span> <@s.text
								name="adSpace.names" /> :</td>
						<td><@s.property value="%{adSpaceName}" /></td>
						<td class="viewlabel"><@s.text name="adSpace.descriptions" />
							:</td>
						<td><@s.property value="%{adSpaceDescription}" /></td>
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
			<@s.set name="version" value="adSpace.version"/>
			<@s.set name="audit" value="adSpace.audit"/>
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