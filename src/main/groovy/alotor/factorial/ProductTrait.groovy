package alotor.factorial

trait ProductTrait {
    BigInteger product(BigInteger from, BigInteger to) {
        // return (from .. to).inject(1g) { a, b -> a * b }
        def result = 1g

        (from .. to).each { n ->
            result = result * n
        }
        return result

    }
}
