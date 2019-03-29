package team.quad.poll;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class PollHandler implements RequestHandler<PollRequest, PollResponse> {

  @Override
  public PollResponse handleRequest(PollRequest request, Context context) {
    return new PollResponse("Voted for " + request.getCategory());
  }
}
