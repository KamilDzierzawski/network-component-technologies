package pl.edu.dik.tks.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.dik.tks.exception.business.RentNotFoundException;
import pl.edu.dik.tks.model.rent.Rent;
import pl.edu.dik.tks.repository.rent.InactiveRentRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InactiveRentService {

    private final InactiveRentRepository inactiveRentRepository;

    public Rent findInactiveRentById(UUID id) throws RentNotFoundException {
        return inactiveRentRepository.findById(id)
                .orElseThrow(() -> new RentNotFoundException("Rent with ID " + id + " not found"));
    }

    public List<Rent> getAllInactiveRents() {
        return inactiveRentRepository.findAll();
    }

    public List<Rent> getInactiveRentsByClientId(UUID clientId) {
        return inactiveRentRepository.getRentsByAccountId(clientId);
    }
}
