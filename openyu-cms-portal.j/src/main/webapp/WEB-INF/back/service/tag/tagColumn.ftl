<tbody>
	<tr>
		<td width="20%" class="freelabel"><span class="required">*</span>
			<@s.text name="tag.id" /> :</td>
		<td width="30%"><@s.textfield id="tag.id"
				name="tag.id" size="30" maxlength="255" /></td>

		<td width="20%" class="freelabel"><span class="required">*</span> <@s.text
				name="tag.names" /> :</td>
		<td width="30%"><@s.textfield id="tagName"
				name="tagName" value="%{tagName}" size="30"
				maxlength="50" /></td>
	</tr>
	<tr>
		<td class="freebutton" colspan="4"><@s.token /> <@s.submit
				value="%{getText('global.submit')}" /> <@s.reset
				value="%{getText('global.reset')}" /></td>
	</tr>
</tbody>