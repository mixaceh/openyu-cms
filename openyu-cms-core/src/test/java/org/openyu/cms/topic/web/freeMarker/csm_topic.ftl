<h3>@csm_topic</h3>
<@csm_topic seq='1'>
	<#if tag_result??>
		${tag_result.seq}, ${tag_result.id}, ${tag_result.name}<br/>
	</#if>
</@csm_topic>

<h3>@csm_topic</h3>
<@csm_topic id='text'>
	<#if tag_result??>
		${tag_result.seq}, ${tag_result.id}, ${tag_result.name}<br/>
	</#if>
</@csm_topic>
