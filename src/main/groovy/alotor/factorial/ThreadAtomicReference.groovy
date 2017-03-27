package alotor.factorial

import java.util.concurrent.atomic.AtomicReference
import java.util.concurrent.Executors

class ThreadAtomicReference implements Factorial {

    BigInteger calculate(BigInteger number) {
        def result = new AtomicReference(1g)
        def pool = Executors.newFixedThreadPool(10)
        def fs = []

        (1 .. number).each { n ->
            fs << pool.submit {
                result = result * n
            }
        }
        fs*.get()
        pool.shutdownNow()
        return result.get()
    }
}


