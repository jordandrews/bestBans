package bestbansbytier

class BanCalculatorService {
    static transactional = false

    Integer daysToCheck = 3
    def delayParse = 500

    def calculateData(ServerRegions region) {
        //This is the array creator and calculator
        //It starts off with a huge loop that runs once per champion.
        //First, it gathers the HTML of the Lolking site on ban data
        //Then the code gets the champion name.
        //It then cross references the HTMl with a champ name to see if it exists

        //Then, the code loops five times, once for each tier.
        //The Lolking site HTML with the champ data is pulled up and stored,
        //It only has to be accessed once per champ per tier (5 times total per champ)
        //A second loop calculates the average of the winrate/pickrate over the days-amount desired.
        //Finally, all this data is stored into the appropiate array.

        //one loop per tier, one loop per champ
        String parsedHTMLPick

        Boolean lolKingSupported = region in ServerRegions.getLolKingSupportedRegions()
        List<String> champList = championList() //There are 126 champions
        Date today = new Date().clearTime() //create a date being today with no timestamp

        //To parse the banrate, only one call is needed per region
        String parsedHTMLBan = parseBanHTML(region)

        //To parse the pickrate from OP, only one call is needed per Region
        if (!lolKingSupported) {
            parsedHTMLPick = parsePickOpHTML(region)
        }

        //Tier list loop
        RankTiers.each { tier ->
            System.out.println("---------------------------------------------------${tier}---------------------------------------------------")
            System.out.println("")
            Thread.sleep(delayParse) // Sleep each tier just for safety on parsing win rates from op.gg

            //To parse the winrate from OP, only one call is needed per tier
            String parsedHTMLWin = parseWinHTML(tier, region)

            //Champion list loop
            champList.each { String champName ->
                System.out.println("Loading... ${champName.capitalize()} for tier ${tier.description} and region ${region.description}")
                log.info("Loading... ${champName.capitalize()} for tier ${tier.description} and region ${region.description}")

                Double winrate = findOpRate(champName, parsedHTMLWin)
                Double banrate = findOpRate(champName, parsedHTMLBan)
                Double pickrate

                //To parse the pickrate of Lolking, a call must be made for every champion in the tier
                if (lolKingSupported) {
                    Thread.sleep(delayParse) // only need to sleep for lolking entries
                    parsedHTMLPick = parsePickKingHTML(tier, champName, region)
                    pickrate = findKingRate(champName, parsedHTMLPick)
                }
                else {
                    pickrate = findOpRate(champName, parsedHTMLPick)
                }

                def champ = ChampData.findByChampionAndTierAndRegionAndCreateDate(champName, tier, region, today) //TODO: add patch number variable to the search
                if(!champ) {    //if not Add our new champData to the database
                    champ = new ChampData(tier: tier,
                            champion: champName,
                            banrate: banrate,
                            winrate: winrate,
                            pickrate: pickrate,
                            createDate: today,
                            patchNumber: "5.21", //TODO: patch number here patchNumber: #
                            region: region
                    )
                }
                else { //else if data exists just update and save (should be a rare case for when server is restarted before the auto populate time for that day)
                    champ.banrate = banrate
                    champ.winrate = winrate
                    champ.pickrate = pickrate
                    champ.region = region
                    champ.patchNumber = "5.21"
                }
                champ = calculateAggregateValues(champ)

                champ.save(flush: true)
            }
        }
    }

    def assignRanks(ServerRegions region) {
        Date today = new Date().clearTime()
        RankTiers.each { RankTiers tier ->

            def i = 1
            ChampData.findAllByTierAndRegionAndPatchNumberAndCreateDate(tier, region, '5.21',  today).sort{-it.influence}.each{ ChampData champ ->
                champ.rank = i
                champ.previousRank = ChampData.findByChampionAndTierAndRegionAndCreateDate(champ.champion, champ.tier, champ.region, champ.createDate-1)?.rank ?: champ.rank
                champ.save(flush: true)
                i++
            }
        }
    }

    List<String> championList() {
        List<String> championList = []

        championList.add("aatrox");
        championList.add("ahri");
        championList.add("akali");
        championList.add("alistar");
        championList.add("amumu");
        championList.add("anivia");
        championList.add("annie");
        championList.add("ashe");
        championList.add("azir");
        championList.add("bard");
        championList.add("blitzcrank");
        championList.add("brand");
        championList.add("braum");
        championList.add("caitlyn");
        championList.add("cassiopeia");
        championList.add("chogath");
        championList.add("corki");
        championList.add("darius");
        championList.add("diana");
        championList.add("drMundo");
        championList.add("draven");
        championList.add("ekko");
        championList.add("elise");
        championList.add("evelynn");
        championList.add("ezreal");
        championList.add("fiddleSticks");
        championList.add("fiora");
        championList.add("fizz");
        championList.add("galio");
        championList.add("gangplank");
        championList.add("garen");
        championList.add("gnar");
        championList.add("gragas");
        championList.add("graves");
        championList.add("hecarim");
        championList.add("heimerdinger");
        championList.add("irelia");
        championList.add("janna");
        championList.add("jarvanIV");
        championList.add("jax");
        championList.add("jayce");
        championList.add("jinx");
        championList.add("kalista");
        championList.add("karma");
        championList.add("karthus");
        championList.add("kassadin");
        championList.add("katarina");
        championList.add("kayle");
        championList.add("kennen");
        championList.add("khazix");
        championList.add("kindred");
        championList.add("kogMaw");
        championList.add("leblanc");
        championList.add("leeSin");
        championList.add("leona");
        championList.add("lissandra");
        championList.add("lucian");
        championList.add("lulu");
        championList.add("lux");
        championList.add("malphite");
        championList.add("malzahar");
        championList.add("maokai");
        championList.add("masterYi");
        championList.add("missFortune");
        championList.add("mordekaiser");
        championList.add("morgana");
        championList.add("nami");
        championList.add("nasus");
        championList.add("nautilus");
        championList.add("nidalee");
        championList.add("nocturne");
        championList.add("nunu");
        championList.add("olaf");
        championList.add("orianna");
        championList.add("pantheon");
        championList.add("poppy");
        championList.add("quinn");
        championList.add("rammus");
        championList.add("rekSai");
        championList.add("renekton");
        championList.add("rengar");
        championList.add("riven");
        championList.add("rumble");
        championList.add("ryze");
        championList.add("sejuani");
        championList.add("shaco");
        championList.add("shen");
        championList.add("shyvana");
        championList.add("singed");
        championList.add("sion");
        championList.add("sivir");
        championList.add("skarner");
        championList.add("sona");
        championList.add("soraka");
        championList.add("swain");
        championList.add("syndra");
        championList.add("tahmKench");
        championList.add("talon");
        championList.add("taric");
        championList.add("teemo");
        championList.add("thresh");
        championList.add("tristana");
        championList.add("trundle");
        championList.add("tryndamere");
        championList.add("twistedFate");
        championList.add("twitch");
        championList.add("udyr");
        championList.add("urgot");
        championList.add("varus");
        championList.add("vayne");
        championList.add("veigar");
        championList.add("velkoz");
        championList.add("vi");
        championList.add("viktor");
        championList.add("vladimir");
        championList.add("volibear");
        championList.add("warwick");
        championList.add("monkeyKing");
        championList.add("xerath");
        championList.add("xinZhao");
        championList.add("yasuo");
        championList.add("yorick");
        championList.add("zac");
        championList.add("zed");
        championList.add("ziggs");
        championList.add("zilean");
        championList.add("zyra");

        return championList;
    }

    ChampData calculateAggregateValues(ChampData champData) {
        def winrate = 0
        def pickrate = 0
        def banrate = 0

        List<ChampData> dataList = fetchPreviousChampData(champData, daysToCheck)
        dataList.each{ ChampData dataEntry ->//TODO: change 3 to size of sliding window
            winrate += dataEntry.winrate
            pickrate += dataEntry.pickrate
            banrate += dataEntry.banrate
        }

        champData.aggregatedWinrate = winrate/(dataList.size())
        champData.aggregatedPickrate = pickrate/(dataList.size())
        champData.aggregatedBanrate = banrate/(dataList.size())

        champData.influence = 100 * (champData.aggregatedPickrate/(100-champData.aggregatedBanrate) * (champData.aggregatedWinrate - 50))
        return champData
    }

    List<ChampData> fetchPreviousChampData (ChampData champData, Integer numberToInclude) {
        List<ChampData> dataList = [champData]
        (1..(numberToInclude-1)).each { i ->
            def champ = ChampData.findByChampionAndTierAndRegionAndPatchNumberAndCreateDate(champData.champion, champData.tier, champData.region, champData.patchNumber, champData.createDate - i)
            if(champ) {
                dataList.add(champ)
            }
        }
        return dataList
    }

    String parsePickKingHTML(RankTiers division, String champName, ServerRegions region) throws IOException {
        String parsed

        parsed = parseWebsite("http://www.lolking.net/champions/" + champName.toLowerCase() + "?region=" + region.name().toLowerCase() + "&map=sr&queue=1x1&league=" + division.description + "#statistics")
        return narrowData(parsed, "popularLineBig", 0, "true")
    }


    String parseWinHTML(RankTiers division, ServerRegions region) throws IOException {
        if (region == ServerRegions.KR)
            return parseWebsite("http://op.gg/statistics/ajax/champion.json/type=win&league=${division.description}&period=today&mapId=1&queue=ranked_solo")
        else
            return parseWebsite("http://${region.name().toLowerCase()}.op.gg/statistics/ajax/champion.json/type=win&league=${division.description}&period=today&mapId=1&queue=ranked_solo")
    }

    String parsePickOpHTML(ServerRegions region) throws IOException {
        if (region == ServerRegions.KR)
            return parseWebsite("http://op.gg/statistics/ajax/champion.json/type=picked&league=&period=today&mapId=1&queue=ranked_solo")
        else
            return parseWebsite("http://${region.name().toLowerCase()}.op.gg/statistics/ajax/champion.json/type=picked&league=&period=today&mapId=1&queue=ranked_solo")
    }

    String parseBanHTML(ServerRegions region) throws IOException {
        if (region == ServerRegions.KR)
            return parseWebsite("http://op.gg/statistics/ajax/champion.json/type=banned&league=&period=today&mapId=1&queue=ranked_solo")
        else
            return parseWebsite("http://${region.name().toLowerCase()}.op.gg/statistics/ajax/champion.json/type=banned&league=&period=today&mapId=1&queue=ranked_solo")
    }

    Double findKingRate(String champion, String rawHTML) throws IOException {
        String parsed;
        String data;
        double dataNumb;

        dataNumb = 999;
        parsed = "error error";

        parsed = rawHTML;

        data = lastData(parsed, "hover", 0, "x22,");
        //System.out.println(data)
        data = narrowData(data, ",", 1, ",")
        //System.out.println(data)
        data = data.replaceAll("[^\\d.]", "");
        dataNumb = Double.parseDouble(data);

        return dataNumb;
    }

    Double findOpRate(String champion, String rawHTML) throws IOException {
        Double rate = 0
        String data
        String[] numbArray

        data = narrowData(rawHTML, champion.capitalize(), 0, "span>")
        data = narrowData(data, "winratio", 11, "%")

        numbArray = data.split(" ")
        for (String s : numbArray)
            rate += Double.parseDouble(s)

        return rate
    }

    String narrowData(String fullText, String startPoint, int adjust, String endPoint) {
        int textStart;
        int textEnd;
        textStart = fullText.indexOf(startPoint) + adjust;
        textEnd = fullText.indexOf(endPoint, textStart);
        if(textStart < 0 || textEnd < 0) {
            return "0"
        }
        return fullText.substring(textStart, textEnd);
    }

    String lastData(String fullText, String startPoint, int adjust, String endPoint) {
        int textStart;
        int textEnd;

        textStart = fullText.lastIndexOf(startPoint) + adjust;
        textEnd = fullText.indexOf(endPoint, textStart);
        return fullText.substring(textStart, textEnd);
    }

    String parseWebsite(String website) throws IOException {
        StringBuffer sourceCode;
        URL siteToParse;

        siteToParse = new URL(website);
        InputStream is = siteToParse.openStream();
        int ptr = 0;
        sourceCode = new StringBuffer();
        while ((ptr = is.read()) != -1)
            sourceCode.append((char)ptr);
        return sourceCode.toString();
    }
}
