package com.fazleskhan.algo_sieve;

import com.fazleskhan.sieveoferatosthenes.PrimesResult;
import com.fazleskhan.sieveoferatosthenes.SieveOfEratosthenes;

public class AlgoSieve {

    private final SieveOfEratosthenes sieve;

    public AlgoSieve() {
        //default constructor
        sieve = new SieveOfEratosthenes();
    }

    public String apply(String s) {
        final int lastNumber = Integer.parseInt(s);

        final PrimesResult result = sieve.calcPrimes(lastNumber);

        return String.format("Sieve of Eratosthenes (last number %s): messages - %s primes - %s",
                s, result.getMessages(), result.getPrimes());


    }
}
