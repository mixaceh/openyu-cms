<h3>@csm_tag</h3>
<@csm_tag seq='1'>
	<#if tag_result??>
		${tag_result.seq}, ${tag_result.id}, ${tag_result.name}<br/>
	</#if>
</@csm_tag>

<h3>@csm_friend_type</h3>
<@csm_tag id='text'>
	<#if tag_result??>
		${tag_result.seq}, ${tag_result.id}, ${tag_result.name}<br/>
	</#if>
</@csm_tag>
