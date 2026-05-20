package fr.neatcraft.championship.match.repository.dao.mongo;

import fr.neatcraft.championship.match.repository.dao.entity.MatchEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MatchSpringMongoRepository extends MongoRepository<MatchEntity, String> {
    List<MatchEntity> findByChampionshipId(String championshipId);
}
