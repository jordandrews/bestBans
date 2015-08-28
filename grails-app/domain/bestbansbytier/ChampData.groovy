package bestbansbytier

class ChampData {
    RankTiers tier
    String champion
    BigDecimal winrate
    BigDecimal pickrate
    BigDecimal banrate
    BigDecimal influence
    Date dateCreated
    Integer ranking
    ServerRegions region = ServerRegions.NA
    String patchNumber = "5.16"



    //Setting Domain Class to not be attached to the database
//    def isAttached() {
//        return false
//    }

    def getAdjustedPickRate() {
        return (100 * pickrate)/(100-banrate)
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

