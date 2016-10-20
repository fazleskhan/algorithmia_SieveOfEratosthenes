package com.fazleskhan.virmedica.question2a;

import com.fazleskhan.virmedica.shared.Helper;
import com.fazleskhan.virmedica.shared.SeiveResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
public class FunctionalMultithreadedSieve {

    private static final String NUMBER_PROCESSORS_TEXT = "The number of available processors is %s";
    private static final String START_TEXT = "Start calculating prime numbers between %s and %s with %s threads";
    private static final int START_NUMBER = 1;
    private static final String START_TIMESTAMP_TEXT = "Multithreaded Sieve took %s ms";
    private static final int FIRST_PRIME_NUMBER = 2;
    private static final String COMMA_DELIMITER = ", ";
    private static final String NUMBER_PRIMES_TEXT = "Number of primes found %s";

    private final Helper helper;

    public FunctionalMultithreadedSieve(Helper helper){
        this.helper = helper;
    }

    private class Input implements Callable {
        final boolean[] sharedPrimes;
        final int threadNumber;
        final int threadCount;

        public Input(boolean[] sharedPrimes, int threadNumber, int threadCount){
            this.sharedPrimes = sharedPrimes;
            this.threadNumber = threadNumber;
            this.threadCount = threadCount;
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

    public SeiveResult calcPrimes(final int lastNumber, final int threadCount) {
        final ArrayList<String> messages = new ArrayList<>();
        final int cores = Runtime.getRuntime().availableProcessors();
        logInfo(String.format(NUMBER_PROCESSORS_TEXT, cores), messages);
        logInfo(String.format(START_TEXT, START_NUMBER, String.valueOf(lastNumber), String.valueOf(threadCount)), messages);
        final long start = System.nanoTime();
        final boolean[] primes = getHelper().initPrimes(lastNumber);

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
                //noinspection ThrowFromFinallyBlock
                throw new RuntimeException("cancel unfinished tasks");
            }
            if( null != executor ){
                executor.shutdown();
            }
        }
        final long end = System.nanoTime();
        final ArrayList<Integer> primesCollector = new ArrayList<>();
        final StringBuilder sb = new StringBuilder();
        for (int k = 0; k < primes.length; k++) {
            if (primes[k]) {
                primesCollector.add(k);
            }
        }
        logInfo(String.format(NUMBER_PRIMES_TEXT, primesCollector.size()), messages);
        logInfo(String.format(START_TIMESTAMP_TEXT, (end - start) / 1000), messages);

        return new SeiveResult(primesCollector.toArray(new Integer[0]),messages.toArray(new String[0]));
    }

    private void logInfo(final String message, final ArrayList<String> messages) {
        messages.add(message);
    }

    private Helper getHelper(){
        return this.helper;
    }
}
