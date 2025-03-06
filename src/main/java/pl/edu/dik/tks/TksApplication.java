package pl.edu.dik.tks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import pl.edu.dik.tks.auth.AuthRepository;
import pl.edu.dik.tks.auth.AuthService;
import pl.edu.dik.tks.config.SecurityConfig;
import pl.edu.dik.tks.exception.DatabaseException;
import pl.edu.dik.tks.exception.auth.DuplicatedKeyException;
import pl.edu.dik.tks.model.account.Account;
import pl.edu.dik.tks.model.account.Role;

import java.util.UUID;

@SpringBootApplication
public class TksApplication {

	public static void main(String[] args) throws DuplicatedKeyException, DatabaseException {
		SpringApplication.run(TksApplication.class, args);

//		ConfigurableApplicationContext context = SpringApplication.run(TksApplication.class, args);
//
//		//SecurityConfig securityConfig = new SecurityConfig();
////		System.out.println(securityConfig.getPrivateKey().toString());
////		System.out.println(securityConfig.getPublicKey().toString());
//
//		String mongoUri = context.getBean("mongoConnectionUri", String.class);
//		String dbName = context.getBean("mongoDatabaseName", String.class);
//
//		AuthRepository authRepository = context.getBean("authRepository", AuthRepository.class);
//		AuthService authService = new AuthService(authRepository, context.getBean("passwordEncoder", org.springframework.security.crypto.password.PasswordEncoder.class));
//
//		Account account = new Account(UUID.randomUUID(), "Maciek", "Kowalski", Role.ADMIN, true, "EloElo123", "eloo123");
//
//		authService.register(account);
//
//		// Print the values
//		System.out.println("MongoDB Connection URI: " + mongoUri);
//		System.out.println("MongoDB Database Name: " + dbName);

	}

}
