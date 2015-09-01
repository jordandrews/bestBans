<%@ page import="java.math.RoundingMode" %>

<tr>
    <td>
        <div style="float: left">
            <g:img  file="champIcons/ChampionThumbs/${champData.champion}Thumb.png" title="${champData.displayName}" alt="${champData.champion}"/>
            ${champData.displayName}
        </div>
    </td>
    <td>${champData.influence.setScale(0, RoundingMode.HALF_UP)}   </td>
    <td>${champData.aggregatedWinrate.setScale(2, RoundingMode.HALF_UP)}% </td>
    <td>${champData.adjustedAggregatedPickRate.setScale(2, RoundingMode.HALF_UP)}%</td>
    <td>${champData.aggregatedBanrate.setScale(2, RoundingMode.HALF_UP)}% </td>
    <td>${champData.aggregatedPickrate.setScale(2, RoundingMode.HALF_UP)}%</td>
</tr>