<!DOCTYPE html>
<html>
	<head>
		<title><g:if env="development">Grails Runtime Exception</g:if><g:else>Error</g:else></title>
		<meta name="layout" content="main">
		<g:if env="development"><asset:stylesheet src="errors.css"/></g:if>
	</head>
	<body>
		<g:if env="development">
			<g:renderException exception="${exception}" />
		</g:if>
		<g:else>
			<ul class="errors">
				<li>We are currently in the process of moving to a more permanent home in the app.  Please check back at www.bestbans.com within the next day or so</li>
				<li>Contact /u/jonnyy9 if you have any questions.</li>
			</ul>
		</g:else>
	</body>
</html>
