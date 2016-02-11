<tbody>
	<tr>
		<td width="20%" class="freelabel"><span class="required">*</span>
			<@s.text name="vote.names" /> :</td>
		<td width="30%"><@s.textfield id="voteName" name="voteName"
				value="%{voteName}" size="40" maxlength="100" /></td>
		<td width="20%" class="freelabel"><span class="required">*</span>
			<@s.text name="vote.valid" /> :</td>
		<td width="30%"><@s.radio list="trueFalseOptions" listKey="id.value"
				listValue="%{getName(getLocale())}" id="vote.valid"
				name="vote.valid" /></td>
	</tr>
	<tr>
		<td width="20%" class="freelabel">
			<@s.text name="vote.descriptions" /> :</td>
		<td width="80%" colspan="3">
			<@s.textarea id="voteDescription" name="voteDescription"
				value="%{voteDescription}" cols="90" rows="5" /></td>
	</tr>
	<tr>
		<td width="20%" class="freelabel">
			<@s.text name="vote.LimitDate" /> :</td>
		<td width="80%" colspan="3">
			<table>
				<tbody>
					<tr>
						<td><@s.text name="vote.startDate" /></td>
						<td>
							<@sx.datetimepicker name="voteStartDate" value="%{voteStartDate}" displayFormat="yyyy-MM-dd" type="date" language="%{getLocale()}" />
							<@sx.datetimepicker name="voteStartTime" value="%{voteStartTime}" displayFormat="HH:mm" type="time" language="%{getLocale()}" />
						</td>
						<td rowspan="2" valign="middle"><span class="pn-fhelp"><@s.text name="vote.explan" /></span></td>
					</tr>
					<tr>
						<td><@s.text name="vote.endDate" /></td>
						<td>
							<@sx.datetimepicker name="voteEndDate" value="%{voteEndDate}" displayFormat="yyyy-MM-dd" type="date" language="%{getLocale()}"/>
							<@sx.datetimepicker name="voteEndTime" value="%{voteEndTime}" displayFormat="HH:mm" type="time" language="%{getLocale()}" />
						</td>
					</tr>
					<tr>
					</tr>
				</tbody>
			</table></td>
	</tr>
	<tr>
		<td width="20%" class="freelabel">
			<@s.text name="vote.repeateHour" /> :</td>
		<td width="80%" colspan="3"><@s.textfield id="vote.repeateHour" name="vote.repeateHour"
				size="30" maxlength="255" />
				<@s.text name="vote.repeateHour.explan" /></td>
	</tr>
	<tr>
		<td width="20%" class="freelabel">
			<@s.text name="vote.restrictMember" /> :</td>
		<td width="80%" colspan="3">
			<@s.radio list="trueFalseOptions" listKey="id.value"
				listValue="%{getName(getLocale())}"
				id="vote.restrictMember" name="vote.restrictMember" />
			<@s.text name="vote.restrictMember.explan" /></td>
	</tr>
	<tr>
		<td width="20%" class="freelabel">
			<@s.text name="vote.restrictIp" /> :</td>
		<td width="30%">
			<@s.radio list="trueFalseOptions" listKey="id.value"
				listValue="%{getName(getLocale())}"
				id="vote.restrictIp" name="vote.restrictIp" /></td>
		<td width="20%" class="freelabel">
			<@s.text name="vote.restrictCookie" /> :</td>
		<td width="30%">
			<@s.radio list="trueFalseOptions" listKey="id.value"
				listValue="%{getName(getLocale())}"
				id="vote.restrictCookie" name="vote.restrictCookie" />
			<@s.text name="vote.restrictCookie.explan" /></td>
	</tr>
	<tr>
		<td width="20%" class="freelabel"><span class="required">*</span>
			<@s.text name="vote.multiSelect" /> :</td>
		<td width="30%">
			<@s.textfield id="vote.multiSelect" name="vote.multiSelect"
				size="10" maxlength="255" />
			</td>
		<td width="20%" class="freelabel">
			<@s.text name="vote.defaultz" /> :</td>
		<td width="30%"><@s.radio list="trueFalseOptions" listKey="id.value"
				listValue="%{getName(getLocale())}" id="vote.defaultz"
				name="vote.defaultz" /></td>
	</tr>
	<tr>
		<td colspan="4" class="pn-fbutton"  align="center">
			<div class="items">
				<@s.text name="vote.voteItems" />&nbsp; 
				<input type="button" value="<@s.text name="vote.voteItem.add" />" onclick="addLines();">
			</div>

<script type="text/javascript">
function addLines() {
	var tpl = $("#itemTpl").val();
	var index = 99;
	$("#itemsContainer").append(tpl);
}
</script>
<textarea id="itemTpl" style="display:none;">
	&lt;div class="items"&gt;
		&lt;input type="hidden" name="ids" id="ids" /&gt;<@s.text name="voteItem.names" />:&lt;input type="text" name="voteNames" size="10" maxlength="255" id="voteNames"/&gt;&nbsp
		<@s.text name="voteItem.voteCount" />:&lt;input type="text" name="voteCounts" maxlength="255" value="0" id="voteCounts"/&gt;&nbsp;
		<@s.text name="voteItem.sort" />:&lt;input type="text" name="sorts" size="5" maxlength="255" value="0" id="sorts"/&gt;&nbsp;
		&lt;input class="del-button" type="button" value="<@s.text name="global.delete" />" onclick="$(this).parent().remove();"/&gt;
	&lt;/div&gt;
</textarea>
			<div id="itemsContainer">
				<@s.iterator value="vote.voteItems.values()" var="voteItem" status="st">
				<div class="items">
					<@s.hidden id="ids" name="ids" value="%{id}" />
					<@s.text name="voteItem.names" />:<@s.textfield id="voteNames" name="voteNames" value="%{getName(getLocale())}" size="10" maxlength="255" />&nbsp;
					<@s.text name="voteItem.voteCount" />:<@s.textfield id="voteCounts" name="voteCounts" value="%{voteCount}" maxlength="255" />&nbsp;
					<@s.text name="voteItem.sort" />:<@s.textfield id="sorts" name="sorts" size="5" value="%{sort}" maxlength="255" />&nbsp; 
					<input class="del-button" type="button" value="<@s.text name="global.delete" />" onclick="$(this).parent().remove();">
				</div>
				</@s.iterator>
			</div>

		</td>
	</tr>
	<tr>
		<td class="freebutton" colspan="4"><@s.token /> <@s.submit
				value="%{getText('global.submit')}" /> <@s.reset
				value="%{getText('global.reset')}" /></td>
	</tr>
	
</tbody>