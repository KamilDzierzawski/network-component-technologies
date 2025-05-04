package pl.edu.dik.ports.infrastructure.account;

import pl.edu.dik.domain.model.account.Account;
import pl.edu.dik.ports.exception.business.DuplicatedKeyException;

public interface CreateAccountPort {

    Account save(Account account) throws DuplicatedKeyException;
}
