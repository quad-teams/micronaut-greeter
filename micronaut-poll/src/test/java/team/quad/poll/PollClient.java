package team.quad.poll;

import io.micronaut.function.client.FunctionClient;
import io.micronaut.http.annotation.Body;
import io.reactivex.Single;
import javax.inject.Named;

@FunctionClient
public interface PollClient {

  @Named("micronaut-poll")
  PollResponse apply(@Body PollRequest request);

}
