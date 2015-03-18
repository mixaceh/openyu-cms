<tbody>
	<tr>
		<td width="20%" class="freelabel"><span class="required">*</span>
			<@s.text name="ftp.names" /> :</td>
		<td width="30%"><@s.textfield id="ftpName" name="ftpName"
				value="%{ftpName}" size="30" maxlength="50" /></td>

		<td width="20%" class="freelabel"><span class="required">*</span>
			<@s.text name="ftp.ip" /> :</td>
		<td width="30%"><@s.textfield id="ftp.ip" name="ftp.ip" size="30"
				maxlength="30" /></td>
	</tr>
	<tr>
		<td class="freelabel"><span class="required">*</span> <@s.text
				name="ftp.port" /> :</td>
		<td><@s.textfield id="ftp.port" name="ftp.port" size="10"
				maxlength="10" /> (<@s.text name="ftp.port.tip" />)</td>

		<td class="freelabel"><span class="required">*</span> <@s.text
				name="ftp.timeout" /> :</td>
		<td><@s.textfield id="ftp.timeout" name="ftp.timeout" size="10"
				maxlength="10" /> <@s.text name="global.unit.sec.tip" />(<@s.text
				name="ftp.timeout.tip" />)</td>
	</tr>
	<tr>
		<td class="freelabel"><span class="required">*</span> <@s.text
				name="ftp.account" /> :</td>
		<td><@s.textfield id="ftp.account" name="ftp.account" size="30"
				maxlength="30" /></td>
		<td class="freelabel"><@s.text name="ftp.password" /> :</td>
		<td><@s.password id="ftp.password" name="ftp.password"
				showPassword="true" size="10" maxlength="10" /> (<@s.text
				name="global.password.tip" />)</td>
	</tr>
	<tr>
		<td class="freelabel"><@s.text name="ftp.path" /> :</td>
		<td><@s.textfield id="ftp.path" name="ftp.path" size="30"
				maxlength="255" /> (<@s.text name="ftp.path.tip" />)</td>
		<td class="freelabel"><span class="required">*</span> <@s.text
				name="ftp.encoding" /> :</td>
		<td><@s.select list="%{encodingOptions}" id="ftp.encoding"
				name="ftp.encoding" cssStyle="width: 100px;">
			</@s.select></td>
	</tr>
	<tr>
		<td class="freelabel"><span class="required">*</span> <@s.text
				name="ftp.url" /> :</td>
		<td colspan="3"><@s.textfield id="ftp.url" name="ftp.url"
				size="30" maxlength="255" /> (<@s.text name="ftp.url.tip" />)</td>
	</tr>
	<tr>
		<td class="freebutton" colspan="4"><@s.token /> <@s.submit
				value="%{getText('global.submit')}" /> <@s.reset
				value="%{getText('global.reset')}" /></td>
	</tr>
</tbody>