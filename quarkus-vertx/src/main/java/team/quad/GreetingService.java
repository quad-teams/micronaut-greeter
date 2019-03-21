package team.quad;

import io.quarkus.vertx.ConsumeEvent;
import io.vertx.core.eventbus.Message;

public class GreetingService {

  @ConsumeEvent("say-hello")
  public void sayHello(Message<String> message) {
    message.reply("Hello " + message.body());
  }

}
