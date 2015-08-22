package bestbansbytier

class ChampData {
    RankTiers rank
    String champion
    double winrate
    double pickrate
    double banrate
    double power

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
        if (this.power > data.power) return -1;
        if (this.power < data.power) return 1;
        return 0;
    }
}

