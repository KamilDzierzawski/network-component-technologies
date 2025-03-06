package pl.edu.dik.tks.auth;

import com.mongodb.MongoWriteException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.edu.dik.tks.exception.DatabaseException;
import pl.edu.dik.tks.exception.auth.DuplicatedKeyException;
import pl.edu.dik.tks.model.account.Account;
import pl.edu.dik.tks.model.account.Role;

@Service
public class AuthService {

    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(AuthRepository authRepository, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.authRepository = authRepository;
    }

    public void register(Account account) throws
            DuplicatedKeyException,
            DatabaseException {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        account.setRole(Role.CLIENT);
        try {
            authRepository.insert(account);
        } catch (MongoWriteException e) {
            if (e.getError().getCode() == 11000) {
                throw new DuplicatedKeyException("Account with this email already exists");
            } else {
                throw new DatabaseException("Database error");
            }
        }
    }
}
