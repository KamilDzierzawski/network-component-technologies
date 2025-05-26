package pl.edu.dik.rabbitmq.exception;

public class DuplicatedKeyRepositoryException extends RepositoryException {
    public DuplicatedKeyRepositoryException(String message) {
        super(message);
    }
}
