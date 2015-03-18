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
		<div id="breadcrumb">
			<@s.url var="parentUrl" namespace="/back/service/contextType" action="index" />
			<@s.set name="parentTitle">
				<@s.text name="contextType.title" />
			</@s.set>
			<@s.url var="currentUrl" namespace="/back/service/contextType" action="list">
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
							<#-- id -->
							<td width="5%" class="searchlabel"><@s.text
									name="contextType.id" /> :</td>
							<td width="15%"><@s.textfield id="searcher.id"
									name="searcher.id" size="20" maxlength="255" /></td>
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
									namespace="/back/service/contextType" action="find" /> <@s.a
									href="#" onclick="paginationSubmit('searchForm',0,'%{url}')">
									<@s.text name="global.find" />
								</@s.a></td>
						</tr>
						<tr>
							<#-- names -->
							<td width="5%" class="searchlabel"></span> <@s.text
									name="contextType.names" /> :</td>
							<td width="15%"><@s.textfield id="searcher.name"
									name="searcher.name" size="20" maxlength="50" /></td>
						</tr>
						<tr>
							<#-- valid -->
							<td class="searchlabel"><@s.text name="contextType.valid" />
								:</td>
							<td colspan="3"><@s.radio list="whetherOptions" listKey="id"
									listValue="%{getName(getLocale())}"
									id="searcher.validOption.id" name="searcher.validOption.id"
									value="searcher.validOption.id" /></td>
						</tr>

					</tbody>
				</table>
				<div id="pagination">
					<@s.url var="url" namespace="/back/service/contextType"
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
				<li><@s.url var="url" namespace="/back/service/contextType"
						action="add" /> <@s.a href="%{url}" target="_self">
						<@s.text name="global.add" />
					</@s.a></li>

				<#-- delete -->
				<li><@s.url var="url" namespace="/back/service/contextType"
						action="delete" /> <a href="#"
					onclick="pickSubmit('listForm','seqs','${url}',
						'<@s.text name="global.delete" />',
						'<@s.text name="global.please.pick.data" />', 
						'<@s.text name="global.confirm" />')">
						<@s.text name="global.delete" />
				</a></li>

				<#-- save -->
				<li><@s.url var="url" namespace="/back/service/contextType"
						action="save" /> <@s.a href="%{url}" target="_self">
						<@s.text name="global.save" />
					</@s.a></li>
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
							<th width="6%"><@s.text name="contextType.seq" /></th>
							<th width="10%"><@s.text name="global.operation" /></th>
							<th width="10%"><@s.text name="contextType.id" /></th>
							<th width="25%"><@s.text name="contextType.names" /></th>
							<th width="8%"><@s.text name="contextType.imgWidth" /></th>
							<th width="8%"><@s.text name="contextType.imgHeight" /></th>
							<th width="5%"><@s.text name="contextType.image" /></th>
							<th width="5%"><@s.text name="contextType.valid" /></th>
						</tr>
					</thead>
					<tbody>
						<@s.iterator value="contextTypes" var="contextType" status="st">
							<tr>

								<#-- pick -->
								<td><@s.checkbox name="seqs" fieldValue="%{seq}"
										value="false" /></td>
								<td><@s.property value="seq" /></td>

								<#-- edit -->
								<td><@s.url var="url" namespace="/back/service/contextType"
										action="edit">
										<@s.param name="contextType.seq">
											<@s.property value="seq" />
										</@s.param>
									</@s.url> <@s.a href="%{url}" target="_self">
										<@s.text name="global.edit" />
									</@s.a></td>

								<td><@s.property value="id" /></td>
								<#-- view -->
								<td><@s.url var="url" namespace="/back/service/contextType"
										action="view">
										<@s.param name="contextType.seq">
											<@s.property value="seq" />
										</@s.param>
									</@s.url> <@s.a href="%{url}" target="_self">
										<@s.property value="%{getName(getLocale())}" />
									</@s.a></td>
								</td>
								<td class="textright"><@s.property value="%{toString(imgWidth)}" /></td>
								<td class="textright"><@s.property value="%{toString(imgHeight)}" /></td>

								<#-- edit -->
								<td><@s.checkbox
										name="%{'contextTypes['+#st.index+'].image'}"
										fieldValue="true" /></td>
								<td><@s.checkbox
										name="%{'contextTypes['+#st.index+'].valid'}"
										fieldValue="true" /></td>
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
