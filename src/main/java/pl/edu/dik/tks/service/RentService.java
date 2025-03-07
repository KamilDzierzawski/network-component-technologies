package pl.edu.dik.tks.service;

import com.mongodb.client.ClientSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.dik.tks.exception.business.AccountNotActiveException;
import pl.edu.dik.tks.exception.business.StartDateBeforeEndDateException;
import pl.edu.dik.tks.model.rent.Rent;
import pl.edu.dik.tks.repository.rent.MongoRentRepository;
import pl.edu.dik.tks.repository.rent.RentRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RentService {

    private final MongoRentRepository mongoRentRepository;

    public Rent createRent(Rent rent) throws StartDateBeforeEndDateException {

        if (rent.getStartDate().isBefore(rent.getEndDate())) {
            throw new StartDateBeforeEndDateException("Start date must be before end date.");
        }

        return mongoRentRepository.save(rent);
    }
}
