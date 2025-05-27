package pl.edu.dik.ports.infrastructure.account;

public interface DeleteAccountPort {
    void deleteByLogin(String login);
}
