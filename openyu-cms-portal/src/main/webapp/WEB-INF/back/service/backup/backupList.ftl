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
			<@s.url var="parentUrl" namespace="/back/service/backup" action="index" />
			<@s.set name="parentTitle">
				<@s.text name="backup.title" />
			</@s.set>
			<@s.url var="currentUrl" namespace="/back/service/backup" action="list">
			</@s.url>
			<@s.set name="currentTitle">
				<@s.text name="global.list" />
			</@s.set>				
			<#include "/WEB-INF/app/breadcrumb.ftl">
		</div>
		<#-- end #breadcrumb-->

		<div id="opnav">
			<ul>
				<#-- backup -->
				<li><@s.url var="url" namespace="/back/service/backup" action="backup" /> 
						<a href="#" onclick="pickSubmit('listForm','tables','${url}',
						'<@s.text name="backup.opt" />',
						'<@s.text name="global.please.pick.data" />', 
						'<@s.text name="global.confirm" />')">
					<@s.text name="backup.opt" /> </a>
				</li>

			</ul>
		</div>
		
		<div class="story">
			<@s.form id="listForm" name="listForm">
				<table class="list" width="100%" border="0">
					<thead>
						<tr>
							<th width="3%" class="listcheckbox"><@s.checkbox id="allSeqs"
									name="allSeqs" value="false"
									onclick="pickAll('tables',this.checked)" /></th>
							<th width="20%"><@s.text name="backup.names" /></th>
						</tr>
					</thead>
					<tbody>
						<@s.iterator value="backups" var="backup" status="st">
							<tr>

								<#-- pick -->
								<td><@s.checkbox name="tables" fieldValue="%{backup}"
										value="false" /></td>

								<#-- view -->
								<#-- url conflit to url so change to urlink -->
								<td><@s.url var="urlink" namespace="/back/service/backup"
										action="view">
										<@s.param name="tableName">
											<@s.property value="backup" />
										</@s.param>
									</@s.url> 
									<@s.a href="%{urlink}" target="_self">
										<@s.property value="backup" />
									</@s.a></td>
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
