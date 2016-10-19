package com.virmedica.question4;

import com.virmedica.main.Command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Make the program from #1 multithreaded, with max number of threads specified on the command line.  Output both the
 * primes as they are produced and the number of primes at completion.
 *
 * Uses Java 8 concurrency
 */
public class Solution2a extends Command {

    private static final String HELP_TEXT = "Calculates prime number between 1 and N with multiple threads. Supply N for the last value and T for the number of threads. Solution2 N T For example Solution2 20 5";
    private static final String NUMBER_PROCESSORS_TEXT = "The number of available processors is %s";
    private static final String START_TEXT = "Start calculating prime numbers between %s and %s with %s threads";
    private static final int START_NUMBER = 1;
    private static final String START_TIMESTAMP_TEXT = "Multithreaded Sieve took %s ms";
    private static final int FIRST_PRIME_NUMBER = 2;
    private static final String COMMA_DELIMITAR = ", ";
    private static final String NUMBER_PRIMES_TEXT = "Number of primes found %s";


    private static final Logger logger = Logger.getLogger(Solution2a.class.getName());

    public Solution2a() {
        super(2, 2, HELP_TEXT);
    }


    private class Input implements Callable{
        final boolean[] sharedPrimes;
        final int threadNumber;
        final int threadCount;

        public Input(boolean[] sharedPrimes, int threadNumber, int threadCount){
            this.sharedPrimes = sharedPrimes;
            this.threadNumber = threadNumber;
            this.threadCount = threadCount;
        }

        public boolean[] getSharedPrimes(){
            return sharedPrimes;
        }
        public int getThreadNumber(){
            return threadNumber;
        }
        public int getThreadCount(){
            return threadCount;
        }

        public Object call(){
            for (int i = FIRST_PRIME_NUMBER + threadNumber; i < sharedPrimes.length; i = i + threadCount) {
                //if the number is prime (which 2 is) go iterate through and set multiples of the value to false
                if (sharedPrimes[i]) {
                    for (int j = FIRST_PRIME_NUMBER; i * j < sharedPrimes.length; j++) {
                        sharedPrimes[i * j] = false;
                    }
                }
            }
            return null;
        }
        @Override
        public String toString(){
            return String.valueOf(threadNumber);
        }
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

        final List<Callable<Input>> inputs = new ArrayList<>();
        for( int i = 0; i < threadCount; i++){
            inputs.add(new Input(primes, i, threadCount));
        }


        ExecutorService executor = null;
        try {
            executor = Executors.newFixedThreadPool(threadCount);
            executor.invokeAll(inputs).stream().map(
                    future -> {
                        try{
                            return future.get();
                        }
                        catch(Exception e){
                            throw new IllegalStateException(e);
                        }
                    }
            ).forEach(
                    input -> {}
            );
            executor.shutdown();
            executor.awaitTermination(5, TimeUnit.SECONDS);
        }catch(InterruptedException ie){
            throw new RuntimeException(ie);
        }finally{
            if( null != executor && !executor.isTerminated()){
                throw new RuntimeException("cancel unfinished tasks");
            }
            if( null != executor ){
                executor.shutdown();
            }
        }
        final long end = System.nanoTime();
        int primeCount = 0;
        final StringBuilder sb = new StringBuilder();
        for (int k = 0; k < primes.length; k++) {
            if (primes[k]) {
                sb.append(k).append(COMMA_DELIMITAR);
                primeCount++;
            }
        }
        logInfo(sb.toString(), messages);
        logInfo(String.format(NUMBER_PRIMES_TEXT, primeCount), messages);

        logInfo(String.format(START_TIMESTAMP_TEXT, (end - start) / 1000), messages);

        return (String[]) messages.toArray(new String[0]);
    }

    private void logInfo(final String message, final ArrayList<String> messages) {
        logger.info(message);
        messages.add(message);
    }


    private boolean[] initPrimes(final int end) {
        final boolean[] primes = new boolean[end];
        Arrays.fill(primes, true);
        primes[0] = false;
        primes[1] = false;
        return primes;
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
