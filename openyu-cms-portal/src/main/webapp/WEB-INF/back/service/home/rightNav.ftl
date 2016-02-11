<div id="message">
	<@s.if test="%{siteOptions!=null}">
		<div class="story">
			<@s.form id="selectSiteForm" name="selectSiteForm"
				namespace="/back/service/home" action="selectSite" validate="true">
				<@s.select list="%{siteOptions}" listKey="seq"
					listValue="%{getName(getLocale())}" id="siteOptionSeq"
					name="siteOptionSeq" onchange="this.form.submit();"
					cssStyle="width: 150px;">
				</@s.select>
			</@s.form>
		</div>
		<br />
	</@s.if>
	<#include "/WEB-INF/app/message.ftl">
</div>
<#-- end #message-->