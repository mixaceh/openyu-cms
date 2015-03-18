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
			<@s.url var="currentUrl" namespace="/back/service/guestbook" action="add">
			</@s.url>
			<@s.set name="currentTitle">
				<@s.text name="global.add" />
			</@s.set>				
			<#include "/WEB-INF/app/breadcrumb.ftl">
		</div>
		<#-- end #breadcrumb-->
		
		<div class="story">
			<@s.form id="addForm" name="addForm" namespace="/back/service/guestbook"
				action="addSave" validate="true">
				<table class="free" width="100%" border="0">
					<#-- hidden field-->
					<@s.hidden id="guestbook.id" name="guestbook.id" />
					<@s.hidden id="guestbook.guestbookDate" name="guestbook.guestbookDate" />
					<@s.hidden id="guestbook.replayDate" name="guestbook.replayDate" />
					<@s.hidden id="guestbook.ip" name="guestbook.ip" />
					
					<#-- hidden biz-->
					<@s.hidden id="siteSeq" name="siteSeq" />

					<#-- column -->
					<#include "guestbookColumn.ftl"/>
				</table>
			</@s.form>
		</div>
		<#-- end .story-->

		<br /> <br /> <br />
		<div class="footer" id="footer">
			<#include "/WEB-INF/back/service/home/footer.ftl">
		</div>
	</div>
</body>
</html>