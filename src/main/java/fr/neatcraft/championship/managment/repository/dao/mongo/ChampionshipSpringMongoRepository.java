package fr.neatcraft.championship.managment.repository.dao.mongo;

import fr.neatcraft.championship.managment.repository.dao.entity.ChampionshipEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChampionshipSpringMongoRepository extends MongoRepository<ChampionshipEntity, String> {
}