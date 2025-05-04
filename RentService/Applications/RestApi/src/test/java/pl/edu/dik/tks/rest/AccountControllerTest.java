package pl.edu.dik.tks.rest;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import pl.edu.dik.tks.TestBaseConfiguration;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class AccountControllerTest extends TestBaseConfiguration {

    @Test
    public void createAccountTest() {

        String login = UUID.randomUUID().toString();

        String id = given()
                .contentType(ContentType.JSON)
                .body("""
                {
                    "login": "%s"
                }
                """.formatted(login))
                .when()
                .post("/accounts")
                .then()
                .statusCode(201)
                .extract()
                .path("id");

        given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/accounts/" + id)
                .then()
                .statusCode(200);
    }


    @Test
    public void findAccountByLoginTest() {

        String login = UUID.randomUUID().toString();

        given()
                .contentType(ContentType.JSON)
                .body("""
                {
                    "login": "%s"
                }
                """.formatted(login))
                .when()
                .post("/accounts")
                .then()
                .statusCode(201);

        given()
                .header("Authorization", "Bearer " + token)
                .queryParam("login", login)
                .when()
                .get("/accounts/by-login")
                .then()
                .statusCode(200);
    }

    @Test
    public void findByMatchingLoginTest() {

        String login = UUID.randomUUID().toString();

        given()
                .contentType(ContentType.JSON)
                .body("""
                {
                    "login": "%s"
                }
                """.formatted(login))
                .when()
                .post("/accounts")
                .then()
                .statusCode(201);

        given()
                .header("Authorization", "Bearer " + token)
                .queryParam("regex", login)
                .when()
                .get("/accounts/search")
                .then()
                .statusCode(200)
                .body("size()", greaterThanOrEqualTo(0));
    }
}
