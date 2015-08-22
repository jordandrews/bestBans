<%@ page import="bestbansbytier.RankTiers" contentType="text/html;charset=UTF-8" %>


<html>
<head>
    <meta name='layout' content="main"/>
    <title>Best Bans By Tier</title>
</head>

<body>

<div class="row col-md-12">
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
</div>

<div class="row col-md-12">
    <g:render template="info" model="[isHome: true]"/>
    <p>Thanks for reading! I hope this may serve of use. I intend to make one of these posts for every patch, so look forward to it!</p>
</div>
</body>
</html>