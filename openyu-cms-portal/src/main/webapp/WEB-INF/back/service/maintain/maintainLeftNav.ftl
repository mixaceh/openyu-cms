<ul>
	<li><@s.url var="url" namespace="/back/service/topic" action="index" /> 
		<@s.a href="%{url}">
			<@s.text name="global.maintain.topic" />
		</@s.a>
	</li>

	<li><@s.url var="url" namespace="/back/service/tag" action="index" /> 
		<@s.a href="%{url}">
			<@s.text name="global.maintain.tag" />
		</@s.a>
	</li>

	<li><@s.url var="url" namespace="/back/service/keyword" action="index" /> 
		<@s.a href="%{url}">
			<@s.text name="global.maintain.keyword" />
		</@s.a>
	</li>

	<li><@s.url var="url" namespace="/back/service/sensitivity" action="index" /> 
		<@s.a href="%{url}">
			<@s.text name="global.maintain.sensitivity" />
		</@s.a>
	</li>

	<li><@s.url var="url" namespace="/back/service/contextRecover" action="index" /> 
		<@s.a href="%{url}">
			<@s.text name="global.maintain.contextRecover" />
		</@s.a>
	</li>

	<li><@s.url var="url" namespace="/back/service/backup" action="index" /> 
		<@s.a href="%{url}"> 
			<@s.text name="global.maintain.backup" />
		</@s.a>
	</li>

	<li><@s.url var="url" namespace="/back/service/restore" action="index" /> 
		<@s.a href="%{url}"> 
			<@s.text name="global.maintain.restore" />
		</@s.a>
	</li>

	<li><@s.url var="url" namespace="/back/service/backupDirectory"	action="index" /> 
		<@s.a href="%{url}"> 
			<@s.text name="global.maintain.backupDirectory" />
		</@s.a>
	</li>

	<li><@s.url var="url" namespace="/back/service/attachment" action="index" /> 
		<@s.a href="%{url}"> 
			<@s.text name="global.maintain.attachment" />
		</@s.a>
	</li>
</ul>
