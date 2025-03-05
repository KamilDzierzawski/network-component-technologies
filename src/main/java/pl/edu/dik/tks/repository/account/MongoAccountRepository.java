package pl.edu.dik.tks.repository.account;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import lombok.Getter;
import pl.edu.dik.tks.model.account.Account;

import java.util.List;
import java.util.Optional;

import org.bson.conversions.Bson;
import com.mongodb.client.model.Filters;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Repository;

@Getter
@Repository
public class MongoAccountRepository implements AccountRepository {
    private final MongoCollection<Account> collection;

    public MongoAccountRepository(MongoDatabase mongoDatabase) {
        this.collection = mongoDatabase.getCollection("account", Account.class);
    }

    @Override
    public Optional<Account> findById(Object id) {
        Bson filter = Filters.eq("_id", id.toString());
        return Optional.ofNullable(collection.find(filter).first());
    }

    @Override
    public Account update(Account updatedAccount) {
        // Extract the ID directly from the object
        Object id = updatedAccount.getId();
        Bson filter = Filters.eq("_id", id.toString());
        return collection.replaceOne(filter, updatedAccount).wasAcknowledged() ? updatedAccount : null;
    }

    @Override
    public void deleteById(Object id) {
        Bson filter = Filters.eq("_id", id.toString());
    }

    @Override
    public List<Account> findAll() {
        return StreamSupport.stream(collection.find().spliterator(), false)
                .collect(Collectors.toList());
    }
}
