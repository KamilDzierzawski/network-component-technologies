package pl.edu.dik.tks;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
		"pl.edu.dik"
})
public class UserServiceApplication {

	public static void main(String[] args) {
//		ConnectionFactory factory = new ConnectionFactory();
//		factory.setHost("localhost");
//		factory.setPort(5672);
//		factory.setUsername("root");
//		factory.setPassword("root");
//
//		try (Connection connection = factory.newConnection()) {
//			System.out.println("Connection to RabbitMQ successful!");
//		} catch (Exception e) {
//			System.err.println("Failed to connect to RabbitMQ: " + e.getMessage());
//		}

		//		try {
		SpringApplication.run(UserServiceApplication.class, args);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

	}
}
