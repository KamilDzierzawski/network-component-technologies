package pl.edu.dik.application.services.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.edu.dik.domain.model.account.Account;
import pl.edu.dik.domain.model.account.Role;
import pl.edu.dik.ports.exception.business.AccountNotFoundException;
import pl.edu.dik.ports.exception.business.DuplicatedKeyException;
import pl.edu.dik.ports.exception.business.IncorrectPasswordException;
import pl.edu.dik.ports.infrastructure.auth.CreateAuthPort;
import pl.edu.dik.ports.infrastructure.auth.ReadAuthPort;
import pl.edu.dik.ports._interface.AuthService;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final CreateAuthPort createAuthPort;
    private final PasswordEncoder passwordEncoder;


    @Override
    public Account register(Account account) throws
            DuplicatedKeyException {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        account.setEnable(true);
        account.setRole(Role.CLIENT);
        return createAuthPort.save(account);
    }
}
