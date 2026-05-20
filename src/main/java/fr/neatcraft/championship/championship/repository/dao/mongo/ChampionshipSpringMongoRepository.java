package fr.neatcraft.championship.championship.repository.dao.mongo;

import fr.neatcraft.championship.championship.repository.dao.entity.ChampionshipEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChampionshipSpringMongoRepository extends MongoRepository<ChampionshipEntity, String> {
}
