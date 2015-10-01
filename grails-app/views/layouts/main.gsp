<%@ page import="bestbansbytier.ServerRegions; grails.util.Environment" %>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="">
	<meta name="author" content="Jonathan Lutz">
	<title><g:layoutTitle default="Grails"/></title>

	<!-- Bootstrap Core CSS -->
	<asset:stylesheet href="bootstrap.css" rel="stylesheet"/>
	<link rel="shortcut icon" href="${assetPath(src: 'favicon.ico')}" type="image/x-icon">

	<asset:stylesheet src="application.css"/>
	<asset:stylesheet src="custom.css"/>
	<asset:javascript src="jquery/js/jquery-1.11.1.js"/>
	<asset:javascript src="bootstrap.js"/>

	<g:layoutHead/>

	<!-- Custom CSS -->
	<style>
	body {
		padding-top: 70px;
		/* Required padding for .navbar-fixed-top. Remove if using .navbar-static-top. Change if height of navigation changes. */
	}
	</style>
	<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

<!-- Navigation -->
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<g:link class="navbar-brand" controller="bestBans" action="index">
				<div class="row">
					Best Bans
					%{--<g:img style="border:2px solid black" file="favicon.ico" width="30" height="30"/>--}%
				</div>
			</g:link>


		</div>
		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><g:link controller="bestBans" action="bronze">Bronze</g:link></li>
				<li><g:link controller="bestBans" action="silver">Silver</g:link></li>
				<li><g:link controller="bestBans" action="gold">Gold</g:link></li>
				<li><g:link controller="bestBans" action="platinum">Platinum</g:link></li>
				<li><g:link controller="bestBans" action="diamond">Diamond</g:link></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li class="noHover" style=""><a href="#">Patch 5.19</a></li>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Current Region: ${session.region ? session.region.name() : 'NA'}<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<g:each in="${ServerRegions}" var="region">
							<li><g:link controller="bestBans" action="changeRegion" params="[region: region.name(), previousController: params.controller, previousAction: params.action]">${region.name()}</g:link></li>
						</g:each>
					</ul>
				</li>
			</ul>

			<g:if test="${Environment.current == Environment.DEVELOPMENT}">
				<div class="ribbon-wrapper"><div class="ribbon">DEV</div></div>
			</g:if>
		</div>
	</div>
</nav>

<!-- Page Content -->
<div class="container">
	<g:layoutBody/>
</div>
<!-- /.container -->

<div class="footer" role="contentinfo"></div>
<div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>
<asset:deferredScripts/>
</body>
<footer>
	<g:if test="${Environment.current == Environment.PRODUCTION}">
		<script>
			(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
				(i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
					m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
			})(window,document,'script','//www.google-analytics.com/analytics.js','ga');

			ga('create', 'UA-67000839-1', 'auto');
			ga('send', 'pageview');
		</script>
	</g:if>
	<br/>
</footer>
</html>
