package challenges.leetcode;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import challenges.AbstractCustomTestRunner;

/**
 * 1114. Print in Order
 *
 * Suppose we have a class:
 *
 * public class Foo {
 *   public void first() { print("first"); }
 *   public void second() { print("second"); }
 *   public void third() { print("third"); }
 * }
 *
 * The same instance of Foo will be passed to three different threads. Thread A will call first(), thread B will call second(), and thread C will call third().
 * Design a mechanism and modify the program to ensure that second() is executed after first(), and third() is executed after second().
 *
 * Example 1:
 *      Input: [1,2,3]
 *      Output: "firstsecondthird"
 *      Explanation: There are three threads being fired asynchronously. The input [1,2,3] means thread A calls first(), thread B calls second(), and thread C
 *                   calls third(). "firstsecondthird" is the correct output.
 *
 * Example 2:
 *      Input: [1,3,2]
 *      Output: "firstsecondthird"
 *      Explanation: The input [1,3,2] means thread A calls first(), thread B calls third(), and thread C calls second(). "firstsecondthird" is the correct output.
 *
 * Note:
 * We do not know how the threads will be scheduled in the operating system, even though the numbers in the input seems to imply the ordering.
 * The input format you see is mainly to ensure our tests' comprehensiveness.
 *
 * @author Hxkandwal
 */
public class PrintInOrder extends AbstractCustomTestRunner {

    class FooSemaphore {

        private Semaphore second;
        private Semaphore third;

        public FooSemaphore() {
            second = new Semaphore(0);
            third = new Semaphore(0);
        }

        public void first(Runnable printFirst) throws InterruptedException {
            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            second.release();
        }

        public void second(Runnable printSecond) throws InterruptedException {
            second.acquire();
            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
            third.release();
        }

        public void third(Runnable printThird) throws InterruptedException {
            third.acquire();
            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
            third.release();
        }
    }

    class FooCountDownLatch {

        private CountDownLatch second;
        private CountDownLatch third;

        public FooCountDownLatch() {
            second = new CountDownLatch(1);
            third = new CountDownLatch(1);
        }

        public void first(Runnable printFirst) throws InterruptedException {
            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            second.countDown();
        }

        public void second(Runnable printSecond) throws InterruptedException {
            second.await();
            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
            third.countDown();
        }

        public void third(Runnable printThird) throws InterruptedException {
            third.await();
            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
        }
    }

    class FooVolatile {

        private volatile boolean onePrinted;
        private volatile boolean twoPrinted;

        public FooVolatile() {
            onePrinted = false;
            twoPrinted = false;
        }

        public synchronized void first(Runnable printFirst) throws InterruptedException {

            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            onePrinted = true;
            notifyAll();
        }

        public synchronized void second(Runnable printSecond) throws InterruptedException {
            while (!onePrinted) {
                wait();
            }
            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
            twoPrinted = true;
            notifyAll();
        }

        public synchronized void third(Runnable printThird) throws InterruptedException {
            while (!twoPrinted) {
                wait();
            }
            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
        }
    }

}
