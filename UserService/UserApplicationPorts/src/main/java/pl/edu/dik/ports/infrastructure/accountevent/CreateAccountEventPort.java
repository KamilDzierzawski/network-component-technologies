package pl.edu.dik.ports.infrastructure.accountevent;

import pl.edu.dik.domain.model.account.Account;

public interface CreateAccountEventPort {
    void publish(Account object);
}
