package pl.edu.dik.adapters.aggregate;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import pl.edu.dik.adapters.exception.DuplicatedKeyRepositoryException;
import pl.edu.dik.adapters.model.account.AccountEnt;
import pl.edu.dik.adapters.model.account.RoleEnt;
import pl.edu.dik.adapters.repository.account.AccountRepository;
import pl.edu.dik.domain.model.account.Account;
import pl.edu.dik.domain.model.account.Role;
import pl.edu.dik.ports.exception.business.DuplicatedKeyException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountRepositoryAdapterMockTest {

    @Mock
    private AccountRepository accountRepository;
    private AccountRepositoryAdapter accountRepositoryAdapter;

    private AccountEnt accountEnt;
    private Account account;

    @BeforeEach
    void setUp() {
        accountRepositoryAdapter = new AccountRepositoryAdapter(accountRepository, new ModelMapper());
        UUID accountId = UUID.randomUUID();
        accountEnt = new AccountEnt(accountId, "Test", "User", RoleEnt.CLIENT, true, "login", "password", 0);
        account = new Account(accountId, "Test", "User", Role.CLIENT, true, "login", "password", 0);
    }

    @Test
    void update() {
        when(accountRepository.update(accountEnt)).thenReturn(accountEnt);

        Account result = accountRepositoryAdapter.update(account);

        assertThat(result)
                .usingRecursiveComparison()
                .isEqualTo(account);

        verify(accountRepository, times(1)).update(accountEnt);
    }

    @Test
    void findByIdTest() {
        when(accountRepository.findById(account.getId())).thenReturn(Optional.ofNullable(accountEnt));

        Optional<Account> result = accountRepositoryAdapter.findById(account.getId());

        assertThat(result)
                .isPresent()
                .get()
                .usingRecursiveComparison()
                .isEqualTo(account);

        verify(accountRepository, times(1)).findById(account.getId());
    }

    @Test
    void findByLogin() {
        when(accountRepository.findByLogin(account.getLogin())).thenReturn(Optional.ofNullable(accountEnt));

        Optional<Account> result = accountRepositoryAdapter.findByLogin(account.getLogin());

        assertThat(result)
                .isPresent()
                .get()
                .usingRecursiveComparison()
                .isEqualTo(account);

        verify(accountRepository, times(1)).findByLogin(account.getLogin());
    }

    @Test
    void findByMatchingLogin() {
        when(accountRepository.findByMatchingLogin(account.getLogin())).thenReturn(List.of(accountEnt));

        var result = accountRepositoryAdapter.findByMatchingLogin(account.getLogin());

        assertThat(result)
                .usingRecursiveComparison()
                .isEqualTo(List.of(account));

        verify(accountRepository, times(1)).findByMatchingLogin(account.getLogin());
    }

    @SneakyThrows
    @Test
    void saveTest() {
        when(accountRepository.save(accountEnt)).thenReturn(accountEnt);

        Account result = accountRepositoryAdapter.save(account);

        assertThat(result)
                .usingRecursiveComparison()
                .isEqualTo(account);

        verify(accountRepository, times(1)).save(accountEnt);
    }

    @SneakyThrows
    @Test
    void saveDuplicatedKeyExceptionTest() {
        when(accountRepository.save(accountEnt)).thenThrow(new DuplicatedKeyRepositoryException("Duplicated key"));

        assertThatThrownBy(() -> accountRepositoryAdapter.save(account))
                .isInstanceOf(DuplicatedKeyException.class)
                .hasMessage("Duplicated key");

        verify(accountRepository, times(1)).save(accountEnt);
    }

    @Test
    void findByLoginTest() {
        when(accountRepository.findByLogin("login")).thenReturn(Optional.ofNullable(accountEnt));

        Optional<Account> result = accountRepositoryAdapter.findByLogin("login");

        assertThat(result)
                .isPresent()
                .get()
                .usingRecursiveComparison()
                .isEqualTo(account);

        verify(accountRepository, times(1)).findByLogin("login");
    }
}