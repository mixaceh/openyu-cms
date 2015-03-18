<tbody>
	<tr>
		<td class="freelabel">
			<span class="required">*</span>
			<@s.text name="guestbook.guestbookType" /> :
		</td>
		<td colspan="3">
			<@s.select list="%{guestbookTypeOptions}" listKey="seq"
				listValue="%{getName(getLocale())}" headerKey="0" headerValue=""
				id="guestbookTypeOptionSeq" name="guestbookTypeOptionSeq" cssStyle="width: 100px;">
			</@s.select>
		</td>
	</tr>
	<tr>
		<td class="freelabel"><@s.text name="guestbook.titles" /> :</td>
		<td colspan="3">
			<@s.textfield id="guestbookTitle" name="guestbookTitle" />
		</td>
	</tr>
	<tr>
		<td class="freelabel"><@s.text name="guestbook.contents" /> :</td>
		<td colspan="3">
			<@s.textfield id="guestbookContent" name="guestbookContent" />
		</td>
	</tr>
	<tr>
		<td class="freelabel"><@s.text name="guestbook.replys" /> :</td>
		<td colspan="3">
			<@s.textarea cols="23" rows="5" id="guestbookReply" name="guestbookReply" />
		</td>
	</tr>
	<tr>
		<td width="20%" class="freelabel">
			<@s.text name="guestbook.email" /> :
		</td>
		<td width="30%">
			<@s.textfield id="guestbook.email" name="guestbook.email" />
		</td>
		<td width="20%" class="freelabel">
			<@s.text name="guestbook.phone" /> :
		</td>
		<td width="30%">
			<@s.textfield id="guestbook.phone" name="guestbook.phone" />
		</td>
	</tr>
	<tr>
		<td class="freelabel"><@s.text name="guestbook.qq" /> :</td>
		<td colspan="3">
			<@s.textfield id="guestbook.qq" name="guestbook.qq" />
		</td>
	</tr>
	<tr>
		<td width="20%" class="freelabel">
			<@s.text name="guestbook.checked" /> :</td>
		<td width="30%">
			<@s.radio list="trueFalseOptions" listKey="id.value"
				listValue="%{getName(getLocale())}" id="guestbook.checked"
				name="guestbook.checked" />
		</td>
		<td width="20%" class="freelabel">
			<@s.text name="guestbook.recommend" /> :</td>
		<td width="30%">
			<@s.radio list="trueFalseOptions" listKey="id.value"
				listValue="%{getName(getLocale())}" id="guestbook.recommend"
				name="guestbook.recommend" />
		</td>
	</tr>
	<tr>
		<td class="freebutton" colspan="4"><@s.token /> <@s.submit
				value="%{getText('global.submit')}" /> <@s.reset
				value="%{getText('global.reset')}" /></td>
	</tr>
</tbody>