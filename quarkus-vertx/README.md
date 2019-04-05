# Quick Guide: Quarkus and Vert.x
In this short guide we'll create the a "greeting" application with a Vert.x twist.

## Setup
A basic project can be created with [Maven](https://maven.apache.org/download.cgi) (version 3.5.3+ ) like so:

    mvn io.quarkus:quarkus-maven-plugin:0.11.0:create \
      -DprojectGroupId=team.quad \
      -DprojectArtifactId=quarkus-vertx

Create a project that includes the _vertx_ extension:

    mvn quarkus:add-extension -Dextensions=vertx

Import the project into your IDE and we're ready to get started.

## Code
Quarkus comes with [RESTEasy](https://resteasy.github.io/) by default so we'll make use of this to create a REST endpoint. The business logic is offloaded to another component listening on the Vert.x event bus.

    @Path("/hello")
    public class GreetingResource {
    
      @Inject
      EventBus eventBus;
    
      @GET
      @Produces(MediaType.TEXT_PLAIN)
      public CompletionStage<String> sayHello(@DefaultValue("") @QueryParam("name") String name) {
        return eventBus.<String>send("say-hello", name).thenApply(Message::body);
      }
    
    }

The name from the incoming request is sent as a message to the consumer listening on the "say-hello" address. The response is handled asynchronously.

Consuming from the Vert.x event bus is made even simpler in Quarkus than Vert.x itself. Annotate a method with `@ConsumeEvent` to indicate which event bus address to listen to and that's more or less it!

    public class GreetingService {
    
      @ConsumeEvent("say-hello")
      public CompletionStage<String> sayHello(String name) {
        return CompletableFuture.supplyAsync(() -> "Hello " + name);
      }
    
    }

## Testing
When it comes to testing, rapid startup time means we can afford to start the entire application before each unit test, eliminating the artificial separation between unit and functional tests that we're used to with other frameworks. Just annotate with `@QuarkusTest`. 

Let’s create a unit test that will check our greeting endpoint:

    @QuarkusTest
    public class GreetingResourceTest {
    
      @Test
      public void test_greeting_gives_200_OK_and_correct_response_body() {
        given()
          .when().get("/hello?name=Quarkus")
          .then()
            .statusCode(200)
            .body(is("Hello Quarkus"));
      }
    }

Quarkus supports JUnit 4 and JUnit 5.

## Run
Compile and start the application:

    mvn compile quarkus:dev

Starting the application in this way allows us to make changes to the code without restarting the application. A feature that comes right out of the box!

Once running, it's ready to receive a request:

    curl localhost:8080/hello?name=Quarkus

Hello Quarkus!


## Container deployment
Quarkus provides the ability to build a native executable for our application out of the box by using GraalVM. This gives us incredibly fast boot times, which are essential for serverless/FaaS environments.

Let’s build it using Docker:

    mvn package -Pnative -Dnative-image.docker-build=true

This produces a native 64 bit Linux executable. When building without Docker, it’s mandatory to have `GRAALVM_HOME` set for the build machine.

Build the docker image:

    docker build -f src/main/docker/Dockerfile -t quarkus-vertx/guide .

And now we can run it:

    docker run -i --rm -p 8080:8080 quarkus-vertx/guide

Push it to a Docker registry of your preference and get it deployed into the cloud. No hassle.
