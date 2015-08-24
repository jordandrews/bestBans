package bestbansbytier

import grails.transaction.Transactional

import java.text.DecimalFormat

@Transactional
class BanCalculatorService {

    Map<RankTiers, List<ChampData>> banMap = [:]
    def daysToCheck = 3
    def delayParse = 150
    String region = "na" //na, euw, eune, br

    List<ChampData> getBans(tier){
        return banMap.get(tier)
    }

    def calculateBans() {
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

        //Champion list loop
        champList.each { String champName ->
            System.out.println("Loading... " + champName.capitalize());

            Double banrate = calculateBanrate(champName, banParsedHTML);

            //Tier list loop
            RankTiers.each { tier ->
                Thread.sleep(delayParse);
                Double winrate = 0.0;
                Double pickrate = 0.0;

                //Days to average
                for(int k=1; k<=daysToCheck; k++) {
                    String parsedHTMLOrigin = parseWinPickHTML(tier.description, champName, region);

                    String parsedHTML = narrowData(parsedHTMLOrigin, "winrateLineBig", 0, "true");
                    winrate += calculateWinPickRate(parsedHTML, k);

                    parsedHTML = narrowData(parsedHTMLOrigin, "popularLineBig", 0, "true");
                    pickrate += calculateWinPickRate(parsedHTML, k);
                }

                winrate = winrate/daysToCheck;
                pickrate = pickrate/daysToCheck;
                Double power = calculatePower(pickrate, winrate, banrate);

                //Add our new champData to the appropriate place in the map
                if(champName) { //TODO: is there a bug happening when we don't have results back?

                    def champ = ChampData.findByChampionAndRank(champName, tier)
                    if(!champ) {
                        new ChampData(rank: tier,
                                    champion: champName,
                                    banrate: banrate,
                                    winrate: winrate,
                                    pickrate: pickrate,
                                    power: power).save(flush: true)
                    }
                    else {
                        champ.banrate = banrate
                        champ.winrate = winrate
                        champ.pickrate = pickrate
                        champ.power = power
                        champ.save(flush: true)
                    }

                }
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

    Double calculatePower(double pickrate, double winrate, double banrate) {
        DecimalFormat df = new DecimalFormat("#.##");


        return Double.valueOf(df.format(100 * (pickrate/(100-banrate) * (winrate - 50))));
    }

    String parseWinPickHTML(String division, String champName, String region) throws IOException {
        String parsed;


        parsed = parseWebsite("http://www.lolking.net/champions/" + champName + "&region=" + region + "&map=sr&queue=1x1&league=" + division + "#statistics");
        return narrowData(parsed, "popularAndWinrateLine", 0, "<");
    }

    String parseBanHTML() throws IOException {
        return parseWebsite("http://www.lolking.net/charts?type=bans");
    }

    Double calculateWinPickRate(String rawHTML, int day) throws IOException {
        String parsed;
        String data;
        double dataNumb;

        dataNumb = 999;
        parsed = "error error";

        if (day == 1)
            parsed = rawHTML;

        if (day == 2)
            parsed = rawHTML.substring(0, rawHTML.lastIndexOf("hover"));

        if (day == 3)
            parsed = rawHTML.substring(0, rawHTML.substring(0, rawHTML.lastIndexOf("hover")).lastIndexOf("hover"));

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
