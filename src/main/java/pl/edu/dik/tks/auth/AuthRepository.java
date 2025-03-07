package pl.edu.dik.tks.auth;

import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.FindOneAndReplaceOptions;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import com.mongodb.client.model.ReturnDocument;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.dik.tks.exception.DatabaseException;
import pl.edu.dik.tks.exception.auth.DuplicatedKeyException;
import pl.edu.dik.tks.model.account.Account;

import static com.mongodb.client.model.Filters.*;
import java.util.Optional;
import java.util.UUID;

@Repository
public class AuthRepository {

    private final MongoCollection<Account> collection;

    @Autowired
    public AuthRepository(MongoDatabase database) {
        this.collection = database.getCollection("account", Account.class);
        ensureUniqueIndex();
    }

    // TODO: Czy nie powinno zwracac obirktu a nie void?
    public Account save(Account object) throws DuplicatedKeyException, DatabaseException {
        try {
            collection.insertOne(object);
            return object;
        } catch (MongoWriteException e) {
            if (e.getError().getCode() == 11000) {
                throw new DuplicatedKeyException("Account with this login already exists");
            } else {
                throw new DatabaseException("Database error");
            }
        }
    }

    public Optional<Account> findByLogin(String login) {
        Bson filter = eq("login", login);
        return Optional.ofNullable(collection.find(filter).first());
    }

    private void ensureUniqueIndex() {
        IndexOptions options = new IndexOptions().unique(true);
        collection.createIndex(Indexes.ascending("login"), options);
    }
}
