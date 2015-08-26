<%--
  Created by IntelliJ IDEA.
  User: jonathanlutz
  Date: 8/17/15
  Time: 7:57 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name='layout' content="main"/>
    <title>${tier} bans</title>
</head>

<body>
<g:if test="${banList.size() < 126}" >
    <div class="alert alert-info" role="info">
        <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
        Champion data has not finished populating, and therefore may not be accurate.  Currently data for ${banList.size()} of 126 champions has been loaded
    </div>
</g:if>
<div class="row">
    <div class="col-md-9">
        <h3>TOP ${tier.toUpperCase()} BANS</h3>
    </div>
</div>

<div class="row">
    <div class="col-md-9">
        <g:if test="${banList.size() >= 12}">
            <g:each in="${banList[0..11]}" var="champData" status="i">
                <g:if test="${i%4==0}"  >
                    <div class="row">
                </g:if>
                <div class="col-md-3">
                    <div style="text-align: center"><g:img  file="champIcons/ChampionSplashes/${champData.champion}splash.png"  width="${117}" height="${212}"/></div>
                    <div style="text-align: center"><b>#${i+1}: ${champData.displayName.toUpperCase()}</b></div>
                    <div style="text-align: center">Influence: ${champData.power.round(2)} </div>
                    <div style="text-align: center">Win Rate: ${champData.winrate.round(2)}% </div>
                    <div style="text-align: center">Pick Rate: ${champData.adjustedPickRate.round(2)}% </div>

                </div>
                <g:if test="${i%4==3}">
                    <g:if test="${i<4}">
                    </g:if>

                    </div>
                    <br/>
                </g:if>
            </g:each>
            <hr/>
        </g:if>
        <table class="table-bordered table-striped">
            <thead>
            <tr>
                <th> Champion </th>
                <th>
                    Influence
                    <a class='my-tool-tip' data-toggle="tooltip" style="color: #ffffff" data-placement="top"
                       title="Influence is the win consistency of a champion in any random match. It is calculated using only winrate and adjusted pickrate:        (Winrate - 0.5) * (Adj. Pickrate) ">
                        <i class='glyphicon glyphicon-question-sign'></i>
                    </a>
                </th>
                <th>Win Rate</th>
                <th>Adj. Pick<br/> Rate
                    <a class='my-tool-tip' data-toggle="tooltip" style="color: #ffffff" data-placement="top" title="(100 * pickrate) / (100-banrate)">
                        <i class='glyphicon glyphicon-question-sign'></i>
                    </a>
                </th>
                <th>Ban Rate</th>
                <th>Pick Rate</th>
            </tr>
            </thead>
            <tbody>
            <g:each in="${banList}" var="champData">
                <tr>
                    <td>
                        <div style="float: left">
                            <g:img  file="champIcons/ChampionThumbs/${champData.champion}Thumb.png"/>
                            ${champData.displayName}
                        </div>
                    </td>
                    <td>${champData.power.round(2)}   </td>
                    <td>${champData.winrate.round(2)}% </td>
                    <td>${champData.adjustedPickRate.round(2)}%</td>
                    <td>${champData.banrate.round(2)}% </td>
                    <td>${champData.pickrate.round(2)}%</td>
                </tr>
            </g:each>
            </tbody>
        </table>
    </div>
    <div class="col-md-3 pull-right"><g:render template="info" model="[isHome: false]"/> </div>
</div>
</body>
</html>

<asset:script>
    $(document).ready(function(){
        $('.my-tool-tip').tooltip()
    });
</asset:script>

