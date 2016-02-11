<#include "/WEB-INF/app/taglib.ftl">
<#-- contextPath -->
<#-- <@s.set var="contextPath" value="#request.get('javax.servlet.forward.context_path')" />-->
<#-- <@s.set var="contextPath" value="#request['javax.servlet.forward.context_path']" />-->
<@s.set var="contextPath" value="#request['.freemarker.TemplateModel'].get('base')" />
<@s.set var="base" value="#contextPath" />
