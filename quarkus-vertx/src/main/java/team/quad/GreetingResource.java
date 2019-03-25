package team.quad;

import io.vertx.axle.core.eventbus.EventBus;
import io.vertx.axle.core.eventbus.Message;
import java.util.concurrent.CompletionStage;
import javax.inject.Inject;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

  @Inject
  EventBus eventBus;

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public CompletionStage<String> sayHello(@DefaultValue("") @QueryParam("name") String name) {
    return eventBus.<String>send("say-hello", name)
      .thenApply(Message::body);
  }

}
