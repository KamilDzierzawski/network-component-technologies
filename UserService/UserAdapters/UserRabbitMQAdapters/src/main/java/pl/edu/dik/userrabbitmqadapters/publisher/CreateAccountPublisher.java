package pl.edu.dik.userrabbitmqadapters.publisher;

import pl.edu.dik.userrabbitmqadapters.model.AccountEnt;

public interface CreateAccountPublisher {
    void publish(AccountEnt account);
}
