package fr.neatcraft.championship.championship.repository.model;

public enum ChampionshipStatus {
    PLANNED, ONGOING, FINISHED;

    public boolean canTransitionTo(ChampionshipStatus next) {
        return switch (this) {
            case PLANNED -> next == ONGOING;
            case ONGOING -> next == FINISHED;
            case FINISHED -> false;
        };
    }
}
