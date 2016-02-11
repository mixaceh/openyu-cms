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
			<@s.url var="parentUrl" namespace="/back/service/friend" action="index" />
			<@s.set name="parentTitle">
				<@s.text name="friend.title" />
			</@s.set>
			<@s.url var="currentUrl" namespace="/back/service/friend" action="list">
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
							<#-- friendType -->
							<td width="5%" class="searchlabel"><@s.text
									name="friend.friendType" /> :</td>
							<td width="15%"><@s.select list="%{friendTypeOptions}"
									listKey="seq" listValue="%{getName(getLocale())}"
									headerKey="0" headerValue="" id="friendTypeSearcher.seq"
									name="friendTypeSearcher.seq" cssStyle="width: 155px;">
								</@s.select></td>
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
									namespace="/back/service/friend" action="find" /> <@s.a
									href="#" onclick="paginationSubmit('searchForm',0,'%{url}')">
									<@s.text name="global.find" />
								</@s.a></td>
						</tr>
						<tr>
							<#-- name -->
							<td class="searchlabel"><@s.text name="friend.names" /> :</td>
							<td><@s.textfield id="searcher.name" name="searcher.name"
									size="20" maxlength="50" /></td>
							<#-- valid -->
							<td colspan="2" class="searchlabel"><@s.text
									name="friend.valid" /> : <@s.radio list="whetherOptions" listKey="id"
									listValue="%{getName(getLocale())}"
									id="searcher.validOption.id" name="searcher.validOption.id"
									value="searcher.validOption.id" /></td>
						</tr>
					</tbody>
				</table>
				<div id="pagination">
					<@s.url var="url" namespace="/back/service/friend" action="find" />
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
				<li><@s.url var="url" namespace="/back/service/friend"
						action="add" /> <@s.a href="%{url}" target="_self">
						<@s.text name="global.add" />
					</@s.a></li>

				<#-- delete -->
				<li><@s.url var="url" namespace="/back/service/friend"
						action="delete" /> <a href="#"
					onclick="pickSubmit('listForm','seqs','${url}',
						'<@s.text name="global.delete" />',
						'<@s.text name="global.please.pick.data" />', 
						'<@s.text name="global.confirm" />')">
						<@s.text name="global.delete" />
				</a></li>

				<#-- save -->
				<li><@s.url var="url" namespace="/back/service/friend"
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
							<th width="6%"><@s.text name="friend.seq" /></th>
							<th width="12%"><@s.text name="global.operation" /></th>
							<th width="25%"><@s.text name="friend.names" /></th>
							<th width="25%"><@s.text name="friend.logo" /></th>
							<th width="7%"><@s.text name="friend.click" /></th>
							<th width="5%"><@s.text name="friend.sort" /></th>
							<th width="5%"><@s.text name="friend.valid" /></th>
						</tr>
					</thead>
					<tbody>
						<@s.iterator value="friends" var="friend" status="st">
							<tr>

								<#-- pick -->
								<td><@s.checkbox name="seqs" fieldValue="%{seq}"
										value="false" /></td>
								<td><@s.property value="seq" /></td>

								<#-- edit -->
								<#-- url conflit to friend.url so change to urlink -->
								<td><@s.url var="urlink" namespace="/back/service/friend"
										action="edit">
										<@s.param name="friend.seq">
											<@s.property value="seq" />
										</@s.param>
									</@s.url> <@s.a href="%{urlink}" target="_self">
										<@s.text name="global.edit" />
									</@s.a></td>

								<#-- view -->
								<#-- url conflit to friend.url so change to urlink -->
								<td><@s.url var="urlink" namespace="/back/service/friend"
										action="view">
										<@s.param name="friend.seq">
											<@s.property value="seq" />
										</@s.param>
									</@s.url> <@s.a href="%{urlink}" target="_self">
										<@s.property value="%{getName(getLocale())}" />
									</@s.a></td>
								</td>
								<td><@s.property value="logo" /></td>
								<td class="textright"><@s.property value="%{toString(click)}" /></td>

								<#-- edit -->
								<td><@s.textfield name="%{'friends['+#st.index+'].sort'}"
										size="10" maxlength="10" cssClass="textright"/>
								<td><@s.checkbox name="%{'friends['+#st.index+'].valid'}"
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
