package alotor.factorial

import java.util.concurrent.atomic.AtomicReference
import java.util.concurrent.Executors

class ThreadBatch implements Factorial, ProductTrait, BatchTrait {
    BigInteger calculate(BigInteger number) {
        def result = new AtomicReference(1g)
        def pool = Executors.newFixedThreadPool(1)
        def fs = []

        int batches = batchesForNum(number)

        (0 ..< batches).each { batch ->
            fs << pool.submit {
                def from = batchFrom(number, batch)
                def to = batchTo(number, batch)
                def current = product(from, to)
                result = result * current
            }
        }

        fs*.get()
        pool.shutdownNow()
        return result.get()
    }
}


