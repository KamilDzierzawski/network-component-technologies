package pl.edu.dik.application.services.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.edu.dik.ports.infrastructure.account.ReadAccountPort;

@Service
@RequiredArgsConstructor
public class AuthUserDetailsServiceImpl implements UserDetailsService {

    private final ReadAccountPort readAccountPort;

    @Override
    public UserDetails loadUserByUsername(String username) {

        return readAccountPort.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Account not found"));

    }
}
