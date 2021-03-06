<%--
  Created by IntelliJ IDEA.
  User: jonathanlutz
  Date: 8/17/15
  Time: 7:57 PM
--%>

<%@ page import="bestbansbytier.ServerRegions; java.math.RoundingMode" contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <meta name='layout' content="main"/>
    <title>Best ${tier} Bans League of Legends </title>
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
        <h1>
            TOP ${tier.toUpperCase()} BANS
            <g:if test="${!(session.region in ServerRegions.lolKingSupportedRegions)}" >
                <a class='my-tool-tip' data-toggle="tooltip" style="color: #ffffff;" data-placement="right"
                       title="Due to data limitations in this region, the pickrate of a champion is the same in each tier.">
                <sup><i class='glyphicon glyphicon-exclamation-sign' style="color: #FFFF99;"></i></sup>
                </a>
            </g:if>
        </h1>
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
                    <g:render template="topChamp" model="[champData: champData, tempRank: i+1]"/>
                </div>
                <g:if test="${i%4==3}">
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
                <g:render template="champDataRow" model="[champData: champData]"/>
            </g:each>
            </tbody>
        </table>
    </div>
    <div class="col-md-3 pull-right"><g:render template="info" model="[isHome: false]"/> </div>
</div>
</body>
</html>



