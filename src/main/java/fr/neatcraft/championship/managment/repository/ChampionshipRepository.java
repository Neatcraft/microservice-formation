package fr.neatcraft.championship.managment.repository;

import fr.neatcraft.championship.managment.repository.dao.ChampionshipDAO;
import fr.neatcraft.championship.managment.repository.dao.entity.ChampionshipEntity;
import fr.neatcraft.championship.managment.repository.model.Championship;
import fr.neatcraft.championship.managment.repository.model.ChampionshipAggregateBuilder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ChampionshipRepository {
    private final ChampionshipDAO championshipDAO;

    public ChampionshipRepository(final ChampionshipDAO championshipDAO) {
        this.championshipDAO = championshipDAO;
    }

    public List<Championship> findAll() {
        return this.championshipDAO.findAll()
                .stream()
                .map(entity -> entity.to(new ChampionshipAggregateBuilder()))
                .toList();
    }

    public Optional<Championship> findById(UUID id) {
        return this.championshipDAO.findById(id.toString())
                .map(entity -> entity.to(new ChampionshipAggregateBuilder()));
    }

    public void create(Championship championship) {
        this.championshipDAO.create(toEntity(championship));
    }

    public void replace(Championship championship) {
        this.championshipDAO.replace(toEntity(championship));
    }

    public void delete(UUID id) {
        this.championshipDAO.delete(id.toString());
    }

    private ChampionshipEntity toEntity(Championship championship) {
        return ChampionshipEntity.builder()
                .id(championship.getId().toString())
                .name(championship.getName())
                .startDate(championship.getStartDate())
                .endDate(championship.getEndDate())
                .status(championship.getStatus().name())
                .build();
    }
}
