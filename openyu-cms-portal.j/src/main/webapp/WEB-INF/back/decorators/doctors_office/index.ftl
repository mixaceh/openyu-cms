<#include "/WEB-INF/app/doc.ftl">
<#include "/WEB-INF/app/taglib.ftl">

<html>
	<head>
		<#include "/WEB-INF/app/meta.ftl">
		<@s.head />
		<@sx.head extraLocales="zh,zh-tw,zh-cn,en-us" />
		<title><@decorator.title default="global.app" /></title>
	
		<link rel="stylesheet" type="text/css"
			href="${base}/back/theme/doctors_office/doctors_office.css"
			media="screen" />
		<script language="JavaScript"
			src="${base}/app/generic.js"
			type="text/javascript"></script>
		<script language="JavaScript"
			src="${base}/jquery/jquery-1.10.2.js"
			type="text/javascript"></script>
		<@decorator.head />	
	</head>
	<body>
		<div id="banner">
			<div class="top_links clearfix" id="topnav">
				<#include "/WEB-INF/back/service/home/topNav.ftl">
			</div>
			<img alt="pumpkin"
				src="${base}/back/theme/doctors_office/img/header_logo.jpg" />
			<div class="page_title">
				<span id="page_title"> 
					<#-- userSeession.site --> 
					<@s.if test="%{userSession.site!=null}">
						<@s.property value="%{userSession.site.getName(getLocale())}" />
					</@s.if>&nbsp;
				</span>
				<br />&nbsp;<br />
				<div id="nav">
					<#include "/WEB-INF/back/service/home/nav.ftl">
				</div>
			</div>
		</div>
	
		<@decorator.body />
	
		<div id="rightcontent">
			<div>
				<#include "/WEB-INF/back/service/home/rightNav.ftl"/>
			</div>
		</div>
	
	</body>
</html>
