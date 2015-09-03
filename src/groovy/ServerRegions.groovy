package bestbansbytier

enum ServerRegions { //supports na, euw, eune, br
    NA("North America"),
    BR("Brazil"),
    EUNE("Europe Nordic & East"),
    EUW("Europe West"),
    LAN("Latin America North"),
    LAS("Latin America South"),
    OCE("Oceania"),
    RU("Russia"),
    TR("Turkey"),
    KR("Republic of Korea")

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
