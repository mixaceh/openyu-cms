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
			<@s.url var="parentUrl" namespace="/back/service/guestbook" action="index" />
			<@s.set name="parentTitle">
				<@s.text name="guestbook.title" />
			</@s.set>
			<@s.url var="currentUrl" namespace="/back/service/guestbook" action="view">
				<@s.param name="guestbook.seq">
					<@s.property value="guestbook.seq" />
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
						<td width="20%" class="viewlabel">
							<span class="required">*</span>
							<@s.text name="guestbook.guestbookType" /> :
						</td>
						<td width="30%" colspan="3">
							<@s.property value="guestbook.guestbookType.seq"/>
						</td>
						
					</tr>
					<tr>
						<td width="20%" class="viewlabel">
							<@s.text name="guestbook.titles" /> :
						</td>
						<td width="30%" colspan="3">
							<@s.property value="%{guestbookTitle}" />
						</td>
					</tr>
					<tr>
						<td width="20%" class="viewlabel">
							<@s.text name="guestbook.contents" /> :
						</td>
						<td width="30%" colspan="3">
							<@s.property value="%{guestbookContent}" />
						</td>
					</tr>
					<tr>
						<td width="20%" class="viewlabel">
							<@s.text name="guestbook.replys" /> :
						</td>
						<td width="30%" colspan="3">
							<@s.property value="%{guestbookReply}" />
						</td>
					</tr>
					<tr>
						<td width="20%" class="viewlabel">
							<@s.property value="guestbook.email" /> :
						</td>
						<td width="30%">
							<@s.property value="guestbook.email" />
						</td>
						<td width="20%" class="viewlabel">
							<@s.property value="guestbook.phone" /> :
						</td>
						<td width="30%">
							<@s.property value="guestbook.phone" />
						</td>
					</tr>
					<tr>
						<td class="viewlabel"><@s.text name="guestbook.qq" /> :</td>
						<td colspan="3">
							<@s.property value="guestbook.qq" />
						</td>
					</tr>
					<tr>
						<td width="20%" class="viewlabel">
							<@s.text name="guestbook.checked" /> :</td>
						<td width="30%">
							<@s.property value="%{getTrueFalseName(guestbook.checked)}" />
						</td>
						<td width="20%" class="viewlabel">
							<@s.text name="guestbook.recommend" /> :</td>
						<td width="30%">
							<@s.property value="%{getTrueFalseName(guestbook.recommend)}" />
						</td>
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
			<@s.set name="version" value="guestbook.version"/>
			<@s.set name="audit" value="guestbook.audit"/>
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