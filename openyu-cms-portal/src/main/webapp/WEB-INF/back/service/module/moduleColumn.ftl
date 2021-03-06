<tbody>
	<tr>
		<td width="20%" class="freelabel"><span class="required">*</span>
			<@s.text name="module.names" /> :</td>
		<td width="30%"><@s.textfield id="moduleName" name="moduleName"
				value="%{moduleName}" size="30" maxlength="50" /></td>

		<td width="20%" class="freelabel"><span class="required">*</span>
			<@s.text name="module.valid" /> :</td>
		<td width="30%"><@s.radio list="trueFalseOptions" listKey="id.value"
				listValue="%{getName(getLocale())}" id="module.valid"
				name="module.valid" /></td>
	</tr>
	<tr>
		<td class="freelabel"><span class="required">*</span> <@s.text
				name="module.path" /> :</td>
		<td colspan="3"><@s.textfield id="module.path" name="module.path"
				size="30" maxlength="255" /></td>
	</tr>
	<tr>
		<td class="freelabel"><span class="required">*</span> <@s.text
				name="module.catalogPrefix" /> :</td>
		<td><@s.textfield id="module.catalogPrefix"
				name="module.catalogPrefix" size="30" maxlength="30" /></td>

		<td class="freelabel"><@s.text name="module.contextPrefix" /> :</td>
		<td><@s.textfield id="module.contextPrefix"
				name="module.contextPrefix" size="30" maxlength="30" /></td>
	</tr>
	<tr>
		<td class="freelabel"><@s.text name="module.catalogImg" /> :</td>
		<td><@s.text name="module.imgWidth" /> :<@s.textfield
				id="module.catalogImgWidth" name="module.catalogImgWidth" size="4"
				maxlength="4" /> <@s.text name="module.imgHeight" /> :<@s.textfield
				id="module.catalogImgHeight" name="module.catalogImgHeight" size="4"
				maxlength="4" /></td>

		<td class="freelabel"><@s.text name="module.contextImg" /> :</td>
		<td><@s.text name="module.imgWidth" /> :<@s.textfield
				id="module.contextImgWidth" name="module.contextImgWidth" size="4"
				maxlength="4" /> <@s.text name="module.imgHeight" /> :<@s.textfield
				id="module.contextImgHeight" name="module.contextImgHeight" size="4"
				maxlength="4" /></td>
	</tr>
	<tr>
		<td class="freelabel"><@s.text name="module.sort" /> :</td>
		<td><@s.textfield id="module.sort" name="module.sort" size="10"
				maxlength="10" /></td>

		<td class="freelabel"><@s.text name="module.context" /> :</td>
		<td><@s.radio list="trueFalseOptions" listKey="id.value"
				listValue="%{getName(getLocale())}" id="module.context"
				name="module.context" /></td>
	</tr>
	<tr>
		<td class="freebutton" colspan="4"><@s.token /> <@s.submit
				value="%{getText('global.submit')}" /> <@s.reset
				value="%{getText('global.reset')}" /></td>
	</tr>
</tbody>