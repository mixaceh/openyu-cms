<ul>
	<li><@s.url var="url" namespace="/back/service/home/static"
			action="index" /> <@s.a href="%{url}">
			<@s.text name="global.generate.home.static" />
		</@s.a></li>

	<li><@s.url var="url" namespace="/back/service/catalog/static" action="index" />
		<@s.a href="%{url}">
			<@s.text name="global.generate.catalog.static" />
		</@s.a></li>

	<li><@s.url var="url" namespace="/back/service/context/static" action="index" />
		<@s.a href="%{url}">
			<@s.text name="global.generate.context.static" />
		</@s.a></li>

	<li><@s.url var="url" namespace="/back/service/text/search" action="index" />
		<@s.a href="%{url}">
			<@s.text name="global.generate.full.text.search" />
		</@s.a></li>
</ul>
