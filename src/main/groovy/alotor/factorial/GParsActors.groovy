package alotor.factorial

import static groovyx.gpars.actor.Actors.actor
import groovyx.gpars.actor.Actor

class GParsActors implements Factorial, ProductTrait {
    static final int MIN_SIZE = 10000

    Actor spawnCalculator() {
        actor {
            react { msg ->
                def (from, to) = msg
                def origin = sender

                if (to - from < 1000) {
                    reply product(from, to)
                } else {
                    def half = from + ((to - from) / 2) as BigInteger

                    def child1 = spawnCalculator()
                    def child2 = spawnCalculator()

                    child1.send([from, half])
                    child2.send([half+1, to])

                    react { a ->
                        react { b ->
                            origin.send(a * b)
                        }
                    }

                }
            }
        }
    }

    BigInteger calculate(BigInteger number) {
        def result = 0g

        def coordinator = actor {
            spawnCalculator().send([1g, number])

            react { msg ->
                result = msg
            }
        }

        coordinator.join()
        return result
    }
}

