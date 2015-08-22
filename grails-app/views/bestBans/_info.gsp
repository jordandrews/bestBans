<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
    <div class="panel ${isHome ? 'panel-home' : 'panel-default'}">
        <div class="panel-heading" role="tab" id="headingOne">
            <h4 class="panel-title">
                <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="false" aria-controls="collapseOne">
                    Intro
                </a>
            </h4>
        </div>
        <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
            <div class="panel-body">
                <p>I've always found the way that people choose bans to be rather jarring. It's based heavily on frustration and apparent power, as opposed to consistency in success over several games.</p>

                <p>I've decided to make <strong>a ban list that is solely based on winrate, pickrate, and banrate (which is partially added to pickrate).</strong> </p>

                <p><strong>Nothing else.</strong></p>

                <p>Because the list is based on averages, <strong>the list is most useful when you know nearly nothing about either team</strong>.</p>

                <p>The list calculates which banned champions will net you more wins compared to simply banning an average champion.</p>

                <p>The data is on NA, Ranked Solo. Team Ranked and other countries may have different rates. Here it is!</p>

                <p><a href="http://i.imgur.com/LBiEmUd.png">Bronze</a></p>

                <p><a href="http://i.imgur.com/rJg5a0H.png">Silver</a></p>

                <p><a href="http://i.imgur.com/hhuK3NN.png">Gold</a></p>

                <p><a href="http://i.imgur.com/6X1r9kY.png">Platinum</a></p>

                <p><a href="http://i.imgur.com/O0VAXnD.png">Diamond</a></p>

                <p><a href="https://docs.google.com/spreadsheets/d/1QZ8Oo5eCULi_6LARS5-CDcsIDZWL7bYK-h5U0byOKew/edit?pli=1#gid=2068984042">Top 60 Best Bans Data</a></p>

                <p>Special thanks to <a href="/u/aMalfunction">/u/aMalfunction</a> for their excellent work in prettying up the data I've given them!</p>

                <p>As with any bans, <strong>you only get the true benefit by asking your team in advance if they intend to play any of the champions.</strong> Otherwise, you are denying the enemy team AND your team the chance of playing a consistent power.</p>
            </div>
        </div>
    </div>
    <div class="panel ${isHome ? 'panel-home' : 'panel-default'}">
        <div class="panel-heading" role="tab" id="headingTwo">
            <h4 class="panel-title">
                <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                    Observations
                </a>
            </h4>
        </div>
        <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
            <div class="panel-body">
                <p><strong>Elise</strong> absolutely dominates in pretty much every Elo. In Diamond their winrate has been fluctuating strangely which has caused more consistent picks to beat them out.</p>

                <p><strong>Nidalee</strong> has completely disappeared from the ban list, with a sub 50% winrate in all Elos now. Their pick rate has been slowly decreasing has people have realized they're no longer the monster of before.</p>

                <p><strong>Xin Zhao</strong> has also made a considerable rise in the lower Elos</p>
            </div>
        </div>
    </div>
    <div class="panel ${isHome ? 'panel-home' : 'panel-default'}">
        <div class="panel-heading" role="tab" id="headingThree">
            <h4 class="panel-title">
                <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                    Why use these suggestions?
                </a>
            </h4>
        </div>
        <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
            <div class="panel-body">
                <p>It bears repeating that the list isn't intended to replace specific banning, but is rather intended for use in an information vacuum. <strong>You should largely follow the list when you have little to no clue what compositions or champions either team is running</strong>, a fairly common occurrence.</p>

                <p><b>Legitimate reasons for circumventing these suggested bans</b>:</p>

                <p>-The enemy can pick a champion that counters a composition your team has planned</p>

                <p>-You know for sure yourself or your team is playing a champion that counters a suggested ban (Zed isn't so scary when you know Malzahar is on your team)</p>

                <p>-There's a high chance the enemy team has a champion specialist who will be much weaker if their primary champion is banned</p>


                <p><b>Reasons to circumvent bans that are not legitimate</b>:</p>

                <p>-A champion is "overpowered." <strong>Bans should not be based on a champion's potential strength, but rather on their win consistency.</strong> Even if Leblanc could theoretically win 100% of the time with perfect play, that situation is so rare that it doesn't change that Leblanc wins only 44% of the time on average. Winrate reflects consistency, not strength.</p>

                <p>-Because your teammates will rage. Let’s assume that your teammates get ticked off every time Leblanc is picked by the enemies. Even with this “buff” caused by annoyed teammates, Leblanc still only wins 44% of the time. Let your teammates be mad; avoiding the ban is still your statistically best chance of success.</p>

                <p>-A champion is annoying to fight. As annoying as certain champions are, if you're trying to maximize your winrate then it's still not a smart idea to ban them simply for being obnoxious. Most obnoxious champions have crippling weaknesses that cause their winrates and/or pickrates to be fairly low.</p>
            </div>
        </div>
    </div>
    <div class="panel ${isHome ? 'panel-home' : 'panel-default'}">
        <div class="panel-heading" role="tab" id="headingFour">
            <h4 class="panel-title">
                <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
                    Methodology
                </a>
            </h4>
        </div>
        <div id="collapseFour" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFour">
            <div class="panel-body">
                <p>All information is gathered from Lolking's winrate and pickrate charts, which can be found for each champ <a href="http://www.lolking.net/champions/malzahar#statistics">here</a>. It grabs the average of the last three days of data.</p>

                <p>I created a program in Java to gather the data and run these calculations, so I can replicate the data instantly.</p>

                <p>The power level calculation was done as follows:</p>

                <p>(WR-0.5) * (PR / (1 - BR))</p>

                <p>This calculates the amount of games that are lost by not banning a champion, compared to the average. Thanks a bunch to <a href="/u/PhreakRiot">/u/PhreakRiot</a> for their help in refining the formula from the previous post!</p>
            </div>
        </div>
    </div>
</div>

<asset:script>
    if(${!isHome}){
        $('#collapseOne').collapse("hide");
    }
</asset:script>