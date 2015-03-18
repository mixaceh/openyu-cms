<h3>@csm_guestbook</h3>
<@csm_guestbook seq='1'>
	<#if tag_result??>
		${tag_result.seq}, ${tag_result.id}, ${tag_result.title}<br/>
	</#if>
</@csm_guestbook>

<h3>@csm_guestbook</h3>
<@csm_guestbook id='law'>
	<#if tag_result??>
		${tag_result.seq}, ${tag_result.id}, ${tag_result.title}<br/>
	</#if>
</@csm_guestbook>
