package team.quad;

import io.quarkus.vertx.ConsumeEvent;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class GreetingService {

  @ConsumeEvent("say-hello")
  public CompletionStage<String> sayHello(String name) {
    return CompletableFuture.supplyAsync(() -> "Hello " + name);
  }

}
