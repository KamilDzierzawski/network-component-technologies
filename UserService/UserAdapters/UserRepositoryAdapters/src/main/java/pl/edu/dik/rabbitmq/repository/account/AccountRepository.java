package pl.edu.dik.rabbitmq.repository.account;


import pl.edu.dik.rabbitmq.exception.DuplicatedKeyRepositoryException;
import pl.edu.dik.rabbitmq.model.account.AccountEnt;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountRepository {

    AccountEnt save(AccountEnt object) throws DuplicatedKeyRepositoryException;

    Optional<AccountEnt> findById(UUID id);

    AccountEnt update(AccountEnt updatedAccount);

    List<AccountEnt> findAll();

    Optional<AccountEnt> findByLogin(String login);

    List<AccountEnt> findByMatchingLogin(String loginSubstring);

    void deleteByLogin(String login);
}

