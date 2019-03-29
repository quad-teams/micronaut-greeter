package team.quad.poll;

import io.micronaut.function.executor.FunctionInitializer;
import java.io.IOException;
import javax.inject.Singleton;

@Singleton
public class CLIPollFunction extends FunctionInitializer {

  public PollResponse execute(PollRequest msg) {
    return new PollResponse(msg.getCategory());
  }

  public static void main(String... args) throws IOException {
    CLIPollFunction function = new CLIPollFunction();
    function.run(args, (context) -> function.execute(context.get(PollRequest.class)));
  }
}


