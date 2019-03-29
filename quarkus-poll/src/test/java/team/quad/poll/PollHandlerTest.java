package team.quad.poll;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@QuarkusTest
public class PollHandlerTest {

  @Test
  public void testHelloEndpoint() {
    PollHandler pollHandler = new PollHandler();
    PollRequest request = new PollRequest("tooling");
    PollResponse response = pollHandler.handleRequest(request, null);
    assertThat(response.getMessage(), is("Voted for " + request.getCategory()));
  }

}
