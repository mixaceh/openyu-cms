<tbody>
	<tr>
		<td width="20%" class="freelabel"><span class="required">*</span>
			<@s.text name="ad.names" /> :</td>
		<td width="30%"><@s.textfield id="adName" name="adName"
				value="%{adName}" size="30" maxlength="50" /></td>
		<td width="20%" class="freelabel"><span class="required">*</span>
			<@s.text name="ad.valid" /> :</td>
		<td width="30%"><@s.radio list="trueFalseOptions" listKey="id.value"
				listValue="%{getName(getLocale())}" id="ad.valid"
				name="ad.valid" /></td>
	</tr>
	<tr>
		<td class="freelabel"><span class="required">*</span>
			<@s.text name="ad.adSpace" /> :</td>
		<td><@s.select list="%{adSpaceOptions}" listKey="seq"
				listValue="%{getName(getLocale())}" headerKey="0" headerValue=""
				id="adSpaceOptionSeq" name="adSpaceOptionSeq" cssStyle="width: 100px;">
			</@s.select></td>
		<td class="freelabel"><span class="required">*</span> 
			<@s.text name="ad.adType" /> :</td>
		<td><@s.select list="%{adTypeOptions}" listKey="id"
				listValue="%{getName(getLocale())}" id="ad.adType"
				name="ad.adType" cssStyle="width: 100px;">
			</@s.select></td>
	</tr>	
	<tr>
		<td class="freelabel"><span class="required">*</span>
			<@s.text name="ad.adTitle" /> :</td>
		<td><@s.textfield id="ad.title" name="ad.title"  size="30"
				maxlength="255" /></td>
		<td class="freelabel"><span class="required">*</span>
			<@s.text name="ad.url" /> :</td>
		<td><@s.textfield id="ad.url" name="ad.url" size="30"
				maxlength="255" /></td>
	</tr>	
	<tr>
		<td class="freelabel"><span class="required">*</span>
			<@s.text name="ad.logo" /> :</td>
		<td><@s.textfield id="ad.logo" name="ad.logo" size="30"
				maxlength="100" /></td>
		<td class="freelabel">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>	
	<tr>
		<td class="freelabel"><span class="required">*</span>
			<@s.text name="ad.imgWidth" /> :</td>
		<td><@s.textfield id="ad.imgWidth" name="ad.imgWidth" size="4"
				maxlength="4" /></td>
		<td class="freelabel"><span class="required">*</span>
			<@s.text name="ad.imgHeight" /> :</td>
		<td><@s.textfield id="ad.imgHeight" name="ad.imgHeight" size="4"
				maxlength="4" /></td>
	</tr>	
	<tr>
		<td class="freelabel"><span class="required">*</span>
			<@s.text name="ad.target" /> :</td>
		<td><@s.select list="%{targetOptions}" id="ad.target"
				name="ad.target" cssStyle="width: 100px;">
			</@s.select></td>
		<td class="freelabel"><span class="required">*</span>
			<@s.text name="ad.weight" /> :</td>
		<td><@s.textfield id="ad.weight" name="ad.weight" size="10"
				maxlength="10" /></td>
	</tr>	
	<tr>
		<td class="freelabel">
			<@s.text name="ad.begDate" /> :</td>
		<td><@sx.datetimepicker name="ad.begDate" displayFormat="yyyy/MM/dd" language="%{getLocale()}" /></td>
		<td class="freelabel">
			<@s.text name="ad.endDate" /> :</td>
		<td><@sx.datetimepicker name="ad.endDate" displayFormat="yyyy/MM/dd" language="%{getLocale()}" /></td>
	</tr>	
	<tr>
		<td class="freelabel"><span class="required">*</span>
			<@s.text name="ad.click" /> :</td>
		<td><@s.textfield id="ad.click" name="ad.click" size="10"
				maxlength="10" /></td>
		<td class="freelabel"><span class="required">*</span>
			<@s.text name="ad.display" /> :</td>
		<td><@s.textfield id="ad.display" name="ad.display" size="10"
				maxlength="10" /></td>
	</tr>	
	<tr>
		<td class="freebutton" colspan="4"><@s.token /> <@s.submit
				value="%{getText('global.submit')}" /> <@s.reset
				value="%{getText('global.reset')}" /></td>
	</tr>
</tbody>