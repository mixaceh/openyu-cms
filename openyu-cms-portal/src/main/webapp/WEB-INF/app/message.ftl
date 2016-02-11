<@s.if test="hasActionErrors()">
	<@s.actionerror />
</@s.if>
<@s.if test="hasActionMessages()">
	<@s.actionmessage />
</@s.if>
<@s.if test="hasFieldErrors()">
	<ul class="errorMessage">
		<@s.iterator value="fieldErrors">
			<li><@s.property value="value[0]" /></li>
		</@s.iterator>
	</ul>
</@s.if>