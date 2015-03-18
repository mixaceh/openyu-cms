<ul>
	<li><a href="<@s.property value="%{getFrontUrl()}" />"  target="_blank">
		<@s.text name="global.front.home" />
	</a></li>

	<li><a href="${base}/j_spring_security_logout">
			<@s.text name="global.logout" />
	</a></li>

	<li><@s.url var="locale_zh_TW" namespace="/back/service/home"
		action="locale">
			<@s.param name="request_locale">zh_TW</@s.param>
		</@s.url>
		<@s.a href="%{locale_zh_TW}">
			<@s.text name="locale.zh_TW" />
		</@s.a></li>

	<li><@s.url var="locale_zh_CN" namespace="/back/service/home"
		action="locale">
		<@s.param name="request_locale">zh_CN</@s.param>
		</@s.url>
		<@s.a href="%{locale_zh_CN}">
			<@s.text name="locale.zh_CN" />
		</@s.a></li>

	<li><@s.url var="locale_en_US" namespace="/back/service/home"
		action="locale">
		<@s.param name="request_locale">en_US</@s.param>
		</@s.url>
		<@s.a href="%{locale_en_US}">
			<@s.text name="locale.en_US" />
		</@s.a></li>
</ul>
