package pl.edu.dik.tks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.dik.tks.exception.DatabaseException;
import pl.edu.dik.tks.exception.auth.AccountNotFoundException;
import pl.edu.dik.tks.model.account.Account;
import pl.edu.dik.tks.repository.account.AccountRepository;

import java.util.List;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account findAccountById(Object id) {
        return accountRepository.findById(id).orElse(null);
    }

    public List<Account> findAllAccounts() throws DatabaseException {
        try {
            return accountRepository.findAll();
        } catch (Exception e) {
            throw new DatabaseException("Database error");
        }
    }

    public Account updateAccount(Account account) throws DatabaseException, AccountNotFoundException {
        try {
            Account foundAccount = accountRepository.findById(account.getId())
                    .orElseThrow(() -> new AccountNotFoundException("Account with ID " + account.getId() + " not found"));

            if (!account.getLogin().equals(foundAccount.getLogin())) {
                foundAccount.setLogin(account.getLogin());
            }
            if (!account.getPassword().equals(foundAccount.getPassword())) {
                foundAccount.setPassword(account.getPassword());
            }

            return accountRepository.update(foundAccount);
        } catch (AccountNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new DatabaseException("Database error");
        }
    }

    //TODO: CHYBA NIE MOZNA USUWAC KONTA Z WYPOZYCZENIAMI
    public void deleteAccountById(Object id) {
        accountRepository.deleteById(id);
    }
}
