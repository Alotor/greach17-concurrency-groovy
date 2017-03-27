package alotor.factorial

import clojure.lang.*
import java.util.concurrent.Executors

class ClojureAtom implements Factorial, ProductTrait, BatchTrait{
    BigInteger calculate(BigInteger number) {
        def result = new Atom(1g)
        def pool = Executors.newFixedThreadPool(10)
        def fs = []

        int batches = batchesForNum(number)

        (0 ..< batches).each { batch ->
            fs << pool.submit {
                def from = batchFrom(number, batch)
                def to = batchTo(number, batch)
                def current = product(from, to)
                result.swap({ value -> value * current } as IFn)
            }
        }

        fs*.get()
        pool.shutdownNow()
        return result.deref()
    }
}


