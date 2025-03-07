package pl.edu.dik.tks.repository.rent;

import pl.edu.dik.tks.model.rent.Rent;

import java.util.List;
import java.util.Optional;

public interface RentRepository {
    Rent insert(Rent rent);

    Optional<Rent> findById(Object id);

    List<Rent> findAll();

    Rent update(Rent rent);

    void deleteById(Object id);
}
