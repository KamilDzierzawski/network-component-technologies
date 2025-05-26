package pl.edu.dik.rabbitmq.repository.account;

import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.bson.conversions.Bson;
import org.springframework.stereotype.Repository;
import pl.edu.dik.rabbitmq.exception.DuplicatedKeyRepositoryException;
import pl.edu.dik.rabbitmq.model.account.AccountEnt;


import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.regex;

@Getter
@Repository
@Slf4j
public class MongoAccountRepository implements AccountRepository {

    private final MongoCollection<AccountEnt> collection;

    public MongoAccountRepository(MongoDatabase mongoDatabase) {
        this.collection = mongoDatabase.getCollection("account", AccountEnt.class);
        ensureUniqueIndex();
    }

    @Override
    public AccountEnt save(AccountEnt object) throws DuplicatedKeyRepositoryException {
        try {
            object.setId(UUID.randomUUID());
            collection.insertOne(object);
            log.info("Saved account to mongoDB {}", object);
            return object;
        } catch (MongoWriteException e) {
            if (e.getError().getCode() == 11000) {
                throw new DuplicatedKeyRepositoryException("Account with this login already exists");
            } else {
                throw e;
            }
        }
    }

    private void ensureUniqueIndex() {
        IndexOptions options = new IndexOptions().unique(true);
        collection.createIndex(Indexes.ascending("login"), options);
    }

    @Override
    public Optional<AccountEnt> findById(UUID id) {
        Bson filter = Filters.eq("_id", id);
        return Optional.ofNullable(collection.find(filter).first());
    }


    @Override
    public AccountEnt update(AccountEnt updatedAccount) {
        UUID id = updatedAccount.getId();
        Bson filter = Filters.eq("_id", id);
        return collection.replaceOne(filter, updatedAccount).wasAcknowledged() ? updatedAccount : null;
    }


    @Override
    public List<AccountEnt> findAll() {
        return StreamSupport.stream(collection.find().spliterator(), false)
                .collect(Collectors.toList());
    }


    @Override
    public Optional<AccountEnt> findByLogin(String login) {
        Bson filter = eq("login", login);
        return Optional.ofNullable(getCollection().find(filter).first());
    }


    @Override
    public List<AccountEnt> findByMatchingLogin(String loginSubstring) {
        Bson filter = regex("login", loginSubstring, "i");
        return StreamSupport.stream(collection.find(filter).spliterator(), false)
                .collect(Collectors.toList());
    }
}
