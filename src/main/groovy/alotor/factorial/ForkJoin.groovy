package alotor.factorial

import static groovyx.gpars.GParsPool.runForkJoin
import static groovyx.gpars.GParsPool.withPool

class ForkJoin implements Factorial, ProductTrait {
    static final int MIN_SIZE = 10000

    BigInteger calculate(BigInteger number) {
        withPool(10) {
            runForkJoin(1, number) { from, to ->
                if (to - from < MIN_SIZE) {
                    return product(from, to)
                } else {
                    int half = from + ((to - from) / 2)
                    forkOffChild(from, half)
                    forkOffChild(half + 1, to)
                    def (a, b) = getChildrenResults()
                    return a * b
                }
            }
        }

    }
}


