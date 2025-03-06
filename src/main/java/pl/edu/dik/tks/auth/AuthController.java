package pl.edu.dik.tks.auth;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import pl.edu.dik.tks.auth.dto.AccountResponse;
import pl.edu.dik.tks.auth.dto.LoginRequest;
import pl.edu.dik.tks.auth.dto.RegisterRequest;
import pl.edu.dik.tks.exception.DatabaseException;
import pl.edu.dik.tks.exception.auth.AccountNotFoundException;
import pl.edu.dik.tks.exception.auth.DuplicatedKeyException;
import pl.edu.dik.tks.model.account.Account;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final ModelMapper modelMapper;
    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody RegisterRequest registerRequest) throws
            DuplicatedKeyException,
            DatabaseException {
        Account account = modelMapper.map(registerRequest, Account.class);
        //Account account = accountMapper.toAccount(registerRequest);
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

    @GetMapping("/me")
    public ResponseEntity<AccountResponse> me() throws
            AccountNotFoundException {
        return ResponseEntity.ok(
                modelMapper.map(
                        authService.me(
                                SecurityContextHolder.getContext().getAuthentication().getName()),
                        AccountResponse.class));

    }
}
