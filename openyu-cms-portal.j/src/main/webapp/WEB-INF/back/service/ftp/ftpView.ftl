<#include "/WEB-INF/app/content.ftl"/>

<html>
<head>
<title><@s.text name="global.app" /></title>
</head>

<body>
	<div class="leftcontent" id="leftnav">
		<img alt="bg image"
			src="${base}/back/theme/doctors_office/img/left_bg_top.gif" />

		<#include "/WEB-INF/back/service/config/configLeftNav.ftl"/>
		<div class="left_news">
			<p>&nbsp;</p>
			<p>&nbsp;</p>
		</div>
	</div>
	<div id="centercontent">
		<#-- breadcrumb -->
		<div id="breadcrumb">
			<@s.url var="parentUrl" namespace="/back/service/ftp" action="index" />
			<@s.set name="parentTitle">
				<@s.text name="ftp.title" />
			</@s.set>
			<@s.url var="currentUrl" namespace="/back/service/ftp" action="view">
				<@s.param name="ftp.seq">
					<@s.property value="ftp.seq" />
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
						<td width="20%" class="viewlabel"><span class="required">*</span>
							<@s.text name="ftp.names" /> :</td>
						<td width="30%"><@s.property value="%{ftpName}" /></td>

						<td width="20%" class="viewlabel"><span class="required">*</span>
							<@s.text name="ftp.ip" /> :</td>
						<td width="30%"><@s.property value="ftp.ip" /></td>
					</tr>
					<tr>
						<td class="viewlabel"><span class="required">*</span> <@s.text
								name="ftp.port" /> :</td>
						<td><@s.property value="ftp.port" /> (<@s.text
								name="ftp.port.tip" />)</td>

						<td class="viewlabel"><span class="required">*</span> <@s.text
								name="ftp.timeout" /> :</td>
						<td><@s.property value="ftp.timeout" /> <@s.text
								name="global.unit.sec.tip" /> (<@s.text name="ftp.timeout.tip" />)</td>
					</tr>
					<tr>
						<td class="viewlabel"><span class="required">*</span> <@s.text
								name="ftp.account" /> :</td>
						<td><@s.property value="ftp.account" /></td>
						<td class="viewlabel"><@s.text name="ftp.password" /> :</td>
						<td>******** (<@s.text name="global.password.tip" />)
						</td>
					</tr>
					<tr>
						<td class="viewlabel"><@s.text name="ftp.path" /> :</td>
						<td><@s.property value="ftp.path" /> (<@s.text
								name="ftp.path.tip" />)</td>
						<td class="viewlabel"><span class="required">*</span> <@s.text
								name="ftp.encoding" /> :</td>
						<td><@s.property value="ftp.encoding" /></td>
					</tr>
					<tr>
						<td class="viewlabel"><span class="required">*</span> <@s.text
								name="ftp.url" /> :</td>
						<td colspan="3"><@s.property value="ftp.url" /> (<@s.text
								name="ftp.url.tip" />)</td>
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
			<@s.set name="version" value="ftp.version"/>
			<@s.set name="audit" value="ftp.audit"/>
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