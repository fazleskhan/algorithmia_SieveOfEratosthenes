package com.virmedica.question2;

import com.virmedica.main.Command;

import java.util.ArrayList;
import java.util.logging.Logger;

import static com.virmedica.shared.Helper.initPrimes;

/**
 * Make the program from #1 multithreaded, with max number of threads specified on the command line.  Output both the
 * primes as they are produced and the number of primes at completion.
 */
public class Solution2 extends Command {

    private static final String HELP_TEXT = "Calculates prime number between 1 and N with multiple threads. Supply N for the last value and T for the number of threads. Solution2 N T For example Solution2 20 5";
    private static final String NUMBER_PROCESSORS_TEXT = "The number of available processors is %s";
    private static final String START_TEXT = "Start calculating prime numbers between %s and %s with %s threads";
    private static final int START_NUMBER = 1;
    private static final String START_TIMESTAMP_TEXT = "Multithreaded Sieve took %s ms";
    private static final int FIRST_PRIME_NUMBER = 2;
    private static final String COMMA_DELIMITER = ", ";
    private static final String NUMBER_PRIMES_TEXT = "Number of primes found %s";


    private static final Logger logger = Logger.getLogger(Solution2.class.getName());

    public Solution2() {
        super(2, 2, HELP_TEXT);
    }

    @Override
    protected String[] myWork(final String[] args) {
        final ArrayList<String> messages = new ArrayList<>();
        final int cores = Runtime.getRuntime().availableProcessors();
        logInfo(String.format(NUMBER_PROCESSORS_TEXT, cores), messages);
        logInfo(String.format(START_TEXT, START_NUMBER, args[0], args[1]), messages);
        final int lastNumber = Integer.parseInt(args[0]);
        final int threadCount = Integer.parseInt(args[1]);
        final long start = System.nanoTime();
        final boolean[] primes = initPrimes(lastNumber);

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
        logger.info(message);
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
}
