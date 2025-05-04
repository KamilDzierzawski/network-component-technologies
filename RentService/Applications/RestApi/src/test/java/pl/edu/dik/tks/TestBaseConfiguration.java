package pl.edu.dik.tks;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.junit.jupiter.Testcontainers;
import pl.edu.dik.adapters.exception.DuplicatedKeyRepositoryException;
import pl.edu.dik.adapters.model.account.AccountEnt;
import pl.edu.dik.adapters.model.account.RoleEnt;
import pl.edu.dik.adapters.repository.auth.AuthRepository;
import pl.edu.dik.domain.model.account.Account;
import pl.edu.dik.rest.config.SecurityConfig;
import pl.edu.dik.rest.config.TokenService;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;
import java.util.stream.Collectors;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = RentServiceApplication.class
)
@Testcontainers
@ContextConfiguration(classes = {SecurityConfig.class, TestContainerConfig.class})
public class TestBaseConfiguration {

    @LocalServerPort
    private int port;
    protected String token;
    private final ModelMapper modelMapper = new ModelMapper();
    @Autowired
    private AuthRepository authRepository;
    @Autowired
    private TokenService tokenService;


    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
        RestAssured.basePath = "/api";
        AccountEnt account = new AccountEnt(null, "Maciek", "Kowalski", RoleEnt.EMPLOYEE, true, UUID.randomUUID().toString(), "P@ssw0rd", 0);
        try {
            authRepository.save(account);
        } catch (DuplicatedKeyRepositoryException e) {
            throw new RuntimeException(e);
        }
        String scope = "ROLE_CLIENT";
        token = tokenService.generateToken(modelMapper.map(account, Account.class), scope);
    }
}
