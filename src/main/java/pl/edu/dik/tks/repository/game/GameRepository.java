package pl.edu.dik.tks.repository.game;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.dik.tks.model.account.Account;
import pl.edu.dik.tks.model.game.Game;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.mongodb.client.model.Filters.eq;

@Repository
public class GameRepository {

    private final MongoCollection<Game> collection;

    @Autowired
    public GameRepository(MongoDatabase database) {
        this.collection = database.getCollection("game", Game.class);
    }

    public void insert(Game game) {
        game.setId(UUID.randomUUID());
        collection.insertOne(game);
    }

    public Optional<Game> findById(Object id) {
        Bson filter = Filters.eq("_id", id.toString());
        return Optional.ofNullable(collection.find(filter).first());
    }

    public List<Game> findAll() {
        return StreamSupport.stream(collection.find().spliterator(), false)
                .collect(Collectors.toList());
    }

    public boolean update(Game updatedEntity) {
        // Extract the ID directly from the object
        Object id = updatedEntity.getId();
        Bson filter = Filters.eq("_id", id.toString());
        return collection.replaceOne(filter, updatedEntity).wasAcknowledged();
    }

}
