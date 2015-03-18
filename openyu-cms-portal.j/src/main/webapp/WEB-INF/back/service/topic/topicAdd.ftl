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
			<@s.url var="currentUrl" namespace="/back/service/topic" action="add">
			</@s.url>
			<@s.set name="currentTitle">
				<@s.text name="global.add" />
			</@s.set>				
			<#include "/WEB-INF/app/breadcrumb.ftl">
		</div>
		<#-- end #breadcrumb-->
		
		<div class="story">
			<@s.form id="addForm" name="addForm" namespace="/back/service/topic"
				action="addSave" validate="true">
				<table class="free" width="100%" border="0">
					<#-- hidden field-->
					<@s.hidden id="topic.id" name="topic.id" />
					
					<#-- hidden biz -->
					<@s.hidden id="siteSeq" name="siteSeq" />

					<#-- column -->
					<#include "topicColumn.ftl"/>	
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