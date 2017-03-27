package alotor.factorial

trait BatchTrait {
    static final int BATCH_SIZE = 10000

    int batchesForNum(BigInteger number) {
        Math.ceil(number / (BATCH_SIZE as float))
    }

    int batchFrom(number, batch) {
        (batch * BATCH_SIZE) + 1
    }

    int batchTo(number, batch) {
        def from = batchFrom(number, batch)
        def to = (from + BATCH_SIZE - 1)
        to = (to <= number) ? to : number
        return to
    }
}
