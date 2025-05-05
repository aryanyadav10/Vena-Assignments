import java.util.*;

//Assignment 3: Odd-Even Printer
//Background:
//Create a coordinated printing system where two threads print odd and even numbers up to 100 in sequence.
//Requirements:
//Thread A prints odd numbers (1, 3, 5...)
//Thread B prints even numbers (2, 4, 6...)
//Output must be in order: 1 2 3 4 5 ... 100
//Use wait() and notify() for coordination

class Printer {
    private int counter = 1;
    private final int MAX = 100;

    public synchronized void printEven() {
        while (counter <= MAX) {
            while (counter % 2 != 0) {
                try {
                    wait();
                } catch (InterruptedException e) {}
            }
            if (counter <= MAX) {
                System.out.println(Thread.currentThread().getName() + ": " + counter);
                counter++;
                notify();
            }
        }
    }

    public synchronized void printOdd() {
        while (counter <= MAX) {
            while (counter % 2 == 0) {
                try {
                    wait();
                } catch (InterruptedException e) {}
            }
            if (counter <= MAX) {
                System.out.println(Thread.currentThread().getName() + ": " + counter);
                counter++;
                notify();
            }
        }
    }
}

class MyRunnable implements Runnable {
    private final Printer printer;
    private final boolean isEven;

    public MyRunnable(Printer printer, boolean isEven) {
        this.printer = printer;
        this.isEven = isEven;
    }

    @Override
    public void run() {
        if (isEven) {
            printer.printEven();
        } else {
            printer.printOdd();
        }
    }
}

public class q2 {
    public static void main(String[] args) {
        Printer printer = new Printer();
        Thread t1 = new Thread(new MyRunnable(printer, false), "OddThread");
        Thread t2 = new Thread(new MyRunnable(printer, true), "EvenThread");
        t1.start();
        t2.start();
    }
}
