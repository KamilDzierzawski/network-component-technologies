package pl.edu.dik.userrabbitmq.listener;

import pl.edu.dik.userrabbitmq.model.AccountMessage;

public interface DeleteAccountListener {
    void deleteAccount(AccountMessage accountMessage);
}
