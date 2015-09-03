package bestbansbytier

enum ServerRegions { //supports na, euw, eune, br
    BR("Brazil"),
    EUNE("Europe Nordic & East"),
    EUW("Europe West"),
    KR("Republic of Korea"),
    LAN("Latin America North"),
    LAS("Latin America South"),
    NA("North America"),
    OCE("Oceania"),
    RU("Russia"),
    TR("Turkey")

    final String description

    ServerRegions(final String description) {
        this.description = description
    }


    String toString() {
        description
    }

    static List getLolKingSupportedRegions () {
        return [BR, EUNE, EUW, NA]
    }
}
