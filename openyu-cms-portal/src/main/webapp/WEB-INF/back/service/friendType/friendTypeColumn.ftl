<tbody>
	<tr>
		<td width="20%" class="freelabel"><span class="required">*</span>
			<@s.text name="friendType.id" /> :</td>
		<td width="30%"><@s.textfield id="friendType.id"
				name="friendType.id" size="30" maxlength="255" /></td>

		<td width="20%" class="freelabel"><@s.text name="friendType.sort" />
			:</td>
		<td width="30%"><@s.textfield id="friendType.sort"
				name="friendType.sort" size="10" maxlength="10" /></td>
	</tr>
	<tr>
		<td class="freelabel"><span class="required">*</span> <@s.text
				name="friendType.names" /> :</td>
		<td colspan="3"><@s.textfield id="friendTypeName"
				name="friendTypeName" value="%{friendTypeName}" size="30"
				maxlength="50" /></td>
	</tr>
	<tr>
		<td class="freebutton" colspan="4"><@s.token /> <@s.submit
				value="%{getText('global.submit')}" /> <@s.reset
				value="%{getText('global.reset')}" /></td>
	</tr>
</tbody>