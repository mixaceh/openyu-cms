<tbody>
	<tr>
		<td width="20%" class="freelabel"><span class="required">*</span>
			<@s.text name="site.names" /> :</td>
		<td width="30%"><@s.textfield id="siteName" name="siteName"
				value="%{siteName}" size="30" maxlength="50" /></td>

		<td width="20%" class="freelabel"><span class="required">*</span>
			<@s.text name="site.shortNames" /> :</td>
		<td width="30%"><@s.textfield id="siteShortName"
				name="siteShortName" size="30" maxlength="50" /></td>
	</tr>
	<tr>
		<td class="freelabel"><span class="required">*</span> <@s.text
				name="site.id" /> :</td>
		<td><@s.textfield id="site.id" name="site.id" size="30"
				maxlength="255" /> (<@s.text name="site.id.tip" />)</td>

		<td class="freelabel"><@s.text name="site.indexRoot" /> :</td>
		<td><@s.radio list="trueFalseOptions" listKey="id.value"
				listValue="%{getName(getLocale())}" id="site.indexRoot"
				name="site.indexRoot" /></td>
	</tr>
	<tr>
		<td class="freelabel"><@s.text name="site.alias" /> :</td>
		<td><@s.textfield id="site.alias" name="site.alias" size="30"
				maxlength="255" /> (<@s.text name="global.multiple.use.comma.tip" />)</td>

		<td class="freelabel"><@s.text name="site.redirect" /> :</td>
		<td><@s.textfield id="site.redirect" name="site.redirect"
				size="30" maxlength="255" /> (<@s.text
				name="global.multiple.use.comma.tip" />)</td>
	</tr>
	<tr>
		<td class="freelabel"><span class="required">*</span> <@s.text
				name="site.resourcePath" /> :</td>
		<td><@s.textfield id="site.resourcePath" name="site.resourcePath" size="30"
				maxlength="30" /></td>
				
		<td class="freelabel"><span class="required">*</span> <@s.text
				name="site.templatePath" /> :</td>
		<td><@s.textfield id="site.templatePath" name="site.templatePath" size="30"
				maxlength="30" /></td>
	</tr>
	<tr>
		<td class="freelabel"><span class="required">*</span> <@s.text
				name="site.relativePath" /> :</td>
		<td><@s.radio list="trueFalseOptions" listKey="id.value"
				listValue="%{getName(getLocale())}" id="site.relativePath"
				name="site.relativePath" /> (<@s.text name="site.relativePath.tip" />)</td>

		<td class="freelabel"><span class="required">*</span> <@s.text
				name="site.protocol" /> :</td>
		<td><@s.select list="%{protocolOptions}" id="site.protocol"
				name="site.protocol" cssStyle="width: 100px;">
			</@s.select></td>
	</tr>
	<tr>
		<td class="freelabel"><span class="required">*</span> <@s.text
				name="site.dynamicSuffix" /> :</td>
		<td><@s.select list="%{dynamicSuffixOptions}" id="site.dynamicSuffix"
				name="site.dynamicSuffix" cssStyle="width: 100px;">
			</@s.select></td>

		<td class="freelabel"><span class="required">*</span> <@s.text
				name="site.staticSuffix" /> :</td>
		<td><@s.select list="%{staticSuffixOptions}" id="site.staticSuffix"
				name="site.staticSuffix" cssStyle="width: 100px;">
			</@s.select></td>
	</tr>
	<tr>
		<td class="freelabel"><@s.text name="site.staticPath" /> :</td>
		<td><@s.textfield id="site.staticPath" name="site.staticPath"
				size="30" maxlength="30" /></td>

		<td class="freelabel"><span class="required">*</span> <@s.text
				name="site.staticIndex" /> :</td>
		<td><@s.radio list="trueFalseOptions" listKey="id.value"
				listValue="%{getName(getLocale())}" id="site.staticIndex"
				name="site.staticIndex" /></td>
	</tr>
	<tr>
		<td class="freelabel"><@s.text name="site.ftp" /> :</td>
		<td><@s.select list="%{ftpOptions}" listKey="seq"
				listValue="%{getName(getLocale())}" headerKey="0" headerValue=""
				id="ftpOptionSeq" name="ftpOptionSeq" cssStyle="width: 100px;">
			</@s.select></td>

		<td class="freelabel"><span class="required">*</span> <@s.text
				name="site.recover" /> :</td>
		<td><@s.radio list="trueFalseOptions" listKey="id.value"
				listValue="%{getName(getLocale())}" id="site.recover"
				name="site.recover" /></td>
	</tr>
	<tr>
		<td class="freelabel"><span class="required">*</span> <@s.text
				name="site.verifyType" /> :</td>
		<td><@s.select list="%{verifyTypeOptions}" listKey="id"
				listValue="%{getName(getLocale())}" id="site.verifyType"
				name="site.verifyType" cssStyle="width: 100px;">
			</@s.select></td>

		<td class="freelabel"><span class="required">*</span> <@s.text
				name="site.modifyType" /> :</td>
		<td><@s.select list="%{modifyTypeOptions}" listKey="id"
				listValue="%{getName(getLocale())}" id="site.modifyType"
				name="site.modifyType" cssStyle="width: 100px;">
			</@s.select></td>
	</tr>
	<tr>
		<td class="freebutton" colspan="4"><@s.token /> <@s.submit
				value="%{getText('global.submit')}" /> <@s.reset
				value="%{getText('global.reset')}" /></td>
	</tr>
</tbody>