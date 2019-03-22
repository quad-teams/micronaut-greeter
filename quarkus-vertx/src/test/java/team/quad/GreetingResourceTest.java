package team.quad;

import io.quarkus.test.junit.QuarkusTest;
import java.util.UUID;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class GreetingResourceTest {

  @Test
  public void when_sendingEmptyName_then_returnHelloOnly() {
    given()
      .when().get("/hello")
      .then()
        .statusCode(200)
        .body(is("Hello "));
  }

  @Test
  public void when_sendingRandomName_then_returnHelloPlusRandomName() {
    String uuid = UUID.randomUUID().toString();
    given()
      .queryParam("name", uuid)
      .when().get("/hello")
      .then()
        .statusCode(200)
        .body(is("Hello " + uuid));
  }

}
