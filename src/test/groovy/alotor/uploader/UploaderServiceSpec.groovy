package alotor.uploader

import spock.lang.Specification
import spock.lang.Ignore
import spock.util.concurrent.*

class UploaderServiceSpec extends Specification {
    @Ignore
    void "Failing Test"() {
        given:
        def uploader = new UploaderService("file")

        when:
        uploader.start()

        then:
        uploader.isDone
    }

    void "BlockingVariable Sample"() {
        given:
        def isDone = new BlockingVariable()

        and:
        def uploader = new UploaderService("file") {
            void setIsDone(boolean v) {
                isDone.set(v)
            }
        }

        when:
        uploader.start()

        then:
        isDone.get()
    }


    void "AsyncConditions Sample"() {
        given:
        def conds = new AsyncConditions()

        and:
        def uploader = new UploaderService("file")

        when:
        uploader.start { msg ->
            conds.evaluate {
                assert msg.type == NotificationType.START ||
                       msg.progress != 0
            }
        }

        then:
        conds.await()
    }

    void "PollingConditions Sample"() {
        given:
        def conds = new PollingConditions()

        and:
        def uploader = new UploaderService("file")

        when:
        uploader.start()

        then:
        conds.eventually {
            assert uploader.isDone
            assert uploader.progress == 100
        }
    }
}
