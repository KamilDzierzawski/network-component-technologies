package pl.edu.dik.tks.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;
import pl.edu.dik.tks.model.account.Account;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthUserDetailsService implements UserDetailsService {
    private final AuthRepository authRepository;

    @Override
    public AuthUserDetails loadUserByUsername(String username) {
        log.debug("Attempting to load user with username: {}", username);

        try {
            Account account = authRepository.findByLogin(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

            log.debug("Found account: {}", account.getLogin());
            return new AuthUserDetails(account);

        } catch (Exception e) {
            log.error("Error loading user {}: {}", username, e.getMessage());
            throw e;
        }
    }
}
