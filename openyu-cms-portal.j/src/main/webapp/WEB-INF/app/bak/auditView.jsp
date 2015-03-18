<%@ include file="/WEB-INF/app/content.jspf"%>
<span class="squares"> <span>&#8250;&#8250;</span>
</span>
<span class="auditlabeltitle"> <s:text
		name="audit.title" /></span>
<table class="audit" width="30%" border="0">
	<tbody>
		<tr>
			<td class="auditlabel"><s:text name="audit.version" /> :</td>
			<td colspan="3">${param.version}</td>
		</tr>
		<tr>
			<td width="20%" class="auditlabel"></span> <s:text
					name="audit.createDate" /> :</td>
			<td width="30%">${param.createDate}</td>

			<td width="20%" class="auditlabel"><s:text
					name="audit.createUser" /> :</td>
			<td width="30%">${param.createUser}</td>
		</tr>
		<tr>
			<td class="auditlabel"></span> <s:text name="audit.modifiedDate" /> :</td>
			<td>${param.modifiedDate}</td>
			<td class="auditlabel"><s:text name="audit.modifiedUser" /> :</td>
			<td>${param.modifiedUser}</td>			
		</tr>
	</tbody>
</table>
