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
    String patchNumber = "5.17"

    static constraints = {
        rank(nullable: true, blank: true)
        previousRank(nullable: true, blank: true)
    }

    def getAdjustedPickRate() {
        return (100 * pickrate)/(100-banrate)
    }

    def getAdjustedAggregatedPickRate() {
        return (100 * aggregatedPickrate)/(100-aggregatedBanrate)
    }

    def getDisplayName() {
        if(champion == 'monkeyking'){
            return 'Wukong'
        }
        if(champion == 'chogath'){
            return "Cho'Gath"
        }
        if(champion == 'drmundo'){
            return "Dr. Mundo"
        }
        if(champion == 'xinzhao'){
            return "Xin Zhao"
        }
        if(champion == 'tahmkench'){
            return "Tahm Kench"
        }
        return champion.capitalize()
    }

    public int compareTo(ChampData data) {
        if (this.influence > data.influence) return -1;
        if (this.influence < data.influence) return 1;
        return 0;
    }
}

