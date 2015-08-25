<%@ page import="bestbansbytier.RankTiers" contentType="text/html;charset=UTF-8" %>


<html>
<head>
    <meta name='layout' content="main"/>
    <title>Best Bans By Tier</title>
</head>

<body>
<g:if test="${banList.size() < 126}" >
    <div class="alert alert-info" role="info">
        <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
        The champion data has not finished populating.  Currently data for ${banList.size()} of 126 champions has been loaded
    </div>
</g:if>

<div class="row col-md-12">
    <div class="col-md-1"> </div>
    <g:each in="${RankTiers*.description}" var="tier">
        <div class="col-md-2">
            <g:link controller="bestBans" action="${tier}">
                <div class="row" style="text-align: center">
                    <g:img  file="TierThumbs/${tier}Thumb.png"/>
                </div>
                <div class="row" style="text-align: center"><b>${tier.toUpperCase()}</b></div>
            </g:link>
        </div>
    </g:each>
    <div class="col-md-1"> </div>

</div>

<div class="row col-md-12">
    <g:render template="info" model="[isHome: true]"/>
    <p>Thanks for reading! I hope this may serve of use. I intend to make one of these posts for every patch, so look forward to it!</p>
</div>
</body>
</html>