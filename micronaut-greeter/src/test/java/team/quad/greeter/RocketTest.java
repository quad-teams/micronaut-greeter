package team.quad.greeter;

import io.micronaut.test.annotation.MicronautTest;
import javax.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
public class RocketTest {

  @Inject
  private RadioClient client;

  @Test
  public void test_greeter_response_is_as_expected() {

    String response = client.greet();
    assertEquals(response, "Hello from Micronaut");
  }
}
