<ul>
	<li><@s.url var="url" namespace="/back/service/profile" action="index" />
		<@s.a href="%{url}">
			<@s.text name="global.home.profile" />
		</@s.a></li>

	<li><@s.url var="url" namespace="/back/service/preference" action="index" />
		<@s.a href="%{url}">
			<@s.text name="global.home.preference" />
		</@s.a></li>

	<li><@s.url var="url" namespace="/back/service/ip/statistics" action="index" />
		<@s.a href="%{url}">
			<@s.text name="global.home.ip.statistics" />
		</@s.a></li>

	<li><@s.url var="url" namespace="/back/service/visitor/statistics" action="index" />
		<@s.a href="%{url}">
			<@s.text name="global.home.visitor.statistics" />
		</@s.a></li>
</ul>


