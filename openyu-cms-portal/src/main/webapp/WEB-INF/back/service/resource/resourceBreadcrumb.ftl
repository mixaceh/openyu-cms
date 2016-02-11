<@s.url var="parentUrl" namespace="/back/service/resource" action="index" />
<@s.set var="parentTitle">
	<@s.text name="resource.title" />
</@s.set>
<#include "/WEB-INF/app/breadcrumb.ftl">
<#-- parentPath -->
<@s.url var="url" namespace="/back/service/resource"
	action="list">
	<@s.param name="selectPath">
		<@s.property value="resourcePath" escape="false" />
	</@s.param>
</@s.url>
<@s.a href="%{url}">
	<@s.property value="resourcePath" />
</@s.a>
<#-- breadcrumbs -->
<@s.iterator value="breadcrumbs" var="breadcrumb" status="st">
	<@s.url var="url" namespace="/back/service/resource"
		action="list">
		<@s.param name="selectPath">
			<@s.property value="path" escape="false" />
		</@s.param>
	</@s.url>
	<@s.a href="%{url}">
		<@s.property value="name" />
	</@s.a>					
</@s.iterator>