package fr.neatcraft.championship.managment.repository.dao.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.UUID;

@Document(collection = "championships")
public class ChampionshipEntity {
    @Id
    private final String id;
    private final String name;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String status;

    @PersistenceCreator
    ChampionshipEntity(String id, String name, LocalDate startDate, LocalDate endDate, String status) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public static Builder builder() { return new Builder(); }

    public <T> T to(ChampionshipEntityBuilder<T> visitor) {
        return visitor
                .id(UUID.fromString(this.id))
                .name(this.name)
                .startDate(this.startDate)
                .endDate(this.endDate)
                .status(this.status)
                .build();
    }

    public static final class Builder {
        private String id;
        private String name;
        private LocalDate startDate;
        private LocalDate endDate;
        private String status;

        private Builder() {}

        public Builder id(String id) { this.id = id; return this; }
        public Builder name(String name) { this.name = name; return this; }
        public Builder startDate(LocalDate startDate) { this.startDate = startDate; return this; }
        public Builder endDate(LocalDate endDate) { this.endDate = endDate; return this; }
        public Builder status(String status) { this.status = status; return this; }

        public ChampionshipEntity build() {
            return new ChampionshipEntity(id, name, startDate, endDate, status);
        }
    }
}
