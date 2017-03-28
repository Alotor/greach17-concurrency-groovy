package alotor.factorial

class Functional implements Factorial {
    BigInteger calculate(BigInteger number) {
        (1g .. number)
            .stream()
            .parallel()
            .reduce { a, b -> a * b }
            .get()
    }
}
