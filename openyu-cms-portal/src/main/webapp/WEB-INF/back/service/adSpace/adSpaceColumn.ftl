<tbody>
	<tr>
		<td width="20%" class="freelabel"><span class="required">*</span>
			<@s.text name="adSpace.id" /> :</td>
		<td width="30%"><@s.textfield id="adSpace.id" name="adSpace.id"
				size="30" maxlength="255" /></td>
		<td width="20%" class="freelabel"><span class="required">*</span>
			<@s.text name="adSpace.valid" /> :</td>
		<td width="30%"><@s.radio list="trueFalseOptions" listKey="id.value"
				listValue="%{getName(getLocale())}" id="adSpace.valid"
				name="adSpace.valid" /></td>
	</tr>
	<tr>
		<td class="freelabel"><span class="required">*</span> <@s.text
				name="adSpace.names" /> :</td>
		<td><@s.textfield id="adSpaceName" name="adSpaceName"
				value="%{adSpaceName}" size="30" maxlength="50" /></td>
		<td class="freelabel"><@s.text name="adSpace.descriptions" /> :</td>
		<td><@s.textarea cols="23" rows="5" id="adSpaceDescription"
				name="adSpaceDescription" /></td>
	</tr>
	<tr>
		<td class="freebutton" colspan="4"><@s.token /> <@s.submit
				value="%{getText('global.submit')}" /> <@s.reset
				value="%{getText('global.reset')}" /></td>
	</tr>
</tbody>