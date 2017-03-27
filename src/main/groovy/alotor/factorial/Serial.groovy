package alotor.factorial

class Serial implements Factorial {
    BigInteger calculate(BigInteger number) {
        def result = 1g

        (1 .. number).each { n ->
            result = result * n
        }
        return result
    }
}
