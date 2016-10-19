package com.virmedica.question1;

import com.virmedica.main.Command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;

/**
 * Write a java program that calculates all prime numbers between 1 and N, with N specified on the command line.
 * Output both the primes as they are produced and the number of primes at completion.  You must implement the algorithm
 * that produces primes and it cannot call external libraries or BigInteger.isProbablePrime(). :)
 */
public class Solution1 extends Command {

    private static final String HELP_TEXT = "Calculates prime number between 1 and N. Supply N as an argument. For example Solution1 20";
    private static final String START__TEXT = "Start calculating prime numbers between %s and %s";
    private static final int START_NUMBER = 1;
    private static final String START_TIMESTAMP_TEXT = "Sieve took %s ms";
    private static final int FIRST_PRIME_NUMBER = 2;
    private static final String COMMA_DELIMITER = ", ";
    private static final String START_SEIVE = "Start Sieve";
    private static final String NUMBER_PRIMES_TEXT = "Number of primes found %s";

    private static final Logger logger = Logger.getLogger(Solution1.class.getName());

    public Solution1() {
        super(1, 1, HELP_TEXT);
    }

    @Override
    protected String[] myWork(String[] args) {
        final ArrayList<String> messages = new ArrayList<>();
        logInfo(String.format(START__TEXT, START_NUMBER, args[0]), messages);
        final int lastNumber = Integer.parseInt(args[0]);
        logInfo(START_SEIVE, messages);
        final long start = System.nanoTime();
        final boolean[] primes = new boolean[lastNumber];
        Arrays.fill(primes, true);
        //0 and 1 are not primes numbers
        primes[0] = false;
        primes[1] = false;
        for (int i = FIRST_PRIME_NUMBER; i < primes.length; i++) {
            //if the number is prime (which 2 is) go iterate through and set mulitples of the value to false
            if (primes[i]) {
                for (int j = FIRST_PRIME_NUMBER; i * j < primes.length; j++) {
                    primes[i * j] = false;
                }
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
        return (String[]) messages.toArray(new String[0]);
    }

    private void logInfo(final String message, final ArrayList<String> messages) {
        logger.info(message);
        messages.add(message);
    }
}
