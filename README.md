Greach 2017 - Make Concurrency Groovy Again
------------------------------------

Sample code for my presentation in Greach 2017

## Benchmark execution

Make sure you have Gradle installed and just run:

```
gradle run
```

You can change the file [Main.groovy](https://github.com/Alotor/greach17-concurrency-groovy/blob/master/src/main/groovy/alotor/Main.groovy) to change the values tried.

## Concurrency models

- [Serial](https://github.com/Alotor/greach17-concurrency-groovy/blob/master/src/main/groovy/alotor/factorial/Serial.groovy)
- [Thread - Shared Memory](https://github.com/Alotor/greach17-concurrency-groovy/blob/master/src/main/groovy/alotor/factorial/ThreadSharedMemory.groovy)
- [Thread - Atomic Reference](https://github.com/Alotor/greach17-concurrency-groovy/blob/master/src/main/groovy/alotor/factorial/ThreadAtomicReference.groovy)
- [Thread - Batch](https://github.com/Alotor/greach17-concurrency-groovy/blob/master/src/main/groovy/alotor/factorial/ThreadBatch.groovy)
- [Streams - Functional](https://github.com/Alotor/greach17-concurrency-groovy/blob/master/src/main/groovy/alotor/factorial/Functional.groovy)
- [GPars - ParallelCollections](https://github.com/Alotor/greach17-concurrency-groovy/blob/master/src/main/groovy/alotor/factorial/ParallelCollections.groovy)
- [GPars - ForkJoin](https://github.com/Alotor/greach17-concurrency-groovy/blob/master/src/main/groovy/alotor/factorial/ForkJoin.groovy)
- [GPars - Actors](https://github.com/Alotor/greach17-concurrency-groovy/blob/master/src/main/groovy/alotor/factorial/GParsActors.groovy)

## Stealing

- [Clojure - Atoms](https://github.com/Alotor/greach17-concurrency-groovy/blob/master/src/main/groovy/alotor/factorial/ClojureAtom.groovy)
- [Akka - Actors](https://github.com/Alotor/greach17-concurrency-groovy/blob/master/src/main/groovy/alotor/factorial/AkkaActors.groovy)
- [Spark](https://github.com/Alotor/greach17-concurrency-groovy/blob/master/src/main/groovy/alotor/factorial/Spark.groovy)

## Testing

- [BlockingVariable](https://github.com/Alotor/greach17-concurrency-groovy/blob/master/src/test/groovy/alotor/uploader/UploaderServiceSpec.groovy#L20)
- [AsyncConditions](https://github.com/Alotor/greach17-concurrency-groovy/blob/master/src/test/groovy/alotor/uploader/UploaderServiceSpec.groovy#L39)
- [PollingConditions](https://github.com/Alotor/greach17-concurrency-groovy/blob/master/src/test/groovy/alotor/uploader/UploaderServiceSpec.groovy#L58)

## LICENSE

This project is licensed under BSD 3-Clause license
