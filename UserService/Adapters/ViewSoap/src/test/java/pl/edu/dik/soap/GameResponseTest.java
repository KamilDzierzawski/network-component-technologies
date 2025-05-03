package pl.edu.dik.soap;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import pl.edu.dik.soap.model.game.GameResponse;

import java.util.UUID;

public class GameResponseTest {

    private final UUID gameId = UUID.randomUUID();
    private final String name = "Test Game";
    private final int pricePerDay = 100;
    private final int minPlayers = 1;
    private final int maxPlayers = 4;
    private final int rentalStatusCount = 0;

    @Test
    void GameResponseSetterAndGetterTest() {
        GameResponse gameResponse = new GameResponse();

        gameResponse.setId(gameId);
        gameResponse.setName(name);
        gameResponse.setPricePerDay(pricePerDay);
        gameResponse.setMinPlayers(minPlayers);
        gameResponse.setMaxPlayers(maxPlayers);
        gameResponse.setRentalStatusCount(rentalStatusCount);

        assertEquals(gameId, gameResponse.getId());
        assertEquals(name, gameResponse.getName());
        assertEquals(pricePerDay, gameResponse.getPricePerDay());
        assertEquals(minPlayers, gameResponse.getMinPlayers());
        assertEquals(maxPlayers, gameResponse.getMaxPlayers());
        assertEquals(rentalStatusCount, gameResponse.getRentalStatusCount());
    }

}
