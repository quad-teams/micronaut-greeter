package team.quad.poll;

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

