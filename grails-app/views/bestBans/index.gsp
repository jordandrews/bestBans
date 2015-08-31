<%@ page import="java.math.RoundingMode; bestbansbytier.RankTiers" contentType="text/html;charset=UTF-8" %>


<html>
<head>
    <meta name='layout' content="main"/>
    <title>Best Bans By Tier</title>
</head>

<body>
<g:if test="${dataCount < 126 }" >
    <div class="alert alert-info" role="info">
        <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
        Champion data has not finished populating, and therefore may not be accurate. Choose desired tier to see progress for your tier
    </div>
</g:if>


<div class="row">

    <div class="row col-md-9">
        <g:each in="${RankTiers*.description}" var="tier">
            <div class="row">
                <g:link controller="bestBans" action="${tier}">
                    <div class="col-md-3" style="text-align: center" >
                        <g:img  file="TierThumbs/${tier}Thumb.png"  width="150" height="150"/>
                    </div>
                    <div class="col-md-6" style="top: 60px; bottom: 60px; vertical-align: bottom">
                        <div class="row"><h3><b>TOP ${tier.toUpperCase()} BANS</b></h3>  </div>
                        <div class="row">See more</div>

                    </div>
                    <div class="col-md-3" style="text-align: center">
                        <g:img  file="TierThumbs/${tier}Thumb.png" width="150" height="150"/>
                    </div>
                </g:link>
            </div>
            <div class="row">
                <g:if test="${banMap && banMap[tier] && banMap[tier].size() > 3}">
                    <g:each in="${banMap[tier]}" var="champData" status="i">
                        <div class="col-md-3">
                            <g:render template="topChamp" model="[champData: champData, tempRank: i+1]"/>
                        </div>
                    </g:each>
                </g:if>
            </div>
            <hr/>
        </g:each>

    </div>

    <div class="col-md-3 pull-right"><g:render template="info" model="[isHome: false]"/> </div>
</div>
</body>
</html>