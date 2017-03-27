package alotor.factorial

import java.util.concurrent.atomic.AtomicReference
import static groovyx.gpars.GParsPool.withPool

class ParallelCollections implements Factorial, ProductTrait, BatchTrait {
    BigInteger calculate(BigInteger number) {
        def result = new AtomicReference(1g)
        int batches = batchesForNum(number)

        withPool(10) {
            (0 ..< batches).eachParallel { batch ->
                def from = batchFrom(number, batch)
                def to = batchTo(number, batch)
                def current = product(from, to)
                result = result * current
            }
        }

        return result.get()
    }
}


