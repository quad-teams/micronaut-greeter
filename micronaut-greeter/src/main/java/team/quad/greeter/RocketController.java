package team.quad.greeter;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import javax.inject.Inject;

@Controller
public class RocketController {

  @Inject
  RadioService radio;

  @Get("/greet")
  public String greet() {
    return radio.greet();
  }
}
