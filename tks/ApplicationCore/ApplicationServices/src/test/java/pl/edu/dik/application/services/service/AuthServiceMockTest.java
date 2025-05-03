package pl.edu.dik.application.services.service;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pl.edu.dik.domain.model.account.Account;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.edu.dik.domain.model.account.Role;
import pl.edu.dik.ports.exception.business.AccountNotFoundException;
import pl.edu.dik.ports.exception.business.IncorrectPasswordException;
import pl.edu.dik.ports.infrastructure.auth.CreateAuthPort;
import pl.edu.dik.ports.infrastructure.auth.ReadAuthPort;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceMockTest {

    @Mock
    private CreateAuthPort createAuthPort;

    @Mock
    private ReadAuthPort readAuthPort;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthServiceImpl authService;

    @SneakyThrows
    @Test
    void register() {
        Account account = new Account();
        account.setPassword("plainPassword");
        when(passwordEncoder.encode("plainPassword")).thenReturn("encodedPassword");
        when(createAuthPort.save(any(Account.class))).thenReturn(account);

        Account result = authService.register(account);

        assertThat(result)
                .extracting(Account::getPassword, Account::isEnable, Account::getRole)
                .containsExactly("encodedPassword", true, Role.CLIENT);

        verify(createAuthPort, times(1)).save(any(Account.class));
    }
}