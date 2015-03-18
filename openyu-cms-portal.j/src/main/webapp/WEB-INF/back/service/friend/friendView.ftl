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
			<@s.url var="parentUrl" namespace="/back/service/friend" action="index" />
			<@s.set name="parentTitle">
				<@s.text name="friend.title" />
			</@s.set>
			<@s.url var="currentUrl" namespace="/back/service/friend" action="view">
				<@s.param name="friend.seq">
					<@s.property value="friend.seq" />
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
							<@s.text name="friend.names" /> :</td>
						<td width="30%"><@s.property value="%{friendName}" /></td>
						<td width="20%" class="viewlabel"><span class="required">*</span>
							<@s.text name="friend.valid" /> :</td>
						<td width="30%"><@s.property
								value="%{getTrueFalseName(friend.valid)}" /></td>
					</tr>
					<tr>
						<td class="viewlabel"><@s.text name="friend.descriptions" />
							:</td>
						<td><@s.property value="friendDescription" /></td>
						<td class="viewlabel"><@s.text name="friend.friendType" /> :</td>
						<td><@s.if test="%{friend.friendType!=null}">
								<#-- <@s.property value="friend.friendType.getName(getLocale())" />-->
								<@s.property value="friend.friendType.id" />
							</@s.if></td>
					</tr>
					<tr>
						<td class="viewlabel"><span class="required">*</span> <@s.text
								name="friend.url" /> :</td>
						<td><@s.property value="friend.url" /></td>
						<td class="viewlabel"><@s.text name="friend.email" /> :</td>
						<td><@s.property value="friend.email" /></td>
					</tr>
					<tr>
						<td class="viewlabel"><@s.text name="friend.logo" /> :</td>
						<td><@s.property value="friend.logo" /></td>
						<td class="viewlabel">&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td class="viewlabel"><span class="required">*</span> <@s.text
								name="friend.sort" /> :</td>
						<td><@s.property value="friend.sort" /></td>
						<td class="viewlabel"><span class="required">*</span> <@s.text
								name="friend.click" /> :</td>
						<td><@s.property value="friend.click" /></td>
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
			<@s.set name="version" value="friend.version"/>
			<@s.set name="audit" value="friend.audit"/>
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