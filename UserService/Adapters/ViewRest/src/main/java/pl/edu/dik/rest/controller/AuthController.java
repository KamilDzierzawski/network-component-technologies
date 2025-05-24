package pl.edu.dik.rest.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import pl.edu.dik.domain.model.account.Account;
import pl.edu.dik.ports._interface.AccountService;
import pl.edu.dik.ports.exception.business.AccountNotFoundException;
import pl.edu.dik.ports.exception.business.DuplicatedKeyException;
import pl.edu.dik.rest.auth.TokenService;
import pl.edu.dik.rest.model.auth.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final ModelMapper modelMapper;
    private final AccountService accountService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest registerRequest) throws DuplicatedKeyException {
        Account account = modelMapper.map(registerRequest, Account.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(accountService.create(account), RegisterResponse.class));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) throws AccountNotFoundException {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getLogin(), loginRequest.getPassword()));
        Account account = accountService.findByLogin(loginRequest.getLogin());
        String token = tokenService.generateToken(account);
        return ResponseEntity.ok(token);
    }
}
