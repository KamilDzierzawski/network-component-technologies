package pl.edu.dik.application.services.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.dik.domain.model.account.Account;
import pl.edu.dik.ports.exception.business.AccountNotFoundException;
import pl.edu.dik.ports.exception.business.DuplicatedKeyException;
import pl.edu.dik.ports.infrastructure.account.CreateAccountPort;
import pl.edu.dik.ports.infrastructure.account.ReadAccountPort;
import pl.edu.dik.ports._interface.AccountService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final CreateAccountPort createAccountPort;
    private final ReadAccountPort readAccountPort;

    @Override
    public Account createAccount(Account account) throws
            DuplicatedKeyException {
        return createAccountPort.save(account);
    }

    @Override
    public Account findAccountById(UUID id) throws AccountNotFoundException {
        return readAccountPort.findById(id).orElseThrow(() -> new AccountNotFoundException("Account with ID " + id + " not found"));
    }

    @Override
    public List<Account> findAllAccounts() {
        return readAccountPort.findAll();
    }

    @Override
    public Account findByLogin(String login) throws AccountNotFoundException {
            return readAccountPort.findByLogin(login)
                    .orElseThrow(() -> new AccountNotFoundException("Account with login " + login + " not found"));

    }

    @Override
    public List<Account> findByMatchingLogin(String regex) {
        return readAccountPort.findByMatchingLogin(regex);
    }


    @Override
    public Account me(String login) throws
            AccountNotFoundException {
        return readAccountPort.findByLogin(login)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));
    }

}
