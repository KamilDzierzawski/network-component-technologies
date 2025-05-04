package pl.edu.dik.tks.rest;


import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import pl.edu.dik.tks.TestBaseConfiguration;

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
