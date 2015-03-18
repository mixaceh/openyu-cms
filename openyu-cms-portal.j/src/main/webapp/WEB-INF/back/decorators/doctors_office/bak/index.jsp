<%@ include file="/WEB-INF/app/doc.jspf"%>
<%@ include file="/WEB-INF/app/taglib.jspf"%>

<html>
<head>
<%@ include file="/WEB-INF/app/meta.jspf"%>
<s:head />
<sx:head />
<title><decorator:title default="global.app" /></title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/back/theme/doctors_office/doctors_office.css"
	media="screen" />
<script language="JavaScript"
	src="${pageContext.request.contextPath}/app/generic.js"
	type="text/javascript"></script>
<script language="JavaScript"
	src="${pageContext.request.contextPath}/jquery/jquery-1.10.2.js"
	type="text/javascript"></script>	
</head>
<body>
	<div id="banner">
		<div class="top_links clearfix" id="topnav">
			<%@ include file="/WEB-INF/back/service/home/topNav.jspf"%>
		</div>
		<img alt="pumpkin"
			src="${pageContext.request.contextPath}/back/theme/doctors_office/img/header_logo.jpg" />
		<div class="page_title">
			<span id="page_title"> <%-- userSeession.site --%> <s:if
					test="%{userSession.site!=null}">
					<s:property value="%{userSession.site.getName(getLocale())}" />
				</s:if>&nbsp;
			</span> <br />&nbsp;<br />
			<div id="nav">
				<%@ include file="/WEB-INF/back/service/home/nav.jspf"%>
			</div>
		</div>
	</div>

	<decorator:body />

	<div id="rightcontent">
		<div>
			<%@ include file="/WEB-INF/back/service/home/rightNav.jspf"%>
		</div>
	</div>

</body>
</html>
