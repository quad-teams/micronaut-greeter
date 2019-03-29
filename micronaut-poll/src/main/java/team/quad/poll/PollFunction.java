package team.quad.poll;

import io.micronaut.function.FunctionBean;
import java.util.function.Function;

@FunctionBean("micronaut-poll")
public class PollFunction implements Function<PollRequest, PollResponse> {

  @Override
  public PollResponse apply(PollRequest request) {
    return new PollResponse("Voted for " + request.getCategory());
  }
}
