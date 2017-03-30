package alotor.factorial

import static akka.japi.pf.ReceiveBuilder.match
import scala.concurrent.duration.Duration
import akka.actor.*

@groovy.transform.TupleConstructor
class StartCalculation {
    int from
    int to
}

@groovy.transform.TupleConstructor
class Result {
    BigInteger value
}


class CalculatorActor extends AbstractActor implements ProductTrait {
    static final int MIN_SIZE = 10000

    BigInteger firstResult = -1
    def origin

    CalculatorActor() {
        receive(
            match(StartCalculation) { msg ->
                def from = msg.from
                def to = msg.to

                this.origin = sender()

                if (to - from < MIN_SIZE) {
                    def resultMsg = new Result(product(from, to))
                    this.origin.tell(resultMsg, self())
                    context.stop(self())
                } else {
                    def half = from + ((to - from) / 2) as BigInteger

                    def child1 = context.actorOf(Props.create(CalculatorActor))
                    child1.tell(new StartCalculation(from, half), self())

                    def child2 = context.actorOf(Props.create(CalculatorActor))
                    child2.tell(new StartCalculation(half+1, to), self())
                }
            }
            .match(Result, { this.firstResult == -1 }) {
                this.firstResult = it.value
            }
            .match(Result, { this.firstResult != -1 }) {
                this.origin.tell(new Result(this.firstResult * it.value), self())
                context.stop(self())
            }
            .build()
        )
    }
}

class AkkaActors implements Factorial {
    static final int MIN_SIZE = 10000

    BigInteger calculate(BigInteger number) {
        def system = ActorSystem.create("Factorial")
        def inbox = Inbox.create(system)

        def actor = system.actorOf(Props.create(CalculatorActor), "calculator")
        inbox.send(actor, new StartCalculation(1, number))

        def result = inbox.receive(Duration.create("1 hour"))

        system.shutdown()
        return result.value
    }
}

