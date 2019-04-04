package team.quad.greeter;

import javax.inject.Singleton;

@Singleton
public class RadioService {

  public String greet() {
    return "Hello from Micronaut";
  }
}
