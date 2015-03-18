<#include "/WEB-INF/app/content.ftl"/>

<html>
<head>
<title><@s.text name="global.app" /></title>
</head>
<body>
	<div class="leftcontent" id="leftnav">
		<img alt="bg image" src="${base}/back/theme/doctors_office/img/left_bg_top.gif" />

		<#include "/WEB-INF/back/service/maintain/maintainLeftNav.ftl"/>
		<div class="left_news">  
			<p>&nbsp;</p>
			<p>&nbsp;</p>
		</div>
	</div>
	<div id="centercontent">
		<#-- breadcrumb -->
		<div id="breadcrumb">
			<@s.url var="parentUrl" namespace="/back/service/sensitivity" action="index" />
			<@s.set name="parentTitle">
				<@s.text name="sensitivity.title" />
			</@s.set>
			<@s.url var="currentUrl" namespace="/back/service/sensitivity" action="view">
				<@s.param name="sensitivity.seq">
					<@s.property value="sensitivity.seq" />
				</@s.param>
				<@s.param name="dictionary.key">
					<@s.property value="dictionary.key" />
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
							<@s.text name="sensitivity.dictionarys.key" /> :
						</td>
						<td width="30%"><@s.property value="dictionary.key" /></td>

						<td width="20%" class="viewlabel">
							<@s.text name="sensitivity.dictionarys.value" /> :
						</td>
						<td width="30%"><@s.property value="dictionary.value" /></td>
						<tr>
							<td width="20%" class="viewlabel">
								<span class="required">*</span>
								<@s.text name="sensitivity.dictionarys.valid" /> :
							</td>
							<td width="80%" colspan="3">
								<@s.property value="%{getTrueFalseName(dictionary.valid)}" />
							</td>
						</tr>
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
			<@s.set name="version" value="sensitivity.version"/>
			<@s.set name="audit" value="sensitivity.audit"/>
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