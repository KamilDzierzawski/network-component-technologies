package pl.edu.dik.ports._interface;

import pl.edu.dik.domain.model.account.Account;
import pl.edu.dik.ports.exception.business.AccountNotFoundException;
import pl.edu.dik.ports.exception.business.DuplicatedKeyException;
import pl.edu.dik.ports.exception.business.IncorrectPasswordException;

import java.util.List;
import java.util.UUID;

public interface AccountService {

    Account createAccount(Account account) throws
            DuplicatedKeyException;

    Account findAccountById(UUID id) throws AccountNotFoundException;

    List<Account> findAllAccounts();

    Account findByLogin(String login) throws AccountNotFoundException;

    List<Account> findByMatchingLogin(String regex);

    Account me(String login) throws AccountNotFoundException;
}
