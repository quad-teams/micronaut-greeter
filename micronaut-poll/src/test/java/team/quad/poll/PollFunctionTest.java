package team.quad.poll;

import io.micronaut.context.ApplicationContext;
import io.micronaut.runtime.server.EmbeddedServer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PollFunctionTest {

  @Test
  public void testFunction() {
    EmbeddedServer server = ApplicationContext.run(EmbeddedServer.class);

    PollClient client = server.getApplicationContext().getBean(PollClient.class);
    PollRequest request = new PollRequest("tooling");
    assertEquals(client.apply(request), "Voted for " + request.getCategory());
    server.stop();
  }
}
