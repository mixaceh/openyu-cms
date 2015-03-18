<ul>
	<li><@s.url var="url" namespace="/back/service/config"
			action="index" /> <@s.a href="%{url}">
			<@s.text name="global.config.config" />
		</@s.a></li>
	<li><@s.url var="url" namespace="/back/service/site" action="editSelf" />
		<@s.a href="%{url}">
			<@s.text name="global.config.site.self" />
		</@s.a></li>
	<li><@s.url var="url" namespace="/back/service/site" action="index" />
		<@s.a href="%{url}">
			<@s.text name="global.config.site" />
		</@s.a></li>
	<li><@s.url var="url" namespace="/back/service/ftp" action="index" />
		<@s.a href="%{url}">
			<@s.text name="global.config.ftp" />
		</@s.a></li>
	<li><@s.url var="url" namespace="/back/service/contextType"
			action="index" /> <@s.a href="%{url}">
			<@s.text name="global.config.contextType" />
		</@s.a></li>

</ul>
