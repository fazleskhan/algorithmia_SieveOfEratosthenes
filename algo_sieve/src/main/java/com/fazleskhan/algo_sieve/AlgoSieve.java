package com.fazleskhan.algo_sieve;

import com.fazleskhan.sieveoferatosthenes.*;

public class AlgoSieve {

    private SieveOfEratosthenes sieve;

    public AlgoSieve(){
        //default constructor
        sieve = new SieveOfEratosthenes();
    }

       public String apply(String s) {
           final int lastNumber = Integer.parseInt(s);

           final PrimesResult result = sieve.calcPrimes(lastNumber);

           final String toReturn = String.format("Sieve of Eratosthenes (last number %s): ",s);
           return toReturn;


        }
}
