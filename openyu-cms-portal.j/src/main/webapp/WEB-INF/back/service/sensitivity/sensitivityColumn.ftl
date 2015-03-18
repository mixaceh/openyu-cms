<tbody>
	<@s.hidden id="origDictionary.key" name="origDictionary.key" />
	<@s.hidden id="origDictionary.value" name="origDictionary.value" />
	<@s.hidden id="origDictionary.valid" name="origDictionary.valid" />

	<tr>
		<td width="20%" class="freelabel">
			<span class="required">*</span>
			<@s.text name="sensitivity.dictionarys.key" /> :
		</td>
		<td width="30%">
			<@s.textfield id="dictionary.key" name="dictionary.key" size="30" maxlength="50" />
		</td>

		<td width="20%" class="freelabel">
			<@s.text name="sensitivity.dictionarys.value" /> :
		</td>
		<td width="30%">
			<@s.textfield id="dictionary.value" name="dictionary.value" size="30" maxlength="50" />
		</td>
	</tr>
	<tr>
		<td width="20%" class="freelabel">
			<span class="required">*</span>
			<@s.text name="sensitivity.dictionarys.valid" /> :
		</td>
		<td width="80%" colspan="3">
			<@s.radio list="trueFalseOptions" listKey="id.value"
				listValue="%{getName(getLocale())}" id="dictionary.valid"
				name="dictionary.valid" />
		</td>
	</tr>
	<tr>
		<td class="freebutton" colspan="4">
			<@s.token /> 
			<@s.submit value="%{getText('global.submit')}" /> 
			<@s.reset value="%{getText('global.reset')}" />
		</td>
	</tr>
</tbody>