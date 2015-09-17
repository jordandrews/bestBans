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
    String patchNumber = "5.18"

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
        def lowerCaseName = champion.toLowerCase()

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
}

