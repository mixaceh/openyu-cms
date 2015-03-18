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
			<@s.url var="parentUrl" namespace="/back/service/ad" action="index" />
			<@s.set name="parentTitle">
				<@s.text name="ad.title" />
			</@s.set>
			<@s.url var="currentUrl" namespace="/back/service/ad" action="view">
				<@s.param name="ad.seq">
					<@s.property value="ad.seq" />
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
							<@s.text name="ad.names" /> :</td>
						<td width="30%"><@s.property value="%{adName}" /></td>

						<td width="20%" class="viewlabel"><span class="required">*</span>
							<@s.text name="ad.valid" /> :</td>
						<td width="30%"><@s.property
								value="%{getTrueFalseName(ad.valid)}" /></td>
					</tr>
					<tr>
						<td class="viewlabel"><span class="required">*</span>
							<@s.text name="ad.adSpace" /> :</td>
						<td><@s.if test="%{ad.adSpace!=null}">
								<@s.property value="ad.adSpace.seq" />
							</@s.if></td>
						<td class="viewlabel"><span class="required">*</span> 
							<@s.text name="ad.adType" /> :</td>
						<td><@s.property value="%{getAdTypeName(ad.adType)}"/></td>
					</tr>	
					<tr>
						<td class="viewlabel"><span class="required">*</span>
							<@s.text name="ad.adTitle" /> :</td>
						<td><@s.property value="ad.title" /></td>

						<td class="viewlabel"><span class="required">*</span>
							<@s.text name="ad.url" /> :</td>
						<td><@s.property value="ad.url" /></td>
					</tr>	
					<tr>
						<td class="viewlabel"><span class="required">*</span>
							<@s.text name="ad.logo" /> :</td>
						<td><@s.property value="ad.logo" /></td>
						<td class="viewlabel">&nbsp;</td>
						<td>&nbsp;</td>
					</tr>			
					<tr>
						<td class="viewlabel"><span class="required">*</span>
							<@s.text name="ad.imgWidth" /> :</td>
						<td><@s.property value="ad.imgWidth" /></td>
						<td class="viewlabel"><span class="required">*</span>
							<@s.text name="ad.imgHeight" /> :</td>
						<td><@s.property value="ad.imgHeight" /></td>
					</tr>	
					<tr>
						<td class="viewlabel"><span class="required">*</span>
							<@s.text name="ad.target" /> :</td>
						<td><@s.property value="ad.target" /></td>
						<td class="viewlabel"><span class="required">*</span>
							<@s.text name="ad.weight" /> :</td>
						<td><@s.property value="ad.weight" /></td>
					</tr>	
					<tr>
						<td class="viewlabel">
							<@s.text name="ad.begDate" /> :</td>
						<td><@s.property value="%{toString(ad.begDate,'yyyy/MM/dd')}" /></td>
						<td class="viewlabel">
							<@s.text name="ad.endDate" /> :</td>
						<td><@s.property value="%{toString(ad.endDate,'yyyy/MM/dd')}" /></td>
					</tr>	
					<tr>
						<td class="viewlabel"><span class="required">*</span>
							<@s.text name="ad.click" /> :</td>
						<td><@s.property value="ad.click" /></td>
						<td class="viewlabel"><span class="required">*</span>
							<@s.text name="ad.display" /> :</td>
						<td><@s.property value="ad.display" /></td>
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
			<@s.set name="version" value="ad.version"/>
			<@s.set name="audit" value="ad.audit"/>
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