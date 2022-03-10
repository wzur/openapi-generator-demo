OpenAPI generator demo
======================

This is an example how to create a Rest server based on Spring Boot framework
and a command line client to talk to it.

## Dependencies

This project requires a Kubernetes cluster to work with. Gradle's target `createKubernetesCluster`
creates one with [kind].

## OpenAPI definition

See [automation.yaml](./spec/automation.yaml) for OpenAPI definition. It defines
a very simply interface to list, create and delete pods with a fixed definition.
All pods are started with `wait` command, and the only modification is the argument
for the `wait`, ie for how long the pod should be running.

The [OpenAPI generator](https://openapi-generator.tech/) gradle [integration](https://openapi-generator.tech/docs/integrations#gradle-integration)
creates skeleton code for the [Rest server](./server) and the [client library](./client).

## How to build

```shell
./gradlew build
```

## How to start a Rest server

```shell
./gradlew createKindCluster :server:bootRun
```
```

This creates a Kubernetes cluster with [kind] and starts a HTTP server on port
8080.

## How to create CLI client

```shell
# build the client
./gradlew :automation-cli:installDist
# start the client
automation-cli/build/install/automation-cli/bin/automation-cli pods ls
```

[kind]: https://kind.sigs.k8s.io/docs/user/quick-start/ "a tool for running local Kubernetes clusters using Docker container “nodes”"
