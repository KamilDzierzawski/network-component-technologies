package pl.edu.dik.tks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
		"pl.edu.dik"
})
public class UserServiceApplication {

	public static void main(String[] args) {
//		try {
			SpringApplication.run(UserServiceApplication.class, args);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

	}
}
