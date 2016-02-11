<tbody>
	<tr>
		<td width="20%" class="freelabel"><span class="required">*</span>
			<@s.text name="group.names" /> :</td>
		<td width="30%"><@s.textfield id="groupName" name="groupName"
				value="%{groupName}" size="30" maxlength="50" /></td>

		<td width="20%" class="freelabel"><span class="required">*</span>
			<@s.text name="group.sort" /> :</td>
		<td width="30%"><@s.textfield id="group.sort" name="group.sort"
				size="10" maxlength="10" /></td>
	</tr>
	<tr>
		<td class="freelabel"><span class="required">*</span> <@s.text
				name="group.dayUpload" /> :</td>
		<td><@s.textfield id="group.dayUpload" name="group.dayUpload"
				size="10" maxlength="10" /> <@s.text name="global.unit.kb.tip" /> (<@s.text
				name="global.zero.no.restriction.tip" />)</td>

		<td class="freelabel"><span class="required">*</span> <@s.text
				name="group.maxUpload" /> :</td>
		<td><@s.textfield id="group.maxUpload" name="group.maxUpload"
				size="10" maxlength="10" /> <@s.text name="global.unit.kb.tip" />(<@s.text
				name="global.zero.no.restriction.tip" />)</td>
	</tr>
	<tr>
		<td class="freelabel"><@s.text name="group.uploadSuffix" /> :</td>
		<td colspan="3"><@s.textfield id="group.uploadSuffix"
				name="group.uploadSuffix" size="30" maxlength="255" /> (<@s.text
				name="global.empty.no.restriction.tip" />, <@s.text
				name="global.multiple.use.comma.tip" />)</td>
	</tr>
	<tr>
		<td class="freelabel"><@s.text name="group.captcha" /> :</td>
		<td><@s.radio list="trueFalseOptions" listKey="id.value"
				listValue="%{getName(getLocale())}" id="group.captcha"
				name="group.captcha" /></td>

		<td class="freelabel"><@s.text name="group.check" /> :</td>
		<td><@s.radio list="trueFalseOptions" listKey="id.value"
				listValue="%{getName(getLocale())}" id="group.check"
				name="group.check" /></td>
	</tr>
	<tr>
		<td class="freelabel"><span class="required">*</span> <@s.text
				name="group.browse.permission" /> :</td>
		<td colspan="3">&nbsp;</td>
	</tr>
	<tr>
		<td class="freelabel"><span class="required">*</span> <@s.text
				name="group.contribute.permission" /> :</td>
		<td colspan="3">&nbsp;</td>
	</tr>
	<tr>
		<td class="freebutton" colspan="4"><@s.token /> <@s.submit
				value="%{getText('global.submit')}" /> <@s.reset
				value="%{getText('global.reset')}" /></td>
	</tr>
</tbody>