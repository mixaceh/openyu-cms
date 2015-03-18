<#include "/WEB-INF/app/content.ftl"/>

<html>
<head>
<title><@s.text name="global.app" /></title>
<script type="text/javascript">	
	function viewImage(a) {
		var max=300;
		var img = $("<img src='"+ a.href +"' style='border:1px solid #ccc;'/>");
		var imgDiv = $("#displayImage").append(img);	
		imgDiv.show();
		var	width = img.attr("width");
		var	height = img.attr("height");
		if(width>=height&&width>max) {
			height = height*max/width;
			width = max;
		} else if(height>=width||height>max) {
			width = width*max/height;
			height = max;
		}
		var offset = $(a).offset();
		imgDiv.css("left",offset.left+$(a).outerWidth()+3);	
		var buttom = $(window).height()+$(document).scrollTop()-max-offset.top;
		if(buttom<0) {
			imgDiv.css("top",offset.top+buttom-3);
		} else {
			imgDiv.css("top",offset.top-3);
		}
		img.width(width);
		img.height(height);
	}
	
	function hideImage(a) {
		$("#displayImage").empty().hide();
	}	
</script>
</head>

<body>
	<div id="displayImage" style="display:none;position:absolute;height:300px;width:300px"></div>
	<div class="leftcontent" id="leftnav">
		<img alt="bg image"
			src="${base}/back/theme/doctors_office/img/left_bg_top.gif" />

		<#include "/WEB-INF/back/service/resource/resourceLeftNav.ftl">
		<div class="left_news">
			<p>&nbsp;</p>
			<p>&nbsp;</p>
		</div>
	</div>
	<div id="centercontent">
		<#-- breadcrumb -->
		<div id="breadcrumb">
			<#include "resourceBreadcrumb.ftl">
		</div>
		<#-- end #breadcrumb-->
		
		<div id="opnav">
			<ul>
				<#-- add dir-->
				<li><@s.url var="url" namespace="/back/service/resource" action="addDir">
						<@s.param name="selectPath">
							<@s.property value="selectPath" escape="false" />
						</@s.param>
					</@s.url>
					<@s.a href="%{url}">
						<@s.text name="resource.addDir" />
					</@s.a></li>

				<#-- add file-->
				<li><@s.url var="url" namespace="/back/service/resource" action="addFile">
						<@s.param name="selectPath">
							<@s.property value="selectPath" escape="false" />
						</@s.param>
					</@s.url>
					<@s.a href="%{url}">
						<@s.text name="resource.addFile" />
					</@s.a></li>

				<#-- delete -->
				<li><@s.url var="url" namespace="/back/service/resource"
						action="delete" /> <a href="#"
					onclick="pickSubmit('listForm','paths','${url}',
						'<@s.text name="global.delete" />',
						'<@s.text name="global.please.pick.data" />', 
						'<@s.text name="global.confirm" />')">
						<@s.text name="global.delete" />
				</a></li>

			</ul>
			<ul>
				<#-- upload -->
				<li><@s.form id="uploadForm" name="uploadForm" namespace="/back/service/resource"
						action="upload" method ="post" enctype ="multipart/form-data" > 
						<@s.hidden id="selectPath" name="selectPath" />
				        <@s.file name ="upload" cssStyle="width: 200px"/>
				        <@s.submit value="%{getText('global.upload')}" /> 
				    </@s.form>		
				</li>
			</ul>
		</div>
		<#-- end #opnav-->

		<div class="story">
			<@s.form id="listForm" name="listForm">
				<#-- hidden biz-->
				<@s.hidden id="selectPath" name="selectPath" />	
						
				<table class="list" width="100%" border="0">
					<thead>
						<tr>
							<th width="3%" class="listcheckbox"><@s.checkbox id="allPaths"
									name="allPaths" value="false"
									onclick="pickAll('paths',this.checked)" /></th>
							<th width="3%">&nbsp;</th>
							<th width="12%"><@s.text name="global.operation" /></th>
							<th width="46%"><@s.text name="resource.fileName" /></th>
							<th width="15%"><@s.text name="resource.length" /></th>
							<th width="18%"><@s.text name="resource.lastModified" /></th>
						</tr>
					</thead>
					<tbody>
						<@s.iterator value="archives" var="archive" status="st">
							<tr>
								<#-- pick -->
								<td><@s.checkbox name="paths" fieldValue="%{path}"
										value="false" /></td>
								<td><img alt="file image" src="${base}/back/theme/file/<@s.property value="icon" />.gif" /></td>

								<td>
									<#-- edit -->
									<#if archive.editType>
										<@s.url var="url" namespace="/back/service/resource"
											action="editFile">
											<@s.param name="selectPath">
												<@s.property value="selectPath" escape="false" />
											</@s.param>
											<@s.param name="origName">
												<@s.property value="name" escape="false" />
											</@s.param>
										</@s.url>
										<@s.a href="%{url}">
											<@s.text name="global.edit" />
										</@s.a>								
									<#else>
										<span style="color: #999999;">
											<@s.text name="global.edit" />
										</span>
									</#if> |
									<#-- rename -->
									<@s.url var="url" namespace="/back/service/resource"
										action="rename">
										<@s.param name="selectPath">
											<@s.property value="selectPath" escape="false" />
										</@s.param>
										<@s.param name="origName">
											<@s.property value="name" escape="false" />
										</@s.param>
									</@s.url>
									<@s.a href="%{url}">
										<@s.text name="global.rename" />
									</@s.a>								
								</td>
																		
								<#-- view -->
								<td>
									<#if archive.directory>
										<@s.url var="url" namespace="/back/service/resource" action="list">
											<@s.param name="selectPath">
												<@s.property value="path" escape="false" />
											</@s.param>
										</@s.url>
										<@s.a href="%{url}">
											<@s.property value="name" />
										</@s.a>
									<#else>
										<#if archive.imageType>
											<a href="<@s.property value="%{#contextPath+path}" />"  target="_blank"
											onmouseover="viewImage(this)" onmouseout="hideImage(this)">
												<@s.property value="name" />
											</a>
										<#else>
											<a href="<@s.property value="%{#contextPath+path}" />"  target="_blank">
												<@s.property value="name" />
											</a>
										</#if>	
									</#if>	
								</td>								
								<td class="textright"><@s.property value="length" /></td>
								<td><@s.property value="%{toString(lastModifiedDate)}" /></td>
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

