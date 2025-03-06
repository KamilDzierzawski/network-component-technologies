package pl.edu.dik.tks.auth;

import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import pl.edu.dik.tks.auth.dto.LoginRequest;
import pl.edu.dik.tks.auth.dto.RegisterAccountRequest;
import pl.edu.dik.tks.exception.DatabaseException;
import pl.edu.dik.tks.exception.auth.DuplicatedKeyException;
import pl.edu.dik.tks.mapper.AccountMapper;
import pl.edu.dik.tks.model.account.Account;
import pl.edu.dik.tks.repository.account.AccountRepository;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AccountMapper accountMapper;
    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody RegisterAccountRequest registerAccountRequest) throws
            DuplicatedKeyException,
            DatabaseException {
        Account account = accountMapper.toAccount(registerAccountRequest);
        authService.register(account);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getLogin(), loginRequest.getPassword()));

        String token = tokenService.generateToken(authentication);

        return ResponseEntity.ok(token);
    }
}
