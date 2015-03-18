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
			<@s.url var="parentUrl" namespace="/back/service/keyword" action="index" />
			<@s.set name="parentTitle">
				<@s.text name="keyword.title" />
			</@s.set>
			<@s.url var="currentUrl" namespace="/back/service/keyword" action="add">
			</@s.url>
			<@s.set name="currentTitle">
				<@s.text name="global.add" />
			</@s.set>				
			<#include "/WEB-INF/app/breadcrumb.ftl">
		</div>
		<#-- end #breadcrumb-->		
		
		<div class="story">
			<@s.form id="addForm" name="addForm"
				namespace="/back/service/keyword" action="addSave"
				validate="true">
				<table class="free" width="100%" border="0">
					<#-- hidden field-->
					<@s.hidden id="keyword.seq" name="keyword.seq" />
					<@s.hidden id="keyword.id" name="keyword.id" />
					<@s.hidden id="keyword.version" name="keyword.version" />
					
					<#-- hidden biz -->
					<@s.hidden id="siteSeq" name="siteSeq" />

					<#-- column -->
					<#include "keywordColumn.ftl"/>
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