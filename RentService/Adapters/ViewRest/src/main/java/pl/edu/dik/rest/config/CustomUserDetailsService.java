package pl.edu.dik.rest.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.edu.dik.domain.model.account.Account;
import pl.edu.dik.ports._interface.AccountService;
import pl.edu.dik.ports.exception.business.AccountNotFoundException;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account;
        try {
            account = accountService.findByLogin(username);
        } catch (AccountNotFoundException e) {
            throw new UsernameNotFoundException("Account not found");
        }
        return org.springframework.security.core.userdetails.User.builder()
                .username(account.getLogin())
                .roles()
                .build();
    }
}
