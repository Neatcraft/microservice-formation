package fr.neatcraft.championship.championship;

import fr.neatcraft.championship.championship.repository.dao.ChampionshipDAO;
import fr.neatcraft.championship.championship.repository.dao.entity.ChampionshipEntity;
import fr.neatcraft.championship.championship.repository.model.ChampionshipStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ChampionshipStubDao implements ChampionshipDAO {

    public final List<ChampionshipEntity> championship = new ArrayList<>(List.of(
            ChampionshipEntity.builder()
                    .id(UUID.randomUUID().toString())
                    .name("Ligue 1")
                    .startDate(LocalDate.of(2026, 8, 1))
                    .endDate(LocalDate.of(2027, 5, 31))
                    .status(ChampionshipStatus.PLANNED.name())
                    .build()
    ));

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
        championship.add(entity);
    }

    @Override
    public void replace(ChampionshipEntity entity) {}

    @Override
    public void delete(String id) {}
}
