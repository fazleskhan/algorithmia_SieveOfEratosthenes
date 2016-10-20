package com.fazleskhan.virmedica.question2;


import com.fazleskhan.virmedica.shared.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MultithreadedSieve {

    private static final String NUMBER_PROCESSORS_TEXT = "The number of available processors is %s";
    private static final String START_TEXT = "Start calculating prime numbers between %s and %s with %s threads";
    private static final int START_NUMBER = 1;
    private static final String START_TIMESTAMP_TEXT = "Multithreaded Sieve took %s ms";
    private static final int FIRST_PRIME_NUMBER = 2;
    private static final String COMMA_DELIMITER = ", ";
    private static final String NUMBER_PRIMES_TEXT = "Number of primes found %s";

    private final Helper helper;

    @Autowired
    public MultithreadedSieve(Helper helper){
        this.helper = helper;
    }

    public String[] calcPrimes(final int lastNumber, final int threadCount) {
        final ArrayList<String> messages = new ArrayList<>();
        final int cores = Runtime.getRuntime().availableProcessors();
        logInfo(String.format(NUMBER_PROCESSORS_TEXT, cores), messages);
        logInfo(String.format(START_TEXT, START_NUMBER, String.valueOf(lastNumber), String.valueOf(threadCount)), messages);
        final long start = System.nanoTime();
        final boolean[] primes = getHelper().initPrimes(lastNumber);

        final ArrayList<Thread> threads = new ArrayList<>(threadCount);
        for (int i = 0; i < threadCount; i++) {
            threads.add(i, new Thread(new PartialSieve(i, threadCount, primes)));
        }
        for (int j = 0; j < threadCount; j++) {
            threads.get(j).start();
        }

        for (int l = 0; l < threadCount; l++) {
            try {
                threads.get(l).join();
            } catch (InterruptedException ie) {
                throw new RuntimeException(ie);
            }
        }

        final long end = System.nanoTime();
        int primeCount = 0;
        final StringBuilder sb = new StringBuilder();
        for (int k = 0; k < primes.length; k++) {
            if (primes[k]) {
                sb.append(k).append(COMMA_DELIMITER);
                primeCount++;
            }
        }
        logInfo(sb.toString(), messages);
        logInfo(String.format(NUMBER_PRIMES_TEXT, primeCount), messages);

        logInfo(String.format(START_TIMESTAMP_TEXT, (end - start) / 1000), messages);
        return messages.toArray(new String[0]);
    }

    private void logInfo(final String message, final ArrayList<String> messages) {
         messages.add(message);
    }

    /**
     *
     */
    public class PartialSieve implements Runnable {

        private final boolean[] sharedPrimes;
        private final int threadNumber;
        private final int totalThreadCount;

        public PartialSieve(int threadNumber, int totalThreadCount, boolean[] sharedPrimes) {
            this.sharedPrimes = sharedPrimes;
            this.threadNumber = threadNumber;
            this.totalThreadCount = totalThreadCount;
        }

        @Override
        public void run() {
            for (int i = FIRST_PRIME_NUMBER + threadNumber; i < sharedPrimes.length; i = i + totalThreadCount) {
                //if the number is prime (which 2 is) go iterate through and set multiples of the value to false
                if (sharedPrimes[i]) {
                    for (int j = FIRST_PRIME_NUMBER; i * j < sharedPrimes.length; j++) {
                        sharedPrimes[i * j] = false;
                    }
                }
            }
        }
    }

    private Helper getHelper(){
        return this.helper;
    }
}
