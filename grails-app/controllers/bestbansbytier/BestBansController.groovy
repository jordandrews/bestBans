package bestbansbytier

class BestBansController {
    BanCalculatorService banCalculatorService

    def index() { }

    def bronze() {
        List<ChampData> banList = ChampData.findAllByRank(RankTiers.BRONZE).sort{-it.power}
        List<ChampData> topTen = []
        if(banList && banList.size() >= 10){
            topTen = banList[0..9]
        }
        render(view: 'bans', model: [topTen: topTen, banList: banList, tier: RankTiers.BRONZE.description])
    }

    def silver() {
        List<ChampData> banList = ChampData.findAllByRank(RankTiers.SILVER).sort{-it.power}
        List<ChampData> topTen = []
        if(banList && banList.size() >= 10){
            topTen = banList[0..9]
        }
        render(view: 'bans', model: [topTen: topTen, banList: banList, tier: RankTiers.SILVER.description])
    }

    def gold() {
        List<ChampData> banList = ChampData.findAllByRank(RankTiers.GOLD).sort{-it.power}
        List<ChampData> topTen = []
        if(banList && banList.size() >= 10){
            topTen = banList[0..9]
        }
        render(view: 'bans', model: [topTen: topTen, banList: banList, tier: RankTiers.GOLD.description])
    }

    def platinum() {
        List<ChampData> banList = ChampData.findAllByRank(RankTiers.PLATINUM).sort{-it.power}
        List<ChampData> topTen = []
        if(banList && banList.size() >= 10){
            topTen = banList[0..9]
        }
        render(view: 'bans', model: [topTen: topTen, banList: banList, tier: RankTiers.PLATINUM.description])
    }

    def diamond() {
        List<ChampData> banList = ChampData.findAllByRank(RankTiers.DIAMOND).sort{-it.power}
        List<ChampData> topTen = []
        if(banList && banList.size() >= 10){
            topTen = banList[0..9]
        }
        render(view: 'bans', model: [topTen: topTen, banList: banList, tier: RankTiers.DIAMOND.description])
    }
}
