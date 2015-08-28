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
		<li>
			We have officially completed the move to <a href="http://www.bestbans.com">www.bestbans.com</a> which will be our permanent home.  Due to better hardware the site should now be very stable and you no longer have to be frustrated with constant crashes.

		</li>
		<li>Contact /u/jonnyy9 if you have any questions.</li>
	</ul>
</g:else>
</body>
</html>
