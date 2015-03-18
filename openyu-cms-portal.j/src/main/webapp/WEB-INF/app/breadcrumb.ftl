<span class="squares">
	<span>&#8250;&#8250;</span>
</span>
<@s.text name="global.current.position" /> :
<#if parentUrl??>
	<@s.a href="%{#parentUrl}">
		<@s.property value="#parentTitle" />
	</@s.a>
<#else>
	<@s.property value="#parentTitle" />
</#if>
&nbsp;»
<#if currentUrl??>
	<@s.a href="%{#currentUrl}">
		<@s.property value="#currentTitle" />
	</@s.a>	
<#else>
	<@s.property value="#currentTitle" />
</#if>	
		
