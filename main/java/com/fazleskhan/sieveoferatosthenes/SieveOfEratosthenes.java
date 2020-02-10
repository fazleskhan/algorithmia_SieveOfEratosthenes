package com.fazleskhan.sieveoferatosthenes;

import java.util.ArrayList;

public class SieveOfEratosthenes {

    private static final String START__TEXT = "Start calculating prime numbers between %s and %s";
    private static final int START_NUMBER = 1;
    private static final String START_TIMESTAMP_TEXT = "Sieve took %s ms";
    private static final int FIRST_PRIME_NUMBER = 2;
    private static final String START_SEIVE = "Start Sieve";
    private static final String NUMBER_PRIMES_TEXT = "Number of primes found %s";

    private final Helper helper;

    /**
     * https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes
     *
     * @param helper
     */
    public SieveOfEratosthenes(Helper helper) {
        this.helper = helper;
    }

    public PrimesResult calcPrimes(final int lastNumber) {
        final ArrayList<String> messages = new ArrayList<>();
        logInfo(String.format(START__TEXT, START_NUMBER, lastNumber), messages);
        logInfo(START_SEIVE, messages);
        final long start = System.nanoTime();
        final boolean[] primes = getHelper().initPrimes(lastNumber);

        for (int i = FIRST_PRIME_NUMBER; i < primes.length; i++) {
            //if the number is prime (which 2 is) go iterate through and set multiples of the value to false
            if (primes[i]) {
                for (int j = FIRST_PRIME_NUMBER; i * j < primes.length; j++) {
                    primes[i * j] = false;
                }
            }
        }
        final long end = System.nanoTime();
        final ArrayList<Integer> primesCollector = new ArrayList<>();
        for (int k = 0; k < primes.length; k++) {
            if (primes[k]) {
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
