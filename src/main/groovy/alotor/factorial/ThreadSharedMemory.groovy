package alotor.factorial

class ThreadSharedMemory implements Factorial {
    BigInteger calculate(BigInteger number) {
        def result = 1g
        def ts = []

        (1 .. number).each { n ->
            ts << Thread.start {
                synchronized(this) {
                    result = result * n
                }
            }
        }
        ts*.join()
        return result
    }
}

