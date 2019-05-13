package team.quad.greeter;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;

@Client("/")
public interface RadioClient {

  @Get("/greet")
  String greet();
}
