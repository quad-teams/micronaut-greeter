package team.quad.poll;

import io.micronaut.function.executor.FunctionInitializer;
import java.io.IOException;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CLIPollFunction extends FunctionInitializer {

  @Inject
  private PollService pollService;

  public String execute(PollRequest request) {
    return pollService.vote(request);
  }

  public static void main(String... args) throws IOException {
    CLIPollFunction function = new CLIPollFunction();
    function.run(args, (context) -> function.execute(context.get(PollRequest.class)));
  }
}


