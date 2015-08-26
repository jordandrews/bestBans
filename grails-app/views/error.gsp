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
				<li>An error has occurred, as we are still in the early phases of develop please be patient while we figure these things out.</li>
				<li>Please check back later or contact /u/jonnyy9 if you have any questions.</li>
			</ul>
		</g:else>
	</body>
</html>
