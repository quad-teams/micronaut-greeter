package team.quad;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.Message;
import java.util.concurrent.CompletableFuture;
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
  Vertx vertx;

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public CompletionStage<String> sayHello(@DefaultValue("") @QueryParam("name") String name) {
    CompletableFuture<String> completableFuture = new CompletableFuture<>();

    vertx.eventBus().<String>send("say-hello", name, handler -> {
      Message<String> result = handler.result();
      completableFuture.complete(result.body());
    });

    return completableFuture;
  }

}
