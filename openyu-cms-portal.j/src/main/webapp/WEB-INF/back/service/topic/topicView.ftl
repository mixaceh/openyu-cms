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
			<@s.url var="parentUrl" namespace="/back/service/topic" action="index" />
			<@s.set name="parentTitle">
				<@s.text name="topic.title" />
			</@s.set>
			<@s.url var="currentUrl" namespace="/back/service/topic" action="view">
				<@s.param name="topic.seq">
					<@s.property value="topic.seq" />
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
							<@s.text name="topic.names" /> :</td>
						<td width="30%"><@s.property value="%{topicName}" /></td>

						<td width="20%" class="viewlabel">
							<@s.text name="topic.catalog" /> :</td>
						<td width="30%"></td>
					</tr>
					<tr>
						<td class="viewlabel">
							<@s.text name="topic.shortNames" /> :</td>
						<td><@s.property value="%{shortName}" /></td>

						<td class="viewlabel">
							<@s.text name="topic.keywords" /> :</td>
						<td><@s.property value="%{shortName}" /></td>
					</tr>	
					<tr>
						<td class="viewlabel">
							<@s.text name="topic.descriptions" /> :</td>
						<td colspan="3"><@s.property value="%{description}" /></td>
					</tr>			
					<tr>
						<td class="viewlabel">
							<@s.text name="topic.recommend" /> :</td>
						<td><@s.property
								value="%{getTrueFalseName(topic.recommend)}" /></td>
						<td class="viewlabel">
							<@s.text name="topic.sort" /> :</td>
						<td><@s.property value="topic.sort" /></td>
					</tr>	
					<tr>
						<td class="viewlabel">
							<@s.text name="topic.tplContent" /> :</td>
						<td colspan="3"><@s.property value="topic.tplContent" /></td>
					</tr>	
					<tr>
						<td class="freelabel">
							<@s.text name="topic.titleImg" /> :</td>
						<td><@s.property value="topic.titleImg" /></td>
						<td colspan="2"></td>
					</tr>	
					<tr>
						<td class="freelabel">
							<@s.text name="topic.contentImg" /> :</td>
						<td><@s.property value="topic.contentImg" /></td>
						<td colspan="2"></td>
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
			<@s.set name="version" value="topic.version"/>
			<@s.set name="audit" value="topic.audit"/>
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