package pl.edu.dik.tks.repository.rent;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import jdk.jfr.Registered;
import org.bson.conversions.Bson;
import org.springframework.stereotype.Repository;
import pl.edu.dik.tks.model.game.Game;
import pl.edu.dik.tks.model.rent.Rent;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.mongodb.client.model.Filters.eq;

@Repository
public class MongoRentRepository implements RentRepository {
    private final MongoCollection<Rent> collection;

    public MongoRentRepository(MongoDatabase database) {
        this.collection = database.getCollection("rent", Rent.class);
    }

    @Override
    public Rent save(Rent rent) {
        collection.insertOne(rent);
        return rent;
    }

    @Override
    public Optional<Rent> findById(Object id) {
        Bson filter = Filters.eq("_id", id.toString());
        return Optional.ofNullable(collection.find(filter).first());
    }

    @Override
    public List<Rent> findAll() {
        return StreamSupport.stream(collection.find().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Rent update(Rent updatedEntity) {
        Object id = updatedEntity.getId();
        Bson filter = Filters.eq("_id", id.toString());
        return collection.replaceOne(filter, updatedEntity).wasAcknowledged() ? updatedEntity : null;
    }

//    @Override
//    public void deleteById(Object id) {
//        Bson filter = eq("_id", id.toString());
//        collection.deleteOne(filter);
//    }

    public boolean isGameRented(Object id) {
        Bson filter = Filters.eq("game._id", id.toString());
        return collection.find(filter).first() != null;
    }

    // Retrieve all active rents for a specific client
//    public List<Rent> getActiveRentsByClientId(UUID clientId) {
//        Bson filter = Filters.eq("client._id", clientId.toString());
//        return findAll(filter);
//    }
}
