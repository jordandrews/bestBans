package bestbansbytier

enum RankTiers {
    BRONZE("bronze"),
    SILVER("silver"),
    GOLD("gold"),
    PLATINUM("platinum"),
    DIAMOND("diamond")

    final String description

    RankTiers(final String description) {
        this.description = description
    }


    String toString() {
        description
    }
}