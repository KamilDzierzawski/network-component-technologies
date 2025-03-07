package pl.edu.dik.tks.repository.game;

import com.mongodb.client.ClientSession;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.dik.tks.model.game.Game;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.inc;

@Repository
public class MongoGameRepository implements GameRepository {

    private final MongoCollection<Game> collection;

    @Autowired
    public MongoGameRepository(MongoDatabase database) {
        this.collection = database.getCollection("game", Game.class);
    }

    public Game save(Game game) {
        collection.insertOne(game);
        return game;
    }

    public Optional<Game> findById(Object id) {
        Bson filter = Filters.eq("_id", id);
        return Optional.ofNullable(collection.find(filter).first());
    }

    public List<Game> findAll() {
        return StreamSupport.stream(collection.find().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Game update(Game updatedEntity) {
        // Extract the ID directly from the object
        Object id = updatedEntity.getId();
        Bson filter = Filters.eq("_id", id);
        return collection.replaceOne(filter, updatedEntity).wasAcknowledged() ? updatedEntity : null;
    }

    public void deleteById(Object id) {
        Bson filter = eq("_id", id);
        collection.deleteOne(filter);
    }

    // Atomic increment to mark as rented
    // https://medium.com/@codersauthority/handling-race-conditions-and-concurrent-resource-updates-in-node-and-mongodb-by-performing-atomic-9f1a902bd5fa
    public boolean markAsRented(ClientSession session, UUID gameId) {
        Bson filter = and(eq("_id", gameId.toString()), eq("rental_status_count", 0)); // Ensure game is not rented
        Bson update = inc("rental_status_count", 1); // Increment rental status count by 1

        Document updatedGame = collection.withDocumentClass(Document.class)
                .findOneAndUpdate(session, filter, update);

        return updatedGame != null; // Returns true if update was successful, false if not
    }

    // Atomic decrement to unmark as rented
    // https://medium.com/@codersauthority/handling-race-conditions-and-concurrent-resource-updates-in-node-and-mongodb-by-performing-atomic-9f1a902bd5fa
    public boolean unmarkAsRented(ClientSession session, UUID gameId) {
        Bson filter = and(eq("_id", gameId.toString()), eq("rental_status_count", 1)); // Ensure game is rented by one renter
        Bson update = inc("rental_status_count", -1); // Decrement rental status count by 1

        Document updatedGame = collection.withDocumentClass(Document.class)
                .findOneAndUpdate(session, filter, update);
        return updatedGame != null; // Returns true if update was successful, false if not
    }
}
