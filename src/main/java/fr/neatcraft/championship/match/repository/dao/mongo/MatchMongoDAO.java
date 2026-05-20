package fr.neatcraft.championship.match.repository.dao.mongo;

import fr.neatcraft.championship.match.repository.dao.MatchDAO;
import fr.neatcraft.championship.match.repository.dao.entity.MatchEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MatchMongoDAO implements MatchDAO {
    private final MatchSpringMongoRepository mongoRepository;

    public MatchMongoDAO(MatchSpringMongoRepository mongoRepository) {
        this.mongoRepository = mongoRepository;
    }

    @Override
    public List<MatchEntity> findByChampionshipId(String championshipId) {
        return mongoRepository.findByChampionshipId(championshipId);
    }

    @Override
    public Optional<MatchEntity> findById(String id) {
        return mongoRepository.findById(id);
    }

    @Override
    public void create(MatchEntity entity) {
        mongoRepository.insert(entity);
    }

    @Override
    public void delete(String id) {
        mongoRepository.deleteById(id);
    }
}
