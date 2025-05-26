package pl.edu.dik.rabbitmq.exception;

public class GameNotAvailableForRentRepositoryException extends RepositoryException {
    public GameNotAvailableForRentRepositoryException(String message) {
        super(message);
    }
}
