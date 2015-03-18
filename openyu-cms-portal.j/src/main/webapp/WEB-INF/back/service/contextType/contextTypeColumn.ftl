<tbody>
	<tr>
		<td width="20%" class="freelabel"><span class="required">*</span>
			<@s.text name="contextType.id" /> :</td>
		<td width="30%"><@s.textfield id="contextType.id"
				name="contextType.id" size="30" maxlength="255" /></td>

		<td width="20%" class="freelabel"><span class="required">*</span>
			<@s.text name="contextType.valid" /> :</td>
		<td width="30%"><@s.radio list="trueFalseOptions" listKey="id.value"
				listValue="%{getName(getLocale())}" id="contextType.valid"
				name="contextType.valid" /></td>
	</tr>
	<tr>
		<td class="freelabel"><span class="required">*</span> <@s.text
				name="contextType.names" /> :</td>
		<td><@s.textfield id="contextTypeName" name="contextTypeName"
				value="%{contextTypeName}" size="30" maxlength="50" /></td>
		<td class="freelabel"><@s.text name="contextType.image" /> :</td>
		<td><@s.radio list="trueFalseOptions" listKey="id.value"
				listValue="%{getName(getLocale())}" id="contextType.image"
				name="contextType.image" /></td>
	</tr>
	<tr>
		<td class="freelabel"><span class="required">*</span> <@s.text
				name="contextType.imgWidth" /> :</td>
		<td><@s.textfield id="contextType.imgWidth"
				name="contextType.imgWidth" size="10" maxlength="10" /></td>

		<td class="freelabel"><span class="required">*</span> <@s.text
				name="contextType.imgHeight" /> :</td>
		<td><@s.textfield id="contextType.imgHeight"
				name="contextType.imgHeight" size="10" maxlength="10" /></td>
	</tr>
	<tr>
		<td class="freebutton" colspan="4"><@s.token /> <@s.submit
				value="%{getText('global.submit')}" /> <@s.reset
				value="%{getText('global.reset')}" /></td>
	</tr>
</tbody>