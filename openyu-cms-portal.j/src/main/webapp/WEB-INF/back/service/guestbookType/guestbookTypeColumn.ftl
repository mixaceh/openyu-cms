<tbody>
	<tr>
		<td width="20%" class="freelabel"><span class="required">*</span>
			<@s.text name="guestbookType.id" /> :</td>
		<td width="30%"><@s.textfield id="guestbookType.id" name="guestbookType.id"
				size="30" maxlength="255" /></td>
		<td width="20%" class="freelabel"><span class="required">*</span>
			<@s.text name="guestbookType.sort" /> :</td>
		<td width="30%">
			<@s.textfield id="guestbookType.sort" name="guestbookType.sort" />
		</td>
	</tr>
	<tr>
		<td class="freelabel"><span class="required">*</span> <@s.text
				name="guestbookType.names" /> :</td>
		<td><@s.textfield id="guestbookTypeName" name="guestbookTypeName"
				value="%{guestbookTypeName}" size="30" maxlength="50" /></td>
		<td class="freelabel"><@s.text name="guestbookType.descriptions" /> :</td>
		<td><@s.textarea cols="23" rows="5" id="guestbookTypeDescription"
				name="guestbookTypeDescription" /></td>
	</tr>
	<tr>
		<td class="freebutton" colspan="4"><@s.token /> <@s.submit
				value="%{getText('global.submit')}" /> <@s.reset
				value="%{getText('global.reset')}" /></td>
	</tr>
</tbody>