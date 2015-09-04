<%@ page import="bestbansbytier.ServerRegions; java.math.RoundingMode; bestbansbytier.RankTiers" contentType="text/html;charset=UTF-8" %>


<html>
<head>
    <meta name='layout' content="main"/>
    <title> Best Bans League Of Legends</title>
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
                        <g:img  file="TierThumbs/${tier}Thumb.png" title="${tier}" alt="${tier}" width="150" height="150"/>
                    </div>
                    <div class="col-md-6" style="top: 60px; bottom: 60px; vertical-align: bottom">
                        <div>
                            <div class="row">
                                <h1>
                                    <b>TOP ${tier.toUpperCase()} BANS</b>
                                    <g:if test="${!(session.region in ServerRegions.lolKingSupportedRegions)}" >
                                        <p class='my-tool-tip' data-toggle="tooltip" style="color: #ffffff; display: inline;" data-placement="top"
                                           title="Due to data limitations in this region, the pickrate of a champion is the same in each tier.">
                                            <sup><i class='glyphicon glyphicon-exclamation-sign' style="color: #FFFF99;"></i></sup>
                                        </p>
                                    </g:if>
                                </h1>

                            </div>
                            <div class="row">See more</div>
                        </div>

                    </div>


                    <div class="col-md-3 tierIcon2" style="text-align: center">
                        <g:img  file="TierThumbs/${tier}Thumb.png" title="${tier}" alt="${tier}" width="150" height="150"/>
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

<asset:script>
    $(document).ready(function(){
        $('.my-tool-tip').tooltip()
    });

    $(window).resize(function() {
        if ($(this).width() < 993) {
           $('.tierIcon2').hide();

        } else {
            $('.tierIcon2').show();
        }

    });
</asset:script>