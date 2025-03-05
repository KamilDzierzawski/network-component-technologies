package pl.edu.dik.tks.repository.account;

import pl.edu.dik.tks.model.account.Account;

import java.util.List;
import java.util.Optional;

public interface AccountRepository {

    Optional<Account> findById(Object id);

    Account update(Account updatedAccount);

    void deleteById(Object id);

    List<Account> findAll();
}

