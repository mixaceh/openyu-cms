<h3>@csm_guestbook_type</h3>
<@csm_guestbook_type seq='1'>
	<#if tag_result??>
		${tag_result.seq}, ${tag_result.id}, ${tag_result.name}<br/>
	</#if>
</@csm_guestbook_type>

<h3>@csm_guestbook_type</h3>
<@csm_guestbook_type id='text'>
	<#if tag_result??>
		${tag_result.seq}, ${tag_result.id}, ${tag_result.name}<br/>
	</#if>
</@csm_guestbook_type>
