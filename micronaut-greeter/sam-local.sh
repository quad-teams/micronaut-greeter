#!/bin/sh
docker build . -t micronaut-greeter
mkdir -p build
docker run --rm --entrypoint cat micronaut-greeter  /home/application/function.zip > build/function.zip

sam local start-api -t sam.yaml -p 3000

