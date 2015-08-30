<%@ page import="java.math.RoundingMode" %>

<div style="text-align: center"><g:img  file="champIcons/ChampionSplashes/${champData.champion}splash.png"  width="${117}" height="${212}"/></div>
<div style="text-align: center"><b>#${champData.rank}(${champData.previousRank - champData.rank}): ${champData.displayName.toUpperCase()}</b></div>
<div style="text-align: center">Influence: ${champData.influence.setScale(0, RoundingMode.HALF_UP)} </div>
<div style="text-align: center">Win Rate: ${champData.winrate.setScale(2, RoundingMode.HALF_UP)}% </div>
<div style="text-align: center">Pick Rate: ${champData.adjustedPickRate.setScale(2, RoundingMode.HALF_UP)}% </div>