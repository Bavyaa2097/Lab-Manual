import java.util.concurrent.Semaphore;

class Foo {

    Semaphore second = new Semaphore(0);
    Semaphore third = new Semaphore(0);

    public Foo() {
    }

    public void first(Runnable printFirst) {
        printFirst.run();
        second.release();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        second.acquire();
        printSecond.run();
        third.release();
    }

    public void third(Runnable printThird) throws InterruptedException {
        third.acquire();
        printThird.run();
    }
}
