<#include "/WEB-INF/app/content.ftl"/>

<html>
<head>
<title><@s.text name="global.app" /></title>
</head>
<body>
	<div class="leftcontent" id="leftnav">
		<img alt="bg image"
			src="${base}/back/theme/doctors_office/img/left_bg_top.gif" />

		<#include "/WEB-INF/back/service/user/userLeftNav.ftl"/>
		<div class="left_news">
			<p>&nbsp;</p>
			<p>&nbsp;</p>
		</div>
	</div>
	<div id="centercontent">
		<#-- breadcrumb -->
		<div id="breadcrumb">
			<@s.url var="parentUrl" namespace="/back/service/group" action="index" />
			<@s.set name="parentTitle">
				<@s.text name="group.title" />
			</@s.set>
			<@s.url var="currentUrl" namespace="/back/service/group" action="view">
				<@s.param name="group.seq">
					<@s.property value="group.seq" />
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
							<@s.text name="group.names" /> :</td>
						<td width="30%"><@s.property value="%{groupName}" /></td>

						<td width="20%" class="viewlabel"><span class="required">*</span>
							<@s.text name="group.sort" /> :</td>
						<td width="30%"><@s.property value="group.sort" /></td>
					</tr>
					<tr>
						<td class="viewlabel"><span class="required">*</span> <@s.text
								name="group.dayUpload" /> :</td>
						<td><@s.property value="group.dayUpload" /> <@s.text
								name="global.unit.kb.tip" /> (<@s.text
								name="global.zero.no.restriction.tip" />)</td>

						<td class="viewlabel"><span class="required">*</span> <@s.text
								name="group.maxUpload" /> :</td>
						<td><@s.property value="group.maxUpload" /> <@s.text
								name="global.unit.kb.tip" />(<@s.text
								name="global.zero.no.restriction.tip" />)</td>
					</tr>
					<tr>
						<td class="viewlabel"><@s.text name="group.uploadSuffix" /> :</td>
						<td colspan="3"><@s.property value="group.uploadSuffix" />(<@s.text
								name="global.empty.no.restriction.tip" />, <@s.text
								name="global.multiple.use.comma.tip" />)</td>
					</tr>
					<tr>
						<td class="viewlabel"><@s.text name="group.captcha" /> :</td>
						<td><@s.property
								value="%{getTrueFalseName(group.captcha)}" /></td>

						<td class="viewlabel"><@s.text name="group.check" /> :</td>
						<td><@s.property
								value="%{getTrueFalseName(group.check)}" /></td>
					</tr>
					<tr>
						<td class="viewlabel"><span class="required">*</span> <@s.text
								name="group.browse" /> :</td>
						<td colspan="3">&nbsp;</td>
					</tr>
					<tr>
						<td class="viewlabel"><span class="required">*</span> <@s.text
								name="group.contribute" /> :</td>
						<td colspan="3">&nbsp;</td>
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
			<@s.set name="version" value="group.version"/>
			<@s.set name="audit" value="group.audit"/>
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