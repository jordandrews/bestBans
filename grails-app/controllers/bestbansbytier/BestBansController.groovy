package bestbansbytier

class BestBansController {
    BanCalculatorService banCalculatorService

    def index() {
        Map<String, List<ChampData>> banMap = [:]
        def dataCount

        RankTiers.each{ tier ->
            def champs = ChampData.findAllByTier(tier).sort{-it.influence}
            if(champs.size() > 3){
                banMap[tier.description] = champs[0..3]
            }
            dataCount = ChampData.findAllByTier(tier).size() // should be the last to finish updating
        }



        render(view: 'index', model: [banMap: banMap, dataCount: dataCount])
    }

    def bronze() {
        List<ChampData> banList = ChampData.findAllByTier(RankTiers.BRONZE).sort{-it.influence}
        List<ChampData> topTen = []
        if(banList && banList.size() >= 10){
            topTen = banList[0..9]
        }
        render(view: 'bans', model: [topTen: topTen, banList: banList, tier: RankTiers.BRONZE.description])
    }

    def silver() {
        List<ChampData> banList = ChampData.findAllByTier(RankTiers.SILVER).sort{-it.influence}
        List<ChampData> topTen = []
        if(banList && banList.size() >= 10){
            topTen = banList[0..9]
        }
        render(view: 'bans', model: [topTen: topTen, banList: banList, tier: RankTiers.SILVER.description])
    }

    def gold() {
        List<ChampData> banList = ChampData.findAllByTier(RankTiers.GOLD).sort{-it.influence}
        List<ChampData> topTen = []
        if(banList && banList.size() >= 10){
            topTen = banList[0..9]
        }
        render(view: 'bans', model: [topTen: topTen, banList: banList, tier: RankTiers.GOLD.description])
    }

    def platinum() {
        List<ChampData> banList = ChampData.findAllByTier(RankTiers.PLATINUM).sort{-it.influence}
        List<ChampData> topTen = []
        if(banList && banList.size() >= 10){
            topTen = banList[0..9]
        }
        render(view: 'bans', model: [topTen: topTen, banList: banList, tier: RankTiers.PLATINUM.description])
    }

    def diamond() {
        List<ChampData> banList = ChampData.findAllByTier(RankTiers.DIAMOND).sort{-it.influence}
        List<ChampData> topTen = []
        if(banList && banList.size() >= 10){
            topTen = banList[0..9]
        }
        render(view: 'bans', model: [topTen: topTen, banList: banList, tier: RankTiers.DIAMOND.description])
    }
}
