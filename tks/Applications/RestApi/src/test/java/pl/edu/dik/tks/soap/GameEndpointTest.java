package pl.edu.dik.tks.soap;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.junit.jupiter.Testcontainers;
import pl.edu.dik.tks.TksApplication;
import pl.edu.dik.tks.TestContainerConfig;
import pl.edu.dik.soap.config.WebServiceConfig;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = TksApplication.class
)
@Testcontainers
@ContextConfiguration(classes = {TestContainerConfig.class, WebServiceConfig.class})
public class GameEndpointTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setup() {
        // Set the dynamic port for REST-assured
        RestAssured.port = port;
    }

    private String extractGameId(String response) {
        Pattern pattern = Pattern.compile("<ns2:id>(.*?)</ns2:id>");
        Matcher matcher = pattern.matcher(response);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    private String createGame(String name, int pricePerDay, int minPlayers, int maxPlayers) {
        String soapRequest = String.format("""
            <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                              xmlns:gm="http://viewsoap.adapters.soap/games">
                <soapenv:Header/>
                <soapenv:Body>
                    <gm:createGameRequest>
                        <gm:name>%s</gm:name>
                        <gm:pricePerDay>%d</gm:pricePerDay>
                        <gm:minPlayers>%d</gm:minPlayers>
                        <gm:maxPlayers>%d</gm:maxPlayers>
                    </gm:createGameRequest>
                </soapenv:Body>
            </soapenv:Envelope>
            """, name, pricePerDay, minPlayers, maxPlayers);

        String response = given()
                .header("Content-Type", "text/xml; charset=UTF-8")
                .body(soapRequest)
                .when()
                .post("/ws")
                .then()
                .statusCode(200)
                .body(containsString("gameResponse"))
                .extract().body().asString();

        String gameId = extractGameId(response);
        assertNotNull(gameId, "Game UUID should not be null");
        assertThat(gameId).isNotEmpty();
        return gameId;
    }

    @Test
    public void testCreateGame() {
        String gameId = createGame("Monopoly", 15, 2, 6);
    }

    @Test
    public void testGetGameById() {
        String gameId = createGame("Clue", 20, 3, 6);

        String soapRequest = String.format("""
            <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                              xmlns:gm="http://viewsoap.adapters.soap/games">
                <soapenv:Header/>
                <soapenv:Body>
                    <gm:getGameByIdRequest>
                        <gm:id>%s</gm:id>
                    </gm:getGameByIdRequest>
                </soapenv:Body>
            </soapenv:Envelope>
            """, gameId);

        given()
                .header("Content-Type", "text/xml; charset=UTF-8")
                .body(soapRequest)
                .when()
                .post("/ws")
                .then()
                .statusCode(200)
                .body(containsString("gameResponse"));
    }

    @Test
    public void testGetAllGames() {
        createGame("Scrabble", 10, 2, 4);

        String soapRequest = """
            <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                              xmlns:gm="http://viewsoap.adapters.soap/games">
                <soapenv:Header/>
                <soapenv:Body>
                    <gm:getAllGamesRequest/>
                </soapenv:Body>
            </soapenv:Envelope>
            """;

        given()
                .header("Content-Type", "text/xml; charset=UTF-8")
                .body(soapRequest)
                .when()
                .post("/ws")
                .then()
                .statusCode(200)
                .body(containsString("getAllGamesResponse"));
    }

    @Test
    public void testUpdateGame() {
        String gameId = createGame("Risk", 18, 2, 6);

        String soapRequest = String.format("""
            <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                              xmlns:gm="http://viewsoap.adapters.soap/games">
                <soapenv:Header/>
                <soapenv:Body>
                    <gm:updateGameRequest>
                        <gm:id>%s</gm:id>
                        <gm:name>Risk Updated</gm:name>
                        <gm:pricePerDay>22</gm:pricePerDay>
                    </gm:updateGameRequest>
                </soapenv:Body>
            </soapenv:Envelope>
            """, gameId);

        given()
                .header("Content-Type", "text/xml; charset=UTF-8")
                .body(soapRequest)
                .when()
                .post("/ws")
                .then()
                .statusCode(200)
                .body(containsString("gameResponse"));
    }

    @Test
    public void testDeleteGame() {
        String gameId = createGame("Clue", 20, 3, 6);

        String soapRequest = String.format("""
            <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                              xmlns:gm="http://viewsoap.adapters.soap/games">
                <soapenv:Header/>
                <soapenv:Body>
                    <gm:deleteGameRequest>
                        <gm:id>%s</gm:id>
                    </gm:deleteGameRequest>
                </soapenv:Body>
            </soapenv:Envelope>
            """, gameId);

        given()
                .header("Content-Type", "text/xml; charset=UTF-8")
                .body(soapRequest)
                .when()
                .post("/ws")
                .then()
                .statusCode(202);
    }
}