package pl.edu.dik.adapters.aggregate;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pl.edu.dik.adapters.exception.DuplicatedKeyRepositoryException;
import pl.edu.dik.adapters.model.account.AccountEnt;
import pl.edu.dik.adapters.repository.account.AccountRepository;
import pl.edu.dik.domain.model.account.Account;
import pl.edu.dik.ports.exception.business.DuplicatedKeyException;
import pl.edu.dik.ports.infrastructure.CreateAccountPort;
import pl.edu.dik.ports.infrastructure.account.ReadAccountPort;
import pl.edu.dik.ports.infrastructure.account.UpdateAccountPort;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class AccountRepositoryAdapter implements ReadAccountPort, UpdateAccountPort, CreateAccountPort {

    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;

    @Override
    public Account save(Account object) throws DuplicatedKeyException {
        try {
            return modelMapper.map(accountRepository.save(modelMapper.map(object, AccountEnt.class)), Account.class);
        } catch (DuplicatedKeyRepositoryException e) {
            throw new DuplicatedKeyException(e.getMessage());
        }
    }

    @Override
    public Account update(Account updatedAccount) {
        return modelMapper.map(accountRepository.update(modelMapper.map(updatedAccount, AccountEnt.class)), Account.class);
    }

    @Override
    public Optional<Account> findById(UUID id) {
        return Optional.ofNullable(modelMapper.map(accountRepository.findById(id), Account.class));
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll().stream().map(gameAccount -> modelMapper.map(gameAccount, Account.class)).collect(Collectors.toList());
    }

    @Override
    public Optional<Account> findByLogin(String login) {
        return Optional.ofNullable(modelMapper.map(accountRepository.findByLogin(login), Account.class));
    }


    @Override
    public List<Account> findByMatchingLogin(String loginSubstring) {
        return accountRepository.findByMatchingLogin(loginSubstring).stream().map(gameAccount -> modelMapper.map(gameAccount, Account.class)).collect(Collectors.toList());
    }
}
