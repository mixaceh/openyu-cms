<h3>@csm_friend_list</h3>
<@csm_friend_list site_seq='1'>
	<#list tag_results as tag_result>
		${tag_result.seq}, ${tag_result.id}, ${tag_result.name}<br/>
	</#list>
</@csm_friend_list>

<h3>@csm_friend_list</h3>
<@csm_friend_list site_id='127.0.0.1'>
	<#list tag_results as tag_result>
		${tag_result.seq}, ${tag_result.id}, ${tag_result.name}<br/>
	</#list>
</@csm_friend_list>	

<h3>@csm_friend_list</h3>
<@csm_friend_list site_seq='1' friend_type_seq='1'>
	<#list tag_results as tag_result>
		${tag_result.seq}, ${tag_result.id}, ${tag_result.name}<br/>
	</#list>
</@csm_friend_list>

<h3>@csm_friend_list</h3>
<@csm_friend_list site_id='127.0.0.1' friend_type_id='a01'>
	<#list tag_results as tag_result>
		${tag_result.seq}, ${tag_result.id}, ${tag_result.name}<br/>
	</#list>
</@csm_friend_list>	