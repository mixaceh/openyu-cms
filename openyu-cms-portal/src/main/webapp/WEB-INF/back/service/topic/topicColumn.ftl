<tbody>
	<tr>
		<td width="20%" class="freelabel"><span class="required">*</span>
			<@s.text name="topic.names" /> :</td>
		<td width="30%"><@s.textfield id="topicName" name="topicName"
				value="%{topicName}" size="30" maxlength="50" /></td>
		<td width="20%" class="freelabel">
			<@s.text name="topic.catalog" /> :</td>
		<td width="30%"></td>
		
	</tr>
	<tr>
		<td class="freelabel">
			<@s.text name="topic.shortNames" /> :</td>
		<td><@s.textfield id="shortName" name="shortName"
				value="%{shortName}" size="30" maxlength="50" /></td>
		<td class="freelabel">
			<@s.text name="topic.keyword" /> :</td>
		<td><@s.textfield id="topic.keyword" name="topic.keyword" size="30"
				maxlength="50" /></td>
	</tr>	
	<tr>
		<td class="freelabel">
			<@s.text name="topic.descriptions" /> :</td>
		<td colspan="3"><@s.textfield id="description" name="description"
				value="%{description}" size="30" maxlength="50" /></td>
	</tr>	
	<tr>
		<td class="freelabel">
			<@s.text name="topic.recommend" /> :</td>
		<td><@s.radio list="trueFalseOptions" listKey="id.value"
				listValue="%{getName(getLocale())}" id="topic.recommend"
				name="topic.recommend" /></td>
		<td class="freelabel"><@s.text name="topic.sort" /> :</td>
		<td><@s.textfield id="topic.sort" name="topic.sort" size="10"
				maxlength="10" /></td>
	</tr>
	<tr>
		<td class="freelabel">
			<@s.text name="topic.template" /> :</td>
		<td colspan="3"><@s.textfield id="topic.template" name="topic.template" size="30"
				maxlength="100" /></td>
	</tr>
	<tr>
		<td class="freelabel">
			<@s.text name="topic.titleImg" /> :</td>
		<td><@s.textfield id="topic.titleImg" name="topic.titleImg" size="30"
				maxlength="100" />
			<@s.text name="topic.imgWidth" /> :<@s.textfield
				id="topic.titleImgWidth" name="topic.titleImgWidth" size="4"
				maxlength="4" /> <@s.text name="topic.imgHeight" /> :<@s.textfield
				id="topic.titleImgHeight" name="topic.titleImgHeight" size="4"
				maxlength="4" /></td>			
		<td colspan="2"></td>
	</tr>
	<tr>
		<td class="freelabel">
			<@s.text name="topic.contextImg" /> :</td>
		<td><@s.textfield id="topic.contextImg" name="topic.contextImg" size="30"
				maxlength="100" />
			<@s.text name="topic.imgWidth" /> :<@s.textfield
				id="topic.contextImgWidth" name="topic.contextImgWidth" size="4"
				maxlength="4" /> <@s.text name="topic.imgHeight" /> :<@s.textfield
				id="topic.contextImgHeight" name="topic.contextImgHeight" size="4"
				maxlength="4" /></td>
		<td colspan="2"></td>
	</tr>
	<tr>
		<td class="freebutton" colspan="4"><@s.token /> 
			<@s.submit value="%{getText('global.submit')}" /> 
			<@s.reset value="%{getText('global.reset')}" /></td>
	</tr>
</tbody>