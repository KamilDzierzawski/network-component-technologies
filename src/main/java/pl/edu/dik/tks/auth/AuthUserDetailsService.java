package pl.edu.dik.tks.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;
import pl.edu.dik.tks.model.account.Account;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
@RequiredArgsConstructor
public class AuthUserDetailsService implements UserDetailsService {
    private final AuthRepository authRepository;

    @Override
    public AuthUserDetails loadUserByUsername(String username) {
        Account account = authRepository.findByLogin(username).orElse(null);
        if (account == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new AuthUserDetails(account);
    }
}
