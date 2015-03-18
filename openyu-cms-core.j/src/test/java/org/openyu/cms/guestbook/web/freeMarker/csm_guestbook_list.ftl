<h3>@csm_guestbook_list</h3>
<@csm_guestbook_list site_seq='1'>
	<#list tag_results as tag_result>
		${tag_result.seq}, ${tag_result.id}, ${tag_result.title}<br/>
	</#list>
</@csm_guestbook_list>

<h3>@csm_guestbook_list</h3>
<@csm_guestbook_list site_id='127.0.0.1'>
	<#list tag_results as tag_result>
		${tag_result.seq}, ${tag_result.id}, ${tag_result.title}<br/>
	</#list>
</@csm_guestbook_list>	

<h3>@csm_guestbook_list</h3>
<@csm_guestbook_list site_seq='1' guestbook_type_seq='1'>
	<#list tag_results as tag_result>
		${tag_result.seq}, ${tag_result.id}, ${tag_result.title}<br/>
	</#list>
</@csm_guestbook_list>

<h3>@csm_guestbook_list</h3>
<@csm_guestbook_list site_id='127.0.0.1' guestbook_type_id='a01'>
	<#list tag_results as tag_result>
		${tag_result.seq}, ${tag_result.id}, ${tag_result.title}<br/>
	</#list>
</@csm_guestbook_list>	