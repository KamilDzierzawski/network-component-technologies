package pl.edu.dik.userrabbitmqadapters.publisher;

import org.springframework.stereotype.Repository;
import pl.edu.dik.userrabbitmqadapters.model.AccountEnt;

@Repository
public class RabbitMQCreateAccountPublisher implements CreateAccountPublisher {
    @Override
    public void publish(AccountEnt account) {
        System.out.println("Publishing account creation event for: " + account.getLogin());
    }
}
