<#include "/WEB-INF/app/content.ftl"/>

<html>
<head>
<title><@s.text name="global.app" /></title>
	<link rel="stylesheet" type="text/css"
		href="${base}/jquery/styles.css"
		media="screen" />
	<script language="JavaScript"
		src="${base}/jquery/script.js"
		type="text/javascript"></script>
	<script language="JavaScript"
		src="${base}/jquery/colorpicker/jquery.colorpicker.js"
		type="text/javascript"></script>
	<script language="JavaScript"
		src="${base}/jquery/colorpicker/colorpicker.js"
		type="text/javascript"></script>
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
			<@s.url var="parentUrl" namespace="/back/service/config" action="index" />
			<@s.set name="parentTitle">
				<@s.text name="config.title" />
			</@s.set>
			<#include "/WEB-INF/app/breadcrumb.ftl">
		</div>
		<#-- end #breadcrumb-->		
		
		<@s.form id="editForm" name="editForm" namespace="/back/service/config"
			action="editSave" validate="true">
			<div class="story" id="main">
				<ul id="holder">
					<li>
						<table class="free" width="100%" border="0">

							<#-- hidden field-->
							<@s.hidden id="config.seq" name="config.seq" />
							<@s.hidden id="config.version" name="config.version" />
							<@s.hidden id="config.id" name="config.id" />
							<@s.hidden id="config.audit.createDate"
								name="config.audit.createDate" />
							<@s.hidden id="config.audit.createUser"
								name="config.audit.createUser" />
							<@s.hidden id="config.audit.modifiedDate"
								name="config.audit.modifiedDate" />
							<@s.hidden id="config.audit.modifiedUser"
								name="config.audit.modifiedUser" />

							<#-- hidden biz-->
							<tbody>
								<tr>
									<td class="freelabeltitle" colspan="4" align="center"><@s.text
											name="config.system" /></td>
								</tr>
								<tr>
									<td width="20%" class="freelabel"><@s.text
											name="config.sysDeployeePath" /> :</td>
									<td width="30%"><@s.textfield id="config.sysDeployeePath"
											name="config.sysDeployeePath" /> <@s.text
											name="config.sysDeployeePath.explan" /></td>

									<td width="20%" class="freelabel"><@s.text
											name="config.sysPort" /> :</td>
									<td width="30%"><@s.textfield id="config.sysPort"
											name="config.sysPort" size="10" maxlength="10" /> <@s.text
											name="config.sysPort.explan" /></td>
								</tr>
								<tr>
									<td class="freelabel"><span class="required">*</span> <@s.text
											name="config.sysDefaultImg" />:</td>
									<td><@s.textfield id="config.sysDefaultImg"
											name="config.sysDefaultImg" size="20" maxlength="255" /> <@s.text
											name="config.sysDefaultImg.explan" /></td>

									<td class="freelabel"><span class="required">*</span> 
										<@s.text name="config.sysEmailValidate" />:</td>
									<td><@s.radio list="trueFalseOptions" listKey="id.value"
											listValue="%{getName(getLocale())}"
											id="config.sysEmailValidate" name="config.sysEmailValidate" /></td>
								</tr>
								<tr>
									<td class="freelabel"><span class="required">*</span> <@s.text
											name="config.sysUploadToDb" />:</td>
									<td><@s.radio list="trueFalseOptions" listKey="id.value"
											listValue="%{getName(getLocale())}" id="config.sysUploadToDb"
											name="config.sysUploadToDb" /></td>

									<td class="freelabel"><span class="required">*</span> <@s.text
											name="config.sysDbFileUri" />:</td>
									<td><@s.textfield id="config.sysDbFileUri"
											name="config.sysDbFileUri" size="15" maxlength="20" /> <@s.text
											name="config.sysDbFileUri.explan" /></td>
								</tr>
							</tbody>
						</table>
					</li>
					<#-- login -->
					<li>
						<table class="free" width="100%" border="0">
							<tbody>
								<tr>
									<td class="freelabeltitle" colspan="4" align="center"><@s.text
											name="config.login" /></td>
								</tr>
								<tr>
									<td width="20%" class="freelabel"><span class="required">*</span>
										<@s.text name="config.loginErrorTimes" /> :</td>
									<td width="30%"><@s.textfield id="config.loginErrorTimes"
											name="config.loginErrorTimes" /> <@s.text
											name="config.loginErrorTimes.explan" /></td>

									<td width="20%" class="freelabel"><span class="required">*</span>
										<@s.text name="config.loginErrorInterval" /> :</td>
									<td width="30%"><@s.textfield
											id="config.loginErrorInterval"
											name="config.loginErrorInterval" /> <@s.text
											name="config.loginErrorInterval.explan" /></td>
								</tr>
								<tr>
									<td width="20%" class="freelabel"><span class="required">*</span>
										<@s.text name="config.emailHost" /> :</td>
									<td width="30%"><@s.textfield id="config.emailHost"
											name="config.emailHost" /></td>

									<td width="20%" class="freelabel"><@s.text
											name="config.emailPort" /> :</td>
									<td width="30%"><@s.textfield id="config.emailPort"
											name="config.emailPort" /> <@s.text
											name="config.emailPort.explan" /></td>
								</tr>
								<tr>
									<td width="20%" class="freelabel"><span class="required">*</span>
										<@s.text name="config.emailUsername" /> :</td>
									<td width="30%"><@s.textfield id="config.emailUsername"
											name="config.emailUsername" /></td>

									<td width="20%" class="freelabel"><span class="required">*</span>
										<@s.text name="config.emailPassword" /> :</td>
									<td width="30%"><@s.textfield id="config.emailPassword"
											name="config.emailPassword" /></td>
								</tr>
								<tr>
									<td width="20%" class="freelabel"><@s.text
											name="config.emailEncoding" /> :</td>
									<td width="30%"><@s.textfield id="config.emailEncoding"
											name="config.emailEncoding" /></td>

									<td width="20%" class="freelabel"><@s.text
											name="config.emailPersonal" /> :</td>
									<td width="30%"><@s.textfield id="config.emailPersonal"
											name="config.emailPersonal" /></td>
								</tr>
								<tr>
									<td width="20%" class="freelabel"><span class="required">*</span>
										<@s.text name="config.forgotPasswordSubject" /> :</td>
									<td width="80%" colspan="3"><@s.textfield
											id="config.forgotPasswordSubject"
											name="config.forgotPasswordSubject" /></td>
								</tr>
								<tr>
									<td width="20%" class="freelabel"><@s.text
											name="config.forgotPasswordText" /> :</td>
									<td width="80%" colspan="3"><@s.textarea cols="30" rows="5"
											id="config.forgotPasswordText"
											name="config.forgotPasswordText" /> </br> <@s.text
											name="config.forgotPasswordText.explan" /></td>
								</tr>
								<tr>
									<td width="20%" class="freelabel"><span class="required">*</span>
										<@s.text name="config.registerSubject" /> :</td>
									<td width="80%" colspan="3"><@s.textfield
											id="config.registerSubject" name="config.registerSubject" /></td>
								</tr>
								<tr>
									<td width="20%" class="freelabel"><@s.text
											name="config.registerText" /> :</td>
									<td width="80%" colspan="3"><@s.textarea cols="30" rows="5"
											id="config.registerText" name="config.registerText" /> </br> <@s.text
											name="config.registerText.explan" /></td>
								</tr>
							</tbody>
						</table>
					</li>
					<#-- member -->
					<li>
						<table class="free" width="100%" border="0">
							<tbody>
								<tr>
									<td class="freelabeltitle" colspan="4" align="center"><@s.text
											name="config.member" /></td>
								</tr>
								<tr>
									<td width="20%" class="freelabel"><span class="required">*</span>
										<@s.text name="config.memberFunction" /> :</td>
									<td width="30%"><@s.radio list="trueFalseOptions"
											listKey="id.value" listValue="%{getName(getLocale())}"
											id="config.member" name="config.member" /></td>

									<td width="20%" class="freelabel"><span class="required">*</span>
										<@s.text name="config.register" /> :</td>
									<td width="30%"><@s.radio list="trueFalseOptions"
											listKey="id.value" listValue="%{getName(getLocale())}"
											id="config.register" name="config.register" /></td>
								</tr>
								<tr>
									<td width="20%" class="freelabel"><span class="required">*</span>
										<@s.text name="config.usernameMinLen" /> :</td>
									<td width="30%"><@s.textfield id="config.usernameMinLen"
											name="config.usernameMinLen" /></td>

									<td width="20%" class="freelabel"><span class="required">*</span>
										<@s.text name="config.passwordMinLen" /> :</td>
									<td width="30%"><@s.textfield id="config.passwordMinLen"
											name="config.passwordMinLen" size="10" maxlength="10" /></td>
								</tr>
								<tr>
									<td width="20%" class="freelabel"><span class="required">*</span>
										<@s.text name="config.userImgWidth" /> :</td>
									<td width="30%"><@s.textfield id="config.userImgWidth"
											name="config.userImgWidth" /></td>

									<td width="20%" class="freelabel"><span class="required">*</span>
										<@s.text name="config.userImgHeight" /> :</td>
									<td width="30%"><@s.textfield id="config.userImgHeight"
											name="config.userImgHeight" size="10" maxlength="10" /></td>
								</tr>
								<tr>
									<td width="20%" class="freelabel"><@s.text
											name="config.usernameReserved" /> :</td>
									<td width="80%" colspan="3"><@s.textarea cols="30" rows="5"
											id="config.usernameReserved" name="config.usernameReserved" />
										</br> <@s.text name="config.usernameReserved.explan" /></td>
								</tr>
							</tbody>
						</table>
					</li>
					<#-- waterMark -->
					<li>
						<table class="free" width="100%" border="0">
							<tbody>

								<tr>
									<td class="freelabeltitle" colspan="4" align="center"><@s.text
											name="config.mark" /></td>
								</tr>
								<tr>
									<td width="20%" class="freelabel"><span class="required">*</span>
										<@s.text name="config.markFunction" /> :</td>
									<td width="80%" colspan="3"><@s.radio list="trueFalseOptions"
											listKey="id.value" listValue="%{getName(getLocale())}"
											id="config.mark" name="config.mark" /></td>
								</tr>
								<tr>
									<td width="20%" class="freelabel"><span class="required">*</span>
										<@s.text name="config.markImage.txt" /> :</td>
									<td width="80%" colspan="3"><@s.text
											name="config.markImage.txt.width" />:<@s.textfield
											id="config.markWidth" name="config.markWidth" size="10"
											maxlength="10" />&nbsp;<@s.text
											name="config.markImage.txt.height" />:<@s.textfield
											id="config.markHeight" name="config.markHeight" size="10"
											maxlength="10" /> <@s.text
											name="config.markImagePath.txt.explan" /></td>
								</tr>
								<tr>
									<td width="20%" class="freelabel"><@s.text
											name="config.markImagePath" /> :</td>
									<td width="80%" colspan="3"><@s.textfield
											id="configMarkImagePath" name="config.markImagePath"
											size="20" maxlength="50" /> <input class="preview-button"
										type="button" value="preview" onclick="preview();" /> <img
										id="markPreview"
										style="border: 1px solid #ccc; vertical-align: middle"
										alt="preview" src="${base}/back/theme/watermark.png" />
										<@s.text name="config.markImagePath.explan" /></td>
								</tr>
								<tr>
									<td width="20%" class="freelabel"><span class="required">*</span>
										<@s.text name="config.markContent" /> :</td>
									<td width="80%" colspan="3"><@s.textfield
											id="config.markContent" name="config.markContent" size="20"
											maxlength="20" /> <@s.text name="config.markContent.explan" />
								</tr>
								<tr>
									<td width="20%" class="freelabel"><span class="required">*</span>
										<@s.text name="config.markContent.property" /> :</td>
									<td width="80%" colspan="3"><@s.text name="config.markSize" />:<@s.textfield
											id="config.markSize" name="config.markSize" size="10"
											maxlength="10" />&nbsp; <@s.text name="config.markColor" />:<@s.textfield
											id="config_markColor" name="config.markColor" size="10"
											maxlength="10" /> <img
										src="${base}/jquery/colorpicker/colorpicker.png"
										id="markColor" style="cursor: pointer" /> &nbsp;<@s.text
											name="config.markAlpha" />: <@s.textfield
											id="config.markAlpha" name="config.markAlpha" size="10"
											maxlength="10" /> <@s.text name="config.markAlpha.explan" /></td>
								</tr>
								<tr>
									<td width="20%" class="freelabel"><span class="required">*</span>
										<@s.text name="config.markPos" /> :</td>
									<td width="80%" colspan="3">
										<#-- markPos --> <@s.select list="%{markPosOptions}"
											listKey="id.value" listValue="%{getName(getLocale())}"
											id="config.markPos" name="config.markPos">
										</@s.select> <@s.text name="config.markOffsetX" />:<@s.textfield
											id="config.markOffsetX" name="config.markOffsetX" size="10"
											maxlength="10" />&nbsp;<@s.text name="config.markOffsetY" />:<@s.textfield
											id="config.markOffsetY" name="config.markOffsetY" size="10"
											maxlength="10" />
									</td>
								</tr>
								<#-- submit -->
							</tbody>
						</table>
					</li>
				</ul>
				<#-- waterMark -->

				<table class="free" width="100%" border="0">
					<tbody>
						<tr>
							<td class="freebutton" colspan="4"><@s.token /> <@s.submit
									value="%{getText('global.submit')}" /> <@s.reset
									value="%{getText('global.reset')}" /></td>
						</tr>
					</tbody>
				</table>
			</div>
		</@s.form>
		<#-- end .story -->
		
		<#-- audit -->
		<div id="audit">
			<@s.set name="version" value="config.version"/>
			<@s.set name="audit" value="config.audit"/>
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