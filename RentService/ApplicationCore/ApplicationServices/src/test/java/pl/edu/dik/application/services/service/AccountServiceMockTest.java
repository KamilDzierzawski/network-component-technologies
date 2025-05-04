package pl.edu.dik.application.services.service;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.dik.domain.model.account.Account;
import pl.edu.dik.domain.model.account.Role;
import pl.edu.dik.ports._interface.AccountService;
import pl.edu.dik.ports.exception.business.AccountNotFoundException;
import pl.edu.dik.ports.infrastructure.account.CreateAccountPort;
import pl.edu.dik.ports.infrastructure.account.ReadAccountPort;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceMockTest {

    @Mock
    private ReadAccountPort readAccountPort;
    @Mock
    private CreateAccountPort createAccountPort;

    private AccountService accountService;

    private UUID accountId;
    private Account account;

    @BeforeEach
    void setUp() {
        accountService = new AccountServiceImpl(createAccountPort, readAccountPort);

        accountId = UUID.randomUUID();
        account = new Account(accountId, "firstname", "lastName", Role.CLIENT, true, "login", "password", 0);
    }

    @SneakyThrows
    @Test
    void findAccountByIdTest() {
        when(readAccountPort.findById(accountId)).thenReturn(Optional.ofNullable(account));

        assertThat(accountService.findAccountById(accountId))
                .isEqualTo(account);

        verify(readAccountPort, times(1)).findById(accountId);
    }

    @Test
    void findAccountByIdNotFoundTest() {
        when(readAccountPort.findById(accountId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> accountService.findAccountById(accountId))
                .isInstanceOf(AccountNotFoundException.class);

        verify(readAccountPort, times(1)).findById(accountId);
    }


    @SneakyThrows
    @Test
    void findByLoginTest() {
        when(readAccountPort.findByLogin("login")).thenReturn(Optional.ofNullable(account));

        Account result = accountService.findByLogin("login");

        assertThat(result)
                .isEqualTo(account);

        verify(readAccountPort, times(1)).findByLogin("login");
    }

    @Test
    void findByLoginNotFoundTest() {
        when(readAccountPort.findByLogin("login")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> accountService.findByLogin("login"))
                .isInstanceOf(AccountNotFoundException.class);

        verify(readAccountPort, times(1)).findByLogin("login");
    }

    @Test
    void findByMatchingLoginTest() {
        when(readAccountPort.findByMatchingLogin("login")).thenReturn(List.of(account));

        assertThat(accountService.findByMatchingLogin("login"))
                .isNotNull();

        verify(readAccountPort, times(1)).findByMatchingLogin("login");
    }


    @SneakyThrows
    @Test
    void me() {
        Account account = new Account();
        when(readAccountPort.findByLogin("userLogin")).thenReturn(Optional.of(account));

        Account result = accountService.me("userLogin");

        assertEquals(account, result);
        verify(readAccountPort, times(1)).findByLogin("userLogin");
    }

    @Test
    void meAccountNotFound() {
        when(readAccountPort.findByLogin("nonexistentUser")).thenReturn(Optional.empty());

        assertThrows(AccountNotFoundException.class, () -> accountService.me("nonexistentUser"));
        verify(readAccountPort, times(1)).findByLogin("nonexistentUser");
    }
}