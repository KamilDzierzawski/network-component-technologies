package pl.edu.dik.userrabbitmq.publisher;

import pl.edu.dik.userrabbitmq.model.AccountMessage;

public interface CreateAccountPublisher {
    void publish(AccountMessage account);
}
