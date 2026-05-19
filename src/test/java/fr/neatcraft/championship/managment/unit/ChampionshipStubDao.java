package fr.neatcraft.championship.managment.unit;

import fr.neatcraft.championship.managment.repository.dao.ChampionshipDAO;
import fr.neatcraft.championship.managment.repository.dao.entity.ChampionshipEntity;
import fr.neatcraft.championship.managment.repository.model.ChampionshipStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ChampionshipStubDao implements ChampionshipDAO {
    public List<ChampionshipEntity> championship;

    public ChampionshipStubDao() {
        this.championship = List.of(
                ChampionshipEntity.builder()
                        .id(UUID.randomUUID().toString())
                        .name("Ligue 1")
                        .startDate(LocalDate.of(2026, 8, 1))
                        .endDate(LocalDate.of(2027, 5, 31))
                        .status(ChampionshipStatus.PLANNED.name())
                        .build()
        );
    }

    @Override
    public List<ChampionshipEntity> findAll() {
        return championship;
    }

    @Override
    public Optional<ChampionshipEntity> findById(String id) {
        return Optional.empty();
    }

    @Override
    public void create(ChampionshipEntity entity) {

    }

    @Override
    public void replace(ChampionshipEntity entity) {

    }

    @Override
    public void delete(String id) {

    }
}
