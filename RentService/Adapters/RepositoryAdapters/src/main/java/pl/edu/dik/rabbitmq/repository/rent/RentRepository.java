package pl.edu.dik.rabbitmq.repository.rent;

import pl.edu.dik.rabbitmq.exception.*;
import pl.edu.dik.rabbitmq.model.rent.RentEnt;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RentRepository {

    RentEnt save(LocalDate startDate, LocalDate endDate, String login, UUID gameId) throws
            ClientNotAvailableForRentRepositoryException,
            GameNotAvailableForRentRepositoryException;


    Optional<RentEnt> findById(Object id);

    List<RentEnt> findAll();

    RentEnt update(RentEnt rent);

    void deleteById(Object id) throws
            RentNotFoundRepositoryException,
            ClientRentCancellationRepositoryException,
            GameRentCancellationRepositoryException;

    List<RentEnt> getRentsByAccountId(UUID clientId);
}
