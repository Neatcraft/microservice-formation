package fr.neatcraft.championship.championship.repository.dao.mongo;

import fr.neatcraft.championship.championship.repository.dao.ChampionshipDAO;
import fr.neatcraft.championship.championship.repository.dao.entity.ChampionshipEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ChampionshipMongoDAO implements ChampionshipDAO {
    private final ChampionshipSpringMongoRepository mongoRepository;

    public ChampionshipMongoDAO(ChampionshipSpringMongoRepository mongoRepository) {
        this.mongoRepository = mongoRepository;
    }

    @Override
    public List<ChampionshipEntity> findAll() {
        return mongoRepository.findAll();
    }

    @Override
    public Optional<ChampionshipEntity> findById(String id) {
        return mongoRepository.findById(id);
    }

    @Override
    public void create(ChampionshipEntity entity) {
        mongoRepository.insert(entity);
    }

    @Override
    public void replace(ChampionshipEntity entity) {
        mongoRepository.save(entity);
    }

    @Override
    public void delete(String id) {
        mongoRepository.deleteById(id);
    }
}
