package alotor

import java.util.concurrent.atomic.AtomicReference
import alotor.factorial.*

class Main {
    static {
        AtomicReference.metaClass.multiply = { val ->
            def old = delegate.get()
            while(!delegate.compareAndSet(old, old * val)) {
                old = delegate.get()
            }
            return delegate
        }
    }

    static Map resultValidation = [:]

    static timed(fn, Object... args) {
        try {
            def t = System.nanoTime()
            def result = fn(*args)

            def key = args.join("-")

            if (resultValidation.containsKey(key)) {
                if(resultValidation[key] != result) {
                    throw new Exception(">> Unexpected result for ${key}.")
                }
            } else {
                // First result is our sanity check
                resultValidation[key] = result
            }

            def time = (System.nanoTime() - t)  / 1e9f
            return "${time.trunc(4)}"
        } catch(Throwable e) {
            // With Throwable we could catch errors while trying
            // to create threads
            return "ERROR(${e.message})"
        }
    }

    static void main(String[] args) {
        def algorithms = [
            Serial,
            ThreadSharedMemory,
            ThreadAtomicReference,
            ThreadBatch,
            Functional,
            ParallelCollections,
            ForkJoin,
            GParsActors,

            ClojureAtom,
            AkkaActors,
            Spark,
        ]

        def values = [
            10000,
            25000,
            100000,
            1000000,
        ]

        println "Algorithm|Value|Time(s)"
        algorithms.each { alg ->
            def f = alg.newInstance()
            values.each {
                println "${f.class.simpleName}|${it}|${timed(f.&calculate, it)}"
            }
        }
    }
}
