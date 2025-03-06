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
        try {
            Account account = authRepository.findByLogin(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

            return new AuthUserDetails(account);

        } catch (Exception e) {
            throw e;
        }
    }
}
