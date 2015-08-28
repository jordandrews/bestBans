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
        Champion data has not finished populating, and therefore may not be accurate.  Currently data for ${dataCount ?: 0} of 126 champions has been loaded
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
                            <div style="text-align: center"><g:img  file="champIcons/ChampionSplashes/${champData.champion}splash.png"/></div>
                            <div style="text-align: center"><b>#${i+1}: ${champData.displayName.toUpperCase()}</b></div>
                            <div style="text-align: center">Influence: ${champData.influence.setScale(2, RoundingMode.HALF_UP)} </div>
                            <div style="text-align: center">Win Rate: ${champData.winrate.setScale(2, RoundingMode.HALF_UP)}% </div>
                            <div style="text-align: center">Pick Rate: ${champData.adjustedPickRate.setScale(2, RoundingMode.HALF_UP)}% </div>
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