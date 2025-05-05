import java.util.*;
import java.util.concurrent.*;

/*
Assignment 1: Multi-threaded Logging System
-------------------------------------------

Part 1: Logging system that accepts messages concurrently and flushes to console every 5 seconds.
Part 2: Graceful shutdown that ensures no log is lost and background thread is stopped.
Part 3: Simulate 100 threads logging 100 messages each.
Bonus features (e.g., log levels, timestamps, file flush) are NOT included for simplicity.
*/

// Logger class that handles receiving and flushing logs
class Logger {
    private final BlockingQueue<String> logQueue = new LinkedBlockingQueue<>();
    private final Thread flusherThread;
    private volatile boolean isRunning = true; // Flag to control the flusher thread

    // Constructor - Starts a background thread to flush logs every 5 seconds
    public Logger() {
        flusherThread = new Thread(() -> {
            try {
                while (isRunning) {
                    Thread.sleep(5000);  // Wait 5 seconds between flushes
                    flushLogs();
                }
            } catch (InterruptedException e) {
                // Interrupted during sleep (shutdown), fall through to flush
            } finally {
                flushLogs(); // Final flush before shutdown
                System.out.println("Logger thread shut down gracefully.");
            }
        });

        flusherThread.start(); // Start the background flushing thread
    }

    // Method to receive log messages from multiple threads
    public void log(String message) {
        logQueue.offer(message); // Add message to the queue (non-blocking)
    }

    // Method to print all logs to console
    private void flushLogs() {
        List<String> logs = new ArrayList<>();
        logQueue.drainTo(logs); // Take all messages from the queue at once

        if (logs.isEmpty()) return; // No logs to print

        System.out.println("\n--- Flushing Logs ---");
        for (String log : logs) {
            System.out.println(log);
        }
        System.out.println("--- Flush Complete ---\n");
    }

    // Gracefully shut down the logger and flush remaining logs
    public void shutdown() {
        isRunning = false;           // Stop the loop
        flusherThread.interrupt();   // Interrupt sleep if active
        try {
            flusherThread.join();    // Wait for flusher thread to finish
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

// Class that simulates logging from a thread
class LogProducer implements Runnable {
    private final Logger logger;
    private final int messageCount;

    public LogProducer(Logger logger, int messageCount) {
        this.logger = logger;
        this.messageCount = messageCount;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        for (int i = 1; i <= messageCount; i++) {
            String logMessage = threadName + " - Log message " + i;
            logger.log(logMessage);  // Send message to logger
            try {
                Thread.sleep(1); // Small delay between logs
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

// Main class to run the full simulation
public class q4 {
    public static void main(String[] args) {
        int totalThreads = 100;
        int messagesPerThread = 100;

        // Step 1: Create logger instance
        Logger logger = new Logger();

        // Step 2: Create and start 100 threads
        List<Thread> threads = new ArrayList<>();
        for (int i = 1; i <= totalThreads; i++) {
            Thread t = new Thread(new LogProducer(logger, messagesPerThread), "Thread-" + i);
            threads.add(t);
            t.start();
        }

        // Step 3: Wait for all threads to finish logging
        for (Thread t : threads) {
            try {
                t.join(); // Wait for each thread to complete
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Step 4: Gracefully shut down logger
        logger.shutdown();

        System.out.println("\n✅ All log messages have been processed.");
    }
}
