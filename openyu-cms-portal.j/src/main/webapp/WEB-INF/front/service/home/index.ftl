<#include "/WEB-INF/app/content.ftl"/>

<html>
<head>
<title><@s.text name="global.app.front" /></title>
</head>


<body>
	<h3>Web service</h3>
	<div class='featurebox_center'>Just for testing</div>
	<p>
		<a href="${base}/web/service">Web service</a>
	</p>
	<p>
		<a href="${base}/web/service/module?wsdl">module?wsdl</a>
	</p>
	<p>
		<a
			href="${base}/web/service/module/findModule?valid=true">module/findModule?valid=true</a>
	</p>
	<p>
		<a
			href="${base}/web/service/module/findDefaultModule">module/findDefaultModule</a>
	</p>
</body>
</html>

