<%@ page import="java.math.RoundingMode" %>

<tr>
    <td>
        <div style="float: left">
            <g:img  file="champIcons/ChampionThumbs/${champData.champion}Thumb.png"/>
            ${champData.displayName}
        </div>
    </td>
    <td>${champData.influence.setScale(0, RoundingMode.HALF_UP)}   </td>
    <td>${champData.winrate.setScale(2, RoundingMode.HALF_UP)}% </td>
    <td>${champData.adjustedPickRate.setScale(2, RoundingMode.HALF_UP)}%</td>
    <td>${champData.banrate.setScale(2, RoundingMode.HALF_UP)}% </td>
    <td>${champData.pickrate.setScale(2, RoundingMode.HALF_UP)}%</td>
</tr>