package fr.neatcraft.championship.championship.repository.model;

import java.time.LocalDate;
import java.util.UUID;

public class Championship {
    private final UUID id;
    private final String name;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final ChampionshipStatus status;

    private Championship(Builder builder) {
        this.id = builder.id != null ? builder.id : UUID.randomUUID();
        this.name = builder.name;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.status = builder.status != null ? builder.status : ChampionshipStatus.PLANNED;
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public ChampionshipStatus getStatus() { return status; }

    public Championship changePeriod(LocalDate newStartDate, LocalDate newEndDate) {
        if (!newEndDate.isAfter(newStartDate)) {
            throw new InvalidPeriodException(newStartDate, newEndDate);
        }
        return Builder.from(this).startDate(newStartDate).endDate(newEndDate).build();
    }

    public Championship changeStatus(ChampionshipStatus newStatus) {
        if (!this.status.canTransitionTo(newStatus)) {
            throw new InvalidStatusTransitionException(this.status, newStatus);
        }
        return Builder.from(this).status(newStatus).build();
    }

    public static Builder builder() { return new Builder(); }

    public <T> T to(ChampionshipBuilder<T> visitor) {
        return visitor
                .id(id)
                .name(name)
                .startDate(startDate)
                .endDate(endDate)
                .status(status)
                .build();
    }

    public static final class Builder {
        private UUID id;
        private String name;
        private LocalDate startDate;
        private LocalDate endDate;
        private ChampionshipStatus status;

        private Builder() {}

        static Builder from(Championship championship) {
            return new Builder()
                    .id(championship.id)
                    .name(championship.name)
                    .startDate(championship.startDate)
                    .endDate(championship.endDate)
                    .status(championship.status);
        }

        public Builder id(UUID id) { this.id = id; return this; }
        public Builder name(String name) { this.name = name; return this; }
        public Builder startDate(LocalDate startDate) { this.startDate = startDate; return this; }
        public Builder endDate(LocalDate endDate) { this.endDate = endDate; return this; }
        public Builder status(ChampionshipStatus status) { this.status = status; return this; }

        public Championship build() { return new Championship(this); }
    }
}
