<#include "/WEB-INF/app/content.ftl"/>

<html>
<head>
<title><@s.text name="global.app" /></title>
</head>
<body>
	<div class="leftcontent" id="leftnav">
		<img alt="bg image"
			src="${base}/back/theme/doctors_office/img/left_bg_top.gif" />

		<#include "/WEB-INF/back/service/template/templateLeftNav.ftl">
		<div class="left_news">
			<p>&nbsp;</p>
			<p>&nbsp;</p>
		</div>
	</div>
	<div id="centercontent">
		<#-- breadcrumb -->
		<div id="breadcrumb">
			<#include "templateBreadcrumb.ftl">
		</div>
		<#-- end #breadcrumb-->

		<div class="story">
			<@s.form id="editFileForm" name="editFileForm" namespace="/back/service/template"
				action="editFileSave" validate="true">
				<table class="free" width="100%" border="0">

					<#-- hidden biz-->
					<@s.hidden id="selectPath" name="selectPath" />	
					<@s.hidden id="origName" name="origName" />	

					<#-- column -->
					<tbody>
						<tr>
							<td width="20%" class="freelabel"><span class="required">*</span>
								<@s.text name="template.fileName" /> :</td>
							<td width="80%"><@s.property value="origName" /></td>
						</tr>
						<tr>
							<td colspan="2"><@s.textarea id="content" name="content"
							cssStyle="width: 97%; height: 350px; margin: 10px;"/></td>
						</tr>						
						<tr>
							<td class="freebutton" colspan="4"><@s.token /> <@s.submit
									value="%{getText('global.submit')}" /> <@s.reset
									value="%{getText('global.reset')}" /></td>
						</tr>	
					</tbody>					
				</table>
			</@s.form>
		</div>
		<#-- end .story-->

		<br /> <br /> <br />
		<div class="footer" id="footer">
			<#include "/WEB-INF/back/service/home/footer.ftl">
		</div>
		<br/>
	</div>
</body>
</html>