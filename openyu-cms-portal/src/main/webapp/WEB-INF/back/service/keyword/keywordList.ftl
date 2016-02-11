<#include "/WEB-INF/app/content.ftl"/>

<html>
<head>
<title><@s.text name="global.app" /></title>
</head>

<body>

	<div class="leftcontent" id="leftnav">
		<img alt="bg image"
			src="${base}/back/theme/doctors_office/img/left_bg_top.gif" />

		<#include "/WEB-INF/back/service/maintain/maintainLeftNav.ftl"/>	
		<div class="left_news">
			<p>&nbsp;</p>
			<p>&nbsp;</p>
		</div>
	</div>
	<div id="centercontent">
		<#-- breadcrumb -->
		<div id="breadcrumb">
			<@s.url var="parentUrl" namespace="/back/service/keyword" action="index" />
			<@s.set name="parentTitle">
				<@s.text name="keyword.title" />
			</@s.set>
			<@s.url var="currentUrl" namespace="/back/service/keyword" action="list">
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
							<#-- name -->
							<td width="5%" class="searchlabel"></span> 
								<@s.text name="keyword.dictionarys.key" /> :
							</td>
							<td width="15%">
								<@s.textfield id="searcher.key" name="searcher.key" size="20" maxlength="50" />
							</td>

							<td width="15%">
								<#-- sort --><@s.text name="global.sort" /> : 
								<@s.select list="inquiry.sorts" listKey="id" listValue="%{getName(getLocale())}" 
									id="inquiry.sort.id" name="inquiry.sort.id">
								</@s.select> 
								<#-- order --> 
								<@s.select list="inquiry.orders" listKey="id" listValue="%{getName(getLocale())}" 
									id="inquiry.order.id" name="inquiry.order.id">
								</@s.select>
							</td>
							<td width="63%">
								<#-- search --> 
								<@s.url var="url" namespace="/back/service/keyword" action="find" /> 
								<@s.a href="#" onclick="paginationSubmit('searchForm',0,'%{url}')">
									<@s.text name="global.find" />
								</@s.a>
							</td>
						</tr>
					</tbody>
				</table>
				<div id="pagination">
					<@s.url var="url" namespace="/back/service/keyword"
						action="find" />
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
				<#-- add -->
				<li><@s.url var="url" namespace="/back/service/keyword"
						action="add">
						<@s.param name="keyword.seq">
							<@s.property value="keyword.seq" />
						</@s.param>
					</@s.url> <@s.a href="%{url}" target="_self">
						<@s.text name="global.add" />
					</@s.a></li>

				<#-- delete -->
				<li><@s.url var="url" namespace="/back/service/keyword"
						action="delete" /> 
					<a href="#" onclick="pickSubmit('listForm','keys','${url}',
							'<@s.text name="global.delete" />',
							'<@s.text name="global.please.pick.data" />', 
							'<@s.text name="global.confirm" />')">
						<@s.text name="global.delete" />
					</a>
				</li>

				<#-- save -->
				<li><@s.url var="url" namespace="/back/service/keyword"
						action="save" >
						<@s.param name="keyword.seq">
							<@s.property value="keyword.seq" />
						</@s.param>
					</@s.url>	
						 
					<@s.a href="%{url}" target="_self">
						<@s.text name="global.save" />
					</@s.a></li>
			</ul>
		</div>
		<#-- end #opnav-->

		<div class="story">
			<@s.form id="listForm" name="listForm">
				<@s.hidden id="keyword.seq" name="keyword.seq" />
				<@s.hidden id="keyword.id" name="keyword.id" />
				<table class="list" width="100%" border="0">
					<thead>
						<tr>
							<th width="3%" class="listcheckbox"><@s.checkbox id="allKeys"
									name="allKeys" value="false"
									onclick="pickAll('keys',this.checked)" /></th>
							<#-- <th width="6%"><@s.text name="keyword.seq" /></th> -->
							<th width="12%"><@s.text name="global.operation" /></th>
							<th width="30%"><@s.text name="keyword.dictionarys.key" /></th>
							<th width="30%"><@s.text name="keyword.dictionarys.value" /></th>
							<th width="5%"><@s.text name="keyword.dictionarys.valid" /></th>
						</tr>
					</thead>
					<tbody>
						<@s.iterator value="keyword.dictionarys.values()" id="dictionary" status="st">
							<tr>
								<#-- pick -->
								<td><@s.checkbox name="keys" fieldValue="%{key}"
										value="false" />
								</td>
								<#-- edit -->
								<td><@s.url var="url" namespace="/back/service/keyword"
										action="edit">
										<@s.param name="keyword.seq">
											<@s.property value="keyword.seq" />
										</@s.param>
										<@s.param name="dictionary.key">
											<@s.property value="key" />
										</@s.param>
									</@s.url> 
									<@s.a href="%{url}" target="_self">
										<@s.text name="global.edit" />
									</@s.a>
								</td>
								<#-- view -->
								<td><@s.url var="url" namespace="/back/service/keyword"
										action="view">
										<@s.param name="keyword.seq">
											<@s.property value="keyword.seq" />
										</@s.param>
										<@s.param name="dictionary.key">
											<@s.property value="key" />
										</@s.param>
									</@s.url> 
									<@s.a href="%{url}" target="_self">
										<@s.property value="key" />
									</@s.a>
								</td>
								<td>
									<@s.property value="value" />
								</td>
								<#-- edit -->
								<td>
									<@s.checkbox name="valid" fieldValue="true" />
								</td>
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
