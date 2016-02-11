<span class="squares"> <span>&#8250;&#8250;</span>
</span>
<span class="loglabeltitle"> <@s.text name="audit.title" /></span>
<table class="log" width="45%" border="0">
	<tbody>
		<tr>
			<td class="loglabel"><@s.text name="audit.version" /> :</td>
			<td colspan="3"><@s.property value="#version" /></td>
		</tr>
		<tr>
			<td width="12%" class="loglabel"><@s.text name="audit.createDate" /> :</td>
			<td width="38%"><#if audit?? && audit.createDate??><@s.property value="%{toString(#audit.createDate)}" /></#if></td>
			<td width="12%" class="loglabel"><@s.text name="audit.createUser" /> :</td>
			<td width="38%"><#if audit?? && audit.createUser??><@s.property value="#audit.createUser" /></#if></td>
		</tr>
		<tr>
			<td class="loglabel"><@s.text name="audit.modifiedDate" /> :</td>
			<td><#if audit?? && audit.modifiedDate??><@s.property value="%{toString(#audit.modifiedDate)}" /></#if></td>
			<td class="loglabel"><@s.text name="audit.modifiedUser" /> :</td>
			<td><#if audit?? && audit.modifiedUser??><@s.property value="#audit.modifiedUser" /></#if></td>			
		</tr>
	</tbody>
</table>
