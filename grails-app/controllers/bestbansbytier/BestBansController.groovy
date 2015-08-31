package bestbansbytier

class BestBansController {
    BanCalculatorService banCalculatorService

    def index() {
        Map<String, List<ChampData>> banMap = [:]
        def dataCount

        session.region = session.region ?: ServerRegions.NA

        RankTiers.each{ tier ->
            def champs = fetchRecentChampDataForTierAndRegion(tier, session.region).sort{-it.influence}
            if(champs.size() > 3){
                banMap[tier.description] = champs[0..3]
            }
            dataCount = champs.size() // should be the last to finish updating
        }

        render(view: 'index', model: [banMap: banMap, dataCount: dataCount])
    }

    def bronze() {
        session.region = session.region ?: ServerRegions.NA
        List<ChampData> banList = fetchRecentChampDataForTierAndRegion(RankTiers.BRONZE, session.region).sort{-it.influence}
        List<ChampData> topTen = []
        if(banList && banList.size() >= 10){
            topTen = banList[0..9]
        }
        render(view: 'bans', model: [topTen: topTen, banList: banList, tier: RankTiers.BRONZE.description])
    }

    def silver() {
        session.region = session.region ?: ServerRegions.NA
        List<ChampData> banList = fetchRecentChampDataForTierAndRegion(RankTiers.SILVER, session.region).sort{-it.influence}
        List<ChampData> topTen = []
        if(banList && banList.size() >= 10){
            topTen = banList[0..9]
        }
        render(view: 'bans', model: [topTen: topTen, banList: banList, tier: RankTiers.SILVER.description])
    }

    def gold() {
        session.region = session.region ?: ServerRegions.NA
        List<ChampData> banList = fetchRecentChampDataForTierAndRegion(RankTiers.GOLD, session.region).sort{-it.influence}
        List<ChampData> topTen = []
        if(banList && banList.size() >= 10){
            topTen = banList[0..9]
        }
        render(view: 'bans', model: [topTen: topTen, banList: banList, tier: RankTiers.GOLD.description])
    }

    def platinum() {
        session.region = session.region ?: ServerRegions.NA
        List<ChampData> banList = fetchRecentChampDataForTierAndRegion(RankTiers.PLATINUM, session.region).sort{-it.influence}
        List<ChampData> topTen = []
        if(banList && banList.size() >= 10){
            topTen = banList[0..9]
        }
        render(view: 'bans', model: [topTen: topTen, banList: banList, tier: RankTiers.PLATINUM.description])
    }

    def diamond() {
        session.region = session.region ?: ServerRegions.NA
        List<ChampData> banList = fetchRecentChampDataForTierAndRegion(RankTiers.DIAMOND, session.region).sort{-it.influence}
        List<ChampData> topTen = []
        if(banList && banList.size() >= 10){
            topTen = banList[0..9]
        }
        render(view: 'bans', model: [topTen: topTen, banList: banList, tier: RankTiers.DIAMOND.description])
    }

    def changeRegion() {
        session.region = ServerRegions[params.region]
        redirect(controller: params.previousController, action: params.previousAction)
    }

    List<ChampData> fetchRecentChampDataForTierAndRegion(def tier, def region) {
        List<ChampData> resultList = []
        Date today = new Date().clearTime() //create a date being today with no timestamp

        List<ChampData> champDataToday = ChampData.findAllByTierAndRegionAndCreateDate(tier, region, today)
        List<ChampData> champDataYesterday = ChampData.findAllByTierAndRegionAndCreateDate(tier, region, today-1)
        def listOfChampNames = (champDataToday*.champion + champDataYesterday*.champion).unique()

        listOfChampNames.each { name ->
            def champData = champDataToday.find{it.champion == name} ?: champDataYesterday.find{it.champion == name}
            resultList.add(champData)
        }

        return resultList
    }
}
