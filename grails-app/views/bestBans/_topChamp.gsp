<%@ page import="java.math.RoundingMode" %>

<g:set var="difference" value="${(champData.previousRank && champData.rank) ? champData.previousRank - champData.rank : 0}" />
<div style="text-align: center"><g:img  file="champIcons/ChampionSplashes/${champData.champion.toLowerCase()}splash.png" title="${champData.displayName}" alt="${champData.champion}"  width="${Math.ceil(117*(1-(0.1*Math.floor((tempRank-1)/4))))}" height="${Math.ceil(212*(1-(0.12*Math.floor((tempRank-1)/4))))}"/></div>
<div class="row">
    <div style="text-align: center"><b>#${champData.rank ?: tempRank}</b>
        <b>: ${champData.displayName.toUpperCase()}</b>
        <g:if test="${difference}">
            (<i class='glyphicon glyphicon-arrow-${difference > 0 ? 'up' : 'down'}' style="color: ${difference > 0 ? 'green' : 'red'}">${difference.abs()}</i>)
        </g:if>
    </div>
</div>
<div style="text-align: center">Influence: ${champData.influence.setScale(0, RoundingMode.HALF_UP)} </div>
<div style="text-align: center">Win Rate: ${champData.aggregatedWinrate.setScale(2, RoundingMode.HALF_UP)}% </div>
<div style="text-align: center">Pick Rate: ${champData.adjustedAggregatedPickRate.setScale(2, RoundingMode.HALF_UP)}% </div>