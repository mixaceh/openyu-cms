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
			<@s.url var="parentUrl" namespace="/back/service/vote" action="index" />
			<@s.set name="parentTitle">
				<@s.text name="vote.title" />
			</@s.set>
			<@s.url var="currentUrl" namespace="/back/service/vote" action="view">
				<@s.param name="vote.seq">
					<@s.property value="vote.seq" />
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
							<@s.text name="vote.names" /> :</td>
						<td width="30%"><@s.property value="%{voteName}"/></td>
						
						<td width="20%" class="viewlabel"><span class="required">*</span>
							<@s.text name="vote.valid" /> :</td>
						<td width="30%"><@s.property value="%{getTrueFalseName(vote.valid)}" /></td>
						
					</tr>
					<tr>
						<td width="20%" class="viewlabel">
							<@s.text name="vote.descriptions" /> :</td>
						<td width="80%" colspan="3">
							<@s.property value="%{voteDescription}" /></td>
				
					</tr>
					<tr>
						<td width="20%" class="viewlabel">
							<@s.text name="vote.LimitDate" /> :</td>
						<td width="80%" colspan="3">
							<table>
								<tbody>
									<tr>
										<td><@s.text name="vote.startDate" /></td>
										<td>
											<@s.property value="%{toString(voteStartDate)}" />
										</td>
										<td rowspan="2" valign="middle"><span class="pn-fhelp"><@s.text name="vote.explan" /></span></td>
									</tr>
									<tr>
										<td><@s.text name="vote.endDate" /></td>
										<td>
											<@s.property value="%{toString(voteEndDate)}" />
										</td>
									</tr>
									<tr>
									</tr>
								</tbody>
							</table></td>
					</tr>
					<tr>
						<td width="20%" class="viewlabel">
							<@s.text name="vote.repeateHour" /> :</td>
						<td width="80%" colspan="3"><@s.property value="vote.repeateHour"/>
								<@s.text name="vote.repeateHour.explan" /></td>
					</tr>
					<tr>
						<td width="20%" class="viewlabel">
							<@s.text name="vote.restrictMember" /> :</td>
						<td width="80%" colspan="3">
							<@s.property value="%{getTrueFalseName(vote.restrictMember)}" />
							<@s.text name="vote.restrictMember.explan" /></td>
					</tr>
					<tr>
						<td width="20%" class="viewlabel">
							<@s.text name="vote.restrictIp" /> :</td>
						<td width="30%">
							<@s.property value="%{getTrueFalseName(vote.restrictIp)}" /></td>
						<td width="20%" class="viewlabel">
							<@s.text name="vote.restrictCookie" /> :</td>
						<td width="30%">
							<@s.property value="%{getTrueFalseName(vote.restrictCookie)}" />
							<@s.text name="vote.restrictCookie.explan" /></td>
					</tr>
					<tr>
						<td width="20%" class="viewlabel"><span class="required">*</span>
							<@s.text name="vote.multiSelect" /> :</td>
						<td width="30%">
							<@s.property value="vote.multiSelect" />
							</td>
						<td width="20%" class="viewlabel">
							<@s.text name="vote.defaultz" /> :</td>
						<td width="30%">
							<@s.property value="%{getTrueFalseName(vote.defaultz)}" /></td>
					</tr>
					<tr>
						<td colspan="4" class="pn-fbutton"  align="center">
							<div class="items">
								<@s.text name="vote.voteItems" />
							</div>
							<div id="itemsContainer">
								<@s.iterator value="vote.voteItems.values()" var="voteItem" status="st">
								<div class="items">
									<@s.text name="voteItem.names" />:<@s.property value="%{getName(getLocale())}" />&nbsp;
									<@s.text name="voteItem.voteCount" />:<@s.property value="%{voteCount}" />&nbsp;
									<@s.text name="voteItem.sort" />:<@s.property value="%{sort}" />&nbsp; 
								</div>
								</@s.iterator>
							</div>
				
						</td>
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
			<@s.set name="version" value="vote.version"/>
			<@s.set name="audit" value="vote.audit"/>
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