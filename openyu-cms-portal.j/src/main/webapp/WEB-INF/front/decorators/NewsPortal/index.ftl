<#include "/WEB-INF/app/taglib.ftl">

<html>
<link
	href="${base}/back/theme/NewsPortal/css/style.css"
	rel="stylesheet" type="text/css">

<head>
<#include "/WEB-INF/app/meta.ftl">
<@s.head />
<@sx.head />
<title><@decorator.title default="global.app.front" /></title>

<script type="text/javascript"
	src="${base}/jquery/jquery-1.10.2.js">
	
</script>
<script type="text/javascript"
	src="${base}/app/generic.js">
	
</script>
<@decorator.head />
</head>

<body>

	<div id="page_wrapper">

		<div id="header_wrapper">

			<div id="header">

				<h1>
					<font color="#FFDF8C">&nbsp;</font>
				</h1>
				<h2>&nbsp;</h2>

			</div>

			<div id="navcontainer">

				<#include "/WEB-INF/front/service/home/nav.ftl">
			</div>

		</div>

		<div id="left_side">
			<#include "/WEB-INF/front/service/home/leftNav.ftl">
		</div>

		<div id="right_side">
			<#include "/WEB-INF/front/service/home/rightNav.ftl">
		</div>

		<div id="content">
			<@decorator.body />
		</div>

		<div id="footer">
			<#include "/WEB-INF/front/service/home/footer.ftl">
		</div>

	</div>

</body>

</html>