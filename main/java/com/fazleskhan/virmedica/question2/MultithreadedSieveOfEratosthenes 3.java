package com.fazleskhan.virmedica.question2;


import com.fazleskhan.virmedica.shared.Helper;
import com.fazleskhan.virmedica.shared.PrimesResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Service
public class MultithreadedSieveOfEratosthenes {

    private static final String NUMBER_PROCESSORS_TEXT = "The number of available processors is %s";
    private static final String START_TEXT = "Start calculating prime numbers between %s and %s with %s threads";
    private static final int START_NUMBER = 1;
    private static final String START_TIMESTAMP_TEXT = "Multithreaded Sieve took %s ms";
    private static final int FIRST_PRIME_NUMBER = 2;
    private static final String NUMBER_PRIMES_TEXT = "Number of primes found %s";

    private final Helper helper;

    @Autowired
    public MultithreadedSieveOfEratosthenes(Helper helper) {
        this.helper = helper;
    }


    public static class SieveWorker implements Runnable {

        private final int intialValue;
        private final Semaphore semaphore;
        private final ExecutorService executor;
        private final boolean[] sharedPrimes;

        public SieveWorker(final Semaphore semaphore, final int intialValue, final boolean[] sharedPrimes, final ExecutorService executor) {
            this.intialValue = intialValue;
            this.semaphore = semaphore;
            this.executor = executor;
            this.sharedPrimes = sharedPrimes;
        }

        //http://stackoverflow.com/questions/15976696/how-to-check-if-a-thread-is-running-in-the-executorservice-thread-pool
        @Override
        public void run() {
            try {
                semaphore.acquire();
                try {
                    for (int i = intialValue; i < sharedPrimes.length; i++) {
                        //if the number is prime (which 2,3,5... is) iterate through and set multiples of the value to false
                        if (sharedPrimes[i] && !maybeScheduleNewWorker(i, sharedPrimes, semaphore, executor)) {
                            for (int j = intialValue; i * j < sharedPrimes.length; j++) {
                                sharedPrimes[i * j] = false;
                            }
                        }
                    }
                } finally {
                    semaphore.release();
                }
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
                ie.printStackTrace();
            }
        }
    }


    protected static boolean maybeScheduleNewWorker(final int intialValue, final boolean[] sharedPrimes
            , final Semaphore semaphore, final ExecutorService executor) {

        if (semaphore.tryAcquire()) {
            final SieveWorker worker = new SieveWorker(semaphore, intialValue, sharedPrimes, executor);
            executor.submit(worker);
            semaphore.release();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Instead of the single threaded Sieve of Eratosthenes this algorithm uses a new thread. When the intial
     * thread starting at 2 finds a new prime it activates a new thread from the ones available with the new prime
     * as the initial value. If all the alloated threads have been activated and a prime is found no new threads are
     * activated and iteration continues
     *
     * @param lastNumber
     * @param maxThreadCount
     * @return
     */
    public PrimesResult calcPrimes(final int lastNumber, final int maxThreadCount) {
        final ArrayList<String> messages = new ArrayList<>();
        final int cores = Runtime.getRuntime().availableProcessors();
        logInfo(String.format(NUMBER_PROCESSORS_TEXT, cores), messages);
        logInfo(String.format(START_TEXT, START_NUMBER, String.valueOf(lastNumber), String.valueOf(maxThreadCount)), messages);
        final long start = System.nanoTime();
        final boolean[] sharedPrimes = getHelper().initPrimes(lastNumber);

        final Semaphore semaphore = new Semaphore(maxThreadCount + 1); //need to add 1 semaphore for when trying to schedule a worker

        final ExecutorService executor = Executors.newFixedThreadPool(maxThreadCount + 1); //need to add 1 thread for the executor itself

        maybeScheduleNewWorker(FIRST_PRIME_NUMBER, sharedPrimes, semaphore, executor);

        do {
            //things need sometime to spin up before we can check on status else the main thread just fall through
            try {
                Thread.sleep(100);
            } catch (InterruptedException ie) {
                throw new RuntimeException(ie);
            }
            if (semaphore.availablePermits() == maxThreadCount + 1) {
                break;
            }
        } while (true);
        executor.shutdown();
        final long end = System.nanoTime();
        final ArrayList<Integer> primesCollector = new ArrayList<>();
        for (int k = 0; k < sharedPrimes.length; k++) {
            if (sharedPrimes[k]) {
                primesCollector.add(k);
            }
        }
        logInfo(String.format(NUMBER_PRIMES_TEXT, primesCollector.size()), messages);
        logInfo(String.format(START_TIMESTAMP_TEXT, (end - start) / 1000), messages);
        return new PrimesResult(primesCollector, messages);

    }

    private void logInfo(final String message, final ArrayList<String> messages) {
        messages.add(message);
    }

    private Helper getHelper() {
        return this.helper;
    }

}
