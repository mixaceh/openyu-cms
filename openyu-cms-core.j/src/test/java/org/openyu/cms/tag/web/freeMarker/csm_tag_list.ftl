<h3>@csm_tag_list</h3>
<@csm_tag_list site_seq='1'>
	<#list tag_results as tag_result>
		${tag_result.seq}, ${tag_result.id}, ${tag_result.name}<br/>
	</#list>
</@csm_tag_list>

