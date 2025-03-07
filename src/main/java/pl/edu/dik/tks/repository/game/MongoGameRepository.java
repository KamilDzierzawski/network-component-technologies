package pl.edu.dik.tks.repository.game;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.dik.tks.model.game.Game;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.mongodb.client.model.Filters.eq;

//@Repository
//public class §MongoGameRepository implements GameRepository {
//
//    private final MongoCollection<Game> collection;
//
//    @Autowired
//    public MongoGameRepository(MongoDatabase database) {
//        this.collection = database.getCollection("game", Game.class);
//    }
//
//    public Game insert(Game game) {
//        game.setId(UUID.randomUUID());
//        return collection.insertOne(game).wasAcknowledged() ? game : null;
//    }
//
//    public Optional<Game> findById(Object id) {
//        Bson filter = Filters.eq("_id", id.toString());
//        return Optional.ofNullable(collection.find(filter).first());
//    }
//
//    public List<Game> findAll() {
//        return StreamSupport.stream(collection.find().spliterator(), false)
//                .collect(Collectors.toList());
//    }
//
//    public Game update(Game updatedEntity) {
//        // Extract the ID directly from the object
//        Object id = updatedEntity.getId();
//        Bson filter = Filters.eq("_id", id.toString());
//        return collection.replaceOne(filter, updatedEntity).wasAcknowledged() ? updatedEntity : null;
//    }
//
//    public void deleteById(Object id) {
//        Bson filter = eq("_id", id.toString());
//        collection.deleteOne(filter);
//    }
//
//}
