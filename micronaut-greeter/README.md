# Quick Guide: Micronaut on AWS Lambda
Here you will:

* Generate a project with the Micronaut CLI (or clone this repository for the full application).
* Code a simple function.
* Unit test the function using Micronaut’s test framework.
* Build an AWS native image.
* Deploy to AWS Lambda with the AWS CLI.

## Setup
Install:
* [Micronaut][1]
* [Docker][2]
* [AWS CLI][3]

[3]: https://docs.aws.amazon.com/cli/latest/userguide/cli-chap-install.html
[2]: https://docs.docker.com/install/
[1]: https://micronaut.io/download.html

Micronaut comes with a convenient CLI to create applications and functions. Create a project called _micronaut-greeter_ with everything required to deploy to AWS, by inlcuding _aws-api-gateway-graal_ feature:

    mn create-app micronaut-greeter --features aws-api-gateway-graal

Alternatively, clone this application instead and skip to the build section of this guide.

## Code
Create a controller and inject a service that does the greeting:

    @Controller
    public class RocketController {
    
        @Inject
        private RadioService radio;
    
        @Get("/greet")
        public String greet() {
            return radio.greet();
        }
    }

Our injected service looks like this:

    @Singleton
    public class RadioService {
    
        public String greet() {
            return "Hello from Micronaut";
        }
    }

That's all the code we need.

## Test
Micronaut Test framework makes it very simple to create an HTTP client dedicated to our application:

    @Client("/")
    public interface RadioClient {
    
        @Get("/greet")
        String greet();
    
    }

All the necessary HTTP client code is generated behind the scenes. 

Inject the client into a class annotation with `@MicronautTest`:

    @MicronautTest
    public class RocketTest {
    
        @Inject
        RadioClient client;
    
        @Test
        public void test_greeter_response_is_as_expected() {
            String response = client.greet();
            assertEquals(response, "Hello from Micronaut");
        }
    }

## Build
The `aws-api-gateway-graal` feature generated a Docker file that we can use to build a native image for AWS Lambda.

Execute the following to build the image:

```bash
docker build . -t micronaut-greeter
mkdir build
docker run --rm --entrypoint cat micronaut-greeter /home/application/function.zip > build/function.zip
```
## Deploy
Use the AWS CLI to create a Lamda function.

    aws lambda create-function \
      --function-name micronaut-greeter \
      --zip-file fileb://build/function.zip \
      --handler function.handler \
      --runtime provided \
      --role arn:aws:iam::123456789012:role/lambda-role

## Run
Test the endpint with the AWS CLI by executing the `invoke` command.

```bash
aws lambda invoke --function-name micronaut-greeter --payload '{"resource": "/{proxy+}","path": "/greet","httpMethod": "GET","pathParameters":{"proxy":"greet"},"requestContext":{"identity":{}}}' build/response.txt
```
