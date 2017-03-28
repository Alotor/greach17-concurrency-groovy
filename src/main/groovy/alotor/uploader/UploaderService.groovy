package alotor.uploader

enum NotificationType {
    START, PROGRESS, FINISH
}

@groovy.transform.Immutable
@groovy.transform.TupleConstructor
class Notification {
    NotificationType type
    int progress

    String toString() {
        "($type, $progress)"
    }
}

@groovy.transform.TupleConstructor(includes=['file', 'notifier'])
class UploaderService {
    String file
    boolean isDone = false
    int progress = 0

    void start(Closure notifier = null) {
        // Simulate an asynchronous uploading
        Thread.start {
            this.progress = 0

            notifier?.call(new Notification(NotificationType.START, 0))
            def random = new Random()
            while(progress < 100) {
                int randomProgress = 1 + random.nextInt(100 - this.progress)
                this.progress += randomProgress
                notifier?.call(new Notification(NotificationType.PROGRESS, this.progress))
                println ">> PROGRESS ${this.progress}"
            }

            this.isDone = true
            notifier?.call(new Notification(NotificationType.FINISH, 100))
        }
    }

    public static void main(String[] args) {
        UploaderService uploader = new UploaderService('/tmp/test.txt')

        // uploader.start { Notification msg ->
        //     println ">> $msg"
        // }
        uploader.start()

        while(!uploader.isDone) {
            println ">>> ${uploader.progress}"
            Thread.sleep(10)
        }
        println ">>> FINISH"
    }
}
