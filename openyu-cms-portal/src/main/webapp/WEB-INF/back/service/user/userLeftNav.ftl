<ul>
	<li><@s.url var="url" namespace="/back/service/front/user"
			action="index" /> <@s.a href="%{url}">
			<@s.text name="global.user.front.user" />
		</@s.a></li>

	<li><@s.url var="url" namespace="/back/service/group"
			action="index" /> <@s.a href="%{url}">
			<@s.text name="global.user.group" />
		</@s.a></li>

	<li><@s.url var="url" namespace="/back/service/user" action="indexSelf" />
		<@s.a href="%{url}">
			<@s.text name="global.user.user.self" />
		</@s.a></li>

	<li><@s.url var="url" namespace="/back/service/user" action="index" />
		<@s.a href="%{url}">
			<@s.text name="global.user.user" />
		</@s.a></li>

	<li><@s.url var="url" namespace="/back/service/role" action="index" />
		<@s.a href="%{url}">
			<@s.text name="global.user.role" />
		</@s.a></li>

	<li><@s.url var="url" namespace="/back/service/mail" action="index" />
		<@s.a href="%{url}">
			<@s.text name="global.user.mail" />
		</@s.a></li>
</ul>
