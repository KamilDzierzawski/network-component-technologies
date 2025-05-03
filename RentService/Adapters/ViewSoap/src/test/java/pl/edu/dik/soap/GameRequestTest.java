package pl.edu.dik.soap;

import org.junit.jupiter.api.Test;
import pl.edu.dik.soap.model.game.*;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameRequestTest {

    @Test
    public void CreateGameSettersAndGettersTest() {
        CreateGameRequest request = new CreateGameRequest();

        String name = "Chess";
        int pricePerDay = 15;
        int minPlayers = 2;
        int maxPlayers = 4;

        request.setName(name);
        request.setPricePerDay(pricePerDay);
        request.setMinPlayers(minPlayers);
        request.setMaxPlayers(maxPlayers);

        assertEquals(name, request.getName());
        assertEquals(pricePerDay, request.getPricePerDay());
        assertEquals(minPlayers, request.getMinPlayers());
        assertEquals(maxPlayers, request.getMaxPlayers());
    }

    @Test
    void DeleteGameSettersAndGettersTest() {
        DeleteGameRequest request = new DeleteGameRequest();

        UUID testId = UUID.randomUUID();
        request.setId(testId);

        assertEquals(testId, request.getId());
    }

    @Test
    void GetGameByIdSettersAndGettersTest() {
        GetGameByIdRequest request = new GetGameByIdRequest();

        UUID testId = UUID.randomUUID();
        request.setId(testId);

        assertEquals(testId, request.getId());
    }

    @Test
    void UpdateGameSettersAndGettersTest() {
        UpdateGameRequest request = new UpdateGameRequest();

        UUID testId = UUID.randomUUID();
        String testName = "Catan";
        int testPrice = 25;

        request.setId(testId);
        request.setName(testName);
        request.setPricePerDay(testPrice);

        assertEquals(testId, request.getId());
        assertEquals(testName, request.getName());
        assertEquals(testPrice, request.getPricePerDay());
    }
}
