package com.fazleskhan.algo_sieve;

import org.junit.Test;

import static org.hamcrest.Matchers.matchesPattern;
import static org.junit.Assert.assertThat;

public class AlgoSieveTests {

    @Test
    public void ZeroLastNumber(){
        AlgoSieve target = new AlgoSieve();

        String result = target.apply("0");
        assertThat(result, matchesPattern(
                "Sieve of Eratosthenes \\(last number 0\\): messages - \\[Start calculating prime numbers between 1 and 0, Start Sieve, Number of primes found 0, Sieve took \\d* ms\\] primes - \\[\\]"
        ));
    }
}
