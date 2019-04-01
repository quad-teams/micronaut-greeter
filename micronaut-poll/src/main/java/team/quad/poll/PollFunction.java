package team.quad.poll;

import io.micronaut.function.FunctionBean;
import java.util.function.Function;
import javax.inject.Inject;

@FunctionBean("micronaut-poll")
public class PollFunction implements Function<PollRequest, String> {

  @Inject
  public PollService pollService;

  @Override
  public String apply(PollRequest request) {
    return pollService.vote(request);
  }
}
