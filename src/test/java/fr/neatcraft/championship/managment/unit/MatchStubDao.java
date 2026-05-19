package fr.neatcraft.championship.managment.unit;

import fr.neatcraft.championship.managment.repository.dao.MatchDAO;
import fr.neatcraft.championship.managment.repository.dao.entity.MatchEntity;

import java.util.List;
import java.util.Optional;

public class MatchStubDao implements MatchDAO {

    @Override
    public List<MatchEntity> findByChampionshipId(String championshipId) {
        return List.of();
    }

    @Override
    public Optional<MatchEntity> findById(String id) {
        return Optional.empty();
    }

    @Override
    public void create(MatchEntity entity) {
    }

    @Override
    public void delete(String id) {
    }
}
