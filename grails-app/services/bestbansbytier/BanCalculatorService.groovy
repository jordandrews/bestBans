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

        //One loop per champ, one loop per tier, one loop per day.

        String banParsedHTML = parseBanHTML()
        List<String> champList = championList() //There are 126 champions
        Date today = new Date().clearTime() //create a date being today with no timestamp

        //Tier list loop
        RankTiers.each { tier ->
            //Champion list loop
            champList.each { String champName ->
                System.out.println("Loading... " + champName.capitalize());

                Double banrate = calculateBanrate(champName, banParsedHTML);


                Thread.sleep(delayParse);
                Double winrate = 0.0;
                Double pickrate = 0.0;

                //Days to average
                String parsedHTMLOrigin = parseWinPickHTML(tier.description, champName, region.name().toLowerCase());
                String parsedHTML = narrowData(parsedHTMLOrigin, "winrateLineBig", 0, "true");
                winrate += calculateWinPickRate(parsedHTML);

                parsedHTML = narrowData(parsedHTMLOrigin, "popularLineBig", 0, "true");
                pickrate += calculateWinPickRate(parsedHTML);

                //Se if an entry already exists in the database
                def champ = ChampData.findByChampionAndTierAndRegionAndPatchNumberAndCreateDate(champName, tier, region, "5.16", today) //TODO: add patch number variable to the search
                if(!champ) {    //if not Add our new champData to the database

                    champ = new ChampData(tier: tier,
                            champion: champName,
                            banrate: banrate,
                            winrate: winrate,
                            pickrate: pickrate,
                            createDate: today,
                            patchNumber: "5.16", //TODO: patch number here patchNumber: #
                            region: region
                    )
                }
                else { //else if data exists just update and save (should be a rare case for when server is restarted before the auto populate time for that day)
                    champ.banrate = banrate
                    champ.winrate = winrate
                    champ.pickrate = pickrate
                    champ.region = region
                    //TODO: patch number add champ.patchNumber =
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
            ChampData.findAllByTierAndRegionAndPatchNumberAndCreateDate(tier, region, '5.16',  today).sort{it.influence}.each{ ChampData champ ->
                champ.rank = i
                champ.previousRank = ChampData.findByChampionAndTierAndRegionAndPatchNumberAndCreateDate(champ.champion, champ.tier, champ.region, champ.patchNumber, champ.createDate-1)?.rank ?: 0
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
        championList.add("drmundo");
        championList.add("draven");
        championList.add("ekko");
        championList.add("elise");
        championList.add("evelynn");
        championList.add("ezreal");
        championList.add("fiddlesticks");
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
        championList.add("jarvaniv");
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
        championList.add("kogmaw");
        championList.add("leblanc");
        championList.add("leesin");
        championList.add("leona");
        championList.add("lissandra");
        championList.add("lucian");
        championList.add("lulu");
        championList.add("lux");
        championList.add("malphite");
        championList.add("malzahar");
        championList.add("maokai");
        championList.add("masteryi");
        championList.add("missfortune");
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
        championList.add("reksai");
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
        championList.add("tahmkench");
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
        championList.add("monkeyking");
        championList.add("xerath");
        championList.add("xinzhao");
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

    String parseWinPickHTML(String division, String champName, String region) throws IOException {
        String parsed;


        parsed = parseWebsite("http://www.lolking.net/champions/" + champName + "&region=" + region + "&map=sr&queue=1x1&league=" + division + "#statistics");
        return narrowData(parsed, "popularAndWinrateLine", 0, "<");
    }

    String parseBanHTML() throws IOException {
        return parseWebsite("http://www.lolking.net/charts?type=bans");
    }

    Double calculateWinPickRate(String rawHTML) throws IOException {
        String parsed;
        String data;
        double dataNumb;

        dataNumb = 999;
        parsed = "error error";

        parsed = rawHTML;
        data = lastData(parsed, "hover", -8, ",");
        data = data.replaceAll("[^\\d.]", "");
        dataNumb = Double.parseDouble(data);

        return dataNumb;
    }

    Double calculateBanrate(String champion, String rawHTML) throws IOException {
        Double banrate = 0
        String banData
        String[] banNumbArray

        banData = narrowData(rawHTML, "LKChart.storeLegacyChart", 0, "script")

        if(banData.contains(champion)) {
            banData = narrowData(banData, champion, 0, "}")
            banData = narrowData(banData, "values1", 12, "\\")
            banData = banData.replace(","," ")

            banNumbArray = banData.split(" ")
            for (String s : banNumbArray)
                banrate += Double.parseDouble(s)
        }

        return banrate
    }

    String narrowData(String fullText, String startPoint, int adjust, String endPoint) {
        int textStart;
        int textEnd;

        textStart = fullText.indexOf(startPoint) + adjust;
        textEnd = fullText.indexOf(endPoint, textStart);
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
