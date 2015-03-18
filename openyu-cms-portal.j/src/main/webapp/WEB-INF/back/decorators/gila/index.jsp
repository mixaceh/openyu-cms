<%@ include file="/WEB-INF/app/doc.jspf"%>
<%@ include file="/WEB-INF/app/taglib.jspf"%>

<html>
<head>
<%@ include file="/WEB-INF/app/meta.jspf"%>
<s:head />
<sx:head />
<title><decorator:title default="global.app" /></title>

<!-- Navigational metadata for large websites (an accessibility feature): -->
<link rel="top" href="./index.html" title="Homepage" />
<link rel="up" href="./index.html" title="Up" />
<link rel="first" href="./index.html" title="First page" />
<link rel="previous" href="./index.html" title="Previous page" />
<link rel="next" href="./index.html" title="Next page" />
<link rel="last" href="./index.html" title="Last page" />
<link rel="toc" href="./index.html" title="Table of contents" />
<link rel="index" href="./index.html" title="Site map" />

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/back/theme/gila/gila-screen.css"
	media="screen" title="Gila (screen)" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/back/theme/gila/gila-print.css"
	media="print" />

<decorator:head />
</head>

<body>
	<!-- For non-visual user agents: -->
	<div id="top">
		<a href="#main-copy" class="doNotDisplay doNotPrint">Skip to main
			content.</a>
	</div>

	<!-- ##### Header ##### -->

	<div id="header">
		<h1 class="headerTitle">
			<a href="./index" title="Browse to homepage">Gila <span>Two</span></a>
		</h1>

		<div class="subHeader">
			<span class="doNotDisplay">Navigation: </span> <a href="./index.html">Products</a>
			| <a href="./index.html">Solutions</a> | <a href="./index.html">Store</a>
			| <a href="./index.html">Support</a> | <a href="./index.html">Contact
				Us</a> | <a href="./index.html">About Us</a>
		</div>
	</div>

	<div id="side-bar">

		<!-- ##### Left Sidebar ##### -->

		<div class="leftSideBar">
			<p class="sideBarTitle">This Page</p>
			<ul>
				<li><a href="#introduction">Introduction</a></li>
				<li><a href="#cross-browser"
					title="Improved cross-browser compatibility">Cross-browser</a></li>
				<li><a href="#stylesheets" title="Modified stylesheets">Stylesheets</a></li>
				<li><a href="#accessibility" title="Improved accessibility">Accessibility</a></li>
			</ul>

			<p class="sideBarTitle">Branch Navigation</p>
			<ul>
				<li><a href="http://www.oswd.org/index.phtml">OSWD Home</a></li>
				<li><a href="http://www.oswd.org/browse.php">Browse Designs</a></li>
				<li><a href="http://www.oswd.org/userinfo.phtml?user=haran">haran&rsquo;s
						Designs</a></li>
				<li><span class="thisPage" title="That's this page!">Gila
						Homepage</span></li>
			</ul>

			<p class="sideBarTitle">Comments</p>
			<span class="sideBarText"> Comments and constructive
				criticisms are welcome <a
				href="http://www.oswd.org/email.phtml?user=haran" title="Email form">via
					email</a>.
			</span>
		</div>

		<!-- ##### Right Sidebar ##### -->

		<div class="rightSideBar">
			<p class="sideBarTitle">News</p>

			<div class="sideBarText">
				<strong>? May 03</strong><br /> Submitted revised version of Gila
				to <a href="http://www.oswd.org">OSWD</a>
			</div>

			<div class="sideBarText">
				<strong>3 Feb 03</strong><br /> Original version of Gila submitted
			</div>

			<a href="./index.html" class="more">more news &raquo;</a>

			<p class="sideBarTitle">Downloads</p>

			<div class="sideBarText">
				<strong>Product Delta</strong><br /> <a href="./index.html">info</a>&nbsp;|&nbsp;<a
					href="./index.html">download</a>
			</div>
			<div class="sideBarText">
				<strong>Product Echo</strong><br /> <a href="./index.html">info</a>&nbsp;|&nbsp;<a
					href="./index.html">download</a>
			</div>
			<div class="sideBarText">
				<strong>Product Foxtrot</strong><br /> <a href="./index.html">info</a>&nbsp;|&nbsp;<a
					href="./index.html">download</a>
			</div>

			<p class="sideBarTitle">Validation</p>

			<div class="sideBarText">
				Validate the <a href="http://validator.w3.org/check/referer">XHTML</a>
				and <a href="http://jigsaw.w3.org/css-validator/check/referer">CSS</a>
				of this page.
			</div>
		</div>

	</div>

	<!-- ##### Main Copy ##### -->

	<div id="main-copy">
		<h1 id="introduction" style="border-top: none; padding-top: 0;">Introduction</h1>
		<p>
			This is version two of Gila, a revision of the <a
				href="http://www.oswd.org/userinfo.phtml?user=haran">original
				design</a> I submitted to <a href="http://www.oswd.org"><acronym
				title="Open Source Web Design">OSWD</acronym></a> in February 2003.
			Following is a summary of the major differences between this version
			and the original.
		</p>
		
		<decorator:body />

	
	</div>

	<!-- ##### Footer ##### -->

	<div id="footer">
		<div class="doNotPrint">
			<a href="./index.html">Tell a Friend</a> | <a href="./index.html">Privacy
				Policy</a> | <a href="./index.html">Site Map</a> | <a
				href="./index.html">Feedback</a> | <a href="./index.html">Help</a>
		</div>

		<div>
			Copyright &copy; 2003, Your Company | Modified on 2003-May-03 by <a
				href="http://www.oswd.org/email.phtml?user=haran"
				title="Email the author">haran</a>
		</div>
	</div>
</body>
</html>