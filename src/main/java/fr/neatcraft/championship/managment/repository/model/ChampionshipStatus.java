package fr.neatcraft.championship.managment.repository.model;

public enum ChampionshipStatus {
    PLANNED, ONGOING, FINISHED;

    public boolean canTransitionTo(ChampionshipStatus target) {
        return switch (this) {
            case PLANNED -> target == ONGOING;
            case ONGOING -> target == FINISHED;
            case FINISHED -> false;
        };
    }
}
