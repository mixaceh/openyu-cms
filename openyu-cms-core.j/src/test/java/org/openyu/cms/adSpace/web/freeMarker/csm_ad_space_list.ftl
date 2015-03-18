<h3>@csm_ad_space_list</h3>
<@csm_ad_space_list site_seq='1'>
	<#list tag_results as tag_result>
		${tag_result.seq}, ${tag_result.id}, ${tag_result.name}<br/>
	</#list>
</@csm_ad_space_list>

<h3>@csm_ad_space_list</h3>
<@csm_ad_space_list site_id='127.0.0.1'>
	<#list tag_results as tag_result>
		${tag_result.seq}, ${tag_result.id}, ${tag_result.name}<br/>
	</#list>
</@csm_ad_space_list>	