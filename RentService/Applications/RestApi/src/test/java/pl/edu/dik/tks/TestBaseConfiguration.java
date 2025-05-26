package pl.edu.dik.tks;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.junit.jupiter.Testcontainers;
import pl.edu.dik.rabbitmq.exception.DuplicatedKeyRepositoryException;
import pl.edu.dik.rabbitmq.model.account.AccountEnt;
import pl.edu.dik.rabbitmq.repository.account.AccountRepository;
import pl.edu.dik.domain.model.account.Account;
import pl.edu.dik.rest.config.SecurityConfig;
import pl.edu.dik.rest.config.TokenService;

import java.util.UUID;

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
    private AccountRepository accountRepository;
    @Autowired
    private TokenService tokenService;


    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
        RestAssured.basePath = "/api";
        String username = UUID.randomUUID().toString();
        AccountEnt account = new AccountEnt(null, username, 0);
        try {
            accountRepository.save(account);
        } catch (DuplicatedKeyRepositoryException e) {
            throw new RuntimeException(e);
        }
        String scope = "ROLE_CLIENT";
        token = tokenService.generateToken(modelMapper.map(account, Account.class), scope);
    }
}
