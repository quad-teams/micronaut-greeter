package team.quad.poll;

import javax.inject.Singleton;

@Singleton
public class PollService {

  public String vote(PollRequest request) {
    return "Voted for " + request.getCategory();
  }
}
