<tbody>
	<tr>
		<td width="20%" class="freelabel"><span class="required">*</span>
			<@s.text name="friend.names" /> :</td>
		<td width="30%"><@s.textfield id="friendName" name="friendName"
				value="%{friendName}" size="30" maxlength="50" /></td>
		<td width="20%" class="freelabel"><span class="required">*</span>
			<@s.text name="friend.valid" /> :</td>
		<td width="30%"><@s.radio list="trueFalseOptions" listKey="id.value"
				listValue="%{getName(getLocale())}" id="friend.valid"
				name="friend.valid" /></td>
	</tr>
	<tr>
		<td class="freelabel"><@s.text name="friend.descriptions" /> :</td>
		<td><@s.textarea cols="23" rows="5" id="friendDescription"
				name="friendDescription" /></td>
		<td class="freelabel"><@s.text name="friend.friendType" /> :</td>
		<td><@s.select list="%{friendTypeOptions}" listKey="seq"
				listValue="%{getName(getLocale())}" headerKey="0" headerValue=""
				id="friendTypeOptionSeq" name="friendTypeOptionSeq" cssStyle="width: 100px;">
			</@s.select></td>
	</tr>
	<tr>
		<td class="freelabel"><span class="required">*</span> <@s.text
				name="friend.url" /> :</td>
		<td><@s.textfield id="friend.url" name="friend.url" size="30"
				maxlength="255" /></td>
		<td class="freelabel"><@s.text name="friend.email" /> :</td>
		<td><@s.textfield id="friend.email" name="friend.email" size="30"
				maxlength="100" /></td>
	</tr>
	<tr>
		<td class="freelabel"><@s.text name="friend.logo" /> :</td>
		<td><@s.textfield id="friend.logo" name="friend.logo" /></td>
		<td class="freelabel">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td class="freelabel"><span class="required">*</span> <@s.text
				name="friend.sort" /> :</td>
		<td><@s.textfield id="friend.sort" name="friend.sort" size="10"
				maxlength="10" /></td>
		<td class="freelabel"><span class="required">*</span> <@s.text
				name="friend.click" /> :</td>
		<td><@s.textfield id="friend.click" name="friend.click" size="10"
				maxlength="10" /></td>
	</tr>
	<tr>
		<td class="freebutton" colspan="4"><@s.token /> <@s.submit
				value="%{getText('global.submit')}" /> <@s.reset
				value="%{getText('global.reset')}" /></td>
	</tr>
</tbody>