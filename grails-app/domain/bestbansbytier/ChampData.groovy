package bestbansbytier

class ChampData {
    RankTiers tier
    String champion
    BigDecimal winrate
    BigDecimal pickrate
    BigDecimal banrate
    BigDecimal aggregatedWinrate  //Average winrate over 3 days
    BigDecimal aggregatedPickrate //Average pickrate over 3 days
    BigDecimal aggregatedBanrate  //Average banrate over 3 days
    BigDecimal influence
    Date createDate = new Date().clearTime()
    Integer rank
    Integer previousRank
    ServerRegions region = ServerRegions.NA
    String patchNumber = "5.22"

    static constraints = {
        rank(nullable: true, blank: true)
        previousRank(nullable: true, blank: true)
    }

    def getAdjustedPickRate() {
        def adjPickRate = (100 * pickrate)/(100-banrate)
        return adjPickRate > 99 ? 99.0 : adjPickRate //Never return a rate over 100
    }

    def getAdjustedAggregatedPickRate() {
        def adjPickRate = (100 * aggregatedPickrate)/(100-aggregatedBanrate)
        return adjPickRate > 99 ? 99.0 : adjPickRate //Never return a rate over 100
    }

    def getDisplayName() {
        def lowerCaseName = champion.toLowerCase()

        transformName(lowerCaseName)
    }

    static def transformName(String lowerCaseName){
        if(lowerCaseName == 'monkeyking'){
            return 'Wukong'
        }
        if(lowerCaseName == 'chogath'){
            return "Cho'Gath"
        }
        if(lowerCaseName == 'drmundo'){
            return "Dr. Mundo"
        }
        if(lowerCaseName == 'xinzhao'){
            return "Xin Zhao"
        }
        if(lowerCaseName == 'tahmkench'){
            return "Tahm Kench"
        }
        if(lowerCaseName == 'twistedfate'){
            return "Twisted Fate"
        }
        if(lowerCaseName == 'jarvaniv'){
            return "Jarvan IV"
        }
        if(lowerCaseName == 'missfortune'){
            return "Miss Fortune"
        }
        if(lowerCaseName == 'masteryi'){
            return "Master Yi"
        }
        if(lowerCaseName == 'leesin'){
            return "Lee Sin"
        }
        if(lowerCaseName == 'kogmaw'){
            return "Kog'Maw"
        }
        if(lowerCaseName == 'reksai'){
            return "Rek'Sai"
        }
        return lowerCaseName.capitalize()
    }

    public int compareTo(ChampData data) {
        if (this.influence > data.influence) return -1;
        if (this.influence < data.influence) return 1;
        return 0;
    }

    static List<String> getChampionList() {
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

    static List<Map> getChampSelect(){
        List<Map> champSelect = []
        getChampionList().each{ name ->
            def displayName = transformName(name.toLowerCase())
            champSelect.add([displayName: displayName, value: name])
        }
        return champSelect
    }
}

