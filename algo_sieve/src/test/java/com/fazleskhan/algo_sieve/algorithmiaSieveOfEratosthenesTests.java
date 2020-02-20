package com.fazleskhan.algo_sieve;

import algorithmia.algorithmia_SieveOfEratosthenes.algorithmia_SieveOfEratosthenes;
import org.junit.Test;

import static org.hamcrest.Matchers.matchesPattern;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class algorithmiaSieveOfEratosthenesTests {

    @Test
    public void zeroLastNumber() {
        algorithmia_SieveOfEratosthenes target = new algorithmia_SieveOfEratosthenes();

        String result = target.apply("0");
        assertThat(result, matchesPattern(
                "Sieve of Eratosthenes \\(last number 0\\): messages - \\[Start calculating prime numbers between 1 and 0, Start Sieve, Number of primes found 0, Sieve took \\d* ms\\] primes - \\[\\]"
        ));
    }

    @Test
    public void oneHundredLastNumber() {
        algorithmia_SieveOfEratosthenes target = new algorithmia_SieveOfEratosthenes();

        String result = target.apply("100");
        assertThat(result, matchesPattern(
                "Sieve of Eratosthenes \\(last number 100\\): messages - \\[Start calculating prime numbers between 1 and 100, Start Sieve, Number of primes found 25, Sieve took \\d* ms\\] primes - \\[2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97\\]"
        ));
    }

    @Test
    public void nullLastNumber() {
        algorithmia_SieveOfEratosthenes target = new algorithmia_SieveOfEratosthenes();

        try {
            target.apply(null);
            fail("A number format exception expected");
        } catch (NumberFormatException nfe) {
            //success
        }
    }

    @Test
    public void emptyStringLastNumber() {
        algorithmia_SieveOfEratosthenes target = new algorithmia_SieveOfEratosthenes();

        try {
            target.apply("");
            fail("A number format exception expected");
        } catch (NumberFormatException nfe) {
            //success
        }
    }

    @Test
    public void nonNumberLastNumber() {
        algorithmia_SieveOfEratosthenes target = new algorithmia_SieveOfEratosthenes();

        try {
            target.apply("a");
            fail("A number format exception expected");
        } catch (NumberFormatException nfe) {
            //success
        }
    }

    @Test
    public void decimalLastNumber() {
        algorithmia_SieveOfEratosthenes target = new algorithmia_SieveOfEratosthenes();

        try {
            target.apply("1.1");
            fail("A number format exception expected");
        } catch (NumberFormatException nfe) {
            //success
        }
    }

}
