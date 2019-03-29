package team.quad.poll;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class PollResponse {

  private String message;

  public PollResponse() {
    this("");
  }

  public PollResponse(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}

