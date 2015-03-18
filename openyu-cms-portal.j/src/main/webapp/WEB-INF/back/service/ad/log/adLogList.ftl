<#include "/WEB-INF/app/content.ftl"/>
<html>
<head>
<title><@s.text name="global.app" /></title>
</head>

<body>

	<div class="leftcontent" id="leftnav">
		<img alt="bg image"
			src="${base}/back/theme/doctors_office/img/left_bg_top.gif" />

		<#include "/WEB-INF/back/service/log/logLeftNav.ftl"/>
		<div class="left_news">
			<p>&nbsp;</p>
			<p>&nbsp;</p>
		</div>
	</div>
	<div id="centercontent">
		<#-- breadcrumb -->
		<div id="breadcrumb">
			<@s.url var="parentUrl" namespace="/back/service/ad/log" action="index" />
			<@s.set name="parentTitle">
				<@s.text name="ad.log.title" />
			</@s.set>
			<@s.url var="currentUrl" namespace="/back/service/ad/log" action="list">
			</@s.url>
			<@s.set name="currentTitle">
				<@s.text name="global.list" />
			</@s.set>				
			<#include "/WEB-INF/app/breadcrumb.ftl">
		</div>
		<#-- end #breadcrumb-->

		<div class="feature">
			<#-- search -->
			<@s.form id="searchForm" name="searchForm">
				<table class="search" width="100%" border="0">
					<tbody>
						<tr>
							<#-- userId -->
							<td width="5%" class="searchlabel"><@s.text
									name="log.userId" /> :</td>
							<td width="15%"><@s.textfield id="userId"
									name="userId" size="15" maxlength="50" /></td>
							<#-- sort,order -->
							<td width="15%"><@s.text name="global.sort" /> :<@s.select list="inquiry.sorts" listKey="id"
									listValue="%{getName(getLocale())}" id="inquiry.sort.id"
									name="inquiry.sort.id">
								</@s.select> <@s.select list="inquiry.orders" listKey="id"
									listValue="%{getName(getLocale())}" id="inquiry.order.id"
									name="inquiry.order.id">
								</@s.select></td>
							<#-- search -->
							<td width="63%"><@s.url var="url"
									namespace="/back/service/ad/log" action="find" /> <@s.a href="#"
									onclick="paginationSubmit('searchForm',0,'%{url}')">
									<@s.text name="global.find" />
								</@s.a></td>
						</tr>
						<tr>
							<#-- clientIp -->
							<td class="searchlabel"><@s.text
									name="log.clientIp" /> :</td>
							<td colspan="3"><@s.textfield id="clientIp"
									name="clientIp" size="15" maxlength="15" /></td>
						</tr>			
					</tbody>
				</table>
				<div id="pagination">
					<@s.url var="url" namespace="/back/service/ad/log" action="find" />
					<@s.include value="/WEB-INF/app/pagination.jsp">
						<@s.param name="action">
							<@s.property value="%{url}" />
						</@s.param>
					</@s.include>
				</div>
				<#-- end #pagination-->

			</@s.form>
		</div>
		<#-- end .feature-->

		<div id="opnav">
			<ul>
				<#-- delete -->
				<li><@s.url var="url" namespace="/back/service/ad/log"
						action="delete" /> <a href="#"
					onclick="pickSubmit('listForm','seqs','${url}',
						'<@s.text name="global.delete" />',
						'<@s.text name="global.please.pick.data" />', 
						'<@s.text name="global.confirm" />')">
						<@s.text name="global.delete" />
				</a></li>
			</ul>
		</div>
		<#-- end #opnav-->

		<div class="story">
			<@s.form id="listForm" name="listForm">
				<table class="list" width="100%" border="0">
					<thead>
						<tr>
							<th width="3%" class="listcheckbox"><@s.checkbox id="allSeqs"
									name="allSeqs" value="false"
									onclick="pickAll('seqs',this.checked)" /></th>
							<th width="4%"><@s.text name="log.seq" /></th>
							<th width="10%"><@s.text name="log.logDate" /></th>
							<th width="5%"><@s.text name="log.userId" /></th>
							<th width="2%"><@s.text name="log.clientIp" /></th>
							<th width="3%"><@s.text name="log.action" /></th>
							<th width="20%"><@s.text name="ad.names" /></th>
						</tr>
					</thead>
					<tbody>
						<@s.iterator value="adLogs" var="adLog" status="st">
							<tr>
								<#-- pick -->
								<td><@s.checkbox name="seqs" fieldValue="%{seq}"
										value="false" /></td>
								<td><@s.property value="seq" /></td>
								<td><@s.property value="%{toString(logDate)}" /></td>
								<td><@s.property value="userId" /></td>
								<td><@s.property value="clientIp" /></td>
								<td><@s.property value="%{getActionName(actionType.value)}" /></td>
								<td><@s.property value="%{getName(getLocale())}" /></td>
							</tr>
						</@s.iterator>
					</tbody>
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
