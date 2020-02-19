package com.fazleskhan.algo_sieve;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AlgoSieveTests {

    @Test
    public void ZeroLastNumber(){
        AlgoSieve target = new AlgoSieve();

        String result = target.apply("0");
        assertThat(result,is(
                "Sieve of Eratosthenes (last number 0): messages - [Start calculating prime numbers between 1 and 0, Start Sieve, Number of primes found 0, Sieve took 44 ms] primes - []"
        ));
    }

    @Test
    public void LastNumberOneHundred(){
        AlgoSieve target = new AlgoSieve();

        String result = target.apply("100");
        assertThat(result,is(
                "Sieve of Eratosthenes (last number 100): messages - [Start calculating prime numbers between 1 and 100, Start Sieve, Number of primes found 25, Sieve took 49 ms] primes - [2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97]"
        ));

    }
}
