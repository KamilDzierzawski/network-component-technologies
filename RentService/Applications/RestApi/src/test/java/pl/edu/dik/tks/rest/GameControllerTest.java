package pl.edu.dik.tks.rest;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.junit.jupiter.Testcontainers;
import pl.edu.dik.adapters.exception.DuplicatedKeyRepositoryException;
import pl.edu.dik.adapters.model.account.AccountEnt;
import pl.edu.dik.adapters.model.account.RoleEnt;
import pl.edu.dik.adapters.repository.auth.AuthRepository;
import pl.edu.dik.domain.model.account.Account;
import pl.edu.dik.rest.config.SecurityConfig;
import pl.edu.dik.rest.config.TokenService;
import pl.edu.dik.tks.TestBaseConfiguration;
import pl.edu.dik.tks.TestContainerConfig;
import pl.edu.dik.tks.RentServiceApplication;

import java.util.UUID;

import static io.restassured.RestAssured.given;


public class GameControllerTest extends TestBaseConfiguration {

    @Test
    public void createGameTest() {
        String id = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body("""
                {
                    "name": "Chess",
                    "pricePerDay": 5,
                    "minPlayers": 2,
                    "maxPlayers": 4
                }
                """)
                .when()
                .post("/games")
                .then()
                .statusCode(201)
                .extract()
                .path("id");

        given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/games/" + id)
                .then()
                .statusCode(200);
    }

    @Test
    public void createGameWithInvalidMinPlayersTest() {
        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body("""
            {
                "name": "Chess",
                "pricePerDay": 5,
                "minPlayers": 0,
                "maxPlayers": 4
            }
            """)
                .when()
                .post("/games")
                .then()
                .statusCode(400);
    }

    @Test
    public void updateGameTest() {
        // Create a new game to later update.
        String gameId = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body("""
                {
                    "name": "Chess",
                    "pricePerDay": 5,
                    "minPlayers": 2,
                    "maxPlayers": 4
                }
                """)
                .when()
                .post("/games")
                .then()
                .statusCode(201)
                .extract()
                .path("id");

        // Create update JSON with the new details.
        String updateGameJson = """
            {
                "id": "%s",
                "name": "Updated Chess",
                "pricePerDay": 7,
                "minPlayers": 2,
                "maxPlayers": 5
            }
            """.formatted(gameId);

        // Submit the update request.
        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body(updateGameJson)
                .when()
                .put("/games")
                .then()
                .statusCode(200);
    }


}
